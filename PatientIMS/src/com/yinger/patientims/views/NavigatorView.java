package com.yinger.patientims.views;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

import com.yinger.patientims.actions.OpenSearchViewAction;
import com.yinger.patientims.navigator.NavigatorEntityElement;
import com.yinger.patientims.navigator.NavigatorEntityFactory;
import com.yinger.patientims.util.PluginUtil;
import com.yinger.patientims.views.viewerProvider.NavigatorTreeViewerContentProvider;
import com.yinger.patientims.views.viewerProvider.NavigatorTreeViewerLabelProvider;

/**
 * ������ͼ
 * 
 */
public class NavigatorView extends ViewPart {

	private TreeViewer treeViewer; // ���鿴��

	@Override
	public void createPartControl(Composite parent) {
		// �������鿴��
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		// ���������ṩ��ͱ�ǩ�ṩ��
		treeViewer.setContentProvider(new NavigatorTreeViewerContentProvider());
		treeViewer.setLabelProvider(new NavigatorTreeViewerLabelProvider());
		// ��������
		treeViewer.setInput(NavigatorEntityFactory.setNavigatorEntity());
		// �Զ���ķ���
		// ������ͼ�Ĺ�����
		setViewToolBar();
		// �Զ���ķ�����ʵ��˫������Ӧ�ı༭���Ĺ���
		hookDoubleClickAction();
	}

	// �������ʵ���Ͼ��Ǹ�treeviewer�����һ������˫���¼��ļ�����
	private void hookDoubleClickAction() {
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = treeViewer.getSelection();
				// �õ�ѡ�е��ע�ⷽ���ǽ��õ���ѡ��ת���� IStructuredSelection���ڵ��� getFirstElement ����
				Object object = ((IStructuredSelection) selection).getFirstElement();
				// �ٽ�����תΪʵ�ʵ����ڵ����
				NavigatorEntityElement element = (NavigatorEntityElement) object;
				// �õ��ö����editorInput
				IEditorInput editorInput = element.getEditorInput();
				// �õ���ǰ����̨��page
				IWorkbenchPage workbenchPage = getViewSite().getPage();
				// ���������� ��Ϣ��ѯ
				if (element.getName().equals("Information Search")) {
					new OpenSearchViewAction(getSite().getWorkbenchWindow()).run();
					return;
				}
				String editorID = null;
				// ����Ҫ���NavigatorEntityFactory���setNavigatorEntity����
				// ��һ���ֶ�ԭ�������޸ģ�����Ϊ��
				if (element.getName().equals("Patient Information")) {// ������Ϣ����
					editorID = PluginUtil.PatientInfoEditor_ID;
				}else if (element.getName().equals("Diagnose Information")) {// �����Ϣ����
					editorID = PluginUtil.DiagnoseInfoEditor_ID;
				}else if (element.getName().equals("Expense Information")) {// ������Ϣ����
					editorID = PluginUtil.ExpenseInfoEditor_ID;
				}else{
					return;
				}
				//IEditorPart:An editor is a visual component within a workbench page.
				IEditorPart editorPart = workbenchPage.findEditor(editorInput);
				if(editorPart != null){//�Ѿ���������ı༭��
					workbenchPage.bringToTop(editorPart);
				}else {//û�д򿪾ʹ���
					try {
						editorPart = workbenchPage.openEditor(editorInput, editorID);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	private void setViewToolBar() {
		// IActionBars:Used by a part to access its menu, toolbar, and
		// status line managers.
		IActionBars bars = getViewSite().getActionBars();
		// ���幤����
		IToolBarManager toolBarManager = bars.getToolBarManager();
		// ���������˵���
		IMenuManager menuManager = bars.getMenuManager();
		// DrillDownAdapter:Implements a simple web style navigation
		// metaphor for a TreeViewer. Home, back,
		// and "drill into" functions are supported for the viewer,
		DrillDownAdapter drillDownAdapter = new DrillDownAdapter(treeViewer);
		// Ϊ��������ӡ�goHome������goBack������goInto������
		drillDownAdapter.addNavigationActions(menuManager);
		// Ϊ�˵�����ӡ�goHome������goBack������goInto������
		drillDownAdapter.addNavigationActions(toolBarManager);
	}

	@Override
	public void setFocus() {

	}

}
