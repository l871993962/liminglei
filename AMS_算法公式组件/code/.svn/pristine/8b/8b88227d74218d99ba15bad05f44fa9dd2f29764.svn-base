/*********************************************************************
Title：公式算法工厂
Description：用于初始化python算法公式
Copyright：Copyright @ 2001-2012 Ysstech,All Rights Reserved
日期  作者  版本  简单描述
2016-03-07 orlando 0.1 创建此类用于替换AlgoInterpreter类，原来的处理方式会造成PremSize内存溢出
2016-03-22 orlando 0.2 修复不能刷新算法缓存BUG，刷新缓存时重新初始化
2016-03-29 orlando 0.3 增加返回Object对象算法调用方法
2016-04-01 liuxiang 0.4 增加交易数据的算法API
2016-05-30 yewenke 0.5 修复返回值为整型的返回值错误
2016-11-01 orlando 0.6 修复带有‘上’字的算法不能编译错误，升级jython.jar到2.5.3版本，增加测试算法的方法
**********************************************************************/
package com.yss.ams.visaval.jython;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

import com.yss.ams.visaval.cache.AlgoCache;
import com.yss.ams.visaval.support.pojo.AdvAlgo;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.YssD;
import com.yss.framework.api.util.YssFun;

/**Jyphon 代理工厂类，用于注册python的class类型，并构建java实例对象
 * 注意：工厂类实例必须是唯一的，才能保证不会重复向python注册class，但是单例模式下就限制此对象只能在YSSUCO这个BUNDLE内部使用；
 * 如果要跨组件调用，就需要将工厂类封装成OSGI服务
 * @Date 2016-03-04
 * @author Orlando
 *
 */
public class AvalJythonFactory {
	
	/**
	 * python转换器，用于将JAVA对象转换为PYTHON对象，或者将python对象转换为JAVA对象
	 */
	private PythonInterpreter interpreter = null;
	
	/**
	 * 缓存python注册的算法类型，避免多次向python注册，造成JVM永久代内存溢出
	 */
	private ConcurrentHashMap<String,PyObject> clazzes = null;
	
	private static AvalJythonFactory factory = new AvalJythonFactory();
	
	/**
	 * 算法接口类型
	 */
	private Class<?> inface = null;
	
	/**
	 * 构造器，初始化属性
	 * @throws ClassNotFoundException
	 */
	private AvalJythonFactory(){
		//Orlando 20161110 增加字符编码设置
		Properties props = new Properties();
		props.put("python.console.encoding", "utf8");
		Properties preprops = System.getProperties();
		PythonInterpreter.initialize(preprops, props, new String[0]);
		this.interpreter = new PythonInterpreter();
		//Orlando 20161110 增加字符编码库到系统查找路径中
		PySystemState sys = Py.getSystemState();
		FileStorePathUtil fileUtil = new FileStorePathUtil(_LIBRARY);
		String filePath = fileUtil.getFilePath();
        sys.path.add(filePath);
		this.clazzes = new ConcurrentHashMap<String,PyObject>();
		inface = IAlgo.class;
		this.initJyphonENV();
	}
	
	/** 创建工厂实例并返回该工厂
	 * 工厂类实例必须是唯一的，才能保证不会重复向python注册class，但是单例模式下就限制此对象只能在YSSUCO这个BUNDLE内部使用；
	 * 如果要跨组件调用，就需要将工厂类封装成OSGI服务
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static synchronized AvalJythonFactory getInstance(){
		if(factory == null){
			factory = new AvalJythonFactory();
		}
		return factory;
	}
	
	/**
	 * 初始化python上下文环境，注册可用工具对象
	 * 此对象是全局共享的，无关组合，无关连接，与事务无关的
	 */
	public void initJyphonENV(){
		interpreter.exec(this.definePythonFunction());
		YssFun fun = new YssFun();
		interpreter.set("funUtil", fun);
		
		YssD yssD = new YssD();
		interpreter.set("mathUtil", yssD);
		
		DateUtil dateUtil = new DateUtil();
		interpreter.set("dateUtil", dateUtil);
		
		// add by yh 2014.04.14 增加算法公式日志对象
		interpreter.set("logger", logger);
	}
	
	public void initJyphonENV1(String key,Object o){
		interpreter.set(key, o);
		
	}
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	public Object executeTest(){
		String algo = AvalJythonFactory.getInstance().tempMap().get("Test");
		AvalJythonFactory.getInstance().updateAlgo("Test", algo);
		AvalJythonFactory.getInstance().registClass("Test", algo);
		IAlgo ialgo = null;
		try {
			ialgo = factory.createAlgoRule("Test");
		} catch (Exception e) {
//			e.printStackTrace();
		}
		HashMap<String, Object> algoPara = new HashMap<String, Object>();
		return ialgo.algoRule(algoPara, null, "", (Date)new Date());
	}
	
