package com.yss.ams.base.information.modules.sys.convertdict.zhzd.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.modules.sys.convertdict.zhzd.dao.ZhzdDao;
import com.yss.ams.base.information.modules.sys.convertdict.zhzd.dao.ZhzdSqlBuilder;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.DbPoolFactory;

/**
 * 转换字典  业务管理类
 * @author 马向峰  拆分  2017.0527
 *
 */
public class ZhzdDataAdmin extends BaseAdmin {
	ZhzdDao svcDao = null;

	public ZhzdDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new ZhzdDao(DbPoolFactory.getInstance().getPool(), new ZhzdSqlBuilder());
	}

	/**
	 * dao层未实现
	 * @return 所有列表数据
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}


	/**
	 * dao层未实现
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * dao层未实现
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	/**
	 * dao层未实现
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] keys)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(keys);
	}

	/**
	 * 根据场景类型查找转换字典，并把 源值 和 转换值 存到Map中
	 * @param type 场景类型
	 * @return 封装了 源值和转换值的Map<源值,转换值>
	 */
	public HashMap<String, String> getKeyConvertMap(String type) {
		
		return svcDao.getKeyConvertMap(type);
	}
	
	/**
	 * 根据场景类型查找转换字典，并把 源值 和 转换值 存到Map中
	 * @param type 场景类型
	 * @return 封装了 源值和转换值的Map<源值,转换值>
	 */
	public HashMap<String, String> getKeyConvertMap(String[] type) {
		
		return svcDao.getKeyConvertMapTAQS(type);
	}

	/**
	 * 根据场景和源值查找转换值
	 * @param srcCode 源值
	 * @param sceneType 场景
	 * @return 转换值
	 */
	public String specificDictConvert(String srcCode, String sceneType) {
		return svcDao.specificConvertDict(srcCode, sceneType);
	}
	/**
	 * comment：根据转换字典代码批量获取转换字典<br>
	 * src：STORY #60135 嘉实基金-系统间升级交互机制 <br>
	 * author：shijian@ysstech.com<br>
	 * date：2018年12月21日<br>
	 */
	public Map<String, String> getConvertMapByGroupCode(String[] groupCodes) {
		return svcDao.getConvertMapByGroupCode(groupCodes);
	}
}
