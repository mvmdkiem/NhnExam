package com.nhn.exam.was.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.model.config.Html;
import com.nhn.exam.was.model.config.Server;
import com.typesafe.config.Config;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class DefaultHtmlConfig {
    private static Logger logger = LoggerFactory.getLogger(DefaultHtmlConfig.class);
    private Map<String, Server> serverMap = new HashMap<String, Server>();
    private Server defaultServer;
	
    public DefaultHtmlConfig(List<Server> servers) throws IllegalAccessException, ParseException {
        for(Server server : servers) {
            serverMap.put(server.getName(), server);
        }
    }
    
    public void setDefaultServer(Server server) {
        this.defaultServer = server;
    }
    
    public void setDefaultServer(String name) {
        Server serverName = serverMap.get(name);
        this.defaultServer = serverName;
    }
    
    public Server getDefaultServer() {
        return this.defaultServer;
    }
    
    public void setDefaultServerDomain(String domain) {
        if(!this.getDefaultServer().getDomain().equals(domain)) {
            for( Map.Entry<String, Server> srv : serverMap.entrySet() ){
                if(srv.getValue().getDomain().equals(domain))
                    this.defaultServer = srv.getValue();
            }
        }
    }

    public Server getServer(String name) {
        Server serverName = serverMap.get(name);
        return serverName;
    }

    public Integer getServerCount() {
        return serverMap.size();
    }
}