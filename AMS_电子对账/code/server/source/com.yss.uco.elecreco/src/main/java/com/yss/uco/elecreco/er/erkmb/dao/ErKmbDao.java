package com.yss.uco.elecreco.er.erkmb.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;

public class ErKmbDao extends GeneralDao {

	public ErKmbDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}


	/**
	 * Fixed by huangsq 20170905 重构查询方法，添加关联状态表
	 * @param paraMap
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			sql = sqlbuilder.getQueryConditionSql(paraNameList);
			
			/*- Start Added by huangsq 20170905 添加关联状态表 */
			if (showState != null && showState.isShow() && !showState.getIsPlm()) {
				sql = buildStateSql(sql, this.sqlbuilder.getTableName(this.dbNameResolver));
			} else if(showState != null && showState.getIsPlm()){
				sql = buildRelaSql(sql, this.sqlbuilder.getTableName(this.dbNameResolver), showState);
			}
			/*- End Added by huangsq 添加关联状态表 */

			pstmt = conn.prepareStatement(sql);
			int fetchSize = pstmt.getFetchSize();
			pstmt.setFetchSize(1000);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue = null;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
					if ("N_AUTHORG".equals(valueFieldName)) {
						continue;
					}

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (java.util.Date.class.equals(paraValue)) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else if (Timestamp.class.equals(paraValue)) {
							Timestamp dateValue = new Timestamp(
									((java.util.Date) paraValue).getTime());
							pstmt.setTimestamp(index, dateValue);
						}else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}

			rs = pstmt.executeQuery();
			BasePojo pojo = (BasePojo) clazz.newInstance();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, clazz, props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,t);
				pojoList.add(t);
			}
			pstmt.setFetchSize(fetchSize);
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
	
	protected BasePojo setResultSet(ResultSetTools rsTools, ResultSet rs, Class<?> clazz, 
			PropertyDescriptor[] props) throws Exception{
		ErKmb km = new ErKmb();
		km.setId(rs.getString("C_IDEN"));
		km.setC_SN(rs.getString("C_SN"));
		km.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
		km.setC_FILE_TYPE(rs.getString("C_FILE_TYPE"));
		km.setC_RPT_TYPE(rs.getString("C_RPT_TYPE"));
		km.setD_START_DATE(DateUtil.dateToString(new Date(rs.getDate("D_START_DATE").getTime()), DateUtil.LONG_DATE_FORMAT));
		km.setD_END_DATE(DateUtil.dateToString(new Date(rs.getDate("D_END_DATE").getTime()), DateUtil.LONG_DATE_FORMAT));
		km.setC_KM_CODE(rs.getString("C_KM_CODE"));
		km.setC_KM_NAME(rs.getString("C_KM_NAME"));
		km.setC_KM_LEVEL(rs.getString("C_KM_LEVEL"));
		km.setC_KM_CODE_P(rs.getString("C_KM_CODE_P"));
		km.setD_DATE(DateUtil.dateToString(new Date(rs.getDate("D_DATE").getTime()), DateUtil.LONG_DATE_FORMAT));
		km.setC_DV_KM_CLS(rs.getString("C_DV_KM_CLS"));
		km.setC_DV_ER_WAY(rs.getString("C_DV_ER_WAY"));
		km.setC_DV_JD_WAY(rs.getString("C_DV_JD_WAY"));
		km.setN_DETAIL(rs.getInt("N_DETAIL"));
		return km;
	}
	
}
