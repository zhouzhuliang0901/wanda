package com.wondersgroup.selfapi.web;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import reindeer.base.utils.AciJsonHelper;
import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.selfapi.util.DzUtils;

@Controller
public class ECTestController {
	
	@RequestMapping("/selfapi/ECTest/adduser.do")
	public void adduser(HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		
		String account = req.getParameter("account");
		String name = req.getParameter("name");
		if(StringUtils.isNotEmpty(name)){
			name = URLDecoder.decode(name, "utf-8");
		}
		Map<String, String> map = new HashMap<String, String>();
//		map.put("SH00PD", "浦东新区");
//		map.put("SH00HP", "黄浦区");
//		map.put("SH00JA", "静安区");
//		map.put("SH00XH", "徐汇区");
//		map.put("SH00CN", "长宁区");
//		map.put("SH00PT", "普陀区");
//		map.put("SH00HK", "虹口区");
//		map.put("SH00YP", "杨浦区");
//		map.put("SH00BS", "宝山区");
//		map.put("SH00MH", "闵行区");
//		map.put("SH00JD", "嘉定区");
//		map.put("SH00JS", "金山区");
//		map.put("SH00SJ", "松江区");
//		map.put("SH00QP", "青浦区");
//		map.put("SH00FX", "奉贤区");
//		map.put("SH00CM", "崇明区");
		map.put("SHSWSH", "上海市水务局");
		map.put("SHRJSH", "上海市人民检察院");
		
		File jsonFile = new File(RdConfig.get("reindeer.file.machine"));
		String jsonStr = ElectronicCertificateController.getMachineInfo(jsonFile,"linfen01");
		net.sf.json.JSONObject jsonMachine = net.sf.json.JSONObject.fromObject(jsonStr);
		String machinePlace = jsonMachine.optString("machinePlace");
		String machineMAC = jsonMachine.optString("machineMAC");
		String place = jsonMachine.optString("place");
		System.out.println(machineMAC+"----"+machinePlace+"----"+place);
		
//		String itemName = "证照自助查询或打印";
//		String itemCode = "357772903100";
//		String businessCode = "21150202143253421495";
		
		String sessionId = DzUtils.getSessionId("zhzzzdyz","YcbkLoeVwK5rH4y");
		System.out.println(sessionId);
		String str = "";
		if(StringUtils.isNotEmpty(account) && StringUtils.isNotEmpty(name)){
			str = DzUtils.addUser(sessionId,account,name);
		} else {
			Set<String> keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()){
				account = it.next();
				name = map.get(account);
				DzUtils.addUser(sessionId,account,name);
				str += "-----"+account+"："+name+"-----";
			}
//			String idCard = DzUtils.checkIDCard(sessionId,"陈雷","310228198808070818","2014-11-15","2034-11-15","ja",machineMAC,itemName,itemCode,businessCode);
//			System.out.println("加密的身份证号："+idCard);
//			str = DzUtils.getCertInfo("0", idCard, "", sessionId,"SH00JA",machineMAC,itemName,itemCode,businessCode);
		}
		
		AciJsonHelper.writeJsonPResponse(req, res, str);
	}
}
