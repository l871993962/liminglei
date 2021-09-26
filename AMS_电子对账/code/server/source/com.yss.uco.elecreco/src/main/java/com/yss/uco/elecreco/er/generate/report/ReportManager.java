package com.yss.uco.elecreco.er.generate.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import com.yss.framework.api.service.YssServiceFactory;
import com.yss.uco.elecreco.er.repcolcfg.pojo.DzRepColCfg;
import com.yss.uco.elecreco.er.repcolcfg.service.IDzRepColCfgService;
/**
 * 电子对账生成时，报表管理类，负责报表的个性设置
 * @author Lenovo
 *STORY59739【易方达基金】【紧急加急】财务报表电子对账生成应该同财务报表配置报错一致
 */
public class ReportManager {
	
	public final static int LRB_COL_SIZE = 2;
	public final static int ZCFZB_COL_SIZE = 2;
	public final static int ZCFZB_XZZ_COL_SIZE = 2;
	public final static int JYYJB_XZZ_COL_SIZE = 2;
	public final static int SYZQYB_COL_SIZE = 6;
	public final static int JZCBDB_COL_SIZE = 2;
	private int colSize = 0;
	private String dzType;
	private String reportCode;
	private List<DzRepColCfg> list = null;
	private String errMessage = "";
	public String getDzType() {
		return dzType;
	}

	public void setDzType(String dzType) {
		this.dzType = dzType;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public ReportManager(String dzType,String reportCode)
	{
		this.dzType = dzType;
		this.reportCode = reportCode;
		this.list = this.initRepColCfg(dzType,reportCode);
		this.colSize = this.initColSize(dzType);
	}

	private int initColSize(String dzType) {
		if(dzType.equalsIgnoreCase("1701"))
		{
			return ZCFZB_COL_SIZE;
		}
		if(dzType.equalsIgnoreCase("1801"))
		{
			return LRB_COL_SIZE;
		}
		if(dzType.equalsIgnoreCase("1901"))
		{
			return SYZQYB_COL_SIZE;
		}
		if(dzType.equalsIgnoreCase("1903"))
		{
			return JZCBDB_COL_SIZE;
		}
		if(dzType.equalsIgnoreCase("1711"))
		{
			return ZCFZB_XZZ_COL_SIZE;
		}
		if(dzType.equalsIgnoreCase("1811"))
		{
			return JYYJB_XZZ_COL_SIZE;
		}
		return 0;
	}

	private List<DzRepColCfg> initRepColCfg(String dzType, String reportCode) {
		IDzRepColCfgService service = YssServiceFactory.getInstance().createService(IDzRepColCfgService.class);
		List<DzRepColCfg> list = service.getDzRepColCfgs(dzType, reportCode);
		if(list == null)
		{
			list = new ArrayList<DzRepColCfg>();
		}
		return list;
	}
	
	public boolean isCustomCol()
	{
		if(this.list != null && list.size() > 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean checkCustomCol()
	{
		if(list.size() != this.colSize)
		{
			this.errMessage += "财务报表个性列设置的条数不正确！";
			return false;
		}
		Set<String> set = new HashSet<String>();
		for(DzRepColCfg col : list)
		{
			if(set.contains(col.getN_REPORT_COL()) && 0 != Integer.parseInt(col.getN_REPORT_COL()))
			{
				this.errMessage += "财务报表个性列设置的偏移量不正确！";
				return false;
			}else
			{
				set.add(col.getN_REPORT_COL());
			}
		}
		return true;
	}
	/**
	 * 根据电子对账生成列顺序获取报表的偏移量
	 * @return
	 */
	public List<Integer> getReortColIndexByElecColIndex()
	{
		List<Integer> repCols = new ArrayList<Integer>();
		TreeMap<String, DzRepColCfg> map = sortDzRepColCfg(list);
		for(String s : map.keySet())
		{
			DzRepColCfg col = map.get(s);
			repCols.add(Integer.parseInt(col.getN_REPORT_COL()));
		}
		return repCols;
		
	}
	/**
	 * 按照电子对账列顺序排序列设置
	 * @return
	 */
	protected TreeMap<String,DzRepColCfg> sortDzRepColCfg(List<DzRepColCfg> list)
	{
		TreeMap<String,DzRepColCfg> result = new TreeMap<String,DzRepColCfg>();
		for(DzRepColCfg col : list)
		{
			result.put(col.getC_ELEC_COL(), col);
		}
		return result;
	}
}
