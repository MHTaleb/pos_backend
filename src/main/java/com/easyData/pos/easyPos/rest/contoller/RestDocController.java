/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * lien : SERVER_URL/docs ceci est proteger avec droit d utilisateur super admin
 *
 * @deprecated Controlleur de la documenation de l api REST ceci va etre
 * replacer par swagger2
 * @author taleb I have to turn this model into an entity persisted using jpa
 */
@RestController
@RequestMapping(value = RestDocController.URL_PATH)
public class RestDocController {

    /**
     *
     */
    public final static String URL_PATH = "/docs";

    /**
     *
     * @return la liste des Rest api avec methode Get
     *
     */
    @GetMapping
    @PreAuthorize(value = USER_ROLE)
    public List getApiSignature() {
        final ArrayList signatures = new ArrayList();
        signatures.add(new Doc("http://localhost:8181/logins/?us_username&us_pwdusr", "to get a user session and all profile information with the user applications list"));

        signatures.add(new Doc("http://localhost:8181/applications/{user_id}", "to get the user applications from all his subscribed packs"));

        return signatures;

    }

    /**
     *
     */
    public static final String USER_ROLE = "hasRole('ROLE_ADMIN')";

    /**
     *
     * @return la liste des API avec methode Put
     */
    @PutMapping
    public List putApiSignature() {
        final ArrayList signatures = new ArrayList();

        signatures.add(new Doc("http://localhost:8181/user/{mng_usrcint}?usr_nom&usr_prn&usr_username&usr_pwd", "to update a user profile"));

        return signatures;
    }

    /**
     *
     * @return la liste des API avec methode POST
     */
    @PostMapping
    public List postApiSignature() {
        final ArrayList signatures = new ArrayList();

        signatures.add(new Doc("http://localhost:8181/logins/?us_username&us_pwdusr&us_cextusr&us_nomusr&us_prnusr&us_datdeb&us_datfin&us_etatusr&us_langue&us_lastuser&us_typusr&us_lastprg&us_nivacc",
                "to create users"));

        return signatures;
    }

    /**
     * une classe POJO qui Represnete un documentation d api REST
     */
    protected static class Doc {

        private String restRequestModel;
        private String description;

        /**
         * constructeur
         *
         * @param restRequestModel lien
         * @param description description
         */
        public Doc(String restRequestModel, String description) {
            this.restRequestModel = restRequestModel;
            this.description = description;
        }

        /**
         * constructeur
         */
        public Doc() {
        }

        /**
         *
         * @return retourne la description
         */
        public String getDescription() {
            return description;
        }

        /**
         *
         * @return retourne le lien
         */
        public String getRestRequestModel() {
            return restRequestModel;
        }

        /**
         *
         * @param description la description a mettre
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         *
         * @param restRequestModel le lient a mettre
         */
        public void setRestRequestModel(String restRequestModel) {
            this.restRequestModel = restRequestModel;
        }

    }

}
