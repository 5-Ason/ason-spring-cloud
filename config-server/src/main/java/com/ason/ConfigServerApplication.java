package com.ason;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
@EnableDiscoveryClient//该注解能激活Eureka中的DiscoveryClient实现，才能实现Controller中对服务信息的输出。
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ConfigServerApplication.class).web(true).run(args);
	}
}
