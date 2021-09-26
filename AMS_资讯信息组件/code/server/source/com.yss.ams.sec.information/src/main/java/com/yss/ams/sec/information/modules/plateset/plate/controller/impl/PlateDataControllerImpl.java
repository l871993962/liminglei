package com.yss.ams.sec.information.modules.plateset.plate.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.plateset.plate.controller.IPlateDataController;
import com.yss.ams.sec.information.support.modules.plateset.plate.pojo.Plate;
import com.yss.ams.sec.information.support.modules.plateset.plate.service.IPlateDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class PlateDataControllerImpl extends AbstractBaseController<IPlateDataService> implements IPlateDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<Plate> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Plate> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Plate.class);
    }

    @Override
    public Plate getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Plate getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Plate> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Plate> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Plate.class);
    }

    @Override
    public List<Plate> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Plate> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Plate.class);
    }

    @Override
    public String updatePlateById(List<Plate> pojoList){
        return getService().updatePlateById(castToBasePojoList(pojoList));
    }

    @Override
    public void insert(List<Plate> list){
         getService().insert(castToBasePojoList(list));
    }

}