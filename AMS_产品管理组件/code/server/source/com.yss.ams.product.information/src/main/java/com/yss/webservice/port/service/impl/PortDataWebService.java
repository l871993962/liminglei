package com.yss.webservice.port.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.product.information.modules.ab.port.service.impl.PortDataService;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.cache.PortClsCache;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheOper;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.right.pojo.RightResult;
import com.yss.webservice.port.IPortDataWebService;
import com.yss.webservice.util.PojoUtil;

/**
 * @ClassName: PortDataWebService
 * @Desc: TODO
 * @author: zhouxudong
 * @date: 2019年1月17日 下午1:27:27
 * @version 1.0
 */
public class PortDataWebService implements IPortDataWebService {

	private Logger logger = LogManager.getLogger(this.getClass());

    /** 
     * @Title: create
     * @Desc: 
     * @param port
     * @return 
     * @see com.yss.webservice.port.service.IPortDataService#create(com.yss.ams.product.information.support.modules.ab.port.pojo.Port) 
     */
    @Override
    public RightResult<Port> create(Port port) {
        RightResult<Port> result = new RightResult<Port>();
        try {
            PortDataService portService = new PortDataService();
            PojoUtil.resetOperatorAndDate(port, "WEBSERVICE");
            portService.insert(port);
            result.setStatus("1");
            PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
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
     * @param port
     * @return 
     * @see com.yss.webservice.port.service.IPortDataService#update(com.yss.ams.product.information.support.modules.ab.port.pojo.Port) 
     */
    @Override
    public RightResult<Port> update(Port port) {
        RightResult<Port> result = new RightResult<Port>();
        try {
            PortDataService userService = new PortDataService();
            PojoUtil.resetOperatorAndDate(port, "WEBSERVICE");
            userService.updateById(port);
            result.setStatus("1");
            PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
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
     * @return 
     * @see com.yss.webservice.port.IPortDataWebService#delete(java.lang.String) 
     */
    @Override
    public RightResult<Port> delete(String portCode) {
        RightResult<Port> result = new RightResult<Port>();
        try {
            PortDataService portDataService = new PortDataService();
            Port port = (Port)portDataService.getDataByCode(portCode);
            if (port != null) {
                portDataService.deleteById(port);
                result.setStatus("1");
                PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
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
     * @return 
     * @see com.yss.webservice.port.IPortDataWebService#query(java.lang.String) 
     */
    @Override
    public RightResult<Port> query(String portCode) {
        RightResult<Port> result = new RightResult<Port>();
        try {
            IPortDataService portDataService = new PortDataService();
            Port port = (Port)portDataService.getDataByCode(portCode);
            List<Port> datas = new ArrayList<Port>();
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
    public RightResult<Port> queryAll() {
        RightResult<Port> result = new RightResult<Port>();
        IPortDataService portDataService = new PortDataService();
        try {
            List<Port> ports = portDataService.getAllDataList();
            List<Port> portList = new ArrayList<Port>();
            for (Port port : ports) {
                portList.add((Port) port);
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
