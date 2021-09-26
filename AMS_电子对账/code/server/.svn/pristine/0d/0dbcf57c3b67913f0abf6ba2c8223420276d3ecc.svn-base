package com.yss.uco.elecreco.er.reverse.compare.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.yss.ams.sec.information.support.modules.sv.base.cache.service.ISecbaseCacheDataService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.restful.RestfulConfigServiceImpl;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.ReflectionUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.DateUtil;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.er.generate.service.GeneElecDataService;
import com.yss.uco.elecreco.er.generate.util.KMTransUtil;
import com.yss.uco.elecreco.er.reverse.compare.common.IgnoreManager;
import com.yss.uco.elecreco.er.reverse.compare.common.ZbMapManager;
import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.service.IDataCompareService;
import com.yss.uco.elecreco.er.reverse.cons.ReveDzCons;
import com.yss.uco.elecreco.er.reverse.manager.ignore.pojo.IgnoreItem;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.uco.elecreco.er.reverse.manager.result.pojo.ReveResult;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.pojo.ResRela;
import com.yss.uco.elecreco.er.reverse.map.assmap.pojo.AssMap;
import com.yss.uco.elecreco.er.reverse.map.assmap.service.IAssMapService;
import com.yss.uco.elecreco.er.reverse.map.kmmap.pojo.KmMap;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaRecord;
import com.yss.uco.elecreco.er.reverse.map.kmrela.service.IKmRelaRecordService;
import com.yss.uco.elecreco.support.bean.ElecRela;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;


public abstract class DataCompareService implements IDataCompareService {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final int DZ_RESULT_INIT = 1;//初始化状态，未开始对账
	public static final int DZ_RESULT_DIFF = 2;//对账不一致
	public static final int DZ_RESULT_SAME = 3;//对账一致
	protected int compareResult = DZ_RESULT_INIT;//对账结果
	/**
	 * 资托资产映射
	 */
	protected List<AssMap> assList = null;
	/**
	 * 席位代码，证券代码转换字典 key:原值，value目标值
	 */
	protected Map<String, String> transMap = null;
	/**
	 * 是否转换席位代码，证券代码
	 */
	protected boolean isTransKM = false;
	/**
	 * 是否将证券内码转换ISIN码
	 */
	protected boolean isTransIsin = false;
	/**
	 * 是否多托管行对账
	 */
	protected boolean isMultiTgh = false;
	/**
	 * 传入的需要对账的数据
	 */
	protected ErReveInfo reveInfo = null;
	/**
	 * key:映射关系ID,查询映射关系时，区分优先级,组合>托管行>公共
	 * 
	 */
	protected Map<String,KmRelaRecord> kmMaps = new HashMap<String,KmRelaRecord>();
	/**
	 * 对账指标，key:指标代码
	 */
	protected Map<String,ElecRela> elecRelas = new HashMap<String, ElecRela>();
	
	/**
	 * 将多对多拆成一对多
	 * 一对多映射关系
	 * key:内部科目
	 * value:Set<String>外部科目代码
	 */
	protected Map<String,Set<String>> otm = new HashMap<String, Set<String>>(); 
	/**
	 * 对账类型
	 */
	protected String fileType = "";
	/**
	 * 用户代码
	 */
	protected String userCode = "";
	/**
	 * 组合代码
	 */
	protected String portCode = "";
	/**
	 * 报表类型
	 */
	protected String rptType = "";
	/**
	 * protected String sn = "";
	 */
	protected String sn = "";
	/**
	 * 对账模式
	 */
	protected String dzMode = "";
	/**
	 * yyyy-MM-dd格式,数据的开始日期，日报与gzDate相同
	 */
	protected String startDate = "";
	/**
	 * yyyy-MM-dd格式，数据的结束日期，日报与gzDate相同
	 */
	protected String endDate = "";
	/**
	 * yyyy-MM-dd格式
	 */
	protected String gzDate = "";
	/**
	 * 证券缓存
	 */
	private BaseCache<SecBase> baseCache = null;
	/**
	 * Dao层类
	 */
	protected DataCompareDao dataCompareDao = null;
	/**
	 * 日志类
	 */
	protected Logger logger = LogManager.getLogger(GeneElecDataService.class);
	
	public DataCompareService(DataCompareDao dataCompareDao)
	{
		this.dataCompareDao = dataCompareDao;
	}
	
	public BaseCache<SecBase> getBaseCache() {
		if(this.baseCache == null)
		{
			this.baseCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		}
		return this.baseCache;
	}

