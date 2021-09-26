package com.yss.ams.sec.information.support.modules.sv.base.service;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 同业拆借信息 接口
 * @author 马向峰
 *
 */
@RestfulSupported
public interface ISecBaseCjService extends IServiceBus {

	/**
	 * add by zhd 2016-09-07
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 查询实际所属证券
	 * @return
	 */
	public List<BasePojo> queryForSjsszq();
	
	/**
	 * add by zhd 2016-09-09
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 重写插入
	 */
	public String insert(List<BasePojo> pojoList);
	
	/**
	 * add by zhd 2016-09-09
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 重写更新
	 */
	public String updateById(List<BasePojo> pojoList);
}
