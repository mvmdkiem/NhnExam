package com.nhn.exam.was.handler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.request.HttpRequest;
import com.nhn.exam.was.model.response.HttpResponse;
import com.nhn.exam.was.servlet.SimpleServlet;
import com.nhn.exam.was.utils.ConfigUtils;
import com.nhn.exam.was.utils.MappingConfig;
import com.nhn.exam.was.utils.RouterUtils;
import com.nhn.exam.was.utils.ServerConfig;
import com.nhn.exam.was.utils.UrlUtils;

public class HttpHandler implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(HttpHandler.class);
	private Socket socket;

	public HttpHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		HttpRequest req = null;
		HttpResponse res = null;
		try (InputStream input = this.socket.getInputStream(); OutputStream output = this.socket.getOutputStream()){
			req = new HttpRequest(input);
			res = new HttpResponse(new BufferedOutputStream(output));
			req.parse();
			
			//1. Request, Response 분석
			logger.info(req.toString());
			logger.info(res.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		File html;
		String root = new File("").getAbsolutePath();
		byte[] data = new byte[0];
		ConfigUtils properties = ConfigUtils.getInstance();
		
		ServerConfig serverConfig = new ServerConfig();
		try (Socket socket = new Socket()) {
			// EXE CHECK And Block Check
			if (UrlUtils.checkBlockedExtension(req.getUrl(), serverConfig.getHost().getFilter())) {
				throw new Exception("ERROR : URL USE THE BLOCK IS NOT PERMISSION");
			}
			
			if(UrlUtils.checkBlockRootPath(req.getUrl())) {
                throw new IOException("ERROR : NOT ROOT ROOT");
            }

			// Web Browser IN TEST GET -> /favicon.ico PASS
			if (req.getUrl() != null && req.getUrl().indexOf("favicon.ico") > -1) {
				throw new Exception("ERROR : NONE FAVICON");
			}
			
			String htmlPath = req.getUrl();
			if (htmlPath.endsWith("/")) htmlPath += properties.getDefaultServer().getSource().getIndex();
			htmlPath = root + properties.getDefaultServer().getHttproot() + htmlPath;
			
			logger.debug("HTML File PATH : " + htmlPath);

			html = new File(htmlPath);

			if (html.canRead()) {
				data = Files.readAllBytes(html.toPath());
				res.setVersion(req.getHeader().getVersion());
				res.setResponseCode("200 OK");
				res.setContentType("text/html");
				res.setLength(data.length);
				res.setHeader();
				res.getWriter().flush();
				res.setOutput(data);
			} else {

				// URL 을 SimpleServlet 구현체로 매핑 구현 (mappingConfig.json 참조)				
				MappingConfig mapping = new MappingConfig();				
				if (mapping.getClassName(req.getUrl()) != null && !"".equals(mapping.getClassName(req.getUrl()))) {
					try {
						Class<SimpleServlet> forName = ((Class<SimpleServlet>) Class.forName(mapping.getClassName(req.getUrl())));
						SimpleServlet servlet = null;
						try {
							servlet = forName.newInstance();
							if (servlet != null) {

								 res.setVersion(req.getHeader().getVersion());
		                            res.setResponseCode("200 OK");
		                            res.setContentType("text/html");
		                            res.setLength(0);
		                            res.setHeader();
		                            servlet.service(req, res);
		                            res.getWriter().flush();
							}
						} catch (InstantiationException e) {
							logger.error(e.getMessage(), e);
						} catch (IllegalAccessException e) {
							logger.error(e.getMessage(), e);
						}
					} catch (ClassNotFoundException e) {
						throw new Exception("CLASS NOT FOUNT");
					}
				} else {
					throw new FileNotFoundException("NOT CALL MAPPING CLASS");
				}
			}
		} catch (FileNotFoundException e) {
			logger.warn(e.getMessage(), e);
			try {
				html = new File(root + properties.getDefaultServer().getPage404());
				data = Files.readAllBytes(html.toPath());
				res.setVersion(req.getHeader().getVersion());
				res.setResponseCode("404 File Not Found");
				res.setContentType("text/html; charset=utf-8");
				res.setLength(data.length);
				res.setHeader();
				res.setOutput(data);

				logger.info(res.toString());
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		} catch (IOException e) {
			logger.warn(e.getMessage(), e);
			try {
				html = new File(root + properties.getDefaultServer().getPage403());
				data = Files.readAllBytes(html.toPath());
				res.setVersion(req.getHeader().getVersion());
				res.setResponseCode("403 Forbidden");
				res.setContentType("text/html; charset=utf-8");
				res.setLength(data.length);
				res.setHeader();
				res.setOutput(data);

				logger.info(res.toString());
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			try {
				html = new File(root + properties.getDefaultServer().getPage500());
				data = Files.readAllBytes(html.toPath());
				res.setVersion(req.getHeader().getVersion());
				res.setResponseCode("500 Internal Server Error");
				res.setContentType("text/html; charset=utf-8");
				res.setLength(data.length);
				res.setHeader();
				res.setOutput(data);

				logger.info(res.toString());
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		} 
	}
}