	public void setBaseCache(BaseCache<SecBase> baseCache) {
		this.baseCache = baseCache;
	}

	/**
	 * 通过比对项获取内部对象属性名称
	 * @param item
	 * @return
	 */
	public abstract String getInnerPropertyByCompareItem(String item);
	/**
	 * 通过比对项获取外部对象名称
	 * @param item
	 * @return
	 */
	public abstract String getOutPropertyByCompareItem(String item);
	
	public abstract List<ReveResult> compareData(IgnoreManager ignoreManager,ZbMapManager zbMapManager) throws Exception;
	@Override
	public Map<String, Object> doOper() {
		return compareData(reveInfo, userCode);
	}
	@Override
	public void init(ErReveInfo info, String userCode) {
		this.reveInfo = info;
		this.userCode = userCode;
	}
	
	public void initReportDate(String gzDate,String rptType) throws Exception 
	{
		Date date = DateUtil.stringtoDate(gzDate, DATE_FORMAT);
		if("01".equalsIgnoreCase(rptType))//01 日报
		{
			this.startDate = gzDate;
			this.endDate = gzDate;
		}else if("03".equalsIgnoreCase(rptType))//月报
		{
			Date tempDate = DateUtil.getFirstNatureDayOnMonth(date);
			this.startDate = DateUtil.dateToString(tempDate, DATE_FORMAT);
			tempDate = DateUtil.getLastNatureDayOnMonth(date);
			this.endDate = DateUtil.dateToString(tempDate, DATE_FORMAT);
		}else if("04".equalsIgnoreCase(rptType))//季报
		{
			Date tempDate = DateUtil.getFirstNatureDayOnQuarter(date);
			this.startDate = DateUtil.dateToString(tempDate, DATE_FORMAT);
			tempDate = DateUtil.getLastNatureDayOnQuarter(date);
			this.endDate = DateUtil.dateToString(tempDate, DATE_FORMAT);
		}else if("05".equalsIgnoreCase(rptType))//半年报
		{
			Date tempDate = DateUtil.getFirstNatureDayOnHalfYear(date);
			this.startDate = DateUtil.dateToString(tempDate, DATE_FORMAT);
			tempDate = DateUtil.nextMonth(tempDate, 6);
			tempDate = DateUtil.getFirstNatureDayOnHalfYear(tempDate);
			tempDate = DateUtil.nextDay(tempDate, -1);
			this.endDate = DateUtil.dateToString(tempDate, DATE_FORMAT);
		}else if("06".equalsIgnoreCase(rptType))//年报
		{
			Date tempDate = DateUtil.getFirstNatureDayOnYear(date);
			this.startDate = DateUtil.dateToString(tempDate, DATE_FORMAT);
			tempDate = DateUtil.getLastNatureDayOnYear(date);
			this.endDate = DateUtil.dateToString(tempDate, DATE_FORMAT);
		}else
		{
			throw new Exception("初始化日期出错！不支持的报表类型！");
		}
	}
	
