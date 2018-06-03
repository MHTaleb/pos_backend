/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import java.util.List;

/**
 *
 * @author taleb
 */
public class PackDTO {

    private String packName;

    private Long packID;
    
        private List<AppDTO> apps;

    /**
     * Get the value of apps
     *
     * @return the value of apps
     */
    public List<AppDTO> getApps() {
        return apps;
    }

    /**
     * Set the value of apps
     *
     * @param apps new value of apps
     */
    public void setApps(List<AppDTO> apps) {
        this.apps = apps;
    }


    /**
     * Get the value of packID
     *
     * @return the value of packID
     */
    public Long getPackID() {
        return packID;
    }

    /**
     * Set the value of packID
     *
     * @param packID new value of packID
     */
    public void setPackID(Long packID) {
        this.packID = packID;
    }

    /**
     * Get the value of packName
     *
     * @return the value of packName
     */
    public String getPackName() {
        return packName;
    }

    /**
     * Set the value of packName
     *
     * @param packName new value of packName
     */
    public void setPackName(String packName) {
        this.packName = packName;
    }

}
