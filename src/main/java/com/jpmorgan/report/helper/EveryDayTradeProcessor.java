package com.jpmorgan.report.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jpmorgan.report.enums.BuyOrSellIndicator;
import com.jpmorgan.report.processor.Processor;
import com.jpmorgan.report.pojo.Entity;

public class EveryDayTradeProcessor implements Processor {

	private static EveryDayTradeProcessor INSTANCE = new EveryDayTradeProcessor();

	private Map<Date, BigDecimal> incomingSettlementMap = new HashMap<Date, BigDecimal>();

	private Map<Date, BigDecimal> outgoingSettlementMap = new HashMap<Date, BigDecimal>();

	public static EveryDayTradeProcessor getInstance() {
		return INSTANCE;
	}

	public void process(Entity entity) {
		if (BuyOrSellIndicator.SELL.equals(entity.getBuyOrSellIndicator())) {
			setIncomingSettlement(entity.getSettlementDate(), entity.getPriceInUsd());
		} else if (BuyOrSellIndicator.BUY.equals(entity.getBuyOrSellIndicator())) {
			setOutgoingSettlement(entity.getSettlementDate(), entity.getPriceInUsd());
		}
	}

	private void setIncomingSettlement(Date settlementDate, BigDecimal settlementAmount) {
		if (incomingSettlementMap.get(settlementDate) != null) {
			BigDecimal oldSettlementAmount = incomingSettlementMap.get(settlementDate);
			BigDecimal newSettlementAmount = oldSettlementAmount.add(settlementAmount);
			incomingSettlementMap.put(settlementDate, newSettlementAmount);
		} else {
			incomingSettlementMap.put(settlementDate, settlementAmount);
		}
	}

	private void setOutgoingSettlement(Date settlementDate, BigDecimal settlementAmount) {
		if (outgoingSettlementMap.get(settlementDate) != null) {
			BigDecimal oldSettlementAmount = outgoingSettlementMap.get(settlementDate);
			BigDecimal newSettlementAmount = oldSettlementAmount.add(settlementAmount);
			outgoingSettlementMap.put(settlementDate, newSettlementAmount);
		} else {
			outgoingSettlementMap.put(settlementDate, settlementAmount);
		}
	}

	public Map<Date, BigDecimal> getTodaysIncomingAggregate() {
		return incomingSettlementMap;
	}

	public Map<Date, BigDecimal> getTodaysOutgoingAggregate() {
		return outgoingSettlementMap;
	}
}
