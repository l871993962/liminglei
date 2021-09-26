package com.yss.ams.product.information.modules.cp.pubacc.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.cp.pubacc.controller.IPubAccDataController;
import com.yss.ams.product.information.support.modules.cp.pubacc.pojo.PubAcc;
import com.yss.ams.product.information.support.modules.cp.pubacc.service.IPubAccDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PubAccDataControllerImpl extends AbstractBaseController<IPubAccDataService> implements IPubAccDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<PubAcc> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PubAcc> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PubAcc.class);
    }

    @Override
    public PubAcc getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public PubAcc getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<PubAcc> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<PubAcc> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),PubAcc.class);
    }

    @Override
    public List<PubAcc> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<PubAcc> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),PubAcc.class);
    }

}