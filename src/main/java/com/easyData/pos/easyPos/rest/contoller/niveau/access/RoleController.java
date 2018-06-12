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
 * ceci est le service qui nous donne la liste des role configurer dans l enum Role
 * ce service Rest est ene lecture seul
 * @author taleb
 */
@RestController
@RequestMapping(RoleController.URL)
public class RoleController extends GenericController {

    /**
     * URL du service
     */
    public static final String URL = "/roles";

    /**
     * lecture de la liste des roles
     * @return le role
     */
    @GetMapping
    protected ServerResponse getRoles() {
        
        if (isSessionValid()) {

            List<Role> Roles;
            Roles = new ArrayList(Arrays.asList(Role.values()));
            initSuccessResponse(Roles);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    /**
     * lire un role specifique 
     * @param id id du role
     * @return le role
     */
    @GetMapping(params = "id")
    protected ServerResponse getRole(
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
