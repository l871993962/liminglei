package com.yss.ams.sec.information.modules.sv.base.controller.impl;

import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseZqController;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseZqService;
import com.yss.ams.sec.information.support.modules.sv.base.vo.TransSecToPlCodeVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class SecBaseZqControllerImpl extends AbstractBaseServiceBusController<SecBase,ISecBaseZqService> implements ISecBaseZqController {

    @Override
    public String singleSecInitFi(String secCode) throws Exception{
        return getService().singleSecInitFi(secCode);
    }

    @Override
    public String multipleSecInitFi(List<SecBase> secList) throws Exception{
        return getService().multipleSecInitFi(secList);
    }

    @Override
    public String ruleIsOpen(String zhgz,String type) throws Exception{
        return getService().ruleIsOpen(zhgz,type);
    }

    //List<SecBase> secList, String zhgz
	@Override
	public String transSecToPlCode(TransSecToPlCodeVo vo) throws Exception {
		 return getService().transSecToPlCode(vo.getSecList(),vo.getZhgz());
	}

}