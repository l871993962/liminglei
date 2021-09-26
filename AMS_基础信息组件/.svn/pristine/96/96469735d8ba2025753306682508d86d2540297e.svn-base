package com.yss.ams.base.information.modules.sys.voc.controller.impl;

import com.yss.ams.base.information.support.sys.voc.controller.IUcoVocController;
import com.yss.ams.base.information.support.sys.voc.pojo.UcoVocVo;
import com.yss.ams.base.information.support.sys.voc.service.IUcoVocService;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class UcoVocControllerImpl extends AbstractBaseController<IUcoVocService> implements IUcoVocController {

	
	//boolean isFistLoad, List<Vocabulary> addList, List<Vocabulary> updList
	@Override
	public void addAndUpdUcoVoc(UcoVocVo vo) throws Exception {
		 getService().addAndUpdUcoVoc(vo.isFistLoad(),vo.getAddList(),vo.getUpdList());
	}

}