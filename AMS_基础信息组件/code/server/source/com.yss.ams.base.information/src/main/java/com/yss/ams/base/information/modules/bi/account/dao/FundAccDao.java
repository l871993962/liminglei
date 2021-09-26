package com.yss.ams.base.information.modules.bi.account.dao;

import java.beans.PropertyDescriptor;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.yss.ams.base.information.support.bi.account.pojo.CashAcc;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.pojo.PortRela;
import com.yss.ams.base.information.util.cache.CacheUtil;
import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidDataException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.enclosure.pojo.DataEnclosure;
import com.yss.platform.support.enclosure.service.IDataEnclosureService;

/**
 * @ClassName FundAccUnifypayDao
 * @Description 产品账户信息
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public class FundAccDao extends GeneralDao {

	private FundAccSqlBuilder builder = null;

	public FundAccDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		builder = (FundAccSqlBuilder) sqlBuilder;
	}
	
	private String replaceNullColumn(String fieldNames){
		fieldNames = fieldNames.toString().replaceAll("C_PORT_CODE","'' as C_PORT_CODE");
		return fieldNames;
	}
	
	/**
	 * 设置虚拟字段值为null，使其在更新时调过该字段
	 * @param pojoList
	 */
	private void setNullAttr(List<FundAcc> pojoList){
		for(FundAcc pojo : pojoList){
			setnullAttr(pojo);
		}
	}
	
	private void setnullAttr(FundAcc pojo){
		pojo.setC_PORT_CODE(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasePojo> void updateById(List<T> pojoList)
			throws DataAccessException {
		/*STORY #41401 产品信息-产品账户设置，批量关联账户 ,保存时，同时将关联关系保存到 T_P_AB_PORT_RELA
		 * 将前台的代码逻辑移至后台处理*/
		
		for(FundAcc fa : (List<FundAcc>)pojoList){
			if(!StringUtil.IsNullOrEmptyT(fa.getC_ACCOUNT_TYPE())) {
				this.deleteTypeRelaInfo(fa);
				this.saveFundTypeRela(fa.getC_PORT_CODE(), fa.getId(), fa.getC_ACCOUNT_TYPE());
			}else{
				this.deleteTypeRelaInfo(fa);
			}
		}
		
		setNullAttr((List<FundAcc>)pojoList);
		super.updateById(pojoList);
	}
	
	/**
	 * 删除账户类型关联数据
	 * @param fa
	 */
	public void deleteTypeRelaInfo (FundAcc fa){
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql.append("delete t_cp_fundtype_rela where c_rela_code = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, fa.getId());
			pstmt.execute();
			conn.commit();
		} catch(Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	
	/**
	 * 插入账户类型关联数据
	 * @param portCode
	 * @param cIden
	 * @param accountTypes
	 */
	public void saveFundTypeRela(String portCode,String cIden,String accountTypes){
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		String[] accounts = accountTypes.split("\\|");
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql.append("insert into t_cp_fundtype_rela (C_IDEN,C_PORT_CODE,C_RELA_CODE,C_ACCOUNT_TYPE,C_UPDATE_TIME,C_UPDATE_BY) ");
			sql.append(" values (SEQU_cp_fundtype_rela.nextval,?,?,?,to_char(sysdate,'yyyymmdd HH24:mi:ss'),?)");
			pstmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < accounts.length; i++) {
				pstmt.setString(1, portCode);
				pstmt.setString(2, cIden);
				pstmt.setString(3, accounts[i]);
				pstmt.setString(4, "".equals(ContextFactory.getContext().getUserCode()) ? " ":ContextFactory.getContext().getUserCode());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (Exception ex) {
			throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	
	@Override
	public <T extends BasePojo> void updateById(T basePojo, Connection conn)
			throws DataAccessException {
		setnullAttr((FundAcc) basePojo);
		super.updateById(basePojo, conn);
	}
	
	@Override
	public <T extends BasePojo> void updateById(T basePojo)
			throws DataAccessException {
		setnullAttr((FundAcc) basePojo);
		super.updateById(basePojo);
	}
	
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> queryByIds(String ids, Class<?> clazz) {
		List<K> dataList = new ArrayList<K>();
		K pojo = null;
		PreparedStatement ptmt = null;
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		try {
			pojo = (K) clazz.newInstance();
			StringBuffer fieldNames = new StringBuffer();

			PropertyDescriptor[] proDescriptors = this
					.getPropertyDescriptors(pojo);

			conn = loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			for (PropertyDescriptor prop : proDescriptors) {
				// 去掉getClass方法
				if (prop.getPropertyType().isAssignableFrom(Class.class)) {
					continue;
				}

				if ("c_USER_PWD".equals(prop.getName())) {
					continue;
				}

				this.buildFieldByCommaQuery(fieldNames, prop, pojo);
			}

			// 构建查询语句
			StringBuffer sqlBuffer = new StringBuffer();

			if (fieldNames.length() > 0) {
				StringUtil.delLastSplitMark(fieldNames, ",");
			}

			if (fieldNames.length() > 0) {
				sqlBuffer.append(" SELECT ");
				String s = replaceNullColumn(fieldNames.toString());
				sqlBuffer.append(s);
				sqlBuffer.append(" FROM ");
				sqlBuffer.append(this.sqlbuilder
						.getTableName(this.dbNameResolver));
				sqlBuffer.append(" WHERE ");
				sqlBuffer.append(" C_IDEN IN (SELECT * FROM TABLE(?)) ");
				sql = sqlBuffer.toString();
			} else {
				throw new InvalidDataException(pojo.getClass().toString()
						+ "的实例没有属性值");
			}
			ptmt = openPreparedStatement(sql, conn);
			ptmt.setArray(
					1,
					OraDbTool.newInstance().sqlOverLongCondition(
							String.valueOf(ids), conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				pojo = (K) rsTools.ResultToBean(rs, clazz);
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			logger.error("查询失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	
	@Override
	protected List<String> getParaName(HashMap<String, Object> paraMap) {
		// BUG #170286 点击新增‘支付自动划款指令’，点击本方账号‘账户名称’下拉框选择按钮，未加载出已有的数据信息 
		List<String> paraNameList = new ArrayList<String>();
		Iterator<Entry<String, Object>> it = paraMap.entrySet().iterator();
		Entry<String, Object> paraEntry;
		String entryKey;
		String checkStateValue = "";

		while (it.hasNext()) {
			paraEntry = it.next();
			entryKey = paraEntry.getKey();

			if ("dataClass".equals(entryKey)) {
				continue;
			}else if ("N_CHECK_STATE".equals(entryKey)) {
				checkStateValue = String.valueOf(paraEntry.getValue());
				continue;
			}else if ("ISLIST".equals(entryKey)) {
				// ISLIST不是需要赋值的参数，所以从列表中移除
				continue;
			}

			paraNameList.add(entryKey);
		}

		if (!"".equals(checkStateValue)) {
			paraNameList.add(checkStateValue);
		}
		if(paraMap.containsKey("ISLIST")){
			// ISLIST不是需要赋值的参数，移除
			paraMap.remove("ISLIST");
		}
		return paraNameList;
	}
	
	/**
	 * BUG #252441 【易方达基金】【运维】产品账户信息维护后清算进来的资金换汇业务没有获取到资金账户
	 * add by liangchong
	 */
	@Override
	public void auditById(List<BasePojo> pojoList, int auditState)
			throws DataAccessException {
		super.auditById(pojoList, auditState);
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer buf = new StringBuffer();
		boolean bTrans = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			
			//根据c_rela_code 更新核算 银行账户信息表中的 审核状态，时间，审核人。
			buf.append(" update t_c_cp_fund_acc t set N_CHECK_STATE = ?,  ");
			buf.append("  C_CHECK_BY = ? ,C_CHECK_TIME = ? where t.c_rela_code = ? ");
			
			pstmt = conn.prepareStatement(buf.toString());
			AuditableParamPojo auditPojo;
			
			for (BasePojo pojo : pojoList) {
				auditPojo = (AuditableParamPojo) pojo;
				auditPojo.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));				
				pstmt.setInt(1, auditState);
				pstmt.setString(2, auditPojo.getOperator());
				pstmt.setString(3, auditPojo.getAuditDate());
				pstmt.setString(4, auditPojo.getId());

				pstmt.executeUpdate();
			}
			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		}catch(Exception ex) {
			this.endTransFinal(conn, true);
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);	
		}
	}

	public List<FundAcc> getAllDataList() {
		List<FundAcc> pojoList = new ArrayList<FundAcc>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);

			conn = this.loadNewConnection();

			sql = builder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(FundAcc.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class,props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	
	public List<FundAcc> getAllDataByPort(String portCode){
		List<FundAcc> pojoList = new ArrayList<FundAcc>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);

			conn = this.loadNewConnection();
			sql = builder.getAllDataByPortSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(FundAcc.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class,props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}

	public FundAcc getDataByCode(String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);

			conn = this.loadNewConnection();

			sql = builder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return t;
	}

	public List<FundAcc> getDataListByTypes(String[] types) {
		List<FundAcc> pojoList = new ArrayList<FundAcc>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql = builder.getDataListByTypes();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}

	public List<FundAcc> getDataListByOrg(String orgCode) {
		List<FundAcc> pojoList = new ArrayList<FundAcc>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql = builder.getDataListByOpenBank();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orgCode);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}

	public List<FundAcc> getDataListByPort(String portCode) {
		List<FundAcc> pojoList = new ArrayList<FundAcc>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql = builder.buildQueryAccTwoSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			////STORY #51553 一个组合存在多个托管账户，EXCLE导入时会出现多条指令，要求区分托管账户
			pstmt.setString(2, portCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	/**
	 * BUG #293262 30.7版本深证通账户余额和明细查询根据组合查询账户时与数据库交互太多
	 * @param portCodeList
	 * @return
	 */
	public HashMap<String ,List<FundAcc>> getDataListByPortlist(String[] portCodeList) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean bTrans = false;

		String sql = "";
		ResultSetTools rsTools = null;
		FundAcc t = null;
		HashMap<String ,List<FundAcc>> portAccMap = new HashMap<String ,List<FundAcc>>();
		
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			
			String tSql = "insert into R_D_UNIFY_PARAM (C_PORT_CODE) values(?)";
			pstmt = conn.prepareStatement(tSql);
			
			for(String portcode : portCodeList){
				pstmt.setString(1, portcode);
				//先将组合放进去
				portAccMap.put(portcode, new ArrayList<FundAcc>());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			closeStatementFinal(pstmt);
			
			sql = builder.buildQueryAccListSql();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				if(portAccMap.containsKey(t.getC_PORT_CODE())){
					portAccMap.get(t.getC_PORT_CODE()).add(t);
				}
			}
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			DbFun.endTransFinal(conn, bTrans);
			releaseConnection(conn);
		}

		return portAccMap;
	}
	
	public HashMap<String, String> getKeyConvertMap() {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {

			conn = this.loadNewConnection();

			sql = builder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String key = rs
						.getString(FundAccColumnName.c_OPEN_ACC_NO
								.toString());
				String val = rs
						.getString(FundAccColumnName.c_OPEN_ACC_NAME
								.toString());
				keyValueMap.put(key, val);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return keyValueMap;
	}

	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {
			String[] strArr = new String[listKey.size()];
			conn = this.loadNewConnection();

			sql = builder.getAllDataSqlByKeys();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(strArr, conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String key = rs
						.getString(FundAccColumnName.c_OPEN_ACC_NO
								.toString());
				String val = rs
						.getString(FundAccColumnName.c_OPEN_ACC_NAME
								.toString());
				keyValueMap.put(key, val);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return keyValueMap;
	}


	public List<FundAcc> getDataListByIds(String ids) {
		List<FundAcc> pojoList = new ArrayList<FundAcc>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql = builder.buildQueryAccByIdsSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(ids, conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}

	public List<FundAcc> getDataListByAssCode(String assCode) {
		List<FundAcc> pojoList = new ArrayList<FundAcc>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql = builder.buildQueryAccByAssCodeSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, assCode);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}

	public String updateFundAccUnifyPay(String accountNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		String result = "fail";
		try {
			conn = this.loadNewConnection();
			sql = builder.buildUpdateAccbyAccNoSql();
			pstmt = conn.prepareStatement(sql);
			//BUG #168183 【综合指令】中不是第一次划款的银行账户依然被标记为红色【优化】
			pstmt.setArray(
					1,
					OraDbTool.newInstance().sqlOverLongCondition(
							accountNo,conn));
			logger.debug(sql);
			pstmt.executeUpdate();
			result = "success";
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}

	public List<BasePojo> getDataListByAccType(String[] types) throws Exception {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);

			conn = this.loadNewConnection();

			sql = builder.getDataListByAccTypeSql();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			pstmt.setArray(2,OraDbTool.newInstance().sqlOverLongCondition(types, conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(FundAcc.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class,props);
				t.setC_DESC(rs.getString("C_PORT_NAME"));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	public List<BasePojo> getDataListByAccType2(String[] types) throws Exception {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		FundAcc t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);

			conn = this.loadNewConnection();

			sql = builder.getDataListByAccTypeSql2();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			pstmt.setArray(2,OraDbTool.newInstance().sqlOverLongCondition(types, conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(FundAcc.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class,props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}

	/**
	 * 查询是否存在指定账户类型的账户
	 * 
	 * @param type
	 * @return
	 */
	public String isAccOfAccTypeExit(String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String result = "False";
		try {
			conn = this.loadNewConnection();
			sql.append("select 1 from  T_P_BI_FUND_ACC TC LEFT JOIN T_P_AB_PORT_ACC_RELA TP ON TC.C_IDEN = TP.C_RELA_CODE ");
			sql.append(" left join t_Cp_fundtype_rela ft on tc.c_iden = ft.c_rela_code where ft.c_account_type = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, type);
			logger.debug(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = "Success";
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}

	/**
	 * 查询指定账户类型的账户
	 * 
	 * @param types
	 * @return
	 */
	public List<FundAcc> queryFundAccByType(String[] types, String accAddr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<FundAcc> pojoList = new ArrayList<FundAcc>();
		FundAcc t = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql.append("  SELECT a.* " );
			sql.append(" from  " );
			sql.append(   FundAccSqlBuilder.getT_P_BI_FUND_ACC() + " a  ");
			sql.append("	where EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  a.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE in (SELECT * FROM TABLE(?))  ) ");
			if (!"".equals(accAddr) && accAddr != null) {
				sql.append(" and C_OPEN_ADDR = ? ");
			}
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			if (!"".equals(accAddr) && accAddr != null) {
				pstmt.setString(2, accAddr);
			}
			logger.debug(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}

	
	/**
	 * 查询指定账户类型的账户
	 * 
	 * @param types
	 * @return
	 */
	public List<FundAcc> queryFundAccByType2(String[] types) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		List<FundAcc> pojoList = new ArrayList<FundAcc>();
		FundAcc t = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql.append("  SELECT a.* " );
			sql.append(   FundAccSqlBuilder.getT_P_BI_FUND_ACC() + " a  ");
			sql.append("  where EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  a.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE IN (SELECT * FROM TABLE(?)) )	 ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			
			logger.debug(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	/**
	 * STORY #35492 南方基金-产品账户信息中大额支付号与关联机构联动
	 * 
	 * @param cPAYCODE
	 * @param cBANKCODE
	 * @param id
	 * @return
	 */
	public String updateOrgInfo(String cPAYCODE, String cBANKCODE, String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = "Fail";
		try {
			conn = this.loadNewConnection();
			String sql = "update T_P_BI_ORG set C_PAY_CODE=?, C_BANK_CODE=? where c_iden = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cPAYCODE);
			pstmt.setString(2, cBANKCODE);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			conn.commit();
			this.closeStatementFinal(pstmt);
			result = "Success";
		} catch (Exception ex) {
			logger.info("修改机构信息错误", ex);
			throw new DataAccessException("修改机构信息错误", ex);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * STORY #42242 歌斐支付平台-能在同一个界面查询所有账户的余额以及发生额 
	 * @param AccNo
	 * @return result
	 */
	public FundAcc getFundAccByAccNo(String accNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		FundAcc result = new FundAcc();
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql.append(" select * from " + FundAccSqlBuilder.getT_P_BI_FUND_ACC()  );
			sql.append(" where c_open_acc_no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 根据账户的开户行，户名，账号，币种查询账户信息（必须全匹配）
	 * @param AccNo
	 * @return result
	 */
	public FundAcc getFundAccByAcc(FundAcc fundAcc){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		FundAcc result = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql.append("  SELECT * from ");
			sql.append(   FundAccSqlBuilder.getT_P_BI_FUND_ACC() + " a  ");
			sql.append(" where  a.C_OPEN_ACC_NO = ? ");
			sql.append("   and  a.C_OPEN_ACC_NAME = ? ");
			sql.append("   and  a.C_OPEN_ADDR = ? ");
			sql.append("   and a.C_DC_CODE = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, fundAcc.getC_OPEN_ACC_NO());
			pstmt.setString(2, fundAcc.getC_OPEN_ACC_NAME());
			pstmt.setString(3, fundAcc.getC_OPEN_ADDR());
			pstmt.setString(4, fundAcc.getC_DC_CODE());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
	/**
	 * STORY #42242 歌斐支付平台-能在同一个界面查询所有账户的余额以及发生额 
	 * @param id
	 * @return result
	 */
	public FundAcc getFundAccById(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		FundAcc result = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql.append("select * from " + FundAccSqlBuilder.getT_P_BI_FUND_ACC() + " where c_iden = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * STORY #41401 产品信息-产品账户设置，批量关联账户
	 * 根据账户信息获取id
	 * @param pojo
	 * @return
	 */
	public String getIdAfterSave(FundAcc pojo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String id = "";
		try {
			conn = this.loadNewConnection();
			sql.append("select * from (");
			sql.append(" select a.c_iden from T_P_BI_FUND_ACC a  where   ");
			sql.append(" a.c_dc_code = ? and a.c_open_addr = ? ");
			sql.append(" and a.c_open_acc_no = ? and a.c_open_acc_name = ? ");
			sql.append(" and a.c_update_by = ? ");
			sql.append(" and a.D_BEGIN = ? and a.D_END = ? and EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  a.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE = ? )	 ");
			sql.append(" order by a.c_update_time desc ) where rownum = 1");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, pojo.getC_DC_CODE());
			pstmt.setString(2, pojo.getC_OPEN_ADDR());
			pstmt.setString(3, pojo.getC_OPEN_ACC_NO());
			pstmt.setString(4, pojo.getC_OPEN_ACC_NAME());
			pstmt.setString(5, pojo.getModifier());
			Date d_begin = new Date((pojo.getD_BEGIN()).getTime());
			pstmt.setDate(6, d_begin);
			Date d_end = new Date((pojo.getD_END()).getTime());
			pstmt.setDate(7, d_end);
			pstmt.setString(8, pojo.getC_ACCOUNT_TYPE());
			logger.debug(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getNString(1);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return id;
	}
	
	/**
	 * STORY #41401 产品信息-产品账户设置，批量关联账户
	 * 根据关联code 和 组合 删除关联关系
	 * @param relaCode
	 * @param port
	 * @return
	 */
	public Boolean deletePortFundRela(String relaCode,String port) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		Boolean result = false;
		try {
			conn = this.loadNewConnection();
			sql.append(" delete from T_P_AB_PORT_ACC_RELA where c_rela_code in (select * from table(?)) and c_port_code = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(String
					.valueOf(relaCode),conn));
			pstmt.setString(2, port);
			int falg = pstmt.executeUpdate();
			if(falg>0){
				result = true;
			}
		} catch(Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * STORY #41401 产品信息-产品账户设置，批量关联账户
	 * 根据关联code 和 组合 删除关联关系
	 * @param relaCode
	 * @param port
	 * @return
	 */
	public Boolean deletePortsFundRela(String relaCode,String port) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		Boolean result = false;
		try {
			conn = this.loadNewConnection();
			if (StringUtil.IsNullOrEmpty(relaCode)){
				sql.append(" delete from T_P_AB_PORT_ACC_RELA where c_port_code in (select * from table(?))");
				//删除产品账户信息时，同步删除T_C_CP_FUND_ACC 。根据组合代码 删除组合关联的账户信息
				sql2.append(" delete from t_c_cp_fund_acc where c_port_code in (select * from table(?))");
			}
			else
			{
				sql.append(" delete from T_P_AB_PORT_ACC_RELA where c_rela_code =? and c_port_code in (select * from table(?))");
				//删除产品账户信息时，同步删除T_C_CP_FUND_ACC 。根据组合代码,关联ID  删除组合关联的账户信息
				sql2.append(" delete from t_c_cp_fund_acc where c_rela_code =? and c_port_code in (select * from table(?))  ");		
			} 
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt2 = conn.prepareStatement(sql2.toString());
			if (StringUtil.IsNullOrEmpty(relaCode)) {
				pstmt.setArray(1,  OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(port),conn));
				pstmt2.setArray(1,  OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(port),conn));
			}
			else {
				pstmt.setString(1, relaCode);
				pstmt.setArray(2,  OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(port),conn));
				pstmt2.setString(1, relaCode);
				pstmt2.setArray(2,  OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(port),conn));
			}
			
			int falg = pstmt.executeUpdate();
			pstmt2.executeUpdate();
			if(falg>0){
				result = true;
			}
			//updateFundAccPort(relaCode,port);
		} catch(Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt,pstmt2);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(sql);
			StringUtil.clearStringBuffer(sql2);
		}
		return result;
	}
	
	
	/**
	 * STORY #41401 产品信息-产品账户设置，批量关联账户
	 * 根据关联code 获取所有组合
	 * @param id
	 * @return
	 */
	public String getPortsByRelaCode(String id, String portCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String ports = "";
		try {
			conn = this.loadNewConnection();
			sql.append("select c_port_code from T_P_AB_PORT_ACC_RELA where c_rela_code = ?  ");
			if(!StringUtil.IsNullOrEmptyT(portCode)){
				sql.append(" and c_port_code = ?");
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			if(!StringUtil.IsNullOrEmptyT(portCode)){
				pstmt.setString(2, portCode);
			}
				
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if(rs.getNString(1) == null){
					break;
				}
				
				ports = ports + rs.getNString(1)+",";
			}
			
			if (ports.endsWith(",")) {
				ports = ports.substring(0, ports.length() - 1);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return ports;
	}
	
	/**
	 * STORY #41401 产品信息-产品账户设置，批量关联账户
	 * 支付产品账户删除时，删掉关联关系数据
	 * @return
	 */
	public Boolean deleteUnusePortRela(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		boolean result = false;
		try {
			conn = this.loadNewConnection();
			sql.append(" delete from T_P_AB_PORT_RELA a where a.c_rela_code not in (select c_iden from T_P_BI_FUND_ACC) and c_rela_type = 'RELA_FUND_ACC' ");
			pstmt = conn.prepareStatement(sql.toString());
			int flag = pstmt.executeUpdate();
			if(flag>0){
				result = true;
			}
		} catch(Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			// 关闭数据库资源
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
		
	/**
	 * 插入机构账户关联关系
	 * @param pojoList
	 * @param orgCode
	 * @return
	 */
	public String insertOrgRelaFundAcc(List<FundAcc> pojoList, String orgCode) {
		String res = "0";
		PreparedStatement ptmt = null;
		Connection conn = null;
		StringBuffer buf = new StringBuffer();
		buf.append("insert into t_p_bi_org_rela (C_IDEN,C_RELA_TYPE,C_ORG_CODE,C_RELA_CODE,C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME)");
		buf.append("values (sequ_p_bi_org_rela.nextval,'fundAccInfo',?,?,'',1,?,?,'','')");
		String userCode = ContextFactory.getContext().getUserCode();
		String nowTime = DateUtil.getNow(MvcConstant._DATA_STD_DATE_FORMAT);
		try {
			conn = loadNewConnection();
			conn.setAutoCommit(false);
			ptmt = openPreparedStatement(buf.toString(), conn);
			for (FundAcc fundAcc : pojoList) {
				ptmt.setString(1,orgCode);
				ptmt.setString(2,fundAcc.getC_OPEN_ACC_NO());
				ptmt.setString(3,userCode);
				ptmt.setString(4,nowTime);
				ptmt.addBatch();
			}
			
			int[] cnt = ptmt.executeBatch();
			if(cnt.length > 0){
				res = String.valueOf(cnt[0]);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			logger.error("插入机构账户关联关系询失败",ex);
			throw new DataAccessException("查插入机构账户关联关系失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}
		return res;
	}
	
	public String insertFundAccForWebServiceInfo(FundAcc acc){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String result = "Fail";
		try{
			conn = this.loadNewConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("insert into T_P_BI_FUND_ACC(C_IDEN,C_OPEN_ACC_NO,C_OPEN_ACC_NAME,C_OPEN_ADDR,C_DC_CODE,C_PAY_CODE,C_ACCOUNT_TYPE,N_CHECK_STATE,C_UPDATE_BY ,C_UPDATE_TIME,D_BEGIN,D_END) ");
			sql.append("select SEQU_P_BI_FUND_ACC.Nextval as c_iden,t.* from ( ");
			sql.append("  select ? as C_OPEN_ACC_NO,? as C_OPEN_ACC_NAME,? as C_OPEN_ADDR,'CNY' as C_DC_CODE ,? as C_PAY_CODE, ");
			sql.append(" '' as C_ACCOUNT_TYPE,1 as N_CHECK_STATE,' ' as C_UPDATE_BY, ");
			sql.append(" to_char(sysdate, 'yyyymmdd hh24:mi:ss') as C_UPDATE_TIME, ");
			sql.append(" to_date(to_char(sysdate, 'yyyy-MM-dd'), 'yyyy-MM-dd') as D_BEGIN, ");
			sql.append(" date '9998-12-31' as D_END from dual ) t  where not exists ");
			sql.append(" (select 1 from T_P_BI_FUND_ACC tt where tt.c_open_acc_no = t.c_open_acc_no and tt.c_open_addr = t.c_open_addr and tt.c_open_acc_name = t.c_open_acc_name ) ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, acc.getC_OPEN_ACC_NO());
			pstmt.setString(2, acc.getC_OPEN_ACC_NAME());
			pstmt.setString(3, acc.getC_OPEN_ADDR());
			pstmt.setString(4, acc.getC_PAY_CODE());
			pstmt.executeUpdate();
			result = "Success";
		}catch(Exception ex){
			logger.error("修改机构信息错误", ex);
			throw new DataAccessException("修改机构信息错误",ex);
		}finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 根据存款账户类型和已经关联到该组合或者没有关联到任一组合的现金账户 从估值迁移 器去除依赖    20171026 add by zhouning
	 * @param portCode 组合代码
	 * @param dvAccType 现金账户类型
	 * @return 现金账户集合
	 */
	public List<CashAcc> getDataListByPortCode(String portCode, String dvAccType)
	{
		List<CashAcc> pojoList = new ArrayList<CashAcc>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		CashAcc t = null;
		try {
			conn = this.loadNewConnection();
			boolean isType = false;
			int i = 1;
			if(null != dvAccType && dvAccType.trim().length() != 0){
				isType = true;
			}
			sql = this.getDataListByPortCode(isType);
			pstmt = conn.prepareStatement(sql);
			//将“|” 转换为“，”号
			portCode = portCode.replace("|", ",");
			pstmt.setArray(i++, OraDbTool.newInstance().sqlOverLongCondition(portCode,conn));
			if(isType){
				pstmt.setString(i++, dvAccType);
			}
			pstmt.setArray(i++, OraDbTool.newInstance().sqlOverLongCondition(portCode,conn));
            if(isType){
    			pstmt.setString(i++, dvAccType);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = new CashAcc();
				t.setC_CA_CODE(rs.getString("C_CA_CODE"));
				t.setC_CA_NAME(rs.getString("C_CA_NAME"));
				t.setC_DC_CODE(rs.getString("C_DC_CODE"));
				t.setC_DESC(rs.getString("C_DESC"));
				if(exisitColumn(rs,"C_DV_ACC_NATURE")){
					t.setC_DV_ACC_NATURE(rs.getString("C_DV_ACC_NATURE"));
				}
				if(exisitColumn(rs,"C_DV_ACC_TYPE")){
					t.setC_DV_ACC_NATURE(rs.getString("C_DV_ACC_TYPE"));
				}
				if(exisitColumn(rs,"C_DV_DETAIL_TYPE")){
					t.setC_DV_ACC_NATURE(rs.getString("C_DV_DETAIL_TYPE"));
				}
				if(exisitColumn(rs,"C_ORG_CODE")){
					t.setC_DV_ACC_NATURE(rs.getString("C_ORG_CODE"));
				}
				t.setId(rs.getString("C_IDEN"));
				t.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询现金账户失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	
	private boolean exisitColumn(ResultSet rs,String columnName){
		boolean result = false;
		try {
			if(rs.findColumn(columnName) > 0){
				result = true;
			}
		} catch (SQLException e){
			result = false;
		}
		return result;
	}
	
	private String getDataListByPortCode(boolean isType)
	{
		String typeStr = " ";
		if(isType){
			typeStr = " and a.c_dv_acc_type = ? ";
		}
		StringBuffer buf = new StringBuffer();
		buf.append("select * from ( select a.* from T_P_BI_CASH_ACC a ");
		buf.append("join t_p_ab_port_rela r on ");
		buf.append("r.c_rela_type = 'RELA_CASH_ACC' ");
		buf.append("and r.n_check_state = 1 ");
		buf.append("and r.c_rela_code = a.c_ca_code  ");
		//buf.append("and ((r.c_dv_type_code = a.c_dv_acc_type) ");
		buf.append("where a.N_CHECK_STATE = 1 and r.c_port_code in (SELECT * FROM TABLE(?)) ");
		buf.append(typeStr);
		buf.append(" order by a.c_ca_code) ");
		buf.append("union ");
		buf.append("select * from (select a.* from T_P_BI_CASH_ACC a ");
		buf.append("where a.N_CHECK_STATE = 1 ");
		buf.append("and not exists (select 1 ");
		buf.append("from t_p_Ab_Port_Rela D where d.c_rela_code = a.c_ca_code AND D.c_rela_type = 'RELA_CASH_ACC'");
		buf.append("and D.c_port_code in (SELECT * FROM TABLE(?)) and D.n_check_state = 1) ");
		buf.append(typeStr);
		buf.append(" order by a.c_ca_code) ");
		return buf.toString();
	}
	
	//STORY #35130 招商基金-删除账户信息时如果此账户已被用则需弹出提示框
	public String queryZfbyPort(String c_open_addr, String c_open_acc_no,String c_open_acc_name){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = "select 1 as c  from "+ this.builder.getT_P_BI_FUND_ACC()+" where c_open_addr = ? and c_open_acc_no = ? and c_open_acc_name = ? and C_HAVEUSED = 1 ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_open_addr);
			pstmt.setString(2, c_open_acc_no);
			pstmt.setString(3, c_open_acc_name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return "true";
			}
		} catch (Exception ex) {
			logger.error("查询失败", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return "false";
	}
	
	public Boolean deleteByRealId(String[] ids){
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		StringBuffer sql = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		Boolean result = false;
		
		try {
			conn = this.loadNewConnection();
			sql.append(" delete from T_P_AB_PORT_ACC_RELA where c_rela_code in (select * from table(?))");
			//删除产品账户信息时，同步删除T_C_CP_FUND_ACC 。根据唯一约束字段  C_OPEN_ACC_NO, C_OPEN_ACC_NAME, C_OPEN_ADDR, C_DC_CODE  删除组合关联的账户信息
			sql2.append(" delete from t_c_cp_fund_acc where  c_rela_code in (select * from table(?))  ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt2 = conn.prepareStatement(sql2.toString());
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(ids, conn));
			pstmt2.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(ids, conn));
			int falg = pstmt.executeUpdate();
			pstmt2.executeUpdate();
			if(falg>0){
				result = true;
			}
		} catch(Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt,pstmt2);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(sql);
			StringUtil.clearStringBuffer(sql2);
		}
		return result;
	}
	
	
	public List<PortRela> getFundAcc(String[] ids){
		List<PortRela> protList = new ArrayList<PortRela>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try{
			conn = this.loadNewConnection();
			sql = builder.getFundAcc();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(ids,conn));
			rs = pstmt.executeQuery();
			while(rs.next()){
				PortRela portRela = new PortRela();
				portRela.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				portRela.setC_RELA_CODE(rs.getString("C_RELA_CODE"));
				portRela.setC_RELA_TYPE(rs.getString("c_ACCOUNT_TYPE"));
				protList.add(portRela);
			}
			
		}catch (Exception ex){
			logger.error("查询失败:", ex);			
		}finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
			DbFun.releaseConnection(conn);
		}
		return protList;
	}
	
	public List<PortRela> getFundAccPort(String portCode){
		List<PortRela> protList = new ArrayList<PortRela>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
//		int length = recordIds.size();
//		String[] arrStr = new String[length];
//		for (int i = 0; i < length; i++) {
//			arrStr[i] = recordIds.get(i);
//		}
		try{
			conn = this.loadNewConnection();
			sql = builder.getFundAccPort();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
//			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(arrStr,conn));
			rs = pstmt.executeQuery();
			while(rs.next()){
				PortRela portRela = new PortRela();
				portRela.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				portRela.setC_RELA_CODE(rs.getString("C_RELA_CODE"));
				portRela.setC_RELA_TYPE(rs.getString("c_ACCOUNT_TYPE"));
				protList.add(portRela);
			}
			
		}catch (Exception ex){
			logger.error("查询失败:", ex);			
		}finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
			DbFun.releaseConnection(conn);
		}
		return protList;
	}


//	
	/**
	 * BUG #181064 综合指令-待划款确认，选择调入DVP指令，点击划款确认，系统返回发送成功，返回小心为“null”，但指令的状态没有发生变化 
	 * 通过账号获取开户机构，过滤开户机构为空的
	 * @param AccNo
	 * @return result
	 */
	public String getFundAccOrgcodeByAccNo(String accNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String orgCode = "";
		try {
			conn = this.loadNewConnection();
			sql.append("select c_org_code from T_P_BI_FUND_ACC where c_open_acc_no = ?  and trim(c_org_code) is not null ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, accNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orgCode = rs.getString(1);
			}
		} catch (Exception ex) {
			logger.error("查询失败：" + ex.getMessage(), ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return orgCode;
	}
	
	/**
	 * 判断账户是否存在
	 * @param types
	 * @return
	 */
	public FundAcc judgeFundAccByCondition(HashMap<String, Object> paraMap) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSetTools rstool = null;
		FundAcc fundAcc = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			String sql =" SELECT * FROM T_P_BI_FUND_ACC  WHERE C_PORT_CODE=? AND C_DC_CODE=? and C_OPEN_ACC_NAME=? and C_OPEN_ACC_NO=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, paraMap.get("C_PORT_CODE").toString());
			pst.setString(2, paraMap.get("C_DC_CODE").toString());
			pst.setString(3, paraMap.get("C_OPEN_ACC_NAME").toString());
			pst.setString(4, paraMap.get("C_OPEN_ACC_NO").toString());		
			rs = pst.executeQuery();
			if (rs.next()) {
				fundAcc = rstool.ResultToBeanGeneric(rs, FundAcc.class);
			}
		} catch (Exception ex) {
			logger.error("查询失败：" + ex.getMessage(), ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return fundAcc;
	}
	
	/**
	 * 更新账户类型
	 * @param types
	 * @return
	 *//*
	public boolean updateFundAccType(HashMap<String, Object> paraMap) {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean bool=false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			String sql =" UPDATE T_P_BI_FUND_ACC SET C_ACCOUNT_TYPE=? WHERE C_PORT_CODE=? AND C_DC_CODE=? and C_OPEN_ACC_NAME=? and C_OPEN_ACC_NO=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, paraMap.get("C_ACCOUNT_TYPE").toString());
			pst.setString(2, paraMap.get("C_PORT_CODE").toString());
			pst.setString(3, paraMap.get("C_DC_CODE").toString());
			pst.setString(4, paraMap.get("C_OPEN_ACC_NAME").toString());
			pst.setString(5, paraMap.get("C_OPEN_ACC_NO").toString());		
			pst.executeUpdate();
			conn.commit();
			bool=true;
		} catch (Exception ex) {
			logger.info("更新失败：" + ex.getMessage(), ex);
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return bool;
	}*/
	
	/**
	 * 更新账户类型
	 * @param types
	 * @return
	 * TODO
	 */
	public boolean updateFundAccType(String iden,String accountType) {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean bool=false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			String sql =" UPDATE T_P_BI_FUND_ACC SET C_ACCOUNT_TYPE=? WHERE C_IDEN=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, accountType);
			pst.setString(2, iden);	
			pst.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			bool=true;
		} catch (Exception ex) {
			logger.error("更新失败：" + ex.getMessage(), ex);
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return bool;
	}
	/**
	 * 更新组合代码
	 * @param types
	 * @return
	 */
	public boolean updatePortCode(String iden,String portCode) {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean bool=false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			String sql =" UPDATE T_P_BI_FUND_ACC SET C_PORT_CODE=? , C_ASS_CODE = ? WHERE C_IDEN=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1,portCode);
			pst.setString(2,portCode);
			pst.setString(3, iden);
			pst.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			bool=true;
		} catch (Exception ex) {
			logger.error("更新失败：" + ex.getMessage(), ex);
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return bool;
	}
	
	
	/**
	 * 查询指定账户类型的账户
	 * 合并21.5webservice时，新增方法
	 * @param types
	 * @return
	 */
	public FundAcc getFundAccByInfo(HashMap<String, String> paraMap) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSetTools rstool = null;
		FundAcc fundAcc = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT a.c_iden, r.c_port_code, a.c_ca_code, a.c_dc_code,  a.c_open_addr,  a.c_open_acc_no,  a.c_sys_code, ");
			sql.append(" a.c_usage,a.c_desc, a.n_check_state, a.c_update_by,  a.c_update_time,  a.c_check_by, a.c_check_time, a.c_open_acc_name, ");
			sql.append(" a.d_begin, a.d_end,a.c_org_code, a.c_holder, a.c_ass_code,a.c_account_type, a.c_haveused, ");
			sql.append(" a.c_inter_org_code,  a.c_bank_code, ");
			sql.append(" a.c_pay_code,a.C_DISCARD_STATUS  FROM T_P_BI_FUND_ACC a INNER JOIN t_p_ab_port_acc_rela r ON a.C_IDEN = r.C_RELA_CODE  WHERE  a.C_DC_CODE=? and a.C_OPEN_ACC_NAME=? and a.C_OPEN_ACC_NO=? ");
			if (paraMap.containsKey("C_OPEN_ADDR")) {
				sql.append("and a.C_OPEN_ADDR=?");
			}
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, paraMap.get("C_DC_CODE").toString());
			pst.setString(2, paraMap.get("C_OPEN_ACC_NAME").toString());
			pst.setString(3, paraMap.get("C_OPEN_ACC_NO").toString());
			if(paraMap.containsKey("C_OPEN_ADDR")){
				pst.setString(4, paraMap.get("C_OPEN_ADDR").toString());
			}

			rs = pst.executeQuery();
			if (rs.next()) {
				fundAcc = rstool.ResultToBeanGeneric(rs, FundAcc.class);
			}
		} catch (Exception ex) {
			logger.error("查询失败：" + ex.getMessage(), ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return fundAcc;
	}
	
	/**
	 * 查询指定账户类型的账户
	 * 合并21.5webservice时，新增方法
	 * @param types
	 * @return
	 */
	public FundAcc getFundAccByInfo(Map<String, Object> paraMap) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSetTools rstool = null;
		FundAcc fundAcc = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append(" SELECT a.c_iden, r.c_port_code, a.c_ca_code, a.c_dc_code,  a.c_open_addr,  a.c_open_acc_no,  a.c_sys_code, ");
			sql.append(" a.c_usage,a.c_desc, a.n_check_state, a.c_update_by,  a.c_update_time,  a.c_check_by, a.c_check_time, a.c_open_acc_name, ");
			sql.append(" a.d_begin, a.d_end,a.c_org_code, a.c_holder, a.c_ass_code,a.c_account_type, a.c_haveused, ");
			sql.append(" a.c_inter_org_code,  a.c_bank_code, ");
			sql.append(" a.c_pay_code,a.C_DISCARD_STATUS  FROM T_P_BI_FUND_ACC a INNER JOIN t_p_ab_port_acc_rela r ON a.C_IDEN = r.C_RELA_CODE  WHERE  a.C_DC_CODE=? and a.C_OPEN_ACC_NAME=? and a.C_OPEN_ACC_NO=? ");
			if (paraMap.containsKey("C_OPEN_ADDR")) {
				sql.append("and a.C_OPEN_ADDR=?");
			}
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, paraMap.get("C_DC_CODE").toString());
			pst.setString(2, paraMap.get("C_OPEN_ACC_NAME").toString());
			pst.setString(3, paraMap.get("C_OPEN_ACC_NO").toString());
			if(paraMap.containsKey("C_OPEN_ADDR")){
				pst.setString(4, paraMap.get("C_OPEN_ADDR").toString());
			}
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(FundAcc.class.newInstance());
			rs = pst.executeQuery();
			if (rs.next()) {
//				fundAcc = rstool.ResultToBeanGeneric(rs, FundAcc.class);
				fundAcc = rstool.ResultToBeanGeneric(rs, FundAcc.class, props);
			}
		} catch (Exception ex) {
			logger.info("查询失败：" + ex.getMessage(), ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return fundAcc;
	}
	
	
	/**
	 * 根据账号，地址，名称获取省份和城市
	 * @param accNo
	 * @param address
	 * @param name
	 * @return
	 */
	public String getProviceAndCityByNoAddrName(String accNo,String address,String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("select ttt.c_open_province,ttt.c_open_city from ");
		sql.append(" (SELECT t.c_iden, ROW_NUMBER() OVER(partition by t.c_open_acc_name,t.c_open_acc_no,t.c_open_addr order by t.c_update_time desc) as RN FROM T_P_BI_FUND_ACC t");
		sql.append(" where  t.c_open_acc_name=? and t.c_open_acc_no=? and t.c_open_addr=?) tt");
		sql.append(" left join T_P_BI_FUND_ACC ttt ");
		sql.append("on tt.c_iden = ttt.c_iden");
		String result = null;
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, accNo);
			pstmt.setString(3, address);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1) + "#" + rs.getString(2);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return result;
	}
	
	/**
	 * 更新缓存
	 * 
	 * @param timestamp  时间戳
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer buf = new StringBuffer();
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);			
			conn = this.loadNewConnection();

			buf.append("select acc.C_IDEN,");
			buf.append(" acc.C_OPEN_ACC_NAME,");
			buf.append(" acc.C_CA_CODE,");
			buf.append(" acc.C_DC_CODE,");
			buf.append(" acc.C_OPEN_ADDR,");
			buf.append(" acc.C_OPEN_ACC_NO,");
			buf.append(" acc.C_ORG_CODE,");
			buf.append(" acc.C_USAGE,");
			buf.append(" acc.C_DESC,");
			buf.append(" acc.C_HOLDER,");
			buf.append(" acc.C_ASS_CODE,");
			buf.append(" acc.C_PAY_CODE,");
			buf.append(" acc.C_INTER_ORG_CODE,");
			buf.append(" acc.C_ACCOUNT_TYPE,");
			buf.append(" acc.C_PROVINCE,");
			buf.append(" acc.C_CITY,");
			buf.append(" acc.D_BEGIN,");
			buf.append(" acc.D_END,");
			buf.append(" acc.C_HAVEUSED,");
			buf.append(" acc.C_OPEN_MODE,");
			buf.append(" acc.C_BC_ORG_CODE,");
			buf.append(" acc.C_RUNNING_ACC,");
			buf.append(" acc.C_BC_LINK_NO,");
			buf.append(" acc.C_CNX,");
			buf.append(" acc.C_SWIFT_CODE,");
			buf.append(" acc.C_BANK_ADDR,");
			buf.append(" acc.C_CREATE_BY,");
			buf.append(" acc.C_CREATE_TIME,");
			buf.append(" acc.C_CHECK_BY,");
			buf.append(" acc.C_CHECK_TIME,");
			buf.append(" acc.C_UPDATE_BY,");
			buf.append(" acc.C_UPDATE_TIME,");
			buf.append(" cury.C_DC_NAME, ");
			buf.append(" rela.C_PORT_CODE ,acc.n_check_state,acc.C_DISCARD_STATUS ");
			buf.append(" from T_P_BI_FUND_ACC acc ");
			buf.append(" left join T_P_AB_PORT_ACC_RELA rela on acc.c_iden = rela.c_rela_code ");
			buf.append(" left join T_S_DC_CURY cury on acc.C_DC_CODE = cury.C_DC_CODE");
			buf.append(" where (TO_DATE(acc.c_check_time,'yyyy-MM-dd hh24:mi:ss') >= to_date(?,'yyyy-MM-dd hh24:mi:ss') ");
			buf.append("  or TO_DATE(acc.c_update_time,'yyyy-MM-dd hh24:mi:ss') >= to_date(?,'yyyy-MM-dd hh24:mi:ss')") ;
			buf.append(" )  and  trim(acc.c_check_time) is not null  and  trim(acc.c_update_time) is not null");
			sql = buf.toString();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, timestamp);
			pstmt.setString(2, timestamp);

			rs = pstmt.executeQuery();
			FundAcc t = null;
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(FundAcc.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class, props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(buf);
		}

		return pojoList;
	}
	
	/**
	 * 账户字符串中，去除中间的款项类型
	 * @param search
	 * @return
	 */
    public String getAccByRmHkType(String search){
    	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.c_open_acc_no              ");
		sql.append("   from T_P_BI_FUND_ACC a            ");
		sql.append("  where  a.c_open_acc_no in(select * ");
		sql.append("           from table(PKG_FUN_MYSPLIT.MYSPLIT(?)))   ");

		StringBuffer result = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			Clob clob = conn.createClob();
			clob.setString(1, search);
			pstmt.setClob(1, clob);
			
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.append(rs.getString(1) + ",");
			}
		} catch (Exception ex) {
			logger.error("查询失败：" + ex.getMessage());
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

    	return result.toString();
    }
    
    /**
	 * 获取以开户地址为父节点，账户为子节点的树形结构
	 * @return
	 */
	public List<BasePojo> getFundAccNoAndAddrList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<BasePojo> dataList = new ArrayList<BasePojo>();
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		FundAcc t = null;
		StringBuffer buf = new StringBuffer();
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			buf.append(" select distinct '' C_IDEN, ");
			buf.append(" '' C_PORT_CODE, ");
			buf.append(" '' C_CA_CODE, ");
			buf.append(" '' C_DC_CODE, ");
			buf.append(" '' C_OPEN_ADDR, ");
			buf.append(" C_OPEN_ADDR as C_OPEN_ACC_NO, ");
			buf.append(" '' C_SYS_CODE, ");
			buf.append(" '' C_USAGE, ");
			buf.append(" '' C_DESC, ");
			buf.append(" N_CHECK_STATE, ");
			buf.append(" '' C_UPDATE_BY, ");
			buf.append(" '' C_UPDATE_TIME, ");
			buf.append(" '' C_CHECK_BY, ");
			buf.append(" '' C_CHECK_TIME, ");
			buf.append(" '' C_OPEN_ACC_NAME, ");
			buf.append(" D_BEGIN, D_END, ");
			buf.append(" '' as C_ORG_CODE, ");
			buf.append(" '' C_HOLDER, ");
			buf.append(" '' C_ASS_CODE, ");
			buf.append(" '' C_HAVEUSED, ");
			buf.append(" 'root' as C_ACCOUNT_TYPE, ");
			buf.append(" '' C_PAY_CODE, ");
			buf.append(" '' C_BANK_CODE, ");
			buf.append(" '' C_INTER_ORG_CODE       ");
			buf.append(" from t_p_bi_fund_acc ");
			buf.append(" where n_check_state = 1 ");
			buf.append(" union all");
			buf.append(" select A.C_IDEN, ");
			buf.append(" A.C_PORT_CODE, ");
			buf.append(" A.C_CA_CODE, ");
			buf.append(" A.C_DC_CODE, ");
			buf.append(" A.C_OPEN_ADDR, ");
			buf.append(" A.C_OPEN_ACC_NO, ");
			buf.append(" A.C_SYS_CODE, ");
			buf.append(" A.C_USAGE, ");
			buf.append(" d.C_dac_name as C_DESC, ");
			buf.append(" A.N_CHECK_STATE, ");
			buf.append(" A.C_UPDATE_BY, ");
			buf.append(" A.C_UPDATE_TIME, ");
			buf.append(" A.C_CHECK_BY, ");
			buf.append(" A.C_CHECK_TIME, ");
			buf.append(" A.C_OPEN_ACC_NAME, ");
			buf.append(" D_BEGIN, D_END,");
			buf.append(" o.C_ORG_NAME as C_ORG_CODE, ");
			buf.append(" A.C_HOLDER, ");
			buf.append(" A.C_ASS_CODE, ");
			buf.append(" A.C_HAVEUSED, ");
			buf.append(" A.C_OPEN_ADDR as C_ACCOUNT_TYPE, ");
			buf.append(" A.C_PAY_CODE, ");
			buf.append(" A.C_BANK_CODE, ");
			buf.append(" A.C_INTER_ORG_CODE ");
			buf.append(" from t_p_bi_fund_acc a ");
			buf.append(" left join t_cp_fundtype_rela ft on a.c_iden = ft.c_rela_code ");
			buf.append(" left join t_p_bi_org o on a.c_org_code=o.c_org_code ");
			buf.append(" left join t_s_dac_type d on ft.C_ACCOUNT_TYPE= d.c_dac_code ");
			buf.append(" where a.n_check_state = 1 ");
			pstmt = conn.prepareStatement(buf.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				dataList.add(t);
			}
		} catch (Exception ex) {
			logger.error("获取账户信息失败", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return dataList;
	}

	public HashMap<String, FundAcc> getUniqueAccountTypeByPorts(String portCodes, String accountType ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		HashMap<String, FundAcc> dataMap = new HashMap<String, FundAcc>();
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		FundAcc t = null;
		String sql = "";
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();
			sql = builder.getUniqueAccountTypeByPortsSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountType);
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(String.valueOf(portCodes), conn));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				String portCode = StringUtil.IsNullOrEmptyT(t.getC_PORT_CODE()) ? "PUBLIC" : t.getC_PORT_CODE();
				
				if(dataMap.containsKey(portCode)){
					dataMap.put(portCode, null);
				}else{
					dataMap.put(portCode, t);
				}
			}
		} catch (Exception ex) {
			logger.error("获取账户信息失败", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return dataMap;
	}
	
	/**
	  * 加载账户类型为托管户、副托管户的账户且关联的组合的【组合级别】为‘组合层’的账户
	  * @return
	  */
	 public ArrayList<FundAcc>  getAllFundAccByType(HashMap<String,String> paraMap,String ports){
	  ArrayList<FundAcc> result = new ArrayList<FundAcc>();
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  StringBuffer sb = new StringBuffer();
	  sb.append(" select a.*  from  T_P_BI_FUND_ACC a   ");
	  sb.append("  left join T_P_AB_PORT_ACC_RELA r on r.c_rela_code = a.c_iden       ");
	  sb.append("  left join T_P_AB_PORT p on p.c_port_code = r.c_port_code           ");    
	  sb.append("  where p.C_DV_PORT_CODE = ?  and                            ");
	  sb.append("  EXISTS (SELECT 1 FROM  T_CP_FUNDTYPE_RELA FT  WHERE  a.C_IDEN = FT.C_RELA_CODE AND ft.C_ACCOUNT_TYPE IN (SELECT * FROM TABLE(?)) )  ");
	  sb.append("  and p.c_port_code in(select * from table(?))                 ");
	  
	  try {
	   conn = this.loadNewConnection();
	   pstmt = conn.prepareStatement(sb.toString());
	   pstmt.setString(1, paraMap.get("C_DV_PORT_CODE"));
	   pstmt.setArray(2,OraDbTool.newInstance().sqlOverLongCondition(
	       String.valueOf(paraMap.get("ARRAY_C_ACCOUNT_TYPE")), conn));
	   pstmt.setArray(3,OraDbTool.newInstance().sqlOverLongCondition(
		       String.valueOf(ports), conn));
	   rs = pstmt.executeQuery();
	   
	   while (rs.next()) {
		    FundAcc acc = new FundAcc();
		    acc.setId(rs.getString("C_IDEN"));
		    acc.setC_OPEN_ACC_NO(rs.getString("C_OPEN_ACC_NO"));
		    acc.setC_OPEN_ACC_NAME(rs.getString("C_OPEN_ACC_NAME"));
		    result.add(acc);
	   }
	  } catch (Exception ex) {
	       logger.error(ex.getMessage());
	  } finally {
		   this.closeResultSetFinal(rs);
		   this.closeStatementFinal(pstmt);
		   this.releaseConnection(conn);
	  }
	    return result;
	 }

	
	public void updateFundAccRela(String id, String c_account_type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		StringBuffer sql2 = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement("update T_P_AB_PORT_ACC_RELA t set t.c_account_type = ? where t.c_rela_code = ?");
			
			//在关联组合时，同步插入 原始 t_c_cp_fund_acc 账户信息表
			sql2.append(" update t_c_cp_fund_acc  Set (                                                              ");
			sql2.append(" 	    C_CA_CODE  ,C_DC_CODE    ,C_OPEN_ADDR                           ");
			sql2.append(" 	     ,C_OPEN_ACC_NO ,C_SYS_CODE    ,C_USAGE ,C_DESC ,N_CHECK_STATE                         ");
			sql2.append(" 	     ,C_UPDATE_BY  ,C_UPDATE_TIME  ,C_CHECK_BY ,C_CHECK_TIME  ,C_OPEN_ACC_NAME             ");
			sql2.append(" 	     ,D_BEGIN,D_END,C_ORG_CODE ,C_HOLDER  ,C_ASS_CODE  ,C_HAVEUSED                         ");
			sql2.append(" 	     ,C_ACCOUNT_TYPE  ,C_PAY_CODE  ,C_BANK_CODE ,C_BC_LINK_NO ,C_PROVINCE                  ");
			sql2.append(" 	     ,C_STR1  ,C_OPEN_CITY  ,C_INTERFACE_CODE ,C_INTER_ORG_CODE ,C_CNX                     ");
			sql2.append(" 	     ,C_DC_NAME ,N_NUMBER2 ,C_PAY_CHANNEL  ,C_SWIFT_CODE ,C_OWNER ,C_STR3 ,C_OPEN_MODE     ");
			sql2.append(" 	     ,C_CREATE_BY ,C_INTERFACE_NAME  ,C_STR2 ,C_OPEN_BANK  ,C_BANK_ADDR ,C_CASH_ACCOUNT    ");
			sql2.append(" 	     ,C_CITY  ,N_NUMBER3 ,C_BC_ORG_CODE ,C_RUNNING_ACC ,C_CREATE_TIME ,N_NUMBER1           ");
			sql2.append(" 	     ,C_OPEN_PROVINCE ,D_YEARLY_CHECK_DATE ,C_BELONG_BANK ,C_ASSO_NUM  ,C_ENABLE  )   =     ");
			sql2.append(" 	    ( select   C_CA_CODE  ,C_DC_CODE ,C_OPEN_ADDR                          ");
			sql2.append(" 	     ,C_OPEN_ACC_NO ,C_SYS_CODE    ,C_USAGE ,C_DESC ,N_CHECK_STATE                         ");
			sql2.append(" 	     ,C_UPDATE_BY  ,C_UPDATE_TIME  ,C_CHECK_BY ,C_CHECK_TIME  ,C_OPEN_ACC_NAME             ");
			sql2.append(" 	     ,D_BEGIN,D_END,C_ORG_CODE ,C_HOLDER  ,C_ASS_CODE  ,C_HAVEUSED                         ");
			sql2.append(" 	     ,?  ,C_PAY_CODE  ,C_BANK_CODE ,C_BC_LINK_NO ,C_PROVINCE                               ");
			sql2.append(" 	     ,C_STR1  ,C_OPEN_CITY  ,C_INTERFACE_CODE ,C_INTER_ORG_CODE ,C_CNX                     ");
			sql2.append(" 	     ,C_DC_NAME ,N_NUMBER2 ,C_PAY_CHANNEL  ,C_SWIFT_CODE ,C_OWNER ,C_STR3 ,C_OPEN_MODE     ");
			sql2.append(" 	     ,C_CREATE_BY ,C_INTERFACE_NAME  ,C_STR2 ,C_OPEN_BANK  ,C_BANK_ADDR ,C_CASH_ACCOUNT    ");
			sql2.append(" 	     ,C_CITY  ,N_NUMBER3 ,C_BC_ORG_CODE ,C_RUNNING_ACC ,C_CREATE_TIME ,N_NUMBER1           ");
			sql2.append(" 	     ,C_OPEN_PROVINCE ,D_YEARLY_CHECK_DATE ,C_BELONG_BANK ,C_ASSO_NUM  ,C_ENABLE           ");
			sql2.append(" 	     from t_p_bi_fund_acc where c_iden = ? )        ");
			sql2.append("  where  c_rela_code = ?  ");
			pstmt2 = conn.prepareStatement(sql2.toString());
			pstmt.setString(1, c_account_type);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
			pstmt2.setString(1, c_account_type);
			pstmt2.setString(2, id);
			pstmt2.setString(3, id);
			pstmt2.executeUpdate();
		} catch (Exception ex) {
			logger.error("更新失败：" + ex.getMessage());
		} finally {
			closeStatementFinal(pstmt);
			closeStatementFinal(pstmt2);
			releaseConnection(conn);
		}
	}

	public Boolean deleteThenSaveFundRela(String relaCode, String port,	String accountType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		StringBuffer deleteSql = new StringBuffer();
		StringBuffer insertSql = new StringBuffer();
		Boolean result = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			//删除操作
			if (StringUtil.IsNullOrEmpty(relaCode)){
				deleteSql.append(" delete from T_P_AB_PORT_ACC_RELA where c_port_code = ? ");
			}
			else{
				deleteSql.append(" delete from T_P_AB_PORT_ACC_RELA where c_rela_code =? and c_port_code = ? ");
			} 
			
			pstmt = conn.prepareStatement(deleteSql.toString());
			
			if (StringUtil.IsNullOrEmpty(relaCode)) {
				pstmt.setString(1, port);
			}
			else {
				pstmt.setString(1, relaCode);
				pstmt.setString(2, port);
			}
			
			pstmt.executeUpdate();
			//插入操作
			insertSql.append(" insert into T_P_AB_PORT_ACC_RELA (C_IDEN,C_PORT_CODE,C_ACCOUNT_TYPE,C_RELA_CODE) ");
			insertSql.append(" values (SEQU_P_AB_PORT_ACC_RELA.nextval,?,?,?)");
			pstmt2 = conn.prepareStatement(insertSql.toString());
			pstmt2.setString(1, port);
			pstmt2.setString(2, accountType);
			pstmt2.setString(3, relaCode);
			int falg = pstmt2.executeUpdate();
			
			
			if(falg>0){
				result = true;
			}
		} catch(Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			closeStatementFinal(pstmt2);
			releaseConnection(conn);
		}
		return result;
	}
	 
	public HashMap<String,String> getFundAccByNoAddrName(List<FundAcc> list) {
		HashMap<String,String> result = new HashMap<String,String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			StringBuffer sql = new StringBuffer();
			Map<String, Vocabulary> vocmap = CacheUtil.getVocCacheByType("CHZH_ZHMXFL");
			sql.append(" select t.c_open_acc_no,t.c_open_addr ,t.c_open_acc_name, t.c_account_type ");
			sql.append(" from T_P_BI_FUND_ACC t  where t.c_open_acc_no in (select * from table(?)) and t.c_open_addr in (select * from table(?)) and t.c_open_acc_name in (select * from table(?))");
			pstmt = conn.prepareStatement(sql.toString());
			StringBuffer accNos = new StringBuffer();
			StringBuffer accAddrs = new StringBuffer();
			StringBuffer accNames = new StringBuffer();
			for(int i= 0;i<list.size();i++){
				FundAcc pojo = list.get(i);
				accNos.append(pojo.getC_OPEN_ACC_NO());
				accNos.append(",");
				
				accAddrs.append(pojo.getC_OPEN_ADDR());
				accAddrs.append(",");
				
				accNames.append(pojo.getC_OPEN_ACC_NAME());
				accNames.append(",");
			}
			
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(accNos.substring(0,accNos.length() -1), conn));
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(accAddrs.substring(0,accAddrs.length() -1), conn));
			pstmt.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(accNames.substring(0,accNames.length() -1), conn));
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				String key = rs.getString("c_open_acc_no")+"#"+rs.getString("c_open_addr") + "#" + rs.getString("c_open_acc_name");
				// TODO
				/*if (!StringUtil.IsNullOrEmptyT(rs.getString("c_account_type"))){
					if (rs.getString("c_account_type").contains("|")){
						String str[]  = rs.getString("c_account_type").split("|");
						vocmap.containsKey(rs.getString("c_account_type")) 
						? vocmap.get().getC_DV_NAME() 
								: " ";
						
					}
				}*/
				
				result.put(key, vocmap.containsKey(rs.getString("c_account_type")) ? vocmap.get(rs.getString("c_account_type")).getC_DV_NAME() : " ");
			}
		} catch (Exception ex) {
			logger.error("更新失败：" + ex.getMessage());
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return result;
	}
	
	/**
	  * [合并代码]STORY71636【中银基金】【高】运营类指数许可费支付指令生成。 add by lijinpeng 2019年5月6日
	  * 根据组合和账户类型使用f_get216_acc_no获得对应的账户
	  * @return
	  */
	 public ArrayList<FundAcc>  queryAccNoByfun(HashMap<String,String> paraMap){
	  ArrayList<FundAcc> result = new ArrayList<FundAcc>();
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  
	  StringBuffer sb = new StringBuffer();
	  
	  sb.append(" SELECT PORT.C_PORT_CODE AS PORT_CODE ,ACC.C_ASS_CODE,ACC.C_BANK_ADDR,ACC.C_BC_LINK_NO,ACC.C_BC_ORG_CODE,ACC.C_CA_CODE,ACC.C_CITY,ACC.C_CNX,ACC.C_DC_CODE,");
	  sb.append(" ACC.C_DC_NAME,ACC.C_DESC,ACC.C_DISCARD_STATUS,ACC.C_HAVEUSED,ACC.C_HOLDER,ACC.C_INTER_ORG_CODE,ACC.C_OPEN_ACC_NAME,ACC.C_OPEN_ACC_NO,ACC.C_OPEN_ADDR, ");
	  sb.append(" ACC.C_OPEN_MODE,ACC.C_ORG_CODE,ACC.C_PAY_CODE,ACC.C_PROVINCE,ACC.C_RUNNING_ACC,ACC.C_SWIFT_CODE,ACC.C_USAGE,ACC.C_IDEN ");
	  sb.append("  FROM T_P_BI_FUND_ACC ACC ,T_P_AB_PORT PORT WHERE ACC.C_IDEN = PKG_FUN_F_GET216_ACC_NO.F_GET216_ACC_NO(PORT.C_PORT_CODE,?) AND PORT.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
	  
	  try {
	   conn = this.loadNewConnection();
	   pstmt = conn.prepareStatement(sb.toString());
	   pstmt.setString(1, paraMap.get("C_ACCOUNT_TYPE")); //单个账户类型
	   pstmt.setArray(2,OraDbTool.newInstance().sqlOverLongCondition(
	       String.valueOf(paraMap.get("ARRAY_C_PORT_CODE")), conn)); //多个组合代码
	   rs = pstmt.executeQuery();
	   
	   while (rs.next()) {
	    FundAcc acc = new FundAcc();
	    acc.setC_ACCOUNT_TYPE(paraMap.get("C_ACCOUNT_TYPE"));
	    acc.setC_PORT_CODE(rs.getString("PORT_CODE"));
	    
//	    acc.setAuditDate(rs.getString("C_CHECK_TIME"));
//	    acc.setAuditState(rs.getInt("N_CHECK_STATE"));
	    acc.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
	    acc.setC_BANK_ADDR(rs.getString("C_BANK_ADDR"));
	    acc.setC_BC_LINK_NO(rs.getString("C_BC_LINK_NO"));
	    acc.setC_BC_ORG_CODE(rs.getString("C_BC_ORG_CODE"));
	    acc.setC_CA_CODE(rs.getString("C_CA_CODE"));
	    acc.setC_CITY(rs.getString("C_CITY"));
	    acc.setC_CNX(rs.getString("C_CNX"));
	    acc.setC_DC_CODE(rs.getString("C_DC_CODE"));
	    acc.setC_DC_NAME(rs.getString("C_DC_NAME"));
	    acc.setC_DESC(rs.getString("C_DESC"));
	    acc.setC_DISCARD_STATUS(rs.getString("C_DISCARD_STATUS"));
	    acc.setC_HAVEUSED(rs.getString("C_HAVEUSED"));
	    acc.setC_HOLDER(rs.getString("C_HOLDER"));
	    acc.setC_INTER_ORG_CODE(rs.getString("C_INTER_ORG_CODE"));
	    acc.setC_OPEN_ACC_NAME(rs.getString("C_OPEN_ACC_NAME"));
	    acc.setC_OPEN_ACC_NO(rs.getString("C_OPEN_ACC_NO"));
	    acc.setC_OPEN_ADDR(rs.getString("C_OPEN_ADDR"));
	    acc.setC_OPEN_MODE(rs.getString("C_OPEN_MODE"));
	    acc.setC_ORG_CODE(rs.getString("C_ORG_CODE"));
	    acc.setC_PAY_CODE(rs.getString("C_PAY_CODE"));
	    acc.setC_PROVINCE(rs.getString("C_PROVINCE"));
	    acc.setC_RUNNING_ACC(rs.getString("C_RUNNING_ACC"));
	    acc.setC_SWIFT_CODE(rs.getString("C_SWIFT_CODE"));
	    acc.setC_USAGE(rs.getString("C_USAGE"));
//	    acc.setD_BEGIN(rs.getDate("D_BEGIN"));
//	    acc.setD_END(rs.getDate("D_END"));
	    acc.setId(rs.getString("C_IDEN"));
//	    acc.setModifier(rs.getString("C_UPDATE_BY"));
//	    acc.setModifyDate(rs.getString("C_UPDATE_TIME"));
//	    acc.setOperator(rs.getString("C_CHECK_BY"));
	    result.add(acc);
	   }
	  } catch (Exception ex) {
	   logger.error(ex.getMessage());
	  } finally {
	   this.closeResultSetFinal(rs);
	   this.closeStatementFinal(pstmt);
	   this.releaseConnection(conn);
	  }
	  return result;
	 }
	 
	 /**
	 * 删除关联关系
	 * @param ids
	 * @return
	 */
	public String updatePaycodeByAcc(HashMap<String, String> map){
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		String result = "";
		try {
			conn = this.loadNewConnection();
			sql.append(" update t_p_bi_fund_acc set c_pay_code = ? where c_open_acc_no = ? and c_open_acc_name = ? and c_open_addr = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, map.get("C_PAY_CODE").toString());
			pstmt.setString(2, map.get("C_OPEN_ACC_NO").toString());
			pstmt.setString(3, map.get("C_OPEN_ACC_NAME").toString());
			pstmt.setString(4, map.get("C_OPEN_ADDR").toString());
			
		    pstmt.executeUpdate();
			result = "Success";
		} catch(Exception ex) {
			logger.error("更新大额支付号失败", ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 更新附件信息
	 * 新增/删除襙做
	 * @param delFileList
	 * @param addFileList
	 * @return String
	 * @author Xqr
	 */
	public String updateFileMsg(List<DataEnclosure> delFileList,List<DataEnclosure> addFileList) {
		String result = "Success";
		IDataEnclosureService dataEnService = YssServiceFactory.getInstance().createService(IDataEnclosureService.class);
		try {
			if(delFileList.size()>0){
				for(DataEnclosure data : delFileList){
					String fileName = data.getC_FileName_S();
					String dataId = data.getC_Data_Id();
					String funCode = data.getC_Fun_Code();
					dataEnService.deleteByFunCode(fileName,dataId,funCode);
				}
			}
			for (DataEnclosure data : addFileList) {
				data.setC_File_Type(null);
			}
	    	return dataEnService.insert(new ArrayList<BasePojo>(addFileList));
		} catch (Exception e) {
			logger.error("新增/删除附件信息失败", e);
			result = "Fail";
		} 
		return result;
	}
	
	
	/**
	  * 根据组合代码获取资产代码
	  * @param codes
	  * @return
	  */
	 public HashMap<String,String> getAssCodeByPortcode(String codes,String type){
		 HashMap<String,String> result = new HashMap<String,String>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = this.loadNewConnection();
				StringBuffer sql = new StringBuffer();
				if("PORTCODE".equals(type)){
					sql.setLength(0);
					sql.append(" select t.c_port_code,t.c_ass_code from t_p_ab_port t where t.c_port_code in (select * from table(?)) ");
				}
				
				if("ASSCODE".equals(type)){
					sql.setLength(0);
					sql.append(" select t.c_port_code,t.c_ass_code from t_p_ab_port t where t.c_ass_code in (select * from table(?)) ");
				}
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(String.valueOf(codes), conn));
				rs = pstmt.executeQuery();
				if("PORTCODE".equals(type)){
					while(rs.next()){
						result.put(rs.getString("c_port_code"), rs.getString("c_ass_code"));
					}
				}
				
				if("ASSCODE".equals(type)){
					while(rs.next()){
						result.put(rs.getString("c_ass_code"), rs.getString("c_port_code"));
					}
				}
			} catch (Exception ex) {
				logger.error("查询失败：" + ex.getMessage());
			} finally {
				closeResultSetFinal(rs);
				closeStatementFinal(pstmt);
				releaseConnection(conn);
			}

			return result;
	 }
	 
	 	public List<FundAcc> getAccNameAndCaCode() {
		// TODO Auto-generated method stub
		List<FundAcc> pojoList = new ArrayList<FundAcc>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		FundAcc t = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);
			conn = this.loadNewConnection();

			sql = builder.getAccNameAndCaCode();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
            
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}

    /**
	  * STORY #91838 【汇添富基金】账户插入restful接口
	  * @author zmk
	  * @date 2020-09-24
    * @param runningAccs 交易编号
    * @return
    */
	 	public List<FundAcc> getCashAccByRunningAccs(String openAccNo,String openAddr, String openAccName, String dcCode) {
		List<FundAcc> pojoList = new ArrayList<FundAcc>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, builder);			
			conn = this.loadNewConnection();
			sql = builder.getCashAccByRunningAccs();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, openAccNo);
			pstmt.setString(2, openAddr);
			pstmt.setString(3, openAccName);
			pstmt.setString(4, dcCode);
			rs = pstmt.executeQuery();
			FundAcc t = null;
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(FundAcc.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, FundAcc.class, props);
				pojoList.add(t);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	} 	
	
	
	/**
	 * STORY #89090 变更托管人及交易席位，可以维护旧流水的截止日期以及新流水的启用日期
	 * 根据组合代码，账户id获取关联信息中的开始时间，结束时间
	 * @param id
	 * @return
	 */
	public String getTimeByRelaPort(String portCode, String fundAccId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String result = "";
		try {
			conn = this.loadNewConnection();
			sql.append("select D_START,D_END from T_P_AB_PORT_ACC_RELA where c_rela_code = ?  ");
			if(!StringUtil.IsNullOrEmptyT(portCode)){
				sql.append(" and c_port_code = ?");
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, fundAccId);
			if(!StringUtil.IsNullOrEmptyT(portCode)){
				pstmt.setString(2, portCode);
			}
				
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result = DateUtil.dateToString(rs.getDate("D_START"), "yyyy-MM-dd") + "," + DateUtil.dateToString(rs.getDate("D_END"), "yyyy-MM-dd") ;
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
	/**
	 * BUG346545【汇添富基金】【营运平台二期】新增支付产品账户信息后界面选择账户类型查不到新增数据
	 * @param baseBean
	 * @param conn
	 * @return
	 */
	public <T extends BaseBean> String insert(T baseBean, Connection conn)
			throws DataAccessException {
		String cIden = super.insert(baseBean, conn);
		/*STORY #41401 产品信息-产品账户设置，批量关联账户 ,保存时，同时将关联关系保存到 T_P_AB_PORT_RELA
		 * 将前台的代码逻辑移至后台处理*/
		FundAcc fa = (FundAcc)baseBean;
		fa.setId(cIden);
		// 当组合不为空时，保存关联关系到 T_P_AB_PORT_ACC_RELA
		if(!StringUtil.IsNullOrEmpty(fa.getC_PORT_CODE())){
			this.savePortFundRela(fa.getC_PORT_CODE(), cIden, fa.getC_ACCOUNT_TYPE());
		}
		
		if(!StringUtil.IsNullOrEmptyT(fa.getC_ACCOUNT_TYPE())){
			this.saveFundTypeRela(fa.getC_PORT_CODE(),cIden,fa.getC_ACCOUNT_TYPE());
		}
		return cIden;
	}
	
	/**
	 * BUG346545【汇添富基金】【营运平台二期】新增支付产品账户信息后界面选择账户类型查不到新增数据
	 * 将关联关系保存
	 * @param portRela
	 * @param userCode
	 * @param updateTime
	 * @return
	 */
	public boolean savePortFundRela(String portCodes, String fundAccID, String accountType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		String[] ports = portCodes.replace("|", ",").split(",");
		boolean result = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql.append("insert into T_P_AB_PORT_ACC_RELA (C_IDEN,C_PORT_CODE,C_ACCOUNT_TYPE,C_RELA_CODE,C_UPDATE_TIME,C_UPDATE_BY) ");
			sql.append(" values (SEQU_P_AB_PORT_ACC_RELA.nextval,?,?,?,?,?)");
			String userCode = ContextFactory.getContext().getUserCode();
			String timeStamp = YssFun.formatDate(new java.util.Date(), YssCons.YSS_DATETIMEFORMAT);
			pstmt = conn.prepareStatement(sql.toString());
			for(int i=0 ; i<ports.length;i++){
				pstmt.setString(1, ports[i]);
				pstmt.setString(2, accountType);
				pstmt.setString(3, fundAccID);
				pstmt.setString(4, timeStamp);
				pstmt.setString(5, userCode);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			closeStatementFinal(pstmt);
			
			// BUG #200453 账户信息关联到组合存在缺陷 
			// 如果是关联的托管户，则更新组合代码到账户表   当前版本不考虑一个账户既是托管户又是托管户副的情况  
			if(!StringUtil.IsNullOrEmpty(accountType) && ("CPZH_TGH".equals(accountType) || "CPZH_TGH_SEC".equals(accountType))){
				sql.delete(0, sql.length());
				sql.append(" UPDATE T_C_CP_FUND_ACC SET C_PORT_CODE = ? WHERE C_IDEN = ? ");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, portCodes);
				pstmt.setString(2, fundAccID);
				pstmt.executeUpdate();
			}
			conn.commit();
			conn.setAutoCommit(true);
			result = true;
		} catch(Exception ex) {
			throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * BUG346545【汇添富基金】【营运平台二期】新增支付产品账户信息后界面选择账户类型查不到新增数据
	 * 插入账户类型关联数据
	 * @param FundAcc
	 */
	public void updateFundAccById(FundAcc funAcc){
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			sql.append(" update t_cp_fundtype_rela set c_port_code = ? ,c_account_type = ?, c_update_time = to_char(sysdate,'yyyymmdd HH24:mi:ss') , c_update_by = to_char(sysdate,'yyyymmdd HH24:mi:ss') where c_rela_code = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, funAcc.getC_PORT_CODE());
			pstmt.setString(2, funAcc.getC_ACCOUNT_TYPE());
			pstmt.setString(3, funAcc.getId());
		    pstmt.executeUpdate();
		    closeStatementFinal(pstmt);
		    sql.setLength(0);
		    sql.append(" update t_p_ab_port_acc_rela set c_port_code = ? ,c_account_type = ?, c_update_time = to_char(sysdate,'yyyymmdd HH24:mi:ss') , c_update_by = to_char(sysdate,'yyyymmdd HH24:mi:ss') where c_rela_code = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, funAcc.getC_PORT_CODE());
			pstmt.setString(2, funAcc.getC_ACCOUNT_TYPE());
			pstmt.setString(3, funAcc.getId());
		    pstmt.executeUpdate();
		    conn.commit();
		} catch(Exception ex) {
			logger.info("更新数据失败", ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	/**
	 * STORY #89090 变更托管人及交易席位，可以维护旧流水的截止日期以及新流水的启用日期
	 * 根据组合代码，账户id更新关联信息中的开始时间，结束时间
	 * @param id
	 * @return
	 */
	public String updateTimeByRelaPort(HashMap<String, String> paraMap){
		String successed = String.valueOf(false);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String portCode =	paraMap.get("portCode");
	    String fundAccId =	paraMap.get("id");
	    String startDate =	paraMap.get("d_start");
	    String endDate =	paraMap.get("d_end");
		try {
			conn = this.loadNewConnection();
			String userCode = ContextFactory.getContext().getUserCode();
            String timeStamp = YssFun.formatDate(new java.util.Date(), YssCons.YSS_DATETIMEFORMAT);            
			sql.append(" update T_P_AB_PORT_ACC_RELA set d_start = ?,d_end =?, C_UPDATE_TIME = ?, C_UPDATE_BY = ? where c_port_code = ? and c_rela_code = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setDate(1, StringUtil.IsNullOrEmpty(startDate) ? YssFun.toSqlDate(YssFun.parseDate("1900-01-01")):YssFun.toSqlDate(YssFun.parseDate(startDate)) );
			pstmt.setDate(2, StringUtil.IsNullOrEmpty(endDate) ? YssFun.toSqlDate(YssFun.parseDate("1900-01-01")):YssFun.toSqlDate(YssFun.parseDate(endDate)) );
			pstmt.setString(3, timeStamp);
			pstmt.setString(4, userCode);			
			pstmt.setString(5, portCode);
			pstmt.setString(6, fundAccId);
			pstmt.executeUpdate();
			successed = String.valueOf(true);
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return successed;
	}
	
	/**
	 * 将关联关系保存
	 * @param portRela
	 * @param userCode
	 * @param updateTime
	 * @return
	 */
	public Boolean savePortFundRelaWithDate(HashMap<String, String> paraMap) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        StringBuffer sql = new StringBuffer();
        Boolean result = false;
        String portCodes =	paraMap.get("portCode");
        String fundAccID =	paraMap.get("id");
        String accountType =	paraMap.get("c_accout_type");
        String start =	paraMap.get("d_start");
        String end =	paraMap.get("d_end");
        String[] ports = portCodes.replace("|", ",").split(",");
        try {
                conn = this.loadNewConnection();
                conn.setAutoCommit(false);
                sql.append("insert into T_P_AB_PORT_ACC_RELA (C_IDEN,C_PORT_CODE,C_ACCOUNT_TYPE,C_RELA_CODE,D_START,D_END, C_UPDATE_TIME, C_UPDATE_BY) ");
                sql.append(" values (SEQU_P_AB_PORT_ACC_RELA.nextval,?,?,?,?,?,?,?)");
                String userCode = ContextFactory.getContext().getUserCode();
                String timeStamp = YssFun.formatDate(new java.util.Date(), YssCons.YSS_DATETIMEFORMAT);

                pstmt = conn.prepareStatement(sql.toString());
                for(int i=0 ; i<ports.length;i++){
                        pstmt.setString(1, ports[i]);
                        pstmt.setString(2, accountType);
                        pstmt.setString(3, fundAccID);
                        pstmt.setDate(4,StringUtil.IsNullOrEmpty(start) ? YssFun.toSqlDate(YssFun.parseDate("1900-01-01")):YssFun.toSqlDate(YssFun.parseDate(start)));
                        pstmt.setDate(5,StringUtil.IsNullOrEmpty(end) ? YssFun.toSqlDate(YssFun.parseDate("9998-12-31")):YssFun.toSqlDate(YssFun.parseDate(end)));
                        pstmt.setString(6, timeStamp);
                        pstmt.setString(7, userCode);
                        pstmt.addBatch();
                }
                pstmt.executeBatch();
                conn.commit();
                result = true;
        } catch(Exception ex) {
                throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
        } finally {
                closeStatementFinal(pstmt);
                releaseConnection(conn);
                StringUtil.clearStringBuffer(sql);
        }
         return result;
      }
	
	public String getAccListByOpenNoAndOpenAddr(String openNo, String openAddr, String iden) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer buf = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			buf.append("  SELECT C_IDEN,C_OPEN_ACC_NAME,C_CA_CODE,C_DC_CODE,C_OPEN_ADDR,C_OPEN_ACC_NO, " );
			buf.append("        C_ORG_CODE,C_USAGE,C_DESC,C_HOLDER,C_ASS_CODE, " );
			buf.append("        C_PAY_CODE,C_INTER_ORG_CODE,C_ACCOUNT_TYPE,C_PROVINCE, " );
			buf.append("        C_CITY,D_BEGIN,D_END,C_HAVEUSED,C_OPEN_MODE,C_BC_ORG_CODE,C_RUNNING_ACC, " );
			buf.append("        C_BC_LINK_NO,C_CNX,C_SWIFT_CODE,C_BANK_ADDR,C_CREATE_BY,C_CREATE_TIME,C_CHECK_BY, " );
			buf.append("        C_CHECK_TIME,C_UPDATE_BY,C_UPDATE_TIME,N_CHECK_STATE,C_DISCARD_STATUS " );
			buf.append(" FROM T_P_BI_FUND_ACC  ");
			buf.append(" WHERE C_OPEN_ACC_NO = ? AND C_OPEN_ADDR=?  AND C_IDEN <> ?");
			pstmt = conn.prepareStatement(buf.toString());
			pstmt.setString(1, openNo);
			pstmt.setString(2, openAddr);
			pstmt.setString(3, iden);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				return "true";
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return "false";
	}
	
	/**
	 * 新增账户
	 * @param fundAccList
	 * @throws DataAccessException
	 */
	public String insertFundAcc(FundAcc f) throws DataAccessException {
		StringBuffer buf1 = new StringBuffer();
		StringBuffer buf2 = new StringBuffer();
		StringBuffer buf3 = new StringBuffer();
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		Connection conn = null;
		String ciden = "";
		// 插入账户信息
		buf1.append(" INSERT INTO T_P_BI_FUND_ACC ");
		buf1.append(" (C_IDEN,C_OPEN_ACC_NO,C_OPEN_ACC_NAME,C_OPEN_ADDR,C_DC_CODE, ");
		buf1.append(" C_PAY_CODE,C_ACCOUNT_TYPE,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME, ");
		buf1.append(" D_BEGIN,D_END,C_PORT_CODE,C_ASS_CODE,C_ORG_CODE) ");
		buf1.append(" SELECT ");
		buf1.append(" ? as C_IDEN,? as C_OPEN_ACC_NO,? as C_OPEN_ACC_NAME,? as C_OPEN_ADDR, ");
		buf1.append(" ? as C_DC_CODE,? as C_PAY_CODE,? as C_ACCOUNT_TYPE,? as N_CHECK_STATE, ");
		buf1.append(" ? as C_UPDATE_BY,to_char(sysdate,'yyyyMMdd HH24:mi:ss') as C_UPDATE_TIME, ");
		buf1.append(" to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') as D_BEGIN, ");
		buf1.append(" to_date('99981231','yyyymmdd') as D_END,? as C_PORT_CODE,? as C_ASS_CODE,? as C_ORG_CODE");
		buf1.append(" FROM DUAL ");
		buf1.append(" WHERE ");
		buf1.append("  NOT EXISTS (SELECT 1 FROM T_P_BI_FUND_ACC WHERE C_OPEN_ACC_NO = ? AND C_OPEN_ACC_NAME = ? AND C_OPEN_ADDR = ? AND C_DC_CODE = ? )");

		// 插入组合账户关联信息
		buf2.append(" INSERT INTO T_P_AB_PORT_ACC_RELA ");
		buf2.append(" (C_IDEN,C_PORT_CODE,C_RELA_CODE,C_ACCOUNT_TYPE )");
		buf2.append(" SELECT ");
		buf2.append(" SEQU_P_AB_PORT_ACC_RELA.NEXTVAL as C_IDEN,? as C_PORT_CODE,? as C_RELA_CODE, ");
		buf2.append(" ? as C_ACCOUNT_TYPE ");
		buf2.append(" FROM DUAL ");

		buf3.append(" INSERT INTO T_CP_FUNDTYPE_RELA (C_IDEN,C_RELA_CODE,C_ACCOUNT_TYPE,C_UPDATE_BY,C_UPDATE_TIME) ");
		buf3.append(" SELECT SEQU_CP_FUNDTYPE_RELA.NEXTVAL AS C_IDEN, ? AS C_RELA_CODE, ? AS C_ACCOUNT_TYPE,  ? AS C_UPDATE_BY, to_char(sysdate,'yyyymmdd hh24:mi:ss') as C_UPDATE_TIME FROM DUAL  ");

		try {
			conn = this.loadNewConnection();
			pstmt1 = conn.prepareStatement(buf1.toString());
			pstmt2 = conn.prepareStatement(buf2.toString());
			pstmt3 = conn.prepareStatement(buf3.toString());
			ciden = getSequenceNextNumber(conn, getSequanceName("T_P_BI_FUND_ACC"));

			int index = 1;
			pstmt1.setString(index++, ciden);
			pstmt1.setString(index++, f.getC_OPEN_ACC_NO());
			pstmt1.setString(index++, f.getC_OPEN_ACC_NAME());
			pstmt1.setString(index++, f.getC_OPEN_ADDR());
			pstmt1.setString(index++, f.getC_DC_CODE());
			pstmt1.setString(index++, f.getC_PAY_CODE());
			pstmt1.setString(index++, f.getC_ACCOUNT_TYPE());
			pstmt1.setInt(index++, f.getAuditState());
			pstmt1.setString(index++, f.getModifier());
			pstmt1.setString(index++, f.getC_PORT_CODE());
			pstmt1.setString(index++, f.getC_ASS_CODE());
			pstmt1.setString(index++, f.getC_ORG_CODE());

			pstmt1.setString(index++, f.getC_OPEN_ACC_NO());
			pstmt1.setString(index++, f.getC_OPEN_ACC_NAME());
			pstmt1.setString(index++, f.getC_OPEN_ADDR());
			pstmt1.setString(index++, f.getC_DC_CODE());
			//判断是否插入账户信息成功
			int isSuc = pstmt1.executeUpdate();

			if(isSuc > 0) {
				pstmt2.setString(1, f.getC_PORT_CODE());
				pstmt2.setString(2, ciden);
				pstmt2.setString(3, f.getC_ACCOUNT_TYPE());
				if (!StringUtil.IsNullOrEmptyT(f.getC_PORT_CODE())) {
					pstmt2.execute();
				}
				
				pstmt3.setString(1, ciden);
				pstmt3.setString(2, f.getC_ACCOUNT_TYPE());
				pstmt3.setString(3, " ");
				
				if (!StringUtil.IsNullOrEmptyT(f.getC_ACCOUNT_TYPE())) {
					pstmt3.execute();
				}
			}

		} catch (Exception ex) {
			logger.error("插入账户信息失败," + ex.getMessage());
			throw new DataAccessException("插入账户信息失败:" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt1);
			this.closeStatementFinal(pstmt2);
			this.closeStatementFinal(pstmt3);
			this.releaseConnection(conn);
		}
		return ciden;
	}
	
	/**
	 * 更新组合代码
	 * @param types
	 * @return
	 */
	public String addPortCodeRela(String iden,String portCode) {
		PreparedStatement pst = null;
		Connection conn = null;
		String result = "Fail";
		try {
			conn = this.loadNewConnection();
			StringBuffer buf = new StringBuffer();
			// 插入组合账户关联信息
			buf.append(" INSERT INTO T_P_AB_PORT_ACC_RELA ");
			buf.append(" (C_IDEN,C_PORT_CODE,C_RELA_CODE )");
			buf.append(" SELECT ");
			buf.append(" SEQU_P_AB_PORT_ACC_RELA.NEXTVAL as C_IDEN,? as C_PORT_CODE,? as C_RELA_CODE ");
			buf.append(" FROM DUAL ");
			
			pst = conn.prepareStatement(buf.toString());
			pst.setString(1,portCode);
			pst.setString(2,iden);
			pst.executeUpdate();
			result = "SUCC";
		} catch (Exception ex) {
			logger.error("新增账户的产品关联关系失败！", ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 更新账户类型
	 * @param types
	 * @return
	 */
	public String addAccTypeRela(String iden,String accountType) {
		PreparedStatement pst = null;
		Connection conn = null;
		String result = "Fail";
		try {
			conn = this.loadNewConnection();
			StringBuffer buf = new StringBuffer();
			// 插入账户类型关联信息
			buf.append(" INSERT INTO T_CP_FUNDTYPE_RELA (C_IDEN,C_RELA_CODE,C_ACCOUNT_TYPE,C_UPDATE_BY,C_UPDATE_TIME) ");
			buf.append(" SELECT SEQU_CP_FUNDTYPE_RELA.NEXTVAL AS C_IDEN, ? AS C_RELA_CODE, ? AS C_ACCOUNT_TYPE,  ? AS C_UPDATE_BY, to_char(sysdate,'yyyymmdd hh24:mi:ss') as C_UPDATE_TIME FROM DUAL  ");

			pst = conn.prepareStatement(buf.toString());
			pst.setString(1,iden);
			pst.setString(2,accountType);
			pst.setString(3,"GFJJ");
			pst.executeUpdate();
			result = "SUCC";
		} catch (Exception ex) {
			logger.error("新增账户的类型关联关系失败！" , ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 查询指定的账户
	 * 通过四要素查询账户，并判断是否关联产品和账户类型，若未关联则插入产品和账户类型关联关系
	 * @param types
	 * @return
	 */
	public FundAcc getFundAccByGF(Map<String, String> paraMap) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSetTools rstool = null;
		FundAcc fundAcc = null;
		String portCode = "";
		String accountType = "";
		String accAddr = "";
		if (paraMap.containsKey("C_PORT_CODE")) {
			portCode = paraMap.get("C_PORT_CODE");
		}
		
		if (paraMap.containsKey("C_ACCOUNT_TYPE")) {
			accountType = paraMap.get("C_ACCOUNT_TYPE");
		}
		
		if (paraMap.containsKey("C_OPEN_ADDR")) {
			accAddr = paraMap.get("C_OPEN_ADDR");
		}
		
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT a.c_iden,  a.c_ca_code, a.c_dc_code,  a.c_open_addr,  a.c_open_acc_no,  a.c_sys_code, ");
			sql.append(" a.c_usage,a.c_desc, a.n_check_state, a.c_update_by,  a.c_update_time,  a.c_check_by, a.c_check_time, a.c_open_acc_name, ");
			sql.append(" a.d_begin, a.d_end,a.c_org_code, a.c_holder, a.c_ass_code, a.c_haveused, ");
			sql.append(" a.c_inter_org_code,  a.c_bank_code, ");
			sql.append(" (SELECT TO_CLOB(F_CONCAT_ARRAY(CAST (collect(C_PORT_CODE ) AS VARTABLETYPE) ,'|')) FROM T_P_AB_PORT_ACC_RELA TP where TP.C_RELA_CODE = A.C_IDEN) as C_PORT_CODE, ");
			sql.append(" (SELECT TO_CLOB(F_CONCAT_ARRAY(CAST (collect(C_ACCOUNT_TYPE ) AS VARTABLETYPE) ,'|')) FROM t_cp_fundtype_rela rela where rela.C_RELA_CODE = A.C_IDEN) as C_ACCOUNT_TYPE, ");
			sql.append(" a.c_pay_code,a.C_DISCARD_STATUS  FROM T_P_BI_FUND_ACC a   WHERE  a.C_DC_CODE=? and a.C_OPEN_ACC_NO=? ");
			if(paraMap.containsKey("C_OPEN_ACC_NAME"))
			{
				sql.append(" and a.C_OPEN_ACC_NAME=?  ");
			}
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, paraMap.get("C_DC_CODE").toString());
			pst.setString(2, paraMap.get("C_OPEN_ACC_NO").toString());
			if(paraMap.containsKey("C_OPEN_ACC_NAME"))
			{
				pst.setString(3, paraMap.get("C_OPEN_ACC_NAME").toString());			}
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(FundAcc.class.newInstance());
			rs = pst.executeQuery();
			if (rs.next()) {
				fundAcc = rstool.ResultToBeanGeneric(rs, FundAcc.class, props);
			}

			if (fundAcc != null) {
				if (!StringUtil.IsNullOrEmpty(portCode)) {
					if (StringUtil.IsNullOrEmptyT(fundAcc.getC_PORT_CODE()) || !("|" +fundAcc.getC_PORT_CODE() + "|").contains("|"+portCode+"|")) {
						// 若没关联产品，或者没关联这个产品，则增加产品关联关系
						this.addPortCodeRela(fundAcc.getId(), portCode);
					}
					fundAcc.setC_PORT_CODE(portCode);
				}
				
				if (!StringUtil.IsNullOrEmpty(accountType)) {
					if (StringUtil.IsNullOrEmptyT(fundAcc.getC_ACCOUNT_TYPE()) || !("|" +fundAcc.getC_ACCOUNT_TYPE()+ "|").contains("|"+accountType+"|")) {
						// 如果账户没有维护账户类型则插入账户类型
						this.addAccTypeRela(fundAcc.getId(), accountType);
					}
					fundAcc.setC_ACCOUNT_TYPE(accountType);
				}
				
				if (!StringUtil.IsNullOrEmpty(accAddr)) {
					if (StringUtil.IsNullOrEmptyT(fundAcc.getC_OPEN_ADDR())) {
						// 如果账户没有开户行则更新开户行
						this.updateFundAccAddr(fundAcc.getId(), accAddr);
					}
					fundAcc.setC_OPEN_ADDR(accAddr);
				}
			}
		} catch (Exception ex) {
			logger.error("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return fundAcc;
	}

	/**
	 * 修改账户开户行
	 * @param id
	 * @param accAddr
	 */
	private void updateFundAccAddr(String id, String accAddr) {
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE T_P_BI_FUND_ACC SET C_OPEN_ADDR = ? WHERE C_IDEN = ? ");
			pst = conn.prepareStatement(buf.toString());
			pst.setString(1,accAddr);
			pst.setString(2,id);
			pst.executeUpdate();
		} catch (Exception ex) {
			logger.error("修改账户开户行：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	}
}
