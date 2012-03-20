package com.yinger.patientims;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.internal.util.PrefUtil;
/**
 * Public base class for configuring a workbench window. 
 * ����̳���WorkbenchWindowAdvisor�࣬��Ҫ�ǶԹ���̨���ڽ��п���
 * ����״̬������������͸��ͼ����������ͼ�������ڳߴ磬����ȵ�
 * ���໹��һ����Ҫ�����þ��Ǵ���ActionBarAdvisor��
 * �����ķ�������������̨������������������Ҫ����
 *
 * ��Ҫ������
 * 1.createEmptyWindowContents��Composite composite�������ص�����û��ҳ����ʾʱҪ��ʾ�Ŀؼ�
 * 2.createWindowContents��Shell shell����������������
 * 3.getWindowConfigurer���������ع���̨��������
 * 4.openIntro�������򿪳�ʼ����
 * 5.postWindowClose�������ڴ��ڹر�֮�����
 * 6.postWindowCreate�������ڴ��ڴ����Ժ󣬴�֮ǰ���� ���� ���ڻָ�������״̬����ִ��postWindowRestor����֮�����
 * 7.postWindowRestor�������ڴ��ڱ��ָ�������״̬֮�󣬴�֮ǰ����
 * 8.perWindowOpen�������ڴ��ڱ���֮ǰ����
 * 9.preWindowShellClose�������ڴ��ڵ�Shell���ر�֮ǰ����
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	//������췽����ApplicationWorkbenchAdvisor�е��õ�
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    //����������ڴ��ڴ�֮ǰ���õģ��������ô��ڵ�������ʾ���
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        //���ô���ı���
        configurer.setTitle("Hospital Management System"); //$NON-NLS-1$
        //���ô���Ĵ�С
        configurer.setInitialSize(new Point(800, 600));
        //����͸��ͼ��
        configurer.setShowMenuBar(true);
        //���ù�����
        configurer.setShowCoolBar(true);
        //����״̬��
        configurer.setShowStatusLine(true);
        //���ÿ�����ͼ��
        configurer.setShowFastViewBars(true);       
        
        //����Ӧ�ó�������
        IPreferenceStore preferenceStore = PrefUtil.getAPIPreferenceStore();
        //����ѡ�����ʽ�����Ǿ��εı߿򣬶��ǻ��ε�
        preferenceStore.setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
        //����͸��ͼ��ť��λ�ã�Ĭ�������Ͻǣ���Ϊ���������Ͻ�
        preferenceStore.setValue(IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR, IWorkbenchPreferenceConstants.TOP_RIGHT);
        
    }
}
