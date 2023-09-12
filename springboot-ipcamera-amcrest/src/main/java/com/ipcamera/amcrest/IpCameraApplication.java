package com.ipcamera.amcrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IpCameraApplication {

	private static final Logger logger = LoggerFactory.getLogger(IpCameraApplication.class);


	public static void main(String[] args) {

		logger.info("STARTING THE APPLICATION");
		SpringApplication.run(IpCameraApplication.class, args);
	}

}
