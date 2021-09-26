package com.yss.ams.sec.information.modules.mp.secmkt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMkt;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
//import com.yss.cache.PortCache;
//import com.yss.data.mp.secmkt.pojo.SecMkt;
//import com.yss.dayf.actProvider.dbUtils.DBUtils;
//import com.yss.framework.api.common.co.Port;
//import com.yss.pub.util.HolidaysAide;
//import com.yss.uco.support.comm.pojo.EConfirm;
import com.yss.framework.db.OraDbTool;

public class SecMktDao extends GeneralDao {

	private SecMktSqlBuilder sqlBuilder = null;
	
	//为防止报错而添加的getset方法 by lihaizhi 20130620
	public SecMktSqlBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	public void setSqlBuilder(SecMktSqlBuilder sqlBuilder) {
		this.sqlBuilder = sqlBuilder;
	}


	public SecMktDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (SecMktSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 净值确认后，若证券行情映射中维护相关信息，则生成一笔证券行情数据
	 * ADD BY ZXL 20141029 招商现场
	 * 
	 * add by liyanjun 2016-3-31 STORY #28721 中信证券-【行政外包4.5系统】增加母基金反确认提示信息
	 * 现有逻辑：针对主、从基金业务特点（从基金投资主基金，主基金净值确认后、作为从基金对应持仓证券的行情），
	 *		用户在｛净值确认管理｝中进行锁定操作时，系统根据｛证券行情映射｝中设置，判别本基金/本分级基金是否与某一证券有映射关系。如有，则对应生成一笔证券行情资料。
	 * 逻辑变更：根据 证券持仓+用户权限 数据，确定需要向哪些用户推送提醒；并以即时通讯模块的飘窗形式进行提醒。
	 * 适用场景：净值反确认操作时触发
	 * @param pojoList
	 * @throws Exception
	 */
	//因为资讯组件拆分要把此类拆分出来，但是此类又要引用非拆分pojo EConfirm 故先行注释，等待后期解决方案
	/*public void doSecMpMap(List<BasePojo> pojoList) throws Exception{
		String sql = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		EConfirm currPojo = null;
		List<SecMkt> lstMp = null;
		SecMkt mp = null;
		try{
			lstMp = new ArrayList<SecMkt>();
			conn =this.loadNewConnection();
			// BUG #132752 【非常紧急】【钜盛华】证券行情映射产生的货币行情分类不对 通过组合缓存关联组合类型  by zhanghualin 2016-06-17
			// 加截缓存,通过组合代码得到组合其他信息
//			PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
			PdPortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PDPORT);
			for(BasePojo pojo : pojoList){
				currPojo = (EConfirm)pojo;
				sql = sqlBuilder.getSecMpMapSql();
				pst = conn.prepareStatement(sql);
				pst.setString(1, currPojo.getC_PORT_CODE());
				pst.setDate(2, YssFun.toSqlDate(currPojo.getD_BIZ_DATE()));
				rs = pst.executeQuery();
				List<String> listSec = QuerySGSecMpMap(currPojo.getC_PORT_CODE(),YssFun.toDate(currPojo.getD_BIZ_DATE()));
				while(rs.next()){
					// liuxiang 2016-7-9 STORY #30775 证券行情映射节假日万分收益
					// 获取节假日处理方式
					// 1.如果是节假日，节假日处理=‘节假日最后一天’：若不是节假日最后一天，不插入。节假日最后一天，插入行情，取估值表中的‘期间累计万份收益’的值。
					// 2.如果是节假日，节假日处理=‘节假日期间每一天’：节假日每天，插入行情，取估值表中的‘每万份收益’的值。
					boolean isEndHoliday = false;// 是否为节假日最后一天
					String hdayProcess = rs.getString("C_DV_HDAY_PROCESS");
					if ("HQYS_JJR_LASTDAY".equals(hdayProcess) && "MWFSY".equals(rs.getString("C_INDEX_CODE"))) {
						Port port = portCache.getCacheByKey(currPojo
								.getC_PORT_CODE());
						if (HolidaysAide.isHoliday(
								YssFun.toDate(currPojo.getD_BIZ_DATE()),
								port.getC_HDAY_CODE())) {
							if (HolidaysAide.isEndHolidayDayOfHolidayPeriod(
									port.getC_HDAY_CODE(),
									YssFun.toDate(currPojo.getD_BIZ_DATE()))) {
								isEndHoliday = true;
							} else {
								continue;
							}
						}
					}
					
					mp = new SecMkt();
					mp.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
					//BUG119223【紧急】货币基金产品净值确认万份收益写入错误
					//根据品种来判断(货币,交易货币,实时货币)  货币类行情/非货币类行情 --LIUCHI/2015-9-17
					Port portBase = portCache.getCacheByKey(rs.getString("C_PORT_CODE"));
					//添加非空判断
					if(null != portBase && portBase.getC_DAT_CLS().indexOf("CLS_HB")>-1){
						mp.setC_MKT_CLS("OU_HB");
					}else{
						mp.setC_MKT_CLS("OU");
					}
					//行情来源为场外净值  liuxiang 2015-8-27 BUG #117888 行情映射添加映射关系后产生的行情无行情来源
					mp.setC_DV_PLAT("PLAT_CWJZ");
					// 节假日最后一天插入期间累计万分收益,其他时间仍为证券行情映射中的指标项 liuxiang 2016-7-9 STORY #30775 证券行情映射节假日万分收益
					if (isEndHoliday) {
						String qjljwfsy = getLastHdayQjljwfsy(
								currPojo.getC_PORT_CODE(),
								rs.getString("C_PORT_CLS_CODE"),
								currPojo.getD_BIZ_DATE());
						mp.setN_PRICE_CLOSE(qjljwfsy);
					} else {
						mp.setN_PRICE_CLOSE(rs.getString("N_PRICE_CLOSE"));
					}
                    //by guoguangyi 2015-10-15 STORY #25937 交易所行情清算需要支持保存昨日收盘价
					mp.setN_PRICE_ZRCLOSE("0"); 
					mp.setD_MKT(YssFun.formatDate(rs.getDate("D_ASTSTAT")));
					mp.setAuditState(1);
					mp.setD_PUB(YssFun.formatDate(rs.getDate("D_ASTSTAT")));
					mp.setC_DATA_IDF("Z");
					mp.setModifier(ContextFactory.getContext().getUserCode());
					mp.setModifyDate(YssFun.formatDatetime(new Date()));
					mp.setN_PRICE_AVG("0");
					mp.setN_PRICE_BUY("0");
					mp.setN_PRICE_HIGH("0");
					mp.setN_PRICE_LOW("0");
					mp.setN_PRICE_NEW("0");
					mp.setN_PRICE_OPEN("0");
					mp.setN_PRICE_SELL("0");
					mp.setN_PRICE_SETT("0");//add by liyanjun 2016-4-29 STORY29425个股期权行情获取收盘价
					mp.setN_TD_AMOUNT("0");//add by dingxukun 20161010 STORY 34828  增加参数控制项不活跃债券公允价值估值
					mp.setN_TD_MONEY("0");//add by dingxukun 20161010 STORY 34828 增加参数控制项不活跃债券公允价值估值
					//add by zzk 20150714 查询非自动数据 BUG #115191 【紧急】批量日期锁定估值表锁定不了
					// 过滤掉非自动插入行情 byleeyu20150715
					if(!listSec.contains(rs.getString("C_SEC_CODE")))
					{
						lstMp.add(mp);
					}
					//根据 证券持仓+用户权限 数据，确定需要向哪些用户推送提醒；并以即时通讯模块的飘窗形式进行提醒。
					sendMsgtoUsers(currPojo, mp.getC_SEC_CODE());
				}
				
				//释放资源
//				DBUtils.cleanUp(rs, pst);
				DbFun.closeResultSetFinal(rs);
				DbFun.closeStatementFinal(pst);
			}
			if(!lstMp.isEmpty()&&lstMp.size()!= 0){
				deleteSecMpMap(lstMp);
				this.insert(lstMp);
			}
		}catch(Exception e){
			throw e;
		}finally{
//			DBUtils.cleanUp(rs, pst);
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
	}*/

	/**
	 * 根据证券代码，行情日期，行情类别删除证券行情表信息
	 * @param lstMp
	 * @throws Exception
	 */
	public void deleteSecMpMap(List<BasePojo> lstMp) throws Exception{
		String sql = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try{
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = sqlBuilder.getDeleteSql();
			pst = conn.prepareStatement(sql);
			for(BasePojo pojo : lstMp){
				
				SecMkt  secmkt = (SecMkt)pojo;
				pst.setString(1, secmkt.getC_SEC_CODE());
				pst.setDate(2, YssFun.toSqlDate(secmkt.getD_MKT()));
				pst.setString(3, secmkt.getC_MKT_CLS());
				pst.addBatch();
			}
			pst.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		}catch(Exception e){
			DbFun.endTransFinal(conn, true); //增加回滚byleeyu20150908
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		
	}
	
	//add by zzk 20150714 查询非自动数据 BUG #115191 【紧急】批量日期锁定估值表锁定不了 
	/**
	 * 查询已手工添加的证券行情信息
	 * @param PortCode 组合代码
	 * @param date 业务日期
	 * @return 返回手工添加的映射的证券行情信息
	 * @throws Exception
	 */
	public List<String> QuerySGSecMpMap(String PortCode,Date date) throws Exception{
		String sql = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		List<String> listSec = new ArrayList<String>();
		try{
			conn = this.loadNewConnection();
			//conn.setAutoCommit(false);// 这句不要byleeyu20150908
			sql = sqlBuilder.getQuerySGSql();
			pst = conn.prepareStatement(sql);
		    pst.setString(1, PortCode);
			pst.setDate(2, YssFun.toSqlDate(date));
			rs = pst.executeQuery();
			while(rs.next()){
				listSec.add(rs.getString("C_SEC_CODE"));
			}
			return listSec;
		}catch(Exception e){
			throw e;
		}finally{
//			DBUtils.cleanUp(rs, pst);
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		
	}

	
	/**
	 * BY Jinghehe 2015-12-07 
	 * 26112 投资标的涉业绩报酬情况下的估值价格计算需求
	 * @param lstMp
	 * @throws Exception
	 */
	public void deleteSecMp(List<SecMkt> lstMp) throws Exception{
		if(null == lstMp || lstMp.isEmpty())
		{
			return;
		}
		
		String sql = null;
		PreparedStatement pst = null;
		Connection conn = null;
		try{
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = sqlBuilder.getdeleteSecMpSql();
			pst = conn.prepareStatement(sql);
			for(SecMkt secmkt : lstMp){
				pst.setString(1, secmkt.getC_SEC_CODE());
				pst.setDate(2, YssFun.toSqlDate(secmkt.getD_MKT()));
				pst.setString(3, secmkt.getC_DV_PLAT());
				pst.addBatch();
			}
			pst.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
		}catch(Exception e){
			DbFun.endTransFinal(conn, true);  
			throw e;
		}finally{
//			DBUtils.cleanUp(pst);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		
	}
	
	/**
	 * add by liyanjun 2016-3-31 STORY #28721 中信证券-【行政外包4.5系统】增加母基金反确认提示信息
	 * 业务逻辑：针对主、从基金业务特点（从基金投资主基金，主基金净值确认后、作为从基金对应持仓证券的行情），
	 *		用户在｛净值确认管理｝中进行锁定操作时，系统根据｛证券行情映射｝中设置，判别本基金/本分级基金是否与某一证券有映射关系。如有，则对应生成一笔证券行情资料。
	 *		根据 证券持仓+用户权限 数据，确定需要向哪些用户推送提醒；并以即时通讯模块的飘窗形式进行提醒。
	 * 适用场景：净值反确认操作时触发
	 * @param pojoList 净值确认数据对象
	 */
	//因为资讯组件拆分要把此类拆分出来，但是此类又要引用非拆分pojo EConfirm 故先行注释，等待后期解决方案
	/*public void doSecMpMapForUnlock(List<BasePojo> pojoList) {
		String sql = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		EConfirm currPojo = null;
		try{
			conn =this.loadNewConnection();
			for(BasePojo pojo : pojoList){
				currPojo = (EConfirm)pojo;
				sql = "SELECT C_SEC_CODE FROM T_P_AB_PORT_SEC WHERE C_PORT_CODE = ? AND N_CHECK_STATE = 1 ";
				pst = conn.prepareStatement(sql);
				pst.setString(1, currPojo.getC_PORT_CODE());
				rs = pst.executeQuery();
				while(rs.next()){
					//根据 证券持仓+用户权限 数据，确定需要向哪些用户推送提醒；并以即时通讯模块的飘窗形式进行提醒。
					sendMsgtoUsers(currPojo, rs.getString("C_SEC_CODE"));
				}
				DbFun.closeResultSetFinal(rs);
			    DbFun.closeStatementFinal(pst);
			}
		}catch(Exception e){
//			e.printStackTrace();
			logger.log("母基金反确认提示出错！", e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
	}*/
	
	/**
	 * add by liyanjun 2016-3-31 STORY #28721 中信证券-【行政外包4.5系统】增加母基金反确认提示信息
	 * 业务逻辑：假定净值确认日为T日，在生成证券行情资料后，进行以下提醒处理：
	 * 	1、查询所有套账的T日财务估值表，以获得有映射证券持仓的套账列表（从基金列表）；
	 * 	2、查询权限（注意包括顶岗管理），获得有上述套账的｛资产估值核算｝执行权限的用户列表（提醒对象列表）。
	 * 	3、以即时通讯模块的飘窗，向上述用户进行提醒，提醒样式如下：
	 * 适用场景：净值确认与反确认操作时触发
	 * 逻辑设计：
	 * @param currPojo 净值确认数据对象
	 * @param mpSec 主基金映射代码
	 * @throws Exception
	 */
	//因为资讯组件拆分要把此类拆分出来，但是此类又要引用非拆分pojo EConfirm 故先行注释，等待后期解决方案
	/*private void sendMsgtoUsers(EConfirm currPojo, String mpSec) throws Exception {
		String sql = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		List<User> users = new ArrayList<User>();
		try{
			IUserDataService userSvc = YssServiceFactory.getInstance()
					.createService(IUserDataService.class);
			conn = this.loadNewConnection();
			sql = sqlBuilder.getQueryUserSql();
			pst = conn.prepareStatement(sql);
		    pst.setString(1, mpSec);
			pst.setDate(2, YssFun.toSqlDate(currPojo.getD_BIZ_DATE()));
			rs = pst.executeQuery();
			while(rs.next()){
				User user = (User)userSvc.getDataByCode(rs.getString("C_USER_CODE"));
				users.add(user);
			}
			
			Application app = new Application();
			app.setAppCode("YSSUCO");
			app.setAppName("核算业务系统");
			String mes = "";
			if ("LOCK".equalsIgnoreCase(currPojo.getC_EXECUTE())) {
				mes ="【" + currPojo.getC_PORT_CODE() + "】基金【" + currPojo.getD_BIZ_DATE() 
						+ "】的净值已锁定，对应映射证券【" + mpSec + "】行情已更新，请注意检查。";
			}else{
				mes ="【" + currPojo.getC_PORT_CODE() + "】基金【" + currPojo.getD_BIZ_DATE() 
						+ "】的净值已反锁定，对应映射证券【" + mpSec + "】行情可能更新，请保持关注。";
			}
			
			UserMessage msg = UserInfoProducer.createMessage(mes, app, null, "");
			// 发送消息给指定岗位，弹出泡泡
			UserInfoProducer.getInstance().sendtoUsers(msg, users);
		}catch(Exception e){
			throw e;
		}finally{
//			DBUtils.cleanUp(rs, pst);
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
	}*/
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-05-30
	 * Status : Add
	 * Comment: 查询汇率
	 * @param date 日期
	 * @param secCode 证券代码(这里单指货币转换代码)
	 * @return
	 */
	public double getRate(Date date,String secCode){
		double rate = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select n_price_close from t_d_mp_sec_mkt where c_sec_code = ? and d_mkt = ? and c_mkt_cls= 'ER'";
		try{
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, secCode);
			pstmt.setDate(2, YssFun.toSqlDate(date));
			rs = pstmt.executeQuery();
			if(rs.next()){
				rate = rs.getDouble("n_price_close");
			}
		}catch(Exception ex){
			throw new BusinessException("获取汇率行情失败!", ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
		return rate;
	}
	
//	/**
//	 * 获取节假日最后一天期间累计万分收益
//	 * 
//	 * @author liuxiang
//	 * @date 2016-7-9 STORY #30775 证券行情映射节假日万分收益
//	 * @param portCode
//	 *            组合代码
//	 * @param portClsCode
//	 *            分级组合
//	 * @param date
//	 *            业务日期
//	 * @return
//	 * @throws Exception
//	 */
//	private String getLastHdayQjljwfsy(String portCode, String portClsCode,
//			String date) throws Exception {
//		String result = null;
//		ResultSet rs = null;
//		PreparedStatement pst = null;
//		Connection conn = null;
//		try {
//			conn = this.loadNewConnection();
//			String sql = sqlBuilder.getLastHdayQjljwfsySql();
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, portCode);
//			pst.setDate(2, YssFun.toSqlDate(date));
//			pst.setString(3, portClsCode);
//			rs = pst.executeQuery();
//			if (rs.next()) {
//				result = rs.getString("C_QJLJWFSY");
//			}
//		} catch (Exception e) {
//			throw e;
//		} finally {
////			DBUtils.cleanUp(rs, pst);
//			DbFun.closeResultSetFinal(rs);
//			DbFun.closeStatementFinal(pst);
//			DbFun.releaseConnection(conn);
//		}
//
//		return result;
//	}

	/**
	 * 根据证券代码获取付息公式
	 * @param secCode 证券代码
	 * @return 证券代码对应的付息公式
	 * @throws Exception
	 */
	public String  getSYLX(String secCode) throws Exception {
		String sql = "select c_dv_PI_mod from t_p_sv_sec_base where N_CHECK_STATE = 1 and C_SEC_CODE = ?";
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		String sylx = "";
		try {
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secCode);
			rs = pst.executeQuery();
			if(rs.next())
			{
				sylx = rs.getString("C_DV_PI_MOD");
			}
		} catch (Exception e) {
			throw e;
		}finally{
//			DBUtils.cleanUp(rs, pst);
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		return sylx;
		
	}
	
	/**
	 * BUG #318348 多行情同时存在  add by zmk 2020-06-15
	 * 检查数据库中是否已有证券内码、行情日期、行情分类、行情来源相同的数据
	 * @param secCode 证券内码
	 * @param d_mkt 行情日期
	 * @param mktCls 行情分类
	 * @param dvPlat 行情来源
	 * @return
	 * @throws Exception
	 */
	public String checkDuplicate(String secCode, String d_mkt, String mktCls, String dvPlat) throws Exception {
		String sql = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		String checkSecCode = "";
		try{
			conn = this.loadNewConnection();
			sql = sqlBuilder.queryDuplicate();
			pst = conn.prepareStatement(sql);
			int index = 1;
		    pst.setString(index++, secCode);
			pst.setDate(index++, YssFun.toSqlDate(d_mkt));
			pst.setString(index++, mktCls);
			pst.setString(index++, dvPlat);
			rs = pst.executeQuery();
			if(rs.next()){
				checkSecCode = rs.getString("C_SEC_CODE");
			}
			return checkSecCode;
		}catch(Exception e){
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		
	}
	
	/**
	 * 根据库存数据表的seccode list查询 证券行情映射表 的  标的组合代码
	 * add baoql 20190827 
	 * STORY #75718 自动化优化：增加智能映射方式 
	 * @param portCodes
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public List<String> getPortCodeBySecCodeList(List<String> portCodes,Date date) throws Exception{
		String sql = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		List<String> listPortCodeMap = new ArrayList<String>();
		try{
			conn = this.loadNewConnection();
			sql = sqlBuilder.getPortCodeBySecCodeListSql();
			pst = conn.prepareStatement(sql);			
			pst.setDate(1, YssFun.toSqlDate(date));
			pst.setDate(2, YssFun.toSqlDate(date));
			pst.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(StringUtil.strlistToArray(portCodes), conn));
			rs = pst.executeQuery();
			while(rs.next()){
				listPortCodeMap.add(rs.getString("C_PORT_CODE"));//映射组合
			}
			return listPortCodeMap;
		}catch(Exception e){
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		
	}
}
