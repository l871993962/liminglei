package com.yss.uco.elecreco.er.ws.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.httpclient.util.DateParseException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.yss.framework.api.busoperservice.IBusiness;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JAXBProcessor;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.uco.elecreco.er.ws.IElecCommWsAPI;
import com.yss.uco.elecreco.er.ws.cons.StateCons;
import com.yss.uco.elecreco.er.ws.dao.ElecCommWsDao;
import com.yss.uco.elecreco.er.ws.pojo.RequestHeader;
import com.yss.uco.elecreco.er.ws.pojo.ResponseHeader;
import com.yss.uco.elecreco.er.ws.pojo.dzdzstate.DzdzCommon;
import com.yss.uco.elecreco.er.ws.pojo.dzdzstate.RequestDzStateXml;
import com.yss.uco.elecreco.er.ws.pojo.dzdzstate.ResponseDzStateXml;
import com.yss.uco.elecreco.er.ws.pojo.execute.ExecuteResponseBody;
import com.yss.uco.elecreco.er.ws.pojo.execute.ExecuteResponseRoot;
import com.yss.uco.elecreco.support.service.IElecTaskService;

/**
 * STORY #90113 公募外包质控系统对接4.5对账系统新增接口需求
 *
 */
public class ElecCommWsAPI implements IElecCommWsAPI{

	/**
	 * 增加日志
	 */
	private static final Logger logger = LogManager.getLogger(ElecCommWsAPI.class);
	
	@Override
	public String execute(String reqXml) {
		HashMap<String, Object> hmData = null;
		ExecuteResponseRoot root = new ExecuteResponseRoot();
		root.setRepTime(DateUtil.getNow());
		try{
			Document document = null;
			Element reqRootEle = null;
			try {
				document = DocumentHelper.parseText(reqXml);
				//获取根节点
				reqRootEle = document.getRootElement(); 
			} catch (DocumentException e) {
				logger.error("报文体解析异常："+e.getMessage(),e);
				root.setRepCode(StateCons.S002.getValue());
				root.setRepDesc(StateCons.S002.getMsg());
				return parseResponseXmlInfo(ExecuteResponseRoot.class, root);
			}
			
			String dsFunc = getBodyElementText(reqRootEle, "", "DS_FUNC");
			if(!"DZDZCOMMON".equals(dsFunc)){
				root.setRepCode(StateCons.E001.getValue());
				root.setRepDesc(StateCons.E001.getMsg());
				return parseResponseXmlInfo(ExecuteResponseRoot.class, root);
			}
			
			String portCode = getBodyElementText(reqRootEle, "PROT_DZDZ", "PORT_CODE");
			String reportType = getBodyElementText(reqRootEle, "PROT_DZDZ", "REPORT_TYPE");
			String operType = getBodyElementText(reqRootEle, "PROT_DZDZ", "OPER_TYPE");
			String date = getBodyElementText(reqRootEle, "PROT_DZDZ", "DATE");
			
			String execOperCode = UUID.randomUUID().toString();
			hmData = new HashMap<String, Object>();
			hmData.put("ARRAY_C_PORT_CODE", portCode);
			hmData.put("ARRAY_C_DZ_CODE", reportType);
			hmData.put("D_START_DATE", date);
			hmData.put("D_END_DATE", date);
			hmData.put("C_OPER_CODE", execOperCode);
			hmData.put("C_DISPATCH_ID", execOperCode);
			hmData.put("C_FUN_CODE", "elecGene");
			hmData.put("OPER_TYPE", operType);
			IElecTaskService elecTaskService = YssServiceFactory.getInstance().createService(IElecTaskService.class);
			elecTaskService.doBusOper(hmData);
			List<BEN_RECORD> lstRecord = ((IBusiness)elecTaskService).getListRecord();
			if (lstRecord != null && lstRecord.size() > 0) {
				List<ExecuteResponseBody> bodyList = new ArrayList<ExecuteResponseBody>();
				ExecuteResponseBody body = null;
				for (BEN_RECORD record : lstRecord) {
					body = new ExecuteResponseBody();
					body.setC_DOING_TYPE(record.getC_Doing_Type().value());
					body.setC_PORT_CODE(record.getC_Port_Code());
					body.setD_TRADE(DateUtil.dateToString(record.getD_Trade(), "yyyy-MM-dd"));
					body.setC_DZCODE(record.getC_Item_Code());
					body.setC_DZNAME(record.getC_Item_Name());
					body.setC_MES_TEXT(record.getC_Mes_Text());
					body.setC_DETAIL_MES(record.getC_Mes_Detail());
					bodyList.add(body);
				}
				root.setResponseBody(bodyList);
			}
			root.setRepCode(StateCons.S000.getValue());
			root.setRepDesc(StateCons.S000.getMsg());
		}catch(Exception e){
			logger.error("ElecCommWsAPI execute error:" + hmData.toString(), e);
			root.setRepCode(StateCons.E999.getValue());
			root.setRepDesc(StateCons.E999.getMsg());
		}
		return parseResponseXmlInfo(ExecuteResponseRoot.class, root);
	}
	
