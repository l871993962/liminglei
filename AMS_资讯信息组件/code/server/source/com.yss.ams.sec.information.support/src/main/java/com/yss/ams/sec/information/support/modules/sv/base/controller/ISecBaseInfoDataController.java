package com.yss.ams.sec.information.support.modules.sv.base.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.sec.information.support.common.YssServiceIdConstant;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecShortPojo;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseInfoDataService;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetDataListByTypesAndDateVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetDataListByTypesVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetSecPortCodeVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.GetShortDataListVo;
import com.yss.ams.sec.information.support.modules.sv.base.vo.UpdateByTimestampPageVo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.CacheDataExtend;
import com.yss.framework.api.common.co.ShortDataListPackage;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.api.service.ServiceException;

/**
*
* @author neil
* @date 2020-09-10 15:18:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_SECINFO,
impl = "com.yss.ams.sec.information.modules.sv.base.service.impl.SecBaseInfoDataService",
interfaceClass = com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseInfoDataService.class,
productLine = ProductLine.SECINFO, serviceMapId = "ISecBaseInfoDataService", menuId = "sv_sec")
public interface ISecBaseInfoDataController extends IBaseController<ISecBaseInfoDataService> {


    @POST
    @Path("/updateByTimestamp")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public CacheData updateByTimestamp(String timestamp);

    @POST
    @Path("/queryByIds")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> queryByIds(String ids);

    @POST
    @Path("/getKeyConvertMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getKeyConvertMap();

    @POST
    @Path("/getKeyConvertMapByList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getKeyConvertMap(List<String> listKey);

    @POST
    @Path("/getDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataList();

    @POST
    @Path("/getDataListRes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<SecBase> getDataListRes();

    @POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase getDataByCode(String dataCode);

    @POST
    @Path("/getPojoByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase getPojoByCode(String pojoCode);

    @POST
    @Path("/getDataListByTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataListByTypes(String[] types);

    @POST
    @Path("/getQueryResByTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<SecBase> getQueryResByTypes(String[] types);

    @POST
    @Path("/getDataListByKeys")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataListByKeys(String[] keys);

    @POST
    @Path("/getQueryResByKeys")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<SecBase> getQueryResByKeys(String[] keys);

    @POST
    @Path("/getDataListByTypesAndMkt")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataListByTypesAndMkt(String[] types);

    @POST
    @Path("/getDataListByDaes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataListByDaes(String parameter);

    @POST
    @Path("/getSecBaseInfoDataBySecCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase getSecBaseInfoDataBySecCode(String cSecCode);

    @POST
    @Path("/getSecBaseInfoDataBySecCodeFromDb")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase getSecBaseInfoDataBySecCodeFromDb(String cSecCode);

    @POST
    @Path("/getSecCacheByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase getSecCacheByCode(String secCode);

    @POST
    @Path("/getDataList1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataList1();

    @POST
    @Path("/getSecByVarDur")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase getSecByVarDur(SecBase secBase);

    @POST
    @Path("/getDataListByTypes1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataListByTypes1(String[] types);

    @POST
    @Path("/getShortDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public ShortDataListPackage<SecShortPojo> getShortDataList(GetShortDataListVo getShortDataListVo) throws ServiceException;

    @POST
    @Path("/getDataListByTypesAndDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<SecBase> getDataListByTypesAndDate(GetDataListByTypesAndDateVo getDataListByTypesAndDateVo);

    @POST
    @Path("/getShortDataMap")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String, String> getShortDataMap() throws ServiceException;

    @POST
    @Path("/getAllIndexDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getAllIndexDataList() throws ServiceException;

    @POST
    @Path("/insert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void insert(List<SecBase> list);

    @POST
    @Path("/getRate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase getRate(SecBase secBase) throws ServiceException;

    @POST
    @Path("/getSec")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public SecBase getSec(SecBase secBase) throws ServiceException;

    @POST
    @Path("/getSecPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public SecBase getSecPortCode(GetSecPortCodeVo getSecPortCodeVo) throws ServiceException;

    @POST
    @Path("/dbjxSecs")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> dbjxSecs(List<String> secCodeList);

    @POST
    @Path("/getDataBySecMktCodeAndMktCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public SecBase getDataBySecMktCodeAndMktCode(@FormParam("secMktCode") String secMktCode,@FormParam("mktCode") String mktCode);

    @POST
    @Path("/updateByTimestampPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public CacheData updateByTimestampPage(UpdateByTimestampPageVo updateByTimestampPageVo);

    @POST
    @Path("/getUpdateByTimestampCount")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getUpdateByTimestampCount(String timestamp);

    @POST
    @Path("/getDataListBySjsszq")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataListBySjsszq(String[] types);

    @POST
    @Path("/getDataListByIndiv")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataListByIndiv() throws ServiceException;

    @POST
    @Path("/isExistsStock")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String isExistsStock(String secCode);

    @POST
    @Path("/getMktNo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getMktNo() throws ServiceException;

    @POST
    @Path("/updateByTimestampNew")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public CacheDataExtend updateByTimestampNew(String timestamp);

    @POST
    @Path("/isExistsAct")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String isExistsAct(String secCode);

    @POST
    @Path("/isExistsStk")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String isExistsStk(String secCode);

    @POST
    @Path("/updateByIds")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public CacheData updateByIds(String ids);

    @POST
    @Path("/setCurrUser")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void setCurrUser(String userCode);

    @POST
    @Path("/getDataListByTypes2")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<SecBase> getDataListByTypes(GetDataListByTypesVo vo);
    

	/**
	 * 传递客户端缓存codes字符串到后台，返回差异数据至前端缓存
	 * @param codes
	 * @return
	 */
    @POST
    @Path("/UpdateDifferent")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public List<String> UpdateDifferent(String codes);
	
	/**
	 * 根据codes获取证券信息（用于前台获取缓存）
	 */
    @POST
    @Path("/updateByCodes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	public CacheData updateByCodes(String codes);

}