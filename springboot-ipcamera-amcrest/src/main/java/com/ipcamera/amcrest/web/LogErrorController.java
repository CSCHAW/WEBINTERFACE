package com.ipcamera.amcrest.web;

import com.ipcamera.amcrest.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class LogErrorController implements ErrorController {

    @Autowired
    private LogService logService;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                addLog(request,"404 error page is opened");
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                addLog(request,"505 error page is opened");
                return "/";
            }
        }
        addLog(request,"Error page is opened");
        return "error";
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

    @Override
    public String getErrorPath() {
        return "error";
    }
}
