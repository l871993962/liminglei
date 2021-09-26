package com.yss.ams.base.information.modules.bi.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.account.pojo.AreaCity;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;

/**
 * 
 * @ClassName BaseOrganDao
 * @Description 公用机构数据库操作类
 * @author liangyilin@ysstech.com
 * @date 2017年6月22日下午7:23:08
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class AreaCityDao extends GeneralDao{

	public AreaCityDao(DbPool dbPool,SQLBuilder sqlBuilder){
		super(dbPool,sqlBuilder);
	}
	
	/**
	 * 
	 * @Title getKeyConvertMap 
	 * @Description 查询省份城市K-V
	 * @author hehonghui@ysstech.com
	 * @date 2017年11月29日下午7:06:28
	 * @return
	 * @throws Exception
	 * @return HashMap<String,String>
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AreaCitySqlBuilder dsServiceBuilder = null;
		AreaCity t = null;
		try {
			dsServiceBuilder = new AreaCitySqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AreaCity.class);
				keyValueMap.put(t.getC_Code(), t.getC_Name());
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
	
	/**
	 * 
	 * @Title getKeyConvertMap 
	 * @Description 通过省份城市代码查询省份城市K-V
	 * @author hehonghui@ysstech.com
	 * @date 2017年11月29日下午7:06:50
	 * @param listKey
	 * @return
	 * @throws Exception
	 * @return HashMap<String,String>
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AreaCitySqlBuilder dsServiceBuilder = null;
		AreaCity t = null;
		try {
			String[] strArr = new String[listKey.size()];
			dsServiceBuilder = new AreaCitySqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, AreaCity.class);
				keyValueMap.put(t.getC_Code(), t.getC_Name());
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
	
	/**
	 * 查询城市信息
	 * STORY #45935 招行银企要支持省市设置
	 * 2018-5-16 BUG203043招行银企直联-分行号格式不对导致银行账户查询失败
	 * 针对招行的城市做特殊处理，把城市名称中的'市'后面截掉。例如：南京市--> 南京
	 * @param accNo
	 * @return
	 */
	public String queryAccCityByAccNo(String accNo) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String city = "";
		try {
			conn = this.loadNewConnection();
			sql.append(" SELECT DISTINCT CASE WHEN UPPER(NVL(ORG.C_ORG_CODE_P, ORG.C_ORG_CODE)) = 'CMBC' THEN "); 
			sql.append(" SUBSTR(ASS.C_NAME,0,INSTR(ASS.C_NAME,'市', 1)-1) ELSE ASS.C_NAME END AS C_CITY, C_OPEN_ACC_NO FROM T_P_BI_FUND_ACC ACC ");
			sql.append(" LEFT JOIN T_S_DC_ADDRESS ASS ON ACC.C_CITY = ASS.C_CODE ");
			sql.append(" JOIN (SELECT O.C_ORG_CODE, O.C_ORG_CODE_P FROM T_P_BI_ORG O) ORG ");
			sql.append(" ON UPPER(ORG.C_ORG_CODE) = UPPER(ACC.C_ORG_CODE) WHERE ACC.C_OPEN_ACC_NO = ? AND C_CITY IS NOT NULL ");
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, accNo);
			rs = pst.executeQuery();
			while(rs.next()) {
				city = rs.getString("C_CITY");
			}
		} catch(Exception e) {
			throw new DataAccessException(e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return city;
	}
	
	/**
	 * 查询省份信息
	 * STORY #45935 招行银企要支持省市设置
	 * 2018-5-16 BUG203043招行银企直联-分行号格式不对导致银行账户查询失败
	 * @param accNo
	 * @return
	 */
	public String queryAccProvinceByAccNo(String accNo) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		String province = "";
		try {
			conn = this.loadNewConnection();
 			sql.append(" SELECT DISTINCT ASS.C_NAME AS C_PROVINCE, C_OPEN_ACC_NO FROM T_P_BI_FUND_ACC ACC LEFT JOIN T_S_DC_ADDRESS ASS "); 
			sql.append(" ON ACC.C_PROVINCE = ASS.C_CODE WHERE ACC.C_OPEN_ACC_NO = ? AND C_PROVINCE IS NOT NULL "); 
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, accNo);
			rs = pst.executeQuery();
			while(rs.next()) {
				province = rs.getString("C_PROVINCE");
			}
		} catch(Exception e) {
			throw new DataAccessException(e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return province;
	}
}
