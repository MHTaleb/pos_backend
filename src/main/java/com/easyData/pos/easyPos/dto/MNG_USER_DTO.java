/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
@Component
public class MNG_USER_DTO {
    /**
     * ID ,nom,prenom,langue,date derniere connexion , list application , num sequence
     * 
     * List Application : 
     * comlplete chaque application son etat active ou desactiver boolean
     * nom de l application String
     * Id de l application Long
     * LOGO Blob
     * Lien String
     * 
     * -----------------------------------------------
     * 
     * dans un click sur une application : 
     * 
     * le client va envoyer le 
     * 
     * ID user , sequence en cours , Id application  -----> List des Menu :
     * 
     * chaque menu avec son etat (ID menu ,IdIcon , Libellé , list des fils)
     * 
     * 
     */
    
     private Long us_usrint;
 private String us_cextusr;
 private String us_nomusr;
 private String us_prnusr;
 
  private Date us_datfin;
 private Date us_lastcnx;
 private MNG_USER_STATE_DTO us_etatusr;
 private MNG_PACK_DTO us_pack;
    
}
