package com.yss.ams.sec.information.modules.mp.secmkt.controller;

import java.util.Date;
import java.util.List;

import com.yss.ams.sec.information.support.modules.mp.secmkt.controller.ISecMktController;
import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMkt;
import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMktVo;
import com.yss.ams.sec.information.support.modules.mp.secmkt.service.ISecMktService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class SecMktControllerImpl extends AbstractBaseServiceBusController<SecMkt,ISecMktService> implements ISecMktController {

	//Date date,String secCode
    @Override
    public double getRate(SecMktVo vo){
        return getService().getRate(vo.getDate(),vo.getSecCode());
    }

    @Override
    public void deleteSecMpMap(List<SecMkt> lstMp){
         getService().deleteSecMpMap(castToBasePojoList(lstMp));
    }

    //String PortCode,Date date
    @Override
    public List<String> QuerySGSecMpMap(SecMktVo vo) throws Exception{
        return getService().QuerySGSecMpMap(vo.getPortCode(),vo.getDate());
    }

    //List<String> secCode,Date date
    @Override
    public List<String> getPortCodeBySecCodeList(SecMktVo vo) throws Exception{
        return getService().getPortCodeBySecCodeList(vo.getSecCodelist(),vo.getDate());
    }
}