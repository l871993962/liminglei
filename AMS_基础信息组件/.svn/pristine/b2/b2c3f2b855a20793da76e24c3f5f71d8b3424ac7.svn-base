package com.yss.ams.base.information.support.sys.daeelem.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 核算元素字典普通服务接口，主要进行增删改查操作
 *
 */
@RestfulSupported
public interface IDaeElemService extends IServiceBus {

	/**
	 * 通过核算元素代码获取核算元素名称
	 * @param daeCode
	 * @return
	 */
	public String getDaeNameByDaeCode(String daeCode);
	
	/**
	 * 需求 4068 过滤显示明细核算元素Jinghehe
	 * @param paraMap
	 * @return
	 */
	public ArrayList<String> getDaeCodesByCondition(HashMap<String, String> paraMap);
	
	
	/**
	 * 根据证券品种code判断是否含有子类没有 从而得出数据是否是明细 By Jinghehe
	 * @param code
	 * @return
	 */
	public String isDetailByCode(String code);
	
	
	/**
	 * 获得证券品种父类code
	 * @param code
	 * @return
	 */
	public String getParentCodeByCode(String code);
	
}
