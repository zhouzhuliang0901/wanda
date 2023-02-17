package com.wondersgroup.assistant.readCardInfo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.util.Base64Decoder;
import com.wondersgroup.assistant.readCardInfo.dao.Sscard;
import com.wondersgroup.assistant.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wfc.facility.tool.autocode.PaginationArrayList;
import wfc.service.database.Conditions;

import com.wondersgroup.assistant.readCardInfo.dao.Idcard;
import com.wondersgroup.assistant.readCardInfo.dao.IdcardDao;
import com.wondersgroup.assistant.readCardInfo.service.readCardInfoService;

import coral.base.data.Transformer4RequestWrapper;
import coral.base.util.RequestWrapper;
import coral.widget.utils.EasyUIHelper;
import coral.widget.utils.EasyUIHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * IDCard业务实现
 * 
 * @author scalffold
 * 
 */
@Service
@Transactional
@Slf4j
public class ReadCardInfoServiceImpl implements readCardInfoService {
	/**
	 * 远程刷卡
	 *
	 * @param remoteInfo
	 * @return
	 */

	@Override
	public String  getReadCardInfo(JSONObject remoteInfo){
		String imgBase64 = "";
		String cardInfo ="";
		HashMap<String, Object> boolInfo = new HashMap<>();
		String zzlx = remoteInfo.getString("zzlx");
		if(StringUtils.isNotBlank(zzlx)&&zzlx.equals("1")){
			Idcard idcard = new Idcard();
			idcard.setZzlx("1");
			idcard.setName("张三");
			idcard.setSex("男");
			idcard.setNation("1");
			idcard.setBirthday("2022-11-11");
			idcard.setIdcode("34456789112");
			idcard.setStartdate("2022-11-11");
			idcard.setEnddate("2033-11-11");
			idcard.setAddress("上海市浦东新区");
			InputStream in = null;
			byte[] imageByte = null;
			String imageFilePath="D:/Study/a.png";
			try {
				//读取文件
				in = new FileInputStream(imageFilePath);
				int size = in.available();
				imageByte = new byte[size];
				//从输入流中将数据读入一个 imageByte字节 数组中。
				in.read(imageByte);
				//关闭此文件输入流并释放与此流有关的所有系统资源。
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			String s = org.apache.commons.codec.binary.Base64.encodeBase64String(imageByte);
			idcard.setImgbase64("data:image/jpeg;base64,"+s);
			idcard.setSamid("123121212");
			idcard.setEname("jerry");
			List rtnList = new ArrayList<>();
			rtnList.add(idcard);
			boolInfo.put("rtnList",rtnList);
			boolInfo.put("rtnmsg","");
			boolInfo.put("sucees", "true");
			Object o = JSONArray.toJSON(boolInfo);
			cardInfo = o.toString();
		}else {
			Sscard sscard = new Sscard();
			sscard.setZzlx("1");
			sscard.setName("张三");
			sscard.setSex("男");
			sscard.setNation("1");
			sscard.setBirthday("2022-11-11");
			sscard.setIdcode("34456789112");
			sscard.setStartdate("2022-11-11");
			sscard.setEnddate("2033-11-11");
			sscard.setAddress("上海市浦东新区");
			sscard.setCardno("123456");
			sscard.setCardidn("1234567");
			sscard.setCardcity("上海市");
			sscard.setCardVer("上海市浦东新区");
			List info = new ArrayList<>();
			info.add(sscard);
			boolInfo.put("rtnList",info);
			boolInfo.put("rtnmsg","");
			boolInfo.put("sucees", "true");
			Object o = JSONArray.toJSON(boolInfo);
			cardInfo = o.toString();
		}
		System.out.println(cardInfo);

		return  cardInfo;
	}

	/**
	 * 远程读取扫描枪
	 * @param sbInfo
	 * @return
	 */
	@Override
	public String getreadScannerInfo(JSONObject sbInfo) {
		String ycsbid = sbInfo.getString("ycsbid");
		String itemcode = sbInfo.getString("itemcode");
		String suid = sbInfo.getString("suid");
		return new ResultUtil().getNullRSuccess();
	}

	/**
	 * 远程获取高拍仪文件
	 * @param archivesInfo
	 * @return
	 */
	@Override
	public String getGpyinfo(JSONObject archivesInfo) {
		ResultUtil resultUtil = new ResultUtil();
		List<Object> rtnList = new ArrayList<>();
		String ycsbid = archivesInfo.getString("ycsbid");
		String archivescode = archivesInfo.getString("archivescode");
		String archivesname = archivesInfo.getString("archivesname");
		String suid = archivesInfo.getString("suid");
		HashMap<String, Object> archives = new HashMap<>();
		archives.put("itemcode", "");
		archives.put("suid", suid);
		archives.put("archivescode", archivescode);
		List<Object> archivesInfos = new ArrayList<>();
		HashMap<String, Object> archive = new HashMap<>();
		archive.put("archivesid","无");
		archive.put("archivesdata","什么材料转成base64");
		archivesInfos.add(archive);
		archives.put("archivesInfos",archivesInfos);
		rtnList.add(archives);
		return resultUtil.getSuccess(rtnList);
	}

	/**
	 * 回执打印-PDF文件
	 * @param response
	 * @return
	 */
	@Override
	public void outputHzPdf(HttpServletResponse response) {
		response.setContentType("application/pdf");
		File file = new File("D:\\Study\\a.pdf");
		FileInputStream in = null;
		OutputStream out = null;
		if(file.exists()){
			try {
				in = new FileInputStream(file);
				out = response.getOutputStream();
				byte[] b = new byte[1024*5];
				int n;
				while((n = in.read(b)) != -1){
					out.write(b,0,n);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					out.flush();
					in .close();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}

	}

	@Override
	public String outputHzJlc(JSONObject JlcInfo) {
		ResultUtil resultUtil = new ResultUtil();
		String ycsbid = JlcInfo.getString("ycsbid");
		String suid = JlcInfo.getString("suid");
		String jlcbh = JlcInfo.getString("jlcbh");
		String xm = JlcInfo.getString("xm");
		String xb = JlcInfo.getString("xb");
		String kh = JlcInfo.getString("kh");
		String csny = JlcInfo.getString("csny");
		return resultUtil.getNullRSuccess();
	}

	/**
	 * 获取签名照片
	 * @param remoteInfo
	 * @return
	 */
	@Override
	public String getQmPic(JSONObject remoteInfo) {
		ResultUtil resultUtil = new ResultUtil();
		String ycsbid = remoteInfo.getString("ycsbid");
		String suid = remoteInfo.getString("suid");
		String scaleHeight = remoteInfo.getString("scaleHeight");
		HashMap<String, Object> getPic = new HashMap<>();
		getPic.put("suid",suid);
		InputStream in = null;
		byte[] imageByte = null;
		String imageFilePath="D:/Study/a.png";
		try {
			//读取文件
			in = new FileInputStream(imageFilePath);
			int size = in.available();
			imageByte = new byte[size];
			//从输入流中将数据读入一个 imageByte字节 数组中。
			in.read(imageByte);
			//关闭此文件输入流并释放与此流有关的所有系统资源。
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String s = org.apache.commons.codec.binary.Base64.encodeBase64String(imageByte);
		getPic.put("qmpic","data:image/jpeg;base64,"+s);
		List rtnList = new ArrayList<>();
		rtnList.add(getPic);
		return resultUtil.getSuccess(rtnList);
	}

	/**
	 * 远程受理设备模拟叫号
	 * @param remoteInfo
	 * @return
	 */
	@Override
	public String callTerminal(JSONObject remoteInfo) {
		ResultUtil resultUtil = new ResultUtil();
		String ycsbid = remoteInfo.getString("ycsbid");
		String systemCode = remoteInfo.getString("systemCode");
		String windowCode = remoteInfo.getString("windowCode");//当前窗口号
		HashMap<String, Object> callInfo = new HashMap<>();
		callInfo.put("ycsbid", ycsbid);
		callInfo.put("systemCode", systemCode);
		callInfo.put("windowCode", windowCode);
		callInfo.put("optNumber", "随机变化的数字，当天内不重复");
		List<Object> rtnList = new ArrayList<>();
		rtnList.add(callInfo);
		return resultUtil.getSuccess(rtnList);
	}


	@Autowired
	private IdcardDao idcardDao;
}
