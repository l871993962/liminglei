/**
 *
 * @Title: PortBusinessRelaDao.java 
 * @Package com.yss.ams.base.information.modules.sys.portbusinessrela.dao 
 * @date 2019年5月13日 下午5:40:38 
 * @version V1.0
 * @Stroy72335/Bug
 * @author xiadeqi   
 */
package com.yss.ams.base.information.modules.sys.portbusinessrange.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.portbusinessrange.pojo.PortBusinessRangePojo;
import com.yss.ams.base.information.util.cache.CacheUtil;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.context.ContextFactory;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/** 
 * 产品业务范围 dao层
 * @ClassName: PortBusinessRelaDao 
 * @date 2019年5月13日 下午5:40:38
 * @Stroy72335/Bug
 * @author xiadeqi 
 */
public class PortBusinessRangeDao  extends GeneralDao{

	PortBusinessRangeSqlBuilder sqlBuilder = null; 
	
	/** 
	 * <p>Title: 构造器</p> 
	 * <p>Description: </p> 
	 * @param pool
	 * @param sqlBuilder 
	 */
	public PortBusinessRangeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (PortBusinessRangeSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 
	 * 根据组合代码查询业务类型code
	 * @Title: queryRelValueByCode 
	 * @param @param portCode
	 * @param @return
	 * @return List<String>    
	 * @throws 
	 * @Stroy73411/Bug
	 * @author xiadeqi
	 */
	public List<String> queryProductBusiCodeByProductCode(String portCode) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		String sql = "";
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			conn = this.loadNewConnection();
			sql = sqlBuilder.queryProductBusiCodeByProductCode();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(PortBusinessRangeColumnName.c_BUSINESS_CODE.toString()));
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * BUG #288785 【华宝基金】自动化流程中根据产品业务范围判断审核才走
	 * 根据产品代码，业务范围查询产品业务范围审核状态
	 * @param portCode
	 * @param busiCode
	 * @return
	 */
	public boolean queryCheckStatusByPortCodeAndBusiCode(String portCode, String busiCode) {
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.queryCheckStatusByPortCodeAndBusiCode();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			pst.setString(2, busiCode);
			rs = pst.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("CNUM");
				if (count > 0) {
					return true;
				}
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return false;
	}
	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * 获取业务类型数据
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	public List<Vocabulary> getDataListByType(String type)
			throws ServiceException {
		List<Vocabulary> list = new ArrayList<Vocabulary>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getDataListByTypeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, type);
			rs = pst.executeQuery();
			while (rs.next()) {
				Vocabulary vocabulary = new Vocabulary();
				vocabulary.setC_DV_CODE(rs.getString("C_BUSINESS_CODE"));
				vocabulary.setC_DV_NAME(rs.getString("C_BUSINESS_NAME"));
				list.add(vocabulary);
			}
			if (list.isEmpty()) {
		        List<Vocabulary> vocList = CacheUtil.getDataListByTypes(type);
		        list.addAll(vocList);
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * 更新业务类型数据
	 * @param type
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public boolean updateDataList(String type, HashMap<String, String> paraMap)
			throws ServiceException {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean ret = false;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			// 删除数据
			sql = sqlBuilder.getDeleteDataByTypeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, type);
			pst.executeUpdate();
			this.closeStatementFinal(pst);
			// 插入数据
			String userCode = ContextFactory.getContext().getUserCode();
			sql = sqlBuilder.getInsertDataListSql();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paraMap.size(); i++) {
				String vocabularyStr = paraMap.get(String.valueOf(i));
				if (vocabularyStr != null && vocabularyStr.contains("#")) {
					int index = vocabularyStr.indexOf("#");
					String businessCode = vocabularyStr.substring(0, index);
					String businessName = vocabularyStr.substring(index + 1);
					if (!"".equals(businessCode.trim()) && !"".equals(businessName.trim())) {
						pst.setString(1, businessCode);
	            		pst.setString(2, businessName);
	            		pst.setString(3, type);
	            		pst.setInt(4, i);;
	            		pst.setString(5, userCode);
	            		pst.addBatch();
					}
				}
			}
			pst.executeBatch();
            conn.commit();
			conn.setAutoCommit(true);
			ret = true;
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return ret;
	}
	
	/**
	 * STORY #86378 【华宝基金】二期自动化应用范围增加其他自动化组合
	 * 根据业务类型代码获取组合集合
	 * @param busiCode
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getPortListByBusiCode(String busiCode)
			throws ServiceException {
		List<String> list = new ArrayList<String>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getPortListByBusiCodeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, busiCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(PortBusinessRangeColumnName.c_PORT_CODE.toString()));
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * STORY #96878 【富国基金】自动化参数复制通过产品参数复制
	 * @param pojoList
	 * @throws ServiceException
	 */
	public void insertPortBusinessRange(List<BasePojo> pojoList)
			throws ServiceException {
		PreparedStatement pst = null;
		Connection conn = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			// 插入数据
			sql = sqlBuilder.getInsertPortBusinessRangeSql();
			pst = conn.prepareStatement(sql);
			for (BasePojo basePojo : pojoList) {
				PortBusinessRangePojo pojo = (PortBusinessRangePojo) basePojo;
				int index = 1;
				pst.setString(index++, pojo.getC_PORT_CODE());
				pst.setString(index++, pojo.getC_BUSINESS_CODE());
				pst.setInt(index++, pojo.getAuditState());
				pst.setString(index++, pojo.getModifier());
				pst.setString(index++, pojo.getModifyDate());
				pst.setString(index++, pojo.getOperator());
				pst.setString(index++, pojo.getAuditDate());
				pst.setString(index++, pojo.getC_PORT_CODE());
				pst.setString(index++, pojo.getC_BUSINESS_CODE());
				pst.setInt(index++, pojo.getAuditState());
				pst.addBatch();
			}
			pst.executeBatch();
            conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	}
}
