package com.yss.ams.syncdata.activator;

import org.osgi.framework.BundleContext;

import com.yss.ams.syncdata.mq.SyncDataConsumer;
import com.yss.framework.api.bundle.BaseApplicationActivator;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;

public class SyncDataActivator extends BaseApplicationActivator{
	private Logger logger = LogManager.getLogger(getClass());
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		//启动数据同步监听
		SyncDataConsumer syncDataConsumer = new SyncDataConsumer();
		syncDataConsumer.startConsumer();
	}
	
	@Override
	protected void doStop(BundleContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void registerManageInterface(BundleContext context) {

	}

	@Override
	protected void registerBundleOperInterface(BundleContext context) {
		// TODO Auto-generated method stub
		
	}
}
