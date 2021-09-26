package com.yss.ams.product.information.modules.pg.portgrouprela.controller.impl;

import java.util.List;

import com.yss.ams.product.information.support.modules.pg.portgrouprela.controller.IBaseAutomaticPortGroupController;
import com.yss.fast.task.support.automatic.service.IAutomaticPortGroupService;
import com.yss.framework.api.restful.base.AbstractBaseController;

/**
 * STORY #78622 华宝：路由条件上要支持选择群组的判断
 * @author lenovo
 * 
 */
public class AutomaticPortGroupControllerImpl extends AbstractBaseController<IAutomaticPortGroupService> implements IBaseAutomaticPortGroupController{

	@Override
	public List<String> queryPortGroupByPortCode(String portCode) {
		return getService().queryPortGroupByPortCode(portCode);
	}


}
