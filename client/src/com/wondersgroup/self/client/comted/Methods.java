package com.wondersgroup.self.client.comted;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;


import net.coobird.thumbnailator.Thumbnails;

import wfc.service.config.Config;
import wfc.service.log.Log;

import com.wondersgroup.self.client.util.DowLoadZip;
import com.wondersgroup.self.client.util.FileStudy;
import com.wondersgroup.self.client.util.ModifyXml;
import com.wondersgroup.self.client.util.ReadXMLContent;
import com.wondersgroup.self.client.util.Screen;
import com.wondersgroup.self.client.util.UpImage;
import com.wondersgroup.self.client.util.Zip;

@SuppressWarnings("all")
public class Methods {

	// 截屏
	public void snapshots(String string) {
		// 保存到本地
		Screen cam = new Screen(Config.get("screen"), "png");
		cam.snapshot();
		//第一种
        try {
			Thumbnails.of(new File(Config.get("screen")+"1.png"))
			.size(800, 700)
			.toFile(new File(Config.get("screen")+"2.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 调用方法从本地读取图片保存数据库为BLOG二进制。
		UpImage uploadPic = new UpImage();
		try {
			uploadPic.UploadPicScreen(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("调用接口成功");
	}

	// 重启
		public void restart() {
			try {
				Runtime.getRuntime().exec("shutdown -r ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	/**
	 * 重启debug 获取debug进程，杀死debug进程，启动debug
	 */
	public void restartDebug() {
		//杀死debug进程
		deleteDebug();
		// 启动debug
		RunDebug();

	}
	
	/**
	 * 杀死debug进程
	 */
	public void deleteDebug() {
		//debug进程名
		//String prefix = "SelfTerminal";
		String checkSelf = Config.get("checkSelf");
		//debug自启脚本进程
		//String checkSelf = "CheckSelfTerminalStarted";
		String prefix = Config.get("debugExeName");
		// 文件读取流
		BufferedReader out = null;
		try {
			// 创建系统进程
			ProcessBuilder pb = new ProcessBuilder("tasklist");
			Process p = pb.start();
			// 读取进程信息
			out = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(p.getInputStream()),
					Charset.forName("GB2312")));
			// 创建存放进程的集合
			List<String> list = new ArrayList<>();
			// 读取并放到list
			String ostr = null;
			while ((ostr = out.readLine()) != null) {
				list.add(ostr);
			}
			// 遍历所有进程
			for (int i = 3; i < list.size(); i++) {
				String process = list.get(i).substring(0, 25).trim(); // 进程名
				String pid = list.get(i).substring(25, 35).trim(); //进程号
				//System.out.println(process+" "+pid);
				// System.out.println(process+":"+pid+"\n");
				// 匹配指定的进程名，若匹配到，则立即杀死,模糊匹配
				if (process.startsWith(prefix)) {
					// System.out.println(process+":"+pid+"\n");
					Runtime.getRuntime().exec(
							"wmic process where name='" + process + "' delete");
					Log.info("self已杀死");
				}
				if(process.startsWith(checkSelf)){
					//System.out.println(process+" "+pid);
					Runtime.getRuntime().exec(
							"taskkill /F /PID "+pid);
					Log.info("checkSelf已杀死");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
		
	// 调用系统的debug的exe，并运行
	public void RunDebug() {
		Process process = null;
		String pathExe = Config.get("debugExeName"); // (C:\ProgramFiles\Tencent\QQ\Bin\qq.exe)
		String pathFile = Config.get("debugExeWorkFile");
		String pathUrl = pathExe+"/"+pathFile;
		try {
			Runtime runtime = Runtime.getRuntime();
			String[] str = { "cmd", "/c", pathUrl };
			File workFile = new File(Config.get("debugExeWorkFile"));
			process = runtime.exec(str, null, workFile);
			System.out.println("打开成功");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("没有找到exe文件");
		}
	}
		
	// 默认60秒后关机
	public void shutdown() {
		// 关机
		try {
			Runtime.getRuntime().exec("Shutdown -s");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");//设置日期格式
		System.out.println(df.format(new Date()));
		File file1 = new File(Config.get("filePath")+"/"+Config.get("fileName"));  
	        //将原文件夹更改为A，其中路径是必要的。注意  
	    file1.renameTo(new File(Config.get("filePath")+"/"+Config.get("fileName")+"更新备份"+df.format(new Date()))); 
	
	}
	// 下载备份解压debug
	public void debugZip() {
		String photoUrl =Config.get("url")+"/infopub/deviceinfo/downloadDebug.do?stAttachId="+Config.get("stAttachId");
        String fileName = Config.get("fileName");
        String filePath = Config.get("filePath");
       // System.out.println(photoUrl+fileName+filePath);
        DowLoadZip dowLoadZip = new DowLoadZip();
        Zip zip = new Zip();
        dowLoadZip.saveUrlAs(photoUrl, filePath,fileName,"GET");
        //杀死旧的debug
        Log.info("开始杀进程");
        deleteDebug();
		 try
		    {
		        Thread.sleep(5000);//休眠五秒执行方法
		    }
		    catch (InterruptedException e)
		    {
		        e.printStackTrace();
		    }
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");//设置日期格式
		File file1 = new File(Config.get("filePath")+"/"+Config.get("fileName"));  
	        //将原文件夹更改为A，其中路径是必要的。注意  
		file1.renameTo(new File(Config.get("filePath")+"/"+Config.get("fileName")+"更新备份"+df.format(new Date()))); 
	   
		try
	    {
	        Thread.sleep(5000);//休眠五秒执行方法
	    }
	    catch (InterruptedException e)
	    {
	        e.printStackTrace();
	    }
	    File file = new File(Config.get("filePath")+"/"+Config.get("fileName")+".zip");
	    zip.unZip(file, Config.get("filePath"));
	    Log.info("开始重命名");
	    //想命名的原文件夹的路径  
        File fileRename = new File(Config.get("filePath")+"/"+Config.get("downName"));  
        //将原文件夹更改为A，其中路径是必要的。注意  
        fileRename.renameTo(new File(Config.get("filePath")+"/"+Config.get("fileName"))); 
	    try
	    {
	        Thread.sleep(5000);//休眠10秒执行方法
	    }
	    catch (InterruptedException e)
	    {
	        e.printStackTrace();
	    }
	    Log.info("执行读取xml操作");
	    xmlContent();
	}
	
	public void resFiled() {
		FileStudy fileStudy = new FileStudy();
		System.out.println(fileStudy.restoreFile());
		//关闭debug
		deleteDebug();;
		File file1 = new File(Config.get("filePath")+"/"+fileStudy.restoreFile());  
		//删除原有得debug
		File file2 = new File(Config.get("filePath")+"/"+Config.get("fileName"));
		//删除debug文件
		while(file2.exists()){
			deleteDirectories(file2);	
		}
        //将原文件夹更改为A，其中路径是必要的。注意  
		file1.renameTo(new File(Config.get("filePath")+"/"+Config.get("fileName"))); 
	}
	
	
	
	//删除文件及其子路径下的文件
	public void deleteDirectories(File file){
		if(file.isFile()){
			file.delete();
		}else{
			File[] files = file.listFiles();
			for(File file1 : files){
				deleteDirectories(file1);
			}
			file.delete();
		}
	}
	
	
	
	
	/**
	 * 说明：根据指定URL将文件下载到指定目标位置
	 * 
	 * @param urlPath
	 *            下载路径
	 * @param downloadDir
	 *            文件存放目录
	 * @return 返回下载文件
	 */
	@SuppressWarnings("all")
	public static File downloadFile(String urlPath, String downloadDir) {
		File file = null;
		try {
			// 统一资源
			URL url = new URL(urlPath);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			//设置超时
			httpURLConnection.setConnectTimeout(1000*5);
			//设置请求方式，默认是GET
			httpURLConnection.setRequestMethod("POST");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 打开到此 URL引用的资源的通信链接（如果尚未建立这样的连接）。
			httpURLConnection.connect();
			// 文件大小
			int fileLength = httpURLConnection.getContentLength();
 
			/*// 控制台打印文件大小
			System.out.println("您要下载的文件大小为:" + fileLength / (1024 * 1024) + "MB");*/
 
			// 建立链接从请求中获取数据
			URLConnection con = url.openConnection();
			BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
			// 指定文件名称(有需求可以自定义)
			String fileFullName = "aaa.docx";
			// 指定存放位置(有需求可以自定义)
			String path = downloadDir + File.separatorChar + fileFullName;
			file = new File(path);
			// 校验文件夹目录是否存在，不存在就创建一个目录
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
 
			OutputStream out = new FileOutputStream(file);
			int size = 0;
			int len = 0;
			byte[] buf = new byte[2048];
			while ((size = bin.read(buf)) != -1) {
				len += size;
				out.write(buf, 0, size);
				// 控制台打印文件下载的百分比情况
				//System.out.println("下载了-------> " + len * 100 / fileLength + "%\n");
			}
			// 关闭资源
			bin.close();
			out.close();
			System.out.println("文件下载成功！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件下载失败！");
		} finally {
			return file;
		}
	}

	//调用系统的exe文件，并运行
	public void Runexe(){
		Process process =null;
		String path =Config.get("exeUrl"); //(C:\Program Files\Tencent\QQ\Bin\qq.exe)
		try { 
		Runtime runtime = Runtime.getRuntime(); 
		String[] str = {"cmd","/c",path};
		File workFile = new File(Config.get("exeWorkFile"));
		process = runtime.exec(str,null,workFile);
		System.out.println("打开成功");
		} catch (IOException e) { 
		e.printStackTrace(); 
		Log.info("没有找到exe文件");
		System.out.println("没有找到exe文件");
		}
	}
	
	
	
	//关闭系统的exe文件
		public void Stopexe(){
			try {
				//Runtime.getRuntime().exec("cmd.exe /c c:\\windows\\system32\\taskkill /f /im  "+Config.get("exeName"));
				Runtime.getRuntime().exec("cmd.exe /C start wmic process where name='"+Config.get("exeName")+"' call terminate");
				System.out.println("关闭成功");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("关闭失败");
			}
		}
		// 修改xml
		public void modify() {
			ModifyXml modifyXml = new ModifyXml();
					try {
						modifyXml.UploadXMl();
						Log.info("修改xml成功");
						System.out.println("修改xml成功");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
		}
		
		//xml内容
		public void xmlContent(){
			ReadXMLContent xmlContent = new ReadXMLContent();
			String str = null;
			try {
				xmlContent.readXMLContent();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
}
