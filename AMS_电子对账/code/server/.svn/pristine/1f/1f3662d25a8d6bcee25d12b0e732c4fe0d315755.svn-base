package com.yss.uco.elecreco.automic.param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.fast.task.support.automatic.ParamConstants;
import com.yss.fast.task.support.automatic.cache.service.IAutoProcessCacheService;
import com.yss.fast.task.support.automatic.pojo.AutoProcess;
import com.yss.fast.task.support.automatic.pojo.AutoProcessTask;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.restful.RestfulConfigServiceImpl;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.uco.elecreco.automic.util.AutomicUtil;

public class ParamUtil {
	/**
	 * 排除掉报表类型，如日报，月报，季报，半年报，年报
	 * @param dztypes
	 * @return
	 */
	public static List<String> filterDzType(List<String> dztypes)
	{
		List<String> res = new ArrayList<String>();
		if(dztypes != null && dztypes.size() > 0)
		{
			for (String dztype : dztypes) {
				if(dztype == null || dztype.length() != 4){
					continue;
				}
				res.add(dztype);
			}
		}
		return res;
	}
	
	/**
	 * 从缓存中获取超时时间
	 * @param params
	 * @return
	 */
	public static String queryOverTime(HashMap<String, Object> params){
		String processCode = AutomicUtil.getStringParam(params, ParamConstants.ProcessCode);
		String taskcode = AutomicUtil.getStringParam(params, ParamConstants.C_TASK_ID);
		AutoProcess autoProcess = null;
		if(new RestfulConfigServiceImpl().getConfig().isShell()){
			IAutoProcessCacheService autoProcessCacheService = YssServiceFactory.getInstance().createService(IAutoProcessCacheService.class);
			autoProcess = autoProcessCacheService.getCacheByKey(processCode);
		}else{
			autoProcess = (AutoProcess)CacheManager.getInstance().getCache(CacheGroup.AUTOPRPCESS).getCacheByKey(processCode);
		}
		if(autoProcess == null){
			return null;
		}
		List<AutoProcessTask> taskList = autoProcess.getProcessTaskList();
		if(taskList == null || taskList.isEmpty()){
			return null;
		}
		
		for (AutoProcessTask autoProcessTask : taskList) {
			if(autoProcessTask.getTaskId().equals(taskcode) && autoProcessTask.getProcessCode().equals(processCode)){
				return autoProcessTask.getTaskOverTime();
			}
		}
		return null;
	}
}
