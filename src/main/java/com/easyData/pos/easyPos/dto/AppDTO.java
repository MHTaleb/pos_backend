/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_DATA;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import java.util.List;

/**
 * Ceci est un DTO pattern implementation , il vise l envoi d une application
 * vers le client cette classe va etre traduite en JSON de la part de spring
 * boot l envoi va contenir l ID, information sur l application, ainsi son type
 * pour confirmer que c est une applicaiton
 *
 * la structure d un DTO est un POJO ou il faut specifier les attribut de la
 * classe avec un constructeur vide , un constructeur avec paramaitre et les
 * accesseurs et muttateurs.
 *
 * @author taleb
 */
public class AppDTO {

    private Long id;
    private List<MNG_COMPOSANT_DATA> cmp_datas;
    private MNG_COMPOSANT_TYPE cmp_type;

    /**
     * constructeur simple
     */
    public AppDTO() {
    }

    /**
     * constructeur avec parametre
     *
     * @param id du composant
     * @param cmp_datas les information sur l application
     * @param cmp_type type du composant (dans notre cas cela devrai tjr etre
     * type APPLICATION
     */
    public AppDTO(Long id, List<MNG_COMPOSANT_DATA> cmp_datas, MNG_COMPOSANT_TYPE cmp_type) {
        this.id = id;
        this.cmp_datas = cmp_datas;
        this.cmp_type = cmp_type;
    }

    /**
     * getter simple
     *
     * @return liste des information sur le composant (application)
     */
    public List<MNG_COMPOSANT_DATA> getCmp_datas() {
        return cmp_datas;
    }

    /**
     * getter simple
     *
     * @return type du composant ( applicaiton)
     */
    public MNG_COMPOSANT_TYPE getCmp_type() {
        return cmp_type;
    }

    /**
     * getter simple
     *
     * @return id du composant ( application )
     */
    public Long getId() {
        return id;
    }

    /**
     * setter simple
     *
     * @param cmp_datas les information du composant (application)
     */
    public void setCmp_datas(List<MNG_COMPOSANT_DATA> cmp_datas) {
        this.cmp_datas = cmp_datas;
    }

    /**
     * setter simple
     *
     * @param cmp_type le type du composant (application)
     */
    public void setCmp_type(MNG_COMPOSANT_TYPE cmp_type) {
        this.cmp_type = cmp_type;
    }

    /**
     * setter simple
     *
     * @param id du composant (application)
     */
    public void setId(Long id) {
        this.id = id;
    }

}
