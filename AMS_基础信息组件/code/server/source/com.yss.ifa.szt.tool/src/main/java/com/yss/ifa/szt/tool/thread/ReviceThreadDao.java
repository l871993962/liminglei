package com.yss.ifa.szt.tool.thread;

import java.io.File;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.file.PropertiesUtil;
import com.yss.ifa.szt.tool.pojo.TransPojo;

public class ReviceThreadDao  extends GeneralDao{

	public ReviceThreadDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	private FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.GLOABL_PATH);
	/**
	 * 是否转换深圳通账户信息
	 */
	private boolean isTransMrAcc = true;
	/**
	 * 是否保存mr日志至中间表
	 * STORY54437参数控制深证通报文是否写入中间表
	 */
	private boolean isSaveTable = true;
	/**
	 * Author : wulongxing
	 * Date   : 2016-12-1
	 * Status : Add
	 * Comment: 查询对接的伺服器版本模式新版本或不配置为 true；false则为旧版本伺服器
	 * STORY54437参数控制深证通报文是否写入中间表
	 * @throws Exception
	 */
	private void getParam(){
		try {
			PropertiesUtil propertiesUtil = new PropertiesUtil();
			String fileName = fileUtil.getFilePath() + "runtime.properties";
			File file = new File(fileName);
			if(file.exists()){
				Properties properties = propertiesUtil.Properties(fileName);
				isTransMrAcc = Boolean.valueOf(properties.getProperty("elec_trans_mracc","true"));
				isSaveTable = Boolean.valueOf(properties.getProperty("elec_save_mr","true"));
			}
		} catch (Throwable e) {
			logger.log("获取runtime.properties配置文件失败", e);
		}
	}
	/**
	 * STORY #32891 支付平台接收MR返回报文，反写给2.5
	 * 保存报文保存到4.5系统中间表
	 * @param pojo
	 * @return
	 */
	public String saveTwoPFiveMsg(TransPojo pojo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		StringBuffer sql = new StringBuffer();
		String result = "fail";
		Clob clob = null;
		ResultSet rs = null;
		try {
			getParam();
			if(!isSaveTable){//STORY54437参数控制深证通报文是否写入中间表
				return result;
			}
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql.append(" insert into T_C_BW_MRFORWARD (FID,FDATE,FTIME,FYSBW,FMBYHBZ,FsrcAppID,FMBYH,SAVE_FLAG,FINBZ,FOUTBZ) VALUES (");
			sql.append(" ?,to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd'),"
					+ "to_char(systimestamp,'yyyyMMddhh24miSSff3'),"
					+ "EMPTY_CLOB(),?,?,?,0,0,0 )");
			pstmt = conn.prepareStatement(sql.toString());
			String ciden = getSequenceNextNumber(conn, "SEQU_C_BW_MRFORWARD");
			pstmt.setString(1, ciden);//报文内容
			//178711  吴龙兴 泰达宏利基金-电子对账和2.5并行中间表传值错误   2.5根据托管行方账户取数据
			if(isTransMrAcc){
				pstmt.setString(2, pojo.getFromApp());//发送方应用标识
				pstmt.setString(3, pojo.getToApp());//接收方应用标识
				pstmt.setString(4, pojo.getFromUser());//发送方用户标识
			}else{//旧版本伺服器发送方及接收方账户放反了
				pstmt.setString(2, pojo.getToApp());//发送方应用标识
				pstmt.setString(3, pojo.getFromApp());//接收方应用标识
				pstmt.setString(4, pojo.getToUser());//发送方用户标识
			}
			pstmt.executeUpdate();
			this.closeStatementFinal(pstmt);
			// 获取clob字段
			sql.setLength(0);
			sql.append("select FYSBW from T_C_BW_MRFORWARD where fid = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, ciden);
			rs = pstmt.executeQuery();
			
			/*BUG #140259 南方基金-4.5电子对账与2.5并行问题：2.5无法解析4.5保存报文 
			 * 替换报文中的换行符*/
			String clobStr = pojo.getSendStr();
			clobStr = clobStr.replace("\r", "");
			clobStr = clobStr.replace("\n", "");
			if(rs.next()){
				clob = rs.getClob(1);
				clob.setString(1, clobStr);
				this.closeStatementFinal(pstmt);
				sql.setLength(0);
				// 更新clob字段
				sql.append("update T_C_BW_MRFORWARD set FYSBW = ? where fid = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setClob(1, clob);
				pstmt.setString(2, ciden);
				pstmt.executeUpdate();
				conn.commit();
			}else{
				/* BUG #141900 南方基金-伺服器收到报文未发给应用系统 */
				logger.log("未获取到需要更新报文大字段的数据，报文数据为保存");
			}
			result = "success";
		} catch (Exception e) {
			logger.log("4.5系统中间表插入报文信息失败！", e);
			throw new DataAccessException("4.5系统中间表插入报文信息失败",e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return result;
	}
}
