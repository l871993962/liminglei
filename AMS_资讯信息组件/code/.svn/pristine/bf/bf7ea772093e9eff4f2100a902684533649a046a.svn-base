package com.yss.ams.sec.information.modules.sv.base.controller.impl;

import com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBasePjController;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBasePjService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class SecBasePjControllerImpl extends AbstractBaseServiceBusController<SecBase,ISecBasePjService> implements ISecBasePjController{

    @Override
    public SecBase queryPjSecBase(String secCode){
        return castToT(getService().queryPjSecBase(secCode));
    }

}