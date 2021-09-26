package com.yss.ams.base.information.modules.sys.dztype.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dztype.controller.IDzTypeDataController;
import com.yss.ams.base.information.support.sys.dztype.pojo.DzType;
import com.yss.ams.base.information.support.sys.dztype.service.IDzTypeDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DzTypeDataControllerImpl extends AbstractBaseController<IDzTypeDataService> implements IDzTypeDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<DzType> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<DzType> getDataListRes(){
        return queryResToT(getService().getDataListRes(),DzType.class);
    }

    @Override
    public DzType getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public DzType getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<DzType> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<DzType> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),DzType.class);
    }

    @Override
    public List<DzType> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<DzType> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),DzType.class);
    }

}