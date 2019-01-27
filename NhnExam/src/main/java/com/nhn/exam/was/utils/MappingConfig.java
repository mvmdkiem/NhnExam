package com.nhn.exam.was.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.request.HttpRequest;
import com.nhn.exam.was.model.response.HttpResponse;
import com.nhn.exam.was.servlet.SimpleServlet;

import lombok.Getter;

/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class MappingConfig {
	private static Logger logger = LoggerFactory.getLogger(MappingConfig.class);
	@Getter
	private static Map<String, Object> mapping; 
	
	public MappingConfig() {
		try {
			if(mapping == null) {
				JsonRead<String, Object> config = new JsonRead<String, Object>();
				mapping = config.jsonToMap("mappingConfig");
			}
			
			logger.debug("Maping INFO : " + mapping);
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
		}
	}
	
	public String getClassName(String url) {
		String packageName = "";
		MappingConfig config = new MappingConfig();
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) config.getMapping().get("mappingInfo");
		
		for(int i = 0; i < list.size(); i++) {
			String name = String.valueOf(list.get(i).get("name"));
			Map<String, String> map = (Map<String, String>) list.get(i).get("route");
			if(map.containsKey(url)) {
				packageName = name + "." + map.get(url);
				break;
			}
		}
		
		logger.debug("Maping INFO : " + packageName);
		
		return packageName;
	}
	
	public void callServlet(HttpRequest req, HttpResponse res, SimpleServlet servlet, String resultCode) {
		try {
			res.setVersion(req.getHeader().getVersion());
			res.setResponseCode(resultCode);
			res.setContentType("text/html charset=utf-8");
			res.setLength(0);
			res.setHeader();
			servlet.service(req, res);
			res.getWriter().flush();
		}catch(Exception e) {
			 logger.error(e.getMessage(), e);
		}
	}
	
	public void callServlet(HttpRequest req, HttpResponse res, byte[] data, String resultCode) {
		try {
			res.setVersion(req.getHeader().getVersion());
			res.setResponseCode(resultCode);
			res.setContentType("text/html charset=utf-8");
			res.setLength(data.length);
			res.setHeader();
			res.getWriter().flush();
			res.setOutput(data);
		}catch(Exception e) {
			 logger.error(e.getMessage(), e);
		}
	}
}
