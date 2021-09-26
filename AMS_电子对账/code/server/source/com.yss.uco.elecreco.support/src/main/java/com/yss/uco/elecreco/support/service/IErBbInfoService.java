package com.yss.uco.elecreco.support.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.vo.ErBbInfoVo;

@RestfulSupported
public interface IErBbInfoService extends IServiceBus {

	/**
	 * 修改电子对账信息审核状态、发送状态
	 * @param bbInfoList
	 * @param status
	 * @return
	 */
	@LinkControllerMethod(value = "updateBbInfo", arguTypes = { ErBbInfoVo.class })
	public String updateBbInfo(@LinkControllerMethodArgu("bbInfoList")List<ErBbInfo> bbInfoList,@LinkControllerMethodArgu("status")String status);
	
	/**
	 * 删除电子对账信息
	 * @param bbInfoList
	 * @return
	 */
	public String deleteBbInfo(List<ErBbInfo> bbInfoList);
	
	/**
	 * 根据报文序号获得发送的xml文件
	 * @param fsn
	 * @return
	 */
	String getXmlFile(ErBbInfo erBbInfo);
	
	String sendBbInfo(List<ErBbInfo> bbInfoList);
	
//	String witeBbInfo(List<ErBbInfo> bbInfoList);
	
	ErBbInfo getBbInfoById(String id);
	////重启对账管理器
	String reStartDzMgr();
	
	/**
	 * STORY31217【招商财富】电子对账管理已反馈新增一个人工可以修改处理状态的功能
	 * @param bbInfoList 
	 * @return
	 */
	String acceptBbInfo(List<ErBbInfo> pojoList);
	
	/**
	 * STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息 新增其他对账中的对账一致方法
	 * @param bbInfoList 
	 * @return
	 */
	String acceptBbInfoForQTDZ(List<ErBbInfo> pojoList);
	
	/**
	 * STORY #50374 电子对账功能优化 
	 * 操作不对账组合
	 * @param pojoList operType：add-设置为不对账，remove-解除不对账设置 
	 */
	@LinkControllerMethod(value = "UnPortOper", arguTypes = { ErBbInfoVo.class })
	String UnPortOper(@LinkControllerMethodArgu("bbInfoList")List<ErBbInfo> pojoList,@LinkControllerMethodArgu("operType")String operType);
	/**
	 * 净值锁定
	 * @param pojos
	 * @param execOperCode
	 * @param isCheckExe
	 * @return
	 */
	@LinkControllerMethod(value = "lockEconfirm", arguTypes = { ErBbInfoVo.class })
	String lockEconfirm(@LinkControllerMethodArgu("bbInfoList")List<ErBbInfo> pojos,@LinkControllerMethodArgu("execOperCode")String execOperCode,@LinkControllerMethodArgu("isCheckExe")String isCheckExe,@LinkControllerMethodArgu("executeType")String executeType);
	
	/**
	 * 获取电子对账结果信息
	 * @param dDate
	 * @param assCodes 资产代码
	 * @return
	 */
	@LinkControllerMethod(value = "getDzResultInfo", arguTypes = { ErBbInfoVo.class })
	public Map<String, ErBbInfo> getDzResultInfo(@LinkControllerMethodArgu("dDate")Date dDate, @LinkControllerMethodArgu("assCodes")String assCodes);

	/**
	 * 是否是人工一致的数据
	 * @param csn 报文序号
	 * @return
	 */
	public boolean isManualAccept(String csn);
	
	/**
     * wSTORY #77811 要可以撤销手工设置的对帐一致
     * 修改或删除对账一致数据
     * @param pojoList
     * @return
     */
	String unAcceptClick(List<ErBbInfo> pojoList);
    /**
     * wSTORY #77811 要可以撤销手工设置的对帐一致
     * 查询差异行数
     * @param pojoList
     * @return
     */
	 String queryNumberOfRows(ErBbInfo erBbInfo);
	 
}
