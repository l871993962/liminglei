package com.yss.ifa.szt.tool.thread;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.yss.fast.mq.common.exception.MQClientException;
import com.yss.fast.mq.support.api.IProducer;
import com.yss.fast.mq.support.constants.TopicConstants;
import com.yss.fast.mq.support.reqvo.ProducerReqInfo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.cache.ErMsgCacheManager;
import com.yss.ifa.szt.tool.cons.SecretTypeCons;
import com.yss.ifa.szt.tool.log.ExeTypeEnum;
import com.yss.ifa.szt.tool.log.MessageLogFactory;
import com.yss.ifa.szt.tool.param.AdmPortActParams;
import com.yss.ifa.szt.tool.param.ErDspParamCodeEnum;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.subscriber.cons.Constants;
import com.yss.ifa.szt.tool.subscriber.cons.TopicCons;
import com.yss.ifa.szt.tool.subscriber.pojo.ErMsgPojo;
import com.yss.ifa.szt.tool.zip.BaoWenTool;

public class ReviceThread implements Callable<Object> {
	
	/**
	 * 线程名字
	 */
	private String name = "";
	/**
	 * 线程开关
	 */
	private boolean flag = true;
	
	/**
	 * BUG #299473 KM系统推送的一笔指令发送时报文重复
	 */
	private long catchSaveTime = 600000L;
	
	/**
	 * //STORY41692估值系统，伺服器发送接收日志单独存放，不输出至log日志中
	 * STORY #65624电子对账收发报文路径支持可配置
	 */
	//private static String logFileName = new FileStorePathUtil(YssConstant.YSSPERIPHERAL).getFilePath();
	/**
	 * STORY #32891 支付平台接收MR返回报文，反写给2.5
	 * 报文写入中间表dao
	 */
	private ReviceThreadDao threadDao = null;
	private BaoWenTool baoWenTool = null;
	/**
	 * STORY34022伺服器改造需支持多应用系统
	 */
//	private static IErResultService resultService = null;
	/**
	 * 构造函数
	 * @param num
	 * @param sender
	 */
	public ReviceThread(int num, long catchSaveTime) {
		this.name = "ReviceThread-" + num;
		this.catchSaveTime = catchSaveTime;
	}
	
	public ReviceThread() {
	}

