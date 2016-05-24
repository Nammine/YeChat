package client.panels;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * 登录界面的登陆，注册，取消按钮
 */
public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1564163L;//保证类的独立性
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
	
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10 ,10));//流式布局，将组件从左往右一次放入，center中间对齐，两个参数为组件间距离，垂直间距
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


