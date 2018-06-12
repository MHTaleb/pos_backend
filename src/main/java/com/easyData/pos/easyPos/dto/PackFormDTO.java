/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import java.util.List;

/**
 * Ce DTO nous aide a recevoir un formulaire d'insertion ou de mise a jour d un
 * pack le client dois envoyer le
 * <ul>
 * <li> id : de mise a jour
 * <li> packName : pour inserer ou modifier le nom du pack
 * <li> List--Long-- : pour specifier la liste des aplication lié a ce pack
 * </ul>
 *
 * @author taleb
 */
public class PackFormDTO {

    private Long id;
    private String packName;
    private List<Long> packApplications;

    /**
     * constructeur vide pour des raisons de DEV
     */
    public PackFormDTO() {
    }

    /**
     * Constructeur avec paramètre
     *
     * @param id identifiant du pack
     * @param packName nom du pack
     * @param applicationsIDs
     */
    public PackFormDTO(Long id, String packName, List<Long> applicationsIDs) {
        this.id = id;
        this.packName = packName;
        this.packApplications = applicationsIDs;
    }

    /**
     * lire id du pack
     *
     * @return id du pack
     */
    public Long getId() {
        return id;
    }

    /**
     * mettre id du pack
     *
     * @param id nouveau ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * lire la liste des id des application liez a ce pack
     *
     * @return list d id applications
     */
    public List<Long> getPackApplications() {
        return packApplications;
    }

    /**
     * lire le nom du pack
     *
     * @return nom du pack
     */
    public String getPackName() {
        return packName;
    }

    /**
     * mettre les id des application lié a ce pack
     *
     * @param packApplications nouvelle liste des id des application lié
     */
    public void setPackApplications(List<Long> packApplications) {
        this.packApplications = packApplications;
    }

    /**
     * mettre le nouveau nom du pack
     *
     * @param packName le nom du pack
     */
    public void setPackName(String packName) {
        this.packName = packName;
    }

}
