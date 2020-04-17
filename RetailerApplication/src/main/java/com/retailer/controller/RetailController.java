package com.retailer.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;


import javax.xml.bind.JAXBException;

import org.apache.tomcat.util.http.parser.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

import com.retailer.attributes.SummaryTransaction;
import com.retailer.attributes.Transaction;
import com.retailer.service.retailService;


@RestController
@RequestMapping(value = "/retail/")
public class RetailController {
	
	
	@Autowired
	public retailService retailService;

	static Map<String, Map<String, BigDecimal>> TransactionMap = new HashMap<String, Map<String, BigDecimal>>();
	static Map<String, Integer> rewardMap = new HashMap<String, Integer>();
	
	
	//static data addition for demo
	static {
		

		try {
			retailService rs = new retailService();
			Transaction tx1 = new Transaction("David", new BigDecimal(140.39), "2020-01-01");
			rs.addTransaction(tx1, TransactionMap, rewardMap);
			Transaction tx2 = new Transaction("David", new BigDecimal(100.00), "2020-02-03");
			rs.addTransaction(tx2, TransactionMap, rewardMap);
			Transaction tx3 = new Transaction("David", new BigDecimal(60.10), "2020-01-25");
			rs.addTransaction(tx3, TransactionMap, rewardMap);
			Transaction tx4 = new Transaction("David", new BigDecimal(30.00), "2020-03-13");
			rs.addTransaction(tx4, TransactionMap, rewardMap);
			Transaction tx5 = new Transaction("Sam", new BigDecimal(120.19), "2020-01-06");
			rs.addTransaction(tx5, TransactionMap, rewardMap);
			Transaction tx6 = new Transaction("Sam", new BigDecimal(70.00), "2020-02-11");
			rs.addTransaction(tx6, TransactionMap, rewardMap);
			Transaction tx7 = new Transaction("Ankit", new BigDecimal(90.00), "2020-01-31");
			rs.addTransaction(tx7, TransactionMap, rewardMap);
			Transaction tx8 = new Transaction("Ankit", new BigDecimal(130.00), "2020-01-09");
			rs.addTransaction(tx8, TransactionMap, rewardMap);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/Transaction", method = RequestMethod.POST)
	public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction Transaction) throws ParseException {
		
		
		if(retailService.isValidFormat("yyyy-MM-dd", Transaction.getTransactionDate())) {
		
		retailService.addTransaction(Transaction, TransactionMap, rewardMap);
		return new ResponseEntity<Transaction>(Transaction, HttpStatus.OK);
		}
		
		return new ResponseEntity<Transaction>(Transaction, HttpStatus.BAD_REQUEST);
			
	}
	
	@RequestMapping(value = "/totalRewardPerCustomer", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Map<String, Integer> totalrewardpoints() {
		
		
		return rewardMap;
		
	}
	
	
	@RequestMapping(value = "/rewardslist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<SummaryTransaction> rewardsList() throws ParseException {
		
		
		return retailService.getAllTransaction(TransactionMap, rewardMap);
		
	}
	
	@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Map<String, Map<String, BigDecimal>> TransactionList() {
		
		
		return TransactionMap;
		
	}
	
	


}

