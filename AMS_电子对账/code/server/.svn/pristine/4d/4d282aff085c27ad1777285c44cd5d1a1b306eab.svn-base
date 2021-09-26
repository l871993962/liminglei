package com.yss.uco.elecreco.bi.elecrela.controller.impl;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.uco.elecreco.bi.elecrela.controller.IElecRelaServiceController;
import com.yss.uco.elecreco.support.bean.ElecRela;
import com.yss.uco.elecreco.support.service.IElecRelaService;


/**
*
* @author tongdengke
* @date 2020-09-19 11:12:15
*/
public class ElecRelaServiceControllerImpl 
						extends AbstractBaseServiceBusController<ElecRela,IElecRelaService> 
											implements IElecRelaServiceController{

    @Override
    public List<BasePojo> getDataList(){
        return getService().getDataList();
    }

    @Override
    public List<BasePojo> getDataListByName(List<String> paraList){
        return getService().getDataListByName(paraList);
    }

    @Override
    public Map<String,String> getZbCodeByKeyCode(String paras){
        return getService().getZbCodeByKeyCode(paras);
    }

}