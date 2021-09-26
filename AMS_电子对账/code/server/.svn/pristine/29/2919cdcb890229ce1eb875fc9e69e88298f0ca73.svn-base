package com.yss.uco.elecreco.er.reverse.compare.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.uco.elecreco.er.generate.util.KMTransUtil;
import com.yss.uco.elecreco.er.reverse.compare.service.impl.DataCompareService;
import com.yss.uco.elecreco.er.reverse.cons.ReveDzCons;
import com.yss.uco.elecreco.er.reverse.manager.ignore.pojo.IgnoreItem;
import com.yss.uco.elecreco.er.reverse.manager.ignore.service.IIgnoreItemService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;

public class IgnoreManager {
	private DataCompareService dataCompareService = null;
	
	private Map<String,IgnoreItem> bfRows = new HashMap<String,IgnoreItem>();
	/**
	 * 第一key:托管行代码 第二层key:忽略标志
	 */
	private Map<String, Map<String,IgnoreItem>> dfRows = new HashMap<String, Map<String,IgnoreItem>>();
	private Map<String,IgnoreItem> bfCols = new HashMap<String,IgnoreItem>();
	/**
	 * 第一key:托管行代码 第二层key:忽略标志
	 */
	private Map<String, Map<String,IgnoreItem>> dfCols = new HashMap<String, Map<String,IgnoreItem>>();
	private Map<String,IgnoreItem> bfCells = new HashMap<String,IgnoreItem>();
	/**
	 * 第一key:托管行代码 第二层key:忽略标志
	 */
	private Map<String, Map<String,IgnoreItem>> dfCells = new HashMap<String, Map<String,IgnoreItem>>();
	public IgnoreManager(DataCompareService dataCompareService)
	{
		this.dataCompareService = dataCompareService;
	}
	/**
	 * 适用下级的忽略设置(本方)
	 * key:行标识
	 */
	private Map<String,IgnoreItem> bfSubSuits = new HashMap<String,IgnoreItem>();
	/**
	 * 适用下级的忽略设置（对方）
	 * 第一key:托管行代码 第二层key:忽略标志
	 * key:行标识
	 */
	private Map<String, Map<String,IgnoreItem>> dfSubSuits = new HashMap<String, Map<String,IgnoreItem>>();
	
	public void initIgnoreItems(String portCode, String fileType,
			String tghCode) throws Exception {
		Map<String,IgnoreItem> dfRow = new HashMap<String, IgnoreItem>();
		Map<String,IgnoreItem> dfCol = new HashMap<String, IgnoreItem>();
		Map<String, IgnoreItem> dfCell = new HashMap<String, IgnoreItem>();
		Map<String, IgnoreItem> dfSubSuit = new HashMap<String, IgnoreItem>();
		this.dfRows.put(tghCode, dfRow);
		this.dfCols.put(tghCode, dfCol);
		this.dfCells.put(tghCode, dfCell);
		this.dfSubSuits.put(tghCode, dfSubSuit);
		IIgnoreItemService ignoreItemService = YssServiceFactory.getInstance().createService(IIgnoreItemService.class);

		List<IgnoreItem> dataList = ignoreItemService.getCompareIgnoreItem(portCode, tghCode, fileType);
		if(dataList == null)
		{
			return;
		}
		IgnoreItem item = null;
		String ignoreType = "";
		String scope = "";
		for(BasePojo pojo : dataList)
		{
			item = (IgnoreItem) pojo;
			ignoreType = item.getC_DV_IGNORE_TYPE();
			scope = item.getC_DV_IGNORE_SCOPE();
			if(ReveDzCons.IGNORE_TYPE_HL_ROW.equals(ignoreType))//行忽略
			{
				if(ReveDzCons.IGNORE_SCOPE_INNER.equals(scope))
				{
					String flag = item.getC_ROW_FLAG();
					if(dataCompareService.isTransKM())
					{
						item.setC_ROW_FLAG(KMTransUtil.transKMCode(flag, dataCompareService.getTransMap()));
					}
					//替换本方科目代码后面的后缀
					if(dataCompareService.isTransIsin() && dataCompareService.haveSecCode(flag))
					{
						int i = flag.lastIndexOf(".");
						String isinCode = dataCompareService.getISINCode(flag.substring(i+1));
						if(isinCode!= null && !StringUtil.IsNullOrEmptyT(isinCode))
						{
							flag = flag.substring(0, i+1)+isinCode;
							item.setC_ROW_FLAG(flag);
						}
					}
					this.bfRows.put(item.getC_ROW_FLAG(), item);
				}else if(ReveDzCons.IGNORE_SCOPE_OUT.equals(scope))
				{
					dfRow.put(item.getC_ROW_FLAG(), item);
				}
				
				if(ReveDzCons.IGNORE_SUB_SUIT_YES.equals(item.getC_DV_SUB_SUIT()))//应用于下级
				{
					if(ReveDzCons.IGNORE_SCOPE_OUT.equals(item.getC_DV_IGNORE_SCOPE()))
					{
						dfSubSuit.put(item.getC_ROW_FLAG(), item);
					}else if(ReveDzCons.IGNORE_SCOPE_INNER.equals(item.getC_DV_IGNORE_SCOPE()))
					{
						this.bfSubSuits.put(item.getC_ROW_FLAG(), item);
					}
					
				}
			}else if(ReveDzCons.IGNORE_TYPE_HL_COL.equals(ignoreType))//列忽略
			{
				if(ReveDzCons.IGNORE_SCOPE_INNER.equals(scope))
				{
					this.bfCols.put(item.getC_COL_FLAG(), item);
				}else if(ReveDzCons.IGNORE_SCOPE_OUT.equals(scope))
				{
					dfCol.put(item.getC_COL_FLAG(), item);
				}
			}else if(ReveDzCons.IGNORE_TYPE_HL_CELL.equals(ignoreType))//单元格忽略
			{
				
				if(ReveDzCons.IGNORE_SCOPE_INNER.equals(scope))
				{
					this.bfCells.put(item.getC_ROW_FLAG()+item.getC_COL_FLAG(), item);
				}else if(ReveDzCons.IGNORE_SCOPE_OUT.equals(scope))
				{
					dfCell.put(item.getC_ROW_FLAG()+item.getC_COL_FLAG(), item);
				}
			}
		}
	}
	
