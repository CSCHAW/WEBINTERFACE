package com.ipcamera.logging.web;

import com.ipcamera.logging.dto.LogDto;
import com.ipcamera.logging.dto.LogResponseDto;
import com.ipcamera.logging.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/log", method = { RequestMethod.POST, RequestMethod.GET})
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> log(@RequestBody LogDto logDto) {

        logService.saveLog(logDto.getServiceName(),logDto.getInfo());
        return new ResponseEntity<String>("Saved", HttpStatus.OK);
    }

    @GetMapping("/all")
    public void gelAllLogs(Model model) {
        List<LogResponseDto> logs=logService.getAllLogs();
        model.addAttribute("logs",logs);
    }

}
