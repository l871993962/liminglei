package com.yss.ams.base.information.modules.bi.account.controller.impl;

import java.util.HashMap;
import java.util.List;
import com.yss.ams.base.information.support.bi.account.controller.IAreaCityDataMapController;
import com.yss.ams.base.information.support.bi.account.pojo.AreaCity;
import com.yss.ams.base.information.support.bi.account.service.IAreaCityDataMapService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
* STORY #94053 业务组件osgi的restful改造
* @author neil
* @date 2020-08-24 16:19:33
*/
public class AreaCityDataMapControllerImpl extends AbstractBaseController<IAreaCityDataMapService> implements IAreaCityDataMapController{

	 @Override
	    public HashMap<String,String> getKeyConvertMap(){
	        return getService().getKeyConvertMap();
	    }

	    @Override
	    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
	        return getService().getKeyConvertMap(listKey);
	    }

	    @Override
	    public List<AreaCity> getDataList(){
	        return getService().getDataList();
	    }

	    @Override
	    public RestfulQueryResult<AreaCity> getDataListRes(){
	        return queryResToT(getService().getDataListRes(),AreaCity.class);
	    }

	    @Override
	    public AreaCity getDataByCode(String dataCode){
	        return getService().getDataByCode(dataCode);
	    }

	    @Override
	    public AreaCity getPojoByCode(String pojoCode){
	        return getService().getPojoByCode(pojoCode);
	    }

	    @Override
	    public List<AreaCity> getDataListByTypes(String[] types){
	        return getService().getDataListByTypes(types);
	    }

	    @Override
	    public RestfulQueryResult<AreaCity> getQueryResByTypes(String[] types){
	        return queryResToT(getService().getQueryResByTypes(types),AreaCity.class);
	    }

	    @Override
	    public List<AreaCity> getDataListByKeys(String[] keys){
	        return getService().getDataListByKeys(keys);
	    }

	    @Override
	    public RestfulQueryResult<AreaCity> getQueryResByKeys(String[] keys){
	        return queryResToT(getService().getQueryResByKeys(keys),AreaCity.class);
	    }
}


