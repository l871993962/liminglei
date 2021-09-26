package com.yss.ams.base.information.modules.sys.acctype.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.acctype.controller.IAccTypeDataController;
import com.yss.ams.base.information.support.sys.acctype.pojo.AccType;
import com.yss.ams.base.information.support.sys.acctype.service.IAccTypeDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class AccTypeDataControllerImpl extends AbstractBaseController<IAccTypeDataService> implements IAccTypeDataController{

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<AccType> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<AccType> getDataListRes(){
        return queryResToT(getService().getDataListRes(),AccType.class);
    }

    @Override
    public AccType getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public AccType getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<AccType> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<AccType> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),AccType.class);
    }

    @Override
    public List<AccType> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<AccType> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),AccType.class);
    }

}