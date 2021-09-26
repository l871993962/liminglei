package com.yss.ams.visaval.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yss.ams.visaval.activator.AvalActivator;
import com.yss.ams.visaval.cache.AlgoCache;
import com.yss.ams.visaval.dao.AdvAlgoDao;
import com.yss.ams.visaval.dao.AdvAlgoSqlBuilder;
import com.yss.ams.visaval.support.api.pojo.AlgoAPI;
import com.yss.ams.visaval.support.api.pojo.DataAPI;
import com.yss.ams.visaval.support.api.pojo.FunAPI;
import com.yss.ams.visaval.support.api.pojo.ParamAPI;
import com.yss.ams.visaval.support.api.pojo.ParamFromSql;
import com.yss.ams.visaval.support.api.pojo.ParentFunAPI;
import com.yss.ams.visaval.support.context.AvalAPIContext;
import com.yss.ams.visaval.support.pojo.AdvAlgo;
import com.yss.ams.visaval.support.pojo.AdvAlgoPara;
import com.yss.ams.visaval.support.service.IVisFunParamCNService;
import com.yss.ams.visaval.support.service.IVisJythonFactoryWrap;
import com.yss.ams.visaval.support.service.IVisJythonService;
import com.yss.ams.visaval.support.service.IVisParameterDataService;
import com.yss.ams.visaval.support.service.IVisAdvAlgoService;
import com.yss.ams.visaval.support.util.pojo.Parameter;
import com.yss.ams.visaval.util.Algo_T_S_PARAM_Init;
import com.yss.ams.visaval.util.Param_From_Sql_Init;
import com.yss.datacheck.annotation.CommonDataCheck;
import com.yss.datacheck.annotation.DefaultDataCheck;
import com.yss.datacheck.enums.CheckFuncGroup;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JsonUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;


/**
 * 数据安全接口实现类
 * 
 * @author ll
 * 
 */
