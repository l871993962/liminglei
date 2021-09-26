package com.yss.ams.product.information.modules.pg.portgroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup;
import com.yss.framework.api.bundle.BundleContextWrapper;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.mvc.pojo.PojoLoader;

/**
 * <群组管理>DAO层
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupDao extends GeneralDao{
	
	private PortGroupSqlBuilder sqlBuilder;

	public PortGroupDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (PortGroupSqlBuilder)sqlBuilder;
	}
	
	/**
	 * 获取群组A区数据
	 * chenwenhai 20140517
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getPortGroupA(HashMap<String, Object> paraMap,BundleContextWrapper bundleContext) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";
		Class<?> clazz = null;

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = sqlBuilder.getPortGroupA(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			clazz = PojoLoader.getPojoClassById("PortGroup",bundleContext);
			rs = pstmt.executeQuery();
			BasePojo t = null;
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, clazz);
				pojoList.add(t);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
	
	/**
	 * 查询群组列表
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getPortGroupListData(HashMap<String, Object> paraMap,BundleContextWrapper bundleContext) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";
		Class<?> clazz = null;

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = sqlBuilder.getPortGroupListData(paraNameList);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			clazz = PojoLoader.getPojoClassById("PortGroup",bundleContext);
			rs = pstmt.executeQuery();
			BasePojo t = null;
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, clazz);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
	
	public String checkGroupCode(String groupCode){
		String returnInfo = "false";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getCheckGroupCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				returnInfo = "true";
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("产品群组模块：A区查询群组失败", e);
		}finally{
			this.closeResultSetFinal(rs); //byleeyu20160617
			this.closeStatementFinal(pstmt); //byleeyu20160617
			this.releaseConnection(conn); //byleeyu20160617
		}
		return returnInfo;
	}
	
	/**
	 * 
	 * 派工单 #2333 估值_V1.300.7.0_UI自动化测试_自动化测试(272)
	 * @param groupCode
	 * @param ciden
	 * @return
	 */
	public String checkGroupCode(String groupCode, String ciden){
		String returnInfo = "false";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getCheckGroupCode("");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, groupCode);
			pstmt.setString(2, StringUtil.IsNullOrEmpty(ciden) ? " " : ciden);
			pstmt.setString(3, groupCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				returnInfo = "true";
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("产品群组模块：A区查询群组失败", e);
		}finally{
			this.closeResultSetFinal(rs); //byleeyu20160617
			this.closeStatementFinal(pstmt); //byleeyu20160617
			this.releaseConnection(conn); //byleeyu20160617
		}
		return returnInfo;
	}

	/**
	 * By Jinghehe 2014-6-3 获取群组
	 * 获取关联方案界面的 新增状态的群组数据
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getPlanRelaPortGroupAdd(
			HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = sqlBuilder.getPlanRelaPortGroupAddSql();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("C_PLAN_TYPE").toString());
			pstmt.setString(2, paraMap.get("C_PLAN_CODE").toString());
			pstmt.setString(3, paraMap.get("D_BEGIN").toString());
			pstmt.setString(4, paraMap.get("D_END").toString());
			pstmt.setString(5, paraMap.get("D_BEGIN").toString());
			pstmt.setString(6, paraMap.get("D_END").toString());
//			pstmt.setString(7, paraMap.get("C_POST_CODE").toString());
//			pstmt.setString(8, paraMap.get("C_USER_CODE").toString());
//			pstmt.setString(9, paraMap.get("C_POST_CODE").toString());
//			pstmt.setString(10, paraMap.get("C_USER_CODE").toString());
			rs = pstmt.executeQuery();
			BasePojo t = null;
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, PortGroup.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}

	/**
	 * By Jinghehe 2014-6-3 获取群组
	 * 获取关联方案界面的浏览状态群组数据
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getPlanRelaPortGroupBrow(
			HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = sqlBuilder.getPlanRelaPortGroupBrowSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("C_PORT_CODE").toString());
			logger.debug(sql);
			rs = pstmt.executeQuery();
			BasePojo t = null;
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, PortGroup.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}

	/**
	 * By Jinghehe 2014-6-5
	 * 群组和组合代码转名称使用的服务类 
	 * 涉及组合和群组参数的界面要进行代码转名称
	 * 仅仅供含有组合和群组的List界面
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getAllDataListSql();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				keyValueMap.put(rs.getString(PortGroupColumnName.c_GROUP_CODE
						.toString()), rs
						.getString(PortGroupColumnName.c_GROUP_NAME 
								.toString()));
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
	
	public Map<String,String> getAllPortGroup(){
		Map<String,String> map = new HashMap<String,String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			String sql = "select c_group_code from t_p_ab_group";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String groupCode = rs.getString("c_group_code");
				map.put(groupCode, groupCode);
			}
		}catch(Exception ex){
			throw new BusinessException("查询组合群组失败!"+ex.getMessage());
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
				
		return map;
	}

	/**
	 * 根据群组代码查询群组信息
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	public List<PortGroup> getDataListByCodes(String[] codes) throws Exception {
		List<PortGroup> pojoList = new ArrayList<PortGroup>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortGroupSqlBuilder portGroupSqlBuilder = null;
		PortGroup t = null;
		
		if(codes == null || codes.length <= 0)
			return pojoList;
		
		try {
			portGroupSqlBuilder = new PortGroupSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, portGroupSqlBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = portGroupSqlBuilder.getGroupByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(codes,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortGroup.class);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	public List<String> getPortCodeByGroupCode(List<String> groupCodes) throws DataAccessException {
		List<String> portCodes = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = "select c_port_code from T_P_AB_GROUP_RELA where c_group_code in (select * from table(?))";
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(groupCodes.toArray(new String[groupCodes.size()]),conn));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				portCodes.add(rs.getString("C_PORT_CODE"));
			}
		} catch (Exception e) {
			logger.error("查询失败："+e.getMessage(), e);
			throw new DataAccessException(e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return portCodes;
	}
}
