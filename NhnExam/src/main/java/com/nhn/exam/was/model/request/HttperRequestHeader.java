package com.nhn.exam.was.model.request;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class HttperRequestHeader {
	@Getter
	private String host;
	@Getter
	private String method;
	@Getter
	private String accessFile;
	@Getter
	private String version = "HTTP/1.0";
	@Getter
	private Map<String, String> params = new HashMap<String, String>();

	@Override
	public String toString() {
		return "HttperRequestHeader [host=" + host + ", method=" + method + ", accessFile=" + accessFile + ", version="
				+ version + ", params=" + params + "]";
	}

	public HttperRequestHeader(String[] arr) {
		String[] strHeader = arr[0].split("\\s+");
		String[] strHost = arr[1].split(":");
		this.host = strHost[1].trim();
		this.method = strHeader[0];
		if (strHeader[1].indexOf("?") > -1) {
			String[] accessFiles = strHeader[1].split("\\?");
			String[] queryString = accessFiles[1].split("\\&");
			for (String qs : queryString) {
				String[] query = qs.split("=");
				params.put(query[0], query[1]);
			}
			strHeader[1] = accessFiles[0];
		}
		this.accessFile = strHeader[1];

		if (strHeader.length > 2) {
			this.version = strHeader[2];
		}
	}

	public String getParam(String key) {
		return this.params.getOrDefault((Object) key, "");
	}
}
