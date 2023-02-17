package com.wondersgroup.selfapi.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reindeer.base.utils.AciHelper;
import reindeer.base.utils.AciJsonHelper;
import wfc.service.log.Log;

import com.wondersgroup.selfapi.bean.ItemSet;
import com.wondersgroup.selfapi.bean.NetReservationVo;
import com.wondersgroup.selfapi.bean.NetreservationDayTime;
import com.wondersgroup.selfapi.bean.OrganNodeInfo;
import com.wondersgroup.selfapi.bean.PlaceInfo;
import com.wondersgroup.selfapi.bean.ReservationResult;
import com.wondersgroup.selfapi.bean.ReservationVoPage;
import com.wondersgroup.selfapi.bean.WindowGroupInfo;
import com.wondersgroup.selfapi.bean.WindowHall;
import com.wondersgroup.selfapi.service.PlaceNetReservationService;
import com.wondersgroup.selfapi.service.ReservationService;

/**
 * 自助预约
 * 
 * @author lenovo
 */
@SuppressWarnings("all")
@RestController
public class ReservationsController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private PlaceNetReservationService placeNetReservationService;

	/**
	 * 获取预约大厅列表
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/getAllHallList")
	public void getAllHallList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		List<WindowHall> list = new ArrayList<WindowHall>();
		String result = "";
		try {
			list = reservationService.getAllHallList();
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 获取所有包含可预约事项信息的大厅信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/getAllHallListForNet")
	public void getAllHallListForNet(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		List<WindowHall> list = new ArrayList<WindowHall>();
		String result = "";
		try {
			list = reservationService.getAllHallListForNet();
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 根据预约大厅的编码获取其下的包含可预约事项的部门
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/getAllOrganNetListByHallCode")
	public void getAllOrganNetListByHallCode(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String hallCode = req.getParameter("hallCode");
		List<OrganNodeInfo> list = new ArrayList<OrganNodeInfo>();
		String result = "";
		try {
			list = reservationService.getAllOrganNetListByHallCode(hallCode);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 根据大厅编码和办理点的ID获取其下所有的可预约事项
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/getAllItemListById")
	public void getAllItemListById(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		ArrayList<ItemSet> list = new ArrayList<ItemSet>();

		String result = "";
		String hallCode = req.getParameter("hallCode");
		String placeId = req.getParameter("placeId");
		placeId = AciHelper.getPlaceId(placeId);
		// 页码
		String current = req.getParameter("currentPage");
		// 页内数据数量
		String page = req.getParameter("pageSize");

		try {
			// 页码不为空,则根据页码进行分页查询
			if (!StringUtils.isBlank(current) && !StringUtils.isBlank(page)) {
				int currentPage = Integer.valueOf(current);
				int pageSize = Integer.valueOf(page);
				list = reservationService.getAllItemListByIdForPage(pageSize,
						currentPage, hallCode, placeId);
			} else {
				list = reservationService.getAllItemListById(hallCode, placeId);
			}
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
		} catch (NumberFormatException e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 根据办理点的ID和事项的编码获取所有可预约天数
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/getReservationAllDay")
	public void getReservationAllDay(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		List<String> list = new ArrayList<String>();
		String result = "";
		// 获取接口需要的参数
		String placeId = req.getParameter("placeId");
		// 判断placedId是否为null
		placeId = AciHelper.getPlaceId(placeId);
		String itemNo = req.getParameter("itemNo");

		list = placeNetReservationService.getPlaceReservationDays(itemNo,
				placeId);
		if (list != null && list.size() > 0) {
			result = JSONArray.fromObject(list).toString();
		} else {
			result = "";
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 根据办理点的ID和事项的编码获取所有可预约天数（ 返回的是可预约的天数以及当天各个时间段已预约满的天数）
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/getReservationAllDays")
	public void getReservationAllDays(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 获取接口需要的参数
		String placeId = req.getParameter("placeId");
		// 判断placeId是否为null
		placeId = AciHelper.getPlaceId(placeId);
		String itemNo = req.getParameter("itemNo");
		List<String> reservationlist = new ArrayList<String>();
		List<String> fullList = new ArrayList<String>();
		String result = "";

		try {
			List<String> list = placeNetReservationService
					.getPlaceReservationDays(itemNo, placeId);
			if (list != null && list.size() > 0) {
				for (String str : list) {
					boolean flags = true;
					List<NetreservationDayTime> dayTimeList = placeNetReservationService
							.getPlaceReservationTimeAndCount(str, itemNo,
									placeId);
					System.out.println("-------------------" + str);
					if (dayTimeList != null && dayTimeList.size() > 0) {
						for (NetreservationDayTime n : dayTimeList) {
							if (n.getSurplusCount() > 0) {
								flags = false;
								break;
							}
						}
					}
					if (flags) {
						fullList.add(str);
					}
				}
				JSONObject obj = new JSONObject();
				obj.put("reservationlist", list);
				obj.put("fullList", fullList);
				result = obj.toString();
			}

		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 获取指定日期下的可预约时间段及预约数
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/getReservationAllTime")
	public void getReservationAllTime(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 获取接口需要参数
		List<NetreservationDayTime> list = new ArrayList<NetreservationDayTime>();
		String placeId = req.getParameter("placeId");
		// 判断placeId是否为null
		placeId = AciHelper.getPlaceId(placeId);
		String itemNo = req.getParameter("itemNo");
		String date = req.getParameter("date");
		String result = "";
		try {
			list = placeNetReservationService.getPlaceReservationTimeAndCount(
					date, itemNo, placeId);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 提交预约信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/saveReservationInfo")
	public void saveReservationInfo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		// 获取接口需要的参数
		String itemNo = req.getParameter("itemNo");
		String placeId = req.getParameter("placeId");
		// 判断placeId是否为null 默认为配置文件中的配置信息
		placeId = AciHelper.getPlaceId(placeId);
		String detailId = req.getParameter("detailId");
		String date = req.getParameter("date");
		String certNo = req.getParameter("certNo");
		String userName = req.getParameter("userName");
		String userId = req.getParameter("userId");
		String mobile = req.getParameter("mobile");
		String identityType = req.getParameter("identityType");
		String reservationSource = req.getParameter("reservationSource");
		String business = req.getParameter("business");
		String unit = req.getParameter("unit");
		String unified = req.getParameter("unified");
		String result = "";
		Log.debug("事项编码:" + itemNo + "办理点ID:" + placeId);
		try {
			ReservationResult reservationResult = placeNetReservationService
					.savePlaceReservationInfo(itemNo, placeId, date, detailId,
							userId, userName, mobile, identityType, certNo,
							reservationSource, business, unit, unified);
			if (reservationResult != null) {
				result = JSONObject.fromObject(reservationResult).toString();
			} else {
				result = "0";
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 方法描述：获取所有的办理点信息
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getPlaceInfo")
	public void getPlaceInfo(HttpServletRequest req, HttpServletResponse res) {
		String part = req.getParameter("part");
		String result = "";
		List<PlaceInfo> list = reservationService.getAllPlaceInfo(part);
		try {
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：根据办理点的父节点获取所有的办理点信息(根据WINDOW_AREA办理点信息表)
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getPlaceInfos")
	public void getPlaceInfos(HttpServletRequest req, HttpServletResponse res) {
		String stParentAreaId = req.getParameter("stParentAreaId");
		String result = "";
		List<PlaceInfo> list = reservationService
				.getAllPlaceInfos(stParentAreaId);
		try {
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：根据办理点id获取其下的业务组别集合
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getAllWindowGroupInfoByPlaceId")
	public void getAllWindowGroupInfoByPlaceId(HttpServletRequest req,
			HttpServletResponse res) {
		String stPlaceId = req.getParameter("stPlaceId");
		String result = "";
		List<WindowGroupInfo> list = new ArrayList<WindowGroupInfo>();
		try {
			list = reservationService.getAllWindowGroupInfoByPlaceId(stPlaceId);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：根据办理点id获取所有部门(部门下有可预约事项的部门)
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getOrgansByPlaceId")
	public void getOrgansByPlaceId(HttpServletRequest req,
			HttpServletResponse res) {
		String dept = req.getParameter("dept");
		String placeId = req.getParameter("placeId");
		String result = "";
		// 如果没有部门的前缀，就查询根目录下的所有部门
		if (StringUtils.isBlank(dept)) {
			dept = "";
		}
		List<OrganNodeInfo> list = new ArrayList<OrganNodeInfo>();
		try {
			list = reservationService.getAllOrgansByPlaceId(dept, placeId);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：获取所有部门(部门下有可预约事项的部门)
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getOrgans")
	public void getOrgans(HttpServletRequest req, HttpServletResponse res) {
		String dept = req.getParameter("dept");
		String result = "";
		// 如果没有部门前缀，就查询根目录下的所有部门
		if (StringUtils.isBlank(dept)) {
			dept = "";
		}
		List<OrganNodeInfo> list = new ArrayList<OrganNodeInfo>();
		try {
			list = reservationService.getAllOrgans(dept);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：获取该部门下所有事项(可预约的事项)
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getAllItemsByOrganId")
	// 列名 'NM_ORDER' 无效,没有该列
	public void getAllItemsByOrganId(HttpServletRequest req,
			HttpServletResponse res) {
		String organId = req.getParameter("organId");
		String current = req.getParameter("currentPage");
		String page = req.getParameter("pageSize");
		String result = "";
		List<ItemSet> list = new ArrayList<ItemSet>();
		try {
			// 页码不为空，则根据页码进行查询
			if (!StringUtils.isBlank(current) && !StringUtils.isBlank(page)) {
				Integer currentPage = Integer.valueOf(current);
				Integer pageSize = Integer.valueOf(page);
				Log.debug(currentPage + "---" + pageSize);
				list = reservationService.getAllItemsByOrganId(organId,
						pageSize, currentPage);
			} else {
				list = reservationService.getAllItemsByOrganId(organId);
			}
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：根据办理点id和部门id获取该部门下所有事项(可预约的事项)
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getAllItemsByOrganIdAndPlaceId")
	// 列名 'NM_ORDER' 无效,没有该列
	public void getAllItemsByOrganIdAndPlaceId(HttpServletRequest req,
			HttpServletResponse res) {
		String organId = req.getParameter("organId");
		String placeId = req.getParameter("placeId");
		// 页码
		String page = req.getParameter("pageSize");
		String current = req.getParameter("currentPage");
		String result = "";
		List<ItemSet> list = new ArrayList<ItemSet>();
		try {
			// 页码不为空，则根据页码进行分页查询
			if (!StringUtils.isBlank(page) && !StringUtils.isBlank(current)) {
				Integer pageSize = Integer.valueOf(page);
				Integer currentPage = Integer.valueOf(current);
				Log.debug(currentPage + "---" + pageSize);
				list = reservationService.getAllItemsByOrganIdAndPlaceId(
						organId, placeId, pageSize, currentPage);
			} else {
				list = reservationService.getAllItemsByOrganIdAndPlaceId(
						organId, placeId);
			}
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：获取该部门下的所有组别信息(根据部门id)
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getAllGroupInfoByOrganId")
	public void getAllGroupInfoByOrganId(HttpServletRequest req,
			HttpServletResponse res) {
		String organId = req.getParameter("organId");
		String result = "";
		List<WindowGroupInfo> list = new ArrayList<WindowGroupInfo>();
		try {
			list = reservationService.getAllWindowGroupInfo(organId);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：根据组别信息获取组别下的所有可预约的事项
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getAllItemsByGroupCode")
	// 列名 'NM_TYPE' 无效
	public void getAllItemsByGroupCode(HttpServletRequest req,
			HttpServletResponse res) {
		String stGroupCode = req.getParameter("stGroupCode");
		String page = req.getParameter("pageSize");
		String current = req.getParameter("currentPage");
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (!StringUtils.isBlank(page)) {
			pageSize = Integer.valueOf(page);
		}
		if (!StringUtils.isBlank(current)) {
			currentPage = Integer.valueOf(current);
		}
		String result = "";
		List<ItemSet> list = new ArrayList<ItemSet>();
		try {
			list = reservationService.getAllItemsByGroupCode(stGroupCode,
					pageSize, currentPage);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：根据事项名称模糊查询可预约的事项(包括显示部门名称)
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getAllItemsByItemName")
	public void getAllItemsByItemName(HttpServletRequest req,
			HttpServletResponse res) {
		String stItemName = req.getParameter("stItemName");
		String page = req.getParameter("pageSize");
		String current = req.getParameter("currentPage");
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (!StringUtils.isBlank(page)) {
			pageSize = Integer.valueOf(page);
		}
		if (!StringUtils.isBlank(current)) {
			currentPage = Integer.valueOf(current);
		}
		String result = "";
		List<ItemSet> list = new ArrayList<ItemSet>();
		try {
			list = reservationService.getAllItemsByItemName(stItemName,
					pageSize, currentPage);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：根据事项名称和办理点id模糊查询可预约的事项(包括显示部门名称)
	 * 
	 * @param req
	 * @param res
	 */
	@RequestMapping("/selfapi/reservations/getAllItemsByItemNameAndPlaceId")
	public void getAllItemsByItemNameAndPlaceId(HttpServletRequest req,
			HttpServletResponse res) {
		String stItemName = req.getParameter("stItemName");
		String placeId = req.getParameter("placeId");

		String page = req.getParameter("pageSize");
		String current = req.getParameter("currentPage");
		Integer pageSize = Integer.MAX_VALUE / 2;
		Integer currentPage = 1;
		if (!StringUtils.isBlank(page)) {
			pageSize = Integer.valueOf(page);
		}
		if (!StringUtils.isBlank(current)) {
			currentPage = Integer.valueOf(current);
		}
		String result = "";
		List<ItemSet> list = new ArrayList<ItemSet>();
		try {
			list = reservationService.getAllItemsByItemNameAndPlaceId(
					stItemName, placeId, pageSize, currentPage);
			if (list != null && list.size() > 0) {
				result = JSONArray.fromObject(list).toString();
			} else {
				result = "0";
			}
			AciJsonHelper.writeJsonPResponse(req, res, result);
		} catch (IOException e) {
			Log.error(e);
		}
	}

	/**
	 * 方法描述：输入预约号和身份证号(也可以是手机号)取消预约
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/cancelReservationByCertNoAndReNo")
	public void cancelReservationByCertNoAndReNo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String result = "";
		try {
			String reservation = req.getParameter("reservationNo");
			String certNo = req.getParameter("certNo");
			String isSuccess = placeNetReservationService
					.cancelReservationByCertNoAndReNo(certNo, reservation);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("result", isSuccess);
			result = jsonObject.toString();
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 方法描述：输入预约号查询预约信息列表（已经预约但是未取号的预约信息）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/findReservationListByNo")
	public void findReservationListByNo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String result = "0";
		try {
			String reservationNo = req.getParameter("reservationNo");
			NetReservationVo netReservationVo = placeNetReservationService
					.findReservationByNo(reservationNo);
			if (netReservationVo == null) {
				netReservationVo = placeNetReservationService
						.findHistoryReservationByNo(reservationNo);
			}
			if (reservationNo != null) {
				result = JSONObject.fromObject(netReservationVo).toString();
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 方法描述：输入身份证号查询预约信息列表（已经预约但是未取号的预约信息）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/findReservationListByCertNo")
	public void findReservationListByCertNo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		String result = "0";
		try {
			String certNo = req.getParameter("certNo");
			List<NetReservationVo> netReservationVoList = placeNetReservationService
					.findReservationListByCertNo(certNo);
			if (netReservationVoList != null) {
				result = JSONArray.fromObject(netReservationVoList).toString();
			}
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

	/**
	 * 方法描述：输入身份证号查询预约信息列表（取过号的和取消预约的预约信息）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/reservations/findAllReservationListByCertNo")
	public void findAllReservationListByCertNo(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		JSONObject obj = new JSONObject();
		String result = "0";
		try {
			String certNo = req.getParameter("certNo");
			List<NetReservationVo> list = placeNetReservationService
					.findReservationListByCertNo(certNo);
			ReservationVoPage reservationVoPage = placeNetReservationService
					.findHistoryReservationListByCertNo(certNo,
							Integer.MAX_VALUE, 1);
			List<NetReservationVo> historyReservationVoList = reservationVoPage
					.getRowList();
			if (list != null && list.size() > 0) {
				obj.put("reservationList", list);
			} else {
				obj.put("reservationList", 0);
			}
			if (historyReservationVoList != null
					&& historyReservationVoList.size() > 0) {
				obj.put("historyList", historyReservationVoList);
			} else {
				obj.put("historyList", 0);
			}
			result = obj.toString();
		} catch (Exception e) {
			Log.error(e);
		}
		AciJsonHelper.writeJsonPResponse(req, res, result);
	}

}
