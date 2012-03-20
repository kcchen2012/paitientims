package com.yinger.patientims.wizards.wizardPages;

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
import org.eclipse.swt.widgets.Text;

import com.yinger.patientims.model.Patient;
import com.yinger.patientims.sigleton.PatientFactory;
import com.yinger.patientims.util.PluginUtil;

public class PatientBasicInfoWizardPage extends WizardPage implements ModifyListener {

	private Text textName;
	private Text textAge;
	private Text textPhone;
	private Text textAddress;
	private Combo comboSex;

	private Patient patient;// ��������б���һ��Patient����
	//�����˵���ģʽ֮������Ǻ��б�Ҫ�ģ���Ϊ������еĺܶ෽��ʹ�õ���patient
	
	private String type;

	// ����ʹ��Ĭ�ϵĹ�������������pubic����protected
	// ����WizardPage��û��Ĭ�ϵĹ�����
	// protected PatientBasicInfoWizardPage(){
	//
	// }
	// ������޲εĹ��췽���е��ø�����вεĹ��췽���������Ͳ��ᱨ����
	//�������õ�ֻ��ҳ���ڵ���ʾ����
	public PatientBasicInfoWizardPage() {
		// ����Implicit super constructor WizardPage() is undefined. Must
		// explicitly invoke another constructor
		super("PatientBasicInfoWizardPage");// �������Ҫ��
		setTitle("Add Patient Information");
		setMessage("Attention:Please input the following information!", IMessageProvider.INFORMATION);
//		this.setImageDescriptor(Activator.getImageDescriptor("/icons/small/add.gif"));
		//���ͼ�겻����ʾ�ڴ�����������Ͻǣ������ڲ����ݵ����Ͻǣ�
		
	}

	// ���protected�Ĺ��췽��һ��Ҫ�У�
	// �������������Ǹ����췽����������Ͳ���Ҫ��
	// �������X�ÿ����У���������type
	protected PatientBasicInfoWizardPage(String pageName) {
		super(pageName);
		this.type = pageName;
	}

	@Override
	// ������Ӧ�Ŀؼ�
	// һ����˵����Ӧ�Ŀؼ����󶼶���Ϊ������ڲ����ԱȽϺ�
	// ���У�һ��label�ؼ��Ͳ�����Ϊ�ڲ������ˣ����ǹ��ʻ��Ļ�������Ҫ��
	// ���⣬һ�������������дĳЩ��Ϣ��page����ʹ��GridLayout
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));// ��һ��������ָ�е���Ŀ���ڶ���������ָ���Ƿ��ǵȿ��
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		// �ȼ���
		// GridData gridData = new GridData();
		// gridData.horizontalAlignment = GridData.FILL;

		// �����Ӧ����Ҫ�������Ϣ�Ŀؼ��������ؼ���Ӽ������������౾����Ϊ��ʵ����ModifyListener��
		new Label(composite, SWT.NONE).setText("Name:");
		textName = new Text(composite, SWT.BORDER);
		textName.setLayoutData(gridData);
		textName.addModifyListener(this);
		
		new Label(composite, SWT.NONE).setText("Age:");
		textAge = new Text(composite, SWT.BORDER);
		textAge.setLayoutData(gridData);
		textAge.addModifyListener(this);

		new Label(composite, SWT.NONE).setText("Sex:");
		comboSex = new Combo(composite, SWT.READ_ONLY);
		comboSex.setItems(new String[]{"Male","Female"});
		comboSex.setText("Male");
		comboSex.setLayoutData(gridData);
		comboSex.addModifyListener(this);

		new Label(composite, SWT.NONE).setText("Phone:");
		textPhone = new Text(composite, SWT.BORDER);
		textPhone.setLayoutData(gridData);
		textPhone.addModifyListener(this);

		new Label(composite, SWT.NONE).setText("Address:");
		textAddress = new Text(composite, SWT.BORDER);
		textAddress.setLayoutData(gridData);
		textAddress.addModifyListener(this);
		setControl(composite);		
		setPageComplete(false);
		
//		if(patient.getId()!=null){//��ʱ��ʾ���β����� �޸�patient
//		if(null!=patient){
		patient = PatientFactory.getInstance();
		if(type.equals(PluginUtil.MODIFY)){
			textName.setText(patient.getName());
			textAge.setText(String.valueOf(patient.getAge()));
			textAddress.setText(patient.getAddress());
			textPhone.setText(patient.getPhone());
			comboSex.setText(patient.getSex());//����ò��û����Ч��
			
			setPageComplete(true);//������޸ĵĻ�������page��Ӧ������Ч�ģ�
		}
		
	}

	@Override
	// ʵ��ModifyListener�ӿڵ�modifyText����
	/**
	 * TODO���뷨�����������֤���е��ı���������ݶ������ǿյģ�������ôд�Ļ����ظ�����Ĵ���ܶ�
	 * �ع��Ļ������Խ���ĳɣ�����page��control�е������ı����ٵ���һ�������ķ�����֤��Ч��
	 * ���������ӵĶ�ĳ����Ҫ���⴦����ı���Ĵ����ֲ��������ˣ�
	 */
	public void modifyText(ModifyEvent e) {
		// �����Ϣ��Ч��������Ӧ�Ĵ�����Ϣ�������ø�ҳ��û����ɣ�next��ť���ǻ�ɫ��
		if (textName.getText().trim().length() == 0) {
			setMessage("Attention:Name must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		if (textAge.getText().trim().length() == 0) {
			setMessage("Attention:Age must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		if (comboSex.getText().trim().length() == 0) {
			setMessage("Attention:Sex must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		if (textPhone.getText().trim().length() == 0) {
			setMessage("Attention:Phone must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		if (textAddress.getText().trim().length() == 0) {
			setMessage("Attention:Address must'n be blank!", IMessageProvider.WARNING);
			setPageComplete(false);
			return;
		}
		setMessage(null);
		savePatientInfo(); // �������ʾͨ������֤������������Ӧ�����ݵ�һ��patient������
		setPageComplete(true);//��һ�����Ҫ�ģ���Ȼ��ȫ��д��ȷҲ�ǲ���next�ģ�
	}

//	public Patient getPatient() {
//		return patient;
//	}
//	
//	public void setPatient(Patient patient){
//		this.patient = patient;
//	}

	// ������д����Ϣ
	public void savePatientInfo() {
//		patient = new Patient();//ע�⣺���ﲻ����new Patient����Ϊ����ҳ���patient���������
		//�õ�PatientFactory�ĵ���ʵ��
//		Patient patient = PatientFactory.getInstance();
		
		/**
		 * �������ӣ�patient��new Patient()��һ���µ�ʵ����idΪnull
		 * ������޸ģ�patient�����е�patient����id��Ϊnull
		 * 
		 * ���Ƕ�������жϲ���������
		 * ����һ���ж�PatientFactory�е�ʵ���Ƿ�Ϊ�գ����ǲ�һ�������ж�patient�Ƿ���null��
		 * ��������ʹ��һ��String���͵Ĳ���type
		 * ����ѡ�÷���һ
		 * ���ǣ�����һ�γ��ԣ�������֮���Ҿ���ʹ��һ��type���ֱ������������õģ�
		 */
		//���丳ֵ
		patient.setName(textName.getText().trim());
		patient.setAddress(textAddress.getText().trim());
		patient.setAge(Integer.valueOf(textAge.getText().trim()));
		patient.setPhone(textPhone.getText().trim());
		patient.setSex(comboSex.getText());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
