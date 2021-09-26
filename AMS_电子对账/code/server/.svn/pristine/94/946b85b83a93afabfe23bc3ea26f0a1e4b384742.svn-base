package com.yss.uco.elecreco.automic.task.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.api.controller.IAutomaticTaskDynamicController;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
url = "com/yss/uco/elecreco/automic/task/controller/IElecDayDzTypeDynamicServiceController",
impl = "com.yss.uco.elecreco.automic.task.ElecDayDzTypeDynamicService",
interfaceClass = IAutomaticTaskDynamicService.class,
productLine = ProductLine.ELECRECO,
serviceMapId = "ElecDayDzTypeDynamicService")
public interface IElecDayDzTypeDynamicServiceController extends IAutomaticTaskDynamicController{

}
