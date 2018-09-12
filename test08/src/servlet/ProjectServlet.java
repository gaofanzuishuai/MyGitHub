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

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import net.sf.json.JSONArray;
import unity.Department;
import unity.Employee;
import unity.Project;
import util.Pagination;
import util.Constant;

public class ProjectServlet extends HttpServlet {
	private static final String path = "WEB-INF/project/";

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
		Project condition = new Project();
		condition.setName(name);
		ProjectDao pd = new ProjectDao();
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = pd.searchMaxYe(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Project> list = pd.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		List<Project> proList=pd.search();
		request.setAttribute("c", condition);
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		request.setAttribute("proList", proList);
		try {
			request.getRequestDispatcher(path + "project.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		}

	}

	public void show(HttpServletRequest request, HttpServletResponse response) {
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		ProjectDao pd = new ProjectDao();
		int count = pd.searchMaxYe(null);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Project> list = pd.search(p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher(path + "project.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		}

	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path + "proadd.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		Project pro = new Project();
		pro.setName(name);
		ProjectDao pd = new ProjectDao();
		boolean flag = pd.add(pro);
		try {
			response.sendRedirect("PS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ProjectDao pd = new ProjectDao();
			Project pro = pd.search(id);
			request.setAttribute("pro", pro);
			request.getRequestDispatcher(path + "proupdate.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generatpd catch block
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Project pro = new Project();
		pro.setName(name);
		ProjectDao pd = new ProjectDao();
		boolean flag = pd.update(pro, id);
		try {
			response.sendRedirect("PS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		ProjectDao pd = new ProjectDao();
		boolean flag = pd.delete(id);
		try {
			response.sendRedirect("PS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
