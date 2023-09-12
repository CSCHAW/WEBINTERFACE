package com.ipcamera.amcrest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogRequestDto implements Serializable {

    @JsonProperty("serviceName")
    @NotNull
    private String serviceName;

    @JsonProperty("info")
    @NotNull
    private String info;
}