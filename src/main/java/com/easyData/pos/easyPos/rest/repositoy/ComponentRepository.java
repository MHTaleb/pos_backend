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
 *
 * @author taleb
 */
@Repository
public interface ComponentRepository extends JpaRepository<MNG_COMPOSANT, Long> {

    public List<MNG_COMPOSANT> findAppByUser(@Param("user") final MNG_USER user, @Param("type") final MNG_COMPOSANT_TYPE type);

    public List<MNG_COMPOSANT> findComponentByUserIdAndComponentType(@Param("userID") final Long userID, @Param("type") final MNG_COMPOSANT_TYPE type);

    public List<MNG_COMPOSANT> findByComponentIds(@Param("cids") final List<Long> cids);

    public List<MNG_COMPOSANT> findAllByComponentType(@Param("mng_composant_type") final MNG_COMPOSANT_TYPE mng_composant_type);

    public List<MNG_COMPOSANT> findAllByUser(@Param("userID") final Long id);
}
