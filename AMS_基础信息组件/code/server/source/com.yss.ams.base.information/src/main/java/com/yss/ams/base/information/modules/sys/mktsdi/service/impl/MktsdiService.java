package com.yss.ams.base.information.modules.sys.mktsdi.service.impl;

import java.util.List;

import com.yss.ams.base.information.modules.sys.mktsdi.dao.MktsdiDao;
import com.yss.ams.base.information.modules.sys.mktsdi.dao.MktsdiSqlBuilder;
import com.yss.ams.base.information.support.sys.mktsdi.service.IMktsdiService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
//import com.yss.para.bi.mktsdi.dao.MktsdiDao;
//import com.yss.para.bi.mktsdi.dao.MktsdiSqlBuilder;
//import com.yss.para.bi.mktsdi.service.IMktsdiService;

/**
 * 交易市场字典t_s_mkt_var Service
 *
 */
public class MktsdiService extends ServiceBus<MktsdiService> implements IMktsdiService {

	/**
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示
	 */
	private MktsdiDao mktsdiDao;

	public MktsdiService() {
		mktsdiDao = new MktsdiDao(DbPoolFactory.getInstance().getPool(), new MktsdiSqlBuilder());
		dao = mktsdiDao;
	}

	/**
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示<br />
	 * 重写根据主键更新对象方法<br />
	 * modify 20160801 不覆盖updateById方法，添加方法updateByPk
	 */
	@Override
	public String updateByPk(List<BasePojo> pojoList) {
		mktsdiDao.updateByPk(pojoList);
		return "";
	}

}
