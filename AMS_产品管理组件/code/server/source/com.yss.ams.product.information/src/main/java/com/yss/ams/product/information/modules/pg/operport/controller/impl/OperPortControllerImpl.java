package com.yss.ams.product.information.modules.pg.operport.controller.impl;

import java.util.Map;

import com.yss.ams.product.information.support.modules.pg.operport.controller.IOperPortController;
import com.yss.ams.product.information.support.modules.pg.operport.service.IOperPortService;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class OperPortControllerImpl extends AbstractBaseServiceBusController<Port,IOperPortService> implements IOperPortController {

    @Override
    public Map<String,Port> getPortByGroupCode(String groupCode){
        return getService().getPortByGroupCode(groupCode);
    }

    @Override
    public Map<String,String> getAllPortGroup(){
        return getService().getAllPortGroup();
    }

}