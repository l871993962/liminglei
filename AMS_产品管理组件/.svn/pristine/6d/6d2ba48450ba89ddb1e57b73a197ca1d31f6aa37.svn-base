package com.yss.ams.product.information.support.modules.ab.port.service;

import java.util.List;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.right.service.IDataRightProvider;

/**
 * <产品基本信息>普通服务接口，主要进行增删改查操作
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */

@RestfulSupported
public interface IPortTansferService extends IServiceBus,IDataRightProvider,IKeyConvertDataService, IControlDataService {

	/**
	 *  到期确认
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  到期取消
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQX(List<Port> lstPort) throws ServiceException;

    /**
	 *  清算确认
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operQSQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  清算取消
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operQSQX(List<Port> lstPort) throws ServiceException;
    
    /**
   	 *  清算关账
   	 *  @param lstPort 组合列表
   	 *  @return 执行状态
   	 */
    String operQSGZ(List<Port> lstPort) throws ServiceException,Exception;

     /**
   	  *  关账撤销
   	  *  @param lstPort 组合列表
   	  *  @return 执行状态
   	  */
     String operGZCX(List<Port> lstPort) throws ServiceException;

   
	
}
