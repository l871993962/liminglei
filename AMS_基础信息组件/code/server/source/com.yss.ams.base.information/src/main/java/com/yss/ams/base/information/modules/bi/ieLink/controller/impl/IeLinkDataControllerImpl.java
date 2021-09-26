package com.yss.ams.base.information.modules.bi.ieLink.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.ieLink.controller.IIeLinkDataController;
import com.yss.ams.base.information.support.bi.ieLink.pojo.IeLink;
import com.yss.ams.base.information.support.bi.ieLink.service.IIeLinkDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class IeLinkDataControllerImpl extends AbstractBaseController<IIeLinkDataService> implements IIeLinkDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<IeLink> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<IeLink> getDataListRes(){
        return queryResToT(getService().getDataListRes(),IeLink.class);
    }

    @Override
    public IeLink getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public IeLink getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<IeLink> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<IeLink> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),IeLink.class);
    }

    @Override
    public List<IeLink> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<IeLink> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),IeLink.class);
    }

    @Override
    public List<IeLink> getDataListByCodes(String[] codes){
        return getService().getDataListByCodes(codes);
    }

    @Override
    public List<IeLink> getDataListByParentCode(String[] codes){
        return getService().getDataListByParentCode(codes);
    }

    @Override
    public List<IeLink> getFeeDataList(){
        return getService().getFeeDataList();
    }

    @Override
    public List<IeLink> getDataListByTypes1(String[] types){
        return getService().getDataListByTypes(types);
    }

	

}