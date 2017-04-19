package com.jpmorgan.report.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jpmorgan.report.comparator.MessageComparator;
import com.jpmorgan.report.pojo.Entity;

public class MessageStore {
	
	private MessageStore(){
		
	}
	
	public static MessageStore getInstance(){
		return INSTANCE;
	}
	
	private static final MessageStore INSTANCE = new MessageStore();
	
	public static List<Entity> outGoingEntityList = new ArrayList<Entity>();
	
	public static List<Entity> incomingEntityList = new ArrayList<Entity>();
	
	MessageComparator comparator = new MessageComparator();
	
	public void storeOutgoingEntities(Entity entity){
		outGoingEntityList.add(entity);
		Collections.sort(outGoingEntityList,comparator);
	}
	
	public void storeIncomingEntities(Entity entity){
		incomingEntityList.add(entity);
		Collections.sort(incomingEntityList,comparator);
	}
}
