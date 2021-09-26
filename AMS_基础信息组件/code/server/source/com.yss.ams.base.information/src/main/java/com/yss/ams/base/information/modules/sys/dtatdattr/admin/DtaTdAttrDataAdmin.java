package com.yss.ams.base.information.modules.sys.dtatdattr.admin;



import java.util.HashMap;
import java.util.List;





import com.yss.ams.base.information.modules.sys.dtatdattr.dao.DtatdAttrDao;
import com.yss.ams.base.information.support.sys.dccury.pojo.DcCury;
//import com.yss.dict.dtatdattr.dao.DtatdAttrDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
/**
 * 交易属性字典T_S_DTA_TD_ATTR admin
 *
 */
public class DtaTdAttrDataAdmin extends BaseAdmin{
	DtatdAttrDao svcDao = null;
	
	/**
	 * 构造方法
	 * @param pool
	 * @param sqlBuilder
	 */
	public DtaTdAttrDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new DtatdAttrDao(pool, sqlBuilder);
	}
	
	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR数据
	 * @return 交易属性字典DtatdAttr pojo对象集合
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}
	
	/**
	 * 根据交易属性代码C_DTA_CODE值获取交易属性字典T_S_DTA_TD_ATTR数据
	 * @return 交易属性字典DtatdAttr pojo对象
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据
	 * @param types 业务类型C_BUSI_TYPE值
	 * @return 交易属性字典DtatdAttr pojo对象集合
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types) throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR的交易属性代码和交易属性名称的集合
	 * @return 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值组成的集合
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		return svcDao.getKeyConvertMap();
	}

	/**
	 * 根据业务类型C_BUSI_TYPE值获取所有交易属性字典T_S_DTA_TD_ATTR数据
	 * @param keys 业务类型C_BUSI_TYPE值
	 * @return 交易属性字典DtatdAttr pojo对象集合
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}
	
	/**
	 * 获取所有的交易属性字典T_S_DTA_TD_ATTR的 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值组成的集合
	 * @return 交易属性代码C_DTA_CODE,交易属性名称C_DTA_NAME列值组成的集合
	 */
	public HashMap<String, String> getShortDataMap() {
		return svcDao.getShortDataMap();
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
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByCodes(String[] codes)
			throws Exception {
		return (List<T>) svcDao.getDataListByCodes(codes);
	}
	
	/**
	 * STORY #96237 凭证处理过程中交易属性需要变更从缓存中处理
	 * @param timestamp
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}
	
	/**
	 * STORY #96237 凭证处理过程中交易属性需要变更从缓存中处理
	 * @param ids
	 * @param clazz
	 * @return
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		//return svcDao.queryByIds(ids, clazz);
		return  null;
	}
	
	
}
