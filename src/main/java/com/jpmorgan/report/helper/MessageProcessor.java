package com.jpmorgan.report.helper;

import java.math.BigDecimal;
import java.util.List;

import com.jpmorgan.report.enums.BuyOrSellIndicator;
import com.jpmorgan.report.storage.MessageStore;
import com.jpmorgan.report.pojo.Entity;
import com.jpmorgan.report.processor.Processor;

public class MessageProcessor implements Processor {
	
	public MessageProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Validation                                    //ValidationUtil called to validate if entity is perfect
	public void process(Entity entity){
		
		BigDecimal totalPrice = getTotalPrice(entity);
		
		entity.setPriceInUsd(getPriceInUSD(totalPrice, entity));
		
		MessageStore mStore = MessageStore.getInstance(); // Singleton instance of message store
		
		if(WorkWeekUtil.isWorkWeekForCurrency(entity.getCurrency(), entity.getSettlementDate())){ //check if falling on weekend
			entity.setSettlementDate(WorkWeekUtil.getWorkingSettlementDate(entity)); //adjust settlement date to next working day
		}
		
		if(BuyOrSellIndicator.SELL.equals(entity.getBuyOrSellIndicator())){
			if(mStore.incomingEntityList.contains(entity)){		//if the list already contains the trade entity, update the entity
				updateEntity(mStore.incomingEntityList, entity);
			}else{
			mStore.storeIncomingEntities(entity);
			}
		}else{
			if(mStore.outGoingEntityList.contains(entity)){
				updateEntity(mStore.outGoingEntityList, entity);
			}else{
			mStore.storeOutgoingEntities(entity);
			}
		}
	}
	
	private BigDecimal getTotalPrice(Entity entity){
		return new BigDecimal(entity.getNoOfUnits()).multiply(entity.getPricePerUnit());
	}
	
	private BigDecimal getPriceInUSD(BigDecimal totalPrice,Entity entity){
		return totalPrice.multiply(entity.getAgreedFxRate());
	}
	
	private void updateEntity(List<Entity> entityList, Entity entity){
		int index = entityList.indexOf(entity);
		Entity oldEntity = entityList.get(index);
		oldEntity.setNoOfUnits(oldEntity.getNoOfUnits()+ entity.getNoOfUnits());
		oldEntity.setPriceInUsd(oldEntity.getPriceInUsd().add(entity.getPriceInUsd()));
		entityList.remove(index);
		entityList.add(oldEntity);
	}
}
