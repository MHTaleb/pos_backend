/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto.component;

import java.util.List;
import java.util.Objects;

/**
 * Formulaire global des composant qui peuvent etre sois une application, menu
 * ou fonction etuliser comme signature du service Rest
 * <b>"SERVER_URL/components"</b>
 *
 * @author taleb
 */
public class ComponentForm {

    private String component_name;

    private List<Long> id_fils;
    private List<Long> id_parents;
    private List<Long> id_datas;

    /**
     * lire nom de ce compsant
     *
     * @return nom de ce compsant
     */
    public String getComponent_name() {
        return component_name;
    }

    /**
     * mettre nom de ce compsant
     *
     * @param component_name nom de ce compsant
     */
    public void setComponent_name(String component_name) {
        this.component_name = component_name;
    }

    /**
     * lire les fils de ce composant
     *
     * @return les fils de ce composant
     */
    public List<Long> getId_fils() {
        return id_fils;
    }

    /**
     * mettre les fils de ce composant
     *
     * @param id_fils les fils de ce composant
     */
    public void setId_fils(List<Long> id_fils) {
        this.id_fils = id_fils;
    }

    /**
     * lire parents de ce composant
     *
     * @return parents de ce composant
     */
    public List<Long> getId_parents() {
        return id_parents;
    }

    /**
     * mettre parents de ce composant
     *
     * @param id_parents parents de ce composant
     */
    public void setId_parents(List<Long> id_parents) {
        this.id_parents = id_parents;
    }

    /**
     * lire les information de ce composant
     *
     * @return les information de ce composant
     */
    public List<Long> getId_datas() {
        return id_datas;
    }

    /**
     * mettre les information de ce composant
     *
     * @param id_datas les information de ce composant
     */
    public void setId_datas(List<Long> id_datas) {
        this.id_datas = id_datas;
    }

    /**
     * constructeur paramaitré
     *
     * @param component_name nom du composant
     * @param id_fils les fils de ce composant
     * @param id_parents les parent de ce composant
     * @param id_datas les information de ce composant
     */
    public ComponentForm(String component_name, List<Long> id_fils, List<Long> id_parents, List<Long> id_datas) {
        this.component_name = component_name;
        this.id_fils = id_fils;
        this.id_parents = id_parents;
        this.id_datas = id_datas;
    }

    /**
     * pour la serialisation
     *
     * @return crc
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.component_name);
        hash = 89 * hash + Objects.hashCode(this.id_fils);
        hash = 89 * hash + Objects.hashCode(this.id_parents);
        hash = 89 * hash + Objects.hashCode(this.id_datas);
        return hash;
    }

    /**
     * fonction de comparaison necessaire pour les list
     *
     * @param obj comparé
     * @return vrai si egaux sinon faux
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComponentForm other = (ComponentForm) obj;
        if (this.component_name.toLowerCase().equals(other.component_name.toLowerCase())) {
            return false;
        }
        if (!Objects.equals(this.id_fils, other.id_fils)) {
            return false;
        }
        if (!Objects.equals(this.id_parents, other.id_parents)) {
            return false;
        }
        return Objects.equals(this.id_datas, other.id_datas);
    }

    /**
     * constructeur simple
     */
    public ComponentForm() {
    }

    /**
     * @deprecated a remplacer par jackson api
     * @return representation psoedo json
     */
    @Override
    public String toString() {
        return "{" + "app_name=" + component_name + ", id_fils=" + id_fils + ", id_parents=" + id_parents + ", id_datas=" + id_datas + '}';
    }

}
