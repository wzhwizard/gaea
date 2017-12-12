package cn.etcp.gaea.gateway.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;

@Configuration
public class VertxConfig {
	@Bean
	@ConfigurationProperties(prefix = "vertxOption")
	public VertxOptions vertxOptions() {
		return new VertxOptions();
	}

	@Bean
	@ConfigurationProperties(prefix = "vertx")
	public Vertx vertx() {
		return Vertx.vertx(vertxOptions());
	}

	@Bean
	@ConfigurationProperties(prefix = "http.client")
	public HttpClientOptions httpClientOptions() {
		return new HttpClientOptions();
	}

	@Bean
	@ConfigurationProperties(prefix = "http.server")
	public HttpServerOptions httpServerOptions() {
		return new HttpServerOptions();

	}

	@Bean
	public HttpClient httpClient() {
		return vertx().createHttpClient(httpClientOptions());
	}

	@Bean
	@ConfigurationProperties(prefix = "http.server")
	public HttpServer httpServer() {
		return vertx().createHttpServer(httpServerOptions());

	}
}
