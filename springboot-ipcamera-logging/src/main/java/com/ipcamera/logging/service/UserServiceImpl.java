package com.ipcamera.logging.service;

import com.ipcamera.logging.model.User;
import com.ipcamera.logging.repository.RoleRepository;
import com.ipcamera.logging.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    //@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(null == userRepository.findByUsername(user.getUsername())){
            user.setPassword(user.getPassword());
            user.setRoles(new HashSet<>(roleRepository.findAll()));
            userRepository.save(user);
        }else{
            logger.debug(String.format("Username is exist %s!", user.getUsername()));
        }

    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean isUserExist(String username, String password) {
        return userRepository.isUserExist(username,password);
    }
}
