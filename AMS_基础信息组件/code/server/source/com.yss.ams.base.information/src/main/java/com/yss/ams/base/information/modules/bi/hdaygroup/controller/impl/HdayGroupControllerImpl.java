package com.yss.ams.base.information.modules.bi.hdaygroup.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.controller.IHdayGroupController;
import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHdayGroupService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class HdayGroupControllerImpl extends AbstractBaseServiceBusController<HdayGroup,IHdayGroupService> implements IHdayGroupController {

    @Override
    public RestfulQueryResult<HdayGroup> getTreeViewData(HashMap<String,String> paraMap){
        return queryResToT(getService().getTreeViewData(paraMap),HdayGroup.class);
    }

    @Override
    public List<HdayGroup> queryAllHdayGroup(){
        return getService().queryAllHdayGroup();
    }

}