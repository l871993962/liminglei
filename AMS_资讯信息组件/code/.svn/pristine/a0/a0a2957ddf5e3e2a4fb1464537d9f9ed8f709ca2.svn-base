package com.yss.ams.sec.information.modules.plateset.plate.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.plateset.plate.controller.IPlate_AController;
import com.yss.ams.sec.information.support.modules.plateset.plate.pojo.Plate;
import com.yss.ams.sec.information.support.modules.plateset.plate.service.IPlate_AService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class Plate_AControllerImpl extends AbstractBaseServiceBusController<Plate,IPlate_AService> implements IPlate_AController {

    @Override
    public RestfulQueryResult<Plate> getTreeViewData(HashMap<String,Object> paraMap){
        return queryResToT(getService().getTreeViewData(paraMap),Plate.class);
    }

    @Override
    public String unAuditById(List<Plate> pojoList){
        return getService().unAuditById(castToBasePojoList(pojoList));
    }

    @Override
    public String getSUBData(String data){
        return getService().getSUBData(data);
    }

}