package cn.etcp.gaea.gateway.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.etcp.gaea.gateway.service.DicoverySevice;
import io.vertx.servicediscovery.ServiceDiscovery;

@Service
public class DynamicDiscoveryService implements DicoverySevice {

	@Resource
	ServiceDiscovery serviceDiscovery;

	@PostConstruct
	public void init() {

//		Record record1 = HttpEndpoint.createRecord("weather", "10.103.22.93", 8793, "/");
//		Record record2 = HttpEndpoint.createRecord("weather", "10.103.22.89", 8793, "/");
//		serviceDiscovery.publish(record1, ar -> {
//			if (ar.succeeded()) {
//				Record publishedRecord = ar.result();
//				System.out.println(publishedRecord);
//			} else {
//				// publication failed
//			}
//		});
//		serviceDiscovery.publish(record2, ar -> {
//			if (ar.succeeded()) {
//				Record publishedRecord = ar.result();
//				System.out.println(publishedRecord);
//			} else {
//				// publication failed
//			}
//		});
	}
}
