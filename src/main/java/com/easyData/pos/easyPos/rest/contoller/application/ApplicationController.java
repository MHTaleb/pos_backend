/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.metier.AppService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(ApplicationController.SERVICE_URI)
public class ApplicationController {
    public static final String SERVICE_URI = "/applications";
    
    @Autowired
    private AppService appService;
    @Autowired
    private ServerResponse serverResponse; 
    
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private HttpSessionVars httpSessionVars;

    @RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
    private ServerResponse getApplications(@PathVariable(name = "user_id") final Long user_id){

        if(httpSession.getAttribute(httpSessionVars.CURRENT_USER) != null ){
            System.out.println("client connected");
            serverResponse = appService.getApps(user_id);
        }
        return serverResponse;
    }

    
    
    
}
