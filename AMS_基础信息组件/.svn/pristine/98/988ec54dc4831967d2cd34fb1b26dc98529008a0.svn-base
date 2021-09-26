package com.yss.ams.base.information.modules.sys.dttdmode.admin;

import java.util.HashMap;
import java.util.List;



import com.yss.ams.base.information.modules.sys.dttdmode.dao.DttdModeDao;
//import com.yss.dict.dttdmode.dao.DttdModeDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 交易方式字典表T_S_DT_TD_MODE Admin
 *
 */
public class DtTdModeDataAdmin extends BaseAdmin{
	DttdModeDao svcDao = null;
	
	/**
	 * 构造方法
	 * @param pool
	 * @param sqlBuilder
	 */
	public DtTdModeDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new DttdModeDao(pool, sqlBuilder);
	}

	/**
	 * 获取所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return 所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 *  根据 交易方式代码C_DT_CODE 获取交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 *  根据 业务类型C_BUSI_TYPE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types) throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 *  联合查询业务类型，交易方式数据,每个业务类型下的交易方式数据
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getTreeData() throws Exception {
		return (List<T>) svcDao.getTreeDataList();
	}

	/**
	 * 根据业务类型C_BUSI_TYPE数组 或者 交易方式代码C_DT_CODE数组，获取业务类型，交易方式联合树形结构
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getTreeDataByTypes(String[] types) throws Exception {
		return (List<T>) svcDao.getTreeDataByTypes(types);
	}

	/**
	 *  联合查询业务类型，交易方式所有数据
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}

	/**
	 *  根据 交易方式代码C_DT_CODE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	/**
	 * 根据时间戳获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		// TODO Auto-generated method stub
		return svcDao.getDataListByTimestamp(timestamp);
	}

	
	/**
	 * 加载分组拆分 交易方式 
	 * By Jinghehe 2015-11-08
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  <T extends BasePojo> List<T>  getTreeDataForRule() {
		return (List<T>) svcDao.getTreeDataForRule();
	}

	/**
	 * 查询分组拆分 交易方式中父节点的业务 
	 * By wangyaokang 2015-11-26
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCodeForRule(String code) throws Exception {
		return (T) svcDao.getDataByCodeForRule(code);
	}

	/**
	 * add by liyanjun 2016-2-20 STORY #28608 【广发证券】在分组恒生交易数据文件接口添加控制
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getTreeDataByCfgCode(String[] cfgCodes) {
		return (List<T>) svcDao.getTreeDataByCfgCode(cfgCodes);
	}
	
	/***
	 * add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
	 * 根据词汇分类，获取非明细业务类型（针对证券清算款非T+1），不从销售方式表中获取
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getSQKDataListByTypes(String[] types) {
		return (List<T>) svcDao.getSQKDataListByTypes(types);
	}
	
	/**
	 * 支持多参数以数组形式传入
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getSQKDataListByTypes(String type) {
		return (List<T>) svcDao.getSQKDataListByTypes(type);
	}
	
	/**
	 * 根据IDs查询数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:47:12
	 * @param ids
	 * @param clazz
	 * @return
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
	
	/***
	 * add by yuanyafeng 20180911 STORY #61545 【紧急】太平保险-附件管理优化（二期）
	 * 根据模块功能代码取对应模块的交易方式，没有的返回空
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByFun(String funCode) {
		return (List<T>) svcDao.getDataListByFun(funCode);
	}
}