	/**将业务方法逻辑注册到python类定义中，并构建类对象放到缓存中
	 * @param code
	 * @param algoContent
	 */
	private void registClass(String code,String algoContent){
		if(!this.clazzes.containsKey(code)){
			this.definePythonClass(code,algoContent);
			PyObject pyObject = interpreter.get("_"+code);
			this.clazzes.putIfAbsent(code, pyObject);
		}
	}
	
	/**从缓存中获取缓存的PYTHON类型构建实例并转化为JAVA算法IAlgo的实现
	 * @param code
	 * @return
	 * @throws Exception
	 */
	private IAlgo createAlgoRule(String code) throws Exception{
		IAlgo algoRule = null;
		Object javaObject = null;
		PyObject pyObject = null;
		if(this.clazzes.containsKey(code)){
			pyObject = this.clazzes.get(code);
		}
		else{
			throw new Exception("未定义算法【"+code+"]类的python实现");
		}
		pyObject = pyObject.__call__();
		javaObject = pyObject.__tojava__(inface);
		algoRule = (IAlgo) javaObject;
		
		return algoRule;
	}
	
	/**向方法体添加内容
	 * @param sb
	 * @param appendContent
	 */
	private void appendBodyScript(StringBuffer sb,String appendContent){
		sb.append(appendContent).append(_ENTRY);
	}

	/**定义PYTHON格式算法类
	 * 1.引用python包
	 * 2.引用javaq包
	 * 3.定义类结构
	 * 4.添加工具对象引用
	 * 5.添加参数对象引用
	 * 6.格式化字符串
	 * 7.执行编译
	 * 8.输出算法到日志
	 * @param code
	 * @param algoContent
	 */
	private void definePythonClass(String code, String algoContent) {
		StringBuffer sb = new StringBuffer();
		this.importJythonLib(sb);
		this.importJavaLib(sb);
		this.initDefineClass(sb,code);
//		this.appendAPI(sb);
		appendBodyScript(sb,"    para = mapMapFromJava(algoPara);");
		
		this.completeMethodBody(sb,algoContent);
		String temp = sb.toString();
		logger.debug(temp);
//Orlando 20161110 使用文件流的执行方式，中文才不会乱码
//		interpreter.exec(temp);
		interpreter.execfile(new ByteArrayInputStream(temp.getBytes()));
	}
	
	private static final String _ENTRY = "\r\n";
	private static final String _LIBRARY = "global/algo/Lib";
	
	private void importJythonLib(StringBuffer sb){
//Orlando 20161110 指定文件编码格式为utf-8
		sb.append("#coding=utf8").append(_ENTRY);
		sb.append("import sys").append(_ENTRY);
	}
	
	/**
	 * 引用JAVA类的包
	 * 注意：如果增加了对JAVA类型的引用，需要在此处添加显示的引用代码
	 * 这里引用的是JAVA组合相关的工具类，核算用到的工具类等业务对象
	 * @param sb
	 */
	private void importJavaLib(StringBuffer sb){
		sb.append("from ").append("com.yss.ams.visaval.jython").append(" import ").append("IAlgo").append(_ENTRY);
	}
	
	/**
	 * 定义PYTHON类型
	 * 该类实现IALGO接口
	 * 并有一个无参构造器__init__,原想将组合，日期做为构造参数传入，但python无法封装DATE对象所以直接做为算法的方法参数引用
	 * @param sb
	 * @param code
	 */
	private void initDefineClass(StringBuffer sb,String code){
		sb.append("class _" + code + "(IAlgo):").append(_ENTRY);
		sb.append("  def __init__(self):").append(_ENTRY);
//		sb.append("    self.portCode=portCode;").append(enter);
//		sb.append("    self.actDate=actDate;").append(enter);
		sb.append("    pass").append(_ENTRY);
		sb.append("  def algoRule(self, algoPara,conn,portCode,actDate):").append(_ENTRY);
		sb.append("    self.portCode=portCode;").append(_ENTRY);
		sb.append("    self.actDate=actDate;").append(_ENTRY);
		sb.append("    self.conn=conn;").append(_ENTRY);
//		sb.append("    portCode=self.portCode;").append(enter);
//		sb.append("    actDate=self.actDate;").append(enter);
	}
	
	/**格式化字符串
	 * @param sb
	 * @param algoContent
	 */
	private void completeMethodBody(StringBuffer sb, String algoContent){
		if(null == algoContent){
			return;
		}
		
		String[] sentences = algoContent.split("\r\n");
		for(String eachSentence : sentences){
			if(eachSentence.trim().length()==0){
				continue;
			}
			sb.append("    ").append(eachSentence).append(_ENTRY);
		}	
	}
	
