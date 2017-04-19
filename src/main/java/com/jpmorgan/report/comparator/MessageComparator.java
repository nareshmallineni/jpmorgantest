package com.jpmorgan.report.comparator;

import java.util.Comparator;

import com.jpmorgan.report.pojo.Entity;

public class MessageComparator implements Comparator<Entity>{

	public int compare(Entity o1, Entity o2) {
		return -(o1.getPriceInUsd().compareTo(o2.getPriceInUsd()));
	}
	
}
