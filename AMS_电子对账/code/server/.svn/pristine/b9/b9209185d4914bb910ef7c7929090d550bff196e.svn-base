package com.yss.uco.elecreco.er.erdblgz.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;
import com.yss.uco.elecreco.er.erdblgz.vo.ErDblgzbVo;

@RestfulSupported
public interface IErDblgzbService extends IServiceBus {
	/**
	 * 根据条件查询电子对账估值信息
	 * @param paraMap
	 * @return
	 */
	 public List<ErDblgzb> getGzData(HashMap<String, Object> paraMap);
	 
	 /**
	  * 插入数据
	  * @param list
	  * @param conn
	  */
	 @LinkControllerMethod(value = "insertDatas", arguTypes = { ErDblgzbVo.class })
	 public void insertDatas(@LinkControllerMethodArgu("list")List<ErDblgzb> list,@LinkControllerMethodArgu("conn")Connection conn);
}
