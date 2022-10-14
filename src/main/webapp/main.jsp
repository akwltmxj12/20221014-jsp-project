<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<% 
			if(session.getAttribute("validMember") == null) {	//	 로그인 안한 상태				
		%>
			<a href="login.jsp">로그인으로 가기</a>
		<%
				} else {
				String id = (String) session.getAttribute("memberId");		// 로그인 로딩중		
				out.println(id + "님 로그인 중");					
		%>
			<br><br>	
			<a href="logout.jsp">로그아웃</a>
			<br><br>	
			<a href="modify.jsp">회원정보수정</a>			<!--로그인 된상태라면 이 란이 보인다.-->
		<%		
			}		
		%>
</body>
</html>