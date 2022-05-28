package kr.study.dev_mook.persistence.impl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.study.dev_mook.model.LoginDTO;
import kr.study.dev_mook.model.UserVO;
import kr.study.dev_mook.persistence.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession session;
	
	private static String NAMSPACE = "kr.study.dev_mook.mapper.UserMapper";

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return session.selectOne(NAMSPACE+".login", dto);
	}

}
