package com.wondersgroup.selfapi.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


import com.wondersgroup.selfapi.bean.NetReservationVo;
import com.wondersgroup.selfapi.bean.NetreservationDayTime;
import com.wondersgroup.selfapi.bean.ReservationResult;
import com.wondersgroup.selfapi.bean.ReservationVoPage;



@WebService(targetNamespace = "http://www.xuhui.gov.cn/placeNetReservationService")
public interface PlaceNetReservationService {


	/**
	 * 根据事项编码和办理点的ID查询可预约的天数
	 */
//	public @WebResult(name = "dateList")
//	List<String> getPlaceReservationDays(
//			@WebParam(name = "sessionId") String sessionId,
//			@WebParam(name = "itemNo") String itemNo,
//			@WebParam(name = "placeId") String placeId);
//	


	/**
	 * 方法描述：根据预约号和身份证号取消预约
	 * @param certNo 证件号
	 * @param reservationNo 预约号
	 * @return
	 */
	@WebMethod(exclude = true)
	public String cancelReservationByCertNoAndReNo(String certNo,
			String reservationNo);

	@WebMethod(exclude = true)
	public List<String> getPlaceReservationDays(String itemNo, String placeId);
	
	
	@WebMethod(exclude = true)
	public List<NetreservationDayTime> getPlaceReservationTimeAndCount(
			String str, String itemNo, String placeId);

	@WebMethod(exclude = true)
	public ReservationResult savePlaceReservationInfo(String itemNo,
			String placeId, String date, String detailId, String userId,
			String userName, String mobile, String identityType, String certNo,
			String reservationSource, String business, String unit,
			String unified);

	/**
	 * 方法描述：根据预约号查询预约信息
	 * @param reservationNo
	 * @return NetReservationVo
	 */
	@WebMethod(exclude = true)
	public NetReservationVo findHistoryReservationByNo(String reservationNo);


	/**
	 * 方法描述：根据预约号查询预约信息（已经预约但是未取号的预约信息）
	 * @param reservationNo
	 * @return NetReservationVo
	 */
	@WebMethod(exclude = true)
	public NetReservationVo findReservationByNo(String reservationNo);


	/**
	 * 方法描述：根据身份证号获取预约列表（已经预约但是未取号的预约信息）
	 * @param certNo
	 * @return List<NetReservationVo>
	 */
	@WebMethod(exclude = true)
	public List<NetReservationVo> findReservationListByCertNo(String certNo);


	/**
	 * 方法描述：根据身份证号获取预约列表（取过号的和取消预约的预约信息）
	 * @param certNo
	 * @param maxValue
	 * @param i
	 * @return ReservationVoPage
	 */
	@WebMethod(exclude = true)
	public ReservationVoPage findHistoryReservationListByCertNo(String certNo,
			int maxValue, int i);

}
		
