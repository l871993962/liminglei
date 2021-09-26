package com.yss.ams.sec.information.modules.mp.secTransfer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//import com.yss.algo.core.JythonFactory;
//import com.yss.algo.core.JythonTypeProcess;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.support.modules.mp.secTransfer.pojo.SecTransfer;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.mp.secTransfer.pojo.TdChan;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * @ClassName SecTransferDao
 * @Description 描述类的作用
 * @author guohui
 * @CreateDate 2016年10月24日下午2:38:12
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class SecTransferDao extends GeneralDao {

	/**
	 * 考虑到大批量数据同时处理的情况,缓存应关闭
	 */
	private static boolean enableCache = true;
	
	/**
	 * 证券转换字典, <转换代码:<原证券代码:目标证券代码>> zhanghualin 2016-11-23
	 * STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出
	 */
	private static HashMap<String, HashMap<String, String>> secTranMap = 
			new HashMap<String, HashMap<String, String>>();
	
	private SectransferSqlBuilder sqlBuilder = null;
	
	static {
		SecTransferDao.reloadSecTranMap();
	}

	public SecTransferDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (SectransferSqlBuilder) sqlbuilder;
	}
	
	public SectransferSqlBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	public void setSqlBuilder(SectransferSqlBuilder sqlBuilder) {
		this.sqlBuilder = sqlBuilder;
	}
	
	/**
	 * 根据转换代码获取证券代码转换表,查询无结果返回 new HashMap<String, String>()
	 * @Title getSecTranMapByTranCode 
	 * @Description STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出 生成确认单
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月25日下午3:46:36
	 * @param tranCode 转换代码
	 * @return
	 */
	public static HashMap<String, String> getSecTranMapByTranCode(String tranCode) {
		if (tranCode == null || !secTranMap.containsKey(tranCode))
			return new HashMap<String, String>();
		else
			return secTranMap.get(tranCode);
	}
	
	/**
	 * 
	 * @Title isEnableCache 
	 * @Description 获取缓存状态,true代表缓存可用
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月24日下午3:12:13
	 * @return
	 */
	public static boolean isEnableCache() {
		return enableCache;
	}

	public static void setEnableCache(boolean enableCache) {
		if (SecTransferDao.enableCache == enableCache)
			return;
		SecTransferDao.enableCache = enableCache;
		if (enableCache) {
			try {
				SecTransferDao.reloadSecTranMap();
			} catch (Exception e) {
				SecTransferDao.setEnableCache(false);
			}
		}
		else {
			synchronized (secTranMap) {
				secTranMap.clear();
			}
		}
	}
	
	/**
	 * 根据转换规则类型，获取转换代码键值对
	 * @Title getSecTranMapByPort 
	 * @Description STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
	 * @author guohui@ysstech.com
	 * @date 2017年02月17日下午5:16:48
	 * @return
	 */
	public HashMap<String, String> getTdChanPlMap(ArrayList<TdChan> listTdChan, String zhgz) throws Exception{
		HashMap<String, String> algoResultMap = new HashMap<String, String>(); //渠道代码、披露内码键值对
		try{		
			algoResultMap = secTranMap.get(zhgz);
		}catch(Exception e){
		}
		return algoResultMap;
	}
	
	/**
	 * 静态方法,清空并重新加载证券转换字典
	 * @Title reloadSecTranMap 
	 * @Description STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出 生成确认单
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月25日下午2:25:49
	 */
	public static void reloadSecTranMap() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SecTransfer> secTrans = new ArrayList<SecTransfer>();
		try {
			SectransferSqlBuilder sqlBuilder = new SectransferSqlBuilder();
			SecTransferDao serviceDao = new SecTransferDao(YssDbPoolFactory.getInstance()
					.getDbPool(SecInfoActivator.class), sqlBuilder);
			String selectSql = sqlBuilder.buildSelectSql(null) + " where N_CHECK_STATE = 1 ";
			conn = serviceDao.loadNewConnection();	
			pst = conn.prepareStatement(selectSql);
			rs = pst.executeQuery();
			secTrans = (new ResultSetTools(serviceDao.dbNameResolver, sqlBuilder))
					.getBeanList(SecTransfer.class, rs);
		}
		catch (Exception ex) {
			return;
		}
		finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);			
		}
		synchronized (secTranMap) {
			secTranMap.clear();
			//SecTransferDao serviceDao = new SecTransferDao(YssDbPoolFactory.getInstance()
			//		.getDbPool(UcoActivator.class), new SectransferSqlBuilder());
			//HashMap<String, Object> paraMap = new HashMap<String, Object>();
			//paraMap.put("N_CHECK_STATE", "SearchAudit");
			//List<BasePojo> basePojos = serviceDao.queryByCondition(
			//		paraMap, SecTransfer.class);
			//for (BasePojo basePojo : basePojos) {
			//	SecTransfer secTran = (SecTransfer)basePojo;
			for (SecTransfer secTran : secTrans) {
				if (!secTranMap.containsKey(secTran.getC_TRANSFER_CODE())) {
					secTranMap.put(secTran.getC_TRANSFER_CODE(), new HashMap<String, String>());
				}
				secTranMap.get(secTran.getC_TRANSFER_CODE())
					.put(secTran.getC_SEC_CODE(), secTran.getC_PUB_CODE());	
			}
		}
	}
	
	public void deleteBySecodes(String[] SecCodes){
		PreparedStatement pst = null;
		PreparedStatement selectPst = null;
		Connection conn = null;
		ResultSet rs = null;
		List<SecTransfer> secTrans = new ArrayList<SecTransfer>();
		String sql = sqlBuilder.buildDeleteBySecodes();
		String selectSql = sqlBuilder.buildSelectSql(null) + " where N_CHECK_STATE = 1 and C_SEC_CODE in (SELECT * FROM TABLE(?)) ";
		try{
			conn = loadNewConnection();	
			com.yss.framework.db.OraDbTool dbTool = com.yss.framework.db.OraDbTool.newInstance();
			// 取出待删除数据,更新
			selectPst = conn.prepareStatement(selectSql);
			selectPst.setArray(1, dbTool.sqlOverLongCondition(SecCodes,conn));
			rs = selectPst.executeQuery();
			secTrans = (new ResultSetTools(dbNameResolver, sqlBuilder))
					.getBeanList(SecTransfer.class, rs);
			// 删除操作
			pst = conn.prepareStatement(sql);
			pst.setArray(1, dbTool.sqlOverLongCondition(SecCodes,conn));
			pst.executeUpdate();
		}catch(Exception e){
			return;
		}finally{
			DbFun.closeResultSetFinal(rs);
			closeStatementFinal(pst, selectPst);
			releaseConnection(conn);
		}
		synchronized (secTranMap) {
			updateSecTranMap(secTrans, "deleteById");
		}
	}
	
	public List<SecTransfer> queryBysgorzhsg(){
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		List<SecTransfer> secTran = new ArrayList<SecTransfer>();
		String sql=" select  c_sec_code from T_D_MP_SEC_TRANSFER where C_data_idf like '%H%' ";
		try {
			conn = loadNewConnection();	
			pst = conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				SecTransfer sect=	new SecTransfer();
				sect.setC_SEC_CODE(rs.getString("c_sec_code"));
				secTran.add(sect);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		} finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		
		return secTran;
	}
	/**
	 * 通过交易渠道删除转换代码
	 * STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
	 * @author guohui@ysstech.com
	 * @date 2017年02月07日下午1:17:34
	 * @param seatCodes
	 * @return String
	 */
	public void deleteBySeatCodes(String[] seatCodes){
		PreparedStatement pst = null;
		PreparedStatement selectPst = null;
		Connection conn = null;
		ResultSet rs = null;
		List<SecTransfer> secTrans = new ArrayList<SecTransfer>();
		String sql = " delete from T_D_MP_SEC_TRANSFER where C_TYPE = 'R' and c_data_idf = 'Z' and C_SEC_CODE in (SELECT * FROM TABLE(?)) ";
		String selectSql = "select * from T_D_MP_SEC_TRANSFER where C_TYPE = 'R' and C_DATA_IDF = 'Z' and N_CHECK_STATE = 1 and C_SEC_CODE in (SELECT * FROM TABLE(?)) ";
		try{
			conn = loadNewConnection();	
			com.yss.framework.db.OraDbTool dbTool = com.yss.framework.db.OraDbTool.newInstance();
			// 取出待删除数据,更新
			selectPst = conn.prepareStatement(selectSql);
			selectPst.setArray(1, dbTool.sqlOverLongCondition(seatCodes,conn));
			rs = selectPst.executeQuery();
			secTrans = (new ResultSetTools(dbNameResolver, sqlBuilder))
					.getBeanList(SecTransfer.class, rs);
			// 删除操作
			pst = conn.prepareStatement(sql);
			pst.setArray(1, dbTool.sqlOverLongCondition(seatCodes,conn));
			pst.executeUpdate();
		}catch(Exception e){
			return;
		}finally{
			DbFun.closeResultSetFinal(rs);
			closeStatementFinal(pst, selectPst);
			releaseConnection(conn);
		}
		synchronized (secTranMap) {
			updateSecTranMap(secTrans, "deleteById");
		}
	}
	
	/**
	 * STORY32744【南方基金】【v2.5需求】南方基金：社保资产证券代码新规则 
	 * @Title transSeccodeToPlcode 
	 * @Description 将需要进行转换的证券内码根据规则进行转换后插入到证券规则转换表里
	 * @author liuyanni@ysstech.com
	 * @date 2016年10月28日下午4:18:02
	 * @param gzCode 规则公式
	 * @param secCodes 证券内部数组
	 * @return void
	 */
	//chenbo 2017-06-26 #42948 资讯信息管理组件化拆分,暂不处理，故注释
