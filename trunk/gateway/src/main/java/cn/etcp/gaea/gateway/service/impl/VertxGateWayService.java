package cn.etcp.gaea.gateway.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import cn.etcp.gaea.gateway.bean.EndPoint;
import cn.etcp.gaea.gateway.service.GateWayService;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;

@Service
public class VertxGateWayService implements GateWayService, InitializingBean {

	@Resource
	HttpClient httpClient;
	@Resource
	HttpServer httpServer;

	public void afterPropertiesSet() throws Exception {
		ConcurrentHashMap<String, EndPoint> dispatcher = new ConcurrentHashMap<>();
		dispatcher.put("weather", new EndPoint(8793, "10.103.22.93", HttpMethod.GET, "/service/o/p/list"));
		httpServer.requestHandler(request -> {
			try {
				String target = request.getHeader("target");
				EndPoint point = dispatcher.get(target);
				String targetURL = point.getRequestURI() + (request.query() == null ? "" : "?" + request.query());
				HttpClientRequest cRequest = httpClient.request(point.getMethod(), point.getPort(), point.getHost(),
						targetURL, response -> {
							request.response().headers().addAll(response.headers());
							request.response().setChunked(true);
							response.bodyHandler(body -> {
								request.response().end(body);
							});

						});
				System.out.println(cRequest.absoluteURI());
				cRequest.end();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}).listen(8080);
	}

}
