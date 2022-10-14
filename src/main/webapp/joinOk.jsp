<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pend.aurrius.member.MemberDao" %>    
<%@ page import="pend.aurrius.member.MemberDto" %>    
<jsp:useBean id="dto"  class="pend.aurrius.member.MemberDto" />
<jsp:setProperty property = "*" name="dto" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//		String id = request.getParameter("id");		
			//	String pw = request.getParameter("pw");		
		//		String name = request.getParameter("name");		
		//		String email = request.getParameter("email");		
				
				//MemberDto dto = new MemberDto();
			//	dto.setId(id);
			//	dto.setPw(pw);
			//	dto.setUsername(name);
			//	dto.setEmail(email);
				
							
				MemberDao dao = new MemberDao();
				
				int idResult = dao.idCheck(dto.getId());
				
				if(idResult == 1) {
					out.println();
				}else{
					int joinCheck = dao.insertMember(dto);	// 하나씩 빼서 하는걸 한번에 압축했다.		
					if(joinCheck == 1) {
						out.println("회원 가입 성공! 가입을 축하드립니다.");
					} else {
						out.println("회원 가입 실패! 가입사항을 다시 확인해주세요.");
					}
				}
		%>


</body>
</html>