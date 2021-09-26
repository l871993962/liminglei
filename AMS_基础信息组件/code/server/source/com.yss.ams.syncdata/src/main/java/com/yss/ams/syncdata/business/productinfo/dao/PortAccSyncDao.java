package com.yss.ams.syncdata.business.productinfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.syncdata.business.productinfo.pojo.PortAccSync;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.context.ContextFactory;

/**
 * STORY #58468 生命周期系统中的账户信息数据同步至基础组件的银行账户信息
 * @author lenovo
 *
 */
public class PortAccSyncDao extends GeneralDao {

	private PortAccSyncSqlBuilder builder = null;
	private String openAddr = "C_OPEN_ADDR";
	
	public PortAccSyncDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		builder = (PortAccSyncSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 设置要维护的表名
	 * @param fundTableName
	 * @param relaTableName
	 */
	public void setTableName(String fundTableName, String relaTableName) {
		builder.setTableName(fundTableName, relaTableName);
	}
	
	/**
	 * 设置组合代码为空
	 * @param pojo
	 */
	private void setnullAttr(PortAccSync pojo) {
		pojo.setC_PORT_CODE(null);
	}
	
	@Override
	public <T extends BaseBean> String insert(T baseBean, Connection conn)
			throws DataAccessException {
		setnullAttr((PortAccSync) baseBean);
		String cIden = super.insert(baseBean, conn);
		return cIden;
	}

	@Override
	public <T extends BasePojo> void updateById(T basePojo, Connection conn)
			throws DataAccessException {
		setnullAttr((PortAccSync) basePojo);
		super.updateById(basePojo, conn);
	}
	
	/**
	 * 查询指定账户类型的账户
	 * @param paraMap
	 * @return
	 */
	public PortAccSync getFundAccByInfo(HashMap<String, Object> paraMap) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSetTools rstool = null;
		PortAccSync fundAcc = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			String sql = builder.getQueryFundAccSql(paraMap);
			pst = conn.prepareStatement(sql);
			pst.setString(1, paraMap.get("C_DC_CODE").toString());
			pst.setString(2, paraMap.get("C_OPEN_ACC_NAME").toString());
			pst.setString(3, paraMap.get("C_OPEN_ACC_NO").toString());
			if(paraMap.containsKey(openAddr)){
				pst.setString(4, paraMap.get(openAddr).toString());
			}
			rs = pst.executeQuery();
			if (rs.next()) {
				fundAcc = rstool.ResultToBeanGeneric(rs, PortAccSync.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询指定账户类型的账户出错：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return fundAcc;
	}
	
	/**
	 * 根据关联code 和 组合 删除关联关系  不新建事务
	 * @param relaCode
	 * @param port
	 * @param conn
	 * @return
	 */
	public boolean deletePortFundRela(String relaCode, String port, Connection conn) {
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			String sql = builder.getDeletePortFundRelaSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, relaCode);
			pstmt.setString(2, port);
			int falg = pstmt.executeUpdate();
			if (falg > 0) {
				result = true;
			}
		} catch (Exception ex) {
			throw new DataAccessException("删除账号组合关联关系失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
		return result;
	}
	
	/**
	 * 插入账户类型关联数据
	 * @param portCode
	 * @param cIden
	 * @param accountTypes
	 */
	public void saveFundTypeRela(String cIden,String accountTypes,Connection conn){
		// 先删除后插入
		this.deleteTypeRelaInfo(cIden,conn);
		
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		String[] accounts = accountTypes.split("\\|");
		try {
			sql.append("insert into t_cp_fundtype_rela (C_IDEN,C_PORT_CODE,C_RELA_CODE,C_ACCOUNT_TYPE,C_UPDATE_TIME,C_UPDATE_BY) ");
			sql.append(" values (SEQU_cp_fundtype_rela.nextval,?,?,?,to_char(sysdate,'yyyymmdd HH24:mi:ss'),?)");
			pstmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < accounts.length; i++) {
				pstmt.setString(1, cIden);
				pstmt.setString(2, accounts[i]);
				pstmt.setString(3, ContextFactory.getContext().getUserCode());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception ex) {
			throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * 删除账户类型关联数据
	 * @param fa
	 */
	public void deleteTypeRelaInfo (String relaCode,Connection conn){
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append("delete t_cp_fundtype_rela where c_rela_code = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, relaCode);
			pstmt.execute();
		} catch(Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * 保存账户组合关联关系  查询关联表 若不存在关联关系则插入
	 * @param relaCode
	 * @param port
	 * @param conn
	 * @return
	 */
	public boolean insertPortFundRela(String relaCode, String port, Connection conn) {
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			String sql = builder.getInsertPortFundRelaSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, port);
			pstmt.setString(2, relaCode);
			pstmt.setString(3, port);
			int falg = pstmt.executeUpdate();
			if (falg > 0) {
				result = true;
			}
		} catch (Exception ex) {
			throw new DataAccessException("保存账号组合关联关系失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
		return result;
	}
	
	/**
	 * 根据关联code更新账户类型
	 * @param relaCode
	 * @param newPort
	 * @param oldPort
	 * @param oldDvTypeCode
	 * @param conn
	 * @return
	 */
	public boolean updatePortFundRela(String relaCode, String newPort, String oldPort, String oldDvTypeCode, Connection conn) {
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			String sql = builder.getUpdatePortFundRelaSql(oldDvTypeCode);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPort);
			pstmt.setString(2, relaCode);
			pstmt.setString(3, oldPort);
			int falg = pstmt.executeUpdate();
			if (falg > 0) {
				result = true;
			}
		} catch (Exception ex) {
			throw new DataAccessException("更新账号组合关联关系失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
		return result;
	}
	
	/**
	 * 更新老账户信息表关联字段
	 * @param idCode
	 * @param conn
	 * @return
	 */
	public boolean updateFundAccRela(String idCode, Connection conn) {
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			String sql = builder.updateFundAccRela();
			if ("".equals(sql)) {
				return result;
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idCode);
			int falg = pstmt.executeUpdate();
			if (falg > 0) {
				result = true;
			}
		} catch (Exception ex) {
			throw new DataAccessException("更新账户信息表关联字段失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
		return result;
	}
}
