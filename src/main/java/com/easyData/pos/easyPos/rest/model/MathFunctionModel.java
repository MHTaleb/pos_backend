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
 *
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

    public MathFunctionModel(Long id, Long a, Long b, Long c, Long d) {
        this.id = id;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public MathFunctionModel() {
    }

    
    
    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public Long getB() {
        return b;
    }

    public void setB(Long b) {
        this.b = b;
    }

    public Long getC() {
        return c;
    }

    public void setC(Long c) {
        this.c = c;
    }

    public Long getD() {
        return d;
    }

    public void setD(Long d) {
        this.d = d;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    
    
    
}
