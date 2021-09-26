package com.yss.ams.base.information.modules.bi.accountType.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.ams.base.information.support.bi.dactype.pojo.AccountType;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountTypeTree;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
/**
 * @ClassName HkTypeUnifypayDao
 * @Description 款项类型设置
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public class AccountTypeDao extends GeneralDao {

	public AccountTypeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	/*START 数据服务方法*/
	public List<AccountType> getAllDataList() throws Exception {
		List<AccountType> pojoList = new ArrayList<AccountType>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccountTypeSqlBuilder dsServiceBuilder = null;
		AccountType t = null;
		try {
			dsServiceBuilder = new AccountTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(AccountType.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccountType.class,props);
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

	public AccountType getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccountTypeSqlBuilder dsServiceBuilder = null;
		AccountType t = null;
		try {
			dsServiceBuilder = new AccountTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccountType.class);
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

	public List<AccountType> getDataListByTypes(String[] types) throws Exception {
		List<AccountType> pojoList = new ArrayList<AccountType>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccountTypeSqlBuilder dsServiceBuilder = null;
		AccountType t = null;
		try {
			dsServiceBuilder = new AccountTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccountType.class);
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

	public List<AccountType> getDataListByKeys(String[] keys) throws Exception {
		List<AccountType> pojoList = new ArrayList<AccountType>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = null;
		ResultSetTools rsTools = null;

		AccountTypeSqlBuilder dsServiceBuilder = null;
		AccountType t = null;
		try {
			dsServiceBuilder = new AccountTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getDataByKeys();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccountType.class);
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

	
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccountTypeSqlBuilder dsServiceBuilder = null;
		AccountType t = null;
		try {
			dsServiceBuilder = new AccountTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccountType.class);
				keyValueMap.put(t.getC_DAC_CODE(), t.getC_DAC_NAME());
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
	
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AccountTypeSqlBuilder dsServiceBuilder = null;
		AccountType t = null;
		try {
			Set<String> listarry = new HashSet<String>();
			//BUG #366090 30.7主干-多账户类型set界面与list界面展示不一致
			int i = 0;
			for (String str : listKey) {
				if(str.contains("|")) {
					String[] strar = str.split("\\|");
					for(String st : strar) {
						listarry.add(st);
					}
				}else {
					listarry.add(str);
				}
			}
		
			String[] strArr = new String[listarry.size()];
			for (String str : listarry) {
				strArr[i++] = str ;
			}
			
			dsServiceBuilder = new AccountTypeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSqlByKeys();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr,conn));

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AccountType.class);
				keyValueMap.put(t.getC_DAC_CODE(), t.getC_DAC_NAME());
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
	/*END 数据服务方法*/
	
	
	
	/**
	 * 根据子节点取父节点
	 * zhangyongzhao
	 * 2017年11月14日
	 */
	public List<AccountTypeTree> accordingChildrenGetTree(List<String> codes,String type) {
		List<AccountTypeTree> list = new ArrayList<AccountTypeTree>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = null;
		//ResultSetTools rsTools = null;
//		AccountType t = null;
		AccountTypeSqlBuilder dsServiceBuilder = null;
		try {
			String[] strArr = new String[codes.size()];
			codes.toArray(strArr);
			conn = this.loadNewConnection();
			dsServiceBuilder = new AccountTypeSqlBuilder();
			
			//rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			sql = dsServiceBuilder.getTree();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr, conn));
			pstmt.setString(2, type);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AccountTypeTree accountTypeTree = new AccountTypeTree();
				accountTypeTree.setC_DAC_CODE(rs.getString("c_DAC_CODE"));
				accountTypeTree.setC_DAC_CODE_P(rs.getString("c_DAC_CODE_P"));
				accountTypeTree.setC_DAC_NAME(rs.getString("c_DAC_NAME"));
				accountTypeTree.setC_DAC_TYPE(rs.getString("c_DAC_TYPE"));
				accountTypeTree.setC_DV_STATE(rs.getString("c_DV_STATE"));
				accountTypeTree.setN_ORDER(rs.getString("n_ORDER"));
				accountTypeTree.setConnect_by_isleaf(rs.getInt("connect_by_isleaf"));
				accountTypeTree.setLevel(rs.getInt("level"));
				list.add(accountTypeTree);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * 根据code查询数据
	 * zhangyongzhao
	 * 2018年07月19日
	 */
	public List<AccountTypeTree> getAccByCodeData(List<String> codes) {
		List<AccountTypeTree> list = new ArrayList<AccountTypeTree>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		AccountTypeSqlBuilder dsServiceBuilder = null;
		try {
			String[] strArr = new String[codes.size()];
			codes.toArray(strArr);
			conn = this.loadNewConnection();
			dsServiceBuilder = new AccountTypeSqlBuilder();
			sql = dsServiceBuilder.getAccByCodeSql();
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, accType);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr, conn));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AccountTypeTree accountTypeTree = new AccountTypeTree();
				accountTypeTree.setId(rs.getString("c_DAC_CODE"));
				accountTypeTree.setC_DAC_CODE(rs.getString("c_DAC_CODE"));
				accountTypeTree.setC_DAC_CODE_P(rs.getString("c_DAC_CODE_P"));
				accountTypeTree.setC_DAC_NAME(rs.getString("c_DAC_NAME"));
				accountTypeTree.setC_DAC_TYPE(rs.getString("c_DAC_TYPE"));
				accountTypeTree.setC_DV_STATE(rs.getString("c_DV_STATE"));
				accountTypeTree.setN_ORDER(rs.getString("n_ORDER"));
				accountTypeTree.setpId(rs.getString("C_DAC_CODE_P"));
				accountTypeTree.setName(rs.getString("C_DAC_NAME"));
				accountTypeTree.setN_ISACCOUNT(1);
				list.add(accountTypeTree);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * 根据父节点获取所有子账户
	 * @param parentNode
	 * @return
	 */
	public List<AccountType> getSubAccountsByParent(String parentNode) {
		List<AccountType> list = new ArrayList<AccountType>();
		AccountTypeSqlBuilder dsServiceBuilder = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = this.loadNewConnection();
			dsServiceBuilder = new AccountTypeSqlBuilder();
			sql = dsServiceBuilder.queryAccoutTypeByParent();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, parentNode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AccountType accountType = new AccountType();
				accountType.setId(rs.getString("c_IDEN"));
				accountType.setC_DAC_CODE(rs.getString("c_DAC_CODE"));
				accountType.setC_DAC_NAME(rs.getString("c_DAC_NAME"));
				accountType.setC_DAC_CODE_P(rs.getString("c_DAC_CODE_P"));
				accountType.setN_ORDER(rs.getString("n_ORDER"));
				accountType.setC_DAC_TYPE(rs.getString("c_DAC_TYPE"));
				accountType.setC_DV_STATE(rs.getString("c_DV_STATE"));
				list.add(accountType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		
		return list;
	}
}
