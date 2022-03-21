package kr.study.dev_mook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import kr.study.dev_mook.util.ServerInfo;

@Configuration
@Profile("dev")
public class ApplicationConfigDev {
	
	@Bean
	public ServerInfo serverInfo() {
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.setIp("localhost");
		serverInfo.setPort("8888");
		return serverInfo;
	}
	
}
