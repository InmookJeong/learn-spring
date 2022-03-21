package kr.study.dev_mook.util;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AdminConnection implements EnvironmentAware, InitializingBean, DisposableBean {
	
	private Environment environment;
	private String adminId;
	private String adminPw;
	
	private String subAdminId;
	private String subAdminPw;

	@Override
	public void setEnvironment(Environment environment) {
		System.out.println("Call setEnvironment method.");
		this.environment = environment;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminId() {
		return adminId;
	}
	
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}

	public String getAdminPw() {
		return adminPw;
	}
	
	

	public String getSubAdminId() {
		return subAdminId;
	}

	public void setSubAdminId(String subAdminId) {
		this.subAdminId = subAdminId;
	}

	public String getSubAdminPw() {
		return subAdminPw;
	}

	public void setSubAdminPw(String subAdminPw) {
		this.subAdminPw = subAdminPw;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Call afterPropertiesSet method.");
		setAdminId(environment.getProperty("admin.id"));
		setAdminPw(environment.getProperty("admin.pw"));
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("Call destroy method.");
	}
}
