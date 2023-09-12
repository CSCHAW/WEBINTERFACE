package com.ipcamera.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class IpCameraLoggingApplication {

	private static final Logger logger = LoggerFactory.getLogger(IpCameraLoggingApplication.class);


	public static void main(String[] args) {

		logger.info("STARTING THE APPLICATION");
		SpringApplication.run(IpCameraLoggingApplication.class, args);
	}

}
