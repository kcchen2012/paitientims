package com.yinger.patientims.views.viewerProvider;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import com.yinger.patientims.navigator.NavigatorEntityElement;

/**
 * ������ͼ��TreeViewer�ı�ǩ�ṩ�� 
 * 
 * ��ǩ�ṩ���ṩ����������ʾ���ı�����ͼƬ
 * ע�⣺TreeViewer�ı�ǩ�ṩ�������ʵ��ILabelProvider��������IBaseLabelProvider
 * 
 * ��ǩ�ṩ�����԰�������ͼ����Դ����Ϊ������ȷ��ͼ�����ͬʵ�����ڲ鿴�������е���������
 */
public class NavigatorTreeViewerLabelProvider implements ILabelProvider {
	
	public NavigatorTreeViewerLabelProvider(){
		//�����д��һ��Ĭ�ϵĹ�����
	}

	@Override
	public void addListener(ILabelProviderListener listener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {

	}

	//�ص㣺ѧ��õ�ϵͳ�ṩ��ͼƬ�ķ�����PlatformUI.getWorkbench().getSharedImages().getImage( ISharedImages.XX)
	@Override//Ҫ��ʾ��ͼƬ
	public Image getImage(Object element) {
		String image1 = ISharedImages.IMG_OBJ_FOLDER;
		String image2 = ISharedImages.IMG_OBJ_FILE;
		NavigatorEntityElement entityElement = (NavigatorEntityElement) element;
		if(entityElement.hasChildren()){
			return PlatformUI.getWorkbench().getSharedImages().getImage(image1);
		}else{
			return PlatformUI.getWorkbench().getSharedImages().getImage(image2);
		}
	}

	@Override//Ҫ��ʾ���ı�
	public String getText(Object element) {
		NavigatorEntityElement entityElement = (NavigatorEntityElement) element;
		return entityElement.getName();
	}

}
