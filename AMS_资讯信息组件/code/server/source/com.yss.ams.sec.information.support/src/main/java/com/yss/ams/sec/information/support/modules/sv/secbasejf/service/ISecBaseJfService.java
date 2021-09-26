package com.yss.ams.sec.information.support.modules.sv.secbasejf.service;

import java.util.HashMap;

import com.yss.ams.sec.information.support.modules.sv.secbasejf.vo.QueryPortRelaChargingSecVo;
import com.yss.ams.sec.information.support.modules.sv.secbasejf.vo.QueryRelaChargingSecVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * STORY #31596 运营费用-支持资产净值扣不计费证券需求
 * 
 * 计费证券信息接口
 * 
 * @author HeLiang
 *
 */

/**
 * 计费证券信息普通服务接口，主要进行增删改查操作
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 *
 */
@RestfulSupported
public interface ISecBaseJfService extends IServiceBus {

	/**
	 * 嵌套窗体组合关联计费证券信息SET界面查询
	 * @param paraMap
	 * @return
	 */
//	public QueryRes getPortRelaChargingSec(HashMap<String,Object> paraMap);
		
	/**
	 * 嵌套窗体LIST界面查询计费证券信息
	 * @author HeLiang
	 * @date 2016-09-06
	 * @param paraMap
	 * @param pageIns
	 * @return QueryRes
	 */
	@LinkControllerMethod(value="queryRelaChargingSec",arguTypes = QueryRelaChargingSecVo.class)
	public QueryRes queryRelaChargingSec(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap, @LinkControllerMethodArgu("pageIns")PageInation pageIns);
	
	/**
	 * 嵌套窗体【选择】窗体查询计费证券信息
	 * @author HeLiang
	 * @date 2016-09-06
	 * @param paraMap
	 * @param pageIns
	 * @return QueryRes
	 */
	@LinkControllerMethod(value="queryPortRelaChargingSec",arguTypes = QueryPortRelaChargingSecVo.class)
	public QueryRes queryPortRelaChargingSec(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("pageIns")PageInation pageIns) throws Exception;
	
}
