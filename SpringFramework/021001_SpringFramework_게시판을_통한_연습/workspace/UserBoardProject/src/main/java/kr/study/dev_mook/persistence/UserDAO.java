package kr.study.dev_mook.persistence;

import kr.study.dev_mook.model.LoginDTO;
import kr.study.dev_mook.model.UserVO;

/* Login 처리를 위한 User DAO Interface */
public interface UserDAO {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
}
