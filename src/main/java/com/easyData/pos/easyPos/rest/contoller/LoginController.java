/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller;

import com.easyData.pos.easyPos.dto.MNG_USER_DTO;
import com.easyData.pos.easyPos.dto.MNG_USER_STATE_DTO;
import com.easyData.pos.easyPos.rest.model.MNG_USER;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public Long register(
            @RequestParam final String us_username,
            @RequestParam final String us_pwdusr,
            @RequestParam final String us_cextusr,
            @RequestParam final String us_nomusr,
            @RequestParam final String us_prnusr,
            @RequestParam final Date us_datdeb,
            @RequestParam final Date us_datfin,
            @RequestParam final Long us_etatusr,
            @RequestParam final Long us_langue,
            @RequestParam final Long us_lastuser,
            @RequestParam final Long us_typusr,
            @RequestParam final Long us_lastprg,
            @RequestParam final Long us_nivacc
    ) {

        if (isFineParam(us_username, us_pwdusr, us_cextusr, us_nomusr, us_prnusr, us_prnusr, us_datdeb, us_datfin, us_etatusr, us_langue, us_lastuser, us_typusr, us_lastprg, us_nivacc)) {

            MNG_USER mng_user = new MNG_USER();
            mng_user.setUs_cextusr(us_cextusr);
            Date us_datcre = Date.from(Instant.now());
            mng_user.setUs_datcre(us_datcre);
            mng_user.setUs_datdeb(us_datdeb);
            mng_user.setUs_datfin(us_datfin);
            mng_user.setUs_datmaj(us_datcre);
            mng_user.setUs_nbrerr(0);
            mng_user.setUs_nomusr(us_nomusr);
            mng_user.setUs_prnusr(us_prnusr);
            mng_user.setUs_pwdusr(us_pwdusr);
            mng_user.setUs_username(us_username);
            repository.save(mng_user);

            return mng_user.getUs_usrint();

        }
        return new Long(-1);
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public List<MNG_USER_DTO> connecte(
            @RequestParam(name = "us_username") final String us_username,
            @RequestParam(name = "us_pwdusr") final String us_pwdusr
    ) {

        System.out.println("username "+us_username);
        System.out.println("password "+us_pwdusr);
        
        // preparation du dto de reponse
        List<MNG_USER_DTO> responses = new ArrayList<>();
        // recuperation du compte
        List<MNG_USER> users = repository.doConnect(us_username, us_pwdusr);

        //l'ensemble des condition pre requise pour un user
        //verifier que la lecture est bien passé
        users.stream().map((user) -> {
            MNG_USER_DTO response = new MNG_USER_DTO();
            if (checkUserFetched(user, us_username, us_pwdusr)) // verifier que le user exist
            {
                if (checkUserDataWellFormed(user)) {
                    // get all needed user information from usertable
                    response.setUs_cextusr(user.getUs_cextusr());
                    response.setUs_datfin(user.getUs_datfin());
                    response.setUs_prnusr(user.getUs_prnusr());
                    
                    final MNG_USER_STATE_DTO mng_user_state_dto = new MNG_USER_STATE_DTO();
                    mng_user_state_dto.setActive(true);
                    response.setUs_etatusr(mng_user_state_dto);
                    
                    // get all needed apps informations
                    // get security sequence number
                    // create spring session
                }
            }
            return response;
        }).forEachOrdered((response) -> {
            responses.add(response);
        });
        //le post traitement mise a jour metier
        return responses;
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

    private boolean isFineParam(String us_username, String us_pwdusr, String us_cextusr, String us_nomusr, String us_prnusr, String us_prnusr0, Date us_datdeb, Date us_datfin, Long us_etatusr, Long us_langue, Long us_lastuser, Long us_typusr, Long us_lastprg, Long us_nivacc) {

        return true;
    }

}
