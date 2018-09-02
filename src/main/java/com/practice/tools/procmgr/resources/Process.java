/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.tools.procmgr.resources;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ram
 */
@XmlRootElement(name = "process")
public class Process {
    public Process(){
        super();
    }
    
    public Process(String name){
        this.name = name;
    }
    
    private String name;
    private String status;
    private List<String> commandOutput = new ArrayList<>();
    private String action;

    public List<String> getCommandOutput() {
        return commandOutput;
    }

    public void setCommandOutput(List<String> commandOutput) {
        this.commandOutput = commandOutput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String actionToBePerformed) {
        this.action = actionToBePerformed;
    }   
    
    public Process clone(){
        return new Process(this.name);
    }
}
