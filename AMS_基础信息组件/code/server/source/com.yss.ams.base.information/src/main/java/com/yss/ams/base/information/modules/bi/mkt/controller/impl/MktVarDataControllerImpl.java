package com.yss.ams.base.information.modules.bi.mkt.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.mkt.controller.IMktVarDataController;
import com.yss.ams.base.information.support.bi.mkt.service.IMktVarDataService;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class MktVarDataControllerImpl extends AbstractBaseController<IMktVarDataService> implements IMktVarDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<Mkt> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Mkt> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Mkt.class);
    }

    @Override
    public Mkt getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public void reloadCache(){
         getService().reloadCache();
    }

}