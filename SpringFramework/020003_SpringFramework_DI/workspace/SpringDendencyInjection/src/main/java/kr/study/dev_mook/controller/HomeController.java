package kr.study.dev_mook.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.study.dev_mook.config.ApplicationConfig;
import kr.study.dev_mook.model.Calculator;
import kr.study.dev_mook.model.MyCalculator;
import kr.study.dev_mook.model.MyInfo;
import kr.study.dev_mook.model.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
//		_notDependencyInjection();
		_doDependencyInjection();
		
		_bmiCalculator();
		
		_allAnnotationConfiguration();
		
		return "home";
	}
	
	/**
	 * DI를 사용하지 않고 계산기 실행
	 */
	private void _notDependencyInjection() {
		
		// new를 통한 전통적인 객체 생성 방법
		MyCalculator myCalculator = new MyCalculator();
		myCalculator.setCalculator(new Calculator());
		myCalculator.setFirstNumber(20);
		myCalculator.setSecondNumber(10);
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.multiple();
		myCalculator.div();
	}
	
	/**
	 * DI를 사용한 계산기 실행
	 */
	private void _doDependencyInjection() {
		
		String configLocation = "classpath:applicationCTX.xml";
		
		// configLocation을 읽어서 _context에 정보를 담는다.
		AbstractApplicationContext _context = new GenericXmlApplicationContext(configLocation);
		// _context에 등록된 Bean을 가져오는데 첫번째 Parameter는 bean의 ID, 두번째 Parameter는 Class Type을 작성한다.
		MyCalculator myCalculator = _context.getBean("myCalculator", MyCalculator.class);
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.multiple();
		myCalculator.div();
		
		_context.close();
		
	}
	
	/**
	 * BMI 계산기를 이용한 DI 학습
	 */
	private void _bmiCalculator() {
		
		String configLocation = "classpath:bmiApplicationCTX.xml";
		// contextFile이 여러개인 경우 Parameter에 Location을 여러 개 입력하면 됨.
		AbstractApplicationContext _context = new GenericXmlApplicationContext(configLocation);
		
		MyInfo myInfo = _context.getBean("myInfo", MyInfo.class);
		myInfo.getInfo();
		
		_context.close();
		
	}
	
	/**
	 * Annotation을 통한 Cofig 작성 및 DI 학습
	 */
	private void _allAnnotationConfiguration() {
		AnnotationConfigApplicationContext _context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		Student student1 = _context.getBean("student1", Student.class);
		System.out.println("이름 : " + student1.getName());
		System.out.println("나이 : " + student1.getAge());
		System.out.println("취미 : " + student1.getHobbys());
		System.out.println("신장 : " + student1.getHeight());
		System.out.println("몸무게 : " + student1.getWeight());
	}
	
}
