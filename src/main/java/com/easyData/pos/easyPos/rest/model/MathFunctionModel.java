/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * ceci est une classe model de la fonction generatrice des fonctions
 * en specifiant les valeurs de abcd on arrive a generer une fonction
 * ceci est stocker dans la bdd
 * cette entité changera d une moyenne hebdomadaire mensuel ou annuel celon le besoin
 * @author taleb
 */
@Entity
public class MathFunctionModel implements Serializable {

    @Id
    private Long id;

    @Basic
    private Long a;
    
    @Basic
    private Long b;
    
    @Basic
    private Long c;
    
    @Basic
    private Long d;

    /**
     * on pourrai avoir plusieur fonction celon le besoin
     * constructeur avec parametre 
     * @param id id de cette fonction dans la bdd
     * @param a valeur polynome a
     * @param b valeur polynome b
     * @param c valeur polynome c
     * @param d valeur polynome d
     */
    public MathFunctionModel(Long id, Long a, Long b, Long c, Long d) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     *
     */
    public MathFunctionModel() {
    }

    /**
     * lire la valeur de a
     * @return a
     */
    public Long getA() {
        return a;
    }

    /**
     * mettre valeur de a
     * @param a nouvelle valeur de a
     */
    public void setA(Long a) {
        this.a = a;
    }

    /**
     * lire valeur de b 
     * @return b
     */
    public Long getB() {
        return b;
    }

    /**
     * mettre la valeur de b
     * @param b nouvelle valeur de b
     */
    public void setB(Long b) {
        this.b = b;
    }

    /**
     * lire la valeur de c
     * @return c
     */
    public Long getC() {
        return c;
    }

    /**
     * mettre la valeur de c
     * @param c la nouvelle valeur de c
     */
    public void setC(Long c) {
        this.c = c;
    }

    /**
     * lire la valeur de d
     * @return d 
     */
    public Long getD() {
        return d;
    }

    /**
     * mettre la valeur de d
     * @param d la nouvelle valeur de d
     */
    public void setD(Long d) {
        this.d = d;
    }

    /**
     * lire le ID de la fonction 
     * @return le id
     */
    public Long getId() {
        return id;
    }

    /**
     * mettre le nouveau id
     * @param id le nouveeau id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    

    
    
    
}
