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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author taleb
 */
@Service
public class UserEntrepriseService {
    
    @Autowired
    private UserRepository userRepository;
    public List<UserDTO> getllAllUsersLight() {
        List<UserDTO> users = new ArrayList();
        userRepository.findAll().parallelStream().forEach(user -> {
            final UserDTO userDTO = new UserDTO();
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

    public boolean delete(Long id) {
        userRepository.deleteById(id);
        return true;
    }
    @Autowired
    protected HttpSession httpSession;
    
    @Autowired
    protected HttpSessionVars httpSessionVars;
    
    protected MNG_USER getCurrentUser(){
        
        return (MNG_USER) httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER) ;
    }
    
    @Autowired
    private UserLangRepository langRepository;
    @Autowired
    private UserPackRepository packRepository;
    @Autowired
    private NiveauAccessRepository accessRepository;
    
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
        user.setLastConnexion(Date.valueOf(LocalDate.MIN));
        user.setLastProg(null);
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
    
    public MNG_USER update(Long idUser,String code_externe, java.util.Date dateDebut, java.util.Date dateFin, boolean etatUser,Long idLangue,Long idAccess, List<Long> idsPack, String nom, String password, String prenom, String username) {
        MNG_USER user = userRepository.getOne(idUser);
        user.setCode_externe(code_externe);
        user.setDateDebut(dateDebut);
        user.setDateFin(dateFin);
        user.setDateMiseAJour(Date.valueOf(LocalDate.now()));
        user.setDernierUtilisateur(getCurrentUser());
        user.setEtatUtilisateur(etatUser);
        user.setLangueUtilisateur(langRepository.findById(idLangue).get());
        user.setLastConnexion(Date.valueOf(LocalDate.MIN));
        user.setLastProg(null);
        user.getMNG_PACKs().clear();
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
    
    public MNG_USER getOne(Long userID){
        return userRepository.getOne(userID);
    }
}
