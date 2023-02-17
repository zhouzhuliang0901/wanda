package com.wondersgroup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tw.ecosystem.reindeer.config.RdConfig;
 
/**
 * 根据表生成对应实体类文件
 * 
 * 生成其他数据库类型的表需要更换驱动类并略做数据类型转换的修改即可
 * 
 * 注意：数据库数据类型只包括了常用的几种，其他数据类型还需要在完善，请添加‘ftype’该数组变量的值
 *
 */
public class TableToEntityUtil {
	
	private static String[][] ftype;
	private static String sqlDrive;
	private static String strurl;
	private static String username;
	private static String password;
	
    public static void main(String args[]) {
        test_Table("SELM_DICTIONARY_CSJHJ");
    }
    
    static {
		// TODO 需要替换的数据类型，待完善
    	ftype = new String[][] { { "tinyint unsigned", "Integer" },
				{ "tinyint", "Integer" }, { "int unsigned", "Integer" },
				{ "int", "Integer" }, { "smallint unsigned", "Integer" },
				{ "smallint", "Integer" }, { "varchar", "String" },
				{ "char", "String" }, { "longtext", "String" },
				{ "text", "String" }, { "decimal unsigned", "BigDecimal" },
				{ "decimal", "BigDecimal" },{ "date", "String" }, 
				{ "timestamp", "String" },{ "datetime", "Timestamp" }, 
				{ "unsigned", "" }};
    	sqlDrive = RdConfig.get("reindeer.service.jdbc.driver");
    	strurl = RdConfig.get("reindeer.service.connection.string");
        username = RdConfig.get("reindeer.service.connection.user");
        password = RdConfig.get("reindeer.service.connection.password");
    }
 
    /**
     * @param tableName 表名
     */
    public static void test_Table(String tableName) {
        // 生成文件存放目录，不包括文件名
        String path = "E:/file/";
        // dao的包路径
        String dao_package = "reindeer.base.log.dao";
        // entity的包路径
        String entity_package = "reindeer.base.log.bean";
        //生成entity文件
        getTableToEntityFile(tableName, entity_package, path);
        //生成Dao文件
        getResultMapToDao(tableName, dao_package, entity_package, path);


    }
 
