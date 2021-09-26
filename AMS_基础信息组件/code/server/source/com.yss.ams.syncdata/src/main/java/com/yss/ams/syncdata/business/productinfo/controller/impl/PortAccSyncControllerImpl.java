package com.yss.ams.syncdata.business.productinfo.controller.impl;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.syncdata.business.productinfo.controller.IPortAccSyncController;
import com.yss.ams.syncdata.business.productinfo.pojo.PortAccSync;
import com.yss.ams.syncdata.business.productinfo.service.IPortAccSyncService;
import com.yss.ams.syncdata.business.productinfo.vo.SyncHandleDataVo;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncPort;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class PortAccSyncControllerImpl extends AbstractBaseController<IPortAccSyncService> implements IPortAccSyncController{

	@Override
	public String syncHandleData(SyncHandleDataVo vo) throws Exception {
		List<Object> pojos = new ArrayList<Object>();
		for(SyncPort pojo : vo.getPojos()){
			pojos.add(pojo);
		}
		return getService().syncHandleData(pojos,vo.getOperType());
		
	}

}