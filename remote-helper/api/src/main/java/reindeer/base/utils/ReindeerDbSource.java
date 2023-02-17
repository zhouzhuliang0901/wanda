package reindeer.base.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import tw.ecosystem.reindeer.config.RdConfigItem;
import tw.ecosystem.reindeer.web.ReindeerWebContext;
import tw.service.db.DBFactory;
import tw.service.db.enumerate.SupportedDBTypes;
import tw.service.db.source.DBSource;
import tw.service.exception.TWException;
import tw.tool.helper.LogHelper;
import tw.tool.util.CloseUtils;

public class ReindeerDbSource implements DBSource {

	private SupportedDBTypes dbType;

	private Connection con;

	@Override
	public void close() {
		CloseUtils.close(con);
	}

	@Override
	public Connection getConnection() {
		if (con == null) {
			if (ReindeerWebContext.isContextReady()) {
				DataSource dataSource = (DataSource) ReindeerWebContext
						.getBean("dataSource");
				try {
					con = dataSource.getConnection();
					return con;
				} catch (SQLException e) {
				}
			}
		}
		if (con == null) {
			String strDBDriver = RdConfigItem.getJdbcDriver(null);
			String strConString = RdConfigItem.getConnectionString(null);
			String strDBUser = RdConfigItem.getConnectionUser(null);
			String strDBPassword = RdConfigItem.getConnectionPassword(null);
			DBSource source = DBFactory.getDbSourceByJdbcSource(strDBDriver,
					strConString, strDBUser, strDBPassword);
			con = source.getConnection();
		}
		return con;
	}

	@Override
	public SupportedDBTypes getDbType() {
		if (dbType == null) {
			try {
				getConnection();
				dbType = SupportedDBTypes.newInstance(this.con.getMetaData()
						.getDatabaseProductName());
			} catch (SQLException e) {
				LogHelper.error(e);
				new TWException(e);
			}
		}
		return dbType;
	}

	@Override
	public String getSchema() {
		return "";
	}

}
