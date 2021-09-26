package com.yss.ams.db.upgrade.syncdata.activator;

import org.osgi.framework.BundleContext;

import com.yss.fast.db.upgrade.support.bundle.BaseDbUpgradeActivator;

/**
 * 数据同步数据库升级组件出发类，集成BaseDbUpgradeActivator类
 * @author chenyoucai 20180626
 */
public class YssSyncDataDbUpActivator extends BaseDbUpgradeActivator {

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
		// TODO Auto-generated method stub
		return "YssSyncDataDbUp";
	}

	/* (non-Javadoc)
	 * @see com.yss.fast.db.upgrade.support.bundle.BaseDbUpgradeActivator#getAppName()
	 */
	@Override
	protected String getAppName() {
		// TODO Auto-generated method stub
		return "数据同步数据库升级组件";
	}
}
