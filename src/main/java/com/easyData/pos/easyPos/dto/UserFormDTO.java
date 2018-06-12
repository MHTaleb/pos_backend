/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 * DTO qui va etre remplacer par UOW pattern
 * <br>
 * ceci est un DTO formulaire il sert a reunir les paramaitre d une requete Rest
 * dans un seul objet cela donne une signature unique dans le controller qui va
 * nous permettre de modifier les pramaitre possible dans un controlleur sans
 * devoir recrire du code partout la liste des paramaitre possible est :
 * java.util.Date dateDebut, date debut validité du compte java.util.Date
 * dateFin, date fin validité du compte boolean etatUser, etat du compte activé
 * ou désactivé Long idLangue, id de la langue Long idAccess, id du acl affecter
 * a cette utilisateur List#Long# idsPack, liste des id des packs String nom,
 * String password, String prenom, String username;
 *
 * @deprecated
 * @author taleb
 */
@Component
public class UserFormDTO {

    private String code_externe;

    private Date dateDebut;

    private Date dateFin;

    private boolean etatUser;

    private Long idLangue;

    private Long idAccess;

    private List<Long> idsPack;

    private String nom;

    private String password;

    private String prenom;

    private String username;

    private Long idUser;

    /**
     *
     * @param code_externe code externe
     * @param dateDebut date debut validité du compte
     * @param dateFin date fin validite de ce compte(utlisateur)
     * @param etatUser etat de l utilisateur activé desactivé
     * @param idLangue id de la langue de cette utilisateur
     * @param idAccess id du ACL de cette utilisateur
     * @param idsPack liste des id des pack lier a cette utilisateur
     * @param nom nom
     * @param password mot de passe
     * @param prenom prenom
     * @param username utilisateur
     * @param idUser Id ou code interne
     */
    public UserFormDTO(String code_externe, Date dateDebut, Date dateFin, boolean etatUser, Long idLangue, Long idAccess, List<Long> idsPack, String nom, String password, String prenom, String username, Long idUser) {
        this.code_externe = code_externe;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.etatUser = etatUser;
        this.idLangue = idLangue;
        this.idAccess = idAccess;
        this.idsPack = idsPack;
        this.nom = nom;
        this.password = password;
        this.prenom = prenom;
        this.username = username;
        this.idUser = idUser;
    }

    /**
     * constructeur simple
     */
    public UserFormDTO() {
    }

    /**
     * Get the value of idUser
     *
     * @return the value of idUser
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Set the value of idUser
     *
     * @param idUser new value of idUser
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    /**
     * Get the value of prenom
     *
     * @return the value of prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the value of prenom
     *
     * @param prenom new value of prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Get the value of idsPack
     *
     * @return the value of idsPack
     */
    public List<Long> getIdsPack() {
        return idsPack;
    }

    /**
     * Set the value of idsPack
     *
     * @param idsPack new value of idsPack
     */
    public void setIdsPack(List<Long> idsPack) {
        this.idsPack = idsPack;
    }

    /**
     * Get the value of idAccess
     *
     * @return the value of idAccess
     */
    public Long getIdAccess() {
        return idAccess;
    }

    /**
     * Set the value of idAccess
     *
     * @param idAccess new value of idAccess
     */
    public void setIdAccess(Long idAccess) {
        this.idAccess = idAccess;
    }

    /**
     * Get the value of idLangue
     *
     * @return the value of idLangue
     */
    public Long getIdLangue() {
        return idLangue;
    }

    /**
     * Set the value of idLangue
     *
     * @param idLangue new value of idLangue
     */
    public void setIdLangue(Long idLangue) {
        this.idLangue = idLangue;
    }

    /**
     * Get the value of etatUser
     *
     * @return the value of etatUser
     */
    public boolean isEtatUser() {
        return etatUser;
    }

    /**
     * Set the value of etatUser
     *
     * @param etatUser new value of etatUser
     */
    public void setEtatUser(boolean etatUser) {
        this.etatUser = etatUser;
    }

    /**
     * Get the value of dateFin
     *
     * @return the value of dateFin
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * Set the value of dateFin
     *
     * @param dateFin new value of dateFin
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Get the value of dateDebut
     *
     * @return the value of dateDebut
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * Set the value of dateDebut
     *
     * @param dateDebut new value of dateDebut
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Get the value of code_externe
     *
     * @return the value of code_externe
     */
    public String getCode_externe() {
        return code_externe;
    }

    /**
     * Set the value of code_externe
     *
     * @param code_externe new value of code_externe
     */
    public void setCode_externe(String code_externe) {
        this.code_externe = code_externe;
    }

    /**
     * retourn le code de hashage necessaire pour la serialisation CRC cheksum
     * control
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.code_externe);
        hash = 79 * hash + Objects.hashCode(this.nom);
        hash = 79 * hash + Objects.hashCode(this.prenom);
        hash = 79 * hash + Objects.hashCode(this.username);
        return hash;
    }

    /**
     * necessaire pour la recherche dans une liste elle permet de verifier si
     * deux objet son pareil
     *
     * @param obj
     * @return
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
        final UserFormDTO other = (UserFormDTO) obj;

        if (!Objects.equals(this.code_externe, other.code_externe)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }

        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }

        return true;
    }

    /**
     * @deprecated a changer par un json generer de jackson api
     * @return
     */
    @Override
    public String toString() {
        return "UserForm{" + "code_externe=" + code_externe + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", etatUser=" + etatUser + ", idLangue=" + idLangue + ", idAccess=" + idAccess + ", idsPack=" + idsPack + ", nom=" + nom + ", password=" + password + ", prenom=" + prenom + ", username=" + username + '}';
    }

}
