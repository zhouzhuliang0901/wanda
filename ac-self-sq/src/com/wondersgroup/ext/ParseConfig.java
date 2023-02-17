package com.wondersgroup.ext;

import java.io.InputStream;
import java.util.Properties;

import tw.ecosystem.reindeer.config.RdConfig;

public class ParseConfig {
	
	public static String get(String name) {
		String value = "";
		String areaCode = RdConfig.get("reindeer.area.code");
		String environment = RdConfig.get("reindeer.huidao.environment");
		InputStream ins = ParseConfig.class.getResourceAsStream("../ext/"
				+ areaCode + "/config" + "/config_" + environment + "_" + areaCode + ".properties");
		Properties p = new Properties();
		try {
			p.load(ins);
			value = p.getProperty(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}
