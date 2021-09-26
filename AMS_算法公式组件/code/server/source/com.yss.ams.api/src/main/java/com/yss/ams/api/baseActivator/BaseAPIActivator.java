package com.yss.ams.api.baseActivator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.yss.ams.visaval.support.api.pojo.AlgoAPI;
import com.yss.ams.visaval.support.api.pojo.FunAPI;
import com.yss.ams.visaval.support.api.pojo.ParamAPI;
import com.yss.ams.visaval.support.api.pojo.ParentFunAPI;
import com.yss.ams.visaval.support.api.pojo.ReturnAPI;
import com.yss.ams.visaval.support.context.AvalAPIContext;
import com.yss.ams.visaval.support.util.cnspojo.CNSU;
import com.yss.ams.visaval.support.util.cnspojo.CNU;
import com.yss.ams.visaval.support.util.pojo.APIFunsU;
import com.yss.ams.visaval.support.util.pojo.AlgoAPIU;
import com.yss.ams.visaval.support.util.pojo.ChildAPIU;
import com.yss.ams.visaval.support.util.pojo.ChildFunU;
import com.yss.ams.visaval.support.util.pojo.KeyWordU;
import com.yss.ams.visaval.support.util.pojo.KeyWordValU;
import com.yss.ams.visaval.support.util.pojo.ParamU;
import com.yss.ams.visaval.support.util.pojo.ParamsU;
import com.yss.ams.visaval.support.util.pojo.ParentAPIU;
import com.yss.ams.visaval.support.util.pojo.ParentFunU;
import com.yss.ams.visaval.support.util.pojo.ReturnValU;
import com.yss.ams.visaval.support.util.sel_parampojo.Selcombox;
import com.yss.ams.visaval.support.util.sel_parampojo.Selcomboxs;
import com.yss.ams.visaval.support.util.sqlpojo.ParamSqlU;
import com.yss.ams.visaval.support.util.sqlpojo.ParamsSqlU;
import com.yss.framework.api.bundle.BaseActivator;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;

/**
 * Author : ChenLong Date : 2017-08-01 Status : Add Comment: API插件启动基类
 */
public abstract class BaseAPIActivator extends BaseActivator{

	public Logger logger = LogManager.getLogger(getClass());

	@Override
	public void start(BundleContext context) throws Exception {
//		registerService(context);
		super.start(context);
		initAvalAPI();
		loadAPIInfo(context);
		initKeyWordParam(context);
		initKeyParamSql(context);
		initCNSData(context);
		initSelParamData(context);
		initYYFY_JX_Map();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		removeAPIInfo(context);
		removeKeyWordInfo(context);
		removeKeyParamSqlInfo(context);
		removeCNSData(context);
		removeSelParamData(context);
	}

	/**
	 * Python初始化算法API
	 */
	public abstract void initAvalAPI();

	/**
	 * 加载算法API缓存信息
	 */
	private void loadAPIInfo(BundleContext context) {
		AlgoAPIU algoAPIU = parseAPIInfo(context);
		addParentFunAPI2Map(algoAPIU);
		addFunAPI2Map(algoAPIU);
	}

