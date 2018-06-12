/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 * DTO d un utilisateur qui va etre remplacer par UOW ce DTO envoi le
 * <b>username</b> ,<b>userID</b> ,<b>List des packs</b> ,<b>aclName</b> ,
 * <b>code_extern</b>
 *
 * @deprecated
 * @author taleb
 * @see PackDTO
 */
@Component
public class UserDTO {

    private String username;

    private Long userID;

    private String code_extern;

    private List<PackDTO> packs;

    private String aclName;

    /**
     * Get the value of code_extern
     *
     * @return the value of code_extern
     */
    public String getCode_extern() {
        return code_extern;
    }

    /**
     * Set the value of code_extern
     *
     * @param code_extern new value of code_extern
     */
    public void setCode_extern(String code_extern) {
        this.code_extern = code_extern;
    }

    /**
     * Get the value of aclName
     *
     * @return the value of aclName
     */
    public String getAclName() {
        return aclName;
    }

    /**
     * Set the value of aclName
     *
     * @param aclName new value of aclName
     */
    public void setAclName(String aclName) {
        this.aclName = aclName;
    }

    /**
     * construcetur avec paramaitre
     *
     * @param username nom utilisateur
     * @param userID ID utilisateur
     */
    public UserDTO(String username, Long userID) {
        this.username = username;
        this.userID = userID;
    }

    /**
     * construceteur simple sans paramaitre
     */
    public UserDTO() {
    }

    /**
     * lire la liste des pack de cette utilisateur
     *
     * @return retourn une liste des Pack lier a cette utilisateur
     */
    public List<PackDTO> getPacks() {
        return packs;
    }

    /**
     * mettre la valeur des pack utilisateur
     *
     * @param packs nouvelle valeur des pack utilisateur
     */
    public void setPacks(List<PackDTO> packs) {
        this.packs = packs;
    }

    /**
     * Get the value of userID
     *
     * @return the value of userID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Set the value of userID
     *
     * @param userID new value of userID
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
