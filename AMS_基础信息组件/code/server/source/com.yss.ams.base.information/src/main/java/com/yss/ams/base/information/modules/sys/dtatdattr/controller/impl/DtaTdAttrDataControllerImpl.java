package com.yss.ams.base.information.modules.sys.dtatdattr.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.dtatdattr.controller.IDtaTdAttrDataController;
import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtaTdAttrDataService;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DtaTdAttrDataControllerImpl extends AbstractBaseController<IDtaTdAttrDataService> implements IDtaTdAttrDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<DtatdAttr> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<DtatdAttr> getDataListRes(){
        return queryResToT(getService().getDataListRes(),DtatdAttr.class);
    }

    @Override
    public DtatdAttr getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public DtatdAttr getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<DtatdAttr> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<DtatdAttr> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),DtatdAttr.class);
    }

    @Override
    public List<DtatdAttr> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<DtatdAttr> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),DtatdAttr.class);
    }

    @Override
    public HashMap<String,String> getShortDataMap(){
        return getService().getShortDataMap();
    }

    @Override
    public List<DtatdAttr> getDataListByCodes(String[] codes){
        return getService().getDataListByCodes(codes);
    }

	@Override
	public CacheData updateByTimestamp(String timestamp) {
		return getService().updateByTimestamp(timestamp);
	}

	@Override
	public List<DtatdAttr> queryByIds(String ids) {
		return getService().queryByIds(ids);
	}

}