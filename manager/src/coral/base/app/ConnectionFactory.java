package coral.base.app;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import wfc.service.config.ConfigValue;
import wfc.service.database.AbstractConnectionFactory;
import wfc.service.database.DB;
import wfc.service.log.Log;

public class ConnectionFactory extends AbstractConnectionFactory {

	@Override
	public Connection getConnection() {
		if (AppContext.isContextReady()) {
			DataSource dataSource = (DataSource) AppContext
					.getBean("dataSource");
			try {
				return dataSource.getConnection();
			} catch (SQLException e) {
				Log.error(e);
				return null;
			}
		} else {
			switch (Integer.parseInt(ConfigValue.getConnectionMode())) {
			case 1: {
				String strDBDriver = ConfigValue.getJdbcDriver();
				String strConString = ConfigValue.getConnectionString();
				String strDBUser = ConfigValue.getConnectionUser();
				String strDBPassword = ConfigValue.getConnectionPassword();
				return DB.getConnectionWithMode1(strDBDriver, strConString,
						strDBUser, strDBPassword);
			}
			case 2: {
				String strDBDriver = ConfigValue.getJdbcDriver();
				String strConString = ConfigValue.getConnectionString();
				return DB.getConnectionWithMode2(strDBDriver, strConString);
			}
			case 3: {
				String strInitialContextFactory = ConfigValue
						.getServerInitialContextFactory();
				String strProviderURL = ConfigValue.getServerProviderUrl();
				String strDBPoolJNDI = ConfigValue.getDatabasePoolJndi();
				return DB.getConnectionWithMode3(strInitialContextFactory,
						strProviderURL, strDBPoolJNDI);
			}
			case 4: {
				String strDBPoolJNDI = ConfigValue.getDatabasePoolJndi();
				return DB.getConnectionWithMode4(strDBPoolJNDI);
			}
			case 5: {
				String strInitialContextFactory = ConfigValue
						.getServerInitialContextFactory();
				String strProviderURL = ConfigValue.getServerProviderUrl();
				String strDBPoolJNDI = ConfigValue.getDatabasePoolJndi();
				String strDBUser = ConfigValue.getConnectionUser();
				String strDBPassword = ConfigValue.getConnectionPassword();
				return DB
						.getConnectionWithMode5(strInitialContextFactory,
								strProviderURL, strDBPoolJNDI, strDBUser,
								strDBPassword);
			}
			case 6: {
				String strDBPoolJNDI = ConfigValue.getDatabasePoolJndi();
				String strDBUser = ConfigValue.getConnectionUser();
				String strDBPassword = ConfigValue.getConnectionPassword();
				return DB.getConnectionWithMode6(strDBPoolJNDI, strDBUser,
						strDBPassword);
			}
			default:
				return null;
			}
		}
	}

}