	private Logger logger = LogManager.getLogger(getClass());
	@Override
	public Object call() throws Exception {
		List <TransPojo> dataList = new ArrayList<TransPojo>();
		Thread.currentThread().setName(name);
		while (flag) {
			////获取当前线程发送的数据
			dataList = ReviceThreadPool.getThreadDataList();
			if (dataList.size() > 0) {
				Connection conn = null;
				try{
					conn = DbPoolFactory.getInstance().getPool().getConnection();
					long startTimeMillis = System.currentTimeMillis();
					for (TransPojo pojo : dataList) {
						//BUG #299473 KM系统推送的一笔指令发送时报文重复
						String primaryKey = pojo.getPrimaryKey();
						if(!StringUtil.IsNullOrEmptyT(primaryKey))
						{
							long timeMillis = System.currentTimeMillis();
							Object object = ErMsgCacheManager.newInstance().set(primaryKey, timeMillis, catchSaveTime);
							if(object != null)
							{
								logger.log("丢弃重复消息,更新缓存：" + JsonUtil.toString(pojo));
								continue;
							}
							sendSyncMsg(primaryKey, startTimeMillis, timeMillis);
						}
						
//						/*BUG #141900 南方基金-伺服器收到报文未发给应用系统,
//						将保存放在报文接入时处理*/
//						try{
//							/*STORY #32891 支付平台接收MR返回报文，反写给2.5
//							 * BUG #140662 南方基金-支付平台-2.5报文与4.5伺服器并行时无法将2.5发送的余额返回报文写入中间表
//							 * 将写入放入finally块，避免2.5发送的报文，如果返回报文4.5解析报错，则日志无法插入到中间表*/
//							//保存报文到中间表
//							if(threadDao == null){
//								threadDao = new ReviceThreadDao(DbPoolFactory.getInstance().getPool(), null);
//							}
//
//							if(!pojo.getSendStr().contains("<IN>")){
//								threadDao.saveTwoPFiveMsg(pojo);
//							}
//						}catch(Throwable e){
//							logger.log("报文保存2.5中间表失败:"+e.getMessage() ,e);
//						}
//						String data = "";
//						try {
//							try {
//								//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
//								//data = BaoWenTool.unZip(pojo.getSendStr());
//								//没有加密信息的话，通过用户信息加载
//								if(StringUtil.IsNullOrEmptyT(pojo.getSecretType()))
//								{
//									BaoWenTool.initTransPojo(pojo);
//								}
//								data = BaoWenTool.unZip(pojo,pojo.getSendStr());
//							} catch (Exception ex) {
//								//wlx 20170902 BUG171771发送杭州银行深证通，无法获取对方的返回信息
//								//有些银行交互报文是明文，解密时会报错，对该类错误系统认为是明文，不予解密
//								if("Not in GZIP format".equalsIgnoreCase(ex.getMessage())){
//									data = pojo.getSendStr();
//								}else {
//									throw ex;
//								}
//							}
							/* STORY #28382民生银行报文不解压 */
							/*if(!data.contains("<FILE_TYPE>1521")){
							 STORY #28382民生银行报文不解压 
							if(data.contains("<IN>") && data.contains("<FUND_NAME>")){
								STORY #31010 民生银行嘉实资本的公司ID和密钥是分开的
								data = AESUtil.getInstance().AESDecode(pojo.getSendStr());
							}
						  }*/
										
//							String FUND_ID = ErRuleService.findNodeName("FUND_ID", data);
							//wlx 20160830 STORY34022伺服器改造需支持多应用系统  电子对账反馈的报文的资产代码是否存在系统中
							//BUG #155204 南方基金-“产品账户信息”设置了资产代码时，系统不出来反馈报文 
//							if(resultService == null){
//								resultService = YssServiceFactory.getInstance().createService(IErResultService.class);
//							}
//							//农行的FUND_ID为null BUG #155106 【紧急】南方基金-发送农行指令，余额明细查询，系统未处理
//							if(FUND_ID != null && FUND_ID.trim().length() > 0 && resultService != null && !resultService.isExistAssetCode(FUND_ID)){//若不存在则不处理该报文
//								continue;
//							}
							// STORY #35077 【南方基金】【紧急】电子对账资产代码转换
//							if(baoWenTool == null){
//								baoWenTool = new BaoWenTool();
//							}
//							String c_ass_code = baoWenTool.getTransferC_Ass_CodeMap(conn, FUND_ID, false);
//							String code = "".equalsIgnoreCase(c_ass_code) ? FUND_ID : c_ass_code;
//							//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求） 
//							boolean isSplit = false;
//							//判断FUND_ID是不是拆分代码
//							String assCode = baoWenTool.getAssCodeBySplitCode(conn, FUND_ID);
//							if(!StringUtil.IsNullOrEmptyT(assCode))//是拆分代码
//							{
//								String portCode = baoWenTool.getPortCodeByAssCode(conn,assCode);
//								if(!StringUtil.IsNullOrEmptyT(portCode))
//								{
//									isSplit = isSplitGenerate(portCode, conn);
//								}
//							}else//不是拆分代码
//							{
//								if(!StringUtil.IsNullOrEmptyT(code))
//								{
//									String portCode = baoWenTool.getPortCodeByAssCode(conn,code);
//									if(!StringUtil.IsNullOrEmptyT(portCode))
//									{
//										isSplit = isSplitGenerate(portCode, conn);
//									}
//								}
//							}
//							if(isSplit)
//							{
//								//将拆分代码替换为资产代码
//								code = assCode;
//							}
//							data = data.replace("<FUND_ID>"+FUND_ID+"</FUND_ID>", "<FUND_ID>"+code+"</FUND_ID>");
//
//							if (pojo.getResult().equalsIgnoreCase("-1")) {
//								//STORY41692估值系统，伺服器发送接收日志单独存放，不输出至log日志中
//								List<TransPojo> sendList = new ArrayList<TransPojo>();
//								sendList.add(pojo);
//								//STORY #65624电子对账收发报文路径支持可配置
//								//MessageLogFactory.getInstance().addMessage(sendList, logFileName, ExeTypeEnum.RECEIVE, DateUtil.dateToString(new Date(), MessageLogFactory.FORMAT_DATETIME));
//								MessageLogFactory.getInstance().addMessageWithConfig(sendList, ExeTypeEnum.RECEIVE, DateUtil.dateToString(new Date(), MessageLogFactory.FORMAT_DATETIME));
//								//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
//								//添加参数
//								ErDataExecuter.newInstance().execute(data,pojo,FUND_ID,isSplit);
//							} else if (pojo.getResult().equalsIgnoreCase("0")) {
//								ErSendExecuter.newInstance().sendSucc(data);
//							} else {
//								ErSendExecuter.newInstance().sendFail(pojo.getErrInfo(), data);
//							}
//						} catch (Throwable e) {
//							logger.log("处理伺服器返回的数据"+data+"失败："+e.getMessage(), e);
//						}
						long saveBeforeTimeMillis = System.currentTimeMillis();
						saveMrMessage(pojo);
						long saveTimeMillis = System.currentTimeMillis();
						logger.log("保存中间表耗时：" + (saveTimeMillis - saveBeforeTimeMillis));
						parseMessage(conn, pojo);
					}
					
					logger.log(String.format("%s：处理完%d条数据耗时%s", this.name, dataList.size(),String.valueOf(System.currentTimeMillis()-startTimeMillis)));
				}catch(Throwable ex){
					logger.log("处理伺服器返回的数据失败：" + ex.getMessage(), ex);
				}finally{
					DbFun.releaseConnection(conn);
					dataList.clear();
					dataList = null;
				}
			} else {
				Thread.sleep(1000);
			}
		}
		return null;
	}
	
