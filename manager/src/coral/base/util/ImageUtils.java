/**
 * Project: GdPlatform
 * Source file: ImageUtils.java
 * Create At 2012-7-10 下午01:17:46
 * Create By 龚云
 */
package coral.base.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiWriter;

/**
 * 图片工具类
 * 
 * @author 龚云
 * 
 */
public class ImageUtils {

	/**
	 * 将图片转换为JPEG
	 * 
	 * @param from
	 * @param to
	 */
	public static void convertToJPEG(File from, File to) {
		try {
			BufferedImage image = ImageIO.read(from);
			if (image != null) {
				ImageProducer ip = image.getSource();
				JimiWriter writer = Jimi.createJimiWriter(to.getAbsolutePath());
				writer.setSource(ip);
				writer.putImage(to.getAbsolutePath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从指定点剪切指定大小的图片
	 * 
	 * @param from
	 *            源图
	 * @param to
	 *            目标图片
	 * @param x
	 *            左上角x坐标
	 * @param y
	 *            左上角y坐标
	 * @param width
	 *            裁剪区域宽度
	 * @param height
	 *            裁剪区域高度
	 */
	public static void cut(File from, File to, int x, int y, int width,
			int height) {
		InputStream is = null;
		OutputStream os = null;
		ImageInputStream iis = null;
		ImageOutputStream oos = null;
		try {
			String suffix = getSuffix(from.getName());
			Iterator<ImageReader> readers = ImageIO
					.getImageReadersByFormatName(suffix);
			ImageReader reader = (ImageReader) readers.next();
			is = new BufferedInputStream(new FileInputStream(from));
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, false);
			ImageReadParam param = reader.getDefaultReadParam();
			Rectangle rect = new Rectangle(x, y, width, height);
			param.setSourceRegion(rect);
			BufferedImage image = reader.read(0, param);
			os = new FileOutputStream(to);
			oos = ImageIO.createImageOutputStream(os);
			ImageIO.write(image, suffix, os);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (iis != null)
					iis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取文件后缀，若没有则默认为JPEG
	 * 
	 * @param fileName
	 * @return
	 */
	protected static String getSuffix(String fileName) {
		String suffix = "JPEG";
		if (fileName.contains(".")) {
			String tmpSuffix = fileName
					.substring(fileName.lastIndexOf(".") + 1);
			for (String imgSuffix : IMG_SUFFIXES) {
				if (imgSuffix.equalsIgnoreCase(tmpSuffix)) {
					suffix = imgSuffix;
					break;
				}
			}
		}
		return suffix;
	}

	/**
	 * 按角度旋转图片
	 * 
	 * @param from
	 *            源图
	 * @param to
	 *            目标图片
	 * @param degree
	 *            角度
	 */
	public static void rotate(File from, File to, int degree) {
		try {
			BufferedImage img = ImageIO.read(from);
			int width = img.getWidth();
			int height = img.getHeight();
			if (Math.abs(degree) == 90 || Math.abs(degree) == 270) {
				int tmp = width;
				width = height;
				height = tmp;
			}
			BufferedImage newImg = new BufferedImage(width, height, img
					.getType());
			Rotate rotate = new Rotate(degree);
			Image image = Toolkit.getDefaultToolkit().createImage(
					new FilteredImageSource(img.getSource(), rotate));
			Graphics g = newImg.getGraphics();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			ImageIO.write(newImg, getSuffix(to.getName()), to);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 支持的图片后缀类型
	 */
	private static final String[] IMG_SUFFIXES = ImageIO
			.getReaderFileSuffixes();

}
