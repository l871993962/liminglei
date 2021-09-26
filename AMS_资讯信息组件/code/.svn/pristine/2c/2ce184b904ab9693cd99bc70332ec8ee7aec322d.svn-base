package com.yss.ams.sec.information.modules.mp.hggthq.service.impl;

import java.util.Date;
import java.util.List;

import com.yss.ams.sec.information.modules.mp.hggthq.dao.CounterRateDao;
import com.yss.ams.sec.information.modules.mp.hggthq.dao.CounterRateSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.hggthq.pojo.CounterRate;
import com.yss.ams.sec.information.support.modules.mp.hggthq.service.ICounterRateService;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
/**
 * 回购收益行情 实现服务类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class CounterRateService extends ServiceBus<CounterRateService>
		implements ICounterRateService {
	private CounterRateDao serviceDao = null;

	public CounterRateService() {
		serviceDao = new CounterRateDao(DbPoolFactory.getInstance().getPool(),
				new CounterRateSqlBuilder());
		dao = serviceDao;
	}

	/**
	 * 根据公共回购收益行情变更对应证券利率
	 * @param mktDate 行情日期
	 * @param duration 回购期限
	 * @param rate 变更后利率
	 */
	@Override
	public int updateSecRate(Date mktDate, int duration, double rate) {
		return serviceDao.updateSecRate(mktDate, duration, rate);
	}
	
	/**
	 * 根据id更新数据（批量）
	 * 
	 * @param pojoList
	 *            代更新数据列表
	 * @return 操作结果
	 */
	@Override
	public String updateById(List<BasePojo> pojoList) {
		String result = super.updateById(pojoList); 
		for (BasePojo basePojo : pojoList) {
			CounterRate counterRate = (CounterRate) basePojo;
			if (counterRate.getC_IS_PUBLIC().equals(VocabularyConsts.ZHEN)) {
				serviceDao.updateSecRate(counterRate.getD_MKT(),
						counterRate.getN_DURATION(), counterRate.getN_RATE());
			}
		}
		return result;
	}
	
	/**
	 * 根据id审核数据（批量）
	 * 
	 * @param pojoList
	 *            数据对象列表
	 * @return 操作结果
	 */
	@Override
	public String auditById(List<BasePojo> pojoList) {
		String result = super.auditById(pojoList);
		for (BasePojo basePojo : pojoList) {
			CounterRate counterRate = (CounterRate) basePojo;
			if (counterRate.getC_IS_PUBLIC().equals(VocabularyConsts.ZHEN)) {
				serviceDao.syncSecRate(counterRate.getD_MKT(),
						counterRate.getN_DURATION(), "Audit");
			}
		}
		return result;
	}
	
	/**
	 * 根据Id反审核数据
	 * 
	 * @param pojoList
	 *            数据列表
	 */
	@Override
	public String unAuditById(List<BasePojo> pojoList) {
		String result = super.unAuditById(pojoList);
		for (BasePojo basePojo : pojoList) {
			CounterRate counterRate = (CounterRate) basePojo;
			if (counterRate.getC_IS_PUBLIC().equals(VocabularyConsts.ZHEN)) {
				serviceDao.syncSecRate(counterRate.getD_MKT(),
						counterRate.getN_DURATION(), "UnAudit");
			}
		}
		return result;
	}
	
	/**
	 * 根据id删除数据（批量）
	 * 
	 * @param pojoList
	 *            数据对象列表
	 * @return 操作结果
	 */
	@Override
	public String deleteById(List<BasePojo> pojoList) {
		String result = super.deleteById(pojoList);
		for (BasePojo basePojo : pojoList) {
			CounterRate counterRate = (CounterRate) basePojo;
			if (counterRate.getC_IS_PUBLIC().equals(VocabularyConsts.ZHEN)) {
				serviceDao.syncSecRate(counterRate.getD_MKT(),
						counterRate.getN_DURATION(), "Delete");
			}
		}
		return result;
	}
}
