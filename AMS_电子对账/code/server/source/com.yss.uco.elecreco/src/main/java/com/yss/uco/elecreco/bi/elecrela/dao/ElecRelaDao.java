package com.yss.uco.elecreco.bi.elecrela.dao;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.er.generate.util.StatsUtil;
import com.yss.uco.elecreco.support.bean.ElecRela;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;

public class ElecRelaDao extends GeneralDao {

	private ElecRelaSqlBuilder sqlBuilder;
	public ElecRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ElecRelaSqlBuilder) sqlBuilder;
	}
	/**
	 * BUG229285【融通基金】电子对账指标关联报错
	 */
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);

			sql = sqlbuilder.getQueryConditionSql(paraNameList);
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
//			long begin = System.currentTimeMillis();
//			System.out.println("打开结果集当前时间戳:" + begin);
			rs = pstmt.executeQuery();
//			long mid = System.currentTimeMillis();
//			System.out.println("打开结果集完成，花费时间:" + (mid - begin) + "ms");
			
			BasePojo pojo = (BasePojo) clazz.newInstance();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools, rs, clazz, props);
				((ElecRela)t).setC_REPORT_NAME(rs.getString("C_REPORT_NAME"));
				getConvertKey(props, t);
				pojoList.add(t);
			}

//			long end = System.currentTimeMillis();
//			System.out.println("结果集滚动完成，花费时间： " + (end - mid) + "ms");

		} catch (Exception ex) {
			logger.error("查询失败：", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}

	public List<BasePojo> getDataList(){
		List<BasePojo> rlsList = new ArrayList<BasePojo>();
		Connection conn = this.loadNewConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = sqlBuilder.getDataListSql();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				rlsList.add((BasePojo)rsTools.ResultToPojoObject(rs, ElecRela.class));
			}
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return rlsList;
	}
	
	public List<BasePojo> getDataListByName(List<String> paraList){
		List<BasePojo> rlsList = new ArrayList<BasePojo>();
		Connection conn = this.loadNewConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql;
		try {
			if (null != paraList && paraList.size()>0) {
				sql = sqlBuilder.getDataListByNameSql();
			}else {
				sql = sqlBuilder.getDataListSql();
			}
			pst = conn.prepareStatement(sql);
			if (null != paraList && paraList.size()>0) {
				pst.setString(1, paraList.get(0));
			}
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				rlsList.add((BasePojo)rsTools.ResultToPojoObject(rs, ElecRela.class));
			}
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return rlsList;
	}
	

	/**
	 * 根据估值指标代码获取电子对账指标代码
	 * 
	 * @param paras
	 * @return Map{key:电子对账指标代码，value:估值指标项代码}
	 */
	public Map<String, String> getZbCodeByKeyCode(String paras) {
		Map<String,String> res = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql;
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getZbCodeByKeyCodeSql();
			pst = conn.prepareStatement(sql);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paras,conn));
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				ElecRela rela = (ElecRela) rsTools.ResultToPojoObject(rs, ElecRela.class);
				res.put(rela.getC_ZB_CODE(),rela.getC_DV_ZB_CODE());
			}
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return res;
	}
	
	/**
	 * 获取电子对账关联指标代码
	 * add by qiantao STORY #83025 产品估值参数控制实收资本小数位 
	 * @param dzCode
	 * @param zbCode
	 * @return
	 */
	public List<String> getRealIndexCode(String dzCode, String zbCode) {
		Connection conn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		List<String> indexCodeList = new ArrayList<String>();
		try {
			conn = loadNewConnection();
			String sql = sqlBuilder.getRealIndexCode();
			psm = conn.prepareStatement(sql);
			psm.setString(1, dzCode);
			psm.setString(2, zbCode);
			rs = psm.executeQuery();
			while (rs.next()) {
				indexCodeList.add(rs.getString("C_ZB_CODE"));
			}
		} catch (Exception ex) {
			logger.error("获取电子对账关联指标代码失败!",ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(psm);
			releaseConnection(conn);
		}
		return indexCodeList;
	}

	/**
	 * 格式化实收资本数量
	 * add by qiantao STORY #83025 产品估值参数控制实收资本小数位 
	 * @param port
	 * @param d_trade
	 * @param value
	 * @return
	 */
	public String formatSSZBValue(String port, String d_trade,BigDecimal value) 
	{
		String sszbAmount = "";
		try {
			AdmPortActParams param = new AdmPortActParams(port, YssFun.parseDate(d_trade));
			param.setDbConn(this.loadNewConnection());
			param.initActParams();
			String blws = StringUtil.IsNullOrEmpty(param.getElecParamValue(ErDspParamCodeEnum.DZ_JD_SSZB)) ? "2" : param.getElecParamValue(ErDspParamCodeEnum.DZ_JD_SSZB);
		    String jwfs = StringUtil.IsNullOrEmpty(param.getElecParamValue(ErDspParamCodeEnum.DZ_JD_SSZB_JWFS)) ? "JW_ROUND_SSZB" : param.getElecParamValue(ErDspParamCodeEnum.DZ_JD_SSZB_JWFS);
		    sszbAmount= StatsUtil.formatSSZBValue(value.doubleValue(),YssFun.toInt(blws),jwfs);
		} catch (Exception e) {
			logger.error("格式化实收资本数量出错！",e);
		}
		return sszbAmount;
	}

	public HashMap<String, String> formatedData(String ports,HashMap<String, String> formatData) {
		HashMap<String, String> formatedResult = new HashMap<String, String>();
		AdmPortActParams actParam = null;
		if (null == formatData) 
		{
			return formatedResult;
		}
		try {
			actParam = new AdmPortActParams(ports, new java.util.Date());
			actParam.setDbConn(this.loadNewConnection());
			actParam.initActParams();
			for (Map.Entry<String, String> entry : formatData.entrySet()) {
				String id = entry.getKey().split("-")[0];
//				String port = entry.getKey().split("-")[1];
				String blws = StringUtil.IsNullOrEmpty(actParam
						.getElecParamValue(ErDspParamCodeEnum.DZ_JD_SSZB)) ? "2"
						: actParam.getElecParamValue(ErDspParamCodeEnum.DZ_JD_SSZB);
				String jwfs = StringUtil.IsNullOrEmpty(actParam
						.getElecParamValue(ErDspParamCodeEnum.DZ_JD_SSZB_JWFS)) ? "JW_ROUND_SSZB" : actParam
						.getElecParamValue(ErDspParamCodeEnum.DZ_JD_SSZB_JWFS);
				String sszbAmount = StatsUtil.formatSSZBValue(new BigDecimal(entry.getValue()).doubleValue(), YssFun.toInt(blws), jwfs);
				formatedResult.put(id, sszbAmount);
			}

		} catch (Exception e) {
			logger.error("格式化实收资本数量出错！",e);
		}
		return formatedResult;
	}
}
