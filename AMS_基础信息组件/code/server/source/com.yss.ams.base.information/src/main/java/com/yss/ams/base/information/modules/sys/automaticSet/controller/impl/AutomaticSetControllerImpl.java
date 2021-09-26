package com.yss.ams.base.information.modules.sys.automaticSet.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.sys.automaticSet.controller.IAutomaticSetController;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojo;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojoVo;
import com.yss.ams.base.information.support.sys.automaticSet.service.IAutomaticSetService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;


/**
* STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
* @author yangze
* @date 2020-12-24
*/
public class AutomaticSetControllerImpl extends AbstractBaseServiceBusController<AutomaticSetPojo, IAutomaticSetService> implements IAutomaticSetController {

    @Override
    public HashMap<String,String> getKeyConvertMap() {
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey) {
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<AutomaticSetPojo> getDataList() {
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<AutomaticSetPojo> getDataListRes() {
        return queryResToT(getService().getDataListRes(), AutomaticSetPojo.class);
    }

    @Override
    public AutomaticSetPojo getDataByCode(String dataCode) {
        return getService().getDataByCode(dataCode);
    }

    @Override
    public List<Vocabulary> getDataListByType(String type) {
        return getService().getDataListByType(type);
    }

    @Override
	public boolean updateDataList(AutomaticSetPojoVo vo) {
		return getService().updateDataList(vo.getType(), vo.getParaMap());
	}

    @Override
    public List<String> getPortListByBusiCode(AutomaticSetPojoVo vo) {
        return getService().getPortListByBusiCode(vo.getType(), vo.getBusiCode());
    }
    
    @Override
    public List<Map<String, String>> getBusiInfoByCode(AutomaticSetPojoVo vo) {
        return getService().getBusiInfoByCode(vo.getType(), vo.getPortCode());
    }
    
    @Override
    public List<String> getAllBusiType() {
        return getService().getAllBusiType();
    }
    
    @Override
    public List<String> getBusiTypeByCode(String portCode) {
        return getService().getBusiTypeByCode(portCode);
    }

	@Override
	public List<String> getBusiTypeByCodes(AutomaticSetPojoVo vo) {
		return getService().getBusiTypeByCodes(vo.getType(), vo.getPortCodes());
	}
}