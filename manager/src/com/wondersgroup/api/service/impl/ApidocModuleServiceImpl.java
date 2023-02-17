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

import com.wondersgroup.api.bean.ApidocInterface;
import com.wondersgroup.api.bean.ApidocModule;
import com.wondersgroup.api.bean.ApidocModuleView;
import com.wondersgroup.api.dao.ApidocInterfaceDao;
import com.wondersgroup.api.dao.ApidocModInterDao;
import com.wondersgroup.api.dao.ApidocModuleDao;
import com.wondersgroup.api.service.ApidocModuleService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;

/**
 * 模块业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class ApidocModuleServiceImpl implements ApidocModuleService {

	/**
	 * 根据主键 {@link ApidocModule#ST_MODULE_ID}获取模块
	 * 
	 * @param stModuleId
	 *            模块主键 {@link ApidocModule#ST_MODULE_ID}
	 * @return 模块实例
	 */
	@Override
	public ApidocModule get(String stModuleId) {
		if (StringUtils.trimToEmpty(stModuleId).isEmpty())
			throw new NullPointerException("Parameter stModuleId cannot be null.");
		return apidocModuleDao.get(stModuleId);
	}

	/**
	 * 查询模块列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 模块列表
	 */
	@Override
	public PaginationArrayList<ApidocModule> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(ApidocModule.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = "order by NM_ORDER ";
		}
		return apidocModuleDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link ApidocModule#ST_MODULE_ID}删除模块
	 * 
	 * @param stModuleId
	 *            模块主键 {@link ApidocModule#ST_MODULE_ID}
	 */
	public void remove(String[] stModuleId) {
		if (stModuleId.length == 0)
			throw new NullPointerException("Parameter stModuleId cannot be null.");
		// 删除模块及关联子模块
		for (String menuId : stModuleId) {
			// 模块Id不为空的场合
			if (!StringUtils.trimToEmpty(menuId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_MODULE_ID", Condition.OT_EQUAL, menuId));
				List<ApidocInterface> iList = apidocInterfaceDao.queryInterfaceByLink(menuId);
				// 删除用户菜单
				apidocModuleDao.delete(conds);
				if(iList.size() != 0){
					for(ApidocInterface item1: iList){
						//删除接口及模块接口关系表数据
						apidocInterfaceDao.delete(item1.getStInterfaceId());
						apidocModInterDao.delete(null, item1.getStInterfaceId());
					}
				}
			}
		}
	}

	/**
	 * 保存或更新模块
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 模块实例
	 */
	@Override
	public ApidocModule saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// ApidocModule.ST_MODULE_ID
		String stModuleId = wrapper.getParameter(ApidocModule.ST_MODULE_ID);
		ApidocModule oldApidocModule = null;
		if (!StringUtils.trimToEmpty(stModuleId).isEmpty()) {
			oldApidocModule = apidocModuleDao.get(stModuleId);
		}
		if (oldApidocModule == null) {// new
			ApidocModule newApidocModule = (ApidocModule) t4r.toBean(ApidocModule.class);
			newApidocModule.setStModuleId(UUID.randomUUID().toString());
			newApidocModule.setDtCreate(new Timestamp(System.currentTimeMillis()));
			apidocModuleDao.add(newApidocModule);
			return newApidocModule;
		}else {// update
			oldApidocModule = (ApidocModule) t4r.toBean(oldApidocModule, ApidocModule.class);
			apidocModuleDao.update(oldApidocModule);
			return oldApidocModule;
		}
	}
	/**
	 * 根据模块ID查询模块信息和模块下的所有接口信息
	 * 
	 * @param stModuleId
	 * @return
	 */
	public ApidocModuleView getModuleAndInterface(String stModuleId){
		Conditions conds = Conditions.newAndConditions();
		ApidocModuleView view = new ApidocModuleView();
	    conds.add(new Condition("ST_PARENT_ID", Condition.OT_EQUAL, stModuleId));
	    ApidocModule moduleInfo = apidocModuleDao.get(stModuleId);
	    //父模块名和说明
	    view.setStModuleName(moduleInfo.getStModuleName());
	    view.setStRemark(moduleInfo.getStRemark());
	    //获取父模块下接口（可以没有）
	    List<ApidocInterface> iList = apidocInterfaceDao.queryInterfaceByLink(stModuleId);
	    if(null != iList && iList.size() != 0){
	    	view.setApidocInterfaces(iList);
	    }
	    //获取子模块及模块下接口
	    List<ApidocModule> list = apidocModuleDao.query(conds, "order by NM_ORDER ");
	    List<ApidocModuleView> childlViewList = new ArrayList<ApidocModuleView>();
	    for(ApidocModule temp: list){
	    	ApidocModuleView childlView = new ApidocModuleView();
	    	ApidocModule childModuleInfo = apidocModuleDao.get(temp.getStModuleId());
	    	childlView.setStModuleName(childModuleInfo.getStModuleName());
	    	childlView.setStRemark(childModuleInfo.getStRemark());
	    	List<ApidocInterface> iChildList = apidocInterfaceDao.queryInterfaceByLink(temp.getStModuleId());
	    	childlView.setApidocInterfaces(iChildList);
	    	childlViewList.add(childlView);
	    }
	    view.setSonApidocModuleView(childlViewList);
	    return view;
	}
	@Autowired
	private ApidocModuleDao apidocModuleDao;
	@Autowired
	private ApidocModInterDao apidocModInterDao;
	@Autowired
	private ApidocInterfaceDao apidocInterfaceDao;

}
