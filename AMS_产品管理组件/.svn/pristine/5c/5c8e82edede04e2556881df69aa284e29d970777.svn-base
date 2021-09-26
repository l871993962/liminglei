package com.yss.ams.product.information.modules.aa.portcls.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.aa.portcls.dao.PortClsDao;
import com.yss.ams.product.information.modules.aa.portcls.dao.PortClsSqlBuilder;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IPortClsService;
import com.yss.ams.product.information.support.cache.PortClsCache;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.datacheck.annotation.CommonDataCheck;
import com.yss.datacheck.enums.CheckFuncGroup;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.mvc.returninfo.ReturnInfoGenerator;


@DefaultCacheRefresh(group = CacheGroup.PORTCLS)
public class PortClsService extends ServiceBus<PortClsService> implements
		IPortClsService {

	private PortClsDao portClsDao = null;

	public PortClsService() throws Exception {
		portClsDao = new PortClsDao(DbPoolFactory.getInstance().getPool(), new PortClsSqlBuilder());
		dao = portClsDao;
	}

	@Override
	public void updateDueDate(String portCode, String dueDate) {
		portClsDao.updateDueDate(portCode, dueDate);
	}
	
	////weijj 20140420 修改注解修改时增加 日期交叉性校验
	@CommonDataCheck(checkFuncGroup = CheckFuncGroup.Edit)
	public String updateById(List<BasePojo> pojoList) {
		return super.updateById(pojoList);
	}
	
	/**
	 * 根据组合code 和分级组合类型 获得对应的分级组合code By Jinghehe 2014-1-7
	 * 
	 * 
	 */
	@Override
	public String getPortClsCode(String portCode, String portClsType) {
		return portClsDao.getPortClsCode(portCode, portClsType);
	}

	@Override
	public List<PortCls> getPortClsByUser(String userCode) {
		return portClsDao.getPortClsByUser(userCode);
	}

	@Override
	public List<PortCls> getPortClsByPortCode(String portCode) {
		PortClsCache portClsCache = CacheManager.getInstance()
				.getCache(CacheGroup.PORTCLS);
		return portClsCache.getCashListByPort(portCode);
	}

	@Override
	public String checkDate(HashMap<String, String> paraMap) {
		return portClsDao.checkDate(paraMap);
	}
	
	@Override
	public String checkDateQSRQ(HashMap<String, String> paraMap) {
		return portClsDao.checkDateQSRQ(paraMap);
	}

    /** 
     * @Title: insert
     * @Desc: 
     * @param pojo
     * @param conn
     * @return 
     * @see com.yss.ams.product.information.support.modules.aa.portcls.service.IPortClsService#insert(com.yss.framework.api.common.co.BasePojo, java.sql.Connection) 
     */
    @Override
    public <K extends BasePojo> String insert(K pojo, Connection conn) {
        String retInfo = "";
        try {
            // 添加对Pojo类型的判断
            // Update By Huxingtao 2013-2-22
            if (pojo instanceof ParamPojo) {
                ((ParamPojo) pojo).setModifyDate(DateUtil
                        .getNow(MvcConstant._DATA_STD_DATE_FORMAT));
            }

            // 当审核状态未开启且ＰＯＪＯ类继承了审核POJO基类时 byleeyu20130718
            if (safeData != null && safeData.getN_CHECK() <= 0
                    && pojo instanceof AuditableParamPojo) {
                AuditableParamPojo auditPojo = (AuditableParamPojo) pojo;
                auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
                auditPojo
                        .setOperator(ContextFactory.getContext().getUserCode()); // 设置已审核的用户
                auditPojo.setAuditDate(DateUtil
                        .getNow(MvcConstant._DATA_STD_DATE_FORMAT));
            }

            /* 添加自定义日志功能 记录执行时间 */
            // StringBuffer buf = new StringBuffer();
            // buf.append("serviceName:").append(this.getClass().getName())
            // .append("  ");
            // buf.append("methodName:").append("insert insertData").append("  ");
            // buf.append("menuId:").append(getMenuId());
            //
            // WriteLog.newInstance().startLog();

            /*
             * Author : ChenLong Date : 2013-11-21 Status : Add Comment:
             * 插入数据C_IDEN返回值集合
             */
            List<String> cidenList = new ArrayList<String>();
            String ciden = dao.insert(pojo, conn);
            //String ciden = insertData(pojo);
            cidenList.add(ciden);

            // WriteLog.newInstance(this.getClass().getSimpleName()).write(buf);
            // StringUtil.clearStringBuffer(buf);
            // buf = null;

            retInfo = ReturnInfoGenerator.getSaveOKStr(menuId, cidenList);
        } catch (Exception ex) {
            String errorMess = "";
            // 添加了对唯一性索引异常的区分
            if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
                // 修改对数据唯一提示参数 需求号: YSSUCO赢时胜2013年03月28日01_A byleeyu 20130910
                errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(ex
                        .getMessage(), dao, pojo);
            } else {
                errorMess = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeSaveErr, menuId);
            }

            throw new ErrorMessageException(ex,errorMess);
        }
        return retInfo;
    }

    /** 
     * @Title: delete
     * @Desc: 
     * @param list
     * @param conn
     * @return 
     * @see com.yss.ams.product.information.support.modules.aa.portcls.service.IPortClsService#delete(java.util.List, java.sql.Connection) 
     */
    @Override
    public String deleteById(List<BasePojo> list, Connection conn) {
        String retInfo = "";

        try {
            dao.deleteById(list);
            if (safeData != null && safeData.getN_RECYCLE() > 0) {
                retInfo = saveDelRecord(list);
            }
            retInfo = getDelInfo(retInfo);
        } catch (Exception ex) {
            if(ex instanceof YssRuntimeException){
                throw new ServiceException(ex);
            }else{
                retInfo = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeDelErr, menuId);
                logger.log(ex.getMessage());
                throw new ErrorMessageException(ex,retInfo);
            }
        }

        return retInfo;
    }

    /** 
     * @Title: updateById
     * @Desc: 
     * @param pojo
     * @param conn
     * @return 
     * @see com.yss.ams.product.information.support.modules.aa.portcls.service.IPortClsService#updateById(com.yss.framework.api.common.co.BasePojo, java.sql.Connection) 
     */
    @Override
    public <K extends BasePojo> String updateById(K pojo, Connection conn) {
        String operRes = "";
        try {
            if (pojo instanceof ParamPojo) {
                ((ParamPojo) pojo).setModifyDate(DateUtil
                        .getNow(MvcConstant._DATA_STD_DATE_FORMAT));
            }

            // 当审核状态未开启且ＰＯＪＯ类继承了审核POJO基类时 byleeyu20130718
            if (safeData != null && safeData.getN_CHECK() <= 0
                    && pojo instanceof AuditableParamPojo) {
                AuditableParamPojo auditPojo = (AuditableParamPojo) pojo;
                auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
                auditPojo
                        .setOperator(ContextFactory.getContext().getUserCode()); // 设置已审核的用户
                auditPojo.setAuditDate(DateUtil.getCurrent());
            }

            dao.updateById(pojo, conn);
            operRes = ReturnInfoGenerator.getUpdateOKStr(menuId);
        } catch (Exception ex) {
            String errorMess = "";
            // 添加了对唯一性索引异常的区分
            if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
                // 修改对数据唯一提示参数 需求号: YSSUCO赢时胜2013年03月28日01_A byleeyu 20130910
                errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(ex
                        .getMessage(), dao, pojo);
            } else {
                errorMess = ReturnInfoGenerator.getOperErrMsg(
                        MvcConstant._CodeSaveErr, menuId);
            }

            throw new ErrorMessageException(ex, errorMess);
        }
        return operRes;
    }
}
