package com.yss.ifa.szt.tool.para.service.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.ifa.szt.tool.para.service.IMrInfoService;
import com.yss.ifa.szt.tool.para.service.controller.IMrInfoController;
import com.yss.ifa.szt.tool.pojo.MrInfo;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class MrInfoControllerImpl extends AbstractBaseServiceBusController<MrInfo,IMrInfoService> implements IMrInfoController {

    @Override
    public List<MrInfo> queryAllMrInfos(){
        return getService().queryAllMrInfos();
    }

    @Override
    public void updateCheckState(MrInfo mrInfo){
        getService().updateCheckState(mrInfo);
    }

}