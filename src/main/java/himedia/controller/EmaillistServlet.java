package himedia.controller;

import java.io.IOException;
import java.util.List;

import himedia.dao.EmaillistDao;
import himedia.dao.EmaillistDaoOracleImpl;
import himedia.dao.EmailVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//	Controller: 요청 처리, 내부 로직 담당
//	- 목록 (GET)
//	- 입력 폼 (GET) ?a=form
//	- 입력 액션 (POST)
//	어노테이션 방식으로 등록
@WebServlet(name = "Emaillist", urlPatterns = "/el")
public class EmaillistServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 a=form이면 입력 폼으로 이동
		String actionName = req.getParameter("a");
		if ("form".equals(actionName)) {
			// 사용자 입력 페이지로 FORWARD
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/form.jsp");
			rd.forward(req, resp);
		} else {
			// 목록 받아오는 부분 -> /el
			EmaillistDao dao = new EmaillistDaoOracleImpl(dbuser, dbpass);
			List<EmailVo> list = dao.getList();

			// list를 요청 객체에 추가
			req.setAttribute("list", list);
			// list 객체를 jsp로 FORWARD
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/index.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 삽입 (INSERT), 수정 (UPDATE), 삭제 (DELETE)
		// a=insert -> 삽입, a=update -> 수정, a=delete -> 삭제
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
				resp.sendRedirect(req.getContextPath() + "/el"); // Redirect (3xx)
			} else {
				System.out.println("INSERT FAILED");
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 입력 중 오류가 발생했습니다.");
			}

		} else if ("delete".equals(actionName)) {
			Long no = Long.valueOf(req.getParameter("no"));
			EmaillistDao dao = new EmaillistDaoOracleImpl(dbuser, dbpass);
			boolean success = dao.delete(no);

			if (success) {
				System.out.println("Delete Success");
				resp.sendRedirect(req.getContextPath() + "/el");
			} else {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 삭제 중 오류가 발생했습니다.");
			}
		} else {
			super.doPost(req, resp);
		}
	}

}