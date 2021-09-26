package com.yss.ams.sec.information.modules.sv.indexinfo.controller.impl;

import java.util.HashMap;

import com.yss.ams.sec.information.support.modules.sv.indexinfo.controller.IIndexInfoController;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.pojo.IndexInfo;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.service.IIndexInfoService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class IndexInfoControllerImpl extends AbstractBaseServiceBusController<IndexInfo,IIndexInfoService> implements IIndexInfoController {

    @Override
    public RestfulQueryResult<IndexInfo> getPortRelaIndex(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPortRelaIndex(paraMap),IndexInfo.class);
    }

}