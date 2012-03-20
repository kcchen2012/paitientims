package com.yinger.patientims;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.yinger.patientims.dialogs.LoginDialog;
import com.yinger.patientims.model.Doctor;
import com.yinger.patientims.util.DBUtil;

/**
 * This class controls all aspects of the application's execution
 * Application����RCPӦ�ó�����������࣬�൱�ڿ�������������Main��������
 * ���ฺ�𴴽�һ������̨��Workbench����������ApplicationWorkbenchAdvisor������
 * ����ʵ����IApplication�ӿ�,����RCPӦ�ó���֮��������ȱ����ء� ���ϵͳ��Ҫ��¼��ͨ������������в����¼ģ�� ͨ�����������಻��Ҫ�Ķ�
 */
public class Application implements IApplication {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.
	 * IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception {
		Display display = PlatformUI.createDisplay(); // ����Display����
		try {
			// ����֮ǰ�Ƚ��е�¼����
			if (!login()) {
				return IApplication.EXIT_OK; // û�е�½�ɹ��������˳�
			}
			// ��������̨���Ӷ���Ӧ�ó��򴰿�
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART)
				return IApplication.EXIT_RESTART;
			else
				return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed()) // �رմ���֮ǰ���ж�display�Ƿ�������
					workbench.close();
			}
		});
	}

	// ��¼��֤
	public boolean login() {
		boolean flag = false;// ��־
		int tryCount = 0;//��¼�Ĵ���
		while (!flag) {
			LoginDialog loginDialog = new LoginDialog(null);// null�ǿ��Եģ�
			Doctor doctor = new Doctor();
			loginDialog.setDoctor(doctor);// �������Ҫ
			if (tryCount == 3) {//���Ե�¼������û�гɹ����˳�
//				loginDialog.setErrorMessage("Wrong Login Information!");
				return false;
			}
			int res = loginDialog.open();// ע�⣬�����û�ֻ�ǵ���˹رգ�
			if (res == IDialogConstants.ABORT_ID || res == IDialogConstants.CANCEL_ID) {
				return false; // �رջ����˳��˾�ֱ���˳�����
			}
			if (res == IDialogConstants.OK_ID) {// Dialog����֮ǰ�����ú��ڲ���doctor����
				doctor = DBUtil.getLoginInfo(doctor);// ��ʱ�ұߵ�doctor��������username��password
			}
			// doctor�϶���Ϊnull(ÿ�ζ���new Doctor())����ô��Ҫ��doctor������
			if (doctor.getName() != null) {// ����û�������name��password֮�󣬵�����Ϣ��Ч�ͻ���ʾ��������û�ֱ�ӵ���˹رյ�¼
				// MessageDialog.openInformation(null,
				// "Error Information", "Wrong Login Information!");
				// loginDialog.setErrorMessage("Wrong Login Information!");
				flag = true;// ��������²��ǵ�¼�ɹ�������������е�¼
			}else {
				tryCount++;
			}
		}
		return true;
	}

}
