package com.yinger.patientims;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import com.yinger.patientims.util.PluginUtil;

/**
 * A perspective factory generates the initial page layout and visible action set for a page. 
 * ͸��ͼ��Ҫʵ�� IPerspectiveFactory �ӿ�
 * createInitialLayout�������Զ�͸��ͼ���в���
 */
public class Perspective implements IPerspectiveFactory {

	//�������������ʼ��͸��ͼ�Ĳ��֣��������������Ŀ�е���ͼ����ע��
	public void createInitialLayout(IPageLayout layout) {
		//�õ��༭����id
		String editorArea = layout.getEditorArea();//Returns the special identifier for the editor area in this page layout
		
		//��ӵ�����ͼ
		//String viewId, int relationship, float ratio, String refId
		layout.addView(PluginUtil.NavigatorView_ID, IPageLayout.LEFT, 0.3f, editorArea);
		
		//���������ͼ����ӵ�������ͼ���·�
		//An IFolderLayout is used to define the initial views within a folder. The folder itself is contained within an IPageLayout. 
		//IFolderLayout���������е�View���һ��folder���ļ��й��ܣ������folder����Ͱ�����IPageLayout
		IFolderLayout leftbottom = layout.createFolder("left", IPageLayout.BOTTOM, 0.4f, PluginUtil.NavigatorView_ID);
		leftbottom.addView(PluginUtil.SearchView_ID);
		
		//��Ӳ�����Ϣ�ͷ�����Ϣ����ͼ����ʵ��������ͼ���ӵ�Ч��
		IFolderLayout rightbottom = layout.createFolder("rightbottom", IPageLayout.BOTTOM, 0.7f, editorArea);
		rightbottom.addView(PluginUtil.PatientInfoView_ID);
		rightbottom.addView(PluginUtil.ExpenseInfoView_ID);
		
	}
}
