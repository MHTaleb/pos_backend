/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.dto.component.ComponentForm;
import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import com.easyData.pos.easyPos.service.ComponentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(ApplicationController.SERVICE_URI)
public class ApplicationController extends GenericController {

    public static final String SERVICE_URI = "/applications";

    @Autowired
    private ComponentService componentService;

    @GetMapping(value = "/user/{user_id}")
    private ServerResponse getApplications(@PathVariable(name = "user_id") final Long user_id) {

        if (isSessionValid()) {
            List<MNG_COMPOSANT> userApps = componentService.getAllByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
            initSuccessResponse(userApps);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    @GetMapping
    private ServerResponse getAllApps() {
        
        if(isSessionValid()){
            List<MNG_COMPOSANT> allApps = componentService.getAllByType(MNG_COMPOSANT_TYPE.APPLICATION);
            initSuccessResponse(allApps);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
        
    }

    @GetMapping("/{app_id}")
    private ServerResponse getApp(
            @PathVariable("app_id") final Long app_id
    ) {
        
        if(isSessionValid()){
            System.out.println("id of app "+app_id);
            MNG_COMPOSANT app = componentService.getComponent(app_id,MNG_COMPOSANT_TYPE.APPLICATION);
            initSuccessResponse(app);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
        
    }

    @PostMapping
    private ServerResponse createApp(
        @RequestParam("componentForm") final ComponentForm applicationForm
            
    ){
        System.out.println("here");
        if (isSessionValid()) {
          
            
            initSuccessResponse(componentService.createComponent(MNG_COMPOSANT_TYPE.APPLICATION, applicationForm.getId_fils(), applicationForm.getId_parents(),applicationForm.getId_datas()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    

    @PutMapping
    private ServerResponse editApp(
        @RequestParam("componentForm") final ComponentForm applicationForm , @RequestParam final Long id
            
    ){
        System.out.println("here");
        if (isSessionValid()) {
          
            
            initSuccessResponse(componentService.editComponent(id, MNG_COMPOSANT_TYPE.APPLICATION, applicationForm.getId_fils(), applicationForm.getId_parents(), applicationForm.getId_datas()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    @DeleteMapping
    private ServerResponse deleteApp(
            @RequestParam("app_id") final Long app_id
    ){
        if (isSessionValid()) {
            componentService.removeComponent(app_id);
            initSuccessResponse("deleted");
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
        
    }
    
}
