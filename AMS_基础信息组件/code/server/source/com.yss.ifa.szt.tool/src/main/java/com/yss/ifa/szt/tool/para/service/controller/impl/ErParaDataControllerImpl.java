package com.yss.ifa.szt.tool.para.service.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.ifa.szt.tool.para.service.IErParaDataService;
import com.yss.ifa.szt.tool.para.service.controller.IErParaDataController;
import com.yss.ifa.szt.tool.pojo.ErPara;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class ErParaDataControllerImpl extends AbstractBaseController<IErParaDataService> implements IErParaDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<ErPara> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<ErPara> getDataListRes(){
        return queryResToT(getService().getDataListRes(),ErPara.class);
    }

    @Override
    public ErPara getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public ErPara getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<ErPara> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<ErPara> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),ErPara.class);
    }

    @Override
    public List<ErPara> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<ErPara> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),ErPara.class);
    }

}