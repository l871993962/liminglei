package com.yss.uco.elecreco.er.mrapi.executer;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.JAXBProcessor;
import com.yss.ifa.szt.tool.thread.IErExecuterService;
import com.yss.ifa.szt.tool.thread.SplitErExecuterService;
import com.yss.uco.elecreco.er.erbbinfo.service.impl.ErBbInfoService;
import com.yss.uco.elecreco.er.erresult.service.impl.ErResultService;
import com.yss.uco.elecreco.support.dzdz.bus.result.pojo.XmlResult;
import com.yss.uco.elecreco.support.dzdz.common.pojo.FileReceipt;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlInRoot;

/**
 * 余额表/估值表/科目表统一走此类
 * @author weijj
 *
 */
public class Executer1001 extends SplitErExecuterService implements IErExecuterService {
	private Logger logger = LogManager.getLogger(Executer1001.class);
	JAXBProcessor jproc = new JAXBProcessor();

	@Override
	public void execute(String data) throws Exception {
		logger.debug("接收回执报文\n" + data);
		
		ErResultService service = new ErResultService();
		//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
		service.initSplitParams(transPojo, assCode, isSplit);
		XmlResult xml = new XmlResult();
		xml = jproc.unMarshal(xml, data);
		////判断报文标识
		String c_sn = xml.getC_SN().trim();
		
		if (c_sn.startsWith("DZ")) {
			service.insert(xml);
		} else {
			////by weijj 20151215 bug123519 
			if(xml.getResultList() != null && xml.getResultList().size() > 0){
				c_sn = xml.getResultList().get(0).getC_REF_NO().trim();
				if(c_sn.startsWith("DZ")){
					xml.setC_SN(c_sn);
					service.insert(xml);
				}else{
					service.insert2(xml);
				}
				
			}else{
				service.insert2(xml);
			}
		}

	}

	@Override
	public void sendSucc(String data) throws Exception {
		logger.debug("数据发送成功：" + data);
		XmlInRoot xml = new XmlInRoot();
		xml = jproc.unMarshal(xml, data);
		ErBbInfoService service = new ErBbInfoService();
		service.updateStatus("ER_SENDED", xml.getC_SN(), xml.getC_FILE_TYPE(),
				"", xml.getC_ASS_CODE());
	}

	@Override
	public void sendFail(String errInfo, String data) throws Exception {
		logger.debug("数据发送失败：" + data);
		XmlInRoot xml = new XmlInRoot();
		xml = jproc.unMarshal(xml, data);
		ErBbInfoService service = new ErBbInfoService();
		service.updateStatus("ER_SENDED_FAIL", xml.getC_SN(),
				xml.getC_FILE_TYPE(), errInfo, xml.getC_ASS_CODE());
	}

	@Override
	public void connectFail(String data) throws Exception {
		String fsn = "";
		String fileType = "";
		XmlInRoot root = new XmlInRoot();
		root = jproc.unMarshal(root, data);
		fsn = root.getC_SN();
		fileType = root.getC_FILE_TYPE();
		ErBbInfoService service = new ErBbInfoService();
		service.updateStatus("ER_SENDED_FAIL", fsn, fileType, "伺服器没有连通！",
				root.getC_ASS_CODE());
		logger.debug("线路不通，请检查与伺服器是否连接正常！\n" + data);
	}

	@Override
	public void retMsg(String data) throws Exception {
		// /处理回执单
		FileReceipt xml = new FileReceipt();
		xml = jproc.unMarshal(xml, data);
		String status = "";
		if (xml.getC_RET_CODE().trim().equalsIgnoreCase("0")) {
			// //接收成功
			status = "ER_SENDED_SECCUSS";
		} else {
			// //接收失败
			status = "ER_SENDED_FAIL";
		}

		ErBbInfoService service = new ErBbInfoService();
		service.updateStatus(status, xml.getC_SN(), xml.getC_FILE_TYPE(),
				xml.getC_RET_MSG(), xml.getC_ASS_CODE());
		logger.debug(data + "\n已发送到托管行");
	}
}