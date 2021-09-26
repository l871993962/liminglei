package com.yss.uco.elecreco.er.generate.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.YssFun;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;
import com.yss.uco.elecreco.support.util.UcoDspParamCodeEnum;



public class KMTransUtil {
	//只转换证券代码
	public final static int ZHGZ_ZQDM = 1;
	//只转换交易渠道
	public final static int ZHGZ_JYQD = 2;
	//证券代码和交易渠道都转换
	public final static int ZHGZ_ALL = 3;
	//证券代码和交易渠道都不转换
	public final static int ZHGZ_BZH = 0;
	
	private static Logger logger = LogManager.getLogger(KMTransUtil.class);
	
	public static String transKMCode(String oldKm,Map<String,String> kvs)
	{
		//BUG237867生成电子对账估值表数据今日单位净值科目代码取值错误
		//一级科目非证券代码或者渠道代码，无需替换
		if(oldKm.lastIndexOf('.') == -1)
		{
			return oldKm;
		}
		String zqdm = oldKm.substring(oldKm.lastIndexOf('.')+1);
		if(kvs.containsKey(zqdm))
		{
			String km = kvs.get(zqdm);
			oldKm = oldKm.replace(zqdm,km);
			//20171120 WLX BUG #180679  （紧急）【嘉实基金】社保组合回购品种生成电子对账报文时需要转换证券代码 社保组合回购品种的证券代码为：R001,R007,R032等
			//20171219 WLX  BUG #184065::（紧急）【嘉实基金】电子对账证券代码转换错误
			oldKm = oldKm.replace(".R", ".000");
			return oldKm;
		}else
		{
			return oldKm;
		}
	}
	/**
	 * 根据转换规则获取对应的Map
	 * */
	public static Map<String, String> getTransMap(Connection conn,int gzCode) throws Exception
	{
		
		Map<String, String> allmap = new HashMap<String, String>();
		Map<String, String> zqmap = new HashMap<String, String>();
		Map<String, String> qdmap = new HashMap<String, String>();
		String sql = " SELECT C_SEC_CODE,C_PUB_CODE,C_TYPE FROM T_D_MP_SEC_TRANSFER where c_transfer_code = 'TRAN_SBLSH' and N_CHECK_STATE = 1 ";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next())
			{
				allmap.put(rs.getString(1), rs.getString(2));
				if("R".equalsIgnoreCase(rs.getString(3).trim()))
				{
					qdmap.put(rs.getString(1), rs.getString(2));
				}else if("P".equalsIgnoreCase(rs.getString(3).trim()))
				{
					zqmap.put(rs.getString(1), rs.getString(2));
				}
			}
			if(KMTransUtil.ZHGZ_ALL == gzCode)
			{
				return allmap;
			}else if(KMTransUtil.ZHGZ_ZQDM == gzCode)
			{
				return zqmap;
			}else if(KMTransUtil.ZHGZ_JYQD == gzCode)
			{
				return qdmap;
			}else if(KMTransUtil.ZHGZ_BZH == gzCode)
			{
				return new HashMap<String, String>();
			}
		}catch(Exception e){
			logger.error("获取转换规则出错！", e);
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return new HashMap<String, String>();
	}
	
	/**
	 *根据SV_BB_ZQDMZH，SV_BB_JYQDZH参数值获取转换规则
	 */
	public static int getGzCode(String zhcs,String qdgz){
		//String zhcs = paras.actSummaryParams.get("SV_BB_ZQDMZH");
		//String qdgz = paras.actSummaryParams.get("SV_BB_JYQDZH");   //交易渠道转换规则   modify by guohui STORY40652【南方基金】
		//当前数据对应的组合、日期下的证券转换规则参数值为不转换
		//交易证券和交易渠道是否转换分别受参数控制，下面临时做分情况处理  modify by guohui
		if((zhcs == null && qdgz == null) || ("TRAN_BZH".equalsIgnoreCase(zhcs) && "TRAN_BZH".equalsIgnoreCase(qdgz))){
			return KMTransUtil.ZHGZ_BZH;
		}else if(zhcs != null && !"TRAN_BZH".equalsIgnoreCase(zhcs) && "TRAN_BZH".equalsIgnoreCase(qdgz)){
			return KMTransUtil.ZHGZ_ZQDM;
		}else if(qdgz != null && "TRAN_BZH".equalsIgnoreCase(zhcs) && !"TRAN_BZH".equalsIgnoreCase(qdgz)){
			return KMTransUtil.ZHGZ_JYQD;
		}else if(zhcs != null && qdgz != null && !"TRAN_BZH".equalsIgnoreCase(zhcs) && !"TRAN_BZH".equalsIgnoreCase(qdgz)){
			return KMTransUtil.ZHGZ_ALL;
		}
		return KMTransUtil.ZHGZ_BZH;
	}
	
	/**
	 *根据SV_BB_ZQDMZH，SV_BB_JYQDZH参数值获取转换规则
	 */
	public static int  getGzCode(Connection conn,String cPortCode,String dDate){
		try{
			//获取参数值
			AdmPortActParams paras = new AdmPortActParams(cPortCode, YssFun.parseDate(dDate));
			paras.setDbConn(conn);
			paras.initActParams();
			String zhcs = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_ZQDMZH);
			String qdgz = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_JYQDZH);   //交易渠道转换规则   modify by guohui STORY40652【南方基金】
			//当前数据对应的组合、日期下的证券转换规则参数值为不转换
			//交易证券和交易渠道是否转换分别受参数控制，下面临时做分情况处理  modify by guohui
			if((zhcs == null && qdgz == null) || ("TRAN_BZH".equalsIgnoreCase(zhcs) && "TRAN_BZH".equalsIgnoreCase(qdgz))){
				return KMTransUtil.ZHGZ_BZH;
			}else if(zhcs != null && !"TRAN_BZH".equalsIgnoreCase(zhcs) && "TRAN_BZH".equalsIgnoreCase(qdgz)){
				return KMTransUtil.ZHGZ_ZQDM;
			}else if(qdgz != null && "TRAN_BZH".equalsIgnoreCase(zhcs) && !"TRAN_BZH".equalsIgnoreCase(qdgz)){
				return KMTransUtil.ZHGZ_JYQD;
			}else if(zhcs != null && qdgz != null && !"TRAN_BZH".equalsIgnoreCase(zhcs) && !"TRAN_BZH".equalsIgnoreCase(qdgz)){
				return KMTransUtil.ZHGZ_ALL;
			}
		}catch (Exception e) {
			logger.error("获取规则代码出错:" + e.getMessage(), e);
		}
		return KMTransUtil.ZHGZ_BZH;
	}
}
