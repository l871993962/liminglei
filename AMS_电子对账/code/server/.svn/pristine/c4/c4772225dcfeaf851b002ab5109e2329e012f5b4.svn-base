package com.yss.uco.elecreco.er.reverse.compare.ye.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.ReflectionUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;
import com.yss.uco.elecreco.er.eryeb.pojo.ErYeb;
import com.yss.uco.elecreco.er.generate.yeb.GeneYEBDataService;
import com.yss.uco.elecreco.er.reverse.compare.common.IgnoreManager;
import com.yss.uco.elecreco.er.reverse.compare.common.ZbMapManager;
import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.service.IDataCompareService;
import com.yss.uco.elecreco.er.reverse.compare.service.impl.DataCompareService;
import com.yss.uco.elecreco.er.reverse.compare.ye.dao.ErYebItemColumnName;
import com.yss.uco.elecreco.er.reverse.compare.ye.dao.ErYebOutItemColumnName;
import com.yss.uco.elecreco.er.reverse.compare.ye.dao.YeResRelaItemColumnName;
import com.yss.uco.elecreco.er.reverse.compare.ye.dao.YeReveResultItemColumnName;
import com.yss.uco.elecreco.er.reverse.cons.ReveDzCons;
import com.yss.uco.elecreco.er.reverse.manager.result.pojo.ReveResult;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.pojo.ResRela;
import com.yss.uco.elecreco.er.reverse.map.assmap.pojo.AssMap;
import com.yss.uco.elecreco.er.reverse.map.kmmap.pojo.KmMap;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaRecord;
import com.yss.uco.elecreco.er.reverse.out.eryeb.pojo.ErYebOut;
import com.yss.uco.elecreco.support.bean.ElecRela;

public class YeDataCompareService extends DataCompareService implements IDataCompareService {
	public YeDataCompareService(DataCompareDao dataCompareDao) {
		super(dataCompareDao);
	}

	private Map<String,BasePojo> outYes = new HashMap<String, BasePojo>();
	/**
	 * key:科目代码
	 */
	private Map<String,BasePojo> innerYes = new HashMap<String, BasePojo>();

