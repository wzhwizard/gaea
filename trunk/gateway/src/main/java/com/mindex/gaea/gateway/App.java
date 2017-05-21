package com.mindex.gaea.gateway;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mindex.gaea.gateway.discovery.DicoverySevice;
import com.mindex.gaea.gateway.server.GateWayServer;

import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;

@Configuration
@ComponentScan
public class App {

	@Bean
	public HttpServerOptions httpServerOptions() {
		return new HttpServerOptions().setCompressionSupported(true).setTcpNoDelay(true).setTcpKeepAlive(true)
				.setIdleTimeout(10000);
	}

	@Bean
	public VertxOptions vertxOptions() {
		return new VertxOptions().setWorkerPoolSize(1024).setInternalBlockingPoolSize(1024);
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		DicoverySevice server = context.getBean(DicoverySevice.class);
		
		System.out.println(Arrays.asList(server.getIPList()));
	}

}
