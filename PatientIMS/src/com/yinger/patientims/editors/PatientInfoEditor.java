package com.yinger.patientims.editors;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import com.yinger.patientims.Activator;
import com.yinger.patientims.dao.PatientDAO;
import com.yinger.patientims.editors.viewerSorter.PatientInfoSorter;
import com.yinger.patientims.model.Patient;
import com.yinger.patientims.sigleton.PatientFactory;
import com.yinger.patientims.util.PluginUtil;
import com.yinger.patientims.views.viewerProvider.PatientInfoTableViewerContentProvider;
import com.yinger.patientims.views.viewerProvider.PatientInfoTableViewerLabelProvider;
import com.yinger.patientims.wizards.AddPatientInfoWizard;

public class PatientInfoEditor extends EditorPart {

	private PatientDAO patientDAO;

	// һ��鿴�����ĸ��ؼ����ࣩ����ʾ��������Ҫ��һ����Ӧ�Ĳ鿴�����ֶ�
	private TableViewer tableViewer;// ���鿴��
	private boolean sort;// ��ʶ����ķ�ʽ

	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	// �������Ҫ�޸ģ���д��
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	// ��������������ñ༭����Ҫ��ʾ������
	public void createPartControl(Composite parent) {
		// ���ȳ�ʼ��һ��PatientDAO
		patientDAO = new PatientDAO();

		// ���ȴ���һ��ViewForm�����������ڿؼ��Ĳ���
		ViewForm viewForm = new ViewForm(parent, SWT.NONE);
		// ����
		viewForm.setLayout(new FillLayout());
		// ����TableViewer
		createTableViewer(viewForm);
		tableViewer.setContentProvider(new PatientInfoTableViewerContentProvider());
		tableViewer.setLabelProvider(new PatientInfoTableViewerLabelProvider());
		tableViewer.setInput(patientDAO.getPatientInfoList());
		tableViewer.setSorter(new PatientInfoSorter());

		// ��ӱ༭���Ĺ������������� �޸ģ�ɾ����ˢ�� ������ť
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT);
		ToolBarManager toolBarManager = new ToolBarManager(toolBar);
		toolBarManager.add(new AddPatientAction());
		toolBarManager.add(new DeletePatientAction());
		toolBarManager.add(new ModifyPatientAction());
		toolBarManager.add(new RefreshAction());
		toolBarManager.update(true);
		// This brings the underlying widgets up to date with any changes.
		
