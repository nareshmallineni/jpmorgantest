package com.jpmorgan.report.helper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map.Entry;

import com.jpmorgan.report.processor.Processor;
import com.jpmorgan.report.storage.MessageStore;
import com.jpmorgan.report.pojo.Entity;

public class ReportProcessor implements Processor {
	public void process(Entity entity){
		EveryDayTradeProcessor everyDayProcessor = EveryDayTradeProcessor.getInstance();
		System.out.println("################################## INCOMING MESSAGES ##################################");
		for(int i = 0; i < MessageStore.incomingEntityList.size() ; i++){
			Entity incomingEntity = MessageStore.incomingEntityList.get(i);
			System.out.println("Rank "+ (int)(i+1) + "   Name ["+incomingEntity.getEntityName()+"]" +
					" Amount Settle In USD ["+incomingEntity.getPriceInUsd() + "]");
		}
		System.out.println("################################## OUTGOING MESSAGES ##################################");
		for(int i = 0; i < MessageStore.outGoingEntityList.size() ; i++){
			Entity outgoingEntity = MessageStore.outGoingEntityList.get(i);
			System.out.println("Rank "+ (int)(i+1) + "   Name ["+outgoingEntity.getEntityName()+"] " +
					"Amount Settle In USD ["+outgoingEntity.getPriceInUsd()+ "]");
		}
		System.out.println("################################## OUTGOING MESSAGES DAYWISE SETTLEMENT ##################################s");
		for(Entry<Date, BigDecimal> entry : everyDayProcessor.getTodaysOutgoingAggregate().entrySet()){
			System.out.println("Date ["+entry.getKey()+"] --------------"+"Amount Settled ["+entry.getValue()+ "]");
		}
		System.out.println("-----------------------------------INCOMING MESSAGES DAYWISE SETTLEMENT------------------------------------");
		for(Entry<Date, BigDecimal> entry : everyDayProcessor.getTodaysIncomingAggregate().entrySet()){
			System.out.println("Date ["+entry.getKey()+"] --------------"+"Amount Settled ["+entry.getValue() + "]");
		}
	}
}
