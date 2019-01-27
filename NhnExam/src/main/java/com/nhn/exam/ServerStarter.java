package com.nhn.exam;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nhn.exam.was.handler.HttpHandler;
import com.nhn.exam.was.utils.DefaultServerConfig;
/**
 * 
 * @author Kim TaeHouyng
 *
 */
public class ServerStarter {
    private static Logger logger = LoggerFactory.getLogger(ServerStarter.class);

	private static final int SERVER_COUNT = 100;
	private int port = 80;
	
	public ServerStarter(int port){
        this.port = port;
    }
	
	public void start() throws Exception {
		//ExecutorService 사용하여 MUlTI THREAD 관리
        ExecutorService pool = Executors.newFixedThreadPool(SERVER_COUNT);
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try {
                	Socket request = server.accept();
                    Runnable run = new HttpHandler(request);
                    pool.submit(run);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
	
	public static void main(String[] ar) {
		DefaultServerConfig properties = DefaultServerConfig.getInstance();

        try {
        	ServerStarter serverStart = new ServerStarter(properties.getPort());
        	serverStart.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
	}
}
