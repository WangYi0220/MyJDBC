package com.wy.jdbc.test;

import java.util.List;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wy.jdbc.JdbcWy;
import com.wy.jdbc.handler.BeanHandler;
import com.wy.jdbc.handler.BeanListHandler;

public class Test {
	public static void main(String[] args) {
		try {
			DataSource dataSource = new ComboPooledDataSource();
			JdbcWy jdbcWy = new JdbcWy(dataSource);
			System.out.println("********单条记录********");
			/*User user = (User) jdbcWy.query("select * from user", new BeanHandler<User>(User.class));
			System.out.println(user);*/
			System.out.println("********条件********");
			String sql = "select * from user where id = ?";
			Object[] paras = {1}; 
			User user1 = (User) jdbcWy.query(sql, paras, new BeanHandler<User>(User.class));
			System.out.println(user1);
			System.out.println("********多条记录********");
			List<Object> objectList = jdbcWy.query("select * from user", new BeanListHandler<User>(User.class));
			for (Object object : objectList) {
				System.out.println(object);
			}
			System.out.println("********条件********");
			objectList = jdbcWy.query(sql, paras, new BeanListHandler<User>(User.class));
			for (Object object : objectList) {
				System.out.println(object);
			}
			System.out.println("********删除********");
			//jdbcWy.update("delete from user where id = ?", paras);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
