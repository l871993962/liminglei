package com.yss.ams.visaval.support.context;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.visaval.support.api.pojo.AlgoAPI;
import com.yss.ams.visaval.support.api.pojo.FunAPI;
import com.yss.ams.visaval.support.api.pojo.ParamAPI;
import com.yss.ams.visaval.support.api.pojo.ParentFunAPI;

public class AvalAPIContext {
	private static AvalAPIContext context = null;
	
	public static AvalAPIContext getInstance(){
		if(context == null){
			synchronized (AvalAPIContext.class) {
				if(context == null){
					context = new AvalAPIContext();
				}
			}
		}		
		
		return context;
	}
	
	/**
	 * 初始化配置文件内下拉框信息
	 */
	private Map<String,Map<String,String>> selParamMap = new HashMap<String, Map<String,String>>();
	public Map<String,Map<String,String>> getSelParamMap(){
		return selParamMap;
	}
	
	public void addSelParamMap(String key,Map<String,String> param){
		if(selParamMap.containsKey(key)){
			selParamMap.remove(key);
		}
		
		selParamMap.put(key, param);
	}
	
	public void removeSelParamMap(String key){
		selParamMap.remove(key);
	}
	
	/**
	 * 根据关键字获取参数信息
	 */
	private Map<String,ParamAPI> paramAPIMap = new HashMap<String, ParamAPI>();
	
	public Map<String,ParamAPI> getParamAPIMap(){
		return paramAPIMap;
	}
	
	public void addParamAPIMap(String key,ParamAPI param){
		if(paramAPIMap.containsKey(key)){
			paramAPIMap.remove(key);
		}
		
		paramAPIMap.put(key, param);
	}
	
	public void removeParamAPIMap(String key){
		paramAPIMap.remove(key);
	}
	
	/**
	 * 存储参数为下拉框的信息，关键字、中文名，value
	 */
	private Map<String,Map<String,String>> selkeyvalueMap = new HashMap<String, Map<String,String>>();
	
	public Map<String,Map<String,String>> getSelKeyValueMap(){
		return selkeyvalueMap;
	}
	
	public void addselkeyvalueMap(String key,Map<String,String> map){
		if(selkeyvalueMap.containsKey(key)){
			selkeyvalueMap.remove(key);
		}
		
		selkeyvalueMap.put(key, map);
	}
	
	/**
	 * cnssetting.xml解析数据
	 */
	private Map<String,String> cnsMap = new HashMap<String, String>();
	
	/**
	 * 得到cnssetting配置文件数据
	 * @return
	 */
	public Map<String,String> getCNSMap(){
		return cnsMap;
	}
	
	public void removeCNSMap(String key){
		cnsMap.remove(key);
	}
	
	public void addCNSMap(String key,String sql){
		if(cnsMap.containsKey(key)){
			cnsMap.remove(key);
		}
		
		cnsMap.put(key, sql);
	}
	
	/**
	 * 关键字来源于SQL查找的数据来源
	 */
	private Map<String,String> paramsFromSqlMap = new HashMap<String, String>();
	/**
	 * 得到函数对象
	 * @return
	 */
	public Map<String,String> getParamsFromSqlMap(){
		return paramsFromSqlMap;
	}
	
	public void removeParamsFromSqlMap(String key){
		paramsFromSqlMap.remove(key);
	}
	
	public void addParamsFromSqlMap(String key,String sql){
		if(paramsFromSqlMap.containsKey(key)){
			paramsFromSqlMap.remove(key);
		}
		
		paramsFromSqlMap.put(key, sql);
	}
	
	
	/**
	 * API函数数据来源
	 */
	private Map<String,FunAPI> funAPIMap = new LinkedHashMap<String, FunAPI>();
	
	/**
	 * 得到函数对象
	 * @return
	 */
	public Map<String,FunAPI> getFunAPIMap(){
		return funAPIMap;
	}
	
	public void removeFunAPIMap(String key){
		funAPIMap.remove(key);
	}
	
	/**
	 * 添加函数对象
	 * @param key
	 * @param funAPI
	 */
	public void addFunAPIMap(String key,FunAPI funAPI){
		if(funAPIMap.containsKey(key)){
			funAPIMap.remove(key);
		}
		
		funAPIMap.put(key, funAPI);
	}
	
