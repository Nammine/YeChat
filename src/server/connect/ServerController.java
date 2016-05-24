package server.connect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

import client.connect.ClientMainClass;
import server.aboutdao.IService;
import entity.*;

public class ServerController {
	private User user;
	private Socket s;
	private IService dao;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private OnlineUser onlineUser;
	
	public ServerController(Socket s) {
		super();
		dao=ServerMainClass.dao;
		this.s = s;
	}
	 
	public void handle() throws Exception {
		ois=new ObjectInputStream(s.getInputStream());
		oos=new ObjectOutputStream(s.getOutputStream());
		onlineUser=new OnlineUser(ois,oos);
		while(true){
			Request req=(Request)ois.readObject();
			ois.read();
			RequestType type=req.getType();
			if(type.equals(RequestType.exit)){
				exitHandle();
				break;
			}else if(type.equals(RequestType.login)){
				loginHandle(req);
			}else if(type.equals(RequestType.register)){
				registerHandle(req);
			}else if(type.equals(RequestType.offline)){
				offlineHandle();
				break;
			}else if(type.equals(RequestType.changeInformation)){
				changeInformationHandle();
			}else if(type.equals(RequestType.modifypasswd)){
				modifypasswdHandle(req);
			}else if(type.equals(RequestType.sendMessage)){
				sendMessageHandle(req);
			}else if(type.equals(RequestType.receiveFile)){
				receiveFileHandle(req);
			}else if(type.equals(RequestType.sendFile)){
				sendFileHandle(req);
			}
		}
	}
	private void modifypasswdHandle(Request req) {
		Long id=Long.parseLong(req.getData("id"));
		String oldpwd=req.getData("oldpwd");//û�п��Ǿ������Ƿ���ȷ
		String newpwd=req.getData("newpwd");
		Response res=new Response(RequestType.modifypasswd);
		try {
			dao.updatePwd(id, oldpwd, newpwd);
			res.setData(1);
			try {
				oos.writeObject(res);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (RuntimeException e) {
			try {
				oos.writeObject(res);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void changeInformationHandle() {
		try {
			User user=(User)ois.readObject();
			Response res=new Response(RequestType.changeInformation);
			try {
				dao.updateUser(user);
				res.setData(user);//�޸ĳɹ�����ֵ��һ������ֵ
				oos.writeObject(res);
				oos.flush();
				
			} catch (RuntimeException e) {
				oos.writeObject(res);//ʧ���򷵻�ֵ��������
				oos.flush();
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void exitHandle() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//�����ļ�
	private void sendFileHandle(Request req) {
	/*try {
		User u=(User)ois.readObject();
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	} */
	}
	//�����ļ�
	private void receiveFileHandle(Request req) {
		
	}
		
	//������Ϣ
	private void sendMessageHandle(Request req) {
		Response res=new Response(RequestType.receiveMessage);
		Message message=null;
		try {
			message=(Message)ois.readObject();
			res.setData(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		User to=message.getTo();
		if(to==null){
			sendToAllUser(res);//���������Ϊnull��������Ϣ��������
		}else{
			//������Ϣ��to�����Լ�
			Response res1=new Response(RequestType.individualTalk);
			res1.setData(message);
			ObjectOutputStream o=null;
			Set<User>set=ServerMainClass.userMap.keySet();
			Iterator it=set.iterator();
			while(it.hasNext()){
				User u=(User)it.next();
				if(u.equals(to)){
					o=ServerMainClass.userMap.get(u).getOos();
					break;
				}				
			}
			try {
				o.writeObject(res1);
				o.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//����
	private void offlineHandle() {
		try {
			User user=(User)ois.readObject();
			Set<User>users=ServerMainClass.userMap.keySet();
			Iterator it=users.iterator();
			 while(it.hasNext()) 
			 { 
				 User u=(User)it.next();
			 if(u.equals(user)){
				 ServerMainClass.userMap.remove(u);
			 }
			 }
			Response res=new Response(RequestType.offline);
			res.setData(user);//�������û����͸����пͻ���
			sendToAllUser(res);
			s.close();
		 }
			catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void registerHandle(Request req) {
		Long id=Long.parseLong((String)req.getData("id"));
		String pwd=(String)req.getData("pwd");
		User user=dao.addUser(id,pwd);
		try {
			oos.writeObject(user);
			System.out.println(user.getId()+":"+user.getPwd());
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//��¼
	private void loginHandle(Request req) {
		Long id=Long.parseLong((String)req.getData("id"));
		String pwd=(String)req.getData("pwd");
		user=dao.getUser(id,pwd);
		Response res;
		try {
			Set<User>users=ServerMainClass.userMap.keySet();
			Iterator iter=users.iterator();
			while(iter.hasNext()){
				User u=(User)iter.next();
				if(u.equals(user)){
					res=new Response(RequestType.haveOnline);
					oos.writeObject(res);
					oos.flush();
					return;//���û��Ѿ�����
				}				
			}
			res=new Response(RequestType.online);
			res.setData(user);
			oos.writeObject(res);
			oos.flush();
			if(user!=null){
				Set<User>set=ServerMainClass.userMap.keySet();
				oos.write(set.size());
				Iterator it=set.iterator();
				while(it.hasNext()){
					oos.writeObject(it.next());
				}
				oos.flush();
				sendToAllUser(res);//֪ͨ�����û������û�����
				ServerMainClass.userMap.put(user, onlineUser);//�����û���Ϣ
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sendToAllUser(Response res){
		try {
			Collection c= ServerMainClass.userMap.values();
			Iterator it=c.iterator();
			while(it.hasNext()){
				ObjectOutputStream o=((OnlineUser)it.next()).getOos();
				o.writeObject(res);
				o.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
