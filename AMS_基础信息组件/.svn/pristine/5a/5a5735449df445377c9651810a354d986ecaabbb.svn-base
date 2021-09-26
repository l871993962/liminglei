package com.yss.ams.base.information.modules.sys.mktsdi.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.sys.mktsdi.pojo.Mktsdi;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.JdbcSupport;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidDataException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.AutoDateProc;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.SysUtil;
//import com.yss.para.bi.mktsdi.pojo.Mktsdi;

/**
 * 交易市场字典t_s_mkt_var dao
 *
 */
public class MktsdiDao extends GeneralDao{

	/**
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示
	 */
	private JdbcSupport jdbcSupport;

	public MktsdiDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		jdbcSupport = new JdbcSupport(pool);
	}


	/**
	 * Author : ChenLong
	 * Date   : 2013-11-18
	 * Status : Add
	 * Comment: 插入数据的CIDEN返回值集合
	 * */
	@Override
	public <T extends BaseBean> List<String> insert(List<T> list, Connection conn)
			throws DataAccessException {
		PreparedStatement pstmt = null;
		boolean sqlIsWaiting = false;
		
		/*
		 * Author : ChenLong
		 * Date   : 2013-11-18
		 * Status : Add
		 * Comment: 插入数据的CIDEN返回值集合
		 * */
		List<String> cidenList = new ArrayList<String>();
		try {

			for (T baseBean : list) {
				if (baseBean instanceof IEffectivable) {
					autDateProc = new AutoDateProc(dbNameResolver, sqlbuilder,
							conn);
					autDateProc.effectiveData((ParamPojo) baseBean);
				}

				PropertyDescriptor[] proDescriptors = this
						.getPropertyDescriptors(baseBean);

				StringBuffer fieldNames = new StringBuffer();
				StringBuffer wildcards = new StringBuffer();

				for (PropertyDescriptor prop : proDescriptors) {
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}
					if ("id".equals(prop.getName())) {
						continue;
					}

					if (!SysUtil.isBaseType(prop.getPropertyType())) {
						continue;
					}
					if ("primeKey".equals(prop.getName())) {
						continue;
					}

					this.buildFieldByComma(fieldNames, prop, baseBean);
					this.buildWildcardsByComma(wildcards, prop, baseBean);
				}

				if (sqlIsWaiting == false) {
					StringBuffer sqlBuffer = new StringBuffer();
					sqlBuffer.append("insert into ");
					sqlBuffer.append(this.sqlbuilder
							.getTableName(this.dbNameResolver));

					if (wildcards.length() > 0) {
						sqlBuffer.append(" (");
						sqlBuffer.append(fieldNames.substring(0, (fieldNames
								.length() - 1)));
						sqlBuffer.append(")");
						sqlBuffer.append(" values ");
						sqlBuffer.append(" (");
						sqlBuffer.append(wildcards.substring(0, (wildcards
								.length() - 1)));
						sqlBuffer.append(")");
					} else {
						throw new InvalidDataException(baseBean.getClass()
								.toString()
								+ "的实例没有属性值");
					}

					pstmt = conn.prepareStatement(sqlBuffer.toString());

					sqlIsWaiting = true;
				}

				int index = 1;
				for (int i = 0; i < proDescriptors.length; i++) {
					PropertyDescriptor prop = proDescriptors[i];
					if (!DaoAssistance.isAppendEffectDate(baseBean, prop
							.getName())) {
						continue;
					}
					// 去掉getClass方法
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}
					if ("id".equals(prop.getName())) {
						continue;
					}

					if (!SysUtil.isBaseType(prop.getPropertyType())) {
						continue;
					}

					if ("primeKey".equals(prop.getName())) {
						continue;
					}

					index = this.setFieldsValue(prop, baseBean, index, pstmt);
				}
				if(pstmt != null){
					pstmt.addBatch();
				}
			}
			if(pstmt != null){
				pstmt.executeBatch();
				pstmt.clearBatch();//addbyleeyu20151015
			}
		} catch (Exception ex) {
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		return cidenList;
	}
	
	/**
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示<br />
	 * 重写根据主键更新对象方法<br />
	 * modify 20160801 不覆盖updateById方法，添加方法updateByPk
	 */
	public <T extends BasePojo> void updateByPk(T basePojo) throws DataAccessException {
		if (basePojo == null) {
			throw new InvalidParametersException("baseBean数据实例不能为空");
		}

		List<T> pojoList = new ArrayList<T>();
		pojoList.add(basePojo);
		this.updateById(pojoList);
	}

	/**
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示<br />
	 * 重写根据主键更新对象方法<br />
	 * modify 20160801 不覆盖updateById方法，添加方法updateByPk
	 */
	public <T extends BasePojo> void updateByPk(List<T> pojoList) throws DataAccessException {
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}
			Object[][] args = new Object[pojoList.size()][];
			for (int i = 0; i < pojoList.size(); i++) {
				Mktsdi mktsdi = (Mktsdi) pojoList.get(i);
				args[i] = new Object[] { mktsdi.getC_MKT_NAME(), mktsdi.getC_DV_MKT_TYPE(), mktsdi.getC_DV_STATE(),
						mktsdi.getC_MKT_CODE() };
			}
			String updateSql = "update T_S_MKT_VAR set C_MKT_NAME=?, C_DV_MKT_TYPE=?, C_DV_STATE=? where C_MKT_CODE=?";
			jdbcSupport.beginTransaction();
			jdbcSupport.executeBatch(updateSql, 100, args);
			jdbcSupport.commit();
		} catch (Exception ex) {
			jdbcSupport.rollback();
			logger.error(ex.getMessage());
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		}
	}

}
