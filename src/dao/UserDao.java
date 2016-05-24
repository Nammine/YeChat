package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import common.ConnectionFactory;


public class UserDao {
	public User findUser(Long id,String pwd){
		User user=null;
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from t_user where id=? and pwd=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1,id);
			pstmt.setString(2,pwd);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			    user=new User();
				Long user_id=rs.getLong("id");
				String user_name=rs.getString("name");
				String user_pwd=rs.getString("pwd");
				String user_sex=rs.getString("sex");
				String user_icon=rs.getString("icon");
				String user_memo=rs.getString("memo");
				user.setId(user_id);
				user.setName(user_name);
				user.setPwd(user_pwd);
				user.setSex(user_sex);
				user.setIcon(user_icon);
				user.setMemo(user_memo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public User addUser(Long id, String pwd)
	{   
		User user=null;
		Connection conn=ConnectionFactory.getConnection();
		String sql="insert into t_user (id,pwd,icon) values(?,?,?)";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1,id);
			pstmt.setString(2,pwd);
			pstmt.setString(3,"1");
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			user=new User();
			user.setId(id);
			user.setName(" ");
			user.setPwd(pwd);
			user.setSex(" ");
			user.setIcon("1");
			user.setMemo(" ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public User findUser(Long id){
		User user=null;
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from t_user where id=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1,id);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
			    user=new User();
				Long user_id=rs.getLong("id");
				String user_name=rs.getString("name");
				String user_pwd=rs.getString("pwd");
				String user_sex=rs.getString("sex");
				String user_icon=rs.getString("icon");
				String user_memo=rs.getString("memo");
				user.setId(user_id);
				user.setName(user_name);
				user.setPwd(user_pwd);
				user.setSex(user_sex);
				user.setIcon(user_icon);
				user.setMemo(user_memo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public void delUser(Long id){
		Connection conn=ConnectionFactory.getConnection();
		String sql="delete from t_user where id=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1,id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void UpdatePwd(Long id,String pwd){
		Connection conn=ConnectionFactory.getConnection();
		String sql="update t_user set pwd=? where id=?";
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,pwd);
			pstmt.setLong(2,id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<User> getAllUsers() {
		Connection conn = ConnectionFactory.getConnection();
		List<User> users = new ArrayList<>();
		try {
			Statement stms = conn.createStatement();
			String sql = "select * from t_user";
			ResultSet rs = stms.executeQuery(sql);
			int flag = 0;
			while(rs.next()){
				User user=new User();
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String pwd = rs.getString("pwd");
				String sex = rs.getString("sex");
				String icon = rs.getString("icon");
				String memo = rs.getString("memo");
				user.setId(id);
				user.setName(name);
				user.setPwd(pwd);
				user.setSex(sex);
				user.setIcon(icon);
				user.setMemo(memo);
				users.add(user);
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	public void updateUser(User user){
		Connection conn=ConnectionFactory.getConnection();
		String sql="select * from t_user where id=?";
		String str=null;
	
		try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setLong(1,user.getId());
			ResultSet rs=pstmt.executeQuery();
			Long id=user.getId();
			String name=user.getName();
			String pwd=user.getPwd();
			String sex=user.getSex();
			String icon=user.getIcon();
			String memo=user.getMemo();
			while(rs.next()){
			    user=new User();
				Long user_id=rs.getLong("id");
				String user_name=rs.getString("name");
				String user_pwd=rs.getString("pwd");
				String user_sex=rs.getString("sex");
				String user_icon=rs.getString("icon");
				String user_memo=rs.getString("memo");
				if(name!=user_name){
					str="update t_user set name=? where id=?";	
					try {
						PreparedStatement pstmt1=conn.prepareStatement(str);
						pstmt1.setString(1,name);
						pstmt1.setLong(2,id);
						pstmt1.executeUpdate();
						pstmt1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(icon!=user_icon){
					str="update t_user set icon=? where id=?";
					try {
						PreparedStatement pstmt1=conn.prepareStatement(str);
						pstmt1.setString(1,icon);
						pstmt1.setLong(2,id);
						pstmt1.executeUpdate();
						pstmt1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(sex!=user_sex){
					str="update t_user set sex=? where id=?";
					try {
						PreparedStatement pstmt1=conn.prepareStatement(str);
						pstmt1.setString(1,sex);
						pstmt1.setLong(2,id);
						pstmt1.executeUpdate();
						pstmt1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(memo!=user_memo){
					str="update t_user set memo=? where id=?";
					try {
						PreparedStatement pstmt1=conn.prepareStatement(str);
						pstmt1.setString(1,memo);
						pstmt1.setLong(2,id);
						pstmt1.executeUpdate();
						pstmt1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

