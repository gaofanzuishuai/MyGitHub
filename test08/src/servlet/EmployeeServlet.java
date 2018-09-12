package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.LifecycleListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.glass.ui.Size;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import dao.DepartmentDao;
import dao.EmployeeDao;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import unity.Department;
import unity.Employee;
import util.Pagination;
import util.Constant;

public class EmployeeServlet extends HttpServlet {
	private static final String path = "WEB-INF/employee/";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
			try {
				request.setCharacterEncoding("utf-8");
				String type = request.getParameter("type");
				Class clazz=this.getClass();
				try {
					Method method=clazz.getDeclaredMethod(type, HttpServletRequest.class,HttpServletResponse.class);
					method.invoke(this, request,response);
				
				
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/*if (type == null) {
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
			} else if ("deleteBatch".equals(type)) {
				deleteBatch(request, response);
			} else if ("showUpdateBatch1".equals(type)) {
				showUpdateBatch1(request, response);
			} else if ("updateBatch1".equals(type)) {
				updateBatch1(request, response);
			} else if ("showUpdateBatch2".equals(type)) {
				showUpdateBatch2(request, response);
			} else if ("updateBatch2".equals(type)) {
				updateBatch2(request, response);
			} else if ("updateBatch3".equals(type)) {
				updateBatch3(request, response);
			} else if ("updateBatch4".equals(type)) {
				updateBatch4(request, response);
			} else if ("upload".equals(type)) {
				upload(request, response);
			}else if ("deletePicture".equals(type)) {
				deletePicture(request, response);
			}*/
		
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		Employee condition = new Employee();
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = -1;
		if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
			age = Integer.parseInt(request.getParameter("age"));
		}
		int depId = -1;
		if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
			depId = Integer.parseInt(request.getParameter("depId"));
		}
		Department dep = new Department();
		dep.setId(depId);
		condition.setName(name);
		condition.setSex(sex);
		condition.setAge(age);
		condition.setDep(dep);
		EmployeeDao ed = new EmployeeDao();
		DepartmentDao dd = new DepartmentDao();
		List<Department> deplist = dd.search();
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		int count = ed.searchMaxYe(condition);
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Employee> list = ed.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		request.setAttribute("c", condition);
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		request.setAttribute("depList", deplist);
		try {
			request.getRequestDispatcher(path + "buhuiadage.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void show(HttpServletRequest request, HttpServletResponse response) {
		int ye = 1;
		if (request.getParameter("ye") != null) {
			ye = Integer.parseInt(request.getParameter("ye"));
		}
		EmployeeDao ed = new EmployeeDao();
		int count = ed.searchMaxYe();
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Employee> list = ed.search(p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		request.setAttribute("p", p);
		request.setAttribute("list", list);
		try {
			request.getRequestDispatcher(path + "buhuiadage.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		DepartmentDao dd = new DepartmentDao();
		List<Department> depList = dd.search();
		request.setAttribute("depList", depList);
		try {
			request.getRequestDispatcher(path + "add.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String photo=request.getParameter("photo1");
		System.out.println(photo);
		Integer depId = null;
		if (!"".equals(request.getParameter("depId"))) {
			depId = Integer.parseInt(request.getParameter("depId"));
		}
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSex(sex);
		emp.setAge(Integer.parseInt(age));
		Department dep = new Department();
		dep.setId(depId);
		emp.setDep(dep);
		emp.setPhoto(photo);
		EmployeeDao ed = new EmployeeDao();
		boolean flag = ed.add(emp);
		try {
			response.sendRedirect("ES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			String name = null;
			String sex = null;
			int age = 0;
			int depId = 0;
			String photoName = null;
			String path = "G:/test08";
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("name")) {
					name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("sex")) {
					sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
				} else if (item.getFieldName().equals("age")) {
					age = Integer.parseInt(new String(item.getString()));
				} else if (item.getFieldName().equals("depId")) {
					depId = Integer.parseInt(new String(item.getString()));
				} else if (item.getFieldName().equals("photo")) {
					UUID uuid = UUID.randomUUID();
					photoName = uuid.toString() + item.getName().substring(item.getName().lastIndexOf("."));
					File myFile = new File(path, photoName);
					item.write(myFile);
				}
			}
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setPhoto(photoName);
			Department dep = new Department();
			dep.setId(depId);
			emp.setDep(dep);
			EmployeeDao ed = new EmployeeDao();
			boolean flag = ed.add(emp);
			response.sendRedirect("ES");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			String photoName ="";
			String path = "G:/test08";
			//List<String> picList=null;
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("myFile")) {
					UUID uuid = UUID.randomUUID();
					photoName = uuid.toString() + item.getName().substring(item.getName().lastIndexOf("."));
					//picList.add(photoName);
					File myFile = new File(path, photoName);
					item.write(myFile);
				}
			}
			PrintWriter out = response.getWriter();
			out.print(photoName);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deletePicture(HttpServletRequest request, HttpServletResponse response) {	
		try {
			boolean flag=false;
			String name=request.getParameter("name");
			String path="G:/test08";
			File file=new File(path,name);
			if(file.exists()) {
				flag=file.delete();
			}
			PrintWriter out=response.getWriter();
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		DepartmentDao dd = new DepartmentDao();
		List<Department> depList = dd.search();
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDao ed = new EmployeeDao();
		Employee emp = ed.search(id);
		request.setAttribute("emp", emp);
		request.setAttribute("depList", depList);
		try {

			request.getRequestDispatcher(path + "update.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSex(sex);
		emp.setAge(Integer.parseInt(age));
		EmployeeDao ed = new EmployeeDao();
		boolean flag = ed.update(emp, id);
		try {
			response.sendRedirect("ES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		EmployeeDao ed = new EmployeeDao();
		boolean flag = ed.delete(id);
		try {
			response.sendRedirect("ES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		EmployeeDao ed = new EmployeeDao();
		boolean flag = ed.deletBatch(ids);
		try {
			response.sendRedirect("ES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdateBatch1(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			EmployeeDao ed = new EmployeeDao();
			List<Employee> list = new ArrayList<>();
			// list = ed.search(ids);
			request.setAttribute("ids", ids);
			request.setAttribute("emp", list.get(0));
			request.getRequestDispatcher(path + "updateBatch1.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBatch1(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSex(sex);
		emp.setAge(Integer.parseInt(age));
		EmployeeDao ed = new EmployeeDao();
		boolean flag = ed.updateBatch1(emp, ids);
		try {
			response.sendRedirect("ES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdateBatch2(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		EmployeeDao ed = new EmployeeDao();
		// List<Employee> list = ed.search(ids);
		// request.setAttribute("emps", list);
		try {
			request.getRequestDispatcher(path + "updateBatch2.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBatch2(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> list = new ArrayList<>();
		String emps = request.getParameter("emps");
		String[] str1 = emps.split(";");
		for (int i = 0; i < str1.length; i++) {
			Employee emp = new Employee();
			String[] str2 = str1[i].split(",");
			emp.setId(Integer.parseInt(str2[0]));
			emp.setName(str2[1]);
			emp.setSex(str2[2]);
			emp.setAge(Integer.parseInt(str2[3]));
			list.add(emp);
		}
		EmployeeDao ed = new EmployeeDao();
		ed.updateBatch2(list);
		try {
			response.sendRedirect("ES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBatch3(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> list = new ArrayList<>();
		String emps1 = request.getParameter("emps");
		String[] str1 = emps1.split(";");
		for (int i = 0; i < str1.length; i++) {
			Employee emp = new Employee();
			String[] str2 = str1[i].split(",");
			emp.setId(Integer.parseInt(str2[0]));
			emp.setName(str2[1]);
			emp.setSex(str2[2]);
			emp.setAge(Integer.parseInt(str2[3]));
			list.add(emp);
		}
		EmployeeDao ed = new EmployeeDao();
		ed.updateBatch2(list);
		try {
			response.sendRedirect("ES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBatch4(HttpServletRequest request, HttpServletResponse response) {
		String emps = request.getParameter("emps");
		JSONArray jsonArray = JSONArray.fromObject(emps);
		List<Employee> list = (List<Employee>) jsonArray.toCollection(jsonArray, Employee.class);
		EmployeeDao ed = new EmployeeDao();
		ed.updateBatch2(list);
		try {
			response.sendRedirect("ES");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
