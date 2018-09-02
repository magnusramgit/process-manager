/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.tools.procmgr.model;

import com.practice.tools.procmgr.resources.Process;
import com.practice.tools.procmgr.util.ApplicationConstants;
import java.util.HashMap;
import java.util.Map;
import static com.practice.tools.procmgr.util.ApplicationConstants.Action.START;
import static com.practice.tools.procmgr.util.ApplicationConstants.Action.STOP;
import static com.practice.tools.procmgr.util.ApplicationConstants.Action.STATUS;
import static com.practice.tools.procmgr.util.ApplicationConstants.Action.PS;
import static com.practice.tools.procmgr.util.ApplicationConstants.Action.DEFAULT;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ram
 */
@Component
public class CommandExecutor implements Command<Process> {
    @Value("${executor_script}")
    private String COMMAND;
    
    static Logger logger = LoggerFactory.getLogger(CommandExecutor.class);
    
     static ProcessBuilder getNewProcBuilder(){
        return new ProcessBuilder();
    }
    
    
    private static final Map<String, Command> COMMANDS = new HashMap<>();
    
    @PostConstruct
    public void init() {
        COMMANDS.put(STOP.name(), new Stop());
        COMMANDS.put(START.name(), new Start());
        COMMANDS.put(STATUS.name(), new Status());
        COMMANDS.put(PS.name(), new ProcessList());
        COMMANDS.put(DEFAULT.name(), new Default());
    }
    
    public static Command<Process> getInstance(String action){
        Command<Process> command= COMMANDS.get(action);
        if(command == null)
            return COMMANDS.get(DEFAULT.name());
        return command;        
    }
        
    private  class Stop implements Command<Process> {
       @Override
        public Process execute(Process process) throws Exception {
            return executeHelper(process, ApplicationConstants.Action.STOP);            
        }
    }
    private  class Start implements Command<Process> {
        @Override
        public Process execute(Process process) throws Exception {
            return executeHelper(process, ApplicationConstants.Action.START);
        }
    }
    private  class Status implements Command<Process> {
        @Override
        public Process execute(Process process) throws Exception {
            return executeHelper(process, ApplicationConstants.Action.STATUS);           
        }         
    }
    
    private  class ProcessList implements Command<Process> {
        @Override
        public Process execute(Process process) throws Exception {
            return executeHelper(process, ApplicationConstants.Action.PS);           
        }         
    } 
    
    private  class Default implements Command<Process> {
        @Override
        public Process execute(Process process) throws Exception {
            return executeHelper(process,null);           
        }         
    } 
    
    private Process executeHelper(Process process, ApplicationConstants.Action action){
            Process pro1 = null;   
            ProcessBuilder procbuilder = getNewProcBuilder();
            if(action != null)
                procbuilder.command(COMMAND, action.name());
            else
                procbuilder.command(COMMAND,process.getAction());
            try {
                logger.info("before calling start on procbuilder {}",process.getAction());
                java.lang.Process proc = procbuilder.start();
                procbuilder.redirectErrorStream(true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line = null;    
                pro1 = process.clone(); 
                while((line = reader.readLine()) != null){
                    pro1.getCommandOutput().add(line);
                    logger.info(line);
                }
                 
            } catch(IOException ex){
               logger.info("Exception: {}",ex);
            }            
            return pro1; 
    }
}