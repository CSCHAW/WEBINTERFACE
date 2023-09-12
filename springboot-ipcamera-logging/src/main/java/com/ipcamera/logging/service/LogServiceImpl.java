package com.ipcamera.logging.service;

import com.ipcamera.logging.dto.LogResponseDto;
import com.ipcamera.logging.model.Log;
import com.ipcamera.logging.repository.LogRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LogRepository logRepository;


    @Override
    public void saveLog(String info) {
        logRepository.save(new Log(info));
    }

    @Override
    public void saveLog(String serviceName, String info) {
        logRepository.save(new Log(serviceName,info));
    }

    @Override
    public List<LogResponseDto> getAllLogs() {
        LogMapper logMapper = Mappers.getMapper(LogMapper.class);
        return logMapper.toLogResponseDto(logRepository.findAll());
    }
}
