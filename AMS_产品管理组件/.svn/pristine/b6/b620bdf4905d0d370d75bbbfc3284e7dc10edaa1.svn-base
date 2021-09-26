package com.yss.ams.product.information.modules.ab.assetstree_b.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;

/**
 * <产品树型结构>DAO层
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_BDao extends GeneralDao {

	private AssetsTree_BSqlBuilder sqlbuilder = null;
	public AssetsTree_BDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlbuilder = (AssetsTree_BSqlBuilder) sqlBuilder;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Comment: STORY49928产品树形结构界面优化
	 * @description : 重写查询分页事件
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

		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			
			sql = sqlbuilder.getQueryConditionSql(paraNameList);

			/*- Start Added by huangsq 20170905 添加关联状态表 */
			if (showState != null && showState.isShow() && !showState.getIsPlm()) {
				sql = buildStateSql(sql, this.sqlbuilder.getTableName(this.dbNameResolver));
			} else if(showState != null && showState.getIsPlm()){
				sql = buildRelaSql(sql, this.sqlbuilder.getTableName(this.dbNameResolver), showState);
			}
			/*- End Added by huangsq 添加关联状态表 */

			if (page != null) {
				sql = buildPagingSql(sql, page);
			}
			
			// 拼接查询执行sql 
			sql = sqlbuilder.getQuerySql(sql, paraMap);
			
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equals(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/
					if ("N_AUTHORG".equals(valueFieldName)) {
						continue;
					}

					//edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总
					if ("ARRAY_C_PORT_CODE_RIGHT".equals(valueFieldName)) {
						continue;
					}
					
					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (java.util.Date.class.equals(paraValue)) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
				
				//edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总
				if(paraMap.containsKey("ARRAY_C_PORT_CODE_RIGHT")){
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get("ARRAY_C_PORT_CODE_RIGHT")),conn));
					index++;
				}
			}
			
			rs = pstmt.executeQuery();
			BasePojo pojo = (BasePojo) clazz.newInstance();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				BasePojo t = this.ResultToBean(rs, clazz, props);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
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
	
	@Override
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap, Class<?> clazz) {
		return this.queryByConditionPage(paraMap, null, clazz);
	}
	
	/**
	 * edit by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
	 * @param rs
	 * @param pojoClass
	 * @return
	 * @throws Exception
	 */
	public BasePojo ResultToBean(ResultSet rs, Class<?> pojoClass, PropertyDescriptor[] props) throws Exception {
		BasePojo pojo = (BasePojo) pojoClass.newInstance();
		PropertyDescriptor prop = null;

		String name = "";
		Object resValue = null;

		for (int i = 0; i < props.length; i++) {
			prop = props[i];
			if (DaoAssistance.isSetValue(prop)) {
				name = prop.getName();

				if (pojo instanceof IEffectivable) {
				} else {
					if ("startUseDate".equals(name)
							|| "endUseDate".equals(name)) {
						continue;
					}
				}
				String columnName = "";
				try{
					columnName = this.sqlbuilder.getColumnNameByProperty(this.dbNameResolver, name);
					/**
					 * add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
					 * isParent只用于判断当前数据是否为父级节点。不报存至数据库
					 */
					if ("".equals(columnName) && "isParent".equals(name)){
						columnName = name;
					}
				}
				catch(Exception ex){
				}
				if (!"".equals(columnName)) {
					try {
						resValue = rs.getObject(columnName);
					} catch (Exception e) {
						resValue = null;
					}

					if (resValue != null) {
						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(pojo, resValue);
						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);
						}
					}
				}
			}
		}

		return pojo;
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-20
	 * Status : Add
	 * Comment: STORY49928产品树形结构界面优化
	 * @description : 重写查询条数事件
	 */
	@Override
	public int queryByConditionCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			

			/*- Start Added by huangsq 20170905 添加关联状态表 */
			if (showState != null && showState.isShow() && !showState.getIsPlm()) {	//如果有状态控制，并且非plm查询，过滤plm数据
				sql = sqlbuilder.getQueryConditionSql(paraNameList);
				sql = buildStateSql(sql, this.sqlbuilder.getTableName(this.dbNameResolver));
				sql = "select count(*) from (" + sql +")";
			} else if(showState != null && showState.getIsPlm()){	//如果是plm查询，不管有没有状态控制，都要过滤掉客户端系统数据。
				sql = sqlbuilder.getQueryConditionSql(paraNameList);
				sql = buildRelaSql(sql, this.sqlbuilder.getTableName(this.dbNameResolver), showState);
				sql = "select count(*) from (" + sql +")";
			} else {	//如果没有状态控制，并且非plm查询，则所有数据可见。
				sql = sqlbuilder.getQueryConditionCountSql(paraNameList);
			}
			
			/*- End Added by huangsq 添加关联状态表 */

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue = null;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equals(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/
					if ("N_AUTHORG".equals(valueFieldName)) {
						continue;
					}
					
					//edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总
					if ("ARRAY_C_PORT_CODE_RIGHT".equals(valueFieldName)) {
						continue;
					}
					
					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (java.util.Date.class.equals(paraValue)) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
				
				//edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总
				if(paraMap.containsKey("ARRAY_C_PORT_CODE_RIGHT")){
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get("ARRAY_C_PORT_CODE_RIGHT")),conn));
					index++;
				}
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recCount = rs.getInt(1);
			}

		} catch (Exception ex) {
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return recCount;
	}
	
	/**
	 * add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
	 * 待拖入行组合信息与所属拖动行组合信息对应的资产类型是否为同一类型
	 * @param portCode		待拖入行组合信息
	 * @param dragPortCode	所属拖动行组合信息
	 * @return
	 */
	public String isSameAssetType(String portCode, String dragPortCode){
		String flag = "false";
		PortCache portCache =CacheManager.getInstance().getCache(CacheGroup.PORT);
		if (null != portCache) {
			if(null != portCache
					.getCacheByKey(portCode)){
				if(portCache
						.getCacheByKey(portCode).getC_DAT_CODE().equals(portCache
						.getCacheByKey(dragPortCode).getC_DAT_CODE())){
					flag = "true";
				}
			}else{
				////如果待拖入行信息为父节点，则portCode存储的是资产类型。
				if(portCode.equals(portCache
						.getCacheByKey(dragPortCode).getC_DAT_CODE())){
					flag = "true";
				}
			}
			
		}
		
		return flag;
	}
	
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Task   : STORY49928产品树形结构界面优化
	 * Comment: 删除或者更新组合信息对应树形结构节点
	 * @param id 		行ID
	 * @param trCode	结构代码
	 * @param isParent	更新行是否为父级节点行
	 * @param type		执行类型：DELETE更新，DELETE删除
	 * @return
	 */
	public int updateOrdelete(String id, String trCode, String isParent, String type){
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = this.loadNewConnection();
			String sql = sqlbuilder.getExecuteSql(type, isParent, trCode);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			count = pstmt.executeUpdate();
		}catch(Exception ex){
			throw new BusinessException("删除或者更新组合信息对应树形结构节点失败!" + ex.getMessage(), ex);
		}finally{
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return count;
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-06-13
	 * Status : Add
	 * Task   : BUG206147节点code不能与组合代码相同
	 * Comment: 查询顶级根节点下的所有子节点
	 * @param topParentCode	顶级根节点代码
	 * @return
	 */
	public Map<String, String> quertAllNodeCode(String topParentCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		Map<String, String> dataMap = new HashMap<String, String>();
		try {
			conn = this.loadNewConnection();
			sql = "SELECT C_TR_CODE FROM T_P_AB_ASS_TR WHERE C_TR_CODE_R = ?";
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, topParentCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String trCode = rs.getString("C_TR_CODE");
				if(!dataMap.containsKey(trCode)){
					dataMap.put(trCode, VocabularyConsts.Blank);
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询根节点下的所有子节点失败：" + ex.getMessage(), ex);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
			DbFun.releaseConnection(conn);
		}
		
		return dataMap;
	}
	
	/**
	 * STORY39490【南方基金】产品树形结构实现私有层面设置，并且针对私有层面的不用审核
	 * @Title getUserId 
	 * @Description 
	 * @author liulei@ysstech.com
	 * @date 2017年4月6日上午9:09:26
	 * @param quyCon
	 * @return
	 * @throws SQLException 
	 */
	public String getUserId(String quyCon) throws SQLException{
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		String value = null;
		try {
			String sql = " select a.c_user from T_P_AB_ASS_TR a where c_tr_code = ? ";
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, quyCon.toString());
			rs = pst.executeQuery();
			if (rs.next()) {
				value = rs.getString("c_user"); 
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return value;
	}
	
	public String getCodeByCId(String id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		String value = null;
		try {
			String sql = " select a.c_tr_code from T_P_AB_ASS_TR a where c_iden = ? ";
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				value = rs.getString("c_tr_code"); 
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.log(e.getMessage(), e);
		}
		finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return value;
	}
	
}
