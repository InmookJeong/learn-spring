package kr.study.dev_mook.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/* Interceptor 구현 연습 */
public class SampleInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 화면 처리 전 추가 작업 수행
		// ex) 실행 결과를 HttpSession 객체에 담아야 하는 경우 - Controller에서 HttpSession을 처리할 필요 없음
		// HttpSession은 Session Cookie를 통해서 동작
		System.out.println("post handle.......");
		
		Object result = modelAndView.getModel().get("result");
		if(result != null) {
			request.getSession().setAttribute("result", result);
			response.sendRedirect("/doA");
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Parameter 중 Object handler는 현재 실행하려는 메소드 자체
		System.out.println("pre handle........");
		
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		
		System.out.println("Bean : " + method.getMethod());
		System.out.println("Method : " + methodObj);
		return true;
	}

}
