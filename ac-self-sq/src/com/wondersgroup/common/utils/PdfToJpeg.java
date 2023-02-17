package com.wondersgroup.common.utils;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import sun.misc.BASE64Encoder;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

/**
 * PDF转图片方法
 * @author 冠云
 *
 */
public class PdfToJpeg {
	
	private static final int PICTURE_MULTIPLE = 5;
	
	public static List<byte[]> changePdfToImg(String des1 , String des2) {
		  List<byte[]> list = new ArrayList<byte[]>();
			try {
				File file = new File(des1);
				RandomAccessFile raf = new RandomAccessFile(file, "r");
				FileChannel channel = raf.getChannel();
				MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
						0, channel.size());
				PDFFile pdffile = new PDFFile(buf);
				for (int i = 1; i <= pdffile.getNumPages(); i++) {
					PDFPage page = pdffile.getPage(i);
					// Rectangle rect = new Rectangle(0, 0, 1240, 1750);
					Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox()
							.getWidth()), ((int) page.getBBox().getHeight()));
					int n = 3;
					Image img = page.getImage(rect.width * n, rect.height * n,
							rect, null, // null
							// for
							// the
							// ImageObserver
							true, // fill background with white
							true // block until drawing is done
							);
					BufferedImage tag = new BufferedImage(rect.width * n,
							rect.height * n, BufferedImage.TYPE_INT_RGB);
					System.out.println(tag + "---" + i);

					ByteArrayOutputStream out111 = new ByteArrayOutputStream();
					ImageIO.write(tag, "jpeg", out111);

					tag.getGraphics().drawImage(img, 0, 0, rect.width * n,
							rect.height * n, null);
					FileOutputStream out = new FileOutputStream(des2 + "_" + i
							+ ".jpg"); // 输出到文件流
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
					param2.setQuality(1f, false);// 1f是提高生成的图片质量
					encoder.setJPEGEncodeParam(param2);
					encoder.encode(tag); // JPEG编码
					
					// 转换byte[]数组
					byte[] bytes = image2byte(des2 + "_" + i + ".jpg");
					list.add(bytes);
					// 关闭流
					out.close();

				}
				channel.close();
				raf.close();
				unmap(buf);// 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return list;
			}
		}
	
    public static List<byte[]> pdf2Png(String pdfFile, String pngFile) throws IOException {
    	List<byte[]> list = new ArrayList<byte[]>();
        File file = new File(pdfFile);
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        PDFFile pdffile = new PDFFile(buf);
        
        byte[] byt = null;
        for (int i = 1; i <= pdffile.getNumPages(); i++) {
            // 将文件分页画到图像
            PDFPage page = pdffile.getPage(i);
            // 获取默认缩放的文件的宽度和高度
            Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
            int width = rect.width * PICTURE_MULTIPLE;
            int height = rect.height * PICTURE_MULTIPLE;
            // 生成图像
            Image img = page.getImage(width, height, rect, null, true, true);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(img, 0, 0, width, height, null);
            // 输出到文件流
            FileOutputStream out = new FileOutputStream(pngFile + i + ".jpg");
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            // JPEG编码
            encoder.encode(tag);
            
            byt = PdfToJpeg.image2byte(pngFile + i + ".jpg");
            list.add(byt);
            
            out.close();
        }
        return list;
	}
	  
	  private static void unmap(final Object buffer) {
		  AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				try {
					Method getCleanerMethod = buffer.getClass().getMethod(
							"cleaner", new Class[0]);
					getCleanerMethod.setAccessible(true);
					sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod
							.invoke(buffer, new Object[0]);
					cleaner.clean();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		});
	  }
	  
		//图片到byte数组
	  	public static byte[] image2byte(String path){
		    byte[] data = null;
		    FileImageInputStream input = null;
		    try {
			      input = new FileImageInputStream(new File(path));
			      ByteArrayOutputStream output = new ByteArrayOutputStream();
			      byte[] buf = new byte[1024];
			      int numBytesRead = 0;
			      while ((numBytesRead = input.read(buf)) != -1) {
			      output.write(buf, 0, numBytesRead);
			      }
			      data = output.toByteArray();
			      output.close();
			      input.close();
		    }catch (Exception e) {
		    	e.printStackTrace();
		    }
		    return data;
	  	}
	  	
	  	public static void main(String[] args) {
	  		List<byte[]> list = PdfToJpeg.changePdfToImg(
	  				"C:\\Users\\wanda\\Desktop\\test\\dangan.pdf",
	  				"C:\\Users\\wanda\\Desktop\\test\\dangan.png");
	  		String png = new BASE64Encoder().encode(list.get(0));
	  		System.out.println(png);
		}
	  	
}
