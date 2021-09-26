package com.yss.uco.elecreco.er.erinfostate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;

public class ErStepStateDao extends GeneralDao{
	
	public ErStepStateDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool,sqlBuilder);
	}
	
	public ErStepState queryByFsn(String fsn){
		ErStepState state = new ErStepState();
		Connection conn = this.loadNewConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" SELECT A.* FROM T_D_ER_STEP_STATE A WHERE A.C_SN = ? AND A.N_CHECK_STATE = 1 ");
			pst = conn.prepareStatement(buf.toString());
			pst.setString(1, fsn);
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				state = (ErStepState) rsTools.ResultToPojoObject(rs, ErStepState.class);
			}
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return state;
	}
	
	public String insertPojo(ErStepState erStatePojo){
		Connection conn = null;
		PreparedStatement pst = null;
		boolean bTrans = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;			
			StringBuffer sb = new StringBuffer();
			sb.append(" insert into T_D_ER_STEP_STATE(C_IDEN,C_SN,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE,C_STATE,C_STEP_DATE,D_DATE,C_ERR) values(SEQU_D_ER_STEP_STATE.NEXTVAL,?,?,?,?,?,?,to_date(?,'YYYY-MM-DD'),?) ");
			pst = conn.prepareStatement(sb.toString());
			pst.setString(1, erStatePojo.getC_SN());
			pst.setString(2, erStatePojo.getC_ASS_CODE());
			pst.setString(3, erStatePojo.getC_FILE_TYPE());
			pst.setString(4, erStatePojo.getC_RPT_TYPE());
			pst.setString(5, erStatePojo.getC_STATE());
			pst.setString(6, erStatePojo.getC_STEP_DATE());
			pst.setString(7, erStatePojo.getD_DATE());
			pst.setString(8, erStatePojo.getErrInfo());
			pst.executeUpdate();
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("插入报文历史信息失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pst);
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
		return null;
	}
	
	public List<BasePojo> queryListByTypes(HashMap<String, String> paraMap){
		List<BasePojo> list = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("  SELECT C_SN,C_ASS_CODE,C_STATE,D_DATE,C_FILE_TYPE,C_RPT_TYPE,C_STEP_DATE,C_ERR FROM   t_d_er_step_state where C_SN = ? AND C_ASS_CODE = ? AND C_STATE IN (SELECT * FROM TABLE(?)) order by C_STEP_DATE DESC ");
			pst = conn.prepareStatement(sb.toString());
			pst.setString(1, paraMap.get("C_SN"));
			pst.setString(2, paraMap.get("C_ASS_CODE"));
			pst.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(String
					.valueOf(paraMap.get("ARRAY_C_STATE")),conn));
			rs = pst.executeQuery();
			while (rs.next()) {
				ErStepState state = new ErStepState();
				state.setC_SN(rs.getString("C_SN"));
				state.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
				state.setC_STATE(rs.getString("C_STATE"));
				state.setD_DATE(rs.getString("D_DATE"));
				state.setC_FILE_TYPE(rs.getString("C_FILE_TYPE"));
				state.setC_RPT_TYPE(rs.getString("C_RPT_TYPE"));
				state.setC_STEP_DATE(rs.getString("C_STEP_DATE"));
				state.setErrInfo(rs.getString("C_ERR"));
				list.add(state);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询报文历史信息失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
}
