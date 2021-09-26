package com.yss.uco.elecreco.service.impl;

import java.lang.reflect.Method;

import com.yss.busoper.service.impl.DayfBizControlService;
import com.yss.framework.api.bundle.BundleContextWrapper;
import com.yss.framework.api.busoperservice.IBaseOper;
import com.yss.framework.api.busoperservice.IBizControlService;
import com.yss.framework.api.common.co.InterceptorRes;
import com.yss.framework.api.common.co.SafeData;
import com.yss.framework.api.common.pi.IApplication;
import com.yss.framework.api.common.pi.ISafeDataDataService;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.logger.HandleOperLogThread;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.I_BusOperCtl;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.IService;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.mvc.control.ControlException;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceMap;
import com.yss.framework.api.servlet.pojo.TransStructure;
import com.yss.framework.api.servlet.util.MethodExecuteUtil;
import com.yss.framework.api.util.BundleServiceUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.mvc.controls.ControlAssistance;
import com.yss.mvc.interceptor.addon.processor.AddOnBeforeProc;
import com.yss.mvc.sessionmsg.msgmgr.MsgCache;
import com.yss.mvc.sessionmsg.msgmgr.MsgCacheMgr;
import com.yss.uco.elecreco.service.IErManageService;

public class ErManageService implements IErManageService{
	@Override
	public Object doMethod(TransStructure transStruct) throws Exception {
		Object value = "";
		InterceptorRes incRec = new InterceptorRes();
		try {
			/* 接口名称 */
			String interfaceName = transStruct.getClassName();
			/* Service信息对象 */
			ServiceMap serviceInfo = YssContextFactory.getInstance().getServiceMap(interfaceName);
			
			IApplication app = serviceInfo.getApp();
			String appCode = app.getAppCode();
			/* 接口实例 */
			Object service = BundleServiceUtil.getService(serviceInfo.getInterfaceName(), appCode);
			/* 重新创建 通过OSGI取到的实例类似单例会有问题 需要新建实例 */
			Object instance = service.getClass().newInstance();
			
			if (instance instanceof I_BusOperCtl) {
				MsgCache msgCache = MsgCacheMgr.newInstance().getCache(((AppContext) ContextFactory.getContext()).getRequest());
				((I_BusOperCtl) instance).setMsgCache(msgCache);
			}
			
			BundleContextWrapper bundleContext = YssContextFactory.getInstance().getBundleContext(appCode);
			if (instance instanceof IServiceBus) {
				((IServiceBus) instance).setBundlContext(bundleContext);
				String menuId = serviceInfo.getServiceInfo().getMenuId();
				//// By Jinghehe 2014-10-15 
				//// 在服务实力化 后对安全管理对象 赋值
				ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
				SafeData safeData = safeDataService.getByFunCode(menuId);
				((IServiceBus) instance).setSafeData(safeData);
				if (menuId != null && !"".equalsIgnoreCase(menuId)
						&& instance instanceof IService) {
					((IService) instance).setMenuId(menuId);
				}
				
				/* 方法所有对象 */
				Method[] methods = service.getClass().getMethods();
				/* 方法单个对象 */
				Method method = MethodExecuteUtil.getInvoiceMethod(methods,
						transStruct);
				/* 方法参数 */
				Object[] methodArgs = MethodExecuteUtil.getMethodArgs(
						transStruct.getParaList(), method, transStruct);
				
				incRec = AddOnBeforeProc.execute(method, (IServiceBus)instance, methodArgs);
				
				if (MvcConstant._Success.equals(incRec.getExecRes())){
					ControlAssistance controlAss = new ControlAssistance();
					boolean isIgnoreOperLog = controlAss.IsIgnoreOperLog(method);
					/* 执行方法 */
					value = method.invoke(instance, methodArgs);
					controlAss.IsReloadCache((IServiceBus)instance, method, methodArgs);
					if (!isIgnoreOperLog) {
						HandleOperLogThread handOperLogThread = new HandleOperLogThread(null);
						handOperLogThread.init((IServiceBus) instance, method.getName(), methodArgs, value);
						Thread thread = new Thread(handOperLogThread);
						thread.start();
					}
				}else {
					value = incRec;
				}
				
				return value;
			} else if (instance instanceof IBaseOper) {
				IBizControlService iBizCtlService = new DayfBizControlService();
				Method[] methods = service.getClass().getMethods();
				Method method = MethodExecuteUtil.getInvoiceMethod(methods,
						transStruct);
				Object[] methodArgs = MethodExecuteUtil.getMethodArgs(
						transStruct.getParaList(), method, transStruct);
				IService svc = (IService) instance;
				iBizCtlService.init(svc, method, methodArgs);
				value = iBizCtlService.doMethod();
				return value;
			}else{
				/* 方法所有对象 */
				Method[] methods = service.getClass().getMethods();
				/* 方法单个对象 */
				Method method = MethodExecuteUtil.getInvoiceMethod(methods,
						transStruct);
				/* 方法参数 */
				Object[] methodArgs = MethodExecuteUtil.getMethodArgs(
						transStruct.getParaList(), method, transStruct);
				/* 执行方法 */
				value = method.invoke(instance, methodArgs);
			}			
			
		} catch (Exception ex) {
			ex = YssException.getUnReflectException(ex);
			if(ex instanceof YssRuntimeException){
				throw ex;
			}else{
				Logger logger = LogManager.getLogger(this.getClass());
				logger.error(ex.getMessage());
				throw new ControlException(ex);
			}
		}
		return value;
	}
}
