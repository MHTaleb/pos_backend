/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.user.adin;

import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(UsersController.SERVICE_URI)
public class UsersController {
    public static final String SERVICE_URI = "/user";
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private HttpSession httpSession;
   
    @Autowired
    private HttpSessionVars httpSessionVars;
    
    @Autowired
    private ServerResponse serverResponse;
   
    
    @RequestMapping(value = "/{user_id}",method = RequestMethod.PUT)
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

    private boolean isSessionValid() {
        
        System.out.println("to be implemented UsersController.isSessionValid");
        System.out.println("current user is "+httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER));
        return httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER) != null;
    }
    
    
    
}
