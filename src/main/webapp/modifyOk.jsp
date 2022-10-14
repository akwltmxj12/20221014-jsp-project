<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto"  class="pend.aurrius.member.MemberDto"  />   
<jsp:setProperty property ="*"  name="dto" />  
<%@ page import="pend.aurrius.member.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%
			MemberDao dao = new MemberDao();
			int modifyFlag = dao.modifyMemberInfo(dto);
			
			
		
		
		
		
		%>
</body>
</html>