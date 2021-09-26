package com.yss.ifa.szt.tool.rptlog.controller.impl;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.ifa.szt.tool.pojo.ErRptLog;
import com.yss.ifa.szt.tool.rptlog.controller.IErRptLogController;
import com.yss.ifa.szt.tool.rptlog.service.IErRptLogService;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class ErRptLogControllerImpl extends AbstractBaseServiceBusController<ErRptLog,IErRptLogService> implements IErRptLogController {

    @Override
    public void deleteRptLog(int day){
         getService().deleteRptLog(day);
    }

    @Override
    public String queryLogById(String id){
        return getService().queryLogById(id);
    }

    @Override
    public void updateSN(String oldSn,String newSn){
         getService().updateSN(oldSn,newSn);
    }

}