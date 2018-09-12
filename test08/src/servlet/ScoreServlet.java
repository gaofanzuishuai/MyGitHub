package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.org.glassfish.gmbal.ManagedAttribute;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import dao.ScoreDao;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import unity.Department;
import unity.Employee;
import unity.Project;
import unity.Score;
import util.Constant;
import util.Grade;
import util.Pagination;

public class ScoreServlet extends HttpServlet {
	private static final String path = "WEB-INF/score/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			}else if ("add".equals(type)) {
				add(request, response);
			}else if ("delete".equals(type)) {
				delete(request, response);
			}else if("manage".equals(type)) {
				manage(request, response);
			}else if("input".equals(type)) {
				input(request, response);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void input(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag=false;
		int id=Integer.parseInt(request.getParameter("id"));
		int value=Integer.parseInt(request.getParameter("value"));
		int empId=Integer.parseInt(request.getParameter("empId"));
		int proId=Integer.parseInt(request.getParameter("proId"));
		Score sc=new Score();
		sc.setId(id);
		ScoreDao sDao=new ScoreDao();
		if(id==0) {
			Employee emp=new Employee();
			Project pro=new Project();
			emp.setId(empId);
			pro.setId(proId);
			sc.setEmp(emp);
			sc.setPro(pro);
			sc.setValue(value);
			id=sDao.add(sc);
			sc.setId(id);
			if(id>0) {
				flag=true;
			}
		}else {			
			flag=sDao.update(value,id);
		}
		Score score=sDao.search(id);
		if(flag) {
			JSON json=JSONObject.fromObject(score);
			out.println(json);
		}else {
			out.print(false);
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String proName = request.getParameter("proName");
		String depName = request.getParameter("depName");
		int value=-1;
		if(request.getParameter("value")!=null&&!"".equals(request.getParameter("value"))) {
			value=Integer.parseInt(request.getParameter("value"));
		}
		Department dep = new Department();
		dep.setName(depName);
		Project pro = new Project();
		pro.setName(proName);
		Employee emp = new Employee();
		emp.setName(name);
		emp.setDep(dep);
		Score condition = new Score();
		condition.setEmp(emp);
		condition.setPro(pro);
		condition.setValue(value);

		ScoreDao sd = new ScoreDao();
		DepartmentDao dd = new DepartmentDao();
		ProjectDao pd = new ProjectDao();
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = sd.searchMaxYe(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Score> list = sd.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		List<Score> scoList = sd.search();
		List<Department> depList = dd.search();
		List<Project> proList = pd.search();
		request.setAttribute("depList", depList);
		request.setAttribute("proList", proList);
		request.setAttribute("c", condition);
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		request.setAttribute("scoList", scoList);
		Grade[] grade=Grade.values();
		Grade[] temps=new Grade[grade.length-1];
		for(int i=0;i<grade.length-1;i++) {
			temps[i]=grade[i];
		}
		request.setAttribute("grades",temps);
		try {
			request.getRequestDispatcher(path + "score.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		}

	}
	public void manage(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String proName = request.getParameter("proName");
		String depName = request.getParameter("depName");
		int value=-1;
		if(request.getParameter("value")!=null&&!"".equals(request.getParameter("value"))) {
			value=Integer.parseInt(request.getParameter("value"));
		}
		Department dep = new Department();
		dep.setName(depName);
		Project pro = new Project();
		pro.setName(proName);
		Employee emp = new Employee();
		emp.setName(name);
		emp.setDep(dep);
		Score condition = new Score();
		condition.setEmp(emp);
		condition.setPro(pro);
		condition.setValue(value);

		ScoreDao sd = new ScoreDao();
		DepartmentDao dd = new DepartmentDao();
		ProjectDao pd = new ProjectDao();
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = sd.searchMaxYe(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Score> list = sd.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		List<Score> scoList = sd.search();
		List<Department> depList = dd.search();
		List<Project> proList = pd.search();
		request.setAttribute("depList", depList);
		request.setAttribute("proList", proList);
		request.setAttribute("c", condition);
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		request.setAttribute("scoList", scoList);
		Grade[] grade=Grade.values();
		Grade[] temps=new Grade[grade.length-1];
		for(int i=0;i<grade.length-1;i++) {
			temps[i]=grade[i];
		}
		request.setAttribute("grades",temps);
		try {
			request.getRequestDispatcher(path + "scoremanager.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		}

	}

	public void add(HttpServletRequest request, HttpServletResponse response) {

	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void sss(HttpServletRequest request, HttpServletResponse response) {
		int id = -1;
		if (request.getParameter("id") != null && !"".equals(request.getParameter("id"))) {
			id = Integer.parseInt(request.getParameter("id"));
		}
		int value = -1;
		if (request.getParameter("value") != null && !"".equals(request.getParameter("value"))) {
			value = Integer.parseInt(request.getParameter("value"));
		}
		String grade = request.getParameter("grade");
	}
}
