package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import unity.Department;
import unity.Employee;

public class DepartmentDao {
	public List<Department> search() {
		List<Department> list = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "select * from department";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				Department dep = new Department();
				dep.setName(rs.getString("name"));
				dep.setId(rs.getInt("id"));
				dep.setEmp_count(rs.getInt("emp_count"));
				list.add(dep);
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

	public List<Department> search(int begin, int size) {
		List<Department> list = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "select * from Dempartment limit " + begin + "," + size + "";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("dep_count"));
				list.add(dep);
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

	public int searchMaxYe(Department condition) {
		Connection conn = null;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String where = " where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}
			if (condition.getEmp_count() != -1) {
				where += " and ifnull(emp_count,0)=" + condition.getEmp_count() + "";
			}
			String str = "select count(*) from department" + where;
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

	public List<Department> search(Department condition, int begin, int size) {
		List<Department> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			Statement stat = conn.createStatement();
			String where = " where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}
			if (condition.getEmp_count() != -1) {
				where += " and ifnull(emp_count,0)=" + condition.getEmp_count() + "";
			}
			String str = "select * from department" + where + " limit " + begin + "," + size + "";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				dep.setEmp_count(rs.getInt("emp_count"));
				list.add(dep);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean add(Department dep) {
		boolean flag = false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "insert into department(name)values('" + dep.getName() + "')";
			int rs = stat.executeUpdate(str);
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
		return flag;
	}

	public boolean delete(int id) {

		boolean flag = false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "delete from department where id=" + id + "";
			int rs = stat.executeUpdate(str);
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
		return flag;
	}

	public boolean update(Department dep, int id) {
		boolean flag = false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "update department set name='" + dep.getName() + "' where id=" + id + "";
			int rs = stat.executeUpdate(str);
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
		return flag;
	}

	public Department search(int id) {
		Department dep = new Department();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "select *from department where id=" + id + "";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				dep.setName(rs.getString("name"));
				dep.setId(rs.getInt("id"));
				dep.setEmp_count(rs.getInt("emp_count"));
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
		return dep;
	}
}
