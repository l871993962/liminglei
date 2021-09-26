package com.yss.ams.sec.information.modules.sv.base.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseInfoDataController;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecShortPojo;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseInfoDataService;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetDataListByTypesAndDateVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetDataListByTypesVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetSecPortCodeVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetShortDataListVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.UpdateByTimestampPageVo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.CacheDataExtend;
import com.yss.framework.api.common.co.ShortDataListPackage;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class SecBaseInfoDataControllerImpl extends AbstractBaseController<ISecBaseInfoDataService> implements ISecBaseInfoDataController{

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<SecBase> queryByIds(String ids){
        return getService().queryByIds(ids);
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<SecBase> getDataList(){
        return castToListT(getService().getDataList(),SecBase.class);
    }

    @Override
    public RestfulQueryResult<SecBase> getDataListRes(){
        return queryResToT(getService().getDataListRes(),SecBase.class);
    }

    @Override
    public SecBase getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public SecBase getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<SecBase> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<SecBase> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),SecBase.class);
    }

    @Override
    public List<SecBase> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<SecBase> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),SecBase.class);
    }

    @Override
    public List<SecBase> getDataListByTypesAndMkt(String[] types){
        return getService().getDataListByTypesAndMkt(types);
    }

    @Override
    public List<SecBase> getDataListByDaes(String parameter){
        return getService().getDataListByDaes(parameter);
    }

    @Override
    public SecBase getSecBaseInfoDataBySecCode(String cSecCode){
        return (SecBase) getService().getSecBaseInfoDataBySecCode(cSecCode);
    }

    @Override
    public SecBase getSecBaseInfoDataBySecCodeFromDb(String cSecCode){
        return (SecBase) getService().getSecBaseInfoDataBySecCodeFromDb(cSecCode);
    }

    @Override
    public SecBase getSecCacheByCode(String secCode){
        return (SecBase) getService().getSecCacheByCode(secCode);
    }

    @Override
    public SecBase getSecByVarDur(SecBase secBase){
        return getService().getSecByVarDur(secBase);
    }

    @Override
    public HashMap<String,String> getShortDataMap(){
        return getService().getShortDataMap();
    }

    @Override
    public List<SecBase> getAllIndexDataList(){
        return getService().getAllIndexDataList();
    }

    @Override
    public void insert(List<SecBase> list){
         getService().insert(list);
    }

    @Override
    public SecBase getRate(SecBase secBase){
        return getService().getRate(secBase);
    }

    @Override
    public SecBase getSec(SecBase secBase){
        return getService().getSec(secBase);
    }

    @Override
    public List<SecBase> dbjxSecs(List<String> secCodeList){
        return getService().dbjxSecs(secCodeList);
    }

    @Override
    public SecBase getDataBySecMktCodeAndMktCode(String secMktCode,String mktCode){
        return getService().getDataBySecMktCodeAndMktCode(secMktCode,mktCode);
    }

    @Override
    public String getUpdateByTimestampCount(String timestamp){
        return getService().getUpdateByTimestampCount(timestamp);
    }

    @Override
    public List<SecBase> getDataListBySjsszq(String[] types){
        return getService().getDataListBySjsszq(types);
    }

    @Override
    public List<SecBase> getDataListByIndiv(){
        return getService().getDataListByIndiv();
    }

    @Override
    public String isExistsStock(String secCode){
        return getService().isExistsStock(secCode);
    }

    @Override
    public String getMktNo(){
        return getService().getMktNo();
    }

    @Override
    public CacheDataExtend updateByTimestampNew(String timestamp){
        return getService().updateByTimestampNew(timestamp);
    }

    @Override
    public String isExistsAct(String secCode){
        return getService().isExistsAct(secCode);
    }

    @Override
    public String isExistsStk(String secCode){
        return getService().isExistsStk(secCode);
    }

    @Override
    public CacheData updateByIds(String ids){
        return getService().updateByIds(ids);
    }

    @Override
    public void setCurrUser(String userCode){
        getService().setCurrUser(userCode);
    }

	@Override
	public List<SecBase> getDataList1() {
		return getService().getDataList();
	}

	@Override
	public List<SecBase> getDataListByTypes1(String[] types) {
		return getService().getDataListByTypes(types);
	}

	//String[] types,String like, PageInation page
	@Override
	public ShortDataListPackage<SecShortPojo> getShortDataList(
			GetShortDataListVo getShortDataListVo) throws ServiceException {
		return getService().getShortDataList(getShortDataListVo.getTypes().toArray(new String[]{}),getShortDataListVo.getLike(),getShortDataListVo.getPage());
	}

	//String[] types, String dateStr
	@Override
	public List<SecBase> getDataListByTypesAndDate(
			GetDataListByTypesAndDateVo getDataListByTypesAndDateVo) {
		return getService().getDataListByTypesAndDate(getDataListByTypesAndDateVo.getTypes().toArray(new String[]{}),getDataListByTypesAndDateVo.getDateStr());
	}

	//SecBase secBase,String portCode
	@Override
	public SecBase getSecPortCode(GetSecPortCodeVo getSecPortCodeVo)
			throws ServiceException {
		return getService().getSecPortCode(getSecPortCodeVo.getSecBase(),getSecPortCodeVo.getPortCode());
	}

	//String timestamp,PageInation page
	@Override
	public CacheData updateByTimestampPage(
			UpdateByTimestampPageVo updateByTimestampPageVo) {
		return getService().updateByTimestampPage(updateByTimestampPageVo.getTimestamp(),updateByTimestampPageVo.getPage());
	}

	//String[] types, String paraValue
	@Override
	public List<SecBase> getDataListByTypes(GetDataListByTypesVo vo) {
		return castToListT(getService().getDataListByTypes(vo.getTypes().toArray(new String[]{}),vo.getParaValue()),SecBase.class);
	}

	@Override
	public List<String> UpdateDifferent(String codes){
		return getService().UpdateDifferent(codes);
	}
	
	@Override
	public CacheData updateByCodes(String codes){
		return getService().updateByCodes(codes);
	}
	
}