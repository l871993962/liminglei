package com.yss.ams.product.information.support.modules.ab.port.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.product.information.support.modules.ab.port.service.IPortDocDynamicCheckDefaultService;
import com.yss.ams.product.information.support.modules.ab.port.vo.DynamicCheckVo;
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.resource.mgr.pojo.BusinessData;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.port.service.impl.PortDocDynamicCheckDefaultService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.port.service.IPortDocDynamicCheckDefaultService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortDocDynamicCheckDefaultService", menuId = "portfolio")
public interface IPortDocDynamicCheckDefaultController extends IBaseController<IPortDocDynamicCheckDefaultService> {

	
	/** 
	 * @Title dynamicCheck 
	 * @Description 动态校验文档列表
	 * @author wangyangzan
	 * @date 2018年6月11日下午2:34:43
	 * @param docTypeName 文档类型名称
	 * @param mgrList 待校验文档列表
	 * @return
	 * @return String 校验结果
	 */
	@POST
    @Path("/deletePortFundRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	String dynamicCheck(DynamicCheckVo vo);
	
	/** 
	 * @Title getAllDataByFunCodeAndDataTime 
	 * @Description 获取功能模块业务数据时间范围内的所有业务数据封装
	 * @author wangyangzan
	 * @date 2018年6月12日上午10:52:49
	 * @param funCode 功能模块代码
	 * @param businessBeginDate 业务开始时间
	 * @param businessEndDate 业务结束时间
	 * @return
	 * @return List<BusinessData> 业务数据集，包含功能模块、投资组合、业务数据ID、业务数据时间等属性
	 */
	@POST
    @Path("/deletePortFundRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	List<BusinessData> getAllDataByFunCodeAndDataTime(String funCode, String businessBeginDate, String businessEndDate);
	
	/** 
	 * @Title getAllDataIdByCondition 
	 * @Description 获取功能模块指定数据ID集的所有业务数据封装
	 * @author wangyangzan
	 * @date 2018年6月12日上午10:52:49
	 * param funCode 功能模块代码
	 * @param dataIds 指定数据ID集，以","分隔
	 * @return
	 * @return List<BusinessData> 业务数据集，包含功能模块、投资组合、业务数据ID、业务数据时间等属性
	 */
	@POST
    @Path("/deletePortFundRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	List<BusinessData> getAllDataByFunCodeAndDataIds(String funCode, String dataIds);
	

}