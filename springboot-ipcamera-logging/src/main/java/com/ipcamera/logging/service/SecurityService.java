package com.ipcamera.logging.service;

public interface SecurityService {
    boolean isAuthenticated(String userName, String password);
}