		// ����viewform
		viewForm.setTopLeft(toolBar);
		viewForm.setContent(tableViewer.getControl());
	}

	private void createTableViewer(Composite composite) {
//		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer = new TableViewer(composite, SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		// ������ʾ����
		table.setHeaderVisible(true);
		// ������ʾ�����
		table.setLinesVisible(true);
		// ���ñ��Ĳ��� TableColumnLayout
		//TODO���ص�ע������Ĳ������ã������У����ᵼ�ºܶ����⣬����������������н���Ŵ���С����
		//�ӳ٣��ܶ����⣡
//		table.setLayout(new TableColumnLayout());
		// ����һ��
		TableColumn tc1 = new TableColumn(table, SWT.LEFT);
		// �����б���
		tc1.setText("ID");
		// �����п�
		tc1.setWidth(60);
		// ������������б����ʱ������
		// ע�⣺widgetSelected������ĩβһ��Ҫrefresh���������Ա�֤�����ֶ�ˢ�£�
		// ���У�������һ��SelectionAdapter�Ϳ����ˣ�
		tc1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -1 : 1);
				tableViewer.refresh();
			}
		});

		TableColumn tc2 = new TableColumn(table, SWT.LEFT);
		tc2.setText("Name");
		tc2.setWidth(100);
		tc2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -2 : 2);
				tableViewer.refresh();
			}
		});

		TableColumn tc3 = new TableColumn(table, SWT.LEFT);
		tc3.setText("Sex");
		tc3.setWidth(60);
		tc3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -3 : 3);
				tableViewer.refresh();
			}
		});

		TableColumn tc4 = new TableColumn(table, SWT.LEFT);
		tc4.setText("Age");
		tc4.setWidth(60);
		tc4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -4 : 4);
				tableViewer.refresh();
			}
		});

		TableColumn tc5 = new TableColumn(table, SWT.LEFT);
		tc5.setText("Phone");
		tc5.setWidth(100);
		tc5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -5 : 5);
				tableViewer.refresh();
			}
		});

		TableColumn tc6 = new TableColumn(table, SWT.LEFT);
		tc6.setText("Department");
		tc6.setWidth(100);
		tc6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -6 : 6);
				tableViewer.refresh();
			}
		});

		TableColumn tc7 = new TableColumn(table, SWT.LEFT);
		tc7.setText("SickRoom");
		tc7.setWidth(80);
		tc7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -7 : 7);
				tableViewer.refresh();
			}
		});

		TableColumn tc8 = new TableColumn(table, SWT.LEFT);
		tc8.setText("SickBed");
		tc8.setWidth(80);
		tc8.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -8 : 8);
				tableViewer.refresh();
			}
		});

		TableColumn tc9 = new TableColumn(table, SWT.LEFT);
		tc9.setText("Address");
		tc9.setWidth(100);
		tc9.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -9 : 9);
				tableViewer.refresh();
			}
		});

		TableColumn tc10 = new TableColumn(table, SWT.LEFT);
		tc10.setText("Login Time");
		tc10.setWidth(100);
		tc10.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sort = !sort;
				((PatientInfoSorter) tableViewer.getSorter()).doSort(sort ? -10 : 10);
				tableViewer.refresh();
			}
		});

	}

	@Override
	public void setFocus() {
	}

	// ע�⣺����������������Ϊ�ڲ��࣡
	// ԭ����������Ҫ���ʵ�ǰ��� TableViewer �������Է����������ʵ�������ܷ��㣡
	class ModifyPatientAction extends Action {
		public ModifyPatientAction() {
			// ������ʾ�ı�
			this.setToolTipText("Modify Patient Information");
			// ����ͼ��
			this.setImageDescriptor(Activator.getImageDescriptor("/icons/Applications.ico"));
		}
		public void run() {
			IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
			Patient patient = (Patient) selection.getFirstElement();
			if (patient == null) {
				return;
			}
			// ���� �޸Ĳ���סԺ��Ϣ��dialog
			PatientFactory.setInstance(patient);
			
			//�����Զ������
			AddPatientInfoWizard wizard = new AddPatientInfoWizard();
			wizard.setType(PluginUtil.MODIFY);
			//�������е�patient�ǵ�ǰѡ�е�patient
//			wizard.setPatient(patient);//�������ʹ����Щget��set
//			PatientFactory.getInstance() = patient;//�����Ĵ��벻�У�������߲��Ǳ���
			//�������PatientFactory�����һ��setInstance����
//			
			//��ʾ�򵼶Ի���
			WizardDialog dialog = new WizardDialog(Display.getDefault().getShells()[0], wizard);
			//���öԻ����С
			dialog.setPageSize(-1, 150);//ע�⣺�߶�Ҫ�㹻�Ĵ󣬷�����Щ�����ʾ������
			//��
			dialog.open();	
			//�޸����֮��Ҫˢ���б�
			tableViewer.setInput(patientDAO.getPatientInfoList());
			tableViewer.refresh();
		}
	}
	
	class AddPatientAction extends Action {
		public AddPatientAction() {
			// ������ʾ�ı�
			this.setToolTipText("Add Patient Information");
			// ����ͼ��
			this.setImageDescriptor(Activator.getImageDescriptor("/icons/small/add.gif"));
		}

		public void run() {
			// ���� �����µĲ���סԺ��Ϣ��dialog
			//�����Զ������
			AddPatientInfoWizard wizard = new AddPatientInfoWizard();
			wizard.setType(PluginUtil.ADD);
			//ע�⣺ÿ�δ���ҳ��ʱ��Ҫ��PatientFactory��instance�������ã���Ȼ���������
//			PatientFactory.setInstance(null);	//��������Ϊnull����Ȼ����Ĳ������жϲ������ͣ�������ֵ��
//			PatientFactory.setInstance(new Patient());
			//��ʾ�򵼶Ի���
			WizardDialog dialog = new WizardDialog(Display.getDefault().getShells()[0], wizard);
			//���öԻ����С
			dialog.setPageSize(-1, 150);//ע�⣺�߶�Ҫ�㹻�Ĵ�
			//��
			dialog.open();
			//������֮��Ҫˢ���б�
//			tableViewer.refresh();//����������У�
			//���ǵ�����ȡ�����ݲ�ˢ����ʵ
			tableViewer.setInput(patientDAO.getPatientInfoList());
			tableViewer.refresh();
		}
	}

	class DeletePatientAction extends Action {

		public DeletePatientAction() {
			// ������ʾ�ı�
			this.setToolTipText("Delete Patient Information");
			// ����ͼ��
			this.setImageDescriptor(Activator.getImageDescriptor("/icons/small/delete.gif"));
		}

		public void run() {
			IStructuredSelection selection = (IStructuredSelection) tableViewer.getSelection();
			Patient patient = (Patient) selection.getFirstElement();
			if (patient == null) {
				return;
			}
			if (MessageDialog.openConfirm(null, "Confirm to delete", "Are you sure to delete?")) {
				if (patientDAO.deletePatient(patient)) {
					@SuppressWarnings("unchecked")
					List<Patient> list = (List<Patient>) tableViewer.getInput();
					list.remove(patient);// ����ɾ������ˢ��ʱ�����µ�����
					tableViewer.remove(patient);// ʵʱ����
				} else {//ɾ������ʱ��ʾ
					MessageDialog.openInformation(null, "Error Information", "Fail to delete the Patient!");
				}
			}
		}

	}

	class RefreshAction extends Action {
		public RefreshAction() {
			// ������ʾ�ı�
			this.setToolTipText("Refresh Patient Information");
			// ����ͼ��
			this.setImageDescriptor(Activator.getImageDescriptor("/icons/Refresh.ico"));
		}
		public void run() {
			tableViewer.setInput(patientDAO.getPatientInfoList());
			tableViewer.refresh();
		}
	}

}
