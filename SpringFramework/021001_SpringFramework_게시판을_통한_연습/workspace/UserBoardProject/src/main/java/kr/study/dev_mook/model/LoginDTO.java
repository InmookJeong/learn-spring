package kr.study.dev_mook.model;

/* Login 처리를 위한 DTO */
public class LoginDTO {
	
	private String uid;
	private String upw;
	private boolean useCookie;
	
	public String getUid() {
		return uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getUpw() {
		return upw;
	}
	
	public void setUpw(String upw) {
		this.upw = upw;
	}
	
	public boolean isUseCookie() {
		return useCookie;
	}
	
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	
	@Override
	public String toString() {
		return "LoginDTO [uid=" + uid + ", upw=" + upw + ", useCookie=" + useCookie + "]";
	}
}