    /**
     * 根据表生成实体类文件
     */
	public static void getTableToEntityFile(String tableName,
			String entity_package, String path) {
		
		System.out.println("==============================");
		System.out.println("正在生成。。。");
		String sql = "SELECT TOP 0 * FROM " + tableName;
		// 获取字段名和字段类型，存入一个map元素的集合
		// map的key为字段名，map的value为字段数据类型
		List<Map<String, String>> list = queryFieldList(sql, sqlDrive, strurl,
				username, password);
		// 替换sql数据类型为Java数据类型，并根据存在的类型添加引入
		String importStr = "";
		if (list != null && list.size() > 0) {
			Set<String> fieldTypeSet = new HashSet<String>();
			for (Map<String, String> map : list) {
				for (Object key : map.keySet()) {
					String fieldtype = map.get(key).toString();
					for (int i = 0; i < ftype.length; i++) {
						if(fieldtype == ftype[i][0]){
							fieldtype = ftype[i][1];
							fieldTypeSet.add(fieldtype);
							break;
						}
					}
					map.put(key.toString(), fieldtype);
				}
			}
			// 根据数据类型添加类引入
			importStr = importFile(fieldTypeSet);
		}
		
		// 模板字符串
		String fieldNameStr = "\tpublic static final String #fieldname# = \"#fieldname#\";\n";
		String propertystr = "\t@Column(name = \"#fieldname#\")\n\tprivate #fieldtype# #fieldname#;\n";
		String methodget1 = "\tpublic #fieldtype# get#fieldname1#() {\n";
		String methodget2 = "\t\treturn #fieldname#;\n";
		String methodget3 = "\t}\n";

		String methodset1 = "\tpublic void set#fieldname1#(#fieldtype# #fieldname#) {\n";
		String methodset2 = "\t\tthis.#fieldname# = #fieldname#;\n";
		String methodset3 = "\t}\n";

		String classstrstart = "package "
				+ entity_package
				+ ";\n\nimport java.io.Serializable;"
				+ importStr
				+ "\nimport javax.persistence.Entity;\nimport javax.persistence.Table;\nimport javax.persistence.Column;"
				+ "\n\n@SuppressWarnings(\"serial\")\n@Entity\n@Table(name = \""
				+ tableName
				+ "\")\npublic class #tablename# implements Serializable {\n"
				+ "\npublic static final String " + tableName + " = \""
				+ tableName + "\";\n";
		String classstrend = "}";
		
		String class_pro = "";// 类拼接
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				for (Object key : map.keySet()) {
					String fieldname = key.toString();
					// 字段名
					class_pro = class_pro + "\n";
					class_pro += fieldNameStr.replace("#fieldname#", fieldname);

				}
			}
			for (Map<String, String> map : list) {
				for (Object key : map.keySet()) {
					String fieldtype = map.get(key).toString();
					String fieldname = key.toString();
//					// 替换数据类型
//					for (int i = 0; i < ftype.length; i++) {
//						fieldtype = fieldtype.replace(ftype[i][0], ftype[i][1]);
//					}
					// 属性
					class_pro = class_pro + "\n";
					class_pro += propertystr
							.replaceFirst("#fieldname#", fieldname)
							.replace("#fieldtype#", fieldtype)
							.replace(
									"#fieldname#",
									setStringFirstLetterLower(setStringHump(
											fieldname, "_")));

				}
			}
			for (Map<String, String> map : list) {
				for (Object key : map.keySet()) {
					String fieldtype = map.get(key).toString();
					String fieldname = key.toString();
//					// 替换数据类型
//					for (int i = 0; i < ftype.length; i++) {
//						fieldtype = fieldtype.replace(ftype[i][0], ftype[i][1]);
//					}
					// 首字母大写字符串
					String fieldnameU = setStringHump(fieldname, "_");
					String fieldnameU1 = fieldnameU.substring(0, 1)
							.toUpperCase();
					String fieldnameU2 = fieldnameU.substring(1);
					fieldnameU = fieldnameU1 + fieldnameU2;
					// get方法
					class_pro = class_pro + "\n";
					class_pro = class_pro
							+ methodget1.replace("#fieldtype#", fieldtype)
									.replace("#fieldname1#", fieldnameU)
							+ methodget2.replace(
									"#fieldname#",
									setStringFirstLetterLower(setStringHump(
											fieldname, "_"))) + methodget3;
					// set方法
					class_pro = class_pro + "\n";
					class_pro = class_pro
							+ methodset1
									.replace("#fieldtype#", fieldtype)
									.replace("#fieldname1#", fieldnameU)
									.replace(
											"#fieldname#",
											setStringFirstLetterLower(setStringHump(
													fieldname, "_")))
							+ methodset2.replace(
									"#fieldname#",
									setStringFirstLetterLower(setStringHump(
											fieldname, "_"))) + methodset3;
				}
			}
		}
		// 添加方法头
		String calssName = setStringHump(tableName, "_");
		class_pro = classstrstart.replace("#tablename#", calssName) + class_pro;
		// 添加方法尾
		class_pro = class_pro + classstrend;

		System.out.println("==============================");
		// 生成文件名及保存路径
		path = path + calssName + ".java";
		// 写文件
		write(class_pro, path);

		System.out.println("javabean文件已生成，请查看：" + path);
	}
	
	private static String importFile(Set<String> fieldTypeSet) {
		String importStr = "\n";
		// TODO 字段属性数据的数据类型列举并拼接，不全，待补充
		Iterator<String> it = fieldTypeSet.iterator();
		while (it.hasNext()) {
		  String str = it.next();
		  if("BigDecimal".equals(str)){
			  importStr += "import java.math.BigDecimal;\n";
		  }
		  if("Timestamp".equals(str)){
			  importStr += "import java.sql.Timestamp;\n";
		  }
		}
		return importStr;
	}
 
    /**
     * TODO 未实现
     * 根据表生成resultMap配置，及select,insert,update全配置
     */
    public static void getTableToResultMap(String tableName, String host, String port,
                                           String database, String username, String password,
                                           String dao_package, String entity_package, String path) {
        String strforname = "com.mysql.jdbc.Driver";
        String strurl = "jdbc:mysql://" + host + ":" + port + "/" + database
                        + "?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull";
 
        System.out.println("==============================");
        System.out.println("正在生成。。。");
        String sql = "SELECT * FROM " + tableName + " limit 0";
        List<Map<String, String>> list = queryFieldList(sql, strforname, strurl, username, password);
 
        //如果包路径不是以“.”结尾的要补齐
        dao_package = dao_package == null ? "" : dao_package;
        dao_package = dao_package.length() > 0 && !dao_package.endsWith(".") ? dao_package + "."
            : dao_package;
        entity_package = entity_package == null ? "" : entity_package;
        entity_package = entity_package.length() > 0 && !entity_package.endsWith(".") ? entity_package
                                                                                        + "."
            : entity_package;
        //模板字符串
        String propertystr = "\t\t<result property=\"#fieldname2#\" column=\"#fieldname#\" />\n";
 
        String mapstart = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n<mapper namespace=\""
                          + dao_package + "#tablefilename1#Dao\">\n\n";
        String mapend = "</mapper>";
        String resultstart = "\t<resultMap id=\"#tablefilename2#Result\" type=\"" + entity_package
                             + "#tablefilename1#\">\n";
        String resultend = "\t</resultMap>\n\n";
        String selectstart = "\t<select id=\"get\" resultMap=\"#tablefilename2#Result\">\n";
        String selectend = "\t</select>\n\n";
        String insertstart = "\t<insert id=\"insert\" parameterType=\"" + entity_package
                             + "#tablefilename1#\">\n";
        String insertend = "\t</insert>\n\n";
        String updatestart = "\t<update id=\"update\" parameterType=\"" + entity_package
                             + "#tablefilename1#\">\n";
        String updateend = "\t</update>\n\n";
 
        String resultMap = "";//字段拼接
        String select_field = "";//select拼接
        String insert_field = "";//insert拼接
        String insert_value = "";//insert value拼接
        String update_set = "";//update set拼接
        if (list != null && list.size() > 0) {
            for (Map<String, String> map : list) {
                for (Object key : map.keySet()) {
                    String fieldname = key.toString();
                    //resultMap
                    resultMap = resultMap
                                + propertystr.replace("#fieldname2#",
                                    setStringFirstLetterLower(setStringHump(fieldname, "_")))
                                    .replace("#fieldname#", fieldname);
                    //select
                    select_field = select_field + "`" + fieldname + "`,";
                    insert_field = insert_field + "`" + fieldname + "`,";
                    insert_value = insert_value + "#{"
                                   + setStringFirstLetterLower(setStringHump(fieldname, "_"))
                                   + "},";
                    update_set = update_set + "`" + fieldname + "`=#{"
                                 + setStringFirstLetterLower(setStringHump(fieldname, "_")) + "},";
                }
            }
        }
 
        resultMap = resultstart.replace("#tablename#",
            setStringFirstLetterUpper(setStringHump(tableName, "_")))
                    + resultMap;
        resultMap = resultMap + resultend;
        if (select_field.endsWith(",")) {
            select_field = select_field.substring(0, select_field.length() - ",".length());
        }
        if (insert_field.endsWith(",")) {
            insert_field = insert_field.substring(0, insert_field.length() - ",".length());
        }
        if (insert_value.endsWith(",")) {
            insert_value = insert_value.substring(0, insert_value.length() - ",".length());
        }
        if (update_set.endsWith(",")) {
            update_set = update_set.substring(0, update_set.length() - ",".length());
        }
 
        //拼接sql语句
        String select = "\t\tselect " + select_field + " from " + tableName + "\n";
        String insert = "\t\tinsert into " + tableName + "(" + insert_field + ")values("
                        + insert_value + ")" + "\n";
        String update = "\t\tupdate " + tableName + " set " + update_set + "\n";
 
        //        System.out.println("select:\n" + select);
        //        System.out.println("insert:\n" + insert);
        //        System.out.println("update:\n" + update);
        //生成select语句的Map配置
        String selectMap = selectstart + select + selectend;
        //生成insert语句的Map配置
        String insertMap = insertstart + insert + insertend;
        //生成update语句的Map配置
        String updateMap = updatestart + update + updateend;
        //各个Map配置组合
        resultMap = resultMap + selectMap + insertMap + updateMap;
        //添加Map文件的头和尾
        resultMap = mapstart + resultMap + mapend;
        String tablefilename1 = setStringFirstLetterUpper(setStringHump(tableName, "_"));
        String tablefilename2 = setStringFirstLetterLower(setStringHump(tableName, "_"));
        resultMap = resultMap.replace("#tablefilename1#", tablefilename1);
        resultMap = resultMap.replace("#tablefilename2#", tablefilename2);
        //打印Map文件
        //        System.out.println("==============================");
        //        System.out.println("resultMap:\n" + resultMap);
        //        System.out.println("==============================");
        //生成文件名及保存路径
 
        System.out.println("==============================");
        path = path + setStringFirstLetterUpper(setStringHump(tableName, "_")) + "Mapper.xml";
        //写文件
        write(resultMap, path);
        System.out.println("Map文件已生成，请查看：" + path);
    }
 
	/**
	 * 根据resultMap生成Dao文件
	 */
	public static void getResultMapToDao(String tableName, String dao_package,
			String entity_package, String path) {
		System.out.println("==============================");
		System.out.println("正在生成。。。");

		// 如果包路径是以“.”结尾的要去掉
		dao_package = dao_package == null ? "" : dao_package;
		dao_package = dao_package.length() > 0 && dao_package.endsWith(".") ? dao_package
				.substring(0, dao_package.length() - 1) : dao_package;
		// 如果包路径不是以“.”结尾的要补齐
		entity_package = entity_package == null ? "" : entity_package;
		entity_package = entity_package.length() > 0
				&& !entity_package.endsWith(".") ? entity_package + "."
				: entity_package;

		String sql = "SELECT TOP 0 * FROM " + tableName;
		// 获取字段名和字段类型，存入一个map元素的集合
		// map的key为字段名，map的value为字段数据类型
		List<Map<String, String>> list = queryFieldList(sql, sqlDrive, strurl,
				username, password);
		// 替换sql数据类型为Java数据类型，并根据存在的类型添加引入

		// 模板字符串
		String daostart = "package " + dao_package + ";\n\nimport "
				+ entity_package
				+ setStringHump(tableName, "_")
				+ ";\nimport java.sql.Connection;\nimport java.util.ArrayList;"
				+ "\nimport java.util.List;\nimport java.util.Map;\n"
				+ "\nimport org.springframework.stereotype.Repository\n\n;" 
				+ "import wfc.facility.tool.autocode.PaginationArrayList;\nimport wfc.service.database.Conditions;"
				+ "\nimport wfc.service.database.RecordSet;\nimport wfc.service.database.SQL;\n\n@Repository"
				+ "\npublic class #tablefilename1#Dao {\n\n"
				+ "\tprivate Connection con = null;\n\n" + "\tpublic void "
				+ setStringHump(tableName, "_") + "Dao(){}\n\n" + "\tpublic void "
				+ setStringHump(tableName, "_")
				+ "Dao(Connection con){\n\t\tthis.con = con;\n\t}\n\n";
		String daoend = "}";
		String insertStr = getInsertSql(tableName, list);
		String queryStr = getQuerySql(tableName);
		String updateStr = getUpdatSql(tableName);
		String deleteStr = getDeleteSql(tableName);
		String setPropertiesStr = getsetPropertiesSql(tableName, list);

		String classstr = "";
		classstr += queryStr+"\n";
		classstr += insertStr+"\n";
		classstr += updateStr+"\n";
		classstr += deleteStr+"\n";
		classstr += setPropertiesStr;
		classstr = daostart.replace("#tablefilename1#",
				setStringFirstLetterUpper(setStringHump(tableName, "_")))
				+ classstr;
		classstr = classstr + daoend;

		// 生成文件名及保存路径
		System.out.println("==============================");
		path = path + setStringFirstLetterUpper(setStringHump(tableName, "_"))
				+ "Dao.java";
		// 写文件
		write(classstr, path);
		System.out.println("Dao文件已生成，请查看：" + path);
	}
    
	/**
	 * @param tableName
	 * @param list
	 * @return
	 */
	private static String getsetPropertiesSql(String tableName, List<Map<String, String>> list) {
		String calssName = setStringHump(tableName, "_");
		String setFields = "";
		String rsSet = "";
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				for (Object key : map.keySet()) {
					String fieldname = key.toString();
					String fieldtype = map.get(key).toString();
					String fieldnameU = setStringHump(fieldname, "_");
					if("datetime".equals(fieldtype) ||
							"timestamp".equals(fieldtype)){
						rsSet = "rs.getTimestamp(\""+fieldname+"\")";
					} else if("int".equals(fieldtype) ||
							"smallint".equals(fieldtype) ||
							"tinyint".equals(fieldtype)){
						rsSet = "rs.getBigDecimal(\""+fieldname+"\")";
					} else {
						rsSet = "rs.getOriginalString(\""+fieldname+"\")";
					}
					setFields += "\t\t"+setStringFirstLetterLower(calssName)+".set"+fieldnameU+"("+rsSet+");\n";
				}
			}
		}
		String sql = "\tprivate void setProperties("+calssName+" "+setStringFirstLetterLower(calssName)+", RecordSet rs) {\n"
				+ setFields
				+ "\t}";
		return sql;
	}

	/**
	 * 删除方法拼接
	 * 
	 * @param tableName
	 * @return
	 */
	private static String getDeleteSql(String tableName) {
		String sql = "\tpublic int delete(Conditions conds) {\n\t\tString sql = \"delete from "
				+ tableName
				+ "\";\n\t\tString subsql = conds != null ? conds.toString() : \"\";\n"
				+ "\t\tif (\"\".equals(subsql)) {\n\t\t\tif (con == null) {\n\t\t\t\treturn SQL.execute(sql).TOTAL_RECORD_COUNT;\n\t\t\t} else {\n"
				+ "\t\t\t\treturn SQL.execute(con, sql).TOTAL_RECORD_COUNT;\n\t\t\t}\n\t\t} else {\n\t\t\tsql += \" where \" + subsql;\n"
				+ "\t\t\tif (con == null) {\n\t\t\t\treturn SQL.execute(sql, conds.getObjectArray()).TOTAL_RECORD_COUNT;\n"
				+ "\t\t\t} else {\n\t\t\t\treturn SQL.execute(con, sql, conds.getObjectArray()).TOTAL_RECORD_COUNT;\n"
				+ "\t\t\t}\n\t\t}\n\t}\n";
		return sql;
	}

	/**
	 * 更新方法拼接
	 * 
	 * @param tableName
	 * @return
	 */
	private static String getUpdatSql(String tableName) {
		String sql = "\tpublic int update(Map<String, Object> map, Conditions conds){\n\t\tString sql = \"update "
				+ tableName
				+ " set \";\n"
				+ "\t\tList<Object> list = new ArrayList<Object>();\n\t\tint i = 0;\n\t\tfor (String field : map.keySet()) {\n"
				+ "\t\t\tif (i++ > 0) {\n\t\t\t\tsql += \", \";\n\t\t\t}\n\t\t\tsql += field + \" = ?\";\n\t\t\tlist.add(map.get(field));\n\t\t}\n"
				+ "\t\tString subsql = conds != null ? conds.toString() : \"\";\n\t\tif (\"\".equals(subsql)) {\n\t\t\tif (con == null) {\n"
				+ "\t\t\t\treturn SQL.execute(sql).TOTAL_RECORD_COUNT;\n\t\t\t} else {\n\t\t\t\treturn SQL.execute(con, sql).TOTAL_RECORD_COUNT;\n"
				+ "\t\t\t}\n\t\t} else {\n\t\t\tsql += \" where \" + subsql;\n\t\t\tlist.addAll(conds.getObjectList());\n\t\t\tif (con == null) {\n"
				+ "\t\t\t\treturn SQL.execute(sql, list.toArray()).TOTAL_RECORD_COUNT;\n\t\t\t} else {\n\t\t\t\treturn SQL.execute(con, sql, list.toArray()).TOTAL_RECORD_COUNT;\n"
				+ "\t\t\t}\n\t\t}\n\t}\n";
		return sql;
	}

	/**
	 * 插入方法拼接
	 * 
	 * @param tableName
	 * @return
	 */
	private static String getInsertSql(String tableName,
			List<Map<String, String>> list) {
		String calssName = setStringHump(tableName, "_");
		String fields = "";
		String values = "";
		String setValues = "";
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				for (Object key : map.keySet()) {
					String fieldname = key.toString();
					// 首字母大写字符串
					String fieldnameU = setStringHump(fieldname, "_");
					fields += fieldname + ", ";
					values += "?,";
					setValues += "\t\t\t"
							+ setStringFirstLetterLower(calssName) + ".get"
							+ fieldnameU + "(),\n";
				}
			}
		}
		fields = fields.substring(0, fields.length() - 2);
		values = values.substring(0, values.length() - 1);
		String sql = "\tpublic int insert("
				+ calssName
				+ " "
				+ setStringFirstLetterLower(calssName)
				+ "){\n"
				+ "\t\tRecordSet rs;"
				+ "\t\tString sql = \"insert into "
				+ tableName
				+ "("
				+ fields
				+ ") values ("
				+ values
				+ ")\";\n"
				+ "\t\tObject[] obj = {\n"
				+ setValues
				+ "\t\t};\n\t\tif (con == null) {\n\t\t\trs = SQL.execute(sql, obj);\n"
				+ "\t\t} else {\n\t\t\trs = SQL.execute(con, sql, obj);\n\t\t}\n\t\treturn rs.TOTAL_RECORD_COUNT;\n\t}\n\n";
		return sql;
	}

	/**
	 * 查询方法拼接
	 * 
	 * @param tableName
	 * @return
	 */
	private static String getQuerySql(String tableName) {
		String calssName = setStringHump(tableName, "_");
		String sql = "\tpublic PaginationArrayList<"
				+ calssName
				+ "> query(Conditions conds, String suffix, int pageSize, int currentPage) {\n"
				+ "\t\tRecordSet rs;\n\t\tif (con == null) {\n"
				+ "\t\t\trs = SQL.query(\""
				+ tableName
				+ "\", \"*\", conds, suffix, pageSize, currentPage);\n\t\t} else {\n"
				+ "\t\t\trs = SQL.query(con, \""
				+ tableName
				+ "\", \"*\", conds, suffix, pageSize, currentPage);\n\t\t}\n"
				+ "\t\tPaginationArrayList<"
				+ calssName
				+ "> pal = new PaginationArrayList<"
				+ calssName
				+ ">(rs.TOTAL_RECORD_COUNT, rs.COMMON_PAGE_SIZE, rs.CURRENT_PAGE);\n"
				+ "\t\twhile (rs.next()) {\n\t\t\t" + calssName + " "
				+ setStringFirstLetterLower(calssName) + " = new " + calssName
				+ "();\n\t\t\tsetProperties("
				+ setStringFirstLetterLower(calssName)
				+ ", rs);\n\t\t\tpal.add("
				+ setStringFirstLetterLower(calssName)
				+ ");\n\t\t}\n\t\treturn pal;\n\t}\n\n";
		return sql;
	}
    
    public static String getUpdateSql(String tableName){
    	String calssName = setStringHump(tableName, "_");
    	String sql = "\tpublic int update("+calssName+" "+setStringFirstLetterLower(calssName)+"){\n";
    	return sql;
    }
 
    /**
     * 查询表的元数据信息
     * @return
     */
    public static List<Map<String, String>> queryFieldList(String querySql, String strforname,
                                                           String strurl, String username,
                                                           String password) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection(strforname, strurl, username, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            	Map<String, String> map = new HashMap<String, String>();
                map.put(rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
 
    /**
     * 设置字符串驼峰格式
     * @param str
     * @param regex_split 驼峰分隔符
     * @return
     */
    private static String setStringHump(String str, String regex_split) {
        if (str == null) {
            return "";
        }
        if (regex_split == null || regex_split.equals("")) {
            regex_split = "_";
        }
        String temp_str = "";
        String sp[] = str.split(regex_split);
        if (sp != null && sp.length > 0) {
            for (int i = 0; i < sp.length; i++) {
                temp_str += setStringFirstLetterUpper(sp[i]);
            }
        }
 
        return temp_str;
    }
 
    /**
     * 字符串首字母大写
     * @param str
     * @return
     */
    private static String setStringFirstLetterUpper(String str) {
        if (str == null) {
            return "";
        }
        String str1 = "";
        String str2 = "";
        if (str.length() > 1) {
            str1 = str.substring(0, 1).toUpperCase();
            str2 = str.substring(1).toLowerCase();
            str = str1 + str2;
        } else {
            str = str.toUpperCase();
        }
        return str;
    }
 
    /**
     * 字符串首字母小写
     * @param str
     * @return
     */
    private static String setStringFirstLetterLower(String str) {
        if (str == null) {
            return "";
        }
        String str1 = "";
        String str2 = "";
        if (str.length() > 1) {
            str1 = str.substring(0, 1).toLowerCase();
            str2 = str.substring(1);
            str = str1 + str2;
        } else {
            str = str.toLowerCase();
        }
        return str;
    }
 
    /**
     * 生成文件
     * @return
     */
    private static void write(String str, String path) {
        FileWriter fw = null;
        PrintWriter out = null;
        try {
            File file = new File(path);
            fw = new FileWriter(file, false);
            out = new PrintWriter(fw);
            out.print(str);
            out.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    public static Connection getConnection(String strforname, String strurl, String username,
                                           String password) {
        String className = strforname;
        Connection conn = null;
        try {
            Class.forName(className);
            String url = strurl;
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
 
}