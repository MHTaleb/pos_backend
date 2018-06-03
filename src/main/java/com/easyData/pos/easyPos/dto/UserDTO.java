/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
@Component
public class UserDTO {
    
    private String username;

    private Long userID;
    
    private List<PackDTO> packs;
    
        private String aclName;

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



    public UserDTO(String username, Long userID) {
        this.username = username;
        this.userID = userID;
    }

    public UserDTO() {
    }    
    public List<PackDTO> getPacks() {
        return packs;
    }

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
