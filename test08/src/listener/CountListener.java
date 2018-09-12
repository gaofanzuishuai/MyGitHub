package listener;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;

import util.MyWebSocket;

public class CountListener implements HttpSessionListener, ServletContextListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("session¥¥Ω®");
		// TODO Auto-generated method stub
		int num = 0;
		ServletContext application = event.getSession().getServletContext();
		if (application.getAttribute("num") != null) {
			num = (Integer) application.getAttribute("num");
		}
		num++;
		application.setAttribute("num", num);
		MyWebSocket.sendMessageAll(String.valueOf(num));
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("session ß–ß");
		int num = 0;
		ServletContext application = event.getSession().getServletContext();
		if (application.getAttribute("num") != null) {
			num = (Integer) application.getAttribute("num");
		}
		num--;
		application.setAttribute("num", num);
		MyWebSocket.sendMessageAll(String.valueOf(num));
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

	}

}