	public Map<String, Object> compareData(ErReveInfo info,String userCode) {
		Map<String,Object> result = new HashMap<String, Object>();
		try{
			//检查参数并初始化参数，包括席位证券代码转换
			initParams(info,userCode);
			//检查是否做做过资产映射
			initAssMap(portCode,fileType);
			if(this.assList.size() == 1)//一个托管行,一条映射关系
			{
				isMultiTgh = false;
				//initIgnoreItems(portCode,fileType,assList.get(0).getC_TGH_CODE());
				IgnoreManager ignoreManager = new IgnoreManager(this);
				ignoreManager.initIgnoreItems(portCode, fileType, assList.get(0).getC_TGH_CODE());
				//initZbMaps(portCode,fileType,assList.get(0).getC_TGH_CODE());
				ZbMapManager zbMapManager = new ZbMapManager(this);
				zbMapManager.initZbMaps(portCode, fileType, assList.get(0).getC_TGH_CODE());
				initKmMaps(portCode,fileType,assList.get(0).getC_TGH_CODE());
				//对账
				List<ReveResult> resList = compareData(ignoreManager, zbMapManager);
				if(this.compareResult != DZ_RESULT_DIFF)
				{
					this.compareResult = DZ_RESULT_SAME;
				}
				result = handleCompareResult(resList,this.reveInfo);
			}else//多条映射关系
			{
				isMultiTgh = true;
				String[] tghCodes = getTghCodesFromAssMaps(this.assList);
				//加载科目映射，只加载组合级别和公共级别
				initPortAndCommKmMaps(portCode,fileType);
				ZbMapManager zbMapManager = new ZbMapManager(this);
				zbMapManager.initZbMaps(portCode, fileType, tghCodes);
				IgnoreManager ignoreManager = new IgnoreManager(this);
				ignoreManager.initIgnoreItems(portCode, fileType, tghCodes);
				Map<String, Map<String,BasePojo>> tghDatas = collectMultTghData(this.assList);
				List<ReveResult> resList = compareMultTghData(tghDatas,ignoreManager,zbMapManager);
				result = handleCompareResult(resList,this.reveInfo);
			}
		}catch(Exception e){
			logger.error("对账失败！"+e.getMessage(), e);
			this.reveInfo.setC_DV_HANDLE_STATE(ReveDzCons.INFO_HS_FAIL);
			this.reveInfo.setC_HANDLE_INFO("对账失败！"+e.getMessage());
			try {
				saveErReveInfo(this.reveInfo);
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
			
		}
		return result;
	}

	private String[] getTghCodesFromAssMaps(List<AssMap> assList) {
		String[] tghCodes = new String[assList.size()];
		int i = 0;
		for(AssMap ass : assList)
		{
			tghCodes[i] =  ass.getC_TGH_CODE();
			i++;
		}
		return tghCodes;
	}

	protected void initPortAndCommKmMaps(String portCode, String fileType) {
		Map<String,KmRelaRecord> relaMap = new HashMap<String, KmRelaRecord>();
		IKmRelaRecordService kmRelaRecordService = YssServiceFactory.getInstance().createService(IKmRelaRecordService.class);
		List<KmRelaRecord> dataList = kmRelaRecordService.getPortAndCommKmMap(portCode);
		if(dataList == null)
		{
			return;
		}
		for(KmRelaRecord krr : dataList)
		{
			List<KmMap> inners = krr.getList_KM_INNER()==null?new ArrayList<KmMap>():krr.getList_KM_INNER();
			List<KmMap> outs = krr.getList_KM_OUT()==null?new ArrayList<KmMap>():krr.getList_KM_OUT();
			
			Set<String> set = null;
			for(KmMap innerKm : inners)
			{
				//证券代码转换，席位转换
				if(isTransKM)
				{
					innerKm.setC_KM_CODE(KMTransUtil.transKMCode(innerKm.getC_KM_CODE(), transMap));
				}
				String code = innerKm.getC_KM_CODE();
				//替换本方科目代码后面的证券代码
				if(isTransIsin && haveSecCode(code))
				{
					int i = code.lastIndexOf(".");
					String isinCode = this.getISINCode(code.substring(i+1));
					if(isinCode!= null && !StringUtil.IsNullOrEmptyT(isinCode))
					{
						code = code.substring(0, i+1)+isinCode;
						innerKm.setC_KM_CODE(code);
					}
				}
				set = new HashSet<String>();
				for(KmMap outKm : outs)
				{
					set.add(outKm.getC_KM_CODE());
				}
				
				//将内部科目代码关联到科目映射关系，用于优先级过滤数据
				
				if(!relaMap.containsKey(code))
				{
					//codes.add(code);
					relaMap.put(code, krr);
					this.kmMaps.put(krr.getKmRela().getId(), krr);
					//将多对多拆成一对多
					this.otm.put(code,set);
				}else
				{
					KmRelaRecord oldKrr = relaMap.get(code);
					List<KmMap> oldKms = oldKrr.getList_KM_INNER()==null?new ArrayList<KmMap>():oldKrr.getList_KM_INNER();
					//移除掉之前添加的映射关系
					this.kmMaps.remove(oldKrr.getKmRela().getId());
					for(KmMap oldKm : oldKms)
					{
						this.otm.remove(oldKm);
					}
					//添加新的映射关系
					this.kmMaps.put(krr.getKmRela().getId(), krr);
					relaMap.put(code, krr);
					//将多对多拆成一对多
					this.otm.put(code,set);
				}
				
			}
		}
	}

	/**
	 * 比较多个托管行的数据（汇总）
	 * @param outDatas 多个托管行汇总后的数据  key:科目代码 	value:托管行数据集合
	 * @return
	 * @throws Exception 
	 */
	public abstract List<ReveResult> compareMultTghData(Map<String, Map<String,BasePojo>> outDatas,IgnoreManager ignoreManager,ZbMapManager zbMapManager) throws Exception;

	/**
	 * 将托管行数据汇总
	 * @param assMaps 资产映射
	 * @return 多个托管行数据 key：科目代码       value：托管行数据
	 * @throws Exception 
	 */
	public abstract Map<String, Map<String,BasePojo>> collectMultTghData(List<AssMap> assMaps) throws Exception;

	/**
	 * 
	 * @param resList
	 * @return
	 * @throws Exception 
	 */
	protected Map<String, Object> handleCompareResult(List<ReveResult> resList,ErReveInfo reveInfo) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		reveInfo.setC_DV_HANDLE_STATE(ReveDzCons.INFO_HS_SUCCESS);
		reveInfo.setC_HANDLE_INFO("对账成功！");
		map.put(ReveDzCons.INFO_HANDLE_STATE, ReveDzCons.INFO_HS_SUCCESS);
		if(this.compareResult==DZ_RESULT_SAME)
		{
			reveInfo.setC_DV_DZ_RESULT(ReveDzCons.INFO_RDZ_RESULT_SAME);
		}else
		{
			reveInfo.setC_DV_DZ_RESULT(ReveDzCons.INFO_RDZ_RESULT_DIFF);
		}
		saveErReveInfo(reveInfo);
		saveDzResult(resList);
		return map;
	}
	
