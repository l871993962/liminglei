package com.yss.ifa.szt.tool.para.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.para.dao.ErParaDao;
import com.yss.ifa.szt.tool.para.dao.ErParaSqlBuilder;
import com.yss.ifa.szt.tool.para.service.IErParaDataService;
/**
 *  /// STORY42784中国银行_深证通伺服器要求采用热备模式
 *  /// STORY42660【中国银行】深证通伺服器要求采用热备模式
 * @ClassName: ErParaDao 
 * @Description: 深圳通参数设置
 * @author wulongxing
 * @date 2017年6月13日 下午2:51:31 
 *
 */
public class ErParaDataService implements IErParaDataService{

	private ErParaDao erParaDao = null;
	public ErParaDataService(){
		erParaDao = new ErParaDao(DbPoolFactory.getInstance().getPool(), new ErParaSqlBuilder());
	}
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		return (K) erParaDao.getPojoByCode(pojoCode);
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return (List<K>) erParaDao.getDataList();
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
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

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return erParaDao.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		try {
			return erParaDao.getKeyConvertMap(listKey);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
