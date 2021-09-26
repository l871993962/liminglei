package com.yss.ams.product.information.modules.pg.portgroup.controller.impl;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.pg.portgroup.controller.IPortGroupController;
import com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortGroupControllerImpl extends AbstractBaseServiceBusController<PortGroup,IPortGroupService> implements IPortGroupController {

    @Override
    public String checkGroupCode(String groupCode){
        return getService().checkGroupCode(groupCode);
    }

    @Override
    public String checkGroupCode1(String groupCode,String ciden){
        return getService().checkGroupCode(groupCode,ciden);
    }

    @Override
    public RestfulQueryResult<PortGroup> getPlanRelaPortGroupAdd(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().getPlanRelaPortGroupAdd(paraMap),PortGroup.class);
    }

    @Override
    public RestfulQueryResult<PortGroup> getPlanRelaPortGroupBrow(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().getPlanRelaPortGroupBrow(paraMap),PortGroup.class);
    }

    @Override
    public RestfulQueryResult<PortGroup> getPortGroupA(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPortGroupA(paraMap),PortGroup.class);
    }

}