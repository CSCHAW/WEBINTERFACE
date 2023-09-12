package com.ipcamera.logging.service;

import com.ipcamera.logging.dto.LogResponseDto;
import com.ipcamera.logging.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface  LogMapper {


    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "log", source = "entity.log")
    @Mapping(target = "serviceName", source = "entity.serviceName")
    @Mapping(target = "time", source = "entity.createDate")
    LogResponseDto toLogResponseDto(Log entity);

    List<LogResponseDto> toLogResponseDto(List<Log> entity);

}
