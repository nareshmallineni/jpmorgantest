package com.jpmorgan.report.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.jpmorgan.report.enums.BuyOrSellIndicator;

public class Entity {
	
	private String entityName;
	
	private BuyOrSellIndicator buyOrSellIndicator;
	
	private BigDecimal agreedFxRate;
	
	private String currency;
	
	private Date instructionDate;
	
	private Date settlementDate;
	
	private Long noOfUnits;
	
	private BigDecimal pricePerUnit;
	
	private BigDecimal priceInUsd;

	public BigDecimal getPriceInUsd() {
		return priceInUsd;
	}

	public void setPriceInUsd(BigDecimal priceInUsd) {
		this.priceInUsd = priceInUsd;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public BuyOrSellIndicator getBuyOrSellIndicator() {
		return buyOrSellIndicator;
	}

	public void setBuyOrSellIndicator(BuyOrSellIndicator buyOrSellIndicator) {
		this.buyOrSellIndicator = buyOrSellIndicator;
	}

	public BigDecimal getAgreedFxRate() {
		return agreedFxRate;
	}

	public void setAgreedFxRate(BigDecimal agreedFxRate) {
		this.agreedFxRate = agreedFxRate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Long getNoOfUnits() {
		return noOfUnits;
	}

	public void setNoOfUnits(Long noOfUnits) {
		this.noOfUnits = noOfUnits;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(BigDecimal pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyOrSellIndicator == null) ? 0 : buyOrSellIndicator.hashCode());
		result = prime * result + ((entityName == null) ? 0 : entityName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (buyOrSellIndicator != other.buyOrSellIndicator)
			return false;
		if (entityName == null) {
			if (other.entityName != null)
				return false;
		} else if (!entityName.equals(other.entityName))
			return false;
		return true;
	}

	
}
