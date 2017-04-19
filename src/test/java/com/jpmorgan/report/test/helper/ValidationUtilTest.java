package com.jpmorgan.report.test.helper;

import java.math.BigDecimal;
import java.util.Date;

import com.jpmorgan.report.enums.BuyOrSellIndicator;
import com.jpmorgan.report.helper.ValidationUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.jpmorgan.report.exception.MessageException;
import com.jpmorgan.report.pojo.Entity;

public class ValidationUtilTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	Entity entity = new Entity();
	@Before
	public void setUp(){
		entity.setEntityName("BAR");
		entity.setBuyOrSellIndicator(BuyOrSellIndicator.BUY);
		entity.setCurrency("EUR");
		entity.setAgreedFxRate(new BigDecimal("5.00"));
		entity.setPricePerUnit(new BigDecimal("2.5"));
		entity.setNoOfUnits(100L);
		entity.setInstructionDate(new Date("11/10/2000"));
		entity.setSettlementDate(new Date("02/11/2017"));
	}
	
	@Test(expected = MessageException.class)
	public void checkForEmptyName() {
		entity.setEntityName("");
		ValidationUtil util = new ValidationUtil();
		util.validate(entity);
		expectedEx.expect(RuntimeException.class);
		expectedEx.expectMessage("Trade Message is empty");
	}
	
	//similar tests can be performed for other validations too
}
