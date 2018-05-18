/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.service.UserPackService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    public static final String URL = "/packs";

    @Autowired
    private UserPackService packService;

    @PostMapping(params = {"pack_name", "mng_composants_ids"})
    private ServerResponse createPack(
            @RequestParam final String pack_name,
            @RequestParam final List<Long> mng_composants_ids
    ) {

        if (isSessionValid()) {
            initSuccessResponse(packService.createPack(pack_name, mng_composants_ids));
            return serverResponse;
        }

        initFailLoginResponse();
        return serverResponse;
    }

    @PostMapping(params = "pack_name")
    private ServerResponse createPack(
            @RequestParam final String pack_name
    ) {

        if (isSessionValid()) {
            initSuccessResponse(packService.createPack(pack_name, new ArrayList<>()));
            return serverResponse;
        }

        initFailLoginResponse();
        return serverResponse;
    }

    @PutMapping(params = {"id", "pack_name", "mng_composants_ids"})
    private ServerResponse updatePack(
            @RequestParam final Long id,
            @RequestParam final String pack_name,
            @RequestParam final List<Long> mng_composants_ids
    ) {
        if (isSessionValid()) {
            initSuccessResponse(packService.editPack(id, pack_name, mng_composants_ids));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    @PutMapping(params = {"id", "pack_name"})
    private ServerResponse updatePack(
            @RequestParam final Long id,
            @RequestParam final String pack_name
    ) {
        if (isSessionValid()) {
            initSuccessResponse(packService.editPack(id, pack_name, new ArrayList<>()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    @DeleteMapping
    private ServerResponse deletePack(
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

    @GetMapping
    private ServerResponse getAllPacks() {

        if (isSessionValid()) {

            initSuccessResponse(packService.getAllPacks());
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

    @GetMapping(params = "id")
    private ServerResponse getPack(
            @RequestParam final Long id
    ) {
        if (isSessionValid()) {

            initSuccessResponse(packService.getPack(id));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

}
