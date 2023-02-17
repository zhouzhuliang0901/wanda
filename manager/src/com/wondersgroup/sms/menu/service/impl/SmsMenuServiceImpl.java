package com.wondersgroup.sms.menu.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tw.tool.util.BeanUtils;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;
import wfc.service.log.Log;

import com.wondersgroup.sms.groupMenu.dao.SmsGroupMenuDao;
import com.wondersgroup.sms.menu.bean.SmsMenu;
import com.wondersgroup.sms.menu.dao.SmsMenuDao;
import com.wondersgroup.sms.menu.dao.SmsMenuExtDao;
import com.wondersgroup.sms.menu.service.SmsMenuService;
import com.wondersgroup.sms.menu.view.SmsMenuView;
import com.wondersgroup.sms.roleMenu.dao.SmsRoleMenuDao;
import com.wondersgroup.sms.userMenu.dao.SmsUserMenuDao;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;

@Repository
public class SmsMenuServiceImpl implements SmsMenuService {

	@Autowired
	private SmsMenuExtDao smsMenuExtDao;

	@Override
	public List<SmsMenuView> getSystemAllMenuTree() {
		List<SmsMenu> list = smsMenuExtDao.query(null, null);
		return getTree(list, "sms_menu_root");
	}

	private List<SmsMenuView> getTree(List<SmsMenu> list, String parentCatalogId) {
		List<SmsMenuView> tree = getChildrenTree(list, parentCatalogId);
		Collections.sort(tree, new Comparator<SmsMenuView>() {

			@Override
			public int compare(SmsMenuView o1, SmsMenuView o2) {
				BigDecimal bo1 = o1.getNmOrder() == null ? new BigDecimal("0")
						: o1.getNmOrder();
				BigDecimal bo2 = o2.getNmOrder() == null ? new BigDecimal("0")
						: o2.getNmOrder();
				return bo1.intValue() - bo2.intValue();
			}
		});
		List<SmsMenuView> result = new ArrayList<SmsMenuView>();
		for (int i = 0; i < tree.size(); i++) {
			SmsMenuView view = tree.get(i);
			if (StringUtils.isNotBlank(view.getStParentId())) {
				view.setChildrenList(getTree(list, view.getStMenuId()));
			}
			result.add(view);
		}
		return result;
	}

	private List<SmsMenuView> getChildrenTree(List<SmsMenu> list,
			String parentCatalogId) {
		List<SmsMenuView> result = new ArrayList<SmsMenuView>();
		for (SmsMenu menu : list) {
			if (parentCatalogId.equals(menu.getStParentId())) {
				SmsMenuView view = new SmsMenuView();
				try {
					BeanUtils.copy(menu, view);
				} catch (Exception e) {
					e.printStackTrace();
				}
				result.add(view);
			}
		}
		return result;
	}
	
	/**
	 * 根据主键 {@link SmsMenu#ST_MENU_ID}获取系统菜单表
	 * 
	 * @param stMenuId
	 *            系统菜单表主键 {@link SmsMenu#ST_MENU_ID}
	 * @return 系统菜单表实例
	 */
	@Override
	public SmsMenu get(String stMenuId) {
		if (StringUtils.trimToEmpty(stMenuId).isEmpty())
			throw new NullPointerException("Parameter stMenuId cannot be null.");
		return smsMenuDao.get(stMenuId);
	}

