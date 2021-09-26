package com.yss.ams.sec.information.modules.sv.base.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.admin.SecBaseInfoDataAdmin;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseInfoSqlBuilder;
import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecShortPojo;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseInfoDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.CacheDataExtend;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ShortDataListPackage;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.log.annotations.OperLog;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.platform.support.workspace.panel.service.IVatPlanSecVarPanelService;

/**
 * 证券基本信息数据服务类
 * @author 马向峰
 *
 */
public class SecBaseInfoDataService implements ISecBaseInfoDataService{
	private SecBaseInfoDataAdmin secBaseAdmin = null;
	private String currUserCode = null;
	public SecBaseInfoDataService() {
		secBaseAdmin = new SecBaseInfoDataAdmin(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseInfoSqlBuilder());
	}

	/**
	 * 获取所有证券信息
	 * @return 证券信息
	 */
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return secBaseAdmin.getAllDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 根据证券的 币种代码、交易市场代码、证券品种代码、资产负债的品种 查询证券信息
	 * @author 马向峰 拆分 20170703
	 * @param filter
	 * @return	证券信息
	 */
	public <K extends BasePojo> List<K> getDataList(SecBase filter) throws ServiceException {
		try {
			return secBaseAdmin.getAllDataList(filter);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据请求返回数据列表title
	 * @author 马向峰
	 * @Date 20170703
	 * @return 列表title
	 */
	public QueryRes getDataListRes() throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secBaseAdmin.getAllDataList();
			res.setDataList(pojoList);
			res.setMenuId("sv_sec");
			res.setHeadKeyList(ServiceAssistance.getListHead("sv_sec",SecInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	/**
	 * 根据Code  获取证券信息
	 * @param dataCode
	 * @return 证券信息
	 * @author 马向峰
	 */
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		try {
			return secBaseAdmin.getDataByCode(dataCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据证券品种代码查询证券信息
	 * @author 马向峰
	 * @param types  证券品种代码
	 * @return 证券信息
	 * @Date 20170703
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		try {
			return secBaseAdmin.getDataListByTypes(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据证券品种代码和证券上市代码  查询证券信息
	 * @param types  证券品种代码
	 * @return 证券信息
	 * @author 马向峰
	 * @Date 20170703
	 */
	public <K extends BasePojo> List<K> getDataListByTypesAndMkt(String[] types)
			throws ServiceException {
		try {
			return secBaseAdmin.getDataListByTypesAndMkt(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据证券品种类型返回数据列表title
	 * @author 马向峰
	 * @Date 20170703
	 * @return 列表title
	 */
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secBaseAdmin.getDataListByTypes(types);
			res.setDataList(pojoList);
			res.setMenuId("sv_sec");
			res.setHeadKeyList(ServiceAssistance.getListHead("sv_sec",SecInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		try {
			return secBaseAdmin.getKeyConvertMap();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据 证券代码、上市日期、退市日期 查询证券信息
	 * @return	证券信息
	 */
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		try {
			return secBaseAdmin.getDataListByKeys(keys);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据 证券代码、上市日期、退市日期 返回数据列表title
	 * @author 马向峰
	 * @Date 20170703
	 * @return 列表title
	 */
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			pojoList = secBaseAdmin.getDataListByKeys(keys);
			res.setDataList(pojoList);
			res.setMenuId("sv_sec");
			res.setHeadKeyList(ServiceAssistance.getListHead("sv_sec",SecInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}

	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByDaes(String parameter)
			throws ServiceException {
		return secBaseAdmin.getDataListByDaes(parameter);
	}

	public BasePojo getSecBaseInfoDataBySecCode(String cSecCode)
			throws ServiceException {
		
		return secBaseAdmin.getSecBaseInfoDataBySecCode(cSecCode);
	}

	/* (non-Javadoc)
	 * @see dataservice.service.ISecBaseInfoDataService#getShortDataList(java.lang.String[], java.lang.String, com.yss.framework.api.common.co.PageInation)
	 */
	@Override
	public ShortDataListPackage<SecShortPojo> getShortDataList(String[] types,String codelike,PageInation page)
			throws ServiceException {
		try {
			return secBaseAdmin.getShortDataList(types,codelike,page);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据证券品种信息或到期日期查询证券信息
	 * @param types  证券品种信息,dateStr  到期日期
	 * @return  证券信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataListByTypesAndDate(
			String[] types, String dateStr) {
		List<SecBase> list = secBaseAdmin.getDataListByTypes(types);
		Date endDate = DateUtil
				.stringtoDate(dateStr, DateUtil.LONG_DATE_FORMAT);
		/// 过滤到期的证券
		for (int i = list.size() - 1; i >= 0; i--) {
			if (DateUtil.dayDiff(list.get(i).getD_OFF_LIST(), endDate) > 0) {
				list.remove(i);
			}
		}
		return (List<K>) list;
	}

	/**
	 * 根据证券代码 查找证券信息
	 * @param pojoCode  证券代码
	 * @return 证券信息
	 */
	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		try {
			return secBaseAdmin.getDataByCode(pojoCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		SecBaseCache cache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		return cache.getKeyConvertMap(listKey);
	}
	
	/**
	 * 根据时间戳更新缓存数据
	 * @param timestamp 时间戳
	 * @return 缓存
	 */
	@Override
	public CacheData updateByTimestamp(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		if(timestamp == null || timestamp.equals("")){
			//张绍林。要直接从数据库里拿
			//BUG #119297 V4.5客户端缓存文件CacheData\10.1.12.11.8083.mdb存在重复数据
			//张绍林-20150924
			//tianpeng-20191008 BUG #278907 【证券缓存】目前系统启动时,证券缓存加载需要5到10分钟左右,导致系统核算等操作报错，需要等缓存加载完才能正常，需要优化，尽量优化到1分钟以内
			//list = secBaseAdmin.getAllDataListFromDb();
			list = secBaseAdmin.getAllDataListFromDbForMultithread();
			
		}
		else{
			list = secBaseAdmin.getDataListByTimestamp(timestamp);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
			/*
			 * STORY #48424 【易方达基金】新增证券品种作应税设置时提醒
			 * 缓存中刷新出新的证券：新增工作面板【增值税方案设置提醒面板】数据
			 * 在消息弹窗中新增关于【证券品种增值税方案设置提醒】的消息
			 * add by yangru 20171108
			 */
//			insertVatPlanSecVarRemindAndSendData(timestamp);
			//add by liyanjun 2018-8-7  BUG #213607 初始化债券每百元利息因刷新证券缓存和证券品种应税设置提醒，导致耗时长
			Thread thread = new Thread(new InsertVatPlanSecVarRemindThread(timestamp));
			thread.setName("ThransferThread_" + timestamp);
			thread.start();
		}
		else{
			// add by yh 2015.04.24
			//如果根据传入的时间戳没有查到时间戳之后的数据，返回的缓存信息信息中时间戳保持原样。保证连续性
			//因为在反审核操作的时候，虽然数据时间是比缓存时间戳新，但是getDataListByTimestamp是不查询未审核的数据，
			//	这个时候会把时间戳清空,导致下一次做数据普通更新时，会全部装载一遍缓存。
			data.setTimestamp(timestamp);
		}
		return data;
	}
	
	@OperLog(Ignore=true)
	public CacheData updateByTimestampPage(String timestamp,PageInation page){
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheData data = new CacheData();
		if(timestamp == null || timestamp.equals("")){
			//张绍林。要直接从数据库里拿
			//BUG #119297 V4.5客户端缓存文件CacheData\10.1.12.11.8083.mdb存在重复数据
			//张绍林-20150924
			list = secBaseAdmin.getDataListPageFromCache(page);
		}
		else{
			list = secBaseAdmin.getDataListByTimestampPageFromCache(timestamp, page);
		}
		
		data.setDataList(JsonUtil.toString(list));
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
		}
		else{
			data.setTimestamp(timestamp);
		}
		return data;
	}
	
	@OperLog(Ignore=true)
	public String getUpdateByTimestampCount(String timestamp){
		if(StringUtil.IsNullOrEmpty(timestamp)){
			return secBaseAdmin.getDataListCountCache();
		}else {
			return secBaseAdmin.getDataListByTimestampCount(timestamp);
		}
	}
	
	/**
	 * 得到证券信息的简短信息HashMap<证券代码, 证券名称>
	 * @return HashMap<证券代码, 证券名称>
	 */
	@Override
	public HashMap<String, String> getShortDataMap() throws ServiceException {
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			SecBaseCache cache =  CacheManager.getInstance().getCache(CacheGroup.SECBASE);
			for (SecBase secBase : cache.getCacheList()) {
				if(!map.containsKey(secBase.getC_SEC_CODE())){
					map.put(secBase.getC_SEC_CODE(), secBase.getC_SEC_NAME());
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return map;
	}

	/**
	 * 查询指数数据转封装成证券基本信息
	 */
	@Override
	public <K extends BasePojo> List<K> getAllIndexDataList()
			throws ServiceException {
		try {
			return secBaseAdmin.getAllIndexDataList();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据证券信息   获取  税率
	 * @param SecBase 证券信息
	 * @return  证券信息  含 税率
	 */
	@Override
	public SecBase getRate(SecBase secBase) throws ServiceException {
		try {
			return secBaseAdmin.getRate(secBase);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	@Override
	public SecBase getSec(SecBase secBase) throws ServiceException {
		try {
			//优化处理,先从缓存中获取
			SecBase result = null;
			SecBaseCache secBaseCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
			MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
			if(secBase.getC_MKT_CODE() != null && mktCache.getCacheByKey(secBase.getC_MKT_CODE()) != null) {
				result = secBaseCache.getCacheByKey(secBase.getC_SEC_CODE() +' ' 
						+mktCache.getCacheByKey(secBase.getC_MKT_CODE()).getC_MKT_NO());
				if (result != null) {
					return result;
				} else {
					result = secBaseAdmin.getSec(secBase);
				}
			} else {
				result = secBaseAdmin.getSec(secBase);
			}
			return result;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * BUG #232668 富国基金-【运维】存在私有债品时，外汇交易中心债券代码清算错误
	 */
	@Override
	public SecBase getSecPortCode(SecBase secBase,String portCode) throws ServiceException {
		SecBase privateSec = null;
		SecBase publicSec = null;
		try {
			List<SecBase> secList  =  secBaseAdmin.getSecByPortCode(secBase,portCode);
			if(!secList.isEmpty()){
				for(SecBase sec : secList){
					if(StringUtil.IsNullOrEmptyT(sec.getC_PORT_CODE())){
						publicSec = sec;
					}else if(sec.getC_PORT_CODE().contains(portCode)){
						privateSec = sec;
					}
				}
				if(null==privateSec){
					return  publicSec;
				}else{
					return  privateSec;
				}
			}
			return null;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public SecBase getSecByVarDur(SecBase secBase) throws ServiceException {
		try {
			return secBaseAdmin.getSecByVarDur(secBase);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void insert(List<SecBase> secBaseList) {
		try {
			secBaseAdmin.insert(secBaseList);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据证券代码获取证券信息
	 * @param secCode
	 * @return 证券信息
	 */
	@Override
	public BasePojo getSecCacheByCode(String secCode) {
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		return secCache.getCacheByKey(secCode);
	}

	@Override
	public List<SecBase> dbjxSecs(List<String> secCodeList) {
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		List<SecBase> secList = new ArrayList<SecBase>();
		for (String secCode : secCodeList) {
			SecBase sec = secCache.getCacheByKey(secCode);
			if (!StringUtil.IsNullOrEmpty(sec.getC_DV_ISSUE()) && sec.getC_DV_ISSUE().equals("HSFS_DB")) {
				secList.add(sec);
			}
		}
		return secList;
	}
	
	@Override
	public SecBase getDataBySecMktCodeAndMktCode(String secMktCode,
			String mktCode) {
		SecBase sec = null;
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		MktCache mktCache  = CacheManager.getInstance().getCache(CacheGroup.MKT);
		if(null != mktCache.getCacheByKey(mktCode)) {
			sec = secCache.getCacheByKey(secMktCode+" "+mktCache.getCacheByKey(mktCode).getC_MKT_NO());
		}
		return sec;
		
	}

//	/**
//	* By Jinghehe 2017-8-5 BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范，这块代码逻辑从ISecBaseInfoDataService 迁移到收支结转IPaySettCopyService data服务
//	 * 检查能否生成红利投资交易数据
//	 * add by liyanjun 2016-8-18 STORY24572 需要可以对不同的货币基金设置对应的红利转投资提醒数据，然后在指定日期进行提醒
//	 * 检查逻辑：根据理财产品信息中设置的货基分投日期，符合设定的日期时就产生红利转投资业务
//	 * @param accDate 核算日期
//	 * @param secBase 理财品种信息
//	 * @return
//	 */
//	@Override
//	public boolean isAutoBuildHltz(Date accDate, SecBase secBase) {	
//		Date tradeDate = null;
//		try {
//			SecBaseLcService secBaseLcService = new SecBaseLcService();
////			PaySettService paySettService = new PaySettService();
//			tradeDate = secBaseLcService.calcDateAtCycle(accDate, secBase.getC_DV_ISSUE(),
//					-1, 0);
//			BaseMktCache mktCache = CacheManager.getInstance().getCache(
//					CacheGroup.BASEMKT);
//			Mkt mkt = mktCache.getCacheByKey(secBase.getC_MKT_CODE());
//			if(mkt == null) return false;
//			//计算偏移日期 引用uco   注释掉
////			tradeDate = paySettService.calcOffsetDate(tradeDate, mkt.getC_HDAY_CODE(),
////					secBase.getC_DV_ASSURE(), secBase.getN_PRICE_ISSUE()
////							.intValue());
////			
////			if (tradeDate == null || YssFun.dateDiff(accDate, tradeDate) != 0)
////				return false;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return true;
//	}
	
	/**
	 * add by zhd 2016-09-20
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 得到所有实际所属证券为空的证券
	 */
	public <K extends BasePojo> List<K> getDataListBySjsszq(String[] types) {
		return secBaseAdmin.getDataListBySjsszq(types);
	}
	
	// STORY #33007 【南方基金】【紧急】存放品种信息机构名称字段只显示商业银行的信息
	// 个性化获取全部交易证券
	public <K extends BasePojo> List<K> getDataListByIndiv()
			throws ServiceException {
		try {
			return secBaseAdmin.getDataListByIndiv();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	
	/**
	 * 检查某证券是否存在持仓
	 * add by liyanjun 2016-8-29 STORY31079 修改咨询信息品种有持仓的情况下给予提醒
	 * @param secCode 证券代码
	 * @return
	 */
	@Override
	public String isExistsStock(String secCode) {
		try {
			return secBaseAdmin.isExistsStock(secCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public <T extends BasePojo> List<T> queryByIds(String ids) {
		return secBaseAdmin.queryByIds(ids, SecBase.class);
	}
	
	@Override
	public String getMktNo(){
		return secBaseAdmin.getMktNo();
	}

	@Override
	public CacheData updateByIds(String ids){
		List<SecBase> dataList = secBaseAdmin.queryByIds(ids, SecBase.class);
		CacheData data = new CacheData();
		data.setDataList(JsonUtil.toString(dataList));
		return data;
	}
	
    //add by liyanjun 2018-8-7  BUG #213607 初始化债券每百元利息因刷新证券缓存和证券品种应税设置提醒，导致耗时长
	// 定义执行任务线程
	class InsertVatPlanSecVarRemindThread implements Runnable{
		private String timestamp = null;
		InsertVatPlanSecVarRemindThread(String timestamp){
			this.timestamp = timestamp;
		}
		
		@Override
		public void run() {
			try {
				// 净值确认或者反确认成功后，对清算中间表数据进行迁移。
				insertVatPlanSecVarRemindAndSendData(timestamp);
			} catch (Exception ex) {
			}
		}
	}
	
	/**
	 * STORY #48424 【易方达基金】新增证券品种作应税设置时提醒
	 * 缓存中刷新出新的证券：新增工作面板【增值税方案设置提醒面板】数据
	 * 在消息弹窗中新增关于【证券品种增值税方案设置提醒】的消息
	 * add by yangru 20171108
	 */
	private void insertVatPlanSecVarRemindAndSendData(String timestamp){
		IVatPlanSecVarPanelService vatPlanSecVarPanelService = 
				YssServiceFactory.getInstance().createService(IVatPlanSecVarPanelService.class);
		vatPlanSecVarPanelService.setCurrUser(this.currUserCode);
		vatPlanSecVarPanelService.saveAndSendVatPlanSecVarPanelData(timestamp);
	}

	@Override
	public void setCurrUser(String userCode) {
		this.currUserCode = userCode;
	}
	
	
	
	/**
	 * 根据时间戳更新数据
	 * BUG #227317 系统启动时内存溢出
	 * 原更新逻辑中有一个转json的逻辑，证券缓存对象很大，导致在转化过程中系统内存溢出,经分析，该转化无实际意义，消耗系统资源，在此进行优化；
	 * add by sunyanlin 20181101
	 */
	@Override
	public CacheDataExtend updateByTimestampNew(String timestamp) {
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = df.format(d);
		
		List<BasePojo> list = null;
		//DOTO 需要补充按时间戳取数逻辑
		CacheDataExtend data = new CacheDataExtend();
		if(timestamp == null || timestamp.equals("")){
			//张绍林。要直接从数据库里拿
			//BUG #119297 V4.5客户端缓存文件CacheData\10.1.12.11.8083.mdb存在重复数据
			//张绍林-20150924
			//tianpeng-20191008 BUG #278907 【证券缓存】目前系统启动时,证券缓存加载需要5到10分钟左右,导致系统核算等操作报错，需要等缓存加载完才能正常，需要优化，尽量优化到1分钟以内
			//list = secBaseAdmin.getAllDataListFromDb();
			list = secBaseAdmin.getAllDataListFromDbForMultithread();
		}
		else{
			list = secBaseAdmin.getDataListByTimestamp(timestamp);
		}
		
		//data.setDataList(JsonUtil.toString(list));
		data.setCacheDataList(list);
		if(list != null && list.size() > 0){
			data.setTimestamp(t);
			/*
			 * STORY #48424 【易方达基金】新增证券品种作应税设置时提醒
			 * 缓存中刷新出新的证券：新增工作面板【增值税方案设置提醒面板】数据
			 * 在消息弹窗中新增关于【证券品种增值税方案设置提醒】的消息
			 * add by yangru 20171108
			 */
//			insertVatPlanSecVarRemindAndSendData(timestamp);
			//add by liyanjun 2018-8-7  BUG #213607 初始化债券每百元利息因刷新证券缓存和证券品种应税设置提醒，导致耗时长
			Thread thread = new Thread(new InsertVatPlanSecVarRemindThread(timestamp));
			thread.setName("ThransferThread_" + timestamp);
			thread.start();
		}
		else{
			// add by yh 2015.04.24
			//如果根据传入的时间戳没有查到时间戳之后的数据，返回的缓存信息信息中时间戳保持原样。保证连续性
			//因为在反审核操作的时候，虽然数据时间是比缓存时间戳新，但是getDataListByTimestamp是不查询未审核的数据，
			//	这个时候会把时间戳清空,导致下一次做数据普通更新时，会全部装载一遍缓存。
			data.setTimestamp(timestamp);
		}
		return data;
	}
	
	/**
	 * 检查某证券是否存在持仓-凭证
	 * add by zengpenglin STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	@Override
	public String isExistsAct(String secCode){
		try {
			return secBaseAdmin.isExistsAct(secCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 检查某证券是否存在持仓-stock
	 * add by zengpenglin STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	@Override
	public String isExistsStk(String secCode){
		try {
			return secBaseAdmin.isExistsStk(secCode);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public BasePojo getSecBaseInfoDataBySecCodeFromDb(String cSecCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return secBaseAdmin.getSecBaseInfoDataBySecCodeFromDb(cSecCode);
	}
	
	public List<SecBase> getSecBaseListBySecCodeListFromDb(List<String> secCode){
		return secBaseAdmin.getSecBaseListBySecCodeListFromDb(secCode);
	}
	
	public int getCountFromDb( ) {
		return secBaseAdmin.getCountFromDb();
	}
	
	
	@Override
	public List<BasePojo> getDataListByTypes(String[] types, String paraValue) {
		List<BasePojo> baseList = new ArrayList<BasePojo>();
		List<SecBase> secBaseList = this.getDataListByTypes(types);
		if(!StringUtil.IsNullOrEmptyT(paraValue))
		for (SecBase basePojo : secBaseList) {
			if (basePojo.getC_SEC_CODE().indexOf(paraValue) >= 0
					|| basePojo.getC_SEC_NAME().indexOf(paraValue) >= 0
					|| basePojo.getC_SEC_MKT_CODE().indexOf(paraValue) >= 0) {
				baseList.add(basePojo);
				
			}			
			if(baseList.size()==400){
				break;
			}
		}
       
		return baseList;
	}
	
	@Override
	public List<String> UpdateDifferent(String codes) {
		List<String> codesList;
		List<String> dbSecCodeList= new ArrayList<String>();
		List<String> resSecCodeList= new ArrayList<String>();
		HashMap<String,String> codesMap = new HashMap<String, String>();
		try {
			if (StringUtil.IsNullOrEmpty(codes)) {
				codesList = new ArrayList<String>();
			}else {
				codes = StringUtil.delLastSplitMark(codes, ",");
				codesList = StringUtil.split(codes, ",");		
			}
			for (String code : codesList) {
				codesMap.put(code, "");
			}
			SecBaseCache cache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);			
			if (null != cache) {
				List<SecBase> cacheList = cache.getCacheList();
				for (SecBase object : cacheList) {
					dbSecCodeList.add(object.getC_SEC_CODE());
				}
				
				for (String code : dbSecCodeList) {
					if (!codesMap.containsKey(code)) {
						resSecCodeList.add(code);
					}
				}
				
				//在结果集末尾添加缓存的时间戳，传给前台进行更新处理
			   if (resSecCodeList.size() > 0 && null != cache.getTimestamp()) {
				   resSecCodeList.add(cache.getTimestamp());
			   }else if(resSecCodeList.size() > 0 && null == cache.getTimestamp()) {
				    Date d = new Date(System.currentTimeMillis());
				    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    String t = df.format(d);
				    resSecCodeList.add(t);
			   }
			}
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		codesMap.clear();
		//System.out.println("差异数量"+resSecCodeList.size());
		return resSecCodeList;
	}

	@Override
	public CacheData updateByCodes(String codes) {
		List<SecBase> list = secBaseAdmin.queryByCodes(codes);
		CacheData data = new CacheData();
		data.setDataList(JsonUtil.toString(list));
		return data;
	}
	
}
