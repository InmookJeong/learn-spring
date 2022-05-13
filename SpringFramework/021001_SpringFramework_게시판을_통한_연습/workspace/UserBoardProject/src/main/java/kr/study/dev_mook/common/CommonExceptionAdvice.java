package kr.study.dev_mook.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * - 공통의 Exception 처리 전용 객체 사용
 * - 호출되는 Method에서 발생된 Exception을 모두 처리하는 역할
 * - Contoller에서 발생하는 Exception 처리
 */
@ControllerAdvice
public class CommonExceptionAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	// Exception Type으로 된 모든 예외 처리
	// Exception 객체만 Parameter로 사용 가능
//	@ExceptionHandler(Exception.class)
//	public String common(Exception e) {
//		logger.info(e.toString());
//		return "error_common";
//	}
	
	// ModelAndView를 이용해 Exception을 화면에 전달 가능
	@ExceptionHandler(Exception.class)
	private ModelAndView errorModelAndView(Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error_common");
		modelAndView.addObject("exception", e);
		return modelAndView;
	}

}
