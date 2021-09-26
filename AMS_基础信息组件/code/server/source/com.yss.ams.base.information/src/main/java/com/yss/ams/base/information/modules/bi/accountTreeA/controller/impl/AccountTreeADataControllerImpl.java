package com.yss.ams.base.information.modules.bi.accountTreeA.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.accountTreeA.controller.IAccountTreeADataController;
import com.yss.ams.base.information.support.bi.accountTreeA.pojo.AccountTreeA;
import com.yss.ams.base.information.support.bi.accountTreeA.service.IAccountTreeADataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
* STORY #94053 业务组件osgi的restful改造
* @author neil
* @date 2020-09-07 14:27:02
*/
public class AccountTreeADataControllerImpl extends AbstractBaseController<IAccountTreeADataService> implements IAccountTreeADataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<AccountTreeA> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<AccountTreeA> getDataListRes(){
        return queryResToT(getService().getDataListRes(),AccountTreeA.class);
    }

    @Override
    public AccountTreeA getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public AccountTreeA getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<AccountTreeA> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<AccountTreeA> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),AccountTreeA.class);
    }

    @Override
    public List<AccountTreeA> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<AccountTreeA> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),AccountTreeA.class);
    }

}