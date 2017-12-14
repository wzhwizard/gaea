package cn.etcp.gaea.gateway.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.etcp.gaea.gateway.service.ConfigService;
import io.vertx.config.ConfigChange;
import io.vertx.config.ConfigRetriever;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

@Service
public class VertxConfigService implements ConfigService, Handler<ConfigChange> {

	Logger logger = LogManager.getLogger();

	@Resource
	ConfigRetriever configRetriever;

	private JsonObject data;

	@PostConstruct
	public void init() {

		configRetriever.listen(this);
		configRetriever.getConfig(event -> {
			if (event.succeeded()) {
				data = event.result();
			} else {
				logger.error("config server init data fail!", event.cause());
			}

		});
	}

	@Override
	public void handle(ConfigChange event) {
		JsonObject previous = event.getPreviousConfiguration();
		data = event.getNewConfiguration();
		logger.info("config server data change: form {} to {}", previous, data);
	}

}
