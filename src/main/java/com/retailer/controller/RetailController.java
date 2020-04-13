package com.retailer.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;


import javax.xml.bind.JAXBException;

import org.apache.tomcat.util.http.parser.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	

	static Map<String, Map<Date, BigDecimal>> TransactionMap = new HashMap<String, Map<Date, BigDecimal>>();
	static Map<String, Integer> rewardMap = new HashMap<String, Integer>();
	
	
	@RequestMapping(value = "/Transaction", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public void addTransaction(@RequestBody Transaction Transaction) {
		
		retailService.addTransaction(Transaction, TransactionMap, rewardMap);
			
	}
	
	@RequestMapping(value = "/totalRewardPerCustomer", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Map<String, Integer> totalrewardpoints() {
		
		
		return rewardMap;
		
	}
	
	
	@RequestMapping(value = "/rewardslist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<SummaryTransaction> rewardsList() {
		
		
		return retailService.getAllTransaction(TransactionMap, rewardMap);
		
	}
	
	@RequestMapping(value = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public Map<String, Map<Date, BigDecimal>> TransactionList() {
		
		
		return TransactionMap;
		
	}
	
	


}

