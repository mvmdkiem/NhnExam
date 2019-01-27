package com.nhn.exam.was.model.request;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
/**
 * 
 * @author Kim TaeHouyng
 *
 */
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
		return "HttperRequestHeader [host=" + host + ", method=" + method + ", accessFile=" + accessFile + ", version=" + version + ", params=" + params + "]";
	}

	public HttperRequestHeader(String[] arr) {
		String[] headers = arr[0].split("\\s+");
		String[] hosts = arr[1].split(":");
		this.host = hosts[1].trim();
		this.method = headers[0];
		if (headers[1].indexOf("?") > -1) {
			String[] accessFiles = headers[1].split("\\?");
			String[] queryString = accessFiles[1].split("\\&");
			for (String qs : queryString) {
				String[] query = qs.split("=");
				params.put(query[0], query[1]);
			}
			headers[1] = accessFiles[0];
		}
		this.accessFile = headers[1];

		if (headers.length > 2) {
			this.version = headers[2];
		}
	}

	public String getParam(String key) {
		return this.params.getOrDefault((Object) key, "");
	}
}
