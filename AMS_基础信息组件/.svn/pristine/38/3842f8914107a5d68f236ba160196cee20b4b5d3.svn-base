package com.yss.ams.base.information.modules.sys.convertdict.zhzd.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.sys.convertdict.zhzd.controller.IZhzdDataController;
import com.yss.ams.base.information.support.sys.convertdict.zhzd.pojo.Zhzd;
import com.yss.ams.base.information.support.sys.convertdict.zhzd.service.IZhzdDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class ZhzdDataControllerImpl extends AbstractBaseController<IZhzdDataService> implements IZhzdDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<Zhzd> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<Zhzd> getDataListRes(){
        return queryResToT(getService().getDataListRes(),Zhzd.class);
    }

    @Override
    public Zhzd getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public Zhzd getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<Zhzd> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<Zhzd> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),Zhzd.class);
    }

    @Override
    public List<Zhzd> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<Zhzd> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),Zhzd.class);
    }

    @Override
    public String specificDictConvert(String srcCode,String sceneType){
        return getService().specificDictConvert(srcCode,sceneType);
    }

    @Override
    public Map<String,String> getConvertMapByGroupCode(String[] groupCodes){
        return getService().getConvertMapByGroupCode(groupCodes);
    }

	@Override
	public HashMap<String, String> getKeyConvertMap2(String type) {
		 return getService().getKeyConvertMap();
	}

}