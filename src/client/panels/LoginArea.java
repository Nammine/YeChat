package client.panels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
/*
 * ��½��������벿��
 */
public class LoginArea extends JPanel{
	private static final long serialVersionUID = 1987461L;//��֤��Ķ�����
	private JTextField name; //�û���
	private JPasswordField pwd;//����
	
	public LoginArea(){
		/*
		 * ָ���߿���ʽ����ɫ�ߣ���1��5
		 */
		Border line = BorderFactory.createLineBorder(Color.gray);//����һ������߽磬��ָ����������ɫ
		Border line2 = BorderFactory.createEmptyBorder(1,1,1,1);//ָ��һ���յı߽磬�������ҵĿ�ȣ��ڴ˷�Χ�ڲ���������
		Border empty = BorderFactory.createEmptyBorder(5,5,5,5);
		Border inOut = BorderFactory.createCompoundBorder(line2, empty);//��ϱ߿򣬽��߿���ʽ���
		inOut = BorderFactory.createCompoundBorder(inOut, line);
		/*
		 * ָ������������
		 */
		JLabel userName = new JLabel("Account��");
		userName.setForeground(Color.gray);
		JLabel userPwd =  new JLabel("Password��");
		userPwd.setForeground(Color.gray);
		
		name = new JTextField(12);
		pwd = new JPasswordField(12);
		
		JPanel namePanel = new JPanel();
		JPanel pwdPanel = new JPanel();
	
		namePanel.add(userName);
		namePanel.add(name);
		
		pwdPanel.add(userPwd);
		pwdPanel.add(pwd);
		
		this.setBorder(inOut);//���ø����ı߽���ʽ
		this.add(namePanel);//�����������
		this.add(pwdPanel);//�����������
		this.setPreferredSize(new Dimension(355, 106));//���������СsetpreferredSize����װ�����СDimension
	}

	public JTextField getNameField() {
		return name;
	}

	public JPasswordField getPwdField() {
		return pwd;
	}
	
}


