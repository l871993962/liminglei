package com.yss.ams.base.information.modules.bi.hdaygroup.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.hdaygroup.dao.HdayGroupDao;
import com.yss.ams.base.information.modules.bi.hdaygroup.dao.HdayGroupSqlBuilder;
import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.ams.base.information.support.bi.hdaygroup.service.IHdayGroupService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 节假日群服务接口实现
 */

/**
 * 节假日群类型服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@DefaultCacheRefresh(group = CacheGroup.HDAY)
public class HdayGroupService extends ServiceBus<HdayGroupService> implements IHdayGroupService {

	private HdayGroupDao serviceDao = null;
	
	public HdayGroupService() throws Exception{
		serviceDao = new HdayGroupDao(DbPoolFactory.getInstance().getPool(), new HdayGroupSqlBuilder());
		dao = serviceDao;
		}
	
	/**
	 * 查询节假日群数据(树形结构显示)
	 * @param paraMap 参数MAP
	 * @return
	 */
	public QueryRes getTreeViewData(HashMap<String, String> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.queryTreeViewData(paraMap,this.bundleContext);
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,InformationActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("base_holidays_A");
		} catch (Exception ex) {
			logger.log("节假日群设置：查询节假日群数据(树形结构显示)失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * add by zuomingke
	 * date 20190713
	 * @return List<HdayGroup>
	 */
	public List<HdayGroup> queryAllHdayGroup(){
		return this.serviceDao.queryAllHdayGroup();
	}
}
