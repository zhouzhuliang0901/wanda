package com.wondersgroup.data.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;




@Component
public class JDBCUtils {

	private static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url="jdbc:sqlserver://localhost:1433;databaseName=ac-product-manager";
	private static String user="sa";
	private static String password="wonders,1";
	
//	private static String url="jdbc:sqlserver://172.16.125.58:1433;DatabaseName=self";
//	private static String user="sa";
//	private static String password="Asdf3.14";

	/**
	 * jdbc.properties配置文件读取，只用执行一次，使用静态代码块
	 */
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return 连接对象
	 */
	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * 释放资源
	 * 
	 * @param statement
	 * @param connection
	 */

	public static void close(Statement statement, Connection connection) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}

	}

	/**
	 * 释放资源
	 * 
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void close(Statement statement, Connection connection,
			ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 释放资源
	 * 
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void close(PreparedStatement statement, Connection connection,
			ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
