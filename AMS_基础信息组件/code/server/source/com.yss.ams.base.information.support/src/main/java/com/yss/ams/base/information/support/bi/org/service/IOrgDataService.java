package com.yss.ams.base.information.support.bi.org.service;

import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.pojo.OrgVo;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 关联机构设置数据服务接口，主要进行跨应用数据获取
 * 
 * 添加继承缓存的服务  edit by liuxiang 2016-2-26 STORY #28246 【自动化款指令】及【划款指令管理】模块界面问题优化
 *
 */
@RestfulSupported
@GenericPojo(pojo = Org.class)
public interface IOrgDataService extends IDataService,
		IControlDataService,IKeyConvertDataService,IDataServiceForCache{
	
	/**
	 * 根据机构名称获取机构对象
	 * @param <K>
	 * @param CounterpartyName 交易对手方名称
	 * @return
	 * @throws Exception
	 */
	public BasePojo getDataByCounterpartyName(String counterpartyName)
			throws ServiceException;
	
	/**
	 * 插入数据到关联机构信息表
	 * @param pojo
	 * @return
	 * @throws ServiceException
	 */
	public String insertOrg(BasePojo pojo) throws ServiceException;
	
	/**
	 * 查询最大机构代码
	 * @return 返回最大机构代码
	 * @throws ServiceException
	 */
	public String getMaxOrgCode() throws ServiceException;
	
	/**
	 * 根据产品账户的付款账号获得机构代码及其父级机构代码
	 * @return 机构代码及其父级机构代码
	 */
	public List<String> getOrgCodebyAccNo(String AccNo);
	
	/**
	 * add by shijian 2016-10-12
	 * STORY #35056 嘉实基金--成交清算日报表--增加名义管理人等字段
	 * 按查询条件查询机构数据
	 * @param condition 查询条件
	 * @return 
	 */
	public <T extends BasePojo> List<T> getDataListByCondition(String condition);
	
	/**
	 * 获取所有父级银行实体
	 * @return 银行实体
	 */
	public <T extends BasePojo> List<T> getAllBankHead() throws ServiceException;
	
	/**
	 * 根据父级机构代码获取所有分支银行实体
	 * @param param 父级机构代码
	 * @return 分支银行实体
	 */
	public <T extends BasePojo> List<T> getBankBranchByHead(String[] param) throws ServiceException;

	/**
	 * 根据机构实体进行插入操作
	 * @param orgList 机构实体
	 */
	public void insert(List<BasePojo> orgList);
	
	/**
	 * 根据机构代码进行插入操作
	 * @param c_Org_Code 机构代码
	 * @return Map<String, String>
	 */
	public Map<String, String> insert(String c_Org_Code);
	
	/**
	 * 按机构资质查询机构数据
	 * @Title getDataListByAptitude 
	 * @Description 按机构资质查询机构数据
	 * @author liangyilin@ysstech.com
	 * @date 2017年7月19日上午9:54:33
	 * @param types
	 * @return
	 * @throws ServiceException
	 * @return List<K>
	 */
	public <K extends BasePojo> List<K> getDataListByAptitude(String[] types) throws ServiceException;
	
	/**
	 * 按机构类型查询顶级机构数据
	 * @Title getParentListByTypes 
	 * @Description 按机构类型查询顶级机构数据
	 * @author liangyilin@ysstech.com
	 * @date 2017年8月22日上午10:20:34
	 * @param types
	 * @return
	 * @throws ServiceException
	 * @return List<K>
	 */
	public <K extends BasePojo> List<K> getParentListByTypes(String[] types) throws ServiceException;

	public String getUpdateByTimestampCount(String timestamp);

	@LinkControllerMethod(value="updateByTimestampPage",arguTypes = OrgVo.class)
	public CacheData updateByTimestampPage(@LinkControllerMethodArgu("timestamp")String timestamp, @LinkControllerMethodArgu("page")PageInation page);
	
	/**
	 * 根据主体资质获取数据
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getDataListByZtzz(String[] types);
	
	public CacheData updateByIds(String ids);
	
	public List<Org> queryOrgByPort(String portCode,String c_dv_type);
}
