/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
    @Component
    public class ComponentDataForm {
            @JsonProperty("attCode")
            private String attCode;
            @JsonProperty("attTitle")
            private String attTitle;
            @JsonProperty("attVal")
            private String attVal;

            public ComponentDataForm() {
            }

            public String getAttCode() {
                return attCode;
            }

            public void setAttCode(String attCode) {
                this.attCode = attCode;
            }

            public String getAttTitle() {
                return attTitle;
            }

            public void setAttTitle(String attTitle) {
                this.attTitle = attTitle;
            }

            public String getAttVal() {
                return attVal;
            }

            public void setAttVal(String attVal) {
                this.attVal = attVal;
            }

            @Override
            public int hashCode() {
                int hash = 7;
                hash = 29 * hash + Objects.hashCode(this.attCode);
                hash = 29 * hash + Objects.hashCode(this.attTitle);
                hash = 29 * hash + Objects.hashCode(this.attVal);
                return hash;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null) {
                    return false;
                }
                if (getClass() != obj.getClass()) {
                    return false;
                }
                final ComponentDataForm other = (ComponentDataForm) obj;
                if (!Objects.equals(this.attCode, other.attCode)) {
                    return false;
                }
                if (!Objects.equals(this.attTitle, other.attTitle)) {
                    return false;
                }
                if (!Objects.equals(this.attVal, other.attVal)) {
                    return false;
                }
                return true;
            }

            @Override
            public String toString() {
                return "{" + "attCode:" + attCode + ", attTitle:" + attTitle + ", attVal:" + attVal + '}';
            }

            public ComponentDataForm(String attCode, String attTitle, String attVal) {
                this.attCode = attCode;
                this.attTitle = attTitle;
                this.attVal = attVal;
            }


        }
