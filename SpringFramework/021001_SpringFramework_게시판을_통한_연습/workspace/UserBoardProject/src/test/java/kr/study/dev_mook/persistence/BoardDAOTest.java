package kr.study.dev_mook.persistence;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.study.dev_mook.model.BoardVO;
import kr.study.dev_mook.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"classpath:framework/spring/*.xml"}
)
public class BoardDAOTest {
	
	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Inject
	private BoardDAO dao;
	
	@Test
	@Description("게시판 생성 테스트")
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다.");
		board.setContent("새로운 글을 넣습니다.");
		board.setWriter("user00");
		dao.create(board);
	}
	
	@Test
	@Description("게시판 상세정보 조회 테스트")
	public void testRead() throws Exception {
		logger.info(dao.read(1).toString());
	}
	
	@Test
	@Description("게시판 수정 테스트")
	public void testUpdate() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 글입니다.");
		board.setContent("수정 테스트");
		dao.update(board);
	}
	
	@Test
	@Description("게시판 삭제 테스트")
	public void testDelete() throws Exception {
		dao.delete(1);
	}
	
	@Test
	@Description("페이지 처리 테스트 코드")
	public void testListPage() throws Exception {
		int page = 3;
		List<BoardVO> list = dao.listPage(page);
		for(BoardVO board : list) {
			logger.info(board.getBno() + " : " + board.getTitle());
		}
	}
	
	@Test
	@Description("페이지 정보 조회 테스트 코드")
	public void testListCriteria() throws Exception {
		Criteria criteria = new Criteria();
		criteria.setPage(2);
		criteria.setPerPageNum(20);;
		
		List<BoardVO> list = dao.listCriteria(criteria);
		for(BoardVO board : list) {
			logger.info(board.getBno() + " : " + board.getTitle());
		}
	}

}
