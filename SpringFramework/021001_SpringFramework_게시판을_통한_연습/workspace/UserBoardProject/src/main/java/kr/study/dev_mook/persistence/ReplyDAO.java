package kr.study.dev_mook.persistence;

import java.util.List;

import kr.study.dev_mook.model.Criteria;
import kr.study.dev_mook.model.ReplyVO;

/* 댓글 작성을 위한 ReplyDAO Interface */
public interface ReplyDAO {
	
	public List<ReplyVO> list(Integer bno) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer rno) throws Exception;
	
	// Paging
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;
	
	public int count(Integer bno) throws Exception;
	
}
