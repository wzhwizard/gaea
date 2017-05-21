package com.mindex.gaea.gateway.discovery;

import org.springframework.stereotype.Service;

@Service
public class DynamicDiscoveryService implements DicoverySevice {

	public String[] getIPList() {
		return new String[] { "0.0.0.0", "127.0.0.1" };
	}
}
