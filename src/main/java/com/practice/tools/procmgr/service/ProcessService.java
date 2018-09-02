/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.tools.procmgr.service;

import com.practice.tools.procmgr.model.CommandExecutor;
import com.practice.tools.procmgr.resources.Process;

/**
 *
 * @author Ram
 */
public class ProcessService {

    public static Process executeProcess(Process process) throws Exception{
        return CommandExecutor.getInstance(process.getAction()).execute(process);
    }
    
}
