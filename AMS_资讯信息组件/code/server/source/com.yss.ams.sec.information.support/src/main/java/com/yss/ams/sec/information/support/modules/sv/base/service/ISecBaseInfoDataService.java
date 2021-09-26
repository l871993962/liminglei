package com.yss.ams.sec.information.support.modules.sv.base.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecShortPojo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetDataListByTypesAndDateVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetDataListByTypesVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetSecPortCodeVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.UpdateByTimestampPageVo;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.CacheDataExtend;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ShortDataListPackage;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 证券基本信息数据接口
 * @author 马向峰
 *
 */
@RestfulSupported
@GenericPojo(pojo=SecBase.class)
public interface ISecBaseInfoDataService extends IDataService,
		IControlDataService, IKeyConvertDataService,IDataServiceForCache {
	public <K extends BasePojo> List<K> getDataListByTypesAndMkt(String[] types)
			throws ServiceException;

	/**
	 * 根据核算元素查数据
	 * 
	 * @param <K>
	 *            POJO类型
	 * @param parameter
	 *            参数
	 * @return 证券列表
	 */
	public <K extends BasePojo> List<K> getDataListByDaes(String parameter)
			throws ServiceException;
	
	
	public BasePojo getSecBaseInfoDataBySecCode(String cSecCode) throws ServiceException;
	
	public BasePojo getSecBaseInfoDataBySecCodeFromDb(String cSecCode) throws ServiceException;
	
	public int getCountFromDb();
	
	public List<SecBase> getSecBaseListBySecCodeListFromDb(List<String> secCode);
	
	public BasePojo getSecCacheByCode(String secCode);
	
	/**
	 * 获取所有数据列表
	 * 
	 * @param <K>
	 * @return
	 * @throws Exception
	 */
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException;
	
	/**
	 * 
	 * @param secBase
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSecByVarDur(SecBase secBase) throws ServiceException;
	
	@Override
	@LinkControllerMethod(value = "getDataListByTypes" ,arguTypes={String[].class})
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
	throws ServiceException;
	
	/**查询精简证券信息
	 * @param types 证券类型
	 * @param like 代码
	 * @return 精简证券信息
	 * @throws ServiceException
	 */
	public ShortDataListPackage<SecShortPojo> getShortDataList(String[] types,String like, PageInation page) throws ServiceException;
	
	/**
	 * 根据证券品种和到期日期查询证券信息
	 * @param types 证券品种
	 * @param dateStr 到期日期
	 * @return 证券信息列表
	 * @author liuxiang
	 * @date 2014/1/22
	 */
	@LinkControllerMethod(value="getDataListByTypesAndDate",arguTypes = GetDataListByTypesAndDateVo.class)
	public <K extends BasePojo> List<K> getDataListByTypesAndDate(@LinkControllerMethodArgu("types")String[] types, @LinkControllerMethodArgu("dateStr")String dateStr);
	

	/**
	 * 取得所有数据(只包含代码和名称)
	 * @author liuxiang
	 * @date 2014-6-10
	 * @return
	 */
    public HashMap<String, String> getShortDataMap() throws ServiceException;
    
    
	/**
	 * By Jinghehe 2014-8-4
	 * 获取所有指数信息转换成 证券基本信息
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getAllIndexDataList()
			throws ServiceException;
	
	
	/**
	 * 彭博清算证券基本信息添加新数据
	 * By Jinghehe 2014-8-8
	 * @param list
	 */
	public void insert(List<SecBase> list);
	
	/**
	 * @param secBase
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getRate(SecBase secBase) throws ServiceException;
	
	/**
	 * 
	 * @param secBase
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSec(SecBase secBase) throws ServiceException;
	
	/**
	 * BUG #232668 富国基金-【运维】存在私有债品时，外汇交易中心债券代码清算错误
	 * @param secBase
	 * @param portCode
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="getSecPortCode",arguTypes = GetSecPortCodeVo.class)
	public SecBase getSecPortCode(@LinkControllerMethodArgu("secBase")SecBase secBase,@LinkControllerMethodArgu("portCode")String portCode) throws ServiceException;
	
	/**
	 * 获取单笔计息证券 
	 * @param secCodeList
	 * @return
	 */
	public List<SecBase> dbjxSecs(List<String> secCodeList);    
	
	/**
	 * 获取证券信息(根据上市代码和市场代码)  
	 * by xhb 20151216  STORY #27530 
	 */
	public SecBase getDataBySecMktCodeAndMktCode(String secMktCode, String mktCode); 
	
	/**
	 * 根据分页获取数据
	 * @param timestamp
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="updateByTimestampPage",arguTypes = UpdateByTimestampPageVo.class)
	public CacheData updateByTimestampPage(@LinkControllerMethodArgu("timestamp")String timestamp,@LinkControllerMethodArgu("page")PageInation page);
	
	/**
	 * 根据时间戳获取数据量
	 * @param timestamp
	 * @return
	 */
	public String getUpdateByTimestampCount(String timestamp);
	
	/**
	 * add by zhd 2016-09-20
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 得到所有实际所属证券为空的证券
	 * @param types
	 * @return
	 */
	public <K extends BasePojo> List<K> getDataListBySjsszq(String[] types);
	
	/**
	 * 存放业务新增页面
	 * 交易证券界面
	 * @author guohui
	 * @date 2016-8-25
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByIndiv() throws ServiceException;
	
//	/**
//	* By Jinghehe 2017-8-5 BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范，这块代码逻辑从ISecBaseInfoDataService 迁移到收支结转IPaySettCopyService data服务
//	 * 检查能否生成红利投资交易数据
//	 * add by liyanjun 2016-8-18 STORY24572 需要可以对不同的货币基金设置对应的红利转投资提醒数据，然后在指定日期进行提醒
//	 * @param accDate 核算日期
//	 * @param secBase 理财品种信息
//	 * @return
//	 */
//	public boolean isAutoBuildHltz(Date accDate, SecBase secBase);
	
	/**
	 * 检查某证券是否存在持仓
	 * add by liyanjun 2016-8-29 STORY31079 修改咨询信息品种有持仓的情况下给予提醒
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsStock(String secCode);
	
	/**
	 * 获取市场代码
	 * @return
	 * @throws ServiceException
	 */
	public String getMktNo() throws ServiceException;
	
	/**
	 * 检查能否生成红利投资交易数据
	 * add by liyanjun 2016-8-18 STORY24572 需要可以对不同的货币基金设置对应的红利转投资提醒数据，然后在指定日期进行提醒
	 * @param accDate 核算日期
	 * @param secBase 理财品种信息
	 * @return
	 *//*
	public boolean isAutoBuildHltz(Date accDate, SecBase secBase);
	*/
	/**
	 * BUG #227317 系统启动时内存溢出
	 * 使用新的 缓存加载逻辑，原逻辑中的转json会导致内存溢出
	 * add by sunyanlin 20181101
	 */
	public CacheDataExtend updateByTimestampNew(String timestamp);
	
	/**
	 * 检查某证券是否存在持仓-凭证
	 * add by zengpenglin STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsAct(String secCode);
	
	/**
	 * 检查某证券是否存在持仓-stock
	 * add by zengpenglin STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsStk(String secCode);
	
	/**
	 * 根据ids获取证券信息（用于前台获取缓存）<br>
	 * BUG #167994 嘉实基金—证券信息加载时间过长，影响客户做账效率 雷建华 20170803
	 * @Title updateByIds 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年8月3日下午4:13:49
	 * @param ids
	 * @return CacheData
	 */
	public CacheData updateByIds(String ids);
	
	/**
	 * 获取当前用户code
	 */
	public void setCurrUser(String userCode);
	
	/**
	 * BUG #268715 【融通基金】-各个界面债券选择控件，债券信息加载不出来，右下角刷新缓存也不行，需要再次反审核债券再审核债券品种信息
	 * @param types
	 * @param paraValue
	 * @return
	 */
	@LinkControllerMethod(value = "getDataListByTypes" ,arguTypes=GetDataListByTypesVo.class)
	public List<BasePojo>  getDataListByTypes(@LinkControllerMethodArgu("types")String[] types, @LinkControllerMethodArgu("paraValue")String paraValue);
	

	/**
	 * 传递客户端缓存codes字符串到后台，返回差异数据至前端缓存
	 * @param codes
	 * @return
	 */
	public List<String> UpdateDifferent(String codes);
	
	/**
	 * 根据codes获取证券信息（用于前台获取缓存）
	 */
	public CacheData updateByCodes(String codes);
	
}
