/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

import com.easyData.pos.easyPos.dto.EasyDataSecuritySequencer;
import com.easyData.pos.easyPos.rest.model.MathFunctionModel;
import com.easyData.pos.easyPos.rest.model.Role;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_NIVEAU_ACCEE;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_PROGRAM;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_LANG;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_TYPE;
import com.easyData.pos.easyPos.rest.repositoy.NiveauAccessRepository;
import com.easyData.pos.easyPos.rest.repositoy.ProgramRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserLangRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserType_Repository;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
@Component
public class DatabaseInit implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UserRepository repo;
    
    @Autowired
    private UserLangRepository langRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private NiveauAccessRepository accessRepository;

    @Autowired
    private UserType_Repository userType_Repository;
    
    @Autowired
    private EasyDataSecuritySequencer dataSecuritySequencer;

    public void init() throws NoSuchAlgorithmException {

        long usersCount;
        try {
            usersCount = repo.count();

        } catch (Exception e) {
            usersCount = 0;
        }

        if (usersCount == 0) {

            MNG_USER u = new MNG_USER();

            u.setCode_externe("001");
            final Date dateDebut = new Date();
            u.setDateCreation(dateDebut);
            u.setDateDebut(dateDebut);
            u.setDateFin(new Date(2047 - 1900, 1, 1));
            u.setDateMiseAJour(dateDebut);
            u.setDernierUtilisateur(null);

            
            u.setEtatUtilisateur(true);

            final MNG_USER_LANG mng_user_lang = new MNG_USER_LANG();
            mng_user_lang.setCode("FR");
            u.setLangueUtilisateur(mng_user_lang);

            u.setLastConnexion(dateDebut);

            final MNG_PROGRAM program = new MNG_PROGRAM();
            program.setProgramName("Serveur MDS 0.1 ");
            u.setLastProg(program);

            u.setMNG_PACKs(null);

            MNG_NIVEAU_ACCEE niveauAccesSuperAdmin = new MNG_NIVEAU_ACCEE();

            final ArrayList<Role> roles = new ArrayList<>();
            roles.add(Role.USER);
            roles.add(Role.ADMIN);
            roles.add(Role.CLIENT);
            roles.add(Role.OTHER);
            niveauAccesSuperAdmin.setAclTitle("Niveau Access Super Admin");
            niveauAccesSuperAdmin.setRoles(roles);
            u.setNiveauAcces(niveauAccesSuperAdmin);

            u.setNom("Taleb");
            u.setNombreErreur(0);
            u.setPassword(Crypto.getSha("admin"));
            u.setPrenom("Mohammed Housseyn");

            MNG_USER_TYPE mng_user_type = new MNG_USER_TYPE();
            u.setTypeUtilisateur(mng_user_type);

            u.setUtilisateur("admin");

            userType_Repository.save(mng_user_type);
            accessRepository.save(niveauAccesSuperAdmin);
            programRepository.save(program);
            langRepository.save(mng_user_lang);
           
            repo.save(u);

            
            dataSecuritySequencer.getFunctionGenerator().getMathRepository().save(new MathFunctionModel(new Long(1), new Long(1), new Long(1), new Long(1), new Long(2)));
            System.out.println(dataSecuritySequencer.nextChallenge(4, 2));
        }

    }

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            init();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DatabaseInit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
