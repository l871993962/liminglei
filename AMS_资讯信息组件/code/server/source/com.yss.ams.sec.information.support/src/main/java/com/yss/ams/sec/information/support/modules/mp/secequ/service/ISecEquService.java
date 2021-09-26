package com.yss.ams.sec.information.support.modules.mp.secequ.service;



import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;



/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
@RestfulSupported
public interface ISecEquService extends IServiceBus {
//	public QueryRes selSecPub(HashMap<String, Object> paraMap,PageInation page,String queryMenuID);
//	public QueryRes selSecLt(HashMap<String, Object> paraMap,PageInation page,String queryMenuID);


	/**
	 * 	/// By Jinghehe 2017-8-4 BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范 代码调整 
	/////// getRemindZQPS getRemindDJPX 挪到 UCORemindService  
	/////// secEquService 服务中的 getRemindZQPS getRemindDJPX 方法废弃掉 
	 * @param days
	 * @return
	 */
	public List<SecBase> getRemindDJPX(int days);
	
	/**
	 * 	/// By Jinghehe 2017-8-4 BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范 代码调整 
	/////// getRemindZQPS getRemindDJPX 挪到 UCORemindService  
	/////// secEquService 服务中的 getRemindZQPS getRemindDJPX 方法废弃掉 
	 * @param days
	 * @return
	 */
	public List<SecBase> getRemindZQPS(int days);
}
