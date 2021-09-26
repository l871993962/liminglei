package com.yss.ams.base.information.modules.bi.tdchan.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.tdchan.controller.ISecSeatDataController;
import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;
import com.yss.ams.base.information.support.bi.tdchan.service.ISecSeatDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class SecSeatDataControllerImpl extends AbstractBaseController<ISecSeatDataService> implements ISecSeatDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<TdChan> queryByIds(String ids){
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
    public List<TdChan> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<TdChan> getDataListRes(){
        return queryResToT(getService().getDataListRes(),TdChan.class);
    }

    @Override
    public TdChan getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public TdChan getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<TdChan> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<TdChan> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),TdChan.class);
    }

    @Override
    public List<TdChan> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<TdChan> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),TdChan.class);
    }

    @Override
    public List<TdChan> getTdChanDataList() throws Exception{
        return getService().getTdChanDataList();
    }

    @Override
    public RestfulQueryResult<TdChan> getTdChanDataListRes() throws Exception{
        return queryResToT(getService().getTdChanDataListRes(),TdChan.class);
    }

    @Override
    public HashMap<String,String> getShortDataMap(){
        return getService().getShortDataMap();
    }

    @Override
    public String insertTdChan(TdChan pojo){
        return getService().insertTdChan(pojo);
    }

    @Override
    public String getMaxTdChanCode(){
        return getService().getMaxTdChanCode();
    }

    @Override
    public TdChan getDataByOrgCode(String orgCode){
        return (TdChan) getService().getDataByOrgCode(orgCode);
    }

    @Override
    public List<TdChan> getDataListByComm(String[] types){
        return getService().getDataListByComm(types);
    }

    @Override
    public List<TdChan> queryPortRelaTradeSeat(String portCode){
        return castToListT(getService().queryPortRelaTradeSeat(portCode),TdChan.class);
    }

    @Override
    public List<TdChan> getDataListByPort(String[] portCode){
        return castToListT(getService().getDataListByPort(portCode),TdChan.class);
    }

    @Override
    public List<TdChan> getDataListByPorts(String[] types){
        return getService().getDataListByPorts(types);
    }

}