package com.yss.uco.elecreco.er.erdata.assist;

import java.util.List;

import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.er.template.pojo.Field;
import com.yss.uco.elecreco.er.template.pojo.Table;

/**
 * sql相关辅助类
 * 
 * @author liuxiang 2015年2月13日
 */
public class SqlAssistant {

	/**
	 * 构造插入sql
	 * 
	 * @param table
	 *            表信息
	 * @param listField
	 *            字段列表
	 * @param sourceSql
	 *            初始Sql
	 * @return
	 * @throws Exception
	 */
	public String buildInsertTabSql(Table table, List<Field> listField,
			String sourceSql) throws Exception {
		StringBuffer buf1 = new StringBuffer();
		StringBuffer buf2 = new StringBuffer();
		String retSql = "";
		try {
			buf1.append("insert into ").append(table.getName()).append(" (");
			buf2.append("select ");
			for (Field field : listField) {
				buf1.append(field.getTargetFiled()).append(",");
				buf2.append(field.getSourceField()).append(" as ")
						.append(field.getTargetFiled()).append(",");
			}

			StringUtil.delLastSplitMark(buf1, ",");
			buf1.append(")");
			StringUtil.delLastSplitMark(buf2, ",");
			buf2.append(" from (").append(sourceSql).append(")");
			buf1.append(buf2);
			retSql = buf1.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			StringUtil.clearStringBuffer(buf1);
			StringUtil.clearStringBuffer(buf2);
		}

		return retSql;
	}
}
