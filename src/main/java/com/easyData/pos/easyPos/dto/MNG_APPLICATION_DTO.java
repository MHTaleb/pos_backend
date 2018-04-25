/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import com.easyData.pos.easyPos.rest.model.component.v1.MNG_APPLICATION;

/**
 *
 * @author taleb
 */
public class MNG_APPLICATION_DTO {
    
    private String app_name;
    private Long app_id;

    public MNG_APPLICATION_DTO(MNG_APPLICATION app) {
        this.app_name = app.getApp_name();
        this.app_id = app.getId();
    }

    public MNG_APPLICATION_DTO(String app_name, Long app_id) {
        this.app_name = app_name;
        this.app_id = app_id;
    }

    public Long getApp_id() {
        return app_id;
    }

    public void setApp_id(Long app_id) {
        this.app_id = app_id;
    }
    
    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }
    
    
    
}
