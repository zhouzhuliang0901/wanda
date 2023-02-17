package com.wondersgroup.dataitem.item325112348123.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import wfc.service.log.Log;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.wondersgroup.dataitem.item276652591922.utils.PdfUtil;

@Controller
public class CityAppearanceController {
	
	/**
	 * 绿容局告知承诺书PDF文件生成
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping("/selfapi/landscapingAndCityAppearance/createLetterOfCommitmentPDF.do")
	public void createLetterOfCommitmentPDF(HttpServletRequest req,
	HttpServletResponse res) throws IOException {
		// TODO
		String filePath = CityAppearanceController.class.getResource("").getPath()+"template/";
    	String fileName = "从事城市生活垃圾经营性清扫、收集、运输、处置服务告知承诺书.pdf";
    	filePath += fileName;
    	// 字体模板
    	String fontName = CityAppearanceController.class.getResource("").getPath()
    			+"template/simsun.ttc,1";
		BaseFont bf = null;
		try {
			bf = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		data.put("uName", new Object[]{"测试", bf});
		data.put("legalRepresentative", new Object[]{"测试", bf});
		data.put("address", new Object[]{"测试测试测试测试测试测试测试测试测试测试", bf});
		data.put("contact", new Object[]{"13545161135", bf});
		
		byte[] btContent = null;
		try {
			btContent = PdfUtil.fillData(filePath, data);
//			FileUtil.getFileFromBytes(btContent, RdConfig.get("reindeer.credit.pdf.url")+"\\残疾人通心优惠套套餐申请表_"+System.currentTimeMillis()+".pdf");
		} catch (DocumentException e) {
			Log.debug(e);
		}finally {}
		
		if(btContent != null){
			OutputStream out = res.getOutputStream();
			res.setContentType("application/pdf");
			out.write(btContent);
			out.close();
		} else {
			
		}
	}
}
