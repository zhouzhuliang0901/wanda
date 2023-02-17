package com.wondersgroup.self.client.config;

import org.apache.commons.lang3.StringUtils;

import wfc.service.config.Config;

public class ClientConfig {

	public static String getPadServerUrl() {
		String padUrl = Config.get("server.padinteraction.url");
		if (StringUtils.isBlank(padUrl))
			padUrl = "http://10.81.16.56:8088/padinteraction/cometd";
		return padUrl;
	}
}
