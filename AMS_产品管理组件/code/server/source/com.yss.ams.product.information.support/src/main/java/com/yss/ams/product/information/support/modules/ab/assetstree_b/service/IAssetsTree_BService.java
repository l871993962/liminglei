package com.yss.ams.product.information.support.modules.ab.assetstree_b.service;

import java.sql.SQLException;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * <产品树型结构>普通服务接口，主要进行增删改查操作
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IAssetsTree_BService extends IServiceBus {
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Comment: 判断拖入行节点与选择行节点的组合信息是否为同一资产类型
	 * Task   : STORY49928产品树形结构界面优化
	 * @param portCode	待拖入位置行信息
	 * @param dragPortCode	所属选择行信息
	 * @return
	 */
	public String isSameAssetType(String portCode, String dragPortCode);
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Comment: 删除或者更新组合信息对应树形结构节点
	 * Task   : STORY49928产品树形结构界面优化
	 * @return
	 */
	@LinkControllerMethod(value = "updateOrdelete", arguTypes = {String.class,String.class,String.class,String.class})
	public int updateOrdelete(String id, String trCode, String isParent, String type);
	
	/**
	 * 产品树形结构拖放，需要传入顶级节点用来更新后台缓存对象(上面的方法因FAST调用保留兼容)
	 * @return
	 */
	@LinkControllerMethod(value = "updateOrdelete", arguTypes = {String.class,String.class,String.class,String.class,String.class})
	public int updateOrdelete(String id, String trCode, String isParent, String type, String trCodeR);

	/**
	 * STORY39490【南方基金】产品树形结构实现私有层面设置，并且针对私有层面的不用审核
	 * @Title getUserId 
	 * @Description 
	 * @author liulei@ysstech.com
	 * @date 2017年4月6日上午9:10:14
	 * @param quyCon
	 * @return
	 * @throws SQLException 
	 */
	public String getUserId(String quyCon) throws SQLException;
	
	public String getCodeByCId(String id);


}
