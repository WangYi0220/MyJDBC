package com.wy.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.wy.jdbc.handler.BeanHandler;
import com.wy.jdbc.handler.BeanListHandler;
import com.wy.jdbc.handler.utils.StrUtils;

public class JdbcWy {
	
	private DataSource dataSource;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public JdbcWy() {
		super();
	}

	public JdbcWy(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public JdbcWy(Connection connection) {
		super();
		this.connection = connection;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public <T> Object query(String sql, BeanHandler<T> beanHandler) throws Exception {
		Object object = getData(sql, beanHandler);
		return object;
	}

	public <T> Object query(String sql, Object[] paras, BeanHandler<T> beanHandler) throws Exception {
		String newSql = StrUtils.sqlHandler(paras, sql);
		Object object = getData(newSql.toString(), beanHandler);
		return object;
	}
	
	public <T> List<Object> query(String sql, BeanListHandler<T> beanListHandler) throws Exception {
		List<Object> objectList = getDateList(sql, beanListHandler);
		return objectList;
	}
	
	public <T> List<Object> query(String sql, Object[] paras, BeanListHandler<T> beanListHandler) throws Exception {
		String newSql = StrUtils.sqlHandler(paras, sql);
		List<Object> objectList = getDateList(newSql.toString(), beanListHandler);
		return objectList;
	}
	
	public void update(String sql, Object[] paras) throws Exception {
		String newSql = StrUtils.sqlHandler(paras, sql);
		if (this.connection == null) {
			this.connection = this.dataSource.getConnection();
		}
		System.out.println(this.connection);
		this.preparedStatement = this.connection.prepareStatement(newSql);
		this.preparedStatement.executeUpdate();
		
	}
	
	private <T> Object getData(String sql, BeanHandler<T> beanHandler) throws Exception{
		Object object = null;
		if (this.connection == null) {
			this.connection = this.dataSource.getConnection();
		}
		System.out.println(this.connection);
		this.preparedStatement = this.connection.prepareStatement(sql);
		this.resultSet = this.preparedStatement.executeQuery();
		int col = this.resultSet.getMetaData().getColumnCount();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		int throwNub = 0;
		while (resultSet.next()) {
			throwNub++;
			for(int i = 1; i <= col; i++) {
				String key = this.resultSet.getMetaData().getColumnName(i);
				Object value = this.resultSet.getObject(i);
				System.out.print("key:" + key);
				System.out.println(", value:" + value);
				dataMap.put(key, value);
			}
		}
		System.out.println("throwNub:"+throwNub);
		if(throwNub > 1){
			throw new Exception("总记录数大于一");
		}
		object = beanHandler.mapToBean(dataMap);
		//close();
		return object;
	}
	
	private <T> List<Object> getDateList(String sql, BeanListHandler<T> beanListHandler) throws Exception {
		if (this.connection == null) {
			this.connection = this.dataSource.getConnection();
		}
		System.out.println(this.connection);
		this.preparedStatement = this.connection.prepareStatement(sql);
		this.resultSet = this.preparedStatement.executeQuery();
		int col = this.resultSet.getMetaData().getColumnCount();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		while (resultSet.next()) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for(int i = 1; i <= col; i++) {
				String key = this.resultSet.getMetaData().getColumnName(i);
				Object value = this.resultSet.getObject(i);
				System.out.print("key:" + key);
				System.out.println(", value:" + value);
				dataMap.put(key, value);
			}
			listMap.add(dataMap);
		}
		List<Object> objectList = beanListHandler.mapListToBean(listMap);
		return objectList;
	}
	
	private void close(){
		try {
			if(this.resultSet != null)this.resultSet.close();
			if(this.preparedStatement != null)this.preparedStatement.close();
			if(this.connection != null)this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
