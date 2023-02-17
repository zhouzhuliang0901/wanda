/**
 * Project: Coral
 * Source file: StreamUtils.java
 * Create At 2013-9-16 下午02:43:22
 * Create By 龚云
 */
package coral.base.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

/**
 * @author 龚云
 * 
 */
public class CloseUtils {

	public static void close(Closeable clo) {
		close(clo, false, null, null);
	}

	public static void close(Closeable clo, Log log) {
		close(clo, false, null, log);
	}

	public static void close(Closeable clo, Logger logger) {
		close(clo, false, logger, null);
	}

	public static void close(Connection con) {
		close(con, false, null, null);
	}

	public static void close(Connection con, Log log) {
		close(con, false, null, log);
	}

	public static void close(Connection con, Logger logger) {
		close(con, false, logger, null);
	}

	public static void close(InputStream is) {
		close(is, false, null, null);
	}

	public static void close(InputStream is, Log log) {
		close(is, false, null, log);
	}

	public static void close(InputStream is, Logger logger) {
		close(is, false, logger, null);
	}

	public static void close(OutputStream os) {
		close(os, false, null, null);
	}

	public static void close(OutputStream os, Log log) {
		close(os, false, null, log);
	}

	public static void close(OutputStream os, Logger logger) {
		close(os, false, logger, null);
	}

	public static void close(Reader rd) {
		close(rd, false, null, null);
	}

	public static void close(Reader rd, Log log) {
		close(rd, false, null, log);
	}

	public static void close(Reader rd, Logger logger) {
		close(rd, false, logger, null);
	}

	public static void close(ResultSet rs) {
		close(rs, false, null, null);
	}

	public static void close(ResultSet rs, Log log) {
		close(rs, false, null, log);
	}

	public static void close(ResultSet rs, Logger logger) {
		close(rs, false, logger, null);
	}

	public static void close(Statement stmt) {
		close(stmt, false, null, null);
	}

	public static void close(Statement stmt, Log log) {
		close(stmt, false, null, log);
	}

	public static void close(Statement stmt, Logger logger) {
		close(stmt, false, logger, null);
	}

	public static void close(Writer wt) {
		close(wt, false, null, null);
	}

	public static void close(Writer wt, Log log) {
		close(wt, false, null, log);
	}

	public static void close(Writer wt, Logger logger) {
		close(wt, false, logger, null);
	}

	public static void closeWithRuntime(Closeable clo) {
		close(clo, true, null, null);
	}

	public static void closeWithRuntime(Connection con) {
		close(con, true, null, null);
	}

	public static void closeWithRuntime(InputStream is) {
		close(is, true, null, null);
	}

	public static void closeWithRuntime(OutputStream os) {
		close(os, true, null, null);
	}

	public static void closeWithRuntime(Reader rd) {
		close(rd, true, null, null);
	}

	public static void closeWithRuntime(ResultSet rs) {
		close(rs, true, null, null);
	}

	public static void closeWithRuntime(Statement stmt) {
		close(stmt, true, null, null);
	}

	public static void closeWithRuntime(Writer wt) {
		close(wt, true, null, null);
	}

	protected static void close(Closeable clo, boolean withRunTimeException,
			Logger logger, Log log) {
		try {
			if (clo != null)
				clo.close();
		} catch (IOException e) {
			if (withRunTimeException)
				throw new RuntimeException(e);
			logException(e, logger, log);
		}
	}

	protected static void close(Connection con, boolean withRunTimeException,
			Logger logger, Log log) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			if (withRunTimeException)
				throw new RuntimeException(e);
			logException(e, logger, log);
		}
	}

	protected static void close(InputStream is, boolean withRunTimeException,
			Logger logger, Log log) {
		try {
			if (is != null)
				is.close();
		} catch (IOException e) {
			if (withRunTimeException)
				throw new RuntimeException(e);
			logException(e, logger, log);
		}
	}

	protected static void close(OutputStream os, boolean withRunTimeException,
			Logger logger, Log log) {
		try {
			if (os != null)
				os.close();
		} catch (IOException e) {
			if (withRunTimeException)
				throw new RuntimeException(e);
			logException(e, logger, log);
		}
	}

	protected static void close(Reader rd, boolean withRunTimeException,
			Logger logger, Log log) {
		try {
			if (rd != null)
				rd.close();
		} catch (IOException e) {
			if (withRunTimeException)
				throw new RuntimeException(e);
			logException(e, logger, log);
		}
	}

	protected static void close(ResultSet rs, boolean withRunTimeException,
			Logger logger, Log log) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			if (withRunTimeException)
				throw new RuntimeException(e);
			logException(e, logger, log);
		}
	}

	protected static void close(Statement stmt, boolean withRunTimeException,
			Logger logger, Log log) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			if (withRunTimeException)
				throw new RuntimeException(e);
			logException(e, logger, log);
		}
	}

	protected static void close(Writer wt, boolean withRunTimeException,
			Logger logger, Log log) {
		try {
			if (wt != null)
				wt.close();
		} catch (IOException e) {
			if (withRunTimeException)
				throw new RuntimeException(e);
			logException(e, logger, log);
		}
	}

	protected static void logException(Exception e, Logger logger, Log log) {
		if (logger != null)
			logger.error(e);
		if (log != null)
			log.error(e);
	}

}
