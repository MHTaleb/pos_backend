/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.user.adin;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.annotation.security.AdminSecured;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.repositoy.NiveauAccessRepository;
import com.easyData.pos.easyPos.rest.repositoy.ProgramRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserLangRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserPackRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserType_Repository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(UsersController.SERVICE_URI)
public class UsersController extends GenericController{
    public static final String SERVICE_URI = "/user";
    
    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private UserRepository userRepo;
    
  
    @Autowired
    private UserLangRepository userLangRepository;
    
    @Autowired
    private UserPackRepository packRepository;
    
    @Autowired
    private UserType_Repository userTypeRepository;
    
    @Autowired
    private NiveauAccessRepository accessRepository;

    @AdminSecured
    @PostMapping
    private ServerResponse createUser(                
    ){
        return serverResponse;
    }
    
    @AdminSecured
    @PutMapping("/{user_id}")
    private ServerResponse updateUser(@PathVariable("user_id") final Long userID,
            @RequestParam("usr_nom") final String nom,
            @RequestParam("usr_prn") final String prenom,
            @RequestParam("usr_username") final String username,
            @RequestParam("usr_pwd") final String password){
        System.out.println("receiving request");
        if(isSessionValid()){
            System.out.println("update password is : "+password);
            MNG_USER mng_user = userRepo.findById(userID).get();
            mng_user.setUtilisateur(username);
            mng_user.setNom(nom);
            mng_user.setPrenom(prenom);
            mng_user.setPassword(password);
            userRepo.save(mng_user);
            
            serverResponse.setMessage("update done successfully");
        }
        
        return serverResponse;
    }

    
    
}
