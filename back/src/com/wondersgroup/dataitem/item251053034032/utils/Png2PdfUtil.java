package com.wondersgroup.dataitem.item251053034032.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class Png2PdfUtil {

	public static File png2pdf(String pnfPath, String pdfPath, float marginTop,
			float newWidth, float newHeight) {
		Document doc = new Document(PageSize.A4, 30, 30, marginTop, 0);
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));
			doc.open();
			doc.newPage();
			Image image = Image.getInstance(pnfPath);
			float height = image.getHeight();
			float width = image.getWidth();
			int percent = getPercent(height, width);
			image.setAlignment(Image.MIDDLE);
			image.scalePercent(percent);
			image.scaleAbsolute(newWidth, newHeight);
			doc.add(image);
			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File outputPdfFile = new File(pdfPath);
		if (!outputPdfFile.exists()) {
			outputPdfFile.deleteOnExit();
			return null;
		}
		return outputPdfFile;
	}

	private static int getPercent(float height, float weight) {
		float percent = 0.0F;
		if (height > weight) {
			percent = PageSize.A4.getHeight() / height * 100;
		} else {
			percent = PageSize.A4.getWidth() / weight * 100;
		}
		return Math.round(percent);
	}
	
	/**
	 * 给PDF文档添加水印
	 * @param pdfFilePath
	 * @param outputFilePath
	 */
    public static void addWaterMark(String pdfFilePath, String outputFilePath) throws Exception {
        //打开pdf文件
        PDDocument doc = PDDocument.load(new File(pdfFilePath));
        doc.setAllSecurityToBeRemoved(true);
        //遍历pdf所有页
        for (PDPage page : doc.getPages()) {
            PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true);
            String ts = "测试水印测试";
            //引入字体文件 解决中文汉字乱码问题
            PDFont font = PDType0Font.load(doc, new FileInputStream("C:\\Users\\wanda\\Desktop\\test\\png\\simsun.ttc"), true);
            float fontSize = 30;
            PDExtendedGraphicsState r0 = new PDExtendedGraphicsState();
            // 水印透明度
            r0.setNonStrokingAlphaConstant(0.2f);
            r0.setAlphaSourceFlag(true);
            cs.setGraphicsStateParameters(r0);
            //水印颜色
            cs.setNonStrokingColor(200, 0, 0);
            cs.beginText();
            cs.setFont(font, fontSize);
            //根据水印文字大小长度计算横向坐标需要渲染几次水印
            float h = ts.length() * fontSize;
            for (int i = 0; i <= 10; i++) {
                // 获取旋转实例
                cs.setTextMatrix(Matrix.getRotateInstance(-150, i * 100, 0));
                cs.showText(ts);
                for (int j = 0; j < 20; j++) {
                    cs.setTextMatrix(Matrix.getRotateInstance(-150, i * 100, j * h));
                    cs.showText(ts);
                }
            }
            cs.endText();
            cs.restoreGraphicsState();
            cs.close();
        }
        doc.save(new File(outputFilePath));
    }
	
	public static void main(String[] args) throws Exception {
		png2pdf("C:\\Users\\wanda\\Desktop\\test\\png\\1.png", 
				"C:\\Users\\wanda\\Desktop\\test\\png\\test.pdf", 170, 500, 500);
	}
}
