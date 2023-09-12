package com.ipcamera.amcrest.web;

import com.ipcamera.amcrest.dto.UserDto;
import com.ipcamera.amcrest.service.LogService;
import com.ipcamera.amcrest.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private LogService logService;


    @GetMapping("/login")
    public String login(@ModelAttribute UserDto userDto, Model model, String error, String logout, HttpServletRequest request) {
        if (securityService.isAuthenticated()) {
            addLog(request,"login page is opened");
            logService.saveLog(String.format("isAuthenticated:%s",true));
            return "redirect:/";
        }

        if (error != null){
            addLog(request,"login page is opened");
            model.addAttribute("error", "Your username and password is invalid.");
            addLog(request,"Your username and password is invalid.");
        }

        if (logout != null){
            addLog(request,"login page is opened");
            model.addAttribute("message", "You have been logged out successfully.");
            addLog(request,"You have been logged out successfully.");
        }

        return "login";
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

    @GetMapping({"/","/live"})
    public String welcome(Model model,HttpServletRequest request) {

        if (!securityService.isAuthenticated()) {
            return "redirect:/login";
        }
        addLog(request,"live page is opened");
        return "live";
    }

}
