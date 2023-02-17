package com.wondersgroup.infopub.util;

import java.util.UUID;

import com.wondersgroup.infopub.bean.InfopubAddress;
import com.wondersgroup.infopub.bean.InfopubArea;
import com.wondersgroup.infopub.dao.InfopubAddressDao;
import com.wondersgroup.infopub.dao.InfopubAreaDao;

import wfc.service.config.Config;
import net.sf.json.JSONObject;

public class Test {
	
	public static void main(String[] args) {
		
		/*String str = "{'1':'{'deptName':'应用程序加载的URL地址','empAnnualIncome':'url','empName':''}', '2':'{'deptName':'是否有高拍仪','empAnnualIncome':'hasCmCapture','empName':'如果设置成Y则表示加载高拍仪驱动,其他则不加载', 'select':'{'1':'Y','2':'N'}'}','3':'{'deptName':'身份证驱动类型','empAnnualIncome':'idCardType','empName':'如果没有配置或者其他值均为默认'}'}";
		
		JSONObject obj = JSONObject.fromObject(str);
		System.out.println(obj);
		String string = Config.get("device.type.0003");
		System.out.println(string);*/
		
		//ReadExcelData.readExcelDeviceToDB("C:\\Users\\biany\\Desktop\\工行地址.xlsx");
		ReadExcelData.readExcelAddressToDB("C:\\Users\\biany\\Desktop\\浦发地址.xlsx");
		//ReadExcelData.readExcelItemToDB("C:\\Users\\biany\\Desktop\\各行事项.xlsx");
		//ReadExcelData.linkItemAndDevice("工行事项","中国工商银行智慧柜员机","C:\\Users\\biany\\Desktop\\各行事项.xlsx");
		//ReadExcelData.addItemCode("C:\\Users\\biany\\Desktop\\事项编码.xlsx");
		
	}
}
