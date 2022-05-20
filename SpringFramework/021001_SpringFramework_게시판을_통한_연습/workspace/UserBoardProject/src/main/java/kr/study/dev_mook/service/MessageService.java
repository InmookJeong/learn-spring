package kr.study.dev_mook.service;

import kr.study.dev_mook.model.MessageVO;

/* AOP 메시지를 다루기 위한 Service Interface */
public interface MessageService {
	
	public void addMessage(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(String uid, Integer mid) throws Exception;
	
}
