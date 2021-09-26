package com.yss.uco.elecreco.er.generate.split.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.generate.split.dao.GenerateSplitDao;
import com.yss.uco.elecreco.er.generate.split.service.IGenerateSplitService;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;

public abstract class GenerateSplitService implements IGenerateSplitService {

	protected GenerateSplitDao dataDao;
	
	public GenerateSplitService(GenerateSplitDao dataDao)
	{
		this.dataDao = dataDao;
	}
	
	@Override
	public ErSplitRela getErSplitRela(String portCode, String geneDate,
			String tghCode, String splitCode, Connection conn) throws Exception {
		return this.dataDao.getErSplitRela(portCode, geneDate, tghCode, splitCode, conn);
	}

	@Override
	public List<ErSplitRela> getErSplitRelas(String portCode, String date,
			Connection conn) throws Exception{
		return this.dataDao.getErSplitRelas(portCode, date, conn);
	}

	@Override
	public List<ErKmb> getUnSplitRuleDetailKm(String portCode,String date,Connection conn) throws Exception{
		return this.dataDao.getUnSplitRuleDetailKm(portCode,date,conn);
	}

	@Override
	public Map<String,String> getSplitRuleDetailKmBySplitRela(String relaId,
			Connection conn) throws Exception {
		return this.dataDao.getSplitRuleDetailKmBySplitRela(relaId,conn);
	}

}
