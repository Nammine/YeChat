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
	public static  Socket socket;//��ǰ���ӵ�Socket����Socket�������ͻ��˳������й�����һֱ��Ч��
	public static ObjectInputStream ois;//����Socket�õ��Ķ������������������ͻ��˳������й�����һֱ��Ч��
	public static ObjectOutputStream oos;//����Socket�õ��Ķ�����������������ͻ��˳������й�����һֱ��Ч��
	public static User currentUser;//��ǰ�û�
	public static List<User> onlineUsers;//��ǰ�����û�
	public static Map<Long,JTextPane> individual;//˽�ĺ���
	public static Set<Long> shield;//���η���
	
	private static void init(){
		try {
			shield=new HashSet<Long>();
			onlineUsers=new ArrayList<User>();
			individual=new HashMap<Long,JTextPane>();
			socket=new Socket("127.0.0.1",9999);
			oos=new ObjectOutputStream(socket.getOutputStream());
			ois=new ObjectInputStream(socket.getInputStream());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),"���ܽ����������ӣ��������ò�����");
			System.exit(0);
		}
	}
	/**
	 * �ͻ������е���������
	 */
	public static void main(String[] args) {
		ClientMainClass.init();
		new LoginWindow().showMe();
	}

}

