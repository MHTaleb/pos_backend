/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * this enum is finally a class param that defines all user roles enabled over spring boot security ant path
 * combining this roles we can crete 16 distinc ACL
 * @author taleb
 */

public enum Role implements GrantedAuthority{

    /**
     *Role admin    
     */
    ADMIN,

    /**
     * role user
     */
    USER,

    /**
     * role client
     */
    CLIENT,

    /**
     * role others
     */
    OTHER;
    
    /**
     * une methode preparé pour spring boot qui va lui donner un role selon l impelmentation spring boot
     * @return
     */
    @Override
    public String getAuthority() {
        return "ROLE_"+this.name();
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ROLE_"+this.name();
    }
    
    
}
