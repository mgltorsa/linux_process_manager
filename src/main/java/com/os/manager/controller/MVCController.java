package com.os.manager.controller;

import com.os.manager.services.ProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * MVCController
 */
@Controller
public class MVCController {

    @Autowired
    private ProcessService service;


    @GetMapping(value="/process")
    public String getProcesses(Model model) {
        model.addAttribute("list", service.getProcesses());
        return "index";
    }

    @GetMapping(value="/remove")
    public String removeProcess(Model model, @RequestParam(value = "pid") String process, RedirectAttributes atr){
        String info=service.deleteProcess(process);
        System.out.println(info);
        atr.addFlashAttribute("info", info);
        return "redirect:process";
    }
    
    
    
}