/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * this enum is finally a class param that defines all user roles enabled over spring boot security ant path
 * @author taleb
 */

public enum Role implements GrantedAuthority{
    ADMIN,USER,CLIENT,OTHER;
    
    
    @Override
    public String getAuthority() {
        return "ROLE_"+this.name();
    }
    
    @Override
    public String toString() {
        return "ROLE_"+this.name();
    }
    
    
}
