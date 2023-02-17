package wfc.service.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.itextpdf.text.log.SysoCounter;
import com.wondersgroup.self.client.util.LocalMac;

public class Config {

	/** 默认配置文件名称 */
	public final static String CONFIG_FILENAME = "wfc_config.properties";

	/** (配置项名称 wfc.service.config.filename) 配置文件名 */
	public final static String CONFIG_FILENAME_KEY = "wfc.service.config.filename";

	/** 配置名称 (文件名称), 判断配置是否加载的标志, null 表示配置尚未加载, 反之表示已加载 */
	private static String configName = null;

	/** 配置内容 (配置项集) */
	private static Properties configProperties = null;

	static {
		search();
	}

	private Config() {
	}

	/**
	 * 取一个配置项的值
	 * 
	 * @param name
	 *            配置项的名称
	 * @return 配置项的值, 不存在该配置项返回 null
	 */
	public static String get(String name) {
		if (name == null) {
			return null;
		}
		return configProperties.getProperty(name);
	}

	/**
	 * 设一个配置项的值
	 * 
	 * @param name
	 *            配置项的名称
	 * @param value
	 *            配置项的值
	 */
	public static void set(String name, String value) {
		if (name == null || value == null) {
			return;
		}
		configProperties.setProperty(name, value);
	}

	/**
	 * 得到配置名称
	 * 
	 * @return 配置名称
	 */
	public static String getConfigName() {
		return configName;
	}

	/**
	 * 得到所有配置内容, 注意不要对返回值进行任何更新, 否则配置内容也会因此被更新
	 * 
	 * @return 所有配置内容
	 */
	public static Properties getConfigProperties() {
		return configProperties;
	}

	/**
	 * 清空配置 (配置初始化)
	 */
	public static void reset() {
		configName = null;
		search();
	}

	/**
	 * 切换不同的配置文件
	 * 
	 * @param filename
	 *            配置文件名
	 */
	public static void switchFilename(String filename) {
		System.setProperty(CONFIG_FILENAME_KEY, filename);
		reset();
	}

