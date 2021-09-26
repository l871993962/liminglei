package com.yss.uco.elecreco.er.generate.zcfzb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.database.DbFun;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.er.generate.report.ReportManager;
import com.yss.uco.elecreco.er.generate.service.GeneElecDataService;

public class GeneZCFZBDataService extends GeneElecDataService{
	/**
	 * 报表管理（自定义列）
	 * STORY59739【易方达基金】【紧急加急】财务报表电子对账生成应该同财务报表配置报错一致
	 */
	protected ReportManager reportManager = null;
	
	protected String C_FILE_TYPE = "1701";
	
	public GeneZCFZBDataService(String c_FILE_TYPE) {
		super();
		C_FILE_TYPE = c_FILE_TYPE;
	}
	@Override
	public Map<String, Object> geneElecData() throws Exception {
		//初始化报表个性设置   STORY59739【易方达基金】【紧急加急】财务报表电子对账生成应该同财务报表配置报错一致
		reportManager = new ReportManager(C_FILE_TYPE, reportCode);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = "";
		String resultDetail = "";
		int count = 0;
		try {
			this.createFsn();
			this.getAssCode();
			this.deleteHistory();
			count += this.geneZCFZData();
		} catch (Throwable e) {
			result = "Fail";
			resultDetail = e.getMessage();
			logger.error("生成资产负债表数据出错：" + e.getMessage(), e);
		}
		
		if(count > 0){
			this.geneErInfo(C_FILE_TYPE, c_rpt_type);
			this.geneErInfoHisAndInst(C_FILE_TYPE, c_rpt_type);//生成历史进行记录
			result = this.fsn;
			resultDetail = "电子对账资产负债表数据生成成功！";
		}else if(!"Fail".equalsIgnoreCase(result)){
			result = "Warn";
			resultDetail = "没有数据生成！";
		}
		resultMap.put("result", result);
		resultMap.put("resultDetail", resultDetail);
		return resultMap;
	}
	private void deleteHistory() throws Exception {
		PreparedStatement pst = null;
		try {
			StringBuffer buffer = new StringBuffer();
			int index = 1;
			buffer.append("DELETE FROM T_D_ER_ZCFZ A WHERE A.C_ASS_CODE = ? AND A.C_RPT_TYPE = ? AND A.D_START_DATE = ? AND A.D_END_DATE = ? AND A.C_FILE_TYPE = ? ");
			pst = conn.prepareStatement(buffer.toString());
			pst.setString(index++, this.assCode);
			pst.setString(index++, this.c_rpt_type);
			pst.setString(index++, this.startDate);
			pst.setString(index++, this.endDate);
			pst.setString(index++, this.C_FILE_TYPE);
			pst.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	/**
	 * 插入科目表T_D_ER_KM预处理sql语句
	 * @return
	 */
	protected String getInsertSql(){
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO T_D_ER_ZCFZ ");
		builder.append("(C_IDEN, C_SN, C_FILE_TYPE, C_RPT_TYPE, C_ASS_CODE, D_START_DATE, "); 
		builder.append("  D_END_DATE, C_INDEX_CODE,C_INDEX_NAME, N_BEGIN_VALUE, N_END_VALUE)"); 
//		builder.append(" VALUES(SEQU_D_ER_ZCFZ.NEXTVAL,'").append(this.fsn).append("','1701','").append(c_rpt_type);
//		builder.append("','").append(this.assCode);
//		builder.append("','").append(this.startDate);
//		builder.append("','").append(this.endDate).append("',");
		builder.append(" VALUES(SEQU_D_ER_ZCFZ.NEXTVAL,?,?,?,?,?,?,");
		builder.append("?,?,?,?)");
		return builder.toString();
		
	}
	/**
	 * BUG197123减少不必要的SQL硬解析比率，优化数据库执行效率
	 */
	@Override
	protected int setPojoHead(PreparedStatement pst, ResultSet rs, int dataType)
			throws SQLException {
		int index = 1;
		pst.setString(index++, this.fsn);//报文序号
		pst.setString(index++, C_FILE_TYPE);//文件类型
		pst.setString(index++, c_rpt_type);//报表类型
		pst.setString(index++, this.assCode);//资产代码
		pst.setString(index++, this.startDate);//开始日期
		pst.setString(index++, this.endDate);//结束日期
		return index;
	}
	/**
	 * 明细项生成
	 * @return
	 * @throws Exception
	 */
	protected int geneZCFZData() throws Exception {
		PreparedStatement pst = null;
		PreparedStatement insertPst = null;
		ResultSet rs = null;
		int count = 0;
		String dataSql = "";
		try {
			insertPst = conn.prepareStatement(getInsertSql());
			if(reportManager.isCustomCol())
			{
				if(!reportManager.checkCustomCol())
				{
					throw new Exception(reportManager.getErrMessage());
				}
				dataSql = getZCFZCustomColSql(reportManager.getReortColIndexByElecColIndex());
			}else
			{
				dataSql = getZCFZSql();
			}
			pst = conn.prepareStatement(dataSql);
			int index = 1;
			pst.setString(index++, reportCode);//STORY53570嘉实基金-电子对账-月报 产品分类配置
			pst.setString(index++, reportCode);//STORY53570嘉实基金-电子对账-月报 产品分类配置
			pst.setString(index++, reportCode);
			pst.setString(index++, portCode);
			pst.setString(index++, reportTime);
			pst.setString(index++, transferReportType(c_rpt_type));
			pst.setString(index++, C_FILE_TYPE);
			
			pst.setString(index++, reportCode);//STORY53570嘉实基金-电子对账-月报 产品分类配置
			pst.setString(index++, reportCode);//STORY53570嘉实基金-电子对账-月报 产品分类配置
			pst.setString(index++, reportCode);
			pst.setString(index++, portCode);
			pst.setString(index++, reportTime);
			pst.setString(index++, transferReportType(c_rpt_type));
			pst.setString(index++, C_FILE_TYPE);
			rs = pst.executeQuery();
			HashMap<String, ElecPerRela> elecPerRelaMap = getPerRelaByPortAndDZCode(portCode, C_FILE_TYPE);
			while (rs.next()) {
				boolean isAdd = setPojo(insertPst, rs, 0,elecPerRelaMap);
				if(isAdd)
				{
					insertPst.addBatch();
					if(++count % batchSize == 0) {
						insertPst.executeBatch();
					}
				}
			}
			insertPst.executeBatch();
			insertPst.close();
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, insertPst);
		}
		return count;
	}
	
	/**
	 * 数据的数据源sql
	 * @return
	 */
	protected String getZCFZSql(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(1);
		return getZCFZCustomColSql(list);
	}
	
	/**
	 * 数据的数据源sql
	 * @return
	 */
	protected String getZCFZCustomColSql(List<Integer> cols){
		StringBuilder builder = new StringBuilder();

		builder.append("select a.c_zb_code,a.c_zb_name, a.beginvalue, b.endvalue, a.n_row_num from\n");
		builder.append("(select a.c_zb_code,a.c_zb_name,nvl(b.c_data,'0') as beginvalue,b.n_row_num, b.n_col_num,b.c_cell_content\n"); 
		//STORY53570嘉实基金-电子对账-月报 产品分类配置
		//builder.append("  from t_z_bi_rela a right join(\n"); 
		builder.append("  from ( \n"); 
		builder.append(" SELECT C_IDEN,C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_PORT_CODE_CLS,C_DV_ZB_CODE,C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME, ");
		builder.append(" C_ORG_CODE,C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL,C_DV_PORT_CLS,C_REPORT_CODE,C_ROW ");
		builder.append("  FROM T_Z_BI_RELA a where a.c_report_code  = ? and a.n_check_state = 1  \n");
		builder.append("  union all  \n");
		builder.append(" SELECT C_IDEN,C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_PORT_CODE_CLS,C_DV_ZB_CODE,C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME, ");
		builder.append(" C_ORG_CODE,C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL,C_DV_PORT_CLS,C_REPORT_CODE,C_ROW ");
		builder.append("  FROM T_Z_BI_RELA b where  trim(b.c_report_code)  is null and b.n_check_state = 1 and not exists (SELECT 1 FROM T_Z_BI_RELA a where a.c_report_code  = ? and a.c_zb_code = b.c_zb_code and a.c_dz_code = b.c_dz_code and a.n_check_state = 1)) a right join( \n");
		builder.append("         select a.c_data, a.n_row_num, a.n_col_num, b.c_cell_content\n"); 
		builder.append("           from t_f_rc_rep_tbdata a\n"); 
		builder.append("           join (select a.c_report_code, a.n_row_num, a.n_col_num,\n"); 
		builder.append("                        a.c_cell_content\n"); 
		builder.append("                   from t_f_rc_rep_tbcell a\n"); 
		builder.append("                   JOIN (select c_report_code\n"); 
		builder.append("                          from T_F_RC_REP_TPL T\n"); 
		builder.append("                         where T.c_report_code = ?) b\n"); 
		builder.append("                     on a.c_report_code = b.c_report_code) b\n"); 
		builder.append("             on a.c_report_code = b.c_report_code\n"); 
		builder.append("            and a.n_row_num = b.n_row_num\n"); //第一列
		builder.append("            and a.n_col_num = b.n_col_num + ").append(cols.get(0)).append("\n");
		builder.append("          where a.c_port_code = ?\n"); 
		builder.append("            and a.c_report_time = ?\n"); 
		builder.append("            and a.c_report_type = ? )b\n"); 
		builder.append("          on a.c_zb_name = trim(b.c_cell_content)\n"); 
		builder.append("         AND (a.c_row = (b.n_row_num +1) OR TRIM(A.C_ROW) IS NULL) ");
		builder.append("          where a.c_dz_code = ? and a.n_check_state = 1)a\n"); 
		builder.append("join\n"); 
		builder.append("(select a.c_zb_code,nvl(b.c_data,'0') as endvalue,b.n_row_num, b.n_col_num,b.c_cell_content\n"); 
		//STORY53570嘉实基金-电子对账-月报 产品分类配置
		//builder.append("  from t_z_bi_rela a right join(\n"); 
		builder.append("  from ( \n"); 
		builder.append(" SELECT C_IDEN,C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_PORT_CODE_CLS,C_DV_ZB_CODE,C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME, ");
		builder.append(" C_ORG_CODE,C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL,C_DV_PORT_CLS,C_REPORT_CODE,C_ROW ");
		builder.append("  FROM T_Z_BI_RELA a where a.c_report_code  = ? and a.n_check_state = 1  \n");
		builder.append("  union all  \n");
		builder.append(" SELECT C_IDEN,C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_PORT_CODE_CLS,C_DV_ZB_CODE,C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME, ");
		builder.append(" C_ORG_CODE,C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL,C_DV_PORT_CLS,C_REPORT_CODE,C_ROW ");
		builder.append("  FROM T_Z_BI_RELA b where  trim(b.c_report_code)  is null and b.n_check_state = 1 and not exists (SELECT 1 FROM T_Z_BI_RELA a where a.c_report_code  = ? and a.c_zb_code = b.c_zb_code and a.c_dz_code = b.c_dz_code and a.n_check_state = 1)) a right join( \n");
		builder.append("         select a.c_data, a.n_row_num, a.n_col_num, b.c_cell_content\n"); 
		builder.append("           from t_f_rc_rep_tbdata a\n"); 
		builder.append("           join (select a.c_report_code, a.n_row_num, a.n_col_num,\n"); 
		builder.append("                        a.c_cell_content\n"); 
		builder.append("                   from t_f_rc_rep_tbcell a\n"); 
		builder.append("                   JOIN (select c_report_code\n"); 
		builder.append("                          from T_F_RC_REP_TPL T\n"); 
		builder.append("                         where T.c_report_code = ?) b\n"); 
		builder.append("                     on a.c_report_code = b.c_report_code) b\n"); 
		builder.append("             on a.c_report_code = b.c_report_code\n"); 
		builder.append("            and a.n_row_num = b.n_row_num\n"); //第二列
		builder.append("            and a.n_col_num = b.n_col_num + ").append(cols.get(1)).append("\n"); 
		builder.append("          where a.c_port_code = ?\n"); 
		builder.append("            and a.c_report_time = ?\n"); 
		builder.append("            and a.c_report_type = ?)b\n"); 
		builder.append("          on a.c_zb_name = trim(b.c_cell_content)\n"); 
		builder.append("         AND (a.c_row = (b.n_row_num +1) OR TRIM(A.C_ROW) IS NULL) ");
		builder.append("          where a.c_dz_code = ? and a.n_check_state = 1)b\n"); 
		builder.append("  on a.c_zb_code = b.c_zb_code AND A.c_cell_content = B.c_cell_content AND a.n_row_num = B.n_row_num  order by a.n_row_num,b.n_row_num,a.c_zb_code");

		return builder.toString();
	}
	/**
	 * 
	 *C_INDEX_CODE,C_INDEX_NAME, N_BEGIN_VALUE, N_END_VALUE
	 * @param pst
	 * @param rs
	 * @throws SQLException
	 */
	protected boolean setPojo(PreparedStatement pst, ResultSet rs, int dataType,HashMap<String, ElecPerRela> elecPerRelaMap) throws SQLException {
		boolean isAdd = true;
		int index = setPojoHead(pst, rs, dataType);
//		ElecPerRela perRelaPojo = this.getPerRelaByCode(rs.getString("C_ZB_CODE"));
		ElecPerRela perRelaPojo = elecPerRelaMap.get(rs.getString("C_ZB_CODE"))==null ? new ElecPerRela() : elecPerRelaMap.get(rs.getString("C_ZB_CODE"));
		if (perRelaPojo != null && "0".equalsIgnoreCase(perRelaPojo.getC_SEND_MODE())) {
			return false;
		}
		switch (dataType) {
		default:
			pst.setString(index++, rs.getString(1));
			pst.setString(index++, rs.getString(2));
			if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE1())||
					"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE1()))) {
				pst.setDouble(index++, 0); //指标发送模式为不发送或者0值发送 置0
			}else{
				pst.setDouble(index++, rs.getDouble(3));
			}
			if (perRelaPojo != null && ("NOSEND".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE2())||
					"SEND0".equalsIgnoreCase(perRelaPojo.getC_ZB_VALUE2()))) {
				pst.setDouble(index++, 0); //指标发送模式为不发送或者0值发送 置0
			}else{
				pst.setDouble(index++, rs.getDouble(4));
			}
//			pst.setDouble(index++, rs.getDouble(2));
//			pst.setDouble(index++, rs.getDouble(3));
			break;
		}
		return isAdd;
	}
}
