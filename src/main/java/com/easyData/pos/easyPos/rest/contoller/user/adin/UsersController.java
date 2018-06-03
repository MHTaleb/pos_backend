/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.user.adin;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.annotation.security.AdminSecured;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.dto.UserFormDTO;
import com.easyData.pos.easyPos.service.UserEntrepriseService;
import com.easyData.pos.easyPos.service.UserPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 * @author taleb
 */
@RestController
@RequestMapping(UsersController.SERVICE_URI)
public class UsersController extends GenericController {

    public static final String SERVICE_URI = "/users";

    @Autowired
    private UserPackService packService;

    @Autowired
    private UserEntrepriseService userService;

    

    /**
     * will be replaced using the RequestBody should update code in respective
     * client edit form
     */
//    @AdminSecured
//    @PutMapping("/{user_id}")
//    private ServerResponse updateUser(@PathVariable("user_id") final Long userID,
//            @RequestParam("usr_nom") final String nom,
//            @RequestParam("usr_prn") final String prenom,
//            @RequestParam("usr_username") final String username,
//            @RequestParam("usr_pwd") final String password){
//        System.out.println("receiving request");
//        if(isSessionValid()){
//            System.out.println("update password is : "+password);
//            MNG_USER mng_user = userRepo.findById(userID).get();
//            mng_user.setUtilisateur(username);
//            mng_user.setNom(nom);
//            mng_user.setPrenom(prenom);
//            mng_user.setPassword(password);
//            userRepo.save(mng_user);
//            
//            serverResponse.setMessage("update done successfully");
//        }
//        
//        return serverResponse;
//    }
    @GetMapping
    private ServerResponse getAllUsers() {
        if (isSessionValid()) {

            initSuccessResponse(userService.getllAllUsersLight());
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    
    @GetMapping("/pack/{pack_id}")
    private ServerResponse getUsersByPack(
            @PathVariable("pack_id") Long pack_id
    ) {
        if (isSessionValid()) {
            System.out.println("processed user pack id");
            initSuccessResponse(packService.getUsersByPack(pack_id));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
     @GetMapping("/{user_id}")
    private ServerResponse getUsersByID(
            @PathVariable("user_id") Long user_id
    ) {
        if (isSessionValid()) {
            System.out.println("processed user pack id");
            initSuccessResponse(userService.getOne(user_id));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    @AdminSecured
    @DeleteMapping
    private ServerResponse deleteUser(@RequestParam Long id) {
        if (isSessionValid()) {

            initSuccessResponse(userService.delete(id));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    @AdminSecured
    @PostMapping
    private ServerResponse createUser(@RequestBody UserFormDTO userForm) {
        if (isSessionValid()) {

            initSuccessResponse(
                    userService.createUser(
                            userForm.getCode_externe(),
                            userForm.getDateDebut(),
                            userForm.getDateFin(),
                            userForm.isEtatUser(),
                            userForm.getIdLangue(),
                            userForm.getIdAccess(),
                            userForm.getIdsPack(),
                            userForm.getNom(),
                            userForm.getPassword(),
                            userForm.getPrenom(),
                            userForm.getUsername())
            );
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    @AdminSecured
    @PutMapping()
    private ServerResponse updateUser(@RequestBody UserFormDTO userForm) {
        if (isSessionValid()) {

            initSuccessResponse(
                    userService.createUser(
                            userForm.getCode_externe(),
                            userForm.getDateDebut(),
                            userForm.getDateFin(),
                            userForm.isEtatUser(),
                            userForm.getIdLangue(),
                            userForm.getIdAccess(),
                            userForm.getIdsPack(),
                            userForm.getNom(),
                            userForm.getPassword(),
                            userForm.getPrenom(),
                            userForm.getUsername())
            );
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

}
