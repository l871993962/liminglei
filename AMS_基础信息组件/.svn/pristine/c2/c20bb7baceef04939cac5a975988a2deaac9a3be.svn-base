package com.yss.ams.base.information.support.bi.org.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.pojo.OrgVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.resource.mgr.pojo.FileTrans;

/**
 * 关联机构设置普通服务接口，主要进行增删改查操作
 * 
 * @author 马向峰  拆分  2017.0527
 *
 */
@RestfulSupported
public interface IOrgService extends IServiceBus {
	
	/**
	 * 组合关联机构SET界面查询
	 * @param paraMap
	 * @return
	 */
	public QueryRes getPortRelaOrg(HashMap<String, Object> paraMap);
	
	/**
	 * 获取关联机构设置的词汇信息
	 * @return 词汇实体List
	 */
	public List<BasePojo> getOrgVoc();
	
	/**
	 * 嵌套窗体查询关联机构设置
	 * @author liuxiang
	 * @date 2014/7/17
	 * @param paraDict
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaOrg",arguTypes = OrgVo.class)
	public QueryRes queryPortRelaOrg(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap, @LinkControllerMethodArgu("page")PageInation page);
	
	/**
	 * 文件上传的方法
	 * @param file 文件传输实体
	 * @return 状态：成功、失败
	 */
	public String uploadLogo(FileTrans file);
	
	/**
	 * 查询图片路径
	 * @param imagePath 图片路径
	 * @return
	 */
	public String queryImage(String imagePath);
		
	/**
	 * 获取委托人组织机构
	 * @return 机构实体List
	 */
	public List<Org> getOrgByConsignerType();
	
	/**
	 * 产品生命周期主体信息功能   提供控制事务的插入方法
	 * @author jiangzhichao
	 * @param pojo
	 * @param conn
	 * @return
	 */
	@LinkControllerMethod(value="connInsert",arguTypes = OrgVo.class)
	public String connInsert(@LinkControllerMethodArgu("pojo")BasePojo pojo,@LinkControllerMethodArgu("conn")Connection conn) throws ServiceException;
	
	/**
	 * 产品生命周期主体信息功能   提供控制事务的删除方法
	 * @author jiangzhichao
	 * @param pojo
	 * @param conn
	 * @return
	 */
	@LinkControllerMethod(value="connDelete",arguTypes = OrgVo.class)
	public void connDelete(@LinkControllerMethodArgu("list")List<BasePojo> list,@LinkControllerMethodArgu("conn")Connection conn) throws ServiceException;
	
	/**
	 * 产品生命周期主体信息功能   提供控制事务的修改方法
	 * @author jiangzhichao
	 * @param pojo
	 * @param conn
	 * @return
	 */
	@LinkControllerMethod(value="connUpdate",arguTypes = OrgVo.class)
	public void connUpdate(@LinkControllerMethodArgu("pojo")BasePojo pojo,@LinkControllerMethodArgu("conn")Connection conn) throws ServiceException;

	public CacheData updateByIds(String ids);

	/**
	 * 先删除后插入数据
	 * @param temOrg
	 */
	public void deleteThenInsert(Org org);


	List<Org> getDataListByTypes(String[] types) throws Exception;
	
	/**
	 * 获取组合的管理人名称
	 * STORY #70280 add by xiadeqi 2019-3-20 
	 * @param portCode
	 * @return
	 */
	public String getManagerNameByPortCode(String portCode) throws ServiceException;
	/**
	 * Author : zuomingke
	 * Date   : 2019-07-04
	 * Status : Add
	 * Comment:  根据机构代码查询机构信息，若为空或null则查询所有的机构信息
	 * @return 
	 */
	public List<Org> getOrgByOrgCodes(String orgCodes) throws Exception;
	
	/**
	 * Author : zuomingke
	 * Date   : 2019-07-04
	 * Status : Add
	 * Comment:  根据机构代码查询关联组合代码
	 * @return 
	 */
	public List<String> getPortCodeListByRelaOrgCode(String orgCode);
	
	/**
     * 获取机构关联联系人信息
     * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
     * @param orgCode
     * @return
     */
    public List<Org> getOrgLinkManList(String orgCode);
}
