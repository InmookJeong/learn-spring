package kr.study.dev_mook.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.study.dev_mook.model.Student;

// JAVA Source Code를 통한 ApplicationConfiguration 작성
// Spring 설정에 사용되는 클래스라는 의미를 명시
@Configuration
public class ApplicationConfig {
	/*
	 * IllegalStateException:CGLIB is required to process @Configuration classes. 에러 발생시 
	 * pom.xml에 cglib Dependency 추가하기.
	 * 단, Spring 3.x 버전에서 cglib 3.x 버전 사용 시 에러 발생하므로 2.2.2 버전 사용.
	 */
	
	@Bean
	public Student student1() {
		
		ArrayList<String> hobbys = new ArrayList<String>();
		hobbys.add("수영");
		hobbys.add("요리");
		
		Student student = new Student("홍길동", 20, hobbys);
		student.setHeight(180);
		student.setWeight(80);
		
		return student;
		
	}

}
