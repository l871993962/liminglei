package com.yss.ams.base.information.modules.bi.accountType.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.dactype.controller.IAccountTypeDataController;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountType;
import com.yss.ams.base.information.support.bi.dactype.service.IAccountTypeDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class AccountTypeDataControllerImpl extends AbstractBaseController<IAccountTypeDataService> implements IAccountTypeDataController{

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<AccountType> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<AccountType> getDataListRes(){
        return queryResToT(getService().getDataListRes(),AccountType.class);
    }

    @Override
    public AccountType getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public AccountType getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<AccountType> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<AccountType> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),AccountType.class);
    }

    @Override
    public List<AccountType> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<AccountType> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),AccountType.class);
    }

}