package com.retailer.attributes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.retailer.attributes.Transaction;
import com.retailer.service.retailService;

@SpringBootTest
class RetailerApplicationTests {
	
	@Autowired
	public retailService retailService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void addTransaction() {
		
		Map<String, Map<Date, BigDecimal>> TransactionMap = new HashMap<String, Map<Date, BigDecimal>>();
		Map<String, Integer> rewardMap = new HashMap<String, Integer>();
		
		Transaction tx = new Transaction("ankit", new BigDecimal("80"), new GregorianCalendar(2019, 11, 10).getTime());
		
		retailService.addTransaction(tx, TransactionMap, rewardMap);
		
		System.out.println("TransactionList - " + "\n" + TransactionMap.toString());
		System.out.println("rewardList - " + "\n" + rewardMap.toString());
		
	}
	
	@Test
	void addTransactionTransactionNull() {
		
		Map<String, Map<Date, BigDecimal>> TransactionMap = new HashMap<String, Map<Date, BigDecimal>>();
		Map<String, Integer> rewardMap = new HashMap<String, Integer>();
		
		Transaction tx = new Transaction("ankit", null, new GregorianCalendar(2019, 11, 10).getTime());
		
		retailService.addTransaction(tx, TransactionMap, rewardMap);
		
		System.out.println("TransactionList - " + "\n" + TransactionMap.toString());
		System.out.println("rewardList - " + "\n" + rewardMap.toString());
		
	}
	
	@Test
	void addTransactionTransactionBlank() {
		
		Map<String, Map<Date, BigDecimal>> TransactionMap = new HashMap<String, Map<Date, BigDecimal>>();
		Map<String, Integer> rewardMap = new HashMap<String, Integer>();
		
		Transaction tx = new Transaction("ankit", new BigDecimal(""), new GregorianCalendar(2019, 11, 10).getTime());
		
		retailService.addTransaction(tx, TransactionMap, rewardMap);
		
		System.out.println("TransactionList - " + "\n" + TransactionMap.toString());
		System.out.println("rewardList - " + "\n" + rewardMap.toString());
		
	}
	
	@Test
	void addTransactionDateNull() {
		
		Map<String, Map<Date, BigDecimal>> TransactionMap = new HashMap<String, Map<Date, BigDecimal>>();
		Map<String, Integer> rewardMap = new HashMap<String, Integer>();
		
		Transaction tx = new Transaction("mehra", new BigDecimal("100"), null);
		
		retailService.addTransaction(tx, TransactionMap, rewardMap);
		
		System.out.println("TransactionList - " + "\n" + TransactionMap.toString());
		System.out.println("rewardList - " + "\n" + rewardMap.toString());
		
	}
	
	

}
