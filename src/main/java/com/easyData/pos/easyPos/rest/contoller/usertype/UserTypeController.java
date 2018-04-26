/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.usertype;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_TYPE;
import com.easyData.pos.easyPos.rest.repositoy.UserType_Repository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(UserTypeController.URL)
public class UserTypeController extends GenericController{
    
    public final static  String URL = "/usertypes";
    
    @Autowired
    private UserType_Repository type_Repository;
    
    @PostMapping 
    private ServerResponse createUserType(){
        
        if(isSessionValid()){
            final MNG_USER_TYPE mng_user_type = new MNG_USER_TYPE();
            
            type_Repository.save(mng_user_type);
            initSuccessResponse(mng_user_type);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
        
    }
    
    @GetMapping
    private ServerResponse getAllUserTypes(){
        if(!isSessionValid()){
            initFailLoginResponse();
            return serverResponse;
        }
        initSuccessResponse(type_Repository.findAll());
        return serverResponse;
    }
    
    @DeleteMapping
    private ServerResponse deleteUserTypes(
            @RequestParam Long id
    ){
        if(!isSessionValid()){
            initFailLoginResponse();
            return serverResponse;
        }
        Optional<MNG_USER_TYPE> optional = type_Repository.findById(id);
        if(!optional.isPresent()){
            initFailDeleteResponse(id);
        }
        type_Repository.deleteById(id);
        initSuccessResponse("Delete done successfully");
        return serverResponse;
    }
}