	/**定义PYTHON函数对象
	 * @return
	 */
	private String definePythonFunction(){
		StringBuffer defaultBuf = new StringBuffer();
		defaultBuf.append("def mapMapFromJava(map):").append(_ENTRY);
		defaultBuf.append("    result = {}").append(_ENTRY);
		defaultBuf.append("    if map == None :").append(_ENTRY);
		defaultBuf.append("      return     ").append(_ENTRY);
		defaultBuf.append("    keys = map.keySet()").append(_ENTRY);
		defaultBuf.append("    for key in keys:").append(_ENTRY);
		defaultBuf.append("      result[key] = map.get(key)").append(_ENTRY);
		defaultBuf.append("    return result").append(_ENTRY);
			
		return defaultBuf.toString();
	}
	
	/**
	 * 从算法缓存中查询算法定义
	 * 
	 * @param algoCode
	 *            算法代码
	 * @return
	 * @throws SQLException
	 */
	private AdvAlgo queryAlgo(String algoCode) throws SQLException {
		if (null == algoCode) {
			return null;
		}
		
		AlgoCache algoCache = CacheManager.getInstance().getCache(CacheGroup.ALGO);
		AdvAlgo advAlgo = algoCache.getCacheByKey(algoCode);
		return advAlgo;
	}

	/**
	 * Orlando 20160329 因为接口IAlgo方法变更调整逻辑
	 * 如果返回值是double直接返回，否则抛出异常
	 * 执行算法并返回结果，如果算法标识或者算法对象为空就返回0，未找到注册的算法类就抛异常
	 * @param algoCode
	 * @param algoPara
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public double executeAlgo(String algoCode, HashMap<String, Object> algoPara,
			Connection conn) throws Exception {
//		double d = 0.0;
//		if (null == algoCode || null == algoPara) {
//			return 0;
//		}
//		
//		AdvAlgo algo = this.queryAlgo(algoCode);
//		if (null == algo || null == algo.getC_ALGO_FORMULA() || algo.getC_ALGO_FORMULA().trim().length() == 0) {
//			return 0;
//		}
//		
//		factory.registClass(algoCode, algo.getC_ALGO_FORMULA());
//		IAlgo algo1 = factory.createAlgoRule(algoCode);
//		
//		d =  algo1.algoRule(algoPara, conn, (String)algoPara.get("portCode"), (Date)algoPara.get("actDate"));
		
		Object obj = this.enhancedPerform(algoCode, algoPara, conn);
		if(obj == null){
			return 0;
		}
		//add by yewenke 20160530
		if(JythonTypeProcess.isInteger(obj)){
			return (Integer)obj+0.0;
		}
		// add by liyanjun 2016-8-22 BUG #137748 【严重】【招商证券】算法优化版本统计不出分级的单位净值
		if(JythonTypeProcess.isNumber(obj)){
			return YssFun.toDouble(obj.toString());
		}
		else{
			//modify by yewenke 2016-03-30  不需要抛出异常
			return 0;
		}
	}
	
	/**
	 * Orlando 20160329 补充逻辑直接返回Object对象
	 * 由调用方去判断使用什么类型转换结果
	 * 增强版算法执行，支持算法执行返回值为任意对象
	 * @param algoContent
	 * @param algoPara
	 * @return
	 * @throws Exception
	 */
	public Object enhancedPerform(String algoCode, HashMap<String, Object> algoPara,
			Connection conn)
			throws Exception {
		Object obj = null;
		if (null == algoCode || null == algoPara) {
			return null;
		}
		
		AdvAlgo algo = this.queryAlgo(algoCode);
		if (null == algo || null == algo.getC_ALGO_FORMULA() || algo.getC_ALGO_FORMULA().trim().length() == 0) {
			return null;
		}
		
		factory.registClass(algoCode, algo.getC_ALGO_FORMULA());
		IAlgo algo1 = factory.createAlgoRule(algoCode);
		
		obj =  algo1.algoRule(algoPara, conn, (String)algoPara.get("portCode"), (Date)algoPara.get("actDate"));
		return obj;
	}
	
	/**
	 * BUG #128367 【紧急】【南方资本】最新的公式执行的逻辑有误
	 * Orlando 20160322 增加方法支持在线调整算法公式
	 * 原来算法公式类已经动态缓存到了factory中，调用此方法进行更新
	 * 注意：此方法要慎用，会造成permSize的增长，不能释放
	 * @param code
	 * @param algoContent
	 */
	public void updateAlgo(String code, String algoContent) {
		//如果已经缓存过了就刷新缓存，如果没有就不处理，在执行该算法时会初始化到缓存中
		if(this.clazzes.containsKey(code)){
			this.definePythonClass(code,algoContent);
			PyObject pyObject = interpreter.get("_"+code);
			this.clazzes.put(code, pyObject);
		}
	}
	
