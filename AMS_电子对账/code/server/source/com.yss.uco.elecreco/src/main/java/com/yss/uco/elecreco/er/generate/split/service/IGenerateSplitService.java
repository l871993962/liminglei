package com.yss.uco.elecreco.er.generate.split.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;

public interface IGenerateSplitService {
	/**
	 * 获取拆分关系
	 * @param portCode
	 * @param geneDate
	 * @param tghCode
	 * @param splitCode
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public ErSplitRela getErSplitRela(String portCode, String geneDate,
			String tghCode, String splitCode, Connection conn) throws Exception;
	/**
	 * 获取拆分关系
	 * @param portCode
	 * @param date
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<ErSplitRela> getErSplitRelas(String portCode,String date,Connection conn) throws Exception;
	/**
	 * 获取该组合在该业务日期没有设置拆分规则的明细科目
	 * @param portCode
	 * @param date
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<ErKmb> getUnSplitRuleDetailKm(String portCode,String date,Connection conn) throws Exception;
	/**
	 * 获取拆分规则
	 * @param portCode
	 * @param date
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getSplitRuleDetailKmBySplitRela(String relaId,Connection conn) throws Exception;
}
