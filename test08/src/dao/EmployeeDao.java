package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.media.jfxmedia.events.NewFrameEvent;
import com.sun.org.apache.bcel.internal.generic.NamedAndTyped;

import unity.Department;
import unity.Employee;

public class EmployeeDao {

	public List<Employee> search() {
		List<Employee> list = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "select * from employee";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setId(rs.getInt("id"));
				emp.setD_id(rs.getInt("d_id"));
				list.add(emp);
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

	public List<Employee> search(int begin, int size) {
		List<Employee> list = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "select * from employee limit " + begin + "," + size + "";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setId(rs.getInt("id"));
				list.add(emp);
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

	public int searchMaxYe() {
		Connection conn = null;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "select count(*) from employee";
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
	public int searchMaxYe(Employee condition) {
		Connection conn = null;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String where = " where 1=1";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += " and e.name='" + condition.getName() + "'";
			}
			if (condition.getSex()!=null&&!condition.getSex().equals("")) {
				where += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge()!=-1) {
				where += " and age=" + condition.getAge();
			}
			if (condition.getDep()!=null&condition.getDep().getId()!=-1) {
				where += " and e.d_id=" + condition.getDep().getId() + "";
			}
			String str = "select count(*) from employee as e left join department as d on e.d_id=d.id" + where;
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

	public Employee search(int id) {
		Employee emp = new Employee();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "select * from employee where id=" + id + "";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				
				Department dep=new Department();
				dep.setId(rs.getInt("d_id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setId(rs.getInt("id"));
				emp.setDep(dep);
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
		return emp;
	}

	public boolean add(Employee emp) {
		boolean flag = false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			String str ="insert into employee(name,sex,age,d_id,photo)values(?,?,?,?,?)";
			PreparedStatement stat=conn.prepareStatement(str);
			stat.setString(1, emp.getName());
			stat.setString(2, emp.getSex());
			stat.setInt(3, emp.getAge());
			stat.setObject(4, emp.getDep().getId());
			stat.setString(5, emp.getPhoto());
			int rs=stat.executeUpdate();
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

	public boolean update(Employee emp, int id) {
		boolean flag = false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "update employee set name='" + emp.getName() + "',sex='" + emp.getSex() + "',age="
					+ emp.getAge() + " where id=" + id + "";
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
			String str = "delete from employee where id=" + id + "";
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

	public boolean deletBatch(String ids) {
		boolean flag = false;
		Connection conn = null;
		int rs = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "delete from employee where id in(" + ids + ")";
			rs = stat.executeUpdate(str);
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

	public boolean updateBatch1(Employee emp, String ids) {
		boolean flag = false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String str = "update employee set name='" + emp.getName() + "',sex='" + emp.getSex() + "',age="
					+ emp.getAge() + " where id in(" + ids + ")";
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

	public int search(String name) {
		Connection conn = null;
		int id=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			String sql = "select id from employee where name='"+name+"'";
			ResultSet rs = stat.executeQuery(sql);
			if (rs.next()) {
				id=rs.getInt("id");
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
		return id;
	}

	public boolean updateBatch2(List<Employee> list) {
		int rs = 0;
		boolean flag = false;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8", "root",
					"123456");
			Statement stat = conn.createStatement();
			for (int index = 0; index < list.size(); index++) {
				String str = "update employee set name='" + list.get(index).getName() + "',sex='"
						+ list.get(index).getSex() + "',age=" + list.get(index).getAge() + " where id in("
						+ list.get(index).getId() + ")";
				rs = stat.executeUpdate(str);
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

	public List<Employee> search(Employee condition,int begin, int size) {
		List<Employee> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?characterEncoding=utf-8",
					"root", "123456");
			Statement stat = conn.createStatement();
			String where = " where 1=1";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += " and e.name='" + condition.getName() + "'";
			}
			if (condition.getSex()!=null&&!condition.getSex().equals("")) {
				where += " and sex='" + condition.getSex() + "'";
			}
			if (condition.getAge()!=-1) {
				where += " and age=" + condition.getAge();
			}
			if (condition.getDep()!=null&&condition.getDep().getId()!=-1) {
				where += " and e.d_id=" + condition.getDep().getId();
			}
			if (condition.getPhoto()!=null&&!condition.getPhoto().equals("")) {
				where += " and photo='" + condition.getPhoto() + "'";
			}
			String str = "select e.*,d.name as depName from employee as e left join department as d on e.d_id=d.id" + where+" limit " + begin + "," + size + "";
			ResultSet rs = stat.executeQuery(str);
			while (rs.next()) {
				Employee emp = new Employee();
				Department dep=new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("depName"));
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSex(rs.getString("sex"));
				emp.setAge(rs.getInt("age"));
				emp.setPhoto(rs.getString("photo"));
				emp.setDep(dep);
				list.add(emp);
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
}
