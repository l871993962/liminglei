package com.yss.uco.elecreco.er.erbbinfo.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;
import com.yss.framework.util.PojoUtils;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.zip.BaoWenTool;
import com.yss.uco.elecreco.er.ergzb.dao.ErGzbSqlBuilder;
import com.yss.uco.elecreco.er.erkmb.dao.ErKmbSqlBuilder;
import com.yss.uco.elecreco.er.eryeb.dao.ErYebSqlBuilder;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.dzdz.common.impl.BaoWenBuilder;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;
import com.yss.uco.elecreco.support.util.UcoDspParamCodeEnum;

public class ErBbInfoDao extends GeneralDao {

	private ErBbInfoSqlBuilder bbInfoSqlBuilder = null;
	private ErGzbSqlBuilder gzbSqlBuilder = null;
	private ErKmbSqlBuilder kmbSqlBuilder = null;
	private ErYebSqlBuilder yebSqlBuilder = null; 

	public ErBbInfoDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		bbInfoSqlBuilder = (ErBbInfoSqlBuilder) sqlBuilder;
		gzbSqlBuilder = new ErGzbSqlBuilder();
		kmbSqlBuilder = new ErKmbSqlBuilder();
		yebSqlBuilder = new ErYebSqlBuilder();
	}

	/**
	 * 修改电子对账信息
	 * 
	 * @param bbInfoList
	 * @param status
	 * @return
	 * @throws SQLException
	 */
	public String updateBbInfo(List<ErBbInfo> bbInfoList, String status) {
		String result = "";
		try {
			List<ErBbInfo> list = new ArrayList<ErBbInfo>();
			for (ErBbInfo bbInfo : bbInfoList) {
				if (bbInfo.getAuditState() == 1) {
					// 未审核的数据不能修改状态
					bbInfo.setC_STATE(status);
					list.add(bbInfo);
				}
			}
			updateById(list);
			result = MvcConstant._Success;
		} catch (Exception ex) {
			result = MvcConstant._Fault;
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList)
			throws DataAccessException {
		deleteBbInfo((List<ErBbInfo>) pojoList);
	}

	/**
	 * 删除电子对账信息
	 * 
	 * @param bbInfoList
	 * @return
	 */
	public String deleteBbInfo(List<ErBbInfo> bbInfoList) {
		String result = "";
		PreparedStatement pst = null;
		PreparedStatement gzbPst = null;
		PreparedStatement kmbPst = null;
		PreparedStatement yebPst = null;
		Connection conn = null;
		String sql = "";
		String gzbSql = "";
		String kmbSql = "";
		String yebSql = "";
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = bbInfoSqlBuilder.getDeleteSql();
			pst = conn.prepareStatement(sql);

			// 科目表
			kmbSql = kmbSqlBuilder.getDeleteSql();
			kmbPst = conn.prepareStatement(kmbSql);

			// 估值表
			gzbSql = gzbSqlBuilder.getDeleteSql();
			gzbPst = conn.prepareStatement(gzbSql);

			// 余额表
			yebSql = yebSqlBuilder.getDeleteSql();
			yebPst = conn.prepareStatement(yebSql);

			for (ErBbInfo erBbInfo : bbInfoList) {
				kmbPst.setString(1, erBbInfo.getC_SN());
				kmbPst.setString(2, erBbInfo.getC_ASS_CODE());
				kmbPst.setString(3, erBbInfo.getC_FILE_TYPE());
				kmbPst.setString(4, erBbInfo.getC_RPT_TYPE());
				
				gzbPst.setString(1, erBbInfo.getC_SN());
				gzbPst.setString(2, erBbInfo.getC_ASS_CODE());
				gzbPst.setString(3, erBbInfo.getC_FILE_TYPE());
				gzbPst.setString(4, erBbInfo.getC_RPT_TYPE());
				
				yebPst.setString(1, erBbInfo.getC_SN());
				yebPst.setString(2, erBbInfo.getC_ASS_CODE());
				yebPst.setString(3, erBbInfo.getC_FILE_TYPE());
				yebPst.setString(4, erBbInfo.getC_RPT_TYPE());
				
				pst.setString(1, erBbInfo.getC_SN());
				pst.setString(2, erBbInfo.getC_ASS_CODE());
				pst.setString(3, erBbInfo.getC_FILE_TYPE());
				pst.setString(4, erBbInfo.getC_RPT_TYPE());
				
				kmbPst.addBatch();
				gzbPst.addBatch();
				yebPst.addBatch();
				pst.addBatch();
			}
			
			kmbPst.executeBatch();
			gzbPst.executeBatch();
			yebPst.executeBatch();
			pst.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);

			result = MvcConstant._Success;

		} catch (Exception ex) {
			logger.error("删除电子对账数据出错：" + ex.getMessage(), ex);
			result = MvcConstant._Fault;
		} finally {
			this.closeStatementFinal(pst, gzbPst);
			this.closeStatementFinal(kmbPst, yebPst);
			this.releaseConnection(conn);
		}
		return result;
	}

	/**
	 * 
	 * @param status
	 *            状态
	 * @param fsn
	 *            报文序号
	 */
	public void updateBbInfo(String status, String fsn, String fileType,
			String errInfo, String cAssCode) {
		updateStatus(status,fsn, fileType,errInfo, cAssCode);
	}
	
	/**
	 * 修改联系人
	 * @param dealer
	 * @param fsn
	 */
	public void updateDealer(String dealer, String fsn) {
		String sql = " UPDATE T_D_ER_INFO  SET C_DEALER = ? WHERE C_SN = ? ";
		Connection conn = null;
		boolean bTrans = false;
		PreparedStatement pst = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			pst = conn.prepareStatement(sql);
			pst.setString(1, dealer);
			pst.setString(2, fsn);
			pst.executeUpdate();
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (SQLException e) {
			logger.error("保存失败：", e);
		} finally {
			this.closeStatementFinal(pst);
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}

	/**
	 * 
	 * @param status
	 *            状态
	 * @param fsn
	 *            报文序号
	 */
	public void updateStatus(String status, String fsn, String fileType,
			String errInfo, String cAssCode) { // 连接改为从外面传入的方式byleeyu20150914
		String sql = bbInfoSqlBuilder.getUpdateStatusSql();
		//Connection conn = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try {
			// 这里改为从外面传入及自己创建两种模式byleeyu20150914
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, errInfo);
			pst.setString(3, DateUtil.getNow("yyyyMMdd HH:mm:ss"));
			pst.setString(4, fsn);
			pst.setString(5, fileType);
			pst.setString(6, cAssCode);
			pst.setString(7, status);
			pst.executeUpdate();
			//int count = pst.executeUpdate();
			//logger.debug(count + ":" +sql); // 屏蔽byleeyu20160715
		} catch (SQLException e) {
			logger.error("保存失败：", e);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	}
	
	public List<TransPojo> getSendData(List<ErBbInfo> list) throws Exception {
		List<TransPojo> dataList = new ArrayList<TransPojo>();
		BaoWenBuilder builder = new BaoWenBuilder();
		Connection conn = this.loadNewConnection();
		Map<String,String> portDateMap = new HashMap<String,String>(); //存储已经遍历过的（组合、日期）对应的“证券代码转换规则”值
		try {
			//在发送（估值表、科目表、余额表）前根据产品估值参数“证券代码转换规则”选择的转换规则重新生成一遍披露代码，若选择的是“不转换”，则不用生成披露代码
			getGzCodeList(conn,list,portDateMap);
			builder.paraMap = portDateMap;
			//生成披露代码并返回产品估值参数“证券代码转换规则”的值
			for (ErBbInfo bbInfo : list) {
				// //根据报文序号从数据库中获得传送的数据
				String key = bbInfo.getC_SN()+":"+bbInfo.getC_FILE_TYPE()+":"+bbInfo.getC_ASS_CODE();
//				logger.debug("电子对账发送组合:" +bbInfo.getC_SN()+":"+bbInfo.getC_FILE_TYPE()+":"+bbInfo.getC_ASS_CODE());
				//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
				if("1011".equalsIgnoreCase(bbInfo.getC_FILE_TYPE())||"1031".equalsIgnoreCase(bbInfo.getC_FILE_TYPE()))
				{
					builder.initSplitParams(bbInfo.getC_TGH_CODE(), bbInfo.getC_SPLIT_CODE(),isSplitGenerate(bbInfo.getC_PORT_CODE(),conn));
				}
				
				TransPojo pojo = null;
				if("A001".equalsIgnoreCase(bbInfo.getC_FILE_TYPE())){
					try{
						pojo = builder.builderTransPojo(conn,
								bbInfo.getC_SN(), bbInfo.getC_FILE_TYPE(),
								bbInfo.getC_ASS_CODE(),bbInfo.getC_PORT_CODE(),bbInfo.getId());
					}catch(Exception e){
						logger.error("获取发送的配置信息失败：", e);
						updateBbInfo("ER_SENDED_FAIL", bbInfo.getC_SN(),
								bbInfo.getC_FILE_TYPE(), e.getMessage(),
								bbInfo.getC_ASS_CODE());
						return dataList;
					}
				}else{
					pojo = builder.builderTransPojo(conn,
							bbInfo.getC_SN(), bbInfo.getC_FILE_TYPE(),
							bbInfo.getC_ASS_CODE(),bbInfo.getC_PORT_CODE(), bbInfo);
				}
				
				if (pojo == null) {
					logger.error("获取明细数据出错：[" + key + "]"+"请设置对账参数" );
					updateBbInfo("ER_SENDED_FAIL", bbInfo.getC_SN(),
							bbInfo.getC_FILE_TYPE(), "请设置对账参数",
							bbInfo.getC_ASS_CODE()); // 改为从这里传入byleeyu20150914
				} else if(pojo.getErrInfo() != null && pojo.getErrInfo().trim().length()>0){
					logger.error("获取明细数据出错：[" + key + "]"+pojo.getErrInfo());
					updateBbInfo("ER_SENDED_FAIL", bbInfo.getC_SN(),
							bbInfo.getC_FILE_TYPE(), pojo.getErrInfo(),
							bbInfo.getC_ASS_CODE()); // 改为从这里传入byleeyu20150914
				//BUG #369816 30.7-估值表已净值锁定，重新生成的数据不可发送	
				} else if("01".equalsIgnoreCase(bbInfo.getC_RPT_TYPE()) && ("已确认".equalsIgnoreCase(bbInfo.getC_CONFIRM_EXECUTE()) || "锁定".equalsIgnoreCase(bbInfo.getC_CONFIRM_EXECUTE()) 
						|| "LOCK".equalsIgnoreCase(bbInfo.getC_CONFIRM_EXECUTE()))) {
					logger.error("净值确认状态为锁定，不发送：[" + key + "]");
					updateBbInfo("ER_SENDED_FAIL", bbInfo.getC_SN(),
							bbInfo.getC_FILE_TYPE(), "净值确认状态为锁定，不发送；如需发送，请先解锁后重新发送！",
							bbInfo.getC_ASS_CODE());
				}
//				else if(pojo.getFromUser().equalsIgnoreCase("ER_IDENTICAL")){
//					////如果状态已经变为对账不一致了，数据不发送
//					pojo.setFromUser("");
//				}
				else{
					pojo.setFromUser(" ");
					//Orlando 20160105 增加唯一标识
					pojo.setKey(key);
					pojo.setBusType("BUSI_DZ");
					dataList.add(pojo);
				}
			}
		} finally {
			this.releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * STORY32744【南方基金】【v2.5需求】南方基金：社保资产证券代码新规则
	 * @Title getGzCodeList 
	 * @Description 在发送（估值表、科目表、余额表）前根据产品估值参数“证券代码转换规则”选择的转换规则,并设置选中数据对应的组合日期的规则代码（portDateMap）
	 * @author liuyanni@ysstech.com
	 * @date 2016年11月3日下午10:20:23
	 * @param conn
	 * @param list 
	 * @param portDateMap key:【组合：日期】    value:规则代码
	 * @return 返回所有涉及到的规则类型list
	 * @return v
	 */
	private void getGzCodeList(Connection conn,List<ErBbInfo> list,Map<String,String> portDateMap){
		try{
			for(ErBbInfo bbInfo : list){
			    List<String> portCLsCodeList = this.getClsCode(bbInfo.getC_PORT_CODE());
				//获取参数值
				AdmPortActParams paras = new AdmPortActParams(bbInfo.getC_PORT_CODE(), YssFun.parseDate(bbInfo.getD_DATE()));
				paras.setDbConn(conn);
				paras.initActParams();
				if(bbInfo.getC_FILE_TYPE().equalsIgnoreCase("1001") || bbInfo.getC_FILE_TYPE().equalsIgnoreCase("1013") ||bbInfo.getC_FILE_TYPE().equalsIgnoreCase("1011") ||bbInfo.getC_FILE_TYPE().equalsIgnoreCase("1031")){
					String key = bbInfo.getC_PORT_CODE() + ":" + bbInfo.getD_DATE();
					if(portDateMap.containsKey(key)){
						continue;
					}
					//wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）产品估值参数“是否将科目末位为五位补齐为六位进行对账”，  1为是；0为否 默认为是
					if(paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_KMBW) != null && "0".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_KMBW))){
						portDateMap.put("SV_BB_DZDZ_KMBW_" + bbInfo.getC_ASS_CODE(), "0");
					}else{
						portDateMap.put("SV_BB_DZDZ_KMBW_" + bbInfo.getC_ASS_CODE(), "1");
					}
					
					String zhcs = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_ZQDMZH);
					String qdgz = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_JYQDZH);   //交易渠道转换规则   modify by guohui STORY40652【南方基金】
					//当前数据对应的组合、日期下的证券转换规则参数值为不转换
					//交易证券和交易渠道是否转换分别受参数控制，下面临时做分情况处理  modify by guohui
					if((zhcs == null && qdgz == null) || ("TRAN_BZH".equalsIgnoreCase(zhcs) && "TRAN_BZH".equalsIgnoreCase(qdgz))){
						portDateMap.put(key, "TRAN_BZH");
					}else if(zhcs != null && !"TRAN_BZH".equalsIgnoreCase(zhcs) && "TRAN_BZH".equalsIgnoreCase(qdgz)){
						portDateMap.put(key, zhcs+"ZQ");
					}else if(qdgz != null && "TRAN_BZH".equalsIgnoreCase(zhcs) && !"TRAN_BZH".equalsIgnoreCase(qdgz)){
						portDateMap.put(key, qdgz+"QD");
					}else if(zhcs != null && qdgz != null && !"TRAN_BZH".equalsIgnoreCase(zhcs) && !"TRAN_BZH".equalsIgnoreCase(qdgz)){
						portDateMap.put(key, zhcs+"ZQQD");
					}
				     // 20181015 wlx STORY61881【招商基金】光大银行电子对账需要支持8位小数
				     if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZ_001) != null){
				      portDateMap.put("DZ_BB_DZDZ_DWJZ_001_"+ bbInfo.getC_ASS_CODE(), paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZ_001,""));
				     }
				     for (String clsCode : portCLsCodeList) {
				      if (!StringUtil.IsNullOrEmptyT(clsCode)) {
				       String paraValue = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_DWJZ_001, clsCode);
				       if (!StringUtil.IsNullOrEmptyT(paraValue)) {
				        portDateMap.put("DZ_BB_DZDZ_DWJZ_001_" + bbInfo.getC_ASS_CODE() + clsCode.trim(), paraValue);
				       }
				      }
				     }
					
					//如果设置了电子对账参数，优先取电子对账设置的发送位数
