package com.yss.uco.elecreco.support.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.uco.elecreco.support.bean.ErTask;
import com.yss.uco.elecreco.support.service.IErTaskService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
*
* @author tongdengke
* @date 2020-09-19 11:12:12
*/
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.er.task.service.impl.ErTaskService",
interfaceClass = com.yss.uco.elecreco.support.service.IErTaskService.class,
productLine = ProductLine.ELECRECO, serviceMapId = "IErTaskService", menuId = "elecCheckMr")
public interface IErTaskServiceController extends IBaseServiceBusController<ErTask,IErTaskService> {


    @POST
    @Path("/startTask")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void startTask(String taskCode);

    @POST
    @Path("/stopTask")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void stopTask(String taskCode);

    @POST
    @Path("/startTask1/*对于重复的url，代码自动生成对URL进行自增+1操作，如果有更好的url命名方式，请自行修改！*/")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void startTask(@FormParam("taskCode") String taskCode,@FormParam("compulsory") boolean compulsory);

    @POST
    @Path("/startAllTask")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErTask> startAllTask();

    @POST
    @Path("/stopAllTask")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<ErTask> stopAllTask();

    @POST
    @Path("/getErTaskByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public ErTask getErTaskByCode(String taskCode);

    @POST
    @Path("/updateTaskByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateTaskByCode(ErTask task);

}