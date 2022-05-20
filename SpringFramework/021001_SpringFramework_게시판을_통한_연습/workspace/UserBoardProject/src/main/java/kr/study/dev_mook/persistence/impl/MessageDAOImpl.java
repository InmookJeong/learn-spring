package kr.study.dev_mook.persistence.impl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.study.dev_mook.model.MessageVO;
import kr.study.dev_mook.persistence.MessageDAO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageDAOImpl.class);
	
	private final String NAMESPACE = "kr.study.dev_mook.mapper.MessageMapper";
	
	@Inject
	private SqlSession session;
	
	
	@Override
	public void create(MessageVO vo) throws Exception {
		session.insert(NAMESPACE+".create", vo);
	}

	@Override
	public MessageVO readMessage(Integer mid) throws Exception {
		return session.selectOne(NAMESPACE+".readMessage", mid);
	}

	@Override
	public void updateState(Integer mid) throws Exception {
		session.update(NAMESPACE+".updateState", mid);
	}

}
