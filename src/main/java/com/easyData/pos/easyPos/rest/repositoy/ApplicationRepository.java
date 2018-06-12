/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.component.v1.MNG_APPLICATION;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * jpa repository pour la gestion des application
 * @author taleb
 */
@Repository
public interface ApplicationRepository extends JpaRepository<MNG_APPLICATION, Long> {
   // public List<MNG_APPLICATION> findByUser(@Param("user") final MNG_USER user);
}
