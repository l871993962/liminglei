package com.yss.uco.elecreco.er.generate.gzb;

import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.sec.information.support.modules.sv.base.cache.service.ISecbaseCacheDataService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssD;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.util.DateUtil;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.er.generate.service.GeneElecDataService;
import com.yss.uco.elecreco.er.generate.service.LicCompanyName;
import com.yss.uco.elecreco.er.generate.util.KMTransUtil;
import com.yss.uco.elecreco.support.bean.ElecRela;
import com.yss.uco.elecreco.support.service.IElecRelaService;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;

public class GeneGZBDataService extends GeneElecDataService{

	protected static final String C_FILE_TYPE = "1011";
	protected static final String C_RPT_TYPE = "01";
	protected static final int DETAIL_TYPE = 1;
	protected static final int NAV_TYPE = 2;
	protected static final int YGZ_TYPE = 3;
	protected static final String GZB_YGZ = "GZBFSMS_YGZ";
	
	protected static final String TOTAL_ALL = "TOTAL_ALL";
	protected static final String TOTAL = "TOTAL";
	//BUG #149621 南方基金-工行电子对账估值表核对不一致(针对于工行做处理)
	private double GH_DWJZ = 0;
	private double GH_DWXJCE = 0;
	//南方基金交行非明细科目需要生成证券数量
	private boolean isGeneAmount = false;
	//南方基金社保产品的估值表C_KEY_CODE指标代码后面加“SB”,造成跟对账指标关联不上
	private boolean isTranSB = false;
	//add by xiaomenglei STORY #44562 【鹏华基金】【紧急】社保电子对账，证券投资市值列是否加溢折价，需要增加参数控制，关联STORY #39277 ，在新增一个参数。
	private boolean addYzj = false;
	
	//BUG #353940 【鹏华基金】电子对账发送的成本列未包含减值准备
	private boolean addJzzb = false;
	
	/**
	 * STORY #89837 人保资产-电子对账生成的估值表持有到期数据生成错误 
	 */
	private boolean TZYZJSZQZ = false;
	
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 将private改为protected，供拆分子类调用
	 *  STORY #48435 嘉实基金电子对账生产沪港深组合的估值表中的行情与估值表展示不一至 
	 *  参数“估值表中行情列是否按原币显示”, true 为原币；false 为行情价格*汇率
	 */
	protected boolean isYbhq = true;
	/**
	 * STORY #48443 嘉实基金电子对账-货币类组合溢折价市值列发送时需要取成本列的值发送 
	 * 【产品估值参数】增加参数“电子对账数据溢折价市值列取估值表成本值”(DZ_BB_DZDZ_YZJSZQZ)，选项“是、否”，默认为“否”。
		“否”：生成电子对账数据时，溢折价市值列取估值表中“市值”的值（现有模式）。
		“是”：生成电子对账数据时，溢折价市值列取估值表中“成本”的值。
	 */
	private boolean YZJSZQZ = false;
	/**
	 * add zhangmingbo  STORY 46715 增加电子对账是否发送实收资本数量的参数
	 * 发送实收资本指标项0065是否发送数量   0为否，1为是
	 */
//	private String DZ_BB_DZDZ_SSZBZSSL="";
	//
	/**
	 * add zhangmingbo STORY 46827[易方达]【紧急】中行对账需求汇总
	 * 合计小计指标项是否发送数量  0为否，1为是
	 */
//	private String DZ_BB_DZDZ_HJXJ="";
	/**
	 * 合计小计指标项代码
	 */
	private List<String> HJXJList = null;
	/**
	 * added by HeLiang 2017-09-04 STORY #46410 【易方达基金】【紧急】电子对账生成报文 基金资产单位净值 需要增加参数控制保留小数位数
	 * 基金单位净值数字格式化类
	 */
//	protected DecimalFormat decimalFormatDWJZ = new DecimalFormat();
	
//	/**
//	 * STORY #86786 华夏基金--可以通过参数控制发送的电子对账指标的保留位数 
//	 */
//	protected DecimalFormat decimalFormat0065 = new DecimalFormat();
//	
//	/**
//	 * 是否格式化实收资本
//	 */
//	private boolean isFormat0065 = false;
	
	/**
	 * STORY #94746 【政策业务】中央数据交换平台开放式基金业务数据交换协议V2.2 
	 */
	protected DecimalFormat decimalFormatPrice = new DecimalFormat();
	
	/**
	 * 是否格式化行情价格
	 */
	private boolean isFormatPrice = false;

	/**
	 *   STORY #51238 （紧急）【嘉实基金】社保估值表指标问题导致电子对账不一致
	 */
	private boolean DZ_BB_DZDZ_XJZBFS = false;
	/**
	 * BUG #183027 【嘉实基金】社保组合可分配收益发送时是否发送净值占市值比例
	 */
	private boolean DZ_BB_DZDZ_KFPSY = true;
	/**
	 * STORY50919QD分级产品电子对账合计项指标根据级别、分级组合对账
	 */
	private boolean DZ_BB_DZDZ_FJHJXJSFJZH = false;
	
	/**
	 * STORY #91826 富国基金-电子对账发送衍生工具相关科目的改造需求 
	 * 是：生成数据时，衍生工具产品成本科目数量填负值，非明细科目的总数量填0
	 * 否：成本科目数量=估值表科目数量，非明细科目总数量为具体值（系统现有规则）
	 */
	private boolean DZ_BB_DZDZ_YSGJMFFS = false;
	
	/**
	 * STORY #91826 富国基金-电子对账发送衍生工具相关科目的改造需求 
	 * 是：生成数据时，衍生工具公产品允价值变动损益科目成本金额填0
	 * 否：科目成本金额=估值表科目金额（系统现有规则）
	 */
	private boolean DZ_BB_DZDZ_YSGJGYJZCB = false;
	
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
	 * BUG #364352 【300.7电子对账】生成估值表电子对账参数，如果有分级组合的数据，参数：电子对账估值表单位净值保留位数，发送数据明细中的分级组合的单位净值数据没有按照电子对账综合参数设置的参数值来判断生成
	 */
	protected HashMap<String, ElecRela> elecRelaMap = new HashMap<String, ElecRela>();
		/**
	 * 是否将1111.科目代码转换为1103.科目代码
	 * BUG217588科目代码1111.开头的科目类别与托管行的1103.开头的科目类别匹配，在生成对账数据时增加参数控制是否将1111.科目代码替换为1103.
	 */
	private boolean trans1111KmCode = false;
	
	/**
	 * STORY #103375 【太平养老】发送电子对账估值表，发现有重复科目
	 */
	private boolean isMergeData = true;
	
