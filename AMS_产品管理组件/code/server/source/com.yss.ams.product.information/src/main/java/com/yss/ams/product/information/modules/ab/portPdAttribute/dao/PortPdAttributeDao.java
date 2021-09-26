package com.yss.ams.product.information.modules.ab.portPdAttribute.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portPdAttribute.pojo.PortPdAttribute;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;

/**
 * 组合产品信息业务处理类
 * @author zhengguiyu
 *
 */
/**
 * <产品属性分类>DAO层
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortPdAttributeDao extends GeneralDao {

	private PortPdAttributeSqlBuilder portPdAttributeSqlBuilder = null;
	
	public PortPdAttributeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		portPdAttributeSqlBuilder = (PortPdAttributeSqlBuilder)sqlBuilder;
	}

	public HashMap<String, String> getVocabularyDict() {
		HashMap<String, String> vocabularyMap = null;
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "";
		ResultSet rs = null;
		
		try {
			vocabularyMap = new HashMap<String, String>();
			conn = this.loadNewConnection();
			sql = portPdAttributeSqlBuilder.getVocabularySql();
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			while(rs.next()){
				vocabularyMap.put(rs.getString("C_DV_CODE"), rs.getString("C_DV_NAME"));
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("产品属性模块：查询词汇信息失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return vocabularyMap;
	}

	public HashMap<String, String> getAssetDict() {
		HashMap<String, String> assetMap = null;
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "";
		ResultSet rs = null;
		
		try {
			assetMap = new HashMap<String, String>();
			conn = this.loadNewConnection();
			sql = portPdAttributeSqlBuilder.getAssetSql();
			pst = conn.prepareStatement(sql);
		
			rs = pst.executeQuery();
			while(rs.next()){
				assetMap.put(rs.getString("C_DAT_CODE"), rs.getString("C_DAT_NAME"));
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("产品属性模块：查询资产类型字典数据失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return assetMap;
	}

	public HashMap<String, String> getPortPojoList(HashMap<String, String> paraMap) {
		HashMap<String, String> assetMap = null;
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "";
		ResultSet rs = null;
		
		try {
			assetMap = new HashMap<String, String>();
			conn = this.loadNewConnection();
			sql = portPdAttributeSqlBuilder.getAssetCodeSql();
			pst = conn.prepareStatement(sql);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE"),conn));
		
			rs = pst.executeQuery();
			while(rs.next()){
				assetMap.put(rs.getString("C_PORT_CODE"), rs.getString("C_DAT_CODE"));
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("产品属性模块：根据条件查新产品基本信息失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return assetMap;
	}

	public HashMap<String, String> getPortNameDict(HashMap<String, String> paraMap) {
		HashMap<String, String> assetMap = null;
			Connection conn = null;
			PreparedStatement pst = null;
			String sql = "";
			ResultSet rs = null;
			
			try {
				assetMap = new HashMap<String, String>();
				conn = this.loadNewConnection();
				sql = portPdAttributeSqlBuilder.getPortNameDictSql();
				pst = conn.prepareStatement(sql);
				pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE"),conn));
			
				rs = pst.executeQuery();
				while(rs.next()){
					assetMap.put(rs.getString("C_PORT_CODE"), rs.getString("C_PORT_NAME"));
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
				logger.log("产品属性模块：根据条件查新产品基本信息失败", e);
			} finally {
				this.closeResultSetFinal(rs);
				this.closeStatementFinal(pst);
				this.releaseConnection(conn);
			}
			return assetMap;
	}

	public List<String> queryPortCodesByType(String portType) {
		List<String> portCodes = null;
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "";
		ResultSet rs = null;
		try {
			portCodes = new ArrayList<String>();
			conn = this.loadNewConnection();
			sql = portPdAttributeSqlBuilder.queryPortCodesByType();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portType);
			rs = pst.executeQuery();
			while(rs.next()){
				portCodes.add(rs.getString("C_PORT_CODE"));
			}
		} catch (Exception e) {
			logger.log("产品属性模块：根据组合类别查询组合code失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return portCodes;
	}
	
	public List<PortPdAttribute> getShortNumPort(){
		List<PortPdAttribute> shortNumPorts = null;
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "";
		ResultSet rs = null;
		conn = this.loadNewConnection();
		sql = portPdAttributeSqlBuilder.getShortNumPort();
		try {
			shortNumPorts = new ArrayList<PortPdAttribute>();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				PortPdAttribute portPdAttribute = new PortPdAttribute();
				portPdAttribute.setC_PORT_CODE(rs.getString("c_port_code"));
				portPdAttribute.setC_SHORT_NUM(rs.getString("c_short_num"));
				shortNumPorts.add(portPdAttribute);
			}
		} catch (SQLException e) {
			logger.log("产品属性模块：查询含有短编码数据失败", e);
		}finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		
		return shortNumPorts;
	}
	
	/**
	 * 获取组合 T+N 估值属性，天数相同的为一组，进行日期的转换
	 * @return
	 * @throws Exception 
	 */
	public HashMap<String, List<String>> getPortCodeForNDay(String ports) throws Exception{
		HashMap<String, List<String>> retMap = new HashMap<String, List<String>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			conn = this.loadNewConnection();

			sql = "SELECT A.C_PORT_CODE,NVL(PD.N_T_DAYS,0) AS N_T_DAYS FROM T_P_AB_PORT A LEFT JOIN T_P_AB_PORT_PD PD ON A.C_PORT_CODE = PD.C_PORT_CODE AND PD.N_CHECK_STATE = 1 "
					+ " WHERE A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(ports,conn));

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String key = rs.getString("N_T_DAYS");
				if (retMap.containsKey(key)){
					retMap.get(key).add(rs.getString("C_PORT_CODE"));
				} else {
					List<String> list = new ArrayList<String>();
					list.add(rs.getString("C_PORT_CODE"));
					retMap.put(key, list);
				}
			}

		} catch (Exception ex) {
			logger.error("获取估值表指标值失败:"+ex.getMessage(), ex);
            throw ex;
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return retMap;
	}
}
