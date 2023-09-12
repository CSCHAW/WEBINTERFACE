package com.ipcamera.logging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogResponseDto implements Serializable {

    private String id;
    private String serviceName;
    private String log;
    private String time;
}