	private AdmPortActParams paras = null;
	//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	////派生了一个子类，修改逻辑时，注意拆分子类
	@Override
	public Map<String, Object> geneElecData() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = "";
		String resultDetail = "";
		int count = 0;
		try {
			//BUG211964电子对账个性指标设置取值逻辑不对
			initElecPerRelaMap();
			//BUG #364352 【300.7电子对账】生成估值表电子对账参数，如果有分级组合的数据，参数：电子对账估值表单位净值保留位数，发送数据明细中的分级组合的单位净值数据没有按照电子对账综合参数设置的参数值来判断生成
			initElecRelaMap();
			//获取参数值
			if (paras == null) {
				paras = new AdmPortActParams(this.portCode, DateUtil.stringtoDate(this.geneDate, "yyyy-MM-dd"));
			}
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
			this.createFsn();//生成对账序号
			this.Pro_GetPlan();//获取核算方案代码及科目体系
			this.getAssCode();//获取资产代码
			this.getDZPara();//获取对账参数
			this.getAmountPara();//
			this.getSSZB();//查询实收资本数量
			this.getZCJZ();//查询资产净值成本，资产净值等
			count = 0;
			if(this.kmLevel > 0){
				count += this.geneGZBDetailData();//生成明细项数据
				boolean isTzyzj = true;
				if(addYzj){
					this.updateGZBDetailAddYZJ();
					isTzyzj = false;
				}
				
				//BUG #367310 【景顺长城】【02280511】【电子对账】货币电子对账市值参数控制不了
				if(YZJSZQZ){
					updateGZBDetailAddYZJSZQZ();
					isTzyzj = false;
				}

				/**
				 * isTzyzj 优先级为，如果溢折价和溢折价市值取成本时。就算开了参数也不处理。
				 */
				if(isTzyzj && this.TZYZJSZQZ){
					updateGZBDetailAddTZYZJ();
				}
				
				if(this.addJzzb){
					this.updateGZBDetailAddJzzb();
				}
				
				count += this.geneGZBNavTypeData();//生成汇总项，指标项数据
				if(GZB_YGZ.equalsIgnoreCase(this.C_GZB_MODE)){
					String gzDetail = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_GZSFZMXKM);
					count += this.geneGZBYGZData(gzDetail);//若有估值增值，则生成估值增值数据
					//根据产品估值参数“电子对账数据估增4级明细科目市值列为0”，判断是否更新市值列
					String sb4 = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_GZKMSZ); 
					if(sb4!= null && "1".equalsIgnoreCase(sb4)){
						this.updateGZBDetailGzsz4();
					}
					//根据产品估值参数“电子对账数据估增3级汇总科目市值列为0”，判断是否更新市值列
					String sb3 = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_GZSJKMSZ); 
					if(sb3 != null && "1".equalsIgnoreCase(sb3)){
						this.updateGZBDetailGzsz3();
					}
					//BUG225877富国基金-【公募】可退替代估增没有发送出市值列的值
					String etfSz = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_GZETFSZ);
					if(!(etfSz!=null && etfSz.equalsIgnoreCase("0"))){
						this.updateETFGZKm();//BUG190590嘉实基金-电子对账-110299科目重复
					}
				}
				//当电子对账数据成本列是否加上溢折价为否，证券代码转换规则为“社保理事会债券代码转换规则”同时成立时，'1111'、'1132'科目更新市值列=成本列、市值占比=成本占比
				if(!addYzj&&isTranSB){
					this.updateGZBDetailSzDyCb();
				}
				//add by xiaomenglei STORY #45854 【鹏华基金】【紧急】社保电子对账，估值表持有到期类成本类科目发送时，行情不是估值表展示的行情，而是直接取的面值100，需要有参数进行控制。 
				String hqqz = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_CYDALHQ);
				if(hqqz!=null && hqqz.equalsIgnoreCase("1")){
					this.updateGZBDetailHq();
				}
				
				this.deleteETFGZKm();//BUG190590嘉实基金-电子对账-110299科目重复BUG #229600 【招商基金】产生的电子对账1103.99.01无法放到1103一级科目下
				
				//add by xiaomenglei STORY #45858 【鹏华基金】【紧急】 社保电子对账，证券投资成本科目发送时，估值表展示如果是无行情，取单位成本，需要增加参数控制。 
				String whq = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_WHQQDWCB);
				if(whq!=null && whq.equalsIgnoreCase("1")){
					this.updateGZBDetailWhqDetail();
				}
				//add by xiaomenglei STORY #45862 【鹏华基金】【紧急】社保电子对账，估值指标项不发送市值占净值比，发送为0 
				String szzjzb = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_ZBXSSZB);
				if(szzjzb != null && szzjzb.equalsIgnoreCase("1")){
					this.updateGZBDetailSZZJZB();
				}
				//add by xiaomenglei STORY #46220 【鹏华基金】增加参数控制，电子对账数据净值增长率指标是否取本期净值增值率
				String jzzzl = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_JZZZL);
				if(jzzzl != null && jzzzl.equalsIgnoreCase("1")){
					this.updateGZBJzzzlDetail();
				}
				//add by xiaomenglei STORY #46241 【鹏华基金】【紧急】养老金电子对账，溢折价明细科目是否发送，需要增加参数控制。
				String yzjmxkm = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_YZJMXKM);
				if(yzjmxkm != null && yzjmxkm.equalsIgnoreCase("0")){
					this.updateGZBYzjmxkmDetail();
				}
				//BUG214534【招商基金】工行社保产品电子对账发送0016基金资产单位净值需要发送估值增值列数据
				String dwjziv = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZIV);
				if(dwjziv != null && "1".equalsIgnoreCase(dwjziv)){
					this.updateDWJZIV();
				}
				//【招商基金】持有到期类债券投资1111开头的 第三级科目的 市值 = 成本
				String mvcblevel3 = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_MVCBLEVEL3);
				if(mvcblevel3 !=null && "1".equalsIgnoreCase(mvcblevel3)){
					this.updateGZBMVCBLevel3();
				}
				
				trans1111KMCode();
			}
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
			if(count > 0){
				this.geneErInfo(C_FILE_TYPE, C_RPT_TYPE);//插入生成记录
				this.geneErInfoHisAndInst(C_FILE_TYPE, C_RPT_TYPE);//生成历史进行记录
				result = this.fsn;
				resultDetail = "电子对账估值表数据生成成功！";
			}else{
				result = "Warn";
				resultDetail = "没有数据生成！";
				//BUG251623电子对账管理生成电子对账，如果方案关联组合设置了群组但是没有单独设置产品进行关联，则无法生成估值表数据
				if(this.kmLevel == 0)
				{
					resultDetail += "请检查是否设置了方案关联组合！";
				}
			}
			
		} catch (Exception e) {
			result = "Fail";
			resultDetail = e.getMessage();
			logger.error("生成估值表数据出错：" + e.getMessage(), e);
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("resultDetail", resultDetail);
		return resultMap;
	}
	
	/**
	 * 初始化指标
	 */
	private void initElecRelaMap() {
		IElecRelaService relaService = YssServiceFactory.getInstance().createService(IElecRelaService.class);
		List<BasePojo> relaList = relaService.getDataList();
		for (BasePojo basePojo : relaList) {
			ElecRela p = (ElecRela) basePojo;
			if (!StringUtil.IsNullOrEmptyT(p.getC_ZB_CODE())) {
				elecRelaMap.put(p.getC_ZB_CODE(), p);
			}
		}
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
						.append("  UPDATE T_D_ER_GZ SET C_KM_NAME = SUBSTR(C_KM_NAME,"+ ngkmLength + ") ")
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
			String updateSql = " UPDATE T_D_ER_GZ A SET A.N_PORT_MV=A.N_PORT_COST,A.N_SZ_JZ_BL = A.N_CB_JZ_BL WHERE A.C_KM_CODE LIKE '1111%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_CODE) = 3 AND A.C_ASS_CODE=? AND A.C_SN= ? AND A.D_START_DATE = ? "
					+"           AND A.C_KM_CODE IN(SELECT PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(B.C_KM_CODE, 3) FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?"
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
			String updateSql = " UPDATE T_D_ER_GZ A SET A.N_PORT_IV = A.N_PORT_MV - A.n_Port_Cost, A.C_KM_NAME = '基金资产单位净值' WHERE A.C_KM_CODE in('0016') AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
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
		HashMap<String, ElecPerRela> map = this.getPerRelaByPortAndDZCode(portCode, "1011");
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
			sql.append(" DELETE FROM T_D_ER_GZ   ")
					.append(" WHERE C_IDEN IN ( ")
					.append(" SELECT MIN(C_IDEN)  FROM T_D_ER_GZ WHERE ")
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
	 * @throws Exception
	 */
	private void updateETFGZKm() throws Exception {
		PreparedStatement pst = null;
		try {
			String updateSql = " UPDATE T_D_ER_GZ A SET A.N_PORT_MV = 0, N_SZ_JZ_BL = 0 WHERE A.C_KM_CODE in('1102.99','1103.99') AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
			pst = conn.prepareStatement(updateSql);
			int index = 1;
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("更新1102.99,1103.99估增科目市值列，市值占比出错！", e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	private void updateGZBYzjmxkmDetail() throws Exception{
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
	    try {
	      sql.append(" DELETE FROM T_D_ER_GZ WHERE  ");
	      sql.append(" C_SN = ? AND C_ASS_CODE = ? AND D_START_DATE = ? AND C_KM_CODE IN( ");
	      sql.append(" SELECT A.C_KM_CODE FROM  T_D_ER_GZ a ");
	      sql.append(" LEFT JOIN T_R_FR_ASTSTAT B ON A.D_START_DATE = B.D_ASTSTAT AND A.C_KM_CODE = B.C_KM_CODE AND B.C_PORT_CODE = ? ");
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
	      sql.append(" UPDATE T_D_ER_GZ G SET G.C_KM_NAME = NVL((SELECT A.C_KM_NAME FROM T_R_FR_ASTSTAT A WHERE A.C_PORT_CODE = G.C_ASS_CODE ");
	      sql.append(" AND A.D_ASTSTAT = G.D_START_DATE AND A.C_KEY_CODE = 'BQJZZZL' AND A.C_KM_CODE = '911'), G.C_KM_NAME), ");
	      sql.append(" G.N_PORT_MV = NVL((SELECT A.N_VA_RATE FROM T_R_FR_ASTSTAT A WHERE A.C_PORT_CODE = G.C_ASS_CODE ");
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
	      String updateSql = " UPDATE T_D_ER_GZ Z  SET Z.N_VA_PRICE = ? WHERE Z.C_ASS_CODE = ? AND Z.C_SN=? AND Z.D_START_DATE = ? AND Z.C_KM_CODE  = ? ";
	     
	      StringBuffer selectSql = new StringBuffer();

	      selectSql.append("select nvl(y.n_rem_cor, 100)as N_VA_PRICE, C_KM_CODE ");
	      selectSql.append("   from T_D_SV_FI_PAY y");
	      selectSql.append("   right join  (select C_KM_CODE, SUBSTR(z.C_KM_CODE,12, LENGTH(z.C_KM_CODE) - 11) as c_sec_code " ); 
	      selectSql.append("       from t_d_er_gz z " ); 
	      selectSql.append("       WHERE Z.C_ASS_CODE = ?" ); 
	      selectSql.append("       AND Z.C_SN = ?" );
	      selectSql.append("       AND Z.D_START_DATE = ? " ); 
	      selectSql.append("       AND (Z.C_KM_CODE LIKE '1111.%.01.%' " ); 
	      selectSql.append(" OR EXISTS (SELECT B.C_KM_Code FROM T_F_SC_KM B LEFT JOIN T_P_AO_DAI R ON B.C_PLAN_CODE = R.C_PLAN_CODE AND B.C_DAI_CODE = R.C_DAI_CODE " );
	      selectSql.append(" WHERE B.C_PLAN_CODE = ? AND B.C_DV_INVEST_CLS= 'IC_HM'  ");
	      selectSql.append(" AND R.N_VAR_INV = 1 AND R.n_Check_State = 1 ");
	      selectSql.append(" AND B.C_DAI_CODE LIKE'%CB%' and B.C_DV_KM_CLS <> 'KC_SY' AND PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(Z.C_KM_CODE, 3) = B.C_KM_Code))  " ); 
	      selectSql.append("       AND Z.n_detail = 1) b ");
	      selectSql.append("       on y.c_sec_code = b.c_sec_code " );
	      selectSql.append("  where y.d_begin <= ?");
	      selectSql.append("  and y.d_end >= ? " );
	      
	      pst = conn.prepareStatement(selectSql.toString());
	      int index = 1;
	      pst.setString(index++, this.assCode);
	      pst.setString(index++, this.fsn);
	      pst.setDate(index++, YssFun.toSqlDate(geneDate));
	      
	      //BUG #348144 【华宝信托】【300.7.1031】电子对账数据持有到期类成本科目行情取面值=是时不生效
	      pst.setString(index++, this.planCode);
	      
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
	      sql.append(" UPDATE T_D_ER_GZ A  SET A.N_VA_PRICE = ");
	      //BUG#172632【易方达基金】生成电子对账当债券卖出无成本只有溢折价时会报错
	      sql.append(" (SELECT ROUND(CASE WHEN B.N_AMOUNT = 0 THEN 0 ELSE B.N_ORIG_COST/B.N_AMOUNT END,CASE WHEN B.C_KM_CODE LIKE '%SZ' AND SEC.C_SEC_VAR_CODE <> 'ZQ_KZZ' THEN 3 ELSE 2 END ) FROM T_R_FR_ASTSTAT B ");
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
			String updateSql = " UPDATE T_D_ER_GZ A  SET A.N_SZ_JZ_BL = 0 WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND A.C_KM_CODE IN('0018','0019','0021') ";
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
		PreparedStatement pst = null;
		try {
			String updateSql = " UPDATE T_D_ER_GZ A  SET A.N_PORT_MV=A.N_PORT_COST,A.N_SZ_JZ_BL = A.N_CB_JZ_BL WHERE (A.C_KM_CODE LIKE '1111%' OR A.C_KM_CODE LIKE '113%.%.02%') AND A.C_ASS_CODE=? AND A.C_SN= ? AND A.D_START_DATE = ? ";
			pst = conn.prepareStatement(updateSql);
			int index = 1;
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("更新估值表市值列出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}

	private void updateGZBDetailGzsz4() throws Exception {
		PreparedStatement pst = null;
		try {
			//科目体系中估值增值科目科目代码不是设置为99的时候 BUG272621【太平养老】【生产】电子对账一级科目估增无法设置为0，原因为估增放在.02科目（非.99科目）。现发现涉及3个科目，详见截图
			//String updateSql = " UPDATE T_D_ER_GZ A SET A.N_PORT_MV = 0, N_SZ_JZ_BL = 0 WHERE A.C_KM_CODE LIKE '%.%.99.%' AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
			StringBuilder updateSql = new StringBuilder();
			updateSql.append(" UPDATE T_D_ER_GZ A SET A.N_PORT_MV = 0, N_SZ_JZ_BL = 0 ");
			updateSql.append(" WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND  PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_Code) = 4");
			updateSql.append(" AND EXISTS (SELECT B.C_KM_Code FROM T_F_SC_KM B WHERE B.C_PLAN_CODE = ? AND B.C_DAI_CODE LIKE'%GYBD%' and B.C_DV_KM_CLS <> 'KC_SY' AND PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3) = B.C_KM_Code)");
			pst = conn.prepareStatement(updateSql.toString());
			int index = 1;
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.setString(index++, this.planCode);
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("更新估值表市值列出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	private void updateGZBDetailGzsz3() throws Exception {
		PreparedStatement pst = null;
		try {
			//科目体系中估值增值科目科目代码不是设置为99的时候 BUG272621【太平养老】【生产】电子对账一级科目估增无法设置为0，原因为估增放在.02科目（非.99科目）。现发现涉及3个科目，详见截图
			//String updateSql = " UPDATE T_D_ER_GZ A SET A.N_PORT_MV = 0, N_SZ_JZ_BL = 0 WHERE A.C_KM_CODE LIKE '%.%.99' AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
			
			StringBuilder updateSql = new StringBuilder();
			updateSql.append(" UPDATE T_D_ER_GZ A SET A.N_PORT_MV = 0, N_SZ_JZ_BL = 0");
			updateSql.append(" WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_Code) = 3");
			updateSql.append(" AND EXISTS(SELECT B.C_KM_Code FROM T_F_SC_KM B WHERE B.C_PLAN_CODE = ? AND B.C_DAI_CODE LIKE'%GYBD%' and B.C_DV_KM_CLS <> 'KC_SY' AND A.C_KM_Code = B.C_KM_Code)");
			pst = conn.prepareStatement(updateSql.toString());
			int index = 1;
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.fsn);
			pst.setDate(index++, YssFun.toSqlDate(geneDate));
			pst.setString(index++, this.planCode);
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("更新估值表市值列出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * BUG #367310 【景顺长城】【02280511】【电子对账】货币电子对账市值参数控制不了
	 * @throws Exception
	 */
	private void updateGZBDetailAddYZJSZQZ() throws Exception {
		//STORY #48443 嘉实基金电子对账-货币类组合溢折价市值列发送时需要取成本列的值发送 
		//将溢折价四级科目的市值列=成本列的值
		int index = 1;
		PreparedStatement updatePst = null;
		String parentUpdateSql = 
					"UPDATE T_D_ER_GZ A SET A.N_PORT_MV = N_PORT_COST, N_SZ_JZ_BL = N_CB_JZ_BL \n" + 
							"  WHERE A.C_ASS_CODE = ? \n" + 
							"  AND A.C_SN = ?\n" + 
							"  AND A.D_START_DATE = ?\n" + 
							"  AND A.C_KM_CODE IN(SELECT C_KM_CODE FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?\n" + 
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
	
	/**
	 * STORY #89837 人保资产-电子对账生成的估值表持有到期数据生成错误 
	 * @throws Exception
	 */
	private void updateGZBDetailAddTZYZJ() throws Exception {
		int index = 1;
		PreparedStatement updatePst = null;
		String parentUpdateSql = 
				"UPDATE T_D_ER_GZ A SET A.N_PORT_MV = N_PORT_COST, N_SZ_JZ_BL = N_CB_JZ_BL \n" + 
						"  WHERE A.C_ASS_CODE = ? \n" + 
						"  AND A.C_SN = ?\n" + 
						"  AND A.D_START_DATE = ?\n" + 
						"  AND A.C_KM_CODE IN(SELECT C_KM_CODE FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?\n" + 
						"      AND B.D_ASTSTAT = ? AND B.C_DAI_CODE IN ('ZQTZ_YZJ','ZQTZ_CB') AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
						"      AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = ?)";
		updatePst = conn.prepareStatement(parentUpdateSql);
		index = 1;
		updatePst.setString(index++, this.assCode);
		updatePst.setString(index++, this.fsn);
		updatePst.setDate(index++, YssFun.toSqlDate(geneDate));

		updatePst.setString(index++, this.portCode);
		updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
		updatePst.setInt(index++, kmLevel);
		updatePst.addBatch();
		updatePst.executeBatch();
		DbFun.closeStatementFinal(updatePst);
	}
	
	/**
	 * BUG #353940 【鹏华基金】电子对账发送的成本列未包含减值准备
	 * @throws Exception
	 */
	private void updateGZBDetailAddJzzb() throws Exception {
		PreparedStatement pst = null;
		PreparedStatement updatePst = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			String updateSql = " UPDATE T_D_ER_GZ A SET A.N_PORT_COST = ?, A.C_KM_NAME = ?, A.N_CB_JZ_BL = ? WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND A.C_KM_CODE = ? ";
			updatePst = conn.prepareStatement(updateSql);
			String selectSql =
					"SELECT A.C_KM_CODE,A.C_KM_NAME AS C_KM_NAME, A.N_PORT_COST + NVL(C.N_PORT_COST,0) AS N_PORT_COST FROM T_D_ER_GZ A\n" +
							"  LEFT JOIN (SELECT B.N_PORT_COST,B.C_KM_CODE FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?\n" + 
							"        AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'JZZB' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
							"        AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)C\n" + 
							"  ON  PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 2) = PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C.C_KM_CODE, 2)\n" + 
							"  AND SUBSTR(A.C_KM_CODE, INSTR(A.C_KM_CODE, '.', 1, 3) + 1, LENGTH(A.C_KM_CODE))\n" + 
							"     = SUBSTR(C.C_KM_CODE, INSTR(C.C_KM_CODE, '.', 1, 3) + 1,LENGTH(C.C_KM_CODE))\n" + 
							"  WHERE A.C_ASS_CODE = ?\n" + 
							"  AND A.C_SN = ?\n" + 
							"  AND A.D_START_DATE = ?\n" + 
							"  AND A.C_KM_CODE IN(SELECT B.C_KM_CODE FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?\n" + 
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
				String kmName = rs.getString("C_KM_NAME");
				kmName = (!StringUtil.IsNullOrEmpty(kmName) && kmName.contains("(总价)")) ? kmName : kmName+"(总价)";
				updatePst.setString(index++, kmName);
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
			DbFun.closeResultSetFinal(rs);
			
		  //减值准备明细科目不发送
		  StringBuffer sql = new StringBuffer();
	      sql.append(" DELETE FROM T_D_ER_GZ WHERE  ");
	      sql.append(" C_SN = ? AND C_ASS_CODE = ? AND D_START_DATE = ? AND C_KM_CODE IN( ");
	      sql.append(" SELECT A.C_KM_CODE FROM  T_D_ER_GZ a ");
	      sql.append(" LEFT JOIN T_R_FR_ASTSTAT B ON A.D_START_DATE = B.D_ASTSTAT AND A.C_KM_CODE = B.C_KM_CODE AND B.C_PORT_CODE = ? ");
	      sql.append(" WHERE a.C_SN = ? AND B.C_DAI_CODE = 'JZZB' ) ");
	      updatePst = conn.prepareStatement(sql.toString());
	      updatePst.setString(1, this.fsn);
	      updatePst.setString(2, this.assCode);
	      updatePst.setDate(3, YssFun.toSqlDate(geneDate));
	      updatePst.setString(4, portCode);
	      updatePst.setString(5, this.fsn);
	      updatePst.executeUpdate();
	      DbFun.closeStatementFinal(updatePst);
		} catch (Exception e) {
			logger.error("生成估值表减值准备汇总数据出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, updatePst);
		}
	}

	private void updateGZBDetailAddYZJ() throws Exception {
		PreparedStatement pst = null;
		PreparedStatement updatePst = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			String updateSql = " UPDATE T_D_ER_GZ A SET A.N_PORT_COST = ?, A.C_KM_NAME = ?, A.N_CB_JZ_BL = ? WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND A.C_KM_CODE = ? ";
			updatePst = conn.prepareStatement(updateSql);
			//将四级溢折价科目的成本加到四级证券投资科目的成本中，并重新计算四级证券投资科目的成本占市值比
			String selectSql =
					"SELECT A.C_KM_CODE,A.C_KM_NAME ||'(总价)' AS C_KM_NAME, A.N_PORT_COST + NVL(C.N_PORT_COST,0) AS N_PORT_COST FROM T_D_ER_GZ A\n" +
							"  LEFT JOIN (SELECT B.N_PORT_COST,B.C_KM_CODE FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?\n" + 
							"        AND B.D_ASTSTAT = ? AND B.C_DAI_CODE = 'ZQTZ_YZJ' AND B.C_DV_INVEST_CLS IN('IC_FS','IC_HM')\n" + 
							"        AND B.C_SEC_VAR_CODE LIKE 'ZQ%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(B.C_KM_Code) = 4)C\n" + 
							"  ON  PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 2) = PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C.C_KM_CODE, 2)\n" + 
							"  AND SUBSTR(A.C_KM_CODE, INSTR(A.C_KM_CODE, '.', 1, 3) + 1, LENGTH(A.C_KM_CODE))\n" + 
							"     = SUBSTR(C.C_KM_CODE, INSTR(C.C_KM_CODE, '.', 1, 3) + 1,LENGTH(C.C_KM_CODE))\n" + 
							"  WHERE A.C_ASS_CODE = ?\n" + 
							"  AND A.C_SN = ?\n" + 
							"  AND A.D_START_DATE = ?\n" + 
							"  AND A.C_KM_CODE IN(SELECT B.C_KM_CODE FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?\n" + 
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
			DbFun.closeResultSetFinal(rs);
			//重新统计三级证券投资科目的成本及成本占净值比    三级科目成本=四级的陈本汇总；成本占比=新成本/市值
			updateSql = " UPDATE T_D_ER_GZ A SET A.N_PORT_COST = ?, A.N_CB_JZ_BL = ? WHERE A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? AND A.C_KM_CODE = ? ";
			String parentUpdateSql = 
					"SELECT SUM(A.N_PORT_COST) AS N_PORT_COST,PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3) AS C_KM_CODE FROM T_D_ER_GZ A WHERE A.C_ASS_CODE = ? \n" + 
							"  AND A.C_SN = ? AND A.D_START_DATE = ?\n" + 
							"  AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_Code) = 4 " +
							"  AND PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3) IN(SELECT PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(B.C_KM_CODE, 3) FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?\n" + 
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
					"UPDATE T_D_ER_GZ A SET A.N_PORT_MV = (SELECT N_PORT_COST FROM T_D_ER_GZ B WHERE B.C_ASS_CODE = ? \n" + 
							"  AND B.C_SN = ? AND B.D_START_DATE = ?\n" + 
							"  AND B.C_KM_CODE = A.C_KM_CODE\n" + 
							"  )\n" + 
							"  WHERE A.C_ASS_CODE = ? \n" + 
							"  AND A.C_SN = ?\n" + 
							"  AND A.D_START_DATE = ?\n" + 
							"  AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_Code) = 3 "+
							"  AND A.C_KM_CODE IN(SELECT PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(B.C_KM_CODE, 3) FROM T_R_FR_ASTSTAT B WHERE B.C_PORT_CODE = ?\n" + 
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
		} catch (Exception e) {
			logger.error("生成估值表溢折价汇总数据出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, updatePst);
		}
	}

	/**
	 * 插入估值表T_D_ER_GZ预处理sql语句
	 * @return
	 */
	protected String getInsertSql(){
		StringBuilder builder = new StringBuilder();
		builder.append("insert into T_D_ER_GZ ");
		builder.append("(C_IDEN, C_SN, C_FILE_TYPE, C_RPT_TYPE, C_ASS_CODE, D_START_DATE, "); 
		builder.append("  D_END_DATE, N_QUOT_LOGO, C_DV_ER_WAY, C_KM_CODE, C_KM_NAME, N_VA_PRICE, N_AMOUNT, "); 
		builder.append("  N_PORT_COST,  N_PORT_MV, N_PORT_IV, N_CB_JZ_BL, N_SZ_JZ_BL, N_DETAIL,C_NAV_TYPE,c_PORT_CLS_CODE,N_ORIG_COST,N_ORIG_MV,N_ORIG_IV, C_ZB_NAME) "); 
//		builder.append(" values(SEQU_D_ER_GZ.NEXTVAL,'").append(this.fsn).append("','1011','01','");
//		builder.append(this.assCode).append("',");
//		builder.append("to_date('").append(this.geneDate).append("','yyyy-MM-dd'),");
//		builder.append("to_date('").append(this.geneDate).append("','yyyy-MM-dd'),1,'FORWARD',");
		builder.append(" values(SEQU_D_ER_GZ.NEXTVAL,?,?,?,?,?,?,?,?,");
		builder.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return builder.toString();
		
	}
	/**
	 * BUG197123减少不必要的SQL硬解析比率，优化数据库执行效率
	 * @param pst
	 * @param rs
	 * @param dataType
	 * @return
	 * @throws Exception
	 */
	protected int setPojoHead(PreparedStatement pst, ResultSet rs, int dataType) throws Exception{
		int index = 0;
		pst.setString(++index, this.fsn);//报文序号
		pst.setString(++index, C_FILE_TYPE);//文件类型
		pst.setString(++index, C_RPT_TYPE);//报表类型
		pst.setString(++index, this.assCode);//资产代码
		pst.setDate(++index, YssFun.toSqlDate(this.geneDate));//开始日期
		pst.setDate(++index, YssFun.toSqlDate(this.geneDate));//结束日期
		pst.setInt(++index, 1);//行情标志
		pst.setString(++index, FORWORD);//对账方向
		return index;
	}
	/**
	 * 明细项生成
	 * @return
	 * @throws Exception
	 */
	protected int geneGZBDetailData() throws Exception {
		PreparedStatement pst = null;
		PreparedStatement insertPst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			insertPst = conn.prepareStatement(getInsertSql());
			pst = conn.prepareStatement(getGZBDetailSql());
			int n_kmLevel = this.kmLevel;
			while(n_kmLevel > 0){
				int index = 1;
				
				pst.setInt(index++, n_kmLevel);
				pst.setInt(index++, n_kmLevel);
				pst.setString(index++, portCode);
				pst.setString(index++, geneDate);
				pst.setString(index++, planCode);
				pst.setInt(index++, n_kmLevel);
				rs = pst.executeQuery();
				//BUG211964电子对账个性指标设置取值逻辑不对
				//减少查询次数
				//HashMap<String, ElecPerRela> elecPerRelaMap = getPerRelaByPortAndDZCode(portCode, "1011");
				while (rs.next()) {
					boolean isAdd = setPojo(insertPst, rs, DETAIL_TYPE,elecPerRelaMap);
					if(isAdd){
						insertPst.addBatch();
						if(++count % batchSize == 0) {
							insertPst.executeBatch();
						}
					}
				}
				insertPst.executeBatch();
				n_kmLevel--;
				DbFun.closeResultSetFinal(rs);
				pst.clearBatch();
			}
			insertPst.close();
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, insertPst);
		}
		return count;
	}
	/**
	 * 估值表明细项数据的数据源sql
	 * @return
	 */
	protected String getGZBDetailSql(){
		StringBuilder builder = new StringBuilder();

		builder.append("  select \n"); 
		builder.append("    		      A.C_KM_CODE as C_KM_CODE,");
		builder.append("                  MAX(CASE WHEN (INSTR(B.C_KM_NAME, '>') = 0 AND INSTR(B.C_KM_NAME, '<') = 0)\n");
		builder.append("                           THEN B.C_KM_NAME else A.C_KM_NAME end) AS C_KM_NAME, \n");
		builder.append("                  MAX(A.N_VA_PRICE) AS N_VA_PRICE, \n"); //BUG #218042 【易方达基金】估值表产生电子对账行情数据错误   当估值表同一个科目有多条数据时，汇总后行情价格就不正确了
		if(this.DZ_BB_DZDZ_YSGJMFFS){
			builder.append(" CASE WHEN MAX(B.C_DAI_CODE) IN ('YSGJ_CB','YSGJ_CDCB') AND MAX(B.C_DTA_CODE) LIKE '%_SELL' AND MAX(NVL(B.N_DETAIL, 1)) = 1 THEN  0-SUM(A.N_AMOUNT) \n"); 
			builder.append(" WHEN MAX(B.C_DAI_CODE) IN ('YSGJ_CB','YSGJ_CDCB') AND MAX(B.C_DTA_CODE) LIKE '%_SELL' AND MAX(NVL(B.N_DETAIL, 1)) = 0 THEN  0  \n");
			builder.append(" ELSE SUM(A.N_AMOUNT) END AS N_AMOUNT, \n");
		}else{
			builder.append("                  SUM(A.N_AMOUNT) AS N_AMOUNT, \n"); 
		}
		
		builder.append("  				  SUM(A.N_ORIG_COST * N_WAY) as N_ORIG_COST, ");
		
//		if(this.DZ_BB_DZDZ_YSGJGYJZCB){
//			builder.append(" CASE WHEN MAX(B.C_DAI_CODE) = 'YSGJ_GYBD' THEN  0 \n");
//			builder.append(" ELSE SUM(A.N_PORT_COST * N_WAY) END AS N_PORT_COST, \n");
//		}else{
			builder.append("                  SUM(A.N_PORT_COST * N_WAY) AS N_PORT_COST, \n"); 
//		}
		builder.append("  				  SUM(A.N_ORIG_MV * N_WAY) as N_ORIG_MV, ");
		builder.append("                  SUM(A.N_PORT_MV * N_WAY) AS N_PORT_MV, \n"); 
		builder.append("  				  SUM(A.N_ORIG_IV * N_WAY) as N_ORIG_IV, ");
		builder.append("                  SUM(A.N_PORT_IV * N_WAY) AS N_PORT_IV, \n"); 
		builder.append("                  MAX(NVL(B.N_DETAIL, 1)) AS N_DETAIL, MAX(C_NAV_TYPE) AS C_NAV_TYPE, \n"); 
		builder.append("     C_PORT_CODE_CLS "); 
		builder.append("             	FROM (select N_AMOUNT,N_ORIG_COST, N_PORT_COST, N_WAY, \n"); 
		//STORY #48435 嘉实基金电子对账生产沪港深组合的估值表中的行情与估值表展示不一至 
		//参数“估值表中行情列是否按原币显示”值为是，行情按照原来的显示，否则行情=round(行情*汇率,2)
		if (isYbhq){ 
			builder.append("                          N_VA_PRICE,");
		}else{
			//BUG210434【建信基金】电子对账行情与估值表展示不一致延伸, 港股通科目时并且不按原币行情显示时，行情=round(行情*汇率,2)
			builder.append("              CASE WHEN SUBSTR(C_KM_CODE, INSTR(C_KM_CODE, ' ') + 1, LENGTH(C_KM_CODE)) = 'HG' THEN ");
			builder.append("                  ROUND(N_VA_PRICE * N_VA_RATE, 2) ");
			builder.append("               WHEN SUBSTR(C_KM_CODE, INSTR(C_KM_CODE, ' ') + 1, LENGTH(C_KM_CODE)) = 'HS' THEN ");
			builder.append("                  ROUND(N_VA_PRICE * N_VA_RATE, 2) ");
			builder.append("               ELSE "); 
			builder.append("                  N_VA_PRICE END AS N_VA_PRICE,");

//			builder.append("                          ROUND(N_VA_PRICE * N_VA_RATE,2) as N_VA_PRICE,");
		}
		builder.append("                          N_ORIG_MV,N_PORT_MV, N_ORIG_IV,N_PORT_IV, \n"); 
		builder.append("                          PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE, ?) as C_KM_CODE, C_KM_NAME, \n"); 
		builder.append("                          PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE_T, ?) as C_KM_CODE_T,C_NAV_TYPE, \n");
		builder.append(" C_PORT_CLS_CODE AS C_PORT_CODE_CLS ");
		builder.append("                      from T_R_FR_ASTSTAT \n"); 
		builder.append("                      where C_PORT_CODE = ? and D_ASTSTAT = to_date(?, 'yyyy-MM-dd') \n"); 
		builder.append("                          AND C_NAV_TYPE not in ('TOTAL', 'TOTAL_ALL') \n"); 
		builder.append("                          AND C_KM_CODE IS NOT NULL AND C_KM_CODE <> ' ') A \n"); 
		builder.append("             		  JOIN (SELECT C_KM_CODE, C_KM_NAME, N_DETAIL,C_DTA_CODE,C_DAI_CODE FROM T_F_SC_KM C WHERE C.C_PLAN_CODE = ? AND C.N_CHECK_STATE = 1  group by C_KM_CODE, C_KM_NAME, N_DETAIL,C_DTA_CODE,C_DAI_CODE) B \n"); 
		builder.append("              		  ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE_T, ?) = B.C_KM_CODE \n"); 
		builder.append("            	GROUP BY A.C_KM_CODE,C_PORT_CODE_CLS order by A.C_KM_CODE \n");

		return builder.toString();
	}
	
	/**
	 * 转换行情价格
	 * @param kmCode
	 * @param price
	 * @return
	 */
	private double covertPrice(String kmCode, double price){
		double resPrice = price;
		if(isFormatPrice && kmCode != null && kmCode.contains("OTC")){
			String s[] = kmCode.split("\\.");
			String secCode = "";
			for (int i = 0; i < s.length; i++) {
				if (s[i].contains(" ")) {
					secCode = s[i];
				}
			}
			ISecbaseCacheDataService secBaseService = YssServiceFactory.getInstance().createService(ISecbaseCacheDataService.class);
			SecBase sec = secBaseService.getCacheByKey(secCode);
			if(null != sec && sec.getC_SEC_VAR_CODE() != null && sec.getC_SEC_VAR_CODE().startsWith("JJ")){
				resPrice = Double.parseDouble(decimalFormatPrice.format(price));
			}
		}
		return resPrice;
	}
	
	/**
	 * 
	 * C_KM_CODE, C_KM_NAME, N_VA_PRICE, N_AMOUNT, N_PORT_COST, N_PORT_MV, N_PORT_IV, N_CB_JZ_BL, N_SZ_JZ_BL,N_DETAIL
	 * @param pst
	 * @param rs
	 * @throws SQLException
	 */
	@Override
	protected boolean setPojo(PreparedStatement pst, ResultSet rs, int dataType,HashMap<String, ElecPerRela> elecPerRelaMap) throws Exception {
		boolean isAdd = true;
		double costBL = 0;
		double mvBL = 0;
		double price = 0;
		int index = setPojoHead(pst,rs,dataType);
		ElecPerRela perRelaPojo = elecPerRelaMap.get(rs.getString("C_KM_CODE"))==null ? new ElecPerRela() : elecPerRelaMap.get(rs.getString("C_KM_CODE"));
		if(perRelaPojo != null && "0".equalsIgnoreCase(perRelaPojo.getC_SEND_MODE())){
			return false;
		}
		//BUG #183049 （紧急）【嘉实基金】社保组合估值表中为0的指标不需要生成电子对账  损益平准-未实现 损益平准-已实现
		if(this.isTranSB && ("0106".equalsIgnoreCase(rs.getString("C_KM_CODE"))||"0107".equalsIgnoreCase(rs.getString("C_KM_CODE")))){
			return false;
		}
//		 STORY #51238 （紧急）【嘉实基金】社保估值表指标问题导致电子对账不一致
		if(!this.DZ_BB_DZDZ_XJZBFS && "0089".equalsIgnoreCase(rs.getString("C_KM_CODE"))){
			return false;
		}
		if(HJXJList == null){
			initHJXJList();
		}
		if(this.N_PORT_MV != 0){
			costBL = YssD.div(rs.getDouble("N_PORT_COST") * 100, this.N_PORT_MV);
			costBL = Double.parseDouble(decimalFormat.format(costBL));
		}
		if(this.N_PORT_MV != 0){
			mvBL = YssD.div(rs.getDouble("N_PORT_MV") * 100, this.N_PORT_MV);
			mvBL = Double.parseDouble(decimalFormat.format(mvBL));
		}
		String kmCode = KMCodeTransfer(rs.getString("C_KM_CODE"));
		pst.setString(index + 1, kmCode);//科目代码
		pst.setString(index + 2, rs.getString("C_KM_NAME"));//科目名称
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE5())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE5()))) {
			pst.setDouble(index + 3, 0); //指标发送模式为不发送或者0值发送 置0
		}else{
			price = Double.parseDouble(YssFun.formatNumber(rs.getDouble("N_VA_PRICE"),
					"#0.0000###########"));
			price = covertPrice(kmCode, price);
			//BUG204964【建信基金】电子对账发送估值表行情保留位数错误
			pst.setDouble(index + 3, price);//行情价格
		}
		//add zhangmingbo  STORY 46715 增加电子对账是否发送实收资本数量的参数
		//add zhangmingbo STORY 46827[易方达]【紧急】中行对账需求汇总
		//数量
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE1())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE1()))) {
			pst.setDouble(index + 4, 0); //指标发送模式为不发送或者0值发送 置0
		}else{
			if("0065".equalsIgnoreCase(rs.getString("C_KM_CODE"))){//实收资本
				String DZ_BB_DZDZ_SSZBZSSL = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_SSZBZSSL,rs.getString("C_PORT_CODE_CLS"));
				if("0".equalsIgnoreCase(DZ_BB_DZDZ_SSZBZSSL) ){ //实收资本是否展示
					pst.setDouble(index + 4, 0);//证券数量
				}else{
					pst.setDouble(index + 4, rs.getDouble("N_AMOUNT"));//证券数量
				}
			}else if(HJXJList.contains(rs.getString("C_KM_CODE"))){
				String DZ_BB_DZDZ_HJXJ=paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_HJXJ,rs.getString("C_PORT_CODE_CLS"));
				if("0".equalsIgnoreCase(DZ_BB_DZDZ_HJXJ)){ //合计小计
					pst.setDouble(index + 4, 0);//证券数量
				}else{
					pst.setDouble(index + 4, rs.getDouble("N_AMOUNT"));//证券数量
				}
			}else{
				pst.setDouble(index + 4, rs.getDouble("N_AMOUNT"));//证券数量
			}
		}

		DecimalFormat decimalFormatDWJZ = new DecimalFormat();
		// 获取产品估值参数【单位净值保留位数】
		String blws = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZ_001,rs.getString("C_PORT_CODE_CLS"));
		// 设置基金单位净值数字格式化类的小数保留位数
		if (StringUtil.IsNullOrEmptyT(blws) == false) {
			decimalFormatDWJZ.setMaximumFractionDigits(YssFun.toInt(blws));
			decimalFormatDWJZ.setMinimumFractionDigits(YssFun.toInt(blws));
			// 如下这样设置才能保证转换整数位超过3位数的数时不报错，例如200000000
			decimalFormatDWJZ.setGroupingSize(0);
			decimalFormatDWJZ.setGroupingUsed(false);
		} else {
			decimalFormatDWJZ = decimalFormat;
		}
		// BUG240913【景顺长城基金】电子对账估值表指标基金资产单位净值计算截位方式与产品估值参数所设置的方式不一致
		// 获取产品估值参数【单位净值截位方式】（四舍五入，截位）
		String jwfs = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZ_002,rs.getString("C_PORT_CODE_CLS"));
		if (StringUtil.IsNullOrEmptyT(blws) == false) {
			if ("JW_TRUNC".equalsIgnoreCase(jwfs)) {
				// 截位
				decimalFormatDWJZ.setRoundingMode(RoundingMode.DOWN);
			} else if ("JW_ROUND".equalsIgnoreCase(jwfs)) {
				// 四舍五入
				decimalFormatDWJZ.setRoundingMode(RoundingMode.HALF_UP);
			} else {
				decimalFormatDWJZ.setRoundingMode(RoundingMode.HALF_UP);
			}
		} else {
			decimalFormatDWJZ = decimalFormat;
			decimalFormatDWJZ.setRoundingMode(RoundingMode.HALF_UP);
		}

		// <Start>: modified by HeLiang 2017-09-04 STORY #46410
		// 【易方达基金】【紧急】电子对账生成报文 基金资产单位净值 需要增加参数控制保留小数位数
		// 数字格式化类decimalFormat默认保留位数为4位，这里需要根据参数【电子对账基金单位净值保留小数位数】控制保留位数，故使用新增的decimalFormatDWJZ
		//成本
		DecimalFormat decimalFormat0065 = new DecimalFormat();
		boolean isFormat0065 = false;
		String blxsw0065 = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_SSZBBLXSWS,rs.getString("C_PORT_CODE_CLS"));
		if (StringUtil.IsNullOrEmptyT(blxsw0065) == false) {
			try{
				int num = new Double(blxsw0065).intValue();
				if(num > 0)
				{
					isFormat0065 = true;
					decimalFormat0065.setMaximumFractionDigits(num);
					decimalFormat0065.setMinimumFractionDigits(num);
					decimalFormat0065.setGroupingSize(0);
					decimalFormat0065.setGroupingUsed(false);
					decimalFormat0065.setRoundingMode(RoundingMode.HALF_UP);
				}else{
					isFormat0065 = false;
				}
			}catch(NumberFormatException e){
				logger.error("解析电子对账实收资本保留小数位数失败");
			}
		}
		
		ElecRela elecRela = null;
		if (!StringUtil.IsNullOrEmptyT(rs.getString("C_KM_CODE")) && elecRelaMap.containsKey(rs.getString("C_KM_CODE"))) {
			elecRela = elecRelaMap.get(rs.getString("C_KM_CODE"));
		}
		
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE2())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE2()))) {
			pst.setDouble(index + 5, 0); //指标发送模式为不发送或者0值发送 置0
		} else {
			if ("0016".equalsIgnoreCase(rs.getString("C_KM_CODE"))) {
				if (this.N_AMOUNT != 0) {// 基金资产单位净值 成本列 = 总成本/实收资本
					pst.setDouble(index + 5, Double.parseDouble(decimalFormatDWJZ.format(YssD.div(this.N_PORT_COST, this.N_AMOUNT))));
				} else if (this.N_SSZB_COST != 0) {// BUG182517（紧急）【嘉实基金】社保电子对账基金单位净值的成本与托管行和对不一致
					pst.setDouble(index + 5, Double.parseDouble(decimalFormatDWJZ.format(YssD.div(this.N_PORT_COST, this.N_SSZB_COST))));
				} else {
					pst.setDouble(index + 5, Double.parseDouble(decimalFormatDWJZ.format(rs.getDouble("N_PORT_COST"))));// 成本
				}
			} else if (isFormat0065 && "0065".equalsIgnoreCase(rs.getString("C_KM_CODE"))) {
				pst.setDouble(index + 5, Double.parseDouble(decimalFormat0065.format(rs.getDouble("N_PORT_COST"))));// 成本
			} else if (elecRela != null && !StringUtil.IsNullOrEmptyT(elecRela.getC_DV_ZB_CODE()) && elecRela.getC_DV_ZB_CODE().contains("DWJZ")) {
				pst.setDouble(index + 5, Double.parseDouble(decimalFormatDWJZ.format(rs.getDouble("N_PORT_COST"))));// 成本
			} else {
				pst.setDouble(index + 5, rs.getDouble("N_PORT_COST"));// 成本
			}
		}
		//BUG #149621 南方基金-工行电子对账估值表核对不一致(针对于工行做处理)
		//市值
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE3())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE3()))) {
			pst.setDouble(index + 6, 0); //指标发送模式为不发送或者0值发送 置0
		}else{
			if("0016".equalsIgnoreCase(rs.getString("C_KM_CODE"))&& DZMS_GHMS.equalsIgnoreCase(this.C_DZ_MODE))
			{
				pst.setDouble(index + 6, GH_DWJZ);
			}else if("0016".equalsIgnoreCase(rs.getString("C_KM_CODE"))){
				//add zhangmingbo  bug 170656 电子对账估值表指标基金单位净值无法计算出金额 begin
				if(this.N_AMOUNT==0){
					//如果实收资本为0 取今日单位净值
					pst.setDouble(index + 6, getDWJZ());
				}else{
					// pst.setDouble(6, Double.parseDouble(decimalFormat.format(this.N_PORT_MV/this.N_AMOUNT)));
					pst.setDouble(index + 6, Double.parseDouble(decimalFormatDWJZ.format(YssD.div(this.N_PORT_MV,this.N_AMOUNT))));
				}
				//add zhangminbo bug 170656 电子对账估值表指标基金单位净值无法计算出金额 end 
			}
			else if("0090".equalsIgnoreCase(rs.getString("C_KM_CODE"))&& DZMS_GHMS.equalsIgnoreCase(this.C_DZ_MODE))
			{
				pst.setDouble(index + 6, GH_DWXJCE);
			}else if ("0017".equalsIgnoreCase(rs.getString("C_KM_CODE"))|| "0018".equalsIgnoreCase(rs.getString("C_KM_CODE")) || "0019".equalsIgnoreCase(rs.getString("C_KM_CODE")) || "0137".equalsIgnoreCase(rs.getString("C_KM_CODE"))) {
				pst.setDouble(index + 6, Double.parseDouble(decimalFormatDWJZ.format(rs.getDouble("N_PORT_MV"))));//市值
			}else if(isFormat0065 && "0065".equalsIgnoreCase(rs.getString("C_KM_CODE"))){
				pst.setDouble(index + 6, Double.parseDouble(decimalFormat0065.format(rs.getDouble("N_PORT_MV"))));//市值
			}else if (elecRela != null && !StringUtil.IsNullOrEmptyT(elecRela.getC_DV_ZB_CODE()) && elecRela.getC_DV_ZB_CODE().contains("DWJZ")) {
				pst.setDouble(index + 6, Double.parseDouble(decimalFormatDWJZ.format(rs.getDouble("N_PORT_MV"))));//市值
			} 
			else
			{   //BUG256361【富国基金】【运维】估值表单位净值保留6位但电子对账fzqsz列发送时只有4位问题
				pst.setDouble(index + 6, rs.getDouble("N_PORT_MV"));//市值
			}
		}
		// <End>: modified by HeLiang 2017-09-04 STORY #46410 【易方达基金】【紧急】电子对账生成报文 基金资产单位净值 需要增加参数控制保留小数位数
		//估值增值
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE6())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE6()))) {
			pst.setDouble(index + 7, 0); //指标发送模式为不发送或者0值发送 置0
		}else{
			if("0002".equalsIgnoreCase(rs.getString("C_KM_CODE"))){//对账指标0002 （股票投资小计）估值增值 = 市值-成本
				pst.setDouble(index + 7, Double.parseDouble(decimalFormat.format(rs.getDouble("N_PORT_MV") - rs.getDouble("N_PORT_COST"))));
			}else{//BUG256361【富国基金】【运维】估值表单位净值保留6位但电子对账fzqsz列发送时只有4位问题
				pst.setDouble(index + 7, rs.getDouble("N_PORT_IV"));//估值增值
			}
		}
		//成本占净值比例
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE7())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE7()))) {
			pst.setDouble(index + 8, 0); //指标发送模式为不发送或者0值发送 置0
		}else{
			pst.setDouble(index + 8, costBL);//成本占净值比例
		}
		//BUG #183027 【嘉实基金】社保组合可分配收益发送时是否发送净值占市值比例
		//市值占净值比
		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE8())||
				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE8()))) {
			pst.setDouble(index + 9, 0); //指标发送模式为不发送或者0值发送 置0
		}else{
			if(!this.DZ_BB_DZDZ_KFPSY && "0021".equalsIgnoreCase(rs.getString("C_KM_CODE"))){
				pst.setDouble(index + 9, 0);//市值占净值比例
			}else{
				pst.setDouble(index + 9, mvBL);//市值占净值比例
			}
		}
