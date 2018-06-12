/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.dto.PackFormDTO;
import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.service.UserPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(PackController.URL)
public class PackController extends GenericController {

    /**
     *
     */
    public static final String URL = "/packs";

    @Autowired
    private UserPackService packService;

    /**
     *
     * @param packFormDTO
     * @return
     */
    @PostMapping
    protected ServerResponse createPack(
            @RequestBody PackFormDTO packFormDTO
    ) {

        if (isSessionValid()) {
            initSuccessResponse(packService.createPack(packFormDTO.getPackName(), packFormDTO.getPackApplications()));
            return serverResponse;
        }

        initFailLoginResponse();
        return serverResponse;
    }

    /**
     *
     * @param packFormDTO
     * @return
     */
    @PutMapping
    protected ServerResponse updatePack(
            @RequestBody PackFormDTO packFormDTO
    ) {
        if (isSessionValid()) {
            initSuccessResponse(packService.editPack(packFormDTO.getId(), packFormDTO.getPackName(),packFormDTO.getPackApplications()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping
    protected ServerResponse deletePack(
            @RequestParam final Long id
    ) {

        if (isSessionValid()) {
            packService.remove(id);
            
            initSuccessResponse("pack with Id " + id + " deleted successfully");
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

    /**
     *
     * @return
     */
    @GetMapping
    protected ServerResponse getAllPacks() {

        if (isSessionValid()) {

            initSuccessResponse(packService.getAllPacks());
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/{id_pack}")
    protected ServerResponse getPack(
            @PathVariable("id_pack") final Long id
    ) {
        if (isSessionValid()) {

            initSuccessResponse(packService.getPack(id));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

}
