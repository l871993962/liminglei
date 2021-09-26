package com.yss.ams.base.information.modules.bi.account.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import com.yss.ams.base.information.restFul.vo.FundAccRetFulVo;
import com.yss.ams.base.information.support.bi.account.controller.IFundAccController;
import com.yss.ams.base.information.support.bi.account.pojo.CashAcc;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.pojo.PortRela;
import com.yss.ams.base.information.support.bi.account.service.IFundAccService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;


/**
*
* @author neil
* @date 2020-08-27 16:02:37
*/
public class FundAccControllerImpl extends AbstractBaseServiceBusController<FundAcc,IFundAccService> implements IFundAccController{

	@Override
	public String getFundAccOrgcodeByAccNo(String accNo) {
		return getService().getFundAccOrgcodeByAccNo(accNo);
	}

	@Override
	public String updateFundAccUnifyPay(String accountNo) {
		return getService().updateFundAccUnifyPay(accountNo);
	}

	@Override
	public List<FundAcc> getDataListByAccTypes(List<String> types) {
		return getService().getDataListByAccTypes(types.toArray(new String[]{}));
	}

	@Override
	public List<FundAcc> getDataListByAccTypes2(List<String> types) {
		return getService().getDataListByAccTypes2(types.toArray(new String[]{}));
	}

	@Override
	public String isAccOfAccTypeExit(String type) {
		return getService().isAccOfAccTypeExit(type);
	}

	@Override
	public List<FundAcc> getFundAccByType(FundAccRetFulVo vo ) {
		return getService().getFundAccByType(vo.getTypes().toArray(new String[]{}),vo.getAccAddr());
	}

	@Override
	public String updateOrgInfo(String cPAYCODE, String cBANKCODE, String id) {
		return getService().updateOrgInfo(cPAYCODE,cBANKCODE,id);
	}

	@Override
	public List<FundAcc> getDataListByPort(String portCode) {
		return getService().getDataListByPort(portCode);
	}

	@Override
	public HashMap<String, List<FundAcc>> getDataListByPortlist(
			List<String> portCodeList) {
		return getService().getDataListByPortlist(portCodeList.toArray(new String[]{}));
	}

	@Override
	public FundAcc getFundAccByAccNo(String accNo) {
		return getService().getFundAccByAccNo(accNo);
	}

	@Override
	public FundAcc getFundAccById(String id) {
		return getService().getFundAccById(id);
	}

	@Override
	public FundAcc getFundAccByAcc(FundAcc fundAcc) {
		return getService().getFundAccByAcc(fundAcc);
	}

	@Override
	public String getIdAfterSave(FundAcc pojo) {
		return getService().getIdAfterSave(pojo);
	}

	@Override
	public Boolean savePortFundRela(String portCodes, String fundAccID,
			String accountType) {
		return getService().savePortFundRela(portCodes,fundAccID,accountType);
	}

	@Override
	public Boolean deletePortFundRela(String relaCode, String port) {
		return getService().deletePortFundRela(relaCode,port);
	}

	@Override
	public Boolean deletePortsFundRela(String relaCode, String port) {
		return getService().deletePortsFundRela(relaCode,port);
	}

	@Override
	public String getPortsByRelaCode(String id, String portCode) {
		return getService().getPortsByRelaCode(id,portCode);
	}

	@Override
	public String getPortsByRelaCode1(String id) {
		return getService().getPortsByRelaCode(id);
	}

	@Override
	public Boolean deleteUnusePortRela() {
		return getService().deleteUnusePortRela();
	}

	@Override
	public String insert(FundAccRetFulVo pojo) {
		return getService().insert(pojo.getOrgCode(),pojo.getPojoList());
	}

	@Override
	public List<FundAcc> getDataListByAssCode(String portCode) {
		return getService().getDataListByAssCode(portCode);
	}

	@Override
	public List<CashAcc> getCADataListByPortCode(String portCode, String dcCode) {
		return getService().getCADataListByPortCode(portCode,dcCode);
	}

	@Override
	public String queryZfbyPort(String c_open_addr, String c_open_acc_no,
			String c_open_acc_name) {
		return getService().queryZfbyPort(c_open_addr,c_open_acc_no,c_open_acc_name);
	}

	@Override
	public String saveRelaInfo(FundAccRetFulVo pojo) {
		return getService().saveRelaInfo(pojo.getPojo(),pojo.getPortCode());
	}

	@Override
	public Boolean deleteByRealId(List<String> realIds) {
		return getService().deleteByRealId(realIds.toArray(new String[]{}));
	}

	@Override
	public List<PortRela> getFundAcc(List<String> recordIds) {
		return getService().getFundAcc(recordIds.toArray(new String[]{}));
	}

	@Override
	public List<PortRela> getFundAccPort(String portCode) {
		return getService().getFundAccPort(portCode);
	}

	@Override
	public String readProperty() {
		return getService().readProperty();
	}

