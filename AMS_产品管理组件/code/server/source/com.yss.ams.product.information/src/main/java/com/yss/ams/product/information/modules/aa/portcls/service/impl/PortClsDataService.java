package com.yss.ams.product.information.modules.aa.portcls.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.aa.portcls.admin.PortClsDataAdmin;
import com.yss.ams.product.information.modules.aa.portcls.dao.PortClsSqlBuilder;
import com.yss.ams.product.information.support.cache.PortClsCache;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService;


public class PortClsDataService implements IClsPortDataService {
	private PortClsDataAdmin clsPortAdmin = null;

	public PortClsDataService() {
		clsPortAdmin = new PortClsDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new PortClsSqlBuilder());
	}

	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return clsPortAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Title STORY36440【南方基金】增加参数控制，当分级产品某一级别全部赎回后，被赎回的分级单位净值直接取其他级别 
	 * @Description 获取所选分组下的所有分级组合+‘系统默认算法’
	 * @author zhaijiajia@ysstech.com
	 * @date 2016年12月8日上午11:08:11
	 * @param types
	 * @param clsPort
	 * @return
	 * @throws ServiceException
	 * @return List<K>
	 */
	public <K extends BasePojo> List<K> getDataListByPorts(String[] types, String[] clsPort)
			throws ServiceException {
		List<K> pojoList;
		try {
			pojoList = clsPortAdmin.getDataListByPorts(types, clsPort);
			return pojoList;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = clsPortAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("productgrade");
			res.setHeadKeyList(ServiceAssistance.getListHead("productgrade",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}
	
	public String getPortClsCode(String code) throws ServiceException{
		try {
			return clsPortAdmin.getPortClsCode(code);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return clsPortAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return clsPortAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = clsPortAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("productgrade");
			res.setHeadKeyList(ServiceAssistance.getListHead("productgrade",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return clsPortAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return clsPortAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = clsPortAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("productgrade");
			res.setHeadKeyList(ServiceAssistance.getListHead("productgrade",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	private String menuId = "";
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
//		PortClsCache portClsCache = CacheManager.newInstance().getCache(CacheGroup.PORTCLS);
//		return (K)portClsCache.getCacheByKey(pojoCode);
		try {
			if (pojoCode.split("/t").length > 1) {
				return clsPortAdmin.getDataByCode(pojoCode);
			} else {
				return (K) clsPortAdmin.getPojoByCode(pojoCode);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		PortClsCache portClsCache = CacheManager.getInstance().getCache(CacheGroup.PORTCLS);
		return portClsCache.getKeyConvertMap(listKey);
	}
	
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//带上时间戳先
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = clsPortAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}
	
	public HashMap<String, String> getShortDataMap(String portCode) throws ServiceException{
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			PortClsCache cache =  CacheManager.getInstance().getCache(CacheGroup.PORTCLS);
			for (PortCls portCls : cache.getCacheList()) {
				if(!map.containsKey(portCls.getC_PORT_CLS_CODE()) && portCls.getC_PORT_CODE().equals(portCode)){
					map.put(portCls.getC_PORT_CLS_CODE(), portCls.getC_PORT_CLS_NAME());
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return map;
	}
	
	public <K extends BasePojo> List<K> getDataListByPorts(String[] ports)
			throws ServiceException {
		List<K> datalist = null;
		try {
			datalist = clsPortAdmin.getDataListByPortTypes(ports);
		} catch (Exception e) {
			throw new ServiceException("根据组合代码查询数据失败!", e);
		}
		return (List<K>)datalist;
	}
	
	/**
	 * 根据组合代码和级别类型获取分级代码
	 * 
	 * @author liuxiang
	 * @date 2016-9-1 STORY #28429 【广发证券】TA净值表导出设置中导出级别的优化
	 * @param portCode
	 *            组合代码
	 * @param types
	 *            级别类型(多个类型以逗号分割)
	 * @return 分级代码(以逗号分割)
	 * @throws ServiceException
	 */
	@Override
	public String getClsCodesByPortCodeAndType(String portCode, String types)
			throws ServiceException {
		return clsPortAdmin.getClsCodesByPortCodeAndType(portCode, types);
	}
	
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return clsPortAdmin.queryByIds(ids, PortCls.class);
	}
	
	public List<PortCls> getPortClsByDate(String PortCode, Date dueDate) throws ServiceException{
		List<PortCls> datalist = null;
		try {
			datalist = clsPortAdmin.getPortClsByDate(PortCode, dueDate);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return datalist;
	}
	
	public PortCls queryPortCls(String portCode, String classPort, Date actDate) throws ServiceException{
		try {
			return clsPortAdmin.queryPortCls(portCode,classPort,actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public PortCls queryPortCls(String portCode, String classPort) throws ServiceException{
		try {
			return clsPortAdmin.queryPortCls(portCode,classPort);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 查询清算日期为核算日的分级产品参数设置
	 * author dingshalu
	 * 2016年3月14日
	 * @return List<PortCls>
	 * @throws Exception
	 */
	public List<PortCls> portClsRecords(Date actDate, String port) throws ServiceException{
		try {
			return clsPortAdmin.portClsRecords(actDate, port);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 根据组合代码,分级组合代码,日期区间 获取分级组合对象
	 * 
	 * @param portCode
	 *            组合代码
	 * @param classPort
	 *            分级组合代码
	 * @return
	 * @throws SQLException
	 */
	public PortCls queryPortCls_Date(String portCode, String classPort, Date actDate) throws ServiceException{
		try {
			return clsPortAdmin.queryPortCls_Date(portCode, classPort, actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

	/**
	 * 根据组合代码获取组合下所有分级组合列表
	 * 
	 * @param portCode
	 *            组合代码
	 * @return
	 * @throws SQLException
	 */
	public List<PortCls> queryPortCls(String portCode) throws ServiceException{
		try {
			return clsPortAdmin.queryPortCls(portCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 根据分级组合代码,分级组合类型,分级级别获取分级组合List
	 * 
	 * @param portCode
	 *            组合代码
	 * @param portClsType
	 *            分级组合类型
	 * @param classPort
	 *            分级级别
	 * @return
	 * @throws SQLException
	 */
	public PortCls queryPortClsByTypeAndClass(String portCode,String portClsType, String classPort)
			throws ServiceException{
		try {
			return clsPortAdmin.queryPortClsByTypeAndClass(portCode, portClsType, classPort);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 根据分级组合代码,分级级别获取未到期分级组合列表
	 * STORY33196（紧急）受证监会13号公告影响，海通“赢财升鑫”产品改造方案。
	 * xiaozhilong 20160831
	 * @param portCode
	 *            组合代码
	 * @param actDate
	 *            核算日期
	 * @param classPort
	 *            分级级别
	 * @return
	 * @throws SQLException
	 */
	public List<PortCls> queryPortClsByClass(String portCode,
			Date actDate, String classPort) throws ServiceException{
		try {
			return clsPortAdmin.queryPortClsByClass(portCode, actDate, classPort);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 根据分级组合代码,分级组合类型,分级级别获取分级组合List
	 * 
	 * @param portCode
	 *            组合代码
	 * @param portClsType
	 *            分级组合类型
	 * @param portClsLevel
	 *            分级级别
	 * @return
	 * @throws SQLException
	 */
	public List<PortCls> queryPortCls(String portCode, String portClsType,
			String portClsLevel) throws ServiceException{
		try {
			return clsPortAdmin.queryPortCls(portCode, portClsType, portClsLevel);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 功能简述：根据组合代码获取该组合下所有的最末级组合的分级信息(海融系列产品估值方案)
	 * 
	 * @param portCode
	 * @return
	 * @throws SQLException
	 */
	public List<PortCls> queryPortClsMx(String portCode) throws ServiceException{
		try {
			return clsPortAdmin.queryPortClsMx(portCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 根据组合代码获取组合下所有分级组合列表
	 * 
	 * @param portCode
	 *            组合代码
	 * @return
	 * @throws SQLException
	 */
	public List<PortCls> queryPortClsList(String portCode) throws ServiceException{
		try {
			return clsPortAdmin.queryPortClsList(portCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 根据组合代码,分级组合代码,业务日期 获取运行期的分级组合对象
	 * 
	 * @param portCode
	 *            组合代码
	 * @param classPort
	 *            分级组合代码
	 * @param actDate
	 *            上市日期
	 * @return
	 * @throws SQLException
	 */
	public PortCls queryPortClsYxq(String portCode, String classPort,
			Date actDate) throws ServiceException{
		try {
			return clsPortAdmin.queryPortClsYxq(portCode, classPort, actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 获取分级产品 不能从缓存获取，缓存保存的分级产品 分级产品code 一样会丢失数据 根据组合和日期获取运作期间的所有分级组合 BY
	 * Jinghehe 2014-6-21
	 * 
	 * @param port
	 * @param accDate
	 * @return
	 * @throws SQLException
	 */
	public List<PortCls> queryPortCls(String port, Date accDate)
			throws ServiceException{
		try {
			return clsPortAdmin.queryPortCls(port, accDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	public List<PortCls> queryPortClsByLiquid(String port, Date accDate)
			throws ServiceException{
		try {
			return clsPortAdmin.queryPortClsByLiquid(port, accDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 根据组合代码,分级组合代码,业务日期 获取上一个到期的分级组合对象
	 * By Jinghehe 2014-6-28
	 * @param portCode
	 *            组合代码
	 * @param classPort
	 *            分级组合代码
	 * @param actDate
	 *            业务日期
	 * @return
	 * @throws SQLException
	 */
	public PortCls queryPreviousPortCls(String portCode, String classPort, Date actDate){
		try {
			return clsPortAdmin.queryPreviousPortCls(portCode, classPort, actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**查询产品下分级组合的集合
	 * @param portCode 组合代码
	 * @param dvCls 分级组合代码
	 * @param actDate 核算日期
	 * @return 分级组合集合（以成立日期排序）
	 * @throws SQLException
	 */
	public List<PortCls> queryPortClsByDvCls(String portCode,String dvCls,Date actDate){
		try {
			return clsPortAdmin.queryPortClsByDvCls(portCode, dvCls, actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**查询产品下分级组合的集合
	 * @param portCode 组合代码
	 * @param dvCls 分级组合代码
	 * @param actDate 核算日期
	 * @return 分级组合集合（以成立日期排序）
	 * @throws SQLException
	 */
	public List<PortCls> queryPortClsByDvCls(String portCode, Date actDate){
		try {
			return clsPortAdmin.queryPortClsByDvCls(portCode, actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 查询产品下运行期某级别分级组合的集合
	 * xiaozhilong STORY29633【广发证券】【紧急】量化避险资产估值需求
	 * @param portCode 组合代码
	 * @param clsLevel 分级级别
	 * @param actDate 核算日期
	 * @return 分级组合集合（以成立日期排序）
	 * @throws SQLException
	 */

	public List<PortCls> queryPortClsByDvClsAndDate(String portCode,String clsLevel, Date actDate){
		try {
			return clsPortAdmin.queryPortClsByDvClsAndDate(portCode, clsLevel, actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 查询产品下运行期所有明细分级产品级别分级组合的集合 
	 * xiaozhilong STORY29633【广发证券】【紧急】量化避险资产估值需求
	 * @param portCode 组合代码
	 * @param clsLevel 分级级别
	 * @param actDate 核算日期
	 * @return 分级组合集合（以成立日期排序）
	 * @throws SQLException
	 */

	public PortCls queryPortClsByClsLevel(String portCode,String clsLevel, Date actDate){
		try {
			return clsPortAdmin.queryPortClsByClsLevel(portCode, clsLevel, actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 查询业务日期内所有分级组合成立日最早或早晚的分级组合
	 * edit by wangtangyao 20160726 STORY #31921  
	 * 根据成立日期先后顺序判断轧差分级或是基准分级时用
	 * @param portCode 组合代码
	 * @param gradePortCode 分级组合代码
	 * @param actDate 上市日期
	 * @return portCls 分级
	 * @throws SQLException
	 */
	public PortCls queryPortClsSort(String portCode, Date actDate, boolean sort) {
		try {
			return clsPortAdmin.queryPortClsSort(portCode, actDate, sort);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * 查询分级收益分配信息 add by fangjiang 2013.06.18 STORY #4066 资产估值_应付投资者收益
	 * 
	 * @param portCode
	 * @param actDate
	 * @return
	 * @throws SQLException
	 * @throws YssException
	 */
	public List<PortCls> queryFjSyfpInfo(String portCode, Date actDate){
		try {
			return clsPortAdmin.queryFjSyfpInfo(portCode, actDate);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
	}

    /** 
     * @Title: insert
     * @Desc: 
     * @param pojo
     * @throws ServiceException 
     * @see com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService#insert(com.yss.framework.api.common.co.BasePojo) 
     */
    @Override
    @CacheRefresh(group = CacheGroup.PORTCLS)
    public <K extends BasePojo> void insert(K pojo) throws ServiceException {
        try {
            clsPortAdmin.insert(pojo);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /** 
     * @Title: updateById
     * @Desc: 
     * @param pojo
     * @throws ServiceException 
     * @see com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService#updateById(com.yss.framework.api.common.co.BasePojo) 
     */
    @Override
    @CacheRefresh(group = CacheGroup.PORTCLS)
    public <K extends BasePojo> void updateById(K pojo) throws ServiceException {
        try {
            clsPortAdmin.updateById(pojo);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /** 
     * @Title: deleteById
     * @Desc: 
     * @param pojo
     * @throws ServiceException 
     * @see com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService#deleteById(com.yss.framework.api.common.co.BasePojo) 
     */
    @Override
    @CacheRefresh(group = CacheGroup.PORTCLS)
    public <K extends BasePojo> void deleteById(K pojo) throws ServiceException {
        try {
            clsPortAdmin.deleteById(pojo);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
	
}