//		//是否明细类
//		if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE8())||
//				"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE8()))) {
//			pst.setDouble(index + 10, 0); //指标发送模式为不发送或者0值发送 置0
//		}else{
			pst.setInt(index + 10, rs.getInt("N_DETAIL"));//是否明细类
//		}
		if(dataType == NAV_TYPE){
			pst.setString(index + 11, rs.getString("C_NAV_TYPE"));//数据类型
		}else{
			pst.setString(index + 11, "");//数据类型
		}
		if(dataType == NAV_TYPE){
			pst.setString(index + 12, rs.getString("C_PORT_CODE_CLS"));//分级组合代码
			pst.setString(index + 16, rs.getString("C_ZB_NAME"));//指标名称
		}else{
			pst.setString(index + 12, "");//分级组合代码
			pst.setString(index + 16, "");//指标名称
		}
		//STORY59971【景顺长城基金】【道富QD】【紧急】估值表核对增加原币和指标的核对
		pst.setDouble(index + 13, rs.getDouble("N_ORIG_COST"));//原币成本
		pst.setDouble(index + 14, rs.getDouble("N_ORIG_MV"));//原币市值
		pst.setDouble(index + 15, rs.getDouble("N_ORIG_IV"));//原币估增
		switch (dataType) {
		case DETAIL_TYPE:
			double n_price = price;
			double n_amount = rs.getDouble("N_AMOUNT");
			if(0 == rs.getInt("N_DETAIL")){ //非明细科目的行情价格与证券数量为0
				n_price = 0;
				if(!this.isGeneAmount){
					n_amount = 0;
				}
			}
			pst.setDouble(index + 3, n_price);
			pst.setDouble(index + 4, n_amount);
			break;
		default:
			break;
		}

		isAdd = executeSpecial(pst, rs, dataType, index);
		return isAdd;
	}

	/**
	 *add zhangmingbo  
	 */
	private void initHJXJList() {
		HJXJList = new ArrayList<String>();
		HJXJList.add("0001");//[证券投资]合计:
		HJXJList.add("0002");//[股票投资]小计:
		HJXJList.add("0003");//[债券投资]小计:
		HJXJList.add("0004");//[买入返售]小计:
		HJXJList.add("0005");//[卖出回购]小计:
	}

	//获取今日单位净值
	private double getDWJZ() throws Exception {
		PreparedStatement pst=null;
		ResultSet rs=null;
		double dwhz=0;
		try {
			String sql="  select c_km_name from t_r_fr_aststat where c_port_code=? and d_aststat= ? and c_key_code='DWJZ' ";
			pst = conn.prepareStatement(sql);
			pst.setString(1,portCode );
			pst.setDate(2, YssFun.toSqlDate(geneDate));
			rs = pst.executeQuery();
			if(rs.next()){
				String string = rs.getString("c_km_name");
				dwhz = Double.parseDouble(string);
			}
		} catch (Exception e) {
			return 0;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return dwhz;
	}

	/**
	 * 汇总项，指标项数据生成
	 * @return
	 */
	protected int geneGZBNavTypeData() throws Exception {
		PreparedStatement pst = null;
		PreparedStatement insertPst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			insertPst = conn.prepareStatement(getInsertSql());
			pst = conn.prepareStatement(getGZBNavTypeSql());
			int index = 1;
			pst.setString(index++, portCode);
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			pst.setString(index++, portCode);
			pst.setString(index++, portCode);
			
			pst.setString(index++, portCode);
			
			rs = pst.executeQuery();			
			if(DZMS_GHMS.equalsIgnoreCase(this.C_DZ_MODE))
			{
				while(rs.next())
				{
					if(rs.getString("C_KM_CODE").equalsIgnoreCase("0018"))
					{
						GH_DWJZ = rs.getDouble("N_PORT_MV");
					}
					if(rs.getString("C_KM_CODE").equalsIgnoreCase("0108"))
					{
						GH_DWXJCE = rs.getDouble("N_PORT_MV");
					}
				}
			}	
			DbFun.closeResultSetFinal(rs);
			rs = pst.executeQuery();	
			//BUG211964电子对账个性指标设置取值逻辑不对
			//减少查询次数
			//HashMap<String, ElecPerRela> elecPerRelaMap = getPerRelaByPortAndDZCode(portCode, "1011");
			while (rs.next()) {
				boolean isAdd = setPojo(insertPst, rs, NAV_TYPE,elecPerRelaMap);
				if(isAdd){
					insertPst.addBatch();
					if(++count % batchSize == 0) {
						insertPst.executeBatch();
					}
				}
			}
			insertPst.executeBatch();
			insertPst.close();
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, insertPst);
		}
		return count;

	}
	/**
	 * 估值表发送模式有估值增值数据生成
	 * @return
	 */
	protected String getGZBNavTypeSql() {
		StringBuilder builder = new StringBuilder();
		String suffix = "";
		if(isTranSB){
			suffix = " regexp_replace(A.C_KEY_CODE,'SB', NULL)AS ";
		}
		//BUG267310【华安基金】对账指标关联设置优化	将对账指标提取出来，方便关联
		//对账指标，特定托管行+托管行为空的
		builder.append(" with BI_RELA as ( ");
		builder.append(" select C_IDEN,C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_PORT_CODE_CLS,C_DV_ZB_CODE,C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME, ");
		builder.append(" C_ORG_CODE,C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL,C_DV_PORT_CLS,C_REPORT_CODE,C_ROW ");
		builder.append(" from T_Z_BI_RELA ");
		builder.append(" WHERE N_CHECK_STATE = 1 ");
		builder.append(" and C_DZ_CODE = '1011' ");
		builder.append(" and (C_ORG_CODE in (select C_rela_code from T_P_AB_PORT_RELA where c_rela_Type = 'RELA_ORG' and c_port_code = ?)  ");
		builder.append(" or trim(C_ORG_CODE) is null) ) ");
		// 非分级指标项
		builder.append("select C_ZB_CODE as C_KM_CODE, \n");
		builder.append(" case when C_NAV_TYPE = 'TOTAL' then C_ZB_NAME \n");
		builder.append("      when C_NAV_TYPE = 'TOTAL_ALL' then C_KM_NAME \n");
		builder.append("      else C_ZB_NAME end as C_KM_NAME, \n"); 
		builder.append(" nvl(N_VA_PRICE,0) as N_VA_PRICE, nvl(N_AMOUNT,0) as N_AMOUNT, nvl(N_ORIG_COST,0) as N_ORIG_COST,nvl(N_PORT_COST,0) as N_PORT_COST, \n"); 
		builder.append(" case when C_NAV_TYPE = 'TOTAL' then N_ORIG_MV \n"); 
		builder.append("      when C_NAV_TYPE = 'TOTAL_ALL' then to_number(NVL(REGEXP_REPLACE(C_KM_NAME, '[^0-9.-]'),0)) \n"); 
		builder.append("      else 0 end as N_ORIG_MV, \n"); 
		builder.append(" case when C_NAV_TYPE = 'TOTAL' then N_PORT_MV \n"); 
		builder.append("      when C_NAV_TYPE = 'TOTAL_ALL' then to_number(NVL(REGEXP_REPLACE(C_KM_NAME, '[^0-9.-]'),0)) \n"); 
		builder.append("      else 0 end as N_PORT_MV, \n"); 
		//builder.append(" nvl(N_PORT_IV,0) as N_PORT_IV, 0 as N_DETAIL,C_NAV_TYPE \n");
		builder.append(" nvl(N_ORIG_IV,0) as N_ORIG_IV,nvl(N_PORT_IV,0) as N_PORT_IV, 0 as N_DETAIL,C_NAV_TYPE, C_PORT_CODE_CLS, \n"); 
		builder.append("      C_ZB_NAME \n"); //BUG289235【鹏华基金】产生电子对账的分级基金，A组合的电子对账产生的电子对账指标是其他组合的 添加C_ZB_NAME
		//builder.append(" from (select A.C_NAV_TYPE,A.C_KM_CODE,A.C_KM_NAME,A.N_VA_PRICE,A.N_AMOUNT,A.N_PORT_COST,A.N_PORT_MV,A.N_PORT_IV, B.C_ZB_CODE, B.C_ZB_NAME from \n");
		builder.append(" from (select A.C_NAV_TYPE,A.C_KM_CODE,A.C_KM_NAME,A.N_VA_PRICE,A.N_AMOUNT,A.N_ORIG_COST,A.N_PORT_COST,A.N_ORIG_MV,A.N_PORT_MV,A.N_ORIG_IV,A.N_PORT_IV, B.C_ZB_CODE, B.C_ZB_NAME, '' as C_PORT_CODE_CLS from \n");
		builder.append("		(select A.C_NAV_TYPE,").append(suffix).append(" C_KEY_CODE, A.C_KM_CODE,A.C_KM_NAME,A.N_VA_PRICE,A.N_AMOUNT,A.N_ORIG_COST,A.N_PORT_COST,A.N_ORIG_MV,A.N_PORT_MV, A.N_ORIG_IV,A.N_PORT_IV,  \n");
		builder.append(" ROW_NUMBER() OVER(PARTITION BY A.c_Key_Code ORDER BY C_KM_CODE) RANK \n"); 
		builder.append("  from T_R_FR_ASTSTAT A  \n"); 
		builder.append(" 				where A.c_port_code = ? and A.D_ASTSTAT = to_date(?, 'yyyy-MM-dd') \n"); 
		builder.append("   				and A.C_NAV_TYPE in ('TOTAL', 'TOTAL_ALL') and instr(A.C_KM_CODE,'_') = 0 ) A \n"); 
		builder.append("   		 join ( "); //20171115 wlx BUG179939差异数据显示内容  ,right join 改为join  跟估值表关联不上的指标不生成
		builder.append(" SELECT C_ZB_CODE,C_ZB_NAME,C_DV_ZB_CODE FROM BI_RELA where trim(C_PORT_CODE_CLS) is null ");
		builder.append(" and trim(C_DV_PORT_CLS_TYPE) is null ");
		builder.append(" and trim(c_Dv_Port_Cls_Level) is null ");
		builder.append(" and trim(C_DV_PORT_CLS) is null ");
		builder.append("					) B \n"); 
		builder.append("   		ON B.C_DV_ZB_CODE = A.c_Key_Code AND A.RANK = 1 ) \n"); 
		
		builder.append("union all \n"); 
		// 分级指标项  对账指标分级组合不为空 + 对账指标分级组合为空，分级类型不为空
		//STORY47037嘉实基金电子对账管理查看托管人反馈的信息时，分级指标名称需要展示至具体的分级代码
		//1.分级产品首先通过[分级组合]字段生成分级合计项
		//2.如果[分级组合]没有设置，可以通过[分级类型]、[分级级别]和[级别类型]判断生成所有符合[分级类型]、[分级级别]和[级别类型]指标设置的分级组合合计项指标
		//[分级类型]、[分级级别]和[级别类型]的判断规则：字段值为空不参与判断，不为空则参与判断
		if(this.DZ_BB_DZDZ_FJHJXJSFJZH){//STORY50919QD分级产品电子对账合计项指标根据级别、分级组合对账
			builder.append(" select case when INSTR(NVL(C_KM_CODE,''), '_') <> 0 then \n"); 
			builder.append("             C_ZB_CODE||trim(substr(C_KM_CODE,INSTR(C_KM_CODE, '_'))) \n");
			builder.append("             else C_ZB_CODE ||'_'||C_PORT_CODE_CLS end as C_KM_CODE, \n");
		}else{
			builder.append(" select C_ZB_CODE as C_KM_CODE, \n"); //STORY #39738 南方基金-分级基金发送估值表电子对账，分级指标需支持设置单独的对账指标代码
		}
		builder.append(" case when C_NAV_TYPE = 'TOTAL' then C_ZB_NAME \n"); 
		builder.append("      when C_NAV_TYPE = 'TOTAL_ALL' then C_KM_NAME \n");
		builder.append("      else C_ZB_NAME end as C_KM_NAME, \n"); 
		builder.append("  nvl(N_VA_PRICE,0) as N_VA_PRICE, nvl(N_AMOUNT,0) as N_AMOUNT,nvl(N_ORIG_COST,0) as N_ORIG_COST, nvl(N_PORT_COST,0) as N_PORT_COST, \n"); 
		builder.append(" case when C_NAV_TYPE = 'TOTAL' then N_ORIG_MV \n"); 
		builder.append("      when C_NAV_TYPE = 'TOTAL_ALL' then to_number(NVL(REGEXP_REPLACE(C_KM_NAME, '[^0-9.-]'),0)) \n"); 
		builder.append("      else 0 end as N_ORIG_MV, \n");
		builder.append(" case when C_NAV_TYPE = 'TOTAL' then N_PORT_MV \n"); 
		builder.append("      when C_NAV_TYPE = 'TOTAL_ALL' then to_number(NVL(REGEXP_REPLACE(C_KM_NAME, '[^0-9.-]'),0)) \n"); 
		builder.append("      else 0 end as N_PORT_MV, \n"); 
		//builder.append(" nvl(N_PORT_IV,0) as N_PORT_IV, 0 as N_DETAIL,C_NAV_TYPE \n"); 
		builder.append(" nvl(N_ORIG_IV,0) as N_ORIG_IV,nvl(N_PORT_IV,0) as N_PORT_IV, 0 as N_DETAIL,C_NAV_TYPE, C_PORT_CODE_CLS, \n"); 
		builder.append("      C_ZB_NAME \n"); //BUG289235【鹏华基金】产生电子对账的分级基金，A组合的电子对账产生的电子对账指标是其他组合的 添加C_ZB_NAME
		builder.append(" from (select A.C_NAV_TYPE,A.C_KM_CODE,A.C_KM_NAME,A.N_VA_PRICE,A.N_AMOUNT,A.N_ORIG_COST,A.N_PORT_COST,A.N_ORIG_MV,A.N_PORT_MV,A.N_ORIG_IV,A.N_PORT_IV, B.C_ZB_CODE, B.C_ZB_NAME,B.C_PORT_CODE_CLS from  \n");
		builder.append("		(select A.C_NAV_TYPE,").append(suffix).append(" C_KEY_CODE,A.C_KM_CODE,A.C_KM_NAME,A.N_VA_PRICE,A.N_AMOUNT,A.N_ORIG_COST,A.N_PORT_COST,A.N_ORIG_MV,A.N_PORT_MV, A.N_ORIG_IV,A.N_PORT_IV from T_R_FR_ASTSTAT A \n");
		builder.append(" 				where A.c_port_code = ? and A.D_ASTSTAT = to_date(?, 'yyyy-MM-dd') \n"); 
		builder.append("   				and A.C_NAV_TYPE in ('TOTAL', 'TOTAL_ALL') and instr(A.C_KM_CODE,'_') > 0 ) A \n"); 
		builder.append("          join (select C_ZB_CODE, C_ZB_NAME, C_PORT_CODE_CLS, C_DV_ZB_CODE \n"); //20171115 wlx BUG179939差异数据显示内容  ,right join 改为join  跟估值表关联不上的指标不生成
		builder.append("                   from BI_RELA WHERE Trim(C_PORT_CODE_CLS) is not null \n"); 
		builder.append("						AND C_PORT_CODE_CLS IN(select C_PORT_CLS_CODE from t_p_Aa_Port_Cls where C_PORT_CODE = ?)");
		builder.append("            union \n"); 
		//STORY47037嘉实基金电子对账管理查看托管人反馈的信息时，分级指标名称需要展示至具体的分级代码   代码新增开始
		builder.append("            select C_ZB_CODE, C_ZB_NAME, C.c_Port_Cls_Code as C_PORT_CODE_CLS, \n"); 
		//BUG238403嘉实基金-电子对账-估值表指标优先级问题
		//builder.append("                   C_DV_ZB_CODE from BI_RELA R \n"); 
		builder.append("                   C_DV_ZB_CODE from ( \n"); 
		builder.append(" select C_IDEN,C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_PORT_CODE_CLS,C_DV_ZB_CODE,C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME, \n");
		builder.append(" C_ORG_CODE,C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL,C_DV_PORT_CLS,C_REPORT_CODE,C_ROW \n");
		builder.append(" FROM BI_RELA A where not exists ( select 1 from BI_RELA B WHERE A.C_ZB_CODE = B.C_ZB_CODE and Trim(B.C_PORT_CODE_CLS) is not null AND B.C_PORT_CODE_CLS IN (select C_PORT_CLS_CODE from t_p_Aa_Port_Cls where C_PORT_CODE = ?)  ) ) R  \n");
		builder.append("            JOIN (select C_PORT_CLS_CODE, C_DV_PORT_CLS_TYPE, C_DV_PORT_CLS, C_DV_PORT_CLS_LEVEL from t_p_Aa_Port_Cls where C_PORT_CODE = ?) C \n"); 
		builder.append("                   on 1=( \n");
		builder.append("                   case when Trim(R.C_PORT_CODE_CLS) is null and trim(R.C_DV_PORT_CLS) is not null and trim(R.C_DV_PORT_CLS_TYPE) is null and trim(R.C_DV_PORT_CLS_LEVEL) is null and C.C_DV_PORT_CLS = R.C_DV_PORT_CLS then 1 \n"); 
		builder.append("                   when Trim(R.C_PORT_CODE_CLS) is null and trim(R.C_DV_PORT_CLS) is null and trim(R.C_DV_PORT_CLS_TYPE) is not null and trim(R.C_DV_PORT_CLS_LEVEL) is null and C.C_DV_PORT_CLS_TYPE = R.C_DV_PORT_CLS_TYPE  then 1  \n"); 
		builder.append("                   when Trim(R.C_PORT_CODE_CLS) is null and trim(R.C_DV_PORT_CLS) is null and trim(R.C_DV_PORT_CLS_TYPE) is null and trim(R.C_DV_PORT_CLS_LEVEL) is not null and C.C_DV_PORT_CLS_LEVEL = R.C_DV_PORT_CLS_LEVEL  then 1  \n"); 
		builder.append("                   when Trim(R.C_PORT_CODE_CLS) is null and trim(R.C_DV_PORT_CLS) is not null and trim(R.C_DV_PORT_CLS_TYPE) is not null and trim(R.C_DV_PORT_CLS_LEVEL) is null and C.C_DV_PORT_CLS = R.C_DV_PORT_CLS and C.C_DV_PORT_CLS_TYPE = R.C_DV_PORT_CLS_TYPE then 1  \n"); 
		builder.append("                   when Trim(R.C_PORT_CODE_CLS) is null and trim(R.C_DV_PORT_CLS) is not null and trim(R.C_DV_PORT_CLS_TYPE) is null and trim(R.C_DV_PORT_CLS_LEVEL) is not null and C.C_DV_PORT_CLS = R.C_DV_PORT_CLS and C.C_DV_PORT_CLS_LEVEL = R.C_DV_PORT_CLS_LEVEL then 1  \n"); 
		builder.append("                   when Trim(R.C_PORT_CODE_CLS) is null and trim(R.C_DV_PORT_CLS) is null and trim(R.C_DV_PORT_CLS_TYPE) is not null and trim(R.C_DV_PORT_CLS_LEVEL) is not null and C.C_DV_PORT_CLS_TYPE = R.C_DV_PORT_CLS_TYPE and C.C_DV_PORT_CLS_LEVEL = R.C_DV_PORT_CLS_LEVEL then 1  \n"); 
		builder.append("                   when Trim(R.C_PORT_CODE_CLS) is null and trim(R.C_DV_PORT_CLS) is not null and trim(R.C_DV_PORT_CLS_TYPE) is not null and trim(R.C_DV_PORT_CLS_LEVEL) is not null and C.C_DV_PORT_CLS = R.C_DV_PORT_CLS and C.C_DV_PORT_CLS_TYPE = R.C_DV_PORT_CLS_TYPE and C.C_DV_PORT_CLS_LEVEL = R.C_DV_PORT_CLS_LEVEL then 1  \n"); 
		builder.append("                   else 0  \n"); 
		builder.append("                   end )  \n"); 
		builder.append("            WHERE R.N_CHECK_STATE = 1 and R.C_DZ_CODE = '1011' and Trim(R.C_PORT_CODE_CLS) is null ) B\n"); 
		//STORY47037嘉实基金电子对账管理查看托管人反馈的信息时，分级指标名称需要展示至具体的分级代码   代码新增结束
		builder.append("   ON B.C_DV_ZB_CODE = A.c_Key_Code \n"); 
		builder.append("     and SUBSTR(A.C_KM_CODE,INSTR(A.C_KM_CODE, '_', 1, 1) + 1) = B.C_PORT_CODE_CLS) \n"); 

		return builder.toString();
	}
	/**
	 * 估值表发送模式有估值增值数据生成
	 * @return
	 */
	protected int geneGZBYGZData(String gzDetail) throws Exception {
		PreparedStatement pst = null;
		PreparedStatement insertPst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			insertPst = conn.prepareStatement(getInsertSql());
			pst = conn.prepareStatement(this.getGZBYGZSql(gzDetail));
			int index = 1;
			if(this.DZ_BB_DZDZ_YSGJGYJZCB){
				pst.setString(index++, planCode);
			}
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			pst.setString(index++, planCode);
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			//BUG278146【华安基金】产生电子对账时，可退替代估增在估值表和余额表取值两次，导致对账不一致
			//BUG240356【国泰基金】生成电子对账，科目double
			pst.setString(index++, this.fsn);
			rs = pst.executeQuery();
			//BUG211964电子对账个性指标设置取值逻辑不对
			//减少查询次数
			//HashMap<String, ElecPerRela> elecPerRelaMap = getPerRelaByPortAndDZCode(portCode, "1011");
			while (rs.next()) {
				boolean isAdd = setPojo(insertPst, rs, YGZ_TYPE, elecPerRelaMap);
				if(isAdd){
					insertPst.addBatch();
					if(++count % batchSize == 0) {
						insertPst.executeBatch();
					}
				}
			}
			insertPst.executeBatch();
			insertPst.close();
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, insertPst);
		}
		return count;
	}
	/**
	 * 估值表发送模式有估值增值数据生成
	 * @return
	 */
	protected String getGZBYGZSql(String gzDetail) throws Exception {
		String sql = "";
		if(this.DZ_BB_DZDZ_YSGJGYJZCB){
			sql = getGZBYGZSql2(gzDetail);
		}else {
			sql = getGZBYGZSqlComm(gzDetail);
		}
		return sql;
	}
	/**
	 * 只取核算项目为C_DAI_CODE = 'ZQTZ_GYBD'
	 * @return
	 * @throws Exception
	 */
