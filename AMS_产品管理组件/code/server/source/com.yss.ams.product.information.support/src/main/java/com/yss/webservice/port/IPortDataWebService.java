package com.yss.webservice.port;

import com.yss.framework.api.common.co.Port;
import com.yss.right.pojo.RightResult;

public interface IPortDataWebService {
    /**
     * @Title: create 
     * @Desc: 新增组合信息<Port>
     * @param port 组合对象
     * @return RightResult<Port>
     */
    RightResult<Port> create(Port port);
    
    /**
     * @Title: update 
     * @Desc: 更新组合信息<Port>
     * @param port 组合对象
     * @return RightResult<Port>
     */
    RightResult<Port> update(Port port);
    
    /**
     * @Title: delete 
     * @Desc: 删除组合信息<Port>
     * @param portCode 组合代码
     * @return RightResult<Port>
     */
    RightResult<Port> delete(String portCode);
    
    /**
     * @Title: query 
     * @Desc: 通过组合代码查询组合信息<Port>
     * @param portCode 组合代码
     * @return RightResult<Port>
     */
    RightResult<Port> query(String portCode);
    
    /**
     * @Title: queryAll 
     * @Desc: 查询所有的组合信息
     * @return RightResult<Port>
     */
    RightResult<Port> queryAll();
}
