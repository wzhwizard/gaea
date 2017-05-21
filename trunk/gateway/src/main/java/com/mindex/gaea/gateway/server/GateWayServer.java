package com.mindex.gaea.gateway.server;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.mindex.gaea.gateway.App;
import com.mindex.gaea.gateway.discovery.DicoverySevice;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

@Service
public class GateWayServer implements Server, InitializingBean {
	@Resource
	DicoverySevice dynamicDiscoveryService;
	@Resource
	HttpServerOptions httpServerOptions;
	@Resource
	VertxOptions vertxOptions;

	private HttpServer server;

	@Override
	public void afterPropertiesSet() throws Exception {
		Vertx vertx = Vertx.vertx(vertxOptions);
		server = vertx.createHttpServer(httpServerOptions);
	}

	public void listen(int port) {
		server.listen(port);

	}



}