	private void addFunAPI2Map(AlgoAPIU algoAPIU) {
		APIFunsU apiFunsU = algoAPIU.getApiFuns();
		ChildAPIU childAPIU = apiFunsU.getChildAPI();
		List<ChildFunU> list = childAPIU.getChildFunList();

		for (ChildFunU childFunU : list) {
			FunAPI funAPI = new FunAPI();
			funAPI.setCode(childFunU.getCode());
			funAPI.setDesc(childFunU.getDesc());
			funAPI.setHasConnection(childFunU.isHasConnection());
			funAPI.setParent(childFunU.getParent());
			funAPI.setText(childFunU.getText());
			funAPI.setValue(childFunU.getValue());

			ReturnAPI returnAPI = new ReturnAPI();
			ReturnValU returnValU = childFunU.getReturnVal();
			returnAPI.setReturnType(returnValU.getType());
			returnAPI.setDesc(returnValU.getName());

			List<ParamAPI> listParamAPI = new ArrayList<ParamAPI>();

			List<ParamU> listParam = childFunU.getParamAPI().getParamList();
			for (ParamU paramU : listParam) {
				ParamAPI paramAPI = new ParamAPI();
				paramAPI.setCode(paramU.getCode());
				paramAPI.setHasdetails(paramU.isHasDetails());
				paramAPI.setIsdefault(paramU.isDefault());
				paramAPI.setKeyWord(paramU.getKeyWord());
				paramAPI.setName(paramU.getName());
				paramAPI.setParamValue(paramU.getValue());
				paramAPI.setSource(paramU.getSource());
				paramAPI.setFromFile(paramU.isFromFile());

				if (null != paramAPI.getKeyWord()
						&& paramAPI.getKeyWord() != "") {
					AvalAPIContext.getInstance().addParamAPIMap(
							paramAPI.getKeyWord(), paramAPI);
				}

				listParamAPI.add(paramAPI);
			}

			funAPI.setReturnAPI(returnAPI);
			funAPI.setParamAPIs(listParamAPI);

			AvalAPIContext.getInstance().addFunAPIMap(funAPI.getCode(), funAPI);
		}
	}
	
	/**
	 * 初始化运营费用和账户计息函数数据
	 */
	private void initYYFY_JX_Map(){
		Map<String, FunAPI> funAPIMap = AvalAPIContext.getInstance().getFunAPIMap();
		List<AlgoAPI> list = new ArrayList<AlgoAPI>();
//		List<AlgoAPI> ZHJX_List = new ArrayList<AlgoAPI>();
		Map<String, ParentFunAPI> parentFunMap = AvalAPIContext.getInstance().getFunParentAPIMap();
		Iterator<String> ite = funAPIMap.keySet().iterator();
		while (ite.hasNext()) {
			String key = (String) ite.next();
			FunAPI funAPI = funAPIMap.get(key);
			if (!"GZ_INVEFEE".equals(funAPI.getParent()) && !"GZ_ACCCASH".equals(funAPI.getParent())){
				/**
				 * 添加函数
				 */
				AlgoAPI algoAPI = new AlgoAPI();
				algoAPI.setAlgoCode(funAPI.getCode());
				algoAPI.setAlgoDesc(funAPI.getDesc());
				algoAPI.setAlgoParent(funAPI.getParent());
				algoAPI.setAlgoText(funAPI.getText());
				algoAPI.setAlgoValue(funAPI.getValue());
				list.add(algoAPI);
				/**
				 * 添加函数父节点
				 */
				
				/*Iterator<String> iter = parentFunMap.keySet().iterator();
				while (iter.hasNext()) {
					ParentFunAPI parentFunAPI = parentFunMap.get(iter.next());
					AlgoAPI a = new AlgoAPI();
					a.setAlgoCode(parentFunAPI.getCode());
					a.setAlgoParent(parentFunAPI.getParent());
					a.setAlgoText(parentFunAPI.getText());
					
					if(!"GZ_INVEFEE".equals(a.getAlgoCode())){
						YYFY_List.add(a);
					}
				}
				AvalAPIContext.getInstance().addEXCEPT_YYFY_JX_Map("EXCEPT_JX_DATA", YYFY_List);*/
			}
			
			
			
			/*else if(!"GZ_ACCCASH".equals(funAPI.getParent())){
				*//**
				 * 添加函数
				 *//*
				AlgoAPI algoAPI = new AlgoAPI();
				algoAPI.setAlgoCode(funAPI.getCode());
				algoAPI.setAlgoDesc(funAPI.getDesc());
				algoAPI.setAlgoParent(funAPI.getParent());
				algoAPI.setAlgoText(funAPI.getText());
				algoAPI.setAlgoValue(funAPI.getValue());
				ZHJX_List.add(algoAPI);
				*//**
				 * 添加函数父节点
				 *//*
				Iterator<String> iter = parentFunMap.keySet().iterator();
				while (iter.hasNext()) {
					ParentFunAPI parentFunAPI = parentFunMap.get(iter.next());
					AlgoAPI a = new AlgoAPI();
					a.setAlgoCode(parentFunAPI.getCode());
					a.setAlgoParent(parentFunAPI.getParent());
					a.setAlgoText(parentFunAPI.getText());
					
					if(!"GZ_ACCCASH".equals(a.getAlgoCode())){
						YYFY_List.add(a);
					}
				}
				
				AvalAPIContext.getInstance().addEXCEPT_YYFY_JX_Map("EXCEPT_YYFY_DATA", ZHJX_List);
			}*/
		}
		
		Iterator<String> iter = parentFunMap.keySet().iterator();
		while (iter.hasNext()) {
			ParentFunAPI parentFunAPI = parentFunMap.get(iter.next());
			AlgoAPI a = new AlgoAPI();
			a.setAlgoCode(parentFunAPI.getCode());
			a.setAlgoParent(parentFunAPI.getParent());
			a.setAlgoText(parentFunAPI.getText());
			
			if(!"GZ_INVEFEE".equals(a.getAlgoCode()) && !"GZ_ACCCASH".equals(a.getAlgoCode())){
				list.add(a);
			}
		}
		
		AvalAPIContext.getInstance().addEXCEPT_YYFY_JX_Map("EXCEPT_YYFY_ZHJX_DATA", list);
	}