	private String getBodyElementText(Element reqRootEle,String node,String childNode){
		String val = "";
		if(!StringUtil.IsNullOrEmptyT(node)){
			Element element = reqRootEle.element(node);
			if(element != null){
				val = element.elementText(childNode);
			}
		}else if(null != reqRootEle){
			val = reqRootEle.elementText(childNode);
		}
		return val;
	}
	
	/**
	 * 解析响应报文
	 * @param clazz 传入需要解析对应的数据实体类
	 * @param response 
	 * @return
	 * @throws WebserviceException 
	 */
	private String parseResponseXmlInfo(Class<?> clazz,ResponseHeader response){
		String result ="" ;
		response.setRepTime(DateUtil.getCurrDate("yyyyMMddHHmmss"));
		try {
			result = JAXBProcessor.marshalWithReturnString(clazz, response, "UTF-8");
		} catch (Exception e) {
			logger.error("解析响应XML失败："+e);
		}
		return result;
	}
	
	/**
	 * 解析请求报文
	 * @param xmlInfo 
	 * @param reqHeader
	 * @return
	 * @throws Exception
	 */
	private RequestHeader parseRequestXmlInfo(String xmlInfo,RequestHeader reqHeader) throws Exception{
		//logger.log("接收请求报文信息："+xmlInfo);
		//去掉报文中的CDATA标签
		xmlInfo = xmlInfo.replace("<![CDATA[", "");
		xmlInfo = xmlInfo.replace("]]>", "");
		xmlInfo = xmlInfo.replace("<DS_MSG_BODY>", "");
		xmlInfo = xmlInfo.replace("</DS_MSG_BODY>", "");
		JAXBProcessor jaxb = new JAXBProcessor();
		RequestHeader request = null;
		try {
			//解析xml字符串
			 request = jaxb.unMarshal(reqHeader,xmlInfo.trim());
		}catch(Exception e){
			logger.error("解析请求XML失败： "+e);
			throw new DateParseException("解析请求报文失败:"+e.getMessage());
		}
		return request;
	}

	/**
	 * STORY #96897 【300.7 0831】南方基金电子对账结果需要支持外部查询
	 */
	@Override
	public String queryDzdzState(String reqXml) {
		logger.info("请求报文：" + reqXml);
		String result = null;
		//预处理报文
		reqXml = reqXml.trim();
		reqXml = preDealxml(reqXml);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		
		//请求or响应对象
		ResponseDzStateXml responseInfo = new ResponseDzStateXml();
		responseInfo.setRepTime(sdf.format(new Date()));
		
		RequestDzStateXml requestXml = new RequestDzStateXml();
		

		String version = "1.0.0";
		JAXBProcessor jproc = new JAXBProcessor();

		logger.info("验证报文中");
		try {
			requestXml = jproc.unMarshal(requestXml, reqXml);
		} catch (YssException e) {
			responseInfo.setRepCode(StateCons.S002.getValue());
			responseInfo.setRepDesc(StateCons.S002.getMsg());
			logger.info("验证解析报文出错");
		}
		logger.info("验证解析报文完成");
		
		if (null != requestXml && !StringUtil.IsNullOrEmptyT(requestXml.getDS_VER())) {
			ElecCommWsDao wsDao = new ElecCommWsDao(DbPoolFactory.getInstance().getPool(),null);
			List<DzdzCommon> list = null;
			try {
			    list = wsDao.queryDzdz(requestXml);
			} catch (Exception e) {
				logger.error("查询电子对账数据出错", e);
			}

			if (list == null) {
				responseInfo.setRepCode(StateCons.S001.getValue());
				responseInfo.setRepDesc(StateCons.S001.getMsg());
				responseInfo.setDzdzList(list);
			}else {
				responseInfo.setRepCode(StateCons.S000.getValue());
				responseInfo.setRepDesc(StateCons.S000.getMsg());
				responseInfo.setDzdzList(list);
			}
		}
		
		try {
			result =  JAXBProcessor.marshalWithReturnString(ResponseDzStateXml.class, responseInfo, "UTF-8");
		} catch (YssException e) {
			logger.error("拼装返回数据失败", e);
		};
		
		return result;
	}
	
	/**
	 * 预处理报文
	 * @param xml
	 * @return
	 */
	private String preDealxml(String xml){
		xml = xml.replace("<![CDATA[", "");
		xml = xml.replace("]]>", "");
		xml = xml.replace("<DS_MSG_BODY>", "");
		xml = xml.replace("</DS_MSG_BODY>", "");
		xml = xml.replace("&nbsp;", " ");
		return xml;
	}

}
