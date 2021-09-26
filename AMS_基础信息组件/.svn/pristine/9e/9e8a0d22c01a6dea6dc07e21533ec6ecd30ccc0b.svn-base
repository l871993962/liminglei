package com.yss.ams.syncdata.business.productinfo.controller.impl;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.syncdata.business.productinfo.controller.IPortSyncController;
import com.yss.ams.syncdata.business.productinfo.service.IPortSyncService;
import com.yss.ams.syncdata.business.productinfo.vo.SyncHandleDataVo;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncPort;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class PortSyncControllerImpl extends AbstractBaseController<IPortSyncService> implements IPortSyncController {

	@Override
	public String syncHandleData(SyncHandleDataVo vo) throws Exception {
		List<Object> pojos = new ArrayList<Object>();
		for(SyncPort pojo : vo.getPojos()){
			pojos.add(pojo);
		}
		
		return getService().syncHandleData(pojos,vo.getOperType());
	}

}