	/**
	 * 移除API缓存信息
	 */
	private void removeAPIInfo(BundleContext context) {
		AlgoAPIU algoAPIU = parseAPIInfo(context);
		APIFunsU apiFunsU = algoAPIU.getApiFuns();
		ParentAPIU parentAPIU = apiFunsU.getParentAPI();
		List<ParentFunU> list = parentAPIU.getParentFunList();
		for (ParentFunU parentFunU : list) {
			AvalAPIContext.getInstance().removeFunParentAPIMap(
					parentFunU.getCode());
			/**
			 * 移除非运营费用和账户计息数据
			 */
			Map<String, List<AlgoAPI>> YYFY_JX_Map = AvalAPIContext.getInstance().getEXCEPT_YYFY_JX_Map();
			if (YYFY_JX_Map.containsKey(parentFunU.getCode())){
				YYFY_JX_Map.remove(parentFunU.getCode());
			}
		}

		ChildAPIU childAPIU = apiFunsU.getChildAPI();
		List<ChildFunU> listFun = childAPIU.getChildFunList();
		for (ChildFunU childFunU : listFun) {
			AvalAPIContext.getInstance().removeFunAPIMap(childFunU.getCode());
		}

	}

	private void addParentFunAPI2Map(AlgoAPIU algoAPIU) {
		APIFunsU apiFunsU = algoAPIU.getApiFuns();
		ParentAPIU parentAPIU = apiFunsU.getParentAPI();

		List<ParentFunU> list = parentAPIU.getParentFunList();
		for (ParentFunU parentFunU : list) {
			ParentFunAPI parentFunAPI = new ParentFunAPI();
			parentFunAPI.setCode(parentFunU.getCode());
			parentFunAPI.setParent(parentFunU.getParent());
			parentFunAPI.setText(parentFunU.getText());
			AvalAPIContext.getInstance().addFunParentAPIMap(
					parentFunAPI.getCode(), parentFunAPI);
		}
	}

