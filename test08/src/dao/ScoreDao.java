package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sun.org.apache.xml.internal.security.Init;

import unity.Department;
import unity.Employee;
import unity.Project;
import unity.Score;
import util.Grade;

public class ScoreDao {
	public List<Score> search() {
		Connection conn =null;
		List<Score> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from v_vscore");
			while (rs.next()) {
				Score score = new Score();
				score.setId(rs.getInt("s_id"));
				score.setValue((Integer) rs.getObject("value"));
				Grade g=Grade.getGrade(rs.getString("grade"));
				score.setGrade(g);
				score.setValue((Integer) rs.getObject("value"));
				Employee emp = new Employee();
				emp.setName(rs.getString("e_name"));
				emp.setId(rs.getInt("e_id"));
				Department dep = new Department();
				dep.setName(rs.getString("d_name"));
				dep.setId(rs.getInt("d_id"));
				emp.setDep(dep);
				Project pro = new Project();
				pro.setName(rs.getString("p_name"));
				pro.setId(rs.getInt("p_id"));
				score.setEmp(emp);
				score.setPro(pro);
				list.add(score);
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;

	}
	public Score search(int id) {
		Connection conn =null;
		Score score=new Score();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("select * from v_vscore where s_id="+id);
			while (rs.next()) {
				score.setId(rs.getInt("s_id"));
				score.setValue((Integer) rs.getObject("value"));
				Grade g=Grade.getGrade(rs.getString("grade"));
				score.setGrade(g);
				score.setValue((Integer) rs.getObject("value"));
				Employee emp = new Employee();
				emp.setName(rs.getString("e_name"));
				emp.setId(rs.getInt("e_id"));
				Department dep = new Department();
				dep.setName(rs.getString("d_name"));
				dep.setId(rs.getInt("d_id"));
				emp.setDep(dep);
				Project pro = new Project();
				pro.setName(rs.getString("p_name"));
				pro.setId(rs.getInt("p_id"));
				score.setEmp(emp);
				score.setPro(pro);
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return score;

	}
	public int add(Score sc) {
		int id=0;
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			Statement stat = conn.createStatement();
			String sql = "insert into score(e_id,p_id,value) values(" + sc.getEmp().getId() + "," + sc.getPro().getId()
					+ "," + sc.getValue() + ") ";
			int i = stat.executeUpdate(sql);
			stat.close();
			String str="select LAST_INSERT_ID()";
			Statement stat1=conn.createStatement();
			ResultSet rs=stat1.executeQuery(str);
			if(rs.next()) {
				id=rs.getInt(1);
			}
			conn.close();
			stat.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}

	public boolean update(int value,int id) {
		boolean flag = false;
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			Statement stat = conn.createStatement();
			int i = stat.executeUpdate("update score set value=" + value + " where id="+id);
			if (i > 0) {
				flag = true;
			}
			conn.close();
			stat.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	public List<Score> search(Score condition,int begin,int size) {
		List<Score> list = new ArrayList<Score>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			// 4:建立Statement sql语句执行器
			stat = conn.createStatement();
			// 5：执行sql语句并得到结果
			String where = "where 1=1";
			if (condition.getEmp()!=null&&condition.getEmp().getName()!=null&&!condition.getEmp().getName().equals("")) {
				where += " and e_name ='" + condition.getEmp().getName() + "' ";
			}
			if (condition.getEmp()!=null&&condition.getEmp().getDep()!=null&&condition.getEmp().getDep().getName()!=null&&!condition.getEmp().getDep().getName().equals("")) {
				where += " and d_name ='" + condition.getEmp().getDep().getName() + "' ";
			}
			if (condition.getPro()!=null&&condition.getPro().getName()!=null&&!condition.getPro().getName().equals("")) {
				where += " and p_name ='" + condition.getPro().getName() + "'";
			}
			String sql = "select * from v_vscore " + where+" limit " + begin + "," + size + "";
			rs = stat.executeQuery(sql);
			// 6:对结果集进行处理
			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("s_id"));

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));

				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_Name"));

				emp.setDep(dep);
				sc.setEmp(emp);

				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));
				sc.setPro(pro);

				sc.setValue((Integer) rs.getObject("value"));
				Grade g=Grade.getGrade(rs.getString("grade"));
				sc.setGrade(g);
				list.add(sc);
			}
			conn.close();
			stat.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
	public int searchMaxYe(Score condition) {
		Connection conn = null;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String where = " where 1=1";
			if (condition.getEmp()!=null&&condition.getEmp().getName()!=null&&!condition.getEmp().getName().equals("")) {
				where += " and e_name ='" + condition.getEmp().getName() + "' ";
		    }
			if (condition.getEmp()!=null&&condition.getEmp().getDep()!=null&&condition.getEmp().getDep().getName()!=null&&!condition.getEmp().getDep().getName().equals("")) {
				where += " and d_name ='" + condition.getEmp().getDep().getName() + "' ";
			}
			if (condition.getPro()!=null&&condition.getPro().getName()!=null&&!condition.getPro().getName().equals("")) {
				where += " and p_name ='" + condition.getPro().getName()
						+"'";
			}
			String str = "select count(*) from v_vscore" + where;
			ResultSet rs = stat.executeQuery(str);
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}
}
