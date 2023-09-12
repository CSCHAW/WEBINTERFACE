package com.ipcamera.amcrest.service;

import com.ipcamera.amcrest.model.User;
import com.ipcamera.amcrest.repository.RoleRepository;
import com.ipcamera.amcrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private LogService logService;
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
        logService.saveLog(String.format("User is created.Name:%s",user.getUsername()));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
