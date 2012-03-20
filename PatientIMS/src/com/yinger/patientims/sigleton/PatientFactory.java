package com.yinger.patientims.sigleton;

import com.yinger.patientims.model.Patient;

/**
 * ���÷Ǳ�׼�ĵ���ģʽ
 * ��Ҫ�������patient���޸�patient��Ϣ��
 * 
 * ͻȻ��һ���뷨�ǽ���һ�������ķ��͵ĵ�����
 * instance�������ǲ�ȷ���ģ����ݴ���Ĳ�����ȷ����
 * �������кܶ����ɾ��ĵ�ϵͳ�зǳ����ã�
 */
public class PatientFactory {
	
	private static Patient instance = new Patient();
	
	private PatientFactory() {
//		instance = new Patient();
	}
	
	public static Patient getInstance(){
		return instance;
	}
	
	public static void setInstance(Patient patient){
		instance = patient;
	}

}
