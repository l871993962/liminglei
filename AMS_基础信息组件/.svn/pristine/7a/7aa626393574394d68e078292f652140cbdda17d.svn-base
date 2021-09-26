package com.yss.ams.syncdata.business.productinfo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.ams.syncdata.business.productinfo.pojo.PortPdAttribute;
import com.yss.ams.syncdata.business.productinfo.pojo.PortRela;
import com.yss.ams.syncdata.support.modules.base.pojo.SyncPortCls;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.util.YssFun;
/**
 * <产品基本信息>DAO层
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortDao extends GeneralDao {
	private PortSqlBuilder portSqlBuilder;

	public PortDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		portSqlBuilder = (PortSqlBuilder) sqlbuilder;
	}
	
	/**
	 * STORY57889【数据管理】数据同步、同步日志
	 * 插入产品关联机构
	 * @param prLisr
	 * @param conn
	 * @throws Exception 
	 */
	public void insertPortRela(List<PortRela> prLisr, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = "insert into t_p_ab_port_rela (C_IDEN, C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME) values(sequ_p_ab_port_rela.nextval,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for(PortRela pr : prLisr) {
				deletePortRela(pr,conn);
				pstmt.setString(1, pr.getC_PORT_CODE());
				pstmt.setString(2, pr.getC_RELA_TYPE());
				pstmt.setString(3, pr.getC_RELA_CODE());
				pstmt.setString(4, pr.getC_DV_TYPE_CODE());
				pstmt.setInt(5, pr.getAuditState());
				pstmt.setString(6, pr.getModifier());
				pstmt.setString(7, pr.getModifyDate());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeStatementFinal(pstmt);
		}
 	}
	
	/**
	 * 插入分级产品参数
	 * @param portCls
	 * @param conn
	 * @throws Exception
	 */
	public void insertPortCls(List<SyncPortCls> portCls, Connection conn) throws Exception {
		StringBuffer buf = new StringBuffer();
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			buf.append(" merge into T_P_AA_PORT_CLS cls ");
			buf.append(" using (select * from dual ) clsn ");
			buf.append(" on (cls.C_PORT_CODE = ? and cls.C_PORT_CLS_CODE = ? ) ");
			buf.append(" when matched then ");
			buf.append(" update set cls.D_TO_LIST = ? ,cls.D_OFF_LIST = ? ,cls.D_LIQUID_DATE = ? ,N_CHECK_STATE = 0 ");
			buf.append(" when not matched then ");
			buf.append(" insert (C_IDEN,N_CHECK_STATE, C_PORT_CODE, C_PORT_CLS_NAME, C_PORT_CLS_CODE, C_DV_PORT_CLS, D_TO_LIST, D_OFF_LIST, D_LIQUID_DATE) ");
			buf.append(" values (sequ_P_AA_PORT_CLS.nextval,0,?,?,?,?,?,?,?) ");
			sql = buf.toString();
			pstmt = conn.prepareStatement(sql);
			for(SyncPortCls spc : portCls) {
				pstmt.setString(1, spc.getC_PORT_CODE());
				pstmt.setString(2, spc.getC_PORT_CLS_CODE());
				pstmt.setDate(3, DbFun.sqlDate(spc.getD_TO_LIST()));
				pstmt.setDate(4, DbFun.sqlDate(spc.getD_OFF_LIST()));
				pstmt.setDate(5, DbFun.sqlDate(spc.getD_LIQUID_DATE() == null ? YssFun.toDate("9998-12-31"):spc.getD_LIQUID_DATE()));
				pstmt.setString(6, spc.getC_PORT_CODE());
				pstmt.setString(7, spc.getC_PORT_CLS_NAME());
				pstmt.setString(8, spc.getC_PORT_CLS_CODE());
				pstmt.setString(9, spc.getC_DV_PORT_CLS());
				pstmt.setDate(10, DbFun.sqlDate(spc.getD_TO_LIST()));
				pstmt.setDate(11, DbFun.sqlDate(spc.getD_OFF_LIST()));
				pstmt.setDate(12, DbFun.sqlDate(spc.getD_LIQUID_DATE() == null ? YssFun.toDate("9998-12-31"):spc.getD_LIQUID_DATE()));
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			pstmt.clearBatch();
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeStatementFinal(pstmt);
		}
 	}
	
	private void deletePortRela(PortRela pr, Connection conn) throws Exception {
		PreparedStatement pst = null;
		String sql = "delete from t_p_ab_port_rela where c_port_code=? and c_rela_type=? and c_dv_type_code=? and c_rela_code=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, pr.getC_PORT_CODE());
			pst.setString(2, pr.getC_RELA_TYPE());
			pst.setString(3, pr.getC_DV_TYPE_CODE());
			pst.setString(4, pr.getC_RELA_CODE());
			pst.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeStatementFinal(pst);
		}
	}
	
	public Port getPortInfo(String cPortCode) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Port t = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			List<String> paraNameList = new ArrayList<String>();
			paraNameList.add("C_PORT_CODE_EQUAL");
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getQueryConditionSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setObject(1, cPortCode);
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			if (rs.next()) {
				t = (Port) rsTools.ResultToBean(rs, Port.class);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	
	public void insertPortPd(PortPdAttribute port,Connection conn){
		PreparedStatement pstmt = null;
		String sql = "";
		String ciden = "";
		try {
			//插入前先删除
			this.deleteByPortCode(port.getC_PORT_CODE(), conn);
			ciden = getSequenceNextNumber(conn,getSequanceName("T_P_AB_PORT_PD"));
			sql = "insert into T_P_AB_PORT_PD (C_IDEN,C_PORT_CODE,C_DAT_CODE,C_ASSETS_CODE,C_COLLECT_CODE,C_INVEST_CODE,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME,C_DAT_MXCODE)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ciden);
			pstmt.setString(2, port.getC_PORT_CODE());
			pstmt.setString(3, port.getC_DAT_CODE());
			pstmt.setString(4, port.getC_ASSETS_CODE());
			pstmt.setString(5, port.getC_COLLECT_CODE());
			pstmt.setString(6, port.getC_INVEST_CODE());
			pstmt.setInt(7, YssConstant.STATE_AUDIT);
			pstmt.setString(8, port.getModifier());
			pstmt.setString(9, port.getModifyDate());
			pstmt.setString(10, port.getModifier());
			pstmt.setString(11, port.getModifyDate());
			pstmt.setString(12, port.getC_DAT_MXCODE());
			pstmt.executeUpdate();	
		} catch (Exception ex) {
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
    }
	
	/**
	 * modified by yangweijie 2017-7-11 STORY #41439 华泰产品要区分集合、定向、专项
	 * @param port
	 */
	public void deleteByPortCode(String port, Connection conn){
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = "delete from T_P_AB_PORT_PD where C_PORT_CODE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, port);
			pstmt.executeUpdate();	
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);

		}
	}
}
