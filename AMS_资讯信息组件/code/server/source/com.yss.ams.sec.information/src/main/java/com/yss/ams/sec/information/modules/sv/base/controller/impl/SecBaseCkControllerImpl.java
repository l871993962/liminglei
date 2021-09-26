package com.yss.ams.sec.information.modules.sv.base.controller.impl;

import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseCkController;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseCkService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class SecBaseCkControllerImpl extends AbstractBaseServiceBusController<SecBase,ISecBaseCkService> implements ISecBaseCkController {

    @Override
    public List<SecBase> queryForSjsszq(){
        return  castToListT(getService().queryForSjsszq());
    }

    @Override
    public String insert(List<SecBase> pojoList){
        return getService().insert(castToBasePojoList(pojoList));
    }

    @Override
    public String updateById(List<SecBase> pojoList){
        return getService().updateById(castToBasePojoList(pojoList));
    }

}