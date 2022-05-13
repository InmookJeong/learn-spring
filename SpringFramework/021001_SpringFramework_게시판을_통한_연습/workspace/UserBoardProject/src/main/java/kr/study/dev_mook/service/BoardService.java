package kr.study.dev_mook.service;

import java.util.List;

import kr.study.dev_mook.model.BoardVO;
import kr.study.dev_mook.model.Criteria;

public interface BoardService {
	
	public void regist(BoardVO board) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void modify(BoardVO board) throws Exception;
	
	public void remove(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception;
	
	public int listCountCriteria(Criteria cri) throws Exception;

}
