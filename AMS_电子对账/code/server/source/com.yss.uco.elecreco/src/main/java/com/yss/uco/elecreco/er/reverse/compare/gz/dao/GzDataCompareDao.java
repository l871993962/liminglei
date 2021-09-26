package com.yss.uco.elecreco.er.reverse.compare.gz.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.er.ergzb.dao.ErGzbSqlBuilder;
import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareSqlBuilder;
import com.yss.uco.elecreco.er.reverse.out.ergzb.pojo.ErGzbOut;
import com.yss.uco.elecreco.er.reverse.out.ergzb.service.IErGzbOutService;
import com.yss.uco.elecreco.support.bean.ErGzb;

/**
 * @author Lenovo
 *
 */
public class GzDataCompareDao extends DataCompareDao {

	public GzDataCompareDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	/**
	 * 删除生成的内部数据
	 */
	public void deleteInnerData(String portCode,String dzDate,String way,String fileType, String rptType, Connection conn)
	{
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).getDeleteInnerDataSql();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, dzDate);
			pstmt.setString(index++, dzDate);
			pstmt.setString(index++, way);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除数据失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}

	public Map<String, BasePojo> getInnerData(String sn)
	{
		Map<String, BasePojo> map = new HashMap<String, BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, new ErGzbSqlBuilder());
			sql = ((DataCompareSqlBuilder) sqlbuilder).getInnerDataSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, sn);
			rs = pstmt.executeQuery();
			ErGzb erGzb = new ErGzb();
			BasePojo pojo = (BasePojo) erGzb;
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, erGzb.getClass(), props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,t);
				erGzb = (ErGzb) t;
				map.put(erGzb.getC_KM_CODE(), erGzb);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询内部估值表失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}

	@Override
	public Map<String, BasePojo> getOutData(String portCode, String gzDate,
			String tghCode, String rptType) {
		Map<String, BasePojo> map = new HashMap<String, BasePojo>();
		IErGzbOutService erGzbOutService = YssServiceFactory.getInstance().createService(IErGzbOutService.class);
		HashMap<String, Object> paras = new HashMap<String, Object>();
		paras.put("dataClass", "ErGzbOut");
		paras.put("ARRAY_C_ASS_CODE", portCode);
		paras.put("C_TGH_CODE", tghCode);
		paras.put("D_GZ_DATE", gzDate);
		QueryRes res = erGzbOutService.queryByCondition(paras);
		if(res != null)
		{
			List<BasePojo> dataList  = res.getDataList();
			if(dataList != null)
			{
				for(BasePojo pojo : dataList)
				{
					ErGzbOut gz = (ErGzbOut) pojo;
					String code = gz.getC_KM_CODE();
					if(!StringUtil.IsNullOrEmptyT(code) && !"null".equalsIgnoreCase(code))
					{
						map.put(code, pojo);
					}
				}
			}
		}
		return map;
	}

	
	@Override
	public boolean updateReportWay(String value,String sn,String portCode,String dzDate,String fileType, String rptType, Connection conn) {
		boolean result = false;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = ((DataCompareSqlBuilder) sqlbuilder).getUpdateReportWaySql();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setString(index++, value);
			pstmt.setString(index++, sn);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, dzDate);
			pstmt.setString(index++, dzDate);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.executeUpdate();
			result = true;
			return result;
		} catch (Exception ex) {
			throw new DataAccessException("更新估值表对账方向失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}
}
