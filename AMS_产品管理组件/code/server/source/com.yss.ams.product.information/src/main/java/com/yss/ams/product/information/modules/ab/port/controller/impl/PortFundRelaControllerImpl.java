package com.yss.ams.product.information.modules.ab.port.controller.impl;

import com.yss.ams.product.information.support.modules.ab.port.controller.IPortFundRelaController;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortFundRelaService;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortFundRelaControllerImpl extends AbstractBaseServiceBusController<Port,IPortFundRelaService> implements IPortFundRelaController {

    @Override
    public String deletePortFundRela(String portCodes,String fundAccId){
        return getService().deletePortFundRela(portCodes,fundAccId);
    }

}