/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.niveau.access;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(RoleController.URL)
public class RoleController extends GenericController {

    public static final String URL = "/roles";

    @GetMapping
    private ServerResponse getRoles() {
        
        if (isSessionValid()) {

            List<Role> Roles;
            Roles = new ArrayList(Arrays.asList(Role.values()));
            initSuccessResponse(Roles);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    

    @GetMapping(params = "id")
    private ServerResponse getRole(
            @RequestParam Long id 
    ) {
        
        if (isSessionValid()) {
           
            List<Role> Roles;
            Roles = new ArrayList(Arrays.asList(Role.values()[id.intValue()]));
            initSuccessResponse(Roles);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    
    

}
