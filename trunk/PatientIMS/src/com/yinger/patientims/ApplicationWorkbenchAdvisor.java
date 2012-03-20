package com.yinger.patientims;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
/**
 * Public base class for configuring the workbench. 
 * ����̳���WorkbenchAdvisor�࣬��������Application����������ApplicationWorkbenchAdvisor��
 * WorkbenchAdvisor����Ҫ�������ù���̨�������߹���̨�����ʾ����ʾʲô��Ϣ
 * WorkbenchAdvisor����Ҫ��ʼ��Ĭ�ϵ�͸��ͼ��ʹ��WorkbenchWindowAdvisor��
 * 
 * ��Ҫ������
 * 1.initialize(IWorkbenchConfigurer configurer)���ڹ���̨����֮ǰ���г�ʼ��
 * 2.openWindows()������������ʱ�򿪹���̨����
 * 3.postShutdown()�����������д��ڹر�֮�󣬹رչ���̨֮ǰ���ã��������浱ǰ��Ӧ�ó����״̬�������ʼ����������������
 * 4.preShutdown()������̨��Ҫ�ر�֮ǰ
 * 5.postStartup()�������ڹ���̨���ڹر�֮������ѭ���¼�����֮ǰ����
 * 6.preStartup()�������ڳ�ʼ������֮�󣬵�һ�����ڴ�֮ǰ����
 * 7.getDefaultPageInput()������Ĭ�ϵ�����
 * 8.getInitialWindowPerspectiveId()������Ϊ�µĹ���̨���ڷ���͸��ͼID
 * 9.getMainPreferencePageId()��������ѡ��ID
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "com.yinger.patientims.perspective"; //$NON-NLS-1$

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
}
