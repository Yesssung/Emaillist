<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// DataBase 접속 정보 확인
ServletContext context = getServletContext();

String dbuser = context.getInitParameter("dbuser");
String dbpass = context.getInitParameter("dbpass");

// Form 입력 데이터
String firstName = request.getParameter("fn");		// 이름 정보
String lastName = request.getParameter("ln");		// 성 정보
String email = request.getParameter("email");		// email 정보

String dburl = "jdbc:oracle:thin:@localhost:1521:xe";

try {
	// Driver load
	Class.forName("oracle.jdbc.driver.OracleDriver");
	// DataBase 연결
	Connection conn = DriverManager(dburl, dbuser, dbpass);
	// SQL 실행
	String sql = "INSERT INTO emaillist (no, last_name, first_name, email) VALUES (seq_emaillist_pk.nextval, ?, ?, ?)";
	// PrepareadStatement에 전달
	PreparedStatement pstm = conn.prepareStatement(sql);