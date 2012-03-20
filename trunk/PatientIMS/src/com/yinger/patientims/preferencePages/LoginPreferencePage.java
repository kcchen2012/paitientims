package com.yinger.patientims.preferencePages;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;

public class LoginPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, ModifyListener {
	private Text text_name;
	private Text text_pwd;
	private Text text_pwd2;
	private Button btnShowPassword;

	public LoginPreferencePage() {
		setDescription("You can modify your login information here!");// ������ѡҳ����ʾ��Ϣ
	}

	@Override
	public void modifyText(ModifyEvent e) {
		String name = text_name.getText();
		String pwd = text_pwd.getText();

		// ������������������õ���ʾ��ʽ����Ȼ������Щbug�����棬���磺
		// �����д��name֮����ɾ���ˣ���ô�Ǹ�������ʾһֱ����ڣ���ʹ����д��name
		if (e.widget.equals(text_name)) {
			if (name == null || name.equals("")) {
				setErrorMessage("UserName can not be null!");
			}
		} else if (e.widget.equals(text_pwd)) {
			if (pwd == null || pwd.equals("")) {
				setErrorMessage("Password can not be null!");
			} else {
				if (btnShowPassword.getSelection()) {
					text_pwd2.setText(text_pwd.getText());
				}
			}
		}
		if (name != null && !name.equals("") && pwd != null && !pwd.equals("")) {
			setMessage("Login information has been changed!");
			setValid(true);
		} else {
			setMessage("Login information is not valid!");
			setValid(false);
		}

		//ԭ���д��
		// if (name==null || name.equals("")) {
		// setErrorMessage("UserName can not be null!");
		// setValid(false);
		// }else if (pwd==null || pwd.equals("")) {
		// setErrorMessage("Password can not be null!");//����������⣡
		// setValid(false);
		// }else {
		// // setErrorMessage("");
		// setMessage("Login information has been changed!");
		// setValid(true);
		// }

	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		// composite.setLayout(new GridLayout(1, false));
		composite.setLayout(new FillLayout());
		Group group = new Group(composite, SWT.NONE);
		group.setText("Login information");
		group.setLayout(new GridLayout(2, false));

		Label lblUser = new Label(group, SWT.NONE);
		lblUser.setText("User:");

		text_name = new Text(group, SWT.BORDER);
		text_name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_name.addModifyListener(this);
		// text_name.setText("yinger");//������Ҳ��֪��������Ը���ֵ����仰�ᱨ��

		Label lblPassword = new Label(group, SWT.NONE);
		lblPassword.setText("Password:");

		text_pwd = new Text(group, SWT.BORDER);
		text_pwd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_pwd.addModifyListener(this);
		// text_pwd.setText("090807");
		text_pwd.setEchoChar('*');// ���벻��ʾ

		btnShowPassword = new Button(group, SWT.CHECK);
		btnShowPassword.setText("show Password");

		text_pwd2 = new Text(group, SWT.BORDER);
		text_pwd2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		text_pwd2.setEditable(false);

		btnShowPassword.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnShowPassword.getSelection()) {
					text_pwd2.setText(text_pwd.getText());
				} else {
					// text_pwd2.setEchoChar('*');
					text_pwd2.setText("");
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		noDefaultAndApplyButton();// ���ء�Restore Default���͡�Apply����ť
		setValid(false);
		return composite;
	}

	@Override
	public boolean performOk() {
		//�����Ӧ���޸ĵ�¼��Ϣ�����ݣ�
		return true;
	}

}
