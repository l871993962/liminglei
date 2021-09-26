package com.yss.ams.syncdata.modules.base.controller.impl;

import java.util.List;

import com.yss.ams.syncdata.support.modules.base.controller.ISyncDataController;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncData;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncModule;
import com.yss.ams.syncdata.support.modules.base.service.ISyncDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class SyncDataControllerImpl extends AbstractBaseController<ISyncDataService> implements ISyncDataController {

    @Override
    public String getSystemCode() throws Exception{
        return getService().getSystemCode();
    }

    @Override
    public String ignoreMessages(List<SyncData> syncDatas){
        return getService().ignoreMessages(syncDatas);
    }

    @Override
    public void syncSuccess(List<String> ids){
         getService().syncSuccess(ids);
    }

    @Override
    public List<SyncModule> queryAllFuncodeCfg(){
        return getService().queryAllFuncodeCfg();
    }

    @Override
    public String saveSyncModule(List<SyncModule> syncModule) throws Exception{
        return getService().saveSyncModule(syncModule);
    }

}