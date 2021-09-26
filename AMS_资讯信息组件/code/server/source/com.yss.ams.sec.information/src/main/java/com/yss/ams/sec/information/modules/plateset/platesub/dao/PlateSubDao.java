package com.yss.ams.sec.information.modules.plateset.platesub.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidDataException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.SysUtil;
import com.yss.framework.util.DateUtil;

/**
 * STORY #33682 彭博证券信息接口_重新设计
 * xiaozhilong 20161122
 */
public class PlateSubDao extends GeneralDao {

	@SuppressWarnings("unused")
	private PlateSubSqlBuilder plateSubSqlBuielder;
	
	public PlateSubDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.plateSubSqlBuielder = (PlateSubSqlBuilder)sqlBuilder;
	}
	/**
	 * 彭博证券信息清算时更新数据需要重写方法，避免基类中的日趋连续性逻辑
	 * 保证数据更新正确
	 * STORY #33682 彭博证券信息接口_重新设计
	 * xiaozhilong 20161122
	 */
	public void updatePlateById(List<BasePojo> pojoList) throws DataAccessException {
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			conn = loadNewConnection();
			conn.setAutoCommit(false);
			for (int i = 0; i < pojoList.size(); i++) {
				BasePojo basePojo = pojoList.get(i);
				if (basePojo instanceof ParamPojo) {
					((ParamPojo) basePojo).setModifyDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				}
				PropertyDescriptor[] proDescriptors = this
						.getPropertyDescriptors(basePojo);
				if (i == 0) {
					StringBuffer sets = new StringBuffer();

					for (PropertyDescriptor prop : proDescriptors) {

						// 去掉getClass方法
						if (prop.getPropertyType()
								.isAssignableFrom(Class.class)) {
							continue;
						}

						if ("id".equals(prop.getName())) {
							continue;
						}
						if (!SysUtil.isBaseType(prop.getPropertyType())) {
							continue;
						}
						logger.debug(prop.getName());
						this.buildFieldByEqual(sets, prop, basePojo);
						this.buildWildcardsByComma(sets, prop, basePojo);
					}

					// 构建查询语句
					StringBuffer sqlBuffer = new StringBuffer();
					sqlBuffer.append("update ");
					sqlBuffer.append(this.sqlbuilder
							.getTableName(this.dbNameResolver));
					sqlBuffer.append(" set ");

					if (sets.length() > 0) {
						sqlBuffer
								.append(sets.substring(0, (sets.length() - 1)));
					} else {
						throw new InvalidDataException(basePojo.getClass()
								.toString()
								+ "的实例没有属性值");
					}

					String columnName = this.sqlbuilder
							.getColumnNameByProperty(dbNameResolver, "id");
					sqlBuffer.append(" where ");
					sqlBuffer.append(columnName);
					sqlBuffer.append("=");
					sqlBuffer.append("?");

					pstmt = conn.prepareStatement(sqlBuffer.toString());
				}
				int index = 1;
				for (PropertyDescriptor dataprop : proDescriptors) {
					// 去掉getClass方法
					if (dataprop.getPropertyType()
							.isAssignableFrom(Class.class)) {
						continue;
					}

					if ("id".equals(dataprop.getName())) {
						continue;
					}
					if (!SysUtil.isBaseType(dataprop.getPropertyType())) {
						continue;
					}
					logger.debug(dataprop.getName());
					index = this.setFieldsValue(dataprop, basePojo, index,
							pstmt);
				}

				String value = basePojo.getId(); //propId.getReadMethod().invoke(basePojo);

				if (value == null) {
					throw new InvalidParametersException("id不能为空");
				}
				if(pstmt != null){
					pstmt.setString(index, value);
					pstmt.executeUpdate();
				}
				conn.commit();
				conn.setAutoCommit(true);
			}
			
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}
	
//	public List<BasePojo> queryPlateSubExtendDataList(HashMap<String, String> paraMap, PageInation page){
//		List<BasePojo> pojoList = new ArrayList<BasePojo>();
//		List<String> paraNameList;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String sql = "";
//		
//		try {
//			paraNameList = getParaName(paraMap);
//			conn = this.loadNewConnection();
//			conn.setAutoCommit(false);
//			sql = this.plateSubSqlBuielder.getPlateSubExtendSql(paraNameList);
//			sql = buildPagingSql(sql, page);
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			if(SqlUtil.isSearchTypeValueExists(paraNameList)){
//				paraNameList.remove(paraNameList.size()-1);
//			}
//			
//			int index = 1;
//			for(String valueFieldName : paraNameList){
//				pstmt.setString(index, paraMap.get(valueFieldName));
//				index++;
//			}
//			
//			rs = pstmt.executeQuery();
//			
//			conn.commit();
//			conn.setAutoCommit(true);
//			
//			while(rs.next()){
//				PlateSubExtend plateSubExtend = new PlateSubExtend();
//				PropertyDescriptor[] propertys = this.getPropertyDescriptors(orgExtend);
//				
//				for(int i = 0; i < propertys.length; i++){
//					PropertyDescriptor prop = propertys[i];
//					if(prop.getPropertyType().isAssignableFrom(Class.class)){
//						continue;
//					}
//					
//					if("c_USER_PWD".equals(prop.getName())){
//						continue;
//					}
//					
//					String name = prop.getName();
//					String columnName = this.plateSubSqlBuielder.getPlateSubExtendColumnName(dbNameResolver, name);
//					Object resValue = rs.getObject(columnName);
//					if(resValue!=null){
//						if(resValue.getClass().equals(byte.class) || resValue.getClass().equals(byte[].class)){
//							resValue = resValue.toString();
//						}else if(resValue.getClass().equals(Timestamp.class)){
//							if(prop.getPropertyType().equals(String.class)){
//								Timestamp time = new Timestamp(DateUtil.stringtoDate(resValue.toString(), DateUtil.FORMAT_ONE).getTime());
//								resValue = DateUtil.dateToString(time, "yyyy-MM-dd");
//							}
//							//YssFun.formatDate(resValue.toString());
//						}
//						
//						if(int.class.equals(prop.getPropertyType())){
//							resValue = new BigDecimal(resValue.toString()).intValue();
//						}
//						
//						prop.getWriteMethod().invoke(plateSubExtend, resValue);
//					}
//					
//				}
//				
//				pojoList.add(plateSubExtend);
//			}
//			
//		} catch (Exception ex) {
//			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
//		} finally {
//			try {
//				if(conn != null){
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return pojoList;
//	}
//	
//	public int queryPlateSubExtendDataListCount(HashMap<String, String> paraMap){
//		List<String> paraNameList;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String sql = "";
//		int recCount = 0;
//		try {
//			paraNameList = getParaName(paraMap);
//			conn = this.loadNewConnection();
//			conn.setAutoCommit(false);
//			sql = this.plateSubSqlBuielder.getPlateSubExtendSqlCount(paraNameList);
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			if(SqlUtil.isSearchTypeValueExists(paraNameList)){
//				paraNameList.remove(paraNameList.size()-1);
//			}
//			
//			int index = 1;
//			for(String valueFieldName : paraNameList){
//				if("N_CHECK_STATE".equals(valueFieldName)){
//					continue;
//				}
//				pstmt.setString(index, paraMap.get(valueFieldName));
//				index++;
//			}
//			
//			rs = pstmt.executeQuery();
//			
//			conn.commit();
//			conn.setAutoCommit(true);
//			
//			while(rs.next()){
//				recCount = rs.getInt("CNT");
//			}
//			
//		} catch (Exception ex) {
//			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
//		} finally {
//			try {
//				if(conn != null){
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return recCount;
//	}
}
