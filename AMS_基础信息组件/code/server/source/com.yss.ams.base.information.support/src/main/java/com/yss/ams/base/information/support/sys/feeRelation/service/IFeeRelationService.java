package com.yss.ams.base.information.support.sys.feeRelation.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * @classDesc 费用关联字典普通服务接口，主要进行增删改查操作
 * @version 1.0 2012-9-22
 * @author yh
 */
/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
@RestfulSupported
public interface IFeeRelationService extends IServiceBus {
	
	/**
	 * 根据条件查询费用关联字段实体
	 * 
	 * @param paraMap 参数集合
	 * 
	 * @return 查询结果对象
	 */
	public List<BasePojo> queryRealDateByCondition(HashMap<String, Object> paraMap);
	
	/**
	 * 根据条件查询，用于查询费用关联界面
	 * 
	 * @param paraMap 参数集合
	 * 
	 * @return 查询结果对象
	 */
	public QueryRes queryByConditionFee(HashMap<String, Object> paraMap);
}
