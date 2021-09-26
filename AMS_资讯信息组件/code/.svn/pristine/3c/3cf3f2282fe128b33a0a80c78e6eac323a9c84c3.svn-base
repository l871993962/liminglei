package  com.yss.ams.sec.information.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUtil {
	/**
	 * 查找结果集中是否包含目标字段  
	 * @param rs
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	public static boolean isFindColumn(ResultSet rs,String columnName) throws SQLException {
		boolean flag=false;
		try {
			if(rs.findColumn(columnName)>0){
				flag=true;
			}
		} catch (SQLException e) {
			flag = false;
		}
		
		return flag;
	}
}