	private void saveDzResult(List<ReveResult> resList) {
		
		Connection conn = null;
		try {
			conn = this.dataCompareDao.loadNewConnection();
			conn.setAutoCommit(false);
			this.dataCompareDao.deleteReveDzResult(portCode, fileType, rptType, startDate, endDate,conn);
			this.dataCompareDao.saveDzResult(resList,conn);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new DataAccessException("保存反向对账结果失败：" + ex.getMessage(), ex);
		} finally {
			this.dataCompareDao.releaseConnection(conn);
		}
		
	}
	
	protected int getKmMapLevel(String scope) {
		if(ReveDzCons.REVE_YSFW_CPYS.equalsIgnoreCase(scope))//优先级最高
		{
			return 3;
		}else if(ReveDzCons.REVE_YSFW_TGFYS.equalsIgnoreCase(scope))
		{
			return 2;
		}else
		{
			return 1;
		}
	}
	
	protected void initKmMaps(String portCode, String fileType,
			String c_TGH_CODE) throws Exception {
		Map<String,KmRelaRecord> relaMap = new HashMap<String, KmRelaRecord>();
		IKmRelaRecordService kmRelaRecordService = YssServiceFactory.getInstance().createService(IKmRelaRecordService.class);
		List<KmRelaRecord> dataList = kmRelaRecordService.getCompareKmMap(portCode, c_TGH_CODE);
		if(dataList == null)
		{
			return;
		}

		for(KmRelaRecord krr : dataList)
		{
			List<KmMap> inners = krr.getList_KM_INNER()==null?new ArrayList<KmMap>():krr.getList_KM_INNER();
			List<KmMap> outs = krr.getList_KM_OUT()==null?new ArrayList<KmMap>():krr.getList_KM_OUT();
			
			Set<String> set = null;
			for(KmMap innerKm : inners)
			{
				//证券代码转换，席位转换
				if(isTransKM)
				{
					innerKm.setC_KM_CODE(KMTransUtil.transKMCode(innerKm.getC_KM_CODE(), transMap));
				}
				String code = innerKm.getC_KM_CODE();
				//替换本方科目代码后面的证券代码
				if(isTransIsin && haveSecCode(code))
				{
					int i = code.lastIndexOf(".");
					String isinCode = this.getISINCode(code.substring(i+1));
					if(isinCode!= null && !StringUtil.IsNullOrEmptyT(isinCode))
					{
						code = code.substring(0, i+1)+isinCode;
						innerKm.setC_KM_CODE(code);
					}
				}
				set = new HashSet<String>();
				for(KmMap outKm : outs)
				{
					set.add(outKm.getC_KM_CODE());
				}
				
				//将内部科目代码关联到科目映射关系，用于优先级过滤数据
				
				if(!relaMap.containsKey(code))
				{
					//codes.add(code);
					relaMap.put(code, krr);
					this.kmMaps.put(krr.getKmRela().getId(), krr);
					//将多对多拆成一对多
					this.otm.put(code,set);
				}else
				{
					KmRelaRecord oldKrr = relaMap.get(code);
					List<KmMap> oldKms = oldKrr.getList_KM_INNER()==null?new ArrayList<KmMap>():oldKrr.getList_KM_INNER();
					//移除掉之前添加的映射关系
					this.kmMaps.remove(oldKrr.getKmRela().getId());
					for(KmMap oldKm : oldKms)
					{
						this.otm.remove(oldKm);
					}
					//添加新的映射关系
					this.kmMaps.put(krr.getKmRela().getId(), krr);
					relaMap.put(code, krr);
					//将多对多拆成一对多
					this.otm.put(code,set);
				}
				
			}
			//this.kmList.add(krr);
			//this.kmMaps.put(key, value)
		}
	}
	
