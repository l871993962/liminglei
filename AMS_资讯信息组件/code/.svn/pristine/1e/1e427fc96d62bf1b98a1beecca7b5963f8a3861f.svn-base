package com.yss.ams.sec.information.modules.sv.fipay.controller.impl;

import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.fipay.controller.IFiPayController;
import com.yss.ams.sec.information.support.modules.sv.fipay.pojo.FiPay;
import com.yss.ams.sec.information.support.modules.sv.fipay.service.IFiPayService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class FiPayControllerImpl extends AbstractBaseServiceBusController<FiPay,IFiPayService> implements IFiPayController {

    @Override
    public String checkDeleteData(List<BasePojo> pojoList){
        return getService().checkDeleteData(pojoList);
    }

    @Override
    public String singleSecFiPayInit(FiPay fiPay) throws Exception{
        return getService().singleSecFiPayInit(fiPay);
    }

    @Override
    public String multipleFiPayInit(List<FiPay> fiPayList) throws Exception{
        return getService().multipleFiPayInit(fiPayList);
    }

}