	/**
	 * 序列化AlgoApi文件
	 * 
	 * @param context
	 * @return
	 */
	private AlgoAPIU parseAPIInfo(BundleContext context) {
		AlgoAPIU algoAPI = null;

		InputStream is = null;
		try {
			Bundle bundle = context.getBundle();
			URL url = bundle.getEntry("META-INF/config/algo/AlgoApi.xml");
			if (url != null) {
				URL runtimeURL = null;
				try {
					runtimeURL = FileLocator.resolve(url);
				} catch (IOException ex) {
					logger.log("加载算法API配置文件出错！", ex);
				}

				JAXBContext jaxbContext = JAXBContext
						.newInstance(AlgoAPIU.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				SAXParserFactory sax = SAXParserFactory.newInstance();
				sax.setNamespaceAware(false);
				XMLReader xmlReader = sax.newSAXParser().getXMLReader();
				is = runtimeURL.openStream();
				Source source = new SAXSource(xmlReader, new InputSource(is));
				algoAPI = (AlgoAPIU) unmarshaller.unmarshal(source);
			}
		} catch (Exception ex) {
			logger.log("加载算法API配置文件出错！", ex);
		}

		return algoAPI;
	}

//	/**
//	 * 注册service服务
//	 * 
//	 * @param context
//	 * @throws Exception
//	 */
//	private void registerService1(BundleContext context) throws Exception {
//		Bundle bundle = context.getBundle();
//		URL url = bundle
//				.getEntry("META-INF/config/service/service/ServiceMap.xml");
//		if (url != null) {
//			URL runtimeURL = null;
//			try {
//				runtimeURL = FileLocator.resolve(url);
//			} catch (IOException e) {
//				logger.log("加载服务映射文件出错！", e);
//			}
//
//			if (runtimeURL == null) {
//				logger.log("未找到初始化ServiceMap文件");
//			} else {
//				SAXReader saxReader = new SAXReader();
//				Document doc = saxReader.read(runtimeURL);
//				Element root = doc.getRootElement();
//				@SuppressWarnings("unchecked")
//				List<Element> intElementList = root.elements("class");
//				for (Element intElements : intElementList) {
//					String interfaceClassName = intElements
//							.attributeValue("interface");
//					String className = intElements.attributeValue("class");
//					Object clazzInsObj = null;
//					try {
//						clazzInsObj = this.getClass().getClassLoader()
//								.loadClass(className).newInstance();
//						context.registerService(interfaceClassName,
//								clazzInsObj, null);
//					} catch (Exception ex) {
//						logger.log("无法实例化服务[" + className + "]", ex);
//					}
//				}
//			}
//		}
//	}

	/**
	 * 初始化API函数方法关键字参数的数据来源
	 * 
	 * @param context
	 */
	private void initKeyWordParam(BundleContext context) {
		ParamsU param = parseParams(context);
		List<KeyWordU> list = param.getKeywords();
		for (KeyWordU keyword : list) {
			Map<String, String> m = new HashMap<String, String>();
			for (KeyWordValU val : keyword.getList()) {
				m.put(val.getCode(), val.getName());
				AvalAPIContext.getInstance().addCnToEnMap(keyword.getCode(),
						val.getCode(), val.getName());
			}
			AvalAPIContext.getInstance().addKeyWordParamMap(keyword.getCode(),
					m);
		}
	}

	/**
	 * 序列化keyparam.xml为对象
	 * 
	 * @param context
	 * @return
	 */
	private ParamsU parseParams(BundleContext context) {
		InputStream is = null;
		ParamsU params = null;
		Bundle bundle = context.getBundle();
		URL url = bundle
				.getEntry("META-INF/config/translate/keyword/keyparam.xml");
		if (url != null) {
			URL runtimeURL = null;
			try {
				runtimeURL = FileLocator.resolve(url);
			} catch (IOException e) {
				logger.log("加载函数参数关键字数据来源失败！", e);
			}

			if (runtimeURL == null) {
				logger.log("未找到初始化keyparam.xml文件");
			} else {
				try {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(ParamsU.class);
					Unmarshaller unmarshaller = jaxbContext
							.createUnmarshaller();
					SAXParserFactory sax = SAXParserFactory.newInstance();
					sax.setNamespaceAware(false);
					XMLReader xmlReader = sax.newSAXParser().getXMLReader();
					is = runtimeURL.openStream();
					Source source = new SAXSource(xmlReader,
							new InputSource(is));
					params = (ParamsU) unmarshaller.unmarshal(source);
				} catch (Exception e) {
					logger.log("加载算法keyparam配置文件出错！", e);
				}
			}
		}
		return params;
	}

	/**
	 * 移除API函数方法关键字参数来源
	 * 
	 * @param context
	 */
	private void removeKeyWordInfo(BundleContext context) {
		ParamsU params = parseParams(context);
		List<KeyWordU> keywords = params.getKeywords();
		for (KeyWordU keyWordU : keywords) {
			AvalAPIContext.getInstance().removeKeyWordParamMap(
					keyWordU.getCode());
			AvalAPIContext.getInstance().removeCnToEnMap(keyWordU.getCode());
		}
	}

	private void initKeyParamSql(BundleContext context) {
		ParamsSqlU paramsSqlU = parseParamSql(context);

		List<ParamSqlU> list = paramsSqlU.getParamSqlList();
		for (ParamSqlU paramSqlU : list) {
			AvalAPIContext.getInstance().addParamsFromSqlMap(
					paramSqlU.getCode(), paramSqlU.getSql());
			/*
			 * IAlgoDataService service =
			 * YssServiceFactory.getInstance().createService
			 * (IAlgoDataService.class); List<ParamFromSql> listParamFromSqls =
			 * service.getParamFromSql(paramSqlU.getSql());
			 * AvalAPIContext.getInstance
			 * ().addParamFromSqlMap(paramSqlU.getCode(), listParamFromSqls);
			 */
		}
	}

	/**
	 * 移除API函数方法关键字参数来源
	 * 
	 * @param context
	 */
	private void removeKeyParamSqlInfo(BundleContext context) {
		ParamsSqlU params = parseParamSql(context);
		List<ParamSqlU> list = params.getParamSqlList();
		for (ParamSqlU paramSqlU : list) {
			AvalAPIContext.getInstance().removeParamsFromSqlMap(
					paramSqlU.getCode());
		}
	}

	private ParamsSqlU parseParamSql(BundleContext context) {

		InputStream is = null;
		ParamsSqlU params = null;
		Bundle bundle = context.getBundle();
		URL url = bundle
				.getEntry("META-INF/config/translate/keyword/keyparam_sql.xml");
		if (url != null) {
			URL runtimeURL = null;
			try {
				runtimeURL = FileLocator.resolve(url);
			} catch (IOException e) {
				logger.log("加载函数参数关键字数据来源失败！", e);
			}

			if (runtimeURL == null) {
				logger.log("未找到初始化keyparam_sql.xml文件");
			} else {
				try {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(ParamsSqlU.class);
					Unmarshaller unmarshaller = jaxbContext
							.createUnmarshaller();
					SAXParserFactory sax = SAXParserFactory.newInstance();
					sax.setNamespaceAware(false);
					XMLReader xmlReader = sax.newSAXParser().getXMLReader();
					is = runtimeURL.openStream();
					Source source = new SAXSource(xmlReader,
							new InputSource(is));
					params = (ParamsSqlU) unmarshaller.unmarshal(source);
				} catch (Exception e) {
					logger.log("加载算法keyparam_sql配置文件出错！", e);
				}
			}
		}
		return params;
	}

	private void removeCNSData(BundleContext context) {
		CNSU cnsu = parseCNS(context);
		if (null == cnsu || null == cnsu.getCnus()) {
			return;
		}

		for (CNU cnu : cnsu.getCnus()) {
			AvalAPIContext.getInstance().removeCNSMap(cnu.getCode());
			AvalAPIContext.getInstance().removeCnToEnMap(cnu.getCode());
		}
	}

	private void initCNSData(BundleContext context) {
		CNSU cnsu = parseCNS(context);
		if (null == cnsu || null == cnsu.getCnus()) {
			return;
		}

		for (CNU cnu : cnsu.getCnus()) {
			AvalAPIContext.getInstance().addCNSMap(cnu.getCode(), cnu.getSql());
		}
	}

	private CNSU parseCNS(BundleContext context) {

		InputStream is = null;
		CNSU params = null;
		Bundle bundle = context.getBundle();
		URL url = bundle
				.getEntry("META-INF/config/translate/cnsource/cnsetting.xml");
		if (url != null) {
			URL runtimeURL = null;
			try {
				runtimeURL = FileLocator.resolve(url);
			} catch (IOException e) {
				logger.log("加载函数参数关键字数据来源失败！", e);
			}

			if (runtimeURL == null) {
				logger.log("未找到初始化cnsetting.xml文件");
			} else {
				try {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(CNSU.class);
					Unmarshaller unmarshaller = jaxbContext
							.createUnmarshaller();
					SAXParserFactory sax = SAXParserFactory.newInstance();
					sax.setNamespaceAware(false);
					XMLReader xmlReader = sax.newSAXParser().getXMLReader();
					is = runtimeURL.openStream();
					Source source = new SAXSource(xmlReader,
							new InputSource(is));
					params = (CNSU) unmarshaller.unmarshal(source);
				} catch (Exception e) {
					logger.log("加载算法cnsetting配置文件出错！", e);
				}
			}
		}
		return params;

	}
	
	private void removeSelParamData(BundleContext context) {
		Selcomboxs selcomboxs = parseSel_Param(context);
		if (null == selcomboxs || null == selcomboxs.getSelcomboxs()) {
			return;
		}

		for (Selcombox sel : selcomboxs.getSelcomboxs()) {
			AvalAPIContext.getInstance().removeSelParamMap(sel.getCode());
		}
	}

	private void initSelParamData(BundleContext context) {
		Selcomboxs selcomboxs = parseSel_Param(context);
		if (selcomboxs != null) {
			List<Selcombox> seList = selcomboxs.getSelcomboxs();
			if (seList.size() > 0) {
				for (int i = 0; i < seList.size(); i++) {
					Selcombox selcombox = seList.get(i);
					String C_DS_ATTR = "fun=" + selcombox.getFun() + "\n"
							+ "MethodName=" + selcombox.getMethodName() + "\n"
							+ "MethodParamValues="
							+ selcombox.getMethodParamValues();
					String C_CTL_ATTR = "DisplayName="
							+ selcombox.getDisplayName() + "\n"
							+ "DisplayValue=" + selcombox.getDisplayValue()
							+ "\n" + "Parameter=" + selcombox.getParameter()
							+ "\n" + "YssCaption=" + selcombox.getYssCaption()
							+ "\n" + "YssIsMust=" + selcombox.getYssIsMust()
							+ "\n" + "YssLength=" + selcombox.getYssLength()
							+ "\n" + "Value=" + selcombox.getValue();
					Map<String,String> map = new HashMap<String, String>();
					map.put("C_DS_ATTR", C_DS_ATTR);
					map.put("C_CTL_ATTR", C_CTL_ATTR);
					
					AvalAPIContext.getInstance().addSelParamMap(selcombox.getCode(), map);
				}
			}
		}
	}

	private Selcomboxs parseSel_Param(BundleContext context) {

		InputStream is = null;
		Selcomboxs params = null;
		Bundle bundle = context.getBundle();
		URL url = bundle
				.getEntry("META-INF/config/translate/sel_param/sel_param.xml");
		if (url != null) {
			URL runtimeURL = null;
			try {
				runtimeURL = FileLocator.resolve(url);
			} catch (IOException e) {
				logger.log("加载函数参数关键字数据来源失败！", e);
			}

			if (runtimeURL == null) {
				logger.log("未找到初始化sel_param.xml文件");
			} else {
				try {
					JAXBContext jaxbContext = JAXBContext
							.newInstance(Selcomboxs.class);
					Unmarshaller unmarshaller = jaxbContext
							.createUnmarshaller();
					SAXParserFactory sax = SAXParserFactory.newInstance();
					sax.setNamespaceAware(false);
					XMLReader xmlReader = sax.newSAXParser().getXMLReader();
					is = runtimeURL.openStream();
					Source source = new SAXSource(xmlReader,
							new InputSource(is));
					params = (Selcomboxs) unmarshaller.unmarshal(source);
				} catch (Exception e) {
					logger.log("加载算法sel_param配置文件出错！", e);
				}
			}
		}
		return params;

	}
}
