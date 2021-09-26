package com.yss.uco.elecreco.automic.task.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.api.controller.IAutomaticTaskDynamicController;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
url = "com/yss/uco/elecreco/automic/task/controller/IElecEndWhereDynamicServiceController",
impl = "com.yss.uco.elecreco.automic.task.ElecEndWhereDynamicService",
interfaceClass = IAutomaticTaskDynamicService.class,
productLine = ProductLine.ELECRECO,
serviceMapId = "ElecEndWhereDynamicService")
public interface IElecEndWhereDynamicServiceController extends IAutomaticTaskDynamicController{

}