//	public HashMap<String,String> transSeccodeToPlcode(String gzCode,HashMap<String,Object> para) throws Exception{
//		Connection conn=null;
//		try{		
//			JythonFactory algoFactory = JythonFactory.getInstance();
//			conn = loadNewConnection();	
//			Object algoResult = algoFactory.enhancedPerform(gzCode, para, conn);
//			return JythonTypeProcess.toMapString_String(algoResult);
//		}catch(Exception e){
//			e.printStackTrace();
//			throw e;
//		}finally{
//			releaseConnection(conn);
//		}
//	}
	
	/**
	 * 查询转换规则是否开启
	 * STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
	 * @author guohui@ysstech.com
	 * @date 2017年02月07日下午1:17:34
	 * @param zhgz 规则公式
	 * @param type 应用条件
	 * @return String
	 */
	public String ruleIsOpen(String zhgz,String type){
		String isTrue = "1";
		String sql = sqlBuilder.getTradeRule();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try{
			conn = loadNewConnection();	
			pst = conn.prepareStatement(sql);
			pst.setString(1, zhgz);
			pst.setString(2, type);
			rs = pst.executeQuery();
			if (!rs.next())
				isTrue = "0";
		}catch(Exception  e){
		}
		finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);			
		}
		return isTrue;
	}
	
	/**
	 * STORY35721【南方基金】【企业年金】【紧急】打印财务报表编制单位增加公司名称 
	 * @Title setSecTran 
	 * @Description 构造证券代码转换信息实体
	 * @author liuyanni@ysstech.com
	 * @date 2016年11月2日下午2:29:52
	 * @param secCode  证券内码
	 * @param gzCode   规则代码
	 * @param plCode   披露代码
	 * @param date     记录变更事件
	 * @param secs     
	 * @return void
	 */
	public void setSecTran(String secCode, String gzCode,String plCode,Date date,List<SecTransfer> secs){
		SecTransfer secTran =new SecTransfer();
		secTran.setC_SEC_CODE(secCode);
		secTran.setC_PUB_CODE(plCode);
		secTran.setC_DATA_IDF("Z");
		secTran.setC_TRANSFER_CODE(gzCode);
		secTran.setAuditState(!" ".equals(plCode) ? 1 : 0);
		secTran.setModifier(ContextFactory.getContext().getUserCode());
		secTran.setModifyDate(YssFun.formatDate(date, "yyyy-MM-dd hh:mm:ss"));
		//STORY38131 区别交易渠道而加入该字段，证券为P,渠道为R by guohui 20170208
		secTran.setC_TYPE("P");
		if (!" ".equals(plCode)) {
			secTran.setOperator(ContextFactory.getContext().getUserCode());
			secTran.setAuditDate(YssFun.formatDate(date, "yyyy-MM-dd hh:mm:ss"));
		}
		secs.add(secTran);
	}
	
	/**
	 * 根据组合代码和查询日期及参数代码,获取对应的组合自定义参数
	 * @Title getParamValue 
	 * @Description STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月25日下午4:35:01
	 * @param portCode 组合代码
	 * @param dateStr 日期
	 * @param dspCode 参数代码
	 * @return
	 */
	public String getParamValue(String portCode, String dateStr, String dspCode) {
		String sql = sqlBuilder.getQueryParamSql();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			conn = loadNewConnection();	
			pst = conn.prepareStatement(sql);
			pst.setString(1, dspCode);
			pst.setString(2, dspCode);
			pst.setString(3, portCode);
			pst.setDate(4, YssFun.toSqlDate(dateStr));
			rs = pst.executeQuery();
			if (rs.next())
				return rs.getString(1);
			else
				return null;
		}
		catch (Exception ex) {
			return null;
		}
		finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);			
		}
	}

	/**
	 * 根据组合代码及日期,后台查询参数“转换规则”,获取对应的证券代码转换表
	 * @Title getSecTranMapByPort 
	 * @Description STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月24日下午5:16:48
	 * @param paramList 参数列表
	 * @return
	 */
	public HashMap<String, String> getSecTranMapByCondition(
			HashMap<String, String> paramList) {
		if (!enableCache || !paramList.containsKey("C_RELA_TYPE") || 
				!paramList.containsKey("D_BEGIN"))
			return new HashMap<String, String>();
		String portCode = paramList.get("C_RELA_TYPE");
		String dateStr = paramList.get("D_BEGIN");		
		String zqzhCode = getParamValue(portCode, dateStr, "SV_BB_ZQDMZH");
		if (!"TRAN_BZH".equals(zqzhCode))
			return getSecTranMapByTranCode(zqzhCode);
		else
			return new HashMap<String, String>();
	}
	
	/**
	 * 根据操作类型及结果,对缓存进行同步更新
	 * @Title updateSecTranMap 
	 * @Description STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出
	 * @author zhanghualin@ysstech.com
	 * @date 2016年11月24日下午1:55:41
	 * @param pojoList 操作对象
	 * @param info 操作类型
	 */
	public <T extends BaseBean> void updateSecTranMap(List<T> pojoList, String info) {
		if (!enableCache)
			return;
		// 更新操作
		if ("deleteById".equals(info) || "unAudit".equals(info)) {
			for (BaseBean basePojo : pojoList) {
				SecTransfer secTran = (SecTransfer)basePojo;
				secTranMap.get(secTran.getC_TRANSFER_CODE()).remove(secTran.getC_SEC_CODE());
			}
		}
		else if ("audit".equals(info)) {
			for (BaseBean basePojo : pojoList) {
				SecTransfer secTran = (SecTransfer)basePojo;
				if (!secTranMap.containsKey(secTran.getC_TRANSFER_CODE())) {
					secTranMap.put(secTran.getC_TRANSFER_CODE(), new HashMap<String, String>());
				}
				secTranMap.get(secTran.getC_TRANSFER_CODE())
					.put(secTran.getC_SEC_CODE(), secTran.getC_PUB_CODE());
			}
		}
		else if ("insert".equals(info) || "update".equals(info)) {
			for (BaseBean basePojo : pojoList) {
				SecTransfer secTran = (SecTransfer)basePojo;
				if (secTran.getAuditState() == YssConstant.STATE_AUDIT) {
					if (!secTranMap.containsKey(secTran.getC_TRANSFER_CODE())) {
						secTranMap.put(secTran.getC_TRANSFER_CODE(), new HashMap<String, String>());
					}
					secTranMap.get(secTran.getC_TRANSFER_CODE())
						.put(secTran.getC_SEC_CODE(), secTran.getC_PUB_CODE());
				}
			}			
		}
	}
	
	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList) {
		super.deleteById(pojoList);
		if (enableCache) {
			synchronized (secTranMap) {
				updateSecTranMap(pojoList, "deleteById");
			}
		}
	}

	@Override
	public <T extends BaseBean> List<String> insert(List<T> list) {
		List<String> ret = super.insert(list);
		if (enableCache) {
			synchronized (secTranMap) {
				updateSecTranMap(list, "insert");
			}
		}
		return ret;
	}

	@Override
	public <T extends BasePojo> void updateById(List<T> pojoList) {
		super.updateById(pojoList);
		if (enableCache) {
			synchronized (secTranMap) {
				updateSecTranMap(pojoList, "update");
			}
		}
	}

	@Override
	public void auditById(List<BasePojo> pojoList, int auditState) {
		super.auditById(pojoList, auditState);
		String info = null;
		if (auditState == YssConstant.STATE_AUDIT)
			info = "audit";
		else if (auditState == YssConstant.STATE_UNAUDIT)
			info = "unAudit";
		
		if (enableCache) {
			synchronized (secTranMap) {
				updateSecTranMap(pojoList, info);
			}
		}		
	}
	
	/**
	 * 根据证券内码查询披露代码
	 * @param secList
	 * @param zhgz
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, String> getPlcode(ArrayList<SecBase> secList,
			String zhgz) throws Exception {
		HashMap<String, String> ResultMap = new HashMap<String, String>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			String[]secCodes = new String[secList.size()];
			for(int i = 0; i < secCodes.length; i++){
				SecBase secBase = secList.get(i);
				secCodes[i] = secBase.getC_SEC_CODE();
			}
			conn = loadNewConnection();	
			String sql = "select r.c_sec_code,r.c_pub_code from T_D_MP_SEC_TRANSFER r where r.c_transfer_code = ? and r.c_sec_code in(select * from table(?))";

			pst = conn.prepareStatement(sql);
			pst.setString(1, zhgz);
			pst.setArray(2, com.yss.framework.db.OraDbTool.newInstance().sqlOverLongCondition(secCodes, conn));
			rs = pst.executeQuery();
			while (rs.next()){
				ResultMap.put(rs.getString(1), rs.getString(2));
			}
		}
		catch (Exception ex) {
			throw ex;
		}
		finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);			
		}
		return ResultMap;
	}
}
