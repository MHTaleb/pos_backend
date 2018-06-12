/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto.component;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * formulaire general pour l insertion d une d attribut d un composant
 * (insertion par lot ) ceci est la signature global de l api
 * <b>"SERVER_URL/components/datas"</b>
 *
 * @author taleb
 */
@Component
public class ComponentDataFormList {

    @JsonProperty("componentDataForms")
    private List<ComponentDataForm> componentDataForms;

    /**
     * constructeur simple
     */
    public ComponentDataFormList() {
        componentDataForms = new ArrayList();
    }

    /**
     * liste des information de ce composant
     *
     * @return liste des information de ce composant
     */
    public List<ComponentDataForm> getComponentDataForms() {
        return componentDataForms;
    }

    /**
     *
     * @param componentDataForms liste des information de ce composant
     */
    public void setComponentDataForms(List<ComponentDataForm> componentDataForms) {
        this.componentDataForms = componentDataForms;
    }

}
