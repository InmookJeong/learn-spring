package kr.study.dev_mook.persistence.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.study.dev_mook.model.LoginDTO;
import kr.study.dev_mook.model.UserVO;
import kr.study.dev_mook.persistence.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	private static String NAMSPACE = "kr.study.dev_mook.mapper.UserMapper";
	
	@Inject
	private SqlSession session;

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return session.selectOne(NAMSPACE+".login", dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) {
		logger.info("Keep login....");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);
		session.update(NAMSPACE+".keepLogin", paramMap);
	}

	@Override
	public UserVO checkUserWithSessionKey(String value) {
		return session.selectOne(NAMSPACE+".checkUserWithSessionKey", value);
	}

}