	/**
	 * 公式检查
	 * 
	 * @param algoContent 算法内容
	 * @return 是否可编译通过
	 * @throws Exception 此方法不会抛出异常
	 */
	public Map<String,String> checkFormula(String algoContent) throws Exception {
		try{
			this.definePythonClass("_TEST_ALGO__", algoContent);
		}
		catch(Exception ex){
			logger.debug(ex.toString());
			String err_msg = ex.toString();
			return getMsg(err_msg);
		}
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("true", "算法配置成功！");
		return map;
	}
	
	private Map<String,String> getMsg(String errMsg){
		//SyntaxError: ("no viable alternative at input '='", ('<iostream>', 29, 11, '    if(r <=  R):\n'))11
		String[] errArr = errMsg.split(",");
		String lineNum = (Integer.parseInt(errArr[2].trim()) - 11) + "";
		String str = errArr[4].replaceAll("'", "");
		String msg = str.substring(0, str.indexOf("\\"));
		Map<String,String> map = new HashMap<String, String>();
		map.put("false", "算法检测异常：第 '" + lineNum + "' 行配置有误！$错误信息：\r\n" + msg.trim());
		return map;
	}
	
	public static void main(String...args) throws Exception{
//		findBug代码检查  注释无用变量声明  edit by sunyanlin 20180320
//		String ss = new String("\u8bd5".getBytes("utf-8"));
		AvalJythonFactory factory = AvalJythonFactory.getInstance();

		String algo ="dict = {'交通':'A','交通银行':'A','中行':'B','中国银行':'B','工商':'C','工商银行':'C','中国工商银行':'C','农行':'D','农业银行':'D','中国农业银行':'D','建行':'E','建设银行':'E','中国建设银行':'E','中信':'F','中信银行':'F','光大':'G','光大银行':'G','中国光大银行':'G','浦发':'H','浦发银行':'H','上海浦发银行':'H', '上海浦东发展银行':'H','招行':'I','招商银行':'I','民生银行':'J','中国民生银行':'J','华夏银行':'K','兴业银行':'L','平安':'M','平安银行':'M','广发':'N','广发银行':'N','包商':'O','包商银行':'O','宁波':'P','宁波银行':'P','江苏':'Q','江苏银行':'Q','恒丰':'R','恒丰银行':'R','天津':'S','天津银行':'S','徽商':'U','上海':'T','上海银行':'T','徽商银行':'U','南京':'V','南京银行':'V','杭州':'W','杭州银行':'W','北京':'X','北京银行':'X','广州':'Y','广州银行':'Y','汉口':'Z', '汉口银行':'Z','哈尔滨':'1','哈尔滨银行':'1','浙商':'2','浙商银行':'2','大连':'3','大连银行':'3'} \r\n";
		algo += "for k,v in dict.items():\r\n";
		algo += "    print k,v\r\n";
		algo += "print sys.getdefaultencoding()\r\n";
		algo += "s='中文'\r\n";
		algo += "print s\r\n";
		algo += "print len(s)\r\n";
		algo += "print type(s)\r\n";
		algo += "print dict.get('上海浦东发展银行')\r\n";
		algo += "print sys.path\r\n";
//		algo += "unicode(s, 'gbk')\r\n";
		
//		String algo = "a = u'风卷残云'\r\n";
//		algo += "print type(a)\r\n";
//		algo += "b = a.unicode(a, 'gb2312')\r\n";
//		algo += "print type(b)";
		
		//测试算法
		//String algo = "print 'a'";
		factory.registClass("a", algo);

		IAlgo ialgo = factory.createAlgoRule("a");
		HashMap<String, Object> algoPara = new HashMap<String, Object>();
		ialgo.algoRule(algoPara, null, "", (Date)new Date());
		
//		//测试检查
//		for(int i=0; i < 100000; i++){
//			factory.checkFormula(algo);
//		}
		
		
//		for(int i=0; i < 100000; i++){
//			StringBuffer sb = new StringBuffer();
//			sb.append("from com.yss.algo.core import IAlgoCheck").append(_ENTRY);
//			sb.append("class IAlgoCheck():").append(_ENTRY);
//			sb.append("  def __init__(self): ").append(_ENTRY);
//			sb.append("      pass ").append(_ENTRY);
//			sb.append("  def check(self): ").append(_ENTRY);
//			sb.append("     "+algo).append(_ENTRY);
//			System.out.println(sb.toString());
//			factory.interpreter.exec(sb.toString());
//		}
	}
	
	private Map<String,String> map = new HashMap<String,String>();
	public Map<String,String> tempMap(){
		return map;
	}
}
