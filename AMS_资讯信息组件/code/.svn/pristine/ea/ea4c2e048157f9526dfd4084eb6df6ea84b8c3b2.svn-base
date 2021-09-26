package com.yss.ams.sec.information.support.modules.pub.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.busoperservice.IBaseOper;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
public interface IAssetStatsCtlInitService extends IBaseOper {

	/**
	 * 债券每百元利息初始化计算，为证券信息表中债券计算基准日期所在付息期间每天的每百元债券利息
	 * By Jinghehe 2014-3-15 新增一个根据日期段 和 证券内码secCodeList 进行初始化 每百元债券利息
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param secCodeList 证券内码list
	 * @throws Exception
	 */
	public void initBondPerHundInterest(Date startDate,Date endDate,List<String> secCodeList) throws Exception;
	
	public void initSingleSecBond(SecBase secBase, HashMap<String, Object> hmData) throws Exception;
}
