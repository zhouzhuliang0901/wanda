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
	 * ???????????? {@link SmsMenu#ST_MENU_ID}?????????????????????
	 * 
	 * @param stMenuId
	 *            ????????????????????? {@link SmsMenu#ST_MENU_ID}
	 * @return ?????????????????????
	 */
	@Override
	public SmsMenu get(String stMenuId) {
		if (StringUtils.trimToEmpty(stMenuId).isEmpty())
			throw new NullPointerException("Parameter stMenuId cannot be null.");
		return smsMenuDao.get(stMenuId);
	}

	/**
	 * ???????????????????????????
	 * 
	 * @param wrapper
	 *            ????????????
	 * @return ?????????????????????
	 */
	@Override
	public PaginationArrayList<SmsMenu> query(RequestWrapper wrapper) {
		Conditions conds = null;
		// ????????????
		String menuName = wrapper.getParameter("menuName");
		// ??????????????????
		String startDate = wrapper.getParameter("startDate");
		// ????????????
		String endDate = wrapper.getParameter("endDate");
		
		// ????????????
		String orderName = wrapper.getParameter
				("columns["+wrapper.getParameter("order[0][column]")+"][data]");
		// ????????????
		String orderType = wrapper.getParameter("order[0][dir]");
		// ??????
		String suffix = "ORDER BY NM_ORDER";
		// ???????????????
		if (orderName != null) {
			// ????????????
			if ("stMenuCode".equals(orderName)) {
				suffix = "ORDER BY ST_MENU_CODE "+orderType.toUpperCase();
			// ????????????
			} else if ("stMenuName".equals(orderName)) {
				suffix = "ORDER BY ST_MENU_NAME "+orderType.toUpperCase();
			// ????????????
			} else if ("stUrl".equals(orderName)){
				suffix = "ORDER BY ST_URL "+orderType.toUpperCase();
			// ????????????
			} else if ("dtCreate".equals(orderName)) {
				suffix = "ORDER BY DT_CREATE "+orderType.toUpperCase();
			} 
			Log.info("orderName:"+orderName+"*****orderType:"+orderType);
		}
		// ????????????
		conds = Conditions.newAndConditions();
		// ???????????????????????????
		if (menuName != null) {
			if (!StringUtils.trim(menuName).isEmpty()) {
				// ????????????
				conds.add(new Condition("ST_MENU_NAME", Condition.OT_LIKE,
						menuName));
			}
		}
		// ???????????????????????????
		if (startDate != null) {
			if (!StringUtils.trim(startDate).isEmpty()) {
				// ????????????
				conds.add(new Condition("DT_CREATE", Condition.OT_GREATER_EQUAL,
						Timestamp.valueOf(startDate + " 00:00:00")));
			}
		}
		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// ????????????
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						Timestamp.valueOf(endDate + " 23:59:59")));
			}
		}		
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			// ????????????
			String length = wrapper.getParameter("length");
			// ????????????
			if (length != null) {
				pageSize = Integer.valueOf(length);
			}
			if (wrapper.getParameter("start") != null) {
				// ?????????
				int start = Integer.valueOf(wrapper.getParameter("start"));
				// ??????????????????
				if ( start != 0) {
					// ?????????
					currentPage = Integer.valueOf(start)/pageSize+1;	
				}
			}
		}
		return smsMenuDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * ???????????? {@link SmsMenu#ST_MENU_ID}?????????????????????
	 * 
	 * @param stMenuId
	 *            ????????????????????? {@link SmsMenu#ST_MENU_ID}
	 */
	@Override
	public void remove(String[] stMenuId) {
		if (stMenuId.length == 0)
			throw new NullPointerException("Parameter stMenuId cannot be null.");
		// ????????????
		smsMenuDao.delete(stMenuId);
		// ??????????????????
		for (String menuId : stMenuId) {
			// ????????????????????????
			if (!StringUtils.trimToEmpty(menuId).isEmpty()) {
				Conditions conds = Conditions.newAndConditions();
				conds.add(new Condition("ST_MENU_ID", Condition.OT_EQUAL, menuId));
				// ??????????????????
				smsUserMenuDao.delete(conds);
				// ??????????????????
				smsRoleMenuDao.delete(conds);
				// ???????????????
				smsGroupMenuDao.delete(conds);
			}
		}
	}

	/**
	 * ??????????????????????????????
	 * 
	 * @param wrapper
	 *            ????????????
	 * @return ?????????????????????
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
			// ????????????
			newSmsMenu.setDtCreate(new Timestamp(System.currentTimeMillis()));
			smsMenuDao.add(newSmsMenu);
			return newSmsMenu;
		}else {// update
			oldSmsMenu = (SmsMenu) t4r.toBean(oldSmsMenu, SmsMenu.class);
			// ????????????
			oldSmsMenu.setDtUpdate(new Timestamp(System.currentTimeMillis()));
			smsMenuDao.update(oldSmsMenu);
			return oldSmsMenu;
		}
	}
	
	/**
	 * ??????????????????
	 */
	@Override
	public boolean checkMenuCode(RequestWrapper wrapper) {
		if (wrapper == null) {
			throw new NullPointerException("??????????????????");
		}
		boolean reslut = false;
		// ????????????
		String menuCode = wrapper.getParameter("menuCode");
		// ??????ID
		String stMenuId = wrapper.getParameter("ST_MENU_ID");
		
		Conditions conds = Conditions.newAndConditions();
		conds.add(new Condition("ST_MENU_CODE", Condition.OT_EQUAL, menuCode));
		// ????????????
		SmsMenu smsMenu = smsMenuDao.checkMenuCode(conds);
		// ????????????????????????????????????
		if (smsMenu != null && !stMenuId.equals(smsMenu.getStMenuId())) {
			reslut = true;
		}
		Log.info("????????????:"+menuCode+",??????ID:"+stMenuId+",????????????:"+String.valueOf(reslut));
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
