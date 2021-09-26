package com.yss.ams.base.information.support.bi.hdaygroup.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.hdaygroup.pojo.HdayGroup;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;
/**
 * 节假日群服务接口
 */

/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
@RestfulSupported
public interface IHdayGroupService extends IServiceBus {
	/**
	 * 查询节假日群数据(树形结构显示)
	 * @param paraMap 参数MAP
	 * @return
	 */
	public QueryRes getTreeViewData(HashMap<String, String> paraMap);
	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * add by zuomingke
	 * date 20190713
	 * @return List<HdayGroup>
	 */
	public List<HdayGroup> queryAllHdayGroup();
}
