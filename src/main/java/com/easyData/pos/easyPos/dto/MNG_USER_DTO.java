/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * ceci est un DTO pattern implementation qui va servir comme reponse a l
 * authetification d un utilisateur l objectif est d envoyer <br>
 * private Long us_usrint; code interne private String us_cextusr; code externe
 * private String us_nomusr; nom private String us_prnusr; prenom private Date
 * us_datfin; date fin du contrat du compte private MNG_USER_STATE_DTO
 * us_etatusr; etat de ce compte (activé,désactivé) private
 * List"MNG_APPLICATION_DTO" us_applications; lste des applicaiton lie a ce
 * compte ( cette liste va etre lise via la relation ManytoMany avec la liste
 * des pack lier a cette utilisateur cette classe DTO est deprecated elle risque
 * de disparaitre dans les prochaine version
 *
 * Le DTO Va etre remplacer par le design pattern UOW (unit of work) qui est
 * mieu adapter a nos critere et besoin L architecture va aussi adapter le
 * designe pattern DDD(Domain driven design)
 *
 * @deprecated
 * @author taleb
 */
@Component
public class MNG_USER_DTO {

    private Long us_usrint;
    private String us_cextusr;
    private String us_nomusr;
    private String us_prnusr;

    private Date us_datfin;
    private Date us_lastcnx;
    private MNG_USER_STATE_DTO us_etatusr;
    private List<MNG_APPLICATION_DTO> us_applications;

    /**
     * constructeur simple
     */
    public MNG_USER_DTO() {
        us_applications = new ArrayList<>();
    }

    /**
     * constructeur avec paramaitre
     *
     * @param us_usrint code interne utilisateur
     * @param us_cextusr code externe
     * @param us_nomusr nom utilisateur
     * @param us_prnusr prenom utilisateur
     * @param us_datfin date fin validite du compte utilisateur
     * @param us_lastcnx date de la derniere connexion
     * @param us_etatusr l etat de cette utilisateur active ou inactive
     * @param us_applications la liste des application de cette utilisateur
     */
    public MNG_USER_DTO(Long us_usrint, String us_cextusr, String us_nomusr, String us_prnusr, Date us_datfin, Date us_lastcnx, MNG_USER_STATE_DTO us_etatusr, List<MNG_APPLICATION_DTO> us_applications) {
        this.us_usrint = us_usrint;
        this.us_cextusr = us_cextusr;
        this.us_nomusr = us_nomusr;
        this.us_prnusr = us_prnusr;
        this.us_datfin = us_datfin;
        this.us_lastcnx = us_lastcnx;
        this.us_etatusr = us_etatusr;

        this.us_applications = us_applications;
    }

    /**
     * getter simple
     *
     * @return code interne qui est le ID dans la BDD
     */
    public Long getUs_usrint() {
        return us_usrint;
    }

    /**
     * setter simple
     *
     * @param us_usrint code interne
     */
    public void setUs_usrint(Long us_usrint) {
        this.us_usrint = us_usrint;
    }

    /**
     * getter simple
     *
     * @return code externe
     */
    public String getUs_cextusr() {
        return us_cextusr;
    }

    /**
     * setter simple
     *
     * @param us_cextusr code externe
     */
    public void setUs_cextusr(String us_cextusr) {
        this.us_cextusr = us_cextusr;
    }

    /**
     * getter simple
     *
     * @return nom d utilisateur
     */
    public String getUs_nomusr() {
        return us_nomusr;
    }

    /**
     * setter seimple
     *
     * @param us_nomusr nom d utilisateur
     */
    public void setUs_nomusr(String us_nomusr) {
        this.us_nomusr = us_nomusr;
    }

    /**
     * getter simple
     *
     * @return prenom d utilisateur
     */
    public String getUs_prnusr() {
        return us_prnusr;
    }

    /**
     * setter simple
     *
     * @param us_prnusr prenom utilisateur
     */
    public void setUs_prnusr(String us_prnusr) {
        this.us_prnusr = us_prnusr;
    }

    /**
     * getter simple
     *
     * @return date fin de validité du compte utlisateur
     */
    public Date getUs_datfin() {
        return us_datfin;
    }

    /**
     * setter simple
     *
     * @param us_datfin date fin de validité du compte utlisateur
     */
    public void setUs_datfin(Date us_datfin) {
        this.us_datfin = us_datfin;
    }

    /**
     * getter simple
     *
     * @return date de la derniere connexion
     */
    public Date getUs_lastcnx() {
        return us_lastcnx;
    }

    /**
     * setter simple
     *
     * @param us_lastcnx date de la derniere connexion
     */
    public void setUs_lastcnx(Date us_lastcnx) {
        this.us_lastcnx = us_lastcnx;
    }

    /**
     * getter simple
     *
     * @return etat utilisateur TRUE activé FALSE desactivé
     */
    public MNG_USER_STATE_DTO getUs_etatusr() {
        return us_etatusr;
    }

    /**
     * setter simple
     *
     * @param us_etatusr etat utilisateur TRUE activé FALSE desactivé
     */
    public void setUs_etatusr(MNG_USER_STATE_DTO us_etatusr) {
        this.us_etatusr = us_etatusr;
    }

    /**
     * getter simple
     *
     * @return liste des application utilisateur
     */
    public List<MNG_APPLICATION_DTO> getUs_applications() {
        return us_applications;
    }

    /**
     * setter simple
     *
     * @param us_applications liste des application
     */
    public void setUs_applications(List<MNG_APPLICATION_DTO> us_applications) {
        this.us_applications = us_applications;
    }

    /**
     * message simple deprecated va etre changer dans la prochaine version en
     * utilisant jakson api pour avoir un document json valide
     *
     * @return une representation qui ressemble a json
     */
    @Override
    public String toString() {
        return "MNG_USER_DTO{" + "us_usrint=" + us_usrint + ", us_cextusr=" + us_cextusr + ", us_nomusr=" + us_nomusr + ", us_prnusr=" + us_prnusr + ", us_datfin=" + us_datfin + ", us_lastcnx=" + us_lastcnx + ", us_etatusr=" + us_etatusr + ", us_applications=" + us_applications + '}';
    }

}
