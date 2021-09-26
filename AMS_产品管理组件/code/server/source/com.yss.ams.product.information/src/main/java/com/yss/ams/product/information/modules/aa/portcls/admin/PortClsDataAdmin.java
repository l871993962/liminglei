package com.yss.ams.product.information.modules.aa.portcls.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.yss.ams.product.information.modules.aa.portcls.dao.PortClsDao;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
/**
 * 分级产品admin类
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */

public class PortClsDataAdmin extends BaseAdmin {
	PortClsDao svcDao = null;

	public PortClsDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new PortClsDao(pool, sqlBuilder);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		PortCls tempCls = null;
		HashMap<String, PortCls> filtermap = new HashMap<String, PortCls>();
		List<PortCls> dataList = svcDao.getDataListByTypes(types);
		// 过滤同组和下相同的分级代码，相同的分级代码只展示一个
		if (!dataList.isEmpty()) {
			//modified by dingshalu 2016年9月6日 STORY #31188 追加资金的提取退回
			//更改逻辑：iterator赋值应该放入types循环中，iterator执行一次后如果不重新赋值则无法再次循环。
			//Iterator<PortCls> iterator = dataList.iterator();
			for (String portCode : types) {
				Iterator<PortCls> iterator = dataList.iterator();
				while (iterator.hasNext()) {
					tempCls = iterator.next();
					if (tempCls.getC_PORT_CODE().equals(portCode)) {
						filtermap.put(tempCls.getC_PORT_CLS_CODE(), tempCls);
					}
				}
			}
			
			dataList.clear();
			for (PortCls portCls : filtermap.values()) {
				dataList.add(portCls);
			}
		}
		return (List<T>) dataList;
		// return (List<T>) svcDao.getDataListByTypes(types);
	}

	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	public String getPortClsCode(String code) throws BusinessException{
		return svcDao.getPortClsCode(code);
	}

	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	public BasePojo getPojoByCode(String pojoCode) {
		return svcDao.getPojoByCode(pojoCode);
	}
	
	
	/**
	 * 根据组合查询出对应的分级组合
	 * add by xhb 20160129
	 * BUG #126111
	 * @param types
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getDataListByPortTypes(String[] types)
			throws Exception {
		PortCls tempCls = null;
		HashMap<String, String> filtermap = new HashMap<String, String>();
		List<PortCls> dataList = svcDao.getDataListByTypes(types);
		List<PortCls> dataListNew = new ArrayList<PortCls>();
		if (!dataList.isEmpty()) {
			Iterator<PortCls> iterator = dataList.iterator();
			while (iterator.hasNext()) {
				tempCls = iterator.next();
				if(!filtermap.containsKey(tempCls.getC_PORT_CODE()+"\t"+tempCls.getC_PORT_CLS_CODE())){
					dataListNew.add(tempCls);
			    }
				filtermap.put(tempCls.getC_PORT_CODE()+"\t"+tempCls.getC_PORT_CLS_CODE(), "1");
			}
		}
		return (List<K>) dataListNew;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByPorts(String[] types, String[] clsPort)
			throws Exception {
		PortCls tempCls = null;
		HashMap<String, PortCls> filtermap = new HashMap<String, PortCls>();
		List<PortCls> dataList = svcDao.getDataListByPorts(types);
		if (!dataList.isEmpty()) {
			// 过滤同组和下相同的分级代码，相同的分级代码只展示一个
			Iterator<PortCls> iterator = dataList.iterator();
			for (String portCode : types) {
				while (iterator.hasNext()) {
					tempCls = iterator.next();
					if (tempCls.getC_PORT_CODE().equals(portCode)) {
						filtermap.put(tempCls.getC_PORT_CLS_CODE(), tempCls);
					}
				}
			}
			
			dataList.clear();
			// 筛选出 组合对应分级组合中未选中的组合
			for (PortCls portCls : filtermap.values()) {
				boolean flag = false;
				for (String clsCode : clsPort) {
					if (portCls.getC_PORT_CLS_CODE().equals(clsCode)) {
						flag = true;
					}
				}
				if (!flag) {
					dataList.add(portCls);
				}
			}
		}
		return (List<T>) dataList;
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
	 */
	public String getClsCodesByPortCodeAndType(String portCode, String types) {
		return svcDao.getClsCodesByPortCodeAndType(portCode, types);
	}
	
	/**
	 * 根据IDs获取数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:50:03
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
	
	public List<PortCls> getPortClsByDate(String PortCode, Date dueDate){
		return svcDao.getPortClsByDate(PortCode,dueDate);
	}
	
	public PortCls queryPortCls(String portCode, String classPort, Date actDate){
		return svcDao.queryPortCls(portCode, classPort, actDate);
	}
	
	public PortCls queryPortCls(String portCode, String classPort){
		return svcDao.queryPortCls(portCode, classPort);
	}
	
	public List<PortCls> portClsRecords(Date actDate, String port){
		return svcDao.portClsRecords(actDate, port);
	}
	
	public PortCls queryPortCls_Date(String portCode, String classPort, Date actDate){
		return svcDao.queryPortCls_Date(portCode, classPort, actDate);
	}
	
	public List<PortCls> queryPortCls(String portCode){
		
		return svcDao.queryPortCls(portCode);
	}
	
	public PortCls queryPortClsByTypeAndClass(String portCode,String portClsType, String classPort){
		return svcDao.queryPortClsByTypeAndClass(portCode, portClsType, classPort);
		
	}
	
	public List<PortCls> queryPortClsByClass(String portCode,Date actDate, String classPort){
		
		return svcDao.queryPortClsByClass(portCode, actDate, classPort);
	}
	
	public List<PortCls> queryPortCls(String portCode, String portClsType,String portClsLevel){
		
		return svcDao.queryPortCls(portCode, portClsType, portClsLevel);
	}
	
	public List<PortCls> queryPortClsMx(String portCode){
		return svcDao.queryPortClsMx(portCode);
	}
	
	public List<PortCls> queryPortClsList(String portCode){
		
		return svcDao.queryPortClsList(portCode);
	}
	
	public PortCls queryPortClsYxq(String portCode, String classPort, Date actDate){
		return svcDao.queryPortClsYxq(portCode, classPort, actDate);
	}
	
	public List<PortCls> queryPortCls(String port, Date accDate){
		return svcDao.queryPortCls(port, accDate);
	}
	
	public List<PortCls> queryPortClsByLiquid(String port, Date accDate){
		return svcDao.queryPortClsByLiquid(port, accDate);
	}
	
	public PortCls queryPreviousPortCls(String portCode, String classPort, Date actDate){
		return svcDao.queryPreviousPortCls(portCode, classPort, actDate);
	}

	public List<PortCls> queryPortClsByDvCls(String portCode,String dvCls,Date actDate){
		return svcDao.queryPortClsByDvCls(portCode, dvCls, actDate);
	}
	
	public List<PortCls> queryPortClsByDvCls(String portCode, Date actDate){
		return svcDao.queryPortClsByDvCls(portCode, actDate);
	}

	public List<PortCls> queryPortClsByDvClsAndDate(String portCode,String clsLevel, Date actDate){
		return svcDao.queryPortClsByDvClsAndDate(portCode, clsLevel, actDate);
	}
	
	public PortCls queryPortClsByClsLevel(String portCode,String clsLevel, Date actDate){
		return svcDao.queryPortClsByClsLevel(portCode, clsLevel, actDate);

	}
	
	public PortCls queryPortClsSort(String portCode, Date actDate, boolean sort) {
		return svcDao.queryPortClsSort(portCode, actDate, sort);
	}

	public List<PortCls> queryFjSyfpInfo(String portCode, Date actDate){
		return svcDao.queryFjSyfpInfo(portCode, actDate);
	}
	
	/**
	 * @Title: insert 
	 * @param pojo
	 * @throws ServiceException
	 */
    public <K extends BasePojo> void insert(K pojo) throws ServiceException {
        try {
            this.svcDao.insert(pojo);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * @Title: updateById 
     * @param pojo
     * @throws ServiceException
     */
    public <K extends BasePojo> void updateById(K pojo) throws ServiceException {
        try {
            this.svcDao.updateById(pojo);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    /**
     * @Title: deleteById 
     * @param pojo
     * @throws ServiceException
     */
    public <K extends BasePojo> void deleteById(K pojo) throws ServiceException {
        try {
            this.svcDao.deleteById(pojo);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }
	
}
