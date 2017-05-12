package com.mindex.gaea.gateway.module;

import com.google.inject.AbstractModule;
import com.mindex.gaea.gateway.discovery.DicoverySevice;
import com.mindex.gaea.gateway.discovery.DynamicDiscoveryService;

public class ServerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DicoverySevice.class).to(DynamicDiscoveryService.class);
	}

}
