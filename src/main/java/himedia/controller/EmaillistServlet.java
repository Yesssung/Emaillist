package himedia.controller;

import java.io.IOException;

import java.util.List;

import himedia.dao.EmailVo;
import himedia.dao.EmaillistDao;
import himedia.dao.EmaillistDaoOracleImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Controller: 요청 처리, 내부 로직 담당
//	- 목록 (GET)
// 	- 입력 폼 (GET)
//	- 입력 액션(POST)
// 어노테이션 방식으로 등록
@WebServlet(name = "Emaillist", urlPatterns = "/el")
public class EmaillistServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Parameter a = form 이면 입력 폼으로 이동
		String actionName = req.getParameter("a");
		if("form".equals(actionName)) {
			// 사용자 입력페이지로 FORWARD
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/Emaillist/form.jsp");
			rd.forward(req, resp);
		} else {
			// 목록 받아오는 부분 -> /el
			EmaillistDao dao = new EmaillistDaoOracleImpl(dbuser, dbpass);
			List<EmailVo> list = dao.getList();
			
			// List를 요청 객체에 추가
			req.setAttribute("list", list);
			// List 객체를 jsp로 전달(FORWARD)하기!
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/Emaillist/index.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 삽입(INSERT), 수정(UPDATE), 삭제(DELETE)
		// a = insert -> 삽입 / a = update -> 수정 / a  = delete-> 삭제
		String actionName = req.getParameter("a");
		if ("insert".equals(actionName)) {
			// INSERT 기능 수행
			String firstName = req.getParameter("fn");
			String lastName = req.getParameter("ln");
			String email = req.getParameter("email");
			
			EmailVo vo = new EmailVo();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			
			EmaillistDao dao = new EmaillistDaoOracleImpl(dbuser, dbpass);
			boolean success = dao.insert(vo);
			
			if (success) {
				System.out.println("INSERT SUCCESS");
			} else {
				System.out.println("INSERT FAILED");
			}
			
			resp.sendRedirect(req.getContextPath() + "/el");
		} else {
			super.doPost(req, resp);
		}
	}


	
	
	
	
}
