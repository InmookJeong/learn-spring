package kr.study.dev_mook.persistence.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.study.dev_mook.model.BoardVO;
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
}
