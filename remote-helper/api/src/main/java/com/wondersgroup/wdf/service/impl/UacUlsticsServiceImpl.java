package com.wondersgroup.wdf.service.impl;

import com.wondersgroup.wdf.dao.UacUlstics;
import com.wondersgroup.wdf.dao.UacUlsticsDao;
import com.wondersgroup.wdf.service.UacUlsticsService;
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
import wfc.service.database.Conditions;

import java.util.UUID;

/**
 * 办件物流业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
public class UacUlsticsServiceImpl implements UacUlsticsService {

	/**
	 * 根据主键 {@link UacUlstics#ST_UNION_LOGISTICS_ID}获取办件物流
	 * 
	 * @param stUnionLogisticsId
	 *            办件物流主键 {@link UacUlstics#ST_UNION_LOGISTICS_ID}
	 * @return 办件物流实例
	 */
	@Override
	public UacUlstics get(String stUnionLogisticsId) {
		if (StringUtils.trimToEmpty(stUnionLogisticsId).isEmpty())
			throw new NullPointerException("Parameter stUnionLogisticsId cannot be null.");
		return uacUlsticsDao.get(stUnionLogisticsId);
	}

	/**
	 * 查询办件物流列表
	 * 
	 * @param wrapper
	 *            查询条件
	 * @return 办件物流列表
	 */
	@Override
	public PaginationArrayList<UacUlstics> query(RequestWrapper wrapper) {
		Conditions conds = Conditions.newAndConditions();
		String suffix = StringUtils.EMPTY;
		int pageSize = Integer.MAX_VALUE / 2;
		int currentPage = 1;
		if (wrapper != null) {
			Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(
					wrapper);
			conds = t4r.toConditions(UacUlstics.class);
			Page page = EasyUIHelper.getPage(wrapper);
			pageSize = page.getPageSize();
			currentPage = page.getCurrentPage();
			suffix = page.getSuffix();
		}
		return uacUlsticsDao.query(conds, suffix, pageSize, currentPage);
	}

	/**
	 * 根据主键 {@link UacUlstics#ST_UNION_LOGISTICS_ID}删除办件物流
	 * 
	 * @param stUnionLogisticsId
	 *            办件物流主键 {@link UacUlstics#ST_UNION_LOGISTICS_ID}
	 */
	@Override
	public void remove(String stUnionLogisticsId) {
		if (StringUtils.trimToEmpty(stUnionLogisticsId).isEmpty())
			throw new NullPointerException("Parameter stUnionLogisticsId cannot be null.");
		uacUlsticsDao.delete(stUnionLogisticsId);
	}

	/**
	 * 保存或更新办件物流
	 * 
	 * @param wrapper
	 *            提交参数
	 * @return 办件物流实例
	 */
	@Override
	public UacUlstics saveOrUpdate(RequestWrapper wrapper) {
		if (wrapper == null)
			throw new NullPointerException("Parameter wrapper cannot be null.");
		Transformer4RequestWrapper t4r = new Transformer4RequestWrapper(wrapper);
		// UacUlstics.ST_UNION_LOGISTICS_ID
		String stUnionLogisticsId = wrapper.getParameter(UacUlstics.ST_UNION_LOGISTICS_ID);
		UacUlstics oldUacUlstics = null;
		if (!StringUtils.trimToEmpty(stUnionLogisticsId).isEmpty()) {
			oldUacUlstics = uacUlsticsDao.get(stUnionLogisticsId);
		}
		if (oldUacUlstics == null) {// new
			UacUlstics newUacUlstics = (UacUlstics) t4r.toBean(UacUlstics.class);
			newUacUlstics.setStUnionLogisticsId(UUID.randomUUID().toString());
			uacUlsticsDao.add(newUacUlstics);
			return newUacUlstics;
		}else {// update
			oldUacUlstics = (UacUlstics) t4r.toBean(oldUacUlstics, UacUlstics.class);
			uacUlsticsDao.update(oldUacUlstics);
			return oldUacUlstics;
		}
	}

	@Override
	public UacUlstics getUlsticsByJSON(JSONObject jsonObject) {

		String st_ship_company = jsonObject.optString("ST_SHIP_COMPANY");
		String st_receiver = jsonObject.optString("ST_RECEIVER");
		String st_receiver_phone = jsonObject.optString("ST_RECEIVER_PHONE");
		String st_receiver_zipcode = jsonObject.optString("ST_RECEIVER_ZIPCODE");
		String st_receiver_province = jsonObject.optString("ST_RECEIVER_PROVINCE");
		String st_receiver_city = jsonObject.optString("ST_RECEIVER_CITY");
		String st_receiver_area = jsonObject.optString("ST_RECEIVER_AREA");
		String st_receiver_address = jsonObject.optString("ST_RECEIVER_ADDRESS");
		String st_sender = jsonObject.optString("ST_SENDER");
		String st_sender_phone = jsonObject.optString("ST_SENDER_PHONE");
		String st_sender_province = jsonObject.optString("ST_SENDER_PROVINCE");
		String st_sender_city = jsonObject.optString("ST_SENDER_CITY");
		String st_sender_area = jsonObject.optString("ST_SENDER_AREA");
		String st_sender_address = jsonObject.optString("ST_SENDER_ADDRESS");
		UacUlstics uacUlstics = new UacUlstics();
		uacUlstics.setStReceiver(st_receiver);
		uacUlstics.setStShipCompany(st_ship_company);
		uacUlstics.setStReceiverPhone(st_receiver_phone);
		uacUlstics.setStReceiverZipcode(st_receiver_zipcode);
		uacUlstics.setStReceiverProvince(st_receiver_province);
		uacUlstics.setStReceiverCity(st_receiver_city);
		uacUlstics.setStReceiverArea(st_receiver_area);
		uacUlstics.setStReceiverAddress(st_receiver_address);
		uacUlstics.setStSender(st_sender);
		uacUlstics.setStSenderPhone(st_sender_phone);
		uacUlstics.setStSenderProvince(st_sender_province);
		uacUlstics.setStSenderCity(st_sender_city);
		uacUlstics.setStSenderArea(st_sender_area);
		uacUlstics.setStSenderAddress(st_sender_address);
		System.out.println("******************************** uacUlstics 字段解析完成******************************");
		return uacUlstics;
	}

	@Autowired
	private UacUlsticsDao uacUlsticsDao;

}
