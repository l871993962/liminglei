package com.yss.uco.elecreco.bi.elecrela.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.framework.api.busoperservice.ILogger;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.SafeData;
import com.yss.framework.api.common.pi.ISafeDataDataService;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.DataProcServiceFactroy;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.platform.support.dataservice.service.IPortDataService;
import com.yss.uco.elecreco.bi.elecrela.dao.ElecPerRelaDao;
import com.yss.uco.elecreco.bi.elecrela.dao.ElecPerRelaSqlBuilder;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.bi.elecrela.service.IElecPerRelaService;
/**
 * STORY #95888 【招商基金】【0331】【公募】新基金成立自动复制''对账指标关联'指标
 * @author zhanghubin 
 * @date 20210312
 */
public class ElecPerRelaService extends ServiceBus<ElecPerRela> implements IElecPerRelaService {
	
	private ElecPerRelaDao serviceDao = null;
	
	public ElecPerRelaService() throws Exception {
		serviceDao = new ElecPerRelaDao(DbPoolFactory.getInstance().getPool(),
				new ElecPerRelaSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public BasePojo getPerRelaByCodeAndName(String c_ZB_CODE,
			String c_ZB_Name, String c_DZ_CODE) {
		return serviceDao.getPerRelaByCodeAndName(c_ZB_CODE,c_ZB_Name,c_DZ_CODE);
	}

	@Override
	public BasePojo getPerRelaByCode(String c_ZB_CODE) {
		return serviceDao.getPerRelaByCode(c_ZB_CODE);
	}

	@Override
	public HashMap<String, ElecPerRela> getPerRelaByPortAndDZCode(
			String c_PORT_CODE, String c_DZ_CODE) {
		// TODO Auto-generated method stub
		return this.serviceDao.getPerRelaByPortAndDZCode(c_PORT_CODE, c_DZ_CODE);
	}
	/**
	 * STORY #95888 【招商基金】【0331】【公募】新基金成立自动复制''对账指标关联'指标
	 * add by zhanghubin 20210315
	 * @param portCode
	 * @param portParamsMap
	 * @param dataCode
	 * @param execProcCode
	 * @param checkState
	 * @return
	 */
	public List<String> copy(String portCode,
			HashMap<String, String> portParamsMap, String dataCode,
			String execProcCode, String checkState) {
		ILogger log = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		logger.debug("对账指标关联复制开始");
		String userCode = ContextFactory.getContext().getUserCode();
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ARRAY_C_PORT_CODE", portCode);
		PageInation page = new PageInation();
		page.setUsePage(false);
		// 查询参考组合的对账指标关联数据
		List<BasePojo> basepojos = super
				.query(paraMap, page, ElecPerRela.class);
		// 失败组合
		List<String> failPorts = new ArrayList<String>();

		ISafeDataDataService safeDataService = HttpServiceFactory.getInstance()
				.createService(ISafeDataDataService.class);
		SafeData safeData = safeDataService.getByFunCode("dzPerRela");
		// 需要复制的数据
		List<BasePojo> saveList = new ArrayList<BasePojo>();
		// List<ElecPerRela> paraList =
		// elecPerRelaDao.getPerRelaByPort(portCode);
		String dataName = "对账指标关联";
		PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
		for (String port : portParamsMap.keySet()) {

			BEN_RECORD ben_Record = new BEN_RECORD(userCode);
			HashMap<String, String> map = portCache.getKeyConvertMap();
			if (map == null) {
				IPortDataService portDataService = YssServiceFactory
						.getInstance().createService(IPortDataService.class);
				map = portDataService.getKeyConvertMap();
			}
			String pre = "组合" + map.get(port) + dataName;
			try {
				
				ben_Record.init(port, dataName, null);
				ben_Record.BeginLog();
				log.write(execProcCode,
						ben_Record);
				String[] params = portParamsMap.get(port).split("=");

				if ("1".equals(params[0])) {
					for (BasePojo basePojo : basepojos) {
						ElecPerRela pojo = (ElecPerRela) basePojo;
						if (!StringUtil.IsNullOrEmptyT(checkState)) {
							if (safeData != null && safeData.getN_CHECK() == 1
									&& ("0".equals(checkState))) {
								pojo.setAuditState(0);
							}
						} else {
							if (safeData != null && safeData.getN_CHECK() == 1
									&& (safeData.getN_COPY_CHECK() != 1)) {
								pojo.setAuditState(0);
							}
						}
						pojo.setC_PORT_CODE(port);
						pojo.setId("");
						pojo.setOperator(userCode);
						pojo.setModifier(userCode);
						pojo.setModifyDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
						pojo.setAuditDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
						saveList.add(pojo);
					}
					ben_Record.appendDetailMes("组合代码：" + port
							+ "，数据项代码：" + dataName);
					((ElecPerRelaDao) dao).deleteBeforeCopy(port);
					dao.insert(saveList);
					String end = "复制成功" + saveList.size() + "条数据，参照组合共"
							+ saveList.size() + "条数据！";
					ben_Record.appendDetailMes(pre + end);
					ben_Record.EndLog_Success(end);
				} else {
					ben_Record.appendDetailMes("组合代码：" + port + "，数据项代码："
							+ dataName);
					String end = params[1];
					ben_Record.appendDetailMes(pre + end);
					ben_Record.EndLog_Warn(end);
				}
			} catch (Exception e) {
				failPorts.add(port);
				logger.log("复制数据错误失败", e);
				ben_Record.appendDetailMes(pre + "已有参数，不允许进行复制！");
				ben_Record.EndLog_Fail("复制数据错误！");
			} finally {
				saveList.clear();
				ben_Record.setC_User_Code(userCode);
				log.write(execProcCode, ben_Record);
			}
		}
		logger.debug("对账指标关联复制结束");
		return failPorts;
	}
	
}
