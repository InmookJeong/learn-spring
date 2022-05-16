package kr.study.dev_mook.persistence.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.study.dev_mook.model.BoardVO;
import kr.study.dev_mook.model.Criteria;
import kr.study.dev_mook.model.SearchCriteria;
import kr.study.dev_mook.persistence.BoardDAO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession session;
	
	private static String NAMESPACE = "kr.study.dev_mook.mapper.BoardMapper";

	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(NAMESPACE+".create", vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne(NAMESPACE+".read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(NAMESPACE+".update", vo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(NAMESPACE+".delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(NAMESPACE+".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if(page <= 0) page = 1;
		page = (page - 1) * 10;
		return session.selectList(NAMESPACE+".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria criteria) throws Exception {
		return session.selectList(NAMESPACE+".listCriteria", criteria);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(NAMESPACE+".countPaging", cri);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(NAMESPACE+".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NAMESPACE+".listSearchCount", cri);
	}
}
