package com.yss.uco.elecreco.er.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.uco.elecreco.er.template.pojo.Deploy;
import com.yss.uco.elecreco.er.template.pojo.DzTemplate;

/**
 * @author liuxiang 2015年2月13日
 */
public class DzTemplateDao extends GeneralDao {

	/**
	 * 报表部署后默认的报表状态 默认 “可用”
	 */
	private static final String DEFAULT_REPORT_STATE = "TEMP_USABLE";

	private DzTemplateSqlBuilder tempSqlBuilder = null;

	public DzTemplateDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		tempSqlBuilder = (DzTemplateSqlBuilder) sqlBuilder;
	}

	/**
	 * 根据模板类型和组合代码查询可用模板
	 * 
	 * @param typeCode
	 *            模板类型代码
	 * @param portCode
	 *            组合代码
	 * @return
	 */
	public DzTemplate getTemplateByTypeCodeAndPortCode(String typeCode,
			String portCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		DzTemplate t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, tempSqlBuilder);
			conn = this.loadNewConnection();
//			conn.setAutoCommit(false);

			sql = tempSqlBuilder.getTemplateByTypeCodeAndPortCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, typeCode);
			pstmt.setString(2, portCode);

			rs = pstmt.executeQuery();
//			conn.commit();
//			conn.setAutoCommit(true);
			if (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DzTemplate.class);
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
	
	/**
	 * 根据模板类型和组合代码查询可用模板
	 * 
	 * @param typeCode
	 *            模板类型代码
	 * @param portCode
	 *            组合代码
	 * @return
	 */
	public HashMap<String, DzTemplate> getTemplateByTypeCodeAndPortCodes(String typeCode,
			String portCodes, Connection conn) { //此处连接从外面传入byleeyu20150914
//		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		ResultSetTools rsTools = null;
		HashMap<String, DzTemplate> map = new HashMap<String, DzTemplate>();
		DzTemplate t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, tempSqlBuilder);
//			conn = this.loadNewConnection();
//			conn.setAutoCommit(false);
			
			sql = tempSqlBuilder.getTemplateByTypeCodeAndPortCodes();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, typeCode);
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(portCodes,conn));
			
			rs = pstmt.executeQuery();
//			conn.commit();
//			conn.setAutoCommit(true);
			if (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, DzTemplate.class);
				map.put(rs.getString("C_PORT_CODE"), t);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
//			releaseConnection(conn);
		}
		
		return map;
	}

	/**
	 * 部署信息，这里实现数据的保存到库
	 * 
	 * @param dirFile
	 *            部署的文件目录
	 * @param template
	 *            模板信息
	 * @param readme
	 *            说明文档信息
	 * @throws Exception
	 */
	public void deploy(String dirFile, Deploy deploy) throws Exception {
		Connection conn = null;
		boolean bTrans = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			if (deploy != null) {
				DzTemplate template = new DzTemplate();
				template.setC_DESC(deploy.getInfo().getDesc());
				template.setC_DV_TMPL_STATUS(DzTemplateDao.DEFAULT_REPORT_STATE);
				template.setC_TMPL_CODE(deploy.getInfo().getCode());
				template.setC_TMPL_NAME(deploy.getInfo().getName());
				template.setC_TMPL_PATH(dirFile);
				template.setC_TMPL_TYPE(deploy.getInfo().getType());
				template.setC_VERSION(deploy.getInfo().getVersion());
				template.setModifier(YssContextFactory.getInstance()
						.getUserCode());
				template.setModifyDate(DateUtil.getNow(DateUtil.FORMAT_ONE));
				this.deleteByCode(deploy.getInfo().getCode(), conn);
				this.insert(template, conn);

				conn.commit();
				conn.setAutoCommit(bTrans);
				bTrans = false;
			}
		} catch (Exception ex) {
			bTrans = true;
			throw ex;
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}


	/**
	 * 卸载
	 * 
	 * @param templateCode
	 *            模板代码
	 * @throws Exception
	 */
	public void unDeploy(String templateCode) throws Exception {
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
				this.deleteByCode(templateCode, conn);

			conn.setAutoCommit(true);
			conn.commit();
		} catch (Exception ex) {
			throw ex;
		} finally {
			releaseConnection(conn);
		}
	}


	/**
	 * 根据模板代码删除数据
	 * 
	 * @param document
	 *            POJO类
	 * @param conn
	 *            连接
	 * @throws Exception
	 */
	private void deleteByCode(String templateCode, Connection conn)
			throws Exception {
		PreparedStatement pst = null;
		String sql = "";
		try {
			sql = tempSqlBuilder.getDeleteByCodeSQL();
			pst = conn.prepareStatement(sql);
			pst.setString(1, templateCode);
			pst.executeUpdate();
		} catch (Exception ex) {
			throw ex;
		} finally {
			this.closeStatementFinal(pst);
		}
	}
	
	/** 代码转名称 **/
	public HashMap<String, String> getKeyConvertMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = sqlbuilder
					.getQueryConditionSql(new ArrayList<String>());
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_TMPL_CODE"), rs.getString("C_TMPL_NAME"));
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询数据失败", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return map;
	}

	public BasePojo getDataByCode(String codeStr) {
		return null;
	}

	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String[] keys = new String[listKey.size()];
			keys = listKey.toArray(keys);
			conn = this.loadNewConnection();
			String sql = tempSqlBuilder.getTemplateByCodeSQL();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_TMPL_CODE"), rs.getString("C_TMPL_NAME"));
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询数据失败", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return map;
	}

	public List<String> getDeployTemplate() {
		List<String> templateList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			conn = this.loadNewConnection();
			String sql =  tempSqlBuilder.getDeployTemplateSQL();
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()){
				templateList.add(rs.getString("C_TMPL_CODE"));
			}
		}catch(Exception ex){
			throw new DataAccessException("查询数据失败",ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return templateList;
	}

}
