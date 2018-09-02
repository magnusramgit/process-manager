/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice.tools.procmgr.model;

/**
 *
 * @author Ram
 */
public interface Command<P>{
    default P execute(P process) throws Exception {
        throw new UnsupportedOperationException("Unsupported");
    }
}
