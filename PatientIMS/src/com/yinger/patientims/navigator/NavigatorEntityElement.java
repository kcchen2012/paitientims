package com.yinger.patientims.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.IEditorInput;

/**
 * ʵ���˵�����ͼ�ڲ��Ľڵ�Ľӿ�ITreeElement
 * 
 * �Ҹ��˸о����Զ���һ��int��������ʾ���element�ڵ��level��[OK!]
 */
public class NavigatorEntityElement implements ITreeElement {

	private String name;// �ڵ�����
	private int level = 0;// �ڵ���ȣ�0��ʾ���ڵ㣬��Ĭ��ֵ
	private List children = new ArrayList();// �ڵ㺢��
	private IEditorInput editorInput;// �ڵ��IEditorInput����
	//IEditorInput�������ģ��ڽӿ���û�ж��壬����Ԫ�ذ�Ҫһ��editor���Ǻ����õ�

	public NavigatorEntityElement() {

	}

	public NavigatorEntityElement(String name) {
		this.name = name;
	}

	public NavigatorEntityElement(int level, String name) {
		this(name);
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public IEditorInput getEditorInput() {
		return editorInput;
	}

	public void setEditorInput(IEditorInput editorInput) {
		this.editorInput = editorInput;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setChildren(List children) {
		this.children = children;
	}

	@Override
	public List getChildren() {
		return children;
	}

	@Override
	public boolean hasChildren() {
		if (children.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addChildren(ITreeElement iElement) {
		children.add(iElement);
	}

}
