package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.Port_A;
import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaOrganController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaOrgan;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaOrganServcie;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaOrganPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaOrganControllerImpl extends AbstractBaseServiceBusController<PortRelaOrgan,IPortRelaOrganServcie> implements IPortRelaOrganController{

    @Override
    public RestfulQueryResult<PortRelaOrgan> queryPortRelaOrgan(HashMap<String,Object> paraMap) throws Exception{
        return queryResToT(getService().queryPortRelaOrgan(paraMap),PortRelaOrgan.class);
    }

    @Override
    public RestfulQueryResult<PortRelaOrgan> queryPortRelaOrganPage(QueryPortRelaOrganPageVo vo) throws Exception{
        return queryResToT(getService().queryPortRelaOrganPage(vo.getParaMap(),vo.getPage()),PortRelaOrgan.class);
    }

    @Override
    public String checkORGConsignerForPort(HashMap<String,String> map){
        return getService().checkORGConsignerForPort(map);
    }

    @Override
    public List<Port_A> getPortInfoByConsigner(String taxConsigner){
        return getService().getPortInfoByConsigner(taxConsigner);
    }

    @Override
    public HashMap<String,List<String>> getPortRelaOrgByPortAndDvType(String portCodes,String dvType){
        return getService().getPortRelaOrgByPortAndDvType(portCodes,dvType);
    }

}