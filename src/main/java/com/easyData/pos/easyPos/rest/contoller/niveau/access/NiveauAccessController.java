/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.niveau.access;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.rest.model.Role;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_NIVEAU_ACCEE;
import com.easyData.pos.easyPos.rest.repositoy.NiveauAccessRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(NiveauAccessController.URL)
public class NiveauAccessController extends GenericController{

    public static final String URL = "/acl";

    @Autowired
    private NiveauAccessRepository niveauAccessRepository;

    @PostMapping
    private ServerResponse createACL(
                @RequestParam final List<Role> roles 
    ){
        if(isSessionValid()){
            final MNG_NIVEAU_ACCEE mng_niveau_accee = new MNG_NIVEAU_ACCEE();
            
            mng_niveau_accee.setRoles(roles);
            niveauAccessRepository.save(mng_niveau_accee);
    
            serverResponse.setMessage("success");
            serverResponse.setContent("Niveau d'accees creer avec succee");
            return serverResponse;
        }
        serverResponse.setContent("failure");
        serverResponse.setMessage("user must be authenticated");
        return serverResponse;
    }
    

}
