package com.yss.uco.elecreco.er.erdztype.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;
import com.yss.uco.elecreco.er.erdztype.cons.RptTypeCons;
import com.yss.uco.elecreco.er.erdztype.util.LicDzTypeUtil;
import com.yss.uco.elecreco.support.bean.ErDzType;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ElecGroupRela;

public class ErDzTypeDao extends GeneralDao  {

	private ErDzTypeSqlBuilder sqlBuilder = null;
	public ErDzTypeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ErDzTypeSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典表数据对应pojo的list
	 * @param set 
	 * @return
	 * @throws DataAccessException
	 */
	public List<ErDzType> getAllData(Set<String> dzTypes) throws DataAccessException {
		List<ErDzType> dataList = new ArrayList<ErDzType>();
		ErDzType erDzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);

			conn = loadNewConnection();
			sql = sqlBuilder.getAllDataByTypesSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, DbFun.sqlOverLongCondition(dzTypes.toArray(new String[dzTypes.size()]), conn));
			rs = ptmt.executeQuery();
			while (rs.next()) {
				erDzType = (ErDzType) rsTools.ResultToBean(rs, ErDzType.class);
				dataList.add(erDzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo的list
	 * @param dzTypes
	 * @return
	 * @throws DataAccessException
	 */
	public List<ErDzType> getDataByKeys(Set<String> dzTypes) throws DataAccessException {
		List<ErDzType> dataList = new ArrayList<ErDzType>();
		ErDzType erDzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);

			conn = loadNewConnection();
			sql = sqlBuilder.getAllDataByTypesSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, DbFun.sqlOverLongCondition(dzTypes.toArray(new String[dzTypes.size()]), conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				erDzType = (ErDzType) rsTools.ResultToBean(rs, ErDzType.class);
				dataList.add(erDzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo的list
	 * @param dzTypes
	 * @return
	 * @throws DataAccessException
	 */
	public List<ErDzType> getDataByTypes(Set<String> dzTypes)
			throws DataAccessException {
		List<ErDzType> dataList = new ArrayList<ErDzType>();
		ErDzType erDzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);

			conn = loadNewConnection();
			sql = sqlBuilder.getAllDataByTypesSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setArray(1, DbFun.sqlOverLongCondition(dzTypes.toArray(new String[dzTypes.size()]), conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				erDzType = (ErDzType) rsTools.ResultToBean(rs, ErDzType.class);
				dataList.add(erDzType);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 根据对账类型代码查询所有对账类型字典表数据对应pojo
	 * @param code
	 * @return
	 * @throws DataAccessException
	 */
	public ErDzType getDataByCode(String code) throws DataAccessException {
		ErDzType erDzType = null;

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);

			conn = loadNewConnection();
			sql = sqlBuilder.getDataByCodeSql();
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, code);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				erDzType = (ErDzType) rsTools.ResultToBean(rs, ErDzType.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return erDzType;
	}
	
	/**
	 * 按照流水号升序的方式查询所有对账类型字典代码和对账类型字典名称转Map
	 * @param dzTypes 
	 * @return
	 * @throws DataAccessException
	 */
	public HashMap<String, String> getKeyConvertMap()
			throws DataAccessException {
		HashMap<String, String> keyConvertMap = new HashMap<String, String>();

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		String mapKey = "", mapValue = "";
		try {

			conn = loadNewConnection();
			sql = sqlBuilder.getAllDzTypeSql();
			ptmt = conn.prepareStatement(sql);

			rs = ptmt.executeQuery();

			while (rs.next()) {
				mapKey = rs.getString(ErDzTypeColumnName.c_DZ_CODE.toString());
				mapValue = rs.getString(ErDzTypeColumnName.c_DZ_NAME.toString());

				keyConvertMap.put(mapKey, mapValue);
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return keyConvertMap;
	}
	
	/**
	 * 根据对账类型代码获取所有对账类型字典代码和对账类型字典名称转Map
	 * @param dzTypes
	 * @return
	 * @throws DataAccessException
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> dzTypes) throws DataAccessException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String mapKey = "", mapValue = "";
		try {
			String[] strArr = new String[dzTypes.size()];

			conn = loadNewConnection();
			sql = sqlBuilder.getAllDataByTypesSql();
			pstmt = conn.prepareStatement(sql);
			
			dzTypes.toArray(strArr);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr,conn));

			rs = pstmt.executeQuery();
			conn.commit();

			while (rs.next()) {
				mapKey = rs.getString(ErDzTypeColumnName.c_DZ_CODE.toString());
				mapValue = rs.getString(ErDzTypeColumnName.c_DZ_NAME.toString());

				keyValueMap.put(mapKey, mapValue);
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
	 * 获取电子对账生成界面对账类型的数据
	 * @return
	 */
	public List<BasePojo> getGeneDzType() {
		List<BasePojo> dataList = new ArrayList<BasePojo>();
		Set<String> dayDzTypes = LicDzTypeUtil.getInstance().getDayDzTypes();
		List<ErDzType> days = this.getDataByKeys(dayDzTypes);
		Set<String> monthDzTypes = LicDzTypeUtil.getInstance().getMonthDzTypes();
		List<ErDzType> months = this.getDataByKeys(monthDzTypes);
		
		Set<String> transTypes = LicDzTypeUtil.getInstance().getTransTypes();
		List<ErDzType> trans = this.getDataByKeys(transTypes);
		
		if(days != null && days.size() > 0)
		{
			dataList.add(createParentObj(RptTypeCons.KEY_DAY_01, RptTypeCons.RPT_DAY.get(RptTypeCons.KEY_DAY_01)));
			for (ErDzType erDzType : days) {
				dataList.add(createDayObj(RptTypeCons.KEY_DAY_01, erDzType));
			}
		}
		if(months != null && months.size()>0)
		{
			for (Entry<String, String> entry : RptTypeCons.RPT_MONTH.entrySet()) {
				dataList.add(createParentObj(entry.getKey(), entry.getValue()));
				for (ErDzType erDzType : months) {
					dataList.add(createMonthObj(entry.getKey(), entry.getKey(),erDzType));
				}
			}
		}
		
		if(trans != null && trans.size()>0)
		{
			for (Entry<String, String> entry : RptTypeCons.RPT_TRANS.entrySet()) {
				dataList.add(createParentObj(entry.getKey(), entry.getValue()));
				for (ErDzType erDzType : trans) {
					dataList.add(createMonthObj(entry.getKey(), entry.getKey(),erDzType));
				}
			}
		}
		
		return dataList;
	}
	
	/**
	 * 获取电子对账所有对账类型的数据
	 * @return
	 */
	public List<BasePojo> getAllDzTypeMap() {
		List<BasePojo> dataList = new ArrayList<BasePojo>();

		String sql = "";
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);

			conn = loadNewConnection();
			sql = sqlBuilder.getAllDzTypeSql();
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				ErDzType erDzType = (ErDzType) rsTools.ResultToBean(rs, ErDzType.class);
				if("01".equalsIgnoreCase(erDzType.getC_DZ_CODE_P()))
				{
					dataList.add(createDayObj(RptTypeCons.KEY_DAY_01, erDzType));
				}else if("03".equalsIgnoreCase(erDzType.getC_DZ_CODE_P())){
					for (Entry<String, String> entry : RptTypeCons.RPT_MONTH.entrySet()) {
						dataList.add(createMonthObj(entry.getKey(), entry.getKey(),erDzType));
					}
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("获取对账类型字典数据列表出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 创建报表类型的父节点
	 * @param code
	 * @param name
	 * @return
	 */
	private ElecGroupRela createParentObj(String code,String name)
	{
		ElecGroupRela parent = new ElecGroupRela();
		parent.setC_ELEC_CODE(code);
		parent.setC_ELEC_NAME(name);
		parent.setC_PARENT_CODE("[root]");
		parent.setC_RESULT_CODE("");
		return parent;
	}
	
	/**
	 * 创建日报子节点
	 * @param parentCode
	 * @param erDzType
	 * @return
	 */
	private ElecGroupRela createDayObj(String parentCode,ErDzType erDzType)
	{
		ElecGroupRela obj = new ElecGroupRela();
		obj.setC_ELEC_CODE(erDzType.getC_DZ_CODE());
		obj.setC_ELEC_NAME(erDzType.getC_DZ_NAME());
		obj.setC_PARENT_CODE(parentCode);
		obj.setC_RESULT_CODE(erDzType.getC_CHECK_FLAG());
		return obj;
	}
	
	/**
	 * 创建月报子节点
	 * @param parentCode
	 * @param rptType
	 * @param erDzType
	 * @return
	 */
	private ElecGroupRela createMonthObj(String parentCode,String rptType,ErDzType erDzType)
	{
		ElecGroupRela obj = createDayObj(parentCode, erDzType);
		obj.setC_ELEC_CODE(rptType+"_"+erDzType.getC_DZ_CODE());
		return obj;
	}

}