	@Override
	public String updateProperty(String checkInfo) {
		return getService().updateProperty(checkInfo);
	}

	@Override
	public String showAreaA() {
		return getService().showAreaA();
	}

	@Override
	public void updatePortCode(String id, String fundId) {
		 getService().updatePortCode(id,fundId);
		
	}

	@Override
	public void updateFundAccType(String id, String accountType) {
		getService().updateFundAccType(id,accountType);
	}

	@Override
	public FundAcc getFundAccByInfo(HashMap<String, String> paraMap) {
		return getService().getFundAccByInfo(paraMap);
	}

	@Override
	public FundAcc getFundAccByInfo1(Map<String, Object> paraMap) {
		return getService().getFundAccByInfo(paraMap);
	}

	@Override
	public List<FundAcc> getFundAccListByCache() {
		return getService().getFundAccListByCache();
	}

	@Override
	public String getAccByRmHkType(String search) {
		return getService().getAccByRmHkType(search);
	}

	@Override
	public List<FundAcc> getFundAccNoAndAddrList() {
		return castToListT(getService().getFundAccNoAndAddrList());
	}

	@Override
	public HashMap<String, FundAcc> getUniqueAccountTypeByPorts(
			String portCodes, String accountType) {
		return getService().getUniqueAccountTypeByPorts(portCodes,accountType);
	}

	@Override
	public ArrayList<FundAcc> getAllFundAccByType(HashMap<String, String> paraMap) throws Exception {
		return getService().getAllFundAccByType(paraMap);
	}

	@Override
	public void deleteThenSaveFundRela(String id, String c_PORTFOLIOID,
			String string) {
		getService().deleteThenSaveFundRela(id,c_PORTFOLIOID,string);
		
	}

	@Override
	public HashMap<String, String> getFundAccByNoAddrName(List<FundAcc> list) {
		return getService().getFundAccByNoAddrName(list);
	}

	@Override
	public RestfulQueryResult<FundAcc> getAccInAccountTreeView(
			HashMap<String, Object> paraMap) {
		return queryResToT(getService().getAccInAccountTreeView(paraMap), FundAcc.class);
	}

	@Override
	public ArrayList<FundAcc> queryAccNoByfun(HashMap<String, String> paraMap) {
		return getService().queryAccNoByfun(paraMap);
	}

	@Override
	public String updatePaycodeByAcc(HashMap<String, String> map) {
		return getService().updatePaycodeByAcc(map);
	}

	@Override
	public String updateFileMsg(FundAccRetFulVo pojo) {
		return getService().updateFileMsg(pojo.getDelFileList(),pojo.getAddFileList());
	}

	@Override
	public String fileDown(String filePath) {
		return getService().fileDown(filePath);
	}

	@Override
	public HashMap<String, String> filesDownLoad(List<String> fileList) {
		return getService().filesDownLoad(fileList);
	}

	@Override
	public String delFile(List<String> filePathList) {
		return getService().delFile(filePathList);
	}

	@Override
	public ResourceMgr UpLoadByNewPath(FundAccRetFulVo pojo) throws Exception {
		return getService().UpLoadByNewPath(pojo.getRemoteFilePath(),pojo.getCjdFile(), pojo.getUserCode());
	}

	@Override
	public HashMap<String, String> getAssCodeByPortcode(String codes,
			String type) {
		return getService().getAssCodeByPortcode(codes,type);
	}

	@Override
	public void updateFundAccCache(FundAccRetFulVo pojo) {
		getService().updateFundAccCache(pojo.getIdList(),pojo.getType());
	}

	@Override
	public List<FundAcc> getAccByRunningAccs(String openAccNo,String openAddr, String openAccName, String dcCode) {
		return getService().getAccByRunningAccs(openAccNo, openAddr,  openAccName,  dcCode);
	}
	
	@Override
	public Boolean savePortFundRelaWithDate(HashMap<String, String> paraMap) {
		return getService().savePortFundRelaWithDate(paraMap);
	}
   
    @Override
	public String getTimeByRelaPort(String portCode, String fundAccId){
		return getService().getTimeByRelaPort(portCode, fundAccId);
	}
    
    @Override
  	public String updateTimeByRelaPort(HashMap<String, String> paraMap){
    	return getService().updateTimeByRelaPort(paraMap);
  	}
	@Override
	public String getAccListByOpenNoAndOpenAddr(String openNo, String openAddr, String iden) {
		return getService().getAccListByOpenNoAndOpenAddr(openNo, openAddr,iden);
}

	@Override
	public String insertFundAcc(FundAcc fa) {
		return getService().insertFundAcc(fa);
	}

	@Override
	public String addPortCodeRela(String id, String fundId) {
		return getService().addPortCodeRela(id, fundId);
	}

	@Override
	public FundAcc getFundAccByGF(Map<String, String> paraMap) {
		return getService().getFundAccByGF(paraMap);
	}
}

