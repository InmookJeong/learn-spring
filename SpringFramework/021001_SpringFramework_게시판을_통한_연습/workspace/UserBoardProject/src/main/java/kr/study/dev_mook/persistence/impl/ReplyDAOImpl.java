package kr.study.dev_mook.persistence.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.study.dev_mook.model.Criteria;
import kr.study.dev_mook.model.ReplyVO;
import kr.study.dev_mook.persistence.ReplyDAO;

/* 댓글 작성을 위한 DAO Impl */
@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Inject
	private SqlSession session;
	
	private static String NAMESPACE = "kr.study.dev_mook.mapper.ReplyMapper";
	
	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return session.selectList(NAMESPACE+".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(NAMESPACE+".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(NAMESPACE+".update", vo);
	}

	@Override
	public void delete(Integer rno) throws Exception {
		session.delete(NAMESPACE+".delete", rno);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		HashMap<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("bno", bno);
		searchMap.put("cri", cri);
		return session.selectList(NAMESPACE+".listPage", searchMap);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(NAMESPACE+".count", bno);
	}

	@Override
	public int getBno(Integer rno) throws Exception {
		return session.selectOne(NAMESPACE+".getBno", rno);
	}

}
