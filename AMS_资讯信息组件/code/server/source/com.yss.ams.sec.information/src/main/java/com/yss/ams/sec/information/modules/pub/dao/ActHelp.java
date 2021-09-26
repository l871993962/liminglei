
package com.yss.ams.sec.information.modules.pub.dao;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wltea.expression.PreparedExpression;
import org.wltea.expression.datameta.BaseDataMeta;
import org.wltea.expression.datameta.Variable;

//import com.yss.cache.CaCache;
//import com.yss.cache.DcCache;
//import com.yss.cache.DtTdModeCache;
//import com.yss.cache.DvaItemCache;
//import com.yss.cache.IeCache;
//import com.yss.cache.MktCache;
//import com.yss.cache.PortCache;
//import com.yss.cache.PortClsCache;
//import com.yss.cache.SecBaseCache;
//import com.yss.cache.SecVarCache;
//import com.yss.cache.VocCache;
//import com.yss.dayf.act.businessOper.entity.BaseEntity;
//import com.yss.dayf.act.businessOper.entity.MultipleFeeEntity;
//import com.yss.dayf.act.businessOper.entity.base.FeeBean;
//import com.yss.dayf.act.businessOper.entity.base.FieldAnnotation;
//import com.yss.dayf.act.businessOper.entity.base.FieldAnnotation.ActTableField;
//import com.yss.dayf.act.businessOper.entity.base.KeyWordBean;
//import com.yss.dayf.actProvider.statistic.basepojo.ArithResult;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.YssFun;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IPortDataService;
import com.yss.platform.support.dataservice.service.IVocDataService;

//import dataservice.comm.pojo.CashAcc;
//import dataservice.comm.pojo.DcCury;
//import dataservice.comm.pojo.DttdMode;
//import dataservice.comm.pojo.DvaItem;
//import dataservice.comm.pojo.Ie;
//import dataservice.comm.pojo.SecVar;
//import dataservice.service.IDCDataService;
//import dataservice.service.IDtaTdAttrDataService;
//import dataservice.service.IDvaItemDataService;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * @classDesc	核算框架处理辅助类
 * @version 1.0 2011-11-3
 * @author yh
 * ===================================
 * 删除一些无用的import by lihaizhi 20130620
 */
public class ActHelp {
	
	private static Logger logger = LogManager.getLogger(ActHelp.class);
	
	/**
	 * 根据字段名和pojo对象,获取对象对应属性的value值
	 * @param fieldName	字段名
	 * @param entityPojo 实体对象
	 * @return 属性值
	 * @throws Exception 
	 */
//	public static Object getFieldValueFromEntity(String fieldName,BaseEntity entityPojo) throws Exception{
//		Field field = null;
//		Object value = 0;
//		//首先从对象的普通属性中找
//		field = getFieldFromClass(fieldName,entityPojo);
//		if(null == field){
//		    //从BaseEntity的actKeyWords属性中查找
//			KeyWordBean actKeyWords = entityPojo.getActKeyWords();
//			if(null != actKeyWords){
//				if(actKeyWords.containsKeyWord(fieldName)){
//					return actKeyWords.getKeyWord(fieldName);
//				}
//			}
//			//从特定实体的属性中查找
//			//如果是包含多笔费用的实体,包含feeInfo属性
//			if(entityPojo instanceof MultipleFeeEntity){
//				MultipleFeeEntity multipleFeeEntity = (MultipleFeeEntity)entityPojo;
//				FeeBean feeInfo = multipleFeeEntity.getFeeInfo();
//				if(null != feeInfo){
//					if(feeInfo.containsFee(fieldName)){
//						return feeInfo.getFee(fieldName);
//					}
//				}
//			}
//		  }
//		else{
//			try {
//				value = field.get(entityPojo);
//			} catch (IllegalArgumentException e) {
////				e.printStackTrace();
//				logger.log("根据字段名和pojo对象,获取对象对应属性的value值出错", e);
//				throw (Exception)e;
//			} catch (IllegalAccessException e) {
////				e.printStackTrace();
//				logger.log("根据字段名和pojo对象,获取对象对应属性的value值出错", e);
//				throw (Exception)e;
//			}
//		}
//		if(value instanceof Integer)
//			value = Double.valueOf((Integer)value);
//		if(value instanceof BigDecimal)
//			value = new BigDecimal(value.toString());
//		return value;
//	}
	
