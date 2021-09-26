package com.yss.ams.product.information.modules.ab.port.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.port.controller.IPortADataController;
import com.yss.ams.product.information.support.modules.ab.port.pojo.Port_A;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortADataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortADataControllerImpl extends AbstractBaseController<IPortADataService> implements IPortADataController{

    @Override
    public List<Port_A> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Port_A> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Port_A.class);
    }

    @Override
    public Port_A getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public RestfulQueryResult<Port_A> doPortFilterRes(String isDataRight,String datClass,String dvPortCode,String trCode){
        return queryResToT(getService().doPortFilterRes(isDataRight,datClass,dvPortCode,trCode),Port_A.class);
    }

    @Override
    public HashMap<String,Port_A> getAssList(){
        return getService().getAssList();
    }

    @Override
    public String saveToOftenUsePort(List<Port_A> basePojoList){
        return getService().saveToOftenUsePort(castToBasePojoList(basePojoList));
    }

    @Override
    public List<String> getOftenUsePortList(){
        return getService().getOftenUsePortList();
    }

    @Override
    public String deleteOftenUsePort(List<Port_A> basePojoList){
        return getService().deleteOftenUsePort(castToBasePojoList(basePojoList));
    }

    @Override
    public RestfulQueryResult<Port_A> doPortFilterRes(String isDataRight,String datClass,String dvPortCode,String trCode,String menuId){
        return queryResToT(getService().doPortFilterRes(isDataRight,datClass,dvPortCode,trCode,menuId),Port_A.class);
    }


}