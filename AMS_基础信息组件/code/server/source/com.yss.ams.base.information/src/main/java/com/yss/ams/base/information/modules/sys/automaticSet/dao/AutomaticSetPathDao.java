package com.yss.ams.base.information.modules.sys.automaticSet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPathPojo;
import com.yss.fast.task.support.automatic.pojo.FileScanChannel;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/**
 * 自动化业务设置dao层
 * 
 * @ClassName: AutomaticSetDao
 * @date 2021年06月01日
 * @Stroy105821
 * @author zhuziqing
 */
public class AutomaticSetPathDao extends GeneralDao {

	AutomaticSetPathSqlBuilder sqlBuilder = null;

	public AutomaticSetPathDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (AutomaticSetPathSqlBuilder) sqlBuilder;
	}

	/**
	 * 查询所有的产品业务分类
	 * 
	 * @return
	 */
	public List<Vocabulary> getAllProductType() {
		List<Vocabulary> list = new ArrayList<Vocabulary>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getAllProductTypeSql();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Vocabulary vocabulary = new Vocabulary();
				vocabulary.setC_DV_NAME(rs.getString("C_PRODUCT_NAME"));
				list.add(vocabulary);
			}
		} catch (Exception ex) {
			logger.error("查询所有的产品业务分类失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}

	/**
	 * 查询所有的产品业务分类、接口代码
	 * 
	 * @return
	 */
	public List<Vocabulary> getInterfaceClass() {
		List<Vocabulary> list = new ArrayList<Vocabulary>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getInterfaceClass();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Vocabulary vocabulary = new Vocabulary();
				vocabulary.setC_DV_CODE(rs.getString("C_PRODUCT_NAME"));
				vocabulary.setC_DV_NAME(rs.getString("C_INTERFACE_NAME"));
				vocabulary.setN_ORDER(rs.getString("C_INTERFACE_CODE"));
				vocabulary.setC_DESC(rs.getString("C_INTERFACE_P_ID"));
				list.add(vocabulary);
			}
		} catch (Exception ex) {
			logger.error("查询所有的产品业务分类、接口代码失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}

	/**
	 * 更新产品业务分类、接口代码
	 * 
	 * @param paraMap
	 * @return
	 */
	public boolean updateDataList(List<HashMap<String, String>> paraMap) {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean ret = false;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			// 删除数据
			sql = sqlBuilder.getDeleteDataByTypeSql();
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			this.closeStatementFinal(pst);
			// 插入数据
			String userCode = ContextFactory.getContext().getUserCode();
			sql = sqlBuilder.getInsertDataListSql();
			pst = conn.prepareStatement(sql);
			int index = 1;
			for (HashMap<String, String> hashMap : paraMap) {
				String productName = hashMap.get("productName");
				String interfacePid = hashMap.get("interfacePid");
				String interfaceCode = hashMap.get("interfaceCode");
				String interfaceName = hashMap.get("interfaceName");

				if (!"".equals(productName.trim()) && !"".equals(interfacePid.trim())
						&& !"".equals(interfaceCode.trim()) && !"".equals(interfaceName.trim())) {
					pst.setInt(1, index++);
					pst.setString(2, productName);
					pst.setString(3, interfacePid);
					pst.setString(4, interfaceCode);
					pst.setString(5, interfaceName);
					pst.setString(6, userCode);
					pst.addBatch();
				}
			}
			pst.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			ret = true;
		} catch (Exception ex) {
			logger.error("更新产品业务分类、接口代码失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return ret;
	}

	/**
	 * 查询产品业务分类、接口代码
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<AutomaticSetPathPojo> getProductType() {
		List<AutomaticSetPathPojo> list = new ArrayList<AutomaticSetPathPojo>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getProductType();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				AutomaticSetPathPojo automaticPojo = new AutomaticSetPathPojo();
				automaticPojo.setC_PRODUCT_NAME(rs.getString("C_PRODUCT_NAME"));
				automaticPojo.setC_INTERFACE_CODE(rs.getString("C_INTERFACE_CODE"));
				automaticPojo.setC_INTERFACE_P_ID(rs.getString("C_INTERFACE_P_ID"));
				list.add(automaticPojo);
			}
		} catch (Exception ex) {
			logger.error("查询产品业务分类、接口代码失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}

	/**
	 * 通过接口分类获取接口信息
	 * 
	 * @param productName
	 * @return
	 */
	public List<AutomaticSetPathPojo> getInterfaceByName(List<String> productName) {
		List<AutomaticSetPathPojo> interfaceList = new ArrayList<AutomaticSetPathPojo>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getInterfaceByName(productName);
			pst = conn.prepareStatement(sql);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(productName.toArray(new String[0]), conn));
			rs = pst.executeQuery();
			while (rs.next()) {
				AutomaticSetPathPojo pojo = new AutomaticSetPathPojo();
				pojo.setC_PRODUCT_NAME(rs.getString("C_PRODUCT_NAME"));
				pojo.setC_INTERFACE_P_ID(rs.getString("C_INTERFACE_P_ID"));
				pojo.setC_INTERFACE_CODE(rs.getString("C_INTERFACE_CODE"));
				interfaceList.add(pojo);
			}
		} catch (Exception ex) {
			logger.error("通过接口分类获取接口信息失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return interfaceList;
	}

	/**
	 * 新增保存
	 * 
	 * @param dataList
	 * @return
	 */
	public boolean saveDataList(List<String> proList, List<HashMap<String, String>> dataList) {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean ret = false;
		String sql = "";
		try {
			// 先删除数据
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			String deletesql = sqlBuilder.getDeleteSetDataByTypeSql();
			pst = conn.prepareStatement(deletesql);
			if (dataList != null && dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					HashMap<String, String> data = dataList.get(i);
					String productName = data.get("C_PRODUCT_NAME");
					String chanelCode = data.get("C_CHANEL_CODE");
					String code = data.get("C_PORT_CODE");
					String interfaceCode = data.get("C_INTERFACE_CODE");
					String interfacePath = data.get("C_INTERFACE_PATH");
					if (StringUtils.isNullOrEmpty(chanelCode)) {
						chanelCode = " ";
					}
					if (StringUtils.isNullOrEmpty(interfacePath)) {
						interfacePath = " ";
					}
					pst.setString(1, productName); // 产品业务分类
					pst.setString(2, chanelCode); //外部渠道类型代码
					pst.setString(3, code); //组合代码  
					pst.setString(4, interfaceCode); // 接口代码
					pst.setString(5, interfacePath); // 接口路径
					pst.addBatch();
				}
				pst.executeBatch();
			}

			this.closeStatementFinal(pst);

			// 新增数据
			String userCode = ContextFactory.getContext().getUserCode();
			sql = sqlBuilder.getSaveDataListSql();
			pst = conn.prepareStatement(sql);
			for (HashMap<String, String> hashMap : dataList) {
				String portCode = hashMap.get("C_PORT_CODE");
				String productName = hashMap.get("C_PRODUCT_NAME");
				String chanelCode = hashMap.get("C_CHANEL_CODE");
				String chanelType = hashMap.get("C_CHANEL_TYPE");
				String interfaceGroup = hashMap.get("C_INTERFACE_GROUP");
				String interfaceCode = hashMap.get("C_INTERFACE_CODE");
				String interfaceName = hashMap.get("C_INTERFACE_NAME");
				String interfacePath = hashMap.get("C_INTERFACE_PATH");
				String productCode = " ";
				if (productName.equals("外部券商")) {
					productCode = "WBQS_TYPE";
				} else if (productName.equals("外部渠道")) {
					productCode = "WBQD_TYPE";
				}
				String parentId = interfaceGroup.replaceAll("\\\\", "").trim();
				
				//如果前端没有设置外部渠道或接口路径，同样可以保存，设置默认值
				if(StringUtils.isNullOrEmpty(chanelCode)) {
					chanelCode = " ";
				}
				if(StringUtils.isNullOrEmpty(chanelType)) {
					chanelType = " ";
				}
				if(StringUtils.isNullOrEmpty(interfacePath)) {
					interfacePath = " ";
				}


				// 插入数据
				if (!"".equals(portCode.trim()) && !"".equals(productName.trim()) && !"".equals(interfaceGroup.trim())
						&& !"".equals(interfaceCode.trim()) 
						&& !"".equals(interfaceName.trim())) {
					pst.setString(1, portCode);
					pst.setString(2, productCode);
					pst.setString(3, productName);
					pst.setString(4, chanelCode);
					pst.setString(5, chanelType);
					pst.setString(6, interfaceGroup);
					pst.setString(7, interfaceCode);
					pst.setString(8, interfaceName);
					pst.setString(9, interfacePath);
					pst.setString(10, parentId);
					pst.setString(11, userCode);
					pst.addBatch();
				}
			}
			
			pst.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			ret = true;
		} catch (Exception ex) {
			logger.error("新增保存数据失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return ret;
	}

	/**
	 * 复制
	 * 
	 * @param pojo
	 * @return
	 */
	public boolean copy(HashMap<String, String> data) {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean ret = false;
		String sql = "";
		try {
			
			// 先删除数据
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			String deletesql = sqlBuilder.getDeleteSetDataByTypeSql();
			pst = conn.prepareStatement(deletesql);
			if (data != null) {
				String productName = data.get("C_PRODUCT_NAME");
				String chanelCode = data.get("C_CHANEL_CODE");
				String code = data.get("C_PORT_CODE");
				String interfaceCode = data.get("C_INTERFACE_CODE");
				String interfacePath = data.get("C_INTERFACE_PATH");
				if (StringUtils.isNullOrEmpty(chanelCode)) {
					chanelCode = " ";
				}
				if (StringUtils.isNullOrEmpty(interfacePath)) {
					interfacePath = " ";
				}
				pst.setString(1, productName); // 产品业务分类
				pst.setString(2, chanelCode); // 外部渠道类型代码
				pst.setString(3, code); // 组合代码
				pst.setString(4, interfaceCode); // 接口代码
				pst.setString(5, interfacePath); // 接口路径
				pst.addBatch();

				pst.executeBatch();
			}
			this.closeStatementFinal(pst);
			
			// 复制数据
			String userCode = ContextFactory.getContext().getUserCode();

			String portCode = data.get("C_PORT_CODE");
			String chanelCode = data.get("C_CHANEL_CODE");
			String chanelType = data.get("C_CHANEL_TYPE");
			String productCode = data.get("C_PRODUCT_CODE");
			String productName = data.get("C_PRODUCT_NAME");
			String interfaceCode = data.get("C_INTERFACE_CODE");
			String interfaceName = data.get("C_INTERFACE_NAME");
			String interfaceGroup = data.get("C_INTERFACE_GROUP");
			String interfacePath = data.get("C_INTERFACE_PATH");
			
			//如果前端没有设置外部渠道或接口路径，同样可以保存，设置默认值
			if(StringUtils.isNullOrEmpty(chanelCode)) {
				chanelCode = " ";
			}
			if(StringUtils.isNullOrEmpty(chanelType)) {
				chanelType = " ";
			}
			if(StringUtils.isNullOrEmpty(interfacePath)) {
				interfacePath = " ";
			}

			String parentId = interfaceGroup.replaceAll("\\\\", "").trim();
			// 插入数据
			if (!"".equals(portCode.trim()) && !"".equals(productName.trim()) 
					&& !"".equals(interfaceGroup.trim())
					&& !"".equals(interfaceCode.trim()) 
					&& !"".equals(interfaceName.trim())) {
				sql = sqlBuilder.getSaveDataListSql();
				pst = conn.prepareStatement(sql);
				pst.setString(1, portCode);
				pst.setString(2, productCode);
				pst.setString(3, productName);
				pst.setString(4, chanelCode);
				pst.setString(5, chanelType);
				pst.setString(6, interfaceGroup);
				pst.setString(7, interfaceCode);
				pst.setString(8, interfaceName);
				pst.setString(9, interfacePath);
				pst.setString(10, parentId);
				pst.setString(11, userCode);
				pst.execute();
			}	
			conn.commit();
			conn.setAutoCommit(true);
			ret = true;
		} catch (Exception ex) {
			logger.error("复制失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return ret;
	}

	/**
	 * 通过分组ID、接口ID查询外部渠道组合路径设置返回给fast
	 * 
	 * @param fileScanChannel
	 * @return 返回业务名称 业务代码 渠道代码 渠道名称接口ID 分组ID 目录
	 */
	public List<FileScanChannel> queryInterfaceChannel(FileScanChannel fileScanChannel) {
		PreparedStatement pst = null;
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		List<FileScanChannel> dataList = new ArrayList<FileScanChannel>();
		try {
			conn = this.loadNewConnection();
			// 获取参数
			String interfaceGroup = " ";
			String interfaceCode = " ";
			if (fileScanChannel != null) {
				interfaceGroup = fileScanChannel.getC_INTERFACE_GROUP();
				interfaceCode = fileScanChannel.getC_INTERFACE_ID();
			}

			// 查询数据
			sql = sqlBuilder.queryInterfaceChannelSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, interfaceGroup);
			pst.setString(2, interfaceCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				FileScanChannel pojo = new FileScanChannel();
				pojo.setC_PORT(rs.getString("C_PORT_CODE"));
				pojo.setC_BUSI_CODE(rs.getString("C_PRODUCT_NAME"));
				pojo.setC_BUSI_NAME(rs.getString("C_PRODUCT_NAME"));
				pojo.setC_CHANEL_CODE(rs.getString("C_CHANEL_CODE"));
				pojo.setC_CHANEL_NAME(rs.getString("C_CHANEL_TYPE"));
				pojo.setC_INTERFACE_ID(interfaceCode);
				pojo.setC_INTERFACE_NAME(rs.getString("C_INTERFACE_NAME"));
				pojo.setC_INTERFACE_GROUP(interfaceGroup);
				pojo.setC_FILE_DIR(rs.getString("C_INTERFACE_PATH"));
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			logger.error("通过分组ID、接口ID查询外部渠道组合路径设置失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return dataList;
	}

	/**
	 * 通过组合代码查询外部渠道组合路径设置返回给fast
	 * 
	 * @param portCode
	 * @return
	 */
	public List<FileScanChannel> queryInterfaceChannelByPortCode(String portCode) {
		PreparedStatement pst = null;
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		List<FileScanChannel> dataList = new ArrayList<FileScanChannel>();
		try {
			conn = this.loadNewConnection();
			// 查询数据
			sql = sqlBuilder.queryInterfaceChannelByPortCodeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				FileScanChannel pojo = new FileScanChannel();
				pojo.setC_CHANEL_CODE(rs.getString("C_PRODUCT_NAME"));
				pojo.setC_CHANEL_NAME(rs.getString("C_CHANEL_TYPE"));
				pojo.setC_INTERFACE_ID(rs.getString("C_INTERFACE_CODE"));
				pojo.setC_INTERFACE_NAME(rs.getString("C_INTERFACE_NAME"));
				pojo.setC_INTERFACE_GROUP(rs.getString("C_INTERFACE_P_ID"));
				pojo.setC_FILE_DIR(rs.getString("C_INTERFACE_PATH"));
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			logger.error("通过组合代码查询外部渠道组合路径设置失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return dataList;
	}

	/**
	 * 获取所有的估值指标
	 * @return
	 */
	public List<AutomaticSetPathPojo> getAllIndex() {
		List<AutomaticSetPathPojo> list = new ArrayList<AutomaticSetPathPojo>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getAllIndex();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				AutomaticSetPathPojo setPathPojo = new AutomaticSetPathPojo();
				setPathPojo.setC_INDEX_CODE(rs.getString("C_INDEX_CODE"));
				setPathPojo.setC_INDEX_NAME(rs.getString("C_INDEX_NAME"));
				setPathPojo.setC_INDEX_ORDER(rs.getString("C_INDEX_ORDER"));
				list.add(setPathPojo);
			}
		} catch (Exception ex) {
			logger.error("获取所有的估值指标失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
    
	/**
	 * 估值指标Tab 新增方法
	 * @param dataList
	 * @return
	 */
	public boolean saveList(List<HashMap<String, String>> dataList) {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean ret = false;
		String sql = "";
		ResultSet rs = null;
		try {
			//先筛选对应的参数集合，剔除数据库中已有而且是已审核的参数
			conn = this.loadNewConnection();
			sql = sqlBuilder.queryParamCheckSql();
			pst = conn.prepareStatement(sql);
		
			List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < dataList.size(); i++) {
				String code = dataList.get(i).get("C_INDEX_CODE");
				String vaTime = dataList.get(i).get("C_VA_TIME");
				String portCode = dataList.get(i).get("C_PORT_CODE");
				
				// 查询数据
				pst.setString(1, code); // 指标代码
				pst.setString(2, vaTime); // 所属估值表日期
				pst.setString(3, portCode);// 组合代码
				rs = pst.executeQuery();
				if (!rs.next()) {
					//当没有查到数据，就存进来，代表数据库中不存在，可以新增
					list.add(dataList.get(i));
				}
			}
			this.closeStatementFinal(pst);
			
			// 先删除数据
			conn.setAutoCommit(false);
			String deletesql = sqlBuilder.getDeleteSetData();
			pst = conn.prepareStatement(deletesql);
			if (dataList != null && list.size() > 0) {
				for (HashMap<String, String> data : list) {
					String code = data.get("C_INDEX_CODE");
					String vaTime = data.get("C_VA_TIME");
					String portCode = data.get("C_PORT_CODE");					
					
					pst.setString(1, code); // 指标代码
					pst.setString(2, vaTime); // 所属估值表日期
					pst.setString(3, portCode);// 组合代码
					pst.addBatch();
				}
				pst.executeBatch();
			}
			this.closeStatementFinal(pst);

			// 新增数据
			String userCode = ContextFactory.getContext().getUserCode();
			sql = sqlBuilder.getSaveListSql();
			pst = conn.prepareStatement(sql);
			for (HashMap<String, String> hashMap : list) {
				String portCode = hashMap.get("C_PORT_CODE");
				String indexCode = hashMap.get("C_INDEX_CODE");
				String vaTime = hashMap.get("C_VA_TIME");
				String businessCode = hashMap.get("C_BUSINESS_TYPE_CODE");
				
				// 插入数据
				if (!"".equals(portCode.trim()) 
						&& !"".equals(indexCode.trim())
					    && !"".equals(vaTime.trim())
					    && !"".equals(businessCode.trim())) {
					pst.setString(1, portCode);
					pst.setString(2, indexCode);
					pst.setString(3, vaTime);
					pst.setString(4, userCode);
					pst.setString(5, businessCode);
					pst.addBatch();
				}
			}
			
			pst.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			ret = true;
		} catch (Exception ex) {
			logger.error("估值指标Tab 新增数据失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return ret;
	}

	/**
	 * STORY #107189 【东证资管】自动化业务设置-外部渠道组合路径设置，组合维护的路径能同步或复制给其他组合。
     * 查询所有可参照的组合
	 * @return
	 */
	public List<AutomaticSetPathPojo> getRePortCodeList() {
		List<AutomaticSetPathPojo> list = new ArrayList<AutomaticSetPathPojo>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getRePortCodeListSql();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				AutomaticSetPathPojo setPathPojo = new AutomaticSetPathPojo();
				String datCode = rs.getString("C_DAT_CODE");    //资产类型代码
				String datName = rs.getString("C_DAT_NAME");   //资产类型名称
				if(!StringUtils.isNullOrEmpty(datCode))
				{
					AutomaticSetPathPojo pojo = new AutomaticSetPathPojo();
					//添加计数器，只有集合中没有才添加
					int count = 0;
					for (AutomaticSetPathPojo automaticSetPathPojo : list) {
						if (datCode.equals(automaticSetPathPojo.getC_INTERFACE_P_ID())) {
							count++;
						}
					}
					if(count == 0) {
						pojo.setC_PORT_CODE(datCode);
						pojo.setC_PORT_NAME(datName);		
						list.add(pojo);  
					}
				}
				setPathPojo.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				setPathPojo.setC_PORT_NAME(rs.getString("C_PORT_NAME"));	
				setPathPojo.setC_INTERFACE_P_ID(datCode);//此处是借C_INTERFACE_P_ID字段存资产代码，方便前端"参照组合"下拉框分级
				list.add(setPathPojo);   
			}
		} catch (Exception ex) {
			logger.error("查询所有可参照的组合失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}

	/**
	 * STORY #107189 【东证资管】自动化业务设置-外部渠道组合路径设置，组合维护的路径能同步或复制给其他组合。
     * 通过参照组合代码和产品业务分类查询对应存的接口信息
	 * @param productName 
	 * @param portCode 
	 * @return
	 */
	public List<BasePojo> queryByCodeAndName(String portCode, List<String> productName) {
		List<BasePojo> interfaceList = new ArrayList<BasePojo>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			//没有参照组合代码直接返回
			if (StringUtil.IsNullOrEmpty(portCode)) {
				return interfaceList;
			}
			
			conn = this.loadNewConnection();
			sql = sqlBuilder.queryByCodeAndNameSql(productName);
			pst = conn.prepareStatement(sql);
			pst.setString(1,portCode); //组合代码
			if (productName != null && productName.size() > 0) {
				pst.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(productName.toArray(new String[0]), conn));
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				AutomaticSetPathPojo pojo = new AutomaticSetPathPojo();
				pojo.setC_PRODUCT_NAME(rs.getString("C_PRODUCT_NAME"));
				pojo.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				pojo.setC_INTERFACE_PATH(rs.getString("C_INTERFACE_PATH"));
				pojo.setC_INTERFACE_P_ID(rs.getString("C_INTERFACE_P_ID"));
				pojo.setC_INTERFACE_CODE(rs.getString("C_INTERFACE_CODE"));
				pojo.setC_INTERFACE_NAME(rs.getString("C_INTERFACE_NAME"));
				pojo.setC_INTERFACE_GROUP(rs.getString("C_GROUP_NAME"));//用此字段存导入接口父级名称
				interfaceList.add(pojo);
			}
		} catch (Exception ex) {
			logger.error("通过参照组合代码和产品业务分类查询对应存的接口信息失败: "+ ex.getMessage(), ex);
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return interfaceList;
	}

}
