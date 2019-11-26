package com.os.manager.controller;

import com.os.manager.model.UserProcess;
import com.os.manager.services.ProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * MVCController
 */
@Controller
public class MVCController {

    @Autowired
    private ProcessService service;


    @GetMapping(value="/")
    public String getProcesses(Model model) {
        model.addAttribute("list", service.getProcesses());
        return "index";
    }

    @DeleteMapping(value="/remove")
    public String removeProcess(Model model, @ModelAttribute UserProcess process){

        service.deleteProcess(process);
        model.addAttribute("list", service.getProcesses());
        return "index";
    }
    
    
    
}