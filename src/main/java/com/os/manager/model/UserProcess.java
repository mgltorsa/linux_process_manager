package com.os.manager.model;

import lombok.Data;

/**
 * UserProcess
 */
@Data
public class UserProcess {


    private String user;
    private String pid;
    private String cpu;
    private String mem;
    private String vsz;
    private String rss;
    private String tty;
    private String stat;
    private String start;
    private String time;
    private String command;

   
    
}