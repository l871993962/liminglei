package com.yss.ams.base.information.support.bi.ieLink.service;



import java.util.List;

import com.yss.ams.base.information.support.bi.ieLink.pojo.IeLink;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * 收支连接设置数据服务接口类，主要进行跨应用数据获取
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@RestfulSupported
@GenericPojo(pojo=IeLink.class)
public interface IIeLinkDataService extends IKeyConvertDataService,IDataService, IControlDataService {
	
	/**
	 * 根据收支链接代码获取收支链接设置list
	 * @param code  收支链接
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByCodes(String[] codes);
	
	/**
	 * 根据多个收支链接上级费用节点获取收支链接设置list
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByParentCode(String[] codes);
	
	/**
	 * add by liyanjun 2016-2-17 BUG #126592 科目体系界面的费用代码选项数据重复
	 * 收支分类项中，如果同一费用代码设置在了多个收支项目，则前台费用控件就会出现多个相同的费用代码，在选择费用时就选择不上了。为了避免影响其他功能的调用，现在在新方法中对费用代码做去重处理
	 * 同BUG #124462 手工凭证录入费用代码选择不了
	 * @return
	 * @throws ServiceException
	 */
	public <K extends BasePojo> List<K> getFeeDataList() throws ServiceException;
	
	/**
	 * 根据收支链接类型获取收支链接设置
	 * @param types 收支链接
	 * @return
	 * @throws Exception
	 */
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)throws ServiceException;

}
