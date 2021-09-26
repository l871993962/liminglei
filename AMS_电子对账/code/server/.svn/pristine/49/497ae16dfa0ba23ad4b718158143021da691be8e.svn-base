package com.yss.uco.elecreco.er.repcfg.dao;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.uco.elecreco.support.bean.DzRepCfgInsert;

public class DzRepCfgDao extends GeneralDao {

	public DzRepCfgDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	@Override
	public <T extends BaseBean> List<String> insert(List<T> list) {
		ArrayList<DzRepCfgInsert> insertPojos = new ArrayList<DzRepCfgInsert>();
		for(T dzRepCfg : list) {
			insertPojos.add(new DzRepCfgInsert((DzRepCfgInsert)dzRepCfg));
		}
		List<String> ret = super.insert(insertPojos);
		return ret;
	}

	@Override
	public <T extends BasePojo> void updateById(List<T> pojoList) {
		ArrayList<DzRepCfgInsert> insertPojos = new ArrayList<DzRepCfgInsert>();
		for(T dzRepCfg : pojoList) {
			insertPojos.add(new DzRepCfgInsert((DzRepCfgInsert)dzRepCfg));
		}
		super.updateById(insertPojos);
	}

	/**
	 * STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
	 * 删除时后台报错，重写插入到回收站表的方法
	 */
	@Override
	public void saveDelRecord(List<BasePojo> pojoList) {
		ArrayList<BasePojo> insertPojos = new ArrayList<BasePojo>();
		for(BasePojo dzRepCfg : pojoList) {
			insertPojos.add(new DzRepCfgInsert((DzRepCfgInsert)dzRepCfg));
		}
		super.saveDelRecord(insertPojos);
	}
	
}
