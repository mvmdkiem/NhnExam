package com.nhn.exam.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.servlet.SimpleServlet;
import com.nhn.exam.was.utils.MappingConfig;

public class MappingConfigUtilsTest {
	private static Logger logger = LoggerFactory.getLogger(MappingConfigUtilsTest.class);
	
	@Test
	public void test() {
		MappingConfig config = new MappingConfig();
		logger.debug(config.toString());
	}
	
	@Test
	public void getMapParamtest() {
		MappingConfig config = new MappingConfig();
		
		Map<String, Object> map = (Map<String, Object>) config.getMapping();
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) map.get("mappingInfo");
		for(int i = 0; i < list.size(); i++) {

		}
		
		logger.debug(map.toString());
		logger.debug(map.get("mappingInfo").toString());
		logger.debug(list.toString());
		
	}
	
	@Test
	public void getPackage() throws ClassNotFoundException {
		MappingConfig config = new MappingConfig();
		logger.info(config.getClassName("/service.Greeting"));
		Class<SimpleServlet> forName1 = ((Class<SimpleServlet>) Class.forName(config.getClassName("/Greeting")));
		Class<SimpleServlet> forName2 = ((Class<SimpleServlet>) Class.forName("com.nhn.exam.was.servlet.impl.HelloServlet"));
		//com.nhn.exam.was.servlet.impl.HelloServlet
		Class<SimpleServlet> forName3 = ((Class<SimpleServlet>) Class.forName("com.nhn.exam.was.servlet.impl.HelloServlet"));
	}
}
