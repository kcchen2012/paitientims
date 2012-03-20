package com.yinger.patientims;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 * ����ࣨ���������̳���AbstractUIPlugin������UI�����
 * ���������Ʋ�����������ڣ��洢�����ȫ����Ϣ
 * ����һ�������࣬�������洢������������һЩ��̬���ܺ����ķ���λ��
 * ��������������������У�Eclipseֻʹ�����������ʵ������������������ʵ��
 * ��ͨ������£����������һ����̬�ֶΣ������������Ψһ��ʵ��
 * ��������Ҫ��ʱ�������Է���ı�����ĸ����ط�������
 * ͨ�����������಻��Ҫ�Ķ�
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.yinger.patientims"; //$NON-NLS-1$   //$NON-NLS-1$ �����ڹ��ʻ��ģ����ܸĶ�

	// The shared instance
	private static Activator plugin;  //Ψһ��ʵ��
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * �õ�Ψһ��ʵ��
	 * Returns the shared instance
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * ����·����������ڲ�����ImageDescriptor�������������Ҫ
	 * �ܶ�ʱ������ͼƬ����ImageDescriptor���ǵ����������
	 * ��һ���ô��������ַ����ǲ������·���õ���
	 * ע��path��д�������������Ŀ�µ�iconsĿ¼�У�Ӧ����  /icons/XX.ico
	 * Returns an image descriptor for the image file at the given plug-in relative path
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
//		AbstractUIPlugin.imageDescriptorFromPlugin(pluginId, imageFilePath)
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
