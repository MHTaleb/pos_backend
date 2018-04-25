/**
 * This file was generated by the Jeddict
 */
package com.easyData.pos.easyPos.rest.model.aoth;

import com.easyData.pos.easyPos.rest.model.component.v1.MNG_FONCTION;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;

/**
 * @author taleb
 */
@Entity
@Component
public class MNG_REGLES implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = MNG_PACK.class)
    private MNG_PACK MNG_PROFILE;

    @OneToOne(targetEntity = MNG_FONCTION.class)
    private MNG_FONCTION MNG_FONCTION;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MNG_PACK getMNG_PROFILE() {
        return this.MNG_PROFILE;
    }

    public void setMNG_PROFILE(MNG_PACK MNG_PROFILE) {
        this.MNG_PROFILE = MNG_PROFILE;
    }

    public MNG_FONCTION getMNG_FONCTION() {
        return this.MNG_FONCTION;
    }

    public void setMNG_FONCTION(MNG_FONCTION MNG_FONCTION) {
        this.MNG_FONCTION = MNG_FONCTION;
    }

}