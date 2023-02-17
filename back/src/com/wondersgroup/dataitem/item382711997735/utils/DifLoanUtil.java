package com.wondersgroup.dataitem.item382711997735.utils;

import java.io.File;
import java.util.List;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;

import com.wondersgroup.dataitem.item382711997735.bean.ProvidentFundCenter;
import com.wondersgroup.dataitem.item382711997735.web.ChangjiangDeltaController;

public class DifLoanUtil {

	/**
	 * 根据中心名称获取中心代码
	 * 
	 * @param zxmc
	 * @return
	 */
	public static String getZxdm(String zxmc) {
		String zxdm = "";
		String filePath = ChangjiangDeltaController.class.getResource("")
				.getPath() + "/template/orgCSJ.xls";
		ImportParams params = new ImportParams();
		params.setStartSheetIndex(0);
		params.setHeadRows(1);
		List<ProvidentFundCenter> centerList = ExcelImportUtil.importExcel(
				new File(filePath), ProvidentFundCenter.class, params);
		for (ProvidentFundCenter center : centerList) {
			if (zxmc.equals(center.getCenterName())) {
				zxdm = center.getCenterCode();
			}
		}
		return zxdm;
	}
	
	public static void main(String[] args) {
		String code = getZxdm("芜湖市住房公积金管理中心");
		System.out.println(code);
	}
}
