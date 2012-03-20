package com.yinger.patientims.wizards.wizardPages;

import java.util.Date;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.yinger.patientims.dao.DepartmentDAO;
import com.yinger.patientims.dao.SickBedDAO;
import com.yinger.patientims.dao.SickRoomDAO;
import com.yinger.patientims.model.Department;
import com.yinger.patientims.model.Patient;
import com.yinger.patientims.model.SickBed;
import com.yinger.patientims.model.SickRoom;
import com.yinger.patientims.sigleton.PatientFactory;
import com.yinger.patientims.util.DBUtil;
import com.yinger.patientims.util.PluginUtil;

public class PatientRegisterInfoWizardPage extends WizardPage implements ModifyListener {

	private Combo comboDepartment;
	private Combo comboSickRoom;
	private Combo comboSickBed;

	private String sickBedNoString = "";
	private String sickRoomNoString = "";

	private Patient patient;// ��������б���һ��Patient����

	private String type;

	public PatientRegisterInfoWizardPage() {
		super("PatientRegisterInfoWizardPage");// �������Ҫ��
		setTitle("Add Patient Information");
		setMessage("Attention:Please input the following information!", IMessageProvider.INFORMATION);
		// this.setImageDescriptor(Activator.getImageDescriptor("/icons/small/add.gif"));

	}

	// ���protected�Ĺ��췽��һ��Ҫ�У�
	protected PatientRegisterInfoWizardPage(String pageName) {
		super(pageName);
		this.type = pageName;
	}

