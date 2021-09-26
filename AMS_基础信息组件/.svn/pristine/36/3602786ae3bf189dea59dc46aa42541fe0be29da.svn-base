package com.yss.ams.base.information.modules.sys.voc.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.voc.controller.IUcoVocCacheDataController;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;
import com.yss.platform.support.dataservice.vo.QueryVocByTypeVo;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class UcoVocCacheDataControllerImpl extends AbstractBaseController<IVocCacheDataService> implements IUcoVocCacheDataController {

	@Override
    public void reloadCache(){
    	getService().reloadCache();
    }
    
    @Override
	public List<Vocabulary> getVocByType(QueryVocByTypeVo vo) {
		return castToListT(getService().getVocByType(vo.getParaMap(), vo.getClazz()),Vocabulary.class);
	}

	@Override
	public HashMap<String, String> getShortDataMap(String type) {
		return getService().getShortDataMap(type);
	}

	@Override
	public CacheData updateByTimestampAndKey(String timestamp, String key) {
		return getService().updateByTimestampAndKey(timestamp, key);
	}

	@Override
	public HashMap<String, String> getVocDic(List<String> keyList) {
		return getService().getVocDic(keyList);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return getService().getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws ServiceException {
		return getService().getKeyConvertMap(listKey);
	}

	@Override
	public List<Vocabulary> getDataList() throws ServiceException {
		return getService().getDataList();
	}

	@Override
	public RestfulQueryResult<Vocabulary> getDataListRes() throws ServiceException {
		return queryResToT(getService().getDataListRes(), Vocabulary.class);
	}

	@Override
	public Vocabulary getDataByCode(String dataCode) throws ServiceException {
		return getService().getDataByCode(dataCode);
	}

	@Override
	public Vocabulary getPojoByCode(String pojoCode) throws ServiceException {
		return getService().getPojoByCode(pojoCode);
	}

	@Override
	public List<Vocabulary> getDataListByTypes(String[] types) throws ServiceException {
		return getService().getDataListByTypes(types);
	}

	@Override
	public RestfulQueryResult<Vocabulary> getQueryResByTypes(String[] types) throws ServiceException {
		return queryResToT(getService().getQueryResByTypes(types), Vocabulary.class);
	}

	@Override
	public List<Vocabulary> getDataListByKeys(String[] keys) throws ServiceException {
		return getService().getDataListByKeys(keys);
	}

	@Override
	public RestfulQueryResult<Vocabulary> getQueryResByKeys(String[] keys) throws ServiceException {
		return queryResToT(getService().getQueryResByKeys(keys), Vocabulary.class);
	}

	@Override
	public CacheData updateByTimestamp(String timestamp) {
		return getService().updateByTimestamp(timestamp);
	}

	@Override
	public List<Vocabulary> queryByIds(String ids) {
		return getService().queryByIds(ids);
	}
	
	@Override
	public Vocabulary getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}
	
	@Override
	public List<Vocabulary> getCacheByTypes(String[] vocTypes) {
		return getService().getCacheByTypes(vocTypes);
	}
	
	@Override
	public List<Vocabulary> getCacheByType(String vocType) {
		return getService().getCacheByType(vocType);
	}
	
	
}