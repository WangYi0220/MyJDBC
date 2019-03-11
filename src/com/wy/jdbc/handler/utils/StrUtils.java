package com.wy.jdbc.handler.utils;

public class StrUtils {
	public static String sqlHandler(Object[] paras, String sql) {
		StringBuffer newSql = new StringBuffer(sql);
		for (Object object : paras) {
			int start = newSql.indexOf("?");//获取问号下标
			newSql.replace(start, start + 1, "'" + object + "'");
		}
		return newSql.toString();
	}
}
