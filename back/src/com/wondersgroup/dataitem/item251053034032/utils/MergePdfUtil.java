package com.wondersgroup.dataitem.item251053034032.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class MergePdfUtil {
	
	public static File mergePdf(List<File> files, String targetPath) {
		// pdf合并工具类
		PDFMergerUtility mergePdf = new PDFMergerUtility();
		try {
			for (File f : files) {
				System.out.println("------"+f.getName());
				if (f.exists() && f.isFile()) {
					// 循环添加要合并的pdf
					mergePdf.addSource(f);
				}
			}
			// 设置合并生成pdf文件名称
			mergePdf.setDestinationFileName(targetPath);
			// 合并pdf
			mergePdf.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new File(targetPath);
	}
	
	public static void main(String[] args) {
		String str1 = "C:\\Users\\wanda\\Desktop\\test\\pdf\\1.pdf";
		String str2 = "C:\\Users\\wanda\\Desktop\\test\\pdf\\2.pdf";
		String str3 = "C:\\Users\\wanda\\Desktop\\test\\pdf\\3.pdf";
		String str7 = "C:\\Users\\wanda\\Desktop\\test\\pdf\\7.pdf";
		List<File> files = new ArrayList<File>();
		files.add(new File(str1));
		files.add(new File(str2));
		files.add(new File(str3));
		files.add(new File(str7));
		mergePdf(files, "C:\\Users\\wanda\\Desktop\\test\\pdf\\merge.pdf");
	}
}
