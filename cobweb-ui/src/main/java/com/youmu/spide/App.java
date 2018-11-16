package com.youmu.spide;

import com.youmu.spider.proxy.impl.xicidaili.XiciProxyModel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection connection = DriverManager.getConnection("jdbc:h2:./test");
		Statement statement = connection.createStatement();
		System.out.println(statement.execute(getDDL()));
		connection.commit();
		System.out.println(getDDL());
	}

	public static String getDDL() {
		Field[] fields = XiciProxyModel.class.getDeclaredFields();
		StringBuilder stringBuilder = new StringBuilder("create table if not exists proxy (\n");
		stringBuilder.append("id int primary key not null,\n");
		for (Field field : fields) {
			if (field.isSynthetic() || Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
				continue;
			}
			stringBuilder.append(field.getName()).append(" ").append(getJdbcType(field.getType())).append(",\n");
		}
		stringBuilder.append(");");
		return stringBuilder.toString();
	}

	public static String getJdbcType(Class clazz) {
		if (clazz.equals(String.class)) {
			return "varchar(255)";
		}
		if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return "int";
		}
		if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return "bigint";
		}
		if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
			return "tinyint";
		}
		if (clazz.isEnum()) {
			return "int";
		}
		if (clazz.equals(Date.class)) {
			return "datetime";
		}
		throw new RuntimeException("unknown type :" + clazz);
	}

}
