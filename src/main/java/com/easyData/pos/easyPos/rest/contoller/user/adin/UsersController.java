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
 * la Rest api de gestion d utilisateur <b> à ne pas confondre avec l api d authentification</b>
 * cette api sert a faire les operaion crud sur les utilisateur
 * @author taleb
 */
@RestController
@RequestMapping(UsersController.SERVICE_URI)
public class UsersController extends GenericController {

    /**
     * lien de l api
     */
    public static final String SERVICE_URI = "/users";

    @Autowired
    private UserPackService packService;

    @Autowired
    private UserEntrepriseService userService;

    

    /**
     * recuperer la liste de tout les utilisateurs
     */
    @GetMapping
    protected ServerResponse getAllUsers() {
        if (isSessionValid()) {

            initSuccessResponse(userService.getllAllUsersLight());
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    /**
     * recuperer la liste des utilisateur qui possede un pack specifique
     * @param pack_id le id du pack 
     * @return la liste d utilisateur qui ont une inscription au pack
     */
    @GetMapping("/pack/{pack_id}")
    protected ServerResponse getUsersByPack(
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
    
    /**
     * recuperer un utilisateur specifique depuis son id
     * @param user_id le id de l utilisateur
     * @return le compte utilisateur de ce ID
     */
    @GetMapping("/{user_id}")
    protected ServerResponse getUsersByID(
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

    /**
     * pour suprimer un compte utilisateur
     * @param id id du compte a suprimer
     * @return message de reussite ou message d erreur
     */
    @AdminSecured
    @DeleteMapping
    protected ServerResponse deleteUser(@RequestParam Long id) {
        if (isSessionValid()) {

            initSuccessResponse(userService.delete(id));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    /**
     * pour creer un utilisateur
     * @param userForm formulaire d insertion d un utilisateur, le id dans le formulaire n ets pas obligatoir vu sa generation automatique
     * @see UserFormDTO le formulaire
     * @return le nouveau utilisateur avec son id ou un message d erreur en cas d echec
     */
    @AdminSecured
    @PostMapping
    protected ServerResponse createUser(@RequestBody UserFormDTO userForm) {
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

    /**
     * pour mettre a jour un utilisateur
     * @param userForm formulaire de mise a jour
     * @see UserFormDTO
     * @return le compte utilisateur avec la nouvelle modification
     */
    @AdminSecured
    @PutMapping()
    protected ServerResponse updateUser(@RequestBody UserFormDTO userForm) {
        if (isSessionValid()) {

            initSuccessResponse(
                    userService.update(userForm.getIdUser(),
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
