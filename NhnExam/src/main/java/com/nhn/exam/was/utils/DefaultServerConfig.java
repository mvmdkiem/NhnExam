package com.nhn.exam.was.utils;

import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.config.Server;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import lombok.Getter;

/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class DefaultServerConfig {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultServerConfig.class);
    private static DefaultHtmlConfig serverUtils;
    private static int serverPort;
    private static DefaultServerConfig instance = null;
    @Getter
    private static MappingConfig mapping;

    private DefaultServerConfig() throws Exception {
        this.mapping = new MappingConfig();
        ServerConfig serverConfig = new ServerConfig();
        
        serverPort = Integer.parseInt(serverConfig.getHost().getPort());
        //blockedExtension = String.valueOf(conf.getString("blocked_extension")).split(",");
        serverUtils = new DefaultHtmlConfig(serverConfig.getHost().getServer());
        serverUtils.setDefaultServer(serverConfig.getHost().getServer().get(0));

        logger.info("--------------------------*");
        logger.info("SERVER START");
        logger.info("SERVER COUNT : " + serverUtils.getServerCount() );
        logger.info("SERVER PORT  : " + serverPort);
        logger.info("--------------------------*");
    }

    public static DefaultServerConfig getInstance() {
        if(instance == null) {
            try {
                instance = new DefaultServerConfig();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return DefaultServerConfig.instance;
    }

    public int getPort() {
        return serverPort;
    }

    public DefaultHtmlConfig getServers() {
        return serverUtils;
    }

    public Server getDefaultServer() {
        return this.getServers().getDefaultServer();
    }
}
