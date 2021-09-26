package com.yss.ams.base.information.support.sys.secvar.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVarVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;


/**
 * 证券品种字典普通服务接口，主要进行增删改查操作
 * 字典表：T_S_DA_SEC_VAR
 *
 */
@RestfulSupported
public interface ISecVarService extends IServiceBus {
	
	/**
	 * 根据条件查询证券品种字典并分页显示
	 * 
	 * @param paraMap 查询条件
	 *        page 分页实体
	 *        queryMenuId 功能代码
	 * @return QueryRes
	 */
	@LinkControllerMethod(value="selectWithSecAttr",arguTypes = SecVarVo.class)
	public QueryRes selectWithSecAttr(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap, @LinkControllerMethodArgu("page")PageInation page, @LinkControllerMethodArgu("queryMenuId")String queryMenuId);
	
	/**
	 * 获取证券品种代码 c_sec_var_code为('PJ','LC','ZQ','CK','HG','CJ')的证券品种字典视图V_S_DA_SEC_VAR的数据
	 * 以及 业务类型c_busi_type为('HG','CJ','CK')的交易方式字典V_S_DT_TD_MODE的数据的集合
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes
	 */
	public QueryRes queryIdxCtrlSec(HashMap<String, Object> paraMap);
	
	/**
	 * 根据交易证券代码获取对应的品种类型
	 * 
	 * @param seccode 交易证券代码
	 * @return QueryRes
	 */
	public QueryRes queryVarcodeByCode(String seccode);
	
	/**
	 * 查询所有证券品种类型，分父子级显示
	 * 
	 * @return HashMap<String, List<SecVar>> 分父子级显示的证券品种实体集合
	 */
	public HashMap<String, List<SecVar>> querySecVar() throws Exception;
}
