package com.wondersgroup.self.client;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import wfc.service.config.Config;

import com.wondersgroup.self.client.comted.CometFactory;
import com.wondersgroup.self.client.comted.Methods;
import com.wondersgroup.self.client.util.DiskUtil;
import com.wondersgroup.self.client.util.Memory;

@SuppressWarnings("all")
public class SelfClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("dd", "runexe");
		CometFactory.getInstance().send("/client/", data);
		//读取设备xml并保存数据库
		Methods methods = new Methods();
		methods.xmlContent();
		
		//读取设备硬盘序列号并保存数据库进行加密
		/*DiskUtil diskUtil = new DiskUtil();
		diskUtil.KeyEncryptionSave();*/
		
		Thread a = new Thread(new AThread());// 建立一个新的线程的对象，这里只能引用静态的方法
		Thread b = new Thread(new BThread());// 建立一个新的线程的对象，这里只能引用静态的方法
		//Thread c = new Thread(new CThread());
		a.start();// 开启线程a 调用是否在线接口
		b.start();// 开启线程b 调用设备信息接口
		//c.start();// 开启线程c 调用设备信息接口
	}

	public static class AThread implements Runnable {// 通过实现Runnable接口来创建线程类
		public void run() {// run方法，里面包含需要执行的任务
			try {// try代码块，当发生异常时会转到catch代码块中
				while (true) {// 定义死循环
					Thread.sleep(5 * 1000);// 死循环中的线程每5s激活一次
					String resultName = jsonpost(Config.get("url")
							+ "/infopub/deviceinfo/isonline.do",
							"&stDeviceMac=" + Config.get("client.mac"));
					//System.out.println(resultName);
				}
			} catch (InterruptedException e) {
			}// 当try代码块有异常时转到catch代码块
		}
	}

	public static class BThread implements Runnable {// 通过实现Runnable接口来创建线程类
		public void run() {// run方法，里面包含需要执行的任务
			try {// try代码块，当发生异常时会转到catch代码块中
				while (true) {// 定义死循环
					Thread.sleep(5 * 1000 * 60);// 死循环中的线程每5分钟激活一次
					// Thread.sleep(6000);//死循环中的线程每400ms激活一次
					Memory memory = new Memory();
					/*
					 * System.out.println(memory.driver());
					 * System.out.println(memory.getCpuRatioForWindows());
					 * System.out.println(memory.getMemery());
					 */
					String resultName = jsonpost(
							Config.get("url") + "/infopub/deviceresult/save.do",
							"&ST_DEVICE_MAC=" + Config.get("client.mac")
									+ "&NM_MEM_USED=" + memory.getMemery()
									+ "&NM_CPU_USED="
									+ memory.getCpuRatioForWindows()
									+ "&CL_HD_USED=" + memory.driver());
					//System.out.println(resultName);
				}
			} catch (InterruptedException e) {
			}// 当try代码块有异常时转到catch代码块
		}
	}

	/*public static class CThread implements Runnable {// 通过实现Runnable接口来创建线程类
		Process process = null;

		public void run() {// run方法，里面包含需要执行的任务
			try {// try代码块，当发生异常时会转到catch代码块中
				while (true) {// 定义死循环
					Thread.sleep(5 * 1000);// 死循环中的线程每30s激活一次
					try {
						Process p = Runtime.getRuntime().exec(
								"cmd /c tasklist ");
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						InputStream os = p.getInputStream();
						byte b[] = new byte[256];
						while (os.read(b) > 0)
							baos.write(b);
						String s = baos.toString();
						// System.out.println(s);
						if (s.indexOf(Config.get("procedureName")) >= 0) {
							// System.out.println( "进程启动 ");
						} else {
							// System.out.println( "进程没有启动");
							Process process = null;
							String path = Config.get("procedureUrl"); // (C:\Program
							try {
								Runtime runtime = Runtime.getRuntime();
								//process = runtime.exec(path);  
								 Desktop desktop = Desktop.getDesktop();
								 desktop.open(new File(path));
//								runtime.exec("cmd.exe /c cd" + path);
//								runtime.exec("SelfTerminal.exe");
//								Runtime.getRuntime().exec("SelfTerminal.exe",
//										null, new File(path));
								// process = runtime.exec(path);
								System.out.println("打开成功");
							} catch (IOException e) {
								e.printStackTrace();
								System.out.println("没有找到exe文件");
							}
						}
					} catch (java.io.IOException ioe) {
					}
				}
			} catch (InterruptedException e) {
			}// 当try代码块有异常时转到catch代码块
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/

	public static String jsonpost(String args, String args1) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		StringBuilder resultName = new StringBuilder();
		try {
			URL realUrl = new URL(args);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求头属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行 否则会抛异常（java.net.ProtocolException: cannot write
			// to a URLConnection if doOutput=false - call setDoOutput(true)）
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流并开始发送参数
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			// 添加参数
			out.write(args1);
			out.flush();
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				resultName.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return resultName.toString();
	}

}