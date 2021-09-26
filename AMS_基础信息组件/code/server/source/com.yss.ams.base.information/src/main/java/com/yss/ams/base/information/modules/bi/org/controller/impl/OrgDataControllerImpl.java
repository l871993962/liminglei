package com.yss.ams.base.information.modules.bi.org.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.org.controller.IOrgDataController;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.pojo.OrgVo;
import com.yss.ams.base.information.support.bi.org.service.IOrgDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class OrgDataControllerImpl extends AbstractBaseController<IOrgDataService> implements IOrgDataController{

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<Org> queryByIds(String ids){
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
    public List<Org> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Org> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Org.class);
    }

    @Override
    public Org getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Org getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Org> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Org> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Org.class);
    }

    @Override
    public List<Org> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Org> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Org.class);
    }

    @Override
    public Org getDataByCounterpartyName(String counterpartyName){
        return (Org) getService().getDataByCounterpartyName(counterpartyName);
    }

    @Override
    public String insertOrg(Org pojo){
        return getService().insertOrg(pojo);
    }

    @Override
    public String getMaxOrgCode(){
        return getService().getMaxOrgCode();
    }

    @Override
    public List<String> getOrgCodebyAccNo(String AccNo){
        return getService().getOrgCodebyAccNo(AccNo);
    }

    @Override
    public List<Org> getDataListByCondition(String condition){
        return getService().getDataListByCondition(condition);
    }

    @Override
    public List<Org> getAllBankHead(){
        return getService().getAllBankHead();
    }

    @Override
    public List<Org> getBankBranchByHead(String[] param){
        return getService().getBankBranchByHead(param);
    }

    @Override
    public void insert(List<Org> orgList){
         getService().insert(castToBasePojoList(orgList));
    }

    @Override
    public Map<String,String> insert(String c_Org_Code){
        return getService().insert(c_Org_Code);
    }

    @Override
    public List<Org> getDataListByAptitude(String[] types){
        return getService().getDataListByAptitude(types);
    }

    @Override
    public List<Org> getParentListByTypes(String[] types){
        return getService().getParentListByTypes(types);
    }

    @Override
    public String getUpdateByTimestampCount(String timestamp){
        return getService().getUpdateByTimestampCount(timestamp);
    }

    //String timestamp, PageInation page
    @Override
    public CacheData updateByTimestampPage(OrgVo vo){
        return getService().updateByTimestampPage(vo.getTimestamp(),vo.getPage());
    }

    @Override
    public List<Org> getDataListByZtzz(String[] types){
        return getService().getDataListByZtzz(types);
    }

    @Override
    public CacheData updateByIds(String ids){
        return getService().updateByIds(ids);
    }

    @Override
    public List<Org> queryOrgByPort(String portCode,String c_dv_type){
        return getService().queryOrgByPort(portCode,c_dv_type);
    }


}