package com.yss.ams.sec.information.modules.plateset.platesub.service.impl;

import java.sql.Connection;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.plateset.platesub.dao.PlateSubDao;
import com.yss.ams.sec.information.modules.plateset.platesub.dao.PlateSubSqlBuilder;
import com.yss.ams.sec.information.support.modules.plateset.platesub.service.IPlateSubService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * STORY #33682 彭博证券信息接口_重新设计
 * xiaozhilong 20161122
 */
public class PlateSubService extends ServiceBus<PlateSubService> implements IPlateSubService {

	private PlateSubDao serviceDao = null;
	public PlateSubService() throws Exception{
		serviceDao = new PlateSubDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new PlateSubSqlBuilder());
		dao = serviceDao;
		}
	
	@Override
	public String updatePlateById(List<BasePojo> pojoList) {
		Connection conn = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = serviceDao.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;			
			serviceDao.updatePlateById(pojoList);			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			serviceDao.endTransFinal(conn, bTrans);
			serviceDao.releaseConnection(conn);
		}
		return null;
	}
}
