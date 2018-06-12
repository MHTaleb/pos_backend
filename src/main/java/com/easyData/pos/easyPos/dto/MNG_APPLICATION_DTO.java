/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.model.component.v1.MNG_APPLICATION;

/**
 * le bute de cette classe est d envlopper la reponse serveur dans un objet cela
 * permetrai aspring de l auto convertir en Json ( au futur meme un XML ) ainsi
 * on garanti que la transformation de données pendant la communication est
 * uniforme entre client et le serveur avec le temps on peu meme se servir d RMI
 * pour charger les DTO ou UOW dynamiquement au projet coté frontend
 *
 * @deprecated ceci va etre supprimer dans la prochaine version car il fesait
 * partie de l ancien model UML
 * @author taleb
 * @see MNG_COMPOSANT qui le nouveau modele dynamique d application , menu ,
 * fonction
 */
public class MNG_APPLICATION_DTO {

    private String app_name;
    private Long app_id;

    /**
     * @deprecated constructeur du DTO objet
     * @param app
     */
    public MNG_APPLICATION_DTO(MNG_APPLICATION app) {
        this.app_name = app.getApp_name();
        this.app_id = app.getId();
    }

    /**
     * @deprecated contructeur du DTO paramaitre
     * @param app_name nom de l application
     * @param app_id id de l application
     */
    public MNG_APPLICATION_DTO(String app_name, Long app_id) {
        this.app_name = app_name;
        this.app_id = app_id;
    }

    /**
     *
     * @deprecated @return id de l application
     */
    public Long getApp_id() {
        return app_id;
    }

    /**
     *
     * @deprecated @param app_id id de l applicaiton
     */
    public void setApp_id(Long app_id) {
        this.app_id = app_id;
    }

    /**
     * @deprecated @return titre de l application
     */
    public String getApp_name() {
        return app_name;
    }

    /**
     *
     * @deprecated @param app_name titre de l application
     */
    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

}
