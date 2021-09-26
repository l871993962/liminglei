package com.yss.uco.elecreco.automic.task.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.factory.RestfulLocalServiceFactory;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.xmlPojo.TaskDynamicData;
import com.yss.uco.elecreco.automic.task.ElecDayDzTypeDynamicService;
import com.yss.uco.elecreco.automic.task.controller.IElecDayDzTypeDynamicServiceController;

public class ElecDayDzTypeDynamicServiceControllerImpl extends AbstractBaseController<IAutomaticTaskDynamicService> 
implements IElecDayDzTypeDynamicServiceController{



	@Override
	public List<TaskDynamicData> getParamDynamicData() {
		return getService().getParamDynamicData();
	}

	@Override
	public IAutomaticTaskDynamicService getService() {
		 return RestfulLocalServiceFactory.createService(IAutomaticTaskDynamicService.class,
                 YssServiceFactory.getInstance().createServiceByImplClass(IAutomaticTaskDynamicService.class,
                		 ElecDayDzTypeDynamicService.class.getName()));
	}


}
