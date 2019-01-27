package com.nhn.exam.was.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.config.Host;

import lombok.Getter;

/**
 * 
 * @author Kim TaeHouyng
 *
 * @param <T> Object 
 * @param <V> String 
 * 
 */
public class ServerConfig {
	private static Logger logger = LoggerFactory.getLogger(ServerConfig.class);
	@Getter
	private static Host host;
	
	public ServerConfig() {
		try {
			JsonRead<Host, String> config = new JsonRead<Host, String>();
			if(host == null) host = config.jsonToObject(new Host(), "serverConfig");
			
			logger.debug("HOST INFO : " + host);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
