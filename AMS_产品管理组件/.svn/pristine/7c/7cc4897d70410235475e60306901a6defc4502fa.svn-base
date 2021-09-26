//package com.yss.ams.product.information.modules.cp.fax.dao;
//
//import java.beans.PropertyDescriptor;
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.yss.ams.product.information.support.modules.cp.fax.pojo.BaseSealRelaInfo;
//import com.yss.datacheck.enums.CheckFuncGroup;
//import com.yss.framework.api.common.YssConstant;
//import com.yss.framework.api.common.co.AuditableParamPojo;
//import com.yss.framework.api.common.co.BaseBean;
//import com.yss.framework.api.common.co.BasePojo;
//import com.yss.framework.api.common.co.IEffectivable;
//import com.yss.framework.api.common.co.PageInation;
//import com.yss.framework.api.common.co.ParamPojo;
//import com.yss.framework.api.database.DbFun;
//import com.yss.framework.api.database.DbPool;
//import com.yss.framework.api.database.ResultSetTools;
//import com.yss.framework.api.exception.DataAccessException;
//import com.yss.framework.api.mvc.AutoDateProc;
//import com.yss.framework.api.mvc.MvcConstant;
//import com.yss.framework.api.mvc.dao.GeneralDao;
//import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
//import com.yss.framework.api.mvc.dao.sql.SqlUtil;
//import com.yss.framework.api.service.YssServiceFactory;
//import com.yss.framework.api.util.DateUtil;
//import com.yss.framework.api.util.EncryptUtil;
//import com.yss.framework.api.util.JsonUtil;
//import com.yss.framework.api.util.StringUtil;
//import com.yss.framework.db.OraDbTool;
//import com.yss.framework.util.PojoUtils;
//import com.yss.platform.support.seal.pojo.ElecSealRight;
//import com.yss.platform.support.seal.service.IElecSealRightService;
//
///**
// * 签章关联DAO
// * 
// * @ClassName ElecSealRelaDao
// * @Description
// * @author leijianhua@ysstech.com
// * @CreateDate 2017年8月17日下午6:49:17
// * @Version V4.5.0.1
// * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
// */
//public class ElecSealRelaDao extends GeneralDao {
//
//	/**
//	 * 字符串类型常量
//	 */
//	private static final int FIELD_TYPE_STRING = 0;
//
//	/**
//	 * LIST类型常量
//	 */
//	private static final int FIELD_TYPE_LIST = 1;
//
//	/**
//	 * sqlBuilder
//	 */
//	private ElecSealRelaSqlBuilder relaSqlBuilder = null;
//
//	/**
//	 * @Description 构造函数
//	 * @author leijianhua@ysstech.com
//	 * @date 2017年8月17日下午6:49:34
//	 * @param pool
//	 * @param sqlBuilder
//	 */
//	public ElecSealRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
//		super(pool, sqlBuilder);
//		relaSqlBuilder = (ElecSealRelaSqlBuilder) sqlBuilder;
//	}
//
//	@Override
//	public <T extends BaseBean> String insert(T baseBean, Connection conn) throws DataAccessException {
//		String ciden = super.insert(baseBean, conn);
//		BaseSealRelaInfo relaInfo = (BaseSealRelaInfo) baseBean;
//		relaInfo.setId(ciden);
//		insertExtInfo(relaInfo, conn);
//		return ciden;
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap, PageInation page, Class<?> clazz) {
////		List<BasePojo> dataList = super.queryByConditionPage(paraMap, page, clazz);
//		HashMap<String, String> extParaMap = null;
//		if(paraMap.containsKey("EXT_PARA_DICT")){
//			String paraJson = paraMap.get("EXT_PARA_DICT").toString();
//			extParaMap = JsonUtil.toBean(paraJson, HashMap.class);
//			paraMap.remove("EXT_PARA_DICT");
//		}
//		
//		List<BasePojo> pojoList = new ArrayList<BasePojo>();
//		List<String> paraNameList;
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "";
//
//		// Object resValue = null;
//		ResultSetTools rsTools = null;
//		try {
//			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
//			paraNameList = getParaName(paraMap);
//			conn = this.loadNewConnection();
//			// conn.setAutoCommit(false);
//
//			if(extParaMap!= null && extParaMap.size()>0){
//				for(String key : extParaMap.keySet()){
//					paraNameList.add("EXT_" + key);
//				}
//			}
//			
//			sql = sqlbuilder.getQueryConditionSql(paraNameList);
//			sql = buildPagingSql(sql, page);
//
//			pstmt = conn.prepareStatement(sql);
//
//			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
//				paraNameList.remove(paraNameList.size() - 1);
//			}
//			
//			
//			
//			//// 如果SQL中有问号时才进行赋值
//			if (sql.indexOf("?") > -1) {
//				int index = 1;
//				Object paraValue;
//				for (String valueFieldName : paraNameList) {
//					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
//						continue;
//					}
//					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
//					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
//					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
//						continue;
//					}
//					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/
//
//					if (valueFieldName.startsWith("ARRAY_")) {
//						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
//								.valueOf(paraMap.get(valueFieldName)),conn));
//					} else if(valueFieldName.startsWith("EXT_")){
//						String extParaKey = valueFieldName.replaceFirst("EXT_", "");
//						String extParaValue = extParaMap.get(extParaKey);
//						if(extParaKey.startsWith("ARRAY_")) extParaKey = extParaKey.replaceFirst("ARRAY_", "");
//						pstmt.setString(index++, extParaKey);
//						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(extParaValue, conn));
//					}
//					else {
//						paraValue = paraMap.get(valueFieldName);
//						if (java.util.Date.class.equals(paraValue)) {
//							Date dateValue = new Date(
//									((java.util.Date) paraValue).getTime());
//							pstmt.setDate(index, dateValue);
//						} else {
//							pstmt.setObject(index, paraValue);
//						}
//					}
//
//					index++;
//				}
//			}
//			rs = pstmt.executeQuery();
//			
//			BasePojo pojo = (BasePojo) clazz.newInstance();
//			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
//			
//			while (rs.next()) {
//				BasePojo t = setResultSet(rsTools, rs, clazz, props);
//				getConvertKey(props, t);
//				pojoList.add(t);
//			}
//
//		} catch (Exception ex) {
//			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
//		} finally {
//			closeResultSetFinal(rs);
//			closeStatementFinal(pstmt);
//			releaseConnection(conn);
//		}
////		return pojoList;
//		
//		
//		
//		queryExtInfo(pojoList);
//		return pojoList;
//	}
//	
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public int queryByConditionCount(HashMap<String, Object> paraMap) {
//		List<String> paraNameList;
//		
//		HashMap<String, String> extParaMap = null;
//		if(paraMap.containsKey("EXT_PARA_DICT")){
//			String paraJson = paraMap.get("EXT_PARA_DICT").toString();
//			extParaMap = JsonUtil.toBean(paraJson, HashMap.class);
//			paraMap.remove("EXT_PARA_DICT");
//		}
//		
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String sql = "";
//		int recCount = 0;
//		try {
//			paraNameList = getParaName(paraMap);
//			if(extParaMap!= null && extParaMap.size()>0){
//				for(String key : extParaMap.keySet()){
//					paraNameList.add("EXT_" + key);
//				}
//			}
//			
//			conn = this.loadNewConnection();
//			// conn.setAutoCommit(false);
//			sql = sqlbuilder.getQueryConditionCountSql(paraNameList);
//
//			pstmt = conn.prepareStatement(sql);
//
//			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
//				paraNameList.remove(paraNameList.size() - 1);
//			}
//
//			//// 如果SQL中有问号时才进行赋值
//			if (sql.indexOf("?") > -1) {
//				int index = 1;
//				Object paraValue = null;
//				for (String valueFieldName : paraNameList) {
//					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
//						continue;
//					}
//					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
//					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
//					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
//						continue;
//					}
//					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/
//
//					if (valueFieldName.startsWith("ARRAY_")) {
//						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
//								.valueOf(paraMap.get(valueFieldName)),conn));
//					} else if(valueFieldName.startsWith("EXT_")){
//						String extParaKey = valueFieldName.replaceFirst("EXT_", "");
//						String extParaValue = extParaMap.get(extParaKey);
//						if(extParaKey.startsWith("ARRAY_")) extParaKey = extParaKey.replaceFirst("ARRAY_", "");
//						pstmt.setString(index++, extParaKey);
//						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(extParaValue, conn));
//					}
//					else {
//						paraValue = paraMap.get(valueFieldName);
//						if (java.util.Date.class.equals(paraValue)) {
//							Date dateValue = new Date(
//									((java.util.Date) paraValue).getTime());
//							pstmt.setDate(index, dateValue);
//						} else {
//							pstmt.setObject(index, paraValue);
//						}
//					}
//
//					index++;
//				}
//			}
//			
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				recCount = rs.getInt(1);
//			}
//
//		} catch (Exception ex) {
//			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
//		} finally {
//			closeResultSetFinal(rs);
//			closeStatementFinal(pstmt);
//			releaseConnection(conn);
//		}
//
//		return recCount;
//	}
//	
//	
//	@Override
//	public <T extends BasePojo> void deleteById(List<T> pojoList) throws DataAccessException {
//		if (pojoList == null || pojoList.size() == 0)
//			return;
//		Connection conn = null;
//		PreparedStatement pst = null;
//		boolean bTrans = false;
//		String sql = "";
//		try {
//			conn = this.loadNewConnection();
//			conn.setAutoCommit(bTrans);
//			bTrans = true;
//			
//			String[] idArray = new String[pojoList.size()];
//			for(int index = 0; index<pojoList.size(); index++){
//				idArray[index] = pojoList.get(index).getId();
//			}
//
//			sql = relaSqlBuilder.getDeleteBaseSQL();
//			pst = conn.prepareStatement(sql);
//			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(idArray, conn));
//			pst.executeUpdate();
//			
//			//删除相应的扩展信息--BUG #213720 组合关联签章界面点击删除，T_S_ELECSEAL_EXT表中的数据没有删除，且关联字段取值有误
//			deleteExtById(idArray, conn);
//			
//			conn.commit();
//			conn.setAutoCommit(bTrans);
//			bTrans = false;
//		} catch (Exception e) {
//			throw new DataAccessException("删除失败：" + e.getMessage(), e);
//		} finally {
//			DbFun.closeStatementFinal(pst);
//			DbFun.endTransFinal(conn, bTrans);
//			DbFun.releaseConnection(conn);
//		}
//	}
//
//	
//	@Override
//	public <T extends BasePojo> void updateById(List<T> pojoList) throws DataAccessException {
//		Connection conn = null;
//		boolean bTrans = false;
//		try {
//			conn = this.loadNewConnection();
//			conn.setAutoCommit(bTrans);
//			bTrans = true;
//			updateById(pojoList, conn);
//			conn.commit();
//			conn.setAutoCommit(bTrans);
//			bTrans = false;
//		} catch (Exception e) {
//			throw new DataAccessException("更新失败:" + e.getMessage(), e);
//		} finally {
//			DbFun.endTransFinal(conn, bTrans);
//			DbFun.releaseConnection(conn);
//		}
//	}
//	
//	/**
//	 * 根据id更新数据
//	 * @Title updateById 
//	 * @Description 
//	 * @author leijianhua@ysstech.com
//	 * @date 2017年8月18日下午5:46:46
//	 * @param pojoList
//	 * @param conn
//	 * @throws DataAccessException
//	 * @return void
//	 */
//	private <T extends BasePojo> void updateById(List<T> pojoList, Connection conn) throws DataAccessException{
//		PreparedStatement pst = null;
//		String auditCode = "";
//		String auditTime = "";
//		int auditState = 0;
//		String sql = "";
//		try {
//			sql = relaSqlBuilder.getUpdateBaseByIdSQL();
//			pst = conn.prepareStatement(sql);
//			String[] idArray = new String[pojoList.size()];
//			for (int i = 0; i < pojoList.size(); i++) {
//				T basePojo = pojoList.get(i);
//				idArray[i] = basePojo.getId();
//				if (basePojo instanceof ParamPojo) {
//					((ParamPojo) basePojo).setModifyDate(DateUtil
//							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
//				}
//				if (basePojo instanceof IEffectivable) {
//					if (n_check == 0) {
//						autDateProc = new AutoDateProc(dbNameResolver,
//								sqlbuilder, conn);
//						autDateProc.inEffectiveDate((ParamPojo) basePojo);
//						autDateProc.effectiveData((ParamPojo) basePojo,CheckFuncGroup.Edit.toString());
//					}
//				}
//				
//				// 处理当不开启已审核状态时，保存几个值到ＰＯＪＯ中,serviceBus已将值放到第一个POJO中了byleeyu20130718
//				if (basePojo instanceof AuditableParamPojo) {
//					AuditableParamPojo auditPojo = (AuditableParamPojo) basePojo;
//					if (i == 0) {
//						auditCode = auditPojo.getOperator();
//						auditTime = auditPojo.getAuditDate();
//						auditState = auditPojo.getAuditState();
//					} else {
//						auditPojo.setOperator(auditCode);
//						auditPojo.setAuditDate(auditTime);
//						auditPojo.setAuditState(auditState);
//					}
//				}
//				
//				BaseSealRelaInfo info = (BaseSealRelaInfo)basePojo;
//				int index = 1;
//				pst.setString(index++, info.getC_SEAL_CODE());
//				pst.setString(index++, info.getC_FUN_CODE());
//				pst.setString(index++, info.getC_Dis_Page());
//				pst.setString(index++, info.getC_Position());
//				pst.setInt(index++, info.getN_MarginX());
//				pst.setInt(index++, info.getN_MarginY());
//				pst.setInt(index++, info.getN_Width());
//				pst.setInt(index++, info.getN_Height());
//				pst.setString(index++, info.getModifier());
//				pst.setString(index++, info.getModifyDate());
//				pst.setString(index++, info.getOperator());
//				pst.setString(index++, info.getAuditDate());
//				pst.setInt(index++, info.getAuditState());
//				pst.setString(index++, info.getId());
//				pst.executeUpdate();
//				
//				// STORY #46087 电子签章权限设置 特殊处理：先把密码查出来补充完整
//				if ("sealPwdMgr".equalsIgnoreCase(info.getC_FUN_CODE())) {				
//					Map<String, String> extAttrDict = info.getExtAttrDict();
//					IElecSealRightService service =  YssServiceFactory.getInstance()
//							.createService(IElecSealRightService.class);
//					//ElecSealRightService service = new ElecSealRightService();
//					HashMap<String, Object> paraMap = new HashMap<String, Object>();
//					paraMap.put("dataClass", ElecSealRight.class);
//					paraMap.put("C_IDEN", info.getId());
//					List<BasePojo> list = service.queryListByCondition(paraMap);
//					if (list != null && list.size() > 0) {
//						ElecSealRight right = (ElecSealRight)list.get(0);
//						//extAttrDict.put("C_SEAL_PWD",YssCipher.decodeByPassword(right.getC_SEAL_PWD()));
//						extAttrDict.put("C_SEAL_PWD",EncryptUtil.YssCipherOrSM4Decrypt(right.getC_SEAL_PWD(), YssConstant.APP_DB_PASSWORD_KEY));
//					}
//				}
//			}
//			
//			//先删除扩展表对应的，再插入
//			deleteExtById(idArray, conn);
//			
//			for(BasePojo basePojo : pojoList){
//				insertExtInfo((BaseSealRelaInfo)basePojo, conn);
//			}
//		} catch (Exception e) {
//			throw new DataAccessException("更新失败:" + e.getMessage(), e);
//		} finally {
//			DbFun.closeStatementFinal(pst);
//		}
//	}
//	
//	/**
//	 * 查询扩展属性信息
//	 * 
//	 * @Title queryExtInfo
//	 * @Description
//	 * @author leijianhua@ysstech.com
//	 * @date 2017年8月18日下午5:21:49
//	 * @param dataList
//	 * @return void
//	 */
//	private void queryExtInfo(List<BasePojo> dataList) {
//		Connection conn = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		String sql = "";
//		try {
//			String[] idArray = new String[dataList.size()];
//			Map<String, BaseSealRelaInfo> pojoMap = new HashMap<String, BaseSealRelaInfo>();
//			for (int i = 0; i < dataList.size(); i++) {
//				BasePojo basePojo = dataList.get(i);
//				idArray[i] = basePojo.getId();
//				pojoMap.put(basePojo.getId(), (BaseSealRelaInfo) basePojo);
//			}
//
//			conn = this.loadNewConnection();
//			sql = relaSqlBuilder.getQueryExtSQL();
//			pst = conn.prepareStatement(sql);
//			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(idArray, conn));
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				String relaId = rs.getString("C_IDEN_RELA");
//				BaseSealRelaInfo relaInfo = pojoMap.get(relaId);
//				String fieldCode = rs.getString("C_FIELD_CODE");
//				String fieldValue = rs.getString("C_FIELD_VALUE");
//				int type = rs.getInt("N_TYPE");
//				if (type == FIELD_TYPE_STRING) {
//					relaInfo.getExtAttrDict().put(fieldCode, fieldValue);
//				} else if (type == FIELD_TYPE_LIST) {
//					List<String> valueList = null;
//					if (relaInfo.getExtListAttrDict().get(fieldCode) == null) {
//						valueList = new ArrayList<String>();
//						relaInfo.getExtListAttrDict().put(fieldCode, valueList);
//					} else {
//						valueList = relaInfo.getExtListAttrDict().get(fieldValue);
//					}
//					valueList.add(fieldValue);
//				}
//			}
//		} catch (Exception e) {
//			throw new DataAccessException("查询失败：" + e.getMessage(), e);
//		} finally {
//			DbFun.closeResultSetFinal(rs);
//			DbFun.closeStatementFinal(pst);
//			DbFun.releaseConnection(conn);
//		}
//	}
//
//	public static void main(String[] args) throws Exception {
//		String fieldValue = "0123456789012345678901234567890123456789";
//		List<String> fieldValueList = new ArrayList<String>();
//		int length = StringUtil.length(fieldValue);
//		while(length > 10){
//			String firstValue = StringUtil.substring(fieldValue, 0, 10);
//			fieldValueList.add(firstValue);
//			fieldValue = StringUtil.substring(fieldValue, 10, length - 10);
//			length = StringUtil.length(fieldValue);
//		}
//		
//		fieldValueList.add(fieldValue);
//		
//		
//		//System.out.println(StringUtil.join(fieldValueList, ","));
//	}
//	
//	/**
//	 * 插入扩展的信息至扩展表
//	 * 
//	 * @Title insertExtInfo
//	 * @Description
//	 * @author leijianhua@ysstech.com
//	 * @date 2017年8月18日下午4:14:15
//	 * @param relaInfo
//	 * @param conn
//	 * @return void
//	 */
//	private void insertExtInfo(BaseSealRelaInfo relaInfo, Connection conn) {
//		if ((relaInfo.getExtAttrDict() == null || relaInfo.getExtAttrDict().size() == 0)
//				&& (relaInfo.getExtListAttrDict() == null || relaInfo.getExtListAttrDict().size() == 0)) {
//			return;
//		}
//
//		PreparedStatement pst = null;
//		String sql = "";
//		try {
//			sql = relaSqlBuilder.getInsetExtSQL();
//			pst = conn.prepareStatement(sql);
//			String relaId = relaInfo.getId();
//			if (relaInfo.getExtAttrDict() != null && relaInfo.getExtAttrDict().size() > 0) {
//				for (Map.Entry<String, String> entry : relaInfo.getExtAttrDict().entrySet()) {
//					String fieldValue = entry.getValue();
//					List<String> fieldValueList = new ArrayList<String>();
//					int length = StringUtil.length(fieldValue);
//					while(length > 4000){
//						String firstValue = StringUtil.substring(fieldValue, 0, 4000);
//						fieldValueList.add(firstValue);
//						fieldValue = StringUtil.substring(fieldValue, 4000, length - 4000);
//						length = StringUtil.length(fieldValue);
//					}
//					// STORY #46087 电子签章权限设置  密码加密处理 by huangjunxiong 2017-09-13
//					if ("C_SEAL_PWD".equalsIgnoreCase(entry.getKey())) {
//						//fieldValue = YssCipher.encodeByPassword(fieldValue);
//						fieldValue = EncryptUtil.YssCipherOrSM4Encrypt(fieldValue, YssConstant.APP_DB_PASSWORD_KEY);
//					}
//					fieldValueList.add(fieldValue);
//					for(int i=0;i<fieldValueList.size();i++){
//						int index = 1;
//						pst.setString(index++, relaId);
//						pst.setString(index++, entry.getKey());
//						pst.setString(index++, fieldValueList.get(i));
//						pst.setInt(index++, FIELD_TYPE_STRING);
//						pst.setInt(index++, i);
//						pst.executeUpdate();
//					}
//					
//				}
//			}
//
//			if (relaInfo.getExtListAttrDict() != null && relaInfo.getExtListAttrDict().size() > 0) {
//				for (Map.Entry<String, List<String>> entry : relaInfo.getExtListAttrDict().entrySet()) {
//					String fieldCode = entry.getKey();
//					List<String> valueList = entry.getValue();
//					if (valueList != null && valueList.size() > 0) {
//						for (String value : valueList) {
//							int index = 1;
//							pst.setString(index++, relaId);
//							pst.setString(index++, fieldCode);
//							pst.setString(index++, value);
//							pst.setInt(index++, FIELD_TYPE_LIST);
//							pst.setInt(index++, 0);
//							pst.executeUpdate();
//						}
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			throw new DataAccessException("插入失败：" + e.getMessage(), e);
//		} finally {
//			DbFun.closeStatementFinal(pst);
//		}
//	}
//
//	/**
//	 * 根据id删除从表
//	 * 
//	 * @Title deleteExtById
//	 * @Description
//	 * @author leijianhua@ysstech.com
//	 * @date 2017年8月18日下午5:38:07
//	 * @param idArray
//	 * @param conn
//	 * @return void
//	 */
//	public void deleteExtById(String[] idArray, Connection conn) {
//		PreparedStatement pst = null;
//		String sql = "";
//		try {
//			sql = relaSqlBuilder.getDeleteExtSQL();
//			pst = conn.prepareStatement(sql);
//			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(idArray, conn));
//			pst.executeUpdate();
//		} catch (Exception e) {
//			throw new DataAccessException("删除失败：" + e.getMessage(), e);
//		} finally {
//			DbFun.closeStatementFinal(pst);
//		}
//
//	}
//
//}
