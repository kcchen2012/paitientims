package com.yinger.patientims;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.yinger.patientims.actions.OpenExpenseInfoViewAction;
import com.yinger.patientims.actions.OpenNavigatorViewAction;
import com.yinger.patientims.actions.OpenPatientInfoViewAction;
import com.yinger.patientims.actions.OpenSearchViewAction;

/**
 * Public base class for configuring the action bars of a workbench window.
 * ����̳���ActionBarAdvisor�࣬���𴴽�RCPӦ�ó�����Ĳ˵�����������״̬���ȵ� �Լ������Ӧ�Ĳ���
 * 
 * ��Ҫ�ķ����� 1.makeActions(IWorkbenchWindow window)����䷽�� 2.fillMenuBar(IMenuManager
 * menuBar)��������˵��� 3.fillCoolBar(ICoolBarManager coolBarManager)����乤����
 * 4.fillStatusLine(IStatusLineManager statusLineManager)�����״̬��
 * 5.isApplicationMenu()�������Ƿ��Ǹ�������ĸ����˵���ID��Ӧ�ó���˵�
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction newWindowAction;
	private IWorkbenchAction exitAction;
	private IWorkbenchAction perspectiveAction;
	private IWorkbenchAction aboutAction;
	private IContributionItem showViewAction;
	private OpenNavigatorViewAction openNavigatorViewAction;
	private OpenPatientInfoViewAction openPatientInfoViewAction;
	private OpenExpenseInfoViewAction openExpenseInfoViewAction;
	private OpenSearchViewAction openSearchViewAction;
	private IWorkbenchAction preferenceAction;

	// ������췽���� ApplicationWorkbenchWindowAdvisor���е���
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	// �����������Ҫ���������ô����еĲ���
	protected void makeActions(IWorkbenchWindow window) {
		// ActionFactory������̨������������ Access to standard actions provided by
		// the workbench.
		// ActionFactory.OPEN_NEW_WINDOW �����廯�˵����ڴ������½����ڡ������Ĳ�����������
		// ���塰�½����ڡ�����
		newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
		newWindowAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_DEF_VIEW));
		// ע�����
		register(newWindowAction);

		// ���塰�˳�������
		exitAction = ActionFactory.QUIT.create(window);
		exitAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		register(exitAction);

		// ���塰��͸��ͼ������
		perspectiveAction = ActionFactory.OPEN_PERSPECTIVE_DIALOG.create(window);
		register(perspectiveAction);

		// ���塰���ڡ�����
		aboutAction = ActionFactory.ABOUT.create(window);
//		aboutAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		aboutAction.setImageDescriptor(Activator.getImageDescriptor("/icons/small/about.gif"));
		register(aboutAction);

		// ���塰��ʾ��ͼ�б�����
		showViewAction = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
//		((IAction) showViewAction).setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT));
		// register(showViewAction); //ע�⣬������ͼ����ע�ᣬregister�����ᱨ��

		// ����򿪡���ѡ�����
		preferenceAction = ActionFactory.PREFERENCES.create(window);
//		preferenceAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ETOOL_HOME_NAV));
		preferenceAction.setImageDescriptor(Activator.getImageDescriptor("/icons/Home.ico"));
		register(preferenceAction);

		// �Զ���Ĳ����࣬�ֱ���������Ӧ����ͼ
		openNavigatorViewAction = new OpenNavigatorViewAction(window);
		openExpenseInfoViewAction = new OpenExpenseInfoViewAction(window);
		openPatientInfoViewAction = new OpenPatientInfoViewAction(window);
		openSearchViewAction = new OpenSearchViewAction(window);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		// IWorkbenchActionConstants:Action ids for standard actions, groups
		// in the workbench menu bar, and global actions.
		// ����File�˵�
		MenuManager fileManager = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
		// ��File�˵�������˳��˵���
		fileManager.add(exitAction);
		// ���File�˵����˵���
		menuBar.add(fileManager);

		// Window�˵�
		MenuManager windowManager = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW);
		windowManager.add(newWindowAction);
		windowManager.add(perspectiveAction);
		windowManager.add(preferenceAction);
		// Window�˵����Ӳ˵�Show View�˵������������˵�
		MenuManager showViewManager = new MenuManager("&Show View", "showView");
//		showViewManager.add(openExpenseInfoViewAction);
//		showViewManager.add(openNavigatorViewAction);
//		showViewManager.add(openPatientInfoViewAction);
//		showViewManager.add(openSearchViewAction);
		// ����һ����other�������ڴ�δ��ʾ����ͼ
		//TODO:����ֵ��� show view���Լ���ʾһ���ֵ�view����Щview�ǵ�ǰ�Ĵ����Ѿ���ʾ������view
		showViewManager.add(showViewAction);
		windowManager.add(showViewManager);
		menuBar.add(windowManager);

		// Help�˵�
		MenuManager helpManager = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
		helpManager.add(aboutAction);
		menuBar.add(helpManager);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// Creates a tool bar manager with the given SWT [button style]
		IToolBarManager toolbar1 = new ToolBarManager(coolBar.getStyle());
		// �����toolbar�������һ��������ť
		toolbar1.add(openNavigatorViewAction);
		// �����toolbar��ӵ�coolbar����
		coolBar.add(toolbar1);

		IToolBarManager toolbar2 = new ToolBarManager(coolBar.getStyle());
		toolbar2.add(openSearchViewAction);
		coolBar.add(toolbar2);

		IToolBarManager toolbar3 = new ToolBarManager(coolBar.getStyle());
		toolbar3.add(perspectiveAction);
		coolBar.add(toolbar3);

	}

}
