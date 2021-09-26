package com.yss.ams.product.information.support.modules.ab.portrela.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRela;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.GetPortEexptExistByCopyVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.PortRelaDeleteByIdVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.PortRelaUpdateByIdVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.PortRelainsertVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccountIdPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccountPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaIndexPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaInvestMgrPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaMemberPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaOrganPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeOrgPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeSeatPageVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
@GenericPojo(pojo=PortRela.class)
public interface IPortRelaService extends IServiceBus {
	/**
	 * 组合关联股东账户
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaCashAccountPage",arguTypes = QueryPortRelaCashAccountPageVo.class)
	QueryRes queryPortRelaCashAccountPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);

	/**
	 * 组合关联股东账户
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes queryPortRelaCashAccount(HashMap<String, Object> paraMap);

	/**
	 * 组合关联客户编号
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaCashAccountIdPage",arguTypes = QueryPortRelaCashAccountIdPageVo.class)
	QueryRes queryPortRelaCashAccountIdPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);

	/**
	 * 组合关联交易席位
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaTradeSeatPage",arguTypes = QueryPortRelaTradeSeatPageVo.class)
	QueryRes queryPortRelaTradeSeatPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);
	
	/**
	 * 组合关联期货公司
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaTradeOrgPage",arguTypes = QueryPortRelaTradeOrgPageVo.class)
	QueryRes queryPortRelaTradeOrgPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);

	/**
	 * 组合关联投资经理
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaInvestMgrPage",arguTypes = QueryPortRelaInvestMgrPageVo.class)
	QueryRes queryPortRelaInvestMgrPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);

	/**
	 * 组合关联机构
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaOrganPage",arguTypes = QueryPortRelaOrganPageVo.class)
	QueryRes queryPortRelaOrganPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);

	/**
	 * 组合关联客户编号
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes queryPortRelaCashAccountId(HashMap<String, Object> paraMap);

	/**
	 * 组合关联交易席位
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes queryPortRelaTradeSeat(HashMap<String, Object> paraMap);
	
	/**
	 * 组合关联期货公司
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes queryPortRelaTradeOrg(HashMap<String, Object> paraMap);
	
	/**
	 * 组合关联期货公司SET界面
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes queryPortRelaTradeOrgSet(HashMap<String, Object> paraMap);
	
	/**
	 * 组合关联期货公司SET界面(新增)
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes getPortRelaTdOrg(HashMap<String, Object> paraMap);

	/**
	 * 组合关联投资经理
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes queryPortRelaInvestMgr(HashMap<String, Object> paraMap);

	/**
	 * 组合关联机构
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes queryPortRelaOrgan(HashMap<String, Object> paraMap);
	
	/**
	 * 组合关指数信息
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaIndexPage",arguTypes = QueryPortRelaIndexPageVo.class)
	QueryRes queryPortRelaIndexPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);

	/**
	 * 组合关联指数信息
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes queryPortRelaIndex(HashMap<String, Object> paraMap);
	
	/**
	 * 根据业务主键先删后增 
	 * @param pojoList
	 * @return
	 */
	String delInsert(List<BasePojo> pojoList); 
	
	/**
	 * 根据业务主键删除
	 * @param pojoList
	 * @return
	 */
	String delByYwId(List<BasePojo> pojoList);
	
	
	/**
	 * 复制关联信息，获取没有已经设置的组合(进滤掉已经存在的关联信息组合)
     * add by xhb 2015-12-29 STORY #26037
	 */
	@LinkControllerMethod(value="getPortEexptExistByCopy",arguTypes = GetPortEexptExistByCopyVo.class)
	List<String> getPortEexptExistByCopy(@LinkControllerMethodArgu("ports")String ports, @LinkControllerMethodArgu("pojo")BasePojo pojo);
	
	/**
	 * 组合关结算会员
	 * 
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	QueryRes queryPortRelaMember(HashMap<String, Object> paraMap);

	/**
	 * 组合关结算会员
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaMemberPage",arguTypes = QueryPortRelaMemberPageVo.class)
	QueryRes queryPortRelaMemberPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);
	
	/**
	 * 现金账户信息
	 * @author guohui 2017-09-04 STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaCashAccPage",arguTypes = QueryPortRelaCashAccPageVo.class)
	QueryRes queryPortRelaCashAccPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);
	
	/**
	 * 现金账户列表查询
	 * @author guohui 2017-09-04 STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
	 * @param paraMap
	 * @param page
	 * @return
	 */
	QueryRes queryPortRelaCashAcc(HashMap<String, Object> paraMap);
	
	 
    /**
     * STORY #65552 场外调用估值接口服务问题 
     * @Title: insert 
     * @Desc: 提供Connection 入参的插入方法
     * @param pojo
     * @param conn
     * @return
     */
	@LinkControllerMethod(value="insert",arguTypes = PortRelainsertVo.class)
    public <K extends BasePojo> String insert(@LinkControllerMethodArgu("pojo")K pojo, @LinkControllerMethodArgu("conn")Connection conn);
    
    /**
     * STORY #65552 场外调用估值接口服务问题 
     * @Title: deleteById 
     * @Desc: 提供Connection入参的删除方法
     * @param list
     * @param conn
     * @return
     */
	@LinkControllerMethod(value="deleteById",arguTypes = PortRelaDeleteByIdVo.class)
    public String deleteById(@LinkControllerMethodArgu("list")List<BasePojo> list, @LinkControllerMethodArgu("conn")Connection conn);
    
    /**
     * STORY #65552 场外调用估值接口服务问题 
     * @Title: updateById 
     * @Desc: 提供Connection 入参的更新方法
     * @param pojo
     * @param conn
     * @return
     */
	@LinkControllerMethod(value="updateById",arguTypes = PortRelaUpdateByIdVo.class)
    public <K extends BasePojo> String updateById(@LinkControllerMethodArgu("pojo")K pojo, @LinkControllerMethodArgu("conn")Connection conn);
}
