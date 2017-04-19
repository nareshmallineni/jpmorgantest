package com.jpmorgan.report.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.GregorianCalendar;

import com.jpmorgan.report.pojo.Entity;

public class WorkWeekUtil {
	public static boolean isWorkWeekForCurrency(String currency, java.util.Date settlementDate) {
		if ("SAR".equals(currency) || "AED".equals(currency)) {
			boolean test = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.FRIDAY)
					.contains(new java.sql.Date(settlementDate.getTime()).toLocalDate().getDayOfWeek());
			return test;
		} else {
			boolean test = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
					.contains(new java.sql.Date(settlementDate.getTime()).toLocalDate().getDayOfWeek());
			return test;
		}
	}
	
	public static java.util.Date getWorkingSettlementDate(Entity entity){
		LocalDate settleDate = new java.sql.Date(entity.getSettlementDate().getTime()).toLocalDate();
		Calendar cal = new GregorianCalendar();
		cal.setTime(entity.getSettlementDate());
		if((DayOfWeek.FRIDAY.equals(settleDate.getDayOfWeek()) && ("SAR".equals(entity.getCurrency())||"AED".equals(entity.getCurrency())))
				|| (DayOfWeek.SATURDAY.equals(settleDate.getDayOfWeek()) && !("SAR".equals(entity.getCurrency()) || "AED".equals(entity.getCurrency())))){
			cal.add(Calendar.DATE, 2);
			return cal.getTime();
		}else{
			cal.add(Calendar.DATE, 1);
			return cal.getTime();
		}
	}
}