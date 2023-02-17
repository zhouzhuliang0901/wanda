package com.wondersgroup.publicService.utils;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;

import tw.ecosystem.reindeer.config.RdConfig;

import com.wondersgroup.common.utils.HttpUtil;
import com.wondersgroup.publicService.bean.SelmZhallItemInfo;
import com.wondersgroup.publicService.web.ItemGuidController;

public class ItemGuidUtil {
	
	public static String getInfo(Map<String, String> head, String paramString,
			String contentType) {
		String items = "";

		head.put("Content-type", contentType);
		// 设置参数到请求对象中
		StringEntity se = new StringEntity(paramString, "utf-8");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
		items = HttpUtil.doPost(head, se);

		return items;
	}
	
	public static String getResponse(Map<String, String> head, String paramString, String contentType) {
		String body = "";
		head.put("Content-type", contentType);
        // 设置参数到请求对象中
        StringEntity se = new StringEntity(paramString,"utf-8");
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, contentType));
        body = HttpUtil.doPost(head, se);
		return body;
	}
	
	public static List<SelmZhallItemInfo> exportExcelItemId(){
		String areaCode = RdConfig.get("reindeer.area.code");
		ImportParams params1 = new ImportParams();
		params1.setStartSheetIndex(0);
		params1.setHeadRows(1);
		List<SelmZhallItemInfo> itemList = ExcelImportUtil.importExcel(
				new File(ItemGuidController.class.getResource("").getPath()+"template/区级公共服务事项-"+areaCode+".xlsx"),
//				new File(file),
				SelmZhallItemInfo.class,
				params1);
		return itemList;
	}
	
	public static void main(String[] args) {
		exportExcelItemId();
	}
}
