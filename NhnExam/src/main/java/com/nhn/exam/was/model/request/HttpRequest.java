package com.nhn.exam.was.model.request;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.utils.DefaultServerConfig;

public class HttpRequest {
	private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);
	private InputStream inputStream;
	private String url;
	private HttperRequestHeader header;

	public HttpRequest(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void parser() {
		int i;

		StringBuffer rbf = new StringBuffer(2048);
		byte[] buffer = new byte[2048];

		try {
			i = inputStream.read(buffer);
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			i = -1;
		}

		for (int j = 0; j < i; j++) {
			rbf.append((char) buffer[j]);
		}

		this.url = this.parseUri(rbf.toString());

		this.checkDomain();
	}

	private String parseUri(String rs) {
		int idx1;
		idx1 = rs.indexOf(" ");

		if (idx1 != -1) {
			String[] tokens = rs.split("\\r\\n");

			this.header = new HttperRequestHeader(tokens);

			return this.header.getAccessFile();
		}

		return null;
	}

	private void checkDomain() throws NullPointerException {
		DefaultServerConfig config = DefaultServerConfig.getInstance();
		if (this.header != null) {
			config.getServers().setDefaultServerDomain(this.header.getHost());
		}
	}

	public String getUrl() {
		return this.url;
	}

	public HttperRequestHeader getHeader() {
		return this.header;
	}

	public String getParameter(String param) {
		return this.header.getParam(param);
	}

	@Override
	public String toString() {
		if (this.header != null) {
			return "HttpRequest: [" + this.header.toString() + "]";
		} else {
			return "";
		}
	}
}