	/**
	 * 更新生成正向电子对账详情的对账方向为反向
	 * @param value
	 * @param sn
	 * @param portCode
	 * @param dzDate
	 */
	protected void updateErInfoWay(String value,String sn,String portCode,String dzDate,String fileType,String rptType,Connection conn) {
		this.dataCompareDao.updateErInfoWay(value, sn, portCode, dzDate,fileType,rptType,conn);
	}
	
	
	protected void saveErReveInfo(ErReveInfo info) throws Exception
	{
		this.dataCompareDao.saveErReveInfo(info);
//		ErReveInfoService infoService = new ErReveInfoService();
//		deleteErReveInfo(info.getC_PORT_CODE(), info.getC_FILE_TYPE(), info.getC_RPT_TYPE(), info.getD_DATE());
//		infoService.insert(info);
	}
	
	/**
	 * 检查并初始化资产映射
	 * 如果没有设置映射，抛出异常，并保存到数据库，在反向对账管理界面展示
	 * @param portCode
	 * @param fileType
	 * @throws Exception
	 */
	protected void initAssMap(String portCode, String fileType) throws Exception {
		IAssMapService assService = YssServiceFactory.getInstance().createService(IAssMapService.class);
		List<AssMap> list = assService.getAssMapByPortCodeAndFileType(portCode, fileType);
		if(list == null || list.size() == 0)
		{
			list = assService.getCommonAssMapByPortCode(portCode);
		}
		if(list == null || list.size() == 0)
		{
			//在异常里面统一处理
//			this.reveInfo.setC_DV_HANDLE_STATE(ReveDzCons.INFO_HS_FAIL);
//			this.reveInfo.setC_HANDLE_INFO("没有设置资产映射！");
//			saveErReveInfo(this.reveInfo);
			throw new Exception("没有设置资产映射！");
		}
		this.assList = list;
	}
	
	public boolean isTransKM()
	{
		return this.isTransKM;
	}
	
	public boolean isMultiTgh()
	{
		return this.isMultiTgh;
	}
	
