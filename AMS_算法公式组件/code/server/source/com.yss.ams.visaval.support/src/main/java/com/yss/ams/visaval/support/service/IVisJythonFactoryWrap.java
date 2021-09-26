package com.yss.ams.visaval.support.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
/**
 * Author : ChenLong
 * Date   : 2017-07-25
 * Status : Add
 * Comment: Python工厂类包装服务接口
 */
public interface IVisJythonFactoryWrap{
	public double executeAlgo(String algoCode, HashMap<String, Object> algoPara,
			Connection conn) throws Exception;
	
	public Object enhancedPerform(String algoCode, HashMap<String, Object> algoPara,
			Connection conn)throws Exception;
	
	public Map<String,String> checkFormula(String algoContent) throws Exception;
	
	public void updateAlgo(String code, String algoContent)throws Exception;
	
	public HashMap<String,String> toMapString_String(Object pyObject);
	
	public HashMap<String,Double> toMapString_Double(Object pyObject);
	
	public boolean isNumber(Object pyObject);
	
	public boolean isInteger(Object pyObject);
	
	public HashMap<String, HashMap<String, Object>> toMapString_Map(Object pyObject);
	
	public void initJyphonENV1(String apiName,Object obj);
	
	public Object executeTest();
	
	public Map<String,String> tempMap();
}
