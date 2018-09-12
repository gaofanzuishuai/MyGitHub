package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.crypto.provider.RSACipher;

import unity.User;

public class UserDao {
	public boolean search(User user) {
		boolean flag=false;
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstat=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","123456");
			pstat=conn.prepareStatement("select * from user where username=? and password=?");
			pstat.setString(1, user.getUserName());
			pstat.setString(2, user.getPassWord());
			rs=pstat.executeQuery();
			if(rs.next()) {
				flag=true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstat.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;		
	}

	public boolean add(User user) {
		boolean flag=false;
		Connection conn=null;
		int rs=-1;
		PreparedStatement pstat=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8","root","123456");
			pstat=conn.prepareStatement("insert into user(username,password)values(?,?)");
			pstat.setString(1, user.getUserName());
			pstat.setString(2, user.getPassWord());
			rs=pstat.executeUpdate();
			if(rs>0) {
				flag=true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

}
