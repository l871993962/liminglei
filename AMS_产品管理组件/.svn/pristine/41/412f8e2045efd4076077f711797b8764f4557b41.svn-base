package com.yss.ams.product.information.modules.ab.port.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.port.controller.IRightPortController;
import com.yss.ams.product.information.support.modules.ab.port.service.IRightPortService;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class RightPortControllerImpl extends AbstractBaseServiceBusController<Port,IRightPortService> implements IRightPortController {

    @Override
    public List<Port> getRightManagePortList(HashMap<String,Object> paraMap) throws Exception{
        return getService().getRightManagePortList(paraMap);
    }

    @Override
    public List<Port> getRightManagePortList() throws Exception{
        return getService().getRightManagePortList();
    }

    @Override
    public List<Port> getPortInfoList(HashMap<String,Object> paraMap) throws Exception{
        return getService().getPortInfoList(paraMap);
    }

    @Override
    public List<Port> getRightManagePortTree(HashMap<String,Object> paraMap) throws Exception{
        return getService().getRightManagePortTree(paraMap);
    }

	@Override
	public List<Port> getRightManagePortListExpertAdd(
			HashMap<String, Object> paraMap) throws Exception {
		return getService().getRightManagePortListExpertAdd(paraMap);
	}

}