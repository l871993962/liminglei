package com.yss.ams.base.information.modules.sys.voc.service.impl;

import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.voc.dao.UcoVocDao;
import com.yss.ams.base.information.modules.sys.voc.dao.UcoVocSqlbuilder;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.ams.base.information.support.sys.voc.service.IUcoVocService;


/**
 * //STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志  edit by sunyanlin 20191202
 * @author lenovo
 *
 */
public class UcoVocService extends ServiceBus<UcoVocService> implements IUcoVocService{
	
	private  UcoVocDao serviceDao = null;
	
	public UcoVocService() {
		serviceDao = new UcoVocDao(YssDbPoolFactory.getInstance()
				.getDbPool(InformationActivator.class), new UcoVocSqlbuilder());
		dao = serviceDao;
	}
	
	
	/**
	 * 增量同步数据到估值专用词汇表
	 */
	@Override
	public void addAndUpdUcoVoc(boolean isFistLoad,List<Vocabulary> addList,List<Vocabulary> updList) throws Exception{
		serviceDao.addAndUpdUcoVoc(isFistLoad,addList, updList);
	}

}
