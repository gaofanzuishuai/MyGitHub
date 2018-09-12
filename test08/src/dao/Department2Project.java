package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.Select;

import jdk.nashorn.internal.ir.Flags;
import unity.Department;
import unity.Project;

public class Department2Project {
	public List<Project> search(int depId) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEndoding=utf-8", "root",
					"123456");
			String sql = "select * from v_view where d_id=" + depId;
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				list.add(pro);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stat.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public List<Project> searchNoProject(int depId) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			stat = conn.createStatement();
			String sql = "select * from project where id not in(select p_id from v_view where d_id=" + depId + ")";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stat.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean add(int depId, int proId) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			stat = conn.createStatement();
			String sql = "insert into rtable(d_id,p_id)values(" + depId + "," + proId + ")";
			rs = stat.executeUpdate(sql);
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean delete(int depId, int proId) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			stat = conn.createStatement();
			String sql = "delete from rtable where d_id=" + depId + " and p_id=" + proId;
			rs = stat.executeUpdate(sql);
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean addBatch(int depId, String[] proIdList) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			stat = conn.createStatement();
			for (int i = 0; i < proIdList.length; i++) {
				int proId=Integer.parseInt(proIdList[i]);
				String sql = "insert into rtable(d_id,p_id)values(" + depId + "," + proId + ")";
				rs = stat.executeUpdate(sql);
			}
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	public boolean deleteBatch(int depId, String[] proIdList) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			stat = conn.createStatement();
			for (int i = 0; i < proIdList.length; i++) {
				int proId=Integer.parseInt(proIdList[i]);
				String sql = "delete from rtable where d_id="+depId+" and p_id="+proId;
				rs = stat.executeUpdate(sql);
			}
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
}
