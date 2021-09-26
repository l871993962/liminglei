package com.yss.ams.sec.information.modules.sv.base.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseLcController;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseLcService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class SecBaseLcControllerImpl extends AbstractBaseServiceBusController<SecBase,ISecBaseLcService> implements ISecBaseLcController{

    @Override
    public String singleSecInitFi(String secCode) throws Exception{
        return getService().singleSecInitFi(secCode);
    }

    @Override
    public String multipleSecInitFi(List<SecBase> secList) throws Exception{
        return getService().multipleSecInitFi(secList);
    }

    @Override
    public List<SecBase> getSecBasesByCondition(HashMap<String,Object> paraMap){
        return castToListT(getService().getSecBasesByCondition(paraMap),SecBase.class);
    }

}