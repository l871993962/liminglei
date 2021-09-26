package com.yss.ams.base.information.modules.sys.convertdict.zhzd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.modules.sys.convertdict.zhzd.admin.ZhzdDataAdmin;
import com.yss.ams.base.information.modules.sys.convertdict.zhzd.dao.ZhzdSqlBuilder;
import com.yss.ams.base.information.support.sys.convertdict.zhzd.pojo.Zhzd;
import com.yss.ams.base.information.support.sys.convertdict.zhzd.service.IZhzdDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
/**
 * 转换字典数据服务类 
 * @author 马向峰 拆分  2017.0527
 */
public class ZhzdDataService implements IZhzdDataService {
	ZhzdDataAdmin admin = null;

	public ZhzdDataService() throws Exception {
		admin = new ZhzdDataAdmin(DbPoolFactory.getInstance().getPool(),
				new ZhzdSqlBuilder());
	}

	/**
	 * 根据唯一标识 获取数据对象
	 * dao层未实现
	 */
	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {

		try {
			return admin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException("根据代码查询数据失败!", e);
		}
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return admin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException("查询所有数据失败!", e);
		}
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param 源值集合
	 * @return 转换字典集合
	 */
	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return admin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException("根据key值查询失败!", e);
		}
	}

	/**
	 * dao层未实现
	 */
	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return admin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException("根据类型查询失败!", e);
		}
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(String type) {
		// TODO Auto-generated method stub
		return admin.getKeyConvertMap(type);
	}

	/**
	 * 性能优化    weijj 20140314
	 * Code 可以重复
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return (K) new Zhzd();
	}

	/**
	 * @param	应用场景
	 * @param	源值
	 * @return 转换值
	 */
	@Override
	public String specificDictConvert(String srcCode, String sceneType) {
		return admin.specificDictConvert(srcCode, sceneType);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		// TODO Auto-generated method stub
		return admin.getKeyConvertMap("");
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		String[] strs = new String[listKey.size()];
		return admin.getKeyConvertMap(listKey.toArray(strs));
	}

	@Override
	public Map<String, String> getConvertMapByGroupCode(String[] groupCodes) {
		return admin.getConvertMapByGroupCode(groupCodes);
	}
}
