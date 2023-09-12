package com.ipcamera.amcrest.component;

import com.ipcamera.amcrest.model.User;
import com.ipcamera.amcrest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);
    @Override
    public void run(String...args) throws Exception {
        logger.info("CREATING ADMIN");
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("LondonAdmin");
        userService.save(admin);
    }
}