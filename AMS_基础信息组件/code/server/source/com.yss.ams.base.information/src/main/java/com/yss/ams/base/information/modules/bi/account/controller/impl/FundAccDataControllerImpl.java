package com.yss.ams.base.information.modules.bi.account.controller.impl;


import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.account.controller.IFundAccDataController;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.service.IFundAccDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
* STORY #94053 业务组件osgi的restful改造
* @author neil
* @date 2020-08-24 16:19:33
*/
public class FundAccDataControllerImpl extends AbstractBaseController<IFundAccDataService> implements IFundAccDataController {

	 @Override
	    public CacheData updateByTimestamp(String timestamp){
	        return getService().updateByTimestamp(timestamp);
	    }

	    @Override
	    public List<FundAcc> queryByIds(String ids){
	        return getService().queryByIds(ids);
	    }

	    @Override
	    public HashMap<String,String> getKeyConvertMap(){
	        return getService().getKeyConvertMap();
	    }

	    @Override
	    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
	        return getService().getKeyConvertMap(listKey);
	    }

	    @Override
	    public List<FundAcc> getDataList(){
	        return getService().getDataList();
	    }

	    @Override
	    public RestfulQueryResult<FundAcc> getDataListRes(){
	        return queryResToT(getService().getDataListRes(),FundAcc.class);
	    }

	    @Override
	    public FundAcc getDataByCode(String dataCode){
	        return getService().getDataByCode(dataCode);
	    }

	    @Override
	    public FundAcc getPojoByCode(String pojoCode){
	        return getService().getPojoByCode(pojoCode);
	    }

	    @Override
	    public List<FundAcc> getDataListByTypes(String[] types){
	        return getService().getDataListByTypes(types);
	    }

	    @Override
	    public RestfulQueryResult<FundAcc> getQueryResByTypes(String[] types){
	        return queryResToT(getService().getQueryResByTypes(types),FundAcc.class);
	    }

	    @Override
	    public List<FundAcc> getDataListByKeys(String[] keys){
	        return getService().getDataListByKeys(keys);
	    }

	    @Override
	    public RestfulQueryResult<FundAcc> getQueryResByKeys(String[] keys){
	        return queryResToT(getService().getQueryResByKeys(keys),FundAcc.class);
	    }

	    @Override
	    public List<FundAcc> getDataListByOrg(String orgCode){
	        return getService().getDataListByOrg(orgCode);
	    }

	    @Override
	    public List<FundAcc> getAllDataByPort(String portCode){
	        return getService().getAllDataByPort(portCode);
	    }

	    @Override
	    public List<FundAcc> getDataListByPort(String portCode){
	        return getService().getDataListByPort(portCode);
	    }

	    @Override
	    public List<FundAcc> getDataListByAssCode(String assCode){
	        return getService().getDataListByAssCode(assCode);
	    }

	    @Override
	    public List<FundAcc> getDataListByIds(String ids){
	        return getService().getDataListByIds(ids);
	    }

	    @Override
	    public List<FundAcc> getAccNameAndCaCode(){
	        return getService().getAccNameAndCaCode();
	    }

   
}


