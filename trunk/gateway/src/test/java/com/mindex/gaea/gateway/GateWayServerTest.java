package com.mindex.gaea.gateway;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mindex.gaea.gateway.module.ServerModule;

public class GateWayServerTest {

	@Test
	public void GuiceTest() {
		Injector injector = Guice.createInjector(new ServerModule());
		GateWayServer server = injector.getInstance(GateWayServer.class);
		server.route();
	}
}
