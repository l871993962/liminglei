package com.yss.ams.visaval.support.api.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.visaval.support.api.pojo.AlgoAPI;
import com.yss.ams.visaval.support.api.pojo.DataAPI;
import com.yss.ams.visaval.support.api.pojo.FunAPI;
import com.yss.ams.visaval.support.api.pojo.ParamAPI;
import com.yss.ams.visaval.support.api.pojo.ReturnAPI;
import com.yss.ams.visaval.support.util.AlgoAPIXMLParse;
import com.yss.ams.visaval.support.util.pojo.APIFunsU;
import com.yss.ams.visaval.support.util.pojo.AlgoAPIU;
import com.yss.ams.visaval.support.util.pojo.ChildAPIU;
import com.yss.ams.visaval.support.util.pojo.ChildFunU;
import com.yss.ams.visaval.support.util.pojo.ParamU;
import com.yss.ams.visaval.support.util.pojo.ParentAPIU;
import com.yss.ams.visaval.support.util.pojo.ParentFunU;
import com.yss.ams.visaval.support.util.pojo.ReturnValU;
import com.yss.framework.api.common.co.BasePojo;

/**
 * 算法API数据管理类 单例模式
 * 
 * @author 马向峰
 * @Date 20170713
 */
public class APIManager {

	/**
	 * 封装前台树数据
	 */
	private static List<BasePojo> algoAPIs = new ArrayList<BasePojo>();

	private static Map<String, DataAPI> dataAPIMap = new HashMap<String, DataAPI>();
	
	private List<ChildFunU> childFunUs = new ArrayList<ChildFunU>();

	/**
	 * 封装前台参数数据
	 */

	private static APIManager instance = null;

	private APIManager() {
		initData();
	}

	/**
	 * 构造APIManager
	 * 
	 * @return
	 */
	public static APIManager getInstance() {

		if (null == instance) {
			synchronized (APIManager.class) {
				if (null == instance) {
					instance = new APIManager();
				}
			}
		}
		
		return instance;
	}

	/**
	 * 返回前台树对象
	 * 
	 * @return
	 */
	public List<BasePojo> getAlgoAPI() {

		return algoAPIs;
	}

	/**
	 * 初始化前台树 只封装树结构数据 参数等数据请求获取
	 */
	public void initData() {
		// 加载解析XML
		AlgoAPIXMLParse xmlParse = new AlgoAPIXMLParse();
		AlgoAPIU apiU = xmlParse.load();
		// 获取functions节点对应对象
		APIFunsU apiFunsU = apiU.getApiFuns();
		// 获取parent节点对应对象
		ParentAPIU parentAPIU = apiFunsU.getParentAPI();
		// 得到parent下function节点集合
		List<ParentFunU> parentFunUList = parentAPIU.getParentFunList();
		ChildAPIU childAPIU = apiFunsU.getChildAPI();
		// 得到child下function节点集合
		List<ChildFunU> childFunUList = childAPIU.getChildFunList();
		childFunUs = childFunUList;
		initTreeData(parentFunUList, childFunUList);
		initParamAndReturnValue(childFunUList);
	}

	/**
	 * 封装参数和返回值
	 * 
	 * @param childFunUList
	 */
	public void initParamAndReturnValue(List<ChildFunU> childFunUList) {

		for (int i = 0; i < childFunUList.size(); i++) {
			ChildFunU childFunU = childFunUList.get(i);
			List<ParamU> paramUList = childFunU.getParamAPI().getParamList();
			DataAPI dataAPI = new DataAPI();
			// 封装ParamAPI
			for (int j = 0; j < paramUList.size(); j++) {
				ParamU paramU = paramUList.get(j);
				ParamAPI paramAPI = new ParamAPI();
				paramAPI.setIsdefault(paramU.isDefault());
				paramAPI.setName(paramU.getName());
				paramAPI.setParamValue(paramU.getValue());
				paramAPI.setKeyWord(paramU.getKeyWord());
				paramAPI.setSource(paramU.getSource());
				paramAPI.setCode(paramU.getCode());
				paramAPI.setHasdetails((paramU.isHasDetails()));
				
				dataAPI.getParamAPIs().add(paramAPI);
			}

			// 封装
			ReturnValU returnValU = childFunU.getReturnVal();
			ReturnAPI returnAPI = new ReturnAPI();
			returnAPI.setDesc(returnValU.getName());
			returnAPI.setReturnType(returnValU.getType());

			dataAPI.setReturnAPI(returnAPI);

			dataAPIMap.put(childFunU.getCode(), dataAPI);
		}

	}

	/**
	 * 封装树对象
	 * 
	 * @param parentFunUList
	 * @param childFunUList
	 */
	private void initTreeData(List<ParentFunU> parentFunUList,
			List<ChildFunU> childFunUList) {
		// 前台树结构用AlgoAPI进行封装，parent="Root"为根节点，根节点和子节点都封装到AlgoAPI，并加到algoAPIs
		// 返回给前台
		for (int i = 0; i < parentFunUList.size(); i++) {
			AlgoAPI api = new AlgoAPI();
			ParentFunU parentFunU = parentFunUList.get(i);
			api.setAlgoCode(parentFunU.getCode());
			api.setAlgoText(parentFunU.getText());
			api.setAlgoParent(parentFunU.getParent());

			algoAPIs.add(api);
		}

		// 封装子节点,并加到algoAPIs 返回给前台
		for (int i = 0; i < childFunUList.size(); i++) {
			AlgoAPI api = new AlgoAPI();
			ChildFunU childFunU = childFunUList.get(i);
			api.setAlgoCode(childFunU.getCode());
			api.setAlgoText(childFunU.getText());
			api.setAlgoParent(childFunU.getParent());
			api.setAlgoDesc(childFunU.getDesc());

			algoAPIs.add(api);
		}
	}

	/**
	 * 根据节点code获取参数和返回值
	 * 
	 * @param code
	 * @return DataAPI
	 */
	public DataAPI getAPIDataByCode(String code) {
		if (null != code && dataAPIMap.containsKey(code)) {
			return dataAPIMap.get(code);
		}
		return null;
	}

	/**
	 * 返回所有函数信息，包含参数
	 * @return
	 */
	public List<FunAPI> getFuns(){
		List<FunAPI> list = new ArrayList<FunAPI>();
		for (ChildFunU ch : childFunUs) {
			FunAPI fun = new FunAPI();
			fun.setCode(ch.getCode());
			fun.setDesc(ch.getDesc());
			fun.setParamAPIs(getParamAPI(ch.getParamAPI().getParamList()));
			fun.setParent(ch.getParent());
			fun.setText(ch.getText());
			fun.setValue(ch.getValue());
			fun.setHasConnection(ch.isHasConnection());
			
			list.add(fun);
		}
		return list;
	}
	
	private List<ParamAPI> getParamAPI(List<ParamU> paramUList){
		List<ParamAPI> list = new ArrayList<ParamAPI>();
		for (int j = 0; j < paramUList.size(); j++) {
			ParamU paramU = paramUList.get(j);
			ParamAPI paramAPI = new ParamAPI();
			paramAPI.setIsdefault(paramU.isDefault());
			paramAPI.setName(paramU.getName());
			paramAPI.setParamValue(paramU.getValue());
			paramAPI.setKeyWord(paramU.getKeyWord());
			paramAPI.setSource(paramU.getSource());
			paramAPI.setCode(paramU.getCode());
			paramAPI.setHasdetails((paramU.isHasDetails()));
			list.add(paramAPI);
		}
		return list;
	}
}
