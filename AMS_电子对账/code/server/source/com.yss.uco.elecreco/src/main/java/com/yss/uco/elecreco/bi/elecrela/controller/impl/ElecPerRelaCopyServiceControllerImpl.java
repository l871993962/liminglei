package com.yss.uco.elecreco.bi.elecrela.controller.impl;

import java.util.List;

import com.yss.framework.api.mvc.vo.CopyListClearVo;
import com.yss.framework.api.mvc.vo.CopyListVo;
import com.yss.framework.api.mvc.vo.CopyMapVo;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.uco.elecreco.support.controller.IElecPerRelaCopyServiceController;
import com.yss.uco.elecreco.support.service.IElecPerRelaCopyService;

/**
 * STORY #95888 【招商基金】【0331】【公募】新基金成立自动复制''对账指标关联'指标
 * @author zhanghubin
 * @date 2021年3月11日
 */
public class ElecPerRelaCopyServiceControllerImpl extends AbstractBaseController<IElecPerRelaCopyService> implements IElecPerRelaCopyServiceController{

	@Override
	public List<String> copy(CopyListVo vo) {
		return getService().copy(vo.getPortCode(),
				vo.getPortCodeList().toArray(new String[vo.getPortCodeList().size()]), vo.getDataCode(),
				vo.getExecProcCode(), vo.getCheckState());
	}

	@Override
	public List<String> copy(CopyListClearVo vo) {
		return getService().copy(vo.getPortCode(),
				vo.getPortCodeList().toArray(new String[vo.getPortCodeList().size()]), vo.getDataCode(),
				vo.getExecProcCode(), vo.getCheckState(), vo.getIsClear());
	}

	@Override
	public List<String> copy(CopyMapVo vo) {
		return getService().copy(vo.getPortCode(), vo.getPortParamsMap(),
				vo.getDataCode(), vo.getExecProcCode(), vo.getCheckState());
	}

}
