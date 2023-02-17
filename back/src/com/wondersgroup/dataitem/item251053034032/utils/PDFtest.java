package com.wondersgroup.dataitem.item251053034032.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import wfc.service.log.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class PDFtest {
	
	public static void main(String[] args) throws FileNotFoundException {
		long a = System.currentTimeMillis();
		String filename = "C:\\Users\\wanda\\Desktop\\test\\pdf\\showStuffPicForBytes.pdf";
		int pageNumber = splitPdf(filename);
		
		List<String> mergePngList = new ArrayList<String>();
		List<File> pdfFiles = new ArrayList<File>();
		String mergePngPath = "";
		String mergePdfPath = "";
		String pdfPath = "C:\\Users\\wanda\\Desktop\\test\\pdf\\pdf.pdf";
		int remainder = pageNumber%2;
		int quotient = pageNumber/2;
		try{
			for(int i=1;i<=quotient;i++){
				List<File> files = new ArrayList<File>();
				String pdfPath1 = filename.substring(0, filename.length() - 4)+"("+(2*(i-1)+1)+").pdf";
				String pngPath1 = filename.substring(0, filename.length() - 4)+"("+(2*(i-1)+1)+").png";
				String pdfPath2 = filename.substring(0, filename.length() - 4)+"("+(2*i)+").pdf";
				String pngPath2 = filename.substring(0, filename.length() - 4)+"("+(2*i)+").png";
				pdf2png(pdfPath1, pngPath1, "png");
				pdf2png(pdfPath2, pngPath2, "png");
				files.add(new File(pngPath1));
				files.add(new File(pngPath2));
				mergePngPath = "C:\\Users\\wanda\\Desktop\\test\\png\\merge("+i+").png";
				try {
					compositePhoto(files, mergePngPath);
					mergePngList.add(mergePngPath);
					files.get(0).deleteOnExit();files.get(1).deleteOnExit();
					new File(pdfPath1).deleteOnExit();new File(pdfPath2).deleteOnExit();
//					System.out.println(mergePngPath);
				} catch (IOException e) {
					Log.debug(e);
				}
			}
			if(remainder == 1){
//				System.out.println("------奇数页最后一页------");
				String lastPdfPath = filename.substring(0, filename.length() - 4)+"("+pageNumber+").pdf";
				mergePngPath = "C:\\Users\\wanda\\Desktop\\test\\png\\merge("+pageNumber+").png";
				pdf2png(lastPdfPath, mergePngPath, "png");
				mergePngList.add(mergePngPath);
				new File(mergePngPath).deleteOnExit();new File(lastPdfPath).deleteOnExit();
			}
			for(int j=1;j<=mergePngList.size();j++){
//				System.out.println("第"+j+"页合并图片转PDF");
				mergePdfPath = mergePngList.get(j-1).substring(0, mergePngList.get(j-1).length() - 4)+".pdf";
				File mergePdf = null;
				if(j == mergePngList.size() && remainder == 1){
					mergePdf = Png2PdfUtil.png2pdf(mergePngList.get(j-1), mergePdfPath, 170f, 500f, 500f);
					pdfFiles.add(mergePdf);
				} else {
					mergePdf = Png2PdfUtil.png2pdf(mergePngList.get(j-1), mergePdfPath, 80f, 500f, 700f);
					pdfFiles.add(mergePdf);
				}
				new File(mergePngList.get(j-1)).deleteOnExit();
			}
			MergePdfUtil.mergePdf(pdfFiles, pdfPath);
			for(File f : pdfFiles){
				if(f.exists()){
					f.deleteOnExit();
				}
			}
		} catch (Exception e) {
			Log.debug("谁知道哪里会出问题呢----"+e);
		}
		long b = System.currentTimeMillis();
		System.out.println("总共所用时间："+(double)(b-a)/1000+"秒");
	}
	
	public static List<String> pdf2png(String file1, String file2, String type) {
		// 将pdf装图片 并且自定义图片得格式大小
		File file = new File(file1);
		File pngFile = null;
		List<String> list = new ArrayList<String>();
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			BufferedImage image = renderer.renderImageWithDPI(0, 144);
			// 输出的png文件
			pngFile = new File(file2);
			ImageIO.write(image, type, pngFile);
			list.add(file2);
			doc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int splitPdf(String filename) {
		PdfReader reader = null;
		try {
			reader = new PdfReader(filename);
		} catch (IOException e) {
			Log.error(e);
		}
		int numberOfPages = reader.getNumberOfPages();
		int newFileCount = 1;
		// PageNumber是从1开始计数的1
		int pageNumber = 1;
		while (pageNumber <= numberOfPages) {
			Document doc = new Document();
			String splitFileName = filename.substring(0, filename.length() - 4)
					+ "(" + newFileCount + ").pdf";
			PdfCopy pdfCopy = null;
			try {
				pdfCopy = new PdfCopy(doc, new FileOutputStream(splitFileName));
			} catch (FileNotFoundException e) {
				Log.error(e);
			} catch (DocumentException e2) {
				Log.error(e2);
			}
			doc.open();
			// 将pdf按页复制到新建的PDF中
			doc.newPage();
			PdfImportedPage page = pdfCopy.getImportedPage(reader, pageNumber);
			try {
				pdfCopy.addPage(page);
			} catch (BadPdfFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			doc.close();
			newFileCount++;
			pageNumber++;
			pdfCopy.close();
		}
		return numberOfPages;
	}
	
	public static File compositePhoto(List<File> files, String fileName)
			throws IOException {
		int width = 0;
		int height = 0;
		for (File file : files) {
			BufferedImage bi = ImageIO.read(file);
			width = bi.getWidth();
			height = height + bi.getHeight();
		}
		// 合成的图片
		BufferedImage cp = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = cp.getGraphics();
		for (int i = 0; i < files.size(); i++) {
			BufferedImage bi = ImageIO.read(files.get(i));
			if (i == 0) {
				g.drawImage(bi, 0, 0, null);// 第一个
			} else {
				BufferedImage bAfter = ImageIO.read(files.get(i - 1));
				g.drawImage(bi, 0, bAfter.getHeight(), null);// 后面部分
			}
		}
		// 检查文件
		File f = new File(fileName);
		if (f.exists()) {
			f.delete();
			f.createNewFile();
		} else {
			f.createNewFile();
		}
		// 输出图片
		ImageIO.write(cp, "png", f);
		return f;
	}

}
