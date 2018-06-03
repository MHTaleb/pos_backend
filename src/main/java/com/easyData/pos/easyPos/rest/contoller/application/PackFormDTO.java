/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import java.util.List;

/**
 *
 * @author taleb
 */
public class PackFormDTO {
    private Long id;
    private String packName;
    private List<Long> packApplications;

    public PackFormDTO() {
    }

    public PackFormDTO(Long id, String packName, List<Long> applicationsIDs) {
        this.id = id;
        this.packName = packName;
        this.packApplications = applicationsIDs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getPackApplications() {
        return packApplications;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackApplications(List<Long> packApplications) {
        this.packApplications = packApplications;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }
    
    
}
