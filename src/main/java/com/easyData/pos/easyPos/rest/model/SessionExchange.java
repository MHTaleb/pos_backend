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
 * cette entité va stoqué le id de la session ( ceci est une representation du rapport envoi/reception x:envoi y:reception
 * le id de cette entité et la paire (x,y) 
 * pour chaque flux bi-directionel entre un client et un serveur un x,y va etre generé cela permetrer la generation d une sequence
 * ainsi le serveur et le client saurant à quel sequence s attendre a la prochaine reponse
 * une fois la sequence validé
 * le recepteur changera x,y et notifi l emeteur
 * ainsi l emeteur mettre a jour ses x,y en lecture de la bdd
 * ceci ce repete jusqu a terminéson de connexion
 * @author taleb
 */
@Entity
@IdClass(value = SessionExchange.SessionExchangeId.class)
public class SessionExchange implements Serializable {

    private Long x;
    private Long y;

    /**
     * recuperer premier id
     * @return
     */
    @Id
    @Column(name = "x")
    public Long getX() {
        return x;
    }

    /**
     * mettre nouveau id de x
     * @param x
     */
    public void setX(Long x) {
        this.x = x;
    }

    /**
     * recuperer deuxieme id
     * @return
     */
    @Id
    @Column(name = "y")
    public Long getY() {
        return y;
    }

    /**
     * mettre le nouveau id de y
     * @param y
     */
    public void setY(Long y) {
        this.y = y;
    }

    /**
     * une classe qui va servir comme objet d id
     */
    public static class SessionExchangeId implements Serializable {

        private Long x;
        private Long y;

        /**
         *
         * @return
         */
        public Long getX() {
            return x;
        }

        /**
         *
         * @param x
         */
        public void setX(Long x) {
            this.x = x;
        }

        /**
         *
         * @return
         */
        public Long getY() {
            return y;
        }

        /**
         *
         * @param y
         */
        public void setY(Long y) {
            this.y = y;
        }

        // implement equals(), hashcode()

        /**
         *
         * @return
         */
        @Override
        public int hashCode() {
            int hash = 5;
            hash = 29 * hash + Objects.hashCode(this.x);
            hash = 29 * hash + Objects.hashCode(this.y);
            return hash;
        }

        /**
         *
         * @param obj
         * @return
         */
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
