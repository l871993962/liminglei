package com.yss.uco.elecreco.automic.task.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.factory.RestfulLocalServiceFactory;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.xmlPojo.TaskDynamicData;
import com.yss.uco.elecreco.automic.task.ElecGeneTaskDynamicService;
import com.yss.uco.elecreco.automic.task.controller.IElecGeneTaskDynamicServiceController;

public class ElecGeneTaskDynamicServiceControllerImpl extends AbstractBaseController<IAutomaticTaskDynamicService> 
implements IElecGeneTaskDynamicServiceController{

	@Override
	public List<TaskDynamicData> getParamDynamicData() {
		return getService().getParamDynamicData();
	}

	@Override
	public IAutomaticTaskDynamicService getService() {
		 return RestfulLocalServiceFactory.createService(IAutomaticTaskDynamicService.class,
                 YssServiceFactory.getInstance().createServiceByImplClass(IAutomaticTaskDynamicService.class,
                		 ElecGeneTaskDynamicService.class.getName()));
	}
}
