package client.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import client.connect.ClientMainClass;
import entity.Request;
import entity.RequestType;

public class ModifyPasswdPanel extends JPanel {
	
	private static final long serialVersionUID = 112315242L;
	private JLabel[] lb = new JLabel[3];
	private JPasswordField oldpwd;
	private JPasswordField newpwd;
	private JPasswordField againpwd;
	private JButton sure;
	private JButton cancer;
	
	public ModifyPasswdPanel(){
		JPanel manage = new JPanel();
		manage.setLayout(new BoxLayout(manage, BoxLayout.Y_AXIS));
		String[] str = {"    Old£º      ","    New£º      ","Again£º"};
		JPanel p1 = new JPanel();
		lb[0] = new JLabel(str[0]);
		oldpwd = new JPasswordField(10);
		p1.add(lb[0]);
		p1.add(oldpwd);
		
		JPanel p2 = new JPanel();
		lb[1] = new JLabel(str[1]);
		newpwd = new JPasswordField(10);
		p2.add(lb[1]);
		p2.add(newpwd);
		
		JPanel p3 = new JPanel();
		lb[2] = new JLabel(str[2]);
		againpwd = new JPasswordField(10);
		p3.add(lb[2]);
		p3.add(againpwd);
		
		JPanel p4 = new JPanel();
		sure = new JButton("   Change   ");
		cancer = new JButton("   Cancel   ");
		p4.add(sure);
		p4.add(cancer);
		
		manage.add(p1);
		manage.add(p2);
		manage.add(p3);
		manage.add(p4);
		this.add(manage);
		this.setSize(300, 200);
	}

	public JButton getCancer() {
		return cancer;
	}

	public JPasswordField getAgainpwd() {
		return againpwd;
	}

	public JPasswordField getNewpwd() {
		return newpwd;
	}

	public JPasswordField getOldpwd() {
		return oldpwd;
	}

	public JButton getSure() {
		return sure;
	}
	
}
