package com.yss.ams.syncdata.business.productinfo.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.syncdata.activator.SyncDataActivator;
import com.yss.ams.syncdata.business.productinfo.dao.PortAccSyncDao;
import com.yss.ams.syncdata.business.productinfo.dao.PortAccSyncSqlBuilder;
import com.yss.ams.syncdata.business.productinfo.pojo.PortAccSync;
import com.yss.ams.syncdata.business.productinfo.service.IPortAccSyncService;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncBankAcc;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheRefresh;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.util.StringUtil;

/**
 * STORY #58468 生命周期系统中的账户信息数据同步至基础组件的银行账户信息
 * @author lenovo
 *
 */
public class PortAccSyncServiceImpl implements IPortAccSyncService {

	private PortAccSyncDao serviceDao = null;
	private String syncDel = "SYNC_DEL";
	
	public PortAccSyncServiceImpl() throws Exception {
		serviceDao = new PortAccSyncDao(YssDbPoolFactory.getInstance().getDbPool(SyncDataActivator.class), 
				new PortAccSyncSqlBuilder());
	}

	/**
	 * 数据同步方法
	 * @param pojo 同步数据
	 * @param operType 操作类型：SYNC_ADD(新增)、SYNC_DEL(删除)、SYNC_UPD(修改)
	 * @return
	 * @throws Exception
	 */
	@Override
	@CacheRefresh(group = CacheGroup.FUNDACC)
	public String syncHandleData(List<Object> pojos, String operType) throws Exception {
		String result = MvcConstant._Success;
		Connection conn = null;
		try {
			conn = serviceDao.loadNewConnection();
			conn.setAutoCommit(false);

			for (int i = 0; i < pojos.size(); i++) {
				SyncBankAcc syncBankAcc = (SyncBankAcc) pojos.get(i);
				PortAccSync fundAcc = new PortAccSync();
				// 处理字段映射
				syncFundAcc(fundAcc, syncBankAcc);
				// 维护不同账户信息表数据
				dealSyncData(operType, "T_P_BI_FUND_ACC", "T_P_AB_PORT_ACC_RELA", fundAcc, conn);
				dealSyncData(operType, "T_C_CP_FUND_ACC", "T_P_AB_PORT_RELA", fundAcc, conn);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			result = MvcConstant._Fault;
			throw e;
		} finally {
			serviceDao.releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 处理同步数据
	 * @param operType
	 * @param fundTableName
	 * @param relaTableName
	 * @param fundAcc
	 * @param conn
	 */
	private void dealSyncData(String operType, String fundTableName, String relaTableName, 
			PortAccSync fundAcc, Connection conn) {
		serviceDao.setTableName(fundTableName, relaTableName);
		HashMap<String, Object> paraMap = new HashMap<String, Object>(4);
		paraMap.put("C_DC_CODE", "CNY");
		paraMap.put("C_OPEN_ACC_NAME", fundAcc.getC_OPEN_ACC_NAME());
		paraMap.put("C_OPEN_ACC_NO", fundAcc.getC_OPEN_ACC_NO());
		paraMap.put("C_OPEN_ADDR", fundAcc.getC_OPEN_ADDR());
		FundAcc cacheFundAcc = serviceDao.getFundAccByInfo(paraMap);

		if (syncDel.equals(operType)) {
			if (cacheFundAcc != null) {
				fundAcc.setId(cacheFundAcc.getId());
				serviceDao.deleteById(fundAcc, conn);
				// 同时删除关联表
				serviceDao.deletePortFundRela(fundAcc.getId(), cacheFundAcc.getC_PORT_CODE(), conn);
				// 删除账户表关联数据
				serviceDao.deleteTypeRelaInfo(fundAcc.getId(), conn);
			}
		} else {
			String portCode = fundAcc.getC_PORT_CODE();
			if (cacheFundAcc != null) {
				fundAcc.setId(cacheFundAcc.getId());
				serviceDao.updateById(fundAcc, conn);
				// 更新关联表
				serviceDao.updatePortFundRela(fundAcc.getId(), portCode, 
						cacheFundAcc.getC_PORT_CODE(), cacheFundAcc.getC_ACCOUNT_TYPE(), conn);
				if (!StringUtil.IsNullOrEmptyT(cacheFundAcc.getC_ACCOUNT_TYPE()) && "T_P_BI_FUND_ACC".equals(fundTableName)){
					serviceDao.saveFundTypeRela(fundAcc.getId(),cacheFundAcc.getC_ACCOUNT_TYPE(),conn);
				}
			} else {
				String cIden = serviceDao.insert(fundAcc, conn);
				// 更新关联表
				serviceDao.insertPortFundRela(cIden, portCode, conn);
				serviceDao.updateFundAccRela(cIden, conn);
				if (!StringUtil.IsNullOrEmptyT(fundAcc.getC_ACCOUNT_TYPE()) && "T_P_BI_FUND_ACC".equals(fundTableName)){
					serviceDao.saveFundTypeRela(cIden,fundAcc.getC_ACCOUNT_TYPE(),conn);
				}
			}
			fundAcc.setC_PORT_CODE(portCode);
		}
	}
	
	/**
	 * 处理pojo之间的字段映射
	 * @param fundAcc
	 * @param syncBankAcc
	 */
	private void syncFundAcc(FundAcc fundAcc, SyncBankAcc syncBankAcc) {
		// 开户账号
		fundAcc.setC_OPEN_ACC_NO(syncBankAcc.getC_OPEN_ACC_NO());
		// 账户名称
		fundAcc.setC_OPEN_ACC_NAME(syncBankAcc.getC_OPEN_ACC_NAME());
		// 大额支付号
		fundAcc.setC_PAY_CODE(syncBankAcc.getC_PAY_NUM());
		// 资产代码
		fundAcc.setC_PORT_CODE(syncBankAcc.getC_PORT_CODE());
		// 账户类型
		fundAcc.setC_ACCOUNT_TYPE(syncBankAcc.getC_ACCOUNT_TYPE());
		// 开户行
		fundAcc.setC_OPEN_ADDR(StringUtil.IsNullOrEmptyT(syncBankAcc.getC_BANK_CODE()) ? " " : syncBankAcc.getC_BANK_CODE());
		// 开户日期
		fundAcc.setD_BEGIN(syncBankAcc.getD_BEGIN());
		// 销户日期
		fundAcc.setD_END(syncBankAcc.getD_END());
		// 币种 生命周期系统中没有给出 暂默认人民币"CNY"
		fundAcc.setC_DC_CODE("CNY");
		// 资产代码
		fundAcc.setC_ASS_CODE(syncBankAcc.getC_ASS_CODE());
		// 所有人
		fundAcc.setC_HOLDER(syncBankAcc.getC_ORG_CODE());
		// 现金账户代码
		fundAcc.setC_CA_CODE(syncBankAcc.getC_CA_CODE());
	}
}
