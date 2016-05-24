package client.panels;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import client.connect.ClientMainClass;
import entity.Request;
import entity.RequestType;
import entity.User;

public class UserManage extends JFrame{
	private static final long serialVersionUID = 2868561L;
	private UserInformationPanel info;
	private ModifyPasswdPanel pwd;
	
	public UserManage() {
		Container container = this.getContentPane();
		info=new UserInformationPanel(ClientMainClass.currentUser);
		pwd=new ModifyPasswdPanel();
		info.setPreferredSize(new Dimension(400,300));
		JTabbedPane pane = new JTabbedPane(JTabbedPane.TOP);
		pane.addTab("Information", info);
		pane.addTab("Sercuty", pwd);
		container.add(pane);
		this.setSize(400, 300);
		this.setLocation(200, 120);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		info.getCancer().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		info.getSure().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				User user=ClientMainClass.currentUser;
				Request req=new Request(RequestType.changeInformation);
				user.setName(info.getUserName().getText().trim());
				if(info.getMale().isSelected()){
					user.setSex("male");
				}else{
					user.setSex("female");
				}
				user.setIcon(String.valueOf(info.getFace().getSelectedIndex()+1));
				user.setMemo(info.getMemo().getText().trim());
				try {
					ClientMainClass.oos.writeObject(req);
					ClientMainClass.oos.writeObject(user);
					ClientMainClass.oos.flush();
					dispose();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		pwd.getCancer().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		pwd.getSure().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(new String(pwd.getOldpwd().getPassword()).trim().equals("")){
					JOptionPane.showMessageDialog(null,"Please input old password");
					pwd.getOldpwd().requestFocusInWindow();
				}if(new String(pwd.getNewpwd().getPassword()).trim().equals("")){
					JOptionPane.showMessageDialog(null,"Please input new password");
					pwd.getNewpwd().requestFocusInWindow();
				}else if(new String(pwd.getAgainpwd().getPassword()).trim().equals("")){
					JOptionPane.showMessageDialog(null,"Please input new password again");
					pwd.getAgainpwd().requestFocusInWindow();
				}else{
					if(new String(pwd.getNewpwd().getPassword()).equals(new String(pwd.getAgainpwd().getPassword()))){
						Request req=new Request(RequestType.modifypasswd);
						req.setData("id",String.valueOf(ClientMainClass.currentUser.getId()));
						req.setData("oldpwd", new String(pwd.getOldpwd().getPassword()));
						req.setData("newpwd", new String(pwd.getNewpwd().getPassword()));
						try {
							ClientMainClass.oos.writeObject(req);
							ClientMainClass.oos.write(1);
							ClientMainClass.oos.flush();
							dispose();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "They are not the same");
						pwd.getNewpwd().requestFocusInWindow();
					}	
				}
			}
		});
	}
}

