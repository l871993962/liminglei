package com.yss.ams.base.information.modules.sys.dtatdattr.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;








import com.yss.ams.base.information.modules.sys.dtatdattr.admin.DtaTdAttrDataAdmin;
import com.yss.ams.base.information.modules.sys.dtatdattr.dao.DtatdAttrSqlBuilder;
import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
import com.yss.ams.base.information.support.sys.dtatdattr.pojo.DtatdAttr;
import com.yss.ams.base.information.support.sys.dtatdattr.service.IDtaTdAttrDataService;
import com.yss.ams.base.information.activator.InformationActivator;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.dict.dtatdattr.admin.DtaTdAttrDataAdmin;
//import com.yss.dict.dtatdattr.dao.DtatdAttrSqlBuilder;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

//import dataservice.comm.pojo.DtatdAttr;
//import dataservice.service.IDtaTdAttrDataService;

/**
 * 交易属性字典T_S_DTA_TD_ATTR service
 *
 */
public class DtaTdAttrDataService implements IDtaTdAttrDataService {
	private DtaTdAttrDataAdmin dtaTdAttrAdmin = null;

	/**
	 * 构造方法
	 */
	public DtaTdAttrDataService() {
		dtaTdAttrAdmin = new DtaTdAttrDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new DtatdAttrSqlBuilder());
	}

	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR数据
	 * @return 交易属性字典DtatdAttr pojo对象集合
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return dtaTdAttrAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR数据，并用此初始化QueryRes对象
	 * @return  初始化了的QueryRes对象
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dtaTdAttrAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_dtatdattrquy");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dtatdattrquy",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据交易属性代码C_DTA_CODE值获取交易属性字典T_S_DTA_TD_ATTR数据
	 * @return 交易属性字典DtatdAttr pojo对象
	 */
	public DtatdAttr getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return dtaTdAttrAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据
	 * @param types 业务类型C_BUSI_TYPE值
	 * @return 交易属性字典DtatdAttr pojo对象集合
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return dtaTdAttrAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据 并用此初始化QueryRes对象
	 * @param types 业务类型C_BUSI_TYPE值
	 * @return 初始化了的QueryRes对象
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dtaTdAttrAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_dtatdattrquy");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dtatdattrquy",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}
	
	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR的交易属性代码和交易属性名称的集合
	 * @return 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值组成的集合
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return dtaTdAttrAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据
	 * @param keys 业务类型C_BUSI_TYPE值
	 * @return 交易属性字典DtatdAttr pojo对象集合
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return dtaTdAttrAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据  并用此初始化QueryRes对象
	 * @param keys 业务类型C_BUSI_TYPE值
	 * @return 初始化了的QueryRes对象
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = dtaTdAttrAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_dtatdattrquy");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_dtatdattrquy",InformationActivator.class));
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

	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR的交易属性代码和交易属性名称的集合
	 * @return 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值组成的集合
	 */
	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return dtaTdAttrAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据交易属性代码C_DTA_CODE值获取交易属性字典T_S_DTA_TD_ATTR数据
	 * @return 交易属性字典DtatdAttr pojo对象
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return dtaTdAttrAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR的 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值组成的集合
	 * @return 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值组成的集合
	 */
	@Override
	public HashMap<String, String> getShortDataMap() throws ServiceException{
		HashMap<String, String> map = null;
		try{
			map = (HashMap<String, String>) dtaTdAttrAdmin.getShortDataMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return map;
	}
	/**
	 * 通过指定codes获取数据   STORY39265商品期权业务
	 * @param codes
	 * @return 数据集合
	 * @throws ServiceException
	 * @author xuyuanhao
	 * @date 2017-3-30
	 * @state add
	 */
	public <K extends BasePojo> List<K> getDataListByCodes(String[] codes)
			throws ServiceException {
		try {
			return dtaTdAttrAdmin.getDataListByCodes(codes);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		data.setTimestamp(timestamp);//把时间带上，因为反审核不会修改修改时间，是获取不到数据的，这时会将时间戳设置为空
		if(timestamp == null || timestamp.equals("")){
			list = this.getDataList();
		}
		else{
			list = dtaTdAttrAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		return data;
	}

	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		//return dtaTdAttrAdmin.queryByIds(ids, DcCury.class);
		return null;
	}
}
