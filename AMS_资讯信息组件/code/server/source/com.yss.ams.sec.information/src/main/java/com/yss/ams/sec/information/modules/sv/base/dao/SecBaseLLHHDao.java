package com.yss.ams.sec.information.modules.sv.base.dao;

import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 利率互换品种信息 dao
 * @author shiliang
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public class SecBaseLLHHDao extends GeneralDao {
	
	public SecBaseLLHHDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
}
