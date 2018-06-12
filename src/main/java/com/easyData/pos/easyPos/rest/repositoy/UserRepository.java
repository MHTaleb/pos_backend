/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * repository des utilisateur
 * elle contient les operation crud et doConnect,findByUsername,findAllByPackId
 * 
 * @author taleb
 */
@Repository
public interface UserRepository extends JpaRepository<MNG_USER, Long> {

    /**
     * le methode jpa qui appel la named query doConnect qui permet la connection
     * @param us_username le nom d utilisateur
     * @param us_pwdusr le mot de passe
     * @return null si la connexion echoue sinon les information sur l utilisateur
     */
    public MNG_USER doConnect(@Param("us_username") final String us_username,@Param("us_pwdusr") final String us_pwdusr );

    /**
     * ceci est une requete pour spring boot security qui lui permet d authentifier celon le nom d utilisateur
     * @param username
     * @return
     */
    @Query("SELECT e FROM MNG_USER e WHERE e.utilisateur = :username")
    public MNG_USER findByUsername(@Param("username") String username);
    
    /**
     * requete qui retourne la liste des utilisateur pack un pack id
     * @param pack_id
     * @return
     */
    public List<MNG_USER> findAllByPackId(@Param("pack_id") Long pack_id);
    
}
