package com.yss.ams.base.information.support.bi.account.cache;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.service.IFundAccDataService;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;

public class BaseFundAccCache extends BaseCache<FundAcc>{
	private IFundAccDataService fundAccDataService = null;
	
	@Override
	protected void loadData() {
		fundAccDataService = YssServiceFactory.getInstance().createService(IFundAccDataService.class);
		CacheData data = fundAccDataService.updateByTimestamp(getTimestamp());
		this.setTimestamp(data.getTimestamp());
		List<FundAcc> lstFundAcc = this.cacheData2List(data.getDataList(), FundAcc.class);
		for (FundAcc fundAcc : lstFundAcc) {
			if(fundAcc.getAuditState() == 0)
			{
				continue;
			}
			String key = fundAcc.getC_PORT_CODE() +"#" +fundAcc.getC_OPEN_ACC_NO()+"#"+fundAcc.getC_OPEN_ACC_NAME()+"#"+fundAcc.getC_OPEN_ADDR() + "#" + fundAcc.getC_DC_CODE();
			this.mapT.put(key, fundAcc);
			keyMap.put(key, fundAcc.getId());
			idMap.put(fundAcc.getId(), fundAcc);
		}
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.FUNDACC;
	}

	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IFundAccDataService.class.getSimpleName());
		return list;
	}

	@Override
	public List<FundAcc> getCacheList() {
		List<FundAcc> list = new ArrayList<FundAcc>();
		rwl.readLock();
		try {
			list = this.mapT.getAllValues();
		} finally {
			rwl.readUnLock();
		}

		return list;
	}

	@Override
	public FundAcc getCacheByKey(String key) {
		return this.mapT.get(key);
	}
	
	@Override
	protected void loadDataByIds(String ids) {
		fundAccDataService = YssServiceFactory.getInstance().createService(IFundAccDataService.class);
		List<FundAcc> lstFundAcc = fundAccDataService.getDataListByIds(ids);
		// BUG #320790 【中银基金】【300.70228】账户信息维护修改后在综合指令界面新增指令时能查询到修改前的账户
		for (FundAcc fundAcc : lstFundAcc) {
			String accKey = fundAcc.getC_PORT_CODE() +"#" +fundAcc.getC_OPEN_ACC_NO()+"#"+fundAcc.getC_OPEN_ACC_NAME()+"#"+fundAcc.getC_OPEN_ADDR() + "#" + fundAcc.getC_DC_CODE();
			String acckey2 = "#" +fundAcc.getC_OPEN_ACC_NO()+"#"+fundAcc.getC_OPEN_ACC_NAME()+"#"+fundAcc.getC_OPEN_ADDR() + "#" + fundAcc.getC_DC_CODE();
			for(String key : mapT.getAllKeys()){
				boolean flag = false;
				if(accKey.equals(key)){
					flag = true;
				}else if(key.contains(acckey2)){
					// 公共账户保存组合关联关系的时候，缓存没有删除
					// 删除组合关联关系的时候，缓存没有删除
					flag = true;
				}else{
					// 账户基本信息修改过的，比如账号、账户名称、开户行、币种
					String id = keyMap.get(key);	// 账户id
					if(StringUtil.IsNullOrEmptyT(id)){
						continue;
					}
					
					if(id.equals(fundAcc.getId())){
						flag = true;
					}
				}
				
				if(flag){
					this.mapT.remove(key);
					this.keyMap.remove(key);
				}
			}
		}
		
		for (FundAcc fundAcc : lstFundAcc) {
			if(fundAcc.getAuditState() == 0)
			{
				continue;
			}
			String key = fundAcc.getC_PORT_CODE() +"#" +fundAcc.getC_OPEN_ACC_NO()+"#"+fundAcc.getC_OPEN_ACC_NAME()+"#"+fundAcc.getC_OPEN_ADDR() + "#" + fundAcc.getC_DC_CODE();
			this.mapT.put(key, fundAcc);
			keyMap.put(key, fundAcc.getId());
			idMap.put(fundAcc.getId(), fundAcc);
		}
		
	}

	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		FundAcc fundAcc = (FundAcc)pojo;
		return fundAcc.getC_PORT_CODE() +"#" +fundAcc.getC_OPEN_ACC_NO()+"#"+fundAcc.getC_OPEN_ACC_NAME()+"#"+fundAcc.getC_OPEN_ADDR() + "#" + fundAcc.getC_DC_CODE();
	}
}
