package com.wondersgroup.sms.role.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import coral.widget.utils.EasyUIHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.menu.dao.SmsMenuDao;
import com.wondersgroup.sms.role.bean.SmsRole;
import com.wondersgroup.sms.role.dao.SmsRoleDao;
import com.wondersgroup.sms.role.service.SmsRoleService;
import com.wondersgroup.sms.roleMenu.bean.SmsRoleMenu;
import com.wondersgroup.sms.roleMenu.dao.SmsRoleMenuDao;
import com.wondersgroup.sms.userRole.dao.SmsUserRoleDao;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;

/**
 * 角色表业务实现
 * 
 */
@Service
@Transactional
public class SmsRoleServiceImpl implements SmsRoleService {

	/**
	 * 根据主键 {@link SmsRole#ST_ROLE_ID}获取角色表
	 * 
	 * @param stRoleId
	 *            角色表主键 {@link SmsRole#ST_ROLE_ID}
	 * @return 角色表实例
	 */
	@Override
	public SmsRole get(String stRoleId) {
		if (StringUtils.trimToEmpty(stRoleId).isEmpty())
			throw new NullPointerException("Parameter stRoleId cannot be null.");
		return smsRoleDao.get(stRoleId);
	}

	/**
	 * 查询角色表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 角色表列表
	 */
	@Override
	public PaginationArrayList<SmsRole> query(RequestWrapper wrapper) {
		Conditions conds = null;
		// 角色代码
		String roleCode = wrapper.getParameter("ST_ROLE_CODE");
		//角色名称
		String roleName = wrapper.getParameter("ST_ROLE_NAME");
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");
		
		// 排列内容
		String orderName = wrapper.getParameter("sort");
		// 排序内容
		String orderType = wrapper.getParameter("order");
		// 排序
		String suffix = "";
		// 存在的场合
		if (orderName != null) {
			// 角色代码
			if ("stRoleCode".equals(orderName)) {
				suffix = "ORDER BY ST_ROLE_CODE "+orderType.toUpperCase();
			// 角色名称
			} else if ("stRoleName".equals(orderName)) {
				suffix = "ORDER BY ST_ROLE_NAME "+orderType.toUpperCase();
			// 角色描述
			} else if ("stDesc".equals(orderName)){
				suffix = "ORDER BY ST_DESC "+orderType.toUpperCase();
			// 创建时间
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE "+orderType.toUpperCase();
			} 
		}
		// 获取数据
		conds = Conditions.newAndConditions();
		// 角色代码存在的场合
		if (roleCode != null) {
			if (!StringUtils.trim(roleCode).isEmpty()) {
				// 角色代码
				conds.add(new Condition("ST_ROLE_CODE", Condition.OT_LIKE,
						roleCode));
			}
		}
		//角色名称存在的场合
		if (roleName != null) {
			if (!StringUtils.trim(roleName).isEmpty()) {
				// 角色名称
				conds.add(new Condition("ST_ROLE_NAME", Condition.OT_LIKE,
						roleName));
			}
		}
		// 开始时间存在的场合
		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				// 开始时间
				conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						startDate+" 00:00"));
			}
		}
		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// 结束时间
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						endDate+" 23:59"));
			}
		}
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			EasyUIHelper.Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
		}
		/*int pageSize = Integer.MAX_VALUE / 2;
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
		}*/
		return smsRoleDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SmsRole#ST_ROLE_ID}删除角色表
	 * 
	 * @param stRoleId
	 *            角色表主键 {@link SmsRole#ST_ROLE_ID}
	 */
	@Override
	public void remove(String[] stRoleId) {
		if (stRoleId.length == 0)
			throw new NullPointerException("Parameter stRoleId cannot be null.");
		// 删除角色
		smsRoleDao.delete(stRoleId);
		// 删除相关数据
		for (String roleId : stRoleId) {
			Conditions conds = Conditions.newAndConditions();
			conds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL, roleId));
			// 删除角色菜单
			smsRoleMenuDao.delete(conds);
			// 删除用户菜单
			smsUserRoleDao.delete(conds);
		}
	}

	/**
	 * 保存或更新角色表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 角色表实例
	 */
	@Override
	public SmsRole saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SmsRole.ST_ROLE_ID
		String stRoleId = wrapper.getParameter("ST_ROLE_ID");
		SmsRole oldSmsRole = null;
		// 菜单ID
		String menuIds = wrapper.getParameter("menuIds");
        Conditions conds = Conditions.newAndConditions();
        // 角色ID
        conds.add(new Condition("ST_ROLE_ID", Condition.OT_EQUAL, stRoleId));
        // 删除数据
        smsRoleMenuDao.delete(conds);
		
		if (!StringUtils.trimToEmpty(stRoleId).isEmpty()) {
			oldSmsRole = smsRoleDao.get(stRoleId);
		}
		if (oldSmsRole == null) {// new
			SmsRole newSmsRole = (SmsRole) t4r.toBean(SmsRole.class);
			newSmsRole.setStRoleId(UUID.randomUUID().toString());
			// 创建时间
			newSmsRole.setDtCreate(new Timestamp(System.currentTimeMillis()));
			smsRoleDao.add(newSmsRole);
			
			// 添加角色菜单
			addRoleMenus(menuIds, newSmsRole.getStRoleId());
			return newSmsRole;
		}else {// update
			oldSmsRole = (SmsRole) t4r.toBean(oldSmsRole, SmsRole.class);
			// 修改时间
			oldSmsRole.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			smsRoleDao.update(oldSmsRole);
			
			// 添加角色菜单
			addRoleMenus(menuIds, oldSmsRole.getStRoleId());
			return oldSmsRole;
		}
	}
	
	/**
	 * 添加添加角色菜单
	 * 
	 * @param menuIds 角色ID
	 * @param roleId
	 */
	private void addRoleMenus(String menuIds, String roleId) {
		if (menuIds != null) {
			String[] menuIdArray = menuIds.split(",");
			for (String menuId : menuIdArray) {
				if (!StringUtils.trimToEmpty(menuId).isEmpty()) {
					SmsRoleMenu roleMenu = new SmsRoleMenu();
					// 角色ID
					roleMenu.setStRoleId(roleId);
					// 菜单ID
					roleMenu.setStMenuId(menuId);
					// 添加角色菜单
					smsRoleMenuDao.add(roleMenu);
				}
			}
		}
	}
	
	/**
	 * 获取角色菜单
	 * 
	 * @param stRoleId 角色ID
	 */
	@Override
	public List<SmsMenu> queryRoleMenu(String stRoleId) {
		if (StringUtils.trimToEmpty(stRoleId).isEmpty()) {
			throw new NullPointerException("参数不能为空！");
		}
        Conditions conds = Conditions.newAndConditions();
        conds.add(new Condition("SRM.ST_ROLE_ID", Condition.OT_EQUAL, stRoleId));
		return smsMenuDao.queryByRoleId(conds, "");
	}

	@Autowired
	private SmsRoleDao smsRoleDao;
	
	@Autowired
	private SmsRoleMenuDao smsRoleMenuDao;
	
	@Autowired
	private SmsMenuDao smsMenuDao;
	
	@Autowired
	private SmsUserRoleDao smsUserRoleDao;
}
