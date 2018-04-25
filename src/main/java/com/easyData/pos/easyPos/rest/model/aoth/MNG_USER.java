/**
 * This file was generated by the Jeddict
 */
package com.easyData.pos.easyPos.rest.model.aoth;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Component;

/**
 * @author taleb
 */
@Entity
@NamedQuery(
        name = "MNG_USER.doConnect",
        query = "SELECT e FROM MNG_USER e WHERE e.utilisateur = :us_username AND e.password =  :us_pwdusr"
)
@Component
public class MNG_USER implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "us_usrint")
    private Long id;

    @Column(unique = true, name = "us_cextusr")
    @Basic
    private String code_externe;

    @Basic
    @Column(name = "us_nomusr")
    private String nom;

    @Basic
    @Column(name = "us_prnusr")
    private String prenom;

    @Basic
    @Column(name = "us_pwdusr")
    private String password;

    @Basic
    @Column(name = "us_datdeb")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Basic
    @Column(name = "us_datfin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @Basic
    @Column(name = "us_nbrerr")
    private long nombreErreur;

    @Basic
    @Column(name = "us_datcre")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Basic
    @Column(name = "us_datmaj")
    @Temporal(TemporalType.DATE)
    private Date dateMiseAJour;

    @Column(unique = true, name = "us_username")
    private String utilisateur;

    @Column(name = "us_lastcnx")
    @Temporal(TemporalType.DATE)
    private Date lastConnexion;

    @OneToOne(targetEntity = MNG_USER_STATE.class)
    private MNG_USER_STATE etatUtilisateur;

    @Column(name = "us_langue")
    private MNG_USER_LANG langueUtilisateur;

    @OneToOne(targetEntity = MNG_USER.class)
    private MNG_USER dernierUtilisateur;

    @OneToOne(targetEntity = MNG_USER_TYPE.class)
    private MNG_USER_TYPE typeUtilisateur;

    @OneToOne(targetEntity = MNG_PROGRAM.class)
    private MNG_PROGRAM lastProg;

    @OneToOne(targetEntity = MNG_NIVEAU_ACCEE.class)
    private MNG_NIVEAU_ACCEE niveauAcces;

    @OneToMany(targetEntity = MNG_PACK.class)
    private List<MNG_PACK> MNG_PACKs;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode_externe() {
        return this.code_externe;
    }

    public void setCode_externe(String code_externe) {
        this.code_externe = code_externe;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public long getNombreErreur() {
        return this.nombreErreur;
    }

    public void setNombreErreur(long nombreErreur) {
        this.nombreErreur = nombreErreur;
    }

    public Date getDateCreation() {
        return this.dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMiseAJour() {
        return this.dateMiseAJour;
    }

    public void setDateMiseAJour(Date dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public String getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Date getLastConnexion() {
        return this.lastConnexion;
    }

    public void setLastConnexion(Date lastConnexion) {
        this.lastConnexion = lastConnexion;
    }

    public MNG_USER_STATE getEtatUtilisateur() {
        return this.etatUtilisateur;
    }

    public void setEtatUtilisateur(MNG_USER_STATE etatUtilisateur) {
        this.etatUtilisateur = etatUtilisateur;
    }

    public MNG_USER_LANG getLangueUtilisateur() {
        return this.langueUtilisateur;
    }

    public void setLangueUtilisateur(MNG_USER_LANG langueUtilisateur) {
        this.langueUtilisateur = langueUtilisateur;
    }

    public MNG_USER getDernierUtilisateur() {
        return this.dernierUtilisateur;
    }

    public void setDernierUtilisateur(MNG_USER dernierUtilisateur) {
        this.dernierUtilisateur = dernierUtilisateur;
    }

    public MNG_USER_TYPE getTypeUtilisateur() {
        return this.typeUtilisateur;
    }

    public void setTypeUtilisateur(MNG_USER_TYPE typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    public MNG_PROGRAM getLastProg() {
        return this.lastProg;
    }

    public void setLastProg(MNG_PROGRAM lastProg) {
        this.lastProg = lastProg;
    }

    public MNG_NIVEAU_ACCEE getNiveauAcces() {
        return this.niveauAcces;
    }

    public void setNiveauAcces(MNG_NIVEAU_ACCEE niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

    public List<MNG_PACK> getMNG_PACKs() {
        return this.MNG_PACKs;
    }

    public void setMNG_PACKs(List<MNG_PACK> MNG_PACKs) {
        this.MNG_PACKs = MNG_PACKs;
    }

}