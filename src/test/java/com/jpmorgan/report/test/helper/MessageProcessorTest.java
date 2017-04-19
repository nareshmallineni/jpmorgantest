package com.jpmorgan.report.test.helper;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jpmorgan.report.helper.WorkWeekUtil;
import org.junit.Assert;
import org.junit.Test;

import com.jpmorgan.report.pojo.Entity;

public class MessageProcessorTest {
	Entity entity = new Entity();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Test
	public void checkIfDateFallsOnWeekend() throws ParseException {
		Date date = dateFormat.parse("01/01/2016");
		Assert.assertEquals(WorkWeekUtil.isWorkWeekForCurrency("USD", date),false);
	}
	
	@Test
	public void checkAdjustedSettlementDateForUSD() throws ParseException {
		Date date = dateFormat.parse("02/01/2016");
		entity.setCurrency("USD");
		entity.setSettlementDate(date);
		assertEquals(WorkWeekUtil.getWorkingSettlementDate(entity).compareTo(dateFormat.parse("02/01/2016")), 1);
	}
	
	@Test
	public void checkAdjustedSettlementDateForAED() throws ParseException {
		Date date = dateFormat.parse("02/11/2016");
		entity.setCurrency("AED");
		entity.setSettlementDate(date);
		assertEquals(WorkWeekUtil.getWorkingSettlementDate(entity).compareTo(dateFormat.parse("02/11/2016")), 1);
	}
}
