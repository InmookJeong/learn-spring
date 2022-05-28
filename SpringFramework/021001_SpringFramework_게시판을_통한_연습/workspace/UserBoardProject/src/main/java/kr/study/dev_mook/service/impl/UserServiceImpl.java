package kr.study.dev_mook.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.study.dev_mook.model.LoginDTO;
import kr.study.dev_mook.model.UserVO;
import kr.study.dev_mook.persistence.UserDAO;
import kr.study.dev_mook.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO dao;
	

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

}
