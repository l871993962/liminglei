package com.yss.ams.sec.information.modules.sv.base.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecShortPojo;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ShortDataListPackage;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.util.StringUtil;

/**
 * 证券信息数据服务类
 * @author 马向峰 拆分 20170627
 *
 */
public class SecBaseInfoDataAdmin extends BaseAdmin {
	private SecBaseDao svcDao = null;
	private SecBaseCache secCache = null; // 证券信息的缓存byleeyu20130819

	public SecBaseInfoDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new SecBaseDao(pool, sqlBuilder);		
	}

	/**
	 * BUG #278907 【证券缓存】目前系统启动时,证券缓存加载需要5到10分钟左右,导致系统核算等操作报错，需要等缓存加载完才能正常，需要优化，尽量优化到1分钟以内
	 * @ClassName ExecutorThread
	 * @Description callable线程，
	 */
	public class ExecutorThread implements Callable<List<SecBase>>{

		PageInation pageInation = null;
		public ExecutorThread(PageInation pageInation){
			this.pageInation = pageInation;
		}
		
		@Override
		public List<SecBase> call() throws Exception {
			int count = 0;
			//调用的服务
			return (List<SecBase>) svcDao.getAllDataList(pageInation);

		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList()
			throws ServiceException {
		// add by yh 2015.05.12 首先从缓存中取值，如果缓存没有，再从数据库装载
 		secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
 		//---------------------2015-6-30 刘志勇 STORY #24186 将系统本地化缓存改为集中式缓存  原代码在使用集中缓存时出现死循环---------------------//
 		
/*		if(null == secCache ){
			return (List<T>) svcDao.getAllDataList();
		}
		else{
			return (List<T>) secCache.getCacheList();
		}  
		*/
		if(secCache!=null && secCache.getTimestamp()!=null){
			return (List<T>) secCache.getCacheList();
		}else{
			return (List<T>) svcDao.getAllDataList();
		}
	    //-----------------------------------------------------------------------------------------//
	}
	
	/**
	 * 根据证券的 币种代码、交易市场代码、证券品种代码、资产负债的品种 查询证券信息
	 * @param filter
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataList(SecBase filter)
			throws ServiceException {
		return (List<T>) svcDao.getAllDataList(filter);
	}

	/**
	 * 从缓存中取出证券信息
	 * @param code	
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> T getDataByCode(String code)
			throws ServiceException {
		// By Jinghehe 2014-1-27 从缓存中取数据 
		secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		return (T) secCache.getCacheByKey(code);
		//return (T) svcDao.getDataByCode(code);
	}

	/**
	 * 该方法 dao层已注释,改为从缓存获取数据
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypes(String[] types)
			throws ServiceException {
		//return (List<T>) svcDao.getDataListByTypes(types);
		secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		if(null == secCache) {
			return (List<T>)svcDao.getDataListByKeys(types);
		}
		return (List<T>)secCache.getDataListBySecVars(types); // 这里改为从缓存中获取数据byleeyu20130819
	}

	/**
	 * 从缓存中获取证券信息
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		//return svcDao.getKeyConvertMap(null);
		secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		return secCache.getKeyConvertMap(); // 这里改为从缓存中获取数据byleeyu20130819
	}

	/**
	 * 增加一个按品种类型来转换代码的方法
	 * 
	 * @param lstSecVar
	 *            品种列表
	 * @return 代码名称列表
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap(ArrayList<String> lstSecVar)
			throws ServiceException {
		return svcDao.getKeyConvertMap(lstSecVar);
	}

	/**
	 * dao层查询部分被注释  
	 * 	返回空集合
	 * @param types
	 * @return 
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByKeys(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByKeys(types);
	}

	/**
	 * 根据交易市场代码查询
	 * @param types	交易市场代码
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByTypesAndMkt(String[] types)
			throws ServiceException {
		return (List<T>) svcDao.getDataListByTypesAndMkt(types);
	}

	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> getDataListByDaes(String parameter)
			throws ServiceException {
		return (List<K>) svcDao.getDataListByDaes(parameter);
	}

	/**
	 * 根据证券代码查询证券信息
	 * @param cSecCode
	 * @return
	 */
	public BasePojo getSecBaseInfoDataBySecCode(String cSecCode) {	
		return svcDao.getSecBaseInfoDataBySecCode(cSecCode);
	}
	
	public BasePojo getSecBaseInfoDataBySecCodeFromDb(String cSecCode) {	
		return svcDao.getSecBaseInfoDataBySecCodeFromDb(cSecCode);
	}
	
	public List<SecBase> getSecBaseListBySecCodeListFromDb(List<String> secCode){
		return svcDao.getSecBaseListBySecCodeListFromDb(secCode);
	}

	public int getCountFromDb() {	
		return svcDao.getCountFromDb();
	}
	
	
	/**
	 * 查询精简证券信息
	 * @param types 证券品种
	 * @param codelike 证券代码
	 * @param page 分页信息
	 * @return 精简证券信息集合
	 */
	public ShortDataListPackage<SecShortPojo> getShortDataList(String[] types, String codelike,PageInation page) {
		return svcDao.getShortDataList(types, codelike, page);
	}

	/**
	 * 根据时间戳查询
	 * @param timestamp
	 * @return
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		return svcDao.getDataListByTimestamp(timestamp);
	}

	/**
	 * 查询指数数据转封装成证券基本信息 
	 * By Jinghehe 2014-8-4
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllIndexDataList() throws ServiceException {
		return (List<T>) svcDao.getAllIndexDataList();
	}

	/**
	 * 获取税率
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getRate(SecBase secBase) throws ServiceException {
		return svcDao.getRate(secBase);
	}

	/**
	 * 获取结算帐户信息
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSec(SecBase secBase) throws ServiceException {
		//STORY #38149 需求上海-[光大证券]金融资产管理平台V4.5[高]2017011901(银行间质押式回购基本信息改造)
		if (!"".equals(secBase.getC_DV_VAR_DUR())) {
			return svcDao.getSecByLimitDays(secBase);
		}else {
			return svcDao.getSec(secBase);
		}
		//return svcDao.getSec(secBase);
	}
	
	/**
	 * BUG #232668 富国基金-【运维】存在私有债品时，外汇交易中心债券代码清算错误
	 * @param secBase
	 * @param portCode
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getSecByPortCode(SecBase secBase,String portCode) throws ServiceException {
		return svcDao.getSecByPortCode(secBase,portCode);
	}

	/**
	 * 根据证券品种、交易市场、回购期限获取品种信息
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSecByVarDur(SecBase secBase) throws ServiceException {
		return svcDao.getSecByVarDur(secBase);
	}
	
	public void insert(List<SecBase> secBaseList) throws ServiceException {
		svcDao.insert(secBaseList);
	}
	
	public BasePojo getSecCacheByCode(String secCode) {
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		return secCache.getCacheByKey(secCode);
	}

	/**
	 * 从数据库获取证券品种信息
	 * BUG #119297 V4.5客户端缓存文件CacheData\10.1.12.11.8083.mdb存在重复数据
	 * 张绍林-20150924
	 **/
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataListFromDb() {
		return (List<T>) svcDao.getAllDataList();
	}
	
	/**
	 * 从数据库获取证券品种信息
	 * BUG #278907 【证券缓存】目前系统启动时,证券缓存加载需要5到10分钟左右,导致系统核算等操作报错，需要等缓存加载完才能正常，需要优化，尽量优化到1分钟以内
	 * tianpeng-20191008
	 **/
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getAllDataListFromDbForMultithread() {
		List<SecBase> secBaseList = new ArrayList<SecBase>();
		//是否开启多线程
		boolean runThread = true;
		int count = 0;
		int splitCnt = 20;// 分组数量
		//线程总数
		int threadCnt = 0;
		// 线程池取CPU数乘以2 +1
		int threadPoolCnt = Runtime.getRuntime().availableProcessors() * 2 +1;
		
		//分页数量动态判断,根据证券基本信息的数据量确定线程数,确定好分页条件
		List<PageInation> pageInationList = pageInationDynamic();
		
		if (!runThread || threadPoolCnt < 4 || pageInationList.size() == 1){
			//如果证券数量小于线程分组数量等，就不要启线程
			return (List<T>) svcDao.getAllDataList();
		} else{
			List<Future<List<SecBase>>> futureList = new ArrayList<Future<List<SecBase>>>();
			ExecutorService threadExe = Executors.newFixedThreadPool(threadPoolCnt);	
		    try{
				for (PageInation pageInation : pageInationList) {
					count++;

					ExecutorThread exeThread = new ExecutorThread(pageInation);
					futureList.add(threadExe.submit(exeThread));
					threadCnt++;

				}

				for (Future<List<SecBase>> future : futureList) {
					try{
						List<SecBase>  secBaseListOnePage= (List<SecBase>) future.get();
					    secBaseList.addAll(secBaseListOnePage);
					}catch(Exception ex){
						threadExe.shutdown();
						throw ex;
					}
				}
				
			}catch(Exception ex){
				threadExe.shutdown();
//				ex.printStackTrace();
			}finally{
				threadExe.shutdown();
			}
		    
		}
		
		return (List<T>) secBaseList;
	}
	
	/* 解决 BUG #127360::YSS-版本自动打包报错 */
	public List<BasePojo> getSecCache(HashMap<String, String> paraMap) {
		return svcDao.getSecCache(paraMap);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListPageFromDb(PageInation page){
		return (List<T>) svcDao.getDataListPage(page);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListPageFromCache(PageInation page){
		SecBaseCache secBaseCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		int begin = (page.getCurrPage() - 1) * page.getPageSize();
		int end = begin+page.getPageSize();
		if(end>page.getTotalNum()){
			end =page.getTotalNum();
		}
		return (List<T>) secBaseCache.getCacheList(begin,end);
	}
	
	public List<BasePojo> getDataListByTimestampPageFromCache(String timestamp,PageInation page) {
		List<BasePojo> list = new ArrayList<BasePojo>();
		SecBaseCache secBaseCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		
		// 根据时间戳，分页查询证券内码
		List<String> secCodesList =  svcDao.getSecCodeDataListByTimestampPage(timestamp, page);
		
		// 遍历证券内码集合，从缓存中获取
		for (String secCode : secCodesList) {	
			//BUG #349423::【招商基金】【0831】【性能】系统空闲时还能检测到CPU使用率在70%以上需优化证券缓存获取逻辑
			SecBase sec = secBaseCache.getCacheByKey(secCode);
			if (sec != null) {
				list.add(sec);
			}
		}
		
		return list;
	}
	
	public List<BasePojo> getDataListByTimestampPage(String timestamp,PageInation page) {
		return svcDao.getDataListByTimestampPage(timestamp, page);
	}
	
	public String getDataListCount(){
		return Integer.toString(svcDao.getDataListCount());
	}
	public String getDataListCountCache(){
		return Integer.toString(CacheManager.getInstance().getCache(CacheGroup.SECBASE).getCacheListCount());
	}
	
	public String getDataListByTimestampCount(String timestamp){
		return Integer.toString(svcDao.getUpdateByTimestampCount(timestamp));
	}

	/**
	 * add by zhd 2016-09-20
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 得到所有实际所属证券为空的证券
	 * @param types
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListBySjsszq(String[] types) {
		return (List<T>) svcDao.getDataListBySjsszq(types);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByIndiv()
			throws ServiceException {
		return (List<T>) svcDao.getDataListByIndiv();
	}
	
	/**
	 * 检查某证券是否存在持仓
	 * add by liyanjun 2016-8-29 STORY31079 修改咨询信息品种有持仓的情况下给予提醒
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsStock(String secCode) {
		return svcDao.isExistsStock(secCode);
	}
	
  	/**
	 * 检查某证券是否存在持仓-凭证
	 * add by zengpenglin STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsAct(String secCode) {
		return svcDao.isExistsAct(secCode);
	}
	
  	/**
	 * 检查某证券是否存在持仓-stock
	 * add by zengpenglin STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsStk(String secCode) {
		return svcDao.isExistsStk(secCode);
	}
	
	/**
	 * 根据IDs获取数据
	 * STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	 * @Title queryByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月29日下午6:55:31
	 * @param ids
	 * @param clazz
	 * @return List<T>
	 */
	public <T extends BasePojo> List<T> queryByIds(String ids, Class<?> clazz) {
		return svcDao.queryByIds(ids, clazz);
	}
	
	/**
	 * 获取中银行间交易市场的市场代码
	 * @return
	 */
	public String getMktNo(){
		return svcDao.getMktNo();
	}
	
	/*BUG #278907 【证券缓存】目前系统启动时,证券缓存加载需要5到10分钟左右,导致系统核算等操作报错，需要等缓存加载完才能正常，需要优化，尽量优化到1分钟以内
	 *分页动态判断
	 *
	 */
	public List<PageInation> pageInationDynamic(){
		List<PageInation> pageInationList= new ArrayList<PageInation>();
		int pageSize = 5000;
		int totalNum = 0;
		int currPage = 1;
		int pageCount = 0;
		
		totalNum = svcDao.getSecBaseCacheTotalNum();
		pageSize = pageSizeDynamic(totalNum);
		
		pageCount = totalNum / pageSize;		
		if(totalNum%pageSize>0){
			pageCount ++;
		}
		
		for(int i = 1 ; i <= pageCount ; i++){
			currPage = i;
			
			PageInation pageInation = new PageInation();
			pageInation.setCurrPage(currPage);
			pageInation.setPageCount(pageCount);
			pageInation.setPageSize(pageSize);
			pageInation.setTotalNum(totalNum);
			
			pageInationList.add(pageInation);
		}

		return pageInationList;
	}
	
	/*BUG #278907 【证券缓存】目前系统启动时,证券缓存加载需要5到10分钟左右,导致系统核算等操作报错，需要等缓存加载完才能正常，需要优化，尽量优化到1分钟以内
	 *分页数量动态判断
	 *1到2W个券以内,分页数量为2000;2W到5W个券以内分页数量为5000,5w到10W以内,分页数量为1W,10W到20w个券,分页数量为2W,20W以上,分页数量为2W;
	 */
	public int pageSizeDynamic(int totalNum){
		int cnt = 5000;
		if (totalNum <= 20000){
			cnt = 2000;
		}
		else if(totalNum>20000 &&totalNum<=50000){
			cnt = 5000;
		}
		else if(totalNum>50000 &&totalNum<=100000){
			cnt = 10000;
		}
		else if(totalNum>100000 &&totalNum<=200000){
			cnt = 20000;
		}
		else if(totalNum>200000 ){
			cnt = 20000;
		}
		
		return cnt;
	}
	
	public List<SecBase> queryByCodes(String codes) {
		BaseCache cache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		List<String> codeList;
		List<SecBase> reslist = new ArrayList<SecBase>();
		try {
			codeList = StringUtil.split(codes, ",");
			
			for (String key : codeList) {
				Object obj = cache.getCacheByKey(key);
				if(obj != null){
					reslist.add((SecBase)obj);
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return reslist;
	}
}
