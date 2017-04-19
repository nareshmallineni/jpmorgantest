package com.jpmorgan.report.main;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.jpmorgan.report.enums.BuyOrSellIndicator;
import com.jpmorgan.report.processor.Processor;
import com.jpmorgan.report.helper.EveryDayTradeProcessor;
import com.jpmorgan.report.helper.ReportProcessor;
import com.jpmorgan.report.pojo.Entity;

public class MainReportProcessor {
	public static void main(String args[]) throws ParseException {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Entity entity1 = new Entity();
		entity1.setEntityName("foo");
		entity1.setBuyOrSellIndicator(BuyOrSellIndicator.BUY);
		entity1.setCurrency("SGP");
		entity1.setAgreedFxRate(new BigDecimal("0.50"));
		entity1.setPricePerUnit(new BigDecimal("100.25"));
		entity1.setNoOfUnits(200L);
		entity1.setInstructionDate(dateFormat.parse("01/01/2016"));		;
		entity1.setSettlementDate(dateFormat.parse("02/01/2016"));
		
		Entity entity2 = new Entity();
		entity2.setEntityName("bar");
		entity2.setBuyOrSellIndicator(BuyOrSellIndicator.SELL);
		entity2.setCurrency("AED");
		entity2.setAgreedFxRate(new BigDecimal("0.22"));
		entity2.setPricePerUnit(new BigDecimal("150.5"));
		entity2.setNoOfUnits(450L);
		entity2.setInstructionDate(dateFormat.parse("05/01/2016"));
		entity2.setSettlementDate(dateFormat.parse("07/01/2016"));
		
		Processor processor =   Processor.newProcessor();
		//EveryDayTradeProcessor process = EveryDayTradeProcessor.getInstance();
		//process.process(entity1);
		processor.process(entity1);
		
		processor.process(entity2);
		//process.process(entity2);

		Processor reportProcessor = new ReportProcessor();
		reportProcessor.process(entity1);
	}
}
