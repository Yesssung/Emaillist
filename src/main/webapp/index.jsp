<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// DB 접속 정보를 Context Parameter로부터 받아오기
ServletContext context = getServletContext();
String dbuser = context.getInitParameter("dbuser");
String dbpass = context.getInitParameter("dbpass");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일링 리스트:메인</title>
</head>
<body>
	<h1>메일링 리스트</h1>
	<h3>Model 1 방식</h3>
<%
String dburl = "jdbc:oracle:thin:@localhost:1521:xe";

try {
	// Driver load
	Class.forName("oracle.jdbc.driver.OracleDriver");
	// DataBase 연결
	Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
	// Statement 생성
	Statement stmt = conn.createStatement();
	// SQL 실행
	String sql = "SELECT * FROM emaillist ORDER BY created_at DESC";
	ResultSet rs = stmt.executeQuery(sql);
	
	while(rs.next()) {
%>
	<!-- 리스트 -->
	<!-- vo 객체의 getter를 이용, 리스트를 표시 -->
	<table border="1" cellpadding="5" cellspacing="2">
		<tr>
			<th>성</th>
			<td><%= rs.getString("last_name") %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%= rs.getString("first_name") %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%= rs.getString("email") %></td>
		</tr>
	</table>
	<br />
<!-- /End -->
<%
	}
	
 	rs.close();
	stmt.close();
	conn.close();
} catch (ClassNotFoundException e) {
	throw new ClassNotFoundException("드라이버를 찾을 수 없습니다.");
} catch (Exception e) {
	throw e;
}
%>

	<p>
	<!-- ContextPath를 받아와서 form.jsp에 링크 연결하기 -->
		<a href="<%= request.getContextPath() %>/form.jsp">추가 이메일 등록</a>
	</p>

</body>
</html>