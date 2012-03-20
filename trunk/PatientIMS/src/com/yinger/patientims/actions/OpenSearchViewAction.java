package com.yinger.patientims.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;

import com.yinger.patientims.Activator;
import com.yinger.patientims.util.PluginUtil;

//�Զ����action
public class OpenSearchViewAction extends Action {
	
	private final IWorkbenchWindow window;
	
	public OpenSearchViewAction(IWorkbenchWindow window){
		this.window = window;
		//���ò˵����ı��������ò˵�����ӿ�ݼ��Լ�����
		this.setText("&Search@Ctrl+F");
		//����������ʾ����Ϣ
		this.setToolTipText("Open Search View");
		//��ӹ�����ͼ�ΰ�ť
		this.setImageDescriptor(Activator.getImageDescriptor("/icons/Search.ico"));
	}
	
	public void run(){
		if (window!=null) {
			try {
				//����ͼ
				window.getActivePage().showView(PluginUtil.SearchView_ID);
			} catch (Exception e) {
				//��������Ի���
				MessageDialog.openError(window.getShell(), "Error", "Error in opening view :"+e.getLocalizedMessage());
			}
		}
	}

}
