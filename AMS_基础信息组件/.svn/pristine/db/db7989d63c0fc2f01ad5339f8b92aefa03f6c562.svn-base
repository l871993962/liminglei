package com.yss.ams.db.upgrade.baseinfo.structs.packages;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.PackageBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
/**
 * 数据库函数包构建实例
 * 创建一个包的过程需要包含两部分
 * 方法规范说明为:
 * 		pkgFunXXXXPackage ：XXXX表示函数名(pkgFunXXXXPackage识别为包的创建)
 * 注：如果高版本中某一个函数需要改变时
 * 改函数内容和增、减参数时 
 * 	建议：XXXX  函数名 注意 函数包名和包体不要修改,从而达到这个函数在这个函数包下进行版本管理
 * FAQ: 如果函数间有调用,记得前置调用的函数包创建过程.
 * @ClassName PackageDescImpl
 * @Description
 * @author shidawei@ysstech.com
 * @CreateDate 2020年04月16日14:11:00
 * @Version 300.7版本
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class PackageDescImpl extends BaseStructDesc {
	
	private PackageBuilder pkgBuilder = null;

	@Override
	public void execute() throws Exception {
		pkgBuilder = getPacBuilder();
		pkgFunGetDayHdayPackage();
		pkgFunIsConTaInPackage();
		pkgFunGetfjkmbyMapInPackage();
	}
	
	private void pkgFunGetDayHdayPackage()throws Exception {
		pkgBuilder.createOrReplacePackage("PKG_FUN_GETDAY_HDAY")
			.begin_SQL()
			.appendLine(" CREATE OR REPLACE PACKAGE PKG_FUN_GETDAY_HDAY AS ")
			.appendLine(" Function GETDAY_HDAY(C_HDay_Code varchar2,D_Stand_Date date,N_Interval_Day int,C_Type varchar2) return Date; ") 
			.appendLine(" END PKG_FUN_GETDAY_HDAY;")
			.end_SQL()
			.build(UpdateType.REQUEST, "82090", "包头创建"," ","2020-04-17");
	
		pkgBuilder.createOrReplacePackageBody("PKG_FUN_GETDAY_HDAY")
			.begin_SQL()
			.appendLine(" CREATE OR REPLACE PACKAGE BODY PKG_FUN_GETDAY_HDAY AS ")
			.appendLine(" Function getDay_HDay(C_HDay_Code varchar2, ")
			.appendLine(" D_Stand_Date date, ")
			.appendLine(" N_Interval_Day int, ")
			.appendLine(" C_Type varchar2) ")
			.appendLine(" return Date as ")
			.appendLine(" /*函数说明：通过节假日代码获取指定的日期[查找基准日期前/后N自然/工作日的日期] ")
			.appendLine(" 注：此函数只适用于节假日表中只保存了节假日日期（非工作日记录）的情况 ,警告：若没有设置范围内的节假日信息，此函数会计算出错误的返回日期 ")
			.appendLine(" 参数说明： D_Stand_Date 基准日期, C_MKT_Code 节假日代码, N_Interval_Day 间隔天数, 大于0向后查找；小于0向前查找；等于0返回基准日期  C_Type 类型 'W'工作日；'D'自然日；'DW'自然日递延至工作日*/ ")
			.appendLine(" D_Return Date; /*返回的日期*/ ")
			.appendLine(" D_Start Date; /*区间开始日期*/ ")
			.appendLine(" D_End Date; /*区间结束日期*/ ")
			.appendLine(" D_Tmp Date; ")
			.appendLine(" N_Count int := 1; ")
			.appendLine(" C_Hday varchar2(20) := C_HDay_Code; ")
			.appendLine(" begin ")
			.appendLine("  D_Return := D_Stand_Date; ")
			.appendLine(" /*判断参数是否合法*/ ")
			.appendLine(" if (('W' != C_Type) AND ('D' != C_Type) and ('DW' != C_Type)) then ")
			.appendLine(" return D_Return; ")
			.appendLine(" else ")
			.appendLine("  if (('D' = C_Type) or ('DW' = C_Type)) then ")
			.appendLine(" D_Return := D_Stand_Date + N_Interval_Day; ")
			.appendLine("  else ")
			.appendLine(" /*工作日的推算*/ ")
			.appendLine(" D_Start := D_Stand_Date; ")
			.appendLine(" D_End := D_Stand_Date + N_Interval_Day; ")
			.appendLine("  D_Return := D_End; ")
			.appendLine("  while (true) loop ")
			.appendLine("  exit when N_Count = 0; ")
			.appendLine("  begin ")
			.appendLine("  if (N_Interval_Day >= 1) then ")
			.appendLine(" select /*+RESULT_CACHE*/ count(*) ")
			.appendLine(" into N_Count ")
			.appendLine(" from T_P_BI_HDAY_SUB a ")
			.appendLine(" where a.C_HDay_Code = C_Hday ")
			.appendLine("  and a.D_HDay > D_Start ")
			.appendLine("  and a.D_HDay <= D_End ")
			.appendLine("  and a.c_Date_Type = 'H' ")
			.appendLine(" and a.N_Check_State = 1; ")
			.appendLine(" if (N_Count > 0) then ")
			.appendLine(" D_Start := D_End; ")
			.appendLine("  D_End := D_End + N_Count; ")
			.appendLine("  D_Return := D_End; ")
			.appendLine("  else ")
			.appendLine(" exit; /*退出*/ ")
			.appendLine("  end if; ")
			.appendLine("  else ")
			.appendLine("  /*如果是逆序*/ ")
			.appendLine("  if (D_Start > D_End) then ")
			.appendLine(" D_Tmp := D_End; ")
			.appendLine(" D_End := D_Start; ")
			.appendLine("  D_Start := D_Tmp; ")
			.appendLine("  end if; ")
			.appendLine("  select /*+RESULT_CACHE*/ count(*) ")
			.appendLine("  into N_Count ")
			.appendLine("  from T_P_BI_HDAY_SUB a ")
			.appendLine("  where a.C_HDay_Code = C_Hday ")
			.appendLine("  and a.D_HDay >= D_Start ")
			.appendLine("  and a.D_HDay < D_End ")
			.appendLine("  and a.c_Date_Type = 'H' ")
			.appendLine("  and a.N_Check_State = 1; ")
			.appendLine("  if (N_Count > 0) then ")
			.appendLine("  D_End := D_Start; ")
			.appendLine("  D_Start := D_End - N_Count; ")
			.appendLine("  D_Return := D_Start; ")
			.appendLine("  else ")
			.appendLine("  exit; /*退出*/ ")
			.appendLine("  end if; ")
			.appendLine("  end if; ")
			.appendLine("  end; ")
			.appendLine("  end loop; ")
			.appendLine("  end if; ")
			.appendLine("  if ('DW' = C_Type) and (N_Interval_Day>0) then ")
			.appendLine("  N_Count:=1; ")
			.appendLine("  while (true) loop ")
			.appendLine("  exit when N_Count = 0; ")
			.appendLine("  select /*+RESULT_CACHE*/ count(*) ")
			.appendLine("  into N_Count ")
			.appendLine("  from T_P_BI_HDAY_SUB a ")
			.appendLine("  where a.C_HDay_Code = C_Hday ")
			.appendLine("  and a.D_HDay = D_Return ")
			.appendLine(" and a.c_Date_Type = 'H' ")
			.appendLine("  and a.N_Check_State = 1; ")
			.appendLine("  if N_Count!=0 then ")
			.appendLine("  D_Return:=D_Return+1; ")
			.appendLine("  end if; ")
			.appendLine("  end loop; ")
			.appendLine("  end if; ")
			.appendLine("  end if; ")
			.appendLine("  return D_Return; ")
			.appendLine(" end; ")
			.appendLine(" END PKG_FUN_GETDAY_HDAY;")
			.end_SQL()
			.build(UpdateType.REQUEST, "82090", "包体创建"," ","2020-04-17");
	}
	
	private void pkgFunIsConTaInPackage()throws Exception {
		pkgBuilder.createOrReplacePackage("PKG_FUN_ISCONTAIN")
			.begin_SQL()
			.appendLine(" CREATE OR REPLACE PACKAGE PKG_FUN_ISCONTAIN AS ")
			.appendLine(" FUNCTION ISCONTAIN(p_stringA in varchar2,p_stringB in varchar2,p_delimiterA in varchar2,p_delimiterB in varchar2) return number; ")
			.appendLine(" END PKG_FUN_ISCONTAIN;")
			.end_SQL()
			.build(UpdateType.REQUEST, "82090", "包头创建"," ","2020-04-17");
		
		pkgBuilder.createOrReplacePackageBody("PKG_FUN_ISCONTAIN")
			.begin_SQL()
			.appendLine(" CREATE OR REPLACE PACKAGE BODY PKG_FUN_ISCONTAIN  AS ")
			.appendLine(" FUNCTION ISCONTAIN(p_stringA in varchar2,p_stringB in varchar2,p_delimiterA in varchar2,p_delimiterB in varchar2) return number as ")
			.appendLine(" /*** 函数说明：  ")
			.appendLine("  p_stringA 字符串格式 a,b,c  ")
			.appendLine("  p_stringB 字符串格式 a,b,c,d  ")
			.appendLine("  p_delimiterA  p_stringA字符串的分隔符  ")
			.appendLine("  p_delimiterB  p_stringB字符串的分隔符  ")
			.appendLine(" 用于判断：p_stringA 字符串以逗号分隔的数据是否在 p_stringB 字符串以分隔的数据中包含  ")
			.appendLine("   1   包含 0 不包含  ")
			.appendLine("  ***/  ")
			.appendLine(" v_lengthA NUMBER := LENGTH(p_stringA);  ")
			.appendLine(" v_startA NUMBER := 1;  ")
			.appendLine(" v_indexA NUMBER;  ")
			.appendLine(" v_strsA varchar2(4000);  ")
			.appendLine(" v_lengthB NUMBER := LENGTH(p_stringB);  ")
			.appendLine(" v_startB NUMBER := 1; ") 
			.appendLine(" v_indexB NUMBER;  ")
			.appendLine(" v_strsB varchar2(4000);  ")
			.appendLine(" v_result NUMBER :=0;-- 返回结果 默认是 0  ")
			.appendLine(" BEGIN  ")
			.appendLine(" WHILE(v_startA <= v_lengthA) ") 
			.appendLine(" LOOP  ")
			.appendLine(" v_indexA := INSTR(p_stringA, p_delimiterA, v_startA); ") 
			.appendLine(" IF v_indexA = 0  ")
			.appendLine(" THEN  ")
			.appendLine(" v_strsA :=SUBSTR(p_stringA, v_startA);  ")
			.appendLine(" v_startA := v_lengthA + 1;  ")
			.appendLine(" ELSE  ")
			.appendLine(" v_strsA :=SUBSTR(p_stringA, v_startA, v_indexA - v_startA);  ")
			.appendLine(" v_startA := v_indexA + 1;  ")
			.appendLine(" END IF;  ")
			.appendLine(" v_startB := 1;  ")
			.appendLine(" v_strsB := '';  ")
			.appendLine("    WHILE(v_startB <= v_lengthB) ") 
			.appendLine("    LOOP  ")
			.appendLine("    v_indexB := INSTR(p_stringB, p_delimiterB, v_startB);  ")
			.appendLine("   IF v_indexB = 0  ")
			.appendLine("   THEN  ")
			.appendLine("   v_strsB :=SUBSTR(p_stringB, v_startB);  ")
			.appendLine("   v_startB := v_lengthB + 1;  ")
			.appendLine("   ELSE  ")
			.appendLine("   v_strsB :=SUBSTR(p_stringB, v_startB, v_indexB - v_startB); ") 
			.appendLine("    v_startB := v_indexB + 1;  ")
			.appendLine("   END IF;  ")
			.appendLine("   IF v_strsB = v_strsA then  ")
			.appendLine("   return 1;  ")
			.appendLine("   end if;  ")
			.appendLine("   END LOOP;  ")
			.appendLine(" END LOOP;  ")
			.appendLine(" RETURN v_result;  ")
			.appendLine(" END isContain; ")
			.appendLine(" END PKG_FUN_ISCONTAIN;")
			.end_SQL()
			.build(UpdateType.REQUEST, "82090", "包体创建"," ","2020-04-17");
	
	}
	
	
	/**
	 * STORY #63208 人保资产-导给委托人的底层数据，按照3.0系统方式提供【接口需求16】 
	 */
	private void pkgFunGetfjkmbyMapInPackage() throws Exception{
		
		pkgBuilder.createOrReplacePackage("PKG_FUN_GETFJKMBYMAP")
		.begin_SQL()
		.appendLine(" CREATE OR REPLACE PACKAGE PKG_FUN_GETFJKMBYMAP AS ")
		.appendLine(" FUNCTION GETFJKMBYMAP(p_stringA in varchar2,p_stringB in varchar2,p_delimiterA in varchar2,p_delimiterB in varchar2) return number; ")
		.appendLine(" END PKG_FUN_GETFJKMBYMAP;")
		.end_SQL()
		.build(UpdateType.REQUEST, "82090", "包头创建"," ","2020-04-17");
		
		pkgBuilder.createOrReplacePackageBody("PKG_FUN_GETFJKMBYMAP")
		.begin_SQL()
		.appendLine(" CREATE OR REPLACE FUNCTION PKG_FUN_GETFJKMBYMAP ")
		.appendLine("               ( var_fjjdm   IN VARCHAR2, ")
		.appendLine("               var_Km_Code IN VARCHAR2, ")
		.appendLine("               var_len     IN NUMBER) ")
		.appendLine("  return VARCHAR2 is R_FJ_KM VARCHAR2(50); ")
		.appendLine(" R_KM VARCHAR2(50); ")
		.appendLine(" begin ")
		.appendLine("     begin ")
		.appendLine(" select facctcode into R_KM from md_km_map a where a.fjjdm = var_fjjdm and substr(c_km_code,0,var_len) = var_Km_Code ")
		.appendLine(" and trim(facctcode) is not null and rownum = 1; ")
		.appendLine(" exception when no_data_found then R_FJ_KM := var_Km_Code; ")
		.appendLine("  end; ")
		.appendLine(" begin ")
		.appendLine("      if (var_len = 4) then ")
		.appendLine("          R_FJ_KM := substr(R_KM,0,4); ")
		.appendLine("      elsif (var_len = 7) then ")
		.appendLine("          R_FJ_KM := substr(R_KM,0,6); ")
		.appendLine("      elsif (var_len = 10) then ")
		.appendLine("          R_FJ_KM := substr(R_KM,0,8); ")
		.appendLine("      elsif (var_len = 13) then  ")
		.appendLine("          R_FJ_KM := substr(R_KM,0,10); ")
		.appendLine("      elsif (var_len = 17) then  ")
		.appendLine("         R_FJ_KM := substr(R_KM,0,13); ")
		.appendLine("      else							    ")
		.appendLine("         R_FJ_KM := var_Km_Code;       ")
		.appendLine("      end if;						    ")
		.appendLine("      exception when no_data_found  then R_FJ_KM := var_Km_Code; ")
		.appendLine("    end;                               ")
		.appendLine("     return R_FJ_KM;  ")
		.appendLine("  end PKG_FUN_GETFJKMBYMAP;                     ");
		
	}
	
	
	
}
