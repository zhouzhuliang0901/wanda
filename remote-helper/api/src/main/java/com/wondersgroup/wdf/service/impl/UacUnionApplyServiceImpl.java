package com.wondersgroup.wdf.service.impl;

import com.wondersgroup.wdf.dao.UacUnionApply;
import com.wondersgroup.wdf.dao.UacUnionApplyDao;
import com.wondersgroup.wdf.service.UacUnionApplyService;
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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * 综合受理业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUnionApplyServiceImpl implements UacUnionApplyService {

	/**
	 * 根据主键 {@link UacUnionApply#ST_APPLY_ID}获取综合受理
	 * 
	 * @param stApplyId
	 *            综合受理主键 {@link UacUnionApply#ST_APPLY_ID}
	 * @return 综合受理实例
	 */
	@Override
	public UacUnionApply get(String stApplyId) {
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		return uacUnionApplyDao.get(stApplyId);
	}

	/**
	 * 查询综合受理列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 综合受理列表
	 */
	@Override
	public PaginationArrayList<UacUnionApply> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = "order by DT_CREATE desc";
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUnionApply.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
		}
		conds.add(new Condition(UacUnionApply.NM_STATUS,Condition.OT_LESS_EQUAL , 5));
		return uacUnionApplyDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUnionApply#ST_APPLY_ID}删除综合受理
	 * 
	 * @param stApplyId
	 *            综合受理主键 {@link UacUnionApply#ST_APPLY_ID}
	 */
	@Override
	public void remove(String stApplyId) {
		if (StringUtils.trimToEmpty(stApplyId).isEmpty())
			throw new NullPointerException("Parameter stApplyId cannot be null.");
		uacUnionApplyDao.delete(stApplyId);
	}

	/**
	 * 保存或更新综合受理
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 综合受理实例
	 */
	@Override
	public UacUnionApply saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUnionApply.ST_APPLY_ID
		String stApplyId = wrapper.getParameter(UacUnionApply.ST_APPLY_ID);
		UacUnionApply oldUacUnionApply = null;
		if (!StringUtils.trimToEmpty(stApplyId).isEmpty()) {
			oldUacUnionApply = uacUnionApplyDao.get(stApplyId);
		}
		if (oldUacUnionApply == null) {// new
			UacUnionApply newUacUnionApply = (UacUnionApply) t4r.toBean(UacUnionApply.class);
			newUacUnionApply.setStApplyId(UUID.randomUUID().toString());
			uacUnionApplyDao.add(newUacUnionApply);
			return newUacUnionApply;
		}else {// update
			oldUacUnionApply = (UacUnionApply) t4r.toBean(oldUacUnionApply, UacUnionApply.class);
			uacUnionApplyDao.update(oldUacUnionApply);
			return oldUacUnionApply;
		}
	}

	/**
	 * 解析json
	 * @param jsonObject
	 * @return UacUnionApply
	 */
	@Override
	public UacUnionApply geuUnionAppByJSON(JSONObject jsonObject) {
		String st_sno = jsonObject.optString("stSno",null);
		String st_citem_name = jsonObject.optString("stCitemName",null);
		String stApplyId = jsonObject.optString("stApplyId",null);
		String stUserId = jsonObject.optString("stUserId",null);
		String stApplyNo = jsonObject.optString("stApplyNo",null);
		String st_citem_id = jsonObject.optString("stCitemId",null);
		String stCitemCode = jsonObject.optString("stCitemCode",null);
		String st_apply_state = jsonObject.optString("stApplyState","0");
		String st_target = jsonObject.optString("stTarget", null);
		String st_baddress = jsonObject.optString("stBaddress", null);
		String st_tel = jsonObject.optString("stTel", null);
		String st_apply_user = jsonObject.optString("stApplyUser", null);
		String st_apply_user_phone = jsonObject.optString("stApplyUserPhone", null);
		String st_identity_no = jsonObject.optString("stIdentityNo", null);
		String st_legal_name = jsonObject.optString("stLegalName", null);
		String st_legal_idcard = jsonObject.optString("stLegalIdcard", null);
		String st_legal_tel = jsonObject.optString("stLegalTel", null);
		String st_license_no = jsonObject.optString("stLicenseNo", null);
		String nm_logistics = jsonObject.optString("nmLogistics", "0");
//		String st_ltype = jsonObject.optString("stLtype", null);
		String nmIdentityType = jsonObject.optString("nmIdentityType", "身份证");
//		String stTransactName = jsonObject.optString("stTransactName", null);
		String stCitemName = jsonObject.optString("stCitemName", null);
		String dtAudit = jsonObject.optString("dtAudit", null);
		String dtUpdate = jsonObject.optString("dtUpdate", null);
		String stOrganId = jsonObject.optString("stOrganId", null);
		String stDepartCode = jsonObject.optString("stDepartCode", null);
		String stDepartName = jsonObject.optString("stDepartName", null);
		String stReceivorId = jsonObject.optString("stReceivorId", null);
		String stReceivorName = jsonObject.optString("stReceivorName", null);
		String stAreaId = jsonObject.optString("stAreaId", null);
		String stAreaCode = jsonObject.optString("stAreaCode", null);
//		String stAreaName = jsonObject.optString("stAreaName", null);
		String stApplyUser = jsonObject.optString("stApplyUser", null);
		String stApplyUserPhone = jsonObject.optString("stApplyUserPhone", null);
		String nmIsCancel = jsonObject.optString("nmIsCancel", null);
		String stTsource = jsonObject.optString("stTsource", null);
		String nmSource = jsonObject.optString("nmSource", null);
		String nmImt = jsonObject.optString("nmImt", null);
		String nmUapply = jsonObject.optString("nmUapply", null);
		String stCertificationType = jsonObject.optString("stCertificationType", null);
		String nmCertificationStatus = jsonObject.optString("nmCertificationStatus", null);
		String stLtype = jsonObject.optString("stLtype", null);
		String dtCertification = jsonObject.optString("dtCertification", null);
		String dtCreate = jsonObject.optString("dtCreate", null);
		String nmRemoved = jsonObject.optString("nmRemoved", null);
		String dtLookLast = jsonObject.optString("dtLookLast", null);
		String nmLook = jsonObject.optString("nmLook", null);
		String stExt1 = jsonObject.optString("stExt1", null);
		String stExt2 = jsonObject.optString("stExt2", null);
		String nmLicenseType = jsonObject.optString("nmLicenseType", null);//办理对象证件类型

		UacUnionApply uacUnionApply = new UacUnionApply();
		if (stApplyId == null){
			stApplyId = UUID.randomUUID().toString();
		}
		if (dtCertification != null) {
			uacUnionApply.setDtCertification(Timestamp.valueOf(dtCertification));
		}
		if (dtCreate!= null) {
			uacUnionApply.setDtCreate(Timestamp.valueOf(dtCreate));
		}
		if (dtLookLast!= null) {
			uacUnionApply.setDtLookLast(Timestamp.valueOf(dtLookLast));
		}
		uacUnionApply.setStOrganId(stOrganId);
		uacUnionApply.setStExt1(stExt1);
		uacUnionApply.setStExt2(stExt2);
//		uacUnionApply.setStLtype(stLtype);
		uacUnionApply.setStCertificationType(stCertificationType);
		uacUnionApply.setStApplyUserPhone(stApplyUserPhone);
		if (nmIsCancel != null)
		uacUnionApply.setNmIsCancel(new BigDecimal(nmIsCancel));
		if (nmLook != null)
		uacUnionApply.setNmLook(new BigDecimal(nmLook));
		if (nmRemoved != null)
		uacUnionApply.setNmRemoved(new BigDecimal(nmRemoved));
		if (nmCertificationStatus != null)
		uacUnionApply.setNmCertificationStatus(new BigDecimal(nmCertificationStatus));
		if (nmUapply != null)
		uacUnionApply.setNmUapply(new BigDecimal(nmUapply));
		if (nmSource != null)
		uacUnionApply.setNmSource(new BigDecimal(nmSource));
		if (nmImt != null)
		uacUnionApply.setNmImt(new BigDecimal(nmImt));
		uacUnionApply.setStAreaId(stAreaId);
		uacUnionApply.setStTsource(stTsource);
		uacUnionApply.setStAreaCode(stAreaCode);
		uacUnionApply.setStApplyUser(stApplyUser);
		uacUnionApply.setStReceivorId(stReceivorId);
		uacUnionApply.setStReceivorName(stReceivorName);
		uacUnionApply.setStDepartCode(stDepartCode);
		uacUnionApply.setStDepartName(stDepartName);
		uacUnionApply.setStApplyId(stApplyId);
		uacUnionApply.setStSno(st_sno);
		uacUnionApply.setStCitemName(st_citem_name);
		uacUnionApply.setStCitemId(st_citem_id);
		uacUnionApply.setStApplyState(st_apply_state);
		uacUnionApply.setStTarget(st_target);
		uacUnionApply.setStBaddress(st_baddress);
		uacUnionApply.setStTel(st_tel);
		uacUnionApply.setStApplyUser(st_apply_user);
		uacUnionApply.setStApplyUserPhone(st_apply_user_phone);
		uacUnionApply.setStIdentityNo(st_identity_no);
		uacUnionApply.setStLegalName(st_legal_name);
		uacUnionApply.setStLegalIdcard(st_legal_idcard);
		uacUnionApply.setStLegalTel(st_legal_tel);
		uacUnionApply.setStLicenseNo(st_license_no);
		if (nm_logistics != null)
		uacUnionApply.setNmLogistics(new BigDecimal(nm_logistics));
		uacUnionApply.setStLtype(nm_logistics);
		uacUnionApply.setNmIdentityType(new BigDecimal(1));
		uacUnionApply.setNmTargetType("0");
		uacUnionApply.setStApplyNo(stApplyNo);
		uacUnionApply.setNmLicenseType(nmIdentityType);
//		uacUnionApply.setStTransactName(stTransactName);
		uacUnionApply.setStCitemName(stCitemName);
		if (dtAudit!= null) {
			uacUnionApply.setDtAudit(Timestamp.valueOf(dtAudit));
		}
		if (dtUpdate!= null) {
			uacUnionApply.setDtUpdate(Timestamp.valueOf(dtUpdate));
		}
		uacUnionApply.setNmLicenseType(nmLicenseType);

		Calendar calendar = Calendar.getInstance();
		calendar.set (Calendar.SECOND, 0);
		calendar.set (Calendar.MILLISECOND, 0);
//		uacUnionApply.setDtCreate(new Timestamp(calendar.getTimeInMillis()));
//		uacUnionApply.setStApplyState("0");
		System.out.println("************************** uacUnionApply字段解析完成****************************");
		return uacUnionApply;
	}

	@Override
	public List<UacUnionApply> queryRece(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUnionApply.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUnionApplyDao.queryRece(conds, suffix, pageSize, currentPage);
	}

	@Autowired
	private UacUnionApplyDao uacUnionApplyDao;

}
