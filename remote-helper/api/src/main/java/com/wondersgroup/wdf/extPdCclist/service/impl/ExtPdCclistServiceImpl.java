package com.wondersgroup.wdf.extPdCclist.service.impl;

import com.wondersgroup.wdf.extPdCclist.dao.ExtPdCclist;
import com.wondersgroup.wdf.extPdCclist.dao.ExtPdCclistDao;
import com.wondersgroup.wdf.extPdCclist.service.ExtPdCclistService;
import com.wondersgroup.wdf.dao.UacUapplyAttachDaoExt;
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

/**
 * 子证归集表业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class ExtPdCclistServiceImpl implements ExtPdCclistService {

	/**
	 * 根据主键 {@link ExtPdCclist#ST_CCLIST_ID}获取子证归集表
	 * 
	 * @param stCclistId
	 *            子证归集表主键 {@link ExtPdCclist#ST_CCLIST_ID}
	 * @return 子证归集表实例
	 */
	@Override
	public ExtPdCclist get(String stCclistId) {
		if (StringUtils.trimToEmpty(stCclistId).isEmpty())
			throw new NullPointerException("Parameter stCclistId cannot be null.");
		return extPdCclistDao.get(stCclistId);
	}

	/**
	 * 查询子证归集表列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 子证归集表列表
	 */
	@Override
	public PaginationArrayList<ExtPdCclist> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		// 单窗子办件
		String subNo = wrapper.getParameter("ST_SUB_NO");
		// 业态办件流水号
		String applyNo = wrapper.getParameter("ST_APPLY_NO");
		// 所属部门ID
		String organId = wrapper.getParameter("ST_ORGAN_ID");
		// 33位事项编码
		String itemCno = wrapper.getParameter("ST_ITEM_CNO");
		// 事项名称
		String itemNo = wrapper.getParameter("ST_ITEM_NO");
		// 证照目录编号
		String certNo = wrapper.getParameter("ST_CERT_NO");
		// 证照名称
		String certName = wrapper.getParameter("ST_CERT_NAME");
		// 证照许可编号
		String licenseNo = wrapper.getParameter("ST_LICENSE_NO");
		// 制证日期
		String dtMark = wrapper.getParameter("DT_MARK");
		// 社会信用代码
		String corporationOrgid = wrapper.getParameter("ST_CORPORATION_ORGID");
		// 企业名称
		String ename = wrapper.getParameter("ST_ENAME");
		// 状态
		String status = wrapper.getParameter("ST_STATUS");
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");
		//排序
		String suffix = "ORDER BY DT_CREATE DESC";
		// 单窗子办件存在的场合
		if (subNo != null) {
			if (!StringUtils.trim(subNo).isEmpty()) {
				conds.add(new Condition("ST_SUB_NO", Condition.OT_EQUAL,
						subNo));
			}
		}
		//业务办件流水号存在的场合
		if (applyNo != null) {
			if (!StringUtils.trim(applyNo).isEmpty()) {
				conds.add(new Condition("ST_APPLY_NO", Condition.OT_EQUAL,
						applyNo));
			}
		}
		//所属部门ID存在的场合
		if (organId != null) {
			if (!StringUtils.trim(organId).isEmpty()) {
				conds.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL,
						organId));
			}
		}
		//33位事项编码存在的场合
		if (itemCno != null) {
			if (!StringUtils.trim(itemCno).isEmpty()) {
				conds.add(new Condition("ST_ITEM_CNO", Condition.OT_EQUAL,
						itemCno));
			}
		}
		//事项名称存在的场合
		if (itemNo != null) {
			if (!StringUtils.trim(itemNo).isEmpty()) {
				conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE,
						itemNo));
			}
		}
		//证照目录编号存在的场合
		if (certNo != null) {
			if (!StringUtils.trim(certNo).isEmpty()) {
				conds.add(new Condition("ST_CERT_NO", Condition.OT_EQUAL,
						certNo));
			}
		}
		//证照名称存在的场合
		if (certName != null) {
			if (!StringUtils.trim(certName).isEmpty()) {
				conds.add(new Condition("ST_CERT_NAME", Condition.OT_LIKE,
						certName));
			}
		}
		//证照许可编号存在的场合
		if (licenseNo != null) {
			if (!StringUtils.trim(licenseNo).isEmpty()) {
				conds.add(new Condition("ST_LICENSE_NO", Condition.OT_EQUAL,
						licenseNo));
			}
		}
		//制证日期存在的场合
		if (dtMark != null) {
			if (!StringUtils.trim(dtMark).isEmpty()) {
				conds.add(new Condition("DT_MARK", Condition.OT_EQUAL,
						dtMark));
			}
		}
		//社会信用代码存在的场合
		if (corporationOrgid != null) {
			if (!StringUtils.trim(corporationOrgid).isEmpty()) {
				conds.add(new Condition("ST_CORPORATION_ORGID", Condition.OT_EQUAL,
						corporationOrgid));
			}
		}
		//企业名称存在的场合
		if (ename != null) {
			if (!StringUtils.trim(ename).isEmpty()) {
				conds.add(new Condition("ST_ENAME", Condition.OT_LIKE,
						ename));
			}
		}
		//状态存在的场合
		if (status != null) {
			if (!StringUtils.trim(status).isEmpty()) {
				conds.add(new Condition("ST_STATUS", Condition.OT_EQUAL,
						status));
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
		// 结束时间存在的场合
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
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
		}

		return extPdCclistDao.query(conds, suffix, pageSize, currentPage);
	}


	/**
	 * 根据主键 {@link ExtPdCclist#ST_CCLIST_ID}删除子证归集表
	 * 
	 * @param stCclistId
	 *            子证归集表主键 {@link ExtPdCclist#ST_CCLIST_ID}
	 */
	@Override
	public void remove(String[] stCclistId) {
		if (stCclistId.length == 0)
			throw new NullPointerException("Parameter stCclistId cannot be null.");
		extPdCclistDao.delete(stCclistId);
	}

	/**
	 * 保存或更新子证归集表
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 子证归集表实例
	 */
	@Override
	public ExtPdCclist saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// ExtPdCclist.ST_CCLIST_ID
		String stCclistId = wrapper.getParameter(ExtPdCclist.ST_CCLIST_ID);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*String dtMark = wrapper.getParameter("DT_MARK");
		dtMark = dtMark.replace("GMT", "").replaceAll("\\(.*\\)", "");
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss z",Locale.ENGLISH);
		Date dateTrans = format.parse(dtMark);
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTrans);*/

		ExtPdCclist oldExtPdCclist = null;
		if (!StringUtils.trimToEmpty(stCclistId).isEmpty()) {
			oldExtPdCclist = extPdCclistDao.get(stCclistId);
		}
		if (oldExtPdCclist == null) {// new
			ExtPdCclist newExtPdCclist = (ExtPdCclist) t4r.toBean(ExtPdCclist.class);
			newExtPdCclist.setStCclistId(UUID.randomUUID().toString());
//			newExtPdCclist.setDtMark(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime()));
//			newExtPdCclist.setDtCreate(new Timestamp(System.currentTimeMillis()));
			newExtPdCclist.setDtCreate(Timestamp.valueOf(dateformat.format(System.currentTimeMillis())));
			extPdCclistDao.add(newExtPdCclist);
			return newExtPdCclist;
		}else {// update
			oldExtPdCclist = (ExtPdCclist) t4r.toBean(oldExtPdCclist, ExtPdCclist.class);
//			oldExtPdCclist.setDtMark(new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime()));
			oldExtPdCclist.setDtUpdate(Timestamp.valueOf(dateformat.format(System.currentTimeMillis())));
			extPdCclistDao.update(oldExtPdCclist);
			return oldExtPdCclist;
		}
	}

	/**
	 * 根据条件查询子证归集表列表
	 *
	 * @param wrapper
	 *            查询条件
	 * @return 子证归集表列表
	 */
	@Override
	public List<ExtPdCclist> select(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		// 单窗子办件
		String subNo = wrapper.getParameter("ST_SUB_NO");
		// 业态办件流水号
		String applyNo = wrapper.getParameter("ST_APPLY_NO");
		// 所属部门ID
		String organId = wrapper.getParameter("ST_ORGAN_ID");
		// 33位事项编码
		String itemCno = wrapper.getParameter("ST_ITEM_CNO");
		// 事项名称
		String itemNo = wrapper.getParameter("ST_ITEM_NO");
		// 证照目录编号
		String certNo = wrapper.getParameter("ST_CERT_NO");
		// 证照名称
		String certName = wrapper.getParameter("ST_CERT_NAME");
		// 证照许可编号
		String licenseNo = wrapper.getParameter("ST_LICENSE_NO");
		// 制证日期
		String dtMark = wrapper.getParameter("DT_MARK");
		// 社会信用代码
		String corporationOrgid = wrapper.getParameter("ST_CORPORATION_ORGID");
		// 企业名称
		String ename = wrapper.getParameter("ST_ENAME");
		// 状态
		String status = wrapper.getParameter("ST_STATUS");
		// 插入开始时间
		String startDate = wrapper.getParameter("startDate");
		// 结束时间
		String endDate = wrapper.getParameter("endDate");
		//排序
		String suffix = "ORDER BY DT_CREATE DESC";
		// 单窗子办件存在的场合
		if (subNo != null) {
			if (!StringUtils.trim(subNo).isEmpty()) {
				conds.add(new Condition("ST_SUB_NO", Condition.OT_EQUAL,
						subNo));
			}
		}
		//业务办件流水号存在的场合
		if (applyNo != null) {
			if (!StringUtils.trim(applyNo).isEmpty()) {
				conds.add(new Condition("ST_APPLY_NO", Condition.OT_EQUAL,
						applyNo));
			}
		}
		//所属部门ID存在的场合
		if (organId != null) {
			if (!StringUtils.trim(organId).isEmpty()) {
				conds.add(new Condition("ST_ORGAN_ID", Condition.OT_EQUAL,
						organId));
			}
		}
		//33位事项编码存在的场合
		if (itemCno != null) {
			if (!StringUtils.trim(itemCno).isEmpty()) {
				conds.add(new Condition("ST_ITEM_CNO", Condition.OT_EQUAL,
						itemCno));
			}
		}
		//事项名称存在的场合
		if (itemNo != null) {
			if (!StringUtils.trim(itemNo).isEmpty()) {
				conds.add(new Condition("ST_ITEM_NO", Condition.OT_LIKE,
						itemNo));
			}
		}
		//证照目录编号存在的场合
		if (certNo != null) {
			if (!StringUtils.trim(certNo).isEmpty()) {
				conds.add(new Condition("ST_CERT_NO", Condition.OT_EQUAL,
						certNo));
			}
		}
		//证照名称存在的场合
		if (certName != null) {
			if (!StringUtils.trim(certName).isEmpty()) {
				conds.add(new Condition("ST_CERT_NAME", Condition.OT_LIKE,
						certName));
			}
		}
		//证照许可编号存在的场合
		if (licenseNo != null) {
			if (!StringUtils.trim(licenseNo).isEmpty()) {
				conds.add(new Condition("ST_LICENSE_NO", Condition.OT_EQUAL,
						licenseNo));
			}
		}
		//制证日期存在的场合
		if (dtMark != null) {
			if (!StringUtils.trim(dtMark).isEmpty()) {
				conds.add(new Condition("DT_MARK", Condition.OT_EQUAL,
						dtMark));
			}
		}
		//社会信用代码存在的场合
		if (corporationOrgid != null) {
			if (!StringUtils.trim(corporationOrgid).isEmpty()) {
				conds.add(new Condition("ST_CORPORATION_ORGID", Condition.OT_EQUAL,
						corporationOrgid));
			}
		}
		//企业名称存在的场合
		if (ename != null) {
			if (!StringUtils.trim(ename).isEmpty()) {
				conds.add(new Condition("ST_ENAME", Condition.OT_LIKE,
						ename));
			}
		}
		//状态存在的场合
		if (status != null) {
			if (!StringUtils.trim(status).isEmpty()) {
				conds.add(new Condition("ST_STATUS", Condition.OT_EQUAL,
						status));
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
		// 结束时间存在的场合
		if (endDate != null) {
			if (!StringUtils.trim(endDate).isEmpty()) {
				// 结束时间
				conds.add(new Condition("DT_CREATE", Condition.OT_LESS_EQUAL,
						endDate+" 23:59"));
			}
		}


		return extPdCclistDao.query(conds, suffix);
	}

	@Autowired
	private ExtPdCclistDao extPdCclistDao;

	@Autowired
	private UacUapplyAttachDaoExt uacUapplyAttachDaoExt;
}
