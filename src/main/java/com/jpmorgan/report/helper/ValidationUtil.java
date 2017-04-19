package com.jpmorgan.report.helper;

import java.math.BigDecimal;

import com.jpmorgan.report.enums.BuyOrSellIndicator;
import com.jpmorgan.report.exception.MessageException;
import com.jpmorgan.report.pojo.Entity;

public class ValidationUtil {
	public void validate(Entity entity){
		if(entity == null){
			throw new MessageException("Trade Message is empty");
		}
		if(entity.getEntityName()== null || entity.getEntityName().length() == 0){
			throw new MessageException("Trade stock name missing");
		}
		if(entity.getAgreedFxRate().compareTo(new BigDecimal("0.01")) < 0){
			throw new MessageException("Invalid Forex Rate");
		}
		if(entity.getNoOfUnits() <= 0){
			throw new MessageException("Units cannot be 0 or less than 0");
		}
		if(!BuyOrSellIndicator.BUY.equals(entity.getBuyOrSellIndicator()) && !BuyOrSellIndicator.SELL.equals(entity.getBuyOrSellIndicator())){
			throw new MessageException("Unable to understand the action. Should be BUY or SELL");
		}
		if(entity.getInstructionDate().compareTo(entity.getSettlementDate()) > 0){
			throw new MessageException("Instruction Date cannot be after settlement date");
		}
		if(entity.getPricePerUnit().compareTo(new BigDecimal("0.01")) < 0){
			throw new MessageException("Invalid price per unit");
		}
		if("USD".equals(entity.getCurrency()) && new BigDecimal("1.00").compareTo(entity.getAgreedFxRate()) != 0){
			throw new MessageException("FX rate should be 1 for USD currency");
		}
	}
}
