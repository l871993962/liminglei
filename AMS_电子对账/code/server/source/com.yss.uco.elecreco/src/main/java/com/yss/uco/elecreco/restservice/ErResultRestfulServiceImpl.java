package com.yss.uco.elecreco.restservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erresult.pojo.ErResultQuery;
import com.yss.uco.elecreco.er.erresult.service.IErResultService;
import com.yss.uco.elecreco.support.restservice.IErResultRestfulService;
import com.yss.uco.elecreco.support.restservice.pojo.ResponseInfo;
import com.yss.uco.elecreco.support.restservice.pojo.RestfulErResult;
import com.yss.uco.elecreco.support.restservice.pojo.RestfulResponseWrapper;

public class ErResultRestfulServiceImpl implements IErResultRestfulService {

	@Override
	public RestfulResponseWrapper queryErResult(String portCode,
			String startDate, String fileType) {
		
		RestfulResponseWrapper restfulResponseWrapper = new RestfulResponseWrapper();
		ResponseInfo responseInfo = new ResponseInfo();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			String c_sn = "";
			String assCode = "";
			conn = DbPoolFactory.getInstance().getPool().getConnection();
			String sql = 
					"SELECT O.C_SN,O.C_ASS_CODE FROM T_D_ER_INFO O" +
							"          JOIN (SELECT C_ASS_CODE, C_PORT_CODE" + 
							"                 FROM T_P_AB_PORT" + 
							"                WHERE C_PORT_CODE = ?) B" + 
							"            ON O.C_ASS_CODE = B.C_ASS_CODE" + 
							"         WHERE O.D_DATE = to_date(?,'yyyyMMdd')" + 
							"           AND O.C_FILE_TYPE = ? AND O.C_STATE IN('ER_IDENTICAL','ER_ACCECPED')" + 
							" ORDER BY c_update_time desc";
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			pst.setString(2, startDate);
			pst.setString(3, fileType);
			rs = pst.executeQuery();
			if(rs.next()){
				c_sn = rs.getString("C_SN");
				assCode = rs.getString("C_ASS_CODE");
			}
			IErResultService erResultService = (IErResultService) YssServiceFactory.getInstance().createService(IErResultService.class);
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("dataClass", "ErResultQuery");
			paraMap.put("SHOW_TYPE", "ALL_DATA");
			paraMap.put("C_CHECK_FLAG", fileType);
			paraMap.put("C_ASS_CODE", assCode);
			paraMap.put("C_SN", c_sn);
			paraMap.put("D_DATE", startDate);
			paraMap.put("C_RPT_TYPE", "01");
			QueryRes queryRes = erResultService.queryByCondition(paraMap);
			List<RestfulErResult> pojoList = new ArrayList<RestfulErResult>();
			if (queryRes !=null && queryRes.getDataList()!= null && queryRes.getDataList().size() > 0) {
				for (BasePojo basePojo : queryRes.getDataList()) {
					pojoList.add(convertBean(basePojo,portCode,fileType));
				}
			}else {
				
			}
			responseInfo.setCode("200001");
			responseInfo.setMessage("获取数据成功");
			responseInfo.setTotal(pojoList.size());
			responseInfo.setData(pojoList);
		}catch(Exception ex){
			responseInfo.setResult(false);
			responseInfo.setMessage(ex.getMessage());
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
			restfulResponseWrapper.setReturnCode("0");
			restfulResponseWrapper.setReturnMsg("正常返回");
			restfulResponseWrapper.setBody(responseInfo);
		}
		return restfulResponseWrapper;
	}

	private RestfulErResult convertBean(BasePojo basePojo,String portCode, String fileType) {
		RestfulErResult restfulErResult = new RestfulErResult();
		ErResultQuery erResult = (ErResultQuery) basePojo;
		restfulErResult.setPORT_CODE(portCode);
		restfulErResult.setFILE_TYPE(fileType);
		restfulErResult.setKM_CODE_BF(erResult.getC_B_KM_CODE());
		restfulErResult.setKM_CODE_DF(erResult.getC_D_KM_CODE());
		restfulErResult.setKM_NAME_BF(erResult.getC_B_KM_NAME());
		restfulErResult.setKM_NAME_DF(erResult.getC_D_KM_NAME());
		restfulErResult.setB_SL(erResult.getN_B_SL().toString());
		restfulErResult.setD_SL(erResult.getN_D_SL().toString());
		restfulErResult.setSLCE(erResult.getN_B_SL().subtract(erResult.getN_D_SL()).toString());
		restfulErResult.setB_JE(erResult.getN_B_JE().toString());
		restfulErResult.setD_JE(erResult.getN_D_JE().toString());
		restfulErResult.setJECE(erResult.getN_B_JE().subtract(erResult.getN_D_JE()).toString());
		restfulErResult.setB_JET(erResult.getN_B_JET().toString());
		restfulErResult.setD_JET(erResult.getN_D_JET().toString());
		restfulErResult.setJFFSECE(erResult.getN_B_JET().subtract(erResult.getN_D_JET()).toString());
		restfulErResult.setB_JEO(erResult.getN_B_JEO().toString());
		restfulErResult.setD_JEO(erResult.getN_D_JEO().toString());
		restfulErResult.setDFFSECE(erResult.getN_B_JEO().subtract(erResult.getN_D_JEO()).toString());
		restfulErResult.setRESULT(erResult.getC_RESULT());
		return restfulErResult;
	}

	/**
	 * 进行数据校验
	 * @param requestInfo
	 * @return
	 */
//	private void verificationDate(RestfulRequestWrapper requestInfo, ResponseInfo responseInfo, String requestType){
//
//		//将解析数据，并判断数据是否符合规范
//		//进行session校验
//		
//		//判断请求中是否有有效的数据
//		if(responseInfo.isResult() && StringUtil.IsNullOrEmptyT(requestInfo.getData().toString())){
//			responseInfo.setResult(false);
//			responseInfo.setCode("400101");
//			responseInfo.setMessage("请求中不含有效的业务载荷,应传递产品基本信息的有序列表。");
//		}
//		
//		if(responseInfo.isResult()){
//			try{
//				portfolioList=JsonUtil.toList(JsonUtil.toString(requestInfo.getData()), Portfolio.class);
//			}catch(Exception ex){
//				responseInfo.setResult(false);
//				responseInfo.setCode("400101");
//				responseInfo.setMessage("请求中不含有效的业务载荷,应传递产品基本信息的有序列表。");
//			}
//		}
//		
//		if(responseInfo.isResult()){
//			if(portfolioList==null||portfolioList.size()==0){
//				responseInfo.setResult(false);
//				responseInfo.setCode("400101");
//				responseInfo.setMessage("请求中不含有效的业务载荷,应传递产品基本信息的有序列表。");
//			}
//		}
//		
//		return portfolioList;
//	}
	
}
