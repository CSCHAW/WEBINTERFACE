package com.ipcamera.amcrest.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private String username;
    private String password;
}
