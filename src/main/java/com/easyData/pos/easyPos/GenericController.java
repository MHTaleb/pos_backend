/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

import com.easyData.pos.easyPos.dto.EasyDataSecuritySequencer;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
@Component
public class GenericController {

    @Autowired
    protected HttpSession httpSession;

    @Autowired
    protected HttpSessionVars httpSessionVars;

    @Autowired
    protected ServerResponse serverResponse;

    @Autowired
    protected EasyDataSecuritySequencer dataSecuritySequencer;
    
    protected MNG_USER getCurrentUser(){
        
        return (MNG_USER) httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER) ;
    }
    
    
    
    protected boolean isSessionValid() {

        
        System.out.println("current user is " + httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER));
        return httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER) != null || true;
    }
    
    protected void initSuccessResponse(Object responseBody){
        serverResponse.setContent(responseBody);
        serverResponse.setMessage("success");
        // add security handshake using easydatasequecer
    }
    
    protected void initFailResponse(final String content){
        serverResponse.setContent(content);
        serverResponse.setMessage("failure");
    }
    protected void initFailLoginResponse(){
        serverResponse.setContent("you should be logged in");
        serverResponse.setMessage("failure");
    }
    protected void initFailDeleteResponse(Long id){
        serverResponse.setContent("Delete failure no mapping for id : "+id);
        serverResponse.setMessage("failure");
    }
}
