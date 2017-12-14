package cn.etcp.gaea.gateway.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import cn.etcp.gaea.gateway.service.GateWayService;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.client.WebClient;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceReference;

@Service
public class VertxGateWayService implements GateWayService, InitializingBean {

	@Resource
	HttpServer httpServer;
	@Resource
	ServiceDiscovery serviceDiscovery;

	public void afterPropertiesSet() throws Exception {

		httpServer.requestHandler(request -> {
			try {
				String target = request.getHeader("target");
				serviceDiscovery.getRecord(r -> r.getName().equals(target), ar -> {
					if (ar.succeeded()) {
						if (ar.result() != null) {
							String targetURL = request.path() + (request.query() == null ? "" : "?" + request.query());
							request.bodyHandler(body -> {
								System.out.println(body);
								ServiceReference reference = null;
								try {
									reference = serviceDiscovery.getReference(ar.result());
									WebClient client = reference.getAs(WebClient.class);
									client.request(HttpMethod.GET, targetURL).sendBuffer(body, e -> {
										if (e.succeeded()) {
											if (e.result() != null) {
												request.response().headers().addAll(e.result().headers());
												request.response().end(e.result().body());
											} else {
											}
										} else {
											System.out.println(e.cause());
										}
										;
									});
								} catch (Throwable tt) {
									tt.printStackTrace();
								} finally {
									if (reference != null)
										reference.release();
								}
							});

						} else {
						}
					} else {
					}

				});

			} catch (Exception e) {
				e.printStackTrace();
			}

		}).listen(8080);
	}

}