//	protected String getGZBYGZSql1() throws Exception {
//		StringBuilder builder = new StringBuilder();
//
//		builder.append("select C_KM_CODE, C_KM_NAME,");
//		builder.append("            0 as N_VA_PRICE,"); 
//		builder.append("            0 as N_AMOUNT,"); 
//		builder.append("            N_PORT_MONEY as N_PORT_COST,"); 
//		builder.append("            N_PORT_MONEY as N_PORT_MV,"); 
//		builder.append("            0 as N_PORT_IV,"); 
//		builder.append("            N_DETAIL as N_DETAIL"); 
//		builder.append("       from (SELECT A.N_PORT_MONEY * B.N_FUND_WAY AS N_PORT_MONEY,"); 
//		builder.append("                    A.C_KM_CODE,"); 
//		builder.append("                    A.C_KM_NAME,"); 
//		builder.append("                    0 as N_DETAIL"); 
//		builder.append("               FROM T_D_AI_STOCK A"); 
//		builder.append("               LEFT JOIN (SELECT N_FUND_WAY"); 
//		builder.append("                           FROM T_S_DAI_ITEM B"); 
//		builder.append("                          WHERE B.C_DAI_CODE = 'ZQTZ_GYBD') B ON 1 = 1"); 
//		builder.append("              WHERE substr(a.c_year_month, -2, 2) <> '00'"); 
//		builder.append("                AND A.C_PORT_CODE = ?"); 
//		builder.append("                AND A.D_STOCK = to_date(?, 'yyyy-MM-dd')"); 
//		builder.append("                AND A.C_DAI_CODE = 'ZQTZ_GYBD'"); 
//		builder.append("             union all"); 
//		builder.append("               SELECT sum(A.N_PORT_MONEY * B.N_FUND_WAY) AS N_PORT_MONEY,"); 
//		builder.append("                            C.C_KM_CODE AS C_KM_CODE,"); 
//		builder.append("                            max(C.C_KM_NAME),"); 
//		builder.append("                            1 as N_DETAIL"); 
//		builder.append("                       FROM T_D_AI_STOCK A"); 
//		builder.append("                       LEFT JOIN (SELECT N_FUND_WAY"); 
//		builder.append("                                   FROM T_S_DAI_ITEM B"); 
//		builder.append("                                  WHERE B.C_DAI_CODE = 'ZQTZ_GYBD') B ON 1 = 1"); 
//		builder.append("                       JOIN T_F_SC_KM C"); 
//		builder.append("                             ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3) = C.C_KM_CODE AND C.C_PLAN_CODE = ? "); 
//		builder.append("                      WHERE substr(a.c_year_month, -2, 2) <> '00'"); 
//		builder.append("                        AND A.C_PORT_CODE = ?"); 
//		builder.append("                        AND A.D_STOCK = to_date(?, 'yyyy-MM-dd')"); 
//		builder.append("                        AND A.C_DAI_CODE = 'ZQTZ_GYBD' group by C.C_KM_CODE)"); 
//		return builder.toString();
//	}

	/**
	 * 华泰外包估增数据sql
	 * 取核算项目包含公允变动
	 * @return
	 * @throws Exception
	 */
	protected String getGZBYGZSqlComm(String gzDetail) throws Exception {
		StringBuilder builder = new StringBuilder();

		builder.append("select\n" );
		builder.append("  C_KM_CODE, C_KM_NAME, 0 as N_VA_PRICE,\n" ); 
		builder.append("  0 as N_AMOUNT,N_ORIG_MONEY as N_ORIG_COST, N_PORT_MONEY as N_PORT_COST,\n" ); 
		builder.append("  N_ORIG_MONEY as N_ORIG_MV,N_PORT_MONEY as N_PORT_MV, 0 as N_ORIG_IV,0 as N_PORT_IV, N_DETAIL,C_PORT_CLS_CODE AS C_PORT_CODE_CLS \n" ); 
		builder.append("from (SELECT \n" ); 
		//STORY #103375 【太平养老】发送电子对账估值表，发现有重复科目
		if(isMergeData){
			builder.append(" SUM(A.N_ORIG_MONEY * B.N_FUND_WAY) AS N_ORIG_MONEY,\n" ); 
			builder.append(" SUM(A.N_PORT_MONEY * B.N_FUND_WAY) AS N_PORT_MONEY,\n" ); 
		}else{
			builder.append(" A.N_ORIG_MONEY * B.N_FUND_WAY AS N_ORIG_MONEY,A.N_PORT_MONEY * B.N_FUND_WAY AS N_PORT_MONEY,\n" ); 
		}
		builder.append("             A.C_KM_CODE, A.C_KM_NAME, " ); 
		if (gzDetail != null && gzDetail.equalsIgnoreCase("1")) {
			builder.append(" 1 as N_DETAIL, ");
		} else {
			builder.append(" 0 as N_DETAIL, ");
		}
		builder.append(" A.C_PORT_CLS_CODE "); 
		builder.append("      FROM T_D_AI_STOCK A\n" ); 
		builder.append("      LEFT JOIN (SELECT N_FUND_WAY,C_DAI_CODE FROM T_S_DAI_ITEM B\n" ); 
		builder.append("                  WHERE B.C_DAI_CODE LIKE'%GYBD%') B ON B.C_DAI_CODE = A.C_DAI_CODE\n" ); 
		builder.append("      WHERE substr(a.c_year_month, -2, 2) <> '00'\n" ); 
		builder.append("            AND A.C_PORT_CODE = ?\n" ); 
		builder.append("            AND A.D_STOCK = to_date(?, 'yyyy-MM-dd')\n" ); 
		builder.append("            AND A.C_DAI_CODE LIKE'%GYBD%' and A.C_DV_KM_CLS <> 'KC_SY'\n" ); 
		//STORY #103375 【太平养老】发送电子对账估值表，发现有重复科目
		if(isMergeData){
			builder.append("        group by a.C_KM_CODE, a.C_KM_NAME, A.C_PORT_CLS_CODE \n" ); 
			builder.append("        having SUM(A.N_PORT_MONEY * B.N_FUND_WAY) > 0 \n" ); 
		}
		builder.append("      union all\n" ); 
		builder.append("      SELECT sum(A.N_ORIG_MONEY * B.N_FUND_WAY) AS N_ORIG_MONEY,sum(A.N_PORT_MONEY * B.N_FUND_WAY) AS N_PORT_MONEY,\n" ); 
		builder.append("             C.C_KM_CODE, C.C_KM_NAME, " ); 
		if (gzDetail != null && gzDetail.equalsIgnoreCase("1")) {
			builder.append(" 0 as N_DETAIL, ");
		} else {
			builder.append(" 1 as N_DETAIL, ");
		}
		builder.append(" A.C_PORT_CLS_CODE "); 
		builder.append("      FROM T_D_AI_STOCK A\n" ); 
		builder.append("      LEFT JOIN (SELECT N_FUND_WAY,C_DAI_CODE FROM T_S_DAI_ITEM B\n" ); 
		builder.append("                  WHERE B.C_DAI_CODE LIKE'%GYBD%') B ON B.C_DAI_CODE = A.C_DAI_CODE\n" ); 
		//BUG244495生成电子对账针对估值明细科目，超过4级科目无法生成父节点
		builder.append("      JOIN T_F_SC_KM C ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, ").append(this.kmLevel-1).append(" ) = C.C_KM_CODE AND C.C_PLAN_CODE = ?  \n" ); 
		builder.append("      WHERE substr(a.c_year_month, -2, 2) <> '00'\n" );
		//BUG278146【华安基金】产生电子对账时，可退替代估增在估值表和余额表取值两次，导致对账不一致 限制整个估增数据
		//BUG240356【国泰基金】生成电子对账，科目double
		//如果明细科目已经存在相同的三级科目，估增不需要再汇总三级科目.
		//builder.append("            AND not exists ( SELECT 1 FROM T_D_ER_GZ G where G.C_KM_CODE = C.C_KM_CODE and G.C_SN = ? ) \n"); 
		builder.append("            AND A.C_PORT_CODE = ?\n" ); 
		builder.append("            AND A.D_STOCK = to_date(?, 'yyyy-MM-dd')\n" ); 
		builder.append("            AND A.C_DAI_CODE LIKE'%GYBD%' and A.C_DV_KM_CLS <> 'KC_SY' group by C.C_KM_CODE, C.C_KM_NAME ,A.C_PORT_CLS_CODE  \n" ); 
		builder.append(" ) C ");
		builder.append(" where not exists ( SELECT 1 FROM T_D_ER_GZ G where G.C_KM_CODE = C.C_KM_CODE and G.C_SN = ? ) ");

		return builder.toString();
	}
	
	/**
	 * 华泰外包估增数据sql
	 * 取核算项目包含公允变动
	 * @return
	 * @throws Exception
	 */
	protected String getGZBYGZSql2(String gzDetail) throws Exception {
		StringBuilder builder = new StringBuilder();

		builder.append("select\n" );
		builder.append("  C_KM_CODE, C_KM_NAME, 0 as N_VA_PRICE,\n" ); 
		builder.append("  0 as N_AMOUNT,N_ORIG_MONEY as N_ORIG_COST, N_PORT_MONEY as N_PORT_COST,\n" ); 
		builder.append("  N_ORIG_MONEY as N_ORIG_MV,N_PORT_MONEY_MV as N_PORT_MV, 0 as N_ORIG_IV,N_PORT_MONEY_MV - N_PORT_MONEY as N_PORT_IV, N_DETAIL, C_PORT_CLS_CODE AS C_PORT_CODE_CLS\n" ); 
		builder.append("from (SELECT SUM(A.N_ORIG_MONEY * B.N_FUND_WAY) AS N_ORIG_MONEY, \n" ); 
		
		if(this.DZ_BB_DZDZ_YSGJGYJZCB){
			builder.append(" CASE WHEN MAX(C.C_DAI_CODE) = 'YSGJ_GYBD' THEN  0 \n");
			builder.append(" ELSE SUM(A.N_PORT_MONEY * B.N_FUND_WAY) END AS N_PORT_MONEY, \n");
		}else{
			builder.append("  SUM(A.N_PORT_MONEY * B.N_FUND_WAY) AS N_PORT_MONEY, \n"); 
		}
		
		builder.append(" SUM(A.N_PORT_MONEY * B.N_FUND_WAY) AS N_PORT_MONEY_MV, " ); 
		builder.append("             MAX(A.C_KM_CODE) C_KM_CODE, MAX(A.C_KM_NAME) C_KM_NAME, " ); 
		if (gzDetail != null && gzDetail.equalsIgnoreCase("1")) {
			builder.append(" 1 as N_DETAIL, ");
		} else {
			builder.append(" 0 as N_DETAIL, ");
		}
		builder.append(" A.C_PORT_CLS_CODE "); 
		builder.append("      FROM T_D_AI_STOCK A\n" ); 
		builder.append("      LEFT JOIN (SELECT N_FUND_WAY,C_DAI_CODE FROM T_S_DAI_ITEM B\n" ); 
		builder.append("                  WHERE B.C_DAI_CODE LIKE'%GYBD%') B ON B.C_DAI_CODE = A.C_DAI_CODE\n" ); 
		builder.append("      JOIN T_F_SC_KM C ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, ").append(this.kmLevel-1).append(" ) = C.C_KM_CODE AND C.C_PLAN_CODE = ?  \n" ); 
		builder.append("      WHERE substr(a.c_year_month, -2, 2) <> '00'\n" ); 
		builder.append("            AND A.C_PORT_CODE = ?\n" ); 
		builder.append("            AND A.D_STOCK = to_date(?, 'yyyy-MM-dd')\n" ); 
		builder.append("            AND A.C_DAI_CODE LIKE'%GYBD%' and A.C_DV_KM_CLS <> 'KC_SY'\n" ); 
		builder.append(" 			group by C.C_KM_CODE, C.C_KM_NAME ,A.C_PORT_CLS_CODE \n" ); 
		builder.append("      union all\n" ); 
		builder.append("      SELECT sum(A.N_ORIG_MONEY * B.N_FUND_WAY) AS N_ORIG_MONEY, \n" ); 
		if(this.DZ_BB_DZDZ_YSGJGYJZCB){
			builder.append(" CASE WHEN MAX(C.C_DAI_CODE) = 'YSGJ_GYBD' THEN  0 \n");
			builder.append(" ELSE SUM(A.N_PORT_MONEY * B.N_FUND_WAY) END AS N_PORT_MONEY, \n");
		}else{
			builder.append("  SUM(A.N_PORT_MONEY * B.N_FUND_WAY) AS N_PORT_MONEY, \n"); 
		}
		builder.append(" \n" ); 
		builder.append(" \n" );
		builder.append("            sum(A.N_PORT_MONEY * B.N_FUND_WAY) AS N_PORT_MONEY_MV, " ); 
		builder.append("             C.C_KM_CODE, C.C_KM_NAME, " ); 
		if (gzDetail != null && gzDetail.equalsIgnoreCase("1")) {
			builder.append(" 0 as N_DETAIL, ");
		} else {
			builder.append(" 1 as N_DETAIL, ");
		}
		builder.append(" A.C_PORT_CLS_CODE "); 
		builder.append("      FROM T_D_AI_STOCK A\n" ); 
		builder.append("      LEFT JOIN (SELECT N_FUND_WAY,C_DAI_CODE FROM T_S_DAI_ITEM B\n" ); 
		builder.append("                  WHERE B.C_DAI_CODE LIKE'%GYBD%') B ON B.C_DAI_CODE = A.C_DAI_CODE\n" ); 
		//BUG244495生成电子对账针对估值明细科目，超过4级科目无法生成父节点
		builder.append("      JOIN T_F_SC_KM C ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, ").append(this.kmLevel-1).append(" ) = C.C_KM_CODE AND C.C_PLAN_CODE = ?  \n" ); 
		builder.append("      WHERE substr(a.c_year_month, -2, 2) <> '00'\n" );
		//BUG278146【华安基金】产生电子对账时，可退替代估增在估值表和余额表取值两次，导致对账不一致 限制整个估增数据
		//BUG240356【国泰基金】生成电子对账，科目double
		//如果明细科目已经存在相同的三级科目，估增不需要再汇总三级科目.
		//builder.append("            AND not exists ( SELECT 1 FROM T_D_ER_GZ G where G.C_KM_CODE = C.C_KM_CODE and G.C_SN = ? ) \n"); 
		builder.append("            AND A.C_PORT_CODE = ?\n" ); 
		builder.append("            AND A.D_STOCK = to_date(?, 'yyyy-MM-dd')\n" ); 
		builder.append("            AND A.C_DAI_CODE LIKE'%GYBD%' and A.C_DV_KM_CLS <> 'KC_SY' group by C.C_KM_CODE, C.C_KM_NAME,A.C_PORT_CLS_CODE \n" ); 
		builder.append(" ) C ");
		builder.append(" where not exists ( SELECT 1 FROM T_D_ER_GZ G where G.C_KM_CODE = C.C_KM_CODE and G.C_SN = ? ) ");

		return builder.toString();
	}
	/**
	 * 预留给客户个性化处理方法，待重写
	 */
	@Override
	protected boolean executeSpecial(PreparedStatement pst, ResultSet rs, int dataType, int indexHead) throws Exception{
		boolean isAdd = true;
		//BUG199876汇添富项目，估值表产生电子对账失败，报空指针
		if(YssContextFactory.getInstance() == null || YssContextFactory.getInstance().getLicense() == null){
			logger.info("获取YssContextFactory.getInstance().getLicense()为NULL");
			return true;
		}
		if(LicCompanyName.COMPANY_GFSC.equalsIgnoreCase(YssContextFactory.getInstance().getLicense().getCompany())){
			if(DZMS_GHMS_Z.equalsIgnoreCase(this.C_DZ_MODE)){
				String kmCode = KMCodeTransfer(rs.getString("C_KM_CODE"));
				if(0 == rs.getInt("N_DETAIL") && 
						("1031.01".equalsIgnoreCase(kmCode) || "1031.02".equalsIgnoreCase(kmCode)
								||"1031.03".equalsIgnoreCase(kmCode)||"1021.01".equalsIgnoreCase(kmCode)
								||"1021.02".equalsIgnoreCase(kmCode)||"1021.03".equalsIgnoreCase(kmCode))){
					isAdd = false;
					return isAdd;
				}
				if("0016".equalsIgnoreCase(rs.getString("C_KM_CODE"))){ //估值增值=市值-成本
					pst.setDouble(indexHead + 7, rs.getDouble("N_PORT_MV") - rs.getDouble("N_PORT_COST"));
				}
			}
			if(!DZMS_ZSZQ.equalsIgnoreCase(this.C_DZ_MODE) && this.N_AMOUNT > 0){
				if("0016".equalsIgnoreCase(rs.getString("C_KM_CODE"))){//基金资产单位净值  成本列 = 总成本/实收资本
					// modified by HeLiang 2017-09-04 STORY #46410 【易方达基金】【紧急】电子对账生成报文 基金资产单位净值 需要增加参数控制保留小数位数
					// 数字格式化类decimalFormat默认保留位数为4位，这里需要根据参数【电子对账基金单位净值保留小数位数】控制保留位数，故使用新增的decimalFormatDWJZ
					// pst.setDouble(5, Double.parseDouble(decimalFormat.format(this.N_PORT_COST/this.N_AMOUNT)));
					DecimalFormat decimalFormatDWJZ = new DecimalFormat();
					// 获取产品估值参数【单位净值保留位数】
					String blws = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZ_001,rs.getString("C_PORT_CODE_CLS"));
					// 设置基金单位净值数字格式化类的小数保留位数
					if (StringUtil.IsNullOrEmptyT(blws) == false) {
						decimalFormatDWJZ.setMaximumFractionDigits(YssFun.toInt(blws));
						decimalFormatDWJZ.setMinimumFractionDigits(YssFun.toInt(blws));
						// 如下这样设置才能保证转换整数位超过3位数的数时不报错，例如200000000
						decimalFormatDWJZ.setGroupingSize(0);
						decimalFormatDWJZ.setGroupingUsed(false);
					} else {
						decimalFormatDWJZ = decimalFormat;
					}
					// BUG240913【景顺长城基金】电子对账估值表指标基金资产单位净值计算截位方式与产品估值参数所设置的方式不一致
					// 获取产品估值参数【单位净值截位方式】（四舍五入，截位）
					String jwfs = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZ_002,rs.getString("C_PORT_CODE_CLS"));
					if (StringUtil.IsNullOrEmptyT(blws) == false) {
						if ("JW_TRUNC".equalsIgnoreCase(jwfs)) {
							// 截位
							decimalFormatDWJZ.setRoundingMode(RoundingMode.DOWN);
						} else if ("JW_ROUND".equalsIgnoreCase(jwfs)) {
							// 四舍五入
							decimalFormatDWJZ.setRoundingMode(RoundingMode.HALF_UP);
						} else {
							decimalFormatDWJZ.setRoundingMode(RoundingMode.HALF_UP);
						}
					} else {
						decimalFormatDWJZ = decimalFormat;
						decimalFormatDWJZ.setRoundingMode(RoundingMode.HALF_UP);
					}
					pst.setDouble(indexHead + 5, Double.parseDouble(decimalFormatDWJZ.format(YssD.div(this.N_PORT_COST, this.N_AMOUNT))));	
				}
			}
			switch (dataType) {
			case DETAIL_TYPE:
				String kmCode = KMCodeTransfer(rs.getString("C_KM_CODE"));
				double n_price = rs.getDouble("N_VA_PRICE");
				n_price = covertPrice(kmCode, n_price);
				double n_amount = rs.getDouble("N_AMOUNT");
				if(!DZMS_ZSZQ.equalsIgnoreCase(this.C_DZ_MODE) && 0 == rs.getInt("N_DETAIL")){
					n_price = 0;
					n_amount = 0;
				}
				pst.setDouble(indexHead + 3, n_price);
				pst.setDouble(indexHead + 4, n_amount);
				break;
			case NAV_TYPE:
				String c_km_code = rs.getString("C_KM_CODE");
				double n_amount1 = rs.getDouble("N_AMOUNT");
				if("0002".equalsIgnoreCase(c_km_code)){
					n_amount1 = 0;
				}
				pst.setDouble(indexHead + 4, n_amount1);
				break;
			}
		}else if(LicCompanyName.COMPANY_HTSC_ZG.equalsIgnoreCase(YssContextFactory.getInstance().getLicense().getCompany())
				|| LicCompanyName.COMPANY_HTSC_WB.equalsIgnoreCase(YssContextFactory.getInstance().getLicense().getCompany())){
			if(!DZMS_GHMS.equalsIgnoreCase(this.C_DZ_MODE)){
				String c_km_code = rs.getString("C_KM_CODE");
				if(c_km_code.contains("1111.")){
					c_km_code = c_km_code.replaceAll("1111.", "1103.");
				}else if(c_km_code.contains("1111") && !c_km_code.contains(".")){
					c_km_code = c_km_code.replaceAll("1111", "1103");
				}
				pst.setString(indexHead + 1, c_km_code);
			}
		}
		return isAdd;
	
	}
	/**
	 * 获取估值表非明细科目是否生成数量参数
	 */
	private void getAmountPara() {
		//获取参数值
		if (paras == null) {
			paras = new AdmPortActParams(this.portCode, DateUtil.stringtoDate(this.geneDate, "yyyy-MM-dd"));
		}
		paras.setDbConn(conn);
		try {
			paras.initActParams();
		} catch (Exception e) {
			logger.error("获取估值表非明细科目是否生成数量参数出错：" + e.getMessage(), e);
		}
		String geneAmount = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_ELCE_GZ_AMOUNT);
		if(geneAmount != null && "1".equalsIgnoreCase(geneAmount)){
			this.isGeneAmount = true;
		}
		String zhcs = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_ZQDMZH);
		//当前数据对应的组合、日期下的证券转换规则参数值为不转换
		if(zhcs != null && "TRAN_SBLSH".equalsIgnoreCase(zhcs)){
			this.isTranSB = true;
		}
		
		String cbjsyzj = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_CBJSYZJ);
		//add by xiaomenglei STORY #44562 【鹏华基金】【紧急】社保电子对账，证券投资市值列是否加溢折价，需要增加参数控制，关联STORY #39277 ，在新增一个参数。
		if(cbjsyzj != null && "1".equalsIgnoreCase(cbjsyzj)){
			this.addYzj = true;
		}
		
		//BUG #353940 【鹏华基金】电子对账发送的成本列未包含减值准备
		String cbjsjzzb = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_CBJSJZZB);
		if(cbjsjzzb != null && "1".equalsIgnoreCase(cbjsjzzb)){
			this.addJzzb = true;
		}
		
		/**
		 * STORY #89837 人保资产-电子对账生成的估值表持有到期数据生成错误 
		 */
		String tzyzjszqz = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TZYZJSZQZ);
		if(tzyzjszqz != null && "1".equalsIgnoreCase(tzyzjszqz)){
			this.TZYZJSZQZ = true;
		}
		
		/**
		 *  STORY #48435 嘉实基金电子对账生产沪港深组合的估值表中的行情与估值表展示不一至 
		 *  参数“估值表中行情列是否按原币显示”, true 为原币；false 为行情价格*汇率
		 */
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_HQBZ) == null || "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_HQBZ))){
			isYbhq = true;
		}else{
			isYbhq = false;
		}
		
		
	    /**
		 * STORY #48443 嘉实基金电子对账-货币类组合溢折价市值列发送时需要取成本列的值发送 
		 * 【产品估值参数】增加参数“电子对账数据溢折价市值列取估值表成本值”(DZ_BB_DZDZ_YZJSZQZ)，选项“是、否”，默认为“否”。
			“否”：生成电子对账数据时，溢折价市值列取估值表中“市值”的值（现有模式）。
			“是”：生成电子对账数据时，溢折价市值列取估值表中“成本”的值。
		 */
	    if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YZJSZQZ) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YZJSZQZ))){
	    	YZJSZQZ = true;
	    }
		//add zhangmingbo
