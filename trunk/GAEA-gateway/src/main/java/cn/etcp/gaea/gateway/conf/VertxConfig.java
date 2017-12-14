package cn.etcp.gaea.gateway.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.consul.ConsulServiceImporter;

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
	public ServiceDiscovery serviceDiscovery() {
		return ServiceDiscovery.create(vertx())
	    .registerServiceImporter(new ConsulServiceImporter(),
	        new JsonObject()
	            .put("host", "10.103.23.129")
	            .put("port", 8500)
	            .put("scan-period", 2000));
//		 ServiceDiscovery.create(vertx(),
//				new ServiceDiscoveryOptions().setAnnounceAddress("service-announce").setName("my-name"));
	}

	@Value("${redis.host}")
	String redisHost;

	@Value("${redis.port}")
	String redisPort;

	@Value("${redis.configKey}")
	String redisConfigKey;

	@Value("${redis.auth}")
	String redisAuth;

	@Bean
	public JsonObject redisConfig() {
		return new JsonObject().put("host", redisHost).put("port", redisPort).put("key", redisConfigKey).put("auth",
				redisAuth);

	}

	@Bean
	public ConfigStoreOptions configStoreOptions() {
		return new ConfigStoreOptions().setType("redis").setConfig(redisConfig());

	}

	@Bean
	public ConfigRetrieverOptions configRetrieverOptions() {
		return new ConfigRetrieverOptions().setScanPeriod(2000);
	}

	@Bean
	public ConfigRetriever configRetriever() {
		return ConfigRetriever.create(vertx(), configRetrieverOptions().addStore(configStoreOptions()));
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
	public HttpServer httpServer() {
		return vertx().createHttpServer(httpServerOptions());

	}
}
