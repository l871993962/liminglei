package com.yss.ams.base.information.support.sys.cashflow.service;

import java.util.ArrayList;

import com.yss.ams.base.information.support.sys.cashflow.pojo.CashFlow;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 现金流标记字典普通服务接口，主要进行增删改查操作
 * @author yuankai 基础信息拆分 2017.5.31
 *
 */
@RestfulSupported
public interface ICashFlowService extends IControlDataService{
	/**
	 * 取得所有数据(只包含代码)
	 * @author hs
	 * @date 2016-08-16
	 * @return
	 */
	public ArrayList<CashFlow> getCashFlowCode() throws ServiceException;

}
