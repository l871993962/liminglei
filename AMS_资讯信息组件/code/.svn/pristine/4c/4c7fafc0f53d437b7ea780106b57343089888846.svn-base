package com.yss.ams.sec.information.modules.mp.preStockInterest.controller.impl;

import java.util.List;

import com.yss.ams.sec.information.support.modules.mp.preStockInterest.controller.IPreStockInterestController;
import com.yss.ams.sec.information.support.modules.mp.preStockInterest.pojo.PreStockInterest;
import com.yss.ams.sec.information.support.modules.mp.preStockInterest.service.IPreStockInterestService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class PreStockInterestControllerImpl extends AbstractBaseServiceBusController<PreStockInterest,IPreStockInterestService> implements IPreStockInterestController {

    @Override
    public String singleSecInitFi(PreStockInterest pre)  throws Exception{
        return getService().singleSecInitFi(pre);
    }

    @Override
    public String multiplePreInitFi(List<PreStockInterest> preList) throws Exception{
        return getService().multiplePreInitFi(preList);
    }

}