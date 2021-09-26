package com.yss.ams.base.information.support.sys.dztype.service;


import com.yss.ams.base.information.support.sys.dztype.pojo.DzType;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * 对账类型字典数据服务接口，主要进行跨应用数据获取
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@RestfulSupported
@GenericPojo(pojo = DzType.class)
public interface IDzTypeDataService extends IControlDataService,IKeyConvertDataService{

}
