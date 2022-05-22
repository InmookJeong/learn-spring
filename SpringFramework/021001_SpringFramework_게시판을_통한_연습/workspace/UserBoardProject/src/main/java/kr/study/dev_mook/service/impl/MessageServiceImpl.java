package kr.study.dev_mook.service.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.study.dev_mook.model.MessageVO;
import kr.study.dev_mook.persistence.MessageDAO;
import kr.study.dev_mook.persistence.PointDAO;
import kr.study.dev_mook.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Inject
	private MessageDAO messageDao;
	
	@Inject
	private PointDAO pointDao;
	
	@Transactional
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		messageDao.create(vo);
		pointDao.updatePoint(vo.getSender(), 10);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mid) throws Exception {
		messageDao.updateState(mid);
		pointDao.updatePoint(uid, 5);
		return messageDao.readMessage(mid);
	}

}
