package com.yss.ams.product.information.support.modules.aa.portcustom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcustom.pojo.PortCustom;
import com.yss.ams.product.information.support.modules.aa.portcustom.vo.InsertCustomPortVo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * <常用产品设置>普通服务接口，主要进行增删改查操作
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IPortCustomService extends IServiceBus {

	/**
	 * 根据条件获取默认的组合列表
	 * 
	 * @param paradict
	 * @return ArrayList<String> 组合列表
	 * @throws ServiceException
	 */
	public ArrayList<String> getUserDefaultPort(HashMap<String, String> paradict) throws ServiceException;

	/**
	 * 获取资产类型
	 * 
	 * @return ArrayList<String> 资产类型
	 * @throws ServiceException
	 */
	public ArrayList<String> getAssetType() throws ServiceException;

	/**
	 * 根据条件获取显示类型
	 * 
	 * @param codeMap 条件
	 * @return String 显示类型
	 * @throws ServiceException
	 */
	public String getShowType(HashMap<String, String> codeMap)
			throws ServiceException;

	/**
	 * 根据参数删除数据
	 * 
	 * @param param 参数
	 * @return String 执行结果
	 * @throws ServiceException
	 */
	public String deleteCustomPort(HashMap<String, String> param)
			throws ServiceException;
	
	/**
	 * 保存数据
	 * 
	 * @param list 组合列表
	 * @param type 类型
	 * @return String 执行结果
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="insertCustomPort",arguTypes = InsertCustomPortVo.class)
	public String insertCustomPort(@LinkControllerMethodArgu("list")List<PortCustom> list,@LinkControllerMethodArgu("type")String type) throws ServiceException;
}
