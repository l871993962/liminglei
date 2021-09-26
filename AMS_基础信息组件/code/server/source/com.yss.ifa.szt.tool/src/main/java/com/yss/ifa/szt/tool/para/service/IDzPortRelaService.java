package com.yss.ifa.szt.tool.para.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.ifa.szt.tool.pojo.DzRelaOrgan;

@RestfulSupported
@GenericPojo(pojo=DzRelaOrgan.class)
public interface IDzPortRelaService extends IServiceBus{
	/**
	 * 
	 * @param paraMap
	 * @param page
	 * @return ��ϴ���
	 */
	List<String> queryPortCodesRelaOrgan(HashMap<String, Object> paraMap);
	
	/**
	 * ����ҵ��������ɾ���� 
	 * @param pojoList
	 * @return
	 */
	String delInsert(List<BasePojo> pojoList);
	
	/**
	 * ����ҵ������ɾ��
	 * @param pojoList
	 * @return
	 */
	String delByYwId(List<BasePojo> pojoList);
}
