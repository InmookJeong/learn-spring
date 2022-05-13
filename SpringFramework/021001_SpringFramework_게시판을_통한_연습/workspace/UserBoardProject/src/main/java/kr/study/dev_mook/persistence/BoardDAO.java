package kr.study.dev_mook.persistence;

import java.util.List;

import kr.study.dev_mook.model.BoardVO;
import kr.study.dev_mook.model.Criteria;

public interface BoardDAO {
	
	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	/* 페이지 처리를 위한 Method 추가 */
	public List<BoardVO> listPage(int page) throws Exception;
	
	/* 시작 페이지 번호와 perPageNum 번호를 가져오기 위한 처리 */
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception;
	
}