	protected void initOutData(String portCode, String gzDate, String tghCode, String rptType) throws Exception {
		outYes = this.dataCompareDao.getOutData(portCode,gzDate,tghCode,rptType);
	}
	/**
	 * 加载内部数据
	 * @param sn
	 * @throws Exception
	 */
	protected void initInnerData(String sn) throws Exception {
		Map<String, BasePojo> datas = this.dataCompareDao.getInnerData(sn);
		if(datas == null || datas.size() == 0)
		{
			throw new Exception("内部数据没有生成！");
		}
		for(BasePojo pojo : datas.values())
		{
			ErYeb gz = (ErYeb) pojo;
			String code = gz.getC_KM_CODE().trim();
			//替换本方科目代码后面的证券代码
			if(isTransIsin() && haveSecCode(code))
			{
				int i = code.lastIndexOf(".");
				String isinCode = this.getISINCode(code.substring(i+1));
				if(isinCode!= null && !StringUtil.IsNullOrEmptyT(isinCode))
				{
					code = code.substring(0, i+1)+isinCode;
				}
			}
			this.innerYes.put(code, gz);
		}
	}
	/**
	 *  生成并加载内部数据
	 * @param portCode
	 * @param gzDate
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	protected String initInnerData(String portCode, String gzDate,String userCode) throws Exception {
		GeneYEBDataService geneService = new GeneYEBDataService();
		String result = "";
		Connection conn = null;
		try{
			conn = this.dataCompareDao.loadNewConnection();
			conn.setAutoCommit(false);
			//先清除历史数据
			this.dataCompareDao.deleteInnerData(portCode, gzDate, "REVERSE",this.fileType,this.rptType, conn);
			this.dataCompareDao.deleteErInfo("REVERSE", portCode, gzDate, this.fileType,this.rptType, conn);
			geneService.init(conn, portCode, gzDate, userCode);
			Map<String, Object> resultMap = geneService.geneElecData();
			if(resultMap != null){
				result = resultMap.get("result").toString();
			}
			if (result.startsWith("DZ")) {
				updateErInfoWay("REVERSE", result, portCode, gzDate,this.fileType,this.rptType,conn);
				updateErYebWay("REVERSE", result, portCode, gzDate,this.fileType,this.rptType,conn);
				this.sn = result;
				this.reveInfo.setC_SN(result);
				conn.commit();
				conn.setAutoCommit(true);
				initInnerData(result);
				return result;
			} else if (result.equalsIgnoreCase("Warn")) {
				throw new Exception("余额表没有数据产生！");
			} else if (result.equalsIgnoreCase("Fail")) {
				throw new Exception("生成余额表失败！");
			} else
			{
				throw new Exception("生成余额表失败！");
			}
		}finally{
			this.dataCompareDao.releaseConnection(conn);
		}
	}
	/**
	 * 更新生成的估值表的对账方向为反向
	 * @param value
	 * @param sn
	 * @param portCode
	 * @param dzDate
	 * @param rptType 
	 * @param fileType 
	 */
	protected void updateErYebWay(String value,String sn,String portCode,String dzDate,String fileType, String rptType, Connection conn) {
		this.dataCompareDao.updateReportWay(value, sn, portCode, dzDate,fileType,rptType,conn);
	}
	/**
	 * 比较一个托管行的数据
	 */
	public List<ReveResult> compareData(IgnoreManager ignoreManager,ZbMapManager zbManager) throws Exception {
		//检查内部数据是否生成
		initInnerData(this.portCode,this.gzDate,this.userCode);
		//检查外部数据是否生成
		initOutData(assList.get(0).getC_PORT_CODE_OUT(),this.gzDate,assList.get(0).getC_TGH_CODE(),this.rptType);
		//获取需要对比的项
		Map<String,String> items = getCompareItem();
		List<ReveResult> reslist = new ArrayList<ReveResult>();
		//比较设置过科目映射的项
		compareKmMapData(items,this.kmMaps,innerYes,outYes,ignoreManager,reslist);
//		7、假如在系统内内部科目A与外部科目B之间存在一对一映射关系，倘若存在内部科目C、外部科目D分别以A、B开头，且C去除A后与D去除B后剩余代码相同，则对账时默认C与D建立了一对一映射关系；
//		9、本方和对方的对账数据中存在未设置映射关系导致无法核对的数据，根据科目代码相同自动建立一对一映射关系，自动完成对账。
		//再比较规则7，最后规则9，
		compareAutoMapData(items,innerYes,outYes,ignoreManager,reslist);
		handleNoMapData(items,innerYes,outYes,ignoreManager,reslist);
		return reslist;
		
	}

