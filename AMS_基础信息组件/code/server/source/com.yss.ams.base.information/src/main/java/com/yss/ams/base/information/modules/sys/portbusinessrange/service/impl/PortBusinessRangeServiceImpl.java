/**
 *
 * @Title: PortBusinessRelaServiceImpl.java 
 * @Package com.yss.ams.base.information.modules.sys.portbusinessrela.service.impl 
 * @date 2019年5月13日 下午5:52:03 
 * @version V1.0
 * @Stroy/Bug
 * @author xiadeqi   
 */
package com.yss.ams.base.information.modules.sys.portbusinessrange.service.impl;

import java.util.HashMap;
import java.util.List;
import com.yss.ams.base.information.modules.sys.portbusinessrange.dao.PortBusinessRangeDao;
import com.yss.ams.base.information.modules.sys.portbusinessrange.dao.PortBusinessRangeSqlBuilder;
import com.yss.ams.base.information.support.sys.portbusinessrange.service.IPortBusinessRangeService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;

/** 
 * 产品业务范围 service实现类
 * @ClassName: PortBusinessRelaServiceImpl 
 * @date 2019年5月13日 下午5:52:03
 * @Stroy72335/Bug
 * @author xiadeqi 
 */
public class PortBusinessRangeServiceImpl  extends ServiceBus<PortBusinessRangeServiceImpl> implements IPortBusinessRangeService {
	private PortBusinessRangeDao portBusinessRangeDao= null;
	public PortBusinessRangeServiceImpl() {
		portBusinessRangeDao = new PortBusinessRangeDao(DbPoolFactory.getInstance().getPool(), new PortBusinessRangeSqlBuilder());
		dao = portBusinessRangeDao;
	}
	
	@Override
	public List<Vocabulary> getDataListByType(String type)
			throws ServiceException {
		return portBusinessRangeDao.getDataListByType(type);
	}
	
	@Override
	public boolean updateDataList(String type, HashMap<String, String> paraMap)
			throws ServiceException {
		return portBusinessRangeDao.updateDataList(type, paraMap);
	}
	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> convertMap = new HashMap<String, String>();
		List<Vocabulary> list = portBusinessRangeDao.getDataListByType("AO_AUTO_BUSINESS");
		IVocCacheDataService vocService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService"); 
		convertMap.putAll(vocService.getKeyConvertMap());
		for (Vocabulary voc : list) {
			convertMap.put(voc.getC_DV_CODE(), voc.getC_DV_NAME());
		}
		return convertMap;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		HashMap<String, String> convertMap = new HashMap<String, String>();
		List<Vocabulary> list = portBusinessRangeDao.getDataListByType("AO_AUTO_BUSINESS");
		IVocCacheDataService vocService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService"); 
		convertMap.putAll(vocService.getKeyConvertMap(listKey));
		for (Vocabulary voc : list) {
			if (listKey.contains(voc.getC_DV_CODE())) {
				convertMap.put(voc.getC_DV_CODE(), voc.getC_DV_NAME());
			}
		}
		return convertMap;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
	public List<String> getPortListByBusiCode(String busiCode)
			throws ServiceException {
		return portBusinessRangeDao.getPortListByBusiCode(busiCode);
	}

	@Override
	public void insertPortBusinessRange(List<BasePojo> pojoList)
			throws ServiceException {
		portBusinessRangeDao.insertPortBusinessRange(pojoList);
	}
}
