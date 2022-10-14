package pend.aurrius.member;


public class MemberDto {		// db 필드명하고 같이하는게 좋다.
	private  String id;				// 멤버변수 4개 생성
	private  String pw;
	private  String Username;
	private  String email;
	private  String signuptime;		//직접만드는건 아니지만 호출했을때 자리를 잡아놔야한다.
	
	// getter,setter   를 만들어야한다. 우클릭-소스해서 만들수있다.
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String name) {
		this.Username = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSignuptime() {
		return signuptime;
	}
	public void setSignuptime(String signuptime) {
		this.signuptime = signuptime;
	}
	
	
	
	
	
}