	/**
	 * 查找配置实现方式
	 */
	private static synchronized void search() {
		// 已有其它线程更新了配置, 则不再查找配置
		if (configName != null) {
			return;
		}

		{
			String dir = "C:";
			String filename = dir + File.separator + CONFIG_FILENAME;
			File file = new File(filename);
			//判断C盘根目录是否存在wfc_config.properties配置文件，如果存在直接加载，否则拷贝到C盘根目录再加载
			if (file.exists()) {
				configName = filename;
				configProperties = loadConfig(file);
				return;
			}else{ //获取设备本地MAC
				InetAddress ia;
				String MAC = "";
				String clientConfig = "D:\\Debug\\client\\wfc_config.properties";//客户端配置文件
				
				try {
					ia = InetAddress.getLocalHost();
					MAC = LocalMac.getLocalMac(ia);
					Properties p = loadConfig(new FileInputStream(clientConfig)); //加载客户端配置文件
					p.setProperty("client.mac", MAC);
					p.store(new FileOutputStream(new File(clientConfig)), "");//写出到客户端的配置文件
					configProperties = p;
					return;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}

		// 系统环境中已设置配置文件路径
		{
			String filename = System.getProperty(CONFIG_FILENAME_KEY);
			if (filename != null) {
				configName = filename;
				configProperties = loadConfig(new File(filename));
				return;
			}
		}

		// 系统 classes 根目录下存放配置文件
		{
			InputStream is = Config.class.getResourceAsStream("/"
					+ CONFIG_FILENAME);
			if (is != null) {
				configName = Config.class.getResource("/" + CONFIG_FILENAME)
						.toString();
				configProperties = loadConfig(is);
				return;
			}
		}

		// 系统环境中已设置的用户根目录, 在此目录中存放配置文件
		{
			String dir = System.getProperty("user.dir");
			String filename = dir + File.separator + CONFIG_FILENAME;
			File file = new File(filename);
			if (file.exists()) {
				configName = filename;
				configProperties = loadConfig(file);
				return;
			}
		}

		// 系统根目录中存放配置文件
		{
			String filename = File.separator + CONFIG_FILENAME;
			File file = new File(filename);
			if (file.exists()) {
				configName = filename;
				configProperties = loadConfig(file);
				return;
			}
		}

		// 没有找到配置
		print("没有找到用户配置");
	}
	
	
	/**
	 * 获取本地MAC地址
	 */
	public static String getLoadMac(){
		try {
			//获取ip
			InetAddress ia = InetAddress.getLocalHost();
			System.out.println(ia);
			//获取网卡
			byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
			System.out.println("mac数组长度："+mac.length);
			StringBuffer sb = new StringBuffer("");
			for(int i = 0; i<mac.length; i++){
				if(0!=i){
					sb.append("-");
				}
				//字节转化为整数
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				System.out.println("每8位："+str);
				if(str.length()==1){
					sb.append("0"+str);
				}else{
					sb.append(str);
				}
			}
			String macAddress = sb.toString().toUpperCase();
			System.out.println("本机MAC地址："+macAddress);
			return macAddress;
			
		} catch (UnknownHostException | SocketException  e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 加载xml配置文件
	 */
	private static Properties loadXml(File file) {
		Properties p = new Properties();
		//创建dom4j解析器
		SAXReader reader = new SAXReader();
		//加载document对象
		Document document;
		try {
			document = reader.read(file);
			//获取根节点
			Element root =  document.getRootElement();
			//遍历获取子节点
			int size = root.nodeCount();
			for(int i = 0; i < size; i++){
				Node node = root.node(i);
				if(node instanceof Element){
					//节点名
					String nodeName = node.getName();
					//节点内容
					String nodeValue = node.getText();
					p.setProperty(nodeName, nodeValue);
				}	
			}
			return p;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	/**
	 * 读取配置文件内容
	 * 
	 * @param is
	 *            文件内容字节流
	 * @return 配置内容
	 */
	private static Properties loadConfig(InputStream is) {
		Properties p = new Properties();
		// 属性文件的编码是 ISO-8859-1
		try {
			p.load(is);
			is.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			Config.print(ex.toString());
			return null;
		}
		Properties p2 = new Properties();
		for (Enumeration<?> e = p.propertyNames(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String value = p.getProperty(key);
			// 将 ISO-8859-1 编码转为 GBK 编码
			try {
				key = new String(key.getBytes("ISO-8859-1"), "GBK");
				value = new String(value.getBytes("ISO-8859-1"), "GBK");
			} catch (UnsupportedEncodingException ex) {
				ex.printStackTrace();
				Config.print(ex.toString());
				return null;
			}
			p2.setProperty(key, value);
		}
		print("配置已加载：" + configName);
		return p2;
	}

	/**
	 * 读取配置文件内容
	 * 
	 * @param file
	 *            properties 配置文件
	 * @return 配置内容
	 */
	private static Properties loadConfig(File file) {
		try {
			return loadConfig(new FileInputStream(file));
		} catch (IOException ex) {
			ex.printStackTrace();
			Config.print(ex.toString());
			return null;
		}
	}

	/**
	 * 用 Config 内部打印格式进行打印
	 * 
	 * @param str
	 *            打印内容
	 */
	private static void print(String str) {
		System.out.println("########## " + str + " ##########");
	}

	public static void main(String[] args) {
		String str = Config.class.getResource("/").toString();
		System.out.println(str);
		/*startWriterTestThread("线程 #1", 1, 300);
		startWriterTestThread("线程 #2", 2, 300);
		startWriterTestThread("线程 #3", 1, 300);
		startWriterTestThread("线程 #4", 2, 300);
		startWriterTestThread("线程 #5", 1, 300);*/
	}

	private static void startWriterTestThread(final String name,
			final int method, final int count) {
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < count; i++) {
					if (method == 1) {
						Config.reset();
						System.out.println(name + " reset " + i);
					} else {
						String value = Config
								.get("wfc.service.connection.mode");
						System.out.println(name + " - " + value);
					}
				}
				try {
					Thread.sleep(2);
				} catch (InterruptedException ex) {
				}
			}
		}).start();
	}

}
