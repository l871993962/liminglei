package com.yss.ams.base.information.modules.bi.org.controller.impl;

import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.org.controller.IOrgLinkRelaController;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.pojo.OrgVo;
import com.yss.ams.base.information.support.bi.org.service.IOrgLinkRelaService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class OrgLinkRelaControllerImpl extends AbstractBaseServiceBusController<Org,IOrgLinkRelaService> implements IOrgLinkRelaController {

    @Override
    public List<Org> getOrgLinkManList(String orgCode){
        return getService().getOrgLinkManList(orgCode);
    }

    @Override
    public Map<String,Org> getOrgLinkManData(String orgCode){
        return getService().getOrgLinkManData(orgCode);
    }

    @Override
    public void updateOrgLinkMan(OrgVo vo){
         getService().updateOrgLinkMan(vo.getOrgCode(),vo.getOrgList());
    }

    @Override
    public void deleteOrgLinkMan(String orgCode){
        getService().deleteOrgLinkMan(orgCode);
    }


}