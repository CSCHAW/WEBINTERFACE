package com.ipcamera.logging.service;

import com.ipcamera.logging.dto.LogResponseDto;

import java.util.List;

public interface LogService {
    void saveLog(String info);

    void saveLog(String serviceName,String info);

    List<LogResponseDto> getAllLogs();

    List<LogResponseDto> getCowriteAllLogs();
}
