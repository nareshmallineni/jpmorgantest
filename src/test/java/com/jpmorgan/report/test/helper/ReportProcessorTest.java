package com.jpmorgan.report.test.helper;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jpmorgan.report.enums.BuyOrSellIndicator;
import com.jpmorgan.report.helper.EveryDayTradeProcessor;
import com.jpmorgan.report.pojo.Entity;
import com.jpmorgan.report.processor.Processor;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ReportProcessorTest {
	Entity entity = new Entity();
	Entity entity2 = new Entity();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@Before
	public void setUp() throws ParseException {
		entity.setEntityName("foo");
		entity.setBuyOrSellIndicator(BuyOrSellIndicator.BUY);
		entity.setCurrency("SGP");
		entity.setAgreedFxRate(new BigDecimal("0.5"));
		entity.setPricePerUnit(new BigDecimal("100.25"));
		entity.setNoOfUnits(200L);
		entity.setInstructionDate(dateFormat.parse("01/01/2016"));
		entity.setSettlementDate(dateFormat.parse("02/01/2016"));
		
		entity2.setEntityName("bar");
		entity2.setBuyOrSellIndicator(BuyOrSellIndicator.BUY);
		entity2.setCurrency("USD");
		entity2.setAgreedFxRate(new BigDecimal("0.22"));
		entity2.setPricePerUnit(new BigDecimal("150.5"));
		entity2.setNoOfUnits(450L);
		entity2.setInstructionDate(dateFormat.parse("05/01/2016"));
		entity2.setSettlementDate(dateFormat.parse("07/01/2016"));
	}
	
	@Test
	public void checkForTodaysCollection() throws ParseException {
		Processor processor = Processor.newProcessor();
		processor.process(entity);
		entity2.setAgreedFxRate(new BigDecimal("1.0"));
		processor.process(entity2);
		EveryDayTradeProcessor everydayProcessor = EveryDayTradeProcessor.getInstance();
		assertEquals(everydayProcessor.getTodaysOutgoingAggregate().get(dateFormat.parse("07/01/2016")).compareTo(new BigDecimal("67725.00")), 0);
	}
}
