package com.yss.ams.product.information.modules.ab.assetstree_b.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.assetsTree_a.dao.AssetsTree_ADao;
import com.yss.ams.product.information.modules.ab.assetsTree_a.dao.AssetsTree_ASqlBuilder;
import com.yss.ams.product.information.modules.ab.assetstree_b.dao.AssetsTree_BDao;
import com.yss.ams.product.information.modules.ab.assetstree_b.dao.AssetsTree_BSqlBuilder;
import com.yss.ams.product.information.modules.ab.port.service.impl.PortDataService;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.pojo.AssetsTree_B;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.service.IAssetsTree_BService;
import com.yss.framework.api.common.co.Port;
import com.yss.datacheck.annotation.CommonDataCheck;
import com.yss.datacheck.enums.CheckFuncGroup;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;

/**
 * <产品树型结构>服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_BService extends ServiceBus<AssetsTree_BService>
		implements IAssetsTree_BService {
	private AssetsTree_BDao serviceDao = null;
	//add by zhoushuhang 2018-03-08 STORY49928产品树形结构界面优化
	private AssetsTree_ADao serviceDao_A = null;
	private PortDataService portDataService = null;
	private HashMap<String, String> convertMap = new HashMap<String, String>();
	private static int totalNum = 0;
	public AssetsTree_BService() throws Exception {
		serviceDao = new AssetsTree_BDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new AssetsTree_BSqlBuilder());
		//add by zhoushuhang 2018-03-08 STORY49928产品树形结构界面优化
		serviceDao_A = new AssetsTree_ADao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new AssetsTree_ASqlBuilder());
		dao = serviceDao;
		portDataService = new PortDataService();
		convertMap.put("ASS", "资产类型");
		convertMap.put("ZCTG", "资产类型-托管行");
		convertMap.put("ZCGL", "资产类型-管理人");
		convertMap.put("ZCWT", "资产类型-委托人");
		convertMap.put("ZCZL", "资产类型-种类");
		convertMap.put("TGZC", "托管行-资产类型");
		convertMap.put("GLZC", "管理人-资产类型");
		convertMap.put("NSPL", "纳税人类型-结转频率");
		convertMap.put("ZCMXLX", "资产类型-明细资产类型");
		convertMap.put("CPZT", "产品状态");
	}
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = super.queryByCondition(paraMap, page);
		String trCode = (String) paraMap.get("C_TR_CODE_R");
		if (convertMap.containsKey(trCode)) {
			 queryRes.getShowConvertAssemble().put("IAssetsTree_ADataService", convertMap);
		}
		return queryRes;
	}
	public String queryDataTotal(HashMap<String, Object> paraMap) {
		String total = super.queryDataTotal(paraMap);
		String trCode = (String) paraMap.get("C_TR_CODE_R");
		if (convertMap.containsKey(trCode)) {
			total = String.valueOf(totalNum);
		}
		return total;
	}
	public List<BasePojo> query(HashMap<String, Object> paraMap, PageInation page, Class<?> clazz) {
		List<BasePojo> dataList = super.query(paraMap, page, clazz);
		String trCode = (String) paraMap.get("C_TR_CODE_R");
		String portCodes = (String) paraMap.get("ARRAY_C_PORT_CODE");
		if (convertMap.containsKey(trCode)) {
			totalNum = 0;
			List<BasePojo> list = portDataService.doPortFilter("true", "", "", "ASS".equals(trCode) ? "" : trCode);
			if (null != list) {
				AssetsTree_B pojo = new AssetsTree_B();
				pojo.setIsParent(1);
				pojo.setAuditState(1);
				pojo.setC_DESC(convertMap.get(trCode));
				pojo.setC_TR_CODE(trCode);
				pojo.setC_PORT_CODE(trCode);
				pojo.setC_TR_CODE_R("[root]");
				dataList.add(pojo);
				for (BasePojo basePojo : list) {
					pojo = new AssetsTree_B();
					Port port = (Port) basePojo;
					if ("PORT_TYPE".equals(port.getdATA_TYPE())) {
						if (portCodes != null && !portCodes.contains(port.getC_PORT_CODE())) {
							continue;
						}
						pojo.setC_TR_CODE_R(port.getC_PORT_CODE_P());
						pojo.setC_TR_CODE(port.getC_PORT_CODE_P());
						pojo.setIsParent(0);
						totalNum++;
					}
					else {
						pojo.setC_TR_CODE_R("[root]".equals(port.getC_PORT_CODE_P()) ? trCode : port.getC_PORT_CODE_P());
						pojo.setC_TR_CODE(port.getC_PORT_CODE());
						pojo.setC_DESC(port.getC_PORT_NAME_ST());
						pojo.setIsParent(1);
						convertMap.put(pojo.getC_TR_CODE(), pojo.getC_DESC());
					}
					pojo.setC_PORT_CODE(port.getC_PORT_CODE());
					pojo.setAuditState(1);
					dataList.add(pojo);
				}
			}
		}
		return dataList;
	}
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-08
	 * Status : Add
	 * Comment: STORY49928产品树形结构界面优化
	 * Description: 删除
	 */
	@Override
	@CommonDataCheck(checkFuncGroup = CheckFuncGroup.Delete)
	public String deleteById(List<BasePojo> pojoList) {
		String retInfo = "";
		Connection conn = dao.loadNewConnection();
		boolean bTrans = false;
		try {
			conn.setAutoCommit(bTrans);
			bTrans = true;
			
			/***
			 * T_P_AB_ASS_TR_SUB 表中 C_TR_CODE_R 存储的是顶级树节点
			 * 但是查询前台显示树形结构样式封装做了特殊处理，借用 C_TR_CODE_R做为父节点，导致删除操作传入的对象C_TR_CODE_R值不正确。
			 * 树形结构缓存更新必须是顶级节点，重新查询
			 */
			AssetsTree_B assetsTree_B = (AssetsTree_B)pojoList.get(0);
			String trCodeR = assetsTree_B.getC_TR_CODE_R();
			//获取顶级节点 BUG #252712 【国海证券】在树形结构中调整组合分类，缓存刷不过来
			if(assetsTree_B.getIsParent() == 0) {
				List<AssetsTree_B> list = dao.queryByIds(assetsTree_B.getId(), AssetsTree_B.class);
				if(list.size() > 0){
					trCodeR = list.get(0).getC_TR_CODE_R();
				}
			} else if (assetsTree_B.getIsParent() == 1){
				List<AssetsTree_B> list = serviceDao_A.queryByIds(assetsTree_B.getId(), AssetsTree_B.class);
				if(list.size() > 0){
					trCodeR = list.get(0).getC_TR_CODE_R();
				}
			}
			
			for (BasePojo pojo : pojoList) {
				AssetsTree_B tree_B = ((AssetsTree_B) pojo);
				if (tree_B.getIsParent() == 1) {
					AssetsTree_A tree_A = new AssetsTree_A();
					tree_A.setId(tree_B.getId());
					serviceDao_A.deleteById(tree_A, conn);
				} else if (null != tree_B) {
					dao.deleteById(pojo, conn);
				}

				retInfo = ReturnInfoGenerator.getDeleteOKStr(this.menuId);
				conn.commit();
				conn.setAutoCommit(bTrans);
				bTrans = false;
			}
			
			//刷新组合树形结构 BUG240153【鹏华基金】维护完产品树形结构之后A区没有同步更新
			PortCache pdPortCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
			pdPortCache.refreshAssetTreeByTrCode(trCodeR);
		} catch (Exception ex) {
			logger.log("产品树形结构:删除数据时失败!", ex);
		}finally {
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
		}

		return retInfo;
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-08
	 * Status : Add
	 * Comment: STORY49928产品树形结构界面优化
	 * Description: 审核
	 */
	@Override
	@CommonDataCheck(checkFuncGroup = CheckFuncGroup.Audit)
	public String auditById(List<BasePojo> pojoList) {
		String retInfo = "";
		Connection conn = dao.loadNewConnection();
		boolean bTrans = false;
		try {
			conn.setAutoCommit(bTrans);
			bTrans = true;
			
			/***
			 * T_P_AB_ASS_TR_SUB 表中 C_TR_CODE_R 存储的是顶级树节点
			 * 但是查询前台显示树形结构样式封装做了特殊处理，借用 C_TR_CODE_R做为父节点，导致删除操作传入的对象C_TR_CODE_R值不正确。
			 * 树形结构缓存更新必须是顶级节点，重新查询
			 */
			AssetsTree_B assetsTree_B = (AssetsTree_B)pojoList.get(0);
			String trCodeR = assetsTree_B.getC_TR_CODE_R();
			//获取顶级节点 BUG #252712 【国海证券】在树形结构中调整组合分类，缓存刷不过来
			if(assetsTree_B.getIsParent() == 0) {
				List<AssetsTree_B> list = dao.queryByIds(assetsTree_B.getId(), AssetsTree_B.class);
				if(list.size() > 0){
					trCodeR = list.get(0).getC_TR_CODE_R();
				}
			} else if (assetsTree_B.getIsParent() == 1){
				List<AssetsTree_B> list = serviceDao_A.queryByIds(assetsTree_B.getId(), AssetsTree_B.class);
				if(list.size() > 0){
					trCodeR = list.get(0).getC_TR_CODE_R();
				}
			}
			
			for (BasePojo pojo : pojoList) {
				AssetsTree_B tree_B = ((AssetsTree_B)pojo);
				if(tree_B.getIsParent() == 1){
					AssetsTree_A tree_A = new AssetsTree_A();
					tree_A.setId(tree_B.getId());
					if (tree_A instanceof AuditableParamPojo) {
						tree_A.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
						tree_A.setOperator(ContextFactory.getContext()
								.getUserCode()); // 设置已审核的用户
						tree_A.setAuditDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					}
					serviceDao_A.auditById(tree_A, conn);
				}else if (null != tree_B){
					tree_B.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					serviceDao.auditById(tree_B, conn);
				}
			}
			
			retInfo = ReturnInfoGenerator.getOperOKStr(
					MvcConstant._CodeCheckOK, MvcConstant._OPER_AUDIT, menuId);
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
			
			//刷新组合树形结构 BUG240153【鹏华基金】维护完产品树形结构之后A区没有同步更新
			PortCache pdPortCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
			pdPortCache.refreshAssetTreeByTrCode(trCodeR);
		} catch (Exception ex) {
			logger.log("产品树形结构:审核数据时失败!", ex);
		}finally {
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
		}
		
		return retInfo;
	}

	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-08
	 * Status : Add
	 * Comment: STORY49928产品树形结构界面优化
	 * Description: 反审核
	 */
	@Override
	@CommonDataCheck(checkFuncGroup = CheckFuncGroup.UnAudit)
	public String unAuditById(List<BasePojo> pojoList) {
		String retInfo = "";
		Connection conn = dao.loadNewConnection();
		boolean bTrans = false;
		try {
			conn.setAutoCommit(bTrans);
			bTrans = true;
			
			/***
			 * T_P_AB_ASS_TR_SUB 表中 C_TR_CODE_R 存储的是顶级树节点
			 * 但是查询前台显示树形结构样式封装做了特殊处理，借用 C_TR_CODE_R做为父节点，导致删除操作传入的对象C_TR_CODE_R值不正确。
			 * 树形结构缓存更新必须是顶级节点，重新查询
			 */
			AssetsTree_B assetsTree_B = (AssetsTree_B)pojoList.get(0);
			String trCodeR = assetsTree_B.getC_TR_CODE_R();
			//获取顶级节点 BUG #252712 【国海证券】在树形结构中调整组合分类，缓存刷不过来
			if(assetsTree_B.getIsParent() == 0) {
				List<AssetsTree_B> list = dao.queryByIds(assetsTree_B.getId(), AssetsTree_B.class);
				if(list.size() > 0){
					trCodeR = list.get(0).getC_TR_CODE_R();
				}
			} else if (assetsTree_B.getIsParent() == 1){
				List<AssetsTree_B> list = serviceDao_A.queryByIds(assetsTree_B.getId(), AssetsTree_B.class);
				if(list.size() > 0){
					trCodeR = list.get(0).getC_TR_CODE_R();
				}
			}
			
			for (BasePojo pojo : pojoList) {
				AssetsTree_B tree_B = ((AssetsTree_B)pojo);
				if(tree_B.getIsParent() == 1){
					AssetsTree_A tree_A = new AssetsTree_A();
					tree_A.setId(tree_B.getId());
					if (tree_A instanceof AuditableParamPojo) {
						tree_A.setAuditState(YssConstant.STATE_UNAUDIT); // 设置未审核
						tree_A.setOperator(ContextFactory.getContext()
								.getUserCode()); // 设置未审核的用户
						tree_A.setAuditDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					}
					serviceDao_A.unAuditById(tree_A, conn);
				}else if (null != tree_B){
					dao.unAuditById(pojo, conn);
				}
			}
			
			retInfo = ReturnInfoGenerator.getOperOKStr(
					MvcConstant._CodeUnCheckOK, MvcConstant._OPER_UNAUDIT, menuId);
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
			
			//刷新组合树形结构 BUG240153【鹏华基金】维护完产品树形结构之后A区没有同步更新
			PortCache pdPortCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
			pdPortCache.refreshAssetTreeByTrCode(trCodeR);
		} catch (Exception ex) {
			logger.log("产品树形结构:反审核数据时失败!", ex);
		}finally {
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
		}
		
		return retInfo;
	}
	
	/**
	 * add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
	 * 判断：待拖入行组合信息与所属选择行组合信息是否为同一资产类型
	 */
	@Override
	public String isSameAssetType(String portCode, String dragPortCode) {
		return serviceDao.isSameAssetType(portCode, dragPortCode);
	}

	/**
	 * add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
	 * 删除或者更新组合信息对应树形结构节点
	 */
	@Override
	public int updateOrdelete(String id, String trCode, String isParent, String type) {
		int count = serviceDao.updateOrdelete(id, trCode, isParent, type);
		
		//刷新组合树形结构 BUG240153【鹏华基金】维护完产品树形结构之后A区没有同步更新
		CacheManager.getInstance().getCache(CacheGroup.PORT).reloadData();
		
		return count;
	}
	
	/**
	 * add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
	 * 删除或者更新组合信息对应树形结构节点
	 */
	@Override
	public int updateOrdelete(String id, String trCode, String isParent, String type, String trCodeR) {
		int count = serviceDao.updateOrdelete(id, trCode, isParent, type);
		
		//刷新组合树形结构 BUG240153【鹏华基金】维护完产品树形结构之后A区没有同步更新
		PortCache pdPortCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
		pdPortCache.refreshAssetTreeByTrCode(trCodeR);
		
		return count;
	}
	
	/**
	 * add by zhoushuhang 2018-06-13 BUG206147节点code不能与组合代码相同
	 * @param topParentCode 最顶级父级节点代码
	 * @return
	 */
	public Map<String, String> quertAllNodeCode(String topParentCode) {
		return serviceDao.quertAllNodeCode(topParentCode);
	}
	
	/**
	 * STORY39490【南方基金】产品树形结构实现私有层面设置，并且针对私有层面的不用审核
	 * @throws SQLException 
	 */
	public String getUserId(String quyCon) throws SQLException{
		return serviceDao.getUserId(quyCon);
	}
	
	public String getCodeByCId(String id) {
		return serviceDao.getCodeByCId(id);
	}
	
	@Override
	public String insert(List<BasePojo> pojoList) {
		String retInfo = super.insert(pojoList);
		
		//刷新组合树形结构 BUG240153【鹏华基金】维护完产品树形结构之后A区没有同步更新
		AssetsTree_B assetsTree_B = (AssetsTree_B)pojoList.get(0);
		PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
		portCache.refreshAssetTreeByTrCode(assetsTree_B.getC_TR_CODE_R());
		
		return retInfo;
	}
}
