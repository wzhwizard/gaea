package com.mindex.gaea.gateway;

import java.util.Arrays;

import com.google.inject.Inject;
import com.mindex.gaea.gateway.discovery.DicoverySevice;

public class GateWayServer {
	DicoverySevice ds;

	@Inject
	public GateWayServer(DicoverySevice ds) {
		this.ds = ds;
	}

	public void route() {
		System.out.println(Arrays.asList(ds.getIPList()));
	}
}
