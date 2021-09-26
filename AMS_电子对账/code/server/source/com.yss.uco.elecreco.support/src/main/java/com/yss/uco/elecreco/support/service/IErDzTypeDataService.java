package com.yss.uco.elecreco.support.service;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.support.bean.ErDzType;

@RestfulSupported
@GenericPojo(pojo = ErDzType.class)
public interface IErDzTypeDataService extends IControlDataService,IKeyConvertDataService{
	/**
	 * 获取电子对账生成界面对账类型的数据
	 * @return
	 */
	public List<BasePojo> getGeneDzType();
	
	/**
	 * 获取电子对账所有对账类型的数据（包含没有许可的）
	 * @return
	 */
	public List<BasePojo> getAllDzTypeMap();
}