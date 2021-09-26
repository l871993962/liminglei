package com.yss.ams.product.information.support.modules.pg.portgrouprela.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.pg.portgrouprela.pojo.PortGroupRela;
import com.yss.ams.product.information.support.modules.pg.portgrouprela.service.IPortGroupRelaService;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.pg.portgrouprela.service.impl.PortGroupRelaService",
interfaceClass = com.yss.ams.product.information.support.modules.pg.portgrouprela.service.IPortGroupRelaService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortGroupRelaService", menuId = "pd_portGroupRela")
public interface IPortGroupRelaController extends IBaseServiceBusController<PortGroupRela,IPortGroupRelaService> {


    @POST
    @Path("/querySelectedPortList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Port> querySelectedPortList(String c_group_code);

    @POST
    @Path("/getFWPortByGroupCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,com.yss.framework.api.common.co.Port> getFWPortByGroupCode(String groupCode);

    @POST
    @Path("/getZJPortByGroupCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Map<String,Port> getZJPortByGroupCode(String groupCode);
    /**
	 * STORY #103420 产品组件提供批量查找群组的方法
	 * @param portCodeList
	 * @return
	 */
    @POST
    @Path("/queryGroupCodeByPortCodeList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Map<String, List<String>> queryGroupCodeByPortCodeList(List<String> portCodeList) ;
	
	/**
	 *  STORY #103420 产品组件提供批量查找群组的方法
	 * @return
	 */
    @POST
    @Path("/queryAllGroupCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public Map<String, List<String>> queryAllGroupCode() ;

}