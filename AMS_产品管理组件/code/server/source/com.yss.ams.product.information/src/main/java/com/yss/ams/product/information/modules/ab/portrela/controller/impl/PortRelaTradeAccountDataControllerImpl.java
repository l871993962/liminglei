package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaTradeAccountDataController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaCashAccount;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeAccountDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaTradeAccountDataControllerImpl extends AbstractBaseController<IPortRelaTradeAccountDataService> implements IPortRelaTradeAccountDataController {

    @Override
    public PortRelaCashAccount getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<PortRelaCashAccount> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<PortRelaCashAccount> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),PortRelaCashAccount.class);
    }

    @Override
    public List<PortRelaCashAccount> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<PortRelaCashAccount> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),PortRelaCashAccount.class);
    }

    @Override
    public List<PortRelaCashAccount> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortRelaCashAccount> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortRelaCashAccount.class);
    }

    @Override
    public PortRelaCashAccount getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

}