package com.ipcamera.logging.service;

import com.ipcamera.logging.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    Boolean isUserExist(String username, String password);


}
