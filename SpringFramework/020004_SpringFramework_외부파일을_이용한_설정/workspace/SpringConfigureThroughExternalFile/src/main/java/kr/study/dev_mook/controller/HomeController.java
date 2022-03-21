package kr.study.dev_mook.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.study.dev_mook.config.ApplicationConfig;
import kr.study.dev_mook.config.ApplicationConfigDev;
import kr.study.dev_mook.config.ApplicationConfigRun;
import kr.study.dev_mook.util.AdminConnection;
import kr.study.dev_mook.util.ServerInfo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private final String CLASS_PATH = "classpath:";
	private final String ADMIN_PROPERTY_LOCATION = "admin.properties";
	private final String APPLICATION_CONTEXT_LOCATION = "applicationCTX.xml";
	
	private final String PROPERTY_ADMIN_ID = "admin.id";
	private final String PROPERTY_ADMIN_PW = "admin.pw";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		_configure();
		_annotationConfigure();
		_configureByProfile();
		
		return "home";
	}
	
	/**
	 * XML을 통한 Context 설정
	 */
	private void _configure() {
		System.out.println("======================================================");
		System.out.println("Call GenericXmlApplicationContext~!");
		
		ConfigurableApplicationContext _context = new GenericXmlApplicationContext();
		ConfigurableEnvironment _environment = _context.getEnvironment();
		
		MutablePropertySources _propertySources = _environment.getPropertySources();
		
		try {
			_propertySources.addLast(new ResourcePropertySource(CLASS_PATH.concat(ADMIN_PROPERTY_LOCATION)));
			
			System.out.println(_environment.getProperty(PROPERTY_ADMIN_ID));
			System.out.println(_environment.getProperty(PROPERTY_ADMIN_PW));
		} catch (IOException e) {}
		
		GenericXmlApplicationContext _genericContext = (GenericXmlApplicationContext) _context;
		_genericContext.load(APPLICATION_CONTEXT_LOCATION);
		_genericContext.refresh();
		
		AdminConnection adminConnection = _genericContext.getBean("adminConnection", AdminConnection.class);
		System.out.println("admin ID : " + adminConnection.getAdminId());
		System.out.println("admin PW : " + adminConnection.getAdminPw());
		System.out.println();
		
		_genericContext.close();
		_context.close();
	}
	
	/**
	 * Annotation을 통한 Context 설정
	 */
	private void _annotationConfigure() {
		System.out.println("======================================================");
		System.out.println("Call AnnotationConfigApplicationContext~!");
		
		AnnotationConfigApplicationContext _annotationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		AdminConnection adminConnection = _annotationContext.getBean("adminConfig", AdminConnection.class);
		
		System.out.println("admin ID : " + adminConnection.getAdminId());
		System.out.println("admin PW : " + adminConnection.getAdminPw());
		System.out.println("subAdmin ID : " + adminConnection.getSubAdminId());
		System.out.println("subAdmin PW : " + adminConnection.getSubAdminPw());
		System.out.println();
		
		_annotationContext.close();
	}
	
	/**
	 * Profile 정보를 통해 Context 설정
	 */
	private void _configureByProfile() {
		
		// Profile 정보를 XML에 전달하여 Context 설정
		_callGenericXmlAppCtx("dev");
		_callGenericXmlAppCtx("run");
		
		// Profile 정보와 Annotation을 이용한 Context 설정
		_callAnnotationCtx("dev");
		_callAnnotationCtx("run");
	}
	
	private void _callGenericXmlAppCtx(String profile) {
		System.out.println("======================================================");
		System.out.println("Call GenericXmlApplicationContext by '"+profile+"' profile~!");
		
		GenericXmlApplicationContext _genericContext = new GenericXmlApplicationContext();
		_genericContext.getEnvironment().setActiveProfiles(profile);
		_genericContext.load("applicationCTX_dev.xml", "applicationCTX_run.xml");
		
		ServerInfo info = _genericContext.getBean("serverInfo", ServerInfo.class);
		System.out.println("ip : " + info.getIp());
		System.out.println("port : " + info.getPort());
		System.out.println();
		_genericContext.close();
	}
	
	private void _callAnnotationCtx(String profile) {
		System.out.println("======================================================");
		System.out.println("Call AnnotationConfigApplicationContext by '"+profile+"' profile~!");
		
		AnnotationConfigApplicationContext _annotationContext = new AnnotationConfigApplicationContext();
		_annotationContext.getEnvironment().setActiveProfiles(profile);
		_annotationContext.register(ApplicationConfigDev.class, ApplicationConfigRun.class);
		_annotationContext.refresh();
		
		ServerInfo info = _annotationContext.getBean("serverInfo", ServerInfo.class);
		System.out.println("ip : " + info.getIp());
		System.out.println("port : " + info.getPort());
		System.out.println();
		_annotationContext.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
