package com.yss.ams.base.information.support.sys.convertdict.zhzd.service;
import java.util.HashMap;
import java.util.Map;

import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;
/**
 * 转换字典数据服务接口，主要进行跨应用数据获取
 * @author 马向峰 拆分  2017.0527
 *
 */
@RestfulSupported
public interface IZhzdDataService extends  IControlDataService,IKeyConvertDataService{
	
	/**
	 * 根据场景类型查找转换字典，并把 源值 和 转换值 存到Map中
	 * @param type 场景类型
	 * @return 封装了 源值和转换值的Map<源值,转换值>
	 */
	@LinkControllerMethod(value = "getKeyConvertMap" , arguTypes = String.class)
	HashMap<String, String> getKeyConvertMap(String type);
	 
	 /**
	 * 根据应用场景，源值获取转换值
	 * @param srcCode 源值
	 * @param sceneType 场景
	 * @return
	 */
	public String specificDictConvert(String srcCode, String sceneType);
	/**
	 * comment：根据转换字典代码批量获取转换字典<br>
	 * src：STORY #60135 嘉实基金-系统间升级交互机制 <br>
	 * author：shijian@ysstech.com<br>
	 * date：2018年12月21日<br>
	 */
	public Map<String, String> getConvertMapByGroupCode(String[] groupCodes);
}
