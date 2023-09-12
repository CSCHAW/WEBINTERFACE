package com.ipcamera.amcrest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
            isAssignableFrom(authentication.getClass())) {
            logger.debug(String.format("login %s fail!", authentication.getName()));
            //logService.saveLog(String.format("login %s fail!", authentication.getName()));
            return false;
        }
        logger.debug(String.format("isAuthenticated %s !", authentication.isAuthenticated()));
        logService.saveLog(String.format("login %s, isAuthenticated %s", authentication.getName(),authentication.isAuthenticated()));
        return authentication.isAuthenticated();
    }

}
