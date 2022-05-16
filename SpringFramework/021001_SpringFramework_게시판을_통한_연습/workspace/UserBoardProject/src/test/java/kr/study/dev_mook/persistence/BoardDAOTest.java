package kr.study.dev_mook.persistence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import kr.study.dev_mook.model.BoardVO;
import kr.study.dev_mook.model.Criteria;
import kr.study.dev_mook.model.SearchCriteria;

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
	
	@Test
	@Description("페이지 조회를 위한 Parameter Query 테스트")
	public void testURI() throws Exception {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.path("/board/read")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build();
		
		String expected = "/board/read?bno=12&perPageNum=20";
		assertEquals(uriComponents.toString(), expected);
	}
	
	@Test
	@Description("페이지 조회를 위한 Parameter Query 테스트 - expand 활용")
	public void testURI2() throws Exception {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.path("/{module}/{page}")
				.queryParam("bno", 12)
				.queryParam("perPageNum", 20)
				.build()
				.expand("board", "read")
				.encode();
		
		String expected = "/board/read?bno=12&perPageNum=20";
		assertEquals(uriComponents.toString(), expected);
	}
	
	@Test
	@Description("Dynamic Query Test")
	public void testDynamic1() throws Exception {
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("글");
		cri.setSearchType("t");
		
		logger.info("=====================================");
		
		List<BoardVO> list = dao.listSearch(cri);
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
		}
		
		logger.info("=====================================");
		logger.info("COUNT : " + dao.listSearchCount(cri));
	}

}
