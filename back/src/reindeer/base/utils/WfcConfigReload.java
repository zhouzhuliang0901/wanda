package reindeer.base.utils;

import java.net.URL;

import reindeer.base.app.AclFilter;
import tw.ecosystem.reindeer.config.RdConfig;
import wfc.service.config.Config;

public class WfcConfigReload {

	public static void reload() {
		URL path = AclFilter.class.getResource("/reindeer_config.properties");
		Config.switchFilename(path.getFile());
		Config.set("wfc.service.connection.mode", "1");
		Config.set("wfc.service.jdbc.driver",
				RdConfig.get("reindeer.service.jdbc.driver"));
		Config.set("wfc.service.connection.string",
				RdConfig.get("reindeer.service.connection.string"));
		Config.set("wfc.service.connection.user",
				RdConfig.get("reindeer.service.connection.user"));
		Config.set("wfc.service.connection.password",
				RdConfig.get("reindeer.service.connection.password"));
	}

}
