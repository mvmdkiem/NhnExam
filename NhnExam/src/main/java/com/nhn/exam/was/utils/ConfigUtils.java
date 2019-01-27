package com.nhn.exam.was.utils;

import java.io.IOException;
import java.util.Arrays;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.Server;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by Eden on 2017. 8. 24..
 */
public class ConfigUtils {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
    private static ServerUtils serverUtils;
    private static int PORT;
    private static String[] blockedExtension;
    private static ConfigUtils instance = null;
    private static RouterUtils routers;

    private ConfigUtils() throws Exception {
        Config conf = ConfigFactory.load();

        PORT = conf.getInt("port");
        blockedExtension = String.valueOf(conf.getString("blocked_extension")).split(",");
        serverUtils = new ServerUtils(conf.getConfigList("servers"));
        serverUtils.setDefaultServer(conf.getString("default"));

        try {
            String fileContent = FileUtil.getFileContent("mapping.json");
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(fileContent);
            JSONObject mapper = (JSONObject) obj;
            routers = new RouterUtils();
            routers.buildRouter(mapper);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConfigUtils getInstance() {
        if(instance == null) {
            try {
                instance = new ConfigUtils();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return ConfigUtils.instance;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
        return PORT;
    }

    /**
     * Gets servers.
     *
     * @return the servers
     */
    public ServerUtils getServers() {
        return serverUtils;
    }

    /**
     * Gets routers.
     *
     * @return the routers
     */
    public RouterUtils getRouters() {
        return routers;
    }

    /**
     * Gets default server.
     *
     * @return the default server
     */
    public Server getDefaultServer() {
        return this.getServers().getDefaultServer();
    }

    
}
