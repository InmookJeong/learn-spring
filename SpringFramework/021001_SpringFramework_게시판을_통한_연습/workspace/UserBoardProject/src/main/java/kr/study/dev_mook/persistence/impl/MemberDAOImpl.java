package kr.study.dev_mook.persistence.impl;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.study.dev_mook.model.MemberVO;
import kr.study.dev_mook.persistence.MemberDAO;

/* DAO를 Spring에 인식시키기 위해 Repository Annotation 이용 */
@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String NAME_SPACE = "kr.study.dev_mook.mapper.MemberMapper";
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(NAME_SPACE+".getTime");
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(NAME_SPACE+".insertMember", vo);
	}

	@Override
	public MemberVO readMember(String userid) throws Exception {
		return sqlSession.selectOne(NAME_SPACE+".selectMember", userid);
	}

	@Override
	public MemberVO readWithPW(String userid, String userpw) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		return sqlSession.selectOne(NAME_SPACE+".readWithPW", paramMap);
	}

}
