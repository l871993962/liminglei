package com.yss.ams.base.information.modules.bi.orgmgr.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.orgmgr.controller.IOrgMgrController;
import com.yss.ams.base.information.support.bi.orgmgr.pojo.OrgMgr;
import com.yss.ams.base.information.support.bi.orgmgr.service.IOrgMgrService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class OrgMgrControllerImpl extends AbstractBaseServiceBusController<OrgMgr,IOrgMgrService> implements IOrgMgrController {

    @Override
    public List<String> getAllMBRCodes(){
        return getService().getAllMBRCodes();
    }

    @Override
    public RestfulQueryResult<OrgMgr> getPortRelaMember(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPortRelaMember(paraMap),OrgMgr.class);
    }

}