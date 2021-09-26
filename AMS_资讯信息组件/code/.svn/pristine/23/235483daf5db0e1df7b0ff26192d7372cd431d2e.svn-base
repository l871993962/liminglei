package com.yss.ams.sec.information.modules.sv.suspendedcond.controller.impl;

import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.suspendedcond.controller.ISuspendedCondController;
import com.yss.ams.sec.information.support.modules.sv.suspendedcond.pojo.SuspendedCond;
import com.yss.ams.sec.information.support.modules.sv.suspendedcond.service.ISuspendedCondService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class SuspendedCondControllerImpl extends AbstractBaseServiceBusController<SuspendedCond,ISuspendedCondService> implements  ISuspendedCondController{

    @Override
    public String updateConds(List<SuspendedCond> pojoList){
        return getService().updateConds(castToBasePojoList(pojoList));
    }

    @Override
    public List<SuspendedCond> getCondList(String port){
        return getService().getCondList(port);
    }

}