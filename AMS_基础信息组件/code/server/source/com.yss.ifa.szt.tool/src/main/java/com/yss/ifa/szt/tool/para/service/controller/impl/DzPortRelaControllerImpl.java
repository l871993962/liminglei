package com.yss.ifa.szt.tool.para.service.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.ifa.szt.tool.para.service.IDzPortRelaService;
import com.yss.ifa.szt.tool.para.service.controller.IDzPortRelaController;
import com.yss.ifa.szt.tool.pojo.DzRelaOrgan;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DzPortRelaControllerImpl extends AbstractBaseServiceBusController<DzRelaOrgan,IDzPortRelaService> implements IDzPortRelaController {

    @Override
    public List<String> queryPortCodesRelaOrgan(HashMap<String,Object> paraMap){
        return getService().queryPortCodesRelaOrgan(paraMap);
    }

    @Override
    public String delInsert(List<DzRelaOrgan> pojoList){
        return getService().delInsert(castToBasePojoList(pojoList));
    }

    @Override
    public String delByYwId(List<DzRelaOrgan> pojoList){
        return getService().delByYwId(castToBasePojoList(pojoList));
    }

}