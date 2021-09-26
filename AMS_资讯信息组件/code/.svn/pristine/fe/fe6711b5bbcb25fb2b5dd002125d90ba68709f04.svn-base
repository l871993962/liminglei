package com.yss.ams.sec.information.support.modules.mp.secmkt.service;




import java.util.Date;
import java.util.List;

import com.yss.ams.sec.information.support.modules.mp.secmkt.pojo.SecMktVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;

@RestfulSupported
public interface ISecMktService extends IServiceBus {
//	public QueryRes selOutMarketValue(HashMap<String, Object> paraMap,PageInation page,String queryMenuID);
//	public QueryRes selRatePriceValue(HashMap<String, Object> paraMap,PageInation page,String queryMenuID);

	/**
	 * Author : ChenLong
	 * Date   : 2016-05-30
	 * Status : Add
	 * Comment: 查询汇率
	 */
	@LinkControllerMethod(value="getRate",arguTypes = SecMktVo.class)
	public double getRate(@LinkControllerMethodArgu("date")Date date,@LinkControllerMethodArgu("secCode")String secCode);
	/**
	 * By Jinghehe 2017-8-4 删除一批证券行情 
	 * BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范
	 * 净值确认后，若证券行情映射中维护相关信息，则生成一笔证券行情数据
	 * ADD BY ZXL 20141029 招商现场
	 * 
	 * add by liyanjun 2016-3-31 STORY #28721 中信证券-【行政外包4.5系统】增加母基金反确认提示信息
	 * 现有逻辑：针对主、从基金业务特点（从基金投资主基金，主基金净值确认后、作为从基金对应持仓证券的行情），
	 *		用户在｛净值确认管理｝中进行锁定操作时，系统根据｛证券行情映射｝中设置，判别本基金/本分级基金是否与某一证券有映射关系。如有，则对应生成一笔证券行情资料。
	 * 逻辑变更：根据 证券持仓+用户权限 数据，确定需要向哪些用户推送提醒；并以即时通讯模块的飘窗形式进行提醒。
	 * 适用场景：净值反确认操作时触发
	 * @param pojoList
	 * @throws Exception
	 */
	public void deleteSecMpMap(List<BasePojo> lstMp);
	
	/**
	 * By Jinghehe 2017-8-4 删除一批证券行情 
	 * BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范
	 * 净值确认后，若证券行情映射中维护相关信息，则生成一笔证券行情数据
	 * ADD BY ZXL 20141029 招商现场
	 * 
	 * add by liyanjun 2016-3-31 STORY #28721 中信证券-【行政外包4.5系统】增加母基金反确认提示信息
	 * 现有逻辑：针对主、从基金业务特点（从基金投资主基金，主基金净值确认后、作为从基金对应持仓证券的行情），
	 *		用户在｛净值确认管理｝中进行锁定操作时，系统根据｛证券行情映射｝中设置，判别本基金/本分级基金是否与某一证券有映射关系。如有，则对应生成一笔证券行情资料。
	 * 逻辑变更：根据 证券持仓+用户权限 数据，确定需要向哪些用户推送提醒；并以即时通讯模块的飘窗形式进行提醒。
	 * 适用场景：净值反确认操作时触发
	 * @param pojoList
	 * @throws Exception
	 */
	@LinkControllerMethod(value="QuerySGSecMpMap",arguTypes = SecMktVo.class)
	public List<String> QuerySGSecMpMap(@LinkControllerMethodArgu("PortCode")String PortCode,@LinkControllerMethodArgu("date")Date date) throws Exception;
	
	/**
	 * 根据库存数据表的seccode list查询 证券行情映射表 的  标的组合代码
	 * add baoql 20190827 
	 * STORY #75718 自动化优化：增加智能映射方式 
	 * @param secCode
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@LinkControllerMethod(value="getPortCodeBySecCodeList",arguTypes = SecMktVo.class)
	public List<String> getPortCodeBySecCodeList(@LinkControllerMethodArgu("secCode")List<String> secCode,@LinkControllerMethodArgu("date")Date date) throws Exception;

}
