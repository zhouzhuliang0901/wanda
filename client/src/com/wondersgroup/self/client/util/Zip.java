package com.wondersgroup.self.client.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import wfc.service.config.Config;


public class Zip {
	private static int BUFFER_SIZE = 1024;
	/**

	  * zip解压  

	  * @param srcFile        zip源文件

	  * @param destDirPath     解压后的目标文件夹

	  * @throws RuntimeException 解压失败会抛出运行时异常

	  */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		/*String photoUrl = "http://localhost:8080/ac-self-manager/infopub/deviceinfo/downloadDebug.do?stAttachId=6225ec32-c595-4698-a7b4-174ac1dd5b91";
        String fileName = "7-zip";
        String filePath = "D:";
        DowLoadZip dowLoadZip = new DowLoadZip();
        dowLoadZip.saveUrlAs(photoUrl, filePath,fileName,"GET");
        System.out.println("下载完成！");
		 try
		    {
		        Thread.sleep(5000);//休眠五秒执行方法
		    }
		    catch (InterruptedException e)
		    {
		        e.printStackTrace();
		    }*/
		File file1 = new File(Config.get("filePath")+"/"+Config.get("fileName"));  
        //将原文件夹更改为A，其中路径是必要的。注意  
    file1.renameTo(new File(Config.get("filePath")+"/"+Config.get("fileName")+"备份")); 
	    System.out.println(System.currentTimeMillis());
	    try
	    {
	        Thread.sleep(5000);//休眠五秒执行方法
	    }
	    catch (InterruptedException e)
	    {
	        e.printStackTrace();
	    }
	    System.out.println(System.currentTimeMillis());
	    File file = new File(Config.get("filePath")+"/"+Config.get("fileName")+".zip");
	    unZip(file, Config.get("filePath"));
	}
	 public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
	     long start = System.currentTimeMillis();

	     // 判断源文件是否存在

	     if (!srcFile.exists()) {
	         throw new RuntimeException(srcFile.getPath() + "所指文件不存在");

	     }

	     // 开始解压

	     ZipFile zipFile = null;

	     try {
	         zipFile = new ZipFile(srcFile,Charset.forName("gbk"));

	         Enumeration<?> entries = zipFile.entries();

	         while (entries.hasMoreElements()) {
	             ZipEntry entry = (ZipEntry) entries.nextElement();

	             System.out.println("解压" + entry.getName());

	             // 如果是文件夹，就创建个文件夹

	             if (entry.isDirectory()) {
	                 String dirPath = destDirPath + "/" + entry.getName();

	                 File dir = new File(dirPath);

	                 dir.mkdirs();

	             } else {
	                 // 如果是文件，就先创建一个文件，然后用io流把内容copy过去

	                 File targetFile = new File(destDirPath + "/" + entry.getName());

	                 // 保证这个文件的父文件夹必须要存在

	                 if(!targetFile.getParentFile().exists()){
	                     targetFile.getParentFile().mkdirs();

	                 }

	                 targetFile.createNewFile();

	                 // 将压缩文件内容写入到这个文件中

	                 InputStream is = zipFile.getInputStream(entry);

	                 FileOutputStream fos = new FileOutputStream(targetFile);

	                 int len;

	                 byte[] buf = new byte[BUFFER_SIZE];

	                 while ((len = is.read(buf)) != -1) {
	                     fos.write(buf, 0, len);

	                 }

	                 // 关流顺序，先打开的后关闭

	                 fos.close();

	                 is.close();

	             }

	         }

	         long end = System.currentTimeMillis();

	         System.out.println("解压完成，耗时：" + (end - start) +" ms");

	     } catch (Exception e) {
	         throw new RuntimeException("unzip error from ZipUtils", e);

	     } finally {
	         if(zipFile != null){
	             try {
	                 zipFile.close();

	             } catch (IOException e) {
	                 e.printStackTrace();

	             }

	         }

	     }

	 }
}
