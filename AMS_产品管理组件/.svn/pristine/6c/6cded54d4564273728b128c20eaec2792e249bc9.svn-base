package com.yss.ams.product.information.modules.aa.portcls.controller.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcls.controller.IPortClsController;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IPortClsService;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.DeleteByIdVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.InsertVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.UpdateByIdVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortClsControllerImpl extends AbstractBaseServiceBusController<PortCls,IPortClsService> implements IPortClsController {

    @Override
    public void updateDueDate(String portCode,String dueDate){
         getService().updateDueDate(portCode,dueDate);
    }

    @Override
    public String getPortClsCode(String portCode,String portClsType){
        return getService().getPortClsCode(portCode,portClsType);
    }

    @Override
    public List<PortCls> getPortClsByUser(String userCode){
        return getService().getPortClsByUser(userCode);
    }

    @Override
    public List<PortCls> getPortClsByPortCode(String portCode){
        return getService().getPortClsByPortCode(portCode);
    }

    @Override
    public String checkDate(HashMap<String,String> paraMap){
        return getService().checkDate(paraMap);
    }

    @Override
    public String checkDateQSRQ(HashMap<String,String> paraMap){
        return getService().checkDateQSRQ(paraMap);
    }

	@Override
	public String insert(InsertVo vo) {
		 return getService().insert(vo.getPojo(),vo.getConn());
	}

	//List<BasePojo> list, Connection conn
	@Override
	public String deleteById(DeleteByIdVo vo) {
		 return getService().deleteById(vo.getList(),vo.getConn());
	}
	
	@Override
	public String updateById(UpdateByIdVo vo) {
		 return getService().updateById(vo.getPojo(),vo.getConn());
	}

}