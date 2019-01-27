package com.nhn.exam.was.model.response;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.request.HttpRequest;

import lombok.Getter;
import lombok.Setter;
/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class HttpResponse {
	private static Logger logger = LoggerFactory.getLogger(HttpRequest.class);

	private OutputStream output;
	private Writer out;
	@Getter @Setter
	private int length;
	@Getter @Setter
	private String version;
	@Getter @Setter
	private String responseCode;
	@Getter @Setter
	private String contentType;
	
	public HttpResponse(OutputStream output) {
		this.output = output;
	}
	
	@Override
	public String toString() {
		return "HttpResponse [output=" + output + ", out=" + out + ", version=" + version + ", responseCode="
				+ responseCode + ", contentType=" + contentType + ", length=" + length + "]";
	}

	public void setHeader() throws IOException {
		Writer writer = this.getWriter();
		if (this.version.startsWith("HTTP/")) {
			writer.write(this.version + " " + this.responseCode + "\r\n");
			Date now = new Date();
			writer.write("Date: " + now + "\r\n");
			writer.write("Server: JHTTP 2.0\r\n");
			writer.write("Content-type: " + this.contentType + "\r\n\r\n");
		}
	}

	public void setOutput(byte[] data) throws IOException {
		try {
			output.write(data);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (output != null) {
				output.flush();
			}
		}
	}

	public Writer getWriter() {
		if (this.out == null) this.out = new OutputStreamWriter(this.output);
		
		return this.out;
	}
}
