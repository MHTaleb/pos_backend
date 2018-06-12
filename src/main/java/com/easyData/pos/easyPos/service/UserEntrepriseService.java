/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.service;

import com.easyData.pos.easyPos.dto.AppDTO;
import com.easyData.pos.easyPos.dto.PackDTO;
import com.easyData.pos.easyPos.dto.UserDTO;
import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.repositoy.NiveauAccessRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserLangRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserPackRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ceci est un service metier de la gestion des utilisateur
 * il nous permet d ajouter un utilisateur, de modifier et de suprimmer.
 * @author taleb
 */
@Service
public class UserEntrepriseService {
    
    @Autowired
    private UserRepository userRepository;

    /**
     * recuperé la liste des utilisateur avec un formatage de données leger
     * qui contient : code_interne(id), code_externe, username, acl, liste des pack
     * les pack sont uassi formter legerement 
     * ils contiennent : pack_id , pack_name, liste des applicaiton
     * la liste des application est limité a : applicaiton_id, les details, type_applicaiton
     * @return
     */
    public List<UserDTO> getllAllUsersLight() {
        List<UserDTO> users = new ArrayList();
        userRepository.findAll().parallelStream().forEach(user -> {
            final UserDTO userDTO = new UserDTO();
            userDTO.setCode_extern(user.getCode_externe());
            userDTO.setUserID(user.getId());
            userDTO.setUsername(user.getUtilisateur());
            userDTO.setAclName(user.getNiveauAcces().getAclTitle());
            List<PackDTO> packs = new ArrayList();
            user.getMNG_PACKs().parallelStream().forEach(pack -> {
                final PackDTO packDTO = new PackDTO();
                packDTO.setPackID(pack.getId());
                packDTO.setPackName(pack.getPackName());
                packs.add(packDTO);
                List<AppDTO> apps = new ArrayList();
                pack.getMng_composants().parallelStream().forEach(app -> {
                    AppDTO appDTO = new AppDTO();
                    appDTO.setId(app.getId());
                    appDTO.setCmp_datas(app.getCmp_datas());
                    appDTO.setCmp_type(app.getCmp_type());
                    apps.add(appDTO);
                });
                packDTO.setApps(apps);
            });
            userDTO.setPacks(packs);
            users.add(userDTO);
        });
        return users;
    }

    /**
     * pour suprimmer un utilisateur
     * @param id
     * @return
     */
    public boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }

    /**
     *
     */
    @Autowired
    protected HttpSession httpSession;
    
    /**
     *
     */
    @Autowired
    protected HttpSessionVars httpSessionVars;
    
    /**
     * pour avoir l utilisateur connecté a cette session http
     * @return
     */
    protected MNG_USER getCurrentUser(){
        
        return (MNG_USER) httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER) ;
    }
    
    @Autowired
    private UserLangRepository langRepository;
    @Autowired
    private UserPackRepository packRepository;
    @Autowired
    private NiveauAccessRepository accessRepository;
    
    /**
     * pour creer un utilisateur
     * @param code_externe code externe
     * @param dateDebut date debut de validité du compte
     * @param dateFin date fin de validité
     * @param etatUser etat utilisateur true ou false
     * @param idLangue id de la langue de cet utilisateur
     * @param idAccess id du acl de cet utilisateur
     * @param idsPack list des id des packs de cet utilisateur
     * @param nom nom de l utilisateur
     * @param password mot de passe
     * @param prenom prenom
     * @param username nom du compte utilisateur
     * @return
     */
    public MNG_USER createUser(String code_externe, java.util.Date dateDebut, java.util.Date dateFin, boolean etatUser,Long idLangue,Long idAccess, List<Long> idsPack, String nom, String password, String prenom, String username) {
        MNG_USER user = new MNG_USER();
        user.setCode_externe(code_externe);
        user.setDateCreation(Date.valueOf(LocalDate.now()));
        user.setDateDebut(dateDebut);
        user.setDateFin(dateFin);
        user.setDateMiseAJour(Date.valueOf(LocalDate.now()));
        user.setDernierUtilisateur(getCurrentUser());
        user.setEtatUtilisateur(etatUser);
        user.setLangueUtilisateur(langRepository.findById(idLangue).get());
        user.setLastConnexion(Date.valueOf(LocalDate.of(2000, Month.MARCH, 1)));
        user.setLastProg(null);
         if(idsPack.isEmpty())idsPack.add(Long.valueOf(-1));
        user.setMNG_PACKs(packRepository.findAllByIds(idsPack));
        user.setNiveauAcces(accessRepository.getOne(idAccess));
        user.setNom(nom);
        user.setNombreErreur(0);
        user.setPassword(password);
        user.setPrenom(prenom);
        user.setTypeUtilisateur(null);
        user.setUtilisateur(username);
        MNG_USER createdUser = userRepository.save(user);
        return createdUser;
    }
    
    /**
     * pour modifier un utilisateur
     * @param idUser id de l utilisateur a modifier
     * @param code_externe nouveau code externe
     * @param dateDebut nouvelle date debut de validité
     * @param dateFin nouvelle date de fin de validité
     * @param etatUser nouveau etat utilsateur
     * @param idLangue id de la nouvelle langue utilisateur
     * @param idAccess id du nouveau acl affecté a cet utilisateur
     * @param idsPack la nouvelle liste des id des pack lié a cet utilisateur
     * @param nom le nouveau nom ed cet utilisateur
     * @param password le nouveau mot de passe
     * @param prenom le nouveau prenom
     * @param username le nouveau nom du compte utilisateur
     * @return acquitement du succes de l operation via une representation json/objet de la modification
     * @see MNG_USER
     */
    public MNG_USER update(Long idUser,String code_externe, java.util.Date dateDebut, java.util.Date dateFin, boolean etatUser,Long idLangue,Long idAccess, List<Long> idsPack, String nom, String password, String prenom, String username) {
        MNG_USER user = userRepository.getOne(idUser);
        user.setCode_externe(code_externe);
        user.setDateDebut(dateDebut);
        user.setDateFin(dateFin);
        user.setDateMiseAJour(Date.valueOf(LocalDate.now()));
        user.setDernierUtilisateur(getCurrentUser());
        user.setEtatUtilisateur(etatUser);
        user.setLangueUtilisateur(langRepository.findById(idLangue).get());
        user.setLastConnexion(Date.valueOf(LocalDate.of(2000, Month.MARCH, 1)));
        user.setLastProg(null);
        user.getMNG_PACKs().clear();
        if(idsPack.isEmpty())idsPack.add(Long.valueOf(-1));
        user.getMNG_PACKs().addAll(packRepository.findAllByIds(idsPack));
        user.setNiveauAcces(accessRepository.getOne(idAccess));
        user.setNom(nom);
        user.setNombreErreur(0);
        user.setPassword(password);
        user.setPrenom(prenom);
        user.setTypeUtilisateur(null);
        user.setUtilisateur(username);
        MNG_USER createdUser = userRepository.save(user);
        return createdUser;
    }
    
    /**
     * recherche un utilisateur dans la bdd par son id 
     * @param userID id de l utilisateur 
     * @return
     */
    public MNG_USER getOne(Long userID){
        return userRepository.getOne(userID);
    }
}
