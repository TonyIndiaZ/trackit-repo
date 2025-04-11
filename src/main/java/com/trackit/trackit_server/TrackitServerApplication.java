package com.trackit.trackit_server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrackitServerApplication {
	
	@Value("${server.port}")
	private static String portNbr;

    private static final Logger log = LoggerFactory.getLogger(TrackitServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TrackitServerApplication.class, args);
        log.info("🚀 TrackIt Server started successfully! : port :: {}", portNbr);
    }
}
