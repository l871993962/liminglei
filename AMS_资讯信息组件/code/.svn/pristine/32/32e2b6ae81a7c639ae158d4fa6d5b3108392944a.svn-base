package com.yss.ams.sec.information.modules.pub.service.impl;

import java.util.HashMap;
import java.util.List;





import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.pub.dao.DayfSysInitDao;
import com.yss.ams.sec.information.modules.pub.dao.DayfSysInitSqlBuilder;
import com.yss.ams.sec.information.support.modules.pub.service.IDayfSysInitService;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.dayf.assetStats.dao.DayfSysInitDao;
//import com.yss.dayf.assetStats.dao.DayfSysInitSqlBuilder;
//import com.yss.dayf.assetStats.service.IDayfSysInitService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
public class DayfSysInitService extends ServiceBus<DayfSysInitService>
		implements IDayfSysInitService {
	private DayfSysInitDao serviceDao = null;

	public DayfSysInitService() {
		serviceDao = new DayfSysInitDao(DbPoolFactory.getInstance().getPool(),
				new DayfSysInitSqlBuilder());
		dao = serviceDao;
	}

	/**
	 * 查询方法
	 */
	public QueryRes queryData(HashMap<String, String> paraMap)
			throws ServiceException {

		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		try {
			dataList = serviceDao.getInitData(paraMap);
			res.setDataList(dataList);
			res.setHeadKeyList(ServiceAssistance.getListHead("sv_sysInit",SecInfoActivator.class));
			res.setMenuId("sv_sysInit");
			setShowConvertAssemble(res);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
		//
		// PreparedStatement pstmt = null;
		// Connection conn = null;
		// String strSql = null; // strSql sql查询语句
		// ResultSet rs = null; // ResultSet 返回一个查询结果集
		// ResultSetTools rsTools = null;
		//
		// String code = paraMap.get("C_DV_TYPE");
		// QueryRes res = new QueryRes();
		// List<BasePojo> dataList = new ArrayList<BasePojo>();
		//
		// StringBuffer buf = new StringBuffer();
		// buf.append(" SELECT ");
		// buf.append(" 	a.C_DV_CODE AS C_DV_ITEM_CODE, ");
		// buf.append(" 	a.C_DESC, ");
		// buf.append(" 	1 AS N_CHECK_STATE ");
		// buf.append(" FROM v_s_dv_voc a ");
		// buf.append(" where C_DV_TYPE = ?");
		// strSql = buf.toString();
		// StringUtil.clearStringBuffer(buf);
		// try {
		// conn = dao.loadNewConnection();
		// rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
		// pstmt = conn.prepareStatement(strSql);
		// pstmt.setString(1, code);
		// rs = pstmt.executeQuery();
		//
		// while (rs.next()) {
		// SysInitItemBean sysBean = rsTools.ResultToBeanGeneric(rs,
		// SysInitItemBean.class);
		// dataList.add(sysBean);
		// }
		// } catch (DataAccessException e) {
		// e.printStackTrace();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// res.setDataList(dataList);
		// res.setHeadKeyList(ServiceAssistance.getListHead("sysInit"));
		// res.setMenuId("sysInit");
		//
		// return res;
	}
}