	/**
	 * 席位代码，证券代码转换,isin码转换
	 * @param portCode
	 * @throws Exception
	 */
	protected void initPortParams(String portCode) throws Exception
	{
		//代码转换
		Connection conn = null;
		AdmPortActParams params = null;
		try {
			params = new AdmPortActParams(this.portCode, new Date());
			conn = dataCompareDao.loadNewConnection();
			params.setDbConn(conn);
			params.initActParams();
			int transGZ = KMTransUtil.getGzCode(params.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_ZQDMZH), params.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_JYQDZH));
			if(transGZ != KMTransUtil.ZHGZ_BZH)
			{
				isTransKM = true;
				transMap = KMTransUtil.getTransMap(conn,transGZ);
			}
			String isin = params.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_FXZHISIN);
			this.isTransIsin = "1".equalsIgnoreCase(isin) ? true : false;
		} catch (Exception e) {
			throw e;
		}finally
		{
			dataCompareDao.releaseConnection(conn);
		}
		
	}
	public void initParams(ErReveInfo info,String userCode) throws Exception {
		if(info == null)
		{
			throw new Exception("请选择一条要生成数据！");
		}
		this.reveInfo = info;
		this.userCode = userCode;
		this.reveInfo.setModifier(userCode);
		this.reveInfo.setAuditState(1);
		this.reveInfo.setModifyDate(DateUtil
				.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
		fileType = info.getC_FILE_TYPE();
		portCode = info.getC_PORT_CODE();
		rptType = info.getC_RPT_TYPE();
		gzDate = info.getD_DATE();
		
		if(fileType == null || "".equalsIgnoreCase(fileType.trim()))
		{
			throw new Exception("对账类型不能为空！");
		}
		if(portCode == null || "".equalsIgnoreCase(portCode.trim()))
		{
			throw new Exception("组合不能为空！");
		}
		
		if(rptType == null || "".equalsIgnoreCase(rptType))
		{
			throw new Exception("日期不能为空！");
		}
		if(gzDate == null || "".equalsIgnoreCase(gzDate.trim()))
		{
			throw new Exception("日期不能为空！");
		}
		try{
			initReportDate(gzDate, rptType);
			//初始化组合参数
			initPortParams(portCode);
			initDzMode();
			initElecRela();
		}catch(Exception e)
		{
			throw new Exception("初始化参数出错！"+e.getMessage(),e);
		}
	}
	
	public void initDzMode()
	{
		dzMode = this.dataCompareDao.getDzMode(portCode, fileType);
	}
	
	public void initElecRela()
	{
		Map<String, ElecRela> map = this.dataCompareDao.getZbItems(fileType);
		if(map != null)
		{
			this.elecRelas.putAll(map);
		}
	}
	
	/**
	 * 获取比对项的值
	 * @param pojo
	 * @param item
	 * @param kmScope
	 * @return
	 * @throws YssException
	 */
	public <K extends BasePojo> BigDecimal getItemValue(K pojo,String item,String kmScope) throws YssException
	{
		if(pojo==null)
		{
			return new BigDecimal(0);
		}
		if(ReveDzCons.REVE_KMFW_INNER.equalsIgnoreCase(kmScope))
		{
			item = getInnerPropertyByCompareItem(item);
		}else if(ReveDzCons.REVE_KMFW_OUT.equalsIgnoreCase(kmScope))
		{
			item = getOutPropertyByCompareItem(item);
		}
		Object resValue =  ReflectionUtil.getFieldValue(pojo, item);
		if(resValue!=null)
		{
			if("".equalsIgnoreCase(String.valueOf(resValue)))
			{
				return new BigDecimal(0);
			}else
			{
				String value = String.valueOf(resValue).trim();
				value = value.replaceAll(",", "");
				value = value.replaceAll("，", "");
				//估值表生成时，直接去掉了%分号，这里也直接去掉
				if(value.endsWith("%")||value.endsWith("%")||value.endsWith("％"))
				{
					value = value.substring(0, value.length()-1);
					return new BigDecimal(value);
					//return new BigDecimal(value).divide(new BigDecimal(100));
				}
				return new BigDecimal(value);
			}
		}
		
		return new BigDecimal(0);
	}
	/**
	 * 设置比对项的结果
	 * 将比对后的数值设置到对应的属性上
	 * @param pojo
	 * @param item
	 * @param value
	 * @throws Exception
	 */
	public abstract String setResultItemValue(BasePojo pojo,String item,String value) throws Exception;
	
	
	public void addResResultToResult(List<ResRela> resResult,ReveResult result,String kmScope)
	{
		if(ReveDzCons.REVE_KMFW_INNER.equalsIgnoreCase(kmScope))
		{
			result.setList_RESRELA_INNER(resResult);
		}else
		{
			result.setList_RESRELA_OUT(resResult);
		}
	}
	
	/**
	 * 适用于科目代码不重复的数据，多条数据的同一科目代码的数据会汇总
	 * @param items
	 * @param innerGzs
	 * @param outGzs
	 * @param ignoreMap 
	 * @param reveResult
	 * @throws Exception 
	 */
	public void compareAllItem(Map<String,String> items,List<BasePojo> innerGzs,List<BasePojo> outGzs,List<ReveResult> reveResults, IgnoreManager ignoreManager) throws Exception
	{
		BigDecimal bfSum = new BigDecimal(0);
		BigDecimal dfSum = new BigDecimal(0);
		boolean isNumber = true;
		boolean isSame = true;
		StringBuffer bfKmCode = new StringBuffer();
		StringBuffer dfKmCode = new StringBuffer();
		StringBuffer itemResult = new StringBuffer();
		ReveResult result = new ReveResult(fileType, portCode, rptType, gzDate, gzDate);
//		Map<String,ResRela> innerRes = new HashMap<String,ResRela>();
//		Map<String,ResRela> outRes = new HashMap<String,ResRela>();
		if(items == null || items.size() == 0)
		{
			return;
		}
		
		if(innerGzs == null)
		{
			innerGzs = new ArrayList<BasePojo>();
		}
		if(outGzs == null)
		{
			outGzs = new ArrayList<BasePojo>();
		}
		//两边都没有数据，不需要比较
		//映射关系在两边都找不到对应的科目会出现这种情况
		if(innerGzs.size() == 0 && outGzs.size() == 0)
		{
			return ;
		}
		List<StringBuffer> innerIgnores = createStringBuffers(innerGzs.size());
		List<StringBuffer> outIgnores = createStringBuffers(outGzs.size());
		List<ResRela> innerResRelas = createResRelas(innerGzs,ReveDzCons.REVE_KMFW_INNER);
		List<ResRela> outResRelas = createResRelas(outGzs,ReveDzCons.REVE_KMFW_OUT);
		for(String item : items.keySet())
		{
			bfSum = new BigDecimal(0);
			for(int inIndex = 0; inIndex < innerGzs.size(); inIndex++)
			{
				BasePojo innerGz = innerGzs.get(inIndex);
				BigDecimal value = new BigDecimal(0);
				try{
					value = getItemValue(innerGz, item,ReveDzCons.REVE_KMFW_INNER);
				}catch(NumberFormatException e){
					isNumber = false;
				}
				//String proName = getInnerPropertyByCompareItem(item);
				String proName = setResultItemValue(innerResRelas.get(inIndex),item,String.valueOf(value));
				String rowFlag = getRowFlag(ReveDzCons.REVE_KMFW_INNER,innerGz);
				if(ignoreManager.isBfRowIgnore(rowFlag))
				{
					innerIgnores.get(inIndex).append(proName).append(",");
					break;
				}
				//本方列忽略item
				if(ignoreManager.isBfColIgnore(item))
				{
					innerIgnores.get(inIndex).append(proName).append(",");
					break;
				}
				//本方单元格忽略
				if(ignoreManager.isBfCellIgnore(rowFlag+item))
				{
					innerIgnores.get(inIndex).append(proName).append(",");
					break;
				}
				bfSum = bfSum.add(value);
			}
			itemResult.append("本方").append(items.get(item)).append(":").append(bfSum).append(",");
			dfSum = new BigDecimal(0);
			for(int outIndex = 0; outIndex < outGzs.size(); outIndex++)
			{
				BasePojo gzOut = outGzs.get(outIndex);
				BigDecimal value = new BigDecimal(0);
				try{
					value = getItemValue(gzOut, item,ReveDzCons.REVE_KMFW_OUT);
				}catch(NumberFormatException e){
					isNumber = false;
				}
				//String proName = getOutPropertyByCompareItem(item);
				String proName = setResultItemValue(outResRelas.get(outIndex),item,String.valueOf(value));
				String rowFlag = getRowFlag(ReveDzCons.REVE_KMFW_OUT,gzOut);
				String tghCode = getTghCodeFromOutObject(gzOut);
				//对方行忽略
				if(ignoreManager.isDfRowIgnore(rowFlag, tghCode))
				{
					outIgnores.get(outIndex).append(proName).append(",");
					break;
				}
				//对方列忽略
				if(ignoreManager.isDfColIgnore(item, tghCode))
				{
					outIgnores.get(outIndex).append(proName).append(",");
					break;
				}
				//对方单元格忽略
				if(ignoreManager.isDfCellIgnore(rowFlag+item, tghCode))
				{
					outIgnores.get(outIndex).append(proName).append(",");
					break;
				}
				dfSum = dfSum.add(value);
			}
			itemResult.append("对方").append(items.get(item)).append(":").append(dfSum).append(",");
			BigDecimal ce = bfSum.subtract(dfSum);
			setResultItemValue(result,item,String.valueOf(ce));
			if(ce.doubleValue() != 0)
			{
				//标记为不一致
				isSame = false;
			}
		}//end for items
		if(!isSame)
		{
			StringBuffer sb = new StringBuffer();
			sb.append("[不一致]").append("[");
			sb.append("本方格式:").append(bfKmCode.length()>0?bfKmCode.toString():",");
			sb.append("对方格式:").append(dfKmCode.length()>0?dfKmCode.toString():",");
			sb.append(itemResult).append("]");
			result.setC_RESULT_INFO(sb.toString());
			result.setC_DV_DZ_RESULT(ReveDzCons.RDZ_RESULT_DIFF);
			//设置本次对账为不一致
			this.compareResult = DZ_RESULT_DIFF;
		}else if(!isNumber)
		{
			//垃圾数据
			result.setC_RESULT_INFO("该行数据格式有问题！");
			result.setC_DV_DZ_RESULT(ReveDzCons.RDZ_RESULT_DIFF);
			result.setC_RESULT_INFO("[不一致]");
		}
		else
		{
			result.setC_DV_DZ_RESULT(ReveDzCons.RDZ_RESULT_SAME);
			result.setC_RESULT_INFO("[一致]");
		}
		setIgnoreFlags(outResRelas,outIgnores);
		setIgnoreFlags(innerResRelas,innerIgnores);
		addResResultToResult(outResRelas, result, ReveDzCons.REVE_KMFW_OUT);
		addResResultToResult(innerResRelas, result, ReveDzCons.REVE_KMFW_INNER);
		result.setD_TIME(DateUtil.getNow(DATE_FORMAT));
		result.setD_START_DATE(startDate);
		result.setD_END_DATE(endDate);
		result.setC_SN(this.sn);
		result.setC_DEALER(userCode);
		reveResults.add(result);
	}
	
	protected void setIgnoreFlags(List<ResRela> resRelas,
			List<StringBuffer> ignoreFlags) {
		for (int i = 0; i < resRelas.size(); i++) {
			resRelas.get(i).setC_IGNORE_FLAG(ignoreFlags.get(i).toString());
		}
		
	}

	protected List<StringBuffer> createStringBuffers(int size) {
		List<StringBuffer> results = new ArrayList<StringBuffer>();
		for(int i = 0; i<size; i++)
		{
			results.add(new StringBuffer());
		}
		return results;
	}
	
	protected List<ResRela> createResRelas(List<BasePojo> datas,String kmScope) throws YssException {
		List<ResRela> results = new ArrayList<ResRela>();
		for(BasePojo pojo : datas)
		{
			ResRela res = createResRela(kmScope, pojo);
			results.add(res);
		}
		return results;
	}

	public abstract String getTghCodeFromOutObject(BasePojo dataPojo);

	/**
	 * 把数据的科目信息放到对账结果里，方便展示
	 * @param kmScope
	 * @param pojo
	 * @return
	 * @throws YssException
	 */
	public abstract ResRela createResRela(String kmScope,BasePojo pojo) throws YssException;
	
	
	
	/**
	 * 通过对账类型获取行标记
	 * 估值表，余额表，科目表为科目代码c_KM_CODE的值
	 * 资产负债表，利润表，所有者权益（基金净值）变动表为指标代码c_INDEX_CODE的值
	 * @param fileType
	 * @return
	 * @throws YssException 
	 */
	public abstract String getRowFlag(String kmScope,BasePojo pojo) throws YssException;

	/**
	 * 从比对项中移除忽略的列
	 * @param items
	 * @param bfCol
	 * @param dfCol
	 */
	public void removeIgnoreItem(Map<String,String> items,Map<String,IgnoreItem> bfCol,Map<String,IgnoreItem> dfCol)
	{
		Iterator<Entry<String, String>> iterator = items.entrySet().iterator();
		//去掉列忽略
		while(iterator.hasNext())
		{
			String item = iterator.next().getKey();
			if(bfCol.containsKey(item))
			{
				iterator.remove();
				continue;
			}
			if(dfCol.containsKey(item))
			{
				iterator.remove();
			}
		}
	}
	
	/**
	 * 获取所需要对账的比对项
	 * @return map key:比对项代码，比对项名称
	 */
	public abstract Map<String,String> getCompareItem();
	/**
	 * 是否将证券内码转换为isin码
	 * @return
	 */
	public boolean isTransIsin()
	{
		return this.isTransIsin;
	}

	/**
	 * 包含证券代码返回true
	 * @return
	 */
	public boolean haveSecCode(String kmCode) {
		if(kmCode == null||StringUtil.IsNullOrEmptyT(kmCode))
		{
			return false;
		}
		if(kmCode.contains(" ")&&kmCode.contains("."))
		{
			return true;
		}
		return false;
	}
	
	public String getISINCode(String secCode) {
		SecBase secbase = null;
		if(new RestfulConfigServiceImpl().getConfig().isShell()){
			ISecbaseCacheDataService secBaseService = YssServiceFactory.getInstance().createService(ISecbaseCacheDataService.class);
			secbase = secBaseService.getCacheByKey(secCode);
		}else{
			 BaseCache<SecBase> cache = this.getBaseCache();
				if(cache != null)
				{
					secbase = cache.getCacheByKey(secCode);
				}
		}
		
		if(secbase != null && !StringUtil.IsNullOrEmptyT(secbase.getC_SEC_ISIN_CODE()))
		{
			return secbase.getC_SEC_ISIN_CODE();
		}
		
		return null;
	}
	/**
	 * 席位代码，证券代码转换字典 key:原值，value目标值
	 * @return
	 */
	public Map<String, String> getTransMap()
	{
		return this.transMap;
	}
}

	