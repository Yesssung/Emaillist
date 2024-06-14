package himedia.controller;

import jakarta.servlet.ServletConfig;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

// Context Parameter를 받아와서 자식들에게 물려주는 역할
public abstract class BaseServlet extends HttpServlet {
	protected String dbuser = null;
	protected String dbpass = null;

	// 초기화 method Override
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Context Parameter(-> 모든 Servlet이 공유하는 데이터) 받아오기
		ServletContext context = getServletContext();
		dbuser = context.getInitParameter("dbuser");
		dbpass = context.getInitParameter("dbpass");
	}
	
	
}