@DefaultCacheRefresh(group = CacheGroup.ALGO)
public class AdvAlgoService extends ServiceBus<AdvAlgoService> implements
		IVisAdvAlgoService {

	private AdvAlgoDao advAlgoDao = null;

	public AdvAlgoService() throws Exception {
		advAlgoDao = new AdvAlgoDao(YssDbPoolFactory.getInstance().getDbPool(
				AvalActivator.class), new AdvAlgoSqlBuilder());
		dao = advAlgoDao;
	}

	@Override
    public String getAllDate() throws YssException {
		return advAlgoDao.getAllDate();
	}
	
	@Override
    public String checkFormula(String isNew, String targetStr)
			throws YssException {
		Map<String,String> checkResult = new HashMap<String, String>();
		try {
			if("True".equals(isNew)){
				IVisJythonFactoryWrap algoFactoryWrap = YssServiceFactory.getInstance().createService(IVisJythonFactoryWrap.class);
				checkResult = algoFactoryWrap.checkFormula(targetStr);
			}else{
				IVisJythonService jythonService = YssServiceFactory.getInstance().createService(IVisJythonService.class);
				checkResult = jythonService.checkFormula(targetStr);
			}
		} catch (Exception e) {
			logger.log("算法公式：公式检查出错", e);
		}
		return JsonUtil.toString(checkResult);
	}

	@Override
    public String getFunc(String funType) throws YssException {
		String retInfo = "";
		try {
			return advAlgoDao.getFunc(funType);

		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.log("算法公式：查询函数失败", ex);
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId);

		}
		return retInfo;

	}

	@Override
    public String getRealExpression(String customize) throws YssException {
		return advAlgoDao.getRealExpression(customize);
	}

	@Override
    public String getKeyWords(String keyType) throws YssException {
		return advAlgoDao.getKeyWords(keyType);
	}

	@Override
	public List<Parameter> getAllParameters() {
		IVisParameterDataService paraDataService = YssServiceFactory.getInstance()
				.createService(IVisParameterDataService.class);
		return paraDataService.getDataList();
	}

	/*
	 * Orlando 20160322 更新算法公式的同时更新python缓存 只有当审核机制未开启状态下才需要在此方法中更新python缓存 BUG
	 * #128367 【紧急】【南方资本】最新的公式执行的逻辑有误
	 * 
	 * @see com.yss.framework.api.mvc.biz.ServiceBus#updateById(java.util.List)
	 */
	@Override
    @DefaultDataCheck
	public String updateById(List<BasePojo> pojoList) {
		String value = "";
		try {
			value = super.updateById(pojoList);
		} catch (ErrorMessageException ex) {
			throw ex;
		}
		try {
			if (this.safeData != null && this.safeData.getN_CHECK() <= 0) {
				for (BasePojo pojo : pojoList) {
					AdvAlgo algo = (AdvAlgo) pojo;
					IVisJythonFactoryWrap factory = YssServiceFactory
							.getInstance().createService(
									IVisJythonFactoryWrap.class);
					factory.updateAlgo(algo.getC_ALGO_CODE(), 
							algo.getC_ALGO_FORMULA());
				}
			}
		} catch (Exception ex) {
			throw new ErrorMessageException(ex, "更新算法公式python缓存出错");
		}

		return value;
	}

	/*
	 * Orlando 20160322 审核算法公式的同时更新python缓存 只有当审核机制开启状态下才在此方法中更新python缓存 BUG
	 * #128367 【紧急】【南方资本】最新的公式执行的逻辑有误
	 * 
	 * @see com.yss.framework.api.mvc.biz.ServiceBus#auditById(java.util.List)
	 */
	@Override
    @CommonDataCheck(checkFuncGroup = CheckFuncGroup.Audit)
	public String auditById(List<BasePojo> pojoList) {
		String value = "";
		try {
			value = super.auditById(pojoList);
			//STORY #31713 【产品优化】算法公式配置优化  审核后刷新算法缓存  马向峰 20170906
			//AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
			/*algoCache.addData2Cache(pojoList);*/
		} catch (Exception ex) {
			throw new BusinessException("算法审核失败", ex);
		}
		try {
			for (BasePojo pojo : pojoList) {
				AdvAlgo algo = (AdvAlgo) pojo;
				String C_ALGO_FORMULA = advAlgoDao.getC_ALGO_FORMUL_ByCode(algo.getC_ALGO_CODE());
				IVisJythonFactoryWrap factory = YssServiceFactory.getInstance()
						.createService(IVisJythonFactoryWrap.class);
				factory.updateAlgo(algo.getC_ALGO_CODE(),
						C_ALGO_FORMULA);
			}
		} catch (Exception ex) {
			throw new ErrorMessageException(ex, "更新算法公式python缓存出错");
		}
		return value;
	}
	
	/**
	 * STORY #31713 【产品优化】算法公式配置优化
	 * 反审核后将对象移出缓存
	 * 20170906
	 * 马向峰
	 */
	/*@CommonDataCheck(checkFuncGroup = CheckFuncGroup.UnAudit)
	public String unAuditById(List<BasePojo> pojoList) {
		String value = "";
		try {
			value = super.unAuditById(pojoList);
			
			AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
			algoCache.removeDataFromCache(pojoList);
		} catch (Exception e) {
			logger.log("将算法移出缓存出错", e);
			throw new ErrorMessageException(e, "将算法移出缓存出错");
		}
		
		return value;
	}
*/
	@Override
    public AdvAlgo getAdvAlgo(String key) {
		AlgoCache algoCache = CacheManager.getInstance().getCache(
				CacheGroup.ALGO);
		AdvAlgo advAlgo = algoCache.getCacheByKey(key);
		return advAlgo;
	}

	@Override
	public String getAllDataTree_A() {
		List<AdvAlgo> list = null;
		try {
			list = advAlgoDao.getAllDataTree_A();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtil.toString(list);
	}

	@Override
	public String getTreeData(String algo_type) {
		
		if ("SF_YYFY".equals(algo_type) || "SF_ZHJX".equals(algo_type)){
			return JsonUtil.toString(getAlgoAPI());
		}
		
		return JsonUtil.toString(getExcept_YYFY_JX_Data());
	}
	
	/**
	 * 得到不包含运营费用和账户计息函数数据；除运营费用和账户计息之外的算法展示
	 * @return
	 */
	private List<BasePojo> getExcept_YYFY_JX_Data(){
		List<BasePojo> list = new ArrayList<BasePojo>();
		Map<String, List<AlgoAPI>> map = AvalAPIContext.getInstance().getEXCEPT_YYFY_JX_Map();
		//modified by yangweijie 2017-9-9 bug指标分级产品下的产品在查询时候前台报异常
		if(map.size()!=0){
			list.addAll(map.get("EXCEPT_YYFY_ZHJX_DATA"));
		}
		//list.addAll(map.get("EXCEPT_YYFY_ZHJX_DATA"));
//		list.addAll(map.get("EXCEPT_YYFY_DATA"));
		return list;
	}
	
	/**
	 * 包含所有函数-->运营费用和账户计息展示
	 * @return
	 */
	private List<BasePojo> getAlgoAPI(){
		
		Map<String, FunAPI> funAPIMap = AvalAPIContext.getInstance().getFunAPIMap();
		Map<String, ParentFunAPI> parentFunMap = AvalAPIContext.getInstance().getFunParentAPIMap();
		List<BasePojo> list = new ArrayList<BasePojo>();
		Iterator<String> it = funAPIMap.keySet().iterator();
		while (it.hasNext()) {
			AlgoAPI algoAPI = new AlgoAPI();
			FunAPI funAPI = funAPIMap.get(it.next());
			algoAPI.setAlgoCode(funAPI.getCode());
			algoAPI.setAlgoDesc(funAPI.getDesc());
			algoAPI.setAlgoParent(funAPI.getParent());
			algoAPI.setAlgoText(funAPI.getText());
			algoAPI.setAlgoValue(funAPI.getValue());
			
			list.add(algoAPI);
		}
		
		Iterator<String> iter = parentFunMap.keySet().iterator();
		while (iter.hasNext()) {
			AlgoAPI algoAPI = new AlgoAPI();
			ParentFunAPI parentFunAPI = parentFunMap.get(iter.next());
			algoAPI.setAlgoCode(parentFunAPI.getCode());
			algoAPI.setAlgoParent(parentFunAPI.getParent());
			algoAPI.setAlgoText(parentFunAPI.getText());
			
			list.add(algoAPI);
		}
		
		return list;
	}

	@Override
	public String getDataAPIByCode(String code) {
		DataAPI api = getDataAPI(code);
		String json = JsonUtil.toString(api);
		return json;
	}
	
	private DataAPI getDataAPI(String code){
		DataAPI dataAPI = new DataAPI();
		FunAPI funAPI = AvalAPIContext.getInstance().getFunAPIMap().get(code);
		dataAPI.setParamAPIs(funAPI.getParamAPIs());
		dataAPI.setReturnAPI(funAPI.getReturnAPI());
		return dataAPI;
	}

	@Override
	public Map<String, String> getParasByCode(String code) {
		
		Map<String, String> result = new HashMap<String, String>();
		//关键字来自T_S_PARAM表
			result = advAlgoDao.getParasByCode(code);
			if(null != result && result.size()>0){
				return result;
			}
		//关键字来自keyparam_sql.xml或keyparam.xml
			if (AvalAPIContext.getInstance().getKeyWordParamMap().containsKey(code)){
				result = AvalAPIContext.getInstance().getKeyWordParamMap().get(code);
			}else{
				List<ParamFromSql>  paramFromSqls= Param_From_Sql_Init.getInstance().getData().get(code);
				//这里添加下拉框来自配置文件sel_param.xml
				if (null == paramFromSqls){
					Map<String, Map<String, String>> selMap = AvalAPIContext.getInstance().getSelParamMap();
					if(selMap.containsKey(code)){
						result = selMap.get(code);
						return result;
					}
					
				}else{
					for (ParamFromSql paramFromSql : paramFromSqls) {
						result.put(paramFromSql.getValue(), paramFromSql.getName());
					}
				}
			}
		return result;
	}

	/**
	 * 参考算法：只允许参考新算法，过滤掉旧算法
	 */
	@Override
	public String getAlgos() {
		List<AdvAlgo> list = advAlgoDao.getAlgos();
		List<AdvAlgo> adList = new ArrayList<AdvAlgo>();
		//参考算法只允许参考新算法，过滤掉旧算法
		for (AdvAlgo advAlgo : list) {
			String isNew = isNewAlgo(advAlgo.getC_ALGO_CODE());
			if("True".equals(isNew)){
				adList.add(advAlgo);
			}
		}
		
		return JsonUtil.toString(adList);
	}

	@Override
	public String getAlgoDesc(String code) {

		return advAlgoDao.getAlgoDesc(code);
	}

	@Override
	public BasePojo getAlgoByCode(String code) {
		AdvAlgo algo = advAlgoDao.getAlgoByCode(code);
		return algo;
	}

	@Override
	public Map<String, Boolean> getRuleByFunCode(String code) {
		List<ParamAPI> list = getDataAPI(code).getParamAPIs();//APIManager.getInstance().getAPIDataByCode(code)
		return getRule(list);
	}

	/**
	 * RULE: isdefault=true 将value的值填充到文本框 不可编辑; isdefault=false and source有值
	 * 文本框不可编辑 函数参数显示name的值; isdefault=false 但是source没有值 文本框可以编辑;
	 * 
	 * @param list
	 * @return
	 */
	private Map<String, Boolean> getRule(List<ParamAPI> list) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		for (ParamAPI paramAPI : list) {
			// 为TRUE则在添加函数的时候参数显示名称，为FALSE则显示值
			if (paramAPI.isIsdefault() == false && paramAPI.getSource() != null
					&& paramAPI.getSource() != "") {
				map.put(paramAPI.getCode(), true);
			} else {
				map.put(paramAPI.getCode(), false);
			}
		}
		return map;
	}

	@Override
	public List<String> FunRegex(String fun) {
		List<String> list = new ArrayList<String>();
		/*fun = fun.replaceAll("=", " = ");
		fun = fun.replaceAll("\\+", " + ");
		fun = fun.replaceAll("\\-", " - ");
		fun = fun.replaceAll("\\*", " * ");
		fun = fun.replaceAll("/", " / ");*/
		Pattern pattern = Pattern.compile("[\\S]+\\((?<=\\()[^\\)]+\\)");
		Matcher matcher = pattern.matcher(fun);
		while (matcher.find()) {
			list.add(matcher.group());
		}

		return list;
	}
	
	private String FunRegex(String fun,List<String> list) {
		fun = fun.replaceAll("=", "=");
		fun = fun.replaceAll("\\+", " + ");
		fun = fun.replaceAll("\\-", " - ");
		fun = fun.replaceAll("\\*", " * ");
		fun = fun.replaceAll("/", " / ");
		Pattern pattern = Pattern.compile("[\\S]+\\((?<=\\()[^\\)]+\\)");
		Matcher matcher = pattern.matcher(fun);
		while (matcher.find()) {
			list.add(matcher.group());
		}

		return fun;
	}

	
	private Map<String,FunAPI> getFunKeyNameMap(){
		Map<String, FunAPI> funAPIMap = AvalAPIContext.getInstance().getFunAPIMap();
		Map<String,FunAPI> funKeyNameMap = new HashMap<String, FunAPI>();
		Iterator<String> ite = funAPIMap.keySet().iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			funKeyNameMap.put(funAPIMap.get(key).getText(), funAPIMap.get(key));
		}
		
		return funKeyNameMap;
	}
	
	private int paramCount(String param) {
		int count = 0;
		char[] ch = param.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (",".equals(ch[i]+"")) {
				++count;
			}
		}
		return count;
	}
	
	
	@Override
	public String preview(String msg) {
		
		//标记参数是否合法，若不合法则直接返回False
		boolean tempFlag = false;
		
		String warnFunName = "";
		
		StringBuffer preString = new StringBuffer();
		
		//正则表达式取出各个函数
		List<String> funList = new ArrayList<String>();
		
		/**
		 * 遍历函数集合，取出函数名称，根据函数名称取出函数信息，找出参数对应的英文并组装成英文函数，
		 */
		String result = FunRegex(msg,funList);
		
		for (String fun : funList) {
			String oldFun = fun;
			int firstIndex = fun.indexOf('(');
			String funName = fun.substring(0, firstIndex);
			
			//检查参数中是否包含空参数，如果有空参数，则不给予翻译，返回提醒信息
			String allParam = getParam(fun);
			String[] ps = allParam.split(",");
			
			for(String strParam : ps){
				if("".equals(strParam) || null == strParam || "" == strParam){
					tempFlag = true;
					warnFunName = funName;
					break;
				}
			}
			
			if(tempFlag){
				break;
			}
			
			int count = paramCount(allParam);
			if(ps.length - count != 1){
				tempFlag = true;
				warnFunName = funName;
				break;
			}
			
			// 解析出函数各个参数
			List<String> params = parseParam(fun);
//			 AvalAPIContext.getInstance().getFunAPIMap();
			Map<String, FunAPI> funMap = getFunKeyNameMap();
			if (funMap.containsKey(funName)) {
				FunAPI funAPI = funMap.get(funName);
				
				//给算法添加connection字段 self.conn
				if (funAPI.isHasConnection() == true){
					String preStr = fun.substring(0, firstIndex+1);
					String endStr = fun.substring(firstIndex+1, fun.length());
					String finalStr = preStr + "self.conn," + endStr;
					//result = result.replace(fun,finalStr);
					fun = fun.replace(fun,finalStr);
				}
				
				//替换函数名
				fun = fun.replaceFirst(funAPI.getText(),funAPI.getValue());
				// 找出了对应的参数
				List<ParamAPI> paramAPIs = funAPI.getParamAPIs();
				
				//规定参数个数一定，故只需遍历从缓存中取出的参数集合即可得到相对应的前台参数
				for (int i=0;i<paramAPIs.size();i++){
					//包含关键字
					if (paramAPIs.get(i).getKeyWord() != null && paramAPIs.get(i).getKeyWord() != ""){
						//根据关键字判断该参数是否加algoPara['']
						boolean flag = advAlgoDao.isFromT_S_Param(paramAPIs.get(i).getKeyWord());
						if (flag){
							fun = fun.replaceFirst(params.get(i),"algoPara['" + paramAPIs.get(i).getKeyWord() + "']");
							//添加前缀
							
							Map<String,String> paramMap = Algo_T_S_PARAM_Init.getInstance().getData();
//							if (null != enCode && enCode != ""){
								if (paramMap.containsKey(paramAPIs.get(i).getKeyWord())){
									preString.append("#" + paramAPIs.get(i).getKeyWord() +"$" +  params.get(i)  + "\r\n");
								}else{
									String enCode = getCnToEnCode(paramAPIs.get(i).getKeyWord(),params.get(i));
									if (null != enCode && enCode != ""){
										preString.append("#" + paramAPIs.get(i).getKeyWord() +":" + enCode + "\r\n");
									}
								}
//							}else{
//								if (paramMap.containsKey(paramAPIs.get(i).getKeyWord())){
//									preString.append("#" + paramAPIs.get(i).getKeyWord() +"$" + params.get(i) + "\r\n");
//								}else{
//									preString.append("#" + paramAPIs.get(i).getKeyWord() +":" + params.get(i) + "\r\n");
//								}
//							}
						}else{
							if (i < params.size()){
								String enCode = getCnToEnCode(paramAPIs.get(i).getKeyWord(),params.get(i));
								fun = fun.replaceFirst(params.get(i),'"' + enCode + '"');
							}
							
						}
						//<param code="blws" name="保留位数" isdefault="false" keyword="GZ_WS" isFromFile="true" hasdetails="false"></param>
						/*if (paramAPIs.get(i).isIsdefault() == false && paramAPIs.get(i).isFromFile() == true && paramAPIs.get(i).isHasdetails() == false){
							result = result.replaceFirst(params.get(i),"algoPara['" + paramAPIs.get(i).getKeyWord() + "']");
						}*/
					}else{
						if (paramAPIs.get(i).isIsdefault() == false && paramAPIs.get(i).getSource() != null && paramAPIs.get(i).getSource() != ""
								&& paramAPIs.get(i).getName().equals(params.get(i))) {
							fun = fun.replaceFirst(params.get(i),paramAPIs.get(i).getSource());
							continue;
						}
					}
				}
			}
			
			result = result.replace(oldFun, fun);
		}
		
		if(tempFlag){
			return "FalseFun" + "$" + warnFunName;
		}
		
		return preString.toString() + result;
	}
	
	private String getParam(String fun){
		Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
		Matcher matcher = pattern.matcher(fun);
		String str = "";
		if(matcher.find()) {
			str = matcher.group();
		}
		return str;
	}
	
	private List<String> parseParam(String fun) {
		Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
		Matcher matcher = pattern.matcher(fun);
		String str = "";
		if(matcher.find()) {
			str = matcher.group();
		}
		return Arrays.asList(str.split(","));
	}

	@Override
	public String isNewAlgo(String code) {
		
		return advAlgoDao.isNewAlgo(code);
	}

	@Override
	public String hasKeyWord(String code) {
		Map<String, FunAPI> funAPIMap = AvalAPIContext.getInstance().getFunAPIMap();
		Iterator<String> ite = funAPIMap.keySet().iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			FunAPI funAPI = funAPIMap.get(key);
			List<ParamAPI> paramList = funAPI.getParamAPIs();
			for(int i=0;i<paramList.size();i++){
				if(paramList.get(i).getCode().equals(code)){
					String keyword = paramList.get(i).getKeyWord();
					if(null != keyword && keyword != ""){
						return "True";
					}
				}
			}
			
		}
		return "False";
	}
	
	/**
	 * 算法前缀有$标记，为了满足该需求，需要加载t_s_param表数据来判断是否在前缀中添加$
	 * @return
	 */
	public Map<String,String> getT_S_PARAM_Data(){
		
		return advAlgoDao.getT_S_PARAM_Data();
	}

	@Override
	public String checkAlgoStatus(String code) {
		return advAlgoDao.checkAlgoStatus(code);
	}

	@Override
	public String checkFunName(String funName) {
		String flag = "False";
		Map<String, FunAPI> map = getFunKeyNameMap();
		if (map.containsKey(funName)){
			flag = "True";
		}
		return flag;
	}

	@Override
	public String getFunList(String funData) {
		List<String> list = FunRegex(funData);
		return JsonUtil.toString(list);
	}

	/**
	 * 根据参数关键字以及参数中文值去获取参数英文
	 * 如果在上下文中找不到就从参数数据表中找然后在放入上下文中供下次使用
	 * 上下文和参数数据表都没有找到返回空值
	 * @param keyword 参数关键字
	 * @param name    参数中文
	 * @return
	 */
	@Override
    public String getCnToEnCode(String keyword,String name){
		String code = AvalAPIContext.getInstance().getCnTopEnCode(keyword, name);
		if("".equals(code)){
			Map<String,String> map = AvalAPIContext.getInstance().getCNSMap();
			String sql = map.get(keyword);
			if (null == sql || "".equals(sql)){
				return "";
			}
			IVisFunParamCNService funParamCNService = YssServiceFactory.getInstance().createService(IVisFunParamCNService.class);
			code = funParamCNService.getCode(sql, name);
			if(!"".equals(code)){
				AvalAPIContext.getInstance().addCnToEnMap(keyword, code, name);
			}
		}
		
		return code;
	}

	@Override
	public String paramHasDetail(String paramName_CH) {
		Map<String, Map<String, String>> map = AvalAPIContext.getInstance().getCnToEnMap();
		Iterator<String> ite = map.keySet().iterator();
		while (ite.hasNext()) {
			String keyword = ite.next();
			Map<String, String> valMap = map.get(keyword);
			if (valMap.containsKey(paramName_CH)){
				//根据关键字获取参数信息
				Map<String, ParamAPI> paramAPIMap = AvalAPIContext.getInstance().getParamAPIMap();
				ParamAPI paramAPI = paramAPIMap.get(keyword);
				if (null != paramAPI && paramAPI.isHasdetails()){
					return "True";
				}
			}
		}
		return "False";
	}

	@Override
	public String getParamCodeByParamName(String paramName_CH) {
		Map<String, Map<String, String>> map = AvalAPIContext.getInstance().getCnToEnMap();
		Iterator<String> ite = map.keySet().iterator();
		while (ite.hasNext()) {
			String keyword = ite.next();
			Map<String, String> valMap = map.get(keyword);
			if (valMap.containsKey(paramName_CH)){
				//根据关键字获取参数信息
//				Map<String, ParamAPI> paramAPIMap = AvalAPIContext.getInstance().getParamAPIMap();
//				ParamAPI paramAPI = paramAPIMap.get(keyword);
				return valMap.get(paramName_CH);
			}
		}
		return "";
	}

	@Override
	public List<AdvAlgo> getAllAchivmentAlgo() throws Exception {
		logger.info("查询算法的公式，只查询部分的字段，不是全部的字段！");
		return advAlgoDao.getAllAchivmentAlgo();
	}
	
	@Override
	public AdvAlgo getAchivmentAlgoByCode(String code) throws Exception {
		logger.info("查询算法的公式，查询全部的字段！");
		return advAlgoDao.getAchivmentAlgoByCode(code);
	}

    @Override
    public List<AdvAlgoPara> getParamListByCode(List<String> paramCodeList) throws Exception {
        return advAlgoDao.getParamListByCode(paramCodeList);
    }
}
