package client.connect;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import client.windows.LoginWindow;
import entity.User;

public class ClientMainClass {
	public static  Socket socket;//当前连接的Socket，此Socket在整个客户端程序运行过程中一直有效。
	public static ObjectInputStream ois;//根据Socket得到的对象输入流，在整个客户端程序运行过程中一直有效。
	public static ObjectOutputStream oos;//根据Socket得到的对象输出流，在整个客户端程序运行过程中一直有效。
	public static User currentUser;//当前用户
	public static List<User> onlineUsers;//当前在线用户
	public static Map<Long,JTextPane> individual;//私聊好友
	public static Set<Long> shield;//屏蔽发言
	
	private static void init(){
		try {
			shield=new HashSet<Long>();
			onlineUsers=new ArrayList<User>();
			individual=new HashMap<Long,JTextPane>();
			socket=new Socket("127.0.0.1",9999);
			oos=new ObjectOutputStream(socket.getOutputStream());
			ois=new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),"不能建立网络连接，请检查配置参数！");
			System.exit(0);
		}
	}
	/**
	 * 客户端运行的主方法。
	 */
	public static void main(String[] args) {
		ClientMainClass.init();
		new LoginWindow().showMe();
	}

}

