package com.yss.ams.syncdata.support.modules.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.syncdata.support.modules.base.service.ISyncGenerateService;
import com.yss.framework.api.busoperservice.BizItem;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseOperController;
import com.yss.framework.api.service.ServiceException;
import com.yss.ams.base.information.common.YssServiceIdConstant;
/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.syncdata.modules.base.service.impl.SyncGenerateService",
interfaceClass = com.yss.ams.syncdata.support.modules.base.service.ISyncGenerateService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "ISyncGenerateService", menuId = "syncdata")
public interface ISyncGenerateController extends IBaseOperController<ISyncGenerateService> {

	/**
	 * 初始化
	 * 
	 * @param userStatus
	 */
    @POST
    @Path("/init")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public void init(HashMap<String, Object> paraMap);	
	
	/**
	 * 获取全部的备选的业务项目
	 * @return　业务项列表
	 * @throws ServiceException
	 */
    @POST
    @Path("/getBizItems")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<BizItem> getBizItems() throws ServiceException;
	
	/**
	 * Orlando 20150519 增加参数，如果没空或者长度为0就走原来的逻辑
	 * 用于任务调度：通过 组合代码获取组合相关的接口
	 * @param codes
	 * @return
	 * @throws ServiceException
	 */
    @POST
    @Path("/getBizItemsByList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<BizItem> getBizItems(List<String> codes) throws ServiceException;
	
	/**
	 * 获取全部的父节点的业务项目
	 * @return　业务项列表
	 * @throws ServiceException
	 */
    @POST
    @Path("/getRootBizItems")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<BizItem> getRootBizItems() throws ServiceException;

	/**
	 * 日终执行的入口方法
	 * 
	 * @return　返回
	 * @throws Exception
	 */
    @POST
    @Path("/execute")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public abstract Map.Entry<String, List<BEN_RECORD>> execute() throws Exception;



	 /**
	  * 20150724 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看
	  * 获取业务日志集合
	  * @return
	  */
    @POST
    @Path("/getListRecord")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<BEN_RECORD> getListRecord();


}