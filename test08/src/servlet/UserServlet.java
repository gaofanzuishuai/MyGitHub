package servlet;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import unity.CreateMD5;
import unity.RandomNumber;
import unity.User;
import unity.ValidateCode;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type == null) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)) {
			doLogin(request, response);
		} else if ("randomImage".equals(type)) {
			randomImage(request, response);
		}else if("showRegister".equals(type)) {
			showRegister(request, response);
		}else if("register".equals(type)) {
			register(request, response);
		}
	}

	public void register(HttpServletRequest request, HttpServletResponse response) {
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		User user=new User();
		user.setUserName(userName);
		user.setPassWord(CreateMD5.getMd5(passWord+"高凡"));
		UserDao userDao=new UserDao();
		boolean flag=userDao.add(user);
		if(flag) {
			try {
				response.sendRedirect("US");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void showRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/user/register.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void randomImage(HttpServletRequest request, HttpServletResponse response) {
		RandomNumber rn = new RandomNumber();
		try {
			// 设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
			ValidateCode vc = rn.generateImage();
			ServletOutputStream outStream = response.getOutputStream();
			// 输出图像到页面
			ImageIO.write(vc.getImage(), "JPEG", outStream);
			outStream.close();
			request.getSession().setAttribute("rand", vc.getRand());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String random = request.getParameter("random");
		if (request.getSession().getAttribute("rand").equals(random)) {
			User user = new User();
			user.setUserName(userName);
			user.setPassWord(CreateMD5.getMd5(passWord+"高凡"));
			UserDao userDao = new UserDao();
			boolean flag = userDao.search(user);
			if (flag) {
				HttpSession session = request.getSession();
				session.setAttribute("userName", userName);
				Cookie cookie = new Cookie("user", userName);
				cookie.setMaxAge(60);
				response.addCookie(cookie);
				try {
					response.sendRedirect(("index"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("密码错误");
				try {
					response.sendRedirect("US");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			String mes="输入的验证码错误";
			request.setAttribute("mes", mes);
			try {
				request.getRequestDispatcher("WEB-INF/user/user.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String name = "";
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if ("user".equals(cookies[i].getName())) {
				name = cookies[i].getValue();
			}
		}
		request.setAttribute("name", name);
		try {
			request.getRequestDispatcher("WEB-INF/user/user.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
