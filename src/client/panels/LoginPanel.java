package client.panels;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * ��¼����ĵ�½��ע�ᣬȡ����ť
 */
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1564163L;//��֤��Ķ�����
	private JButton btnRegister;
	private JButton btnLoad;
	private JButton btnCancer;
	
	public LoginPanel(){
		btnRegister = new JButton("Register");
		btnRegister.setForeground(Color.gray);
		btnLoad = new JButton("Login");
		btnLoad.setForeground(Color.gray);
		btnCancer = new JButton("Cancle");
		btnCancer.setForeground(Color.gray);
	
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10 ,10));//��ʽ���֣��������������һ�η��룬center�м���룬��������Ϊ�������룬��ֱ���
		this.add(btnRegister);
		this.add(btnLoad);
		this.add(btnCancer);
	}

	public JButton getBtnCancer() {
		return btnCancer;
	}

	public JButton getBtnLoad() {
		return btnLoad;
	}

	public JButton getBtnRegister() {
		return btnRegister;
	}

}


