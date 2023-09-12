package com.ipcamera.logging.service;

import com.ipcamera.logging.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{

    @Autowired
    private UserService userService;

    @Override
    public boolean isAuthenticated(String userName, String password) {
        User user = userService.findByUsername(userName);
        if(null == user){
            return false;
        }
        return userService.isUserExist(userName,password);
    }
}
