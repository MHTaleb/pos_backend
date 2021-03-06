/**
 * This file was generated by the Jeddict
 */
package com.easyData.pos.easyPos.rest.model.component.v1;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Component;

/**
 * @deprecated a supprimer dans la prochaine version
 * @author taleb
 */
@Entity
@Component
public class MNG_MENU {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = MNG_FONCTION.class)
    private List<MNG_FONCTION> MNG_FONCTION;

    @OneToMany(targetEntity = MNG_MENU.class)
    private List<MNG_MENU> MNG_MENUs;

    /**
     *
     * @return
     */
    public Long getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public List<MNG_FONCTION> getMNG_FONCTION() {
        return MNG_FONCTION;
    }

    /**
     *
     * @param MNG_FONCTION
     */
    public void setMNG_FONCTION(List<MNG_FONCTION> MNG_FONCTION) {
        this.MNG_FONCTION = MNG_FONCTION;
    }

    /**
     *
     * @return
     */
    public List<MNG_MENU> getMNG_MENUs() {
        return this.MNG_MENUs;
    }

    /**
     *
     * @param MNG_MENUs
     */
    public void setMNG_MENUs(List<MNG_MENU> MNG_MENUs) {
        this.MNG_MENUs = MNG_MENUs;
    }

}
