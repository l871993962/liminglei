package com.yss.ams.product.information.modules.pg.portgrouprela.controller.impl;

import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.support.modules.pg.portgrouprela.controller.IPortGroupRelaController;
import com.yss.ams.product.information.support.modules.pg.portgrouprela.pojo.PortGroupRela;
import com.yss.ams.product.information.support.modules.pg.portgrouprela.service.IPortGroupRelaService;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortGroupRelaControllerImpl extends AbstractBaseServiceBusController<PortGroupRela,IPortGroupRelaService> implements IPortGroupRelaController {

    @Override
    public List<Port> querySelectedPortList(String c_group_code){
        return getService().querySelectedPortList(c_group_code);
    }

    @Override
    public Map<String,com.yss.framework.api.common.co.Port> getFWPortByGroupCode(String groupCode){
        return getService().getFWPortByGroupCode(groupCode);
    }

    @Override
    public Map<String,Port> getZJPortByGroupCode(String groupCode){
        return getService().getZJPortByGroupCode(groupCode);
    }

	@Override
	public Map<String, List<String>> queryGroupCodeByPortCodeList(
			List<String> portCodeList) {
		return getService().queryGroupCodeByPortCodeList(portCodeList);
	}

	@Override
	public Map<String, List<String>> queryAllGroupCode() {
		return getService().queryAllGroupCode();
	}

}