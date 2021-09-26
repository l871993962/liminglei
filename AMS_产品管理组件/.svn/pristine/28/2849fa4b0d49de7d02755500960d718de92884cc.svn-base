package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRela;
import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaTradeAccountNoDataController;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaTradeAccountNoDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaTradeAccountNoDataControllerImpl extends AbstractBaseController<IPortRelaTradeAccountNoDataService> implements IPortRelaTradeAccountNoDataController {

    @Override
    public PortRela getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<PortRela> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<PortRela> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),PortRela.class);
    }

    @Override
    public List<PortRela> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<PortRela> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),PortRela.class);
    }

    @Override
    public List<PortRela> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortRela> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortRela.class);
    }

    @Override
    public PortRela getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

}