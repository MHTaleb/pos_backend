/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.service;

import com.easyData.pos.easyPos.dto.UserDTO;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_PACK;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.repositoy.ComponentRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserPackRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ceci est un service qui implemente la logique metier des pack
 * ou on implemente le raisonement d ajout, modification, recherche et supression de pack
 * 
 * @author taleb
 */
@Service
public class UserPackService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPackRepository packRepository;

    @Autowired
    private ComponentRepository componentRepository;

    /**
     * logique metier de creation d un pack 
     * @param packName nom du pack 
     * @param mng_composants_ids id des applicaiton lié au pack 
     * @return le pack ajouter
     * @throws SQLException echec de l operation en sql
          * @see  MNG_PACK
     */
    public MNG_PACK createPack(final String packName, final List<Long> mng_composants_ids) {
        MNG_PACK pack = new MNG_PACK();
        pack.setPackName(packName);
        List<MNG_COMPOSANT> composants = componentRepository.findByComponentIds(mng_composants_ids);
        pack.setMng_composants(composants);
        packRepository.save(pack);
        return pack;
    }

    /**
     * logique metier de la modification d un pack 
     * @param id du pack a modifier
     * @param packName nouveau nom du pack 
     * @param mng_composants_ids nouvelle liste des application lié
     * @return le pack modifier
     * @see  MNG_PACK
     */
    public MNG_PACK editPack(final Long id, final String packName, final List<Long> mng_composants_ids) {
        MNG_PACK pack = packRepository.findById(id).get();
        pack.setPackName(packName);
        List<MNG_COMPOSANT> composants = componentRepository.findByComponentIds(mng_composants_ids);
        pack.setMng_composants(composants);
        packRepository.save(pack);
        return pack;
    }

    /**
     * methode metier qui recherche tout les pack
     * @return list des pack
     * @see  MNG_PACK
     */
    public List<MNG_PACK> getAllPacks() {
        final List<MNG_PACK> findAll = packRepository.findAll();
        final List<MNG_PACK> response = new ArrayList();
        findAll.stream().forEach(pack -> {
            MNG_PACK lightPack = new MNG_PACK();
            lightPack.setId(pack.getId());
            lightPack.setPackName(pack.getPackName());
            List<MNG_COMPOSANT> apps = new ArrayList();
            pack.getMng_composants().stream().forEach(app -> {
                MNG_COMPOSANT lightApp = new MNG_COMPOSANT();
                lightApp.setId(app.getId());
                lightApp.setCmp_datas(app.getCmp_datas());
                lightApp.setCmp_type(app.getCmp_type());
                apps.add(lightApp);
            });
            lightPack.setMng_composants(apps);
            response.add(lightPack);
        });

        return response;
    }

    /**
     * lire un pack specifique par son id 
     * @param id du pack 
     * @return le pack
     * @see  MNG_PACK
     */
    public MNG_PACK getPack(final Long id) {
        return packRepository.findById(id).get();
    }

    /**
     * list des utilisateur affilier a un pack
     * @param pack_id id du pack 
     * @return list des utilisateur lié a ce pack 
     * @see  UserDTO
     */
    public List<UserDTO> getUsersByPack(final Long pack_id) {
        List<MNG_USER> allUsers = userRepository.findAllByPackId(pack_id);
        final ArrayList<UserDTO> usersDtos = new ArrayList<>();
        allUsers.stream().forEach(user -> {
            UserDTO userDto = new UserDTO();
            userDto.setUsername(user.getUtilisateur());
            userDto.setUserID(user.getId());
            usersDtos.add(userDto);
        });
        return usersDtos;
    }

    /**
     * supression d un pack 
     * @param id du pack a supprimer
     */
    public void remove(Long id) {
        packRepository.getOne(id).getMng_composants().clear();
        packRepository.deleteById(id);
    }

}
