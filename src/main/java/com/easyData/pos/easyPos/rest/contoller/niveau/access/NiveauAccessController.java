/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.niveau.access;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.annotation.security.AdminSecured;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.Role;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_NIVEAU_ACCEE;
import com.easyData.pos.easyPos.rest.repositoy.NiveauAccessRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 * 
 * <b> <h2>par définition un ACL est une combinaison de Roles </h2> </b>
 * 
 * refactoring needed I will delagate all pre request handling of session to a handlerInterceptor that I just found about in
 * http://javasampleapproach.com/java-integration/use-springmvc-handlerinterceptor-spring-boot
 * https://www.tuturself.com/posts/view?menuId=3&postId=1071
 * 
 */
@AdminSecured
@RestController
@RequestMapping(NiveauAccessController.URL)
public class NiveauAccessController extends GenericController{

    /**
     * url du service des niveeau d access (acl : access level)
     */
    public static final String URL = "/acl";

    @Autowired
    private NiveauAccessRepository niveauAccessRepository;

    /**
     * Post pour creer un nouveau ACL via un titre et la liste des roles
     * exemple : SERVER_URL/acl?aclTitle=user&roles=ADMIN,CLIENT
     * @param aclTitle titre du nouveau ACL
     * @param roles List des Roles
     * @return une reponse serveur avec acl creer en succes ou message d erreur en cas d echec
     * @see Role
     */
    @PostMapping
    protected ServerResponse createACL(
                @RequestParam("aclTitle") final String aclTitle,
                @RequestParam("roles") final List<String> roles 
    ){
        if(isSessionValid()){
            final MNG_NIVEAU_ACCEE mng_niveau_accee = new MNG_NIVEAU_ACCEE();
            mng_niveau_accee.setAclTitle(aclTitle);
            List<Role> enumRoles = new ArrayList();
            roles.stream().forEach(role->{
                enumRoles.add(Role.valueOf(role));
            });
            mng_niveau_accee.setRoles(enumRoles);
            niveauAccessRepository.save(mng_niveau_accee);
            initSuccessResponse(mng_niveau_accee);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    /**
     * lire la liste de tout les acl defini
     * @return la liste des acl
     */
    @GetMapping
    protected ServerResponse getAcls(){
        if (isSessionValid()) {
            
            initSuccessResponse(niveauAccessRepository.findAll());
            return serverResponse;
            
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    /**
     * recuperer un acl specifique 
     * @param id id de l acl specifique 
     * @return un acl
     */
    @GetMapping(params = "id")
    protected ServerResponse getAcl(
            @RequestParam Long id
    ){
        if (isSessionValid()) {
            
            initSuccessResponse(niveauAccessRepository.findById(id).get());
            return serverResponse;
            
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    /**
     * pour mettre a jour un acl 
     * @param id de l acl a mettre a jour
     * @param aclTitle le nouveau titre 
     * @param roles nouvelle liste des roles (List de String va etre converti en List de Role par Spring)
     * @return le nouveau ACL ou un message d erreur en ca d echec
     */
    @PutMapping(params={"id","aclTitle","roles"})
    protected ServerResponse updateACL(
            @RequestParam Long id,
            @RequestParam String aclTitle,
            @RequestParam List<Role> roles
    ){
        
        if(roles.size() > Role.values().length){
            initFailResponse(" roles set inapropriate check your roles ");
            return serverResponse;
        }
        
        if (isSessionValid()) {
            
            MNG_NIVEAU_ACCEE updatable_acl = niveauAccessRepository.findById(id).get();   
            updatable_acl.setAclTitle(aclTitle);
            updatable_acl.setRoles(roles);
            niveauAccessRepository.save(updatable_acl);
            initSuccessResponse(updatable_acl);
            return serverResponse;
            
        }
        initFailLoginResponse();
        return serverResponse;
    }
  
    /**
     * mettre ajour que le nom d un acl
     * @param id id de l acl a mettre a jour
     * @param aclTitle nouveau titre de l acl
     * @return l acl modifier ou un message  d erreur
     */
    @PutMapping(params={"id","aclTitle"})
    protected ServerResponse updateACL(
            @RequestParam Long id,
            @RequestParam String aclTitle  
    ){
        
       
        
        if (isSessionValid()) {
            
            MNG_NIVEAU_ACCEE updatable_acl = niveauAccessRepository.findById(id).get();   
            updatable_acl.setAclTitle(aclTitle);
            updatable_acl.setRoles(new ArrayList());
            niveauAccessRepository.save(updatable_acl);
            initSuccessResponse(updatable_acl);
            return serverResponse;
            
        }
        initFailLoginResponse();
        return serverResponse;
    }
  
    /**
     * pour supprimer un acl
     * @param id de l acl  a supprimer
     * @return message de suppression ou un message d erreur
     */
    @DeleteMapping
    protected ServerResponse deleteACL(
            @RequestParam Long id
    ){
        if (isSessionValid()) {
            niveauAccessRepository.deleteById(id);
            initSuccessResponse("successfully removed");
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }
    
    

}
