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
 *
 * @author taleb
 */
@Component
public class ComponentDataFormList {
        @JsonProperty("componentDataForms")
        private List<ComponentDataForm> componentDataForms;
        
        public ComponentDataFormList() {
            componentDataForms = new ArrayList();
        }

        public List<ComponentDataForm> getComponentDataForms() {
            return componentDataForms;
        }

        public void setComponentDataForms(List<ComponentDataForm> componentDataForms) {
            this.componentDataForms = componentDataForms;
        }
        
    }