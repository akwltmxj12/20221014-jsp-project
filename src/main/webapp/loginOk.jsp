<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pend.aurrius.member.MemberDao" %> 
<jsp:useBean id="dao"  class="pend.aurrius.member.MemberDao" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 확인</title>
</head>
<body>
		<h3>로그인 확인</h3>
		<hr>
		<%
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDao da = new MemberDao();
			int userFlag = dao.userCheck(id, pw);
			
			// userFlag값이 1이면 아이디,비밀번호 모두 일치 -> 로그인 성공
			//  userFlag값이 0이면 아이디조차 db에 존재하지않음 -> 비회원
			//  userFlag값이 2이면 아이디는 db에 존재하지만 사용자가 입력한 비번이 틀림 -> 회원이지만 비번 다시확인
			
			if(userFlag == 0) {
				out.println("입력하신 아이디가 존재하지 않습니다. 다시 확인해 주세요.");
			} else if(userFlag == 2) {
				out.println("입력하신 비밀번호가 틀립니다. 다시 확인해 주세요.");
			} else { //로그인성공
				
				session.setAttribute("memberId", id);
				session.setAttribute("validmember", "yes");
				
				out.println(id + "님 반갑습니다. 로그인에 성공하셨씁니다.");
			}
		
		%>
		<a href="main.jsp">메인화면으로 가기</a>
</body>
</html>