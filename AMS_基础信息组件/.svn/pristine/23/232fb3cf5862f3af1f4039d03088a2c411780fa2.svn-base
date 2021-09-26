package com.yss.ams.base.information.modules.bi.region.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.region.pojo.Area;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;

/**
 * 地区信息dao
 * @author 马向峰 拆分
 *@Date 20170601
 */
public class AreaDao extends GeneralDao {
	
	public AreaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * 所有地区信息
	 * 
	 * @param planType
	 * @return
	 */
	public List<BasePojo> getAllAreas() {
		List<BasePojo> areaList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = ((AreaSqlBuilder) sqlbuilder)
				.getAllAreasByTypeSql(dbNameResolver);
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			logger.debug(sql);
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlbuilder);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, Area.class);
				areaList.add(t);
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("地区设置：查询所有地区出错", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return areaList;
	}

	/**
	 * 所有地区最上层所属地区
	 * 
	 * @return
	 */
	public List<Area> getAllTopAreas() {
		List<Area> areaList = new ArrayList<Area>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = ((AreaSqlBuilder) sqlbuilder).getAllTopAreasSql();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			logger.debug(sql);
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlbuilder);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, Area.class);
				areaList.add((Area) t);
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("地区设置：查询所有地区最上层所属地区出错", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return areaList;
	}
	
	/**
	 * 获取所有地区非最上层所属地区
	 * 
	 * @return
	 */
	public List<Area> getAllNotTopAreas() {
		List<Area> areaList = new ArrayList<Area>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = ((AreaSqlBuilder) sqlbuilder).getAllNotTopAreasSql();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			logger.debug(sql);
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlbuilder);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, Area.class);
				areaList.add((Area) t);
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("地区设置：查询所有地区非最上层所属地区出错", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return areaList;
	}

	/**
	 * 所有地区信息
	 * 
	 * @param planType
	 * @return
	 */
	public List<BasePojo> getDataList() {
		List<BasePojo> areaList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = ((AreaSqlBuilder) sqlbuilder).getCommonSql();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			logger.debug(sql);
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlbuilder);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, Area.class);
				areaList.add(t);
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("地区设置：查询所有地区信息出错", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return areaList;
	}
	
	/**
	 * 根据地区信息代码，将地区信息转为map
	 * @param listKey
	 * @return	HashMap<地区代码, 地区名称>
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		HashMap<String, String> keyValueMap = new HashMap<String,String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = ((AreaSqlBuilder) sqlbuilder).getAllDataSqlByKeys();
		try {
			String[] strArr = new String[listKey.size()];
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr,conn));
			logger.debug(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				keyValueMap.put(rs.getString("C_AREA_CODE"),rs.getString("C_AREA_NAME"));
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("地区设置：数据代码名称转换出错", ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return keyValueMap;
	}

	/**
	 * 将所有地区信息转为map
	 * @param listKey
	 * @return	HashMap<地区代码, 地区名称>
	 */
	public HashMap<String, String> getKeyConvertMap() {
		List<BasePojo> list = getDataList();
		HashMap<String, String> relMap = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			Area area = (Area) list.get(i);
			relMap.put(area.getC_AREA_CODE(), area.getC_AREA_NAME());
		}

		return relMap;
	}
	
	/**
	 * 根据地区代码，查找地区信息
	 * @param code	地区代码
	 * @return	地区信息
	 * @throws Exception
	 */
	public Area getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AreaSqlBuilder areaSqlBuilder = null;
		Area t = null;
		try {
			areaSqlBuilder = new AreaSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, areaSqlBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = areaSqlBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Area.class);
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
	
}
