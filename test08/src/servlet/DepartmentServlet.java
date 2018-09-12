package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.LifecycleListener;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.glass.ui.Size;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import dao.Department2Project;
import dao.DepartmentDao;
import dao.EmployeeDao;
import net.sf.json.JSONArray;
import unity.Department;
import unity.Employee;
import util.Pagination;
import util.Constant;

public class DepartmentServlet extends HttpServlet {
private static final String path="WEB-INF/department/";
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			} 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		int emp_count = -1;
		if (request.getParameter("emp_count")!=null&&!"".equals(request.getParameter("emp_count"))) {
			emp_count = Integer.parseInt(request.getParameter("emp_count"));
		}
		Department condition = new Department();
		condition.setName(name);
		condition.setEmp_count(emp_count);
		DepartmentDao ed = new DepartmentDao();
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = ed.searchMaxYe(condition);
		Pagination p = new Pagination(ye,count,Constant.EMP_NUM_IN_PAGE,Constant.EMP_NUM_OF_PAGE);
		List<Department> list = ed.search(condition,p.getBegin(),Constant.EMP_NUM_IN_PAGE);
		request.setAttribute("c", condition);
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher(path+"department.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path+"depadd.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		Department dep = new Department();
		dep.setName(name);
		DepartmentDao ed = new DepartmentDao();
		boolean flag = ed.add(dep);
		try {
			response.sendRedirect("DS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			DepartmentDao ed = new DepartmentDao();
			Department dep = ed.search(id);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path+"depupdate.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Department dep = new Department();
		dep.setName(name);
		DepartmentDao ed = new DepartmentDao();
		boolean flag = ed.update(dep, id);
		try {
			response.sendRedirect("DS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		DepartmentDao ed = new DepartmentDao();
		boolean flag = ed.delete(id);
		try {
			response.sendRedirect("DS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
