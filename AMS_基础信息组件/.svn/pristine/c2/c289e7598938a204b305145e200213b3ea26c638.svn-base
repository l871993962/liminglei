package com.yss.ams.base.information.support.bi.region.service;

import java.util.List;

import com.yss.ams.base.information.support.bi.region.pojo.Area;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 地区信息设置普通服务接口，主要进行增删改查操作
 * @author 马向峰 拆分
 * @Date 20170601
 */
@RestfulSupported
public interface IAreaService extends IServiceBus {
	
	/**
	 * 获取所有地区信息
	 * @return QueryRes
	 */
	public QueryRes getAllAreasByType();
	
	/**
	 * 获取所有地区最上层所属地区
	 * @return List<Area>
	 */
	public List<Area> getAllTopAreas();
}