	/**
	 * 发送消息
	 * @param primaryKey
	 * @param startTimeMillis
	 * @param catchSaveTime
	 */
	private void sendSyncMsg(String primaryKey, long startTimeMillis, long catchSaveTime){
		try {
			IProducer producer = YssServiceFactory.getInstance().createService(IProducer.class);
			ProducerReqInfo producerReqInfo = new ProducerReqInfo(TopicCons.ER_INFO.toString());
			ErMsgPojo pojo = new ErMsgPojo();
			pojo.setPrimaryKey(primaryKey);
			pojo.setTimeMillis(startTimeMillis);
			pojo.setCatchSaveTime(catchSaveTime);
			String message = JsonUtil.toString(pojo);
			List<String> msgList = new ArrayList<String>();
			msgList.add(message);
			producerReqInfo.setMsgs(msgList);
			producerReqInfo.setTag(Constants.erMsgTimeMillis);
			// 发送方案调度同步消息
			producer.produceMsg(producerReqInfo);
		} catch (MQClientException e) {
			logger.error("发送同步数据操作任务调度消息失败", e);
		}
	}
	
	/**
	 * 报文返回报文至中间表
	 * @param pojo
	 */
	private void saveMrMessage(TransPojo pojo){
		try{
			/*STORY #32891 支付平台接收MR返回报文，反写给2.5
			 * BUG #140662 南方基金-支付平台-2.5报文与4.5伺服器并行时无法将2.5发送的余额返回报文写入中间表
			 * 将写入放入finally块，避免2.5发送的报文，如果返回报文4.5解析报错，则日志无法插入到中间表*/
			//保存报文到中间表
			if(threadDao == null){
				threadDao = new ReviceThreadDao(DbPoolFactory.getInstance().getPool(), null);
			}

			if(!pojo.getSendStr().contains("<IN>")){
				threadDao.saveTwoPFiveMsg(pojo);
			}
		}catch(Throwable e){
			logger.log("报文保存2.5中间表失败:"+e.getMessage() ,e);
		}
	}
	
	public String decryptStr(TransPojo pojo) {
		String data = "";
		try {
			data = this.decrypt(pojo);
		} catch (Exception e) {
			data = pojo.getSendStr();
		}
		return data;
	}
	
