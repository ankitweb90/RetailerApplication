package com.retailer.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import com.retailer.attributes.SummaryTransaction;
import com.retailer.attributes.Transaction;

@Service
public class retailService {

	public void addTransaction(Transaction tx, Map<String, Map<String, BigDecimal>> TransactionMap, Map<String, Integer> rewardMap) throws ParseException {
		
		if(TransactionMap.containsKey(tx.getCustomerName())) {
			
			String strDate = addTimeToDate(tx.getTransactionDate());
			
			Map<String, BigDecimal> nestedMap = TransactionMap.get(tx.getCustomerName());
			nestedMap.put(strDate, tx.getTransactionAmount());
			
			TransactionMap.put(tx.getCustomerName(), nestedMap);	
			
			updateRewardMap(tx, rewardMap);
			
		}
		else {

			String strDate = addTimeToDate(tx.getTransactionDate());
			
			Map<String, BigDecimal> nestedMap = new HashMap<String, BigDecimal>();
			nestedMap.put(strDate, tx.getTransactionAmount());
			
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

	public List<SummaryTransaction> getAllTransaction(Map<String, Map<String, BigDecimal>> transactionMap,
													Map<String, Integer> rewardMap) throws ParseException {
	
		
		
		List<SummaryTransaction>  List = new CopyOnWriteArrayList<SummaryTransaction>();
		
		for(Map.Entry<String, Map<String, BigDecimal>> map : transactionMap.entrySet()) {
			SummaryTransaction summary = new SummaryTransaction();
			summary.setCustomerName(map.getKey());
		
			List.add(summary);
			
		}
		List<SummaryTransaction> finalList = getRewardsPerMonth(transactionMap, List);
		
		
		
		
		return finalList;
	}

	private List<SummaryTransaction> getRewardsPerMonth(Map<String, Map<String, BigDecimal>> TransactionMap,
			List<SummaryTransaction> List) throws ParseException {
	
		List<SummaryTransaction> resultList = new ArrayList<SummaryTransaction>();
		
		
		for(SummaryTransaction tx : List) {
			Map<String, Integer> txmap = new HashMap<>();
			String customerName = tx.getCustomerName();
			int totalRewardPoints=0;
			Map<String, BigDecimal> nestedMap = TransactionMap.get(customerName);
			
			for(Map.Entry<String, BigDecimal> map : nestedMap.entrySet()) {
				
				String date = map.getKey();
				
			    Date date1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);  
				
				String month = new SimpleDateFormat("MMMM").format(date1);
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
	
	
	
	private String addTimeToDate(String dateString) throws ParseException {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeString = df.format(new Date()).substring(10);
		
		dateString = dateString+" "+timeString;
		Date startUserDate = df.parse(dateString);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String strDate = dateFormat.format(startUserDate);  
		
		
		return strDate;
	}
	
    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
	
	
	
	

}
