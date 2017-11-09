package cn.etcp.gaea.gateway.service.impl;

import org.springframework.stereotype.Service;

import cn.etcp.gaea.gateway.service.DicoverySevice;

@Service
public class DynamicDiscoveryService implements DicoverySevice {

	public String[] getIPList() {
		return new String[] { "0.0.0.0", "127.0.0.1" };
	}
}
