package server.connect;

import java.net.*;
import java.io.*;
import java.util.*;

import server.aboutdao.IService;
import server.aboutdao.Service;
import entity.OnlineUser;
import entity.User;
import java.util.concurrent.*;

public class ServerMainClass {
	public static Map<User,OnlineUser> userMap;
	public static IService dao;
	/**
	 * 静态初始化方法。
	 *
	 */
	public static void init(){
		userMap=new ConcurrentHashMap<User,OnlineUser> ();
		dao=new Service();
	}
	
	public static void main(String[] args) {
		init();
		ServerSocket ss=null;
		Socket s=null;
		try {
			ss=new ServerSocket(9999);
			while(true){
				s=ss.accept();
				new ServerThread(s).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

