/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

import com.easyData.pos.easyPos.dto.EasyDataSecuritySequencer;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * cette class reunis tout ce qui est de commin entre les controlleurs ( api
 * rest ) elle a comme objectif de : gerer les session http gerer l utilisateur
 * en cours conencter gerer le sequenceur gerer les modele de reponse des api
 * Rest
 *
 * @author taleb
 */
@Component
public class GenericController {

    /* Session http*/
    /**
     *
     */
    @Autowired
    protected HttpSession httpSession;

    /* Variable de session*/
    /**
     *
     */
    @Autowired
    protected HttpSessionVars httpSessionVars;

    /* bean de reponse stereotype d une requete */
    /**
     *
     */
    @Autowired
    protected ServerResponse serverResponse;

    /* sequenceur */
    /**
     *
     */
    @Autowired
    protected EasyDataSecuritySequencer dataSecuritySequencer;

    /**
     * cette methode sert a connaitre l etulisateur qui s ai connecter au
     * serveur portant la session en cours sachant que spring boot genere des
     * session au niveau servlet a chaque connexion
     *
     * @return le compte utilisateur de la session en cours
     */
    protected MNG_USER getCurrentUser() {

        return (MNG_USER) httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER);
    }

    /**
     * Verifier que la session en cours a passer l authetifiaction
     *
     * @return true si la session a ete deja authentifier, false si cette
     * session n'as jamais ete authentifier
     */
    protected boolean isSessionValid() {

        System.out.println("current user is " + httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER));
        return httpSession.getServletContext().getAttribute(httpSessionVars.CURRENT_USER) != null || true;
    }

    /**
     * cette methode permet de preparer une reponse de reussite du traitement de
     * l api rest
     *
     * @param responseBody
     */
    protected void initSuccessResponse(Object responseBody) {
        serverResponse.setContent(responseBody);
        serverResponse.setMessage("success");
        // add security handshake using easydatasequecer
    }

    /**
     * cette methode permet de preparer une reponse d'échec du traitement de l
     * api rest
     *
     * @param content
     */
    protected void initFailResponse(final String content) {
        serverResponse.setContent(content);
        serverResponse.setMessage("failure");
    }

    /**
     * cette methode permet de preparer une reponse indiquant qu'une
     * authentification ets necessaire pour se servir de l api rest
     */
    protected void initFailLoginResponse() {
        serverResponse.setContent("you should be logged in");
        serverResponse.setMessage("failure");
    }

    /**
     * cette methode permet de preparer une reponse de l'échec d'une supression
     * de données via l api rest
     *
     * @param id
     */
    protected void initFailDeleteResponse(Long id) {
        serverResponse.setContent("Delete failure no mapping for id : " + id);
        serverResponse.setMessage("failure");
    }
}
