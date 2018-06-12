/**
 * This file was generated by the Jeddict
 */
package com.easyData.pos.easyPos.rest.model.component;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.springframework.stereotype.Component;

/**
 * entité des attribut ou information sur un composant
 * @author taleb
 */
@Component
@Entity
@NamedQueries({
    @NamedQuery(name = "MNG_COMPOSANT_DATA.findByIds",query = "SELECT e FROM MNG_COMPOSANT_DATA e WHERE e.id IN :ids ")

})
public class MNG_COMPOSANT_DATA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private String cmp_attr_label;

    @Basic
    private String cmp_attr_value;

    @Basic
    private String cmp_attr_code;

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
    public String getCmp_attr_label() {
        return this.cmp_attr_label;
    }

    /**
     *
     * @param cmp_attr_label
     */
    public void setCmp_attr_label(String cmp_attr_label) {
        this.cmp_attr_label = cmp_attr_label;
    }

    /**
     *
     * @return
     */
    public String getCmp_attr_value() {
        return this.cmp_attr_value;
    }

    /**
     *
     * @param cmp_attr_value
     */
    public void setCmp_attr_value(String cmp_attr_value) {
        this.cmp_attr_value = cmp_attr_value;
    }

    /**
     *
     * @return
     */
    public String getCmp_attr_code() {
        return this.cmp_attr_code;
    }

    /**
     *
     * @param cmp_attr_code
     */
    public void setCmp_attr_code(String cmp_attr_code) {
        this.cmp_attr_code = cmp_attr_code;
    }

}
