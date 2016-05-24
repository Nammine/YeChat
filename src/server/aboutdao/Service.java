package server.aboutdao;

import java.util.ArrayList;
import java.util.List;



import entity.User;
import dao.*;

public class Service implements IService {

	public User addUser(Long id,String pwd) {
		User user=null;
		UserDao userDao=new UserDao();
		user=userDao.findUser(id, pwd);
		if(user==null){
			user=userDao.addUser(id, pwd);
		}
		
		return user;
	}

	public void delUser(Long id) {
		UserDao userDao=new UserDao();
		User user = this.getUser(id);
		if(user!=null){
			userDao.delUser(id);
		}
	}


	public User getUser(Long id) {
		UserDao userDao=new UserDao();
		User user = null;
		user = userDao.findUser(id);
		return user;
	}

	public User getUser(Long id, String pwd) {
		UserDao userDao=new UserDao();
		User user = userDao.findUser(id,pwd);
		return user;
	}


	public void updatePwd(Long id, String oldPwd, String newPwd) {
		UserDao userDao=new UserDao();
		User user = null;
		user=userDao.findUser(id, oldPwd);
		if (user != null) {
			user.setPwd(newPwd);
			try {
				userDao.UpdatePwd(id,newPwd);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
	
		}
	}



	@Override
	public List<User> getAllUser() {
		UserDao userDao=new UserDao();
		return userDao.getAllUsers();
	}

	@Override
	public int getUserCount() {
		UserDao userDao=new UserDao();
		return userDao.getAllUsers().size();
	}

	@Override
	public void updateUser(User user) {
		UserDao userDao=new UserDao();
		userDao.updateUser(user);
		// TODO Auto-generated method stub
		
	}

	/*public static void main(String[] args) {
           
		Service service = new Service();
		User user=service.getUser(1L);
		user.setIcon("22");
		service.updateUser(user);
	    
	}*/
}

