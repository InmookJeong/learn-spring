package kr.study.dev_mook.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	private static final String LOGIN = "login";
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");
		
		if(userVO != null) {
			logger.info("New Login Success");
			// 로그인한 사용자에 대한 정보를 HttpSession에 저장
			session.setAttribute(LOGIN, userVO);
			session.setAttribute("test", "this test");
			
			// 자동 로그인을 위한 Cookie 생성
			if(request.getParameter("useCookie") != null) {
				logger.info("remember me....");
				// SessionID == Session Cookie 값
				Cookie loginCookie = new Cookie("loginCookie", session.getId());
				loginCookie.setPath("/");
				// MaxAge는 초 단위 입력 : 7일 보관
				loginCookie.setMaxAge(60 * 60 * 24 * 7);
				// 만둘어진 쿠키는 HttpServletResponse에 담겨서 전송
				response.addCookie(loginCookie);
			}
//			response.sendRedirect("/");
			
			// 로그인 처리 후 원래 접속하려던 URI로 이동
			Object dest = session.getAttribute("dest");
			response.sendRedirect((dest != null) ? (String) dest:"/");
		}
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) != null) {
			logger.info("Clear Login Data Before.");
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	
}
