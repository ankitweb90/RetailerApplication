package com.retailer.attributes;

import java.math.BigDecimal;
import java.text.ParseException;
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
	void addTransaction() throws ParseException {
		
		Map<String, Map<String, BigDecimal>> TransactionMap = new HashMap<String, Map<String, BigDecimal>>();
		Map<String, Integer> rewardMap = new HashMap<String, Integer>();
		
		Transaction tx = new Transaction("ankit", new BigDecimal("80"),"2019-11-10");
		
		retailService.addTransaction(tx, TransactionMap, rewardMap);
		
		System.out.println("TransactionList - " + "\n" + TransactionMap.toString());
		System.out.println("rewardList - " + "\n" + rewardMap.toString());
		
	}
	
	@Test
	void addTransactionTransactionNull() throws ParseException {
		
		Map<String, Map<String, BigDecimal>> TransactionMap = new HashMap<String, Map<String, BigDecimal>>();
		Map<String, Integer> rewardMap = new HashMap<String, Integer>();
		
		Transaction tx = new Transaction("ankit", null, "2019-11-10");
		
		retailService.addTransaction(tx, TransactionMap, rewardMap);
		
		System.out.println("TransactionList - " + "\n" + TransactionMap.toString());
		System.out.println("rewardList - " + "\n" + rewardMap.toString());
		
	}
	
	@Test
	void addTransactionTransactionBlank() throws ParseException {
		
		Map<String, Map<String, BigDecimal>> TransactionMap = new HashMap<String, Map<String, BigDecimal>>();
		Map<String, Integer> rewardMap = new HashMap<String, Integer>();
		
		Transaction tx = new Transaction("ankit", new BigDecimal(""), "2019-11-10");
		
		retailService.addTransaction(tx, TransactionMap, rewardMap);
		
		System.out.println("TransactionList - " + "\n" + TransactionMap.toString());
		System.out.println("rewardList - " + "\n" + rewardMap.toString());
		
	}
	
	@Test
	void addTransactionDateNull() throws ParseException {
		
		Map<String, Map<String, BigDecimal>> TransactionMap = new HashMap<String, Map<String, BigDecimal>>();
		Map<String, Integer> rewardMap = new HashMap<String, Integer>();
		
		Transaction tx = new Transaction("mehra", new BigDecimal("100"), null);
		
		retailService.addTransaction(tx, TransactionMap, rewardMap);
		
		System.out.println("TransactionList - " + "\n" + TransactionMap.toString());
		System.out.println("rewardList - " + "\n" + rewardMap.toString());
		
	}
	
	

}
