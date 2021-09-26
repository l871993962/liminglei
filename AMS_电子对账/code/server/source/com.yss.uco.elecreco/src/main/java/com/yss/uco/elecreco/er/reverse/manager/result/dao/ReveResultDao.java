package com.yss.uco.elecreco.er.reverse.manager.result.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.uco.elecreco.er.reverse.cons.ReveDzCons;
import com.yss.uco.elecreco.er.reverse.manager.result.pojo.ReveResult;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.dao.ResRelaDao;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.dao.ResRelaSqlBuilder;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.pojo.ResRela;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;

public class ReveResultDao extends GeneralDao  {

private ReveResultSqlBuilder sqlBuilder = null;
	public ReveResultDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ReveResultSqlBuilder) sqlBuilder;
	}
	@Override
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		// TODO Auto-generated method stub
		return this.queryByConditionPage(paraMap,null, clazz);
	}
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;
		Map<String,ReveResult> map = new HashMap<String, ReveResult>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, new FBResRelaSqlBuilder());
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);

			sql = this.sqlBuilder.getQueryConditionSql(paraNameList, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
//			long begin = System.currentTimeMillis();
//			System.out.println("打开结果集当前时间戳:" + begin);
			rs = pstmt.executeQuery();
//			long mid = System.currentTimeMillis();
//			System.out.println("打开结果集完成，花费时间:" + (mid - begin) + "ms");
			
			//BasePojo pojo = (BasePojo) clazz.newInstance();
			ReveResult result = new ReveResult();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(result);
			
			while (rs.next()) {
				BasePojo t = this.setResultSet(rsTools, rs, clazz, props);
				getConvertKey(props, t);
				result = (ReveResult) t;
				if(map.containsKey(result.getId()))
				{
					ReveResult result2 = map.get(result.getId());
					result2.getList_RESRELA_INNER().addAll(result.getList_RESRELA_INNER());
					result2.getList_RESRELA_OUT().addAll(result.getList_RESRELA_OUT());
				}else
				{
					map.put(result.getId(), result);
					pojoList.add(result);
				}
			}

//			long end = System.currentTimeMillis();
//			System.out.println("结果集滚动完成，花费时间： " + (end - mid) + "ms");

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
	protected BasePojo setResultSet(ResultSetTools rsTools, ResultSet rs, Class<?> clazz, 
			PropertyDescriptor[] props) throws Exception{
		//return rsTools.ResultToBean(rs, clazz, props);
		BasePojo pojo = (BasePojo) clazz.newInstance();
		PropertyDescriptor prop = null;
		List<ResRela> inners = new ArrayList<ResRela>();
		List<ResRela> outs = new ArrayList<ResRela>();
		ReveResult result = (ReveResult)pojo;
		result.setList_RESRELA_INNER(inners);
		result.setList_RESRELA_OUT(outs);
		String name = "";
		Object resValue = null;

		for (int i = 0; i < props.length; i++) {
			prop = props[i];
			if (DaoAssistance.isSetValue(prop)) {
				name = prop.getName();

				if (pojo instanceof IEffectivable) {
				} else {
					if ("startUseDate".equalsIgnoreCase(name)
							|| "endUseDate".equalsIgnoreCase(name)) {
						continue;
					}
					if ("list_RESRELA_OUT".equalsIgnoreCase(name)
							|| "list_RESRELA_INNER".equalsIgnoreCase(name)) {
						continue;
					}
				}
				String columnName = "";
				try{
					columnName = this.sqlbuilder.getColumnNameByProperty(this.dbNameResolver, name);
				}
				catch(Exception ex){
				}
				if (!"".equalsIgnoreCase(columnName)) {
					try {
						resValue = rs.getObject(columnName);
					} catch (Exception e) {
						resValue = null;
						//throw new Exception(e.getMessage() + " : " + columnName);
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
		ResRela res = new ResRela();
		PropertyDescriptor[] resprops = PojoUtils.getPropertyDescriptors(res);
		BasePojo resPojo = rsTools.ResultToBean(rs, res.getClass(), resprops);
		res = (ResRela) resPojo;
		if(ReveDzCons.REVE_KMFW_INNER.equals(res.getC_DV_KM_SCOPE()))
		{
			result.getList_RESRELA_INNER().add(res);
		}else if(ReveDzCons.REVE_KMFW_OUT.equals(res.getC_DV_KM_SCOPE()))
		{
			result.getList_RESRELA_OUT().add(res);
		}
		return pojo;
	}
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
			sql = this.sqlBuilder.getQueryConditionCountSql(paraNameList, null);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue = null;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

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
	@Override
	public <T extends BaseBean> List<String> insert(List<T> list,
			Connection conn) throws DataAccessException {
		List<String> cidenList = new ArrayList<String>();
		List<ResRela> relas = new ArrayList<ResRela>();
		ReveResult result = null;

		try {
			for(T t : list)
			{
				String id = this.insert(t, conn);
				cidenList.add(id);
				result = (ReveResult) t;
				for(ResRela rela : result.getList_RESRELA_INNER())
				{
					rela.setC_RESULT_RELA(id);
					relas.add(rela);
				}
				for(ResRela rela : result.getList_RESRELA_OUT())
				{
					rela.setC_RESULT_RELA(id);
					relas.add(rela);
				}
			}
			new ResRelaDao(pool, new ResRelaSqlBuilder()).insert(relas, conn);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (DataAccessException)ex;
			}else{
				logger.error("插入失败：" + ex.getMessage());
				throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
			}
		} 
		return cidenList;
		//return super.insert(list, conn);
	}
	
	
	

}