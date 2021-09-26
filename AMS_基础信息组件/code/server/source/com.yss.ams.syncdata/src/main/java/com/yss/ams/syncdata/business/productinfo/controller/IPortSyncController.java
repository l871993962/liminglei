package com.yss.ams.syncdata.business.productinfo.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.syncdata.business.productinfo.service.IPortSyncService;
import com.yss.ams.syncdata.business.productinfo.vo.SyncHandleDataVo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
/**
*
* @author neil
* @date 2020-09-07 18:11:10
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.syncdata.business.productinfo.service.impl.PortSyncService",
interfaceClass = com.yss.ams.syncdata.business.productinfo.service.IPortSyncService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IPortSyncService", menuId = "productsInfo")
public interface IPortSyncController extends IBaseController<IPortSyncService> {

	/**
	 * 数据同步方法
	 * @param pojo 同步数据
	 * @param operType 操作类型：SYNC_ADD(新增)、SYNC_DEL(删除)、SYNC_UPD(修改)
	 * @return
	 * @throws Exception
	 */
	@POST
    @Path("/syncHandleData")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public String syncHandleData(SyncHandleDataVo vo) throws Exception;
}