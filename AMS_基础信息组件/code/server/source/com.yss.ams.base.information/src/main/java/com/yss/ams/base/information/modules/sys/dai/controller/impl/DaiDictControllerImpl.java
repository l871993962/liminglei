package com.yss.ams.base.information.modules.sys.dai.controller.impl;

import java.util.List;

import com.yss.ams.base.information.support.sys.dai.controller.IDaiDictController;
import com.yss.ams.base.information.support.sys.dai.pojo.Dai;
import com.yss.ams.base.information.support.sys.dai.service.IDaiDictService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DaiDictControllerImpl extends AbstractBaseServiceBusController<Dai,IDaiDictService> implements IDaiDictController {

    @Override
    public List<Dai> queryByCacheForKm(){
        return getService().queryByCacheForKm();
    }

}