package com.yss.ams.base.information.support.bi.mkt.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.mkt.pojo.MarketVoc;
import com.yss.ams.base.information.support.bi.mkt.pojo.MktVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 交易市场设置普通服务，主要进行增删改查操作 <br>
 * STORY #41193 基础信息设置组件化拆分<br>
 * @author xiaozhilong
 */
@RestfulSupported
public interface IMktService extends IServiceBus {
	/**
	 * 判断所属市场是否存在
	 * @param MktCode 市场代码
	 * @return
	 */
	String getCheckStatus(String MktCode);
	/**
	 * 根据参数查询交易市场设置
	 * @param paraMap 参数MAP
	 * @param page 分页
	 * @param queryMenuID
	 * @return
	 */
	@LinkControllerMethod(value="selectByConditionExtend",arguTypes = MktVo.class)
	public QueryRes selectByConditionExtend(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,@LinkControllerMethodArgu("page")PageInation page,@LinkControllerMethodArgu("queryMenuID")String queryMenuID);
	/**
	 * 查询所有可用交易市场字典信息
	 * @return 字典信息LIST
	 */
	public List<MarketVoc> getAllMkt();
	/**
	 * STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息<br>
	 * 判断交易市场代码是否与清算机构重复<br>
	 * @param mktCode 交易市场代码
	 * @return
	 */
	public String compareQsjg (String mktCode);
}
