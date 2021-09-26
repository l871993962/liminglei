package com.yss.uco.elecreco.er.generate.dblgzb;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.dayf.actProvider.statistic.asset.pojo.Asset;
import com.yss.dayf.actProvider.statistic.asset.service.IAssetStatsService;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssD;
import com.yss.framework.api.util.YssFun;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;
import com.yss.uco.elecreco.er.erdblgz.service.IErDblgzbService;
import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.er.generate.service.GeneElecDataService;
import com.yss.uco.elecreco.er.generate.util.ElecRelaManager;
import com.yss.uco.elecreco.er.generate.util.KMTransUtil;
import com.yss.uco.elecreco.support.bean.ElecRela;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;

public class GeneDBLGZBDataService extends GeneElecDataService{
	protected static final String C_FILE_TYPE = "1013";
	protected static final String C_RPT_TYPE = "01";
	protected static final int DETAIL_TYPE = 1;
	protected static final int NAV_TYPE = 2;
	protected static final int YGZ_TYPE = 3;
	protected static final String GZB_YGZ = "GZBFSMS_YGZ";
	
	protected static final String TOTAL_ALL = "TOTAL_ALL";
	protected static final String TOTAL = "TOTAL";
	//南方基金社保产品的估值表C_KEY_CODE指标代码后面加“SB”,造成跟对账指标关联不上
	private boolean isTranSB = false;
	/**
	 *   STORY #51238 （紧急）【嘉实基金】社保估值表指标问题导致电子对账不一致
	 */
	private boolean DZ_BB_DZDZ_XJZBFS = false;
	//add by xiaomenglei STORY #44562 【鹏华基金】【紧急】社保电子对账，证券投资市值列是否加溢折价，需要增加参数控制，关联STORY #39277 ，在新增一个参数。
	private boolean addYzj = false;
	
	/**
	 * STORY #80041双估值电子对账双估值表剔除持有至到期估值增值科目
	 */
	private boolean deleteCYDQ = false;
	
	/**
	 * STORY #48443 嘉实基金电子对账-货币类组合溢折价市值列发送时需要取成本列的值发送 
	 * 【产品估值参数】增加参数“电子对账数据溢折价市值列取估值表成本值”(DZ_BB_DZDZ_YZJSZQZ)，选项“是、否”，默认为“否”。
		“否”：生成电子对账数据时，溢折价市值列取估值表中“市值”的值（现有模式）。
		“是”：生成电子对账数据时，溢折价市值列取估值表中“成本”的值。
	 */
	private boolean YZJSZQZ = false;
	
	//南方基金交行非明细科目需要生成证券数量
	private boolean isGeneAmount = false;

	/**
	 * STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。 添加转换规则Map
	 * */
	private Map<String, String> kmTransMap = new HashMap<String, String>();
	private boolean isTransKM = false;
	/**
	 * BUG211964电子对账个性指标设置取值逻辑不对
	 */
	protected HashMap<String, ElecPerRela> elecPerRelaMap = null;
	
	/**
	 * BUG #183027 【嘉实基金】社保组合可分配收益发送时是否发送净值占市值比例
	 */
	private boolean DZ_BB_DZDZ_KFPSY = true;
		/**
	 * 是否将1111.科目代码转换为1103.科目代码
	 * BUG217588科目代码1111.开头的科目类别与托管行的1103.开头的科目类别匹配，在生成对账数据时增加参数控制是否将1111.科目代码替换为1103.
	 */
	private boolean trans1111KmCode = false;
	
	private Map<String,ErKmb> kmInfos = null;
	
	protected ElecRelaManager elecRelaManager = null;
	
	/**
	 * key：科目代码
	 */
	protected Map<String,ErDblgzb> gzDatas = new HashMap<String, ErDblgzb>();//所有数据
	
	/**
	 * 比例保留位数
	 */
	protected int scale = 4;
	
	protected AdmPortActParams paras = null;
	
	@Override
	public Map<String, Object> geneElecData() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = "";
		String resultDetail = "";
		try {
			long startTime = System.currentTimeMillis();
			//BUG211964电子对账个性指标设置取值逻辑不对
			initElecPerRelaMap();
			long zbTime = System.currentTimeMillis();
			appendLogDetail(String.format("初始化电子对账个性指标设置(耗时：%d)", zbTime - startTime));
			//获取参数值
			paras = new AdmPortActParams(this.portCode, new Date());
			paras.setDbConn(conn);
			paras.initActParams();
			int transGZ = KMTransUtil.getGzCode(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_ZQDMZH), paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_JYQDZH));
			if(transGZ != KMTransUtil.ZHGZ_BZH)
			{
				isTransKM = true;
				kmTransMap = KMTransUtil.getTransMap(conn,transGZ);
			}else
			{
				isTransKM = false;
			}
			long paraTime = System.currentTimeMillis();
			appendLogDetail(String.format("初始化电子对账综合参数(耗时：%d)", paraTime - zbTime));
			this.createFsn();//生成对账序号
			long snTime = System.currentTimeMillis();
			appendLogDetail(String.format("初始化报文序号(耗时：%d)：%s", snTime - paraTime, this.fsn));
			this.Pro_GetPlan();//获取核算方案代码及科目体系
			long planTime = System.currentTimeMillis();
			appendLogDetail(String.format("初始化核算方案(耗时：%d)", planTime - snTime));
			appendLogDetail(String.format("核算方案：%s 科目级别：%d", this.planCode, this.kmLevel));
			this.getAssCode();//获取资产代码
			long assTime = System.currentTimeMillis();
			appendLogDetail(String.format("初始化资产代码(耗时：%d)：%s", assTime - planTime, this.assCode));
			Map<String, Object> dzPara = this.getDZPara();//获取对账参数
			long dzTime = System.currentTimeMillis();
			appendLogDetail(String.format("初始化电子对账参数(耗时：%d)", dzTime - assTime));
			appendLogDetail(String.format("电子对账参数：%s", dzPara.toString()));
			this.getAmountPara();//
			long getParaTime = System.currentTimeMillis();
			appendLogDetail(String.format("获取电子对账综合参数(耗时：%d)", getParaTime - dzTime));
