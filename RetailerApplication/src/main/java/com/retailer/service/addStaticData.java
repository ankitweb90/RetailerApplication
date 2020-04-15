package com.retailer.service;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.retailer.attributes.Transaction;

public class addStaticData {
	
	@Autowired
	public retailService retailService;
	
	
	
	public void data(Map<String, Map<Date, BigDecimal>> TransactionMap, Map<String, Integer> rewardMap) {
		

		
//		Map<Date, BigDecimal> mp = new HashMap<Date, BigDecimal>();
//		mp.put(new GregorianCalendar(2020,01,11).getTime(), new BigDecimal("140.39")); 
//		TransactionMap.put("David", mp);
		
		
		
		
		
	}

}
