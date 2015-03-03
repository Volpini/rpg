/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rpg.controller;

/**
 *
 * @author ECLima
 */
public class BaseController {
    
    
    public boolean hasDeletePermission(){
        return false;
    }
    
    public boolean hasEditPermission(){
        return false;
    }
}
