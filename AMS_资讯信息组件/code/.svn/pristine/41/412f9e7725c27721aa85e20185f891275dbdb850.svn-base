package com.yss.ams.sec.information.modules.pub.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.support.modules.mp.secequ.pojo.SecEqu;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.data.mp.secequ.pojo.SecEqu;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * 债券理财权益数据处理类
 * @author CL
 *
 */
public class AdmFiLcEqu extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 自动产生权益数据日期
	 */
	@SuppressWarnings("unused")
	private Date markDate = null;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private GeneralDao dao = null;
	private Logger logger = LogManager.getLogger(AdmFiLcEqu.class);

	/**
	 * 构造方法
	 * 
	 * @param generateDate
	 */
	public AdmFiLcEqu(Date generateDate) {

		try {
			generateDate = dateFormat.parse(dateFormat.format(generateDate));
			dao = new GeneralDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), null);
		} catch (ParseException e) {
//			e.printStackTrace();
			logger.log("公共处理：债券理财权益处理类初始化出错", e);
		}
		this.markDate = generateDate;
	}

	/**
	 * 构造方法
	 * 
	 * @param generateDate
	 */
	public AdmFiLcEqu() {

		try {
			dao = new GeneralDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), null);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("公共处理：债券理财权益处理类初始化出错", e);
		}
	}
	
	/**
	 * 保存一组权益数据到权益数据表中
	 * 
	 * @param perHundIntPojos
	 * @throws Exception
	 */
	public void saveEqu(List<SecEqu> equPojos,Connection dbConn)throws Exception {
		PreparedStatement pst = null;
		//Connection conn = null;
		try {
			//conn = this.getDbConn();
			//conn.setAutoCommit(false);
			StringBuffer buff = new StringBuffer();
			buff.append("INSERT INTO T_D_MP_SEC_EQU (C_IDEN,C_EQU_CLS,C_SEC_CODE,C_SEC_CODE_TAG,C_MKT_CODE,");
			buff.append("C_DS_CODE,N_EQU_RATIO_PT,N_EQU_RATIO_AT,N_PRICE_PLAC,C_DC_CODE,C_DV_VAR_DUR,");
			buff.append("C_DV_CODE,C_DTA_CODE,D_REG,D_FINAL,D_EXR,C_DESC,C_UPDATE_BY,");
			buff.append("C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME,N_CHECK_STATE,C_DATA_IDF) VALUES ");
			buff.append("(SEQU_D_MP_SEC_EQU.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");//21
			String updateTime = YssFun.formatDatetime(new Date());
			if (null != equPojos && 0 != equPojos.size()) {
					pst = dbConn.prepareStatement(buff.toString());
					for (SecEqu pojo : equPojos) {
						if(null != pojo.getC_SEC_CODE()){
							// 数据标识 DJ-对价派息；SP-证券送配；LT-证券流通；FX-证券发行
							pst.setString(1, null==pojo.getC_EQU_CLS() ? "DJ" : pojo.getC_EQU_CLS());
							// 证券代码
							pst.setString(2, pojo.getC_SEC_CODE());
							// 标的证券
							pst.setString(3, null==pojo.getC_SEC_CODE_TAG() ? " " : pojo.getC_SEC_CODE_TAG());
							// 市场代码
							pst.setString(4, null==pojo.getC_MKT_CODE() ? " " : pojo.getC_MKT_CODE());
							// 权益类型
							pst.setString(5, null==pojo.getC_DS_CODE() ? " " : pojo.getC_DS_CODE());
							// 税前权益比例
							pst.setDouble(6, null==pojo.getN_EQU_RATIO_PT() || "".equalsIgnoreCase(pojo.getN_EQU_RATIO_PT().trim()) ? 0.0 : Double.parseDouble(pojo.getN_EQU_RATIO_PT()));
							// 税后权益比例
							pst.setDouble(7, null==pojo.getN_EQU_RATIO_AT() || "".equalsIgnoreCase(pojo.getN_EQU_RATIO_AT().trim()) ? 0.0 : Double.parseDouble(pojo.getN_EQU_RATIO_AT()));
							// 配售价格
							pst.setBigDecimal(8, null==pojo.getN_PRICE_PLAC() || "".equalsIgnoreCase(pojo.getN_EQU_RATIO_AT().trim()) ? BigDecimal.ZERO : BigDecimal.valueOf(Double.valueOf(pojo.getN_PRICE_PLAC())));
							// 币种
							pst.setString(9, null==pojo.getC_DC_CODE() ? "CNY" : pojo.getC_DC_CODE());
							// 锁定期限
							pst.setString(10, null==pojo.getC_DV_VAR_DUR() || "".equalsIgnoreCase(pojo.getC_DV_VAR_DUR()) ? " " : pojo.getC_DV_VAR_DUR());
							// 发行方式/分红类型
							pst.setString(11, null==pojo.getC_DV_CODE() || "".equalsIgnoreCase(pojo.getC_DV_VAR_DUR()) ? " " : pojo.getC_DV_CODE());
							// 交易属性
							pst.setString(12, null==pojo.getC_DTA_CODE() || "".equalsIgnoreCase(pojo.getC_DV_VAR_DUR()) ? " " : pojo.getC_DTA_CODE());
							// 登记日
							pst.setDate(13, YssFun.toSqlDate(pojo.getD_REG()));
							// 缴款截止日
							pst.setDate(14, YssFun.toSqlDate(pojo.getD_FINAL()));
							// 除权日
							pst.setDate(15, YssFun.toSqlDate(pojo.getD_EXR()));
							// 描述
							pst.setString(16, null==pojo.getC_DESC() || "".equalsIgnoreCase(pojo.getC_DV_VAR_DUR()) ? " " : pojo.getC_DESC());
							// 创建人
							pst.setString(17, ContextFactory.getContext().getUserCode());
							// 创建时间
							pst.setString(18, updateTime);
							// 审核人
							pst.setString(19, ContextFactory.getContext().getUserCode());
							// 审核时间
							pst.setString(20, updateTime);
							// 审核状态
							pst.setDouble(21, 1);
							
							// 数据来源
							if(null != pojo.getC_DATA_IDF()
									&& pojo.getC_DATA_IDF().equals("Z"))
							{
								pst.setString(22, "Z");
							}
							else {
								pst.setString(22, "H");
							}

							
							pst.addBatch();
						}
					}
					pst.executeBatch();
					pst.clearBatch(); //addbyleeyu20151015
				
//				finally {
//					dao.closeStatementFinal(pst);
//				}
			}
			//conn.commit();
		} catch (SQLException e) {
//				e.printStackTrace();
			logger.log("公共处理_债券理财权益处理: 保存一组权益数据到权益数据表中报错", e);
			throw (Exception) e;
		} 	//conn.setAutoCommit(true);
		 catch (Exception ex) {
			throw ex;
		} finally {
			dao.closeStatementFinal(pst);
			//dao.releaseConnection(conn);
		}
	}
	
	/**
	 * 删除一组权益数据
	 * 
	 * @param equPojos
	 * @throws Exception
	 */
	public void deleteEqu(List<SecEqu> equPojos,Connection dbConn)throws Exception {
		PreparedStatement pst = null;
		String sql = "DELETE FROM T_D_MP_SEC_EQU WHERE C_SEC_CODE = ? AND D_EXR = ? and C_DATA_IDF= 'Z'";
		if (null != equPojos && 0 != equPojos.size()) {
			//Connection conn = null;
			try {
				//conn = this.getDbConn();
				//conn.setAutoCommit(false);
				pst = dbConn.prepareStatement(sql);
				for (SecEqu pojo : equPojos) {
					pst.setString(1, pojo.getC_SEC_CODE());
					pst.setDate(2, YssFun.toSqlDate(pojo.getD_EXR()));
					pst.addBatch();
				}
				pst.executeBatch();
				pst.clearBatch();//addbyleeyu20151015
				//conn.commit();
				//conn.setAutoCommit(true);
			} catch (SQLException e) {
//				e.printStackTrace();
				logger.log("公共处理_债券理财权益处理:删除权益数据报错", e);
				throw (Exception) e;
			} finally {
				dao.closeStatementFinal(pst);
				//dao.releaseConnection(conn);
			}
		}
	}

	/**
	 * 检查【对价派息信息】中如果存在证券内码相同、登记日期相同、除权日期相同的手工数据时
	 * @param equ
	 * @return
	 * @throws Exception
	 */
	public boolean isExistEqu(SecEqu equ) throws Exception {
		boolean result = false;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(" select * from T_D_MP_SEC_EQU where c_data_idf = 'H' and n_check_state = 1  ");
		buffer.append(" and c_sec_code = ? and d_reg = ? and d_exr= ? ");
			Connection conn = null;
			try {
				conn = YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class).getConnection();
				conn.setAutoCommit(false);
				pst = conn.prepareStatement(buffer.toString());
				pst.setString(1, equ.getC_SEC_CODE());
				pst.setDate(2, YssFun.toSqlDate(equ.getD_REG()));
				pst.setDate(3, YssFun.toSqlDate(equ.getD_EXR()));
				rs = pst.executeQuery();
				if (rs.next()) {
					result = true;
				}
			} catch (SQLException e) {
//				e.printStackTrace();
				logger.log("公共处理_债券理财权益处理:删除权益数据报错", e);
				throw (Exception) e;
			} finally {
				dao.closeResultSetFinal(rs); //addbyleeyu20151015
				dao.closeStatementFinal(pst);
				dao.releaseConnection(conn);
			}
			return result;
		}
	/**
	 * #155809 嘉实基金-债券历史付息，初始化债券每百元利息时，系统报错，导致债券每百元历史付息信息被删除
	 * 删除保存进行事务控制	
	 * @throws Exception
	 */	
     public void insertEqu(List<SecEqu> equPojos) throws Exception{ 		
 		Connection dbConn = null;
		try{
			//获取连接			
	 		dbConn = dao.loadNewConnection();         	
	    	//事务开始
	 		dbConn.setAutoCommit(false);			
			// 删除已经存在数据
			deleteEqu(equPojos,dbConn);
			// 保存债券、理财对息权益数据
			saveEqu(equPojos,dbConn);
			//业务处理完毕，事务提交
			dbConn.commit();	
			dbConn.setAutoCommit(true);
		}catch(Exception ex){
			throw ex;
		}finally{
			dao.releaseConnection(dbConn);
		}    	 
     }
}
