package com.yss.ams.product.information.modules.pg.portgroup.admin;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupDao;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;

/**
 * <群组管理>管理类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupAdmin  extends BaseAdmin{
	
	private PortGroupDao serviceDao = null;
	
	private Logger logger = LogManager.getLogger(PortGroupAdmin.class);

	public PortGroupAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		serviceDao = new PortGroupDao(pool, sqlBuilder);
	}
	
	public QueryRes getPortGroupA(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
//			serviceDao = new PortGroupDao(DbPoolFactory.getInstance().getPool(), new PortGroupSqlBuilder());
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPortGroupA(paraMap,null);
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getListHeadMap("portGroup");
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);

//			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portGroup");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品群组模块：A区查询群组失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/**
	 * By Jinghehe 2014-6-5
	 * 群组和组合代码转名称使用的服务类 
	 * 涉及组合和群组参数的界面要进行代码转名称
	 * 仅仅供含有组合和群组的List界面
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() {
		return serviceDao.getKeyConvertMap();
	}
	
	/**
	 * 根据群组代码查询群组
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	@SuppressWarnings("unchecked")
	public <T extends BasePojo> List<T> getDataListByCodes(String[] codes) throws Exception {
		return (List<T>) serviceDao.getDataListByCodes(codes);
	}

	
}
