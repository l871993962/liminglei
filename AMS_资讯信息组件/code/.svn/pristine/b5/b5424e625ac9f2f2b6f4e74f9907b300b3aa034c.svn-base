package com.yss.ams.sec.information.modules.mp.secequ.controller.impl;

import java.util.List;

import com.yss.ams.sec.information.support.modules.mp.secequ.controller.ISecEquController;
import com.yss.ams.sec.information.support.modules.mp.secequ.pojo.SecEqu;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecEquService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class SecEquControllerImpl extends AbstractBaseServiceBusController<SecBase,ISecEquService> implements ISecEquController {

    @Override
    public List<SecBase> getRemindDJPX(int days){
        return getService().getRemindDJPX(days);
    }

    @Override
    public List<SecBase> getRemindZQPS(int days){
        return getService().getRemindZQPS(days);
    }

}