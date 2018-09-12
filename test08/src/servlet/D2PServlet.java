package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.print.attribute.ResolutionSyntax;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.fastinfoset.algorithm.FloatEncodingAlgorithm;

import dao.Department2Project;
import dao.DepartmentDao;
import unity.Department;
import unity.Project;
import util.Constant;
import util.Pagination;

public class D2PServlet extends HttpServlet {

	private static final String path = "WEB-INF/d2p/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			} else if ("search2".equals(type)) {
				search2(request, response);
			} else if ("add2".equals(type)) {
				add2(request, response);
			} else if ("delete2".equals(type)) {
				delete2(request, response);
			} else if ("search3".equals(type)) {
				search3(request, response);
			} else if ("deleteBatch".equals(type)) {
				deleteBatch(request, response);
			} else if ("addBatch".equals(type)) {
				addBatch(request, response);
			} else if ("search4".equals(type)) {
				search4(request, response);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteBatch(HttpServletRequest request, HttpServletResponse response) {
		String str1 = request.getParameter("depId");
		String str2 = request.getParameter("proIdList");
		int depId = Integer.parseInt(str1);
		String[] proIdList = str2.split(",");
		Department2Project d2p = new Department2Project();
		boolean flag = d2p.deleteBatch(depId, proIdList);
		try {
			PrintWriter out = response.getWriter();
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addBatch(HttpServletRequest request, HttpServletResponse response) {
		String str1 = request.getParameter("depId");
		String str2 = request.getParameter("proIdList");
		int depId = Integer.parseInt(str1);
		String[] proIdList = str2.split(",");
		Department2Project d2p = new Department2Project();
		boolean flag = d2p.addBatch(depId, proIdList);
		try {
			PrintWriter out = response.getWriter();
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteBach(HttpServletRequest request, HttpServletResponse response) {

	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		String str = request.getParameter("depId");
		int depId = Integer.parseInt(str);
		Department2Project d2p = new Department2Project();
		DepartmentDao dd = new DepartmentDao();
		Department dep = dd.search(depId);
		List<Project> proList = d2p.search(depId);
		List<Project> noList = d2p.searchNoProject(depId);
		request.setAttribute("dep", dep);
		request.setAttribute("proList", proList);
		request.setAttribute("noList", noList);
		try {
			request.getRequestDispatcher(path + "dep2pro.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search2(HttpServletRequest request, HttpServletResponse response) {
		String str = request.getParameter("depId");
		int depId = Integer.parseInt(str);
		Department2Project d2p = new Department2Project();
		DepartmentDao dd = new DepartmentDao();
		Department dep = dd.search(depId);
		List<Project> proList = d2p.search(depId);
		List<Project> noList = d2p.searchNoProject(depId);
		request.setAttribute("dep", dep);
		request.setAttribute("proList", proList);
		request.setAttribute("noList", noList);
		try {
			request.getRequestDispatcher(path + "dep2pro.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search3(HttpServletRequest request, HttpServletResponse response) {
		String str = request.getParameter("depId");
		int depId = Integer.parseInt(str);
		Department2Project d2p = new Department2Project();
		DepartmentDao dd = new DepartmentDao();
		Department dep = dd.search(depId);
		List<Project> proList = d2p.search(depId);
		List<Project> noList = d2p.searchNoProject(depId);
		request.setAttribute("dep", dep);
		request.setAttribute("proList", proList);
		request.setAttribute("noList", noList);
		try {
			request.getRequestDispatcher(path + "dep2pro2.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search4(HttpServletRequest request, HttpServletResponse response) {
		String str = request.getParameter("depId");
		int depId = Integer.parseInt(str);
		Department2Project d2p = new Department2Project();
		DepartmentDao dd = new DepartmentDao();
		Department dep = dd.search(depId);
		List<Project> proList = d2p.search(depId);
		List<Project> noList = d2p.searchNoProject(depId);
		request.setAttribute("dep", dep);
		request.setAttribute("proList", proList);
		request.setAttribute("noList", noList);
		try {
			request.getRequestDispatcher(path + "dep2pro3.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void search5(HttpServletRequest request, HttpServletResponse response) {
		String str = request.getParameter("depId");
		int depId = Integer.parseInt(str);
		Department2Project d2p = new Department2Project();
		DepartmentDao dd = new DepartmentDao();
		Department dep = dd.search(depId);
		List<Project> proList = d2p.search(depId);
		List<Project> noList = d2p.searchNoProject(depId);
		try {
			PrintWriter out=response.getWriter();
			out.print(proList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		String str1 = request.getParameter("depId");
		String str2 = request.getParameter("proId");
		int depId = Integer.parseInt(str1);
		int proId = Integer.parseInt(str2);
		Department2Project d2p = new Department2Project();
		boolean flag = d2p.add(depId, proId);
		try {
			response.sendRedirect("DPS?depId=" + depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add2(HttpServletRequest request, HttpServletResponse response) {

		String str1 = request.getParameter("depId");
		String str2 = request.getParameter("proId");
		int depId = Integer.parseInt(str1);
		int proId = Integer.parseInt(str2);
		Department2Project d2p = new Department2Project();
		boolean flag = d2p.add(depId, proId);
		try {
			PrintWriter out = response.getWriter();
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String str1 = request.getParameter("depId");
		String str2 = request.getParameter("proId");
		int depId = Integer.parseInt(str1);
		int proId = Integer.parseInt(str2);
		Department2Project d2p = new Department2Project();
		boolean flag = d2p.delete(depId, proId);
		try {
			response.sendRedirect("DPS?depId=" + depId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete2(HttpServletRequest request, HttpServletResponse response) {
		String str1 = request.getParameter("depId");
		String str2 = request.getParameter("proId");
		int depId = Integer.parseInt(str1);
		int proId = Integer.parseInt(str2);
		Department2Project d2p = new Department2Project();
		boolean flag = d2p.delete(depId, proId);
		try {
			PrintWriter out = response.getWriter();
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
