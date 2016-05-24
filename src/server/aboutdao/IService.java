package server.aboutdao;

import java.util.List;

import entity.User;


public interface IService {
	
	List<User> getAllUser();  //取得所有用户 
	User getUser(Long id);  //查找用户
	User getUser(Long id,String pwd);  //查找用户
	int getUserCount();//获得用户数
	User addUser(Long id,String pwd);//添加用户
	void updateUser(User user);//修改用户
	void delUser(Long id); //删除用户
	void updatePwd(Long id,String oldPwd,String newPwd);  //修改用户密码
}	



