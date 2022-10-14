<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pend.aurrius.member.*" %>     



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
		<%
			MemberDao dao = new MemberDao();
			String id = (String) session.getAttribute("memberId");
			MemberDto dto = dao.getMemberInfo(id);	
		%>
		<h3>회원 정보 수정</h3>
		<hr>
		<form action="modifyOk.jsp" method="post">
		아이디 : <input type="text" name="id" value="<%= dto.getId() %>" readonly="readonly"><br><br>
		비밀번호 : <input type="password" name="pw" value="<%= dto.getPw() %>"><br><br>
		이름 : <input type="text" name="username" value="<%= dto.getname() %>"><br><br>
		이메일 : <input type="text" name="email" value="<%= dto.getEmail() %>"><br><br>
		가입일 :  <input type="text" name="signuptime" value="<%= dto.getSignuptime() %>" readonly="readonly">
		<input type="submit" value="수정완료" >&nbspl;&nbsp;&nbsp;
		<input type="submit" value="수정취소" onclick="javascript:window.location='join.jsp'">
		</form>
</body>
</html>