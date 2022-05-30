package kr.study.dev_mook.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import kr.study.dev_mook.model.UserVO;
import kr.study.dev_mook.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	private static final String LOGIN = "login";
	
	@Inject
	private UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		// 사용자가 로그인 상태인지 확인
		if(session.getAttribute(LOGIN) == null) {
			logger.info("Current User is not Logined");
			
			saveDest(request);
			
			// loginCookie가 존재할 경우에 대한 처리
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				// loginCookie가 있으면 DB에 해당 사용자 정보가 있는지 확인
				UserVO userVO = service.checkLoginBefore(loginCookie.getValue());
				
				logger.info("USER : " + userVO.toString());
				// 사용자 정보가 있으면 HttpSession을 통해 사용자 정보 전달
				if(userVO != null) {
					session.setAttribute(LOGIN, userVO);
					return true;
				}
			}
			
			response.sendRedirect("/user/login");
			return false;
		}
		
		return true;
	}
	
	// 로그인 성공 후 원래 가려고 했던 URI로 이동하도록 처리
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if(request.getMethod().equals("GET")) {
			logger.info("dest : " + (uri + query));
			request.getSession().setAttribute("dest", uri+query);
		}
	}

}
