/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.user.adin;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.annotation.security.AdminSecured;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_NIVEAU_ACCEE;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_PACK;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_PROGRAM;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_LANG;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_STATE;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_TYPE;
import com.easyData.pos.easyPos.rest.repositoy.NiveauAccessRepository;
import com.easyData.pos.easyPos.rest.repositoy.ProgramRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserLangRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserPackRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserState_Repository;
import com.easyData.pos.easyPos.rest.repositoy.UserType_Repository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private UserState_Repository userStateRepository;
    
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
                @RequestParam String code_extern, 
                @RequestParam Date date_creation, 
                @RequestParam Date date_debut, 
                @RequestParam Date date_fin,
                @RequestParam Date date_mise_jour,
                @RequestParam Long user_state_id,
                @RequestParam Long user_lang_id,
                @RequestParam Long program_id, 
                @RequestParam Long user_type_id, 
                @RequestParam List<Long> ids,
                @RequestParam Long niveau_access_id,
                @RequestParam String nom,
                @RequestParam String password,
                @RequestParam String prenom,
                @RequestParam String username
                
    ){
        
        if(!isSessionValid()){
            initFailLoginResponse();
            return  serverResponse;
                    }
        MNG_USER user = new MNG_USER();
        user.setCode_externe(code_extern);
        user.setDateCreation(date_creation);
        user.setDateDebut(date_debut);
        user.setDateFin(date_fin);
        user.setDateMiseAJour(date_mise_jour);
        user.setDernierUtilisateur(getCurrentUser());
        
        MNG_USER_STATE etatUtilisateur = userStateRepository.getOne(user_state_id);
        if(etatUtilisateur == null){
            initFailResponse("etat utilisateur avec ID introuvable "+user_state_id);
            return serverResponse;
        }
        user.setEtatUtilisateur(etatUtilisateur);
        
        MNG_USER_LANG langUtilisateur = userLangRepository.getOne(user_lang_id);
        if(langUtilisateur == null){
            initFailResponse("langue utilisateur avec ID introuvable "+user_lang_id);
            return serverResponse;
        }
        user.setLangueUtilisateur(langUtilisateur);
        
        user.setLastConnexion(null);
        
        MNG_PROGRAM mng_program = programRepository.getOne(program_id);
        if(mng_program == null){
            initFailResponse("programe administrateur de donnée avec ID introuvable "+program_id);
        }
        user.setLastProg(mng_program);
        
        List<MNG_PACK> MNG_PACKs = packRepository.findAllByIds(ids);
        if(MNG_PACKs == null || (MNG_PACKs.isEmpty() && !ids.isEmpty() )){
            initFailResponse("laison par liste de pack invalide : un ID ou plusieur dans la liste sont introuvable dans la base de données "+ids);
        }
        user.setMNG_PACKs(MNG_PACKs);
        
        
        MNG_NIVEAU_ACCEE mng_niveau_accee = accessRepository.getOne(niveau_access_id);
        if(mng_niveau_accee == null){
            initFailResponse("niveau d access introuvable avec l ID :"+niveau_access_id);
        }
        user.setNiveauAcces(mng_niveau_accee);
        
        user.setNom(nom);
        user.setNombreErreur(0);
        user.setPassword(password);
        user.setPrenom(prenom);
        
        MNG_USER_TYPE typeUtilisateur = userTypeRepository.getOne(user_type_id);
        user.setTypeUtilisateur(typeUtilisateur);
        
        user.setUtilisateur(username);
        
        return serverResponse;
    }
    
    
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
