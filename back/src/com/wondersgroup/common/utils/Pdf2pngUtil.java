package com.wondersgroup.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class Pdf2pngUtil {

	public static List<String> pdf2png(String file1, String file2, String type) {
		// 将pdf装图片 并且自定义图片得格式大小
		File file = new File(file1);
		File pngFile = null;
		List<String> list = new ArrayList<String>();
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < pageCount; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 144);
				// 输出的png文件
				pngFile = new File(file2 + i + "." + type);
				ImageIO.write(image, type, pngFile);
				list.add(file2 + i + "." + type);
			}
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
