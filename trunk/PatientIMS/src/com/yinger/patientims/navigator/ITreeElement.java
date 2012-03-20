package com.yinger.patientims.navigator;

import java.util.List;

/**
 * ���嵼����ͼ�ڲ��Ľڵ�Ľӿ�
 * 
 * �Ҿ��ò��õĵط�����û���������÷��ͻ��ƣ�
 */
public interface ITreeElement {

	public void setName(String name);

	public String getName();

	public void setChildren(List children);

	public List getChildren();

	public boolean hasChildren();

	public void addChildren(ITreeElement iElement);

}
