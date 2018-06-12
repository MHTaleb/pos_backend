/**
 * This file was generated by the Jeddict
 */
package com.easyData.pos.easyPos.rest.model.aoth;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Component;

/**
 * l entité qui reflete la table des utilisateur elle a deux requere jpa
 * <ul>
 * <li> doConnect est la requete qui gere la connexion
 * <li> findAllByPackId est la requete qui retourne une liste d utilisateur qui
 * ont une relation avec un pack specifique (pack_id)
 * </ul>
 * <h1>pour les mot de passe chaque client de l application DEVRAIT ENVOYER SON
 * MDP en SHA-256 ou une autre cryptologie, il est interdit de l envoyer en
 * claire</h1>
 *
 * @author taleb
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "MNG_USER.doConnect",
            query = "SELECT e FROM  MNG_USER e WHERE e.utilisateur = :us_username AND  e.password  =  :us_pwdusr"
    )
    ,
    @NamedQuery(
            name = "MNG_USER.findAllByPackId",
            query = "SELECT e FROM MNG_USER e WHERE EXISTS ( SELECT 1 FROM MNG_PACK p WHERE p.id = :pack_id AND p MEMBER OF e.MNG_PACKs)"
    )
})
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

    @Basic
    private boolean etatUtilisateur;

    @Column(name = "us_langue")
    private MNG_USER_LANG langueUtilisateur;

    @OneToOne(targetEntity = MNG_USER.class)
    @JsonBackReference
    private MNG_USER dernierUtilisateur;

    @OneToOne(targetEntity = MNG_USER_TYPE.class)
    private MNG_USER_TYPE typeUtilisateur;

    @OneToOne(targetEntity = MNG_PROGRAM.class)
    private MNG_PROGRAM lastProg;

    @OneToOne(targetEntity = MNG_NIVEAU_ACCEE.class)
    private MNG_NIVEAU_ACCEE niveauAcces;

    @ManyToMany(targetEntity = MNG_PACK.class)
    private List<MNG_PACK> MNG_PACKs;

    /**
     * lire le id de l utilisateur
     *
     * @return le id de l utilisateur
     */
    public Long getId() {
        return this.id;
    }

    /**
     * mettre le id de l utilisateur a une nouvelel valeur
     *
     * @param id le nouveau id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * lire le code externe de utilisateur
     *
     * @return le code externe de l utilisateur
     */
    public String getCode_externe() {
        return this.code_externe;
    }

    /**
     * mettre un nouveau code externe de l utilisateur
     *
     * @param code_externe le nouveau code externe de l utilisateur
     */
    public void setCode_externe(String code_externe) {
        this.code_externe = code_externe;
    }

    /**
     * lire le nom de l utilisateur
     *
     * @return le nom de l utilisateur
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * mettre un nouveau nom de l utilisateur (nuance ce n est pas le username,
     * mais le nom propre de la personne exemple Taleb,Berahab,Semmoud)
     *
     * @param nom le nouveau nom de l utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * lire le prenom de l utilisateur
     *
     * @return le prenom de l utilisateur
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * mettre le nouveau prenom de l utilisateur
     *
     * @param prenom le nouveau prenom de l utilisateur
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * lire le mot de passe
     *
     * @return le mot de passe
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * mettre le nouveau mot de passe
     *
     * @param password le nouveau mot de passe
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * lire la date de debut de validité de cet utilisateur
     *
     * @return la date de debut de validité
     */
    public Date getDateDebut() {
        return this.dateDebut;
    }

    /**
     * mettre une nouvelle date de debut de validité de ce compte
     *
     * @param dateDebut la nouvelle date de debut de validité
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * lire la date de fin de validité de cet utilisateur
     *
     * @return la date de fin de validité de cet utilisateur
     */
    public Date getDateFin() {
        return this.dateFin;
    }

    /**
     * mettre la nouvelle date de fin de validité de cet utilisateur
     *
     * @param dateFin la nouvelle date de fin de validité
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * lire le nombre d erreur
     *
     * @return le nombre d erreur
     */
    public long getNombreErreur() {
        return this.nombreErreur;
    }

    /**
     * mettre a jour le nombre d erreur
     *
     * @param nombreErreur le nouveau nombre d erreur
     */
    public void setNombreErreur(long nombreErreur) {
        this.nombreErreur = nombreErreur;
    }

    /**
     * lire la date de creation de cet utilisateur
     *
     * @return la date de creation du compte
     */
    public Date getDateCreation() {
        return this.dateCreation;
    }

    /**
     * mettre a jour la date de creation
     *
     * @param dateCreation la nouvelle date de cretaion de ce compte
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * lire la date de la derniere mise a jour de ce compte
     *
     * @return la date de la derniere mise a jour de compte
     */
    public Date getDateMiseAJour() {
        return this.dateMiseAJour;
    }

    /**
     * mettre a jour la date de la derniere msie a jour
     *
     * @param dateMiseAJour la nouvelle date de la mise a jour de ce compte
     */
    public void setDateMiseAJour(Date dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    /**
     *
     * @return
     */
    public String getUtilisateur() {
        return this.utilisateur;
    }

    /**
     *
     * @param utilisateur
     */
    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     *
     * @return
     */
    public Date getLastConnexion() {
        return this.lastConnexion;
    }

    /**
     *
     * @param lastConnexion
     */
    public void setLastConnexion(Date lastConnexion) {
        this.lastConnexion = lastConnexion;
    }

    /**
     *
     * @return
     */
    public boolean isEtatUtilisateur() {
        return etatUtilisateur;
    }

    /**
     *
     * @param etatUtilisateur
     */
    public void setEtatUtilisateur(boolean etatUtilisateur) {
        this.etatUtilisateur = etatUtilisateur;
    }

    /**
     *
     * @return
     */
    public MNG_USER_LANG getLangueUtilisateur() {
        return this.langueUtilisateur;
    }

    /**
     *
     * @param langueUtilisateur
     */
    public void setLangueUtilisateur(MNG_USER_LANG langueUtilisateur) {
        this.langueUtilisateur = langueUtilisateur;
    }

    /**
     *
     * @return
     */
    public MNG_USER getDernierUtilisateur() {
        return this.dernierUtilisateur;
    }

    /**
     *
     * @param dernierUtilisateur
     */
    public void setDernierUtilisateur(MNG_USER dernierUtilisateur) {
        this.dernierUtilisateur = dernierUtilisateur;
    }

    /**
     *
     * @return
     */
    public MNG_USER_TYPE getTypeUtilisateur() {
        return this.typeUtilisateur;
    }

    /**
     *
     * @param typeUtilisateur
     */
    public void setTypeUtilisateur(MNG_USER_TYPE typeUtilisateur) {
        this.typeUtilisateur = typeUtilisateur;
    }

    /**
     *
     * @return
     */
    public MNG_PROGRAM getLastProg() {
        return this.lastProg;
    }

    /**
     *
     * @param lastProg
     */
    public void setLastProg(MNG_PROGRAM lastProg) {
        this.lastProg = lastProg;
    }

    /**
     *
     * @return
     */
    public MNG_NIVEAU_ACCEE getNiveauAcces() {
        return this.niveauAcces;
    }

    /**
     *
     * @param niveauAcces
     */
    public void setNiveauAcces(MNG_NIVEAU_ACCEE niveauAcces) {
        this.niveauAcces = niveauAcces;
    }

    /**
     *
     * @return
     */
    public List<MNG_PACK> getMNG_PACKs() {
        return this.MNG_PACKs;
    }

    /**
     *
     * @param MNG_PACKs
     */
    public void setMNG_PACKs(List<MNG_PACK> MNG_PACKs) {
        this.MNG_PACKs = MNG_PACKs;
    }

}
