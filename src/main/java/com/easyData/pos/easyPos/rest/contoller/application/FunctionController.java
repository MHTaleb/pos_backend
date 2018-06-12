/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.dto.component.ComponentForm;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import com.easyData.pos.easyPos.service.ComponentService;
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
 * un controlleur pour la creation des fonctions
 * @author taleb
 */
@RestController
@RequestMapping(FunctionController.URL_PATH)
public class FunctionController extends GenericController{

    /**
     *
     */
    public static final String URL_PATH = "/fonctions";

   
     @Autowired
    private ComponentService componentService;
    
    /**
     *
     * @param componentForm
     * @return
     */
    @PostMapping
    protected ServerResponse createFonction(
            @RequestParam final ComponentForm componentForm
    ){
        
        if(isSessionValid()){
            initSuccessResponse(componentService.createComponent(MNG_COMPOSANT_TYPE.FONCTION, componentForm.getId_fils(), componentForm.getId_parents(),componentForm.getId_datas()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
        
    }
    
    /**
     *
     * @param componentForm
     * @param fonction_id
     * @return
     */
    @PutMapping
    protected ServerResponse updateFonction(
                @RequestParam final ComponentForm componentForm, @RequestParam final Long fonction_id
    ){
        if (isSessionValid()) {
            
            initSuccessResponse(componentService.editComponent(fonction_id,  MNG_COMPOSANT_TYPE.FONCTION, componentForm.getId_fils(), componentForm.getId_parents(), componentForm.getId_datas()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
                
    }
    
    /**
     *
     * @param fonction_id
     * @return
     */
    @DeleteMapping
    protected ServerResponse deleteFonction(
            @RequestParam final Long fonction_id
    ){
        if (isSessionValid()) {
            componentService.removeComponent(fonction_id);
            initSuccessResponse("Deleted");
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
                
    }
    
    /**
     *
     * @return
     */
    @GetMapping
    protected ServerResponse getAllFonctions(){
        if (isSessionValid()) {
            
            initSuccessResponse(componentService.getAllByType(MNG_COMPOSANT_TYPE.FONCTION));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    /**
     *
     * @param fonction_id
     * @return
     */
    @GetMapping("/{fonction_id}")
    protected ServerResponse getFonction(
            @PathVariable("fonction_id") final Long fonction_id
    ){
        if (isSessionValid()) {
            initSuccessResponse(componentService.getComponent(fonction_id, MNG_COMPOSANT_TYPE.FONCTION));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
   

}
