package com.yss.ams.syncdata.support.modules.base.service;

import java.util.List;

import com.yss.ams.syncdata.business.productinfo.vo.SyncHandleDataVo;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;

/**
 * 数据同步实现接口
 * @author chenyoucai 
 */
public interface ISyncDataOperService{

	/**
	 * 数据同步方法
	 * @param pojo 同步数据
	 * @param operType 操作类型：SYNC_ADD(新增)、SYNC_DEL(删除)、SYNC_UPD(修改)
	 * @return
	 * @throws Exception
	 */
	@LinkControllerMethod(value="syncHandleData",arguTypes = SyncHandleDataVo.class)
	public String syncHandleData(@LinkControllerMethodArgu("pojos")List<Object> pojos,@LinkControllerMethodArgu("operType")String operType) throws Exception;
}
