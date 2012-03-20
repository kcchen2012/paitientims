package com.yinger.patientims.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ���ݿ�����Ļ����࣬����������DAO��ĸ���
 * �����˽������ݿ����Ӻ͹ر����ݿ����ӵķ���
 */
public class DAOBase {
	private Connection connection;

	// �õ����ݿ�����
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/patientims", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// �ر����ݿ�����
	public void closeConnnection() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection = null;
			}
		}
	}

}
