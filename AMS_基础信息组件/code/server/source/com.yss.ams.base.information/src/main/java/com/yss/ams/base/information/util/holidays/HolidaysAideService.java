package com.yss.ams.base.information.util.holidays;

import java.util.Calendar;
import java.util.Date;

import com.yss.ams.base.information.support.util.holidays.HolidaysAide;
import com.yss.ams.base.information.support.util.holidays.IHolidaysAideService;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.util.YssFun;

public class HolidaysAideService implements IHolidaysAideService{

	@Override
	public boolean isHoliday(Date date, String holidaysCode) throws YssException {
		return HolidaysAide.isHoliday(date, holidaysCode);
	}

	@Override
	public Date getWorkDayByHolidayCode(Date specifiedDate, int offset, String holidayCode)
			throws YssException {
		return HolidaysAide.getWorkDayByHolidayCode(specifiedDate, offset, holidayCode);
	}

	/**
	 * Author : ChenLong
	 * Date   : 2017-03-23
	 * Status : Add
	 * Comment: STORY #39265 商品期权业务
	 * IF 标的品种 =M   
   	 * 标的期货合约交割月份前一个月的第5个交易日.
   	 * IF 标的品种 =SR
   	 * 标的期货合约交割月份前二个月的倒数第5个交易日    
     * 标的期货合约交割月份取文件中的‘标的合约’的后4位数字中的最后2位。特别注意如果后2位为01时，前一个月是上一前度12月。上一年度取后4位数字中的前2位。例如M1801,上一个月为2017年12月.
     * 如果只有3位数值，后2位表示月份。第1位表示年份的最后一位，年份的其他3位根据当前年份获取，需要特别注意：当前年份2019年，第一位数值为3，生成的年份应该是2023。IIF（当年年份最后1位>=文件中第一位，当前年份前3位+1&文件中第1位，当前年份前3位&文件中第1位)  
	 * @param bdhy 标的合约
	 * @param marketCode 市场代码
	 * @param bdpz 标的品种
	 * @return
	 * @throws YssException
	 */
	public Date getLastTradeDate(String bdhy,String marketCode,String bdpz) throws YssException{
		Date date = null;
		String holidaysCode = HolidaysAide.getHolidaysCode(marketCode);
		String time = getTime(bdhy);
		/* 日期增减方向  */
		int way = 1;
		/* 前几个月 */
		int preMonth = 0;
		/*白糖的 最后交易日是前2个月的倒是第5个工作日 */
		if("SR".equals(bdpz)){			
			way = -1;
			preMonth = 2;
		}else{
			way  = 1;
			preMonth = 1;
		}
		
		if(time.length() == 3){
			date = getDateForThreeNumber(time,preMonth,way);
		}else if(time.length() == 4){
			date = getDateForFourNumber(time,preMonth,way);
		}
		
		int count = 0;
		while(true){
			if(!HolidaysAide.isHoliday(date, holidaysCode)){
				count++;
			}
			
			if(count == 5){
				break;
			}
			
			date = YssFun.addDay(date, way);
		}
		
		return date;
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2017-03-31
	 * Status : Add
	 * Comment: 处理四位数字的时间
	 * @param time
	 * @param preMonth 往前几个月
	 * @param way 1或-1
	 * @return
	 */
	private Date getDateForFourNumber(String time,int preMonth,int way){
		int year = Integer.valueOf(time.substring(0,2));
		int month = Integer.valueOf(time.substring(2,4));
		Calendar calendar = Calendar.getInstance();
		int currYear = calendar.get(1);
		year = currYear / 100 * 100 + year;
		month = month - preMonth;
		int day = 1;
		if(way == 1){
			month = month - 1;
		}else{
			day  = 0;
		}
		
		calendar.set(year, month ,day);
		return calendar.getTime();
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2017-03-31
	 * Status : Add
	 * Comment: 处理三位位数字的时间
	 * @param time
	 * @param preMonth 往前几个月
	 * @param way 1或-1
	 * @return
	 */
	private Date getDateForThreeNumber(String time,int preMonth,int way){
		int year = Integer.valueOf(time.substring(0,1));
		int month = Integer.valueOf(time.substring(1,3));
		Calendar calendar = Calendar.getInstance();
		int currYear = calendar.get(1);
		int subNum = currYear % 10;
		int preNum = currYear / 10;
		
		if(subNum >= year){
			preNum = preNum * 10 + 10;
		}else{
			preNum = preNum * 10;
		}
		
		year = preNum + year;
		month = month - preMonth;
		int day = 1;
		if(way == 1){
			month = month - 1;
		}else{
			day  = 0;
		}
		
		calendar.set(year, month ,day);
		return calendar.getTime();
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2017-03-31
	 * Status : Add
	 * Comment: 取出字符串后几位的数字
	 * @param bdpz 标的品种
	 * @return
	 */
	private String getTime(String bdpz){
		StringBuilder strBuilder = new StringBuilder();
		int length = bdpz.length();
		for(int i = length - 1;i >= 0;i--){
			char value = bdpz.charAt(i);
			if(Character.isDigit(value)){
				strBuilder.append(value);
			}else{
				break;
			}
		}
		return strBuilder.reverse().toString();
	}

	@Override
	public Date getWorkDay(Date specifiedDate, String holidaysCode, int offset) throws YssException {
		return HolidaysAide.getWorkDay(specifiedDate, holidaysCode, offset);
	}
	
}
