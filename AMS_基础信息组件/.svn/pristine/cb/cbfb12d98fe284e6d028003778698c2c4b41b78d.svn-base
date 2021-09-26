package com.yss.ams.base.information.modules.sys.convertdict.zdorg.controller.impl;

import java.util.HashMap;

import com.yss.ams.base.information.support.sys.convertdict.zdorg.controller.IZdOrgController;
import com.yss.ams.base.information.support.sys.convertdict.zdorg.pojo.ZdOrgTreeView;
import com.yss.ams.base.information.support.sys.convertdict.zdorg.service.IZdOrgService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class ZdOrgControllerImpl extends AbstractBaseServiceBusController<ZdOrgTreeView,IZdOrgService> implements IZdOrgController {

    @Override
    public RestfulQueryResult<ZdOrgTreeView> getTreeViewData(HashMap<String,Object> paraMap){
        return queryResToT(getService().getTreeViewData(paraMap),ZdOrgTreeView.class);
    }

}