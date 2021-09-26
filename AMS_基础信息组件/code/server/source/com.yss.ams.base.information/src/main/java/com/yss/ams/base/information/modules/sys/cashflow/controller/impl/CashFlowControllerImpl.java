package com.yss.ams.base.information.modules.sys.cashflow.controller.impl;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.sys.cashflow.controller.ICashFlowController;
import com.yss.ams.base.information.support.sys.cashflow.pojo.CashFlow;
import com.yss.ams.base.information.support.sys.cashflow.service.ICashFlowService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class CashFlowControllerImpl extends AbstractBaseController<ICashFlowService> implements ICashFlowController {

    @Override
    public CashFlow getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<CashFlow> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<CashFlow> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),CashFlow.class);
    }

    @Override
    public List<CashFlow> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<CashFlow> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),CashFlow.class);
    }

    @Override
    public List<CashFlow> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<CashFlow> getDataListRes(){
        return queryResToT(getService().getDataListRes(),CashFlow.class);
    }

    @Override
    public CashFlow getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public ArrayList<CashFlow> getCashFlowCode(){
        return getService().getCashFlowCode();
    }

}