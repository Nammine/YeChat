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
 * 登陆界面的输入部分
 */
public class LoginArea extends JPanel{
	private static final long serialVersionUID = 1987461L;//保证类的独特性
	private JTextField name; //用户名
	private JPasswordField pwd;//密码
	
	public LoginArea(){
		/*
		 * 指定边框样式，灰色线，空1，5
		 */
		Border line = BorderFactory.createLineBorder(Color.gray);//建立一个线务边界，并指定线条的颜色
		Border line2 = BorderFactory.createEmptyBorder(1,1,1,1);//指定一个空的边界，上下左右的宽度，在此范围内不能有内容
		Border empty = BorderFactory.createEmptyBorder(5,5,5,5);
		Border inOut = BorderFactory.createCompoundBorder(line2, empty);//组合边框，将边框样式组合
		inOut = BorderFactory.createCompoundBorder(inOut, line);
		/*
		 * 指定该面板的内容
		 */
		JLabel userName = new JLabel("Account：");
		userName.setForeground(Color.gray);
		JLabel userPwd =  new JLabel("Password：");
		userPwd.setForeground(Color.gray);
		
		name = new JTextField(12);
		pwd = new JPasswordField(12);
		
		JPanel namePanel = new JPanel();
		JPanel pwdPanel = new JPanel();
	
		namePanel.add(userName);
		namePanel.add(name);
		
		pwdPanel.add(userPwd);
		pwdPanel.add(pwd);
		
		this.setBorder(inOut);//设置该面板的边界样式
		this.add(namePanel);//加入姓名面板
		this.add(pwdPanel);//加入密码面板
		this.setPreferredSize(new Dimension(355, 106));//设置组件大小setpreferredSize，封装组件大小Dimension
	}

	public JTextField getNameField() {
		return name;
	}

	public JPasswordField getPwdField() {
		return pwd;
	}
	
}


