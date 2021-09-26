package com.yss.ams.sec.information.modules.sv.fiincome.controller.impl;

import java.util.HashMap;

import com.yss.ams.sec.information.support.modules.sv.fiincome.controller.IFiIncomeController;
import com.yss.ams.sec.information.support.modules.sv.fiincome.pojo.FiIncome;
import com.yss.ams.sec.information.support.modules.sv.fiincome.service.IFiIncomeService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class FiIncomeControllerImpl extends AbstractBaseServiceBusController<FiIncome,IFiIncomeService> implements IFiIncomeController {

    @Override
    public String calcBeforeTaxAndDue(HashMap<String,Object> paraMap) throws Exception{
        return getService().calcBeforeTaxAndDue(paraMap);
    }

}