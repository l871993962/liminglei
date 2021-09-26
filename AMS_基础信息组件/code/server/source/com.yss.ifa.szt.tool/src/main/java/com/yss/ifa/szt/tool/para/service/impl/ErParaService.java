package com.yss.ifa.szt.tool.para.service.impl;

import java.util.List;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.para.dao.ErParaDao;
import com.yss.ifa.szt.tool.para.dao.ErParaSqlBuilder;
import com.yss.ifa.szt.tool.para.service.IErParaService;
import com.yss.ifa.szt.tool.pojo.MrInfo;

/**
 *  /// STORY42784中国银行_深证通伺服器要求采用热备模式
 *  /// STORY42660【中国银行】深证通伺服器要求采用热备模式
 * @ClassName: ErParaService 
 * @Description: 深圳通参数设置服务类
 * @author wulongxing
 * @date 2017年6月13日 下午2:51:31 
 *
 */
public class ErParaService extends ServiceBus<ErParaService> implements IErParaService{

	private ErParaDao erParaDao = null;
	
	public ErParaService(){
		erParaDao = new ErParaDao(DbPoolFactory.getInstance().getPool(), new ErParaSqlBuilder());
		dao = erParaDao;
	}

	@Override
	public List<MrInfo> queryMrInfos(String c_Para_Code) {
		return erParaDao.queryMrInfos(c_Para_Code);
	}
}
