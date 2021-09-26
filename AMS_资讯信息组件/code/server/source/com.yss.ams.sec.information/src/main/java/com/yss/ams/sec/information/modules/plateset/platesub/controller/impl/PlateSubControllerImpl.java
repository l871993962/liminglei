package com.yss.ams.sec.information.modules.plateset.platesub.controller.impl;

import java.util.List;

import com.yss.ams.sec.information.support.modules.plateset.platesub.pojo.PlateSub;
import com.yss.ams.sec.information.support.modules.plateset.platesub.service.IPlateSubService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.ams.sec.information.support.modules.plateset.platesub.controller.IPlateSubController;

/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class PlateSubControllerImpl extends AbstractBaseServiceBusController<PlateSub,IPlateSubService> implements IPlateSubController {

    @Override
    public String updatePlateById(List<PlateSub> pojoList){
        return getService().updatePlateById(castToBasePojoList(pojoList));
    }

}