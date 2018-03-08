/**
 * This file was generated by the Jeddict
 */
package com.easyData.pos.easyPos.rest.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author taleb
 */
@Entity
@NamedQuery(
        name = "MNG_USER.doConnect",
        query = "SELECT e FROM MNG_USER e WHERE e.us_username LIKE :us_username AND e.us_pwdusr LIKE :us_pwdusr"
)
@Table(name = "MNG_USER")
public class MNG_USER implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long us_usrint;

    @Basic
    private String us_cextusr;

    @Basic
    private String us_nomusr;

    @Basic
    private String us_prnusr;

    @Basic
    private String us_pwdusr;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date us_datdeb;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date us_datfin;

    @Basic
    private long us_nbrerr;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date us_datcre;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date us_datmaj;

    @Basic
    private String us_username;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date us_lastcnx;

    @OneToOne(targetEntity = MNG_USER_STATE.class)
    private MNG_USER_STATE us_etatusr;

    @OneToOne(targetEntity = MNG_USER_LANG.class)
    private MNG_USER_LANG us_langue;

    @OneToOne(targetEntity = MNG_USER.class)
    private MNG_USER us_lastuser;

    @OneToOne(targetEntity = MNG_USER_TYPE.class)
    private MNG_USER_TYPE us_typusr;

    @OneToOne(targetEntity = MNG_PROGRAM.class)
    private MNG_PROGRAM us_lastprg;

    @OneToOne(targetEntity = MNG_NIVEAU_ACCEE.class)
    private MNG_NIVEAU_ACCEE us_nivacc;

    public Long getUs_usrint() {
        return this.us_usrint;
    }

    public void setUs_usrint(Long us_usrint) {
        this.us_usrint = us_usrint;
    }

    public String getUs_cextusr() {
        return this.us_cextusr;
    }

    public void setUs_cextusr(String us_cextusr) {
        this.us_cextusr = us_cextusr;
    }

    public String getUs_nomusr() {
        return this.us_nomusr;
    }

    public void setUs_nomusr(String us_nomusr) {
        this.us_nomusr = us_nomusr;
    }

    public String getUs_prnusr() {
        return this.us_prnusr;
    }

    public void setUs_prnusr(String us_prnusr) {
        this.us_prnusr = us_prnusr;
    }

    public String getUs_pwdusr() {
        return this.us_pwdusr;
    }

    public void setUs_pwdusr(String us_pwdusr) {
        this.us_pwdusr = us_pwdusr;
    }

    public Date getUs_datdeb() {
        return this.us_datdeb;
    }

    public void setUs_datdeb(Date us_datdeb) {
        this.us_datdeb = us_datdeb;
    }

    public Date getUs_datfin() {
        return this.us_datfin;
    }

    public void setUs_datfin(Date us_datfin) {
        this.us_datfin = us_datfin;
    }

    public long getUs_nbrerr() {
        return this.us_nbrerr;
    }

    public void setUs_nbrerr(long us_nbrerr) {
        this.us_nbrerr = us_nbrerr;
    }

    public Date getUs_datcre() {
        return this.us_datcre;
    }

    public void setUs_datcre(Date us_datcre) {
        this.us_datcre = us_datcre;
    }

    public Date getUs_datmaj() {
        return this.us_datmaj;
    }

    public void setUs_datmaj(Date us_datmaj) {
        this.us_datmaj = us_datmaj;
    }

    public String getUs_username() {
        return this.us_username;
    }

    public void setUs_username(String us_username) {
        this.us_username = us_username;
    }

    public Date getUs_lastcnx() {
        return this.us_lastcnx;
    }

    public void setUs_lastcnx(Date us_lastcnx) {
        this.us_lastcnx = us_lastcnx;
    }

    public MNG_USER_STATE getUs_etatusr() {
        return this.us_etatusr;
    }

    public void setUs_etatusr(MNG_USER_STATE us_etatusr) {
        this.us_etatusr = us_etatusr;
    }

    public MNG_USER_LANG getUs_langue() {
        return this.us_langue;
    }

    public void setUs_langue(MNG_USER_LANG us_langue) {
        this.us_langue = us_langue;
    }

    public MNG_USER getUs_lastuser() {
        return this.us_lastuser;
    }

    public void setUs_lastuser(MNG_USER us_lastuser) {
        this.us_lastuser = us_lastuser;
    }

    public MNG_USER_TYPE getUs_typusr() {
        return this.us_typusr;
    }

    public void setUs_typusr(MNG_USER_TYPE us_typusr) {
        this.us_typusr = us_typusr;
    }

    public MNG_PROGRAM getUs_lastprg() {
        return this.us_lastprg;
    }

    public void setUs_lastprg(MNG_PROGRAM us_lastprg) {
        this.us_lastprg = us_lastprg;
    }

    public MNG_NIVEAU_ACCEE getUs_nivacc() {
        return this.us_nivacc;
    }

    public void setUs_nivacc(MNG_NIVEAU_ACCEE us_nivacc) {
        this.us_nivacc = us_nivacc;
    }

}
