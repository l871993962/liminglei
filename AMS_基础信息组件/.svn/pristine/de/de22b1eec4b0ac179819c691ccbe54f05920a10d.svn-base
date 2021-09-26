package com.yss.ams.base.information.modules.bi.ieLink.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.ieLink.admin.IeLinkDataAdmin;
import com.yss.ams.base.information.modules.bi.ieLink.dao.IeLinkSqlBuilder;
import com.yss.ams.base.information.support.bi.ieLink.service.IIeLinkDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 收支连接设置数据服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class IeLinkDataService implements IIeLinkDataService {
	private IeLinkDataAdmin ieLinkDataAdmin = null;

	public IeLinkDataService() {
		ieLinkDataAdmin = new IeLinkDataAdmin(DbPoolFactory.getInstance()
				.getPool(), new IeLinkSqlBuilder());
	}

	/**
	 * 获取所有收支链接设置所有数据
	 * @return	收支链接设置数据集合
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return ieLinkDataAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	/**
	 * 查询关联信息，用于查询收支连接设置关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieLinkDataAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("base_ieLink");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_ieLink",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据收支链接代码获取收支链接设置
	 * @param code  收支链接
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return ieLinkDataAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 根据收支链接代码获取收支链接设置list
	 * @param code  收支链接
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByCodes(String[] codes)
		       throws ServiceException {
			try {
				return ieLinkDataAdmin.getDataListByCodes(codes);
			}catch(Exception e){
				throw new ServiceException(e);
			}
		}

	/**
	 * 根据收支链接类型获取收支链接设置
	 * @param types 收支链接
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return ieLinkDataAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 查询关联信息，用于查询收支连接设置关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieLinkDataAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("base_ieLink");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_ieLink",InformationActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 获取收支链接设置转换map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(){
		return ieLinkDataAdmin.getKeyConvertMap();
	}

	/**
	 * 根据多个收支链接类型获取收支链接设置
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return ieLinkDataAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 查询关联信息，根据多个收支连接类型查询收支连接设置关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = ieLinkDataAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("base_ieLink");
			res.setHeadKeyList(ServiceAssistance.getListHead("base_ieLink",InformationActivator.class));
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
	 * 根据收支链接代码获取收支链接设置
	 * @param code  收支链接
	 * @return
	 * @throws Exception
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return ieLinkDataAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据多个收支链接上级费用节点获取收支链接设置list
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	@Override
	public <K extends BasePojo> List<K> getDataListByParentCode(String[] codes) {
		try {
			return ieLinkDataAdmin.getDataListByParentCode(codes);
		}catch(Exception e){
			throw new ServiceException(e);
		}
	}
	
	/**
	 * add by liyanjun 2016-2-17 BUG #126592 科目体系界面的费用代码选项数据重复
	 * 收支分类项中，如果同一费用代码设置在了多个收支项目，则前台费用控件就会出现多个相同的费用代码，在选择费用时就选择不上了。为了避免影响其他功能的调用，现在在新方法中对费用代码做去重处理
	 * 同BUG #124462 手工凭证录入费用代码选择不了
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getFeeDataList() throws ServiceException {
		try {
			return ieLinkDataAdmin.getAllFeeDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		return null;
	}

}
