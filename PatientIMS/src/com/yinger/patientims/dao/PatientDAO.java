package com.yinger.patientims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yinger.patientims.model.Patient;
import com.yinger.patientims.util.DBUtil;

public class PatientDAO extends DAOBase {

	// �õ�����סԺ�Ĳ��˵���Ϣ
	// SELECT
	// p.id,p.name,p.age,p.sex,p.address,p.logtime,p.phone,d.name,b.sickbedno,r.sickroomno
	// FROM t_patient p,t_department d,t_sickbed b,t_sickroom r
	// WHERE p.sickbed_id=b.id and b.sickroom_id=r.id and r.department_id=d.id
	public List<Patient> getPatientInfoList() {
		Patient patient;
		List<Patient> list = new ArrayList<Patient>();
		// ע�⣺����������name��Ҫʹ�õ�����
		String sql = "SELECT p.id,p.name as pname,p.age,p.sex,p.address,p.logtime,p.phone,d.name as dname,b.sickbedno,r.sickroomno "
				+ "FROM t_patient p,t_department d,t_sickbed b,t_sickroom r "
				+ "WHERE p.sickbed_id=b.id and b.sickroom_id=r.id and r.department_id=d.id";
		Connection connection = null;
		// ��finally��ʹ������������Ҫ��ʼ��
		// ���У���һ��Ҫ�������棬������try���棨finally���ſ��Ա����ʵ�
		// ���У�һ��Ҫ��finally���ر����ݿ������Ǻ���Ҫ�ģ�
		try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			ResultSet resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				patient = setOnePatient(resultSet);
				list.add(patient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// �����ݿ��е�һ��patient��¼������һ��Patient������
	// �������������쳣���׳����ϲ㴦��
	private Patient setOnePatient(ResultSet resultSet) throws Exception {
		Patient patient = new Patient();
		if (resultSet.getLong("id") != 0) {
			patient.setId(resultSet.getLong("id"));
		}
		if (resultSet.getString("pname") != null) {
			patient.setName(resultSet.getString("pname"));
		}
		if (resultSet.getString("sex") != null) {
			patient.setSex(resultSet.getString("sex"));
		}
		if (resultSet.getString("phone") != null) {
			patient.setPhone(resultSet.getString("phone"));
		}
		if (resultSet.getString("address") != null) {
			patient.setAddress(resultSet.getString("address"));
		}
		if (resultSet.getInt("age") != 0) {
			patient.setAge(resultSet.getInt("age"));
		}
		if (resultSet.getDate("logtime") != null) {
			patient.setLogtime(resultSet.getDate("logtime"));
		}
		if (resultSet.getString("dname") != null) {
			// ע����һ�����������׷�����ָ���쳣����һ��Patient������Department�����Բ�û�б���ʼ����
			// ����Ҫ������new���������󣬶���SickRoom�ȵ�ͬ��
			patient.getDepartment().setName(resultSet.getString("dname"));
		}
		if (resultSet.getInt("sickbedno") != 0) {
			patient.getSickbed().setSickBedNo(resultSet.getInt("sickbedno"));
		}
		if (resultSet.getInt("sickroomno") != 0) {
			patient.getSickroom().setSickRoomNo(resultSet.getInt("sickroomno"));
		}
		return patient;
	}

	// ɾ��������Ϣ
	public boolean deletePatient(Patient patient) {
		Long id = patient.getId();
		Connection connection = null;
		String sql = "delete from t_patient where id=" + id + "";
		try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			int res = pStatement.executeUpdate();
			if (res > 0) {
				return true; // ע�⣺������Ȼ��return�ˣ��������������������֮ǰ����Ҫִ��finally
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// ��Ӳ���סԺ��Ϣ
	public boolean insertPatient(Patient patient) {
		Connection connection = null;
		// INSERT INTO
		// t_patient(name,sex,age,phone,logtime,address,sickbed_id) VALUES
		// ('patient"+i+"','Ů',37,'1533535354','2011-10-10','TianJin',"+id+")
		String sql = "INSERT INTO t_patient(name,sex,age,phone,logtime,address,sickbed_id) VALUES ('" + patient.getName() + "','" + patient.getSex() + "',"
				+ patient.getAge() + ",'" + patient.getPhone() + "','" + DBUtil.simpleDateFormat.format(patient.getLogtime()) + "','" + patient.getAddress() + "',"
				+ patient.getSickbed().getId() + ")";
		//patient.getLogtime().toLocaleString()  �Ѿ����Ƽ�ʹ����
		//ע�⣺ʱ�����е��ر�أ�һ��Ҫ patient.getLogtime().toLocaleString()��������ʹ�� DateFormater����formatһ�£�
		try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql);
			int res = pStatement.executeUpdate();
			if (res > 0) {
				return true; // ע�⣺������Ȼ��return�ˣ��������������������֮ǰ����Ҫִ��finally
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// �޸Ĳ���סԺ��Ϣ
	public boolean updatePatient(Patient patient) {
		Connection connection = null;
		//UPDATE t_patient as p set p.name='yyh' , p.address='ghalsdhgl' , p.age=24 , p.phone='523659365', p.sex='female'
		// where id=6
		StringBuffer sql = new StringBuffer("UPDATE t_patient as p ");
		sql.append(" set p.name='"+patient.getName()+"' ");
		sql.append(", p.address='"+patient.getAddress()+"' ");
		sql.append(" , p.age="+patient.getAge()+" ");
		sql.append(" , p.phone='"+patient.getPhone()+"' ");
		sql.append(", p.sex='"+patient.getSex()+"' ");
		sql.append(" ,p.sickbed_id= "+patient.getSickbed().getId()+" ");
		sql.append(" where id="+patient.getId());
		try {
			connection = getConnection();
			PreparedStatement pStatement = connection.prepareStatement(sql.toString());
			int res = pStatement.executeUpdate();
			if (res > 0) {
				return true; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
