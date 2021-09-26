package com.yss.ams.product.information.support.modules.aa.portcls.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.DeleteByIdVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.InsertVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.UpdateByIdVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * <产品分级信息>普通服务接口，主要进行增删改查操作
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */
@RestfulSupported
public interface IPortClsService extends IServiceBus {
	/**
	 * 用于更新
	 * 新产品起息确认后 分级产品参数的截止日期改为 起息截止日期
	 * Based on Bug8899
	 * @param portCode
	 * @param dueDate
	 */
	public void updateDueDate(String portCode, String dueDate);
	
	/**
	 * 根据组合code 和分级组合类型 获得对应的分级组合code By Jinghehe 2014-1-7
	 * @param portCode
	 * @param portClsType
	 * @return
	 */
	public String getPortClsCode(String portCode, String portClsType);
	
	/**
	 * 根据用户权限查找分级组合
	 * @param userCode 用户代码
	 * @return
	 */
	public List<PortCls> getPortClsByUser(String userCode);
	
	/**
	 * 根据组合代码查找分级组合
	 * @param portCode 组合代码
	 * @return
	 */
	public List<PortCls> getPortClsByPortCode(String portCode);
	
	/**
	 * 校验日期是否重叠（到期日期）
	 * add by Yuntao Lau 2015.12.19 BUG #123015
	 * @param paraMap 条件
	 * @return 日期是否重叠
	 */
    public String checkDate(HashMap<String, String> paraMap);
    
   	/**
	 * 校验日期是否重叠（清算日期）
	 * add by xuhanbing 2016.12.7 
	 * STORY #35787 海通资管 赢财升鑫产品的 每年基准收益率参数优化
	 * @param paraMap 条件
	 * @return 日期是否重叠
	 */
    public String checkDateQSRQ(HashMap<String, String> paraMap);
    
    /**
     * STORY #65552 场外调用估值接口服务问题 
     * @Title: insert 
     * @Desc: 提供Connection 入参的插入方法
     * @param pojo
     * @param conn
     * @return
     */
    @LinkControllerMethod(value="insert",arguTypes = InsertVo.class)
    public <K extends BasePojo> String insert(@LinkControllerMethodArgu("pojo")K pojo, @LinkControllerMethodArgu("conn")Connection conn);
    
    /**
     * STORY #65552 场外调用估值接口服务问题 
     * @Title: deleteById 
     * @Desc: 提供Connection入参的删除方法
     * @param list
     * @param conn
     * @return
     */
    @LinkControllerMethod(value="deleteById",arguTypes = DeleteByIdVo.class)
    public String deleteById(@LinkControllerMethodArgu("list")List<BasePojo> list, @LinkControllerMethodArgu("conn")Connection conn);
    
    /**
     * STORY #65552 场外调用估值接口服务问题 
     * @Title: updateById 
     * @Desc: 提供Connection 入参的更新方法
     * @param pojo
     * @param conn
     * @return
     */
    @LinkControllerMethod(value="updateById",arguTypes = UpdateByIdVo.class)
    public <K extends BasePojo> String updateById(@LinkControllerMethodArgu("pojo")K pojo, @LinkControllerMethodArgu("conn")Connection conn);
}
