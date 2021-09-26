package com.yss.ams.base.information.modules.sys.mktsdi.controller.impl;

import java.util.List;

import com.yss.ams.base.information.support.sys.mktsdi.controller.IMktsdiController;
import com.yss.ams.base.information.support.sys.mktsdi.pojo.Mktsdi;
import com.yss.ams.base.information.support.sys.mktsdi.service.IMktsdiService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class MktsdiControllerImpl extends AbstractBaseServiceBusController<Mktsdi,IMktsdiService> implements IMktsdiController {

    @Override
    public String updateByPk(List<Mktsdi> pojoList){
        return getService().updateByPk(castToBasePojoList(pojoList));
    }

}