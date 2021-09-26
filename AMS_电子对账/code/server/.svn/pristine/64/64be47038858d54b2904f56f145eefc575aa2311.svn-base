package com.yss.uco.elecreco.er.reverse.compare.gz.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.ReflectionUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;
import com.yss.uco.elecreco.er.generate.gzb.GeneGZBDataService;
import com.yss.uco.elecreco.er.reverse.compare.common.IgnoreManager;
import com.yss.uco.elecreco.er.reverse.compare.common.ZbMapManager;
import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.gz.dao.ErGzbItemColumnName;
import com.yss.uco.elecreco.er.reverse.compare.gz.dao.ErGzbOutItemColumnName;
import com.yss.uco.elecreco.er.reverse.compare.gz.dao.GzResRelaItemColumnName;
import com.yss.uco.elecreco.er.reverse.compare.gz.dao.GzReveResultItemColumnName;
import com.yss.uco.elecreco.er.reverse.compare.service.IDataCompareService;
import com.yss.uco.elecreco.er.reverse.compare.service.impl.DataCompareService;
import com.yss.uco.elecreco.er.reverse.cons.ReveDzCons;
import com.yss.uco.elecreco.er.reverse.manager.result.pojo.ReveResult;
import com.yss.uco.elecreco.er.reverse.manager.result.resrela.pojo.ResRela;
import com.yss.uco.elecreco.er.reverse.map.assmap.pojo.AssMap;
import com.yss.uco.elecreco.er.reverse.map.kmmap.pojo.KmMap;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaRecord;
import com.yss.uco.elecreco.er.reverse.map.zbmap.pojo.ZbMap;
import com.yss.uco.elecreco.er.reverse.out.ergzb.pojo.ErGzbOut;
import com.yss.uco.elecreco.support.bean.ElecRela;
import com.yss.uco.elecreco.support.bean.ErGzb;

public class GzDataCompareService extends DataCompareService implements IDataCompareService {

	public GzDataCompareService(DataCompareDao dataCompareDao) {
		super(dataCompareDao);
	}

	private Map<String,BasePojo> outGzs = new HashMap<String, BasePojo>();
	/**
	 * key:科目代码
	 */
	private Map<String,BasePojo> innerGzs = new HashMap<String, BasePojo>();
	
	/**
	 * key:指标代码(名称)
	 */
	private Map<String,Map<String,BasePojo>> multSpecialGzZbsOut = new HashMap<String, Map<String,BasePojo>>();
	/**
	 * key:指标代码(名称)
	 */
	private Map<String,BasePojo> specialGzZbsOut = new HashMap<String, BasePojo>();
	
	protected void initOutData(String portCode, String gzDate, String c_TGH_CODE, String rptType) throws Exception {
		Map<String, BasePojo> outDatas = this.dataCompareDao.getOutData(portCode, gzDate, c_TGH_CODE, rptType);
		if(outDatas == null || outDatas.size() == 0)
		{
			throw new Exception("外部数据没有导入！");
		}
		ErGzbOut gz = null;
		//只对比科目代码不为空的项
		//指标项需要比对的值放到名称列的需要特殊处理
		for(BasePojo pojo : outDatas.values())
		{
			gz = (ErGzbOut) pojo;
			if(gz != null && !StringUtil.IsNullOrEmptyT(gz.getC_KM_CODE()))
			{
				gz.setC_KM_CODE(gz.getC_KM_CODE().trim());
				
				if(isSpecialZbItem(gz.getC_KM_NAME().trim()))
				{
					gz.setC_KM_NAME(gz.getC_KM_NAME().trim());
					//STORY59971【景顺长城基金】【道富QD】【紧急】估值表核对增加原币和指标的核对
					//3、调整托管行估值表的total all（科目名称列展示数据的指标项）的指标项在 本币市值列与我放数据进行核对。
					gz.setN_PORT_MV(gz.getC_KM_NAME());
					this.specialGzZbsOut.put(gz.getC_KM_CODE(), gz);
				}else
				{
					this.outGzs.put(gz.getC_KM_CODE(), gz);
				}
			}
		}
	}
	/**
	 * 是不是数字，包括百分号,负号,逗号
	 * @param value
	 * @return
	 */
	protected boolean isNumber(String value) {
		boolean b = false;
		if(value.startsWith(",")||value.startsWith("，"))
		{
			return false;
		}
		if(value.contains(","))
		{
			value = value.replaceAll(",", "");
		}
		if(value.contains("，"))
		{
			value = value.replaceAll("，", "");
		}
		String regex = "[-]?[0-9]*[.]?[0-9]+[%]?";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		b = matcher.matches();
		return b;
	}
	
