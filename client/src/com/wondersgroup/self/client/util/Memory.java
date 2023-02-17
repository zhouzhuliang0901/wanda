package com.wondersgroup.self.client.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

import javax.swing.filechooser.FileSystemView;

import com.sun.management.OperatingSystemMXBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Memory {
	 private static final int CPUTIME = 500;
	 private static final int PERCENT = 100;
	 private static final int FAULTLENGTH = 10;
	 
	  public static void main(String[] args) {
		  System.out.println(getCpuRatioForWindows());
		  //System.out.println(getMemery());
		  //System.out.println(driver());
		 // driver();
		}
	public static String FormetFileSize(long fileS) {
	       DecimalFormat df = new DecimalFormat("#.00");
	       String fileSizeString = "";
	       if (fileS < 1024) {
	           fileSizeString = df.format((double) fileS) + "B";
	       } else if (fileS < 1048576) {
	           fileSizeString = df.format((double) fileS / 1024) + "K";
	       } else if (fileS < 1073741824) {
	           fileSizeString = df.format((double) fileS / 1048576) + "M";
	       } else {
	           fileSizeString = df.format((double) fileS / 1073741824) + "G";
	       }
	       return fileSizeString;
	   }

	   /**
	    * 获取硬盘的每个盘符
	    */
	   public static JSONObject driver(){
	       // 当前文件系统类
	       FileSystemView fsv = FileSystemView.getFileSystemView();
	       // 列出所有windows 磁盘
	       File[] fs = File.listRoots();
	       // 显示磁盘卷标
	       JSONArray json1 = new JSONArray();
	       for (int i = 0; i < fs.length; i++) {
	    	   JSONObject json = new JSONObject();
	    	   json.put("磁盘名", fsv.getSystemDisplayName(fs[i]));
	    	   json.put("总共", FormetFileSize(fs[i].getTotalSpace()));
	    	   json.put("已用", FormetFileSize(fs[i].getTotalSpace()-fs[i].getFreeSpace()));
	    	   json.put("剩余", FormetFileSize(fs[i].getFreeSpace()));
	    	  json1.add(json);
	    	   //System.out.println(json);
	       }
	       JSONObject json2 = new JSONObject();
	       json2.put("data", json1);
		return json2;
	   }
		 // 获取内存使用率
		 public static String getMemery() {
		  OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
		    .getOperatingSystemMXBean();
		  // 总的物理内存+虚拟内存
		  long totalvirtualMemory = osmxb.getTotalSwapSpaceSize();
		  // 剩余的物理内存
		  long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
		  Double compare = (Double) (1 - freePhysicalMemorySize * 1.0
		    / totalvirtualMemory) * 100;
		  String str =  compare.intValue() + "";
		  return str;
		 }

		 /*// 获取文件系统使用率
		 public static List<String> getDisk() {
		  // 操作系统
		  List<String> list = new ArrayList<String>();
		  for (char c = 'A'; c <= 'Z'; c++) {
		   String dirName = c + ":/";
		   File win = new File(dirName);
		   if (win.exists()) {
		    long total = (long) win.getTotalSpace();
		    long free = (long) win.getFreeSpace();
		    Double compare = (Double) (1 - free * 1.0 / total) * 100;
		    String str = c + "盘 :已使用 " + compare.intValue() + "%";
		    list.add(str);
		   }
		  }
		  return list;
		 }*/

		 // 获得cpu使用率
		 public static int getCpuRatioForWindows() {
		  try {
		   String procCmd = System.getenv("windir")
		     + "//system32//wbem//wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
		   // 取进程信息
		   long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
		   Thread.sleep(CPUTIME);
		   long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
		   if (c0 != null && c1 != null) {
		    long idletime = c1[0] - c0[0];
		    long busytime = c1[1] - c0[1];
		    return 
		      + Double.valueOf(
		        PERCENT * (busytime) * 1.0
		          / (busytime + idletime)).intValue()
		     ;
		   } else {
		    return  0;
		   }
		  } catch (Exception ex) {
		   ex.printStackTrace();
		   return  0;
		  }
		 }

		 // 读取cpu相关信息
		 private static long[] readCpu(final Process proc) {
		  long[] retn = new long[2];
		  try {
		   proc.getOutputStream().close();
		   InputStreamReader ir = new InputStreamReader(proc.getInputStream());
		   LineNumberReader input = new LineNumberReader(ir);
		   String line = input.readLine();
		   if (line == null || line.length() < FAULTLENGTH) {
		    return null;
		   }
		   int capidx = line.indexOf("Caption");
		   int cmdidx = line.indexOf("CommandLine");
		   int rocidx = line.indexOf("ReadOperationCount");
		   int umtidx = line.indexOf("UserModeTime");
		   int kmtidx = line.indexOf("KernelModeTime");
		   int wocidx = line.indexOf("WriteOperationCount");
		   long idletime = 0;
		   long kneltime = 0;
		   long usertime = 0;
		   while ((line = input.readLine()) != null) {
		    if (line.length() < wocidx) {
		     continue;
		    }
		    // 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
		    // ThreadCount,UserModeTime,WriteOperation
		    String caption = substring(line, capidx, cmdidx - 1).trim();
		    String cmd = substring(line, cmdidx, kmtidx - 1).trim();
		    if (cmd.indexOf("wmic.exe") >= 0) {
		     continue;
		    }
		    String s1 = substring(line, kmtidx, rocidx - 1).trim();
		    String s2 = substring(line, umtidx, wocidx - 1).trim();
		    if (caption.equals("System Idle Process")
		      || caption.equals("System")) {
		     if (s1.length() > 0)
		      idletime += Long.valueOf(s1).longValue();
		     if (s2.length() > 0)
		      idletime += Long.valueOf(s2).longValue();
		     continue;
		    }
		    if (s1.length() > 0)
		     kneltime += Long.valueOf(s1).longValue();
		   /* if (s2.length() > 0)
		     usertime += Long.valueOf(s2).longValue();*/
		   }
		   retn[0] = idletime;
		   retn[1] = kneltime + usertime;
		   return retn;
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  } finally {
		   try {
		    proc.getInputStream().close();
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		  return null;
		 }

		 /**
		  * 由于String.subString对汉字处理存在问题（把一个汉字视为一个字节)，因此在 包含汉字的字符串时存在隐患，现调整如下：
		  * 
		  * @param src
		  *            要截取的字符串
		  * @param start_idx
		  *            开始坐标（包括该坐标)
		  * @param end_idx
		  *            截止坐标（包括该坐标）
		  * @return
		  */
		 private static String substring(String src, int start_idx, int end_idx) {
		  byte[] b = src.getBytes();
		  String tgt = "";
		  for (int i = start_idx; i <= end_idx; i++) {
		   tgt += (char) b[i];
		  }
		  return tgt;
		 }
		 
		 public static String jsonpost(String args, String args1) {
		        OutputStreamWriter out = null;
		        BufferedReader in = null;
		        StringBuilder result = new StringBuilder();
		        try {
		            URL realUrl = new URL(args);
		            // 打开和URL之间的连接
		            URLConnection conn = realUrl.openConnection();
		            //设置通用的请求头属性
		            conn.setRequestProperty("accept", "*/*");
		            conn.setRequestProperty("connection", "Keep-Alive");
		            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		            // 发送POST请求必须设置如下两行   否则会抛异常（java.net.ProtocolException: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)）
		            conn.setDoOutput(true);
		            conn.setDoInput(true);
		            //获取URLConnection对象对应的输出流并开始发送参数
		            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		            //添加参数
		            out.write(args1);
		            out.flush();
		            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		            String line;
		            while ((line = in.readLine()) != null) {
		                result.append(line);
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
		        return result.toString();
		    }
}