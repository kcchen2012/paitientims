package com.yinger.patientims.editors.editorActionDelegate;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import com.yinger.patientims.sigleton.PatientFactory;
import com.yinger.patientims.wizards.AddPatientInfoWizard;

/**
 * �༭�������Ĵ�����
 * 
 * �����������plugin.xml�ļ���
 * ������action�ڲ˵�������������λ�ã��Լ�Ҫ���õĶ��������ࣨҲ���Ǳ��ࣩ
 * ������һ��action�����������൱��һ��action�������Թ�����һ���༭��
 * ������༭��editor����ʱ����Ӧ�Ķ���action�ͻ���ֳ����������ִ�в�������������ʵ�ֵ�
 * 
 * TODO:����һ��IEditorPart���󣬵��Ǿ�������ã�
 *
 */
public class PatientEditorActionDelegate implements IEditorActionDelegate {
	
	private IEditorPart editorPart;

	@Override
	public void run(IAction action) {
		//�����Զ������
		AddPatientInfoWizard wizard = new AddPatientInfoWizard();
		//ע�⣺ÿ�δ���ҳ��ʱ��Ҫ��PatientFactory��instance�������ã���Ȼ���������
		PatientFactory.setInstance(null);	
		//��ʾ�򵼶Ի���
		WizardDialog dialog = new WizardDialog(Display.getDefault().getShells()[0], wizard);
		//���öԻ����С
		dialog.setPageSize(-1, 150);//ע�⣺�߶�Ҫ�㹻�Ĵ�
		//��
		dialog.open();	
//		editorPart.getEditorInput().
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.editorPart = targetEditor;
	}

}
