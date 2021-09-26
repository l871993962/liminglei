package com.yss.ams.product.information.modules.ab.assetsTree_a.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.assetsTree_a.dao.AssetsTree_ADao;
import com.yss.ams.product.information.modules.ab.assetsTree_a.dao.AssetsTree_ASqlBuilder;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetTreeATreeView;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.service.IAssetsTree_AService;
import com.yss.datacheck.annotation.CommonDataCheck;
import com.yss.datacheck.enums.CheckFuncGroup;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.SafeData;
import com.yss.framework.api.common.pi.ISafeDataDataService;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;

/**
 * <A区资产树型结构>服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_AService extends ServiceBus<AssetsTree_AService>
		implements IAssetsTree_AService {
	private AssetsTree_ADao serviceDao = null;
	
	public AssetsTree_AService() throws Exception {
		serviceDao = new AssetsTree_ADao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new AssetsTree_ASqlBuilder());
		dao = serviceDao;
	}

	public QueryRes getTreeViewData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = serviceDao.queryTreeViewData(paraMap, AssetTreeATreeView.class);
			addTreeViewData(dataList);

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					ProductInfoActivator.class));
		
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("pd_assetsTree_A");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("资产属性结构A区：查询资产属性数据出错", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 添加系统默认生成的节点
	 * @param dataList
	 */
	private void addTreeViewData(List<BasePojo> dataList) {
		List<BasePojo> list = new ArrayList<BasePojo>();
		AssetTreeATreeView data = new AssetTreeATreeView();
		data.setC_TR_CODE("ASS");
		data.setC_TR_NAME("资产类型");
		list.add(data);
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("ZCTG");
		data.setC_TR_NAME("资产类型-托管行");
		list.add(data);
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("ZCGL");
		data.setC_TR_NAME("资产类型-管理人");
		list.add(data);
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("ZCWT");
		data.setC_TR_NAME("资产类型-委托人");
		list.add(data);
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("ZCZL");
		data.setC_TR_NAME("资产类型-种类");
		list.add(data);
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("TGZC");
		data.setC_TR_NAME("托管行-资产类型");
		list.add(data);
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("GLZC");
		data.setC_TR_NAME("管理人-资产类型");
		list.add(data);
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("NSPL");
		data.setC_TR_NAME("纳税人类型-结转频率");
		list.add(data);
		//ZCMXLX\t资产类型-明细资产类型
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("ZCMXLX");
		data.setC_TR_NAME("资产类型-明细资产类型");
		list.add(data);
		data = new AssetTreeATreeView();
		data.setC_TR_CODE("CPZT");
		data.setC_TR_NAME("产品状态");
		list.add(data);
		// 批量添加信息
		for (BasePojo basePojo : list) {
			AssetTreeATreeView pojo = (AssetTreeATreeView) basePojo;
			pojo.setN_ORDER(0);
			pojo.setAuditState(1);
			pojo.setC_DV_UN_DIS("0");
			pojo.setParentCode("[root]");
			pojo.setC_TR_CODE_P("[root]");
			pojo.setNodeCode(pojo.getC_TR_CODE());
			pojo.setC_TR_CODE_R(pojo.getC_TR_CODE());
		}
		list.addAll(dataList);
		dataList.clear();
		dataList.addAll(list);
	}
	public String getListData(HashMap<String, Object> paraMap){
		if(serviceDao.quertListDate(paraMap)){
			return "true";
		}else{
			return "false";
		}
	}

	public List<AssetTreeATreeView> getTreeViewDataList(HashMap<String, Object> paraMap) {
		List<AssetTreeATreeView> dataList = serviceDao.queryTreeViewData(paraMap, AssetTreeATreeView.class);
		return dataList;
	}
	
	/**
	 * 获取常用组合的组合代码list  add by chenwenhai 20140528 产品群组需求
	 * @param paraMap
	 * @return
	 */
	public List<String> getOftenUsePortList(HashMap<String, Object> paraMap){
		List<String> portCodeList = serviceDao.getOftenUsePortList(paraMap);
		return portCodeList;
	}
	
	/**
	 * 插入数据
	 * 
	 * @param pojoList
	 *            数据列表
	 * @return 操作结果
	 * 
	 */
	@CommonDataCheck(checkFuncGroup = CheckFuncGroup.Add)
	public String deleteAndInsert(List<BasePojo> pojoList) {
		String retInfo = "";
		try {
			logger.debug("================================= " + "\r\n"
					+ "  Start Insert Data... ");
			if (safeData != null && safeData.getN_CHECK() <= 0) {

				for (BasePojo pojo : pojoList) {
					if (pojo instanceof AuditableParamPojo) {
						AuditableParamPojo auditPojo = (AuditableParamPojo) pojo;
						auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
						auditPojo.setOperator(ContextFactory.getContext()
								.getUserCode()); // 设置已审核的用户
						auditPojo.setAuditDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					}
				}
			}
			List<String> cidenList = serviceDao.deleteAndInsert(pojoList);
			retInfo = ReturnInfoGenerator.getSaveOKStr(menuId, cidenList);

			logger.debug("  Complete " + "\r\n"
					+ "================================= ");
		} catch (Exception ex) {
			
			String errorMess = "";
			if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
				errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(ex.getMessage(), dao, pojoList.get(0));
			} else if (ex.getMessage().contains(
					"Invalid Date Data While oper UpdateById ! ")) {
				errorMess = ReturnInfoGenerator.getDateErrStr();
			} else {
				errorMess = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeSaveErr, menuId);
			}		
			
			logger.log(ex.getMessage());
			throw new ErrorMessageException(ex,errorMess);
		}

		return retInfo;
	}
	/**
	 * 将产品从常用产品中删除  add by chenwenhai 20140528 产品群组需求
	 * @param basePojoList
	 * @return
	 */
	public String deleteOftenUsePortByCode(List<BasePojo> basePojoList){
		String returnInfo = "";
		returnInfo = serviceDao.deleteOftenUsePortByCode(basePojoList);
		return returnInfo;
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-09-26
	 * Status : Add
	 * Comment: 获取轧差分组
	 */
	public List<AssetsTree_A> getNettingGroup(){
		return serviceDao.getNettingGroup();
	}
	
	/**
	 * STORY36179嘉实基金-新建一个产品树形结构时，可自动生成 “未分配”结构，将没有分配到结构下的组合默认放到“未分配”下  zhujinyang 2017-02-06
	 * @param 
	 * @return
	 */
	public String insert(List<BasePojo> pojoList){
		String s = "";
		try {
			s = super.insert(pojoList);
		} catch (Exception e) {			
			throw new ServiceException("新增失败!当前代码结构已经被其他用户使用，私有树只有创建人才可查看。", e);
		}
		AssetsTree_A  assetsTree_child = new AssetsTree_A();
		BasePojo bpojo = null;
		String c_tr_code_s = null;
		//新增的是一级节点
		AssetsTree_A  assetsTree_a = (AssetsTree_A) pojoList.get(0);		
		if("[root]".equals(assetsTree_a.getC_TR_CODE_P())){
			assetsTree_child.setC_DESC(assetsTree_a.getC_DESC());
			assetsTree_child.setC_DV_TR(assetsTree_a.getC_DV_TR());	
			assetsTree_child.setC_TR_NAME("未分配");
			assetsTree_child.setC_TR_CODE_R(assetsTree_a.getC_TR_CODE_R());
			assetsTree_child.setC_TR_CODE_P(assetsTree_a.getC_TR_CODE());
			try {
				c_tr_code_s = serviceDao.getSequenceNextNumber(serviceDao.loadNewConnection(),"SEQU_P_AB_ASS_TR");
				assetsTree_child.setC_TR_CODE(c_tr_code_s);
			} catch (Exception e) {
				throw new ServiceException("查询序列失败!", e);
			}
			assetsTree_child.setAuditDate(assetsTree_a.getAuditDate());
			
			// BUG #250339 【工银瑞信】产品树形结构当审核状态为未审核时，在节点下依旧显示，且节点没有组合代码但是在产品组合中显示代码（紧急）
			ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
			SafeData safeData = safeDataService.getByFunCode("pd_assetsTree_A");
			if (safeData != null && safeData.getN_CHECK() == 1 && assetsTree_a.getAuditState() == 0) {
				assetsTree_child.setAuditState(0);  
			} else {
				assetsTree_child.setAuditState(1);
			}
			
			assetsTree_child.setEndUseDate(assetsTree_a.getEndUseDate());
			assetsTree_child.setId(assetsTree_a.getId());
			assetsTree_child.setModifier(assetsTree_a.getModifier());
			assetsTree_child.setModifyDate(assetsTree_a.getModifyDate());
			assetsTree_child.setOperator(assetsTree_a.getOperator());
			assetsTree_child.setStartUseDate(assetsTree_a.getStartUseDate());
			bpojo = assetsTree_child;
			super.insert(bpojo);	
		}
			
		return s;
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Comment: 更新【产品树形结构】A区产品类型中的执行顺序
	 * @param order  执行顺序
	 * @param 更新产品树形结构集合	
	 */
	public String updateAssOrder(List<String> trCodes)
	{
		//返回结果
		String retInfo = "";
		Connection conn = serviceDao.loadNewConnection();
		boolean bTrans = false;
		try {
			conn.setAutoCommit(bTrans);
			bTrans = true;
			for(String trCode : trCodes){
				retInfo = serviceDao.updateAssOrder(conn, trCode);
			}
			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		}catch (Exception ex) {
			logger.log("更新产品类型执行顺序失败!", ex);
		}finally {
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
		}
		return retInfo;
	}
	
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-06-13
	 * Status : Add
	 * Comment: BUG206147节点code不能与组合代码相同
	 */
	@Override
	@CommonDataCheck(checkFuncGroup = CheckFuncGroup.Delete)
	public String deleteById(List<BasePojo> pojoList) {
		String retInfo = "";
		Connection conn = null;
		boolean bTrans = false;
		try {
			conn = dao.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			for (BasePojo pojo : pojoList) {
				AssetsTree_A assetsTree_a = ((AssetsTree_A)pojo);
				serviceDao.deleteAllRelaPortDataByTopParent(conn, assetsTree_a.getC_TR_CODE_R());
				serviceDao.deleteAllNodeByTopParent(conn, assetsTree_a.getC_TR_CODE_R());
			}
			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
			retInfo = ReturnInfoGenerator.getDeleteOKStr(this.menuId);
		} catch (Exception ex) {
			logger.log("产品树形结构A区:删除数据时失败!", ex);
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		}finally{
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
		}
		
		return retInfo;
	}
}
