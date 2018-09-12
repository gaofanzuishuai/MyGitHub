package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import unity.Department;

public class BaiduDao {
	public List<String> search(String content) {
		List<String> list = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "select content from test01 where content like '" + content + "%'";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				list.add(rs.getString("content"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return list;
	}

}
