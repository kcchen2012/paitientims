package com.yinger.patientims.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;

import com.yinger.patientims.Activator;
import com.yinger.patientims.util.PluginUtil;

//�Զ����action
public class OpenPatientInfoViewAction extends Action {
	
	private final IWorkbenchWindow window;
	
	public OpenPatientInfoViewAction(IWorkbenchWindow window){
		this.window = window;
		//���ò˵����ı��������ò˵�����ӿ�ݼ��Լ�����
		this.setText("&PatientInfo@Ctrl+P");
		//����������ʾ����Ϣ
		this.setToolTipText("Open PatientInfo View");
		//��ӹ�����ͼ�ΰ�ť
		this.setImageDescriptor(Activator.getImageDescriptor("/icons/User.ico"));
	}
	
	public void run(){
		if (window!=null) {
			try {
				//����ͼ
				window.getActivePage().showView(PluginUtil.PatientInfoView_ID);
			} catch (Exception e) {
				//��������Ի���
				MessageDialog.openError(window.getShell(), "Error", "Error in opening view :"+e.getLocalizedMessage());
			}
		}
	}

}
