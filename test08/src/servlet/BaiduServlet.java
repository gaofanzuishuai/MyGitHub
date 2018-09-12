package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BaiduDao;
import javafx.scene.web.PromptData;

public class BaiduServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if ("prompt".equals(type)) {
			prompt(request, response);
		}
	}

	private void prompt(HttpServletRequest request, HttpServletResponse response) {
		try {
			String content = request.getParameter("content");
			PrintWriter out = response.getWriter();
			BaiduDao baiduDao = new BaiduDao();
			List<String> list = baiduDao.search(content);
			String str = "";
			for (int i = 0; i < list.size(); i++) {
				str += list.get(i) + "<br />";
			}
			if (content.equals("")) {
				out.print("");
			} else {
				out.print(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
