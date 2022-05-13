package kr.study.dev_mook.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.study.dev_mook.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"classpath:framework/spring/*.xml"}
)
public class MemberDAOTest {
	
	@Inject
	private MemberDAO dao;
	
	@Test
	public void testTime() throws Exception {
		System.out.println(dao.getTime());
	}
	
	@Test
	public void testInsertMember() throws Exception {
		MemberVO vo = new MemberVO();
		vo.setUserid("user00");
		vo.setUserpw("user00");
		vo.setUsername("User_0");
		vo.setEmail("user00@aaa.com");
		dao.insertMember(vo);
	}
	
	@Test
	public void testReadMember() throws Exception {
		System.out.println(dao.readMember("user00").toString());
	}
	
	@Test
	public void testReadWithPW() throws Exception {
		System.out.println(dao.readWithPW("user00", "user00").toString());
	}
}
