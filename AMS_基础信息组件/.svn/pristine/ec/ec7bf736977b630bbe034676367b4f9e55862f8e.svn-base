/**
 *
 * @Title: AutoExpRouteDataService.java 
 * @Package com.yss.ams.base.information.modules.sys.portbusinessrange.service.impl 
 * @date 2019年5月20日 上午11:15:10 
 * @version V1.0
 * @Stroy/Bug
 * @author xiadeqi   
 */
package com.yss.ams.base.information.modules.sys.portbusinessrange.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.portbusinessrange.dao.PortBusinessRangeDao;
import com.yss.ams.base.information.modules.sys.portbusinessrange.dao.PortBusinessRangeSqlBuilder;
import com.yss.fast.task.support.automatic.service.IAutoRouteBusiService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/** 
 * 查询产品业务范围有关的路由条件信息
 * @ClassName: AutoExpRouteDataService 
 * @date 2019年5月20日 上午11:15:10
 * @Stroy73411/Bug
 * @author xiadeqi 
 */
public class AutoRouteBusiServiceImpl extends ServiceBus<AutoRouteBusiServiceImpl> implements IAutoRouteBusiService{

	private PortBusinessRangeDao portBusinessRangeDao = null;
	public AutoRouteBusiServiceImpl() {
		portBusinessRangeDao = new PortBusinessRangeDao(DbPoolFactory.getInstance().getPool(), new PortBusinessRangeSqlBuilder());
	}

	/* (非 Javadoc) 
	 * <p>Title: queryProductBusiCodeByProductCode</p> 
	 * <p>Description: </p> 
	 * @param porgCode
	 * @return 
	 * @see com.yss.fast.task.support.automatic.service.IAutoRouteBusiService#queryProductBusiCodeByProductCode(java.lang.String) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public List<String> queryProductBusiCodeByProductCode(String portCode) {
		return portBusinessRangeDao.queryProductBusiCodeByProductCode(portCode);
	}
	
	/**
	 * BUG #288785 【华宝基金】自动化流程中根据产品业务范围判断审核才走
	 * 根据产品代码，业务范围查询产品业务范围审核状态
	 * @param portCode
	 * @param busiCode
	 * @return
	 */
	@Override
	public boolean queryCheckStatusByPortCodeAndBusiCode(String portCode, String busiCode) {
		return portBusinessRangeDao.queryCheckStatusByPortCodeAndBusiCode(portCode, busiCode);
	}

	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * 获取路由值
	 * @param varCode
	 * @ret
	 */
	@Override
	public HashMap<String, String> getRouteInfoByVarCode(String varCode) {
		HashMap<String, String> map = new HashMap<String, String>();
		List<Vocabulary> list = portBusinessRangeDao.getDataListByType(varCode);
		for (Vocabulary vocabulary : list) {
			map.put(vocabulary.getC_DV_CODE(), vocabulary.getC_DV_NAME());
		}
		return map;
	}
}
