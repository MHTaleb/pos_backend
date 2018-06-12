/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.tools;

import org.springframework.stereotype.Component;

/**
 * class contenant des constante sur les nom de variable stoqué dans la session http de la servlet
 * @author taleb
 */
@Component
public class HttpSessionVars {
    
    /**
     * nom de l attribut dans la session qui contient l utilisateur en cours de la servlet
     */
    public final String CURRENT_USER = "current_user";
    
    
}