	/**
	 * 处理没有做映射的数据
	 * @param items
	 * @param innerGzs
	 * @param outGzs
	 * @param ignoreMap 
	 * @param reslist
	 * @throws Exception
	 */
	private void handleNoMapData(Map<String, String> items,
			Map<String, BasePojo> innerGzs, Map<String, BasePojo> outGzs,
			IgnoreManager ignoreManager, List<ReveResult> reslist) throws Exception {
		List<BasePojo> erGzs = new ArrayList<BasePojo>();
		List<BasePojo> erGzOuts = new ArrayList<BasePojo>();
		Set<Entry<String, BasePojo>> innerSets = innerGzs.entrySet();
		Iterator<Entry<String, BasePojo>> innerIt = innerSets.iterator();
		while(innerIt.hasNext())
		{
			Entry<String, BasePojo> entry = innerIt.next();
			erGzOuts.clear();
			erGzs.clear();
			erGzs.add(entry.getValue());
			compareAllItem(items, erGzs, erGzOuts, reslist, ignoreManager);
			innerIt.remove();
		}
		Set<Entry<String, BasePojo>> outSets = outGzs.entrySet();
		Iterator<Entry<String, BasePojo>> outIt = outSets.iterator();
		while(outIt.hasNext())
		{
			Entry<String, BasePojo> entry = outIt.next();
			erGzOuts.clear();
			erGzs.clear();
			erGzOuts.add(entry.getValue());
			compareAllItem(items, erGzs, erGzOuts, reslist, ignoreManager);
			outIt.remove();
		}
	}
	/**
	 * 9、本方和对方的对账数据中存在未设置映射关系导致无法核对的数据，根据科目代码相同自动建立一对一映射关系，自动完成对账。
	 * @param key：内部科目代码
	 * @param innerGzs
	 * @param outGzs
	 * @return
	 */
	private String getKmCodeOutFromRule9(String key,Set<String> outGzs)
	{
		key = key.replaceAll("\\.", "");
		if(outGzs.contains(key))
		{
			return key;
		}else//匹配不上时去掉市场继续匹配
		{
			int j = key.indexOf(" ");
			if(j > 0)
			{
				key = key.substring(0, j);
				if(outGzs.contains(key))
				{
					return key;
				}
			}
		}
		return null;
	}
	/**
	 * 7、假如在系统内内部科目A与外部科目B之间存在一对一映射关系，倘若存在内部科目C、外部科目D分别以A、B开头，且C去除A后与D去除B后剩余代码相同，则对账时默认C与D建立了一对一映射关系；
	 * @param key 内部科目代码
	 * @param outGzs
	 * @return
	 */
	private String getKmCodeOutFromRule7(String key,Set<String> outGzs)
	{
		if(!key.contains("."))
		{
			return null;
		}
		StringBuffer innerKmCode = new StringBuffer(key); 
		while(key.contains("."))
		{
			int i = key.lastIndexOf(".");
			if(otm.containsKey(innerKmCode.substring(0, i)))
			{
				//去掉后缀的.托管行的科目代码不包括.
				String suffix = innerKmCode.substring(i+1);
				suffix = suffix.replaceAll("\\.", "");
				Set<String> set = otm.get(innerKmCode.substring(0, i));
				Iterator<String> iterator = set.iterator();
				while(iterator.hasNext())
				{
					String preOut = iterator.next();
					String kmCodeOut = preOut+suffix;
					kmCodeOut = kmCodeOut.replaceAll("\\.", "");
					if(outGzs.contains(kmCodeOut))
					{
						return kmCodeOut;
					}else//匹配不上时，去掉市场继续匹配
					{
						int j = kmCodeOut.indexOf(" ");
						if(j > 0)
						{
							kmCodeOut = kmCodeOut.substring(0, j);
							if(outGzs.contains(kmCodeOut))
							{
								return kmCodeOut;
							}
						}
					}
				}
			}
			key = key.substring(0, i);
		}
		return null;
	}
	/**
	 * 通过内部科目代码根据自动映射规则找到外部科目代码
	 * 先比较规则8，再比较规则7，最后规则9，
	 * @param key
	 * @param outGzs
	 * @return
	 */
	private String getKmCodeOutByKmCode(String key,Set<String> outGzs)
	{
		String kmCode = null;
		kmCode = getKmCodeOutFromRule7(key, outGzs);
		if(kmCode != null)
		{
			return kmCode;
		}
		kmCode = getKmCodeOutFromRule9(key, outGzs);
		if(kmCode != null)
		{
			return kmCode;
		}
		return kmCode;
	}
	/**
	 * 8这种情况不会出现，屏蔽掉
	 *7、假如在系统内内部科目A与外部科目B之间存在一对一映射关系，倘若存在内部科目C、外部科目D分别以A、B开头，且C去除A后与D去除B后剩余代码相同，则对账时默认C与D建立了一对一映射关系；
	 *8、假如在系统内维护内部科目A与外部科目B之间存在一对一映射关系，倘若存在内部科目C由内部科目A+债券代码E组成，外部科目D由外部科目B+债券代码F组成、债券代码E与债券代码F建立了映射关系，对账时自动处理为C与D建立了一对一映射关系；
	 *9、本方和对方的对账数据中存在未设置映射关系导致无法核对的数据，根据科目代码相同自动建立一对一映射关系，自动完成对账。
	 * 先比较规则8，再比较规则7，最后规则9，
	 * @param items
	 * @param innerGzs
	 * @param outGzs
	 * @param ignoreMap 
	 * @return
	 * @throws Exception 
	 */
	private void compareAutoMapData(Map<String,String> items,
			Map<String, BasePojo> innerYes,Map<String, BasePojo> outGzs,IgnoreManager ignoreManager, List<ReveResult> reveResult) throws Exception {
		Iterator<Entry<String, BasePojo>> innerIt = innerYes.entrySet().iterator();
		String kmCodeOut = null;
		List<BasePojo> gzs = null;
		List<BasePojo> gzOuts = null;
		Map<String,String> compareItems = items;
		while(innerIt.hasNext())
		{
			compareItems = items;
			gzs = new ArrayList<BasePojo>();
			gzOuts = new ArrayList<BasePojo>();
			Entry<String, BasePojo> entry = innerIt.next();
			gzs.add(entry.getValue());
			//先用指标代码或者科目代码匹配
			//匹配不上的指标项用科目名称匹配
			kmCodeOut = getKmCodeOutByKmCode(entry.getKey(), outGzs.keySet());
			if(kmCodeOut != null && !"".equalsIgnoreCase(kmCodeOut.trim()))
			{
				BasePojo erGzbOut = outGzs.get(kmCodeOut);
				gzOuts.add(erGzbOut);
				//移除比对过的科目
				outGzs.remove(kmCodeOut);
			}else if(this.elecRelas.containsKey(entry.getKey()))//匹配不上的指标项用科目名称匹配
			{
				ElecRela elecRela = elecRelas.get(entry.getKey());
				if(elecRela != null)
				{
					String zbName = elecRela.getC_ZB_NAME();
					if(outGzs.containsKey(zbName))
					{
						BasePojo erGzbOut = outGzs.get(zbName);
						gzOuts.add(erGzbOut);
						//移除比对过的科目
						outGzs.remove(zbName);
					}
				}
				
			}
			compareAllItem(compareItems, gzs, gzOuts, reveResult, ignoreManager);
			//移除比对过的科目
			innerIt.remove();
		}
	}
	/**
	 * 比较设置过科目映射的数据
	 * @param items
	 * @param kms
	 * @param ignoreMap 
	 * @param innerGzs
	 * @param outGzs
	 * @param reveResult
	 * @throws Exception
	 */
	public void compareKmMapData(Map<String,String> items,Map<String,KmRelaRecord> kms,
			Map<String, BasePojo> innerYes,
			Map<String, BasePojo> outYes, IgnoreManager ignoreManager, List<ReveResult> reveResult) throws Exception {
		//List<ReveResult> reveResult = new ArrayList<ReveResult>();
		List<KmMap> innerKms = null;
		List<KmMap> outKms = null;
		List<BasePojo> erYes = null;
		List<BasePojo> erYeOuts = null;
		//匹配时要移除本方科目代码后面的后缀
		for(KmRelaRecord krr : kms.values())
		{
			innerKms = krr.getList_KM_INNER()==null?new ArrayList<KmMap>():krr.getList_KM_INNER();
			outKms = krr.getList_KM_OUT()==null?new ArrayList<KmMap>():krr.getList_KM_OUT();
			erYes = new ArrayList<BasePojo>();
			for(KmMap km : innerKms)
			{
				//移除本方科目代码后面的后缀
				String code = km.getC_KM_CODE().trim();
				//设置过科目映射的科目不一定有数据
				if(innerYes.containsKey(code))
				{
					erYes.add(innerYes.get(code));
					//移除比对过的科目
					innerYes.remove(code);
				}
			}
			erYeOuts = new ArrayList<BasePojo>();
			for(KmMap km : outKms)
			{
				//设置过科目映射的科目不一定有数据
				if(outYes.containsKey(km.getC_KM_CODE()))
				{
					erYeOuts.add(outYes.get(km.getC_KM_CODE()));
					//移除比对过的科目
					outYes.remove(km.getC_KM_CODE());
				}
			}
			compareAllItem(items, erYes, erYeOuts, reveResult, ignoreManager);
		}//end for
	}
	/***
	 * 
	 * @param items
	 * @param kms
	 * @param innerYes
	 * @param outYes
	 * @param ignoreMap
	 * @param reveResult
	 * @throws Exception
	 */
	public void compareMultTghKmMapData(Map<String,String> items,Map<String,KmRelaRecord> kms,
			Map<String, BasePojo> innerYes,
			Map<String, Map<String, BasePojo>> outYes, IgnoreManager ignoreManager, List<ReveResult> reveResult) throws Exception {
		List<KmMap> innerKms = null;
		List<KmMap> outKms = null;
		List<BasePojo> erCompareYes = null;
		List<BasePojo> erCompareYeOuts = null;
		Map<String, BasePojo> tghMap = null;
		Set<String> tghCodes = outYes.keySet();
		//匹配时要移除本方科目代码后面的后缀
		for(KmRelaRecord krr : kms.values())
		{
			innerKms = krr.getList_KM_INNER()==null?new ArrayList<KmMap>():krr.getList_KM_INNER();
			outKms = krr.getList_KM_OUT()==null?new ArrayList<KmMap>():krr.getList_KM_OUT();
			erCompareYes = new ArrayList<BasePojo>();
			for(KmMap km : innerKms)
			{
				//移除本方科目代码后面的后缀
				String code = km.getC_KM_CODE().trim();
				//设置过科目映射的科目不一定有数据
				if(innerYes.containsKey(code))
				{
					erCompareYes.add(innerYes.get(code));
					//移除比对过的科目
					innerYes.remove(code);
				}
			}
			erCompareYeOuts = new ArrayList<BasePojo>();
			for(KmMap km : outKms)
			{
				String kmCode = km.getC_KM_CODE();
				String tghCode = km.getC_TGH_CODE();
				//从对应托管行取数据
				if(outYes.containsKey(tghCode))
				{
					tghMap = outYes.get(tghCode);
					//设置过科目映射的不一定有数据
					if(tghMap.containsKey(kmCode))
					{
						BasePojo basePojo = tghMap.get(kmCode);
						erCompareYeOuts.add(basePojo);
						tghMap.remove(kmCode);
					}
				}else//遍历所有数据
				{
					for(String s : tghCodes)
					{
						tghMap = outYes.get(s);
						//设置过科目映射的不一定有数据
						if(tghMap.containsKey(kmCode))
						{
							BasePojo basePojo = tghMap.get(kmCode);
							erCompareYeOuts.add(basePojo);
							tghMap.remove(kmCode);
						}
					}
				}
			}
			compareAllItem(items, erCompareYes, erCompareYeOuts, reveResult, ignoreManager);
		}//end for
	}
	/**
	 * 通过比较项获取内部余额表属性
	 */
	@Override
	public String getInnerPropertyByCompareItem(String item) {
		return ErYebItemColumnName.valueOf(item).toString();
	}
	/**
	 * 通过比较项获取外部余额表属性
	 */
	@Override
	public String getOutPropertyByCompareItem(String item) {
		return ErYebOutItemColumnName.valueOf(item).toString();
	}
	@Override
	public ResRela createResRela(String kmScope, BasePojo pojo)
			throws YssException {
		ResRela resRela = new ResRela();
		if(ReveDzCons.REVE_KMFW_INNER.equals(kmScope))
		{
			ErYeb gz = (ErYeb) pojo;
			resRela.setC_DV_KM_SCOPE(kmScope);
			String kmCode = gz.getC_KM_CODE();
			if(kmCode != null&&this.elecRelas.containsKey(kmCode))
			{
				if(this.elecRelas.get(kmCode) != null)
				{
					String zbName = this.elecRelas.get(kmCode).getC_ZB_NAME();
					kmCode = kmCode + "("+zbName+")";
				}
			}
			resRela.setC_KM_CODE(kmCode);
			resRela.setC_KM_NAME(gz.getC_KM_NAME());
		}else
		{
			ErYebOut gz = (ErYebOut) pojo;
			resRela.setC_DV_KM_SCOPE(kmScope);
			resRela.setC_KM_CODE(gz.getC_KM_CODE());
			resRela.setC_KM_NAME(gz.getC_KM_NAME());
			resRela.setC_TGH_CODE(gz.getC_TGH_CODE());
		}
		return resRela;
	}
	/**
	 * 获取行标识
	 */
	@Override
	public String getRowFlag(String kmScope,BasePojo pojo)
			throws YssException {
		String value = "";
		if(ReveDzCons.REVE_KMFW_INNER.equals(kmScope))
		{
			ErYeb ye = (ErYeb) pojo;
			value = ye.getC_KM_CODE();
		}else
		{
			ErYebOut ye = (ErYebOut) pojo;
			value = ye.getC_KM_CODE();
		}
		return value;
	}
	/**
	 * 设置对账结果
	 */
	@Override
	public String setResultItemValue(BasePojo pojo, String item, String value) throws Exception {
		if(pojo == null)
		{
			return "";
		}
		if(pojo instanceof ResRela)//
		{
			item = YeResRelaItemColumnName.valueOf(item).toString();
		}else if(pojo instanceof ReveResult)
		{
			item = YeReveResultItemColumnName.valueOf(item).toString();
		}
		
		ReflectionUtil.setProperty(pojo, item, value);
		return item;
	}
	/**
	 * 获取对账类型所需要对账的比对项
	 * @param fileType
	 * @return
	 */
	@Override
	public Map<String,String> getCompareItem() 
	{
		Map<String,String> map = new HashMap<String, String>();
		IVocCacheDataService vocCacheDataService = YssServiceFactory.getInstance().createService(IVocCacheDataService.class);
		List<BasePojo> list = vocCacheDataService.getDataListByTypes(new String[]{"REVE_YE_HDX"});
		Vocabulary voc = null;
		for(BasePojo pojo : list)
		{
			voc = (Vocabulary) pojo;
			map.put(voc.getC_DV_CODE(),voc.getC_DV_NAME());
		}
		return map;
	}
	@Override
	public List<ReveResult> compareMultTghData(
			Map<String, Map<String,BasePojo>> outDatas,IgnoreManager ignoreManager,ZbMapManager zbManager) throws Exception 
	{
		if(outDatas == null)//校验外部数据
		{
			this.logger.info("未查找到外部数据！");
			throw new Exception("未查找到外部数据！");
		}
		if(this.innerYes == null)//校验内部数据
		{
			this.logger.info("未查找到内部数据！");
			throw new Exception("未查找到内部数据！");
		}
		//检查内部数据是否生成
		initInnerData(this.portCode,this.gzDate,this.userCode);

		Map<String,String> items = getCompareItem();
		List<ReveResult> reslist = new ArrayList<ReveResult>();
		//比较设置过科目映射的项
		compareMultTghKmMapData(items,this.kmMaps,innerYes,outDatas,ignoreManager,reslist);
		//科目代码一致的自动映射
		compareMultTghAutoMapData(items,innerYes,outDatas,ignoreManager,reslist);
		handleMultTghNoMapData(items,innerYes,outDatas,ignoreManager,reslist);
		return reslist;		
	}
	private void handleMultTghNoMapData(Map<String, String> items,
			Map<String, BasePojo> innerYes,
			Map<String, Map<String,BasePojo>> outDatas,
			IgnoreManager ignoreManager,
			List<ReveResult> reslist) throws Exception 
	{
		List<BasePojo> CompareErYes = new ArrayList<BasePojo>();
		List<BasePojo> CompareErYeOuts = new ArrayList<BasePojo>();
		Set<Entry<String, BasePojo>> innerSets = innerYes.entrySet();
		Iterator<Entry<String, BasePojo>> innerIt = innerSets.iterator();
		while(innerIt.hasNext())
		{
			Entry<String, BasePojo> entry = innerIt.next();
			CompareErYeOuts.clear();
			CompareErYes.clear();
			CompareErYes.add(entry.getValue());
			compareAllItem(items, CompareErYes, CompareErYeOuts, reslist,ignoreManager);
			innerIt.remove();
		}
		Set<String> tghCodes = outDatas.keySet();
		for(String tghCode : tghCodes)
		{
			Set<Entry<String, BasePojo>> outSets = outDatas.get(tghCode).entrySet();
			Iterator<Entry<String, BasePojo>> outIt = outSets.iterator();
			while(outIt.hasNext())
			{
				Entry<String, BasePojo> entry = outIt.next();
				CompareErYeOuts.clear();
				CompareErYes.clear();
				CompareErYeOuts.add(entry.getValue());
				compareAllItem(items, CompareErYes, CompareErYeOuts, reslist,ignoreManager);
				outIt.remove();
			}
		}
	}
	private void compareMultTghAutoMapData(Map<String, String> items,
			Map<String, BasePojo> innerYes,
			Map<String, Map<String,BasePojo>> outYes,
			IgnoreManager ignoreManager,
			List<ReveResult> reslist) throws Exception 
	{
		Iterator<Entry<String, BasePojo>> innerIt = innerYes.entrySet().iterator();
		String kmCodeOut = null;
		List<BasePojo> yes = null;
		List<BasePojo> yeOuts = null;
		Map<String,String> compareItems = items;
		while(innerIt.hasNext())
		{
			compareItems = items;
			yes = new ArrayList<BasePojo>();
			yeOuts = new ArrayList<BasePojo>();
			Entry<String, BasePojo> entry = innerIt.next();
			yes.add(entry.getValue());
			//先用指标代码或者科目代码匹配
			//匹配不上的指标项用科目名称匹配
			//多个托管行都要匹配
			for(String tghCode : outYes.keySet())
			{
				Map<String, BasePojo> tghMap = outYes.get(tghCode);
				kmCodeOut = getKmCodeOutByKmCode(entry.getKey(), tghMap.keySet());
				if(kmCodeOut != null && !"".equalsIgnoreCase(kmCodeOut.trim()))
				{
					BasePojo erGzbOut =  tghMap.get(kmCodeOut);
					yeOuts.add(erGzbOut);
					//移除比对过的科目
					tghMap.remove(kmCodeOut);
				}else if(this.elecRelas.containsKey(entry.getKey()))//匹配不上的指标项用科目名称匹配
				{
					ElecRela elecRela = elecRelas.get(entry.getKey());
					if(elecRela != null)
					{
						String zbName = elecRela.getC_ZB_NAME();
						if(tghMap.containsKey(zbName))
						{
							BasePojo erGzbOut = tghMap.get(zbName);
							yeOuts.add(erGzbOut);
							//移除比对过的科目
							tghMap.remove(zbName);
						}
					}
					
				}
			}
			
			compareAllItem(compareItems, yes, yeOuts, reslist, ignoreManager);
			//移除比对过的科目
			innerIt.remove();
		}
		
	}
	
	@Override
	public Map<String, Map<String,BasePojo>> collectMultTghData(
			List<AssMap> assMaps) {
		Map<String, Map<String,BasePojo>> map = new HashMap<String, Map<String,BasePojo>>();
		for(AssMap ass : assMaps)
		{
			String tghCode = ass.getC_TGH_CODE();
			Map<String, BasePojo> data = this.dataCompareDao.getOutData(ass.getC_PORT_CODE_OUT(),gzDate,tghCode,rptType);
			map.put(tghCode, data);
		}
		return map;
	}
	
	@Override
	public String getTghCodeFromOutObject(BasePojo gzOut) {
		String result = "";
		if(gzOut != null)
		{
			ErYebOut yeb = (ErYebOut) gzOut;
			if(yeb.getC_TGH_CODE() != null)
			{
				result = yeb.getC_TGH_CODE();
			}
		}
		return result;
	}
}