	@Override
	// ������Ӧ�Ŀؼ�
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));// ��һ��������ָ�е���Ŀ���ڶ���������ָ���Ƿ��ǵȿ��
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);

		// �����Ӧ����Ҫ�������Ϣ�Ŀؼ��������ؼ���Ӽ������������౾����Ϊ��ʵ����ModifyListener��
		new Label(composite, SWT.NONE).setText("Department:");
		comboDepartment = new Combo(composite, SWT.READ_ONLY);
		comboDepartment.setLayoutData(gridData);
		comboDepartment.addModifyListener(this);
		// ��������
		for (Department department : new DepartmentDAO().getDepartmentList()) {
			comboDepartment.add(department.getName());// ����Ҫ���ַ�����ӵ��б���
			comboDepartment.setData(department.getName(), department);// ���Ҫ����Data����String��Object�γ�ӳ��
		}
		// comboDepartment.setSelection(new Point(0,
		// 0));//TODO:��һ�䲻�Ǻ���⣬ò��û��Ч��

		// �տ�ʼֻ�����ÿ��ҵ��б��������б�̬�ĸı���
		new Label(composite, SWT.NONE).setText("SickRoom:");
		comboSickRoom = new Combo(composite, SWT.READ_ONLY);
		comboSickRoom.setLayoutData(gridData);
		comboSickRoom.addModifyListener(this);
		comboSickRoom.setEnabled(false);// �����кܶ�ĵط���Ҫ��������������ع�һ��

		new Label(composite, SWT.NONE).setText("SickBed:");
		comboSickBed = new Combo(composite, SWT.READ_ONLY);
		comboSickBed.setLayoutData(gridData);
		comboSickBed.addModifyListener(this);
		comboSickBed.setEnabled(false);

		setControl(composite);
		setPageComplete(false);

		// if(patient!=null){//��ʱ��ʾ���β����� �޸�patient
		patient = PatientFactory.getInstance();
		if (type.equals(PluginUtil.MODIFY)) {
			comboDepartment.setText(patient.getDepartment().getName());
			sickBedNoString = String.valueOf(patient.getSickbed().getSickBedNo());
			sickRoomNoString = String.valueOf(patient.getSickroom().getSickRoomNo());
			comboSickRoom.setText(sickRoomNoString);
			comboSickBed.setText(sickBedNoString);
			setPageComplete(true);
		}

	}

	@Override
	// ʵ��ModifyListener�ӿڵ�modifyText����
	/* ���������Ҫ���㣬����Ҫ����e.getSource()������һ����setPageComplete�����һ����setEnabled */
	public void modifyText(ModifyEvent e) {
		// �����Ϣ��Ч��������Ӧ�Ĵ�����Ϣ�������ø�ҳ��û����ɣ�next��ť���ǻ�ɫ��
		if (e.getSource().equals(comboDepartment)) {// ���ȵõ������¼����¼�Դ
			if (comboDepartment.getText().trim().length() == 0) {// TODO������¼�ò�Ʋ��ᷢ����
				// ûѡ֮ǰ���ᴥ������ѡ��֮��Ͳ�����ѡ�񵽿հ�
				setMessage("Attention:You should choose a department!", IMessageProvider.WARNING);
				setPageComplete(false);
				comboSickRoom.setEnabled(false);
				comboSickBed.setEnabled(false);
			} else {
				// ����ÿ������ֵ֮ǰҪɾ������ǰ��ֵ�����������������鷳����ÿ�ζ�Ҫ��һ�����ݿ�������ֵ
				refreshComboSickRoom(comboDepartment.getText());
				comboSickRoom.setEnabled(true);
				comboSickBed.setEnabled(false);
			}
			return;
		}
		if (e.getSource().equals(comboSickRoom)) {
			if (comboSickRoom.getText().trim().length() == 0) {
				setMessage("Attention:You should choose a sickroom!", IMessageProvider.WARNING);
				setPageComplete(false);
				comboSickBed.setEnabled(false);
			} else {
				refreshComboSickBed(comboSickRoom.getText());
				comboSickBed.setEnabled(true);
			}
			return;
		}
		if (e.getSource().equals(comboSickBed)) {
			if (comboSickBed.getText().trim().length() == 0) {
				setMessage("Attention:You should choose a sickbed!", IMessageProvider.WARNING);
				setPageComplete(false);
				return;
			}
		}

		// ������޸Ĳ��������һ���ԭ�����Ǹ�����������Ҳ����ͬ�ģ���������ͬ����ʾ��Ϣ
		// ע�������String.valueOf����Ҫ�ģ�����ʡ���ˣ���ȻString��int��equals����Զ�������
		if (type.equals(PluginUtil.MODIFY) && comboSickRoom.getText().equals(sickRoomNoString) && comboSickBed.getText().equals(sickBedNoString)) {
			setMessage("Warning:The SickBed has been taken by the present patient!", IMessageProvider.WARNING);
			// ��ʱ�ǿ��Է��صģ���Ч��
		} else {
			// �ж������λ�Ƿ��Ѿ������ˣ�
			/* TODO����������Ǹ�Ϊ������ס�Ĵ�λ */
			if (DBUtil.validateSickBed(comboDepartment.getText(), Integer.valueOf(comboSickBed.getText()), Integer.valueOf(comboSickRoom.getText()))) {
				setMessage("Error:The SickBed has been taken!", IMessageProvider.WARNING);
				setPageComplete(false);
				return;
			}else{
				setMessage(null);
			}
		}
		// ���ﲻ�ܵ���savePatientInfo��������Ȼ��ı�ԭ�е�patient��ֵ�����������´�ѡ��ԭ�е���Ϣʱ��ʾ���ǲ��ԣ�
		savePatientInfo(); // �������ʾͨ������֤������������Ӧ�����ݵ�һ��patient������
		setPageComplete(true);
	}

	// ˢ�²����б�
	private void refreshComboSickBed(String text) {
		comboSickBed.setItems(new String[0]);// java.lang.IllegalArgumentException:
									// Argument cannot be null
		String where = " and sickroom_id = (select id from t_sickroom where sickroomno=" + text + ") ";
		for (SickBed sickBed : new SickBedDAO().getSickBedList(where)) {
			comboSickBed.add(String.valueOf(sickBed.getSickBedNo()));
			comboSickBed.setData(String.valueOf(sickBed.getSickBedNo()), sickBed);// ��Ϊ����setData�ˣ����Կ��Ժ����ɵĵõ�����id
		}
	}

	// ˢ�²����б�
	private void refreshComboSickRoom(String text) {
		comboSickRoom.setItems(new String[0]);
		String where = " and department_id = (select id from t_department where name='" + text + "') ";
		for (SickRoom sickRoom : new SickRoomDAO().getSickRoomList(where)) {
			comboSickRoom.add(String.valueOf(sickRoom.getSickRoomNo()));
			comboSickRoom.setData(String.valueOf(sickRoom.getSickRoomNo()), sickRoom);
		}
	}

	// public Patient getPatient() {
	// return patient;
	// }
	//
	// public void setPatient(Patient patient) {
	// this.patient = patient;
	// }

	// ������д����Ϣ
	public void savePatientInfo() {
		// �õ�PatientFactory�ĵ���ʵ��
		// Patient patient = PatientFactory.getInstance();
		// patient = PatientFactory.getInstance();//���ǵõ����patient

		// ���丳ֵ
		// ע��Ǽ�ʱ����Ҫ����
		if (type.equals(PluginUtil.ADD)) {// ֻҪ�����ʱ��Ҫ�޸�
			patient.setLogtime(new Date());
		}
		patient.getDepartment().setName(comboDepartment.getText());
		patient.getSickroom().setSickRoomNo(Integer.valueOf(comboSickRoom.getText()));
		patient.getSickbed().setSickBedNo(Integer.valueOf(comboSickBed.getText()));

		// ���ò���id
		SickBed sickBed = (SickBed) comboSickBed.getData(comboSickBed.getText());
		patient.getSickbed().setId(sickBed.getId());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
