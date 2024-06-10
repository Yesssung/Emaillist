<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ServletContext context = getServletContext();

String dbuser = context.getInitParameter("dbuser");
String dbpass = context.getInitParameter("dbpass");

String no = request.getParameter("no");	

String dburl = "jdbc:oracle:thin:@localhost:1521:xe";

try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
	String sql = "DELETE FROM emaillist WHERE no= ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	
	pstmt.setString(1, no);

	
	int deletedCount = pstmt.executeUpdate(); 	// 영향 받은 레코드 카운트
	
	if (deletedCount == 1) {					// DELETE 성공
 		// 다른 페이지로 Redirect : 3xx
 		response.sendRedirect(request.getContextPath());	
	} else {
		%>
		<h1> Error </h1>
		<p> 아이템을 삭제하지 못했습니다.</p>
		<%
	}
	// 자원 정리
	
	pstmt.close();
	conn.close();
} catch (Exception e) {
	throw e;
}
%>
