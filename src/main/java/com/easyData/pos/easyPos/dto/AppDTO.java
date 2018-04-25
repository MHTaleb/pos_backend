/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_DATA;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import java.util.List;

/**
 *
 * @author taleb
 */
public class AppDTO {

    private Long id;
    private List<MNG_COMPOSANT_DATA> cmp_datas;
    private MNG_COMPOSANT_TYPE cmp_type;

    public AppDTO() {
    }

    public AppDTO(Long id, List<MNG_COMPOSANT_DATA> cmp_datas, MNG_COMPOSANT_TYPE cmp_type) {
        this.id = id;
        this.cmp_datas = cmp_datas;
        this.cmp_type = cmp_type;
    }

    public List<MNG_COMPOSANT_DATA> getCmp_datas() {
        return cmp_datas;
    }

    public MNG_COMPOSANT_TYPE getCmp_type() {
        return cmp_type;
    }

    public Long getId() {
        return id;
    }

    public void setCmp_datas(List<MNG_COMPOSANT_DATA> cmp_datas) {
        this.cmp_datas = cmp_datas;
    }

    public void setCmp_type(MNG_COMPOSANT_TYPE cmp_type) {
        this.cmp_type = cmp_type;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
