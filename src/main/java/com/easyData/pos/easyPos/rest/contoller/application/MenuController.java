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
 *
 * @author taleb
 */
@RestController
@RequestMapping(MenuController.URL)
public class MenuController extends GenericController {
    public final static String URL = "/menus";
    
    @Autowired
    private ComponentService componentService;
    
    @PostMapping
    private ServerResponse createMenu(
            @RequestParam final ComponentForm componentForm
    ){
        
        if(isSessionValid()){
            initSuccessResponse(componentService.createComponent(MNG_COMPOSANT_TYPE.MENU, componentForm.getId_fils(), componentForm.getId_parents(),componentForm.getId_datas()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
        
    }
    
    @PutMapping
    private ServerResponse updateMenu(
             @RequestParam final ComponentForm componentForm, @RequestParam final Long menu_id
    ){
        if (isSessionValid()) {
            
            initSuccessResponse(componentService.editComponent(menu_id,  MNG_COMPOSANT_TYPE.MENU, componentForm.getId_fils(), componentForm.getId_parents(), componentForm.getId_datas()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
                
    }
    
    @DeleteMapping
    private ServerResponse deleteMenu(
            @RequestParam final Long menu_id
    ){
        if (isSessionValid()) {
            componentService.removeComponent(menu_id);
            initSuccessResponse("Deleted");
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
                
    }
    
    @GetMapping
    private ServerResponse getAllMenus(){
        if (isSessionValid()) {
            
            initSuccessResponse(componentService.getAllByType(MNG_COMPOSANT_TYPE.MENU));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    @GetMapping("/{menu_id}")
    private ServerResponse getMenu(
            @PathVariable("menu_id") final Long menu_id
    ){
        if (isSessionValid()) {
            initSuccessResponse(componentService.getComponent(menu_id, MNG_COMPOSANT_TYPE.MENU));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
}
