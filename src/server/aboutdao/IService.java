package server.aboutdao;

import java.util.List;

import entity.User;


public interface IService {
	
	List<User> getAllUser();  //ȡ�������û� 
	User getUser(Long id);  //�����û�
	User getUser(Long id,String pwd);  //�����û�
	int getUserCount();//����û���
	User addUser(Long id,String pwd);//����û�
	void updateUser(User user);//�޸��û�
	void delUser(Long id); //ɾ���û�
	void updatePwd(Long id,String oldPwd,String newPwd);  //�޸��û�����
}	



