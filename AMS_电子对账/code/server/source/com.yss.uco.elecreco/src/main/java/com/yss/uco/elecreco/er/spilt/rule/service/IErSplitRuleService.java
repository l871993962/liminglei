package com.yss.uco.elecreco.er.spilt.rule.service;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.uco.elecreco.er.spilt.rule.pojo.ErSplitRule;
import com.yss.uco.elecreco.er.spilt.rule.vo.ErSplitRuleVo;

@RestfulSupported
public interface IErSplitRuleService extends IServiceBus  {
	/**
	 * 更新映射规则，界面保存按钮调用的入口
	 * @param addRules
	 * @param removeRules
	 * @return
	 * @throws Exception
	 */
	@LinkControllerMethod(value = "updateRelaDetailKmInfo", arguTypes = { ErSplitRuleVo.class })
	public String updateRelaDetailKmInfo(@LinkControllerMethodArgu("addRules")List<ErSplitRule> addRules,@LinkControllerMethodArgu("removeRules")List<ErSplitRule> removeRules);
	/**
	 * 显示该组合在该日期没有设置拆分规则的明细科目
	 * @param portCode
	 * @param date
	 * @return
	 */
	public QueryRes showUnSplitDetailKmInfo(String portCode,String date);
	/**
	 * 
	 * @param portCode
	 * @param tghCode
	 * @param splitCode
	 * @return
	 */
	public QueryRes showRelaDetailKmInfo(String id);
	/**
	 * 获取该拆分映射更新对应的拆分规则
	 * @param rela
	 * @return
	 */
	public List<ErSplitRule> getSplitRulesBySplitRela(ErSplitRela rela);
	/**
	 * 获取托管科目列头
	 * @return
	 */
	public List<HeadKey> getTghKmTableHeadKeys();
	/**
	 * 获取组合科目列头
	 * @return
	 */
	public List<HeadKey> getPortKmTableHeadKeys();
}