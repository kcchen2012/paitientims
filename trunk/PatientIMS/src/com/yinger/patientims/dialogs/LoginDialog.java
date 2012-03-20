package com.yinger.patientims.dialogs;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.yinger.patientims.model.Doctor;

/**
 * ��¼�Ի���̳б�������Ի���TitleAreaDialog��
 * 
 */

// TitleAreaDialog : A dialog that has a title area for displaying a title and
// an image as well as a common area for
// displaying a description, a message, or an error message.
public class LoginDialog extends TitleAreaDialog {

	private Text text_username;
	private Text text_password;
	private Doctor doctor = new Doctor();//���ȹ���һ��Doctor

	public LoginDialog(Shell parentShell) {
		super(parentShell);
		//�����������shell���в�������Ϊ����shell��û�д�����		
	}

	// �����������������dialog��������ݣ�ʵ����dialog�ϰ벿�ֵ�����
	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent); // ���ø���ķ�����
		// ����dialog�ϵı���
		setTitle("Add Your Login Information");
		// ���ñ����������Ϣ��ʾ
		// setMessage("Attention:The textfield must't be blank!");
		setMessage("Attention: You just have three times to try!", IMessageProvider.INFORMATION);// ���˵ڶ��������Ϳ�����ʾһ����Ϣ��ͼ��
		// Minimal interface to a message provider. Used for dialog pages
		// which can provide a message with an icon.
		return contents;
	}

	// �������Ҳ����������dialog��������ݣ��������õ���dialog�°벿�ֵ�����
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));// (int numColumns,
										// boolean
										// makeColumnsEqualWidth)
		// composite.setLayout(new
		// GridLayout());//����ԭ���������˰���һ��Ҫ������swt�е�GridLayout
		// The method setLayout(Layout) in the type Composite is not
		// applicable for the arguments (GridLayout)
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL); // ����ˮƽ����
		composite.setLayoutData(layoutData);

		Label usernameLabel = new Label(composite, SWT.NONE);
		usernameLabel.setText("UserName��");
		text_username = new Text(composite, SWT.BORDER);
		text_username.setLayoutData(layoutData);

		Label passwordLabel = new Label(composite, SWT.NONE);
		passwordLabel.setText("Password��");
		text_password = new Text(composite, SWT.BORDER);
		text_password.setLayoutData(layoutData);
		text_password.setEchoChar('*'); // ������Ҫ����Ϊ��ʾ *

		return composite;
	}

	// ��Dialog���м̳й����ķ��������ڴ������¼��������������ķ���ʹ����open�����и�����ֵ
	// ע�⣺�����ǲ�Ҫ�Ե�¼����ʵ�ʵ���֤�ģ���ֻ���ڷǿ�����·����û����µļ���
	// Notifies that this dialog's button with the given id has been pressed.
	@Override
	protected void buttonPressed(int buttonId) {
		// ���� OK
		if (IDialogConstants.OK_ID == buttonId) {
			if (!checkLogin()) {//�����д������Ϊ�գ����޸���ʾ��ϢΪһ��������Ϣ��ͬʱ��¼���ڲ���رգ�
				return;
			}
			setLoginUser();//�����д�����ݲ�Ϊ�գ���ô���������õ�doctor���棬���ô��巵��OK����
			okPressed();// �򸸴���֪ͨ��¼���ڷ��ص���OK��ť�����£�Ҳ���Ǵ��ڵķ���ֵ��OK��
			// Dialog :Notifies that the ok button of this dialog has been pressed.
			// �ܽ᣺���������okPressed����cancelPressed��������ô�Ի����Ҫ�����ˣ��Ի���ᱻdispose
			// ����Ի���͸�����֮���ͨ�ſ����ڶԻ����б���һ����Ӧ�Ķ��������Ϳ����ڶԻ���dispose֮ǰ���Ǹ�������������úã�
			return;
		}
		// ���� Cancel
		if (IDialogConstants.CANCEL_ID == buttonId) {
			cancelPressed();// Notifies that the cancel button of this dialog has been pressed.
		}
	}

	// ����Ҫ�����õ�¼���û�
	private void setLoginUser() {
//		doctor = new Doctor();
		doctor.setUsername(text_username.getText());
		doctor.setPassword(text_password.getText());
	}

	// ����¼��Ϣ���ж��Ƿ���Ϣ��Ч
	private boolean checkLogin() {
		String username = text_username.getText();
		String password = text_password.getText();
		// ������Ҫ����ʾ������Ϣ������
		if (username == null || username.equals("") || password == null || password.equals("")) {
			setErrorMessage("Error:UserName and Password must'n be blank!");
			return false;
		}
		return true;
	}

	// ��Window���м̳й����ķ����������ڴ�����ʾʱ���ô������ʾ��Ϣ
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("User Login");
		newShell.setSize(400, 220);// ע������ĸ߶ȣ��߶�һ��Ҫ�㹻�Ĵ󣬷���ť���ܾͻῴ����
		newShell.setLocation(400, 300);
		// newShell.setImage(new Image(null, "/icons/Home.ico"));
//		newShell.setImage(getDefaultImage());// ����ͼƬʧ�ܣ�Ĭ��ͼƬ��ϵͳĬ�ϵ��Ǹ��ѿ��ĳ���ͼ��
//		newShell.setImage(new Image(null, System.getProperty("user.dir")+"\\icons\\User.ico"));
		//·�����ˣ� E:\Java_Study\Eclipse\Eclipse for rcp\eclipse_rcp\icons\User.ico
//		newShell.setImage(new Image(Display.getDefault(), ClassLoader.getSystemResourceAsStream("icons/User.ico")));
		//�������ǲ��У�display��null
	}

	// ��Window���м̳й����ķ��������������ı�dialog�Ĵ������ʽ
	@Override
	protected int getShellStyle() {
		return super.getShellStyle();
		// return super.getShellStyle() | SWT.MAX | SWT.MIN; // ������󻯺���С��
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

}
