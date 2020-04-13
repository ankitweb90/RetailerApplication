# RetailerApplication

1. Add a Transaction: 
   http://localhost:8080/retail/Transaction
   JSON Payload ex-
   
   {
	    "customerName": "David",
	    "transactionDate": "2019-12-10",
	    "transactionAmount": 90.00
	 }
   
 2. Display Total transactions of each customer
    http://localhost:8080/retail/transactions
    
    Sample JSON Response- 
    
    {
    "David": {
        "2019-11-10T00:00:00.000+0000": 60,
        "2019-11-20T00:00:00.000+0000": 120,
        "2019-08-10T00:00:00.000+0000": 100
    },
    "Sophie": {
        "2019-11-10T00:00:00.000+0000": 20,
        "2019-12-10T00:00:00.000+0000": 90
    }
}
    
 3. Display monthwise reward points per customer
    http://localhost:8080/retail/rewardslist
    
    Sample JSON Response-
    
    [{
        "customerName": "David",
        "rewardsPerMonth": {
                 "November": 100,
                 "August": 50
    },
        "totalRewards": 150
    }, {
        "customerName": "Sophie",
        "rewardsPerMonth": {
                 "December": 40,
                 "November": 0
    },
        "totalRewards": 40
}]

4. Display Total reward per customer
   http://localhost:8080/retail/totalRewardPerCustomer
   
   Sample JSON Response - 
   
   {
        "ankit": 150,
        "sophie": 40
  }

 
