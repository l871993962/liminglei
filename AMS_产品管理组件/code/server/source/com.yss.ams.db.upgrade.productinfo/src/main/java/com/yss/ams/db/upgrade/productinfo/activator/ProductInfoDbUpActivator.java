package com.yss.ams.db.upgrade.productinfo.activator;

import org.osgi.framework.BundleContext;

import com.yss.fast.db.upgrade.support.bundle.BaseDbUpgradeActivator;

/**
 * The activator class controls the plug-in life cycle
 * 产品信息组件脚本升级组件出发类，集成BaseDbUpgradeActivator类
 */
public class ProductInfoDbUpActivator extends BaseDbUpgradeActivator {

	@Override
	public void start(BundleContext bundlecontext) throws Exception {
		// TODO Auto-generated method stub
		super.start(bundlecontext);
	}

	/* (non-Javadoc)
	 * @see com.yss.fast.db.upgrade.support.bundle.BaseDbUpgradeActivator#getAppCode()
	 */
	@Override
	protected String getAppCode() {
		return "ProductinfoDbUp";
	}

	/* (non-Javadoc)
	 * @see com.yss.fast.db.upgrade.support.bundle.BaseDbUpgradeActivator#getAppName()
	 */
	@Override
	protected String getAppName() {
		return "产品信息组件";
	}

}
