package com.yss.ams.base.information.modules.bi.salesnet.controller.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.salesnet.controller.ISalesNetController;
import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNet;
import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNetVo;
import com.yss.ams.base.information.support.bi.salesnet.service.ISalesNetService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class SalesNetControllerImpl extends AbstractBaseServiceBusController<SalesNet,ISalesNetService> implements ISalesNetController {

    @Override
    public List<SalesNet> queryPdNet(HashMap<String,String> paraDict){
        return castToListT(getService().queryPdNet(paraDict));
    }

    @Override
    public void deleteNetInfoByVendorCode(SalesNetVo vo){
         getService().deleteNetInfoByVendorCode(vo.getVendorCodes().toArray(new String[]{}),vo.getConn());
    }

	
}