package com.wondersgroup.sms.group.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.sms.group.bean.SmsGroup;
import com.wondersgroup.sms.group.dao.SmsGroupDao;
import com.wondersgroup.sms.group.service.SmsGroupService;
import com.wondersgroup.sms.groupMenu.bean.SmsGroupMenu;
import com.wondersgroup.sms.groupMenu.dao.SmsGroupMenuDao;
import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.menu.dao.SmsMenuDao;
import com.wondersgroup.sms.userGroup.dao.SmsUserGroupDao;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;

/**
 * 用户组业务实现
 * 
 * @author guicb
 * 
 */
@Service
@Transactional
public class SmsGroupServiceImpl implements SmsGroupService {

	/**
	 * 根据主键 {@link SmsGroup#ST_GROUP_ID}获取用户组
	 * 
	 * @param stGroupId
	 *            用户组主键 {@link SmsGroup#ST_GROUP_ID}
	 * @return 用户组实例
	 */
	@Override
	public SmsGroup get(String stGroupId) {
		if (StringUtils.trimToEmpty(stGroupId).isEmpty())
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		return smsGroupDao.get(stGroupId);
	}

	/**
	 * 查询用户组列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 用户组列表
	 */
	@Override
	public PaginationArrayList<SmsGroup> query(RequestWrapper wrapper) {
		Conditions conds = null;
		// 用户组别名称
		String stGroupName = wrapper.getParameter("stGroupName");
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");
		
		// 排列内容
		String orderName = wrapper.getParameter
				("columns["+wrapper.getParameter("order[0][column]")+"][data]");
		// 排序内容
		String orderType = wrapper.getParameter("order[0][dir]");
		// 排序
		String suffix = "ORDER BY NM_ORDER";
		// 存在的场合
		if (orderName != null) {
			// 用户组ID
			if ("stGroupId".equals(orderName)) {
				suffix = "ORDER BY ST_GROUP_ID "+orderType.toUpperCase();
			// 排序字段
			} else if ("nmOrder".equals(orderName)) {
				suffix = "ORDER BY NM_ORDER "+orderType.toUpperCase();
			// 用户组编码
			} else if ("stGroupCode".equals(orderName)){
				suffix = "ORDER BY ST_GROUP_CODE "+orderType.toUpperCase();
			// 创建时间
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE "+orderType.toUpperCase();
			// 用户组名称
			} else if ("stGroupName".equals(orderName)) {
				suffix = "ORDER BY 	ST_GROUP_NAME "+orderType.toUpperCase();
			} 
		}
		// 获取数据
		conds = Conditions.newAndConditions();
		// 用户组别名称存在的场合
		if (stGroupName != null) {
			if (!StringUtils.trim(stGroupName).isEmpty()) {
				// 用户组别名称
				conds.add(new Condition("ST_GROUP_NAME", Condition.OT_LIKE,
						stGroupName));
			}
		}
		// 开始时间存在的场合
		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				// 开始时间
				conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						Timestamp.valueOf(startDate + " 00:00:00")));
			}
		}
		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// 结束时间
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
				if ( start != 0) {
					// 当前页
					currentPage = Integer.valueOf(start)/pageSize+1;	
				}
			}
		}
		return smsGroupDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SmsGroup#ST_GROUP_ID}删除用户组
	 * 
	 * @param stGroupId
	 *            用户组主键 {@link SmsGroup#ST_GROUP_ID}
	 */
	@Override
	public void remove(String[] stGroupId) {
		if (stGroupId.length == 0)
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		smsGroupDao.delete(stGroupId);
		
		for (String groupId : stGroupId) {
			// 组别ID存在的场合
			if (!StringUtils.trimToEmpty(groupId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL,groupId));
				// 删除组菜单
				smsGroupMenuDao.delete(conds);
				// 删除用户组
				smsUserGroupDao.delete(conds);
			}
		}
	}

	/**
	 * 保存或更新用户组
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 用户组实例
	 */
	@Override
	public SmsGroup saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SmsGroup.ST_GROUP_ID
		String stGroupId = wrapper.getParameter(SmsGroup.ST_GROUP_ID);
		// 菜单ID
		String menuIds = wrapper.getParameter("menuId");
        Conditions conds = Conditions.newAndConditions();
        // 角色ID
        conds.add(new Condition("ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
        // 删除数据
        smsGroupMenuDao.delete(conds);
		
		SmsGroup oldSmsGroup = null;
		if (!StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			oldSmsGroup = smsGroupDao.get(stGroupId);
		}
		if (oldSmsGroup == null) {// new
			SmsGroup newSmsGroup = (SmsGroup) t4r.toBean(SmsGroup.class);
			newSmsGroup.setStGroupId(UUID.randomUUID().toString());
			// 创建时间
			newSmsGroup.setDtCreate(new Timestamp(System.currentTimeMillis()));
			smsGroupDao.add(newSmsGroup);
			
			// 添加角色菜单
			addGroupMenus(menuIds, newSmsGroup.getStGroupId());
			return newSmsGroup;
		}else {// update
			oldSmsGroup = (SmsGroup) t4r.toBean(oldSmsGroup, SmsGroup.class);
			// 更新时间
			oldSmsGroup.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			smsGroupDao.update(oldSmsGroup);
			// 添加角色菜单
			addGroupMenus(menuIds, oldSmsGroup.getStGroupId());
			return oldSmsGroup;
		}
	}
	
	/**
	 * 查看用户组菜单
	 */
	@Override
	public List<SmsMenu> queryGroupMenu(String stGroupId) {
		// 组ID不存在的场合
		if (StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			throw new NullPointerException("组别ID不存在的场合");
		}
        Conditions conds = Conditions.newAndConditions();
        // 组ID
        conds.add(new Condition("SGM.ST_GROUP_ID", Condition.OT_EQUAL, stGroupId));
		return smsMenuDao.queryByGroupId(conds, "");
	}
	
	/**
	 * 添加角色菜单
	 * 
	 * @param menuIds
	 * @param stGroupId
	 */
	private void addGroupMenus(String menuIds, String stGroupId) {
		//  菜单ID 存在的场合
		if (menuIds != null) {
			String[] menuIdArray = menuIds.split(",");
			for (String menuId : menuIdArray) {
				if (!StringUtils.trimToEmpty(menuId).isEmpty()) {
					SmsGroupMenu groupMenu = new SmsGroupMenu();
					// 角色ID
					groupMenu.setStGroupId(stGroupId);
					// 菜单ID
					groupMenu.setStMenuId(menuId);
					// 保存数据
					smsGroupMenuDao.add(groupMenu);
				}
			}
		}
	}

	@Autowired
	private SmsGroupDao smsGroupDao;
	
	@Autowired
	private SmsMenuDao smsMenuDao;

	@Autowired
	private SmsGroupMenuDao smsGroupMenuDao;
	
	@Autowired
	private SmsUserGroupDao smsUserGroupDao;
}
