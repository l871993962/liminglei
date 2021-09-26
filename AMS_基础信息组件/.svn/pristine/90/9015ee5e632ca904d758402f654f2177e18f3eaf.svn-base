package com.yss.ams.base.information.modules.bi.region.service.impl;

import java.util.List;

import com.yss.ams.base.information.modules.bi.region.dao.AreaDao;
import com.yss.ams.base.information.modules.bi.region.dao.AreaSqlBuilder;
import com.yss.ams.base.information.support.bi.region.pojo.Area;
import com.yss.ams.base.information.support.bi.region.service.IAreaService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

/**
 * 地区信息服务类
 * @author 马向峰 拆分
 *
 */
public class AreaService extends ServiceBus<AreaService> implements IAreaService {

	private AreaDao serviceDao = null;
	public AreaService() throws Exception{
		serviceDao = new AreaDao(DbPoolFactory.getInstance().getPool(), new AreaSqlBuilder());
		dao = serviceDao;
		}

	/**
	 * 所有地区信息
	 * @param planType
	 * @return
	 */
	public QueryRes getAllAreasByType(){
		QueryRes ques = new QueryRes();
		ques.setMenuId("base_area");
		ques.setDataList(serviceDao.getAllAreas());
		return ques;
	}

	/**
	 * 所有地区最上层所属地区
	 */
	public List<Area> getAllTopAreas() {
		return serviceDao.getAllTopAreas();
	}
	
	
}
