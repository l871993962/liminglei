package com.yss.ams.product.information.modules.aa.portcls.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcls.controller.IClsPortDataController;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortDataService;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetDataListByPortsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetPortClsByDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.PortClsRecordsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryFjSyfpInfoVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByClassVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByClsLevelVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByDvClsAndDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByDvClsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByLiquidVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsSortVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsYxqVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPreviousPortClsVo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class ClsPortDataControllerImpl extends AbstractBaseController<IClsPortDataService> implements IClsPortDataController {

    @Override
    public CacheData updateByTimestamp(String timestamp){
        return getService().updateByTimestamp(timestamp);
    }

    @Override
    public List<PortCls> queryByIds(String ids){
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
    public List<PortCls> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortCls> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortCls.class);
    }

    @Override
    public PortCls getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public PortCls getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<PortCls> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<PortCls> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),PortCls.class);
    }

    @Override
    public List<PortCls> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<PortCls> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),PortCls.class);
    }

    @Override
    public String getPortClsCode(String code){
        return getService().getPortClsCode(code);
    }

    @Override
    public HashMap<String,String> getShortDataMap(String portCode){
        return getService().getShortDataMap(portCode);
    }

    @Override
    public List<PortCls> getDataListByPorts(String[] ports){
        return getService().getDataListByPorts(ports);
    }

    @Override
    public String getClsCodesByPortCodeAndType(String portCode,String types){
        return getService().getClsCodesByPortCodeAndType(portCode,types);
    }

    @Override
    public PortCls queryPortClsByTypeAndClass(String portCode,String portClsType,String classPort){
        return getService().queryPortClsByTypeAndClass(portCode,portClsType,classPort);
    }


    @Override
    public List<PortCls> queryPortCls(String portCode,String portClsType,String portClsLevel){
        return getService().queryPortCls(portCode,portClsType,portClsLevel);
    }

    @Override
    public List<PortCls> queryPortClsMx(String portCode){
        return getService().queryPortClsMx(portCode);
    }

    @Override
    public List<PortCls> queryPortClsList(String portCode){
        return getService().queryPortClsList(portCode);
    }

	//String PortCode, Date dueDate
	@Override
	public List<PortCls> getPortClsByDate(GetPortClsByDateVo vo) {
		return getService().getPortClsByDate(vo.getPortCode(),vo.getDueDate());
	}

	//String portCode
	@Override
	public PortCls queryPortCls(QueryPortClsVo vo) {
		return (PortCls) getService().queryPortCls(vo.getPortCode());
	}

	//Date actDate, String port
	@Override
	public List<PortCls> portClsRecords(PortClsRecordsVo vo) {
		return getService().portClsRecords(vo.getActDate(),vo.getPort());
	}

	//String portCode, String classPort, Date actDate
	@Override
	public PortCls queryPortCls_Date(QueryPortClsDateVo vo) {
		return getService().queryPortCls_Date(vo.getPortCode(),vo.getClassPort(),vo.getActDate());
	}

	//String portCode,Date actDate, String classPort
	@Override
	public List<PortCls> queryPortClsByClass(QueryPortClsByClassVo vo) {
		return getService().queryPortClsByClass(vo.getPortCode(),vo.getActDate(),vo.getClassPort());
	}

	//String portCode, String classPort,Date actDate
	@Override
	public PortCls queryPortClsYxq(QueryPortClsYxqVo vo) {
		return getService().queryPortClsYxq(vo.getPortCode(),vo.getClassPort(),vo.getActDate());
	}

	//String port, Date accDate
	@Override
	public List<PortCls> queryPortCls4(QueryPortClsVo vo) {
		return getService().queryPortCls(vo.getPort(),vo.getAccDate());
	}

	//String port, Date accDate
	@Override
	public List<PortCls> queryPortClsByLiquid(QueryPortClsByLiquidVo vo) {
		return getService().queryPortClsByLiquid(vo.getPort(),vo.getAccDate());
	}

	//String portCode, String classPort, Date actDate
	@Override
	public PortCls queryPreviousPortCls(QueryPreviousPortClsVo vo) {
		return getService().queryPreviousPortCls(vo.getPortCode(),vo.getClassPort(),vo.getActDate());
	}

	//String portCode, Date actDate
	@Override
	public List<PortCls> queryPortClsByDvCls(QueryPortClsByDvClsVo vo) {
		return getService().queryPortClsByDvCls(vo.getPortCode(),vo.getActDate());
	}

	//String portCode,String dvCls,Date actDate
	@Override
	public List<PortCls> queryPortClsByDvCls1(QueryPortClsByDvClsVo vo) {
		return getService().queryPortClsByDvCls(vo.getPortCode(),vo.getDvCls(),vo.getActDate());
	}

	//String portCode,String clsLevel, Date actDate
	@Override
	public List<PortCls> queryPortClsByDvClsAndDate(
			QueryPortClsByDvClsAndDateVo vo) {
		return getService().queryPortClsByDvClsAndDate(vo.getPortCode(),vo.getClsLevel(),vo.getActDate());
	}

	//String portCode,String clsLevel, Date actDate
	@Override
	public PortCls queryPortClsByClsLevel(QueryPortClsByClsLevelVo vo) {
		return getService().queryPortClsByClsLevel(vo.getPortCode(),vo.getClsLevel(),vo.getActDate());
	}

	//String portCode, Date actDate, boolean sort
	@Override
	public PortCls queryPortClsSort(QueryPortClsSortVo vo) {
		return getService().queryPortClsSort(vo.getPortCode(),vo.getActDate(),vo.isSort());
	}

	//String portCode, Date actDate
	@Override
	public List<PortCls> queryFjSyfpInfo(QueryFjSyfpInfoVo vo) {
		return getService().queryFjSyfpInfo(vo.getPortCode(),vo.getActDate());
	}

	@Override
	public void insert(PortCls pojo) {
		 getService().insert(pojo);
	}

	@Override
	public void updateById(PortCls pojo) {
		 getService().updateById(pojo);
	}

	@Override
	public void deleteById(PortCls pojo) {
		 getService().deleteById(pojo);
	}

	@Override
	public List<PortCls> getDataListByPorts1(GetDataListByPortsVo vo) {
		return getService().getDataListByPorts(vo.getTypes().toArray(new String[]{}),vo.getClsPort().toArray(new String[]{}));
	}
	
	@Override
	public PortCls queryPortCls1(String portCode, String classPort) {
		return getService().queryPortCls(portCode,classPort);
	}

	@Override
	public List<PortCls> queryPortCls2(String portCode) {
		return getService().queryPortCls(portCode);
	}

}