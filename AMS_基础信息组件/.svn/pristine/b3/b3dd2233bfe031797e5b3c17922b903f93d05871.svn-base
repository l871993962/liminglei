/**
 *
 * @Title: AutoExpRouteDataService.java 
 * @Package com.yss.ams.base.information.modules.sys.portbusinessrange.service.impl 
 * @date 2019年5月20日 上午11:15:10 
 * @version V1.0
 * @Stroy/Bug
 * @author xiadeqi   
 */
package com.yss.ams.base.information.modules.sys.portbusinessrange.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.portbusinessrange.dao.PortBusinessRangeDao;
import com.yss.ams.base.information.modules.sys.portbusinessrange.dao.PortBusinessRangeSqlBuilder;
import com.yss.ams.base.information.support.sys.portbusinessrange.controller.IBaseAutoRouteBusiController;
import com.yss.fast.task.support.automatic.service.IAutoRouteBusiService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.db.DbPoolFactory;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/** 
 * 查询产品业务范围有关的路由条件信息
 * @ClassName: AutoExpRouteDataService 
 * @date 2019年5月20日 上午11:15:10
 * @Stroy73411/Bug
 * @author xiadeqi 
 */
public class AutoRouteBusiControllerImpl extends AbstractBaseController<IAutoRouteBusiService> implements IBaseAutoRouteBusiController{

	@Override
	public List<String> queryProductBusiCodeByProductCode(String porgCode) {
		return getService().queryProductBusiCodeByProductCode(porgCode);
	}

	@Override
	public boolean queryCheckStatusByPortCodeAndBusiCode(String portCode,
			String busiCode) {
		return getService().queryCheckStatusByPortCodeAndBusiCode(portCode,busiCode);
	}

	@Override
	public HashMap<String, String> getRouteInfoByVarCode(String varCode) {
		return getService().getRouteInfoByVarCode(varCode);
	}

	
}
