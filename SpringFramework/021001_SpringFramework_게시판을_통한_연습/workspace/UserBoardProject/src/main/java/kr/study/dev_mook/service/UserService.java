package kr.study.dev_mook.service;

import kr.study.dev_mook.model.LoginDTO;
import kr.study.dev_mook.model.UserVO;

/* Login 처리를 위한 Service Interface */
public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
}
