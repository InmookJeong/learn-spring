package kr.study.dev_mook.persistence;

import kr.study.dev_mook.model.MessageVO;

/* AOP 메시지를 위한 MessageDAO Interface */
public interface MessageDAO {
	
	public void create(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(Integer mid) throws Exception;
	
	public void updateState(Integer mid) throws Exception;

}
