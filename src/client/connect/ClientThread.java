package client.connect;

import java.awt.Color;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.net.*;
import java.util.Calendar;

import client.panels.UserListPanel;
import client.windows.ChatWindow;
import entity.*;

public class ClientThread extends Thread{
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Socket s;
	private JTextPane receive;
	private JTextPane record;
	private JTextPane publicInfo;
	private UserListPanel userList;
	private SimpleAttributeSet set;
	
	public ClientThread(JTextPane receive, JTextPane record, JTextPane publicInfo, UserListPanel userList) {
		this.receive = receive;
		this.record = record;
		this.publicInfo = publicInfo;
		this.userList = userList;
		s=ClientMainClass.socket;
		ois=ClientMainClass.ois;
		oos=ClientMainClass.oos;
		set=new SimpleAttributeSet();
		StyleConstants.setFontSize(set, 16);
		StyleConstants.setFontFamily(set,"宋体");
		StyleConstants.setForeground(set, new Color(0,139,139));
	}

	@Override
	public void run() {
		while(s.isConnected()){
			try {
				Response res=(Response)ois.readObject();
				if(res!=null){
					RequestType type=res.getType();
					if(type.equals(RequestType.online)){
						onlineHandle(res);
					}else if(type.equals(RequestType.offline)){
						offlineHandle(res);
					}else if(type.equals(RequestType.changeInformation)){
						changeInformationHandle(res);
					}else if(type.equals(RequestType.modifypasswd)){
						modifypasswdHandle(res);
					}else if(type.equals(RequestType.receiveMessage)){
						receiveMessageHandle(res);
					}else if(type.equals(RequestType.individualTalk)){
						individualTalkHandle(res);
					}else if(type.equals(RequestType.receiveFile)){
						receiveFileHandle(res);
					}else if(type.equals(RequestType.publicInfo)){
						publicInfoHandle(res);
					}

				}
			} catch (EOFException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void modifypasswdHandle(Response res) {
		if(res.getData()!=null){
			JOptionPane.showMessageDialog(null, "Success");
		}else{
			JOptionPane.showMessageDialog(null, "Fail\nTHE server is busy,please try again later!");
		}
	}

	private void changeInformationHandle(Response res) {
		if(res.getData()!=null){
			JOptionPane.showMessageDialog(null, "Success");
		}else{
			JOptionPane.showMessageDialog(null, "Server is busy,do it later");
		}
	}

	private void publicInfoHandle(Response res) {
		/*String str=(String)res.getData();
		publicInfo.setText(str);*/
	}

	private void receiveFileHandle(Response res) {
		
	}

	private void individualTalkHandle(Response res) {
		Message message=(Message)res.getData();
		User user=message.getFrom();
		if(ClientMainClass.shield.contains(message.getFrom().getId())){
			return;
		}
		if(!ClientMainClass.individual.containsKey(user.getId())){
			int flag=JOptionPane.showConfirmDialog(null,user.getName()+"wants to chat with you?","",JOptionPane.YES_NO_OPTION);
			if(flag==JOptionPane.NO_OPTION){
				return;
			}
			ChatWindow indi=new ChatWindow(user);
			ClientMainClass.individual.put(user.getId(), indi.getReceivedmessageArea().getTextPane());
			indi.showMe();
		}
		JTextPane jtp=ClientMainClass.individual.get(user.getId());
		try {//输出信息发送人，时间
			jtp.getDocument().insertString(jtp.getDocument().getLength(),
																	user.getName()+" "+message.getTime()+"\n",
																	set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		//输出信息
		message.analysisMessage(jtp);
	}

	private void receiveMessageHandle(Response res) {
		Message message=(Message)res.getData();
		if(ClientMainClass.shield.contains(message.getFrom().getId())){
			return;
		}
		try {//输出信息发送人，时间
			receive.getDocument().insertString(receive.getDocument().getLength(),
																	message.getFrom().getName()+" "+message.getTime()+"\n",
																	set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		//输出信息
		message.analysisMessage(receive);
	}

	private void offlineHandle(Response res) {
		User user=(User)res.getData();
		ClientMainClass.onlineUsers.remove(user);
		userList.freash(ClientMainClass.onlineUsers);
		Calendar c = Calendar.getInstance();
		String time=c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH)+" "+
				(c.get(Calendar.HOUR_OF_DAY)+8)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
		try {
			receive.getDocument().insertString(receive.getDocument().getLength(),
					user.getName()+" "+time+" 下线\n",
					set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void onlineHandle(Response res) {
		User user=(User)res.getData();
		ClientMainClass.onlineUsers.add(user);
		userList.freash(ClientMainClass.onlineUsers);
		Calendar c = Calendar.getInstance();
		String time=c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH)+" "+
				(c.get(Calendar.HOUR_OF_DAY)+8)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
		try {
			receive.getDocument().insertString(receive.getDocument().getLength(),
					user.getName()+" "+time+" 上线\n",
					set);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
}

