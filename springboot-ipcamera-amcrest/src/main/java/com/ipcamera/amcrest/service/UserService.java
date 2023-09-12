package com.ipcamera.amcrest.service;

import com.ipcamera.amcrest.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
