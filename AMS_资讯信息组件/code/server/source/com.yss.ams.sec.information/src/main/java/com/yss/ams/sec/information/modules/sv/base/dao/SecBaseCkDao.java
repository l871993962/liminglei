package com.yss.ams.sec.information.modules.sv.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 存放品种信息 dao
 * @author 马向峰
 *
 */
public class SecBaseCkDao extends GeneralDao {

	public SecBaseCkDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * add by zhd 2016-09-07
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 查询实际所属证券
	 * @return
	 */
	public List<BasePojo> queryForSjsszq() {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ResultSetTools rsTools = null;
		SecBaseCkSqlBuilder sqlbuilder = null;
		BasePojo pojo = null;
		try {
			sqlbuilder = new SecBaseCkSqlBuilder();
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			sql = sqlbuilder.getQueryForSjsszq();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pojo = rsTools.ResultToBean(rs, SecBase.class);
				pojoList.add(pojo);
			}
		} catch (Exception ex) {
			logger.log(ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	
	/**
	 * add by zhd 2016-09-09
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 插入
	 */
	public <T extends BaseBean> List<String> insert(List<T> pojoList) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		ResultSetTools rsTools = null;
		SecBaseCkSqlBuilder sqlbuilder = null;
		String sjsszq = null;
		List<String> cidenList = new ArrayList<String>();
		try {
			conn = this.loadNewConnection();
			sqlbuilder = new SecBaseCkSqlBuilder();
			rsTools = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			for(BaseBean pojo : pojoList) {
				sjsszq = ((SecBase)pojo).getC_SJSSZQ();
				if(sjsszq.isEmpty() || sjsszq.trim().equals("")) {
					String ciden = insert(pojo, conn);
					cidenList.add(ciden);
					continue;
				}
				sql = sqlbuilder.getQueryBySjsszq();
				pst = conn.prepareStatement(sql);
				pst.setString(1, sjsszq);
				rs = pst.executeQuery();
				SecBase pojo2 = null;
				if(rs.next()) {
					pojo2 = (SecBase)rsTools.ResultToBean(rs, SecBase.class);
					pojo2.setC_SEC_CODE(((SecBase)pojo).getC_SEC_CODE());
					pojo2.setC_SEC_NAME(((SecBase)pojo).getC_SEC_NAME());
					pojo2.setC_SEC_MKT_CODE(((SecBase)pojo).getC_SEC_MKT_CODE());
					pojo2.setC_MKT_CODE(((SecBase)pojo).getC_MKT_CODE());
					pojo2.setC_SJSSZQ(((SecBase)pojo).getC_SJSSZQ());
					pojo2.setAuditDate(((SecBase)pojo).getAuditDate());
					pojo2.setAuditState(((SecBase)pojo).getAuditState());
					pojo2.setModifier(((SecBase)pojo).getModifier());
					pojo2.setModifyDate(((SecBase)pojo).getModifyDate());
					pojo2.setOperator(((SecBase)pojo).getOperator());
					pojo = pojo2;
				}
				String ciden = insert(pojo, conn);
				cidenList.add(ciden);
				this.closeResultSetFinal(rs);
				this.closeStatementFinal(pst);
			}
		} catch (Exception ex) {
			logger.log(ex.getMessage(), ex);
			//add by gongyue 20170522
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return cidenList;
	}

	/**
	 * add by zhd 2016-09-09
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 更新
	 */
	public <T extends BasePojo> void updateById(List<T> pojoList) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		PreparedStatement pst1 = null;
		ResultSet rs1 = null;
		String sql = null;
		ResultSetTools rsTools = null;
		SecBaseCkSqlBuilder sqlbuilder = null;
		String sjsszq = null;
		try {
			conn = this.loadNewConnection();
			sqlbuilder = new SecBaseCkSqlBuilder();
			rsTools = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			for(BasePojo pojo : pojoList) {
				sjsszq = ((SecBase)pojo).getC_SJSSZQ();
				if(sjsszq.isEmpty() || sjsszq.trim().equals("")) {
					sql = sqlbuilder.getQueryBySecCode();
					pst = conn.prepareStatement(sql);
					pst.setString(1, ((SecBase)pojo).getC_SEC_CODE());
					rs = pst.executeQuery();
					SecBase pojo3 = null;
					while(rs.next()) {
						pojo3 = (SecBase)rsTools.ResultToBean(rs, SecBase.class);
						SecBase pojo4 = (SecBase)pojo;
						//预报存要改动的原真实证券信息
						String secCode = pojo4.getC_SEC_CODE();
						String secName = pojo4.getC_SEC_NAME();
						String secMktCode = pojo4.getC_SEC_MKT_CODE();
						String mktCode = pojo4.getC_MKT_CODE();
						String cSjsszq = pojo4.getC_SJSSZQ();
						String id = pojo4.getId();
						String auditDate = pojo4.getAuditDate();
						int auditState = pojo4.getAuditState();
						String modifier = pojo4.getModifier();
						String modifyDate = pojo4.getModifyDate();
						String operator = pojo4.getOperator();
						//复制虚拟证券不变的信息
						pojo4.setC_SEC_CODE(pojo3.getC_SEC_CODE());
						pojo4.setC_SEC_NAME(pojo3.getC_SEC_NAME());
						pojo4.setC_SEC_MKT_CODE(pojo3.getC_SEC_MKT_CODE());
						pojo4.setC_MKT_CODE(pojo3.getC_MKT_CODE());
						pojo4.setC_SJSSZQ(pojo3.getC_SJSSZQ());
						pojo4.setId(pojo3.getId());
						pojo4.setAuditDate(pojo3.getAuditDate());
						pojo4.setAuditState(pojo3.getAuditState());
						pojo4.setModifier(pojo3.getModifier());
						pojo4.setModifyDate(pojo3.getModifyDate());
						pojo4.setOperator(pojo3.getOperator());
						pojo3 = pojo4;
						//更新虚拟证券
						super.updateById(pojo3);
						//还原原先的真实证券信息
						pojo3.setC_SEC_CODE(secCode);
						pojo3.setC_SEC_NAME(secName);
						pojo3.setC_SEC_MKT_CODE(secMktCode);
						pojo3.setC_MKT_CODE(mktCode);
						pojo3.setC_SJSSZQ(cSjsszq);
						pojo3.setId(id);
						pojo3.setAuditDate(auditDate);
						pojo3.setAuditState(auditState);
						pojo3.setModifier(modifier);
						pojo3.setModifyDate(modifyDate);
						pojo3.setOperator(operator);
						pojo = pojo3;
					}
					continue;
				}
				sql = sqlbuilder.getQueryBySjsszq();
				pst1 = conn.prepareStatement(sql);
				pst1.setString(1, sjsszq);
				rs1 = pst1.executeQuery();
				SecBase pojo2 = null;
				if(rs1.next()) {
					pojo2 = (SecBase)rsTools.ResultToBean(rs1, SecBase.class);
					pojo2.setC_SEC_CODE(((SecBase)pojo).getC_SEC_CODE());
					pojo2.setC_SEC_NAME(((SecBase)pojo).getC_SEC_NAME());
					pojo2.setC_SEC_MKT_CODE(((SecBase)pojo).getC_SEC_MKT_CODE());
					pojo2.setC_MKT_CODE(((SecBase)pojo).getC_MKT_CODE());
					pojo2.setC_SJSSZQ(((SecBase)pojo).getC_SJSSZQ());
					pojo2.setAuditDate(((SecBase)pojo).getAuditDate());
					pojo2.setAuditState(((SecBase)pojo).getAuditState());
					pojo2.setModifier(((SecBase)pojo).getModifier());
					pojo2.setModifyDate(((SecBase)pojo).getModifyDate());
					pojo2.setOperator(((SecBase)pojo).getOperator());
					pojo = pojo2;
				}
				this.closeResultSetFinal(rs);
				this.closeResultSetFinal(rs1);
				this.closeStatementFinal(pst);
				this.closeStatementFinal(pst1);
			}
			super.updateById(pojoList);
		} catch (Exception ex) {
			logger.log(ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeResultSetFinal(rs1);
			this.closeStatementFinal(pst);
			this.closeStatementFinal(pst1);
			this.releaseConnection(conn);
		}
	}

}