	/**
	 * 除了运营费用和账户
	 */
	//private Map<String,List<AlgoAPI>> except_YYFY_ZHJX_Map = new LinkedHashMap<String, List<AlgoAPI>>();
	
	/**
	 * 存储：除了运营费用和账户计息之外的函数列表
	 */
	private Map<String,List<AlgoAPI>> EXCEPT_YYFY_JX_Map = new HashMap<String, List<AlgoAPI>>();
	
	public Map<String,List<AlgoAPI>> getEXCEPT_YYFY_JX_Map(){
		return EXCEPT_YYFY_JX_Map;
	}
	
	public void removeEXCEPT_YYFY_JX_Map(String key){
		EXCEPT_YYFY_JX_Map.remove(key);
	}
	public void addEXCEPT_YYFY_JX_Map(String key,List<AlgoAPI> yYFY_List){
		if(funAPIMap.containsKey(key)){
			EXCEPT_YYFY_JX_Map.remove(key);
		}
		
		EXCEPT_YYFY_JX_Map.put(key, yYFY_List);
	}
	
	
	/**
	 * API函数父节点数据来源
	 */
	private Map<String,ParentFunAPI> funParentAPIMap = new LinkedHashMap<String, ParentFunAPI>();
	
	/**
	 * 得到函数父节点对象
	 * @return
	 */
	public Map<String,ParentFunAPI> getFunParentAPIMap(){
		return funParentAPIMap;
	}
	
	public void removeFunParentAPIMap(String key){
		funParentAPIMap.remove(key);
	}
	
	/**
	 * 添加函数父节点对象
	 * @param key
	 * @param parentFunAPI
	 */
	public void addFunParentAPIMap(String key,ParentFunAPI parentFunAPI){
		if(funParentAPIMap.containsKey(key)){
			funParentAPIMap.remove(key);
		}
		
		funParentAPIMap.put(key, parentFunAPI);
	}
	
	/**
	 * API函数参数关键字的数据来源
	 */
	private Map<String,Map<String,String>> keyWordParamMap = new HashMap<String,Map<String,String>>();
	
	public Map<String,Map<String,String>> getKeyWordParamMap(){
		return keyWordParamMap;
	}
	
	public void addKeyWordParamMap(String key,Map<String,String> map){
		if(keyWordParamMap.containsKey(key)){
			keyWordParamMap.remove(key);
		}
		
		keyWordParamMap.put(key, map);
	}
	
	public Map<String,String> getKeyWordParamMapParamMap(String key){
		return keyWordParamMap.get(key);
	}
	
	public void removeKeyWordParamMap(String key){
		keyWordParamMap.remove(key);
	}
	
	/**
	 * 存放中文与英文转换数据
	 */
	private Map<String,Map<String,String>> cnToEnMap = new HashMap<String,Map<String,String>>();
	
	public Map<String,Map<String,String>> getCnToEnMap(){
		return cnToEnMap;
	}
	
	/**
	 * 根据中文名称获取对应的英文名
	 * @param keyword 参数关键字
	 * @param name    参数中文
	 * @return
	 */
	public String getCnTopEnCode(String keyword,String name){
		String code = "";
		if(cnToEnMap.containsKey(keyword)){
			Map<String,String> map = cnToEnMap.get(keyword);
			if(map.containsKey(name)){
				code = map.get(name);
			}
		}
		return code;
	}
	
	/**
	 * 缓存参数中文与英文关系
	 * @param keyword 参数关键字
	 * @param code    参数英文
	 * @param name    参数中文
	 */
	public void addCnToEnMap(String keyword,String code,String name){
		Map<String,String> map = null;
		if(cnToEnMap.containsKey(keyword)){
			map = cnToEnMap.get(keyword);
			map.put(name, code);
		}else{
			map = new HashMap<String, String>();
			map.put(name, code);
			cnToEnMap.put(keyword, map);
		}
	}
	
	/**
	 * 移除关键字参数中文与英文关系
	 * @param keyword 参数关键字
	 */
	public void removeCnToEnMap(String keyword){
		if(cnToEnMap.containsKey(keyword)){
			cnToEnMap.remove(keyword);
		}
	}
}
