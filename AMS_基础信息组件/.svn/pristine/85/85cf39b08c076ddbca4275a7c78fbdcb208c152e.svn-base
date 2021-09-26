package com.yss.ams.base.information.support.bi.org.service;

import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.pojo.OrgVo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
 * @author lenovo
 *
 */
@RestfulSupported
public interface IOrgLinkRelaService extends IServiceBus {

	/**
     * 获取机构关联联系人信息
     * @param orgCode
     * @return
     */
    public List<Org> getOrgLinkManList(String orgCode);
    
    /**
     * 获取机构关联联系人信息
     * @param orgCode
     * @return
     */
    public Map<String, Org> getOrgLinkManData(String orgCode);
    
    /**
     * 更新机构关联联系人信息
     * @param orgCode
     * @param orgList
     */
    @LinkControllerMethod(value="updateOrgLinkMan",arguTypes = OrgVo.class)
    public void updateOrgLinkMan(@LinkControllerMethodArgu("orgCode")String orgCode, @LinkControllerMethodArgu("orgList")List<Org> orgList);
    
    /**
     * 删除机构关联联系人信息
     * @param orgCode
     */
    public void deleteOrgLinkMan(String orgCode);
}
