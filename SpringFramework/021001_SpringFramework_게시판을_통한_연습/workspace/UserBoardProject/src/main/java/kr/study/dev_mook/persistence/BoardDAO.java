package kr.study.dev_mook.persistence;

import java.util.List;

import kr.study.dev_mook.model.BoardVO;
import kr.study.dev_mook.model.Criteria;
import kr.study.dev_mook.model.SearchCriteria;

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
	
	/* 전체 게시글 갯수 가져오기 */
	public int countPaging(Criteria cri) throws Exception;
	
	/* 검색을 통해 게시글 목록 조회 */
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	
	/* 검색을 통해 게시글 갯수 가져오기 */
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	/* 댓글 숫자 변경 처리 */
	public void updateReplyCnt(Integer bno, int amount) throws Exception;
	
	public void updateViewCnt(Integer bno) throws Exception;
	
	/* 게시판 파일 업로드 처리 */
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(Integer bno) throws Exception;
	
}
