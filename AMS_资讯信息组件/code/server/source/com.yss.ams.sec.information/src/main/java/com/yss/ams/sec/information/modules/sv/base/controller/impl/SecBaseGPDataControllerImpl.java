package com.yss.ams.sec.information.modules.sv.base.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseGPDataController;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseGPDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class SecBaseGPDataControllerImpl extends AbstractBaseController<ISecBaseGPDataService> implements ISecBaseGPDataController{

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<SecBase> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<SecBase> getDataListRes(){
        return queryResToT(getService().getDataListRes(),SecBase.class);
    }

    @Override
    public SecBase getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

}