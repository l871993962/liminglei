package com.yss.ams.base.information.modules.bi.ieLink.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.ieLink.dao.IeLinkDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

/**
 * 收支连接设置业务管理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class IeLinkDataAdmin extends BaseAdmin {
	private IeLinkDao svcDao = null;

	public IeLinkDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new IeLinkDao(pool, sqlBuilder);
	}

	/**
	 * 获取所有收支链接设置所有数据
	 * @return	收支链接设置数据集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList() throws Exception {
		return (List<T>) svcDao.getAllDataList();
	}

	/**
	 * 根据收支链接代码获取收支链接设置
	 * @param code  收支链接
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code) throws Exception {
		return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 根据收支链接代码获取收支链接设置list
	 * @param code  收支链接
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByCodes(String[] codes)
			throws Exception {
		return (List<T>) svcDao.getDataListByCodes(codes);
	}
	
	/**
	 * 根据收支链接类型获取收支链接设置
	 * @param types 收支链接
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByTypes(types);
	}

	/**
	 * 获取收支链接设置转换map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap(){
		return svcDao.getKeyConvertMap();
	}

	/**
	 * 根据多个收支链接类型获取收支链接设置
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws Exception {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	/**
	 * 根据多个收支链接上级费用节点获取收支链接设置list
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public <T extends BasePojo> List<T> getDataListByParentCode(String[] codes) {
		// TODO Auto-generated method stub
		return (List<T>) svcDao.getDataListByParentCode(codes);
	}

	public String getFeeCodeByKmCode(String kmCode) {
		String[] kmcodes = kmCode.split("\\.");
		return svcDao.getFeeCodeByKmCode(kmcodes);
			
	}
	
	/**
	 * add by liyanjun 2016-2-17 BUG #126592 科目体系界面的费用代码选项数据重复
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllFeeDataList() throws Exception {
		return (List<T>) svcDao.getAllFeeDataList();
	}
	
}
