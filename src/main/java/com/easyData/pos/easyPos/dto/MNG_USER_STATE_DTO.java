/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

/**
 * DTO qui Va etre remplacer par UOW dans les prochaine version etat d un
 * utilisateur sois active sois non
 *
 * @deprecated
 * @author taleb
 */
public class MNG_USER_STATE_DTO {

    private boolean active;

    /**
     * constructeur simple
     */
    public MNG_USER_STATE_DTO() {
    }

    /**
     * setter de l'etat d activiter
     *
     * @param active true pour active false pour desactivé
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * getter d activité
     *
     * @return vrai si l utilisateur est active false si l utilisateur est
     * desactiver
     */
    public boolean isActive() {
        return active;
    }

}
