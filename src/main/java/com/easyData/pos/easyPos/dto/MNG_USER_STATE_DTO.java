/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

/**
 *
 * @author taleb
 */
public class MNG_USER_STATE_DTO {
    private boolean active;

    public MNG_USER_STATE_DTO() {
    }

    
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
    
}
