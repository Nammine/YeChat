package client.windows;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.EOFException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import client.connect.ClientMainClass;
import client.windows.ClientWindow;
import entity.Request;
import entity.RequestType;
import entity.Response;
import entity.User;
import client.panels.*;
/*
 * 建立登录界面
 */
public class LoginWindow extends JFrame {
	private static final long serialVersionUID = 11896163L;//保证类的独立性
	private LoginTopPanel top;
	private LoginArea loginArea;
	private LoginPanel login;
	
	public LoginWindow(){
		super("YeChat");
		Container container = this.getContentPane();//获得内容面板，而后向其添加组件
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));//箱式布局，允许在容器中纵向或者横向防止多个控件。Y_AXIS是指定从上到下垂直布置组件。
		
		top=new LoginTopPanel();
		loginArea=new LoginArea();
		login=new LoginPanel();
		
		container.add(top);
		container.add(loginArea);
		container.add(login);
		addHanderListener();
	}
	private void addHanderListener(){
		
		login.getBtnCancer().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				loginArea.getNameField().setText("");
				loginArea.getPwdField().setText("");
			}
		});
		
		login.getBtnLoad().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				load(loginArea.getNameField().getText().trim(),new String(loginArea.getPwdField().getPassword()).trim());
			}
		});
		//注册
		login.getBtnRegister().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			    register(loginArea.getNameField().getText().trim(),new String(loginArea.getPwdField().getPassword()).trim());
			}
		});
		
	   this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					Request req=new Request(RequestType.exit);
					ClientMainClass.oos.writeObject(req);
					ClientMainClass.oos.flush();
					System.exit(0);		
				}catch (EOFException e2) {
					
				}catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	private void register(String id,String pwd){
		if(id.equals("")){
			JOptionPane.showMessageDialog(null,"Please input your id");
			loginArea.getNameField().requestFocusInWindow();
		}else if(pwd.equals("") ){
			JOptionPane.showMessageDialog(null,"Please input your password");
			loginArea.getPwdField().requestFocusInWindow();
		}else{
			try {
				Request req=new Request(RequestType.register);
				req.setData("id",id);
				req.setData("pwd",pwd);
				ClientMainClass.oos.writeObject(req);//发送登录请求
				ClientMainClass.oos.write(1);
				ClientMainClass.oos.flush();
				User user=(User)ClientMainClass.ois.readObject();
				if(user!=null){
					JOptionPane.showMessageDialog(null,"Success\nId : "+user.getId()+"\nPassword : "+user.getPwd());
				}
				loginArea.getNameField().requestFocusInWindow();
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	private void load(String id,String pwd){
		if(id.equals("")){
			JOptionPane.showMessageDialog(null,"Please input your id");
			loginArea.getNameField().requestFocusInWindow();
		}else if(pwd.equals("") ){
			JOptionPane.showMessageDialog(null,"Please input your password");
			loginArea.getPwdField().requestFocusInWindow();
		}else{	
			try {
				Request req=new Request(RequestType.login);
				req.setData("id",id);
				req.setData("pwd",pwd);
				ClientMainClass.oos.writeObject(req);//发送登录请求
				ClientMainClass.oos.write(1);
				ClientMainClass.oos.flush();
				Response res=(Response)ClientMainClass.ois.readObject();
				User user=(User)res.getData();
				if(res.getType().equals(RequestType.haveOnline)){
					JOptionPane.showMessageDialog(this,"Sorry,the user is already online");
				}else if(user!=null&&res.getType().equals(RequestType.online)){
					ClientMainClass.currentUser=user;
					int n=ClientMainClass.ois.read();
					for(int i=0;i<n;i++){
						ClientMainClass.onlineUsers.add((User)ClientMainClass.ois.readObject());
					}
					this.dispose();
					new ClientWindow().showMe();
				}else{
					JOptionPane.showMessageDialog(this,"Sorry,the id or the password are not right");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
		
	public void showMe(){
		ImageIcon tp1=new ImageIcon("images/bigheads/1.jpg");
		this.setIconImage(tp1.getImage());
		this.setTitle("YeChat");
		this.setSize(new Dimension(360,230));
		this.setLocation(300,200);				
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
   
}


