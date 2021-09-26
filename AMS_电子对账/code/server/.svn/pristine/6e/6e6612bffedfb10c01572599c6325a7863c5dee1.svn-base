package com.yss.uco.elecreco.er.erdblgz.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;

public class ErDblgzbDao extends GeneralDao {

	public ErDblgzbDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	@Override
	protected BasePojo setResultSet(ResultSetTools rsTools, ResultSet rs, Class<?> clazz, 
			PropertyDescriptor[] props) throws Exception{
		BasePojo bean = rsTools.ResultToBean(rs, clazz, props);
		ErDblgzb gz = (ErDblgzb) bean;
		String type = gz.getC_NAV_TYPE();
		if(!StringUtil.IsNullOrEmpty(type) && ("TOTAL".equalsIgnoreCase(type) || "TOTAL_ALL".equalsIgnoreCase(type)))
		{
			String name = gz.getC_ZB_NAME();
			if(!StringUtil.IsNullOrEmpty(gz.getC_PORT_CLS_CODE()))
			{
				name = name + "-" + gz.getC_PORT_CLS_CODE();
			}
			gz.setC_KM_CODE(gz.getC_KM_CODE() + "(" + name + ")");
		}
		
		return gz;
	}

	/**
	 * 20171121 wlx BUG180889【中国银行】电子对账生成后已生成记录中指标项重复
	 * @param paraMap
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryGzbData(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		return super.queryByCondition(paraMap, clazz);
	}
	/**
	 * 获取 HashMap<指标代码, 指标名称> 
	 * wlx 2016-12-2 BUG #146679::嘉实基金电子对账估值指标显示前台修改
	 * @param paraMap2 条件
	 * @param 
	 * @return
	 */
	public HashMap<String, String> queryElecRelaMap(HashMap<String, Object> paraMap2){
		HashMap<String, String> paraMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			conn = loadNewConnection();
			StringBuffer buf = new StringBuffer();
			buf.append("  SELECT A.C_ZB_CODE,A.C_ZB_NAME FROM T_Z_BI_RELA A WHERE A.C_DZ_CODE ='1013' ");
			psm = conn.prepareStatement(buf.toString());
			rs = psm.executeQuery();
			while (rs.next()) {
				if(!paraMap.containsKey(rs.getString("C_ZB_CODE"))){
						paraMap.put(rs.getString("C_ZB_CODE"), rs.getString("C_ZB_NAME"));
				}
			}
		} catch (Exception ex) {
			logger.error("查询对账指标出错:" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(psm);
			releaseConnection(conn);
		}
		return paraMap;
	}
	
	public void insertDatas(List<ErDblgzb> list,Connection conn) throws DataAccessException{
		String sql = "";
		PreparedStatement ptmt = null;
		int batchSize = 1000;
		int count = 0;
		try{
			sql = ((ErDblgzbSqlBuilder)sqlbuilder).getInsertSql();
			ptmt = conn.prepareStatement(sql);
			for(ErDblgzb gz : list)
			{
				int index = 1;
				ptmt.setString(index++,gz.getC_FILE_TYPE());
				ptmt.setString(index++,gz.getC_ASS_CODE());
				ptmt.setString(index++,gz.getC_RPT_TYPE());
				ptmt.setDate(index++,YssFun.toSqlDate(gz.getD_START_DATE()));
				ptmt.setDate(index++,YssFun.toSqlDate(gz.getD_END_DATE()));
				ptmt.setString(index++,gz.getC_KM_CODE());
				ptmt.setString(index++,gz.getC_KM_CODE_P());
				ptmt.setString(index++,gz.getC_KM_NAME());
				ptmt.setBigDecimal(index++,gz.getN_VA_PRICE());
				ptmt.setInt(index++,gz.getN_QUOT_LOGO());
				ptmt.setBigDecimal(index++,gz.getN_AMOUNT());
				ptmt.setBigDecimal(index++,gz.getN_ORIG_COST());
				ptmt.setBigDecimal(index++,gz.getN_PORT_COST());
				ptmt.setBigDecimal(index++,gz.getN_ORIG_MV());
				ptmt.setBigDecimal(index++,gz.getN_PORT_MV());
				ptmt.setBigDecimal(index++,gz.getN_ORIG_IV());
				ptmt.setBigDecimal(index++,gz.getN_PORT_IV());
				ptmt.setBigDecimal(index++,gz.getN_CB_JZ_BL());
				ptmt.setBigDecimal(index++,gz.getN_SZ_JZ_BL());
				ptmt.setInt(index++,gz.getN_DETAIL());
				ptmt.setString(index++,gz.getC_SN());
				ptmt.setString(index++,gz.getC_DV_ER_WAY());
				ptmt.setString(index++,gz.getC_NAV_TYPE());
				ptmt.setString(index++,gz.getC_PORT_CLS_CODE());
				ptmt.setString(index++,gz.getC_ZB_NAME());
				ptmt.setInt(index++,gz.getN_WAY());
				ptmt.addBatch();
				if(++count % batchSize == 0) {
					ptmt.executeBatch();
				}
			}
			ptmt.executeBatch();
		}catch(Exception ex){
			throw new DataAccessException("插入双估值表数据出错",ex);
		}finally{
			this.closeStatementFinal(ptmt);
		}
		
	}
}
