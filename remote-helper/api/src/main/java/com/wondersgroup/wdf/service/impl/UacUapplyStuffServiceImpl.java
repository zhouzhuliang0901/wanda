package com.wondersgroup.wdf.service.impl;

import com.wondersgroup.wdf.dao.UacUapplyStuff;
import com.wondersgroup.wdf.dao.UacUapplyStuffDao;
import com.wondersgroup.wdf.service.UacUapplyStuffService;
import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
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
 * 综合受理电子材料业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUapplyStuffServiceImpl implements UacUapplyStuffService {

	/**
	 * 根据主键 {@link UacUapplyStuff#ST_ESTUFF_ID}获取综合受理电子材料
	 * 
	 * @param stEstuffId
	 *            综合受理电子材料主键 {@link UacUapplyStuff#ST_ESTUFF_ID}
	 * @return 综合受理电子材料实例
	 */
	@Override
	public UacUapplyStuff get(String stEstuffId) {
		if (StringUtils.trimToEmpty(stEstuffId).isEmpty())
			throw new NullPointerException("Parameter stEstuffId cannot be null.");
		return uacUapplyStuffDao.get(stEstuffId);
	}

	/**
	 * 查询综合受理电子材料列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理电子材料列表
	 */
	@Override
	public PaginationArrayList<UacUapplyStuff> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUapplyStuff.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUapplyStuffDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 查询已上传材料列表
	 *
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理电子材料列表
	 */
	@Override
	public List<UacUapplyStuff> queryAttach(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		String stApplyId = wrapper.getParameter("ST_APPLY_ID");

		conds.add(new Condition(UacUapplyStuff.ST_APPLY_ID, Condition.OT_EQUAL, stApplyId));
		return uacUapplyStuffDao.query(conds, suffix);
	}


	/**
	 * 根据主键 {@link UacUapplyStuff#ST_ESTUFF_ID}删除综合受理电子材料
	 * 
	 * @param stEstuffId
	 *            综合受理电子材料主键 {@link UacUapplyStuff#ST_ESTUFF_ID}
	 */
	@Override
	public void remove(String stEstuffId) {
		if (StringUtils.trimToEmpty(stEstuffId).isEmpty())
			throw new NullPointerException("Parameter stEstuffId cannot be null.");
		uacUapplyStuffDao.delete(stEstuffId);
	}

	/**
	 * 保存或更新综合受理电子材料
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合受理电子材料实例
	 */
	@Override
	public UacUapplyStuff saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUapplyStuff.ST_ESTUFF_ID
		String stEstuffId = wrapper.getParameter(UacUapplyStuff.ST_ESTUFF_ID);
		UacUapplyStuff oldUacUapplyStuff = null;
		if (!StringUtils.trimToEmpty(stEstuffId).isEmpty()) {
			oldUacUapplyStuff = uacUapplyStuffDao.get(stEstuffId);
		}
		if (oldUacUapplyStuff == null) {// new
			UacUapplyStuff newUacUapplyStuff = (UacUapplyStuff) t4r.toBean(UacUapplyStuff.class);
			newUacUapplyStuff.setStEstuffId(UUID.randomUUID().toString());
			uacUapplyStuffDao.add(newUacUapplyStuff);
			return newUacUapplyStuff;
		}else {// update
			oldUacUapplyStuff = (UacUapplyStuff) t4r.toBean(oldUacUapplyStuff, UacUapplyStuff.class);
			uacUapplyStuffDao.update(oldUacUapplyStuff);
			return oldUacUapplyStuff;
		}
	}

	@Override
	public UacUapplyStuff getUacUapplyStuffByJSON(JSONObject jsonObject) {
//		String st_apply_id = jsonObject.optString("ST_APPLY_ID");
		String st_cstuff_id = jsonObject.optString("ST_CSTUFF_ID");
		String st_desc = jsonObject.optString("ST_DESC");
		String st_opinion = jsonObject.optString("ST_OPINION","正常");
		int nm_status = jsonObject.optInt("NM_STATUS", 1);
		UacUapplyStuff uacUapplyStuff = new UacUapplyStuff();
		uacUapplyStuff.setStEstuffId(UUID.randomUUID().toString());
//		uacUapplyStuff.setStApplyId(st_apply_id);
		uacUapplyStuff.setStCstuffId(st_cstuff_id);
		uacUapplyStuff.setStDesc(st_desc);
		uacUapplyStuff.setStOpinion(st_opinion);
		uacUapplyStuff.setNmStatus(new BigDecimal(nm_status));
		System.out.println("************************** UacUapplyStuff 字段解析完成****************************");
		return uacUapplyStuff;
	}

	@Override
	public UacUapplyStuff geteditUacUapplyStuffByJSON(JSONObject jsonObject) {
		String st_apply_id = jsonObject.optString("ST_APPLY_ID");
		String st_cstuff_id = jsonObject.optString("ST_CSTUFF_ID");
		String st_desc = jsonObject.optString("ST_DESC");
		String st_opinion = jsonObject.optString("ST_OPINION","正常");
		int nm_status = jsonObject.optInt("NM_STATUS", 1);
		UacUapplyStuff uacUapplyStuff = new UacUapplyStuff();
		uacUapplyStuff.setStEstuffId(UUID.randomUUID().toString());
		uacUapplyStuff.setStApplyId(st_apply_id);
		uacUapplyStuff.setStCstuffId(st_cstuff_id);
		uacUapplyStuff.setStDesc(st_desc);
		uacUapplyStuff.setStOpinion(st_opinion);
		uacUapplyStuff.setNmStatus(new BigDecimal(nm_status));
		System.out.println("************************** UacUapplyStuff 字段解析完成****************************");
		return uacUapplyStuff;
	}

	@Autowired
	private UacUapplyStuffDao uacUapplyStuffDao;

}
