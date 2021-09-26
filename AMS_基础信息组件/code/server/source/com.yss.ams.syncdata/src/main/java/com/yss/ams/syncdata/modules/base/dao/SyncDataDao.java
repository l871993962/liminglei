package com.yss.ams.syncdata.modules.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.syncdata.support.modules.base.pojo.SyncModule;
import com.yss.ams.syncdata.support.modules.base.pojo.XMLSyncData;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;
import com.yss.platform.support.dataservice.service.IVocDataService;

/**
 * 数据同步 dao
 * @author chenyoucai
 */
public class SyncDataDao extends GeneralDao {
	private SyncDataSqlBuilder sqlBuilder;
	
	public SyncDataDao(DbPool pool, SQLBuilder sqlBuilder) throws YssException {
		super(pool, sqlBuilder);
		this.sqlBuilder = (SyncDataSqlBuilder)sqlBuilder;
	}

	/**
	 * 忽略消息:只有已接收状态下的消息才可忽略
	 * @param ids
	 * @return
	 */
	public String ignoreMessages(List<String> ids) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sql = "";
		try{
			conn = this.loadNewConnection();
			sql = sqlBuilder.ignoreMessagesSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ContextFactory.getContext().getUserCode());
			pstmt.setString(2, DateUtil.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
			pstmt.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(ids.toArray(new String[ids.size()]), conn));
			pstmt.executeUpdate();
		}catch(Exception ex){
			throw new BusinessException("数据同步忽略消息失败!" + ex.getMessage(), ex);
		}finally{
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
		return "success";
	}

	/**
	 * 数据同步成功，将消息状态改成已更新
	 * @param ids
	 * @return
	 */
	public void syncSuccess(List<String> ids) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sql = "";
		try{
			conn = this.loadNewConnection();
			sql = sqlBuilder.syncSuccessSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ContextFactory.getContext().getUserCode());
			pstmt.setString(2, DateUtil.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
			pstmt.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(ids.toArray(new String[ids.size()]), conn));
			pstmt.executeUpdate();
		}catch(Exception ex){
			throw new BusinessException("数据同步消息失败!" + ex.getMessage(), ex);
		}finally{
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}
	
	/**
	 * 根据业务系统code获取该业务系统监听的功能模块
	 * @param systemCode
	 * @return
	 */
	public List<String> getModuleCfg(String systemCode) {
		List<String> funcodes= new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Connection conn = null;
		String sql="";
		try{	
			conn = this.loadNewConnection();
			sql = sqlBuilder.getModuleCfgSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, systemCode);
			rSet = pstmt.executeQuery();
			while(rSet.next()){
				funcodes.add(rSet.getString("C_MODULE_CODE"));
			}
		} catch (Exception ex) {
			logger.log("数据同步-查询业务系统监听的功能模块失败!", ex);
		} finally{
			this.closeResultSetFinal(rSet);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return funcodes;
	}

	/**
	 * 保存数据同步模块设置
	 * @param syncModule
	 * @return
	 * @throws YssException 
	 * @throws Exception
	 */
	public String saveSyncModule(List<SyncModule> syncModules,XMLSyncData syncDataIp){
		PreparedStatement pstmtD = null;
		PreparedStatement pstmtI = null;
		Connection conn = null;
		String sqlD = "";
		String sqlI = "";
		try{
			conn = this.loadNewConnection();
			sqlD = sqlBuilder.deleteSyncModuleSql();
			pstmtD = conn.prepareStatement(sqlD);
			pstmtD.setString(1, syncDataIp.getSystemCode());
			pstmtD.executeUpdate();
			
			sqlI = sqlBuilder.saveSyncModuleSql();
			pstmtI = conn.prepareStatement(sqlI);
			//遍历同步模块
			for(SyncModule module : syncModules){
				if(module.getC_CONFIGURED().equals("TRUE")){
					pstmtI.setString(1, syncDataIp.getSystemCode());
					pstmtI.setString(2, module.getC_MODULE_CODE());
					pstmtI.setString(3, ContextFactory.getContext().getUserCode());
					pstmtI.setString(4, DateUtil.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					pstmtI.executeUpdate();
				}
			}
		
		}catch(Exception ex){
			throw new BusinessException("保存保存数据同步模块设置失败!" + ex.getMessage(), ex);
		}finally{
			this.closeStatementFinal(pstmtD);
			this.closeStatementFinal(pstmtI);
			this.releaseConnection(conn);
		}
		
		return "success";
	}
	
	/**
	 * 获取同步监听模块配置信息
	 * @param syncDataIpAndBusiCode
	 * @return
	 */
	public List<SyncModule> queryAllFuncodeCfg(String systemCode) {
		List<SyncModule> syncModules= new ArrayList<SyncModule>();
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		Connection conn = null;
		SyncModule syncModule = null;
		String sql="";
		try{	
			IVocDataService vocDataService = YssServiceFactory.getInstance().createService(IVocDataService.class);
			HashMap<String, String> vocMap = vocDataService.getShortDataMap("SYNCFUNCODE");
			
			List<String> moduleList = new ArrayList<String>();
			conn = this.loadNewConnection();
			sql = sqlBuilder.getModuleCfgSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, systemCode);
			rSet = pstmt.executeQuery();
			while(rSet.next()){
				moduleList.add(rSet.getString("C_MODULE_CODE"));
			}
			
			for (Map.Entry<String, String> entry : vocMap.entrySet()) {
				syncModule = new SyncModule();
				syncModule.setC_MODULE_CODE(entry.getKey());
				syncModule.setC_MODULE_NAME(entry.getValue());
				if (moduleList.contains(entry.getKey())) {
					syncModule.setC_CONFIGURED("TRUE");
				}
				syncModules.add(syncModule);
			}
		} catch (Exception ex) {
			logger.log("数据同步-查询业务系统监听的功能模块失败!", ex);
		} finally{
			this.closeResultSetFinal(rSet);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return syncModules;
	}
}
