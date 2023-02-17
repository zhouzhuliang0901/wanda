package com.wondersgroup.wdf.service.impl;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import com.wondersgroup.wdf.dao.UacItemsLinkTwo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;
import wfc.service.database.Sequence;

import com.wondersgroup.wdf.dao.UacItemGroupLinkTwo;
import com.wondersgroup.wdf.dao.UacItemGroupLinkTwoDao;
import com.wondersgroup.wdf.service.UacItemGroupLinkTwoService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.base.util.StringUtil;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 组关联事项业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacItemGroupLinkTwoServiceImpl implements UacItemGroupLinkTwoService {

	/**
	 * 根据主键 {@link UacItemGroupLinkTwo#ST_ITEM_ID} {@link UacItemGroupLinkTwo#ST_GROUP_ID}获取组关联事项
	 * 
	 * @param stItemId
	 *            组关联事项主键 {@link UacItemGroupLinkTwo#ST_ITEM_ID}
	 * @param stGroupId
	 *            组关联事项主键 {@link UacItemGroupLinkTwo#ST_GROUP_ID}
	 * @return 组关联事项实例
	 */
	@Override
	public UacItemGroupLinkTwo get(String stItemId, String stGroupId) {
		if (StringUtils.trimToEmpty(stItemId).isEmpty())
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (StringUtils.trimToEmpty(stGroupId).isEmpty())
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		return uacItemGroupLinkTwoDao.get(stItemId, stGroupId);
	}

	/**
	 * 查询组关联事项列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 组关联事项列表
	 */
	@Override
	public PaginationArrayList<UacItemGroupLinkTwo> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacItemGroupLinkTwo.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacItemGroupLinkTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacItemGroupLinkTwo#ST_ITEM_ID} {@link UacItemGroupLinkTwo#ST_GROUP_ID}删除组关联事项
	 * 
	 * @param stItemId
	 *            组关联事项主键 {@link UacItemGroupLinkTwo#ST_ITEM_ID}
	 * @param stGroupId
	 *            组关联事项主键 {@link UacItemGroupLinkTwo#ST_GROUP_ID}
	 */
	@Override
	public void remove(String[] stItemId, String[] stGroupId) {
		if (stItemId.length == 0)
			throw new NullPointerException("Parameter stItemId cannot be null.");
		if (stGroupId.length == 0)
			throw new NullPointerException("Parameter stGroupId cannot be null.");
		uacItemGroupLinkTwoDao.delete(stItemId, stGroupId);
	}

	/**
	 * 保存或更新组关联事项
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 组关联事项实例
	 */
	@Override
	public UacItemGroupLinkTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItemGroupLinkTwo.ST_ITEM_ID
		String stItemId = wrapper.getParameter(UacItemGroupLinkTwo.ST_ITEM_ID);
		// UacItemGroupLinkTwo.ST_GROUP_ID
		String stGroupId = wrapper.getParameter(UacItemGroupLinkTwo.ST_GROUP_ID);
		UacItemGroupLinkTwo oldUacItemGroupLinkTwo = null;
		if (!StringUtils.trimToEmpty(stItemId).isEmpty() && !StringUtils.trimToEmpty(stGroupId).isEmpty()) {
			oldUacItemGroupLinkTwo = uacItemGroupLinkTwoDao.get(stItemId, stGroupId);
		}
		if (oldUacItemGroupLinkTwo == null) {// new
			UacItemGroupLinkTwo newUacItemGroupLinkTwo = (UacItemGroupLinkTwo) t4r.toBean(UacItemGroupLinkTwo.class);
			newUacItemGroupLinkTwo.setStItemId(UUID.randomUUID().toString());
			newUacItemGroupLinkTwo.setStGroupId(UUID.randomUUID().toString());
			uacItemGroupLinkTwoDao.add(newUacItemGroupLinkTwo);
			return newUacItemGroupLinkTwo;
		}else {// update
			oldUacItemGroupLinkTwo = (UacItemGroupLinkTwo) t4r.toBean(oldUacItemGroupLinkTwo, UacItemGroupLinkTwo.class);
			uacItemGroupLinkTwoDao.update(oldUacItemGroupLinkTwo);
			return oldUacItemGroupLinkTwo;
		}
	}

	/**
	 * 根据一个事项组id保存多个组关联事项
	 */
	@Override
	public UacItemGroupLinkTwo saveitem(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		//多个事项id
		String[] stItemId = wrapper.getParameterValues("ST_ITEM_ID[]");
		//序号
		//String nmOrder = wrapper.getParameter(UacItemsLinkTwo.NM_ORDER);
		//事项组id
		String stGroupId = wrapper.getParameter("ST_GROUP_ID");
		UacItemGroupLinkTwo uacItemGroupLinkTwo = new UacItemGroupLinkTwo();
		if(stItemId.length != 0){
			for (String s : stItemId) {
				uacItemGroupLinkTwo.setStItemId(s);
				/*uacItemsLinkTwo.setNmOrder(new BigDecimal(nmOrder));
				uacItemsLinkTwo.setNmMust(new BigDecimal(nmMust));*/
				uacItemGroupLinkTwo.setStGroupId(stGroupId);
				uacItemGroupLinkTwoDao.add(uacItemGroupLinkTwo);
			}
		}
		return null;
	}

	@Autowired
	private UacItemGroupLinkTwoDao uacItemGroupLinkTwoDao;

}
