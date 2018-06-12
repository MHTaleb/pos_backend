/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * repository jpa pour la gestion des composant
 * @author taleb
 */
@Repository
public interface ComponentRepository extends JpaRepository<MNG_COMPOSANT, Long> {

    /**
     * retourn une liste des composant lié a un utilisateur
     * la requete s appel findAppByUser dans la class MNG_COMPOSANT
     * @param user utilisateur 
     * @param type type de composant
     * @return liste de composant
     */
    public List<MNG_COMPOSANT> findAppByUser(@Param("user") final MNG_USER user, @Param("type") final MNG_COMPOSANT_TYPE type);

    /**
     * liste des composant d un utilisateur celon un type 
     * @param userID
     * @param type
     * @return
     */
    public List<MNG_COMPOSANT> findComponentByUserIdAndComponentType(@Param("userID") final Long userID, @Param("type") final MNG_COMPOSANT_TYPE type);

    /**
     * retourne une liste de composant depuis la list des id
     * @param cids list des ids
     * @return la liste de composant
     */
    public List<MNG_COMPOSANT> findByComponentIds(@Param("cids") final List<Long> cids);

    /**
     * la liste de tout les composant d un type 
     * @param mng_composant_type type de composant desiré
     * @return la liste de tout les composant
     */
    public List<MNG_COMPOSANT> findAllByComponentType(@Param("mng_composant_type") final MNG_COMPOSANT_TYPE mng_composant_type);

    /**
     * la liste des composant lié  a un utlisateur 
     * @param id id de l utilisateur 
     * @return la liste de ses composant
     */
    public List<MNG_COMPOSANT> findAllByUser(@Param("userID") final Long id);
}
