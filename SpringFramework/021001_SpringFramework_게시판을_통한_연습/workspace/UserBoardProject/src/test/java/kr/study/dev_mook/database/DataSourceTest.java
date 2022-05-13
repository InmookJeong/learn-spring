package kr.study.dev_mook.database;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/* 
 * TestCode 실행 시 Spring이 Loading 되도록 Annotation 추가
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations = {"classpath:framework/spring/context-*.xml"}
)
public class DataSourceTest {
	
	// Spring이 생성하여 주입(DI)
	@Inject
	private DataSource ds;
	
	@Test
	public void testConnection() throws Exception {
		try(Connection con = ds.getConnection()) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
