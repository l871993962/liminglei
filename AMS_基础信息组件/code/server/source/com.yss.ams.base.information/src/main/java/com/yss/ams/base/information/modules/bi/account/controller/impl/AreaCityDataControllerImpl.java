package com.yss.ams.base.information.modules.bi.account.controller.impl;

import java.util.HashMap;

import com.yss.ams.base.information.support.bi.account.controller.IAreaCityDataController;
import com.yss.ams.base.information.support.bi.account.pojo.AreaCity;
import com.yss.ams.base.information.support.bi.account.service.IAreaCityDataService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
* STORY #94053 业务组件osgi的restful改造
* @author neil
* @date 2020-08-24 16:19:33
*/
public class AreaCityDataControllerImpl  extends AbstractBaseServiceBusController<AreaCity,IAreaCityDataService> implements IAreaCityDataController{

	 @Override
	    public HashMap<String,String> getKeyConvertMap() throws Exception{
	        return getService().getKeyConvertMap();
	    }

	    @Override
	    public String queryAccCityByAccNo(String accNo){
	        return getService().queryAccCityByAccNo(accNo);
	    }

	    @Override
	    public String queryAccProvinceByAccNo(String accNo){
	        return getService().queryAccProvinceByAccNo(accNo);
	    }

		

}