	/**
	 * 查询系统菜单表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 系统菜单表列表
	 */
	@Override
	public PaginationArrayList<SmsMenu> query(RequestWrapper wrapper) {
		Conditions conds = null;
		// 角色名称
		String menuName = wrapper.getParameter("menuName");
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
			// 角色代码
			if ("stMenuCode".equals(orderName)) {
				suffix = "ORDER BY ST_MENU_CODE "+orderType.toUpperCase();
			// 角色名称
			} else if ("stMenuName".equals(orderName)) {
				suffix = "ORDER BY ST_MENU_NAME "+orderType.toUpperCase();
			// 角色描述
			} else if ("stUrl".equals(orderName)){
				suffix = "ORDER BY ST_URL "+orderType.toUpperCase();
			// 创建时间
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE "+orderType.toUpperCase();
			} 
			Log.info("orderName:"+orderName+"*****orderType:"+orderType);
		}
		// 获取数据
		conds = Conditions.newAndConditions();
		// 角色名称存在的场合
		if (menuName != null) {
			if (!StringUtils.trim(menuName).isEmpty()) {
				// 角色名称
				conds.add(new Condition("ST_MENU_NAME", Condition.OT_LIKE,
						menuName));
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
		return smsMenuDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link SmsMenu#ST_MENU_ID}删除系统菜单表
	 * 
	 * @param stMenuId
	 *            系统菜单表主键 {@link SmsMenu#ST_MENU_ID}
	 */
	@Override
	public void remove(String[] stMenuId) {
		if (stMenuId.length == 0)
			throw new NullPointerException("Parameter stMenuId cannot be null.");
		// 删除菜单
		smsMenuDao.delete(stMenuId);
		// 删除关联菜单
		for (String menuId : stMenuId) {
			// 菜单不为空的场合
			if (!StringUtils.trimToEmpty(menuId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_MENU_ID", Condition.OT_EQUAL, menuId));
				// 删除用户菜单
				smsUserMenuDao.delete(conds);
				// 删除角色菜单
				smsRoleMenuDao.delete(conds);
				// 删除组菜单
				smsGroupMenuDao.delete(conds);
			}
		}
	}

	/**
	 * 保存或更新系统菜单表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 系统菜单表实例
	 */
	@Override
	public SmsMenu saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// SmsMenu.ST_MENU_ID
		String stMenuId = wrapper.getParameter(SmsMenu.ST_MENU_ID);
		SmsMenu oldSmsMenu = null;
		if (!StringUtils.trimToEmpty(stMenuId).isEmpty()) {
			oldSmsMenu = smsMenuDao.get(stMenuId);
		}
		if (oldSmsMenu == null) {// new
			SmsMenu newSmsMenu = (SmsMenu) t4r.toBean(SmsMenu.class);
			newSmsMenu.setStMenuId(UUID.randomUUID().toString());
			// 插入时间
			newSmsMenu.setDtCreate(new Timestamp(System.currentTimeMillis()));
			smsMenuDao.add(newSmsMenu);
			return newSmsMenu;
		}else {// update
			oldSmsMenu = (SmsMenu) t4r.toBean(oldSmsMenu, SmsMenu.class);
			// 更新时间
			oldSmsMenu.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			smsMenuDao.update(oldSmsMenu);
			return oldSmsMenu;
		}
	}
	
	/**
	 * 检查资源编号
	 */
	@Override
	public boolean checkMenuCode(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("参数不能为空");
		}
		boolean reslut = false;
		// 资源编号
		String menuCode = wrapper.getParameter("menuCode");
		// 菜单ID
		String stMenuId = wrapper.getParameter("ST_MENU_ID");
		
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_MENU_CODE", Condition.OT_EQUAL, menuCode));
		// 获取菜单
		SmsMenu smsMenu = smsMenuDao.checkMenuCode(conds);
		// 数据存在且不是修改的场合
		if (smsMenu != null && !stMenuId.equals(smsMenu.getStMenuId())) {
			reslut = true;
		}
		Log.info("资源编号:"+menuCode+",菜单ID:"+stMenuId+",是否存在:"+String.valueOf(reslut));
		return reslut;
	}

	@Autowired
	private SmsMenuDao smsMenuDao;
	
	@Autowired
	private SmsUserMenuDao smsUserMenuDao;
	
	@Autowired
	private SmsRoleMenuDao smsRoleMenuDao;
	
	@Autowired
	private SmsGroupMenuDao smsGroupMenuDao;
}