//					String numStr = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_GZBFSXSWS);
//					if(!StringUtil.IsNullOrEmptyT(numStr)){
//						try{
//							int num = new Double(numStr).intValue();
//							if(num > 0)
//							{
//								portDateMap.put("DZ_BB_DZDZ_DWJZ_001_"+ bbInfo.getC_ASS_CODE(), num + "");
//							}
//						}catch(NumberFormatException e){
//							logger.error("解析电子对账估值表保留小数位数失败");
//						}
//					}
					
					//STORY #91033 【鹏华基金】境外交易证券发送电子对账时证券代码问题优化  电子对账估值表境外债券科目代码使用ISIN码匹配,1为是；0为否 默认为否
					if(paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_KMISIN) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_KMISIN))){
						portDateMap.put("SV_BB_DZDZ_KMISIN_"+ bbInfo.getC_ASS_CODE(), "1");
					}else{
						portDateMap.put("SV_BB_DZDZ_KMISIN_"+ bbInfo.getC_ASS_CODE(), "0");
					}
					
					//STORY #94514 【招商财富】无法与托管行核对协议回购明细科目 ，是：生成数据时，排除估值表中协议回购代码的数据，否：系统数据正常加载和显示（系统现有规则）
					if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_BFSHGMX) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_BFSHGMX))){
						portDateMap.put("DZ_BB_DZDZ_BFSHGMX_"+ bbInfo.getC_ASS_CODE(), "1");
					}else{
						portDateMap.put("DZ_BB_DZDZ_BFSHGMX_"+ bbInfo.getC_ASS_CODE(), "0");
					}
					
					//STORY #103067 【长江养老】增加参数控制区分深港通和沪港通股票发送电子对账
					if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_SENDSCJM) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_SENDSCJM))){
						portDateMap.put("DZ_BB_DZDZ_SENDSCJM_"+ bbInfo.getC_ASS_CODE(), "1");
					}else{
						portDateMap.put("DZ_BB_DZDZ_SENDSCJM_"+ bbInfo.getC_ASS_CODE(), "0");
					}
				}
				
				//BUG #338178 【富国基金】【0630.0723版本】产生电子对账月报报文中的begin_date和end_date逻辑问题 电子对账发送期初日期和期末日期是否为生成日期-默认值：0(1:是;0：否)
				if(paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_SENDDATE) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_SENDDATE))){
					portDateMap.put("SV_BB_DZDZ_SENDDATE_"+ bbInfo.getC_ASS_CODE(), "1");
				}else{
					portDateMap.put("SV_BB_DZDZ_SENDDATE_"+ bbInfo.getC_ASS_CODE(), "0");
				}
			}
		}catch (Exception e) {
			logger.error("获取规则代码出错:" + e.getMessage(), e);
		} 
	}
	
	/**
	 * 
	 * @param c_ass_code
	 * @param startDate
	 * @return
	 */
	public Map<String, String> getFileMap(String c_ass_code, String startDate) {
		Map<String, String> map = new HashMap<String, String>();
		Connection conn = this.loadNewConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = bbInfoSqlBuilder.getSelectSqlBySN();
			pst = conn.prepareStatement(sql);
			pst.setString(1, startDate);
			pst.setString(2, c_ass_code);
			rs = pst.executeQuery();
			String key;
			String value;
			String state;
			while (rs.next()) {
				key = rs.getString("C_FILE_TYPE");
				value = rs.getString("C_SN");
				state = rs.getString("C_STATE");
				//STORY #102662 【富国基金300.7】工行电子对账有漏发送风险 
				if("ER_SEND".equalsIgnoreCase(state)){
					continue;
				}
				//wlx 20171027 BUG #177775 南方基金--工行电子对账估值表、余额表未接收到反馈结果，状态处于发送成功，但伺服器已收到反馈 
				//根据数据的IDEN最大值往小取，大的取到就不再取，而不是用报文序号的最大值
				if(!map.containsKey(key)){
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			logger.error("查询报文序号文件类型出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}

		return map;
	}

	public void updateRegStatus(String status, String fsn, String fileType,
			String errInfo, String cAssCode) {
		String sql = bbInfoSqlBuilder.getUpdateStatusSql2();
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, errInfo);
			pst.setString(3, DateUtil.getNow("yyyyMMdd HH:mm:ss"));
			pst.setString(4, fsn);
			pst.setString(5, fileType);
			pst.setString(6, cAssCode);
			pst.setString(7, status);
			pst.executeUpdate();
			//int count = pst.executeUpdate();
			//logger.debug(count + ":" +sql); // 屏蔽byleeyu20160715
		} catch (SQLException e) {
			logger.error("保存失败：", e);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	}
	
	public void updateSendedStatus(String status, String fsn, String fileType,
			String errInfo, String cAssCode) {
		String sql = bbInfoSqlBuilder.getUpdateStatusSql3();
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, errInfo);
			pst.setString(3, DateUtil.getNow("yyyyMMdd HH:mm:ss"));
			pst.setString(4, fsn);
			pst.setString(5, fileType);
			pst.setString(6, cAssCode);
			pst.setString(7, status);
			pst.executeUpdate();
//			logger.debug(count + ":" +sql);
		} catch (SQLException e) {
			logger.error("保存失败：", e);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	}
	
	public int queryByConditionCount(HashMap<String, Object> paraMap) {
		if(paraMap.get("ARRAY_C_STATE")!= null && paraMap.get("ARRAY_C_STATE").toString().equalsIgnoreCase("ER_UNGENE")){//查询未生成分页数据时
			return queryUnGeneCount(paraMap);
		}else if(paraMap.get("ARRAY_C_STATE")!= null && paraMap.get("ARRAY_C_STATE").toString().equalsIgnoreCase("ER_BDZ")){//查询其他对账分页数据
			return queryBDZCount(paraMap);//STORY57791【鹏华基金】电子对账管理优化需求 增加其他分页数据总条数查询
		}
		//STORY67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面 由复选框控制是否显示未生成数据
		//STORY57791【鹏华基金】电子对账管理优化需求 总览分页不加载未生成数据
		else if(paraMap.get("ARRAY_C_STATE") == null && "true".equalsIgnoreCase(String.valueOf(paraMap.get("ShowUnGeneData")))){//查询总览分页数据
			paraMap.remove("ShowUnGeneData");
			return queryByAllConditionCount(paraMap);
		}
		else{//正常分页数据查询
			//STORY57791【鹏华基金】电子对账管理优化需求 正常分页数据查询数据总条数使用基类即可
			if(paraMap.containsKey("ShowUnGeneData"))
			{
				paraMap.remove("ShowUnGeneData");
			}
			return super.queryByConditionCount(paraMap);
//			List<String> paraNameList;
//
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//
//			String sql = "";
//			int recCount = 0;
//			try {
//				paraNameList = getParaName(paraMap);
//				conn = this.loadNewConnection();
//				// conn.setAutoCommit(false);
//				sql = sqlbuilder.getQueryConditionCountSql(paraNameList);
//
//				pstmt = conn.prepareStatement(sql);
//
//				if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
//					paraNameList.remove(paraNameList.size() - 1);
//				}
//
//				//// 如果SQL中有问号时才进行赋值
//				if (sql.indexOf("?") > -1) {
//					int index = 1;
//					Object paraValue = null;
//					for (String valueFieldName : paraNameList) {
//						if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
//							continue;
//						}
//						/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
//						 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
//						if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
//							continue;
//						}
//						/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/
//
//						if (valueFieldName.startsWith("ARRAY_")) {
//							pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
//									.valueOf(paraMap.get(valueFieldName)),conn));
//						} else {
//							paraValue = paraMap.get(valueFieldName);
//							if (java.util.Date.class.equalsIgnoreCase(paraValue)) {
//								java.sql.Date dateValue = new java.sql.Date(
//										((java.util.Date) paraValue).getTime());
//								pstmt.setDate(index, dateValue);
//							} else {
//								pstmt.setObject(index, paraValue);
//							}
//						}
//
//						index++;
//					}
//				}
//				
//				rs = pstmt.executeQuery();
//
//				if (rs.next()) {
//					recCount = rs.getInt(1);
//				}
//
//			} catch (Exception ex) {
//				throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
//			} finally {
//				closeResultSetFinal(rs);
//				closeStatementFinal(pstmt);
//				releaseConnection(conn);
//			}
//
//			return recCount;
		}
	}
	
	private int queryUnGeneCount(HashMap<String, Object> paraMap) {
		ResultSet rs = null;
		PreparedStatement pst = null;
		int count = 0;
		Connection conn = null;
		String fileType = null;
		String[] rptTypes = new String[]{"01","03","04","05","06"};
		String[] fileTypes = null;
		String[] dayFileTypes = null;//日报类型
		String[] noDayFileTypes = null;//非日报类型
		try {
			conn = loadNewConnection();
			Object portObj = paraMap.get("ARRAY_C_PORT_CODE");
			Object startObj = paraMap.get("D_TRADE_START");
			Object endObj = paraMap.get("D_TRADE_END");
			if (portObj == null || startObj == null
					|| portObj.toString().length() == 0) {
				return 0;
			}
			if(paraMap.containsKey("ARRAY_C_DZ_CODE"))
			{
				fileType = String.valueOf(paraMap.get("ARRAY_C_DZ_CODE"));
				fileTypes = fileType.split(",");
				dayFileTypes = getDayFileTypes(fileTypes);
				noDayFileTypes = getNotDayFileTypes(fileTypes);
				if(noDayFileTypes == null || noDayFileTypes.length == 0)//只查询日报时，非日报的不用虚拟出来
				{
					rptTypes = new String[]{"01"};
				}if(dayFileTypes == null || dayFileTypes.length == 0)//只查询非日报时，日报的不用虚拟出来
				{
					rptTypes = new String[]{"03","04","05","06"};
				}else
				{
					rptTypes = new String[]{"01","03","04","05","06"};
				}
			}
			String[] ports = portObj.toString().split(",");
			Date startDate = DateUtil.stringtoDate(startObj.toString(),
					"yyyy-MM-dd");
			Date endDate = DateUtil.stringtoDate(endObj.toString(),
					"yyyy-MM-dd");
			String sql = bbInfoSqlBuilder.getQueryUnGeneSql(rptTypes,true);
			pst = conn.prepareStatement(sql);
			int index = 1;
			for(String rptType : rptTypes)
			{
				//无对账类型时，虚拟所有数据
				if(StringUtil.IsNullOrEmptyT(fileType))
				{
					if("01".equalsIgnoreCase(rptType))
					{
						fileTypes = new String[]{"1001","1011","1013","1031"};
					}else
					{
						fileTypes = new String[]{"1701","1801","1711","1811","1901","1903"};
					}
				}else//有对账类型时，只查询对应的数据
				{
					if("01".equalsIgnoreCase(rptType))
					{
						fileTypes = dayFileTypes;
					}else
					{
						fileTypes = noDayFileTypes;
					}
				}
				pst.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(ports,conn));
				pst.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(getDatesWithRptType(startDate, endDate, rptType),conn));
				pst.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(fileTypes,conn));
			}
			rs = pst.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return count;
	}
	/**
	 * 从当前数组中筛选出日报的对账类型
	 * @param fileTypes
	 * @return
	 */
	private String[] getNotDayFileTypes(String[] fileTypes) {
		List<String> list = new ArrayList<String>();
		if(fileTypes != null && fileTypes.length > 0)
		{
			for (String fileType : fileTypes) {
				if("1701".equalsIgnoreCase(fileType)||"1801".equalsIgnoreCase(fileType)||"1711".equalsIgnoreCase(fileType)||"1811".equalsIgnoreCase(fileType)||"1901".equalsIgnoreCase(fileType)||"1903".equalsIgnoreCase(fileType))
				{
					list.add(fileType);
				}
			}
		}
		return list.toArray(new String[list.size()]);
	}
	/**
	 * 从当前数组中筛选出非日报的对账类型
	 * @param fileTypes
	 * @return
	 */
	private String[] getDayFileTypes(String[] fileTypes) {
		List<String> list = new ArrayList<String>();
		if(fileTypes != null && fileTypes.length > 0)
		{
			for (String fileType : fileTypes) {
				if("1011".equalsIgnoreCase(fileType)||"1001".equalsIgnoreCase(fileType)||"1031".equalsIgnoreCase(fileType) || "1013".equalsIgnoreCase(fileType) )
				{
					list.add(fileType);
				}
			}
		}
		return list.toArray(new String[list.size()]);
	}

	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		if(paraMap.get("ARRAY_C_STATE")!= null && paraMap.get("ARRAY_C_STATE").toString().equalsIgnoreCase("ER_UNGENE")){//查询未生成分页数据
			return queryUnGene(paraMap,page);
		}else if(paraMap.get("ARRAY_C_STATE")!= null && paraMap.get("ARRAY_C_STATE").toString().equalsIgnoreCase("ER_BDZ")){//查询其他对账分页数据
			return queryBDZ(paraMap, page, clazz);
		}
		//STORY67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面 由复选框控制是否显示未生成数据
		// STORY57791【鹏华基金】电子对账管理优化需求  总览分页数据不加载未生成数据
		else if(paraMap.get("ARRAY_C_STATE") == null && "true".equalsIgnoreCase(String.valueOf(paraMap.get("ShowUnGeneData")))){//查询总览分页数据
			paraMap.remove("ShowUnGeneData");
			return queryByAllConditionPage(paraMap, page, clazz);
		}
		else{//正常分页数据查询
			if(paraMap.containsKey("ShowUnGeneData"))
			{
				paraMap.remove("ShowUnGeneData");
			}
			return queryByConditionPage1(paraMap, page, clazz);
		}
	}
	
	private List<BasePojo> queryBDZ(HashMap<String, Object> paraMap,
			PageInation page,Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);

			sql = ((ErBbInfoSqlBuilder)sqlbuilder).getQueryBDZSql(paraNameList);
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, clazz);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	/**
	 * 查询其他对账方式数据总条数
	 * @param paraMap
	 * @return
	 */
	private int queryBDZCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = ((ErBbInfoSqlBuilder)sqlbuilder).getQueryBDZCountSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return count;
	}
	//STORY67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面
	//STORY57791【鹏华基金】电子对账管理优化需求 总览分页不加载未生成数据
	public int queryByAllConditionCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int recCount = 0;
		String fileType = null;
		String[] rptTypes = new String[]{"01","03","04","05","06"};
		String[] fileTypes = null;
		String[] dayFileTypes = null;//日报类型
		String[] noDayFileTypes = null;//非日报类型
		Object portObj = paraMap.get("ARRAY_C_PORT_CODE");
		Object startObj = paraMap.get("D_TRADE_START");
		Object endObj = paraMap.get("D_TRADE_END");
		try {
			if (portObj == null || portObj.toString().length() == 0) {
				return 0;
			}
			String[] ports = portObj.toString().split(",");
			Date startDate = DateUtil.stringtoDate(startObj.toString(),
					"yyyy-MM-dd");
			Date endDate = DateUtil.stringtoDate(endObj.toString(),
					"yyyy-MM-dd");
			if(paraMap.containsKey("ARRAY_C_DZ_CODE"))
			{
				fileType = String.valueOf(paraMap.get("ARRAY_C_DZ_CODE"));
				fileTypes = fileType.split(",");
				dayFileTypes = getDayFileTypes(fileTypes);
				noDayFileTypes = getNotDayFileTypes(fileTypes);
				if(noDayFileTypes == null || noDayFileTypes.length == 0)//只查询日报时，非日报的不用虚拟出来
				{
					rptTypes = new String[]{"01"};
				}if(dayFileTypes == null || dayFileTypes.length == 0)//只查询非日报时，日报的不用虚拟出来
				{
					rptTypes = new String[]{"03","04","05","06"};
				}else
				{
					rptTypes = new String[]{"01","03","04","05","06"};
				}
			}
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = ((ErBbInfoSqlBuilder)sqlbuilder).getAllData(paraNameList, true, rptTypes);
			

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			int index = 1;
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							java.sql.Date dateValue = new java.sql.Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
			for(String rptType : rptTypes)//未生成设置参数
			{
				//无对账类型时，虚拟所有数据
				if(StringUtil.IsNullOrEmptyT(fileType))
				{
					if("01".equalsIgnoreCase(rptType))
					{
						fileTypes = new String[]{"1001","1011","1013","1031"};
					}else
					{
						fileTypes = new String[]{"1701","1801","1711","1811","1901","1903"};
					}
				}else//有对账类型时，只查询对应的数据
				{
					if("01".equalsIgnoreCase(rptType))
					{
						fileTypes = dayFileTypes;
					}else
					{
						fileTypes = noDayFileTypes;
					}
				}
				pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(ports,conn));
				pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(getDatesWithRptType(startDate, endDate, rptType),conn));
				pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(fileTypes,conn));
			}
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				recCount = rs.getInt(1);
			}

		} catch (Exception ex) {
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return recCount;
	}
	
	
	private List<BasePojo> queryByAllConditionPage(
			HashMap<String, Object> paraMap, PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String fileType = null;
		String[] rptTypes = new String[]{"01","03","04","05","06"};
		String[] fileTypes = null;
		String[] dayFileTypes = null;//日报类型
		String[] noDayFileTypes = null;//非日报类型
		Object portObj = paraMap.get("ARRAY_C_PORT_CODE");
		Object startObj = paraMap.get("D_TRADE_START");
		Object endObj = paraMap.get("D_TRADE_END");
		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			if (portObj == null || portObj.toString().length() == 0) {
				return pojoList;
			}
			String[] ports = portObj.toString().split(",");
			Date startDate = DateUtil.stringtoDate(startObj.toString(),
					"yyyy-MM-dd");
			Date endDate = DateUtil.stringtoDate(endObj.toString(),
					"yyyy-MM-dd");
			if(paraMap.containsKey("ARRAY_C_DZ_CODE"))
			{
				fileType = String.valueOf(paraMap.get("ARRAY_C_DZ_CODE"));
				fileTypes = fileType.split(",");
				dayFileTypes = getDayFileTypes(fileTypes);
				noDayFileTypes = getNotDayFileTypes(fileTypes);
				if(noDayFileTypes == null || noDayFileTypes.length == 0)//只查询日报时，非日报的不用虚拟出来
				{
					rptTypes = new String[]{"01"};
				}if(dayFileTypes == null || dayFileTypes.length == 0)//只查询非日报时，日报的不用虚拟出来
				{
					rptTypes = new String[]{"03","04","05","06"};
				}else
				{
					rptTypes = new String[]{"01","03","04","05","06"};
				}
			}
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = ((ErBbInfoSqlBuilder)sqlbuilder).getAllData(paraNameList, false, rptTypes);
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			int index = 1;
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							java.sql.Date dateValue = new java.sql.Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
			for(String rptType : rptTypes)//未生成设置参数
			{
				//无对账类型时，虚拟所有数据
				if(StringUtil.IsNullOrEmptyT(fileType))
				{
					if("01".equalsIgnoreCase(rptType))
					{
						fileTypes = new String[]{"1001","1011","1013","1031"};
					}else
					{
						fileTypes = new String[]{"1701","1801","1711","1811","1901","1903"};
					}
				}else//有对账类型时，只查询对应的数据
				{
					if("01".equalsIgnoreCase(rptType))
					{
						fileTypes = dayFileTypes;
					}else
					{
						fileTypes = noDayFileTypes;
					}
				}
				pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(ports,conn));
				pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(getDatesWithRptType(startDate, endDate, rptType),conn));
				pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(fileTypes,conn));
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, clazz);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	/**
	 * 月报（季报/半年报/年报）取月末（季末/年末）和截止日期
	 * @param count
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String[] getQueryDateList(Date startDate, Date endDate, String rptType) {
		int dayCount = (int) DateUtil.dayDiff(startDate,endDate)+1;
		int monthCount = YssFun.monthDiff(startDate, endDate)+1;
		int yearCount = YssFun.getYear(endDate)-YssFun.getYear(startDate)+1;
		String[] sqlDate = null;
		if(rptType.equalsIgnoreCase("06")) {
			sqlDate = new String[yearCount];
		} else {
			sqlDate = new String[monthCount];
		}
		
		if(dayCount>0) {
			int j = 0;
			for(int i=0; i<dayCount; i++) {
				if(checkDateByReport(rptType, DateUtil.nextDay(startDate,i), endDate)) {
					sqlDate[j] = DateUtil.dateToString(DateUtil.nextDay(startDate,i), "yyyy-MM-dd");
					j++; 
				}
			}
		}
		return sqlDate;
	}
	
	/**
	 * 根据报表类型判断查询电子对账的日期
	 * @param rptType
	 * @param date
	 * @param endDate
	 * @return
	 */
	public Boolean checkDateByReport(String rptType, Date date, Date endDate)
    {
        String sDate = YssFun.formatDate(date, "yyyyMMdd");
        int month = YssFun.getMonth(date);
        int year = YssFun.getYear(date);
        // 判断季度
        int num = (Integer.parseInt(sDate.subSequence(3, 6).toString())-1) / 3;
        //// 日期控件最后一天所有报表类型都会执行生成
        if (date.compareTo(endDate)==0)
        {
            return true;
        }

        if ("03".equalsIgnoreCase(rptType))
        {
            //// 月报
            if (month==2)
            {
                if ((year%100 == 0 && year%400 == 0) || (year%100 != 0 && year%4 == 0))
                {
                   //// 闰月
                   if (sDate.equalsIgnoreCase(sDate.substring(0, 6) + "29"))
                   {
                       return true;
                   }
                } else {
                    if (sDate.equalsIgnoreCase(sDate.substring(0, 6) + "28"))
                    {
                        return true;
                    }
                } 
            }
            else if (month==4 || month==6 || month==9 || month==11)
            {
                //// 小月
                if (sDate.equalsIgnoreCase(sDate.substring(0, 6) + "30"))
                {
                    return true;
                }
            }
            else
            {
                //// 大月
                if (sDate.equalsIgnoreCase(sDate.substring(0, 6) + "31"))
                {
                    return true;
                }
            }
        }
        else if ("04".equalsIgnoreCase(rptType))
        {
            //// 季报
            String quarter = "";
            if(num == 0) quarter = "03";
		    else if(num == 1) quarter = "06";
		    else if(num == 2) quarter = "09";
		    else quarter = "12";

		    if(((num == 1 || num== 2) && sDate.equalsIgnoreCase(sDate.substring(0, 4) + quarter + "30")) ||
                ((num == 0 || num== 3) && sDate.equalsIgnoreCase(sDate.substring(0, 4) + quarter + "31"))) {
                return true;   				    
		    } 
        }
        else if ("05".equalsIgnoreCase(rptType))
        {
            //// 半年报
            if (sDate.equalsIgnoreCase(sDate.substring(0, 4) + "0630") || sDate.equalsIgnoreCase(sDate.substring(0, 4) + "1231"))
            {
                return true;
            }
        } else {
            //// 年报
            if (sDate.equalsIgnoreCase(sDate.substring(0, 4) + "1231"))
            {
                return true;
            }
		}

        return false;
    }

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 根据报表类型获取需要生成报表的日期
	 * @param startDate
	 * @param endDate
	 * @param rptType
	 * @return
	 */
	private String getDatesWithRptType(Date startDate,Date endDate,String rptType)
	{
		//List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		if("01".equalsIgnoreCase(rptType))//日报
		{
			while(startDate.compareTo(endDate)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextDay(startDate,1);
			}
		}else if("03".equalsIgnoreCase(rptType))//月报
		{
			while(startDate.compareTo(endDate)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextMonth(startDate, 1);
			}
		}else if("04".equalsIgnoreCase(rptType))//季报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnQuarter(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnQuarter(startDate);
			while(startDateQ.compareTo(endDateQ)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextMonth(startDate,3);
				startDateQ = DateUtil.getFirstNatureDayOnQuarter(startDate);
			}
		}else if("05".equalsIgnoreCase(rptType))//半年报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnHalfYear(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnHalfYear(startDate);
			while(startDateQ.compareTo(endDateQ)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextMonth(startDate,6);
				startDateQ = DateUtil.getFirstNatureDayOnHalfYear(startDate);
			}
		}else if("06".equalsIgnoreCase(rptType))//年报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnYear(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnYear(startDate);
			while(startDateQ.compareTo(endDateQ)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextMonth(startDate,12);
				startDateQ = DateUtil.getFirstNatureDayOnYear(startDate);
			}
		}
		//return list;
		if(sb.toString().endsWith(","))
		{
			return sb.substring(0, sb.length()-1);
		}
		return sb.toString();
	}
	

	/**
	 * 构造未生成的数据， 未生成分页，调用这个方法，其他的调用老方法, 只查询 上一个工作日未生成的数据
	 * 
	 * @throws SQLException
	 */
	public List<BasePojo> queryUnGene(HashMap<String, Object> paraMap,PageInation page){
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		PreparedStatement pst = null;
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		String fileType = null;
		String[] rptTypes = new String[]{"01","03","04","05","06"};
		String[] fileTypes = null;
		String[] dayFileTypes = null;//日报类型
		String[] noDayFileTypes = null;//非日报类型
		Connection conn = null;
		Object portObj = paraMap.get("ARRAY_C_PORT_CODE");
		Object startObj = paraMap.get("D_TRADE_START");
		Object endObj = paraMap.get("D_TRADE_END");
		try {
			conn = loadNewConnection();
			
			if (portObj == null || startObj == null
					|| portObj.toString().length() == 0) {
				return pojoList;
			}
			if(paraMap.containsKey("ARRAY_C_DZ_CODE"))
			{
				fileType = String.valueOf(paraMap.get("ARRAY_C_DZ_CODE"));
				fileTypes = fileType.split(",");
				dayFileTypes = getDayFileTypes(fileTypes);
				noDayFileTypes = getNotDayFileTypes(fileTypes);
				if(noDayFileTypes == null || noDayFileTypes.length == 0)//只查询日报时，非日报的不用虚拟出来
				{
					rptTypes = new String[]{"01"};
				}if(dayFileTypes == null || dayFileTypes.length == 0)//只查询非日报时，日报的不用虚拟出来
				{
					rptTypes = new String[]{"03","04","05","06"};
				}else
				{
					rptTypes = new String[]{"01","03","04","05","06"};
				}
			}
			String[] ports = portObj.toString().split(",");
			Date startDate = DateUtil.stringtoDate(startObj.toString(),
					"yyyy-MM-dd");
			Date endDate = DateUtil.stringtoDate(endObj.toString(),
					"yyyy-MM-dd");
			String sql = bbInfoSqlBuilder.getQueryUnGeneSql(rptTypes,false);
			pst = conn.prepareStatement(sql);
			int index = 1;
			for(String rptType : rptTypes)
			{
				//无对账类型时，虚拟所有数据
				if(StringUtil.IsNullOrEmptyT(fileType))
				{
					if("01".equalsIgnoreCase(rptType))
					{
						fileTypes = new String[]{"1001","1011","1013","1031"};
					}else
					{
						fileTypes = new String[]{"1701","1801","1711","1811","1901","1903"};
					}
				}else//有对账类型时，只查询对应的数据
				{
					if("01".equalsIgnoreCase(rptType))
					{
						fileTypes = dayFileTypes;
					}else
					{
						fileTypes = noDayFileTypes;
					}
				}
				pst.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(ports,conn));
				pst.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(getDatesWithRptType(startDate, endDate, rptType),conn));
				pst.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(fileTypes,conn));
			}
			
			rs = pst.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools, rs, ErBbInfo.class); // 提供可以重写的方法byleeyu20130420
				getConvertKey(PojoUtils.getPropertyDescriptors(t), t);
				pojoList.add(t);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return pojoList;
	}
	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public ErBbInfo getBbInfoById(String id) {
		ErBbInfo info = new ErBbInfo();
		Connection conn = this.loadNewConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = bbInfoSqlBuilder.getSelectSqlById();
			pst = conn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				info = (ErBbInfo) rsTools.ResultToPojoObject(rs, ErBbInfo.class);
			}
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return info;
	}
	
	@SuppressWarnings("unchecked")
	public <K extends BasePojo> List<K> queryByIds(String ids, Class<?> clazz) {
		List<K> dataList = new ArrayList<K>();
		K pojo = null;
		PreparedStatement ptmt = null;
		Connection conn = null;
		String sql = bbInfoSqlBuilder.getSelectSqlByIds();
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		try {
			pojo = (K) clazz.newInstance();
			
			conn = loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			ptmt = openPreparedStatement(sql, conn);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(String.valueOf(ids),conn));
			
			rs = ptmt.executeQuery();
			
			while (rs.next()) {
				pojo = (K) rsTools.ResultToBean(rs, clazz);
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	
	public List<BasePojo> queryByConditionPage1(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);

			sql = ((ErBbInfoSqlBuilder)sqlbuilder).getQuerySql(paraNameList);
			//STORY57791【鹏华基金】电子对账管理优化需求 总览分页不加载未生成数据，则走正常分页逻辑，总览分页查询ARRAY_C_STATE为null
			String c_state = "";
			if(paraMap.get("ARRAY_C_STATE") != null){
				c_state = paraMap.get("ARRAY_C_STATE").toString();
			}
			sql += ((ErBbInfoSqlBuilder)sqlbuilder).getOrderBy(c_state);
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							java.sql.Date dateValue = new java.sql.Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, clazz);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	/**
	 * 批量根据业务主键查询 发送数据
	 * String 1011,portCode,SN
	 * ////STORY31217【招商财富】电子对账管理已反馈新增一个人工可以修改处理状态的功能
	 * 把基类的方法重写了，造成日志取数据取不到
	 * @param list
	 * @return
	 */
	public List<ErBbInfo> getSelectSqlByIdx(String[] list) {
		List<ErBbInfo> dataList = new ArrayList<ErBbInfo>();
		ErBbInfo pojo = null;
		PreparedStatement ptmt = null;
		Connection conn = null;
		String sql = bbInfoSqlBuilder.getSelectSqlByIdx();
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		try {
			conn = loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			ptmt = openPreparedStatement(sql, conn);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(list,conn));
			rs = ptmt.executeQuery();
			while (rs.next()) {
				pojo = (ErBbInfo) rsTools.ResultToBean(rs, ErBbInfo.class);
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 获取fsn报文序号
	 * @return
	 * @throws Exception
	 */
	public String createFsn(Connection conn) throws Exception{
		String fsn = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sqlString = "";
			sqlString = " select SEQU_D_ER_SN.NEXTVAL AS SN from dual";
			pst = conn.prepareStatement(sqlString);
			rs = pst.executeQuery();
			if(rs.next()){
				fsn = "DZ" + DateUtil.getCurrDate("yyyyMMdd") + String.format("%05d", Integer.valueOf(rs.getString(1)));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return fsn;
	}

	/**
	 * STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息 新增其他对账中的对账一致方法
	 * STORY #67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面
	 * @param bbInfoList 
	 * @return
	 */
	public String acceptBbInfoForQTDZ(List<ErBbInfo> pojoList) {
		Connection conn = null;
		PreparedStatement pst = null;
		List<ErBbInfo> updatBbInfos = new ArrayList<ErBbInfo>();
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			StringBuffer buf = new StringBuffer();
			buf.append("insert into T_D_ER_INFO (C_IDEN,C_FILE_TYPE,C_RPT_TYPE,C_STATE,D_DATE,C_ERR_INFO,C_DV_RESULT,C_SUMMARY,C_UPDATE_BY,C_UPDATE_TIME,C_ASS_CODE,C_PORT_CODE,C_CHECK_BY,C_CHECK_TIME,C_DV_ER_WAY,N_CHECK_STATE, C_SN) values (SEQU_D_ER_INFO.nextval,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?,?,?,(SELECT C_ASS_CODE FROM T_P_AB_PORT where C_PORT_CODE=?),?,?,?,?,?,?)");
			pst = conn.prepareStatement(buf.toString());
			for (ErBbInfo erBbInfo : pojoList) {
				if(!StringUtil.IsNullOrEmptyT(erBbInfo.getId()))
				{
					updatBbInfos.add(erBbInfo);
					continue;
				}
				int index = 1;
				pst.setString(index++, erBbInfo.getC_FILE_TYPE());
				pst.setString(index++, erBbInfo.getC_RPT_TYPE());
				pst.setString(index++, erBbInfo.getC_STATE());
				pst.setString(index++, erBbInfo.getD_DATE());
				pst.setString(index++, erBbInfo.getC_ERR_INFO());
				pst.setString(index++, erBbInfo.getC_DV_RESULT());
				pst.setString(index++, erBbInfo.getC_SUMMARY());
				pst.setString(index++, erBbInfo.getModifier());
				pst.setString(index++, erBbInfo.getModifyDate());
				pst.setString(index++, erBbInfo.getC_PORT_CODE());
				//STORY73476【鹏华基金】并行组合电子对账需求
				pst.setString(index++, erBbInfo.getC_PORT_CODE());
				pst.setString(index++, erBbInfo.getModifier());
				pst.setString(index++, erBbInfo.getModifyDate());
				pst.setString(index++, "FORWARD");
				pst.setInt(index++, 1);
				pst.setString(index++, createFsn(conn));
				pst.addBatch();
			}
			if(updatBbInfos.size() > 0)
			{
				acceptBbInfo(updatBbInfos, conn);
			}
			pst.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new DataAccessException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return "";
	}
	
	/**
	 * STORY31217【招商财富】电子对账管理已反馈新增一个人工可以修改处理状态的功能
	 * @param bbInfoList 
	 * @return
	 */
	public String acceptBbInfo(List<ErBbInfo> pojoList) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			acceptBbInfo(pojoList, conn);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			this.logger.error("人工一致出错！");
			throw new DataAccessException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	
		return "";
	}
	/**
	 * STORY #67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面
	 * @param bbInfoList 
	 * @return
	 */
	public String acceptBbInfo(List<ErBbInfo> pojoList,Connection conn) {
		if(pojoList == null || pojoList.size() == 0)
		{
			return "";
		}
		PreparedStatement pst = null;
		try {
			StringBuffer buf = new StringBuffer();
			buf.append(" UPDATE T_D_ER_INFO ");
			//STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息，修改更新语句
			//buf.append(" SET C_STATE = ?,C_ERR_INFO = ?,C_UPDATE_BY = ?,C_UPDATE_TIME = ? WHERE C_IDEN = ? ");
			buf.append(" SET C_STATE = ?,C_ERR_INFO = ?,C_UPDATE_BY = ?,C_UPDATE_TIME = ?,C_DV_RESULT = ?,C_SUMMARY = ? WHERE C_IDEN = ? ");
			pst = conn.prepareStatement(buf.toString());
			for (ErBbInfo erBbInfo : pojoList) {
				pst.setString(1, erBbInfo.getC_STATE());
				pst.setString(2, erBbInfo.getC_ERR_INFO());
				pst.setString(3, erBbInfo.getModifier());
				pst.setString(4, erBbInfo.getModifyDate());
				pst.setString(5, erBbInfo.getC_DV_RESULT());
				pst.setString(6, erBbInfo.getC_SUMMARY());
				pst.setString(7, erBbInfo.getId());
				pst.addBatch();
			}
			pst.executeBatch();
		} catch (Exception ex) {
			throw new DataAccessException(ex);
		} finally {
			this.closeStatementFinal(pst);
		}
	
		return "";
	}
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 是否拆分生成科目表，估值表
	 * @param assCode 资产代码
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private boolean isSplitGenerate(String portCode,Connection conn) throws Exception {
		boolean result = false;
		AdmPortActParams paras = new AdmPortActParams(portCode, new Date());
		paras.setDbConn(conn);
		paras.initActParams();
		String status = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_GZBCFFS);
		if("1".equalsIgnoreCase(status))
		{
			result =  true;
		}
		return result;
	}
	public String getXmlFile(ErBbInfo erBbInfo) {
		String content = "";
		Connection conn = null;
		try {
			List<ErBbInfo> dataList = new ArrayList<ErBbInfo>();
			dataList.add(erBbInfo);
			BaoWenBuilder builder = new BaoWenBuilder();
			conn = this.loadNewConnection();
			Map<String,String> portDateMap = new HashMap<String,String>(); //存储已经遍历过的（组合、日期）对应的“证券代码转换规则”值
			//在发送（估值表、科目表、余额表）前根据产品估值参数“证券代码转换规则”选择的转换规则重新生成一遍披露代码，若选择的是“不转换”，则不用生成披露代码
			getGzCodeList(conn,dataList,portDateMap);
			//wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）
			builder.paraMap = portDateMap;
			//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
			if("1011".equalsIgnoreCase(erBbInfo.getC_FILE_TYPE())||"1031".equalsIgnoreCase(erBbInfo.getC_FILE_TYPE()))
			{
				builder.initSplitParams(erBbInfo.getC_TGH_CODE(), erBbInfo.getC_SPLIT_CODE(),isSplitGenerate(erBbInfo.getC_PORT_CODE(),conn));
			}
			
			TransPojo pojo = null;
			if("A001".equalsIgnoreCase(erBbInfo.getC_FILE_TYPE())){
					pojo = builder.builderTransPojo(conn,
							erBbInfo.getC_SN(), erBbInfo.getC_FILE_TYPE(),
							erBbInfo.getC_ASS_CODE(),erBbInfo.getC_PORT_CODE(),erBbInfo.getId());
			}else{
				pojo = builder.builderTransPojo(conn,
						erBbInfo.getC_SN(), erBbInfo.getC_FILE_TYPE(),
						erBbInfo.getC_ASS_CODE(),erBbInfo.getC_PORT_CODE(), erBbInfo);
			}
			
			if (pojo == null) {
				content = "<?xml version=\"1.0\" encoding = \"UTF-8\"?>\r\n<提示信息>请设置对账参数！</提示信息>";
			}else{
				//STORY #60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
				//content = new String(BaoWenTool.unZip(pojo.getSendStr(),"GBK").replace("encoding=\"GBK\"", "encoding=\"UTF-8\"").getBytes("UTF-8"),"UTF-8");
				content = BaoWenTool.unZipAppendHead(pojo, pojo.getSendStr());
			}
		}catch(Exception ex){
			logger.error("获取明细数据出错 :"+ ex.getMessage(),ex);
			content = "<?xml version=\"1.0\" encoding = \"UTF-8\"?>\r\n<提示信息>获取报文数据出错:"+ex.getMessage()+"</提示信息>";
		} finally {
			this.releaseConnection(conn);
		}
		return content;
	}
	/**
	 * 将组合，及顺序插入临时表
	 * //wlx 20180206 STORY52002嘉实基金-电子对账管理界面展示优化  查询数据根据前台勾选的组合先后顺序排序,对账类型第二排序，对账日期为第三排序
	 * @param conn
	 * @param paraMap
	 */
	private void portIntoTempTable(Connection conn, HashMap<String, Object> paraMap){
		PreparedStatement pst = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into R_D_ER_TEMP_PORT(C_PORT_CODE,N_ORDER) values(?,?)";
			if(paraMap.containsKey("ARRAY_C_PORT_CODE")){
				String portCodes = paraMap.get("ARRAY_C_PORT_CODE").toString();
				String[] aryPortCode = portCodes.split(",");
				pst = conn.prepareStatement(sql);
				for(int i = 0;i < aryPortCode.length; i++){
					pst.setString(1, aryPortCode[i]);
					pst.setInt(2, i+1);
					pst.addBatch();
				}
				pst.executeBatch();
			}
		} catch (Exception ex) {
			throw new DataAccessException(ex);
		} finally {
			this.closeStatementFinal(pst);
		}
	}

	@Override
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap,
			Class<?> clazz) {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			portIntoTempTable(conn,paraMap);
			sql = sqlbuilder.getQueryConditionSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue = null;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							java.sql.Date dateValue = new java.sql.Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else if (paraValue instanceof Timestamp) {
							Timestamp dateValue = new Timestamp(
									((java.util.Date) paraValue).getTime());
							pstmt.setTimestamp(index, dateValue);
						}else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}

			rs = pstmt.executeQuery();

			BasePojo pojo = (BasePojo) clazz.newInstance();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, clazz, props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,t);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
	
	/**
	 * 获取电子对账结果信息
	 * @param dDate
	 * @param assCodes 资产代码
	 * @return
	 */
	public Map<String, ErBbInfo> getDzResultInfo(Date dDate, String assCodes) {
		Map<String, ErBbInfo> dzResultMap = new HashMap<String, ErBbInfo>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select t.c_ass_code, t.c_file_type, t.c_state, t.c_err_info ");
			sql.append(" from (select a.c_ass_code, a.c_file_type, a.c_state, replace(a.c_err_info, '个', '') as c_err_info, ");
			sql.append(" row_number() over(partition by a.c_ass_code, a.c_file_type order by a.c_update_time desc) rn ");
			sql.append(" from T_D_ER_INFO a where a.d_date = ? and a.c_ass_code in (select * from table(?))) t where t.rn = 1 ");
			pst = conn.prepareStatement(sql.toString());
			pst.setDate(1, YssFun.toSqlDate(dDate));
			pst.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(assCodes, conn));
			rs = pst.executeQuery();
			while (rs.next()) {
				ErBbInfo erBbInfo = new ErBbInfo();
				erBbInfo.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
				erBbInfo.setC_FILE_TYPE(rs.getString("C_FILE_TYPE"));
				erBbInfo.setC_STATE(rs.getString("C_STATE"));
				erBbInfo.setC_ERR_INFO(rs.getString("C_ERR_INFO"));
				dzResultMap.put(erBbInfo.getC_ASS_CODE() + "-" + erBbInfo.getC_FILE_TYPE(), erBbInfo);
			}
		} catch (Exception e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return dzResultMap;
	}
	

	/**
	 * 根据报文序号判断是否是人工一致的数据
	 * @param csn
	 * @return
	 */
	public boolean isManualAccept(String csn) {
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = this.bbInfoSqlBuilder.getBbInfoBySnSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, csn);
			rs = pst.executeQuery();
			if (rs.next()) {
				if("手工对账一致".equalsIgnoreCase(rs.getString("C_ERR_INFO")))
				{
					return true;
				}
			}
		} catch (Exception e) {
			throw new DataAccessException("查询手工一致数据失败：" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return false;
	}
	
	/**
     * 撤销电子对账一致数据
     * @param erBbInfo
     */
	public void updateErBbInfo(ErBbInfo erBbInfo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql=" update T_D_ER_INFO set C_STATE= ?,C_ERR_INFO=?,C_DV_RESULT=?,C_UPDATE_BY=?,C_UPDATE_TIME=? where C_SN=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "ER_IDENTICAL");
			pstmt.setString(2, erBbInfo.getC_ERR_INFO());
			pstmt.setString(3, "");
			pstmt.setString(4,erBbInfo.getOperator());
			pstmt.setString(5,erBbInfo.getAuditDate());
			pstmt.setString(6,erBbInfo.getC_SN());	
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			throw new DataAccessException("撤回对账一致失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
	}
	
	/**
     * STORY #58759 嘉实基金-电子对账-电子对账管理界面净值确认按钮修改 (#2 #1 )
     * @param erBbInfo
     */
	public void updateErBbInfoJzQr(List<ErBbInfo> pojoList) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql=" update T_D_ER_INFO set C_CONFIRM_EXECUTE= ? where C_IDEN = ? ";
			pstmt = conn.prepareStatement(sql);
			for (ErBbInfo erBbInfo : pojoList) {
				pstmt.setString(1, erBbInfo.getC_CONFIRM_EXECUTE());
				pstmt.setString(2, erBbInfo.getId());
				pstmt.addBatch();
			}
			pstmt.executeBatch();	
		} catch (Exception ex) {
			throw new DataAccessException("修改净值确认状态失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
	}
	
	/**
	 * 删除手工对账一致数据
	 * @param erBbInfo
	 */
	public void deleteByCsn(ErBbInfo erBbInfo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql=" delete from T_D_ER_INFO where C_SN=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, erBbInfo.getC_SN());	
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			// TODO: handle exception
			throw new DataAccessException("撤回手工对账一致失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}
	
	/**
	  * 查询产品的分级组合
	  * @return
	  * @throws Exception
	  */
	public List<String> getClsCode(String portCode) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> portCLsCodeList = new ArrayList<String>();
		try {
			conn = this.loadNewConnection();
			String sqlString = "select A.C_PORT_CLS_CODE from t_p_Aa_Port_Cls A where A.c_port_code = ? ";
			pst = conn.prepareStatement(sqlString);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				String clsCode = rs.getString(1);
				portCLsCodeList.add(clsCode);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return portCLsCodeList;
	}
}
