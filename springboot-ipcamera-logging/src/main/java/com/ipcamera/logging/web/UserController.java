package com.ipcamera.logging.web;

import com.ipcamera.logging.dto.UserDto;
import com.ipcamera.logging.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private LogController logController;


    @GetMapping({"/","/login"})
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/logout")
    public String logOut(Model model){
        model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }
    @PostMapping("logUser")
    public String logUser(@ModelAttribute UserDto userDto, BindingResult errors, Model model){
        if(null == model || null == userDto.getUsername() || null == userDto.getPassword()){
            return "login";
        }

        if (securityService.isAuthenticated(userDto.getUsername(),userDto.getPassword())) {
            logController.gelAllLogs(model);
            logController.gelCowriteAllLogs(model);
            model.addAttribute("userName",userDto.getUsername());
            return "welcome";
        }else{
            model.addAttribute("error", "Your username and password is invalid.");
        }
        return "login";//"redirect:/";
    }

    @GetMapping({ "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping({ "/cowrite"})
    public String cowrite(Model model) {
        logController.gelCowriteAllLogs(model);
        return "cowrite";
    }

}