//		this.DZ_BB_DZDZ_SSZBZSSL = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_SSZBZSSL);
//		this.DZ_BB_DZDZ_HJXJ=paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_HJXJ);
		
		//STORY #86786 华夏基金--可以通过参数控制发送的电子对账指标的保留位数 
//		String blxsw0065 = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_SSZBBLXSWS);
//		if (StringUtil.IsNullOrEmptyT(blxsw0065) == false) {
//			try{
//				int num = new Double(blxsw0065).intValue();
//				if(num > 0)
//				{
//					isFormat0065 = true;
//					decimalFormat0065.setMaximumFractionDigits(num);
//					decimalFormat0065.setMinimumFractionDigits(num);
//					decimalFormat0065.setGroupingSize(0);
//					decimalFormat0065.setGroupingUsed(false);
//					decimalFormat0065.setRoundingMode(RoundingMode.HALF_UP);
//				}else{
//					isFormat0065 = false;
//				}
//			}catch(NumberFormatException e){
//				logger.error("解析电子对账实收资本保留小数位数失败");
//			}
//		}
		
		//STORY #94746 【政策业务】中央数据交换平台开放式基金业务数据交换协议V2.2 
		String blxswPrice = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_CWJJHQBLWS);
		if (StringUtil.IsNullOrEmptyT(blxswPrice) == false) {
			try{
				int num = new Double(blxswPrice).intValue();
				if(num > 0)
				{
					isFormatPrice = true;
					decimalFormatPrice.setMaximumFractionDigits(num);
					decimalFormatPrice.setMinimumFractionDigits(num);
					decimalFormatPrice.setGroupingSize(0);
					decimalFormatPrice.setGroupingUsed(false);
					decimalFormatPrice.setRoundingMode(RoundingMode.HALF_UP);
				}else{
					isFormatPrice = false;
				}
			}catch(NumberFormatException e){
				logger.error("解析电子对账估值表场外基金行情列保留位数失败");
			}
		}
		
		//BUG #183027 【嘉实基金】社保组合可分配收益发送时是否发送净值占市值比例,  默认为是
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_KFPSY) == null || "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_KFPSY))){
			this.DZ_BB_DZDZ_KFPSY = true;
		}else{
			this.DZ_BB_DZDZ_KFPSY = false;
		}
		//STORY50919QD分级产品电子对账合计项指标根据级别、分级组合对账 
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_FJHJXJSFJZH) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_FJHJXJSFJZH))){
			this.DZ_BB_DZDZ_FJHJXJSFJZH = true;
		}else{
			this.DZ_BB_DZDZ_FJHJXJSFJZH = false;
		}
		//  STORY #51238 （紧急）【嘉实基金】社保估值表指标问题导致电子对账不一致
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_XJZBFS) == null || "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_XJZBFS))){
			this.DZ_BB_DZDZ_XJZBFS = true;
		}else{
			this.DZ_BB_DZDZ_XJZBFS = false;
		}
		
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TRANS1111) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TRANS1111))){
			trans1111KmCode = true;
		}else{
			trans1111KmCode = false;
		}
		
		//STORY50919QD分级产品电子对账合计项指标根据级别、分级组合对账 
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YSGJMFFS) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YSGJMFFS))){
			this.DZ_BB_DZDZ_YSGJMFFS = true;
		}else{
			this.DZ_BB_DZDZ_YSGJMFFS = false;
		}
		
		//STORY50919QD分级产品电子对账合计项指标根据级别、分级组合对账 
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YSGJGYJZCB) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YSGJGYJZCB))){
			this.DZ_BB_DZDZ_YSGJGYJZCB = true;
		}else{
			this.DZ_BB_DZDZ_YSGJGYJZCB = false;
		}
		
		//STORY #103375 【太平养老】发送电子对账估值表，发现有重复科目
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_MERGEDATA) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_MERGEDATA))){
			this.isMergeData = true;
		}else{
			this.isMergeData = false;
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
				selectBuilder.append("SELECT C_KM_CODE FROM T_D_ER_GZ WHERE C_ASS_CODE = ? AND C_SN = ? AND D_START_DATE = ? ");
				StringBuilder updateBuilder = new StringBuilder();
				updateBuilder.append("UPDATE T_D_ER_GZ SET C_KM_CODE = ? WHERE C_ASS_CODE = ? AND C_SN = ? AND D_START_DATE = ? AND C_KM_CODE = ? ");

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
				String updateSql = " UPDATE T_D_ER_GZ A SET A.C_KM_CODE = REGEXP_REPLACE(A.C_KM_CODE,'1111','1103',1,1) WHERE A.C_KM_CODE LIKE '1111%' AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
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
}
