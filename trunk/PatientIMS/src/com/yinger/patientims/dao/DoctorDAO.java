package com.yinger.patientims.dao;

import java.sql.ResultSet;

import com.yinger.patientims.model.Doctor;

public class DoctorDAO extends DAOBase {

	// �����ݿ���ȡ����һ��ҽ����Ϣ���õ�һ��Doctor������
	// ��������������쳣��ֱ�ӽ��쳣�׳�ȥ
	public static Doctor setOneDoctor(ResultSet resultSet) throws Exception {
		Doctor doctor = new Doctor();
		doctor.setId(resultSet.getLong("id"));
		doctor.setAge(resultSet.getInt("age"));
		doctor.setSex(resultSet.getString("sex"));
		doctor.setTitle(resultSet.getString("title"));
		doctor.setName(resultSet.getString("name"));
		doctor.setPhone(resultSet.getString("phone"));
		doctor.setLogtime(resultSet.getDate("logtime"));
		doctor.setPassword(resultSet.getString("password"));
		doctor.setUsername(resultSet.getString("username"));
		return doctor;
	}

}
