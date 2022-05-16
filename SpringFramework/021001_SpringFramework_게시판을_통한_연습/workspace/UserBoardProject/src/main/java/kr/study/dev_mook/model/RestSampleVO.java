package kr.study.dev_mook.model;

// REST 방식의 요청에 대해 JSON으로 반환하기 위한 모델
public class RestSampleVO {
	
	private Integer mno;
	private String firstName;
	private String lastName;
	public Integer getMno() {
		return mno;
	}
	
	public void setMno(Integer mno) {
		this.mno = mno;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "SampleJsonVO [mno=" + mno + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
}
