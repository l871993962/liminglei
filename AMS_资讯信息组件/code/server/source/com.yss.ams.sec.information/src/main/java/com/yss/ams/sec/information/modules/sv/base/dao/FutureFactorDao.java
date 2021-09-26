package com.yss.ams.sec.information.modules.sv.base.dao;

import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 期货结算债券转换信息 dao
 * @author gongyue
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public class FutureFactorDao extends GeneralDao{

	public FutureFactorDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

}
