package com.yinger.patientims.editors.viewerSorter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import com.yinger.patientims.model.Patient;
/**
 * ���鿴����������
 * ���ڸ������е�������������
 * 
 * �Ҿ�����һ���ط����ã��Ǿ�����������֣����̶���ĳһ�о�����ĳ��
 * ĳһ�е������㷨Ҳ�ǣ����о������compare����̫���ˣ����ڸ���
 * ->�������ⲿ�Զ���һ���࣬�������������������򷽷���ͳһ
 * ���� IDASC=1  IDDSC=-1 
 */
public class PatientInfoSorter extends ViewerSorter {

	private int column;

	public void doSort(int column) {
		this.column = column;
	}

	@Override // ��������ķ���
	public int compare(Viewer viewer, Object e1, Object e2) {
		Patient patient1 = (Patient) e1;
		Patient patient2 = (Patient) e2;
		switch (column) {
		case 1:
			return patient2.getId().compareTo(patient1.getId());
		case -1:
			return patient1.getId().compareTo(patient2.getId());
		case 2:
			return patient2.getName().compareTo(patient1.getName());
		case -2:
			return patient1.getName().compareTo(patient2.getName());
		case 3:
			return patient2.getSex().compareTo(patient1.getSex());
		case -3:
			return patient1.getSex().compareTo(patient2.getSex());
		case 4:
			return (patient2.getAge() + "").compareTo(patient1.getAge() + "");
		case -4:
			return (patient1.getAge() + "").compareTo(patient2.getAge() + "");
		case 5:
			return patient2.getPhone().compareTo(patient1.getPhone());
		case -5:
			return patient1.getPhone().compareTo(patient2.getPhone());
		case 6:
			return patient2.getDepartment().getName().compareTo(patient1.getDepartment().getName());
		case -6:
			return patient1.getDepartment().getName().compareTo(patient2.getDepartment().getName());
		case 7:
			return (patient2.getSickroom().getSickRoomNo() + "").compareTo(patient1.getSickroom().getSickRoomNo() + "");
		case -7:
			return (patient1.getSickroom().getSickRoomNo() + "").compareTo(patient2.getSickroom().getSickRoomNo() + "");
		case 8:
			return (patient2.getSickbed().getSickBedNo() + "").compareTo(patient1.getSickbed().getSickBedNo() + "");
		case -8:
			return (patient1.getSickbed().getSickBedNo() + "").compareTo(patient2.getSickbed().getSickBedNo() + "");
		case 9:
			return patient2.getAddress().compareTo(patient1.getAddress());
		case -9:
			return patient1.getAddress().compareTo(patient2.getAddress());
		case 10:
			return patient2.getLogtime().compareTo(patient1.getLogtime());
		case -10:
			return patient1.getLogtime().compareTo(patient2.getLogtime());
		default:
			return 0;
		}
	}

}
