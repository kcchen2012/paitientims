package com.yinger.patientims.navigator;

import java.util.ArrayList;
import java.util.List;

import com.yinger.patientims.editors.editorInput.DiagnoseInfoEditorInput;
import com.yinger.patientims.editors.editorInput.ExpenseInfoEditorInput;
import com.yinger.patientims.editors.editorInput.PatientInfoEditorInput;
/**
 * �����������Ҫ������Ǹ��������ڵ�����ͼ��Ҫ��ʾ��TreeViewer�еĽڵ�Ԫ��
 * ʵ�幤���ཫʵ�����еĸ����ڵ����ݹ�����������ͳ�������
 */
public class NavigatorEntityFactory {

	public static Object setNavigatorEntity() {
		/**
		 * �����漰��һ��ͳһ�������⣬��ЩString�������ĵط������ˣ��������޸ģ�
		 * ������������
		 */
		
		// Level one һ���ڵ�
		NavigatorEntityElement root1 = new NavigatorEntityElement(0, "Hospital Management");
		NavigatorEntityElement root2 = new NavigatorEntityElement(0, "Expense Management");
		NavigatorEntityElement root3 = new NavigatorEntityElement(0, "Information Search");

		// level two �����ڵ�
		NavigatorEntityElement element1 = new NavigatorEntityElement(1, "Patient Information");
		NavigatorEntityElement element2 = new NavigatorEntityElement(1, "Diagnose Information");
		NavigatorEntityElement element3 = new NavigatorEntityElement(1, "Expense Information");

		// ���¼��ڵ���ӵ��ϼ�
		root1.addChildren(element1);
		root1.addChildren(element2);
		root2.addChildren(element3);
		
		// ���ñ༭��
		element1.setEditorInput(new PatientInfoEditorInput());
		element2.setEditorInput(new DiagnoseInfoEditorInput());
		element3.setEditorInput(new ExpenseInfoEditorInput());
		
		// Ҫ���ص�list
		List list = new ArrayList();
		list.add(root1);
		list.add(root2);
		list.add(root3);
		return list;
	}
}
