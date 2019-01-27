package com.nhn.exam.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.config.Host;
import com.nhn.exam.was.utils.JsonRead;

public class JsonConfigUtilsTest {
    private static Logger logger = LoggerFactory.getLogger(JsonConfigUtilsTest.class);
    
    @Test
	public void jsonConfigTest() {
		try {
			//was config read
			JsonRead<Host, String> config = new JsonRead<Host, String>();
			Host value = config.jsonToObject(new Host(), "serverConfig");
			logger.info(value.getName());
			logger.info(value.getPort());
			logger.info(value.getFilter().toString());
			logger.info(value.getServer().toString());
			//was run
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
