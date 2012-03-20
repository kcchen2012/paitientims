package com.yinger.patientims.wizards;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;

import com.yinger.patientims.Activator;
import com.yinger.patientims.dao.PatientDAO;
import com.yinger.patientims.model.Patient;
import com.yinger.patientims.sigleton.PatientFactory;
import com.yinger.patientims.util.DBUtil;
import com.yinger.patientims.util.PluginUtil;
import com.yinger.patientims.wizards.wizardPages.PatientBasicInfoWizardPage;
import com.yinger.patientims.wizards.wizardPages.PatientRegisterInfoWizardPage;

public class AddPatientInfoWizard extends Wizard {
	/**
	 * �Ҿ�������Ӧ��ʹ�õ�̬ģʽ����һ���ࣨ������Patient�ࣩ������һ��patient����
	 * �Ӷ�ȷ���½���ʱ�������ͬһ��patient
	 * ͬʱ��ʾpatient����ϢҲ��һ��patient��Ϊ�˱�������patient��ͻ���������Ǹ����ж�������
	 * ��ͬ��patient�����ǲ��õ���ģʽ���з��ʣ�OK�� ��Ӳ�����Ϣ���޸Ĳ�����Ϣ��ʵ��ͬһ�����壡
	 * ���ǲ�ͬ�������ʱpatient��û�����ݵ�
	 */
//	private Patient patient;
	// ע�⣡���������page�����Wizard�ж�ʹ����һ��Patient����
	// Ŀ����Ϊ��ͨ�����patient�õ��������Ϣ
	// �����Զ����WizardPage
	private PatientBasicInfoWizardPage pageOne;
	private PatientRegisterInfoWizardPage pageTwo;
	
	private String type;

	@Override
	// �����ҳ�棨ʵ����IWizardPage���ࣩ
	/* ����Ҫע���������page��patient��������ι�����һ��� */
	public void addPages() {
		// ����ͼƬ��һ����ԣ�û������
		getShell().setImage(new Image(null, Activator.getImageDescriptor("/icons/small/add.gif").getImageData()));
		// TODO���������ñ�����һ��û����Ч��
		getShell().setText("Add Patient Information");

		pageOne = new PatientBasicInfoWizardPage();
		pageOne.setType(type);
		// �ص㣡
//		pageOne.setPatient(patient);
		pageTwo = new PatientRegisterInfoWizardPage();
		pageTwo.setType(type);
		// pageTwo.setPatient(patient);
		// �ص㣡
//		pageTwo.setPatient(pageOne.getPatient());// ��ҳ��patient��ǰҳ��patient��
		addPage(pageOne);
		addPage(pageTwo);
	}

	@Override
	public boolean performFinish() {
		// ���patient���˺ܶ����Ϣ������û�в���id
//		patient = pageTwo.getPatient();
		//��������page�����ã�PatientFactory�ĵ���ʵ���Ѿ������ǿյ���
		Patient patient = PatientFactory.getInstance();
		// ���ȸ������е���Ϣ�õ�����id
//		Long id = DBUtil.getSickBedId(patient);//ʵ������û�б�Ҫ�ģ���Ϊ���Դ������б��еõ�sickbed����id���Ժ����׵ĵõ�
//		Long id = patient.getSickbed().getId();
//		if (id != 0) {
//			patient.getSickbed().setId(id);
//		} else {
//			MessageDialog.openError(null, "Information", "Add patient information failed!");
//			return false;
//		}
//		 Ȼ���ٲ��뵽���ݿ���
		if(type.equals(PluginUtil.ADD)){
			if (new PatientDAO().insertPatient(patient)) {
				MessageDialog.openInformation(null, "Information", "Add patient information successfully!");
				return true;
			} else {
				MessageDialog.openError(null, "Information", "Add patient information failed!");
				return false;
			}
		}else{
			if (new PatientDAO().updatePatient(patient)) {
				MessageDialog.openInformation(null, "Information", "Modify patient information successfully!");
				return true;
			} else {
				MessageDialog.openError(null, "Information", "Modify patient information failed!");
				return false;
			}
		}

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public Patient getPatient() {
//		return patient;
//	}
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}
	
	

}
