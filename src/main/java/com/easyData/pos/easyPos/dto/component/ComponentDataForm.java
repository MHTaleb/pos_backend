/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * signature de l api rest pour la creation de donnees general sur un composant
 * ce formulaire est asigner a l api <b>"SERVER_URL/components/datas"</b>
 * ce formulaire annoté en json exige que le client envoi ses donnees avec on y
 * recois le code d atribut , titre , valeur
 *
 * @author taleb
 */
@Component
public class ComponentDataForm {

    @JsonProperty("attCode")
    private String attCode;
    @JsonProperty("attTitle")
    private String attTitle;
    @JsonProperty("attVal")
    private String attVal;

    /**
     * constructeur simple
     */
    public ComponentDataForm() {
    }

    /**
     * lire le code atribut
     *
     * @return code de l attribut
     */
    public String getAttCode() {
        return attCode;
    }

    /**
     * ecrire un nouveau code attribut
     *
     * @param attCode nouveau code attribut
     */
    public void setAttCode(String attCode) {
        this.attCode = attCode;
    }

    /**
     * titre complet de l atribut
     *
     * @return titre complet de l atribut
     */
    public String getAttTitle() {
        return attTitle;
    }

    /**
     * mettre titre complet de l atribut
     *
     * @param attTitle titre complet de l atribut
     */
    public void setAttTitle(String attTitle) {
        this.attTitle = attTitle;
    }

    /**
     * Valeur de l attribut
     *
     * @return Valeur de l attribut
     */
    public String getAttVal() {
        return attVal;
    }

    /**
     * mettre valeur de l attribut
     *
     * @param attVal Valeur de l attribut
     */
    public void setAttVal(String attVal) {
        this.attVal = attVal;
    }

    /**
     * necessaire pour la serialisation
     *
     * @return CRC
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.attCode);
        hash = 29 * hash + Objects.hashCode(this.attTitle);
        hash = 29 * hash + Objects.hashCode(this.attVal);
        return hash;
    }

    /**
     * necessaire pour la comparaison, utiliser lors la comparaison dans une
     * List
     *
     * @param obj comparée
     * @return vrai si egaux faux si non
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
        final ComponentDataForm other = (ComponentDataForm) obj;
        if (!Objects.equals(this.attCode, other.attCode)) {
            return false;
        }
        if (!Objects.equals(this.attTitle, other.attTitle)) {
            return false;
        }
        if (!Objects.equals(this.attVal, other.attVal)) {
            return false;
        }
        return true;
    }

    /**
     * @deprecated a changer par un json valide par Jackson API
     * @return representation pseode JSON
     */
    @Override
    public String toString() {
        return "{" + "attCode:" + attCode + ", attTitle:" + attTitle + ", attVal:" + attVal + '}';
    }

    /**
     * constructeur avec paramaitre
     *
     * @param attCode code de l attribut exemple IMG
     * @param attTitle titre de l attribut exemple image
     * @param attVal Valeur de l attribut exemple c:/temp/logo.svg
     */
    public ComponentDataForm(String attCode, String attTitle, String attVal) {
        this.attCode = attCode;
        this.attTitle = attTitle;
        this.attVal = attVal;
    }

}
