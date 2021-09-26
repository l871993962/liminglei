package com.yss.ams.visaval.support.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : ChenLong
 * Date   : 2017-08-02
 * Status : Add
 * Comment: 旧算法执行服务接口
 */
public interface IVisJythonService {
	public double executeAlgo(String algoCode, HashMap<String, Object> algoPara,
			Connection conn) throws Exception;
	
	public Object enhancedPerform(String algoCode, HashMap<String, Object> algoPara,
			Connection conn)throws Exception;
	
	public void updateAlgo(String code, String algoContent);
	
	public Map<String,String> checkFormula(String algoContent) throws Exception;
}
