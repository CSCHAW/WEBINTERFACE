package com.ipcamera.amcrest.service;


import com.ipcamera.amcrest.LogRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class LogServiceImpl implements LogService{

    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Override
    public void saveLog(String log) {
        try{
            String uri = "http://localhost:8082/log/add";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> result = restTemplate.postForEntity(uri, new LogRequestDto("ipcamera.amcrest",log), String.class);
            logger.info(result.getBody());

        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
        }

    }
}
