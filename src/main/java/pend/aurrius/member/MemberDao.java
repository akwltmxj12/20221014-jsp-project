package pend.aurrius.member;

import java.sql.*;


public class MemberDao {		
	
	
	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/serverdb";
	static String user = "root";
	static String pass = "12345";
	
	//static를 쓰는건 위에 4개는 상수로써도 무방하기때문이다. 변하지않는다. 상수 선언
	
	public int insertMember(MemberDto dto) {			// => 메서드,  같은 패키지에있다면 경로는생략된다.
		
		String id = dto.getId();
		String pw = dto.getPw();
		String name = dto.getUsername();
		String email = dto.getEmail();
		
		
		
		String sql = "INSERT INTO members(id, pw, username, email) VALUES('"+id+" ', '"+pw+"' , '"+name+"' , '"+email+"')";
	
		Connection conn = null;				// connection 생성
		Statement stmt  =null;				// Statement 생성
		int dbFlag = 0;
		
		
		try {											//예외처리를 안하면 자바에서는 오류가 발생하여 아예 호출이안된다.
			Class.forName(driverName);	// 밑에꺼랑 다르지만 한번에 예외처리를 할수있다.
			conn = DriverManager.getConnection(url, user, pass);
			
			stmt = conn.createStatement();
			dbFlag = stmt.executeUpdate(sql); // 성공하면 1이 반환
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {					//자바에서는 sql소속은 전부 예외처리해야한다.
	    	try {
	    		if(stmt != null) {	
						stmt.close();
	    		}
	    		if(conn != null) {
						conn.close();
	    		}	
	    		} catch (SQLException e) {		
				e.printStackTrace();
			}
		}		
			return dbFlag;	// 성공여부 값 반환,  1이면 성공이다.
	}
	

	public int idCheck(String id) { // 아이디의 중복 가입 여부(같은 아이디가 이미 존재하는지 여부 확인)
			
			int idFlag = 0;
			
			Connection conn = null;	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql="SELECT * FROM members WHERE id=?";
			
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, pass);
				pstmt = conn.prepareStatement(sql);			
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();		// 불러온 레코드가 1개아니면 없거나
				
				if(rs.next()) {	// true 어차피 1번만 돌아가면되는 if문인데 1개를 불러왓으므로 참이된다
					idFlag=1; // 이미 가입하려는 아이디가 존재함
				} else {
					idFlag=0; // 가입하려는 아이디와 같은 아이디가 없으므로 가입가능
				}
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
			
			return idFlag;
						
	}
	public int userCheck(String id, String pw) {
		
			int userFlag = 0;
			
			Connection conn = null;	
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			String sql="SELECT pw FROM members WHERE id=?";
			
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, pass);
				pstmt = conn.prepareStatement(sql);			
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();		
				
				
				
				if(rs.next()) {	
					String dbPw = rs.getString("pw");
					if(dbPw.equals(pw) ) {
						userFlag=1;		// 아이디가 존재하므로 회원임
					} else {				
						userFlag=2;			//아이디는 존재하지만 비밀번호가 틀림
					} 
					} else {
						userFlag=0;		// 아이디가 존재하지 않으므로 비회원임
					}
				
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt !=null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
			
			return userFlag;		
			// 1이 반한되면 로그인 성공, 0이 반한되면 회원아님(id존재하지않음), 2가 반한되면 비밀번호만 틀림
	}
	
	public MemberDto getMemberInfo(String id) {	// 아이디로 테이블을 검색하여 해당 아이디의 모든정보를 반환
		
		MemberDto dto = null;
		
		Connection conn = null;	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql="SELECT pw FROM members WHERE id=?";
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, pass);
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();		
			
			
			
			if(rs.next()) {	
				
				dto = new MemberDto();
				
				String dbID = rs.getString("id");
				String dbPw = rs.getString("pw");
				String dbUsername = rs.getString("name");
				String dbEmail = rs.getString("email");
				String dbSignuptime = rs.getString("signuptime");
				
				dto.setId(dbID);
				dto.setId(dbPw);
				dto.setId(dbUsername);
				dto.setId(dbEmail);
				dto.setId(dbSignuptime);
				}	// dto를 호출하여 메소드로 하나씩 뽑아내서 setter로 dto에 넣는다.
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt !=null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		
		return dto;
	}
	
	
	public int modifyMemberInfo(MemberDto dto) {  //modify.jsp에서 보내준 수정된 회원정보를 db에 다시넣기(업데이트)
		int updateFlag =0;
		
		Connection conn = null;	
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE members SET pw=?, Username=?, email=? WHERE id=?";
		// 업데이트 다음에 테이블 들어간다. 그리고 필드 들어간다. 해석하면 타이거를 찾아-> 필드 내용수정
		try {											
			Class.forName(driverName);	
			conn = DriverManager.getConnection(url, user, pass);
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getUsername());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getId());
			
			updateFlag = pstmt.executeUpdate();	//수정 성공이면 1이 반환, 아니면 다른 값 반환
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {				
	    	try {
	    		if(pstmt != null) {	
						pstmt.close();
	    		}
	    		if(conn != null) {
						conn.close();
	    		}	
	    		} catch (SQLException e) {		
				e.printStackTrace();
			}
		}		
		
		return updateFlag;	// 1이 반한되면 정부수정 성공, 아니면 정보수정 실패
	}
	
	
	
	
	
	
	
	
	
}