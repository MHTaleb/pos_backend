/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.contoller.tools.RequestParamVars;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_DATA;
import com.easyData.pos.easyPos.rest.repositoy.ComponentDataRepository;
import com.easyData.pos.easyPos.rest.repositoy.ComponentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(ComponentDataController.REST_URL)
public class ComponentDataController extends GenericController {

    public static final String REST_URL = "/components/datas";

    @Autowired
    private ComponentDataRepository componentDataRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @PostMapping(params = {"code_att", "label_att", "value_att"})
    public ServerResponse addData(
            @RequestParam(RequestParamVars.CODE_ATT) String code_att,
            @RequestParam(RequestParamVars.LABEL_ATT) String label_att,
            @RequestParam(RequestParamVars.VALUE_ATT) String value_att
    ) {

        if (isSessionValid()) {
            MNG_COMPOSANT_DATA mng_composant_data = new MNG_COMPOSANT_DATA();

            mng_composant_data.setCmp_attr_code(code_att);
            mng_composant_data.setCmp_attr_label(label_att);
            mng_composant_data.setCmp_attr_value(value_att);

            componentDataRepository.save(mng_composant_data);

            initSuccessResponse(mng_composant_data);
            return serverResponse;

        }

        initFailLoginResponse();
        return serverResponse;
    }

    @PostMapping
    private ServerResponse creatAllCompDatas(
            @RequestParam final List<ComponentDataForm> componentDataFormValues
    ) {

        if (isSessionValid()) {
            List<Long> datasIds = new ArrayList();
            componentDataFormValues.stream().forEach(formValue -> {
                MNG_COMPOSANT_DATA mng_composant_data = new MNG_COMPOSANT_DATA();

                mng_composant_data.setCmp_attr_code(formValue.getAttCode());
                mng_composant_data.setCmp_attr_label(formValue.getAttTitle());
                mng_composant_data.setCmp_attr_value(formValue.getAttVal());

                componentDataRepository.save(mng_composant_data);
                datasIds.add(mng_composant_data.getId());
            });
            
            initSuccessResponse(datasIds);
            return serverResponse;
            
        }
        initFailLoginResponse();
        return serverResponse;
    }

    @GetMapping
    private ServerResponse getDataByComponentID(
            @RequestParam final Long id
    ) {
        if (isSessionValid()) {

            initSuccessResponse(componentRepository.getOne(id).getCmp_datas());
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

   
    

}
