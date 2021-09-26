package com.yss.ams.sec.information.modules.mp.indexmp.dao;

import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
/**
 * 指数行情资料 dao
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class IndexMpDao extends GeneralDao {

	public IndexMpDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

}
