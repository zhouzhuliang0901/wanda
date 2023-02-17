package wfc.service.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 读取配置中的配置项<br>
 * 
 * <p>
 * 读取配置项首先需要加载配置, 配置为一个 properties 属性文件, 配置可以存放在不同的文件目录中,
 * 系统按优先级顺序在各个允许存放配置的目录中寻找配置文件, 当正确找到配置后, 系统加载该配置, 如果配置加载成功则在终端上有相应提示,
 * 以下是配置的优先级排列情况, 在前的优先级较高
 * <p style="margin-left:35px">
 * 在系统环境中设置的 properties 配置文件路径, 在系统环境中设置 wfc.service.config.filename 参数,
 * 该参数可在启动应用时设置 (-Dwfc.service.config.filename=[filename]), 也可在应用中通过代码设置
 * System.setProperty("wfc.service.config.filename", "[filename]");
 * <p style="margin-left:35px">
 * 在系统 classes 根目录下, 如果存在 wfc_config.properties 文件, 就加载该配置
 * <p style="margin-left:35px">
 * 在当前应用的根目录下, 即根据系统环境中的 user.dir 参数所得到的路径下 (可通过方法
 * System.getProperty("user.dir") 查实) 如果存在 wfc_config.properties 文件, 就加载该配置
 * <p style="margin-left:35px">
 * 在当前应用所在驱动器的根目录下, 如果存在 wfc_config.properties (可通过方法 new
 * java.io.File("/wfc_config.properties").exists() 查实) 如果存在
 * wfc_config.properties 文件, 就加载该配置
 * 
 * <p>
 * <b>properties 配置文件示例</b>
 * <p style="margin-left:35px">
 * <br>
 * wfc.service.connection.mode = 1 <br>
 * wfc.service.jdbc.driver = org.postgresql.Driver <br>
 * wfc.service.connection.string = jdbc:postgresql://localhost:5432/test <br>
 * wfc.service.connection.user = test <br>
 * wfc.service.connection.password = test <br>
 * 
 * <p>
 * <b>使用示例</b>
 * <p style="margin-left:35px">
 * <code>
 * <br><b>Config.reset();</b>
 * <br>String connectionString = <b>Config.get("wfc.service.connection.string")</b>;
 * <br>System.out.println(<b>Config.getName()</b>);
 * <br><b>Config.switchFilename("c:/app/config_app.properties");</b>
 * <br>Properties p = <b>Config.getProperties()</b>;
 * </code>
 * 
 * @author 虞越
 * @version 1.6 <br>
 *          created 2002/08/01 <br>
 *          revised by 虞越 2002/08/11 修改了 getConfigName 函数,
 *          纠正当只有取过配置项值之后才能得到配置文件名称的 bug <br>
 *          revised by 虞越 2002/08/23 修改了部分函数的名称及细节上的实现, 添加 set 函数 <br>
 *          revised by 虞越 2002/09/11 添加了 getProperties 方法 <br>
 *          revised by 虞越 2002/09/12 构造函数定为 private, 使得无法创建本类的实例 <br>
 *          revised by 虞越 2002/10/30 添加 switchInstance, switchFilename 方法,
 *          在不同的配置实现之间进行切换 <br>
 *          revised by 虞越 2002/12/12 删除 ConfigException 类, 当找不到配置时, 调用
 *          UserConfigHelper 类来得到默认配置 <br>
 *          revised by 虞越 2003/05/22 使用 File 类的 getAbsolutePath 方法替代 getPath 方法,
 *          从而可以打印完整的文件路径 <br>
 *          revised by 虞越 2003/06/05 在搜索配置时加入判断是否已有配置的代码, 解决多线程时重复搜索配置, 产生读配置错误的
 *          bug <br>
 *          revised by 虞越 2003/07/29 去除了查找 JAVA_HOME/jre/lib 目录下配置的步骤 <br>
 *          revised by 虞越 2003/08/18 对通过 WebappConfigServlet 读取配置的方式作了修改, 避免有多个
 *          webapp 时读到其它 webapp WEB-INF 目录所在位置下的配置的错误 <br>
 *          revised by 虞越 2004/09/20 添加了查找 classes 根目录下配置的步骤 <br>
 *          revised by 虞越 2008/05/18 重新整理代码, 取消了多种配置方式, 以提高执行效率, 改 flush 方法为
 *          reset 方法 <br>
 *          revised by 虞越 2009/12/23 改成找不到配置的情况下不抛例外 <br>
 * @see ConfigValue
 */

public class Config {

	/** 默认配置文件名称 */
	public final static String CONFIG_FILENAME = "reindeer_config.properties";

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
		// System.setProperty(CONFIG_FILENAME_KEY, filename);
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
		startWriterTestThread("线程 #1", 1, 300);
		startWriterTestThread("线程 #2", 2, 300);
		startWriterTestThread("线程 #3", 1, 300);
		startWriterTestThread("线程 #4", 2, 300);
		startWriterTestThread("线程 #5", 1, 300);
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