/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_DATA;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * jpa repository de gestion de donnée composant
 * @author taleb
 */
@Repository
public interface ComponentDataRepository extends JpaRepository<MNG_COMPOSANT_DATA, Long>{

    /**
     * retourne une liste des informations de composant a partir de la list des id
     * @param ids la list des id des information composant
     * @return list d information
     */
    public List<MNG_COMPOSANT_DATA> findByIds(@Param("ids") List<Long> ids);
}
