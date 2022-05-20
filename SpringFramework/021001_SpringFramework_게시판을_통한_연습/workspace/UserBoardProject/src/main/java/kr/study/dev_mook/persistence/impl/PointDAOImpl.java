package kr.study.dev_mook.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.study.dev_mook.persistence.PointDAO;

@Repository
public class PointDAOImpl implements PointDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PointDAOImpl.class);
	
	@Inject
	private SqlSession session;
	
	private final String NAMESPACE = "kr.study.dev_mook.mapper.PointMapper";

	@Override
	public void updatePoint(String uid, int point) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("point", point);
		session.update(NAMESPACE+".updatePoint", paramMap);
	}

}
