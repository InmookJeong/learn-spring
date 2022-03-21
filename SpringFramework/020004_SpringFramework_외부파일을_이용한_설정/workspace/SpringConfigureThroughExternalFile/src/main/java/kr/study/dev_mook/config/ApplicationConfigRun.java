package kr.study.dev_mook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import kr.study.dev_mook.util.ServerInfo;

@Configuration
@Profile("run")
public class ApplicationConfigRun {
	
	@Bean
	public ServerInfo serverInfo() {
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.setIp("192.168.0.2");
		serverInfo.setPort("9191");
		return serverInfo;
	}
	
}
