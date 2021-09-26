package com.yss.uco.elecreco.er.erresult.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
public interface IErResultService extends IServiceBus {
	
	public QueryRes queryOrigDataByCondition(HashMap<String, Object> paraMap);
	public boolean isExistAssetCode(String assetCode);
	

	/**
	 * 判断指标是否一致
	 * @param csn 报文序号
	 * @param rowFlag 指标代码或者科目代码
	 * @return
	 */
	public boolean isSameData(String csn, List<String> rowFlag);
	
	public int queryUnAcceptCount(String csn);
	
	/**
	 * 获取组合关联机构设置的托管人
	 * @param portcodes
	 * @return
	 */
	public Map<String, String> getPortRelaOrgData(String portcodes);

}