	/**
	 * 从对象本身的属性中查找字段，不包含引用类属性自包含的值
	 * @param fieldName 字段名
	 * @param entityPojo 对象
	 * @return 字段值
	 * @throws Exception 
	 */
//	public static Field getFieldFromClass(String fieldName,BaseEntity entityPojo) throws Exception{
//		Field field = null;
//		boolean isEnd = false;
//		Class<?> cls = entityPojo.getClass();
//		while(!isEnd){
//			try {
//				field = cls.getDeclaredField(fieldName);
//				field.setAccessible(true);
//				return field;
//			}  catch (NoSuchFieldException e) {
//				//从继承类中查找
//				cls = cls.getSuperclass();
//				if(null == cls){ isEnd = true;}
//			} catch (IllegalArgumentException e) {
////				e.printStackTrace();
//				logger.log("从对象本身的属性中查找字段出错", e);
//				throw (Exception)e;
//			} 
//		}
//		return field;
//	}
	
	/**
	 * 把摘要中代码转换为对应为文本，例如把交易方式代码转换为交易方式名称，把词汇代码转换为词汇名称
	 * @param fieldName 代码
	 * @param pojo 对象
	 * @return
	 * @throws Exception 
	 */
//	public static String convertFieldValueToText(String fieldName,BaseEntity pojo) throws Exception{
//		Field field = ActHelp.getFieldFromClass(fieldName, pojo);
//		Object obj = null;
//		try {
//			obj = field.get(pojo);
//		} catch (IllegalArgumentException e) {
////			e.printStackTrace();
//			logger.log("把摘要中代码转换为对应为名称出错", e);
//		} catch (IllegalAccessException e) {
////			e.printStackTrace();
//			logger.log("把摘要中代码转换为对应为名称出错", e);
//		}
//		//日期类型转换为yyyy-MM-dd格式
//		if(obj instanceof java.util.Date){
//			obj = YssFun.formatDate((java.util.Date)obj);
//		}
//		//如果字段有注解
//		else if(field.isAnnotationPresent(FieldAnnotation.class) && obj != null){
//			FieldAnnotation fieldAnnotation = null;
//			fieldAnnotation = field.getAnnotation(FieldAnnotation.class);
//			//查找代表交易市场的字段
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_MKT_CODE)){
//				//IMktDataService mktdataService = YssServiceFactory.getInstance().createService(IMktDataService.class);
//				//Mkt mkt = mktdataService.getDataByCode(obj.toString());
//				// 添加缓存byleeyu20130809
//				MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
//				Mkt mkt = mktCache.getCacheByKey(obj.toString());
//				if(null != mkt){
//				String mktName = mkt.getC_MKT_NAME();
//				if(null != mktName) obj = mktName;
//				}
//			}
//			//查找代表证券品种的字段
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_SEC_VAR_CODE)){
//				//ISecVarDataService secvardataService = YssServiceFactory.getInstance().createService(ISecVarDataService.class);
//				//SecVar secvar = secvardataService.getDataByCode(obj.toString());
//				// 添加缓存byleeyu20130809
//				//modify by chenyoucai 20161107 STORY #35715 【紧急】【招商基金】【保险】增值税需分明细和汇总计提   汇总时不显示证券品种
//				if(obj!=null){
//					SecVarCache secvarCache = CacheManager.getInstance().getCache(CacheGroup.SECVAR);
//					SecVar secvar = secvarCache.getCacheByKey(obj.toString());
//					
//					if(null != secvar){
//						String secVarName = secvar.getC_SEC_VAR_NAME();
//						if(null != secVarName) obj = secVarName;
//						}
//				}
//			}
//			//查找代表交易方式的字段
//			// Modified By xzl 20140428 BUG #91632 增加“理财兑付”交易方式
//			// 修改用于凭证界面代码转换
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_DT_CODE) || 
//					fieldName.equalsIgnoreCase("tradeDtCode")){
//				//IDtTdModeDataService dttdModeDataService = YssServiceFactory.getInstance().createService(IDtTdModeDataService.class);
//				//DttdMode dttdMode = dttdModeDataService.getDataByCode(obj.toString());
//				// 添加缓存byleeyu20130809
//				DtTdModeCache dttdmodeCache = CacheManager.getInstance().getCache(CacheGroup.DTTDMODE);
//				DttdMode dttdMode = dttdmodeCache.getCacheByKey(obj.toString());
//				
//				if(null != dttdMode){
//					String tradeModeName = dttdMode.getC_DT_NAME();
//					if(null != tradeModeName) obj = tradeModeName;
//				}
//			}
//			//查找代表现金账户的字段
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_CA_CODE) ||
//					0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_CA_CODE_IN)){
//				//ICADataService caDataService = YssServiceFactory.getInstance().createService(ICADataService.class);
//				//CashAcc cashacc = caDataService.getDataByCode(obj.toString());
//				// 添加缓存byleeyu20130809
//				CaCache caCache = CacheManager.getInstance().getCache(CacheGroup.CA);
//				CashAcc cashacc = caCache.getCacheByKey(obj.toString());
//				
//				if(null != cashacc){
//					String caName = cashacc.getC_CA_NAME();
//					if(null != caName) obj = obj.toString() + "_" + caName;
//				}
//			}
//			//查找代表投资分类的字段
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_DV_INVEST_CLS)){
//				//IVocDataService vocDataService = YssServiceFactory.getInstance().createService(IVocDataService.class);
//				//Vocabulary voca = vocDataService.getDataByCode(obj.toString());
//				// 添加缓存byleeyu20130809
//				VocCache vocCache = CacheManager.getInstance().getCache(CacheGroup.VOC);
//				Vocabulary voca = vocCache.getCacheByKey(obj.toString());
//				if(null != voca) obj = voca.getC_DV_NAME();
//			}
//			//CL 20121101 查找运营费用代码字段
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_FEE_CODE)){
//				//IIeDataService ieDataService = YssServiceFactory.getInstance().createService(IIeDataService.class);
//				//IFeeDataService ieDataService = YssServiceFactory.getInstance().createService(IFeeDataService.class);
//				//Ie ie = ieDataService.getDataByCode(obj.toString());
//				// 添加缓存byleeyu20130809
//				IeCache ieCache = CacheManager.getInstance().getCache(CacheGroup.IE);
//				Ie ie = ieCache.getCacheByKey(obj.toString());
//				
//				if(null != ie){
//					String feeName = ie.getC_FEE_NAME();
//					if(null != feeName) obj = feeName;
//				}
//			}
//			//Orlando 2013-06-13 查找证券代码转名称
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_SEC_CODE) ||
//					0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_SEC_CODE_TAG)){
//				//ISecBaseInfoDataService secDataService = YssServiceFactory.getInstance().createService(ISecBaseInfoDataService.class);
//				//HashMap<String,String> secMap = secDataService.getKeyConvertMap();
//				// 添加缓存byleeyu20130809
//				//modify by chenyoucai 20161107 STORY #35715 【紧急】【招商基金】【保险】增值税需分明细和汇总计提   汇总时不显示证券代码
//				if(obj!=null){
//					SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
//					HashMap<String,String> secMap = secCache.getKeyConvertMap();				
//					
//					if(null != secMap){
//						String secName = secMap.get(obj.toString());
//						if(null != secName) obj = obj.toString() + "_" +secName;
//					}
//				}
//			}
//			//fjl 2014-03-12 查找账户类型转名称
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_DV_ACC_TYPE) ||
//					0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_DV_ACC_TYPE_IN)){
//				VocCache vocCache = CacheManager.getInstance().getCache(CacheGroup.VOC);
//				Vocabulary voca = vocCache.getCacheByKey(obj.toString());
//				if(null != voca) obj = voca.getC_DV_NAME();
//			}
//			//xzl 2014-6-30添加分级组合代码转换
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_PORT_CLS_CODE)) {
//				PortClsCache portClsCache = CacheManager.getInstance().getCache(CacheGroup.PORTCLS);
//				HashMap<String, String> keyMap = portClsCache.getKeyConvertMap();
//				if (keyMap.containsKey(obj.toString())) {
//					obj = keyMap.get(obj.toString());
//				}else {
//					obj = obj.toString();
//				}
//			}
//			//By Jinghehe 2014-8-6添加 交易属性代码转换
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_DTA_CODE)) {
//				IDtaTdAttrDataService dtaTdDataService = YssServiceFactory.getInstance().createService(IDtaTdAttrDataService.class);
//				HashMap<String, String> keyMap = dtaTdDataService.getKeyConvertMap();
//				if (keyMap.containsKey(obj.toString())) {
//					obj = keyMap.get(obj.toString());
//				}else {
//					obj = obj.toString();
//				}
//			}
//			//查找代表发行方式的字段  STORY #43379 【紧急】新股中签凭证摘要区分网上中签或网下中签 20170626添加 
//			if(0 == fieldAnnotation.expressTableField().compareTo(ActTableField.C_DV_ISSUE_MODE)){
//				VocCache vocCache = CacheManager.getInstance().getCache(CacheGroup.VOC);
//				Vocabulary voc = vocCache.getCacheByKey(obj.toString());
//				if(null != voc){
//					String vocName = voc.getC_DV_NAME();
//					if(null != vocName) obj = vocName;
//				}
//			}
//		}	
//		//如果是字符串类型,从缓存中根据代码获取值,如果获取不到,则返回原值
//		else if(obj instanceof String){
//			if(fieldName.equalsIgnoreCase("portCode")){
//				// 添加缓存byleeyu20130809
//				PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
//				Port port = portCache.getCacheByKey(obj.toString());
//				// BUG #81556 资产估值，针对新增组合操作时，有可能会出现空指针错误  caowei 2013/10/21 
//				// 先去缓存中取，如果缓存中取不到再去数据库中取，再刷新缓存
//				if (null == port) {
//					IPortDataService portDataService = YssServiceFactory.getInstance()
//							.createService(IPortDataService.class);
//					port = portDataService.getDataByCode(obj.toString());
//					portCache.update(new CacheRefreshInfo());
//				}
//				obj = port.getC_PORT_NAME_ST();
//			}
//			else if(fieldName.equalsIgnoreCase("tradeMode")){
//				
//				// 添加缓存byleeyu20130809
//				DvaItemCache dvaitemCache = CacheManager.getInstance().getCache(CacheGroup.DVAITEM);
//				DvaItem dvaItem = dvaitemCache.getCacheByKey(obj.toString());
//				if (null == dvaItem) {
//					IDvaItemDataService dvaItemDataService = YssServiceFactory.getInstance().createService(IDvaItemDataService.class);
//					dvaItem = dvaItemDataService.getDataByCode(obj.toString());
//					dvaitemCache.update(new CacheRefreshInfo());
//				}
//				
//				if (null != dvaItem) {
//					obj = dvaItem.getC_DVA_ITEM_NAME();
//				}
//			}
//			else if(fieldName.equalsIgnoreCase("cury") || fieldName.endsWith("assistCury")){
//				// 添加缓存byleeyu20130809
//				DcCache dcCache = CacheManager.getInstance().getCache(CacheGroup.DC);
//				DcCury dcCury = dcCache.getCacheByKey(obj.toString());
//				if (null == dcCury) {
//					IDCDataService dcDataService = YssServiceFactory.getInstance().createService(IDCDataService.class);
//					dcCury = dcDataService.getDataByCode(obj.toString());
//					dcCache.update(new CacheRefreshInfo());
//				}
//				obj = dcCury.getC_DC_NAME();
//			}
//			//STORY #27530 （紧急）新债申请页面调整  新增描述字段
//			else if(fieldName.equalsIgnoreCase("desc")){
//				return obj != null ? obj.toString():null;
//			}
//			// BUG #134215 【广发证券】银行间编码修改需求变更刷脚本后的问题
//			// xiaozhilong
//			// 在这里添加oppositeSecCode类型进行证券缓存获取，并代码转换，
//			// 如果不加oppositeSecCode，在生成分录对代码转换时会走词汇缓存，
//			// 导致找不到，每一条分录都会查询刷新词汇缓存，不仅最终转换不了证券名称，还会严重影响证券转换核算效率
//			else if (fieldName.equalsIgnoreCase("oppositeSecCode")) {
//				SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
//				HashMap<String,String> secMap = secCache.getKeyConvertMap();				
//				
//				if(null != secMap){
//					String secName = secMap.get(obj.toString());
//					if(null != secName) obj = obj.toString() + "_" +secName;
//				}
//			}
//			//BUG #156249 【南方基金】转托管业务转出的券代码有问题    addby wsm 2017-4-6
//			else if(fieldName.equalsIgnoreCase("oppositeSecCode")){
//				SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
//				HashMap<String,String> secMap = secCache.getKeyConvertMap();				
//				
//				if(null != secMap){
//					String secName = secMap.get(obj.toString());
//					if(null != secName) obj = obj.toString() + "_" +secName;
//				}
//			}
////			end
//			else {
//				
//				// 添加缓存byleeyu20130809
//				VocCache vocCache = CacheManager.getInstance().getCache(CacheGroup.VOC);
//				Vocabulary voca = vocCache.getCacheByKey(obj.toString());
//				if (null == voca) {
//					IVocDataService vocDataService = YssServiceFactory.getInstance().createService(IVocDataService.class);
//					voca = vocDataService.getDataByCode(obj.toString());
//					vocCache.update(new CacheRefreshInfo());
//				}
//				if(null != voca) obj = voca.getC_DV_NAME();
//			}
//		}
//		return obj != null ? obj.toString():null;
//	}
	
	/**
	 * 从一个算数表达式字符串中找到参与计算的各个字段名
	 * @param expression	算数表达式字符串
	 * @return	字段名数组
	 */
	public static List<String> getFieldNamesFromExpression(String expression){
		//String regex = "\\b[a-zA-Z_0-9]+\\b";	//正则表达式:查找有英文字母组成的单词
		//String regex = "\\b[0-9]?[a-zA-Z_]+\\b";//正则表达式:查找由英文字母,下划线
		String regex = "[\\$]?\\b[0-9]*[a-zA-Z_]+[0-9]*\\b";//有英文字母或下划线或数字组成的单词(其中单词不能全由数字组成),而且单词前出现$符号0次或者一次
		Pattern pattern = null;
		Matcher matcher = null;
		List<String> fieldNames = null;
		if(null != expression){
			//使用正则表达式查找字符串中参与计算的所有字段名
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(expression);
			while(matcher.find()){
				if( null == fieldNames){
					fieldNames = new ArrayList<String>();
				}
				//如果单词前出现$符号,跳过此单词
				if(!matcher.group().matches("\\$.*")){
					fieldNames.add(matcher.group());
				}
			}
		}
		return fieldNames;
	}
	
	/**
	 * 从一个算数表达式字符串中找到参与计算的各个字段变量
	 * @param expression	算数表达式字符串
	 * @return	变量数组
	 */
	public static  List<Variable> getVariablesFromExpression(String expression){
		return getVariablesFromExpression(expression,false);
	}
	
	/**
	 * 从一个算数表达式字符串中找到参与计算的各个字段变量
	 * @param expression	算数表达式字符串
	 * @param cashType     STORY35882【招商基金】【保险3.0升级】实收资本数量要求能保留8位小数（防止频繁变更，4.5直接改为16位小数）
	 *       凭证模板中的数量要支持BigDecimal，故加一个计算的数据的类型的判断，如果是要计算数量，全部给BigDecimal.其他保持不变
	 * @return	变量数组
	 */
	public static  List<Variable> getVariablesFromExpression(String expression,boolean isCalAmount){
		String regex = "[\\$]?\\b[0-9]*[a-zA-Z_]+[0-9]*\\b";//有英文字母或下划线或数字组成的单词(其中单词不能全由数字组成),而且单词前出现$符号0次或者一次
		Pattern pattern = null;
		Matcher matcher = null;
		List<Variable> variables = null;
		String dataType = null;//标识数据类型
		if(null != expression){
			//使用正则表达式查找字符串中参与计算的所有字段名
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(expression);
			while(matcher.find()){
				if( null == variables){
					variables = new ArrayList<Variable>();
				}
				//如果单词前出现$符号
				if(matcher.group().matches("\\$.*")){
					//如果使用函数$DATE,那么标记下一个单词类型应该为日期类型
					if(matcher.group().equalsIgnoreCase("$DATE")){
						dataType = "date";
					}
					//如果使用函数$STRING,那么标记下一个单词类型应该为字符串类型
					if(matcher.group().equalsIgnoreCase("$STRING")){
						dataType = "string";
					}
					//如果使用函数$BOOLEAN,那么标记下一个单词类型应该为布尔类型
					if(matcher.group().equalsIgnoreCase("$BOOLEAN")){
						dataType = "boolean";
					}
					//中断此次循环
					continue;
				}
				if(null != dataType && dataType.equalsIgnoreCase("date")){
					//把标记重新清掉
					dataType = null;
					variables.add(new Variable(matcher.group(),BaseDataMeta.DataType.DATATYPE_DATE,null));
				}
				else if(null != dataType && dataType.equalsIgnoreCase("string")){
					//把标记重新清掉
					dataType = null;
					variables.add(new Variable(matcher.group(),BaseDataMeta.DataType.DATATYPE_STRING,null));
				}
				else if(null != dataType && dataType.equalsIgnoreCase("boolean")){
					//把标记重新清掉
					dataType = null;
					variables.add(new Variable(matcher.group(),BaseDataMeta.DataType.DATATYPE_BOOLEAN,null));
				}
				else if(isCalAmount){
					//如果是计算凭证模板中的数量，默认参与计算的各个因子都是BigDecimal
					variables.add(new Variable(matcher.group(),BaseDataMeta.DataType.DATATYPE_BigDecimal,null));
				}
				//默认变量类型为double
				else{
					variables.add(new Variable(matcher.group(),BaseDataMeta.DataType.DATATYPE_DOUBLE,null));
				}
			}
		}
		return variables;
	}
	
	/**
	 * 根据编译好的条件计算一个表达式的值,使用对象中金额字段的原币金额进行计算
	 * @param pojo
	 * @param pe
	 * @param fieldNames
	 * @return
	 * @throws Exception
	 */
//	public static double calcArithExpressionBaseOriginCury(BaseEntity pojo,PreparedExpression pe,List<String> fieldNames) throws Exception{
//		double result = 0;
//		if(null != pojo && null != pe && null != fieldNames){
//			for(String fieldName : fieldNames){
//				Object fieldValue = ActHelp.getFieldValueFromEntity(fieldName, pojo);
//				if(fieldValue instanceof ArithResult){
//					ArithResult arithResult = (ArithResult) fieldValue;
//					fieldValue = arithResult.getOrigMoney();
//				}					
//				pe.setArgument(fieldName, fieldValue);
//			}
//			result = ((Double)pe.execute()).doubleValue();
//		}
//		return result;
//	}
	
	/**
	 * STORY35882【招商基金】【保险3.0升级】实收资本数量要求能保留8位小数（防止频繁变更，4.5直接改为16位小数）
	 * add by liuyanni 20161118  数量要支持BigDecimal类型。
	 * 根据编译好的条件计算一个表达式的值
	 * @param pojo
	 * @param pe
	 * @param fieldNames
	 * @return
	 * @throws Exception
	 */
//	public static BigDecimal calcArithExpressionBaseAmount(BaseEntity pojo,PreparedExpression pe,List<String> fieldNames) throws Exception{
//		BigDecimal result = BigDecimal.ZERO;
//		if(null != pojo && null != pe && null != fieldNames){
//			for(String fieldName : fieldNames){
//				Object fieldValue = ActHelp.getFieldValueFromEntity(fieldName, pojo);
//				//add by xsm 产品销售数据核算，报错
//				if(fieldValue==null){
//					return result;
//				}
//				if(fieldValue instanceof ArithResult){
//					ArithResult arithResult = (ArithResult) fieldValue;
//					fieldValue = arithResult.getOrigMoney();
//				}					
//				pe.setArgument(fieldName, fieldValue);
//			}
//			result = new BigDecimal(pe.execute().toString());
//		}
//		return result;
//	}
	
	/**
	 * 根据编译好的条件计算一个表达式的值，使用对象中金额字段的本位币金额进行计算
	 * @param pojo
	 * @param pe
	 * @param fieldNames
	 * @return
	 * @throws Exception
	 */
//	public static double calcArithExpresssionBasePortCury(BaseEntity pojo,PreparedExpression pe,List<String> fieldNames) throws Exception{
//		double result = 0;
//		if(null != pojo && null != pe && null != fieldNames){
//			for(String fieldName : fieldNames){
//				Object fieldValue = ActHelp.getFieldValueFromEntity(fieldName, pojo);
//				if(fieldValue instanceof ArithResult){
//					ArithResult arithResult = (ArithResult) fieldValue;
//					fieldValue = arithResult.getPortMoney();
//				}					
//				pe.setArgument(fieldName, fieldValue);
//			}
//			result = ((Double)pe.execute()).doubleValue();
//		}
//		return result;
//	}
	
	/**
	 * 根据编译好的条件计算一个逻辑表达式的值
	 * @param pojo
	 * @param pe
	 * @param variables
	 * @return
	 * @throws Exception
	 */
//	public static String executeLogicExpression(BaseEntity pojo,PreparedExpression pe,List<Variable> variables) throws Exception{
//		String result = null;
//		if(null == pojo || null == pe || null == variables)
//			return null;
//		//为各个变量赋值
//		for(Variable var : variables){
//			Object fieldValue = ActHelp.getFieldValueFromEntity(var.getVariableName(), pojo);
//			pe.setArgument(var.getVariableName(), fieldValue);
//		}
//		result = pe.execute().toString();
//		return result;
//	}
	
	
	
}
