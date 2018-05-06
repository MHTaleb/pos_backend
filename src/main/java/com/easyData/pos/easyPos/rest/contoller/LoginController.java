/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.rest.contoller.tools.RequestParamVars;
import com.easyData.pos.easyPos.dto.EasyDataSecuritySequencer;
import com.easyData.pos.easyPos.rest.metier.AppService;
import com.easyData.pos.easyPos.dto.MNG_APPLICATION_DTO;
import com.easyData.pos.easyPos.dto.MNG_USER_DTO;
import com.easyData.pos.easyPos.dto.MNG_USER_STATE_DTO;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_DATA;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.repositoy.ApplicationRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(LoginController.SERVICE_URI)
public class LoginController extends GenericController {

    public static final String SERVICE_URI = "/logins";

  
    @Autowired
    private AppService appService;

    @Autowired
    private UserRepository repository;

    
    /**
     * I have to reimplement to use services ( my design is not flexible and not easely upgradable ) I should use encapsulationI have to add
     *      .user service implementation
     *      .application service implementation
     *      .server service implementation : here i should delegate user sesison registration to this service method(registerUser)
     *          this will help me check licence of the user over the server
     */
    



    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ServerResponse<MNG_USER_DTO> connecte(
            @RequestParam(name = RequestParamVars.USERNAME) final String us_username,
            @RequestParam(name = RequestParamVars.PASSWORD) final String us_pwdusr) {

        System.out.println("username " + us_username);
        System.out.println("password " + us_pwdusr);

        // recuperation du compte
        MNG_USER user = repository.doConnect(us_username, us_pwdusr);

        //l'ensemble des condition pre requise pour un user
        //verifier que la lecture est bien passé
        MNG_USER_DTO response = new MNG_USER_DTO();
        if (checkUserFetched(user, us_username, us_pwdusr)) // verifier que le user exist
        {
            if (checkUserDataWellFormed(user)) {

                httpSession.getServletContext().setAttribute(httpSessionVars.CURRENT_USER, user);

                System.out.println(httpSession.getAttribute(httpSessionVars.CURRENT_USER));

                serverResponse.setMessage("success");

                // get all needed user information from usertable
                response.setUs_cextusr(user.getCode_externe());
                response.setUs_datfin(user.getDateFin());
                response.setUs_prnusr(user.getPrenom());
                response.setUs_nomusr(user.getNom());
                response.setUs_usrint(user.getId());

                final MNG_USER_STATE_DTO mng_user_state_dto = new MNG_USER_STATE_DTO();
                mng_user_state_dto.setActive(true);
                response.setUs_etatusr(mng_user_state_dto);

                // get all needed apps informations
                List<MNG_APPLICATION_DTO> us_applications = new ArrayList<>();
                appService.getFormatedApps(user.getId()).stream().forEach(app -> {
                    Predicate<? super MNG_COMPOSANT_DATA> prdct = prd -> {
                        return prd.getCmp_attr_code().toUpperCase().equals("TITRE");
                    };
                    us_applications.add(new MNG_APPLICATION_DTO(app.getCmp_datas().stream().filter(prdct).findFirst().get().getCmp_attr_value(), app.getId()));
                });

                response.setUs_applications(us_applications);

            }
        }
        System.out.println("response fetched : " + response);

        System.out.println("response to client : " + response);
        //le post traitement mise a jour metier
        serverResponse.setContent(response);

        return serverResponse;
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
