package com.yss.uco.elecreco.activator;

import org.osgi.framework.BundleContext;

import com.yss.framework.api.bundle.BaseApplicationActivator;
import com.yss.framework.api.bundle.ClassServiceHandler;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1001;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1011;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1013;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1022;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1031;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1701;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1711;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1801;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1811;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1901;
import com.yss.uco.elecreco.er.mrapi.executer.Executer1903;
import com.yss.uco.elecreco.er.mrapi.executer.ExecuterA001;
import com.yss.uco.elecreco.er.mrapi.thread.SendThreadPool;
import com.yss.uco.elecreco.service.IErManageService;
import com.yss.uco.elecreco.service.impl.ErManageService;

public class ErActivator extends BaseApplicationActivator {
	
	private static Logger logger = LogManager.getLogger(ErActivator.class);
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		ClassServiceHandler.addClassServiceMap("Executer1001", Executer1001.class);
		ClassServiceHandler.addClassServiceMap("Executer1011", Executer1011.class);
		ClassServiceHandler.addClassServiceMap("Executer1013", Executer1013.class);
		ClassServiceHandler.addClassServiceMap("Executer1022", Executer1022.class);
		ClassServiceHandler.addClassServiceMap("Executer1031", Executer1031.class);
		ClassServiceHandler.addClassServiceMap("Executer1701", Executer1701.class);
		ClassServiceHandler.addClassServiceMap("Executer1801", Executer1801.class);
		ClassServiceHandler.addClassServiceMap("Executer1711", Executer1711.class);
		ClassServiceHandler.addClassServiceMap("Executer1811", Executer1811.class);
		ClassServiceHandler.addClassServiceMap("Executer1901", Executer1901.class);
		ClassServiceHandler.addClassServiceMap("Executer1903", Executer1903.class);
		ClassServiceHandler.addClassServiceMap("ExecuterA001", ExecuterA001.class);
	}
	
	/**
	 * BUG266596深证通伺服器YssMrApi3.6版本目前存在的两个问题【生产优化】
	 */
	@Override
	protected void afterStart(BundleContext bundleContext) {
		SendThreadPool.start();
		logger.info("电子对账发送线程启动完成！");
	}

	@Override
	protected void registerManageInterface(BundleContext context) {
		/* 注册清算管理应用接口服务 */
		IErManageService manageService = new ErManageService();
		context.registerService(IErManageService.class.getName(),
				manageService, null);

		/* 绑定应用级别Bundle管理服务接口 */
		Context appContext = YssContextFactory.getInstance().getAppContext(
				this.getClass());
		appContext.setManageInterface(IErManageService.class.getName());
		
	}

	@Override
	protected void doStop(BundleContext context) {
		SendThreadPool.stop();
	}

	@Override
	protected void registerBundleOperInterface(BundleContext context) {
		// TODO Auto-generated method stub

	}
	
}
