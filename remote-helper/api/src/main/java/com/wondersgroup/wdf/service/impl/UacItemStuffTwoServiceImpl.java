package com.wondersgroup.wdf.service.impl;

import com.wondersgroup.wdf.dao.UacItemStuffTwo;
import com.wondersgroup.wdf.dao.UacItemStuffTwoDao;
import com.wondersgroup.wdf.service.UacItemStuffTwoService;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Condition;
import wfc.service.database.Conditions;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * 事项材料业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacItemStuffTwoServiceImpl implements UacItemStuffTwoService {

	/**
	 * 根据主键 {@link UacItemStuffTwo#ST_ITEM_STUFF_ID}获取事项材料
	 * 
	 * @param stItemStuffId
	 *            事项材料主键 {@link UacItemStuffTwo#ST_ITEM_STUFF_ID}
	 * @return 事项材料实例
	 */
	@Override
	public UacItemStuffTwo get(String stItemStuffId) {
		if (StringUtils.trimToEmpty(stItemStuffId).isEmpty())
			throw new NullPointerException("Parameter stItemStuffId cannot be null.");
		return uacItemStuffTwoDao.get(stItemStuffId);
	}

	/**
	 * 查询事项材料列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 事项材料列表
	 */
	@Override
	public PaginationArrayList<UacItemStuffTwo> query(RequestWrapper wrapper) {
		Conditions conds;
		//事项id
		String stItemId = wrapper.getParameter("ST_ITEM_ID");
		// 是否删除状态为0
		String nmRemoved = "0";
		// 排序
		String suffix = "ORDER BY NM_ORDER2";
		// 获取数据
		conds = Conditions.newAndConditions();
		if (!StringUtils.trim(nmRemoved).isEmpty()) {
			conds.add(new Condition("NM_REMOVED", Condition.OT_EQUAL,
					nmRemoved));
		}

		if (stItemId != null) {
			if (!StringUtils.trim(stItemId).isEmpty()) {
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
						stItemId));
			}
		}

		int pageSize;
		int currentPage;
		Page page = EasyUIHelper.getPage(wrapper);
		pageSize = page.getPageSize();
		currentPage = page.getCurrentPage();
		return uacItemStuffTwoDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 查询事项材料列表
	 *
	 * @param wrapper
	 *            查询条件
	 * @return 事项材料列表
	 */
	@Override
	public List<UacItemStuffTwo> queryStuff(RequestWrapper wrapper) {
		Conditions conds;
		//事项id
		String stItemId = wrapper.getParameter("ST_ITEM_ID");
		// 是否删除状态为0
		String nmRemoved = "0";
		// 排序
		String suffix = "ORDER BY NM_ORDER2";
		// 获取数据
		conds = Conditions.newAndConditions();
		if (!StringUtils.trim(nmRemoved).isEmpty()) {
			conds.add(new Condition("NM_REMOVED", Condition.OT_EQUAL,
					nmRemoved));
		}

		if (stItemId != null) {
			if (!StringUtils.trim(stItemId).isEmpty()) {
				conds.add(new Condition("ST_ITEM_ID", Condition.OT_EQUAL,
						stItemId));
			}
		}
		return uacItemStuffTwoDao.query(conds, suffix);
	}

	/**
	 * 根据主键 {@link UacItemStuffTwo#ST_ITEM_STUFF_ID}删除事项材料
	 * 
	 * @param stItemStuffId
	 *            事项材料主键 {@link UacItemStuffTwo#ST_ITEM_STUFF_ID}
	 */
	@Override
	public void remove(String stItemStuffId) {
		if (StringUtils.trimToEmpty(stItemStuffId).isEmpty())
			throw new NullPointerException("Parameter stItemStuffId cannot be null.");
		uacItemStuffTwoDao.delete(stItemStuffId);
	}

	/**
	 * 保存或更新事项材料
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 事项材料实例
	 */
	@Override
	public UacItemStuffTwo saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacItemStuffTwo.ST_ITEM_STUFF_ID
		String stItemStuffId = wrapper.getParameter(UacItemStuffTwo.ST_ITEM_STUFF_ID);
		UacItemStuffTwo oldUacItemStuffTwo = null;
		if (!StringUtils.trimToEmpty(stItemStuffId).isEmpty()) {
			oldUacItemStuffTwo = uacItemStuffTwoDao.get(stItemStuffId);
		}
		if (oldUacItemStuffTwo == null) {// new
			UacItemStuffTwo newUacItemStuffTwo = (UacItemStuffTwo) t4r.toBean(UacItemStuffTwo.class);
			newUacItemStuffTwo.setStItemStuffId(UUID.randomUUID().toString());
			newUacItemStuffTwo.setNmRemoved(new BigDecimal(0));
			uacItemStuffTwoDao.add(newUacItemStuffTwo);
			return newUacItemStuffTwo;
		}else {// update
			oldUacItemStuffTwo = (UacItemStuffTwo) t4r.toBean(oldUacItemStuffTwo, UacItemStuffTwo.class);
			uacItemStuffTwoDao.update(oldUacItemStuffTwo);
			return oldUacItemStuffTwo;
		}
	}

	@Override
	public void logicDelete(String[] stItemStuffId) {
		if (stItemStuffId.length == 0)
			throw new NullPointerException("Parameter stItemStuffId cannot be null.");
		uacItemStuffTwoDao.logicDelete(stItemStuffId);

	}

	@Autowired
	private UacItemStuffTwoDao uacItemStuffTwoDao;

}