//			this.getSSZB();//查询实收资本数量
			this.getZCJZ();//查询资产净值成本，资产净值等
			long zcjzTime = System.currentTimeMillis();
			appendLogDetail(String.format("获取资产净值成本(耗时：%d)", zcjzTime - getParaTime));
			this.kmInfos = this.getKmInfos(this.portCode, geneDate);
			long kmTime = System.currentTimeMillis();
			appendLogDetail(String.format("初始化科目体系(耗时：%d)", kmTime - zcjzTime));
			elecRelaManager = new ElecRelaManager();
			elecRelaManager.initDblGz(conn, this.portCode);
			long relaTime = System.currentTimeMillis();
			appendLogDetail(String.format("初始化对账指标关联设置(耗时：%d)", relaTime - kmTime));
			
			Map<String,ErDblgzb> map = new HashMap<String, ErDblgzb>();
			List<Asset> gzbs = this.getGzbData(this.portCode,this.geneDate);//生成明细项数据
			if(gzbs != null && gzbs.size() > 0)//转换数据
			{
				for (Asset asset : gzbs) {
					//STORY80041双估值电子对账双估值表剔除持有至到期估值增值科目	估值表中也会存估增数据
					if(this.deleteCYDQ)
					{
						//根据 【核算项目=证券投资_公允价值，投资分类=持有到期类】去掉此类持有至到期估值增值科目（行）
						if("ZQTZ_GYBD".equalsIgnoreCase(asset.getC_DAI_CODE()) && "IC_HM".equalsIgnoreCase(asset.getC_DV_INVEST_CLS()))
						{
							continue;
						}
					}
					if(TOTAL.equalsIgnoreCase(asset.getC_NAV_TYPE()) || TOTAL_ALL.equalsIgnoreCase(asset.getC_NAV_TYPE()))
					{
						List<ErDblgzb> list = getTotalErDblgzb(asset, elecRelaManager);
						if(list == null || list.size() == 0)
						{
							continue;
						}
						for (ErDblgzb erDblgzb : list) {
							boolean res = handleTotal(erDblgzb);
							if(!res)
							{
								continue;
							}
							String kmCode = erDblgzb.getC_KM_CODE();
							if(!StringUtil.IsNullOrEmptyT(erDblgzb.getC_PORT_CLS_CODE()))
							{
								kmCode = kmCode + "_" + erDblgzb.getC_PORT_CLS_CODE();
							}
							this.gzDatas.put(kmCode, erDblgzb);
						}
					}else
					{
						ErDblgzb gz = getErDblgzb(asset);
						if(gz == null)
						{
							continue;
						}
						upSum(gz,getParentKmCode(gz), map);//向上汇总
						String kmCode = gz.getC_KM_CODE();
						if(this.gzDatas.containsKey(gz.getC_KM_CODE()))
						{
							gzDatas.put(kmCode, dataSum(gzDatas.get(kmCode), gz));
						}else
						{
							this.gzDatas.put(gz.getC_KM_CODE(), gz);
						}
						this.gzDatas.putAll(map);
					}
				}
				
			}
			long gzTime = System.currentTimeMillis();
			appendLogDetail(String.format("生成双估值表数据(耗时：%d)", gzTime - relaTime));
			if(GZB_YGZ.equals(this.C_GZB_MODE)){
				List<Asset> gzs = this.getGzData(this.portCode,this.geneDate);//生成明细项数据
				if(gzs != null && gzs.size() > 0)//转换数据
				{
					Map<String,ErDblgzb> temp = new HashMap<String, ErDblgzb>();
					for (Asset basePojo : gzs) {
						if(gzDatas.containsKey(basePojo.getC_KM_CODE()))//估值表中已经获取到了，就不需要再次生成
						{
							continue;
						}
						//STORY80041双估值电子对账双估值表剔除持有至到期估值增值科目
						if(this.deleteCYDQ)
						{
							//根据 【核算项目=证券投资_公允价值，投资分类=持有到期类】去掉此类持有至到期估值增值科目（行）
							if("ZQTZ_GYBD".equalsIgnoreCase(basePojo.getC_DAI_CODE()) && "IC_HM".equalsIgnoreCase(basePojo.getC_DV_INVEST_CLS()))
							{
								continue;
							}
						}
						ErDblgzb gz = getErDblgzb(basePojo);
						if(gz == null)
						{
							continue;
						}
						temp.put(gz.getC_KM_CODE(), gz);
						String parentKmCode = getParentKmCode(gz);
						if(gzDatas.containsKey(parentKmCode))//估值表中已经获取到了，就不需要再次向上汇总生成
						{
							continue;
						}
						upSum(gz,parentKmCode,temp,1);
					}
					addYgzToDatas(gzDatas,temp);
				}
				long ygzTime = System.currentTimeMillis();
				appendLogDetail(String.format("生成估增数据(耗时：%d)", ygzTime - gzTime));
				gzTime = ygzTime;
			}
			IErDblgzbService erDblgzbService = YssServiceFactory.getInstance().createService(IErDblgzbService.class);
			ArrayList<ErDblgzb> list = new ArrayList<ErDblgzb>();
			list.addAll(this.gzDatas.values());
			erDblgzbService.insertDatas(list, conn);
			long insertTime = System.currentTimeMillis();
			appendLogDetail(String.format("持久化数据(耗时：%d)", insertTime - gzTime));
			updateGzbData(paras);
			appendLogDetail(String.format("调整最终数据(耗时：%d)", System.currentTimeMillis() - gzTime));
			
			if(gzDatas.size() > 0){
				this.geneErInfo(C_FILE_TYPE, C_RPT_TYPE);//插入生成记录
				this.geneErInfoHisAndInst(C_FILE_TYPE, C_RPT_TYPE);//生成历史进行记录
				result = this.fsn;
				resultDetail = "电子对账双估值表数据生成成功！";
			}else{
				result = "Warn";
				resultDetail = "没有数据生成！";
			}
			
		} catch (Exception e) {
			result = "Fail";
			resultDetail = e.getMessage();
			logger.error("生成双估值表数据出错：" + e.getMessage(), e);
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("resultDetail", resultDetail);
		return resultMap;
	}
	
	protected void addYgzToDatas(Map<String, ErDblgzb> gzDatas,
			Map<String, ErDblgzb> temp) {
		for (String key : temp.keySet()) {
			ErDblgzb value = handleYgzData(temp.get(key));
			if(value != null)
			{
				gzDatas.put(key, value);
			}
		}
		
	}

	protected ErDblgzb handleYgzData(ErDblgzb erDblgzb) {
		if(erDblgzb == null)
		{
			return null;
		}
		//根据产品估值参数“电子对账数据估增4级明细科目市值列为0”，判断是否更新市值列
		String sb4 = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_GZKMSZ); 
		if(sb4!= null && "1".equals(sb4)){
			this.updateGZBDetailGzsz4(erDblgzb);
		}
		//根据产品估值参数“电子对账数据估增3级汇总科目市值列为0”，判断是否更新市值列
		String sb3 = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_GZSJKMSZ); 
		if(sb3 != null && "1".equals(sb3)){
			this.updateGZBDetailGzsz3(erDblgzb);
		}
		//BUG225877富国基金-【公募】可退替代估增没有发送出市值列的值
		String etfSz = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_GZETFSZ);//电子对账数据1102.99,1103.99科目市值列为0
		if(!(etfSz!=null && etfSz.equals("0"))){
			this.updateETFGZKm(erDblgzb);//BUG190590嘉实基金-电子对账-110299科目重复
		}
		String gzDetail = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_GZSFZMXKM);//更换上下级明细标识
		if (gzDetail != null && gzDetail.equals("1")) {
			//不做操作
		} else {
			//明细数据和非明细数据调换
			if(1 == erDblgzb.getN_DETAIL())
			{
				erDblgzb.setN_DETAIL(0);
			}else
			{
				erDblgzb.setN_DETAIL(1);
			}
			
		}
		return erDblgzb;
	}

	/**
	 * 查询资产净值成本，资产净值，估值增值；用于计算成本占净值比例，市值占净值比例
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getZCJZ()throws Exception{
		Map<String, Object> ZCJZ = new HashMap<String, Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			StringBuilder sqlString = new StringBuilder();
			sqlString.append(" select A.N_PORT_COST, A.N_PORT_MV, A.N_PORT_IV from T_R_FR_ASTSTAT_DBL A ");
			sqlString.append(" where A.c_port_code = ? and A.D_ASTSTAT = to_date(?, 'yyyy-MM-dd')");
			sqlString.append(" and A.C_KEY_CODE = 'ZCJZ' and A.C_NAV_TYPE = 'TOTAL' AND INSTR(C_KM_CODE, '_') = 0 ");//wlx 20171127 BUG181621嘉实基金-电子对账核对后-比例不一致,分级产品的资产净值KEYCODE也是ZCJZ 所以加上KMCODE筛选母级产品资产净值
			pst = conn.prepareStatement(sqlString.toString());
			pst.setString(1, this.portCode);
			pst.setString(2, geneDate);
			rs = pst.executeQuery();
			if(rs.next()){
				this.N_PORT_COST = rs.getDouble(1);
				this.N_PORT_MV = rs.getDouble(2);
				this.N_PORT_IV = rs.getDouble(3);
				ZCJZ.put("N_PORT_COST", this.N_PORT_COST);
				ZCJZ.put("N_PORT_MV", this.N_PORT_MV);
				ZCJZ.put("N_PORT_IV", this.N_PORT_IV);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return ZCJZ;
	}
	
	protected boolean handleTotal(ErDblgzb gz) {
		String code = gz.getC_KM_CODE();
		ElecPerRela perRelaPojo = elecPerRelaMap.get(code) == null ? new ElecPerRela() : elecPerRelaMap.get(code);
		if(perRelaPojo != null && "0".equals(perRelaPojo.getC_SEND_MODE())){
			return false;
		}
		
		//BUG #183049 （紧急）【嘉实基金】社保组合估值表中为0的指标不需要生成电子对账  损益平准-未实现 损益平准-已实现
		if(this.isTranSB && ("0106".equalsIgnoreCase(code)||"0107".equalsIgnoreCase(code))){
			return false;
		}
		
//		 STORY #51238 （紧急）【嘉实基金】社保估值表指标问题导致电子对账不一致
		if(!this.DZ_BB_DZDZ_XJZBFS && "0089".equals(code)){
			return false;
		}
		
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE5())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE5()))) {
			gz.setN_VA_PRICE(BigDecimal.ZERO);
		}
		//数量
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE1())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE1()))) {
			gz.setN_AMOUNT(BigDecimal.ZERO);
		}
		
		//成本
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE2())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE2()))) {
			gz.setN_PORT_COST(BigDecimal.ZERO);
		}
		
		//市值
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE3())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE3()))) {
			gz.setN_PORT_MV(BigDecimal.ZERO);; //指标发送模式为不发送或者0值发送 置0
		}
		
		// <End>: modified by HeLiang 2017-09-04 STORY #46410 【易方达基金】【紧急】电子对账生成报文 基金资产单位净值 需要增加参数控制保留小数位数
		//估值增值
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE6())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE6()))) {
			gz.setN_PORT_IV(BigDecimal.ZERO);; //指标发送模式为不发送或者0值发送 置0
		}
		
		//成本占净值比例
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE7())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE7()))) {
			gz.setN_CB_JZ_BL(BigDecimal.ZERO);
		}
		
		//BUG #183027 【嘉实基金】社保组合可分配收益发送时是否发送净值占市值比例
		//市值占净值比
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE8())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE8()))) {
			gz.setN_SZ_JZ_BL(BigDecimal.ZERO);
		}else{
			if(!this.DZ_BB_DZDZ_KFPSY && "0021".equals(code)){
				gz.setN_SZ_JZ_BL(BigDecimal.ZERO);
			}
		}
		
		return true;
	}


	private void updateGzbData(AdmPortActParams paras) throws Exception {
		
		if(addYzj){
			this.updateGZBDetailAddYZJ();
		}
		
		//BUG274559【鹏华基金】参数电子对账数据成本列加上溢折价这个参数控制了市值发送，导致双估值表发送的市值不对
		//当电子对账数据成本列是否加上溢折价为否，证券代码转换规则为“社保理事会债券代码转换规则”同时成立时，'1111'、'1132'科目更新市值列=成本列、市值占比=成本占比
		if(!addYzj&&isTranSB){
			this.updateGZBDetailSzDyCb();
		}
		//经现场测试，不需要该参数，屏蔽掉该参数
//		//add by xiaomenglei STORY #45854 【鹏华基金】【紧急】社保电子对账，估值表持有到期类成本类科目发送时，行情不是估值表展示的行情，而是直接取的面值100，需要有参数进行控制。 
//		String hqqz = paras.actSummaryParams.get("SV_BB_DZDZ_CYDALHQ");
//		if(hqqz!=null && hqqz.equals("1")){
//			this.updateGZBDetailHq();
//		}
		
		//add by xiaomenglei STORY #45858 【鹏华基金】【紧急】 社保电子对账，证券投资成本科目发送时，估值表展示如果是无行情，取单位成本，需要增加参数控制。 
		String whq = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_WHQQDWCB);
		if(whq!=null && whq.equals("1")){
			this.updateGZBDetailWhqDetail();
		}
		//add by xiaomenglei STORY #46220 【鹏华基金】增加参数控制，电子对账数据净值增长率指标是否取本期净值增值率
		String jzzzl = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_JZZZL);
		if(jzzzl != null && jzzzl.equals("1")){
			this.updateGZBJzzzlDetail();
		}
		//add by xiaomenglei STORY #46241 【鹏华基金】【紧急】养老金电子对账，溢折价明细科目是否发送，需要增加参数控制。
		String yzjmxkm = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_YZJMXKM);
		if(yzjmxkm != null && yzjmxkm.equals("0")){
			this.updateGZBYzjmxkmDetail();
		}
		//BUG214534【招商基金】工行社保产品电子对账发送0016基金资产单位净值需要发送估值增值列数据
		String dwjziv = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZIV);
		if(dwjziv != null && "1".equals(dwjziv)){
			this.updateDWJZIV();
		}
		//【招商基金】持有到期类债券投资1111开头的 第三级科目的 市值 = 成本
		String mvcblevel3 = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_MVCBLEVEL3);
		if(mvcblevel3 !=null && "1".equals(mvcblevel3)){
			this.updateGZBMVCBLevel3();
		}
		
		trans1111KMCode();
		
		/**
		* STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。 添加转换规则Map
		* */
		transKMCode();
		
		/**
		 * STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化 数据insert之后
		 * 通过组合代码取到科目长度限制，若不为空对则对科目名称长度大于科目长度限制的科目名称进行截取
		 * @author cls
		 * @date 2018年8月13日
		 * */
		KMNameSub();
	}

	/**
	 * STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化 数据insert之后
	 * 通过组合代码取到科目长度限制，若不为空对则对科目名称长度大于科目长度限制的科目名称进行截取
	 * 
	 * @author cls
	 * @date 2018年8月13日
	 */
	private void KMNameSub() {

		PreparedStatement pst = null;
		PreparedStatement updatePst = null;
		ResultSet rs = null;
		try {
			StringBuilder updateBuilder = new StringBuilder();
			
			if (!StringUtil.IsNullOrEmpty(C_KM_NAME_LENGTH)) {
				int kmLength = Integer.parseInt(C_KM_NAME_LENGTH);
				//取负
				int ngkmLength=-kmLength;
				updateBuilder
						.append("  UPDATE T_D_ER_DBLGZ SET C_KM_NAME = SUBSTR(C_KM_NAME,"+ ngkmLength + ") ")
						.append("  WHERE     C_SN = ?  AND  C_ASS_CODE = ? AND D_START_DATE = ? ")
						.append("  AND  LENGTH(C_KM_NAME) > " + kmLength);
				updatePst = conn.prepareStatement(updateBuilder.toString());

				int index = 1;
				updatePst.setString(index++, this.fsn);
				updatePst.setString(index++, this.assCode);
				updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
				updatePst.execute();
			}

		} catch (Exception e) {
			logger.error("截取科目名称长度出错 ：" + e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(updatePst);
			DbFun.closeStatementFinal(pst);
		}

	}
	
	/**
	 * 【招商基金】持有到期类债券投资1111开头的 第三级科目的 市值 = 成本
	 * @throws Exception
	 */
	private void updateGZBMVCBLevel3() throws Exception {
		PreparedStatement pst = null;
		try {
			String updateSql = " UPDATE T_D_ER_DBLGZ A SET A.N_PORT_MV=A.N_PORT_COST,A.N_SZ_JZ_BL = A.N_CB_JZ_BL WHERE A.C_KM_CODE LIKE '1111%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_CODE) = 3 AND A.C_ASS_CODE=? AND A.C_SN= ? AND A.D_START_DATE = ? "
					+"           AND A.C_KM_CODE IN(SELECT PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(B.C_KM_CODE, 3) FROM t_r_Fr_Aststat_dbl B WHERE B.C_PORT_CODE = ?"
					+"		      AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_CB' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')"
					+"		      AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)";
			pst = conn.prepareStatement(updateSql);
			int index = 1;
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.setString(index++, this.portCode);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("持有到期类债券投资1111开头的 第三级科目的 市值 = 成本出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * BUG214534【招商基金】工行社保产品电子对账发送0016基金资产单位净值需要发送估值增值列数据
	 * 修改0016指标估值增值列=市值-成本
	 * @throws Exception
	 */
	private void updateDWJZIV() throws Exception {
		PreparedStatement pst = null;
		try {
			String updateSql = " UPDATE T_D_ER_DBLGZ A SET A.N_PORT_IV = A.N_PORT_MV - A.n_Port_Cost, A.C_KM_NAME = '基金资产单位净值' WHERE A.C_KM_CODE in('0016') AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
			pst = conn.prepareStatement(updateSql);
			int index = 1;
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("更新修改0016指标估值增值列=市值-成本！", e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	/**
	 * 初始化电子对账指标设置
	 */
	protected void initElecPerRelaMap() {
		HashMap<String, ElecPerRela> map = this.getPerRelaByPortAndDZCode(portCode, "1013");
		if(map != null)
		{
			this.elecPerRelaMap = map;
		}else//防止空指针
		{
			this.elecPerRelaMap = new HashMap<String, ElecPerRela>();
		}
	}
	/**
	 * BUG190590嘉实基金-电子对账-110299科目重复
	 * 删除1102.99,1103.99科目
	 * @throws Exception
	 */
	private void deleteETFGZKm() throws Exception{
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append(" DELETE FROM T_D_ER_DBLGZ   ")
					.append(" WHERE C_IDEN IN ( ")
					.append(" SELECT MIN(C_IDEN)  FROM T_D_ER_DBLGZ WHERE ")
					.append(" C_SN = ? AND C_ASS_CODE = ? AND D_START_DATE = ? AND C_KM_CODE IN('1102.99','1103.99')")
					.append("  GROUP BY C_KM_CODE ")
					.append("   HAVING COUNT(1) > 1 ) ");
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, this.fsn);
			pst.setString(2, this.assCode);
			pst.setDate(3, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("删除1102.99,1103.99科目出错！", e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	/**
	 * BUG190590嘉实基金-电子对账-110299科目重复
	 * 更新1102.99,1103.99估增科目市值列，市值占比
	 * @param erDblgzb 
	 * @throws Exception
	 */
	private void updateETFGZKm(ErDblgzb erDblgzb) {
		if("1102.99".equals(erDblgzb.getC_KM_CODE()) || "1103.99".equals(erDblgzb.getC_KM_CODE()))
		{
			erDblgzb.setN_PORT_MV(BigDecimal.ZERO);
			erDblgzb.setN_SZ_JZ_BL(BigDecimal.ZERO);
		}
	}
	
	private void updateGZBYzjmxkmDetail() throws Exception{
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
	    try {
	      sql.append(" DELETE FROM T_D_ER_DBLGZ WHERE  ");
	      sql.append(" C_SN = ? AND C_ASS_CODE = ? AND D_START_DATE = ? AND C_KM_CODE IN( ");
	      sql.append(" SELECT A.C_KM_CODE FROM  T_D_ER_DBLGZ a ");
	      sql.append(" LEFT JOIN t_r_Fr_Aststat_dbl B ON A.D_START_DATE = B.D_ASTSTAT AND A.C_KM_CODE = B.C_KM_CODE AND B.C_PORT_CODE = ? ");
	      sql.append(" WHERE a.C_SN = ? AND B.C_DAI_CODE = 'ZQTZ_YZJ' ) ");
	      pst = conn.prepareStatement(sql.toString());
	      pst.setString(1, this.fsn);
	      pst.setString(2, this.assCode);
	      pst.setDate(3, YssFun.toSqlDate(geneDate));
	      pst.setString(4, portCode);
	      pst.setString(5, this.fsn);
	      pst.executeUpdate();
	    } catch (Exception e) {
	      logger.error("更新估值表溢折价明细科目出错：" + e.getMessage(), e);
	      throw e;
	    }finally{
	      DbFun.closeStatementFinal(pst);
	    }
	}
	
	private void updateGZBJzzzlDetail() throws Exception{
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
	    try {
	      sql.append(" UPDATE T_D_ER_DBLGZ G SET G.C_KM_NAME = NVL((SELECT A.C_KM_NAME FROM t_r_Fr_Aststat_dbl A WHERE A.C_PORT_CODE = G.C_ASS_CODE ");
	      sql.append(" AND A.D_ASTSTAT = G.D_START_DATE AND A.C_KEY_CODE = 'BQJZZZL' AND A.C_KM_CODE = '911'), G.C_KM_NAME), ");
	      sql.append(" G.N_PORT_MV = NVL((SELECT A.N_VA_RATE FROM t_r_Fr_Aststat_dbl A WHERE A.C_PORT_CODE = G.C_ASS_CODE ");
	      sql.append(" AND A.D_ASTSTAT = G.D_START_DATE AND A.C_KEY_CODE = 'BQJZZZL' AND A.C_KM_CODE = '911'), G.N_PORT_MV) ");
	      sql.append(" WHERE G.C_ASS_CODE = ? AND G.C_SN = ? AND G.D_START_DATE = ?AND G.C_KM_CODE = '0020' ");
	      pst = conn.prepareStatement(sql.toString());
	      pst.setString(1, this.assCode);
	      pst.setString(2, this.fsn);
	      pst.setDate(3, YssFun.toSqlDate(geneDate));
	      pst.executeUpdate();
	    } catch (Exception e) {
	      logger.error("更新估值表净值增长率出错：" + e.getMessage(), e);
	      throw e;
	    }finally{
	      DbFun.closeStatementFinal(pst);
	    }
	}
	
	/**
	 * add by xiaomenglei STORY #45854 【鹏华基金】【紧急】社保电子对账，估值表持有到期类成本类科目发送时，行情不是估值表展示的行情，而是直接取的面值100，需要有参数进行控制。 
	 * @throws Exception
	 */
	private void updateGZBDetailHq() throws Exception {
	    PreparedStatement pst = null;
	    PreparedStatement updatePst = null;
	    ResultSet rs = null;
	    try {
	    	 //wlx 20171207 BUG #183242 （紧急）【嘉实基金】电子对账中提前提前还本行情取值有问题
	      String updateSql = " UPDATE T_D_ER_DBLGZ Z  SET Z.N_VA_PRICE = ? WHERE Z.C_ASS_CODE = ? AND Z.C_SN=? AND Z.D_START_DATE = ? AND Z.C_KM_CODE  = ? ";
	     
	      StringBuffer selectSql = new StringBuffer();

	      selectSql.append("select nvl(y.n_rem_cor, 100)as N_VA_PRICE, C_KM_CODE ");
	      selectSql.append("   from T_D_SV_FI_PAY y");
	      selectSql.append("   right join  (select C_KM_CODE, SUBSTR(z.C_KM_CODE,12, LENGTH(z.C_KM_CODE) - 11) as c_sec_code " ); 
	      selectSql.append("       from T_D_ER_DBLGZ z " ); 
	      selectSql.append("       WHERE Z.C_ASS_CODE = ?" ); 
	      selectSql.append("       AND Z.C_SN = ?" );
	      selectSql.append("       AND Z.D_START_DATE = ? " ); 
	      selectSql.append("       AND Z.C_KM_CODE LIKE '1111.%.01.%'" ); 
	      selectSql.append("       AND Z.n_detail = 1) b ");
	      selectSql.append("       on y.c_sec_code = b.c_sec_code " );
	      selectSql.append("  where y.d_begin <= ?");
	      selectSql.append("  and y.d_end >= ? " );
	      
	      pst = conn.prepareStatement(selectSql.toString());
	      int index = 1;
	      pst.setString(index++, this.assCode);
	      pst.setString(index++, this.fsn);
	      pst.setDate(index++, YssFun.toSqlDate(geneDate));
	      
	      pst.setDate(index++, YssFun.toSqlDate(geneDate));
	      pst.setDate(index++, YssFun.toSqlDate(geneDate));
	      
	      updatePst = conn.prepareStatement(updateSql);
	      rs = pst.executeQuery();
	      while(rs.next()){
	    	  index = 1;
	    	  updatePst.setDouble(index++, rs.getDouble("N_VA_PRICE"));
	    	  updatePst.setString(index++, this.assCode);
	    	  updatePst.setString(index++, this.fsn);
	    	  updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
		      
	    	  updatePst.setString(index++, rs.getString("C_KM_CODE"));
	    	  updatePst.addBatch();
	      }
	      updatePst.executeBatch();
	      
	    } catch (Exception e) {
	      logger.error("更新估值表行情列出错：" + e.getMessage(), e);
	      throw e;
	    }finally{
	    	DbFun.closeResultSetFinal(rs);
	    	DbFun.closeStatementFinal(updatePst);
	    	DbFun.closeStatementFinal(pst);
	    }
	  }
	
	/**
	 * add by xiaomenglei STORY #45858 【鹏华基金】【紧急】 社保电子对账，证券投资成本科目发送时，估值表展示如果是无行情，取单位成本，需要增加参数控制。 
	 * @throws Exception
	 */
	private void updateGZBDetailWhqDetail() throws Exception {
	    PreparedStatement pst = null;
	    StringBuffer sql = new StringBuffer();
	    try {
	      sql.append(" UPDATE T_D_ER_DBLGZ A  SET A.N_VA_PRICE = ");
	      //BUG#172632【易方达基金】生成电子对账当债券卖出无成本只有溢折价时会报错
	      sql.append(" (SELECT ROUND(CASE WHEN B.N_AMOUNT = 0 THEN 0 ELSE B.N_ORIG_COST/B.N_AMOUNT END,CASE WHEN B.C_KM_CODE LIKE '%SZ' AND SEC.C_SEC_VAR_CODE <> 'ZQ_KZZ' THEN 3 ELSE 2 END ) FROM t_r_Fr_Aststat_dbl B ");
	      sql.append(" LEFT JOIN T_P_SV_SEC_BASE SEC ON SEC.C_SEC_CODE = SUBSTR(B.C_KM_CODE,LENGTH(B.C_KM_CODE)-8) ");
	      ////add by liangchong bug 171445 电子对账无行情取单位成本   不需要判断停牌信息
	      /////sql.append(" WHERE B.C_PORT_CODE = ? AND B.D_ASTSTAT = ? AND B.C_KM_CODE = A.C_KM_CODE AND B.C_SUSPENSION = '无行情')  ");
	      sql.append(" WHERE B.C_PORT_CODE = ? AND B.D_ASTSTAT = ? AND B.C_KM_CODE = A.C_KM_CODE )  ");
	      sql.append(" WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND A.C_KM_CODE LIKE '11%.%.01.%' AND A.N_VA_PRICE = 0 ");
	      pst = conn.prepareStatement(sql.toString());
	      pst.setString(1, portCode);
	      pst.setDate(2, YssFun.toSqlDate(geneDate));
	      pst.setString(3, this.assCode);
	      pst.setString(4, this.fsn);
	      pst.setDate(5, YssFun.toSqlDate(geneDate));
	      pst.executeUpdate();
	    } catch (Exception e) {
	      logger.error("更新估值表行情列出错：" + e.getMessage(), e);
	      throw e;
	    }finally{
	      DbFun.closeStatementFinal(pst);
	    }
	  }
	
	private void updateGZBDetailSZZJZB() throws Exception {
		PreparedStatement pst = null;
		try {
			String updateSql = " UPDATE T_D_ER_DBLGZ A  SET A.N_SZ_JZ_BL = 0 WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND A.C_KM_CODE IN('0018','0019','0021') ";
			pst = conn.prepareStatement(updateSql);
			int index = 1;
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("更新估值表指标项市值占净值比列出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	private void updateGZBDetailSzDyCb() throws Exception {
		PreparedStatement updatePst = null;
		try {
//			String updateSql = " UPDATE T_D_ER_DBLGZ A  SET A.N_PORT_MV=A.N_PORT_COST,A.N_SZ_JZ_BL = A.N_CB_JZ_BL WHERE (A.C_KM_CODE LIKE '1111%' OR A.C_KM_CODE LIKE '113%.%.02%') AND A.C_ASS_CODE=? AND A.C_SN= ? AND A.D_START_DATE = ? ";
//			pst = conn.prepareStatement(updateSql);
//			int index = 1;
//			pst.setString(index++, this.assCode);
//			pst.setString(index++, this.fsn);
//			pst.setDate(index++, YssFun.toSqlDate(geneDate));
//			pst.executeUpdate();
			
			//将溢折价的三级科目市值 = 成本
			String parentUpdateSql = 
					"UPDATE T_D_ER_DBLGZ A SET A.N_PORT_MV = N_PORT_COST, N_SZ_JZ_BL = N_CB_JZ_BL" + 
							"  WHERE A.C_ASS_CODE = ? \n" + 
							"  AND A.C_SN = ?\n" + 
							"  AND A.D_START_DATE = ?\n" + 
							"  AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_Code) = 3 "+
							"  AND A.C_KM_CODE IN(SELECT PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(B.C_KM_CODE, 3) FROM t_r_Fr_Aststat_dbl B WHERE B.C_PORT_CODE = ?\n" + 
							"      AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_YZJ' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
							"      AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)";
			updatePst = conn.prepareStatement(parentUpdateSql);
			int index = 1;

			updatePst.setString(index++, this.assCode);
			updatePst.setString(index++, this.fsn);
			updatePst.setDate(index++, YssFun.toSqlDate(geneDate));

			updatePst.setString(index++, this.portCode);
			updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
			updatePst.addBatch();
			updatePst.executeBatch();
			DbFun.closeStatementFinal(updatePst);
			
			//将溢折价四级科目的市值列=成本列的值
			parentUpdateSql = 
					"UPDATE T_D_ER_DBLGZ A SET A.N_PORT_MV = N_PORT_COST, N_SZ_JZ_BL = N_CB_JZ_BL \n" + 
							"  WHERE A.C_ASS_CODE = ? \n" + 
							"  AND A.C_SN = ?\n" + 
							"  AND A.D_START_DATE = ?\n" + 
							"  AND A.C_KM_CODE IN(SELECT C_KM_CODE FROM t_r_Fr_Aststat_dbl B WHERE B.C_PORT_CODE = ?\n" + 
							"      AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_YZJ' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
							"      AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)";
			updatePst = conn.prepareStatement(parentUpdateSql);
			index = 1;
			updatePst.setString(index++, this.assCode);
			updatePst.setString(index++, this.fsn);
			updatePst.setDate(index++, YssFun.toSqlDate(geneDate));

			updatePst.setString(index++, this.portCode);
			updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
			updatePst.addBatch();
			updatePst.executeBatch();
			DbFun.closeStatementFinal(updatePst);
		} catch (Exception e) {
			logger.error("更新估值表市值列出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(updatePst);
		}
	}

	/**
	 * 估增4级科目市值，市值占比置为0
	 * @param erDblgzb
	 * @throws Exception
	 */
	private void updateGZBDetailGzsz4(ErDblgzb erDblgzb) {
		String code = erDblgzb.getC_KM_CODE();
		if(!StringUtil.IsNullOrEmptyT(code) && code.split("\\.").length == 4)
		{
			erDblgzb.setN_PORT_MV(BigDecimal.ZERO);
			erDblgzb.setN_SZ_JZ_BL(BigDecimal.ZERO);
		}
	}
	
	/**
	 * 估增3级科目市值，市值占比置为0
	 * @throws Exception
	 */
	private void updateGZBDetailGzsz3(ErDblgzb erDblgzb){
		String code = erDblgzb.getC_KM_CODE();
		if(!StringUtil.IsNullOrEmptyT(code) && code.split("\\.").length == 3)
		{
			erDblgzb.setN_PORT_MV(BigDecimal.ZERO);
			erDblgzb.setN_SZ_JZ_BL(BigDecimal.ZERO);
		}
	}

	private void updateGZBDetailAddYZJ() throws Exception {
		PreparedStatement pst = null;
		PreparedStatement updatePst = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			String updateSql = " UPDATE T_D_ER_DBLGZ A SET A.N_PORT_COST = ?, A.C_KM_NAME = ?, A.N_CB_JZ_BL = ? WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND A.C_KM_CODE = ? ";
			updatePst = conn.prepareStatement(updateSql);
			//将四级溢折价科目的成本加到四级证券投资科目的成本中，并重新计算四级证券投资科目的成本占市值比
			String selectSql =
					"SELECT A.C_KM_CODE,A.C_KM_NAME ||'(总价)' AS C_KM_NAME, A.N_PORT_COST + NVL(C.N_PORT_COST,0) AS N_PORT_COST FROM T_D_ER_DBLGZ A\n" +
							"  LEFT JOIN (SELECT B.N_PORT_COST,B.C_KM_CODE FROM t_r_Fr_Aststat_dbl B WHERE B.C_PORT_CODE = ?\n" + 
							"        AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_YZJ' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
							"        AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)C\n" + 
							"  ON  PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 2) = PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C.C_KM_CODE, 2)\n" + 
							"  AND SUBSTR(A.C_KM_CODE, INSTR(A.C_KM_CODE, '.', 1, 3) + 1, LENGTH(A.C_KM_CODE))\n" + 
							"     = SUBSTR(C.C_KM_CODE, INSTR(C.C_KM_CODE, '.', 1, 3) + 1,LENGTH(C.C_KM_CODE))\n" + 
							"  WHERE A.C_ASS_CODE = ?\n" + 
							"  AND A.C_SN = ?\n" + 
							"  AND A.D_START_DATE = ?\n" + 
							"  AND A.C_KM_CODE IN(SELECT B.C_KM_CODE FROM t_r_Fr_Aststat_dbl B WHERE B.C_PORT_CODE = ?\n" + 
							"      AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_CB' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
							"      AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)";
			pst = conn.prepareStatement(selectSql);
			int index = 1;
			pst.setString(index++, portCode);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			
			pst.setString(index++, portCode);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			rs = pst.executeQuery();
			while(rs.next()){
				index = 1;
				updatePst.setDouble(index++, rs.getDouble("N_PORT_COST"));
				updatePst.setString(index++, rs.getString("C_KM_NAME"));
				////BUG #177898 嘉实基金电子对账-货币类组合成本（总价）占比错误 
				double costBL = 0;
				if(this.N_PORT_MV != 0){
					costBL = YssD.div(rs.getDouble("N_PORT_COST") * 100, this.N_PORT_MV);
					costBL = Double.parseDouble(decimalFormat.format(costBL));
				}
				updatePst.setDouble(index++, costBL);
				updatePst.setString(index++, this.assCode);
				updatePst.setString(index++, this.fsn);
				updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
				updatePst.setString(index++, rs.getString("C_KM_CODE"));
				
				updatePst.addBatch();
				if(++count % batchSize == 0) {
					updatePst.executeBatch();
				}
			}
			updatePst.executeBatch();
			DbFun.closeStatementFinal(updatePst);
			DbFun.closeStatementFinal(pst);
			DbFun.closeResultSetFinal(rs);
			//重新统计三级证券投资科目的成本及成本占净值比    三级科目成本=四级的陈本汇总；成本占比=新成本/市值
			updateSql = " UPDATE T_D_ER_DBLGZ A SET A.N_PORT_COST = ?, A.N_CB_JZ_BL = ? WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND A.C_KM_CODE = ? ";
			String parentUpdateSql = 
					"SELECT SUM(A.N_PORT_COST) AS N_PORT_COST,PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3) AS C_KM_CODE FROM T_D_ER_DBLGZ A WHERE A.C_ASS_CODE = ? \n" + 
							"  AND A.C_SN = ? AND A.D_START_DATE = ?\n" + 
							"  AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_Code) = 4 " +
							"  AND PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3) IN(SELECT PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(B.C_KM_CODE, 3) FROM t_r_Fr_Aststat_dbl B WHERE B.C_PORT_CODE = ?\n" + 
							"      AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_CB' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
							"      AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)" +
							"  GROUP BY PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3) ";
							
			pst = conn.prepareStatement(parentUpdateSql);
			index = 1;
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.setString(index++, this.portCode);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			rs = pst.executeQuery();
			
			updatePst = conn.prepareStatement(updateSql);
			while(rs.next()){
				index = 1;
				updatePst.setDouble(index++, rs.getDouble("N_PORT_COST"));
				////BUG #177898 嘉实基金电子对账-货币类组合成本（总价）占比错误 
				double costBL = 0;
				if(this.N_PORT_MV != 0){
					costBL = YssD.div(rs.getDouble("N_PORT_COST") * 100, this.N_PORT_MV);
					costBL = Double.parseDouble(decimalFormat.format(costBL));
				}
				updatePst.setDouble(index++, costBL);
				
				updatePst.setString(index++, this.assCode);
				updatePst.setString(index++, this.fsn);
				updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
				updatePst.setString(index++, rs.getString("C_KM_CODE"));
				
				updatePst.addBatch();
			}
			updatePst.executeBatch();
			DbFun.closeStatementFinal(updatePst);
			DbFun.closeResultSetFinal(rs);
			//将溢折价的三级科目市值 = 成本
			parentUpdateSql = 
					"UPDATE T_D_ER_DBLGZ A SET A.N_PORT_MV = (SELECT N_PORT_COST FROM T_D_ER_DBLGZ B WHERE B.C_ASS_CODE = ? \n" + 
							"  AND B.C_SN = ? AND B.D_START_DATE = ?\n" + 
							"  AND B.C_KM_CODE = A.C_KM_CODE\n" + 
							"  )\n" + 
							"  WHERE A.C_ASS_CODE = ? \n" + 
							"  AND A.C_SN = ?\n" + 
							"  AND A.D_START_DATE = ?\n" + 
							"  AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_Code) = 3 "+
							"  AND A.C_KM_CODE IN(SELECT PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(B.C_KM_CODE, 3) FROM t_r_Fr_Aststat_dbl B WHERE B.C_PORT_CODE = ?\n" + 
							"      AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_YZJ' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
							"      AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)";
			updatePst = conn.prepareStatement(parentUpdateSql);
			index = 1;
			updatePst.setString(index++, this.assCode);
			updatePst.setString(index++, this.fsn);
			updatePst.setDate(index++, YssFun.toSqlDate(geneDate));

			updatePst.setString(index++, this.assCode);
			updatePst.setString(index++, this.fsn);
			updatePst.setDate(index++, YssFun.toSqlDate(geneDate));

			updatePst.setString(index++, this.portCode);
			updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
			updatePst.addBatch();
			updatePst.executeBatch();
			DbFun.closeStatementFinal(updatePst);
			//STORY #48443 嘉实基金电子对账-货币类组合溢折价市值列发送时需要取成本列的值发送 
			//将溢折价四级科目的市值列=成本列的值
			if(YZJSZQZ){
				parentUpdateSql = 
						"UPDATE T_D_ER_DBLGZ A SET A.N_PORT_MV = N_PORT_COST, N_SZ_JZ_BL = N_CB_JZ_BL \n" + 
								"  WHERE A.C_ASS_CODE = ? \n" + 
								"  AND A.C_SN = ?\n" + 
								"  AND A.D_START_DATE = ?\n" + 
								"  AND A.C_KM_CODE IN(SELECT C_KM_CODE FROM t_r_Fr_Aststat_dbl B WHERE B.C_PORT_CODE = ?\n" + 
								"      AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_YZJ' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
								"      AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)";
				updatePst = conn.prepareStatement(parentUpdateSql);
				index = 1;
				updatePst.setString(index++, this.assCode);
				updatePst.setString(index++, this.fsn);
				updatePst.setDate(index++, YssFun.toSqlDate(geneDate));

				updatePst.setString(index++, this.portCode);
				updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
				updatePst.addBatch();
				updatePst.executeBatch();
				DbFun.closeStatementFinal(updatePst);
			}
			
		} catch (Exception e) {
			logger.error("生成估值表溢折价汇总数据出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, updatePst);
		}
	}

	/**
	 * 获取估值表非明细科目是否生成数量参数
	 */
	private void getAmountPara() {
		String zhcs = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_ZQDMZH);
		//当前数据对应的组合、日期下的证券转换规则参数值为不转换
		if(zhcs != null && "TRAN_SBLSH".equals(zhcs)){
			this.isTranSB = true;
		}
		
		String geneAmount = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_ELCE_GZ_AMOUNT);
		if(geneAmount != null && "1".equals(geneAmount)){
			this.isGeneAmount = true;
		}
		
		String cbjsyzj = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_CBJSYZJ);
		//add by xiaomenglei STORY #44562 【鹏华基金】【紧急】社保电子对账，证券投资市值列是否加溢折价，需要增加参数控制，关联STORY #39277 ，在新增一个参数。
		if(cbjsyzj != null && "1".equals(cbjsyzj)){
			this.addYzj = true;
		}
	    /**
		 * STORY #48443 嘉实基金电子对账-货币类组合溢折价市值列发送时需要取成本列的值发送 
		 * 【产品估值参数】增加参数“电子对账数据溢折价市值列取估值表成本值”(DZ_BB_DZDZ_YZJSZQZ)，选项“是、否”，默认为“否”。
			“否”：生成电子对账数据时，溢折价市值列取估值表中“市值”的值（现有模式）。
			“是”：生成电子对账数据时，溢折价市值列取估值表中“成本”的值。
		 */
	    if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YZJSZQZ) != null && "1".equals(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YZJSZQZ))){
	    	YZJSZQZ = true;
	    }
	    
	  //BUG #183027 【嘉实基金】社保组合可分配收益发送时是否发送净值占市值比例,  默认为是
	    if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_KFPSY) == null || "1".equals(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_KFPSY))){
  			this.DZ_BB_DZDZ_KFPSY = true;
  		}else{
  			this.DZ_BB_DZDZ_KFPSY = false;
  		}
	    
	//  STORY #51238 （紧急）【嘉实基金】社保估值表指标问题导致电子对账不一致
	    if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_XJZBFS) == null || "1".equals(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_XJZBFS))){
			this.DZ_BB_DZDZ_XJZBFS = true;
		}else{
			this.DZ_BB_DZDZ_XJZBFS = false;
		}
		
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TRANS1111) != null && "1".equals(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TRANS1111))){
			trans1111KmCode = true;
		}else{
			trans1111KmCode = false;
		}
		
		//STORY80041双估值电子对账双估值表剔除持有至到期估值增值科目
		String isDelate = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_SGZBFSCYDDGZ);//双估值表是否发送持有到期估增科目
		//默认值为是,为否的时候删除持有至到期估值增值科目
		if(isDelate != null && "0".equals(isDelate)){
			this.deleteCYDQ = true;
		}
	}
	/**
	 * STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。 添加转换规则Map
	 * 转换证券代码
	 * */
	private void transKMCode(){
		PreparedStatement pst = null;
		PreparedStatement updatePst = null;
		ResultSet rs = null;
		try {
			if(isTransKM)
			{
				StringBuilder selectBuilder = new StringBuilder();
				selectBuilder.append("SELECT C_KM_CODE FROM T_D_ER_DBLGZ WHERE C_ASS_CODE = ? AND C_SN = ? AND D_START_DATE = ? ");
				StringBuilder updateBuilder = new StringBuilder();
				updateBuilder.append("UPDATE T_D_ER_DBLGZ SET C_KM_CODE = ? WHERE C_ASS_CODE = ? AND C_SN = ? AND D_START_DATE = ? AND C_KM_CODE = ? ");

				pst = conn.prepareStatement(selectBuilder.toString());
				int index = 1;
				pst.setString(index++, this.assCode);
				pst.setString(index++, this.fsn);
				pst.setDate(index++, YssFun.toSqlDate(geneDate));

				updatePst = conn.prepareStatement(updateBuilder.toString());
				rs = pst.executeQuery();
				String newKmCode = "";
				while(rs.next()){
					newKmCode = KMTransUtil.transKMCode(rs.getString("C_KM_CODE"), kmTransMap);
					index = 1;
					updatePst.setString(index++, newKmCode);

					updatePst.setString(index++, this.assCode);
					updatePst.setString(index++, this.fsn);
					updatePst.setDate(index++, YssFun.toSqlDate(geneDate));

					updatePst.setString(index++, rs.getString("C_KM_CODE"));
					updatePst.addBatch();
				}
				updatePst.executeBatch();
			}
		} catch (Exception e) {
			 logger.error("转换披露代码出错：" + e.getMessage(), e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(updatePst);
			DbFun.closeStatementFinal(pst);
		}
	}
	/**
	 * BUG217588科目代码1111.开头的科目类别与托管行的1103.开头的科目类别匹配，在生成对账数据时增加参数控制是否将1111.科目代码替换为1103.
	 * 
	 * @throws Exception
	 */
	private void trans1111KMCode() throws Exception {
		if(this.trans1111KmCode){
			PreparedStatement pst = null;
			try {
				String updateSql = " UPDATE T_D_ER_DBLGZ A SET A.C_KM_CODE = REGEXP_REPLACE(A.C_KM_CODE,'1111','1103',1,1) WHERE A.C_KM_CODE LIKE '1111%' AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
				pst = conn.prepareStatement(updateSql);
				int index = 1;
				pst.setString(index++, this.assCode);
				pst.setString(index++, this.fsn);
				pst.setDate(index++, YssFun.toSqlDate(geneDate));
				pst.executeUpdate();
			} catch (Exception e) {
				logger.error("科目代码1111.更新为1103.！", e);
				throw e;
			}finally{
				DbFun.closeStatementFinal(pst);
			}
		}
	}
	
	protected Map<String,ErKmb> getKmInfos(String port,String geneDate) throws Exception
	{
		Map<String,ErKmb> map = new HashMap<String, ErKmb>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT C_KM_CODE,C_KM_CODE_P,C_KM_NAME,N_DETAIL FROM T_F_SC_KM C WHERE C.C_PLAN_CODE = ? AND C.N_CHECK_STATE = 1";
			pst = conn.prepareStatement(sql);
			int index = 1;
			pst.setString(index++, this.planCode);
			rs = pst.executeQuery();
			while(rs.next()){
				ErKmb km = new ErKmb();
				km.setC_KM_CODE(rs.getString("C_KM_CODE"));
				km.setC_KM_CODE_P(rs.getString("C_KM_CODE_P"));
				km.setC_KM_NAME(rs.getString("C_KM_NAME"));
				km.setN_DETAIL(rs.getInt("N_DETAIL"));
				map.put(km.getC_KM_CODE(), km);
			}
		} catch (Exception e) {
			 logger.error("获取科目体系出错：" + e.getMessage(), e);
			 throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return map;
	}
	
	/**
	 * 向上汇总数据，并添加到集合中
	 * @param pojo
	 * @param parentKmCode
	 * @param datas
	 * @throws CloneNotSupportedException
	 */
	protected void upSum(ErDblgzb pojo,String parentKmCode, Map<String,ErDblgzb> datas) throws CloneNotSupportedException
	{
		if(!StringUtil.IsNullOrEmpty(parentKmCode))
		{
			ErDblgzb parentObj = null;
			if(datas.containsKey(parentKmCode))//已经存在，取出数据，求和
			{
				parentObj = dataSum(datas.get(parentKmCode), pojo);
				datas.put(parentKmCode, parentObj);
			}else{//不存在，新增
				parentObj = createParentObj(parentKmCode,pojo);
				if(parentObj == null)
				{
					this.logger.error("创建父级节点失败："+pojo.getC_KM_CODE_P());
					return;
				}
				datas.put(parentKmCode, parentObj);
			}
			parentKmCode = getParentKmCode(parentObj);
			//继续向上遍历
			upSum(pojo,parentKmCode, datas);
		}
	}
	
	/**
	 * 向上汇总数据，并添加到集合中
	 * @param pojo 最明细pojo
	 * @param parentKmCode 上级科目代码
	 * @param datas 父级数据集合
	 * @param floor	向上遍历层数
	 * @throws CloneNotSupportedException 
	 */
	protected void upSum(ErDblgzb pojo,String parentKmCode, Map<String,ErDblgzb> datas,int floor) throws CloneNotSupportedException
	{
		if(floor-- > 0)
		{
			if(!StringUtil.IsNullOrEmpty(parentKmCode))
			{
				ErDblgzb parentObj = null;
				if(datas.containsKey(parentKmCode))//已经存在，取出数据，求和
				{
					parentObj = dataSum(datas.get(parentKmCode), pojo);
					datas.put(parentKmCode, parentObj);
				}else{//不存在，新增
					parentObj = createParentObj(parentKmCode,pojo);
					if(parentObj == null)
					{
						this.logger.error("创建父级节点失败："+pojo.getC_KM_CODE_P());
						return;
					}
					datas.put(parentKmCode, parentObj);
				}
				parentKmCode = getParentKmCode(parentObj);
				//继续向上遍历
				upSum(pojo,parentKmCode, datas,floor);
			}
		}
		
	}
	
	protected ErDblgzb createParentObj(String kmCode, ErDblgzb erDblgzb) throws CloneNotSupportedException
	{
		if(this.kmInfos.containsKey(kmCode))
		{
			ErDblgzb gz = (ErDblgzb) erDblgzb.clone();
			ErKmb km = this.kmInfos.get(kmCode);
			gz.setC_KM_CODE(kmCode);
			gz.setC_KM_NAME(km.getC_KM_NAME());
			gz.setC_KM_CODE_P(km.getC_KM_CODE_P());
			gz.setN_DETAIL(km.getN_DETAIL());
			gz.setN_VA_PRICE(BigDecimal.ZERO);
			if(!this.isGeneAmount){
				gz.setN_AMOUNT(BigDecimal.ZERO);
			}
			return gz;
		}
		return null;
	}
	
	/**
	 * 将operPojo对应的属性值汇总到descPojo对应的属性上
	 * @param descPojo
	 * @param operPojo
	 * @param fileds
	 * @return
	 */
	protected ErDblgzb dataSum(ErDblgzb descPojo,ErDblgzb operPojo)
	{
		if(!this.isGeneAmount){
			descPojo.setN_AMOUNT(BigDecimal.ZERO);
		}else
		{
			descPojo.setN_AMOUNT(descPojo.getN_AMOUNT().add(operPojo.getN_AMOUNT()));
		}
		descPojo.setN_PORT_COST(descPojo.getN_PORT_COST().add(operPojo.getN_PORT_COST()));
		descPojo.setN_PORT_IV(descPojo.getN_PORT_IV().add(operPojo.getN_PORT_IV()));
		descPojo.setN_PORT_MV(descPojo.getN_PORT_MV().add(operPojo.getN_PORT_MV()));
		descPojo.setN_VA_PRICE(BigDecimal.ZERO);
		BigDecimal jz = new BigDecimal(this.N_PORT_MV);
		if(BigDecimal.ZERO.compareTo(jz) != 0)
		{
			descPojo.setN_CB_JZ_BL(descPojo.getN_PORT_COST().multiply(new BigDecimal(100)).divide(jz,this.scale,BigDecimal.ROUND_HALF_EVEN));
			descPojo.setN_SZ_JZ_BL(descPojo.getN_PORT_MV().multiply(new BigDecimal(100)).divide(jz,this.scale,BigDecimal.ROUND_HALF_EVEN));
		}
		return descPojo;
	}
	
	/**
	 *获取上级科目代码
	 * @param kmCode
	 * @return 上级科目代码，没有上级科目时，返回null
	 */
	protected String getParentKmCode(ErDblgzb gz)
	{
		if(this.kmInfos.containsKey(gz.getC_KM_CODE_P()))
		{
			return gz.getC_KM_CODE_P();
		}
		return null;
	}
	
	protected void initHead(ErDblgzb gz)
	{
		gz.setC_SN(this.fsn);
		gz.setC_FILE_TYPE(C_FILE_TYPE);
		gz.setC_RPT_TYPE(C_RPT_TYPE);
		gz.setC_ASS_CODE(this.assCode);
		gz.setD_START_DATE(this.geneDate);
		gz.setD_END_DATE(this.geneDate);
		gz.setN_QUOT_LOGO(1);
		gz.setC_DV_ER_WAY(FORWORD);
	}
	
	/**
	 * 初始化数据，如果数值为null置为0
	 * @param gz
	 * @param asset
	 */
	protected void initValue(ErDblgzb gz,Asset asset)
	{
		
	}
	
	/**
	 * 转换估值pojo为电子对账pojo
	 * @param pojo
	 * @return
	 */
	protected ErDblgzb getErDblgzb(Asset asset)
	{
		ErDblgzb gz = new ErDblgzb();
		if(!kmInfos.containsKey(asset.getC_KM_CODE_T()))
		{
			this.logger.debug("该条数据未关联到科目体系，丢弃："+asset.getC_KM_CODE());
			return null;
		}
		ErKmb erKmb = kmInfos.get(asset.getC_KM_CODE_T());
		initHead(gz);
		gz.setN_DETAIL(erKmb.getN_DETAIL());
		gz.setC_KM_CODE_P(erKmb.getC_KM_CODE_P());
		gz.setC_KM_CODE(asset.getC_KM_CODE());
		gz.setC_KM_NAME(asset.getC_KM_NAME());
		
		if(StringUtil.IsNullOrEmptyT(asset.getN_WAY()))
		{
			this.logger.debug(asset.getC_KM_CODE() + "科目方向不正确！丢弃：" + asset.getN_WAY());
			return null;
		}
		
		try{
			gz.setN_WAY((int) Double.parseDouble(asset.getN_WAY()));
		}catch(NumberFormatException e)
		{
			this.logger.debug(asset.getC_KM_CODE() + "科目方向不正确！丢弃：" + asset.getN_WAY());
			return null;
		}
		BigDecimal way = new BigDecimal(gz.getN_WAY());
		gz.setN_AMOUNT(asset.getN_AMOUNT() == null ? new BigDecimal(0) : asset.getN_AMOUNT());
		gz.setN_PORT_COST(asset.getN_PORT_COST() == null ? new BigDecimal(0) : asset.getN_PORT_COST().multiply(way));
		gz.setN_PORT_IV(asset.getN_PORT_IV() == null ? new BigDecimal(0) : asset.getN_PORT_IV().multiply(way));
		gz.setN_PORT_MV(asset.getN_PORT_MV() == null ? new BigDecimal(0) : asset.getN_PORT_MV().multiply(way));
		gz.setN_VA_PRICE(asset.getN_VA_PRICE() == null ? new BigDecimal(0) : asset.getN_VA_PRICE());
		gz.setC_NAV_TYPE(asset.getC_NAV_TYPE());
		BigDecimal jz = new BigDecimal(this.N_PORT_MV);
		if(BigDecimal.ZERO.compareTo(jz) != 0)
		{
			gz.setN_CB_JZ_BL(gz.getN_PORT_COST().multiply(new BigDecimal(100)).divide(jz,this.scale,BigDecimal.ROUND_HALF_EVEN));
			gz.setN_SZ_JZ_BL(gz.getN_PORT_MV().multiply(new BigDecimal(100)).divide(jz,this.scale,BigDecimal.ROUND_HALF_EVEN));
		}
		return gz;
	}
	
	/**
	 * 一条汇总数据可能设置了多个指标
	 * @param asset
	 * @param manager
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	protected List<ErDblgzb> getTotalErDblgzb(Asset asset,ElecRelaManager manager) throws CloneNotSupportedException
	{
		List<ErDblgzb> list = new ArrayList<ErDblgzb>();
		ErDblgzb gz = new ErDblgzb();
		initHead(gz);
		gz.setN_DETAIL(0);
		gz.setC_KM_CODE(asset.getC_KM_CODE());
		gz.setC_KM_NAME(asset.getC_KM_NAME());
		gz.setN_AMOUNT(asset.getN_AMOUNT() == null ? new BigDecimal(0) : asset.getN_AMOUNT());
		gz.setN_PORT_COST(asset.getN_PORT_COST() == null ? new BigDecimal(0) : asset.getN_PORT_COST());
		gz.setN_PORT_IV(asset.getN_PORT_IV() == null ? new BigDecimal(0) : asset.getN_PORT_IV());
		if(TOTAL_ALL.equalsIgnoreCase(asset.getC_NAV_TYPE()))
		{
			String kmName = asset.getC_KM_NAME().replaceAll("[^0-9.-]", "");
			if(StringUtil.IsNullOrEmptyT(kmName))
			{
				gz.setN_PORT_MV(BigDecimal.ZERO);
			}else
			{
				try{
					gz.setN_PORT_MV(new BigDecimal(kmName));
				}catch(NumberFormatException e)
				{
					this.logger.debug(asset.getC_KM_NAME() + "数值非法："+asset.getC_KEY_CODE() + "(" + asset.getC_KEY_NAME() + ")");
					gz.setN_PORT_MV(BigDecimal.ZERO);
				}
			}
		}else
		{
			gz.setN_PORT_MV(asset.getN_PORT_MV() == null ? new BigDecimal(0) : asset.getN_PORT_MV());
		}
		gz.setN_VA_PRICE(asset.getN_VA_PRICE() == null ? new BigDecimal(0) : asset.getN_VA_PRICE());
		gz.setC_NAV_TYPE(asset.getC_NAV_TYPE());
		BigDecimal jz = new BigDecimal(this.N_PORT_MV);
		if(BigDecimal.ZERO.compareTo(jz) != 0)
		{
			gz.setN_CB_JZ_BL(gz.getN_PORT_COST().multiply(new BigDecimal(100)).divide(jz,this.scale,BigDecimal.ROUND_HALF_EVEN));
			gz.setN_SZ_JZ_BL(gz.getN_PORT_MV().multiply(new BigDecimal(100)).divide(jz,this.scale,BigDecimal.ROUND_HALF_EVEN));
		}
		
		if(isTranSB && !StringUtil.IsNullOrEmpty(asset.getC_KEY_CODE()))
		{
			String code = asset.getC_KEY_CODE().replaceAll("SB", "");
			asset.setC_KEY_CODE(code);
		}
		//关联指标
		List<ElecRela> relas = manager.getDblGzElecRela(asset);
		if(relas == null || relas.size() == 0)
		{
			this.logger.debug("该条数据未设置对账指标，丢弃：" + asset.getC_KEY_CODE() + "(" + asset.getC_KEY_NAME() + ")");
			return null;
		}
		
		for (ElecRela rela : relas) {
			ErDblgzb temp = (ErDblgzb) gz.clone();
			temp.setC_ZB_NAME(rela.getC_ZB_NAME());
			temp.setC_KM_CODE(rela.getC_ZB_CODE());
			if(TOTAL.equals(asset.getC_NAV_TYPE()))
			{
				temp.setC_KM_NAME(temp.getC_ZB_NAME());
			}
			if(asset.getC_KM_CODE().contains("_"))//分级组合
			{
				String clsCode = asset.getC_KM_CODE().substring(asset.getC_KM_CODE().indexOf("_")+1);
				temp.setC_PORT_CLS_CODE(clsCode);
			}
			list.add(temp);
		}
		return list;
	}
	
	protected List<Asset> getGzbData(String port,String date) throws Exception
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("C_PORT_CODE", port);
		map.put("D_ASTSTAT_DATE", date);//yyyy-MM-dd
		map.put("C_IS_GZ", "true");//估值表数据
		IAssetStatsService assetStatsService = YssServiceFactory.getInstance().createService(IAssetStatsService.class);
		List<Asset> list = assetStatsService.queryAssetOrStockList(map);
		
		return list;
	}
	
	protected List<Asset> getGzData(String port,String date) throws Exception
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("C_PORT_CODE", port);
		map.put("D_ASTSTAT_DATE", date);//yyyy-MM-dd
		map.put("C_IS_GZ", "false");//估增数据
		IAssetStatsService assetStatsService = YssServiceFactory.getInstance().createService(IAssetStatsService.class);
		List<Asset> list = assetStatsService.queryAssetOrStockList(map);
		this.logger.debug("获取估增数据："+list.size());
		return list;
	}
	

	@Override
	protected boolean setPojo(PreparedStatement pst, ResultSet rs,
			int dataType, HashMap<String, ElecPerRela> elecPerRelaMap)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