	public void initIgnoreItems(String portCode, String fileType,
			String[] tghCodes) throws Exception 
	{
		for (String tghCode : tghCodes) {
			initIgnoreItems(portCode, fileType, tghCode);
		}
	}
	
	public Map<String,IgnoreItem> getBfRowIgnoreItems()
	{
		Map<String, IgnoreItem> map = this.bfRows;
		if(map != null)
		{
			return map;
		}
		return new HashMap<String, IgnoreItem>();
	}
	
	public Map<String,IgnoreItem> getDfRowIgnoreItems(String tgh)
	{
		Map<String, IgnoreItem> map = this.dfRows.get(tgh);
		if(map != null)
		{
			return map;
		}
		return new HashMap<String, IgnoreItem>();
	}
	
	public Map<String,IgnoreItem> getBfColIgnoreItems()
	{
		Map<String, IgnoreItem> map = this.bfCols;
		if(map != null)
		{
			return map;
		}
		return new HashMap<String, IgnoreItem>();
	}
	
	public Map<String,IgnoreItem> getDfColIgnoreItems(String tgh)
	{
		Map<String, IgnoreItem> map = this.dfCols.get(tgh);
		if(map != null)
		{
			return map;
		}
		return new HashMap<String, IgnoreItem>();
	}
	
	public Map<String,IgnoreItem> getBfCellIgnoreItems()
	{
		Map<String, IgnoreItem> map = this.bfCells;
		if(map != null)
		{
			return map;
		}
		return new HashMap<String, IgnoreItem>();
	}
	
	public Map<String,IgnoreItem> getDfCellIgnoreItems(String tgh)
	{
		Map<String, IgnoreItem> map = this.dfCells.get(tgh);
		if(map != null)
		{
			return map;
		}
		return new HashMap<String, IgnoreItem>();
	}
	
	public Map<String,IgnoreItem> getBfSubSuitIgnoreItems()
	{
		Map<String, IgnoreItem> map = this.bfSubSuits;
		if(map != null)
		{
			return map;
		}
		return new HashMap<String, IgnoreItem>();
	}
	
	public Map<String,IgnoreItem> getDfSubSuitIgnoreItems(String tgh)
	{
		Map<String, IgnoreItem> map = this.dfSubSuits.get(tgh);
		if(map != null)
		{
			return map;
		}
		return new HashMap<String, IgnoreItem>();
	}
	
	/**
	 * 该行数据被忽略，父行被忽略并应用于下级，返回true
	 * @param kmCode
	 * @param tghCode
	 * @param ignoreScope
	 * @return
	 */
	public boolean isBfRowIgnore(String kmCode)
	{
		if(this.getBfRowIgnoreItems().containsKey(kmCode))
		{
			return true;
		}
		//父节点是否已被忽略，并被应用于下级
		if(isBfSubSuit(kmCode))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 该行数据被忽略，父行被忽略并应用于下级，返回true
	 * @param kmCode
	 * @param tghCode
	 * @param ignoreScope
	 * @return
	 */
	public boolean isDfRowIgnore(String rowFlag,String tghCode)
	{
		if(this.getDfRowIgnoreItems(tghCode).containsKey(rowFlag))
		{
			return true;
		}
		//父节点是否已被忽略，并被应用于下级
		if(isDfSubSuit(rowFlag,tghCode))
		{
			return true;
		}
		return false;
	}
	
	/***
	 * 该行数据被忽略，父行被忽略并应用于下级，返回true
	 * @param kmCode
	 * @param tghCode
	 * @param kmScope
	 * @return
	 */
	public boolean isBfSubSuit(String kmCode)
	{
		while(kmCode.contains("."))
		{
			int i = kmCode.lastIndexOf(".");
			kmCode = kmCode.substring(0, i);
			if(this.getBfSubSuitIgnoreItems().containsKey(kmCode))
			{
				return true;
			}
			
		}
		
		return false;
	}
	
	/***
	 * 该行数据被忽略，父行被忽略并应用于下级，返回true
	 * @param kmCode
	 * @param tghCode
	 * @param kmScope
	 * @return
	 */
	public boolean isDfSubSuit(String kmCode,String tghCode)
	{
		StringBuffer sb = new StringBuffer(kmCode);
		Set<String> keySet = this.getDfSubSuitIgnoreItems(tghCode).keySet();
		for(String s : keySet)
		{
			if(sb.toString().startsWith(s))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isBfColIgnore(String colFlag)
	{
		if(this.getBfColIgnoreItems().containsKey(colFlag))
		{
			return true;
		}
		return false;
	}
	
	public boolean isDfColIgnore(String colFlag,String tghCode)
	{
		if(this.getDfColIgnoreItems(tghCode).containsKey(colFlag))
		{
			return true;
		}
		return false;
	}
	
	public boolean isBfCellIgnore(String rowColFlag)
	{
		if(this.getBfCellIgnoreItems().containsKey(rowColFlag))
		{
			return true;
		}
		return false;
	}
	
	public boolean isDfCellIgnore(String rowColFlag,String tghCode)
	{
		if(this.getDfCellIgnoreItems(tghCode).containsKey(rowColFlag))
		{
			return true;
		}
		return false;
	}
}
