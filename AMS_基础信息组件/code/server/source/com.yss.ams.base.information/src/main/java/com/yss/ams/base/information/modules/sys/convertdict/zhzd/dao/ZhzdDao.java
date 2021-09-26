package com.yss.ams.base.information.modules.sys.convertdict.zhzd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.ams.base.information.support.sys.convertdict.zhzd.pojo.Zhzd;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.JsonUtil;

/**
 * 转换字典dao
 * @author 马向峰  拆分  2017.0527
 *
 */
public class ZhzdDao extends GeneralDao {

	private ZhzdSqlBuilder zhzdSqlBuilder;
	public ZhzdDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.zhzdSqlBuilder = (ZhzdSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 根据场景类型查找转换字典，并把 源值 和 转换值 存到Map中
	 * @param type 场景类型
	 * @return 封装了 源值和转换值的Map<源值,转换值>
	 */
	public HashMap<String, String> getKeyConvertMap(String type) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		Zhzd t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, zhzdSqlBuilder);
			conn = this.loadNewConnection();
			sql = zhzdSqlBuilder.getSqlByType();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, sqlOverLongCondition(new String[]{type}));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Zhzd.class);
				keyValueMap.put(t.getC_S_CODE(), t.getC_T_CODE());
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
	 * 根据场景和源值查找转换值
	 * @param srcCode 源值
	 * @param sceneType 场景
	 * @return 转换值
	 */
	public String specificConvertDict(String srcCode, String sceneType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String targetCode = null;
		try {
			conn = this.loadNewConnection();
			sql = zhzdSqlBuilder.specificSceneConvert();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sceneType);
			pstmt.setString(2, srcCode);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				targetCode = rs.getString("C_T_CODE");
				break;
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return targetCode;
	}
	
	
	public List<Zhzd> getDataListByKeys(String[] keys) {
		List<Zhzd> listzhzd = new ArrayList<Zhzd>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		Zhzd t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, zhzdSqlBuilder);
			conn = this.loadNewConnection();
			sql = zhzdSqlBuilder.getZHZDDataConv();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, sqlOverLongCondition(keys));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Zhzd.class);
				listzhzd.add(t);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return listzhzd;
	}
	public Zhzd getDataByCode(String code) {
		return null;
	}
	public List<Zhzd> getAllDataList() {
		return null;
	}
	
	/**
	 * 根据场景类型查找转换字典，并把 源值 和 转换值 存到Map中
	 * @param type 场景类型
	 * @return 封装了 源值和转换值的Map<源值,转换值>
	 */
	public HashMap<String, String> getKeyConvertMapTAQS(String[] keys) {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		Zhzd t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, zhzdSqlBuilder);
			conn = this.loadNewConnection();
			sql = zhzdSqlBuilder.getZHZDDataByCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, sqlOverLongCondition(keys));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Zhzd.class);
				keyValueMap.put(t.getC_S_CODE(), t.getC_T_CODE());
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
	 * comment：丢弃表中的全部数据<br>
	 * src：STORY #60135 嘉实基金-系统间升级交互机制 <br>
	 * author：shijian@ysstech.com<br>
	 * date：2018年12月21日<br>
	 */
	public  void clearTempFeeId(Connection conn) throws SQLException{
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate("DELETE FROM  R_D_FEE_ID");
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.log("clearTempFeeId ->"+e.getMessage());
		} finally{
			closeStatementFinal(stat);
		}
	}
	
	/**
	 * comment：把集合插入临时表中<br>
	 * src：STORY #60135 嘉实基金-系统间升级交互机制 <br>
	 * author：shijian@ysstech.com<br>
	 * date：2018年12月21日<br>
	 */
	public  void insertTempFeeId(Connection conn,List<String> idList) throws SQLException{
		String sql = "INSERT INTO R_D_FEE_ID (ID_D_AC_TD_IVT) VALUES (?)";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			for(int i =0;i<idList.size();i++){
				pst.setString(1, idList.get(i));
				pst.addBatch();
			}
			pst.executeBatch();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.log("insertTempFeeId ->"+e.getMessage());
		} finally{
			if(null != pst) {
				pst.clearBatch();
			}
			closeStatementFinal(pst);
		}
	}
	
	/**
	 * comment：根据转换字典代码批量获取转换字典<br>
	 * src：STORY #60135 嘉实基金-系统间升级交互机制 <br>
	 * author：shijian@ysstech.com<br>
	 * date：2018年12月21日<br>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getConvertMapByGroupCode(String[] groupCodes) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Map<String, Map<String, String>> convertMaps= new HashMap<String, Map<String, String>>();
		Map<String, String> convertMapByGroupCode = new HashMap<String, String>();
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			clearTempFeeId(conn);
			List<String> groupCodeList = Arrays.asList(groupCodes);
			insertTempFeeId(conn, groupCodeList);
			sql = zhzdSqlBuilder.getConvertMapByGroup();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String groupCode = rs.getString("C_GROUP_CODE");
				String sCode = rs.getString("C_S_CODE");
				String tCode = rs.getString("C_T_CODE");
				if(convertMaps.containsKey(groupCode)){
					convertMaps.get(groupCode).put(sCode, tCode);
				}else{
					Map<String, String> convertMap = new HashMap<String, String>();
					convertMap.put(sCode, tCode);
					convertMaps.put(groupCode, convertMap);
				}
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
			if(!convertMaps.isEmpty()){
				Set<String> keySet = convertMaps.keySet();
				for(String key : keySet){
					Map<String, String> convertMap = convertMaps.get(key);
					String mapString = JsonUtil.toString(convertMap);
					convertMapByGroupCode.put(key, mapString);
				}
			}
				
		} catch (Exception e) {
			logger.log("根据转换字典代码批量获取转换字典出错", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return convertMapByGroupCode;
	}
	
}
