package com.yss.ams.product.information.fast.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年4月4日 下午3:28:32
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastCommonInfoSqlBuilder implements SQLBuilder {

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param dbNameResolver
	 * @param @return
	 * @param @deprecated
	 */
	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param dbNameResolver
	 * @param @return
	 * @param @deprecated
	 */
	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param dbNameResolver
	 * @param @return
	 * @param @deprecated
	 */
	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param dbNameResolver
	 * @param @return
	 * @param @deprecated
	 */
	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param dbNameResolver
	 * @param @return
	 */
	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param dbNameResolver
	 * @param @param name
	 * @param @return
	 */
	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param dbNameResolver
	 * @param @return
	 */
	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param dbNameResolver
	 * @param @return
	 */
	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param paraNameList
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:28:32
	 * @param @param paraNameList
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDictDataByKey() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT C_GROUP_CODE, C_GROUP_NAME, C_GROUP_CODE_P ");
		buf.append(" FROM T_V_D_GROUP WHERE C_DV_SCENE = ? ");
		buf.append(" AND N_CHECK_STATE = 1 ");
		return buf.toString();
	}
}