	/**
	 * 解密数据
	 * @param pojo
	 * @return
	 * @throws Exception
	 */
	private String decrypt(TransPojo pojo) throws Exception{
		String data = "";
		try {
			//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
			//data = BaoWenTool.unZip(pojo.getSendStr());
			//没有加密信息的话，通过用户信息加载
			if(StringUtil.IsNullOrEmptyT(pojo.getSecretType()))
			{
				BaoWenTool.initTransPojo(pojo);
			}
			data = BaoWenTool.unZip(pojo,pojo.getSendStr());
		} catch (Exception ex) {
			//BUG280615【华宝基金】账户余额查询，系统解析托管行返回报文出错。
			this.logger.log("解密失败："+JsonUtil.toString(pojo),ex);
			//BUG #305577 银华基金-估值核算-【300.7-1130】民生电子对账无法接收
			try {
				if(!StringUtil.IsNullOrEmptyT(pojo.getSecretType()) && !SecretTypeCons.AES_ECB_CS5P.equalsIgnoreCase(pojo.getSecretType())){
					//AES加密,模式：AES/ECB/PKCS5Padding
					pojo.setSecretType("");
					List<TransPojo>  transPojoList = BaoWenTool.initTransPojoList(pojo);
					if(transPojoList != null && transPojoList.size() > 0){
						for (TransPojo transPojo : transPojoList) {
							if(SecretTypeCons.AES_ECB_CS5P.equals(transPojo.getSecretType())){
								pojo.setSecretKey(transPojo.getSecretKey());
								pojo.setSecretType(transPojo.getSecretType());
								pojo.setCharSet(transPojo.getCharSet());
								break;
							}
						}
						
						if(StringUtil.IsNullOrEmptyT(pojo.getSecretType())){
							pojo.setSecretType(SecretTypeCons.AES_ECB_CS5P);
						}
					}else{
						pojo.setSecretType(SecretTypeCons.AES_ECB_CS5P);
					}
					
					data = BaoWenTool.unZip(pojo,pojo.getSendStr());
				}else if(!StringUtil.IsNullOrEmptyT(pojo.getSecretType()) && !SecretTypeCons.BASE64.equalsIgnoreCase(pojo.getSecretType())){
					//base64位
					pojo.setSecretType(SecretTypeCons.BASE64);
//					pojo.setSecretKey("");
					data = BaoWenTool.unZip(pojo,pojo.getSendStr());
				}else{
					//原报文
					data = pojo.getSendStr();
				}
			} catch (Exception exi) {
				this.logger.log("解密失败："+JsonUtil.toString(pojo),exi);
				data = pojo.getSendStr();
			}
			//wlx 20170902 BUG171771发送杭州银行深证通，无法获取对方的返回信息
			//有些银行交互报文是明文，解密时会报错，对该类错误系统认为是明文，不予解密
//			if("Not in GZIP format".equalsIgnoreCase(ex.getMessage())){
//				data = pojo.getSendStr();
//			}else {
//				throw ex;
//			}
		}
		return data;
	}
	
