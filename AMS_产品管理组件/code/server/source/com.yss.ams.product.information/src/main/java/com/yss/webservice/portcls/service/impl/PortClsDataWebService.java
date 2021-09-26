package com.yss.webservice.portcls.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.product.information.modules.aa.portcls.service.impl.PortClsDataService;
import com.yss.ams.product.information.modules.aa.portcls.service.impl.PortClsService;
import com.yss.ams.product.information.support.cache.PortClsCache;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheOper;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.mvc.controls.ControlAssistance;
import com.yss.right.pojo.RightResult;
import com.yss.webservice.portcls.IPortClsDataWebService;
import com.yss.webservice.util.PojoUtil;

public class PortClsDataWebService implements IPortClsDataWebService {

	private Logger logger = LogManager.getLogger(this.getClass());
	
    /** 
     * @Title: create
     * @Desc: 
     * @param portCls
     * @return 
     * @see com.yss.webservice.portcls.IPortClsDataWebService#create(com.yss.webservice.portcls.PortCls) 
     */
    @Override
    public RightResult<PortCls> create(PortCls portCls) {
        RightResult<PortCls> result = new RightResult<PortCls>();
        try {
            
            PortClsDataService userService = new PortClsDataService();
            PojoUtil.resetOperatorAndDate(portCls, "WEBSERVICE");
            userService.insert(portCls);
            result.setStatus("1");
            PortClsCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORTCLS);
            portCache.update(new CacheRefreshInfo());
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setStatus("0");
            result.setDetail(e.getMessage());
        }
        return result;
    }

    /** 
     * @Title: update
     * @Desc: 
     * @param PortCls
     * @return 
     * @see com.yss.webservice.portcls.IPortClsDataWebService#update(com.yss.webservice.portcls.PortCls) 
     */
    @Override
    public RightResult<PortCls> update(PortCls portCls) {
        RightResult<PortCls> result = new RightResult<PortCls>();
        try {
            PortClsDataService userService = new PortClsDataService();
            PojoUtil.resetOperatorAndDate(portCls, "WEBSERVICE");
            userService.updateById(portCls);
            result.setStatus("1");
            PortClsCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORTCLS);
            portCache.update(new CacheRefreshInfo());
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setStatus("0");
            result.setDetail(e.getMessage());
        }
        return result;
    }

    /** 
     * @Title: delete
     * @Desc: 
     * @param portCode
     * @param classPort
     * @return 
     * @see com.yss.webservice.portcls.IPortClsDataWebService#delete(java.lang.String, java.lang.String) 
     */
    @Override
    public RightResult<PortCls> delete(String portCode, String classPort) {
        RightResult<PortCls> result = new RightResult<PortCls>();
        try {
            PortClsDataService portClsDataService = new PortClsDataService();
            PortCls port = portClsDataService.getPojoByCode(portCode + "/t" + classPort);
            if (port != null) {
                portClsDataService.deleteById(port);
                result.setStatus("1");
                
                PortClsCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORTCLS);
                CacheRefreshInfo refreshInfo = new CacheRefreshInfo();
                List<String> idlists = new ArrayList<String>();
                idlists.add(port.getId());
                refreshInfo.setIdList(idlists);
                refreshInfo.setOper(CacheOper.DEL);
                portCache.update(refreshInfo);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setStatus("0");
            result.setDetail(e.getMessage());
        }
        return result;
    }

    /** 
     * @Title: query
     * @Desc: 
     * @param portCode
     * @param classPort
     * @return 
     * @see com.yss.webservice.portcls.IPortClsDataWebService#query(java.lang.String, java.lang.String) 
     */
    @Override
    public RightResult<PortCls> query(String portCode, String classPort) {
        RightResult<PortCls> result = new RightResult<PortCls>();
        try {
            PortClsDataService portClsDataService = new PortClsDataService();
            PortCls port = portClsDataService.getDataByCode(portCode + "/t" +classPort);
            List<PortCls> datas = new ArrayList<PortCls>();
            if (port != null) {
                datas.add(port);
            }
            result.setStatus("1");
            result.setDatas(datas);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setStatus("0");
            result.setDetail(e.getMessage());
        }

        return result;
    }
    
    /** 
     * @Title: queryAll
     * @Desc: 
     * @return 
     * @see com.yss.webservice.port.IPortDataWebService#queryAll() 
     */
    @Override
    public RightResult<PortCls> queryAll() {
        RightResult<PortCls> result = new RightResult<PortCls>();
        PortClsDataService portClsDataService = new PortClsDataService();
        try {
            List<PortCls> ports = portClsDataService.getDataList();
            List<PortCls> portList = new ArrayList<PortCls>();
            for (PortCls port : ports) {
                portList.add((PortCls) port);
            }
            result.setDatas(portList);
            result.setStatus("1");
            
        } catch (Exception e) {
            logger.error(e.getMessage());
            result.setStatus("0");
            result.setDetail(e.getMessage());
        }
        return result;
    }
}
