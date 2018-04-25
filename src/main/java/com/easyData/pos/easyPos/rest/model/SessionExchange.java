/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 *
 * @author taleb
 */
@Entity
@IdClass(value = SessionExchange.SessionExchangeId.class)
public class SessionExchange implements Serializable {

    private Long x;
    private Long y;

    @Id
    @Column(name = "x")
    public Long getX() {
        return x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    @Id
    @Column(name = "y")
    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public static class SessionExchangeId implements Serializable {

        private Long x;
        private Long y;

        public Long getX() {
            return x;
        }

        public void setX(Long x) {
            this.x = x;
        }

        public Long getY() {
            return y;
        }

        public void setY(Long y) {
            this.y = y;
        }

        // implement equals(), hashcode()
        @Override
        public int hashCode() {
            int hash = 5;
            hash = 29 * hash + Objects.hashCode(this.x);
            hash = 29 * hash + Objects.hashCode(this.y);
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
            final SessionExchangeId other = (SessionExchangeId) obj;
            if (!Objects.equals(this.x, other.x)) {
                return false;
            }
            if (!Objects.equals(this.y, other.y)) {
                return false;
            }
            return true;
        }

    }
}
