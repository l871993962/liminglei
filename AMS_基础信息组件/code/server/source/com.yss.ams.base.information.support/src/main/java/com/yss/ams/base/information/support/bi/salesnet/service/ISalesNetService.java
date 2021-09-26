package com.yss.ams.base.information.support.bi.salesnet.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNetVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 销售网点设置普通服务接口类，主要进行增删改查操作
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@RestfulSupported
public interface ISalesNetService extends IServiceBus {

	/**
	 * 查询产品计划录入收益率时加载的销售网点,只包含分行和村镇银行
	 * @param paraDict
	 * @return
	 */
	public List<BasePojo>  queryPdNet(HashMap<String, String> paraDict);
	
	/**
	 * Author : ChenLong
	 * Date   : 2017-12-29
	 * Status : Add
	 * Comment: 根据销售商代码删除网点信息
	 * @param vendorCodes 销售商代码集合
	 * @param conn
	 */
	@LinkControllerMethod(value="deleteNetInfoByVendorCode",arguTypes = SalesNetVo.class)
	public void deleteNetInfoByVendorCode(@LinkControllerMethodArgu("vendorCodes")String[] vendorCodes,@LinkControllerMethodArgu("conn")Connection conn);
}