	protected boolean isSpecialZbItem(String kmName)
	{
		if(kmName == null)
		{
			return false;
		}
		if(StringUtil.IsNullOrEmptyT(kmName))
		{
			return false;
		}else
		{
			if(isNumber(kmName))
			{
				return true;
			}
		}
		return false;
	}
	
	protected void initInnerData(String sn) throws Exception {
		Map<String, BasePojo> datas = this.dataCompareDao.getInnerData(sn);
		if(datas == null || datas.size() == 0)
		{
			throw new Exception("内部数据没有生成！");
		}
		for(BasePojo pojo : datas.values())
		{
			ErGzb gz = (ErGzb) pojo;
			if(gz != null && !StringUtil.IsNullOrEmptyT(gz.getC_KM_CODE()))
			{
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
				this.innerGzs.put(code, gz);
			}
		}
	}

	protected String initInnerData(String portCode, String gzDate,String userCode) throws Exception {
		//调用生成的逻辑
		//String sn = "";
		GeneGZBDataService geneService = new GeneGZBDataService();
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
				//result_detail =  resultMap.get("resultDetail").toString();
			}
			if (result.startsWith("DZ")) {
				updateErInfoWay("REVERSE", result, portCode, gzDate,this.fileType,this.rptType,conn);
				updateErGzbWay("REVERSE", result, portCode, gzDate,this.fileType,this.rptType,conn);
				
				this.sn = result;
				this.reveInfo.setC_SN(result);
				conn.commit();
				conn.setAutoCommit(true);
				initInnerData(result);
				return result;
				//ben_Record.EndLog_Success("执行成功");
			} else if (result.equalsIgnoreCase("Warn")) {
				//ben_Record.EndLog_Warn("没有数据产生");
				throw new Exception("估值表没有数据产生！");
			} else if (result.equalsIgnoreCase("Fail")) {
				//ben_Record.EndLog_Fail("执行失败");
				throw new Exception("生成估值表失败！");
			} else
			{
				throw new Exception("生成估值表失败！");
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
	protected void updateErGzbWay(String value,String sn,String portCode,String dzDate,String fileType, String rptType, Connection conn) {
		this.dataCompareDao.updateReportWay(value, sn, portCode, dzDate,fileType,rptType,conn);
	}
	
	public List<ReveResult> compareData(IgnoreManager ignoreManager,ZbMapManager zbMapManager) throws Exception {
		//检查内部数据是否生成
		initInnerData(this.portCode,this.gzDate,this.userCode);
		//检查外部数据是否生成
		initOutData(assList.get(0).getC_PORT_CODE_OUT(),this.gzDate,assList.get(0).getC_TGH_CODE(),this.rptType);
//		//区分忽略设置类型
//		Map<String,IgnoreItem> bfRow = ignoreMap.get(ReveDzCons.IGNORE_KEY_BF_ROW);
//		Map<String,IgnoreItem> dfRow = ignoreMap.get(ReveDzCons.IGNORE_KEY_DF_ROW);
//		Map<String,IgnoreItem> bfCol = ignoreMap.get(ReveDzCons.IGNORE_KEY_BF_COL);
//		Map<String,IgnoreItem> dfCol = ignoreMap.get(ReveDzCons.IGNORE_KEY_DF_COL);
		//去掉本方行忽略的科目
//		removeIgnoreInnerKm(innerGzs, bfRow);
		//去掉对方行忽略的科目
//		removeIgnoreOutKm(outGzs, dfRow);
		//获取需要对比的项
		Map<String,String> items = getCompareItem();
		//去掉列忽略
//		removeIgnoreItem(items, bfCol, dfCol);
		List<ReveResult> reslist = new ArrayList<ReveResult>();
		//比较设置过科目映射的项
		compareKmMapData(items,this.kmMaps,innerGzs,outGzs,ignoreManager,reslist);
		//比较设置过指标映射的项
		compareZbMapData(items,zbMapManager,innerGzs,specialGzZbsOut,outGzs,ignoreManager,reslist);
//		7、假如在系统内内部科目A与外部科目B之间存在一对一映射关系，倘若存在内部科目C、外部科目D分别以A、B开头，且C去除A后与D去除B后剩余代码相同，则对账时默认C与D建立了一对一映射关系；
//		8、假如在系统内维护内部科目A与外部科目B之间存在一对一映射关系，倘若存在内部科目C由内部科目A+债券代码E组成，外部科目D由外部科目B+债券代码F组成、债券代码E与债券代码F建立了映射关系，对账时自动处理为C与D建立了一对一映射关系；
//		9、本方和对方的对账数据中存在未设置映射关系导致无法核对的数据，根据科目代码相同自动建立一对一映射关系，自动完成对账。
		//先比较规则8，再比较规则7，最后规则9，
		compareAutoMapData(items,innerGzs,specialGzZbsOut,outGzs,ignoreManager,reslist);
		handleNoMapData(items,innerGzs,specialGzZbsOut,outGzs,ignoreManager,reslist);
		return reslist;
		
	}

	protected void handleNoMapData(Map<String, String> items,
			Map<String, BasePojo> innerGzs, Map<String, BasePojo> outGzZbs,Map<String, BasePojo> outGzs,
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
		handleNoMapSpecialZb(outGzZbs,ignoreManager,reslist);
	}
	
	protected void handleMultTghNoMapSpecialZb(Map<String, Map<String,BasePojo>> outZbs,IgnoreManager ignoreManager,List<ReveResult> reveResult) throws Exception
	{
		for(String key : outZbs.keySet())
		{
			Set<Entry<String, BasePojo>> set2 = outZbs.get(key).entrySet();
			Iterator<Entry<String, BasePojo>> iterator2 = set2.iterator();
			while(iterator2.hasNext())
			{
				Entry<String, BasePojo> next = iterator2.next();
				if(next.getValue()!=null)
				{
					ErGzbOut gz = (ErGzbOut) next.getValue();
					if(gz != null)
					{
						ArrayList<BasePojo> list = new ArrayList<BasePojo>();
						list.add(gz);
						compareAllItem(getSpecialZbCompareItem(), new ArrayList<BasePojo>(), list, reveResult, ignoreManager);
						iterator2.remove();
					}
				}
			}
		}
	}
	
	protected void handleNoMapSpecialZb(Map<String, BasePojo> outZbs,IgnoreManager ignoreManager,List<ReveResult> reveResult) throws Exception
	{
		Set<Entry<String, BasePojo>> set2 = outZbs.entrySet();
		Iterator<Entry<String, BasePojo>> iterator2 = set2.iterator();
		while(iterator2.hasNext())
		{
			Entry<String, BasePojo> next = iterator2.next();
			if(next.getValue()!=null)
			{
				ErGzbOut gz = (ErGzbOut) next.getValue();
				//String code = gz!=null?gz.getC_KM_CODE():null;
				if(gz != null)
				{
					ArrayList<BasePojo> list = new ArrayList<BasePojo>();
					list.add(gz);
					compareAllItem(getSpecialZbCompareItem(), new ArrayList<BasePojo>(), list, reveResult, ignoreManager);
					iterator2.remove();
				}
			}
		}
	}

	/**
	 * 9、本方和对方的对账数据中存在未设置映射关系导致无法核对的数据，根据科目代码相同自动建立一对一映射关系，自动完成对账。
	 * @param key：内部科目代码
	 * @param innerGzs
	 * @param outGzs
	 * @return
	 */
	private String getKmCodeOutFromRule9(String key,Set<String> kmCodes)
	{
		key = key.replaceAll("\\.", "");
		if(kmCodes.contains(key))
		{
			return key;
		}else//匹配不上时去掉市场继续匹配
		{
			int j = key.indexOf(" ");
			if(j > 0)
			{
				key = key.substring(0, j);
				if(kmCodes.contains(key))
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
	private String getKmCodeOutFromRule7(String key,Set<String> kmCodes)
	{
		if(!key.contains("."))
		{
			return null;
		}
//		可能包含8这种情况
//		特殊情况1
//		映射关系：
//		11321832
//		127004 SZ	001002 SZ
//		科目代码
//		1132.01.03.09.002.127004 SZ		1132010309002001002 SZ
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
					if(kmCodes.contains(kmCodeOut))
					{
						return kmCodeOut;
					}else//匹配不上时，去掉市场继续匹配
					{
						int j = kmCodeOut.indexOf(" ");
						if(j > 0)
						{
							kmCodeOut = kmCodeOut.substring(0, j);
							if(kmCodes.contains(kmCodeOut))
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
	 * 8、假如在系统内维护内部科目A与外部科目B之间存在一对一映射关系，倘若存在内部科目C由内部科目A+债券代码E组成，外部科目D由外部科目B+债券代码F组成、债券代码E与债券代码F建立了映射关系，对账时自动处理为C与D建立了一对一映射关系；
	 * @param key:内部科目代码
	 * @param outGzs
	 * @return
	 */
//	private String getKmCodeOutFromRule8(String key,Map<String,BasePojo> outGzs)
//	{
//		if(key.contains("."))
//		{
//			int i = key.lastIndexOf(".");
//			if(otm.containsKey(key.substring(i+1)))
//			{
//				if(otm.containsKey(key.substring(0, i))&&otm.containsKey(key.substring(i+1)))
//				{
//					String kmCodeOut = "";
//					Set<String> setSuffix = otm.get(key.substring(i+1));
//					Iterator<String> iteratorSuff = setSuffix.iterator();
//					Set<String> setPre = otm.get(key.substring(0, i));
//					Iterator<String> iteratorPre = setPre.iterator();
//					while(iteratorPre.hasNext())
//					{
//						String pre = iteratorPre.next();
//						while(iteratorSuff.hasNext())
//						{
//							String suffixOut = iteratorSuff.next();
//							kmCodeOut = pre+suffixOut;
//							kmCodeOut = kmCodeOut.replaceAll("\\.", "");
//							if(outGzs.containsKey(kmCodeOut))
//							{
//								return kmCodeOut;
//							}
//						}
//					}
//					
//				}
//			}
//		}
//		
//		return null;
//	}
	/**
	 * 通过内部科目代码根据自动映射规则找到外部科目代码
	 * 先比较规则8，再比较规则7，最后规则9，
	 * @param key
	 * @param outGzs
	 * @return
	 */
	protected String getKmCodeOutByKmCode(String key,Set<String> kmCodes)
	{
		String kmCode = null;
		//kmCode = getKmCodeOutFromRule8(key, outGzs);
//		if(kmCode != null)
//		{
//			return kmCode;
//		}
		kmCode = getKmCodeOutFromRule7(key, kmCodes);
		if(kmCode != null)
		{
			return kmCode;
		}
		kmCode = getKmCodeOutFromRule9(key, kmCodes);
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
			Map<String, BasePojo> innerGzs, Map<String, BasePojo> specialOutZbs,Map<String, BasePojo> outGzs,IgnoreManager ignoreManager, List<ReveResult> reveResult) throws Exception {
		Iterator<Entry<String, BasePojo>> innerIt = innerGzs.entrySet().iterator();
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
					String zbCode = elecRela.getC_ZB_CODE();//先匹配指标代码，再匹配指标名称
					if(outGzs.containsKey(zbCode) || specialOutZbs.containsKey(zbCode))
					{
						if(outGzs.containsKey(zbCode))
						{
							BasePojo erGzbOut = outGzs.get(zbCode);
							gzOuts.add(erGzbOut);
							//移除比对过的科目
							outGzs.remove(zbCode);
						}else if(specialOutZbs.containsKey(zbCode))
						{
							BasePojo erGzbOut = specialOutZbs.get(zbCode);
							gzOuts.add(erGzbOut);
							//移除比对过的科目
							specialOutZbs.remove(zbCode);
							//只比对市值列
							compareItems = getSpecialZbCompareItem();
						}
					}else
					{
						String zbName = elecRela.getC_ZB_NAME();
						if(outGzs.containsKey(zbName))
						{
							BasePojo erGzbOut = outGzs.get(zbName);
							gzOuts.add(erGzbOut);
							//移除比对过的科目
							outGzs.remove(zbName);
						}else if(specialOutZbs.containsKey(zbName))
						{
							BasePojo erGzbOut = specialOutZbs.get(zbName);
							gzOuts.add(erGzbOut);
							//移除比对过的科目
							specialOutZbs.remove(zbName);
							//只比对市值列
							compareItems = getSpecialZbCompareItem();
						}
					}
					
				}
				
			}
			compareAllItem(compareItems, gzs, gzOuts, reveResult, ignoreManager);
			//移除比对过的科目
			innerIt.remove();
		}
		
	}
	

	public void compareKmMapData(Map<String,String> items,Map<String,KmRelaRecord> kms,
			Map<String, BasePojo> innerGzs,
			Map<String, BasePojo> outGzs, IgnoreManager ignoreManager, List<ReveResult> reveResult) throws Exception {
		//List<ReveResult> reveResult = new ArrayList<ReveResult>();
		List<KmMap> innerKms = null;
		List<KmMap> outKms = null;
		List<BasePojo> erGzs = null;
		List<BasePojo> erGzOuts = null;
		//匹配时要移除本方科目代码后面的后缀
		for(KmRelaRecord krr : kms.values())
		{
			innerKms = krr.getList_KM_INNER()==null?new ArrayList<KmMap>():krr.getList_KM_INNER();
			outKms = krr.getList_KM_OUT()==null?new ArrayList<KmMap>():krr.getList_KM_OUT();
			erGzs = new ArrayList<BasePojo>();
			for(KmMap km : innerKms)
			{
				//移除本方科目代码后面的后缀
				String code = km.getC_KM_CODE().trim();
				//在初始化的时候转换
//				if(haveSecCode(code))
//				{
//					int i = code.lastIndexOf(".");
//					String isinCode = this.getISINCode(code.substring(i+1));
//					if(isinCode!= null && !StringUtil.IsNullOrEmptyT(isinCode))
//					{
//						code = code.substring(0, i+1)+isinCode;
//					}
//				}
				//设置过科目映射的科目不一定有数据
				if(innerGzs.containsKey(code))
				{
					erGzs.add(innerGzs.get(code));
					//移除比对过的科目
					innerGzs.remove(code);
				}
				
			}
			erGzOuts = new ArrayList<BasePojo>();
			for(KmMap km : outKms)
			{
				//设置过科目映射的科目不一定有数据
				if(outGzs.containsKey(km.getC_KM_CODE()))
				{
					erGzOuts.add(outGzs.get(km.getC_KM_CODE()));
					//移除比对过的科目
					outGzs.remove(km.getC_KM_CODE());
				}
			}
			compareAllItem(items, erGzs, erGzOuts, reveResult, ignoreManager);
		}//end for
		//return reveResult;
	}
	
	/**
	 * 值放在科目名称列的指标需要特殊处理
	 * @param items
	 * @param zbs
	 * @param innerGzs
	 * @param outGzs
	 * @param ignoreMap 
	 * @param reveResult
	 * @throws Exception
	 */
	public void compareMultTghZbMapData(Map<String,String> items,ZbMapManager zbManager,
			Map<String, BasePojo> innerGzs,Map<String, Map<String,BasePojo>> specialGzsOut,
			Map<String, Map<String,BasePojo>> outGzs,IgnoreManager ignoreManager, List<ReveResult> reveResult) throws Exception
	{
		List<BasePojo> erGzs = null;
		List<BasePojo> erGzOuts = null;
		Set<String> innerZbCodes = zbManager.getInnerZbCodes();
		for(String zbCode : innerZbCodes)
		{
			erGzs = new ArrayList<BasePojo>();
			//设置过科目映射的科目不一定有数据
			if(innerGzs.containsKey(zbCode))
			{
				erGzs.add(innerGzs.get(zbCode));
				//移除比对过的科目
				innerGzs.remove(zbCode);
			}
			erGzOuts = new ArrayList<BasePojo>();
			Map<String, ZbMap> zbMap = zbManager.getZbMapsByInnerZbCode(zbCode);
			boolean isSpecialZb =  isSpecialZbForMultTgh(zbMap,specialGzsOut);
			for (String tghCode : zbMap.keySet()) {
				if(!outGzs.containsKey(tghCode))
				{
					continue;
				}
				String zbCodeOut = zbMap.get(tghCode).getC_ZB_CODE_OUT();
				//Map<String, BasePojo> tghData = isSpecialZb ? specialGzsOut.get(tghCode) : outGzs.get(tghCode);
				//取出外部数据,先从特殊指标里取（total），再从正常指标取(total all)
				Map<String, BasePojo> tghData = specialGzsOut.get(tghCode);
				Map<String, BasePojo> tghData2 = outGzs.get(tghCode);
//				if(tghData == null)
//				{
//					tghData = outGzs.get(tghCode);
//				}
				if(tghData != null && tghData.containsKey(zbCodeOut))//正常指标项，核对列都需要核对的值
				{
					erGzOuts.add(tghData.get(zbCodeOut));
					//移除比对过的科目
					tghData.remove(zbCodeOut);
				}else if(tghData2 != null && tghData2.containsKey(zbCodeOut))
				{
					erGzOuts.add(tghData2.get(zbCodeOut));
					//移除比对过的科目
					tghData2.remove(zbCodeOut);
				}
				
			}// end tgh
			items = isSpecialZb ? getSpecialZbCompareItem() : items;
			compareAllItem(items, erGzs, erGzOuts, reveResult, ignoreManager);
		}//end zb
		
	}
	private boolean isSpecialZbForMultTgh(Map<String, ZbMap> zbMap,
			Map<String, Map<String, BasePojo>> specialGzsOut) {
		for(ZbMap zb : zbMap.values())
		{
			String out = zb.getC_ZB_CODE_OUT();
			for(Map<String, BasePojo> map : specialGzsOut.values())
			{
				if(map.containsKey(out))
				{
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 值放在科目名称列的指标需要特殊处理
	 * @param items
	 * @param zbs
	 * @param innerGzs
	 * @param outGzs
	 * @param ignoreMap 
	 * @param reveResult
	 * @throws Exception
	 */
	public void compareZbMapData(Map<String,String> items,ZbMapManager zbManager,
			Map<String, BasePojo> innerGzs,Map<String, BasePojo> specialGzsOut,
			Map<String, BasePojo> outGzs,IgnoreManager ignoreManager, List<ReveResult> reveResult) throws Exception
	{
		String zbCodeOut = "";
		List<BasePojo> erGzs = null;
		List<BasePojo> erGzOuts = null;
		Set<String> innerZbCodes = zbManager.getInnerZbCodes();
		for(String zbCode : innerZbCodes)
		{
			zbCodeOut = zbManager.getOutZbCodeByInnerZbCode(zbCode);
			if(zbCodeOut == null)
			{
				continue;
			}
			//if(innerGzs.containsKey(zbCode)||outGzs.containsKey(zbCodeOut))//正常指标项
			if(outGzs.containsKey(zbCodeOut))//正常指标项，核对列都需要核对的值
			{
				erGzs = new ArrayList<BasePojo>();
				//设置过科目映射的科目不一定有数据
				if(innerGzs.containsKey(zbCode))
				{
					erGzs.add(innerGzs.get(zbCode));
					//移除比对过的科目
					innerGzs.remove(zbCode);
				}
				erGzOuts = new ArrayList<BasePojo>();
				//设置过科目映射的科目不一定有数据
				if(outGzs.containsKey(zbCodeOut))
				{
					erGzOuts.add(outGzs.get(zbCodeOut));
					//移除比对过的科目
					outGzs.remove(zbCodeOut);
				}
				compareAllItem(items, erGzs, erGzOuts, reveResult, ignoreManager);
			}else if(specialGzsOut.containsKey(zbCodeOut)){//特殊指标项，只有一列的值需要核对的
				//已将科目名称列的值放到市值列，只需要核对市值列的值就行
				//compareSpecialZb(zbCode, zbCodeOut,reveResult);
				erGzs = new ArrayList<BasePojo>();
				//设置过科目映射的科目不一定有数据
				if(innerGzs.containsKey(zbCode))
				{
					erGzs.add(innerGzs.get(zbCode));
					//移除比对过的科目
					innerGzs.remove(zbCode);
				}
				erGzOuts = new ArrayList<BasePojo>();
				//设置过科目映射的科目不一定有数据
				if(specialGzsOut.containsKey(zbCodeOut))
				{
					erGzOuts.add(specialGzsOut.get(zbCodeOut));
					//移除比对过的科目
					specialGzsOut.remove(zbCodeOut);
				}
				compareAllItem(getSpecialZbCompareItem(), erGzs, erGzOuts, reveResult, ignoreManager);
			}
			
		}//end zb
		
	}
	
	public Map<String,String> getSpecialZbCompareItem()
	{
		//特殊指标项，只比对市值列
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("REVE_GZ_SZ", "本币市值");
		return map;
	}

	@Override
	public String getInnerPropertyByCompareItem(String item) {
		
		return ErGzbItemColumnName.valueOf(item).toString();
	}


	@Override
	public String getOutPropertyByCompareItem(String item) {
		return ErGzbOutItemColumnName.valueOf(item).toString();
	}


	@Override
	public ResRela createResRela(String kmScope, BasePojo pojo)
			throws YssException {
		ResRela resRela = new ResRela();
		if(ReveDzCons.REVE_KMFW_INNER.equals(kmScope))
		{
			ErGzb gz = (ErGzb) pojo;
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
			ErGzbOut gz = (ErGzbOut) pojo;
			resRela.setC_DV_KM_SCOPE(kmScope);
			resRela.setC_KM_CODE(gz.getC_KM_CODE());
			resRela.setC_KM_NAME(gz.getC_KM_NAME());
			resRela.setC_TGH_CODE(gz.getC_TGH_CODE());
		}
		return resRela;
	}


	@Override
	public String getRowFlag(String kmScope,BasePojo pojo)
			throws YssException {
		String value = "";
		if(ReveDzCons.REVE_KMFW_INNER.equals(kmScope))
		{
			ErGzb gz = (ErGzb) pojo;
			value = gz.getC_KM_CODE();
		}else
		{
			ErGzbOut gz = (ErGzbOut) pojo;
			value = gz.getC_KM_CODE();
		}
		return value;
	}


	@Override
	public String setResultItemValue(BasePojo pojo, String item, String value) throws Exception {
		if(pojo == null)
		{
			return "";
		}
		if(pojo instanceof ResRela)//
		{
			item = GzResRelaItemColumnName.valueOf(item).toString();
		}else if(pojo instanceof ReveResult)
		{
			item = GzReveResultItemColumnName.valueOf(item).toString();
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
		List<BasePojo> list = vocCacheDataService.getDataListByTypes(new String[]{"REVE_GZ_HDX"});
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
			Map<String, Map<String,BasePojo>> outDatas,IgnoreManager ignoreManager,ZbMapManager zbMapManager) throws Exception {
		if(outDatas == null)//校验外部数据
		{
			this.logger.info("未查找到外部数据！");
			throw new Exception("未查找到外部数据！");
		}
		if(this.innerGzs == null)//校验内部数据
		{
			this.logger.info("未查找到内部数据！");
			throw new Exception("未查找到内部数据！");
		}
		//检查内部数据是否生成
		initInnerData(this.portCode,this.gzDate,this.userCode);

		Map<String,String> items = getCompareItem();
		List<ReveResult> reslist = new ArrayList<ReveResult>();
		//比较设置过科目映射的项
		compareMultTghKmMapData(items,this.kmMaps,innerGzs,outDatas,ignoreManager,reslist);
		//比较设置过指标映射的项
		compareMultTghZbMapData(items,zbMapManager,innerGzs,multSpecialGzZbsOut,outDatas,ignoreManager,reslist);
		//科目代码一致的自动映射
		compareMultTghAutoMapData(items,innerGzs,outDatas,ignoreManager,reslist);
		handleMultTghNoMapData(items,innerGzs,outDatas,ignoreManager,reslist);
		handleMultTghNoMapSpecialZb(outDatas,ignoreManager,reslist);
		return reslist;	
	}
	
	public void compareMultTghKmMapData(Map<String,String> items,Map<String,KmRelaRecord> kms,
			Map<String, BasePojo> innerYes,
			Map<String, Map<String,BasePojo>> outGzs, IgnoreManager ignoreManager, List<ReveResult> reveResult) throws Exception {
		List<KmMap> innerKms = null;
		List<KmMap> outKms = null;
		List<BasePojo> erCompareGZs = null;
		List<BasePojo> erCompareGzOuts = null;
		Map<String, BasePojo> tghMap = null;
		Set<String> tghCodes = outGzs.keySet();
		//匹配时要移除本方科目代码后面的后缀
		for(KmRelaRecord krr : kms.values())
		{
			innerKms = krr.getList_KM_INNER()==null?new ArrayList<KmMap>():krr.getList_KM_INNER();
			outKms = krr.getList_KM_OUT()==null?new ArrayList<KmMap>():krr.getList_KM_OUT();
			erCompareGZs = new ArrayList<BasePojo>();
			for(KmMap km : innerKms)
			{
				//移除本方科目代码后面的后缀
				String code = km.getC_KM_CODE().trim();
				//设置过科目映射的科目不一定有数据
				if(innerYes.containsKey(code))
				{
					erCompareGZs.add(innerYes.get(code));
					//移除比对过的科目
					innerYes.remove(code);
				}
			}
			erCompareGzOuts = new ArrayList<BasePojo>();
			for(KmMap km : outKms)
			{
				String kmCode = km.getC_KM_CODE();
				String tghCode = km.getC_TGH_CODE();
				//从对应托管行取数据
				if(outGzs.containsKey(tghCode))
				{
					tghMap = outGzs.get(tghCode);
					//设置过科目映射的不一定有数据
					if(tghMap.containsKey(kmCode))
					{
						BasePojo basePojo = tghMap.get(kmCode);
						erCompareGzOuts.add(basePojo);
						tghMap.remove(kmCode);
					}
				}else//遍历所有数据
				{
					for(String s : tghCodes)
					{
						tghMap = outGzs.get(s);
						//设置过科目映射的不一定有数据
						if(tghMap.containsKey(kmCode))
						{
							BasePojo basePojo = tghMap.get(kmCode);
							erCompareGzOuts.add(basePojo);
							tghMap.remove(kmCode);
						}
					}
				}
			}
			compareAllItem(items, erCompareGZs, erCompareGzOuts, reveResult, ignoreManager);
		}//end for
	}
	
	protected void handleMultTghNoMapData(Map<String, String> items,
			Map<String, BasePojo> innerGzs,
			Map<String, Map<String,BasePojo>> outDatas,
			IgnoreManager ignoreManager,
			List<ReveResult> reslist) throws Exception 
	{
		List<BasePojo> CompareErGzs = new ArrayList<BasePojo>();
		List<BasePojo> CompareErGzOuts = new ArrayList<BasePojo>();
		Set<Entry<String, BasePojo>> innerSets = innerGzs.entrySet();
		Iterator<Entry<String, BasePojo>> innerIt = innerSets.iterator();
		while(innerIt.hasNext())
		{
			Entry<String, BasePojo> entry = innerIt.next();
			CompareErGzOuts.clear();
			CompareErGzs.clear();
			CompareErGzs.add(entry.getValue());
			compareAllItem(items, CompareErGzs, CompareErGzOuts, reslist,ignoreManager);
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
				CompareErGzOuts.clear();
				CompareErGzs.clear();
				CompareErGzOuts.add(entry.getValue());
				compareAllItem(items, CompareErGzs, CompareErGzOuts, reslist,ignoreManager);
				outIt.remove();
			}
		}
	}
	protected void compareMultTghAutoMapData(Map<String, String> items,
			Map<String, BasePojo> innerGzs,
			Map<String, Map<String,BasePojo>> outGzs,
			IgnoreManager ignoreManager,
			List<ReveResult> reslist) throws Exception 
	{
		Iterator<Entry<String, BasePojo>> innerIt = innerGzs.entrySet().iterator();
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
			//多个托管行都要匹配
			for(String tghCode : outGzs.keySet())
			{
				Map<String, BasePojo> tghMap = outGzs.get(tghCode);
				kmCodeOut = getKmCodeOutByKmCode(entry.getKey(), tghMap.keySet());
				if(kmCodeOut != null && !"".equalsIgnoreCase(kmCodeOut.trim()))
				{
					BasePojo erGzbOut =  tghMap.get(kmCodeOut);
					gzOuts.add(erGzbOut);
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
							gzOuts.add(erGzbOut);
							//移除比对过的科目
							tghMap.remove(zbName);
						}
					}
					
				}
			}
			
			compareAllItem(compareItems, gzs, gzOuts, reslist, ignoreManager);
			//移除比对过的科目
			innerIt.remove();
		}
		
	}
	
	@Override
	public Map<String, Map<String,BasePojo>> collectMultTghData(List<AssMap> assMaps)
			throws Exception {
		Map<String, Map<String,BasePojo>> map = new HashMap<String, Map<String,BasePojo>>();
		for(AssMap ass : assMaps)
		{
			Map<String, BasePojo> gzdata = new HashMap<String, BasePojo>();
			Map<String, BasePojo> speGzdata = new HashMap<String, BasePojo>();
			String tghCode = ass.getC_TGH_CODE();
			Map<String, BasePojo> outDatas = this.dataCompareDao.getOutData(ass.getC_PORT_CODE_OUT(),gzDate,tghCode,rptType);
			if(outDatas == null || outDatas.size() == 0)
			{
				throw new Exception("缺少外部数据！");
			}
			ErGzbOut gz = null;
			//只对比科目代码不为空的项
			//指标项需要比对的值放到名称列的需要特殊处理
			for(BasePojo pojo : outDatas.values())
			{
				gz = (ErGzbOut) pojo;
				if(gz != null && !StringUtil.IsNullOrEmptyT(gz.getC_KM_CODE()))
				{
					gz.setC_KM_CODE(gz.getC_KM_CODE().trim());
					
					if(isSpecialZbItem(gz.getC_KM_NAME().trim()))
					{
						gz.setC_KM_NAME(gz.getC_KM_NAME().trim());
						//STORY59971【景顺长城基金】【道富QD】【紧急】估值表核对增加原币和指标的核对
						//3、调整托管行估值表的total all（科目名称列展示数据的指标项）的指标项在 本币市值列与我放数据进行核对。
						gz.setN_PORT_MV(gz.getC_KM_NAME());
						speGzdata.put(gz.getC_KM_CODE(), gz);
					}else
					{
						gzdata.put(gz.getC_KM_CODE(), gz);
					}
				}
			}
			map.put(tghCode, gzdata);
			this.multSpecialGzZbsOut.put(tghCode, speGzdata);
		}
		return map;
	}
	@Override
	public String getTghCodeFromOutObject(BasePojo gzOut) {
		String result = "";
		if(gzOut != null)
		{
			ErGzbOut gzb = (ErGzbOut) gzOut;
			if(gzb.getC_TGH_CODE() != null)
			{
				result = gzb.getC_TGH_CODE();
			}
		}
		return result;
	}
	
	
}
