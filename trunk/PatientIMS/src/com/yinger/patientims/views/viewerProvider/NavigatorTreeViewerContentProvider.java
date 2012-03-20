package com.yinger.patientims.views.viewerProvider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.yinger.patientims.navigator.ITreeElement;
/**
 * ������ͼ�е�TreeViewer�������ṩ��
 * 
 * �����ṩ���ṩ���ǲ鿴�������ݣ�Ҳ���ǲ鿴���е�Ԫ����ʲô����
 * ע�⣺TreeViewer�������ṩ��Ҫʵ�ֵĽӿ���ITreeContentProvider��������IContentProvider
 * IContentProvider�ӿ��ṩ�˻�����������Э�飬���ڽ������ṩ��������Ԫ�ؽ��й���
 * �Լ���������Ԫ�صĸ���
 * 
 * ͨ���������ṩ��֪�����������Ԫ�غ������Ĳ鿴������֮�����ӳ��
 * 
 */
public class NavigatorTreeViewerContentProvider implements ITreeContentProvider {
	
	public NavigatorTreeViewerContentProvider(){
		//�����д��һ��Ĭ�ϵĹ�����
	}

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override//���������趨��Ԫ��ʱ�����ز鿴������ʾ��Ԫ��
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			return ((List) inputElement).toArray();
		}else{
			return new Object[0];//Ϊʲô�ڲ���list��ʱ��Ҫ���ص���һ���յ�Object�����أ�
		}
	}

	@Override//�õ�ָ����Ԫ�ص���Ԫ��
	public Object[] getChildren(Object parentElement) {
		ITreeElement treeElement = (ITreeElement)parentElement;
		List children = treeElement.getChildren();
		if(children == null || children.isEmpty()){
			return new Object[0];
		}else{
			return children.toArray();
		}
	}

	@Override//û��ȥʵ��
	public Object getParent(Object element) {
		return null;
	}

	@Override//�Ƿ����ӽڵ�
	public boolean hasChildren(Object element) {
		ITreeElement treeElement = (ITreeElement)element;
		List children = treeElement.getChildren();
		if(children == null || children.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

}
