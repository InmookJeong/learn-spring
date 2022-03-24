package kr.study.dev_mook.model;

public class Student {
	
	private String name;
	private int age;
	private int gradeNumber;
	private int classNumber;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getGradeNumber() {
		return gradeNumber;
	}
	
	public void setGradeNumber(int gradeNumber) {
		this.gradeNumber = gradeNumber;
	}
	
	public int getClassNumber() {
		return classNumber;
	}
	
	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}
	
	public void getStudentInfo() {
		System.out.println("이름 : " + getName());
		System.out.println("나이 : " + getAge());
		System.out.println("학년 : " + getGradeNumber());
		System.out.println("반 : " + getClassNumber());
	}
}
