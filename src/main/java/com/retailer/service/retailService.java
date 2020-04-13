package com.retailer.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.retailer.attributes.SummaryTransaction;
import com.retailer.attributes.Transaction;

@Service
public class retailService {

	public void addTransaction(Transaction tx, Map<String, Map<Date, BigDecimal>> TransactionMap, Map<String, Integer> rewardMap) {

		if(TransactionMap.containsKey(tx.getCustomerName())) {
			Map<Date, BigDecimal> nestedMap = TransactionMap.get(tx.getCustomerName());
			
			nestedMap.put(tx.getTransactionDate(), tx.getTransactionAmount());
			
			TransactionMap.put(tx.getCustomerName(), nestedMap);
			
			
			updateRewardMap(tx, rewardMap);
			
		}
		else {
			
			Map<Date, BigDecimal> nestedMap = new HashMap<Date, BigDecimal>();
			nestedMap.put(tx.getTransactionDate(), tx.getTransactionAmount());
			
			TransactionMap.put(tx.getCustomerName(), nestedMap);
			
			updateRewardMap(tx, rewardMap);
		}

		
	}
	
	private static void updateRewardMap(Transaction transaction, Map<String, Integer> rewardMap) {
		
		
		BigDecimal txAmount = transaction.getTransactionAmount();
		
		int reward = calculateRewardPoints(txAmount);

		
		if(rewardMap.containsKey(transaction.getCustomerName())) {
			
			rewardMap.put(transaction.getCustomerName(), rewardMap.get(transaction.getCustomerName()) + reward);
		}
		
		else {
			rewardMap.put(transaction.getCustomerName(), reward);
		}
		
	
}

	private static int calculateRewardPoints(BigDecimal txAmount) {
		int reward = 0;
		if(txAmount.compareTo(new BigDecimal("50")) == 1 && 
				txAmount.compareTo(new BigDecimal("100")) == -1 ) {
			
				reward = txAmount.intValue() - 50;

				
		}else if(txAmount.compareTo(new BigDecimal("100")) == 1) {
			
			reward = 2*(txAmount.intValue() - 100)+50;
			
		}else if(txAmount.compareTo(new BigDecimal("100")) == 0){
			reward = txAmount.intValue() - 50;
		}
		

		
		return reward;
	}

	public List<SummaryTransaction> getAllTransaction(Map<String, Map<Date, BigDecimal>> TransactionMap,
													Map<String, Integer> rewardMap) {
	
		
		
		List<SummaryTransaction>  List = new CopyOnWriteArrayList<SummaryTransaction>();
		
		for(Map.Entry<String, Map<Date, BigDecimal>> map : TransactionMap.entrySet()) {
			SummaryTransaction summary = new SummaryTransaction();
			summary.setCustomerName(map.getKey());
		
			List.add(summary);
			
		}
		List<SummaryTransaction> finalList = getRewardsPerMonth(TransactionMap, List);
		
		
		
		
		return finalList;
	}

	private List<SummaryTransaction> getRewardsPerMonth(Map<String, Map<Date, BigDecimal>> TransactionMap,
			List<SummaryTransaction> List) {
	
		List<SummaryTransaction> resultList = new ArrayList<SummaryTransaction>();
		
		
		for(SummaryTransaction tx : List) {
			Map<String, Integer> txmap = new HashMap<>();
			String customerName = tx.getCustomerName();
			int totalRewardPoints=0;
			Map<Date, BigDecimal> nestedMap = TransactionMap.get(customerName);
			
			for(Map.Entry<Date, BigDecimal> map : nestedMap.entrySet()) {
				
				Date date = map.getKey();
				String month = new SimpleDateFormat("MMMM").format(date);
				int rewardspoint = calculateRewardPoints(map.getValue());
				
				if(txmap.containsKey(month)) {
					txmap.put(month, txmap.get(month)+rewardspoint);
				}else {
					txmap.put(month, rewardspoint);
				}
				totalRewardPoints += rewardspoint;
			
				
			}
			tx.setRewardsPerMonth(txmap);
			tx.setTotalRewards(totalRewardPoints);
			resultList.add(tx);
			
		}
		
	return resultList;
		
	}
	
	
	
	
	
	
	

}
