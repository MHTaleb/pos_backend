/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller;

import com.easyData.pos.easyPos.dto.MNG_USER_DTO;
import com.easyData.pos.easyPos.rest.model.MNG_USER;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
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
@RequestMapping(LoginController.SERVICE_URI)
public class LoginController {

    public static final String SERVICE_URI = "/logins";

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/{us_username}/{us_pwdusr}", method = RequestMethod.GET)
    public MNG_USER_DTO connecte(@PathVariable final String username, @PathVariable final String password) {

        // preparation du dto de reponse
        MNG_USER_DTO response = new MNG_USER_DTO();
        // recuperation du compte
        MNG_USER user = repository.doConnect(username, password);

        //l'ensemble des condition pre requise pour un user
        //verifier que la lecture est bien passé
        if (checkUserFetched(user, username, password)) // verifier que le user exist    
        {
            if (checkUserDataWellFormed(user)) {
                // get all needed user information from usertable
                
                // get all needed apps informations
                // get security sequence number

                // create spring session
            }
        }

        //le post traitement mise a jour metier
        return response;
    }

    private boolean checkUserFetched(MNG_USER user, String username, String password) {
        boolean isNull = user == null;
        if (isNull) {
            //do log
            //do trace
            //show in console
            System.out.println("user login failed at jpa level : using " + username + " - " + password);
            return false;
        }

        return true;
    }

    private boolean checkUserDataWellFormed(MNG_USER user) {
        try {
            
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
