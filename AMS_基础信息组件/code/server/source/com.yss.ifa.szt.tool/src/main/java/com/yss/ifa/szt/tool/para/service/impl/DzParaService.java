package com.yss.ifa.szt.tool.para.service.impl;

import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.license.pojo.AuthenticateResult;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.license.ErOrgAuthUtil;
import com.yss.ifa.szt.tool.para.dao.DzParaDao;
import com.yss.ifa.szt.tool.para.dao.DzParaSqlBuilder;
import com.yss.ifa.szt.tool.para.service.IDzParaService;
import com.yss.ifa.szt.tool.para.service.IElecParaService;
import com.yss.ifa.szt.tool.pojo.DzPara;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.util.SecretKeyUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;

public class DzParaService extends ServiceBus<DzParaService> implements
		IDzParaService,IElecParaService {
	private DzParaDao serviceDao = null;

	public DzParaService() throws Exception {
		serviceDao = new DzParaDao(DbPoolFactory.getInstance().getPool(),
				new DzParaSqlBuilder());
		dao = serviceDao;
	}

	public List<TransPojo> getInitMrApiData() {
		return serviceDao.getInitMrApiData();
	}

//
//	/**
//	 * 
//	 */
//	@Override
//	public List<BasePojo> getElecParaPojo(String orgCode) {
//		// TODO Auto-generated method stub
//		return this.serviceDao.getElecParaPojo(orgCode);
//	}
//
	
	/**
	 * 根据资产代码查询电子对账参数设置-资托互动
	 * add by chenyoucai 2018-9-13 STORY #30828 资管和托管互动
	 * @return
	 */
	public DzPara getParaByAssCode(String assCode) {
		List<DzPara> list = serviceDao.getParaByAssCode(assCode);
		if(list == null){
			return null;
		}
		
		if (list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}

	/**
	 * 将密钥加密
	 * @param encryptStr
	 * @param charSet
	 * @param key
	 * @return 加密后的密钥
	 * @throws Exception 
	 */
	public String encryptData(String encryptStr) throws Exception  {
		if(StringUtil.IsNullOrEmptyT(encryptStr))
		{
			return encryptStr;
		}
		return SecretKeyUtil.encryptSecretKey(encryptStr);
	}
	
	/**
	 * 将密钥解密
	 * @param encryptStr
	 * @param charSet
	 * @param key
	 * @return 解密后的密钥
	 * @throws Exception 
	 */
	public String decryptData(String encryptStr) throws Exception  {
		if(StringUtil.IsNullOrEmptyT(encryptStr))
		{
			return encryptStr;
		}
		return SecretKeyUtil.decryptSecretKey(encryptStr);
	}

	/**
	 * 先验证新增的数据是否已申请许可，如果未申请许可，新增失败，给出提示
	 */
	@Override
	public String insert(List<BasePojo> pojoList) {
		int count = getUseCount();//已经设置的数量
		if(pojoList != null)
		{
			for (BasePojo basePojo : pojoList) {
				DzPara para = (DzPara) basePojo;
				if(!"BUSI_DZ".equalsIgnoreCase(para.getC_BUS_TYPE()))
				{
					continue;
				}
				
				//一个机构许可只能使用一次
				if(isUsedLicOrg(para.getC_BUS_TYPE(), para.getC_DV_LICORG(),""))
				{
					return ReturnInfoGenerator.getDataCheckFaultStr(this.menuId, "许可信息验证失败：该许可信息已经被使用！");
				}
				
				//验证许可
				AuthenticateResult result = ErOrgAuthUtil.authDzPara(para, count, 1);
				if(!result.isAuthorized())
				{
					return ReturnInfoGenerator.getDataCheckFaultStr(this.menuId, result.getFaildMsg());
				}
				count++;
			}
		}
		
		return super.insert(pojoList);
	}
	
	/**
	 * 获取电子对账参数设置业务类型为电子对账的数量
	 * @return
	 */
	public int getUseCount()
	{
		return serviceDao.getUseCount();
	}
	
	/**
	 * @param busType 业务类型
	 * @param licOrg 许可码
	 * @param id 数据ID
	 * @return 该许可码是否已经被使用
	 */
	public boolean isUsedLicOrg(String busType,String licOrg,String id)
	{
		return serviceDao.isUsedLicOrg(busType,licOrg,id);
	}
	
	/**
	 * 根据ID获取参数的业务类型
	 * @param para
	 * @return
	 */
	public String getBusiTypeById(String id)
	{
		return serviceDao.getBusiTypeById(id);
	}

	/**
	 * 先验证修改的数据是否已申请许可，如果未申请许可，修改失败，给出提示
	 */
	@Override
	public String updateById(List<BasePojo> pojoList) {
		int count = getUseCount();
		if(pojoList != null)
		{
			for (BasePojo basePojo : pojoList) {
				DzPara para = (DzPara) basePojo;
				//非电子对账业务不做限制
				if(!"BUSI_DZ".equalsIgnoreCase(para.getC_BUS_TYPE()))
				{
					continue;
				}
				//1.没有升级许可的所有功能都能使用 2.升级许可的只有许可的机构可以使用
//				//旧版本历史数据不做限制
//				if("OLD_LIC".equalsIgnoreCase(para.getC_DV_LICORG()))
//				{
//					continue;
//				}
				
				//一个机构许可只能使用一次
				if(isUsedLicOrg(para.getC_BUS_TYPE(), para.getC_DV_LICORG(),para.getId()))
				{
					return ReturnInfoGenerator.getDataCheckFaultStr(this.menuId, "许可信息验证失败：该许可信息已经被使用！");
				}
				

				//如果是由别的业务类型修改为电子对账，则已使用数量+1
				if(!"BUSI_DZ".equals(getBusiTypeById(para.getId())))
				{
					count++;
				}
				
				AuthenticateResult authDzParas = ErOrgAuthUtil.authDzPara(para, count, 2);
				if(!authDzParas.isAuthorized())
				{
					return ReturnInfoGenerator.getDataCheckFaultStr(this.menuId, authDzParas.getFaildMsg());
				}
				
			}
		}
		return super.updateById(pojoList);
	}
	
}
