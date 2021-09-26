package com.yss.ams.product.information.modules.ab.productInfoQuery.service.impl;



import com.yss.ams.product.information.modules.ab.productInfoQuery.dao.ProductInfoQueryDao;
import com.yss.ams.product.information.modules.ab.productInfoQuery.dao.ProductInfoQuerySqlBuilder;
import com.yss.ams.product.information.support.modules.ab.productInfoQuery.pojo.ProductInfoQuery;
import com.yss.ams.product.information.support.modules.ab.productInfoQuery.service.IProductInfoService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**

 * 2021-03-16
 * STORY #99875 【估值核算】【国寿资产】【V1.300.7.0_GSZC_20201218】-增加产品信息查询界面
 * @author wanghaocheng
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class ProductInfoService extends ServiceBus<ProductInfoQuery>
		implements IProductInfoService {

	private ProductInfoQueryDao portInfoQueryDao = null;
	public ProductInfoService() throws Exception {
		portInfoQueryDao = new ProductInfoQueryDao(DbPoolFactory.getInstance().getPool(), new ProductInfoQuerySqlBuilder());
		dao = portInfoQueryDao;
	}
	
}
