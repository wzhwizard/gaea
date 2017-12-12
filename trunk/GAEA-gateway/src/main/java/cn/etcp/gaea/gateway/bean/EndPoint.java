package cn.etcp.gaea.gateway.bean;

import io.vertx.core.http.HttpMethod;

public class EndPoint {
	int port;
	String host;
	HttpMethod method;
	String requestURI;

	public EndPoint(int port, String host, HttpMethod method, String requestURI) {
		this.port = port;
		this.host = host;
		this.method = method;
		this.requestURI = requestURI;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

}
