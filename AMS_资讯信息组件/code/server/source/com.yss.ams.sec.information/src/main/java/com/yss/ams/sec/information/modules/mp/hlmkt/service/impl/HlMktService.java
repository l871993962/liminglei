package com.yss.ams.sec.information.modules.mp.hlmkt.service.impl;

import java.util.HashMap;
import java.util.List;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secmkt.dao.SecMktDao;
import com.yss.ams.sec.information.modules.mp.secmkt.dao.SecMktSqlBuilder;
import com.yss.ams.sec.information.modules.mp.secmkt.service.impl.SecMktService;
import com.yss.ams.sec.information.support.modules.mp.hlmkt.service.IHlMktService;
import com.yss.ams.sec.information.support.modules.mp.secmkt.service.ISecMktService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.sysinit.HeadKey;

/**
 * 汇率资料行情
 * 
 * @author ll 20121009
 */
public class HlMktService extends ServiceBus<HlMktService> implements
		IHlMktService {

	private SecMktDao serviceDao = null;

	public HlMktService() throws Exception {
		serviceDao = new SecMktDao(DbPoolFactory.getInstance().getPool(),
				new SecMktSqlBuilder());
		dao = serviceDao;
	}

	/**
	 * 调用证券行情的查询方法
	 */
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		ISecMktService marketValueService;
		QueryRes queryRes = new QueryRes();
		try {
			marketValueService = new SecMktService();
			marketValueService.setMenuId("sv_exratepriceadmin");			
			queryRes = marketValueService.queryByCondition(paraMap, page);
			Context context = YssContextFactory.getInstance().getAppContext(SecInfoActivator.class);
			List<HeadKey> list = context.getListHeadMap("sv_exratepriceadmin").getHeadKeyList();
			queryRes.setHeadKeyList(list);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("证券行情查询时失败!", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}


    /////// By Jinghehe 2017-8-4 BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范 代码调整 
    /////// 挪到 IBaseValService 父类 接口当中 以便供其他界面调用 而不仅仅是凭证界面使用 核算管理数据 在YssData 项目中
    /////// IHlMktService 服务中的 queryErByCondition 方法废弃掉  
//	因为资讯组件拆分要把此类拆分出来，但是此类又要引用非拆分ValueERProcess类 故先行注释掉以下方法，等待后期解决方案：
//	public String queryErByCondition(String port, String accDate, String tdCury)
//			throws Exception {
//		String er = "";
//		Date date = YssFun.toDate(accDate);
//		Connection conn = dao.loadNewConnection();
//		ValueERProcess valueEr = new ValueERProcess();
//		valueEr.setDbConn(conn);
//		er = valueEr.proceedExpr(port, date, tdCury);
//			 // 跟据汇率表达式计算汇率
//		er = ComputeUtil.calcErExpr(er).toString();
//		DbFun.releaseConnection(conn); //释放数据库链接
//		return er;
//	}

}
