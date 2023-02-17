package com.wondersgroup.api.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.api.bean.ApidocAllInfoView;
import com.wondersgroup.api.bean.ApidocInterface;
import com.wondersgroup.api.bean.ApidocModule;
import com.wondersgroup.api.bean.ApidocModuleView;
import com.wondersgroup.api.bean.ApidocProject;
import com.wondersgroup.api.dao.ApidocInterfaceDao;
import com.wondersgroup.api.dao.ApidocModInterDao;
import com.wondersgroup.api.dao.ApidocModuleDao;
import com.wondersgroup.api.dao.ApidocProjectDao;
import com.wondersgroup.api.service.ApidocModuleService;
import com.wondersgroup.api.service.ApidocProjectService;
import com.wondersgroup.sms.user.bean.SmsUser;

import coral.base.app.AppContext;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;

/**
 * 项目业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class ApidocProjectServiceImpl implements ApidocProjectService {

	/**
	 * 根据主键 {@link ApidocProject#ST_PROJECT_ID}获取项目
	 * 
	 * @param stProjectId
	 *            项目主键 {@link ApidocProject#ST_PROJECT_ID}
	 * @return 项目实例
	 */
	@Override
	public ApidocProject get(String stProjectId) {
		if (StringUtils.trimToEmpty(stProjectId).isEmpty())
			throw new NullPointerException(
					"Parameter stProjectId cannot be null.");
		return apidocProjectDao.get(stProjectId);
	}

	/**
	 * 查询项目列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 项目列表
	 */
	@Override
	public PaginationArrayList<ApidocProject> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = " order by NM_ORDER ";
		String stProjectName = wrapper.getParameter("stProjectName");
		String startDate = wrapper.getParameter("startDate");
		String endDate = wrapper.getParameter("endDate");
		// 获取用户
		SmsUser user = (SmsUser) wrapper.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		// session过期过期
		if (user == null) {
			throw new NullPointerException("session 过期，无法获取用户");
		}
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, user
				.getStUserId()));
		if (stProjectName != null) {
			if (!StringUtils.trim(stProjectName).isEmpty()) {
				conds.add(new Condition("ST_PROJECT_NAME", Condition.OT_LIKE,
						stProjectName));
			}
		}
		// 开始时间存在的场合
		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				conds.add(new Condition("DT_CREATE",
						Condition.OT_GREATER_EQUAL, Timestamp.valueOf(startDate + " 00:00:00")));
			}
		}
		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						Timestamp.valueOf(endDate + " 23:59:59")));
			}
		}
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			// 每页长度
			String length = wrapper.getParameter("length");
			// 页面长度
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (wrapper.getParameter("start") != null) {
				// 开始页
				int start = Integer.valueOf(wrapper.getParameter("start"));
				// 第一页的场合
				if (start != 0) {
					// 当前页
					currentPage = Integer.valueOf(start) / pageSize + 1;
				}
			}
			Log.info("每页长度:" + pageSize + "当前页:" + currentPage);
		}
		return apidocProjectDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link ApidocProject#ST_PROJECT_ID}删除项目
	 * 
	 * @param stProjectId
	 *            项目主键 {@link ApidocProject#ST_PROJECT_ID}
	 */
	@Override
	public void remove(String[] stProjectId) {
		if (stProjectId.length == 0)
			throw new NullPointerException("Parameter stTextId cannot be null.");
		// 删除
		for (String Id : stProjectId) {
			// ID不为空的场合
			if (!StringUtils.trimToEmpty(Id).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_PROJECT_ID", Condition.OT_EQUAL, Id));
				// 删除项目
				apidocProjectDao.delete(conds);
				// 删除模块目录先查询出接口列表
				List<ApidocModule> mlist = apidocModuleDao.query(conds, null);
				for (ApidocModule item : mlist) {
					List<ApidocInterface> iList = apidocInterfaceDao
							.queryInterfaceByLink(item.getStModuleId());
					apidocModuleDao.delete(item.getStModuleId());
					// 删除接口
					for (ApidocInterface item1 : iList) {
						apidocInterfaceDao.delete(item1.getStInterfaceId());
						// 删除关联表数据
						apidocModInterDao
								.delete(null,item1.getStInterfaceId());
					}
				}

			}
		}
	}

	/**
	 * 保存或更新项目
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 项目实例
	 */
	@Override
	public ApidocProject saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// ApidocProject.ST_PROJECT_ID
		String stProjectId = wrapper.getParameter(ApidocProject.ST_PROJECT_ID);
		ApidocProject oldApidocProject = null;
		if (!StringUtils.trimToEmpty(stProjectId).isEmpty()) {
			oldApidocProject = apidocProjectDao.get(stProjectId);
		}
		if (oldApidocProject == null) {// 添加
			ApidocProject newApidocProject = (ApidocProject) t4r
					.toBean(ApidocProject.class);
			// 获取用户
			SmsUser user = (SmsUser) wrapper.getRequest().getSession()
					.getAttribute(AppContext.SESSION_USER);
			// session过期过期
			if (user == null) {
				throw new NullPointerException("session 过期，无法获取用户");
			}
			newApidocProject.setStUserId(user.getStUserId().toString());
			newApidocProject.setDtCreate(new Timestamp(System
					.currentTimeMillis()));
			newApidocProject.setStProjectId(UUID.randomUUID().toString());
			apidocProjectDao.add(newApidocProject);
			return newApidocProject;
		} else {// 更新
			oldApidocProject = (ApidocProject) t4r.toBean(oldApidocProject,
					ApidocProject.class);
			apidocProjectDao.update(oldApidocProject);
			return oldApidocProject;
		}
	}

	/**
	 * 根据stUserId查询项目列表
	 * 
	 */
	@Override
	public List<ApidocProject> queryProjectList(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		// 获取用户
		SmsUser user = (SmsUser) wrapper.getRequest().getSession()
				.getAttribute(AppContext.SESSION_USER);
		// session过期过期
		if (user == null) {
			throw new NullPointerException("session 过期，无法获取用户");
		}
		conds.add(new Condition("ST_USER_ID", Condition.OT_EQUAL, user
				.getStUserId()));

		return apidocProjectDao.query(conds, " order by NM_ORDER ");
	}
	
	/**
	 * 根据stProjectId查询项目下所有模块和接口信息
	 * 
	 * @param stProjectId
	 * @return ApidocAllInfoView 对象
	 */
	public ApidocAllInfoView queryByProjectId(String stProjectId) {
		Conditions conds = Conditions.newAndConditions();
		ApidocAllInfoView allInfo = new ApidocAllInfoView();
		List<ApidocModuleView> mvList = new ArrayList<ApidocModuleView>();
		if (stProjectId != null) {
			ApidocProject pInfo = apidocProjectDao.get(stProjectId);
			//插入项目信息
			allInfo.setStProjectName(pInfo.getStProjectName());
			allInfo.setStRemark(pInfo.getStRemark());
			if (!StringUtils.trim(stProjectId).isEmpty()) {
				conds.add(new Condition("ST_PROJECT_ID", Condition.OT_EQUAL,
						stProjectId));
			}
		}
		//获取模块列表
		List<ApidocModule> mList = apidocModuleDao.query(conds, "and ST_PARENT_ID is null order by NM_ORDER ");
		for(ApidocModule item: mList){
			//得到所有模块和模块下的接口信息
			ApidocModuleView mInfo =  apidocModuleService.getModuleAndInterface(item.getStModuleId());
			mvList.add(mInfo);
		}
		allInfo.setApidocModuleViewList(mvList);
		return allInfo;
	}

	@Autowired
	private ApidocProjectDao apidocProjectDao;
	@Autowired
	private ApidocModuleDao apidocModuleDao;
	@Autowired
	private ApidocInterfaceDao apidocInterfaceDao;
	@Autowired
	private ApidocModInterDao apidocModInterDao;
	@Autowired
	private ApidocModuleService apidocModuleService;

}
