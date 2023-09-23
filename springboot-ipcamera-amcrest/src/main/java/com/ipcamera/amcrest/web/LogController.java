package com.ipcamera.amcrest.web;

import com.ipcamera.amcrest.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class LogController {


    @Autowired
    private LogService logService;

    @GetMapping("/buttonLog")
    public void buttonLog(@RequestParam String buttonName, HttpServletRequest request) {
        addLog(request,buttonName + " is clicked ");
    }

    private void addLog(HttpServletRequest request, String message) {
        String ip = Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr());
        if (ip.equals("0:0:0:0:0:0:0:1")) ip = "127.0.0.1";
        Assert.isTrue(ip.chars().filter($ -> $ == '.').count() == 3, "Illegal IP: " + ip);


        StringBuilder builder = new StringBuilder();
        builder.append(message);
        builder.append(",IP:");
        builder.append(ip);
        builder.append(",Server name:");
        builder.append(request.getServerName());
        builder.append(",Server port:");
        builder.append(request.getServerPort());
        logService.saveLog(builder.toString());
    }
}
