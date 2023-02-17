package com.wondersgroup.infopub.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wondersgroup.infopub.bean.SelmBigscreenCache;
import com.wondersgroup.infopub.dao.SelmBigscreenCacheDao;
import com.wondersgroup.infopub.service.InfoPubService;


import reindeer.base.utils.AciJsonHelper;
import reindeer.base.utils.EasyUIHelper;
import tw.ecosystem.reindeer.web.HttpReqRes;

/**
 * 信息web控制层
 * 
 * @author guicb
 * 
 */
@Controller
public class InfoPubController {
	@Autowired
	private InfoPubService infoPubService;
	@Autowired
	private SelmBigscreenCacheDao selmBigscreenCacheDao;
	/**
	 * 设备地图
	 */
	@RequestMapping("/infopub/deviceinfo/placeshow.do")
	public void placeShow(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject json = null;
		try {
			json = infoPubService.getAlldeviceInfo(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(json.toString());
	}
	
	
	/**
	 * 查询区域设备正常异常量
	 */
	@RequestMapping("/infopub/deviceinfo/placeshowArea.do")
	public void placeShowArea(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject json = null;
		try {
			json = infoPubService.getAreadeviceInfo(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(json.toString());
	}
	
	/**
	 *  查询相应办件信息数量
	 */
	@RequestMapping("/infopub/deviceinfo/getSelmQuery.do")
	public void getSelmQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject json = null;
		try {
			json = infoPubService.getSelmQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(json.toString());
	}
	
	/**
	 *  查询相应区级办件信息数量
	 */
	@RequestMapping("/infopub/deviceinfo/getAreaSelmQuery.do")
	public void getAreaSelmQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject json = null;
		try {
			json = infoPubService.getAreaSelmQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(json.toString());
	}
	
	/**
	 * xaizai 设备信息列表为埋点使用 
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 * @throws JSONException
	 */
	@RequestMapping("/infopub/deviceinfo/listMac.do")
	public void deviceInfoListMac(HttpServletRequest req, HttpServletResponse res)
			throws IOException, JSONException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceInfoListMac(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		EasyUIHelper.writeResponse(res, obj.toString());
	}
	
	
	/**
	 * 办件信息统计top20
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/selmQuertTop.do")
	public void selmQuertTop(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.selmQuertTop(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 根据区域终端设备数
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmStatistics/addresslistTypeDevice.do")
	public void addresslistTypeDevice(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.addresslistTypeDevice(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		httpReqRes.writeJsonP(obj.toString());
	}
	
	/**
	 * 事项总数
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/selmQuertNum.do")
	public void selmQuert(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.selmQuertNum(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 最新办件信息top2
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/selmQuerTow.do")
	public void selmQuertListTow(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.selmQuertListTow(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 区域街道天30办件量
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/areaSelmQuer.do")
	public void areaSelmQuer(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.areaSelmQuert(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	

	/**
	 * 区域办件top10
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/areaSelmQuerTop.do")
	public void areaSelmQuerTop(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.areaSelmQuertTop(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 街道办件量top10
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/streetSelmQuerTop.do")
	public void streetSelmQuerTop(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.streetSelmQuerTop(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 街道查询设备相关信息
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/streetDeviceList.do")
	public void streetDeviceList(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.streetDeviceList(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 区域街道设备数
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/areaDeviceInfo.do")
	public void areaDeviceInfo(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.areaDeviceInfo(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 根据事项统计区域相应办件量
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/itemAreaQuery.do")
	public void itemAreaQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.itemAreaQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 根据事项统计街道相应办件量top16
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/itemStreetQuery.do")
	public void itemStreetQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.itemStreetQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	
	/**
	 * 根据事项查询年龄分布人数
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/itemPeNumber.do")
	public void itemPeNumber(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.itemPeNumber(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	
	/**
	 * 事项访问数量和办理成功数量对比
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/visitComItem.do")
	public void visitComItem(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.visitComItem(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	
	/**
	 * 业务各时段办件量对比，近6个月 每月办件量对比
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/itemPercentMonQuery.do")
	public void itemPercentMon(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.itemPercentMonth(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	
	/**
	 * 查询各区域30天办件量
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/areaThirtySelmQuery.do")
	public void areaThirtySelmQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.areaThirtySelmQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	
	/**
	 * 区域设备数（中心 延申 社会）
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/selmQueryHis/areaDeviceCount.do")
	public void areaDeviceCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.areaDeviceCount(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	
	
	/**
	 * 当天办件量
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@RequestMapping("/infopub/selmQueryHis/todaySelmQuery.do")
	public void todaySelmQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.todaySelmQuery(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}*/
	@RequestMapping("/infopub/selmQueryHis/todaySelmQuery.do")
	public void todaySelmQuery(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		String data = "";
		try {
			SelmBigscreenCache sbc = selmBigscreenCacheDao.get("infopub","selmQueryHis","todaySelmQuery.do");
			if(null != sbc){
				data = sbc.getStJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, data);
	}
	
	
	/**
	 * 各类终端数量及各自办理量
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@RequestMapping("/infopub/deviceinfo/deviceAndHandCount.do")
	public void deviceAndHandCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.deviceAndHandCount(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}*/
	@RequestMapping("/infopub/deviceinfo/deviceAndHandCount.do")
	public void deviceAndHandCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		String data = "";
		try {
			SelmBigscreenCache sbc = selmBigscreenCacheDao.get("infopub","deviceinfo","deviceAndHandCount.do");
			if(null != sbc){
				data = sbc.getStJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, data);
	}
	
	/**
	 * 当月热门服务top5
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@RequestMapping("/infopub/deviceinfo/itemOfMonth.do")
	public void itemOfMonth(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.itemOfMonth(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}*/
	@RequestMapping("/infopub/deviceinfo/itemOfMonth.do")
	public void itemOfMonth(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		String data = "";
		try {
			SelmBigscreenCache sbc = selmBigscreenCacheDao.get("infopub","deviceinfo","itemOfMonth.do");
			if(null != sbc){
				data = sbc.getStJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, data);
	}
	
	
	/**
	 * 各区设备当月办件量
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@RequestMapping("/infopub/deviceinfo/areaItemOfMonth.do")
	public void areaItemOfMonth(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.areaItemOfMonth(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}*/
	@RequestMapping("/infopub/deviceinfo/areaItemOfMonth.do")
	public void areaItemOfMonth(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		String data = "";
		try {
			SelmBigscreenCache sbc = selmBigscreenCacheDao.get("infopub","deviceinfo","areaItemOfMonth.do");
			if(null != sbc){
				data = sbc.getStJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, data);
	}
	
	/**
	 * 社会化服务总量top5
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@RequestMapping("/infopub/deviceinfo/socialCount.do")
	public void socialCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.socialCount(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}*/
	@RequestMapping("/infopub/deviceinfo/socialCount.do")
	public void socialCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		String data = "";
		try {
			SelmBigscreenCache sbc = selmBigscreenCacheDao.get("infopub","deviceinfo","socialCount.do");
			if(null != sbc){
				data = sbc.getStJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, data);
	}
	
	
	/**
	 * 各区设备累计办件量
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	/*@RequestMapping("/infopub/deviceinfo/areaItemCount.do")
	public void areaItemCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.areaItemCount(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}*/
	@RequestMapping("/infopub/deviceinfo/areaItemCount.do")
	public void areaItemCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		String data = "";
		try {
			SelmBigscreenCache sbc = selmBigscreenCacheDao.get("infopub","deviceinfo","areaItemCount.do");
			if(null != sbc){
				data = sbc.getStJson();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, data);
	}
	
	/**
	 * 实时办件
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/realTimeHand.do")
	public void realTimeHand(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.realTimeHand(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
	/**
	 * 各区政务终端数量统计
	 * 
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/infopub/deviceinfo/govDeviceCount.do")
	public void govDeviceCount(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setHeader("Access-Control-Allow-Origin","*");
		res.setHeader("Cache-Control","no-cache");
		HttpReqRes httpReqRes = new HttpReqRes(req, res);
		JSONObject obj = null;
		try {
			obj = infoPubService.govDeviceCount(httpReqRes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AciJsonHelper.writeJsonPResponse(req, res, obj.toString());
	}
	
}