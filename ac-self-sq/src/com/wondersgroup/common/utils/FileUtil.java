package com.wondersgroup.common.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	/**
	 * 文件转化为字节数组
	 * @param file
	 * @return
	 */
	public static byte[] getBytesFromFile(File file) {
		if (file == null) {
			return null;
		}
		long length = file.length();
		length = length > Integer.MAX_VALUE ? Integer.MAX_VALUE : length;
		FileInputStream stream = null;
		ByteArrayOutputStream out = null;
		try {
			stream = new FileInputStream(file);
			out = new ByteArrayOutputStream((int) length);
			byte[] b = new byte[10240];
			int n;
			while ((n = stream.read(b, 0, 10240)) > 0)
				out.write(b, 0, n);

			byte[] buf = out.toByteArray();
			out.close();
			stream.close();
			return buf;
		} catch (IOException e) {
		}
		finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 字节数组转文件
	 * @param bt
	 * @param outputFile
	 * @return
	 */
	public static File getFileFromBytes(byte[] bt, String outputFile) {
		BufferedOutputStream stream = null;
		FileOutputStream fstream = null;
		File file = null;
		try {
			file = new File(outputFile);
			fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(bt);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fstream != null) {
				try {
					fstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
}
