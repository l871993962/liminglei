package com.yss.ams.visaval.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.yss.ams.visaval.cache.AlgoCache;
import com.yss.ams.visaval.jython.AvalJythonFactory;
import com.yss.ams.visaval.jython.JythonTypeProcess;
import com.yss.ams.visaval.support.pojo.AdvAlgo;
import com.yss.ams.visaval.support.service.IVisJythonFactoryWrap;
import com.yss.ams.visaval.support.service.IVisJythonService;
import com.yss.ams.visaval.support.service.IVisAdvAlgoService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.service.YssServiceFactory;

/**
 * Author : ChenLong
 * Date   : 2017-07-25
 * Status : Add
 * Comment: Python工厂类包装服务
 */
public class JythonFactoryWrap implements IVisJythonFactoryWrap{
	@Override
	public double executeAlgo(String algoCode, HashMap<String, Object> algoPara,
			Connection conn) throws Exception{
		AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
		AdvAlgo advAlgo = algoCache.getCacheByKey(algoCode);
		// edit by lizhidong 20170921 华泰证券回归测试-产品指标配置算法类型设置为固定值，根据固定值取算法获取不到算法报错
		if (null == advAlgo || null == advAlgo.getC_ALGO_FORMULA() || advAlgo.getC_ALGO_FORMULA().trim().length() == 0) {
			return 0;
		}
		/* advAlgo.getC_ALGO_FORMULA_ZH()有值表明是新算法 */
		if(advAlgo.getC_ALGO_FORMULA_ZH() != null && !"".equals(advAlgo.getC_ALGO_FORMULA_ZH())){
			return AvalJythonFactory.getInstance().executeAlgo(algoCode, algoPara, conn);
		}else{
			IVisJythonService jythonService = YssServiceFactory.getInstance().createService(IVisJythonService.class);
			return jythonService.executeAlgo(algoCode, algoPara, conn);
		}
	}
	
	@Override
	public Object enhancedPerform(String algoCode, HashMap<String, Object> algoPara,
			Connection conn)throws Exception{
		AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
		AdvAlgo advAlgo = algoCache.getCacheByKey(algoCode);
		/* advAlgo.getC_ALGO_FORMULA_ZH()有值表明是新算法 */
		if(advAlgo.getC_ALGO_FORMULA_ZH() != null && !"".equals(advAlgo.getC_ALGO_FORMULA_ZH())){
			return AvalJythonFactory.getInstance().enhancedPerform(algoCode, algoPara, conn);
		}else{
			IVisJythonService jythonService = YssServiceFactory.getInstance().createService(IVisJythonService.class);
			return jythonService.enhancedPerform(algoCode, algoPara, conn);
		}
	}
	
	@Override
	public Map<String,String> checkFormula(String algoContent) throws Exception{
		AvalJythonFactory factory = AvalJythonFactory.getInstance();
		return factory.checkFormula(algoContent);
	}
	
	@Override
	public void updateAlgo(String code, String algoContent)throws Exception{
//		AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
//		AdvAlgo advAlgo = algoCache.getCacheByKey(code);
		//由于在算法公式新增的时候没有有效刷新缓存,所以这里直接采用去库里查询的方式来区分是否为新算法 add by yinyuyi 20190326
	    //这个可能为一种临时的解决方案,但是能够解决新算法审核成功的问题
		/* advAlgo.getC_ALGO_FORMULA_ZH()有值表明是新算法 */
	    
	    //是否为新算法
	    boolean isNewAlgo = false;
	    
	    IVisAdvAlgoService algoService = YssServiceFactory.getInstance().createService(IVisAdvAlgoService.class);
	    
	    isNewAlgo = Boolean.valueOf(algoService.isNewAlgo(code));
	    
		if(isNewAlgo){
			AvalJythonFactory.getInstance().updateAlgo(code, algoContent);
		}else{
			IVisJythonService jythonService = YssServiceFactory.getInstance().createService(IVisJythonService.class);
			jythonService.updateAlgo(code, algoContent);
		}
	}
	
	@Override
	public HashMap<String,String> toMapString_String(Object pyObject){
		return JythonTypeProcess.toMapString_String(pyObject);
	}
	
	@Override
	public HashMap<String,Double> toMapString_Double(Object pyObject){
		return JythonTypeProcess.toMapString_Double(pyObject);
	}
	
	@Override
	public boolean isNumber(Object pyObject){
		return JythonTypeProcess.isNumber(pyObject);
	}
	
	@Override
	public boolean isInteger(Object pyObject){
		return JythonTypeProcess.isInteger(pyObject);
	}

	@Override
	public HashMap<String, HashMap<String, Object>> toMapString_Map(
			Object pyObject) {
		return JythonTypeProcess.toMapString_Map(pyObject);
	}

	@Override
	public void initJyphonENV1(String apiName,Object obj) {
		AvalJythonFactory.getInstance().initJyphonENV1(apiName, obj);;
	}
	
	@Override
    public Object executeTest(){
		AvalJythonFactory factory = AvalJythonFactory.getInstance();
		return factory.executeTest();
	}
	
	@Override
    public Map<String,String> tempMap(){
		AvalJythonFactory factory = AvalJythonFactory.getInstance();
		return factory.tempMap();
	}
}
