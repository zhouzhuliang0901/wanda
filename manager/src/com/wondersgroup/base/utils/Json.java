package com.wondersgroup.base.utils;

import java.util.Map;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSONObject;

public class Json {
	public static void main(String[] args) {
		//device.type.0001={{'应用程序加载的URL地址':'url'},{'是否有高拍仪':'hasCmCapture','备注':'如果设置成Y则表示加载高拍仪驱动,其他则不加载，N：没有;Y-AX：默认高拍仪；Y-ELOAM：良田；BH-IMAGE：凯广荣高拍仪'},{'身份证驱动类型':'idCardType','备注':'如果没有配置或者其他值均为默认，XD：熊帝（新中新）；STD：标准读取（新中新）;MR：默认；MT3Y：明泰','选择项':'[XD,STD,MR,MT3Y]'}}
		//JSONObject obj = (JSONObject) JSONObject.parseObject({'应用程序加载的URL地址':'url','设备ID':'uniqueId','二维码扫描类型':'qrCodePort','界面无操作返回首页（单位：秒）':'idleTime'});
		String resultName = ("{'k1':[{'应用程序加载的URL地址':'url'}],'k2':[{'是否有高拍仪':'hasCmCapture'},{'备注':'如果设置成Y则表示加载高拍仪驱动'}],'k3':[{'身份证驱动类型':'idCardType'},{'备注':'如果没有'},{'选择项':'[XD,STD,MR,MT3Y]'}]}");
		JSONObject jsobject = JSONObject.parseObject(resultName);
		
		 for (Map.Entry<String, Object> entry : jsobject.entrySet()) {
	        System.out.println("key值="+entry.getKey());
	        System.out.println("对应key值的value="+entry.getValue());
	        //{"listDetailDataBean":[{"deptCode":"1111","deptName":"实习部","empAnnualIncome":"1120.00","empJob":"实习0","empName":"员工名0","empNumber":"S1312300"},{"deptCode":"1111","deptName":"实习部","empAnnualIncome":"1120.00","empJob":"实习1","empName":"员工名1","empNumber":"S1312301"},{"deptCode":"1111","deptName":"实习部","empAnnualIncome":"1120.00","empJob":"实习2","empName":"员工名2","empNumber":"S1312302"},{"deptCode":"1111","deptName":"实习部","empAnnualIncome":"1120.00","empJob":"实习3","empName":"员工名3","empNumber":"S1312303"}
	       /* String key = entry.getKey();
	        JSONArray fromObject = JSONArray.fromObject(key);
	        System.out.println(fromObject+"------------------------");*/
	    }
	}
}
