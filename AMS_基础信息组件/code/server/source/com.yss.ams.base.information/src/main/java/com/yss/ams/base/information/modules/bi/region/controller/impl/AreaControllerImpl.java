package com.yss.ams.base.information.modules.bi.region.controller.impl;

import java.util.List;

import com.yss.ams.base.information.support.bi.region.controller.IAreaController;
import com.yss.ams.base.information.support.bi.region.pojo.Area;
import com.yss.ams.base.information.support.bi.region.service.IAreaService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class AreaControllerImpl extends AbstractBaseServiceBusController<Area,IAreaService> implements IAreaController {

    @Override
    public RestfulQueryResult<Area> getAllAreasByType(){
        return queryResToT(getService().getAllAreasByType(),Area.class);
    }

    @Override
    public List<Area> getAllTopAreas(){
        return getService().getAllTopAreas();
    }

}