	private void parseMessage(Connection conn, TransPojo pojo){
		String data = "";
		long startTimeMillis = System.currentTimeMillis();
		try {
			try {
				data = decrypt(pojo);
			} catch (Exception e) {
				logger.log("处理伺服器返回的数据"+JsonUtil.toString(pojo)+"失败："+e.getMessage(), e);
				return;
			}
			long decrTime = System.currentTimeMillis();
			logger.log("解密耗时：" + (decrTime - startTimeMillis));
			String FUND_ID = ErRuleService.findNodeName("FUND_ID", data);
			//wlx 20160830 STORY34022伺服器改造需支持多应用系统  电子对账反馈的报文的资产代码是否存在系统中
			//BUG #155204 南方基金-“产品账户信息”设置了资产代码时，系统不出来反馈报文 
			//		if(resultService == null){
			//			resultService = YssServiceFactory.getInstance().createService(IErResultService.class);
			//		}
			//		//农行的FUND_ID为null BUG #155106 【紧急】南方基金-发送农行指令，余额明细查询，系统未处理
			//		if(FUND_ID != null && FUND_ID.trim().length() > 0 && resultService != null && !resultService.isExistAssetCode(FUND_ID)){//若不存在则不处理该报文
			//			continue;
			//		}
			// STORY #35077 【南方基金】【紧急】电子对账资产代码转换
			if(baoWenTool == null){
				baoWenTool = new BaoWenTool();
			}
			String c_ass_code = baoWenTool.getTransferC_Ass_CodeMap(conn, FUND_ID, false);
			String code = "".equalsIgnoreCase(c_ass_code) ? FUND_ID : c_ass_code;
			//BUG #329074 【富国基金】0228电子对账处理深证通返回报文判断FUND_ID是不是拆分代码，电子指令不需要此逻辑
			boolean isSplit = false;
			if("BUSI_DZ".equalsIgnoreCase(pojo.getBusType())){
				//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求） 
				//判断FUND_ID是不是拆分代码
				String assCode = baoWenTool.getAssCodeBySplitCode(conn, FUND_ID);
				logger.log("拆分代码:" + assCode);
				if(!StringUtil.IsNullOrEmptyT(assCode))//是拆分代码
				{
					String portCode = baoWenTool.getPortCodeByAssCode(conn,assCode);
					if(!StringUtil.IsNullOrEmptyT(portCode))
					{
						isSplit = isSplitGenerate(portCode, conn);
					}
				}
//				else//不是拆分代码
//				{
//					if(!StringUtil.IsNullOrEmptyT(code))
//					{
//						String portCode = baoWenTool.getPortCodeByAssCode(conn,code);
//						if(!StringUtil.IsNullOrEmptyT(portCode))
//						{
//							isSplit = isSplitGenerate(portCode, conn);
//						}
//					}
//				}
				if(isSplit)
				{
					//将拆分代码替换为资产代码
					code = assCode;
				}
			}
			
			data = data.replace("<FUND_ID>"+FUND_ID+"</FUND_ID>", "<FUND_ID>"+code+"</FUND_ID>");
			long queryTime = System.currentTimeMillis();
			this.logger.log("查找报文节点耗时：" + (queryTime - decrTime));
			if (pojo.getResult().equalsIgnoreCase("-1")) {
				//STORY41692估值系统，伺服器发送接收日志单独存放，不输出至log日志中
				List<TransPojo> sendList = new ArrayList<TransPojo>();
				sendList.add(pojo);
				//STORY #65624电子对账收发报文路径支持可配置
				//MessageLogFactory.getInstance().addMessage(sendList, logFileName, ExeTypeEnum.RECEIVE, DateUtil.dateToString(new Date(), MessageLogFactory.FORMAT_DATETIME));
				MessageLogFactory.getInstance().addMessageWithConfig(sendList, ExeTypeEnum.RECEIVE, DateUtil.dateToString(new Date(), MessageLogFactory.FORMAT_DATETIME));
				long timeMillis2 = System.currentTimeMillis();
				logger.log("落地耗时：" + (timeMillis2 - queryTime));
				//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
				//添加参数
				ErDataExecuter.newInstance().execute(data,pojo,FUND_ID,isSplit);
				long timeMillis3 = System.currentTimeMillis();
				logger.log("业务处理耗时：" + (timeMillis3 - timeMillis2));
			} else if (pojo.getResult().equalsIgnoreCase("0")) {
				ErSendExecuter.newInstance().setPojo(pojo);
				ErSendExecuter.newInstance().sendSucc(data);
				List<TransPojo> sendList = new ArrayList<TransPojo>();
				sendList.add(pojo);
				MessageLogFactory.getInstance().addMessageWithConfig(sendList, ExeTypeEnum.RECEIVE, DateUtil.dateToString(new Date(), MessageLogFactory.FORMAT_DATETIME));
			} else {
				ErSendExecuter.newInstance().setPojo(pojo);
				ErSendExecuter.newInstance().sendFail(pojo.getErrInfo(), data);
			}
		} catch (Throwable e) {
			logger.log("处理伺服器返回的数据"+data+"失败："+e.getMessage(), e);
		}
	}
	
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 是否拆分生成科目表，估值表
	 * @param portCode 组合代码
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private boolean isSplitGenerate(String portCode,Connection conn) throws Exception {
		AdmPortActParams paras = new AdmPortActParams(portCode, new Date());
		paras.setDbConn(conn);
		paras.initActParams();
		String status = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_GZBCFFS);
		if("1".equalsIgnoreCase(status))
		{
			return true;
		}
		return false;
	}
	
	public void stop(){
		flag = false;
		ReviceThreadPool.signalAll();
	}
}
