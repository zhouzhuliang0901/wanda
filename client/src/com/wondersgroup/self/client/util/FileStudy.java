package com.wondersgroup.self.client.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FileStudy {
	public static void main(String[] args) {
		System.out.println(restoreFile());
	}
	public static String restoreFile() {
        // 指定文件夹
        File file = new File("D:\\");
        List<File> fileList = null;
        // 包含字符
        String filter = "更新备份";
        if (file != null) {
            if (file.isDirectory()) {
                File[] fileArray = file.listFiles();
                if (fileArray != null && fileArray.length > 0) {
                    fileList = new ArrayList<File>();
                    // 包括文件，文件夹的判断
                    for (File f : fileArray) {
                        String fileName = f.getName();
                        if (fileName.indexOf(filter) != -1) {
                            fileList.add(f);
                        }
                    }
                }
            } else {
                System.out.println("Not Directory.");
            }
        }
        if (fileList != null && fileList.size() > 0) {
        	List array = new ArrayList();
            for (File f : fileList) {
            	String sformat = f.getName().substring(f.getName().length()-19);
            	//字符串转时间
        		SimpleDateFormat sdf_input = new SimpleDateFormat("yyyy-MM-dd hh：mm：ss");
        		SimpleDateFormat sdf_target =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
        		Date date = new Date(System.currentTimeMillis());
        		//System.out.println(sdf.format(date));
        		String dateStr = null;
        		String dateStrSd = null;
        		try {
        			dateStr = sdf_target.format(sdf_input.parse(sformat));
        			dateStrSd = sdf.format(sdf_input.parse(sformat));
        			} catch (Exception e) {
        			}
        		if(sdf.format(date).equals(dateStrSd)){
        			/*System.out.println(dateStr);
        			System.out.println(f.getName());*/
        			array.add(f.getName());
        		}
            }
            return (String) array.get(0);
           // System.out.println(array.get(0));
        }
		return "";
    }
}
