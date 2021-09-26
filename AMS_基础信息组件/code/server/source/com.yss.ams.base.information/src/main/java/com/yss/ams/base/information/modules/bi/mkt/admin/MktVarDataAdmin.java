package com.yss.ams.base.information.modules.bi.mkt.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.mkt.dao.MktVarDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 获取市场代码中文转换
 */

/**
 * 交易市场业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktVarDataAdmin extends BaseAdmin {
	private MktVarDao mktVarDao = null;

	public MktVarDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		mktVarDao = new MktVarDao(pool, sqlBuilder);
	}

	/**
	 * 获取市场代码代码对应中文转换
	 * @param 
	 * @return HashMap
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return mktVarDao.getKeyConvertMap();
	}
	
	/**
	 * 获取多个市场代码代码对应中文转换
	 * @param listKey
	 * @return HashMap
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		return mktVarDao.getKeyConvertMap(listKey);
	}
}
