package com.yss.ams.sec.information.modules.sv.indexstock.controller.impl;

import com.yss.ams.sec.information.support.modules.sv.indexstock.controller.IIndexStockController;
import com.yss.ams.sec.information.support.modules.sv.indexstock.pojo.IndexStock;
import com.yss.ams.sec.information.support.modules.sv.indexstock.service.IIndexStockService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class IndexStockControllerImpl extends AbstractBaseServiceBusController<IndexStock,IIndexStockService> implements IIndexStockController {

    @Override
    public RestfulQueryResult<IndexStock> getUnSelectedSecBase(String c_Index_Code,String d_Begin) throws Exception{
        return queryResToT(getService().getUnSelectedSecBase(c_Index_Code,d_Begin),IndexStock.class);
    }

    @Override
    public RestfulQueryResult<IndexStock> getSelectedSecBase(String c_Index_Code,String d_Begin) throws Exception{
        return queryResToT(getService().getSelectedSecBase(c_Index_Code,d_Begin),IndexStock.class);
    }

    @Override
    public RestfulQueryResult<IndexStock> getLastSelectedSecBase(String c_Index_Code,String d_Begin) throws Exception{
        return queryResToT(getService().getLastSelectedSecBase(c_Index_Code,d_Begin),IndexStock.class);
    }

    @Override
    public RestfulQueryResult<IndexStock> getLastUnSelectedSecBase(String c_Index_Code,String d_Begin) throws Exception{
        return queryResToT(getService().getLastUnSelectedSecBase(c_Index_Code,d_Begin),IndexStock.class);
    }

}