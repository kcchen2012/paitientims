package com.yinger.patientims.views.viewerProvider;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * ��ס��������һ��TableViewer�������ṩ����һ��Ҫ��ʵ��IStructuredContentProvider�ӿ�
 * ������IContentProvider��IStructuredContentProvider�ӿ��ж�����getElements���������
 * ������Ҫʵ�ֵģ���setInputʱ�����list����ת����Object����
 */
public class PatientInfoTableViewerContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return ((List)inputElement).toArray();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
