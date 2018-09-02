/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.tools.procmgr.controller;

import com.practice.tools.procmgr.resources.Process;
import com.practice.tools.procmgr.service.ProcessService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ram
 */
@RestController
public class ProcessController {
    @RequestMapping(value="/{name}",method = RequestMethod.GET)
    public Process getStatus(@PathVariable String name){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        Process process = new Process(name);
        Process procNew = null;
        try {
            logger.info("before calling execute");
            procNew = ProcessService.executeProcess(process);
        } catch (Exception ex){            
        }        
        return procNew;
    }
    
    @RequestMapping(value="/{name}",method = RequestMethod.POST)
    public Process action(@PathVariable String name, @RequestBody Process process){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        Process procNew = null;
        try {
            logger.info("before calling execute");
            procNew = ProcessService.executeProcess(process);
        } catch (Exception ex){            
        }        
        return procNew;
    }
}
