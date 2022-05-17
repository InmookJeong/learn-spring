package kr.study.dev_mook.service;

import java.util.List;

import kr.study.dev_mook.model.Criteria;
import kr.study.dev_mook.model.ReplyVO;

/* 댓글 작성을 위한 Service Interface */
public interface ReplyService {
	
	public void addReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listReply(Integer bno) throws Exception;
	
	public void modifyReply(ReplyVO vo) throws Exception;
	
	public void removeReply(Integer rno) throws Exception;
	
	// 페이지 처리
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;

}
