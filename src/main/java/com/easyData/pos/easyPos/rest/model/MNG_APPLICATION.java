/**
 * This file was generated by the Jeddict
 */
package com.easyData.pos.easyPos.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author taleb
 */
@Entity
public class MNG_APPLICATION {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = MNG_MENU.class)
    private MNG_MENU MNG_MENU;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MNG_MENU getMNG_MENU() {
        return this.MNG_MENU;
    }

    public void setMNG_MENU(MNG_MENU MNG_MENU) {
        this.MNG_MENU = MNG_MENU;
    }

}
